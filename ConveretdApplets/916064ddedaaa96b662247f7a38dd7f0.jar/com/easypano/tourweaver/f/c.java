// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import com.easypano.tourweaver.a.e;
import java.awt.Toolkit;
import java.awt.Point;

class c implements com.easypano.tourweaver.c.c
{
    public static final int a = 1;
    public static final int b = 2;
    String c;
    int d;
    Object e;
    Point f;
    int g;
    int i;
    int j;
    int k;
    int l;
    int m;
    boolean n;
    private static String[] z;
    
    public c(final Object e, final int d, final String c) {
        this.c = "";
        this.d = -1;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.n = false;
        this.e = e;
        this.d = d;
        this.c = c;
    }
    
    public void i() {
        this.e = null;
    }
    
    public long j() {
        int length;
        final boolean b = (length = ((this.e instanceof byte[]) ? 1 : 0)) != 0;
        if (!r.i) {
            if (!b) {
                return 0L;
            }
            length = ((byte[])this.e).length;
        }
        return length;
    }
    
    public void a(final Object e) {
        this.e = e;
        this.n = true;
    }
    
    public boolean h() {
        return this.n;
    }
    
    public int[] a(int[] array) {
        final boolean i = r.i;
        final int[] array2 = array;
        Label_0024: {
            if (!i) {
                if (array2 != null) {
                    break Label_0024;
                }
                final int[] array3 = new int[this.g * this.i];
            }
            array = array2;
        }
        int d;
        final int n = d = this.d;
        int n3;
        final int n2 = n3 = 1;
        if (!i) {
            if (n == n2) {
                a(array, (byte[])this.e, this.g, this.i);
                return array;
            }
            final int d2;
            d = (d2 = this.d);
            final int n4;
            n3 = (n4 = 2);
        }
        Label_0140: {
            c c = null;
            Label_0117: {
                if (!i) {
                    if (n != n2) {
                        return array;
                    }
                    c = this;
                    if (i) {
                        break Label_0117;
                    }
                    d = this.l * this.m;
                    n3 = 8000000;
                }
                if (d > n3) {
                    a(array, (byte[])this.e, this.g, this.i);
                    if (!i) {
                        break Label_0140;
                    }
                }
                c = this;
            }
            c.b(array, (byte[])this.e, this.g, this.i);
        }
        System.gc();
        return array;
    }
    
    public static void a(final int[] array, final byte[] array2, final int n, final int n2) {
        if (array2 == null) {
            return;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(array2);
        e.a(image);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            System.out.println(c.z[0]);
            ex.printStackTrace();
        }
        catch (Error error) {
            System.out.println(c.z[1]);
        }
    }
    
    public synchronized int[] b(final int[] array, final byte[] array2, final int n, final int n2) {
        final int[] array3 = new int[n * n2];
        final Image image = Toolkit.getDefaultToolkit().createImage(array2);
        com.easypano.tourweaver.a.e.a(image);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array3, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            System.out.println(com.easypano.tourweaver.f.c.z[3]);
            ex.printStackTrace();
        }
        catch (Error error) {
            System.out.println(com.easypano.tourweaver.f.c.z[2]);
        }
        this.a(array3, array);
        return array;
    }
    
    private void a(final int[] array, final int[] array2) {
        final boolean i = r.i;
        final double n = this.m * 1.0 / this.i;
        final double n2 = this.l * 1.0 / this.g;
        final int n3 = this.m / this.i;
        int n4 = 0;
        int n5 = 0;
        int j = 0;
    Label_0137_Outer:
        while (j < this.m) {
            int k = 0;
            while (true) {
                while (k < this.l) {
                    array2[n4 + k] = array[(int)(j / n) * this.g + (int)(k / n2)];
                    ++k;
                    if (!i) {
                        if (i) {
                            break;
                        }
                        continue Label_0137_Outer;
                    }
                    else {
                        if (i) {
                            break Label_0137_Outer;
                        }
                        continue Label_0137_Outer;
                    }
                }
                n4 += this.l;
                n5 += this.g;
                ++j;
                continue;
            }
        }
    }
    
    public Object a() {
        return this.e;
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    public String b() {
        return this.c;
    }
    
    public void f(final int j) {
        this.j = j;
    }
    
    public void g(final int k) {
        this.k = k;
    }
    
    public int f() {
        return this.j;
    }
    
    public int g() {
        return this.k;
    }
    
    public int c() {
        return this.g;
    }
    
    public int d() {
        return this.i;
    }
    
    public void a(final int d) {
        this.d = d;
    }
    
    public int e() {
        return this.d;
    }
    
    public void b(final int g) {
        this.g = g;
    }
    
    public void c(final int i) {
        this.i = i;
    }
    
    public void d(final int l) {
        this.l = l;
    }
    
    public void e(final int m) {
        this.m = m;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "zA\u001d\u0007\u0007-Z\u001cI\u0010lA\u0001\f*]v5-\u0001yR".toCharArray();
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
                            c2 = '\r';
                            break;
                        }
                        case 1: {
                            c2 = '3';
                            break;
                        }
                        case 2: {
                            c2 = 'r';
                            break;
                        }
                        case 3: {
                            c2 = 'i';
                            break;
                        }
                        default: {
                            c2 = '`';
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
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "hA\u0000\u0006\u0012-Z\u001cI\u0010lA\u0001\f*]v5-\u0001yR".toCharArray();
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
                            c4 = '\r';
                            break;
                        }
                        case 1: {
                            c4 = '3';
                            break;
                        }
                        case 2: {
                            c4 = 'r';
                            break;
                        }
                        case 3: {
                            c4 = 'i';
                            break;
                        }
                        default: {
                            c4 = '`';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "hA\u0000\u0006\u0012-Z\u001cI\u0010lA\u0001\f&l@\u0006?\thD\u0017\u001b".toCharArray();
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
                            c6 = '\r';
                            break;
                        }
                        case 1: {
                            c6 = '3';
                            break;
                        }
                        case 2: {
                            c6 = 'r';
                            break;
                        }
                        case 3: {
                            c6 = 'i';
                            break;
                        }
                        default: {
                            c6 = '`';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "zA\u001d\u0007\u0007-Z\u001cI\u0010lA\u0001\f&l@\u0006?\thD\u0017\u001b".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\r';
                            break;
                        }
                        case 1: {
                            c8 = '3';
                            break;
                        }
                        case 2: {
                            c8 = 'r';
                            break;
                        }
                        case 3: {
                            c8 = 'i';
                            break;
                        }
                        default: {
                            c8 = '`';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                c.z = z;
                return;
            }
            continue;
        }
    }
}
