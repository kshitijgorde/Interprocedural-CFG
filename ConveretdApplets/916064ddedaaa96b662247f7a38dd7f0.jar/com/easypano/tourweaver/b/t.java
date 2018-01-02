// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Color;
import com.easypano.tourweaver.g;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.awt.Image;

public class t extends s
{
    int y;
    y z;
    int A;
    private static String[] B;
    
    public t() {
        this.y = 0;
        this.A = 0;
    }
    
    public void a(final Image image, final String s) {
    }
    
    public y c(final String s) {
        final boolean u = f.u;
        if (s == null) {
            return null;
        }
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            final y y = e.nextElement();
            if (s.equals(y.getName())) {
                return y;
            }
            if (u) {
                break;
            }
        }
        return null;
    }
    
    public void b(final int a) {
        this.A = a;
    }
    
    public void a(final Rectangle rectangle) {
        final y z = this.z;
        if (!f.u) {
            if (z == null) {
                return;
            }
            final y z2 = this.z;
        }
        z.a(rectangle);
    }
    
    public Image n() {
        final y z = this.z;
        if (!f.u) {
            if (z == null) {
                return null;
            }
            final y z2 = this.z;
        }
        return z.t;
    }
    
    public y d(final String s) {
        final boolean u = f.u;
        final y c = this.c(s);
        y z;
        final y y = z = this.z;
        if (!u) {
            if (y != null) {
                this.z.a(false);
            }
            this.z = c;
            final y y2;
            z = (y2 = c);
        }
        if (!u) {
            if (y == null) {
                return null;
            }
            c.a(true);
            z = c;
        }
        return z;
    }
    
    public void doLayout() {
        final boolean u = f.u;
        t t = this;
        if (!u) {
            if (this.y == 0) {
                return;
            }
            t = this;
        }
        final Enumeration e = t.e();
        int n = 0;
        while (e.hasMoreElements()) {
            final y y = e.nextElement();
            int n2 = y.getBounds().width;
            if (!u) {
                if (n2 < this.A) {
                    n2 = this.A;
                }
                y.setBounds(0, n * this.y, n2, this.y);
                this.b(n2, this.y * (n + 1));
                ++n;
            }
            if (u) {
                break;
            }
        }
    }
    
    public void a(final g g) {
        final boolean u = f.u;
        super.a(g);
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().a(g);
            if (u) {
                break;
            }
        }
    }
    
    public void e(final String s) {
        final boolean u = f.u;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().c(s);
            if (u) {
                break;
            }
        }
    }
    
    public void a(final Color color, final Color color2) {
        final boolean u = f.u;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().a(color, color2);
            if (u) {
                break;
            }
        }
    }
    
    private void b(final Image image, final String s) {
        final boolean u = f.u;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().a(image, s);
            if (u) {
                break;
            }
        }
    }
    
    public void c(final int y) {
        final boolean u = f.u;
        this.y = y;
        final Enumeration e = this.e();
        while (e.hasMoreElements()) {
            e.nextElement().c(y);
            if (u) {
                break;
            }
        }
    }
    
    public void a(final Image image) {
        this.b(image, t.B[1]);
    }
    
    public void b(final Image image) {
        this.b(image, t.B[2]);
    }
    
    public void c(final Image image) {
        this.b(image, t.B[0]);
    }
    
    static {
        final String[] b = new String[3];
        final int n = 0;
        final char[] charArray = "\u0016B\u0000Z\u0004\u0011".toCharArray();
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
                            c2 = 'e';
                            break;
                        }
                        case 1: {
                            c2 = '\'';
                            break;
                        }
                        case 2: {
                            c2 = 'l';
                            break;
                        }
                        case 3: {
                            c2 = '?';
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
        b[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u000bH\u001eR\u0006\t".toCharArray();
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
                            c4 = 'e';
                            break;
                        }
                        case 1: {
                            c4 = '\'';
                            break;
                        }
                        case 2: {
                            c4 = 'l';
                            break;
                        }
                        case 3: {
                            c4 = '?';
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
        b[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\nQ\tM".toCharArray();
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
                            c6 = 'e';
                            break;
                        }
                        case 1: {
                            c6 = '\'';
                            break;
                        }
                        case 2: {
                            c6 = 'l';
                            break;
                        }
                        case 3: {
                            c6 = '?';
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
                b[n9] = new String(charArray3).intern();
                t.B = b;
                return;
            }
            continue;
        }
    }
}
