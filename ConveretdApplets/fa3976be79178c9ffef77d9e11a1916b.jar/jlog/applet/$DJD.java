// 
// Decompiled by Procyon v0.5.30
// 

package jlog.applet;

class $DJD extends Thread
{
    $WQC $EJD;
    public static final int $FJD = 250;
    
    $DJD(final $WQC $ejd) {
        super("AppStateSyncStop");
        this.$EJD = $ejd;
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            for (int i = this.$EJD.$AJD; i > 0; i -= 250) {
                if (currentThread != this.$EJD.$BJD) {
                    break;
                }
                Thread.sleep(250L);
            }
        }
        catch (InterruptedException ex) {}
        finally {
            synchronized (this.$EJD) {
                if (currentThread == this.$EJD.$BJD) {
                    this.$EJD.$BJD = null;
                    this.$EJD.$ZID.stop();
                    this.$EJD.setState(this.$EJD.state & 0xFFFFFFFD);
                }
                this.$EJD.notifyAll();
            }
            // monitorexit(this.$EJD)
        }
    }
}
