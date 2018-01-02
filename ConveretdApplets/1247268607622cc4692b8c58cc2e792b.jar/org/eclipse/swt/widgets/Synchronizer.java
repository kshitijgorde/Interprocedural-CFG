// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.SWT;

public class Synchronizer
{
    Display display;
    int messageCount;
    RunnableLock[] messages;
    Object messageLock;
    Thread syncThread;
    static final int GROW_SIZE = 4;
    static final int MESSAGE_LIMIT = 64;
    static final boolean IS_CARBON;
    static final boolean IS_GTK;
    static /* synthetic */ Class class$0;
    
    static {
        IS_CARBON = "carbon".equals(SWT.getPlatform());
        IS_GTK = "gtk".equals(SWT.getPlatform());
    }
    
    public Synchronizer(final Display display) {
        this.messageLock = new Object();
        this.display = display;
    }
    
    void addLast(final RunnableLock runnableLock) {
        int n = 0;
        synchronized (this.messageLock) {
            if (this.messages == null) {
                this.messages = new RunnableLock[4];
            }
            if (this.messageCount == this.messages.length) {
                final RunnableLock[] messages = new RunnableLock[this.messageCount + 4];
                System.arraycopy(this.messages, 0, messages, 0, this.messageCount);
                this.messages = messages;
            }
            this.messages[this.messageCount++] = runnableLock;
            n = ((this.messageCount == 1) ? 1 : 0);
        }
        // monitorexit(this.messageLock)
        if (n != 0) {
            this.display.wakeThread();
        }
    }
    
    protected void asyncExec(final Runnable runnable) {
        if (runnable == null && !Synchronizer.IS_CARBON && !Synchronizer.IS_GTK) {
            this.display.wake();
            return;
        }
        this.addLast(new RunnableLock(runnable));
    }
    
    int getMessageCount() {
        synchronized (this.messageLock) {
            // monitorexit(this.messageLock)
            return this.messageCount;
        }
    }
    
    void releaseSynchronizer() {
        this.display = null;
        this.messages = null;
        this.messageLock = null;
        this.syncThread = null;
    }
    
    RunnableLock removeFirst() {
        synchronized (this.messageLock) {
            if (this.messageCount == 0) {
                // monitorexit(this.messageLock)
                return null;
            }
            final RunnableLock runnableLock = this.messages[0];
            System.arraycopy(this.messages, 1, this.messages, 0, --this.messageCount);
            this.messages[this.messageCount] = null;
            if (this.messageCount == 0 && this.messages.length > 64) {
                this.messages = null;
            }
            // monitorexit(this.messageLock)
            return runnableLock;
        }
    }
    
    boolean runAsyncMessages() {
        return this.runAsyncMessages(false);
    }
    
    boolean runAsyncMessages(final boolean b) {
        boolean b2 = false;
        do {
            final RunnableLock removeFirst = this.removeFirst();
            if (removeFirst == null) {
                return b2;
            }
            b2 = true;
            synchronized (removeFirst) {
                this.syncThread = removeFirst.thread;
                try {
                    removeFirst.run();
                }
                catch (Throwable throwable) {
                    SWT.error(46, removeFirst.throwable = throwable);
                }
                finally {
                    this.syncThread = null;
                    removeFirst.notifyAll();
                }
                this.syncThread = null;
                removeFirst.notifyAll();
            }
            // monitorexit(removeFirst)
        } while (b);
        return b2;
    }
    
    protected void syncExec(final Runnable runnable) {
        RunnableLock runnableLock = null;
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Synchronizer.class$0)) == null) {
            try {
                clazz = (Synchronizer.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.display == null || this.display.isDisposed()) {
                SWT.error(45);
            }
            if (!this.display.isValidThread()) {
                if (runnable == null) {
                    this.display.wake();
                    // monitorexit(clazz3)
                    return;
                }
                runnableLock = new RunnableLock(runnable);
                runnableLock.thread = Thread.currentThread();
                this.addLast(runnableLock);
            }
        }
        // monitorexit(clazz3)
        if (runnableLock == null) {
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        synchronized (runnableLock) {
            boolean b = false;
            while (!runnableLock.done()) {
                try {
                    runnableLock.wait();
                }
                catch (InterruptedException ex2) {
                    b = true;
                }
            }
            if (b) {
                Compatibility.interrupt();
            }
            if (runnableLock.throwable != null) {
                SWT.error(46, runnableLock.throwable);
            }
        }
    }
}
