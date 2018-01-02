// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.util.ExceptionMonitor;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.polling.AbstractPollingIoProcessor;
import java.util.List;
import org.apache.mina.core.session.IoSession;

public class DefaultIoFuture implements IoFuture
{
    private final IoSession session;
    private final Object lock;
    private IoFutureListener<?> firstListener;
    private List<IoFutureListener<?>> otherListeners;
    private Object result;
    private boolean ready;
    private int waiters;
    
    public DefaultIoFuture(final IoSession session) {
        this.session = session;
        this.lock = this;
    }
    
    @Override
    public IoSession getSession() {
        return this.session;
    }
    
    public IoFuture awaitUninterruptibly() {
        try {
            this.await0(Long.MAX_VALUE, false);
        }
        catch (InterruptedException ex) {}
        return this;
    }
    
    private boolean await0(final long n, final boolean b) throws InterruptedException {
        long n2;
        if ((n2 = System.currentTimeMillis() + Long.MAX_VALUE) < 0L) {
            n2 = Long.MAX_VALUE;
        }
        synchronized (this.lock) {
            if (this.ready) {
                return this.ready;
            }
            if (Long.MAX_VALUE <= 0L) {
                return this.ready;
            }
            ++this.waiters;
            try {
                while (true) {
                    try {
                        this.lock.wait(Math.min(Long.MAX_VALUE, 5000L));
                    }
                    catch (InterruptedException ex) {}
                    if (this.ready) {
                        return true;
                    }
                    if (n2 < System.currentTimeMillis()) {
                        return this.ready;
                    }
                }
            }
            finally {
                --this.waiters;
                if (!this.ready) {
                    this.checkDeadLock();
                }
            }
        }
    }
    
    private void checkDeadLock() {
        if (!(this instanceof CloseFuture) && !(this instanceof WriteFuture) && !(this instanceof ReadFuture) && !(this instanceof ConnectFuture)) {
            return;
        }
        StackTraceElement[] stackTrace;
        StackTraceElement[] array;
        for (int length = (array = (stackTrace = Thread.currentThread().getStackTrace())).length, i = 0; i < length; ++i) {
            if (AbstractPollingIoProcessor.class.getName().equals(array[i].getClassName())) {
                new IllegalStateException("t").getStackTrace();
                throw new IllegalStateException("DEAD LOCK: " + IoFuture.class.getSimpleName() + ".await() was invoked from an I/O processor thread.  " + "Please use " + IoFutureListener.class.getSimpleName() + " or configure a proper thread model alternatively.");
            }
        }
        StackTraceElement[] array2;
        for (int length2 = (array2 = stackTrace).length, j = 0; j < length2; ++j) {
            final StackTraceElement stackTraceElement = array2[j];
            try {
                if (IoProcessor.class.isAssignableFrom(DefaultIoFuture.class.getClassLoader().loadClass(stackTraceElement.getClassName()))) {
                    throw new IllegalStateException("DEAD LOCK: " + IoFuture.class.getSimpleName() + ".await() was invoked from an I/O processor thread.  " + "Please use " + IoFutureListener.class.getSimpleName() + " or configure a proper thread model alternatively.");
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean isDone() {
        synchronized (this.lock) {
            return this.ready;
        }
    }
    
    public final void setValue(final Object result) {
        synchronized (this.lock) {
            if (this.ready) {
                return;
            }
            this.result = result;
            this.ready = true;
            if (this.waiters > 0) {
                this.lock.notifyAll();
            }
        }
        if (this.firstListener != null) {
            this.notifyListener(this.firstListener);
            this.firstListener = null;
            if (this.otherListeners != null) {
                final Iterator<IoFutureListener<?>> iterator = this.otherListeners.iterator();
                while (iterator.hasNext()) {
                    this.notifyListener(iterator.next());
                }
                this.otherListeners = null;
            }
        }
    }
    
    protected final Object getValue() {
        synchronized (this.lock) {
            return this.result;
        }
    }
    
    @Override
    public IoFuture addListener(final IoFutureListener<?> firstListener) {
        if (firstListener == null) {
            throw new IllegalArgumentException("listener");
        }
        boolean b = false;
        synchronized (this.lock) {
            if (this.ready) {
                b = true;
            }
            else if (this.firstListener == null) {
                this.firstListener = firstListener;
            }
            else {
                if (this.otherListeners == null) {
                    this.otherListeners = new ArrayList<IoFutureListener<?>>(1);
                }
                this.otherListeners.add(firstListener);
            }
        }
        if (b) {
            final IoFutureListener ioFutureListener;
            this.notifyListener(ioFutureListener);
        }
        return this;
    }
    
    private void notifyListener(final IoFutureListener ioFutureListener) {
        try {
            ioFutureListener.operationComplete(this);
        }
        catch (Throwable t) {
            ExceptionMonitor.getInstance().exceptionCaught(t);
        }
    }
}
