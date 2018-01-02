import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryLWStatus extends Thread
{
    private ryLiveWire \u01a8;
    private Color[] \u01a7;
    boolean \u01a6;
    
    public ryLWStatus(final ryLiveWire \u01a8, final Color[] \u01a82) {
        this.\u01a8 = \u01a8;
        this.\u01a7 = \u01a82;
    }
    
    public void run() {
        this.\u01a6 = false;
        this.\u01a8.\u014e.setFont(this.\u01a8.\u0147);
        for (int i = 0; i < this.\u01a7.length; ++i) {
            this.\u01a8.\u014e.setColor(this.\u01a8.\u015a);
            this.\u01a8.\u014e.fillRect(this.\u01a8.\u011b.x, 0, this.\u01a8.\u011b.width, this.\u01a8.\u011b.height);
            this.\u01a8.\u014e.setColor(this.\u01a7[i]);
            this.\u01a8.\u014e.drawString(this.\u01a8.\u0119, 3, this.\u01a8.\u0144.getAscent() + (this.\u01a8.\u011b.height - this.\u01a8.\u0144.getHeight()) / 2);
            this.\u01a0();
            try {
                Thread.sleep(this.\u01a8.\u012e);
            }
            catch (Exception ex) {}
            if (this.\u01a8.\u0119 == "") {
                this.\u01a6 = true;
            }
            if (this.\u01a6) {
                this.\u01a0();
                break;
            }
        }
        if (this.\u01a6) {
            this.\u01a8.\u014e.setColor(this.\u01a8.\u015a);
            this.\u01a8.\u014e.fillRect(this.\u01a8.\u011b.x, 0, this.\u01a8.\u011b.width, this.\u01a8.\u011b.height);
        }
        this.\u01a8.\u0169();
        this.\u01a0();
    }
    
    private void \u01a0() {
        this.\u01a8.repaint(this.\u01a8.\u011b.x, this.\u01a8.\u011b.y, this.\u01a8.\u011b.width, this.\u01a8.\u011b.height);
    }
    
    protected void \u019f() {
        this.\u01a6 = true;
    }
}
