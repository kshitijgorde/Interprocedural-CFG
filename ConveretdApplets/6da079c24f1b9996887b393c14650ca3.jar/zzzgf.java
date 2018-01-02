import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class zzzgf extends Panel
{
    public Image zzzqe;
    public Graphics zzzre;
    public Dimension zzzse;
    public FontMetrics zzzte;
    public Font zzzue;
    public Color zzzve;
    public Color zzzwe;
    public Color zzzxe;
    public Color zzzye;
    public int zzzze;
    public int zzzaf;
    public String zzzbf;
    public boolean zzzcf;
    public static int zzzdf;
    public String zzzef;
    private boolean l;
    
    public void update(final Graphics graphics) {
        try {
            int zzzaf;
            if (this.zzzcf) {
                zzzaf = (this.zzzse.width - this.zzzte.stringWidth(this.zzzbf)) / 2;
                while (null != null) {}
            }
            else {
                zzzaf = this.zzzaf;
            }
            final int n = zzzaf;
            this.zzzre.setColor(this.zzzve);
            this.zzzre.fillRect(0, 0, this.zzzse.width, this.zzzse.height);
            this.zzzre.setColor(this.zzzye);
            this.zzzre.fillRect(0, this.zzzaf, this.zzzse.width - 1, this.zzzse.height - this.zzzaf - 1);
            this.zzzre.setColor(this.zzzwe);
            this.zzzre.drawRect(0, this.zzzaf, this.zzzse.width - 1, this.zzzse.height - this.zzzaf - 1);
            this.zzzre.setColor(this.zzzxe);
            this.zzzre.drawString(this.zzzbf, n, this.zzzaf + this.zzzze);
            this.zzzre.drawString(this.zzzef, n, this.zzzze + this.zzzse.height / 2 + this.zzzaf / 2);
            graphics.drawImage(this.zzzqe, 0, 0, this);
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.l) {
                this.update(graphics);
            }
            if (this.zzzqe == null) {
                this.zzzqe = this.createImage(this.zzzse.width, this.zzzse.height);
                (this.zzzre = this.zzzqe.getGraphics()).setFont(this.zzzue);
                this.l = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public zzzgf() {
        final String zzzef = "";
        this.l = false;
        this.zzzef = zzzef;
    }
}
