// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Rectangle;
import java.awt.Shape;
import com.easypano.tourweaver.f.h;
import java.awt.Image;
import com.easypano.tourweaver.f.e;

public class c extends b implements e
{
    private static final long serialVersionUID = -8396264862631848359L;
    String sb;
    Image tb;
    String ub;
    int vb;
    int wb;
    private static String[] xb;
    
    public c() {
        this.sb = "";
        this.ub = "";
        this.vb = 0;
        this.wb = 0;
        super.fb = c.xb[2];
    }
    
    public boolean e() {
        return false;
    }
    
    public String f() {
        return null;
    }
    
    public int g() {
        return 0;
    }
    
    public String m() {
        return this.ub;
    }
    
    public void a(final h h) {
    }
    
    public void b(final h h) {
    }
    
    public double n() {
        return 0.0;
    }
    
    public void a(final double n) {
    }
    
    public void b(final double n) {
    }
    
    public Shape h() {
        return null;
    }
    
    public void addAttributes(final String s, final String ub) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.addAttributes(s, ub);
        final boolean equals = s.equals(c.xb[1]);
        if (!u) {
            if (equals) {
                final Rectangle bounds = this.getBounds();
                this.vb = bounds.x + bounds.width / 2;
                this.wb = bounds.y + bounds.height / 2;
                if (!u) {
                    return;
                }
            }
            s.equals(c.xb[0]);
        }
        if (equals) {
            this.ub = ub;
        }
    }
    
    public void a(final double n, final double n2) {
        this.setBounds((int)(this.vb * n + 0.5) - this.getBounds().width / 2, (int)(this.wb * n2 + 0.5) - this.getBounds().height / 2, this.getBounds().width, this.getBounds().height);
    }
    
    public void a(final Shape shape) {
    }
    
    public void a(final int n, final int n2) {
    }
    
    public int getX() {
        return this.vb;
    }
    
    public int getY() {
        return this.wb;
    }
    
    static {
        final String[] xb = new String[3];
        final int n = 0;
        final char[] charArray = "\u007fc)\u0011\u0017r".toCharArray();
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
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = '}';
                            break;
                        }
                        case 3: {
                            c2 = 'h';
                            break;
                        }
                        default: {
                            c2 = 'g';
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
        xb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "u\u007f\b\u0006\u0003d".toCharArray();
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
                            c4 = '\u0017';
                            break;
                        }
                        case 1: {
                            c4 = '\u0010';
                            break;
                        }
                        case 2: {
                            c4 = '}';
                            break;
                        }
                        case 3: {
                            c4 = 'h';
                            break;
                        }
                        default: {
                            c4 = 'g';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        xb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "cq\u001f".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0017';
                            break;
                        }
                        case 1: {
                            c6 = '\u0010';
                            break;
                        }
                        case 2: {
                            c6 = '}';
                            break;
                        }
                        case 3: {
                            c6 = 'h';
                            break;
                        }
                        default: {
                            c6 = 'g';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                xb[n9] = new String(charArray3).intern();
                c.xb = xb;
                return;
            }
            continue;
        }
    }
}
