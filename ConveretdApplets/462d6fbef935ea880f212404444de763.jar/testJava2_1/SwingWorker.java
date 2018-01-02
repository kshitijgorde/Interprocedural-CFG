// 
// Decompiled by Procyon v0.5.30
// 

package testJava2_1;

import javax.swing.SwingUtilities;

public abstract class SwingWorker
{
    private Object value;
    private Thread thread;
    private ThreadVar threadVar;
    
    protected synchronized Object getValue() {
        return this.value;
    }
    
    private synchronized void setValue(final Object x) {
        this.value = x;
    }
    
    public abstract Object construct();
    
    public void finished() {
    }
    
    public void interrupt() {
        final Thread t = this.threadVar.get();
        if (t != null) {
            t.interrupt();
        }
        this.threadVar.clear();
    }
    
    public Object get() {
        while (true) {
            final Thread t = this.threadVar.get();
            if (t == null) {
                break;
            }
            try {
                t.join();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return this.getValue();
    }
    
    public SwingWorker() {
        final Runnable doFinished = new Runnable() {
            public void run() {
                SwingWorker.this.finished();
            }
        };
        final Runnable doConstruct = new Runnable() {
            public void run() {
                try {
                    SwingWorker.this.setValue(SwingWorker.this.construct());
                }
                finally {
                    SwingWorker.this.threadVar.clear();
                }
                SwingUtilities.invokeLater(doFinished);
            }
        };
        final Thread t = new Thread(doConstruct);
        this.threadVar = new ThreadVar(t);
    }
    
    public void start() {
        final Thread t = this.threadVar.get();
        if (t != null) {
            t.start();
        }
    }
    
    private static class ThreadVar
    {
        private Thread thread;
        
        ThreadVar(final Thread t) {
            this.thread = t;
        }
        
        synchronized Thread get() {
            return this.thread;
        }
        
        synchronized void clear() {
            this.thread = null;
        }
    }
}
