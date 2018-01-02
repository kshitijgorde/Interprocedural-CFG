import java.io.File;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class schedThread implements Runnable
{
    static final long MINCALLREUSE = 120000L;
    Vector<IAXCall> callarray;
    long now;
    long lastPresenceUpdateSent;
    int presenceUpdateDelayInSeconds;
    String callTarget;
    int msUntilExit;
    static boolean nowPlayingTones;
    private static File spoolPath;
    
    public schedThread() {
        this.lastPresenceUpdateSent = 0L;
        this.presenceUpdateDelayInSeconds = 30;
        this.callTarget = "";
        this.msUntilExit = -1;
        this.callarray = IAXCall.getCallarray();
        System.out.println("Call Array was initialized");
        System.out.println(this.callarray.size());
        schedThread.spoolPath = new File(Starphone.getClientHomePath() + File.separator + "spool");
    }
    
    public schedThread(final int ms) {
        this.lastPresenceUpdateSent = 0L;
        this.presenceUpdateDelayInSeconds = 30;
        this.callTarget = "";
        this.msUntilExit = -1;
        this.msUntilExit = ms;
    }
    
    public void run() {
        if (this.msUntilExit > -1) {
            try {
                Thread.sleep(this.msUntilExit);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.exit(0);
        }
        Starphone.mw.repaint();
        System.out.println("repainted");
        while (true) {
            this.now = System.currentTimeMillis();
            if (this.now > this.lastPresenceUpdateSent + this.presenceUpdateDelayInSeconds * 1000 || this.lastPresenceUpdateSent == 0L) {
                Starphone.genCall(this.callTarget);
                this.lastPresenceUpdateSent = this.now;
            }
            for (int i = 1; i < this.callarray.size(); ++i) {
                this.now = System.currentTimeMillis();
                if (this.callarray.get(i).endTime != 0L && this.now - this.callarray.get(i).endTime > 120000L) {
                    this.callarray.get(i).scallno = -1;
                    this.callarray.get(i).endTime = 0L;
                }
            }
            if (schedThread.nowPlayingTones) {
                IAXCall.playTwoTones(440, 480, 2);
                try {
                    Thread.sleep(3000L);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
    static {
        schedThread.nowPlayingTones = false;
        schedThread.spoolPath = null;
    }
}
