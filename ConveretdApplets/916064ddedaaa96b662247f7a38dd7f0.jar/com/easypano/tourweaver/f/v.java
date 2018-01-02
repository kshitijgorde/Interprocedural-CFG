// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.image.ImageObserver;
import com.easypano.tourweaver.a.e;
import java.awt.Image;
import java.awt.Graphics;

public class v extends s
{
    int A;
    int B;
    int C;
    Graphics D;
    private static String[] E;
    
    public v() {
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = null;
    }
    
    public void a(final Image p3, final Image q, final Image o) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        Image image = q;
        if (!i) {
            if (q == null) {
                throw new NullPointerException(v.E[1]);
            }
            image = o;
        }
        if (image != null) {
            super.p = p3;
            super.q = q;
            super.o = o;
            int c = 0;
            final int c2 = com.easypano.tourweaver.a.e.c(q);
            final int d = com.easypano.tourweaver.a.e.d(q);
            int d2 = 0;
            Label_0098: {
                if (!i) {
                    if (p3 != null) {
                        c = com.easypano.tourweaver.a.e.c(p3);
                        d2 = com.easypano.tourweaver.a.e.d(p3);
                        if (!i) {
                            break Label_0098;
                        }
                    }
                    c = c2;
                }
                d2 = d;
            }
            final int c3 = com.easypano.tourweaver.a.e.c(o);
            final int d3 = com.easypano.tourweaver.a.e.d(o);
            int n3;
            int n2;
            final int n = n2 = (n3 = c);
            int n6;
            int n5;
            final int n4 = n5 = (n6 = c2);
            if (!i) {
                if (n != n4) {
                    throw new IllegalArgumentException(v.E[0]);
                }
                final int n7;
                n2 = (n7 = (n3 = d2));
                final int n8;
                n5 = (n8 = (n6 = d));
            }
            if (!i) {
                if (n != n4) {
                    throw new IllegalArgumentException(v.E[0]);
                }
                n3 = (n2 = c);
                n6 = (n5 = c3);
            }
            if (!i) {
                if (n2 != n5) {
                    throw new IllegalArgumentException(v.E[0]);
                }
                n3 = d2;
                n6 = d3;
            }
            if (n3 == n6) {
                this.A = c;
                this.B = d;
                this.C = this.A / 10;
                this.D = o.getGraphics();
                return;
            }
            throw new IllegalArgumentException(v.E[0]);
        }
        throw new NullPointerException(v.E[1]);
    }
    
    public void c() {
        super.p = null;
        super.q = null;
        super.o = null;
        final Graphics d = this.D;
        if (!com.easypano.tourweaver.f.r.i) {
            if (d == null) {
                return;
            }
            final Graphics d2 = this.D;
        }
        d.dispose();
    }
    
    public void a(final int n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        if (n == super.n) {
            return;
        }
        v v = this;
        if (!i) {
            if (this.D == null) {
                return;
            }
            v = this;
        }
        final Image q = v.q;
        Label_0089: {
            v v2 = null;
            Label_0073: {
                if (!i) {
                    if (q == null) {
                        return;
                    }
                    v2 = this;
                    if (i) {
                        break Label_0073;
                    }
                    final Image p = super.p;
                }
                if (q != null) {
                    this.D.drawImage(super.p, 0, 0, com.easypano.tourweaver.a.e.f);
                    if (!i) {
                        break Label_0089;
                    }
                }
                v2 = this;
            }
            v2.D.clearRect(0, 0, this.A, this.B);
        }
        final double n2 = this.C * n / 100.0;
        double n3 = 0.0;
        double n4 = (this.C - n2) / 2.0;
        int j = 0;
        while (j < 10) {
            this.D.drawImage(super.q, (int)n4, 0, (int)(n4 + n2), this.B, (int)n3, 0, (int)(n3 + this.C), this.B, com.easypano.tourweaver.a.e.f);
            n3 += this.C;
            n4 += this.C;
            ++j;
            if (i) {
                return;
            }
            if (i) {
                break;
            }
        }
        super.n = n;
    }
    
    public boolean a(final h h) {
        return h instanceof v;
    }
    
    static {
        final String[] e = new String[2];
        final int n = 0;
        final char[] charArray = "m@^gw[Znue[K_4p\u001e[YpJSOX3k_[\u000bwjXNNafP\\\u000b`jDM\n".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = '(';
                            break;
                        }
                        case 2: {
                            c2 = '+';
                            break;
                        }
                        case 3: {
                            c2 = '\u0013';
                            break;
                        }
                        default: {
                            c2 = '\u0003';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        e[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "m@^gw[Znue[K_4p\u001e[YpJSOX3jM\bEfoR\t".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '>';
                            break;
                        }
                        case 1: {
                            c4 = '(';
                            break;
                        }
                        case 2: {
                            c4 = '+';
                            break;
                        }
                        case 3: {
                            c4 = '\u0013';
                            break;
                        }
                        default: {
                            c4 = '\u0003';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                e[n5] = new String(charArray2).intern();
                v.E = e;
                return;
            }
            continue;
        }
    }
}
