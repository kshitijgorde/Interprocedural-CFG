// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.usage;

import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import org.slf4j.Logger;
import org.apache.activemq.Service;

public abstract class Usage<T extends Usage> implements Service
{
    private static final Logger LOG;
    protected final Object usageMutex;
    protected int percentUsage;
    protected T parent;
    private UsageCapacity limiter;
    private int percentUsageMinDelta;
    private final List<UsageListener> listeners;
    private final boolean debug;
    private String name;
    private float usagePortion;
    private final List<T> children;
    private final List<Runnable> callbacks;
    private int pollingTime;
    private final AtomicBoolean started;
    private ThreadPoolExecutor executor;
    
    public Usage(final T parent, String name, final float portion) {
        this.usageMutex = new Object();
        this.limiter = new DefaultUsageCapacity();
        this.percentUsageMinDelta = 1;
        this.listeners = new CopyOnWriteArrayList<UsageListener>();
        this.debug = Usage.LOG.isDebugEnabled();
        this.usagePortion = 1.0f;
        this.children = new CopyOnWriteArrayList<T>();
        this.callbacks = new LinkedList<Runnable>();
        this.pollingTime = 100;
        this.started = new AtomicBoolean();
        this.parent = parent;
        this.usagePortion = portion;
        if (parent != null) {
            this.limiter.setLimit((long)(parent.getLimit() * portion));
            name = parent.name + ":" + name;
        }
        this.name = name;
    }
    
    protected abstract long retrieveUsage();
    
    public void waitForSpace() throws InterruptedException {
        this.waitForSpace(0L);
    }
    
    public boolean waitForSpace(final long timeout) throws InterruptedException {
        return this.waitForSpace(timeout, 100);
    }
    
    public boolean waitForSpace(final long timeout, final int highWaterMark) throws InterruptedException {
        if (this.parent != null && !this.parent.waitForSpace(timeout, highWaterMark)) {
            return false;
        }
        synchronized (this.usageMutex) {
            this.percentUsage = this.caclPercentUsage();
            if (this.percentUsage >= highWaterMark) {
                long timeleft;
                for (long deadline = timeleft = ((timeout > 0L) ? (System.currentTimeMillis() + timeout) : Long.MAX_VALUE); timeleft > 0L; timeleft = deadline - System.currentTimeMillis()) {
                    this.percentUsage = this.caclPercentUsage();
                    if (this.percentUsage < highWaterMark) {
                        break;
                    }
                    this.usageMutex.wait(this.pollingTime);
                }
            }
            return this.percentUsage < highWaterMark;
        }
    }
    
    public boolean isFull() {
        return this.isFull(100);
    }
    
    public boolean isFull(final int highWaterMark) {
        if (this.parent != null && this.parent.isFull(highWaterMark)) {
            return true;
        }
        synchronized (this.usageMutex) {
            this.percentUsage = this.caclPercentUsage();
            return this.percentUsage >= highWaterMark;
        }
    }
    
    public void addUsageListener(final UsageListener listener) {
        this.listeners.add(listener);
    }
    
    public void removeUsageListener(final UsageListener listener) {
        this.listeners.remove(listener);
    }
    
    public long getLimit() {
        synchronized (this.usageMutex) {
            return this.limiter.getLimit();
        }
    }
    
    public void setLimit(final long limit) {
        if (this.percentUsageMinDelta < 0) {
            throw new IllegalArgumentException("percentUsageMinDelta must be greater or equal to 0");
        }
        synchronized (this.usageMutex) {
            this.limiter.setLimit(limit);
            this.usagePortion = 0.0f;
        }
        this.onLimitChange();
    }
    
    protected void onLimitChange() {
        if (this.usagePortion > 0.0f && this.parent != null) {
            synchronized (this.usageMutex) {
                this.limiter.setLimit((long)(this.parent.getLimit() * this.usagePortion));
            }
        }
        final int percentUsage;
        synchronized (this.usageMutex) {
            percentUsage = this.caclPercentUsage();
        }
        this.setPercentUsage(percentUsage);
        for (final T child : this.children) {
            child.onLimitChange();
        }
    }
    
    public float getUsagePortion() {
        synchronized (this.usageMutex) {
            return this.usagePortion;
        }
    }
    
    public void setUsagePortion(final float usagePortion) {
        synchronized (this.usageMutex) {
            this.usagePortion = usagePortion;
        }
        this.onLimitChange();
    }
    
    public int getPercentUsage() {
        synchronized (this.usageMutex) {
            return this.percentUsage;
        }
    }
    
    public int getPercentUsageMinDelta() {
        synchronized (this.usageMutex) {
            return this.percentUsageMinDelta;
        }
    }
    
    public void setPercentUsageMinDelta(final int percentUsageMinDelta) {
        if (percentUsageMinDelta < 1) {
            throw new IllegalArgumentException("percentUsageMinDelta must be greater than 0");
        }
        final int percentUsage;
        synchronized (this.usageMutex) {
            this.percentUsageMinDelta = percentUsageMinDelta;
            percentUsage = this.caclPercentUsage();
        }
        this.setPercentUsage(percentUsage);
    }
    
    public long getUsage() {
        synchronized (this.usageMutex) {
            return this.retrieveUsage();
        }
    }
    
    protected void setPercentUsage(final int value) {
        synchronized (this.usageMutex) {
            final int oldValue = this.percentUsage;
            this.percentUsage = value;
            if (oldValue != value) {
                this.fireEvent(oldValue, value);
            }
        }
    }
    
    protected int caclPercentUsage() {
        if (this.limiter.getLimit() == 0L) {
            return 0;
        }
        return (int)(this.retrieveUsage() * 100L / this.limiter.getLimit() / this.percentUsageMinDelta * this.percentUsageMinDelta);
    }
    
    private void fireEvent(final int oldPercentUsage, final int newPercentUsage) {
        if (this.debug) {
            Usage.LOG.debug(this.getName() + ": usage change from: " + oldPercentUsage + "% of available memory, to: " + newPercentUsage + "% of available memory");
        }
        if (this.started.get()) {
            if (oldPercentUsage >= 100 && newPercentUsage < 100) {
                synchronized (this.usageMutex) {
                    this.usageMutex.notifyAll();
                    if (!this.callbacks.isEmpty()) {
                        for (final Runnable callback : new ArrayList<Runnable>(this.callbacks)) {
                            this.getExecutor().execute(callback);
                        }
                        this.callbacks.clear();
                    }
                }
            }
            if (!this.listeners.isEmpty()) {
                final Runnable listenerNotifier = new Runnable() {
                    @Override
                    public void run() {
                        for (final UsageListener l : Usage.this.listeners) {
                            l.onUsageChanged(Usage.this, oldPercentUsage, newPercentUsage);
                        }
                    }
                };
                if (this.started.get()) {
                    this.getExecutor().execute(listenerNotifier);
                }
                else {
                    Usage.LOG.warn("Not notifying memory usage change to listeners on shutdown");
                }
            }
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return "Usage(" + this.getName() + ") percentUsage=" + this.percentUsage + "%, usage=" + this.retrieveUsage() + " limit=" + this.limiter.getLimit() + " percentUsageMinDelta=" + this.percentUsageMinDelta + "%";
    }
    
    @Override
    public void start() {
        if (this.started.compareAndSet(false, true)) {
            if (this.parent != null) {
                this.parent.addChild((Usage<Usage<Usage<Usage>>>)this);
            }
            for (final T t : this.children) {
                t.start();
            }
        }
    }
    
    @Override
    public void stop() {
        if (this.started.compareAndSet(true, false)) {
            if (this.parent != null) {
                this.parent.removeChild((Usage<Usage<Usage<Usage>>>)this);
            }
            synchronized (this.usageMutex) {
                this.usageMutex.notifyAll();
                for (final Runnable callback : new ArrayList<Runnable>(this.callbacks)) {
                    callback.run();
                }
                this.callbacks.clear();
            }
            for (final T t : this.children) {
                t.stop();
            }
        }
    }
    
    private void addChild(final T child) {
        this.children.add(child);
        if (this.started.get()) {
            child.start();
        }
    }
    
    private void removeChild(final T child) {
        this.children.remove(child);
    }
    
    public boolean notifyCallbackWhenNotFull(final Runnable callback) {
        if (this.parent != null) {
            final Runnable r = new Runnable() {
                @Override
                public void run() {
                    synchronized (Usage.this.usageMutex) {
                        if (Usage.this.percentUsage >= 100) {
                            Usage.this.callbacks.add(callback);
                        }
                        else {
                            callback.run();
                        }
                    }
                }
            };
            if (this.parent.notifyCallbackWhenNotFull(r)) {
                return true;
            }
        }
        synchronized (this.usageMutex) {
            if (this.percentUsage >= 100) {
                this.callbacks.add(callback);
                return true;
            }
            return false;
        }
    }
    
    public UsageCapacity getLimiter() {
        return this.limiter;
    }
    
    public void setLimiter(final UsageCapacity limiter) {
        this.limiter = limiter;
    }
    
    public int getPollingTime() {
        return this.pollingTime;
    }
    
    public void setPollingTime(final int pollingTime) {
        this.pollingTime = pollingTime;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public T getParent() {
        return this.parent;
    }
    
    public void setParent(final T parent) {
        this.parent = parent;
    }
    
    public void setExecutor(final ThreadPoolExecutor executor) {
        this.executor = executor;
    }
    
    public ThreadPoolExecutor getExecutor() {
        return this.executor;
    }
    
    static {
        LOG = LoggerFactory.getLogger(Usage.class);
    }
}
