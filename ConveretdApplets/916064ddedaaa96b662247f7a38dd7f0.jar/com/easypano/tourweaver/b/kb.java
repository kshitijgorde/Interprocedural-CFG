// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public class kb implements Shape
{
    Rectangle a;
    double b;
    double c;
    double d;
    double e;
    
    public kb() {
        this.a = new Rectangle();
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.e = 0.0;
    }
    
    public kb(final int n, final int n2, final int n3, final int n4) {
        this.a = new Rectangle();
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.e = 0.0;
        this.a.setBounds(n, n2, n3, n4);
        this.b = this.a.width / 2.0;
        this.c = this.a.height / 2.0;
        this.d = this.a.x + this.b;
        this.e = this.a.y + this.c;
    }
    
    public kb(final Rectangle rectangle) {
        this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private boolean a(final int n, final int n2) {
        final double n3 = dcmpg(this.a(n, (double)n2), 1.0);
        if (!f.u && n3 > 0) {}
        return n3 != 0.0;
    }
    
    public Point a(final int n, final int n2, final int n3, final int n4) {
        final boolean u = f.u;
        int n5 = n3;
        int n6 = n4;
        double n7 = 0.0;
        int n8 = n;
        if (!u) {
            if (n != n3) {
                n7 = (n2 - n4) / (n - n3);
            }
            n8 = n4;
        }
        final double n9 = n8 - n7 * n3;
        int n10 = n;
        int n11 = n;
        int n12 = n;
        int n13 = n3;
        int n14 = n3;
        int n15 = n3;
        if (!u) {
            if (n > n3) {
                int i = n;
                while (i >= n3) {
                    final int n16 = (int)(n7 * i + n9 + 0.5);
                    if (u) {
                        return new Point(n5, n6);
                    }
                    if (!u) {
                        if (this.a(i, n16)) {
                            n5 = i;
                            n6 = n16;
                            if (!u) {
                                break;
                            }
                        }
                        --i;
                    }
                    if (u) {
                        break;
                    }
                }
                if (!u) {
                    return new Point(n5, n6);
                }
            }
            n10 = n;
            n11 = n;
            n12 = n;
            n13 = n3;
            n14 = n3;
            n15 = n3;
        }
        if (!u) {
            if (n12 < n15) {
                int j = n;
                while (j <= n3) {
                    final int n17 = (int)(n7 * j + n9 + 0.5);
                    if (u) {
                        return new Point(n5, n6);
                    }
                    if (!u) {
                        if (this.a(j, n17)) {
                            n5 = j;
                            n6 = n17;
                            if (!u) {
                                break;
                            }
                        }
                        ++j;
                    }
                    if (u) {
                        break;
                    }
                }
                if (!u) {
                    return new Point(n5, n6);
                }
            }
            n10 = n;
            n11 = n;
            n13 = n3;
            n14 = n3;
        }
        int n18 = 0;
        Label_0289: {
            if (!u) {
                if (n11 != n14) {
                    return new Point(n5, n6);
                }
                n18 = n2;
                n10 = n2;
                if (u) {
                    break Label_0289;
                }
                n13 = n4;
            }
            if (n10 > n13) {
                int k = n2;
                while (k >= n4) {
                    if (u) {
                        return new Point(n5, n6);
                    }
                    final boolean a = this.a(n3, k);
                    Label_0275: {
                        if (!u) {
                            if (!a) {
                                break Label_0275;
                            }
                            n5 = n3;
                        }
                        n6 = (a ? 1 : 0);
                        if (!u) {
                            break;
                        }
                    }
                    --k;
                    if (u) {
                        break;
                    }
                }
                if (!u) {
                    return new Point(n5, n6);
                }
            }
            n18 = n2;
        }
        int l = n18;
        while (l <= n4) {
            final boolean a2 = this.a(n3, l);
            Label_0325: {
                if (!u) {
                    if (!a2) {
                        break Label_0325;
                    }
                    n5 = n3;
                }
                n6 = (a2 ? 1 : 0);
                if (!u) {
                    break;
                }
            }
            ++l;
            if (u) {
                break;
            }
        }
        return new Point(n5, n6);
    }
    
    public boolean contains(final Point2D point2D) {
        return false;
    }
    
    public boolean contains(final double n, final double n2, final double n3, final double n4) {
        return false;
    }
    
    public boolean contains(final Rectangle2D rectangle2D) {
        return false;
    }
    
    public boolean contains(final double n, final double n2) {
        final double n3 = dcmpg(this.a(n, n2), 1.0);
        if (!f.u && n3 >= 0) {}
        return n3 != 0.0;
    }
    
    public double a(final double n, final double n2) {
        return this.a(n - this.d) / this.a(this.b) + this.a(n2 - this.e) / this.a(this.c);
    }
    
    private double a(final double n) {
        return n * n;
    }
    
    public Rectangle getBounds() {
        return this.a;
    }
    
    public Rectangle2D getBounds2D() {
        return null;
    }
    
    public PathIterator getPathIterator(final AffineTransform affineTransform) {
        return null;
    }
    
    public PathIterator getPathIterator(final AffineTransform affineTransform, final double n) {
        return null;
    }
    
    public boolean intersects(final Rectangle2D rectangle2D) {
        return false;
    }
    
    public boolean intersects(final double n, final double n2, final double n3, final double n4) {
        return false;
    }
}
