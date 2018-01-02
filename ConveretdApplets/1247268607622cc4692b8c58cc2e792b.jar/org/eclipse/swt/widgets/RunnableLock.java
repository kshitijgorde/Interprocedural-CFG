// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

class RunnableLock
{
    Runnable runnable;
    Thread thread;
    Throwable throwable;
    
    RunnableLock(final Runnable runnable) {
        this.runnable = runnable;
    }
    
    boolean done() {
        return this.runnable == null || this.throwable != null;
    }
    
    void run() {
        if (this.runnable != null) {
            this.runnable.run();
        }
        this.runnable = null;
    }
}
