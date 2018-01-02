// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Image;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Rectangle;

public class u extends t
{
    int B;
    int C;
    boolean D;
    boolean E;
    Rectangle F;
    int G;
    
    public u() {
        this.B = 0;
        this.C = 0;
        this.D = true;
        this.E = true;
        this.G = 10;
    }
    
    public void b(final Rectangle f) {
        this.F = f;
    }
    
    public void a(final Color color, final Color color2, final Color color3) {
        final boolean u = f.u;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().a(color, color2, color3);
            if (u) {
                break;
            }
        }
    }
    
    public void c(final int b, final int c) {
        final boolean u = f.u;
        this.C = c;
        this.B = b;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().a(b, c);
            if (u) {
                break;
            }
        }
    }
    
    public void a(final boolean d) {
        final boolean u = f.u;
        this.D = d;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().b(d);
            if (u) {
                break;
            }
        }
    }
    
    public void b(final boolean e) {
        this.E = e;
    }
    
    public void a(final Image image, final String s) {
        final boolean u = f.u;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().a(image, s);
            if (u) {
                break;
            }
        }
    }
    
    public void doLayout() {
        final boolean u = f.u;
        int y;
        final int n = y = super.y;
        if (!u) {
            if (n == 0) {
                return;
            }
            final int b;
            y = (b = this.B);
        }
        if (!u) {
            if (n == 0) {
                return;
            }
            y = this.C + 1 + super.y;
        }
        int n2 = y;
        final boolean d = this.D;
        u u2 = null;
        Label_0080: {
            if (!u) {
                if (!d) {
                    n2 -= 1 + super.y;
                }
                u2 = this;
                if (u) {
                    break Label_0080;
                }
                final boolean e = this.E;
            }
            if (d) {
                this.d(n2);
                if (!u) {
                    return;
                }
            }
            u2 = this;
        }
        u2.e(n2);
    }
    
    private void d(final int n) {
        final boolean u = f.u;
        final int n2;
        int width = n2 = (super.p.size() + 1) * this.G + super.p.size() * this.B;
        final int width2 = this.F.width;
        Label_0082: {
            if (!u) {
                if (n2 < width2) {
                    width = this.F.width;
                }
                if (u) {
                    break Label_0082;
                }
                final int height = this.F.height;
            }
            if (n2 < width2) {
                final int height2 = this.F.height;
            }
        }
        this.b(width, n);
        final Enumeration e = this.e();
        int n3 = 0;
        while (e.hasMoreElements()) {
            e.nextElement().setBounds((n3 + 1) * this.G + n3 * this.B, 0, this.B, n);
            ++n3;
            if (u) {
                break;
            }
        }
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
    }
    
    private void e(final int n) {
        final boolean u = f.u;
        final int n3;
        int n2 = n3 = (this.F.width - 10) / (this.B + 10);
        if (!u) {
            if (n3 <= 0) {
                n2 = 1;
            }
            final int g = this.G;
        }
        int n4 = n3;
        final int n5 = (super.p.size() + n2 - 1) / n2;
        int width = (n2 + 1) * this.G + n2 * this.B;
        int n6 = (n5 + 1) * this.G + n5 * n;
        final int n7 = width;
        final int width2 = this.F.width;
        Label_0154: {
            int height = 0;
            Label_0152: {
                if (!u) {
                    if (n7 < width2) {
                        width = this.F.width;
                        n4 = (width - n2 * this.B) / (n2 + 1);
                    }
                    final int n8;
                    height = (n8 = n6);
                    if (u) {
                        break Label_0152;
                    }
                    final int height2 = this.F.height;
                }
                if (n7 >= width2) {
                    break Label_0154;
                }
                height = this.F.height;
            }
            n6 = height;
        }
        this.b(width, n6);
        final Enumeration e = this.e();
        int n9 = 0;
        while (e.hasMoreElements()) {
            final int n10 = n9 / n2;
            final int n11 = n9 % n2;
            e.nextElement().setBounds((n11 + 1) * n4 + n11 * this.B, (n10 + 1) * this.G + n10 * n, this.B, n);
            ++n9;
            if (u) {
                break;
            }
        }
    }
}
