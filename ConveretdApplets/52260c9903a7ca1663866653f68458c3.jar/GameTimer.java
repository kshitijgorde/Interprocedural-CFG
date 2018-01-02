// 
// Decompiled by Procyon v0.5.30
// 

public class GameTimer extends Thread implements Runnable
{
    private int count;
    private Thread timer;
    
    public GameTimer(final int count) {
        this.count = count;
        this.start();
    }
    
    public void start() {
        (this.timer = new Thread(this)).start();
    }
    
    public void run() {
        while (this.count != 0) {
            try {
                final Thread timer = this.timer;
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            --this.count;
        }
    }
    
    public int getTime() {
        return this.count;
    }
}
