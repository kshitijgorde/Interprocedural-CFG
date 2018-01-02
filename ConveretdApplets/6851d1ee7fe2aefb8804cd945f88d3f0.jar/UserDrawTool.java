import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class UserDrawTool
{
    public static final int xka = 6;
    private int yka;
    private int zka;
    protected int eka;
    private int Aka;
    protected double[] Yja;
    protected double[] Xja;
    protected Point[] Wja;
    protected Color xa;
    
    public UserDrawTool(final int n) {
        this.eka = 0;
        this.xa = Color.green;
        this.Yja = new double[n];
        this.Xja = new double[n];
        this.Wja = new Point[n];
        for (int i = 0; i < this.Wja.length; ++i) {
            this.Wja[i] = new Point();
        }
        this.eka = 0;
    }
    
    public UserDrawTool(final int n, final int eka) {
        this(n);
        this.eka = eka;
    }
    
    public void setColor(final Color xa) {
        this.xa = xa;
    }
    
    public Color getColor() {
        return this.xa;
    }
    
    public void b(final Graphics graphics, final Rectangle clip, final Color color, final throws throws1, final throws throws2) {
        graphics.setClip(clip);
        for (int i = 0; i < this.Wja.length; ++i) {
            this.Wja[i].x = (int)throws1.b(this.Yja[i]);
            this.Wja[i].y = (int)throws2.b(this.Xja[i]);
        }
        this.b(graphics, color, throws1, throws2);
    }
    
    public abstract boolean _(final int p0, final int p1, final throws p2, final throws p3);
    
    public void a(final int yka, final int zka, final throws throws1, final throws throws2) {
        this.yka = yka;
        this.zka = zka;
        for (int i = 0; i < this.Wja.length; ++i) {
            this.Wja[i].x = (int)throws1.b(this.Yja[i]);
            this.Wja[i].y = (int)throws2.b(this.Xja[i]);
        }
        this.Aka = this.a(yka, zka);
    }
    
    public void _(final int yka, final int zka, final Graphics graphics, final Rectangle clip, final Color color, final throws throws1, final throws throws2) {
        final int n = yka - this.yka;
        final int n2 = zka - this.zka;
        graphics.setClip(clip);
        this.b(graphics, color, throws1, throws2);
        this.yka = yka;
        this.zka = zka;
        if (this.Aka == -1) {
            for (int i = 0; i < this.Wja.length; ++i) {
                final Point point = this.Wja[i];
                point.x += n;
                final Point point2 = this.Wja[i];
                point2.y += n2;
            }
        }
        else {
            final Point point3 = this.Wja[this.Aka];
            point3.x += n;
            final Point point4 = this.Wja[this.Aka];
            point4.y += n2;
        }
        for (int j = 0; j < this.Wja.length; ++j) {
            this.Yja[j] = throws1.a(this.Wja[j].x);
            this.Xja[j] = throws2.a(this.Wja[j].y);
        }
        this.b(graphics, color, throws1, throws2);
    }
    
    public void a(final Graphics graphics, final Rectangle clip, final Color color, final throws throws1, final throws throws2) {
        graphics.setClip(clip);
        for (int i = 0; i < this.Wja.length; ++i) {
            this.Yja[i] = throws1.a(this.Wja[i].x);
            this.Xja[i] = throws2.a(this.Wja[i].y);
        }
        this.b(graphics, color, throws1, throws2);
        for (int j = 0; j < this.Wja.length; ++j) {
            this.Wja[j].x = (int)throws1.b(this.Yja[j]);
            this.Wja[j].y = (int)throws2.b(this.Xja[j]);
        }
        this.b(graphics, color, throws1, throws2);
        this.Aka = -1;
    }
    
    public void _(final Graphics graphics, final Rectangle clip, final Color color, final throws throws1, final throws throws2) {
        graphics.setClip(clip);
        this.b(graphics, color, throws1, throws2);
    }
    
    public void a(final double n, final double n2) {
        for (int i = 0; i < this.Yja.length; ++i) {
            this.Yja[i] = n;
            this.Xja[i] = n2;
        }
    }
    
    public void a(final double[] array, final double[] array2) {
        for (int i = 0; i < this.Yja.length; ++i) {
            if (i < array.length) {
                this.Yja[i] = array[i];
            }
            else {
                this.Yja[i] = 0.0;
            }
            if (i < array2.length) {
                this.Xja[i] = array2[i];
            }
            else {
                this.Xja[i] = 0.0;
            }
        }
    }
    
    public double[] V() {
        return this.Yja;
    }
    
    public double[] W() {
        return this.Xja;
    }
    
    protected abstract void b(final Graphics p0, final Color p1, final throws p2, final throws p3);
    
    protected static void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4, float n5) {
        n5 *= 1.5f;
        final float n6 = n2 - n4;
        final float n7 = n3 - n;
        final float n8 = (float)Math.sqrt(n6 * n6 + n7 * n7);
        final float n9 = n6 * n5 / 2.0f / n8;
        final float n10 = n7 * n5 / 2.0f / n8;
        graphics.fillPolygon(new Polygon(new int[] { Math.round(n + n9), Math.round(n - n9), Math.round(n3 - n9), Math.round(n3 + n9) }, new int[] { Math.round(n2 + n10), Math.round(n2 - n10), Math.round(n4 - n10), Math.round(n4 + n10) }, 4));
    }
    
    protected int a(final int n, final int n2) {
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < this.Wja.length; ++j) {
                final int abs = Math.abs(this.Wja[j].x - n);
                final int abs2 = Math.abs(this.Wja[j].y - n2);
                if (abs <= i && abs2 <= i) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public void h(final int eka) {
        this.eka = eka;
    }
}
