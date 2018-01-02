// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.util.Vector;
import java.net.URL;

public class cj
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
    public double m;
    public double n;
    public double o;
    public double p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public boolean v;
    public int w;
    private Vector x;
    private Vector y;
    
    public cj() {
        this.d = 2;
        this.e = a("[|7f]f~?m");
        this.f = -1;
        this.g = 1;
        this.h = 2000;
        this.i = "";
        this.j = "";
        this.k = null;
        this.l = "";
        this.m = 3.141592653589793;
        this.n = -3.141592653589793;
        this.o = 0.0;
        this.p = 1.5707963267948966;
        this.q = -this.p;
        this.r = 0.0;
        this.s = 3.141592653589793;
        this.t = -3.141592653589793;
        this.u = 0.0;
        this.v = true;
        this.w = 20;
        this.x = new Vector();
        this.y = new Vector();
    }
    
    public void a(final dv dv, final Rectangle rectangle) {
        this.x.addElement(dv);
        this.y.addElement(rectangle);
    }
    
    public Rectangle[] a() {
        final boolean q = com.easypano.tw.g.q;
        final Rectangle[] array = new Rectangle[this.y.size()];
        int n = 0;
        while (true) {
            Label_0038: {
                if (!q) {
                    break Label_0038;
                }
                final Rectangle[] array2;
                array2[n] = this.y.elementAt(n);
                ++n;
            }
            if (n < this.y.size()) {
                continue;
            }
            final Rectangle[] array2 = array;
            if (!q) {
                return array2;
            }
            continue;
        }
    }
    
    public dv[] b() {
        final boolean q = com.easypano.tw.g.q;
        final dv[] array = new dv[this.x.size()];
        int n = 0;
        while (true) {
            Label_0038: {
                if (!q) {
                    break Label_0038;
                }
                final dv[] array2;
                array2[n] = this.x.elementAt(n);
                ++n;
            }
            if (n < this.x.size()) {
                continue;
            }
            final dv[] array2 = array;
            if (!q) {
                return array2;
            }
            continue;
        }
    }
    
    public void destroyResource() {
        for (int size = this.x.size(), i = 0; i < size; ++i) {
            ((dv)this.x.elementAt(i)).destroyResource();
        }
        this.x.removeAllElements();
        this.x = null;
        this.y.removeAllElements();
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
                            c2 = '(';
                            break;
                        }
                        case 1: {
                            c2 = '\u001f';
                            break;
                        }
                        case 2: {
                            c2 = 'R';
                            break;
                        }
                        case 3: {
                            c2 = '\b';
                            break;
                        }
                        default: {
                            c2 = '8';
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
