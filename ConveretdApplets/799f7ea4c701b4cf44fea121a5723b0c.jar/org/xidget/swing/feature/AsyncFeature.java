// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.util.Hashtable;
import org.xidget.ifeature.IAsyncFeature;

public class AsyncFeature implements IAsyncFeature
{
    private Hashtable<Object, Timer> timers;
    
    public AsyncFeature() {
        this.timers = new Hashtable<Object, Timer>();
    }
    
    @Override
    public void run(final Runnable runnable) {
        SwingUtilities.invokeLater(runnable);
    }
    
    @Override
    public void runWait(final Runnable runnable) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(runnable);
    }
    
    @Override
    public void schedule(final Object o, final int n, final boolean repeats, final Runnable runnable) {
        if (n == 0 && !repeats) {
            this.run(runnable);
        }
        final Timer timer = this.timers.remove(o);
        if (timer != null) {
            timer.stop();
        }
        final Timer timer2 = new Timer(n, new Task(runnable));
        timer2.setRepeats(repeats);
        this.timers.put(o, timer2);
        timer2.start();
    }
    
    @Override
    public void cancel(final Object o) {
        final Timer timer = this.timers.remove(o);
        if (timer != null) {
            timer.stop();
        }
    }
    
    private class Task implements ActionListener
    {
        private Runnable runnable;
        
        public Task(final Runnable runnable) {
            this.runnable = runnable;
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            AsyncFeature.this.run(this.runnable);
        }
    }
}
