// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.util;

import com.chess.util.Util;
import javax.swing.SwingUtilities;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.List;

public abstract class SwingWorkerMimic<T, V>
{
    private T result;
    private Thread workerThread;
    private Throwable workerThreadException;
    
    protected abstract T doInBackground() throws Exception;
    
    protected abstract void done();
    
    protected abstract void process(final List<V> p0);
    
    public T get() throws InterruptedException, ExecutionException {
        this.workerThread.join();
        if (this.workerThreadException != null) {
            throw new ExecutionException(this.workerThreadException);
        }
        return this.result;
    }
    
    protected void publish(final V... chunks) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SwingWorkerMimic.this.process(Arrays.asList((V[])chunks));
            }
        });
    }
    
    public void execute() {
        (this.workerThread = new Thread() {
            public void run() {
                try {
                    SwingWorkerMimic.this.result = (T)SwingWorkerMimic.this.doInBackground();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            SwingWorkerMimic.this.done();
                        }
                    });
                }
                catch (Exception ex) {
                    Util.getLogger((Class)SwingWorkerMimic.class).error((Object)"error running background task", (Throwable)ex);
                    SwingWorkerMimic.this.workerThreadException = ex;
                }
            }
        }).start();
    }
}
