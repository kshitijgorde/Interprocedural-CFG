// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import javax.swing.SwingUtilities;
import logging.LogHolder;
import logging.LogType;

public class AWTUpdateQueue
{
    private Runnable m_awtRunnable;
    private int m_jobs;
    private Object JOB_LOCK;
    private Object UPDATE_LOCK;
    
    public AWTUpdateQueue(final Runnable awtRunnable) {
        this.JOB_LOCK = new Object();
        this.UPDATE_LOCK = new Object();
        this.m_awtRunnable = awtRunnable;
        this.m_jobs = 0;
    }
    
    public void update(final boolean b) {
        synchronized (this.JOB_LOCK) {
            if (this.m_jobs >= 2 && !b) {
                return;
            }
            ++this.m_jobs;
        }
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                AWTUpdateQueue.this.doUpdateQueue();
            }
        });
        thread.setDaemon(true);
        thread.start();
        if (b) {
            try {
                thread.join();
            }
            catch (InterruptedException ex) {
                LogHolder.log(3, LogType.GUI, ex);
            }
        }
    }
    
    private void doUpdateQueue() {
        final Runnable runnable = new Runnable() {
            public void run() {
                AWTUpdateQueue.this.m_awtRunnable.run();
                AWTUpdateQueue.this.m_jobs--;
            }
        };
        synchronized (this.UPDATE_LOCK) {
            try {
                SwingUtilities.invokeAndWait(runnable);
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.GUI, ex);
            }
        }
    }
}
