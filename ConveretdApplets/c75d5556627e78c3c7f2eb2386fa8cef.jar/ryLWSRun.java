import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryLWSRun extends Thread
{
    private ryLiveWire \u01a5;
    private ryLWMenu \u01a4;
    private ryLWItem \u01a3;
    private Color[] \u01a2;
    private boolean \u01a1;
    
    public ryLWSRun(final ryLiveWire \u01a5, final Color[] \u01a3) {
        this.\u01a1 = false;
        this.\u01a5 = \u01a5;
        this.\u01a2 = \u01a3;
    }
    
    public void setMenu(final ryLWMenu \u01a5, final ryLWItem \u01a3) {
        this.\u01a4 = \u01a5;
        this.\u01a1 = this.\u01a4.\u018e;
        this.\u01a3 = \u01a3;
    }
    
    public void run() {
        if (this.\u01a3.\u0182 == 0) {
            return;
        }
        this.\u01a5.\u012f = true;
        this.\u01a5.\u0125 = false;
        this.\u01a5.\u014f.setFont(this.\u01a5.\u0148);
        this.\u01a5.\u014f.setColor(this.\u01a5.\u0166);
        this.\u01a5.\u014f.fillRect(0, 0, this.\u01a3.\u0180.width, this.\u01a5.\u0142);
        this.\u01a0();
        final int \u0183 = this.\u01a3.\u0182;
        for (int i = 0; i < \u0183; ++i) {
            if (!this.\u01a1) {
                this.\u01a3.\u0183[i].\u01ad = this.\u01a3.\u0183[i].\u01ae + this.\u01a5.\u012a - this.\u01a4.\u0194;
                this.\u01a3.\u0183[i].\u01ac = this.\u01a3.\u0183[i].\u01ad - this.\u01a2.length * this.\u01a5.\u0117;
            }
            else {
                this.\u01a3.\u0183[i].\u01ad = this.\u01a3.\u0183[i].\u01ae - (0 + this.\u01a3.\u0180.x);
                this.\u01a3.\u0183[i].\u01ac = this.\u01a3.\u0183[i].\u01ad + this.\u01a2.length * this.\u01a5.\u0117;
            }
        }
        int j = 0;
        if (!this.\u01a5.\u012f) {
            this.\u01a5.quit();
            this.\u01a0();
            return;
        }
        while (j < \u0183) {
            for (int k = 0; k < this.\u01a2.length; ++k) {
                this.\u01a5.\u014f.setColor(this.\u01a5.\u0166);
                this.\u01a5.\u014f.fillRect(0, 0, this.\u01a5.\u0143, this.\u01a5.\u0142);
                for (int l = \u0183 - 1; l >= 0; --l) {
                    final ryLWSub ryLWSub = this.\u01a3.\u0183[l];
                    if (ryLWSub.\u01ac == ryLWSub.\u01ad) {
                        this.\u01a5.\u014f.setColor(this.\u01a5.\u0161);
                    }
                    else if (!this.\u01a1 && ryLWSub.\u01ac < ryLWSub.\u01ad && l != j) {
                        this.\u01a5.\u014f.setColor(this.\u01a5.\u0166);
                    }
                    else if (this.\u01a1 && ryLWSub.\u01ac > ryLWSub.\u01ad && l != j) {
                        this.\u01a5.\u014f.setColor(this.\u01a5.\u0166);
                    }
                    else if (!this.\u01a1 && ryLWSub.\u01ac < ryLWSub.\u01ad && l == j) {
                        final ryLWSub ryLWSub2 = ryLWSub;
                        ryLWSub2.\u01ac += this.\u01a5.\u0117;
                        this.\u01a5.\u014f.setColor(this.\u01a2[k]);
                    }
                    else if (this.\u01a1 && ryLWSub.\u01ac > ryLWSub.\u01ad && l == j) {
                        final ryLWSub ryLWSub3 = ryLWSub;
                        ryLWSub3.\u01ac -= this.\u01a5.\u0117;
                        this.\u01a5.\u014f.setColor(this.\u01a2[k]);
                    }
                    this.\u01a5.\u014f.drawString(ryLWSub.\u01b2, ryLWSub.\u01ac, this.\u01a3.\u0186 - this.\u01a3.\u0180.y);
                    this.\u01a5.\u014f.drawRect(this.\u01a3.\u0180.x, this.\u01a3.\u0180.y, this.\u01a3.\u0180.width, this.\u01a3.\u0180.height);
                }
                this.\u01a5.\u014f.setColor(this.\u01a5.\u0162);
                if (!this.\u01a1) {
                    this.\u01a5.\u014f.drawString(this.\u01a3.\u018a, 2, this.\u01a3.\u0186 - this.\u01a3.\u0180.y);
                }
                else {
                    this.\u01a5.\u014f.drawString(this.\u01a3.\u018a, this.\u01a3.\u0180.width - this.\u01a3.\u0181.width + 2, this.\u01a3.\u0186 - this.\u01a3.\u0180.y);
                }
                this.\u01a0();
                if (!this.\u01a5.\u012f) {
                    break;
                }
                try {
                    Thread.sleep(this.\u01a5.\u012e);
                }
                catch (Exception ex) {}
            }
            if (!this.\u01a5.\u012f) {
                break;
            }
            ++j;
        }
        if (!this.\u01a5.\u012f) {
            this.\u01a5.quit();
            this.\u01a0();
            return;
        }
        try {
            Thread.sleep(this.\u01a5.\u012e);
        }
        catch (Exception ex2) {}
        this.\u01a5.\u0125 = true;
        this.\u01a5.\u012f = false;
        this.\u01a5.\u014f.setColor(this.\u01a5.\u0166);
        this.\u01a5.\u014f.fillRect(0, 0, this.\u01a5.\u0143, this.\u01a5.\u0142);
        this.\u01a0();
        this.\u01a5.stop();
    }
    
    private void \u01a0() {
        this.\u01a5.repaint(this.\u01a3.\u0180.x, this.\u01a3.\u0180.y, this.\u01a3.\u0180.width + 2, this.\u01a3.\u0180.height + 2);
    }
    
    protected void \u019f() {
        this.\u01a5.\u012f = false;
    }
}
