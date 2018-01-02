import java.awt.Event;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Color;
import com.javathings.math.zzzzl;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class zzzjh extends Canvas
{
    private zzzip zzzgg;
    private zzzzl zzzhg;
    private Color zzzig;
    private Color zzzjg;
    private Hashtable zzzkg;
    private Hashtable zzzlg;
    private String zzzmg;
    public double zzzng;
    public double zzzog;
    public double zzzpg;
    public double zzzqg;
    public int zzzrg;
    private double zzzsg;
    private double zzztg;
    private double zzzug;
    private double zzzvg;
    private int zzzwg;
    private double zzzxg;
    private double zzzyg;
    private Font zzzzg;
    private FontMetrics zzzah;
    private String zzzbh;
    private int zzzch;
    private int zzzdh;
    private int zzzeh;
    private int zzzfh;
    private int zzzgh;
    private int zzzhh;
    private boolean zzzih;
    
    public zzzjh() {
        this.zzzmg = "";
        this.zzzbh = "";
        this.zzzch = -1;
        this.zzzdh = -1;
        this.zzzeh = -1;
        this.zzzfh = -1;
        this.zzzgh = 1;
        this.zzzhh = 1;
        this.zzzih = false;
        this.zzzng = -10.0;
        this.zzzog = 10.0;
        this.zzzpg = -10.0;
        this.zzzqg = 10.0;
        this.zzzrg = 100;
        this.zzzsg = this.zzzng;
        this.zzztg = this.zzzog;
        this.zzzug = this.zzzpg;
        this.zzzvg = this.zzzqg;
        this.zzzwg = this.zzzrg;
        this.zzzhg = new zzzzl();
        this.zzzmg = "x";
        this.zzzkg = new Hashtable(100);
        this.zzzlg = new Hashtable(10);
        this.zzzig = Color.black;
        this.zzzjg = Color.white;
        this.setBackground(this.zzzig);
        this.setForeground(this.zzzjg);
        this.setFont(this.zzzzg = new Font("TimesRoman", 0, 9));
        this.zzzah = this.getFontMetrics(this.zzzzg);
    }
    
    public zzzjh(final Color color, final Color color2) {
        this();
        this.zzzig = color;
        this.zzzjg = color2;
        this.setBackground(color);
        this.setForeground(color2);
    }
    
    public zzzjh(final zzzip zzzgg, final Color color, final Color color2) {
        this();
        this.zzzgg = zzzgg;
        this.zzzig = color;
        this.zzzjg = color2;
        this.setBackground(color);
        this.setForeground(color2);
    }
    
    public zzzjh(final zzzip zzzgg) {
        this();
        this.zzzgg = zzzgg;
    }
    
    public zzzjh(final zzzip zzzip, final Hashtable zzzlg) {
        this(zzzip);
        if (zzzlg != null && zzzlg instanceof Hashtable) {
            this.zzzlg = zzzlg;
        }
    }
    
    public zzzjh(final zzzip zzzip, final Hashtable hashtable, final Color color, final Color color2) {
        this(zzzip, hashtable);
        this.zzzig = color;
        this.zzzjg = color2;
        this.setBackground(color);
        this.setForeground(color2);
    }
    
    public void paint(final Graphics graphics) {
        try {
            this.zzzag(graphics);
        }
        catch (Exception ex) {
            if (this.zzzgg != null) {
                this.zzzgg.zzzhp(ex.getMessage());
            }
        }
    }
    
    public final void zzzag(final Graphics graphics) throws Exception {
        graphics.setColor(this.zzzig);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(this.zzzjg);
        this.zzzqf(graphics);
        this.zzzuf(graphics);
    }
    
    public final void zzzrf() throws Exception {
        final Graphics graphics = this.getGraphics();
        this.zzzag(graphics);
        graphics.dispose();
    }
    
    public final void zzzyf() {
        final Graphics graphics = this.getGraphics();
        this.zzzkg.clear();
        graphics.setColor(this.zzzig);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(this.zzzjg);
        this.zzzqf(graphics);
    }
    
    public final void zzzzf(final String s) {
        this.zzzdg(s);
        try {
            this.zzzrf();
        }
        catch (Exception ex) {
            if (this.zzzgg != null) {
                this.zzzgg.zzzhp(ex.getMessage());
            }
        }
    }
    
    public final void zzzbg() {
        final Graphics graphics = this.getGraphics();
        this.zzzqf(graphics);
        graphics.dispose();
    }
    
    public final void zzzqf(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        this.zzzxg = -this.zzzng / (this.zzzog - this.zzzng);
        this.zzzyg = this.zzzqg / (this.zzzqg - this.zzzpg);
        graphics.drawLine(0, (int)Math.round(height * this.zzzyg), width, (int)Math.round(height * this.zzzyg));
        graphics.drawLine((int)Math.round(width * this.zzzxg), 0, (int)Math.round(width * this.zzzxg), height);
        final double n = width / Math.abs(this.zzzog - this.zzzng);
        for (double n2 = width * this.zzzxg; n2 < width; n2 += n) {
            graphics.drawLine((int)Math.round(n2), (int)Math.round(height * this.zzzyg), (int)Math.round(n2), (int)Math.round(height * this.zzzyg) - 2);
        }
        for (double n3 = width * this.zzzxg; n3 > 0.0; n3 -= n) {
            graphics.drawLine((int)Math.round(n3), (int)Math.round(height * this.zzzyg), (int)Math.round(n3), (int)Math.round(height * this.zzzyg) - 2);
        }
        final double n4 = height / Math.abs(this.zzzqg - this.zzzpg);
        for (double n5 = height * this.zzzyg; n5 < height; n5 += n4) {
            graphics.drawLine((int)Math.round(width * this.zzzxg), (int)Math.round(n5), (int)Math.round(width * this.zzzxg) + 2, (int)Math.round(n5));
        }
        for (double n6 = height * this.zzzyg; n6 > 0.0; n6 -= n4) {
            graphics.drawLine((int)Math.round(width * this.zzzxg), (int)Math.round(n6), (int)Math.round(width * this.zzzxg) + 2, (int)Math.round(n6));
        }
    }
    
    public final void zzzxf(final String s, final Graphics graphics) throws Exception {
        final double n = (this.zzzog - this.zzzng) / this.zzzrg;
        final boolean[] array = new boolean[this.zzzrg];
        final int[] array2 = new int[this.zzzrg];
        final int[] array3 = new int[this.zzzrg];
        final int height = this.size().height;
        final int width = this.size().width;
        int n2 = 0;
        double zzzjl = 0.0;
        int i = 0;
        if (this.zzzvf()) {
            this.zzzrf();
        }
        if (s.equals("") || this.zzzcg(s)) {
            return;
        }
        try {
            while (i < this.zzzrg) {
                array2[i] = (int)Math.round(i * width / this.zzzrg);
                final double n3 = zzzjl;
                final int n4 = n2;
                this.zzzlg.put(this.zzzmg, String.valueOf(this.zzzng + i * n));
                zzzjl = this.zzzhg.zzzjl(s, this.zzzlg);
                if (Double.isNaN(zzzjl) || Double.isInfinite(zzzjl)) {
                    n2 = 0;
                }
                else {
                    n2 = 1;
                }
                array[i] = false;
                final double n5;
                if (zzzjl > (n5 = Math.abs(this.zzzqg - this.zzzpg) + 0.5 * (this.zzzqg + this.zzzpg))) {
                    array3[i] = (int)Math.round(height * this.zzzyg - n5 * height / (this.zzzqg - this.zzzpg));
                    n2 = 0;
                }
                else {
                    final double n6;
                    if (zzzjl < (n6 = -Math.abs(this.zzzqg - this.zzzpg) + 0.5 * (this.zzzqg + this.zzzpg))) {
                        array3[i] = (int)Math.round(height * this.zzzyg - n6 * height / (this.zzzqg - this.zzzpg));
                        n2 = 0;
                    }
                    else {
                        array3[i] = (int)Math.round(height * this.zzzyg - zzzjl * height / (this.zzzqg - this.zzzpg));
                    }
                }
                if (i > 0 && n4 != 0 && n2 != 0 && this.zzzpf(s, this.zzzng + (i - 1) * n, this.zzzng + i * n, n3, zzzjl, 1)) {
                    array[i] = true;
                }
                ++i;
            }
            for (int j = 0; j < this.zzzrg; ++j) {
                if (j > 0 && array[j]) {
                    graphics.drawLine(array2[j - 1], array3[j - 1], array2[j], array3[j]);
                }
            }
            this.zzzof(s, array2, array3, array, graphics.getColor());
        }
        finally {
            this.zzzlg.remove(this.zzzmg);
        }
    }
    
    public final boolean zzzpf(final String s, final double n, final double n2, final double n3, final double n4, final int n5) {
        final int n6 = 8000;
        if (Math.abs((n4 - n3) / (n2 - n)) < 4 * n5) {
            return true;
        }
        if (Math.abs((n4 - n3) / (n2 - n)) > n6) {
            return false;
        }
        this.zzzlg.put(this.zzzmg, String.valueOf((n + n2) / 2.0));
        double zzzjl;
        try {
            zzzjl = this.zzzhg.zzzjl(s, this.zzzlg);
        }
        catch (Exception ex) {
            return false;
        }
        if (Double.isNaN(zzzjl) || Double.isInfinite(zzzjl)) {
            return false;
        }
        if (Math.abs((n4 - zzzjl) / (n2 - n)) < Math.abs((zzzjl - n3) / (n2 - n))) {
            return this.zzzpf(s, n, (n + n2) / 2.0, n3, zzzjl, 2 * n5);
        }
        return this.zzzpf(s, (n + n2) / 2.0, n2, zzzjl, n4, 2 * n5);
    }
    
    public final void zzzwf(final zzzp zzzp, final Graphics graphics) throws Exception {
        int i = 1;
        graphics.setColor(zzzp.zzze);
        if (zzzp.zzzc(this.zzzng, this.zzzog, this.zzzpg, this.zzzqg, this.zzzrg, this.size().width, this.size().height)) {
            this.zzzdg(zzzp.zzzd);
            this.zzzxf(zzzp.zzzd, graphics);
            return;
        }
        while (i < zzzp.zzzm) {
            if (i > 0 && zzzp.zzzh[i]) {
                graphics.drawLine(zzzp.zzzf[i - 1], zzzp.zzzg[i - 1], zzzp.zzzf[i], zzzp.zzzg[i]);
            }
            ++i;
        }
    }
    
    public final void zzzsf(final String s) throws Exception {
        final Graphics graphics = this.getGraphics();
        this.zzzxf(s, graphics);
        graphics.dispose();
    }
    
    public final void zzzuf(final Graphics graphics) throws Exception {
        final Enumeration<zzzp> elements = this.zzzkg.elements();
        while (elements.hasMoreElements()) {
            this.zzzwf(elements.nextElement(), graphics);
        }
    }
    
    public final void zzzeg() throws Exception {
        final Graphics graphics = this.getGraphics();
        this.zzzuf(graphics);
        graphics.dispose();
    }
    
    private final boolean zzzof(final String s, final int[] array, final int[] array2, final boolean[] array3, final Color color) {
        final zzzp zzzp = new zzzp(s, array, array2, array3, color, this.zzzng, this.zzzog, this.zzzpg, this.zzzqg, this.zzzrg, this.size().width, this.size().height);
        if (!this.zzzkg.containsKey(s)) {
            this.zzzkg.put(s, zzzp);
            return true;
        }
        return false;
    }
    
    public final Enumeration zzzfg() {
        return this.zzzkg.keys();
    }
    
    public final void zzztf(final String zzzmg) {
        this.zzzmg = zzzmg;
    }
    
    private final void zzzdg(final String s) {
        this.zzzkg.remove(s);
    }
    
    private final boolean zzzcg(final String s) {
        return this.zzzkg.containsKey(s);
    }
    
    public final boolean zzzvf() {
        if (this.zzzrg != this.zzzwg || this.zzzng != this.zzzsg || this.zzzog != this.zzztg || this.zzzpg != this.zzzug || this.zzzqg != this.zzzvg) {
            this.zzzwg = this.zzzrg;
            this.zzzsg = this.zzzng;
            this.zzztg = this.zzzog;
            this.zzzug = this.zzzpg;
            this.zzzvg = this.zzzqg;
            return true;
        }
        return false;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.zzzeh = n;
        this.zzzfh = n2;
        this.zzzch = n;
        this.zzzdh = n2;
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.zzzih) {
            this.zzzih = false;
            final int width = this.size().width;
            final int height = this.size().height;
            final double n3 = this.zzzog - this.zzzng;
            final double n4 = this.zzzqg - this.zzzpg;
            if (this.zzzgh > 5 || this.zzzhh > 5) {
                this.zzzog = this.zzzng + n * n3 / width;
                this.zzzng += this.zzzeh * n3 / width;
                this.zzzpg = this.zzzqg - n2 * n4 / height;
                this.zzzqg -= this.zzzfh * n4 / height;
                if (this.zzzng > this.zzzog) {
                    final double zzzog = this.zzzog;
                    this.zzzog = this.zzzng;
                    this.zzzng = zzzog;
                }
                if (this.zzzpg > this.zzzqg) {
                    final double zzzqg = this.zzzqg;
                    this.zzzqg = this.zzzpg;
                    this.zzzpg = zzzqg;
                }
            }
            try {
                this.zzzrf();
            }
            catch (Exception ex) {}
            if (this.zzzgg != null) {
                this.zzzgg.zzzgp(this.zzzng, this.zzzog, this.zzzpg, this.zzzqg);
            }
        }
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(this.zzzig);
        graphics.setXORMode(new Color(16776960));
        if (this.zzzih) {
            graphics.drawRect(this.zzzch, this.zzzdh, this.zzzgh, this.zzzhh);
        }
        this.zzzgh = Math.abs(n - this.zzzeh);
        this.zzzhh = Math.abs(n2 - this.zzzfh);
        this.zzzch = ((n < this.zzzeh) ? n : this.zzzch);
        this.zzzdh = ((n2 < this.zzzfh) ? n2 : this.zzzdh);
        graphics.drawRect(this.zzzch, this.zzzdh, this.zzzgh, this.zzzhh);
        this.zzzih = true;
        return this.mouseMove(event, n, n2);
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        final int n3 = 8;
        final StringBuffer sb = new StringBuffer(50);
        final int width = this.size().width;
        final int height = this.size().height;
        final int stringWidth = this.zzzah.stringWidth(this.zzzbh);
        final int height2 = this.zzzah.getHeight();
        Graphics graphics = this.getGraphics();
        sb.append("( ");
        sb.append(this.zzzng + n * (Math.abs(this.zzzog - this.zzzng) / width));
        sb.append(" ; ").append(this.zzzqg - n2 * (Math.abs(this.zzzqg - this.zzzpg) / height));
        sb.append(" )");
        this.zzzbh = sb.toString();
        final int stringWidth2 = this.zzzah.stringWidth(this.zzzbh);
        final int n4 = (stringWidth2 < stringWidth) ? stringWidth : stringWidth2;
        graphics.setColor(this.zzzig);
        graphics.fillRect(width - n4 - n3, height - height2 - n3, n4 + 2, height2 + 2);
        if (stringWidth + 2 - stringWidth2 > 0) {
            graphics.clipRect(width - stringWidth - n3, height - height2 - n3, stringWidth + 2 - stringWidth2, height2 + 2);
            try {
                this.zzzag(graphics);
            }
            catch (Exception ex) {}
            graphics.dispose();
            graphics = this.getGraphics();
        }
        graphics.setColor(this.zzzjg);
        graphics.drawString(this.zzzbh, width - stringWidth2 - n3, height - n3);
        graphics.dispose();
        return true;
    }
}
