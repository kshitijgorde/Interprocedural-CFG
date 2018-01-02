// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class ce
{
    private Vector a;
    private cj b;
    private int c;
    private int d;
    private boolean e;
    
    public ce(final cj b) {
        this.a = new Vector();
        this.c = -1;
        this.d = -1;
        this.e = true;
        this.b = b;
    }
    
    public void a(final bx bx, final cj cj, final cf cf) {
        final int q = h.q;
        double n = 30.0;
        final double a = this.a(bx);
        final int e = ds.e(bx.a(a("m\u0017X\u0004\u001fs\u0003A")));
        cf.a((int)n, a("Q\u0019M\bXs\u0011\f<Pi\u001e\f%_{\u0019^\u0001Pi\u001fC\u0002"));
        this.c = ds.e(bx.a(a("m\u0017X\u0004\u001fy\u0013J\rDq\u0002")));
        this.e = ds.g(bx.a(a("m\u0017X\u0004\u001f|\u0003X\u0003Aq\u0017U")));
        int n2 = 0;
        while (true) {
            Label_0717: {
                if (q == 0) {
                    break Label_0717;
                }
                final cd cd = new cd();
                final String string = "p" + n2;
                cd.h = bx.a(String.valueOf(string) + a("3\u0018M\u0001T"));
                cd.i = ds.e(bx.a(String.valueOf(string) + a("3\u0005C\u0019_y")));
                final int a2 = bx.a();
                if (q == 0) {
                    switch (a2) {
                        case 1: {
                            cd.i = -1;
                            break;
                        }
                    }
                    cd.j = ds.g(bx.a(String.valueOf(string) + a("3\u001aC\u0003A")));
                    ds.e(bx.a(String.valueOf(string) + a("3\u0010^\r\\xXB\u0019\\")));
                }
                final int n3 = a2;
                int n4 = 0;
                while (true) {
                    Label_0678: {
                        if (q == 0) {
                            break Label_0678;
                        }
                        final cc cc = new cc();
                        final String string2 = String.valueOf(string) + a("3\u0010") + n4;
                        cc.d = ds.e(bx.a(String.valueOf(string2) + a("3\u0005")));
                        cc.e = ds.e(bx.a(String.valueOf(string2) + a("3\u0002"))) * 1000;
                        final dz k = ds.k(bx.a(String.valueOf(string2) + a("3\u0000")));
                        cc.f = k.a;
                        cc.g = k.b;
                        cc.h = k.c;
                        final ci a3 = cj.a(cc.d);
                        final int d;
                        Label_0616: {
                            if (q != 0 || a3 != null) {
                                d = a3.d;
                                if (q != 0) {
                                    break Label_0616;
                                }
                                switch (d) {
                                    case 2:
                                    case 3: {
                                        cc.f = cc.f * 3.141592653589793 / 180.0 + 3.141592653589793;
                                        cc.g = k.b * 3.141592653589793 / 180.0;
                                        cc.h = k.c * 3.141592653589793 / 180.0;
                                        break;
                                    }
                                }
                            }
                            ds.e(bx.a(String.valueOf(string2) + a("3\u0012")));
                        }
                        Label_0668: {
                            Label_0651: {
                                switch (d) {
                                    case 1: {
                                        cc.i = 1;
                                        if (q != 0) {
                                            break Label_0651;
                                        }
                                        break Label_0668;
                                    }
                                    case 2: {
                                        cc.i = 2;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_0668;
                                    }
                                }
                            }
                            cc.i = 3;
                        }
                        cd.a(cc);
                        ++n4;
                    }
                    if (n4 < n3) {
                        continue;
                    }
                    break;
                }
                this.a.addElement(cd);
                cf.a((int)(n += a), a("Q\u0019M\bXs\u0011\f<Pi\u001e\f%_{\u0019^\u0001Pi\u001fC\u0002"));
                ++n2;
            }
            if (n2 >= e) {
                return;
            }
            continue;
        }
    }
    
    private double a(final bx bx) {
        double n = 10.0;
        final int e = ds.e(bx.a(a("m\u0017X\u0004\u001fs\u0003A")));
        if (e > 0) {
            n = 10.0 / (e + 1);
        }
        return n;
    }
    
    public int a() {
        return this.a.size();
    }
    
    public cd a(int n) {
        final int q = h.q;
        int n3;
        final int n2 = n3 = n;
        if (q == 0) {
            if (n2 < 0) {
                n = 0;
            }
            final int n4;
            n3 = (n4 = n);
        }
        if (q == 0) {
            if (n2 >= this.a.size()) {
                n = this.a.size() - 1;
            }
            n3 = n;
        }
        if (n3 >= 0) {
            return this.a.elementAt(n);
        }
        return null;
    }
    
    public int b() {
        return this.c;
    }
    
    public int c() {
        return this.d;
    }
    
    public boolean d() {
        return this.e;
    }
    
    public void destroyResource() {
        this.a.removeAllElements();
        this.a = null;
        this.b = null;
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
                            c2 = '\u001d';
                            break;
                        }
                        case 1: {
                            c2 = 'v';
                            break;
                        }
                        case 2: {
                            c2 = ',';
                            break;
                        }
                        case 3: {
                            c2 = 'l';
                            break;
                        }
                        default: {
                            c2 = '1';
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
