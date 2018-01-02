import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class zzzkg extends Panel
{
    public Color zzzjf;
    public Color zzzkf;
    public Color zzzlf;
    public Color zzzmf;
    public Color zzznf;
    public Color zzzof;
    public Color zzzpf;
    public Color zzzqf;
    public Image zzzrf;
    public Graphics zzzsf;
    public FontMetrics zzztf;
    public Dimension zzzuf;
    public Font zzzvf;
    public boolean zzzwf;
    public boolean zzzxf;
    public boolean zzzyf;
    public int zzzzf;
    public int zzzag;
    public int zzzbg;
    public int zzzcg;
    public int zzzdg;
    public int zzzeg;
    public char[][] zzzfg;
    public char[][] zzzgg;
    public char[][] zzzhg;
    public zzzke zzzig;
    private boolean Lt;
    
    public zzzkg() {
        this.Lt = false;
    }
    
    public String zzzif() {
        return this.lt(this.zzzig);
    }
    
    private String lt(final zzzke zzzke) {
        final int zzzde = zzzke.zzzde;
        final int zzzee = zzzke.zzzee;
        final StringBuffer sb = new StringBuffer();
        int i = 0;
        final StringBuffer sb2 = sb;
        while (i < zzzke.zzzud()) {
            sb2.append(this.zzzgg[zzzde][zzzee + i]);
            ++i;
        }
        return sb2.toString();
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.Lt) {
                this.update(graphics);
            }
            if (this.zzzrf == null) {
                this.zzzrf = this.createImage(this.zzzuf.width, this.zzzuf.height);
                this.zzzsf = this.zzzrf.getGraphics();
                this.setFont(this.zzzvf);
                this.Lt = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        try {
            Rectangle zzzyd = null;
            this.zzzsf.setColor(this.zzzjf);
            this.zzzsf.fillRect(0, 0, this.zzzuf.width, this.zzzuf.height);
            this.zzzsf.setColor(this.zzzpf);
            int i = 0;
            if (null != null) {}
            while (i < this.zzzag) {
                int j = 0;
                if (null != null) {}
                while (j < this.zzzbg) {
                    if (this.zzzfg[i][j] != '#') {
                        this.zzzsf.fillRect(j * this.zzzzf, i * this.zzzzf, this.zzzzf, this.zzzzf);
                    }
                    ++j;
                }
                ++i;
            }
            if (this.zzzig != null) {
                this.zzzsf.setColor(this.zzzmf);
                final Rectangle zzzxd = this.zzzig.zzzxd();
                this.zzzsf.fillRect(zzzxd.x, zzzxd.y, zzzxd.width, zzzxd.height);
                this.zzzsf.setColor(this.zzzof);
                zzzyd = this.zzzig.zzzyd();
                this.zzzsf.fillRect(zzzyd.x, zzzyd.y, this.zzzzf, this.zzzzf);
            }
            this.zzzsf.setColor(this.zzzqf);
            this.zzzsf.fillRect(this.zzzeg * this.zzzzf, 0, this.zzzzf, this.zzzag * this.zzzzf);
            if (this.zzzig != null && this.zzzig.zzzee + this.zzzig.zzzbe == this.zzzeg) {
                this.zzzsf.setColor(this.zzzqf.brighter());
                this.zzzsf.fillRect(zzzyd.x, zzzyd.y, this.zzzzf, this.zzzzf);
            }
            this.zzzsf.setColor(this.zzzkf);
            int k = 0;
            if (null != null) {}
            while (k < this.zzzag) {
                for (int l = 0; l < this.zzzbg; ++l) {
                    if (this.zzzfg[k][l] != '#') {
                        this.zzzsf.drawRect(l * this.zzzzf, k * this.zzzzf, this.zzzzf, this.zzzzf);
                    }
                }
                ++k;
            }
            if (this.zzzxf || this.zzzyf) {
                for (int n = 0; n < this.zzzag; ++n) {
                    for (int n2 = 0; n2 < this.zzzbg; ++n2) {
                        if (this.zzzhg[n][n2] != '\0') {
                            this.zzzgg[n][n2] = this.zzzhg[n][n2];
                        }
                    }
                }
            }
            int n3 = 0;
            if (null != null) {}
            while (n3 < this.zzzag) {
                for (int n4 = 0; n4 < this.zzzbg; ++n4) {
                    if (this.zzzgg[n3][n4] != '\0') {
                        this.zzzsf.setColor(this.zzzlf);
                        if (this.zzzwf && this.zzzgg[n3][n4] != this.zzzfg[n3][n4]) {
                            this.zzzsf.setColor(this.zzznf);
                        }
                        this.zzzcg = (this.zzzzf - this.zzztf.charWidth(this.zzzgg[n3][n4])) / 2;
                        this.zzzsf.drawChars(this.zzzgg[n3], n4, 1, n4 * this.zzzzf + this.zzzcg, n3 * this.zzzzf + this.zzzdg);
                    }
                }
                ++n3;
            }
            graphics.drawImage(this.zzzrf, 0, 0, this);
        }
        catch (Exception ex) {}
    }
    
    public void setFont(final Font font) {
        if (this.zzzsf != null) {
            this.zzzsf.setFont(font);
        }
    }
}
