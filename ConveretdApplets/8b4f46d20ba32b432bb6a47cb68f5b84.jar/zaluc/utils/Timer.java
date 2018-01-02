// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.utils;

public class Timer implements Runnable
{
    private long sleepTimeMSecs;
    private Callback wakeUp;
    private int taskId;
    private Thread thread;
    
    public Timer(final long sleepTimeMSecs, final Callback wakeUp, final int taskId) {
        this.sleepTimeMSecs = sleepTimeMSecs;
        this.wakeUp = wakeUp;
        this.taskId = taskId;
        (this.thread = new Thread(this, "Timer Thread")).start();
    }
    
    public void reset() {
        this.thread.interrupt();
    }
    
    public void run() {
        do {
            try {
                Thread.sleep(this.sleepTimeMSecs);
            }
            catch (InterruptedException ex) {}
        } while (Thread.interrupted());
        this.wakeUp.callback(this.taskId, 0, null, null);
    }
}
