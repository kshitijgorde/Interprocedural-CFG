// 
// Decompiled by Procyon v0.5.30
// 

package jlog.applet;

public class $WQC implements $IQC
{
    public static final int $I0C = 1;
    public static final int $J0C = 2;
    int state;
    $IQC $ZID;
    int $AJD;
    Thread $BJD;
    static final boolean DEBUG = false;
    
    void $CJD() {
        final Thread $bjd = this.$BJD;
        if ($bjd != null) {
            try {
                this.$BJD = null;
                $bjd.interrupt();
                if ($bjd.isAlive()) {
                    this.wait(this.$AJD + 250);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public $WQC(final $IQC $iqc) {
        this($iqc, 0);
    }
    
    public $WQC(final $IQC $zid, final int $ajd) {
        this.state = 0;
        this.$BJD = null;
        this.$ZID = $zid;
        this.$AJD = $ajd;
    }
    
    public synchronized void destroy() {
        if ((this.state & 0x1) != 0x1) {
            return;
        }
        if (this.$BJD != null) {
            this.$CJD();
        }
        if ((this.state & 0x2) == 0x2) {
            this.$ZID.stop();
            this.setState(this.state & 0xFFFFFFFD);
        }
        this.$ZID.destroy();
        this.setState(this.state & 0xFFFFFFFE);
    }
    
    public synchronized int getState() {
        return this.state;
    }
    
    public synchronized void init() {
        if ((this.state & 0x1) == 0x1) {
            return;
        }
        this.$ZID.init();
        this.setState(this.state | 0x1);
    }
    
    void setState(final int state) {
        this.state = state;
    }
    
    public synchronized void start() {
        if ((this.state & 0x2) == 0x2 && this.$BJD == null) {
            return;
        }
        if ((this.state & 0x1) != 0x1) {
            this.init();
        }
        if (this.$BJD != null) {
            this.$CJD();
        }
        else {
            this.$ZID.start();
        }
        this.setState(this.state | 0x2);
    }
    
    public synchronized void stop() {
        if ((this.state & 0x2) != 0x2 || this.$BJD != null) {
            return;
        }
        if (this.$AJD > 0) {
            (this.$BJD = new $DJD(this)).start();
        }
        else {
            this.$ZID.stop();
            this.setState(this.state & 0xFFFFFFFD);
        }
    }
}
