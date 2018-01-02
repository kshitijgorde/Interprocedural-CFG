// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class z extends y
{
    Image J;
    String K;
    Image L;
    String M;
    boolean N;
    int O;
    int P;
    int Q;
    int R;
    private static String[] S;
    
    public z() {
        this.N = true;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
    }
    
    public void b(final boolean n) {
        this.N = n;
    }
    
    public void destroy() {
        super.destroy();
        this.L = null;
        this.J = null;
    }
    
    public void c(final boolean n) {
        this.N = n;
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.addAttributes(s, s2);
        final boolean equals = s.equals(z.S[1]);
        if (!u) {
            if (equals) {
                this.K = s2;
                if (!u) {
                    return;
                }
            }
            s.equals(z.S[0]);
        }
        if (equals) {
            this.M = s2;
        }
    }
    
    public void a(final Image image, final String s) {
        super.a(image, s);
        final boolean equals = s.equals(this.M);
        if (!com.easypano.tourweaver.b.f.u) {
            if (equals) {
                this.L = image;
            }
            s.equals(this.K);
        }
        if (equals) {
            this.J = image;
            this.O = image.getWidth(this);
            this.P = image.getHeight(this);
            this.c();
        }
    }
    
    public void a(final int q, final int r) {
        this.Q = q;
        this.R = r;
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        z z = this;
        z z2 = this;
        z z3 = this;
        if (!u) {
            if (super.C != null) {
                graphics.setColor(super.C);
                graphics.fillRect(0, 0, this.Q, this.R);
            }
            z = this;
            z2 = this;
            z3 = this;
        }
        if (!u) {
            if (z3.J != null) {
                graphics.drawImage(this.J, (this.Q - this.O) / 2, (this.R - this.P) / 2, this.O, this.P, this);
            }
            z = this;
            z2 = this;
        }
        Label_0171: {
            if (!u) {
                if (z2.N) {
                    z = this;
                    if (u) {
                        break Label_0171;
                    }
                    if (super.x != null) {
                        graphics.drawImage(super.x, 1, this.R + 2, super.G - super.E + 1, super.H - super.F + this.R + 2, super.E, super.F, super.G, super.H, this);
                    }
                }
                z = this;
            }
        }
        if (z.B != null) {
            graphics.setColor(super.B);
            graphics.drawRect(0, 0, this.getBounds().width - 1, this.getBounds().height - 1);
        }
    }
    
    static {
        final String[] s = new String[2];
        final int n = 0;
        final char[] charArray = "\"{0:?\u0002q</".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'V';
                            break;
                        }
                        case 1: {
                            c2 = '\u0014';
                            break;
                        }
                        case 2: {
                            c2 = 'D';
                            break;
                        }
                        case 3: {
                            c2 = '[';
                            break;
                        }
                        default: {
                            c2 = 'S';
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
        s[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "?y#".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'V';
                            break;
                        }
                        case 1: {
                            c4 = '\u0014';
                            break;
                        }
                        case 2: {
                            c4 = 'D';
                            break;
                        }
                        case 3: {
                            c4 = '[';
                            break;
                        }
                        default: {
                            c4 = 'S';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                s[n5] = new String(charArray2).intern();
                z.S = s;
                return;
            }
            continue;
        }
    }
}
