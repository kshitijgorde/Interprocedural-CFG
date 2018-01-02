// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.util.Vector;
import java.net.URL;

public class ci
{
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public int d;
    public String e;
    public int f;
    public int g;
    public int h;
    public String i;
    public String j;
    public URL k;
    public String l;
    public int m;
    public int n;
    public double o;
    public double p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double v;
    public double w;
    public boolean x;
    public int y;
    private Vector z;
    private Vector A;
    
    public ci() {
        this.d = 2;
        this.e = a("\u000f~\u001e&S2|\u0016-");
        this.f = -1;
        this.g = 1;
        this.h = 2000;
        this.i = "";
        this.j = "";
        this.k = null;
        this.l = "";
        this.m = -1;
        this.n = -1;
        this.o = 3.141592653589793;
        this.p = -3.141592653589793;
        this.q = 0.0;
        this.r = 1.5707963267948966;
        this.s = -this.r;
        this.t = 0.0;
        this.u = 3.141592653589793;
        this.v = -3.141592653589793;
        this.w = 0.0;
        this.x = true;
        this.y = 20;
        this.z = new Vector();
        this.A = new Vector();
    }
    
    public void a(final du du, final Rectangle rectangle) {
        this.z.addElement(du);
        this.A.addElement(rectangle);
    }
    
    public Rectangle[] a() {
        final int q = com.easypano.tw.h.q;
        final Rectangle[] array = new Rectangle[this.A.size()];
        int n = 0;
        while (true) {
            Label_0038: {
                if (q == 0) {
                    break Label_0038;
                }
                final Rectangle[] array2;
                array2[n] = this.A.elementAt(n);
                ++n;
            }
            if (n < this.A.size()) {
                continue;
            }
            final Rectangle[] array2 = array;
            if (q == 0) {
                return array2;
            }
            continue;
        }
    }
    
    public du[] b() {
        final int q = com.easypano.tw.h.q;
        final du[] array = new du[this.z.size()];
        int n = 0;
        while (true) {
            Label_0038: {
                if (q == 0) {
                    break Label_0038;
                }
                final du[] array2;
                array2[n] = this.z.elementAt(n);
                ++n;
            }
            if (n < this.z.size()) {
                continue;
            }
            final du[] array2 = array;
            if (q == 0) {
                return array2;
            }
            continue;
        }
    }
    
    public void destroyResource() {
        for (int size = this.z.size(), i = 0; i < size; ++i) {
            ((du)this.z.elementAt(i)).destroyResource();
        }
        this.z.removeAllElements();
        this.z = null;
        this.A.removeAllElements();
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '|';
                            break;
                        }
                        case 1: {
                            c2 = '\u001d';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = 'H';
                            break;
                        }
                        default: {
                            c2 = '6';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
