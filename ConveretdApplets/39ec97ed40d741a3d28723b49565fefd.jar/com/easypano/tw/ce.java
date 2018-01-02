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
        final boolean q = g.q;
        double n = 30.0;
        final double a = this.a(bx);
        final int e = ds.e(bx.a(a("\u0006K\b\u00141\u0018_\u0011")));
        cf.a((int)n, a(":E\u001d\u0018v\u0018M\\,~\u0002B\\5q\u0010E\u000e\u0011~\u0002C\u0013\u0012"));
        this.c = ds.e(bx.a(a("\u0006K\b\u00141\u0012O\u001a\u001dj\u001a^")));
        this.e = ds.g(bx.a(a("\u0006K\b\u00141\u0017_\b\u0013o\u001aK\u0005")));
        int n2 = 0;
        while (true) {
            Label_0717: {
                if (!q) {
                    break Label_0717;
                }
                final cd cd = new cd();
                final String string = "p" + n2;
                cd.h = bx.a(String.valueOf(string) + a("XD\u001d\u0011z"));
                cd.i = ds.e(bx.a(String.valueOf(string) + a("XY\u0013\tq\u0012")));
                final int a2 = bx.a();
                if (!q) {
                    switch (a2) {
                        case 1: {
                            cd.i = -1;
                            break;
                        }
                    }
                    cd.j = ds.g(bx.a(String.valueOf(string) + a("XF\u0013\u0013o")));
                    ds.e(bx.a(String.valueOf(string) + a("XL\u000e\u001dr\u0013\u0004\u0012\tr")));
                }
                final int n3 = a2;
                int n4 = 0;
                while (true) {
                    Label_0678: {
                        if (!q) {
                            break Label_0678;
                        }
                        final cc cc = new cc();
                        final String string2 = String.valueOf(string) + a("XL") + n4;
                        cc.d = ds.e(bx.a(String.valueOf(string2) + a("XY")));
                        cc.e = ds.e(bx.a(String.valueOf(string2) + a("X^"))) * 1000;
                        final dx k = ds.k(bx.a(String.valueOf(string2) + a("X\\")));
                        cc.f = k.a;
                        cc.g = k.b;
                        cc.h = k.c;
                        final ci a3 = cj.a(cc.d);
                        final int d;
                        Label_0616: {
                            if (q || a3 != null) {
                                d = a3.d;
                                if (q) {
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
                            ds.e(bx.a(String.valueOf(string2) + a("XN")));
                        }
                        Label_0668: {
                            Label_0651: {
                                switch (d) {
                                    case 1: {
                                        cc.i = 1;
                                        if (q) {
                                            break Label_0651;
                                        }
                                        break Label_0668;
                                    }
                                    case 2: {
                                        cc.i = 2;
                                        if (q) {
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
                cf.a((int)(n += a), a(":E\u001d\u0018v\u0018M\\,~\u0002B\\5q\u0010E\u000e\u0011~\u0002C\u0013\u0012"));
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
        final int e = ds.e(bx.a(a("\u0006K\b\u00141\u0018_\u0011")));
        if (e > 0) {
            n = 10.0 / (e + 1);
        }
        return n;
    }
    
    public int a() {
        return this.a.size();
    }
    
    public cd a(int n) {
        final boolean q = g.q;
        int n3;
        final int n2 = n3 = n;
        if (!q) {
            if (n2 < 0) {
                n = 0;
            }
            final int n4;
            n3 = (n4 = n);
        }
        if (!q) {
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
                            c2 = 'v';
                            break;
                        }
                        case 1: {
                            c2 = '*';
                            break;
                        }
                        case 2: {
                            c2 = '|';
                            break;
                        }
                        case 3: {
                            c2 = '|';
                            break;
                        }
                        default: {
                            c2 = '\u001f';
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
