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
        final boolean q = h.q;
        double n = 30.0;
        final double a = this.a(bx);
        final int e = dt.e(bx.a(a("gn1V\u0017yz(")));
        cf.a((int)n, a("[`$ZPyhenXcgewWq`7SXcf*P"));
        this.c = dt.e(bx.a(a("gn1V\u0017sj#_L{{")));
        this.e = dt.g(bx.a(a("gn1V\u0017vz1QI{n<")));
        int n2 = 0;
        while (true) {
            Label_0717: {
                if (!q) {
                    break Label_0717;
                }
                final cd cd = new cd();
                final String string = "p" + n2;
                cd.h = bx.a(String.valueOf(string) + a("9a$S\\"));
                cd.i = dt.e(bx.a(String.valueOf(string) + a("9|*KWs")));
                final int a2 = bx.a();
                if (!q) {
                    switch (a2) {
                        case 1: {
                            cd.i = -1;
                            break;
                        }
                    }
                    cd.j = dt.g(bx.a(String.valueOf(string) + a("9c*QI")));
                    dt.e(bx.a(String.valueOf(string) + a("9i7_Tr!+KT")));
                }
                final int n3 = a2;
                int n4 = 0;
                while (true) {
                    Label_0678: {
                        if (!q) {
                            break Label_0678;
                        }
                        final cc cc = new cc();
                        final String string2 = String.valueOf(string) + a("9i") + n4;
                        cc.d = dt.e(bx.a(String.valueOf(string2) + a("9|")));
                        cc.e = dt.e(bx.a(String.valueOf(string2) + a("9{"))) * 1000;
                        final dy k = dt.k(bx.a(String.valueOf(string2) + a("9y")));
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
                            dt.e(bx.a(String.valueOf(string2) + a("9k")));
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
                cf.a((int)(n += a), a("[`$ZPyhenXcgewWq`7SXcf*P"));
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
        final int e = dt.e(bx.a(a("gn1V\u0017yz(")));
        if (e > 0) {
            n = 10.0 / (e + 1);
        }
        return n;
    }
    
    public int a() {
        return this.a.size();
    }
    
    public cd a(int n) {
        final boolean q = h.q;
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
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = '\u000f';
                            break;
                        }
                        case 2: {
                            c2 = 'E';
                            break;
                        }
                        case 3: {
                            c2 = '>';
                            break;
                        }
                        default: {
                            c2 = '9';
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
