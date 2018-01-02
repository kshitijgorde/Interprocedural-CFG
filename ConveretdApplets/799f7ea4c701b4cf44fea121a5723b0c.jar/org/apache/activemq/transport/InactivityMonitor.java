// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport;

import org.slf4j.LoggerFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;
import java.io.IOException;
import org.apache.activemq.command.KeepAliveInfo;
import java.util.concurrent.ThreadFactory;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.thread.SchedulerTimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.command.WireFormatInfo;
import java.util.Timer;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;

public class InactivityMonitor extends TransportFilter
{
    private static final Logger LOG;
    private static ThreadPoolExecutor ASYNC_TASKS;
    private static int CHECKER_COUNTER;
    private static long DEFAULT_CHECK_TIME_MILLS;
    private static Timer READ_CHECK_TIMER;
    private static Timer WRITE_CHECK_TIMER;
    private WireFormatInfo localWireFormatInfo;
    private WireFormatInfo remoteWireFormatInfo;
    private final AtomicBoolean monitorStarted;
    private final AtomicBoolean commandSent;
    private final AtomicBoolean inSend;
    private final AtomicBoolean failed;
    private final AtomicBoolean commandReceived;
    private final AtomicBoolean inReceive;
    private final AtomicInteger lastReceiveCounter;
    private SchedulerTimerTask writeCheckerTask;
    private SchedulerTimerTask readCheckerTask;
    private boolean ignoreRemoteWireFormat;
    private boolean ignoreAllWireFormatInfo;
    private long readCheckTime;
    private long writeCheckTime;
    private long initialDelayTime;
    private boolean useKeepAlive;
    private boolean keepAliveResponseRequired;
    private WireFormat wireFormat;
    private final Runnable readChecker;
    private final Runnable writeChecker;
    private ThreadFactory factory;
    
    private boolean allowReadCheck(final long elapsed) {
        return elapsed > this.readCheckTime * 9L / 10L;
    }
    
    public InactivityMonitor(final Transport next, final WireFormat wireFormat) {
        super(next);
        this.monitorStarted = new AtomicBoolean(false);
        this.commandSent = new AtomicBoolean(false);
        this.inSend = new AtomicBoolean(false);
        this.failed = new AtomicBoolean(false);
        this.commandReceived = new AtomicBoolean(true);
        this.inReceive = new AtomicBoolean(false);
        this.lastReceiveCounter = new AtomicInteger(0);
        this.ignoreRemoteWireFormat = false;
        this.ignoreAllWireFormatInfo = false;
        this.readCheckTime = InactivityMonitor.DEFAULT_CHECK_TIME_MILLS;
        this.writeCheckTime = InactivityMonitor.DEFAULT_CHECK_TIME_MILLS;
        this.initialDelayTime = InactivityMonitor.DEFAULT_CHECK_TIME_MILLS;
        this.useKeepAlive = true;
        this.readChecker = new Runnable() {
            long lastRunTime;
            
            @Override
            public void run() {
                final long now = System.currentTimeMillis();
                final long elapsed = now - this.lastRunTime;
                if (this.lastRunTime != 0L && InactivityMonitor.LOG.isDebugEnabled()) {
                    InactivityMonitor.LOG.debug("" + elapsed + " ms elapsed since last read check.");
                }
                if (!InactivityMonitor.this.allowReadCheck(elapsed)) {
                    InactivityMonitor.LOG.debug("Aborting read check.. Not enough time elapsed since last read check.");
                    return;
                }
                this.lastRunTime = now;
                InactivityMonitor.this.readCheck();
            }
        };
        this.writeChecker = new Runnable() {
            long lastRunTime;
            
            @Override
            public void run() {
                final long now = System.currentTimeMillis();
                if (this.lastRunTime != 0L && InactivityMonitor.LOG.isDebugEnabled()) {
                    InactivityMonitor.LOG.debug(this + " " + (now - this.lastRunTime) + " ms elapsed since last write check.");
                }
                this.lastRunTime = now;
                InactivityMonitor.this.writeCheck();
            }
        };
        this.factory = new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable, "InactivityMonitor Async Task: " + runnable);
                thread.setDaemon(true);
                return thread;
            }
        };
        this.wireFormat = wireFormat;
        if (this.wireFormat == null) {
            this.ignoreAllWireFormatInfo = true;
        }
    }
    
    @Override
    public void start() throws Exception {
        this.next.start();
        this.startMonitorThreads();
    }
    
    @Override
    public void stop() throws Exception {
        this.stopMonitorThreads();
        this.next.stop();
    }
    
    final void writeCheck() {
        if (this.inSend.get()) {
            if (InactivityMonitor.LOG.isTraceEnabled()) {
                InactivityMonitor.LOG.trace("A send is in progress");
            }
            return;
        }
        if (!this.commandSent.get() && this.useKeepAlive) {
            if (InactivityMonitor.LOG.isTraceEnabled()) {
                InactivityMonitor.LOG.trace(this + " no message sent since last write check, sending a KeepAliveInfo");
            }
            InactivityMonitor.ASYNC_TASKS.execute(new Runnable() {
                @Override
                public void run() {
                    if (InactivityMonitor.this.monitorStarted.get()) {
                        try {
                            final KeepAliveInfo info = new KeepAliveInfo();
                            info.setResponseRequired(InactivityMonitor.this.keepAliveResponseRequired);
                            InactivityMonitor.this.oneway(info);
                        }
                        catch (IOException e) {
                            InactivityMonitor.this.onException(e);
                        }
                    }
                }
            });
        }
        else if (InactivityMonitor.LOG.isTraceEnabled()) {
            InactivityMonitor.LOG.trace(this + " message sent since last write check, resetting flag");
        }
        this.commandSent.set(false);
    }
    
    final void readCheck() {
        final int currentCounter = this.next.getReceiveCounter();
        final int previousCounter = this.lastReceiveCounter.getAndSet(currentCounter);
        if (this.inReceive.get() || currentCounter != previousCounter) {
            if (InactivityMonitor.LOG.isTraceEnabled()) {
                InactivityMonitor.LOG.trace("A receive is in progress");
            }
            return;
        }
        if (!this.commandReceived.get()) {
            if (InactivityMonitor.LOG.isDebugEnabled()) {
                InactivityMonitor.LOG.debug("No message received since last read check for " + this.toString() + "! Throwing InactivityIOException.");
            }
            InactivityMonitor.ASYNC_TASKS.execute(new Runnable() {
                @Override
                public void run() {
                    InactivityMonitor.this.onException(new InactivityIOException("Channel was inactive for too (>" + InactivityMonitor.this.readCheckTime + ") long: " + InactivityMonitor.this.next.getRemoteAddress()));
                }
            });
        }
        else if (InactivityMonitor.LOG.isTraceEnabled()) {
            InactivityMonitor.LOG.trace("Message received since last read check, resetting flag: ");
        }
        this.commandReceived.set(false);
    }
    
    @Override
    public void onCommand(final Object command) {
        this.commandReceived.set(true);
        this.inReceive.set(true);
        try {
            if (command.getClass() == KeepAliveInfo.class) {
                final KeepAliveInfo info = (KeepAliveInfo)command;
                if (info.isResponseRequired()) {
                    try {
                        info.setResponseRequired(false);
                        this.oneway(info);
                    }
                    catch (IOException e) {
                        this.onException(e);
                    }
                }
            }
            else {
                if (command.getClass() == WireFormatInfo.class) {
                    synchronized (this) {
                        IOException error = null;
                        this.remoteWireFormatInfo = (WireFormatInfo)command;
                        try {
                            this.startMonitorThreads();
                        }
                        catch (IOException e2) {
                            error = e2;
                        }
                        if (error != null) {
                            this.onException(error);
                        }
                    }
                }
                synchronized (this.readChecker) {
                    this.transportListener.onCommand(command);
                }
            }
        }
        finally {
            this.inReceive.set(false);
        }
    }
    
    @Override
    public void oneway(final Object o) throws IOException {
        synchronized (this.inSend) {
            this.inSend.set(true);
            try {
                if (this.failed.get()) {
                    throw new InactivityIOException("Cannot send, channel has already failed: " + this.next.getRemoteAddress());
                }
                if (o.getClass() == WireFormatInfo.class) {
                    synchronized (this) {
                        this.localWireFormatInfo = (WireFormatInfo)o;
                        this.startMonitorThreads();
                    }
                }
                this.next.oneway(o);
            }
            finally {
                this.commandSent.set(true);
                this.inSend.set(false);
            }
        }
    }
    
    @Override
    public void onException(final IOException error) {
        if (this.failed.compareAndSet(false, true)) {
            this.stopMonitorThreads();
            this.transportListener.onException(error);
        }
    }
    
    public void setKeepAliveResponseRequired(final boolean val) {
        this.keepAliveResponseRequired = val;
    }
    
    public void setUseKeepAlive(final boolean val) {
        this.useKeepAlive = val;
    }
    
    public void setIgnoreRemoteWireFormat(final boolean val) {
        this.ignoreRemoteWireFormat = val;
    }
    
    public long getReadCheckTime() {
        return this.readCheckTime;
    }
    
    public void setReadCheckTime(final long readCheckTime) {
        this.readCheckTime = readCheckTime;
    }
    
    public long getInitialDelayTime() {
        return this.initialDelayTime;
    }
    
    public void setInitialDelayTime(final long initialDelayTime) {
        this.initialDelayTime = initialDelayTime;
    }
    
    private synchronized void startMonitorThreads() throws IOException {
        if (this.monitorStarted.get()) {
            return;
        }
        if (!this.configuredOk()) {
            return;
        }
        if (this.readCheckTime > 0L) {
            this.monitorStarted.set(true);
            this.writeCheckerTask = new SchedulerTimerTask(this.writeChecker);
            this.readCheckerTask = new SchedulerTimerTask(this.readChecker);
            this.writeCheckTime = ((this.readCheckTime > 3L) ? (this.readCheckTime / 3L) : this.readCheckTime);
            synchronized (InactivityMonitor.class) {
                if (InactivityMonitor.CHECKER_COUNTER == 0) {
                    InactivityMonitor.ASYNC_TASKS = this.createExecutor();
                    InactivityMonitor.READ_CHECK_TIMER = new Timer("InactivityMonitor ReadCheck", true);
                    InactivityMonitor.WRITE_CHECK_TIMER = new Timer("InactivityMonitor WriteCheck", true);
                }
                ++InactivityMonitor.CHECKER_COUNTER;
                InactivityMonitor.WRITE_CHECK_TIMER.scheduleAtFixedRate(this.writeCheckerTask, this.initialDelayTime, this.writeCheckTime);
                InactivityMonitor.READ_CHECK_TIMER.scheduleAtFixedRate(this.readCheckerTask, this.initialDelayTime, this.readCheckTime);
            }
        }
    }
    
    private boolean configuredOk() throws IOException {
        boolean configured = false;
        if (this.ignoreAllWireFormatInfo) {
            configured = true;
        }
        else if (this.localWireFormatInfo != null && this.remoteWireFormatInfo != null) {
            if (!this.ignoreRemoteWireFormat) {
                if (InactivityMonitor.LOG.isDebugEnabled()) {
                    InactivityMonitor.LOG.debug("Using min of local: " + this.localWireFormatInfo + " and remote: " + this.remoteWireFormatInfo);
                }
                this.readCheckTime = Math.min(this.localWireFormatInfo.getMaxInactivityDuration(), this.remoteWireFormatInfo.getMaxInactivityDuration());
                this.initialDelayTime = Math.min(this.localWireFormatInfo.getMaxInactivityDurationInitalDelay(), this.remoteWireFormatInfo.getMaxInactivityDurationInitalDelay());
            }
            else {
                if (InactivityMonitor.LOG.isDebugEnabled()) {
                    InactivityMonitor.LOG.debug("Using local: " + this.localWireFormatInfo);
                }
                this.readCheckTime = this.localWireFormatInfo.getMaxInactivityDuration();
                this.initialDelayTime = this.localWireFormatInfo.getMaxInactivityDurationInitalDelay();
            }
            configured = true;
        }
        return configured;
    }
    
    private synchronized void stopMonitorThreads() {
        if (this.monitorStarted.compareAndSet(true, false)) {
            this.readCheckerTask.cancel();
            this.writeCheckerTask.cancel();
            synchronized (InactivityMonitor.class) {
                InactivityMonitor.WRITE_CHECK_TIMER.purge();
                InactivityMonitor.READ_CHECK_TIMER.purge();
                --InactivityMonitor.CHECKER_COUNTER;
                if (InactivityMonitor.CHECKER_COUNTER == 0) {
                    InactivityMonitor.WRITE_CHECK_TIMER.cancel();
                    InactivityMonitor.READ_CHECK_TIMER.cancel();
                    InactivityMonitor.WRITE_CHECK_TIMER = null;
                    InactivityMonitor.READ_CHECK_TIMER = null;
                    InactivityMonitor.ASYNC_TASKS.shutdownNow();
                    InactivityMonitor.ASYNC_TASKS = null;
                }
            }
        }
    }
    
    private ThreadPoolExecutor createExecutor() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), this.factory);
    }
    
    static {
        LOG = LoggerFactory.getLogger(InactivityMonitor.class);
        InactivityMonitor.DEFAULT_CHECK_TIME_MILLS = 30000L;
    }
}
