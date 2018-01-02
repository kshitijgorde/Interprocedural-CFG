import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class var extends Panel
{
    private Image T;
    private Graphics U;
    private Dimension size;
    private Font za;
    private Font Aa;
    private FontMetrics Ba;
    private Rectangle bounds;
    private String Ca;
    private String Da;
    private int Ea;
    private int Fa;
    private int Ga;
    private static String o = "\u2f80\u2fb3\u2fa8\u2fa0\u2fad";
    private static String p = "";
    private static String q = "\u2fb5\u2fa0\u2faf\u2fa6\u2fb3\u2fa0\u2fac";
    private static String C = "\u2fe1\u2ffb\u2fe1\u2fe1";
    
    public var() {
        this.za = new Font(var.o, 1, 12);
        this.Aa = new Font(var.o, 0, 12);
        this.Ba = this.getFontMetrics(this.za);
        this.Ea = -1;
        this.Fa = -1;
        this.Ga = 10;
    }
    
    public void setTitle(final String ca) {
        this.Ca = ca;
        this.Fa = -1;
    }
    
    public void b(final String da) {
        this.Da = da;
    }
    
    public void update(final Graphics graphics) {
        if (this.Ca == null || this.Ca.equals(var.p)) {
            this.setTitle(var.q);
        }
        this.U.setColor(this.getBackground());
        this.U.fillRect(0, 0, this.size.width, this.size.height);
        this.U.setFont(this.za);
        if (this.Ea == -1) {
            this.Ea = switch.a(this.Ca, false, this.bounds, this.U) - this.bounds.y;
        }
        this.U.setColor(this.getForeground());
        if (this.Da != null) {
            this.U.drawString(String.valueOf(this.Ca) + var.C, this.Ga, this.Ea);
        }
        else {
            this.U.drawString(this.Ca, this.Ga, this.Ea);
        }
        if (this.Da != null) {
            if (this.Fa == -1) {
                this.Fa = this.Ba.stringWidth(String.valueOf(this.Ca) + var.C) + this.Ga;
            }
            this.U.setFont(this.Aa);
            this.U.setColor(this.getForeground());
            this.U.drawString(this.Da, this.Fa, this.Ea);
        }
        graphics.drawImage(this.T, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.T == null) {
            this.size = this.getSize();
            this.bounds = this.getBounds();
            this.T = this.createImage(this.size.width, this.size.height);
            this.U = this.T.getGraphics();
        }
        this.update(graphics);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF2FC1);
        }
        return new String(array);
    }
    
    static {
        var.o = _(var.o);
        var.p = _(var.p);
        var.q = _(var.q);
        var.C = _(var.C);
    }
}
