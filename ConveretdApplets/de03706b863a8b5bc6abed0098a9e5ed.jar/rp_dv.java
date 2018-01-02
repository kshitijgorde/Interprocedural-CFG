import java.awt.Point;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_dv implements Cloneable
{
    public int a;
    int b;
    int c;
    public Dimension a;
    Dimension b;
    private HashMap a;
    private int d;
    
    public rp_dv() {
        this.a = 5;
        this.a = new Dimension();
        this.b = null;
        this.a = null;
        this.d = 0;
    }
    
    public abstract void a(final rp_eS p0, final rp_h p1);
    
    abstract boolean a(final int p0, final int p1);
    
    public abstract boolean b(final int p0, final int p1);
    
    public abstract int a();
    
    public abstract int b();
    
    public abstract int c();
    
    public abstract int d();
    
    public abstract int e();
    
    public abstract rp_fT a();
    
    public abstract void a(final rp_fT p0);
    
    public boolean a() {
        return false;
    }
    
    public boolean b() {
        return false;
    }
    
    public abstract rp_as a();
    
    public String a() {
        return null;
    }
    
    final void a(final int n, final boolean b) {
        this.d |= 0x1;
    }
    
    public final void a(final String s, final Object o) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(s, o);
    }
    
    public final Object a(final String s) {
        if (this.a == null) {
            return null;
        }
        return this.a.get(s);
    }
    
    rp_bV[] a(final rp_dC rp_dC) {
        return null;
    }
    
    final String a(final rp_eP rp_eP) {
        rp_eP.a().a(false);
        final StringBuffer sb;
        (sb = new StringBuffer(rp_eP.a().a(0, "W"))).append(": ");
        sb.append(rp_eP.a().a(this.a()));
        sb.append(" ; ");
        sb.append(rp_eP.a().a(0, "D"));
        sb.append(": ");
        sb.append(rp_eP.a().a(this.d()));
        if (this.c() > 0) {
            sb.append(" ; ");
            sb.append(rp_eP.a().a(0, "H"));
            sb.append(": ");
            sb.append(rp_eP.a().a(this.e()));
        }
        return sb.toString();
    }
    
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
    
    public final void a(final Graphics graphics, final Dimension dimension, final Dimension dimension2, final Color color, final Color color2, final boolean b, final int n, final rp_fK rp_fK, final JComponent component, final boolean b2) {
        if (this.a().a() == 2) {
            this.a(rp_fK, graphics, dimension, dimension2, color, color2, b2);
            return;
        }
        if (this.a().a(n) || this.a().b(n)) {
            this.a(rp_fK, graphics, dimension, dimension2, color, color2, b2);
            return;
        }
        final Image a;
        if ((a = this.a().a(n)) == null) {
            URL url;
            if ((url = this.a().a(n, rp_fK)) == null) {
                final String d = this.a().d;
                String a2 = null;
                if (this.a().a() == 1) {
                    a2 = ((rp_eJ)this.a()).a;
                }
                url = rp_fK.a(d, a2, n);
                this.a().a(n, url);
            }
            if (url != null) {
                this.a().a(n, rp_fK, component);
            }
        }
        if (a != null) {
            graphics.drawImage(a, (dimension.width - n) / 2, (dimension.height - n / 4 * 3) / 2, null);
            return;
        }
        this.a(rp_fK, graphics, dimension, dimension2, color, color2, b2);
    }
    
    private void a(final rp_fK rp_fK, final Graphics a, final Dimension dimension, final Dimension dimension2, final Color color, final Color color2, final boolean b) {
        if (this.a()) {
            final int max = Math.max(1 + dimension2.width / (dimension.width - 1), 1 + dimension2.height / (dimension.height - 1));
            final rp_eV rp_eV;
            (rp_eV = new rp_eV(new Point(-max * dimension.width / 2, -max * dimension.height / 2), max)).a = a;
            if (!b) {
                final Color a2 = rp_fK.a().a().a();
                final Color b2 = rp_fK.a().a().b();
                if (a2 != null && b2 != null) {
                    rp_eV.b(a2, b2);
                }
            }
            final rp_q rp_q = new rp_q(rp_eV);
            if (color != null && color2 != null) {
                rp_eV.a(true);
                rp_eV.a(color, color2);
            }
            this.a(rp_eV, rp_q);
            return;
        }
        a(a, dimension, this.a().b());
    }
    
    public static void a(final Graphics graphics, final Dimension dimension, final String s) {
        graphics.setColor(new Color(255, 200, 200));
        graphics.drawLine(10, 10, dimension.width - 20, dimension.height - 20);
        graphics.drawLine(dimension.width - 20, 10, 10, dimension.height - 20);
        graphics.setColor(Color.black);
        if (s != null) {
            graphics.drawString(s, 10, 20);
        }
        final int n = graphics.getFontMetrics().getHeight() + 2;
        graphics.drawString("Graphics", 10, n + 20);
        graphics.drawString("not available", 20, n + 20 + n);
    }
}
