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
    public static final int Isa = 6;
    private int Jsa;
    private int Ksa;
    protected int tra;
    private int Lsa;
    protected double[] _ra;
    protected double[] Zqa;
    protected Point[] Yqa;
    protected Color T;
    
    public UserDrawTool(final int n) {
        this.tra = 0;
        this.T = Color.green;
        this._ra = new double[n];
        this.Zqa = new double[n];
        this.Yqa = new Point[n];
        for (int i = 0; i < this.Yqa.length; ++i) {
            this.Yqa[i] = new Point();
        }
        this.tra = 0;
    }
    
    public UserDrawTool(final int n, final int tra) {
        this(n);
        this.tra = tra;
    }
    
    public void setColor(final Color t) {
        this.T = t;
    }
    
    public void _(final Graphics graphics, final Rectangle clip, final Color color, final o o, final o o2) {
        graphics.setClip(clip);
        for (int i = 0; i < this.Yqa.length; ++i) {
            this.Yqa[i].x = (int)o.b(this._ra[i]);
            this.Yqa[i].y = (int)o2.b(this.Zqa[i]);
        }
        this.b(graphics, color, o, o2);
    }
    
    public abstract boolean a(final int p0, final int p1, final o p2, final o p3);
    
    public void a(final int jsa, final int ksa, final o o, final o o2) {
        this.Jsa = jsa;
        this.Ksa = ksa;
        for (int i = 0; i < this.Yqa.length; ++i) {
            this.Yqa[i].x = (int)o.b(this._ra[i]);
            this.Yqa[i].y = (int)o2.b(this.Zqa[i]);
        }
        this.Lsa = this._(jsa, ksa);
    }
    
    public void _(final int jsa, final int ksa, final Graphics graphics, final Rectangle clip, final Color color, final o o, final o o2) {
        final int n = jsa - this.Jsa;
        final int n2 = ksa - this.Ksa;
        graphics.setClip(clip);
        this.b(graphics, color, o, o2);
        this.Jsa = jsa;
        this.Ksa = ksa;
        if (this.Lsa == -1) {
            for (int i = 0; i < this.Yqa.length; ++i) {
                final Point point = this.Yqa[i];
                point.x += n;
                final Point point2 = this.Yqa[i];
                point2.y += n2;
            }
        }
        else {
            final Point point3 = this.Yqa[this.Lsa];
            point3.x += n;
            final Point point4 = this.Yqa[this.Lsa];
            point4.y += n2;
        }
        for (int j = 0; j < this.Yqa.length; ++j) {
            this._ra[j] = o._(this.Yqa[j].x);
            this.Zqa[j] = o2._(this.Yqa[j].y);
        }
        this.b(graphics, color, o, o2);
    }
    
    public void b(final Graphics graphics, final Rectangle clip, final Color color, final o o, final o o2) {
        graphics.setClip(clip);
        for (int i = 0; i < this.Yqa.length; ++i) {
            this._ra[i] = o._(this.Yqa[i].x);
            this.Zqa[i] = o2._(this.Yqa[i].y);
        }
        this.b(graphics, color, o, o2);
        for (int j = 0; j < this.Yqa.length; ++j) {
            this.Yqa[j].x = (int)o.b(this._ra[j]);
            this.Yqa[j].y = (int)o2.b(this.Zqa[j]);
        }
        this.b(graphics, color, o, o2);
        this.Lsa = -1;
    }
    
    public void a(final Graphics graphics, final Rectangle clip, final Color color, final o o, final o o2) {
        graphics.setClip(clip);
        this.b(graphics, color, o, o2);
    }
    
    public void d(final double n, final double n2) {
        for (int i = 0; i < this._ra.length; ++i) {
            this._ra[i] = n;
            this.Zqa[i] = n2;
        }
    }
    
    public void b(final double[] array, final double[] array2) {
        for (int i = 0; i < this._ra.length; ++i) {
            if (i < array.length) {
                this._ra[i] = array[i];
            }
            else {
                this._ra[i] = 0.0;
            }
            if (i < array2.length) {
                this.Zqa[i] = array2[i];
            }
            else {
                this.Zqa[i] = 0.0;
            }
        }
    }
    
    public double[] Z() {
        return this._ra;
    }
    
    public double[] _a() {
        return this.Zqa;
    }
    
    protected abstract void b(final Graphics p0, final Color p1, final o p2, final o p3);
    
    protected static void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, float n5) {
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
            for (int j = 0; j < this.Yqa.length; ++j) {
                final int abs = Math.abs(this.Yqa[j].x - n);
                final int abs2 = Math.abs(this.Yqa[j].y - n2);
                if (abs <= i && abs2 <= i) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public void I(final int tra) {
        this.tra = tra;
    }
}
