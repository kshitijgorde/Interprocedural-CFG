// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Image;
import java.awt.image.ImageObserver;
import com.easypano.tw.dt;
import java.awt.Dimension;
import java.awt.Graphics;
import com.easypano.tw.g;

public class c extends a
{
    protected boolean P;
    private int Q;
    public static boolean R;
    
    public c(final g g, final boolean p2) {
        super(g);
        this.P = false;
        this.P = p2;
        this.c(22);
        this.b(13);
        g.b(false);
    }
    
    protected void f(final Graphics graphics) {
        final boolean o = com.easypano.tw.c.a.O;
        c c = this;
        if (!o) {
            if (this.P) {
                final int n = super.k.getBounds().width - super.N.left - super.N.right;
                int n2 = 0;
                int n3 = 1;
                final String a = super.k.e().a();
                c c2 = this;
                Label_0096: {
                    if (!o) {
                        if (super.l != null) {
                            break Label_0096;
                        }
                        c2 = this;
                    }
                    c2.l = graphics.getFontMetrics(super.k.getFont());
                }
                final int length = a.length();
                if (!o) {
                    if (length <= 0) {
                        return;
                    }
                    graphics.setFont(super.k.getFont());
                    graphics.setColor(super.I);
                    (super.l = graphics.getFontMetrics()).getHeight();
                }
                final int n4 = length;
                int n5 = super.l.getAscent() + super.N.top;
                while (true) {
                    while (true) {
                        int n8 = 0;
                        int n9 = 0;
                        Label_0506: {
                            Label_0499: {
                                if (!o) {
                                    break Label_0499;
                                }
                                final c c3 = this;
                                int n6 = c3.l.stringWidth(a.substring(n2, n3));
                                int n7 = 0;
                                while (true) {
                                    Label_0209: {
                                        if (!o) {
                                            break Label_0209;
                                        }
                                        ++n3;
                                        n6 = super.l.stringWidth(a.substring(n2, n3));
                                    }
                                    if (a.charAt(n3 - 1) != '\n') {
                                        n7 = (n8 = n6);
                                        if (!o) {
                                            n9 = n;
                                            if (o) {
                                                break Label_0506;
                                            }
                                            if (n7 <= n9) {
                                                final int n10 = n3;
                                                if (!o) {
                                                    if (n10 < a.length()) {
                                                        continue;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                                final int n11 = n7;
                                int n14;
                                int char1;
                                int n13;
                                final int n12 = n13 = (char1 = (n14 = a.charAt(n3 - 1)));
                                int descent;
                                int n17;
                                int n16;
                                final int n15 = n16 = (n17 = (descent = 10));
                                Label_0425: {
                                    Label_0393: {
                                        if (!o) {
                                            if (n12 == n15) {
                                                --n3;
                                                if (!o) {
                                                    break Label_0393;
                                                }
                                            }
                                            char1 = (n13 = (n14 = super.l.stringWidth(a.substring(n2, n3))));
                                            n17 = (n16 = (descent = n));
                                        }
                                        if (o) {
                                            break Label_0425;
                                        }
                                        if (n13 > n16) {
                                            int n18 = n3 - 1;
                                            int n20 = 0;
                                            int n22 = 0;
                                            Label_0381: {
                                                while (true) {
                                                    Label_0326: {
                                                        if (!o) {
                                                            break Label_0326;
                                                        }
                                                        --n18;
                                                    }
                                                    if (n18 > n2) {
                                                        final int n19 = n20 = (char1 = (n14 = a.charAt(n18 - 1)));
                                                        final int n21 = n22 = (n17 = (descent = 32));
                                                        if (o || o) {
                                                            break Label_0381;
                                                        }
                                                        if (n19 != n21) {
                                                            final int n23 = n20 = (char1 = (n14 = a.charAt(n18 - 1)));
                                                            final int n24 = n22 = (n17 = (descent = 255));
                                                            if (o) {
                                                                break Label_0381;
                                                            }
                                                            if (n23 < n24) {
                                                                continue;
                                                            }
                                                        }
                                                    }
                                                    break;
                                                }
                                                char1 = (n20 = (n14 = n18));
                                                n17 = (n22 = (descent = n2));
                                            }
                                            if (o) {
                                                break Label_0425;
                                            }
                                            if (n20 != n22) {
                                                n3 = n18;
                                            }
                                        }
                                    }
                                    graphics.drawString(a.substring(n2, n3), super.N.left, n5);
                                    n14 = (char1 = a.charAt(n11 - 1));
                                    descent = (n17 = 10);
                                }
                                if (!o) {
                                    if (char1 == n17) {
                                        n3 = n11;
                                    }
                                    n14 = n5;
                                    descent = super.l.getDescent();
                                }
                                final int n25 = n14 + descent;
                                this.a(graphics, super.N.left, n25, super.N.left + super.l.stringWidth(a.substring(n2, n3)), n25);
                                n2 = n3++;
                                n5 += n4;
                            }
                            n8 = n2;
                            a.length();
                        }
                        if (n8 < n9) {
                            continue;
                        }
                        break;
                    }
                    final c c3 = this;
                    if (o) {
                        continue;
                    }
                    break;
                }
                this.Q = n5 - super.l.getAscent() - super.l.getLeading() + super.N.bottom;
                if (!o) {
                    return;
                }
            }
            c = this;
        }
        c.f(graphics);
    }
    
    public Dimension a() {
        final boolean o = com.easypano.tw.c.a.O;
        c c = this;
        if (!o) {
            if (this.P) {
                final Dimension dimension = new Dimension(0, 0);
                Image image2;
                final Image image = image2 = super.v;
                if (!o) {
                    if (image != null) {
                        dimension.width = super.v.getWidth(dt.d);
                        dimension.height = super.v.getHeight(dt.d);
                    }
                    final Image w;
                    image2 = (w = super.w);
                }
                c c2 = null;
                Label_0142: {
                    if (!o) {
                        if (image != null) {
                            dt.a(dimension, super.w.getWidth(dt.d), super.w.getHeight(dt.d));
                        }
                        c2 = this;
                        if (o) {
                            break Label_0142;
                        }
                        image2 = super.x;
                    }
                    if (image2 != null) {
                        dt.a(dimension, super.x.getWidth(dt.d), super.x.getHeight(dt.d));
                    }
                    c2 = this;
                }
                final String a = c2.k.e().a();
                c c3 = null;
                Label_0471: {
                    if (a.length() > 0) {
                        c3 = this;
                        if (o) {
                            break Label_0471;
                        }
                        if (super.l != null) {
                            final int n = super.k.getBounds().width - super.N.left - super.N.right;
                            int n2 = 0;
                            int n3 = 1;
                            super.l.getHeight();
                            int n4 = super.l.getAscent() + super.N.top;
                            while (true) {
                                while (true) {
                                    Label_0426: {
                                        if (!o) {
                                            break Label_0426;
                                        }
                                        int n5 = 0;
                                        int n7 = 0;
                                        int n9 = 0;
                                        int length = 0;
                                        Label_0329: {
                                            while (true) {
                                                Label_0250: {
                                                    if (!o) {
                                                        break Label_0250;
                                                    }
                                                    ++n3;
                                                }
                                                if (!a.substring(n3 - 1, n3).equals("\n")) {
                                                    final int n6;
                                                    n5 = (n6 = super.l.stringWidth(a.substring(n2, n3)));
                                                    final int n8;
                                                    n7 = (n8 = n);
                                                    if (o || o) {
                                                        break Label_0329;
                                                    }
                                                    if (n5 <= n7) {
                                                        n9 = n3;
                                                        length = a.length();
                                                        if (o) {
                                                            break Label_0329;
                                                        }
                                                        if (n9 < length) {
                                                            continue;
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                            final c c4 = this;
                                            c4.l.stringWidth(a.substring(n2, n3));
                                        }
                                        Label_0423: {
                                            if (!o) {
                                                if (n5 > n7) {
                                                    int n10 = n3 - 1;
                                                    boolean b;
                                                    while (true) {
                                                        Label_0351: {
                                                            if (!o) {
                                                                break Label_0351;
                                                            }
                                                            --n10;
                                                        }
                                                        if (n10 <= n2) {
                                                            goto Label_0386;
                                                        }
                                                        final int n6;
                                                        b = ((n6 = (a.substring(n10 - 1, n10).equals(" ") ? 1 : 0)) != 0);
                                                        if (!o && !o && !b) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                    final boolean b2 = n2 != 0;
                                                    if (o) {
                                                        break Label_0423;
                                                    }
                                                    if (b != b2) {
                                                        n3 = n10;
                                                    }
                                                }
                                                final int n11 = n4 + super.l.getDescent();
                                                n2 = n3++;
                                            }
                                        }
                                        n4 = n9 + length;
                                    }
                                    if (n2 < a.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                final c c4 = this;
                                if (o) {
                                    continue;
                                }
                                break;
                            }
                            this.Q = n4 - super.l.getAscent() - super.l.getLeading() + super.N.bottom;
                        }
                    }
                    c3 = this;
                }
                if (c3.k != null) {
                    dt.a(dimension, super.k.getBounds().width, this.Q);
                }
                return dimension;
            }
            c = this;
        }
        return c.a();
    }
}
