import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryLWRun extends Thread
{
    private ryLiveWire \u019e;
    private ryLWMenu \u019d;
    private Color[] \u019c;
    private boolean \u019b;
    private boolean \u019a;
    
    public ryLWRun(final ryLiveWire \u019e, final Color[] \u026f) {
        this.\u019b = false;
        this.\u019a = true;
        this.\u019e = \u019e;
        this.\u019c = \u026f;
    }
    
    public void setMenu(final ryLWMenu \u0272, final boolean \u019a) {
        this.\u019d = \u0272;
        this.\u019b = this.\u019d.\u018e;
        this.\u019a = \u019a;
    }
    
    public void run() {
        this.\u019e.\u0130 = true;
        this.\u019e.\u0126 = -1;
        this.\u019e.\u0150.setFont(this.\u019e.\u0148);
        this.\u019e.\u0150.setColor(this.\u019e.\u0166);
        this.\u019e.\u0150.fillRect(0, 0, this.\u019e.\u0143, this.\u019e.\u0142);
        this.\u019e.repaint();
        final int \u0195 = this.\u019d.\u0195;
        for (int i = 0; i < \u0195; ++i) {
            this.\u019d.\u0196[i].\u0185 = this.\u019d.\u0196[i].\u0186 - this.\u019e.\u013a;
            this.\u019d.\u0196[i].\u0184 = this.\u019d.\u0196[0].\u0185 - this.\u019e.\u0137;
        }
        int j = 0;
        if (!this.\u019e.\u0130) {
            this.\u019e.stop();
            this.\u01a0(false);
            return;
        }
        while (j < \u0195) {
            if (this.\u019a) {
                this.\u019e.\u016a(this.\u019d.\u018b);
            }
            for (int k = 0; k < this.\u019c.length; ++k) {
                this.\u019e.\u0150.setColor(this.\u019e.\u0166);
                this.\u019e.\u0150.fillRect(0, 0, this.\u019e.\u0143, this.\u019e.\u0142);
                for (int l = \u0195 - 1; l >= 0; --l) {
                    final ryLWItem ryLWItem = this.\u019d.\u0196[l];
                    if (ryLWItem.\u0184 == ryLWItem.\u0185) {
                        this.\u019e.\u0150.setColor(this.\u019e.\u0163);
                    }
                    else if (ryLWItem.\u0184 < ryLWItem.\u0185 && l != j) {
                        final ryLWItem ryLWItem2 = ryLWItem;
                        ++ryLWItem2.\u0184;
                        this.\u019e.\u0150.setColor(this.\u019e.\u0166);
                    }
                    else if (ryLWItem.\u0184 < ryLWItem.\u0185 && l == j) {
                        final ryLWItem ryLWItem3 = ryLWItem;
                        ++ryLWItem3.\u0184;
                        this.\u019e.\u0150.setColor(this.\u019c[k]);
                    }
                    this.\u019e.\u0150.drawString(ryLWItem.\u018a, ryLWItem.\u0187, ryLWItem.\u0184);
                    if (this.\u019e.\u0124) {
                        this.\u019e.\u0150.setColor(this.\u019e.\u015e);
                        int n = 3;
                        if (!this.\u019e.\u011f) {
                            n = 5;
                        }
                        if (this.\u019e.\u0123 == 0 || this.\u019e.\u0123 == 2) {
                            n = 0;
                        }
                        if (!this.\u019b) {
                            this.\u019e.\u0150.fillRect(this.\u019d.\u0194 - 3, 0, 1, this.\u019d.\u0196[j].\u0184 - n);
                        }
                        else {
                            this.\u019e.\u0150.fillRect(this.\u019d.\u0193 + 2, 0, 1, this.\u019d.\u0196[j].\u0184 - n);
                        }
                    }
                    if (this.\u019e.\u0123 == 1 || this.\u019e.\u0123 == 3) {
                        this.\u019e.\u0150.setColor(this.\u019e.\u015c);
                        if (!this.\u019e.\u011f) {
                            if (!this.\u019b) {
                                this.\u019e.\u0150.fillRect(this.\u019d.\u0194 - 5, this.\u019d.\u0196[j].\u0184 - 4, 5, 4);
                            }
                            else {
                                this.\u019e.\u0150.fillRect(this.\u019d.\u0193, this.\u019d.\u0196[j].\u0184 - 4, 5, 4);
                            }
                        }
                        else {
                            int \u0260 = this.\u019d.\u0194 - 5;
                            if (this.\u019b) {
                                \u0260 = this.\u019d.\u0193;
                            }
                            this.\u019e.\u0150.drawLine(\u0260, this.\u019d.\u0196[j].\u0184 - 2, \u0260 + 5, this.\u019d.\u0196[j].\u0184 - 2);
                            this.\u019e.\u0150.drawLine(\u0260 + 1, this.\u019d.\u0196[j].\u0184 - 1, \u0260 + 4, this.\u019d.\u0196[j].\u0184 - 1);
                            this.\u019e.\u0150.drawLine(\u0260 + 2, this.\u019d.\u0196[j].\u0184, \u0260 + 3, this.\u019d.\u0196[j].\u0184);
                        }
                    }
                }
                this.\u01a0(false);
                if (!this.\u019e.\u0130) {
                    break;
                }
                try {
                    Thread.sleep(this.\u019e.\u012e);
                }
                catch (Exception ex) {}
            }
            if (!this.\u019e.\u0130) {
                break;
            }
            ++j;
        }
        if (!this.\u019e.\u0130) {
            this.\u019e.stop();
            return;
        }
        try {
            Thread.sleep(this.\u019e.\u012e);
        }
        catch (Exception ex2) {}
        this.\u019e.\u0130 = false;
        this.\u019e.stop();
        this.\u01a0(true);
    }
    
    private void \u01a0(final boolean b) {
        if (!this.\u019e.\u0130 && !b) {
            return;
        }
        if (!this.\u019b) {
            this.\u019e.repaint(this.\u019d.\u0194 - 5, this.\u019e.\u013a - 3, this.\u019d.\u0192 + 12, this.\u019e.\u0142);
        }
        else {
            this.\u019e.repaint(this.\u019d.\u0193 - this.\u019d.\u0192, this.\u019e.\u013a - 3, this.\u019d.\u0192 + 12, this.\u019e.\u0142);
        }
    }
    
    protected void \u019f() {
        this.\u019e.\u0130 = false;
        this.\u019e.\u0126 = -1;
    }
}
