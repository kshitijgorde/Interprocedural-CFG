// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.StopThread;

public class GoTimer extends StopThread
{
    public long Interval;
    TimedBoard B;
    
    public GoTimer(final TimedBoard b, final long interval) {
        this.Interval = interval;
        this.B = b;
        this.start();
    }
    
    public void run() {
        try {
            while (!this.stopped()) {
                Thread.sleep(this.Interval);
                this.B.alarm();
            }
        }
        catch (Exception ex) {}
    }
}
