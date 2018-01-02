import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Wp
{
    public static final int ra = 6;
    private int sa;
    private int ta;
    protected int c;
    private int ua;
    protected double[] eb;
    protected double[] db;
    protected Point[] cb;
    protected Color Ba;
    
    public Wp(final int n) {
        this.c = 0;
        this.Ba = Color.green;
        this.eb = new double[n];
        this.db = new double[n];
        this.cb = new Point[n];
        for (int i = 0; i < this.cb.length; ++i) {
            this.cb[i] = new Point();
        }
        this.c = 0;
    }
    
    public Wp(final int n, final int c) {
        this(n);
        this.c = c;
    }
    
    public void setColor(final Color ba) {
        this.Ba = ba;
    }
    
    public void a(final Graphics graphics, final Rectangle clip, final Color color, final Np np, final Np np2) {
        graphics.setClip(clip);
        for (int i = 0; i < this.cb.length; ++i) {
            this.cb[i].x = (int)np.b(this.eb[i]);
            this.cb[i].y = (int)np2.b(this.db[i]);
        }
        this.b(graphics, color, np, np2);
    }
    
    public abstract boolean _(final int p0, final int p1, final Np p2, final Np p3);
    
    public void a(final int sa, final int ta, final Np np, final Np np2) {
        this.sa = sa;
        this.ta = ta;
        for (int i = 0; i < this.cb.length; ++i) {
            this.cb[i].x = (int)np.b(this.eb[i]);
            this.cb[i].y = (int)np2.b(this.db[i]);
        }
        this.ua = this._(sa, ta);
    }
    
    public void a(final int sa, final int ta, final Graphics graphics, final Rectangle clip, final Color color, final Np np, final Np np2) {
        final int n = sa - this.sa;
        final int n2 = ta - this.ta;
        graphics.setClip(clip);
        this.b(graphics, color, np, np2);
        this.sa = sa;
        this.ta = ta;
        if (this.ua == -1) {
            for (int i = 0; i < this.cb.length; ++i) {
                final Point point = this.cb[i];
                point.x += n;
                final Point point2 = this.cb[i];
                point2.y += n2;
            }
        }
        else {
            final Point point3 = this.cb[this.ua];
            point3.x += n;
            final Point point4 = this.cb[this.ua];
            point4.y += n2;
        }
        for (int j = 0; j < this.cb.length; ++j) {
            this.eb[j] = np.e(this.cb[j].x);
            this.db[j] = np2.e(this.cb[j].y);
        }
        this.b(graphics, color, np, np2);
    }
    
    public void _(final Graphics graphics, final Rectangle clip, final Color color, final Np np, final Np np2) {
        graphics.setClip(clip);
        for (int i = 0; i < this.cb.length; ++i) {
            this.eb[i] = np.e(this.cb[i].x);
            this.db[i] = np2.e(this.cb[i].y);
        }
        this.b(graphics, color, np, np2);
        for (int j = 0; j < this.cb.length; ++j) {
            this.cb[j].x = (int)np.b(this.eb[j]);
            this.cb[j].y = (int)np2.b(this.db[j]);
        }
        this.b(graphics, color, np, np2);
        this.ua = -1;
    }
    
    public void b(final Graphics graphics, final Rectangle clip, final Color color, final Np np, final Np np2) {
        graphics.setClip(clip);
        this.b(graphics, color, np, np2);
    }
    
    public void m(final double n, final double n2) {
        for (int i = 0; i < this.eb.length; ++i) {
            this.eb[i] = n;
            this.db[i] = n2;
        }
    }
    
    public void b(final double[] array, final double[] array2) {
        for (int i = 0; i < this.eb.length; ++i) {
            if (i < array.length) {
                this.eb[i] = array[i];
            }
            else {
                this.eb[i] = 0.0;
            }
            if (i < array2.length) {
                this.db[i] = array2[i];
            }
            else {
                this.db[i] = 0.0;
            }
        }
    }
    
    public double[] a() {
        return this.eb;
    }
    
    public double[] d() {
        return this.db;
    }
    
    protected abstract void b(final Graphics p0, final Color p1, final Np p2, final Np p3);
    
    protected static void _(final Graphics graphics, final int n, final int n2, final int n3, final int n4, float n5) {
        n5 *= 1.5f;
        final float n6 = n2 - n4;
        final float n7 = n3 - n;
        final float n8 = (float)Math.sqrt(n6 * n6 + n7 * n7);
        final float n9 = n6 * n5 / 2.0f / n8;
        final float n10 = n7 * n5 / 2.0f / n8;
        graphics.fillPolygon(new Polygon(new int[] { Math.round(n + n9), Math.round(n - n9), Math.round(n3 - n9), Math.round(n3 + n9) }, new int[] { Math.round(n2 + n10), Math.round(n2 - n10), Math.round(n4 - n10), Math.round(n4 + n10) }, 4));
    }
    
    protected int _(final int n, final int n2) {
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < this.cb.length; ++j) {
                final int abs = Math.abs(this.cb[j].x - n);
                final int abs2 = Math.abs(this.cb[j].y - n2);
                if (abs <= i && abs2 <= i) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public void k(final int c) {
        this.c = c;
    }
}
