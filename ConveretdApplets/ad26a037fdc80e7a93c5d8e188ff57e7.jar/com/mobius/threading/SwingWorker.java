// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

import javax.swing.SwingUtilities;

public abstract class SwingWorker
{
    private Object value;
    private ThreadVar threadVar;
    
    protected synchronized Object getValue() {
        return this.value;
    }
    
    private synchronized void setValue(final Object value) {
        this.value = value;
    }
    
    public abstract Object construct();
    
    public void finished() {
    }
    
    public void interrupt() {
        final Thread value = this.threadVar.get();
        if (value != null) {
            value.interrupt();
        }
        this.threadVar.clear();
    }
    
    public Object get() {
        while (true) {
            final Thread value = this.threadVar.get();
            if (value == null) {
                break;
            }
            try {
                value.join();
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return this.getValue();
    }
    
    public SwingWorker() {
        this.threadVar = new ThreadVar(new Thread(new Runnable() {
            private final /* synthetic */ Runnable val$doFinished = new Runnable() {
                public void run() {
                    SwingWorker.this.finished();
                }
            };
            
            public void run() {
                try {
                    SwingWorker.this.setValue(SwingWorker.this.construct());
                }
                finally {
                    SwingWorker.this.threadVar.clear();
                }
                SwingUtilities.invokeLater(this.val$doFinished);
            }
        }));
    }
    
    public void start() {
        final Thread value = this.threadVar.get();
        if (value != null) {
            value.start();
        }
    }
    
    private static class ThreadVar
    {
        private Thread thread;
        
        ThreadVar(final Thread thread) {
            this.thread = thread;
        }
        
        synchronized Thread get() {
            return this.thread;
        }
        
        synchronized void clear() {
            this.thread = null;
        }
    }
}
