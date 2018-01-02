// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class cf
{
    private Vector a;
    private ck b;
    private int c;
    private int d;
    private boolean e;
    public static byte[] f;
    
    static {
        cf.f = new byte[] { -127, -63, -13, 79, 1, -43, -32, 0, 0, 21, -82, 1, 43, -30, -39, 4, 17, -63, 19, -97, 88, -23, -125, -64, -13, 64, -10, 54, 72, 66, -119, 124, -32, 3, 83, 120, 0, 3, -86, 112, -62, 18, -66, -31, 3, 4, 16, 2, 1, 98, 40, 5, 2, -96, -112, 8, 41, 72, -127, 19, 102, -120, 67, 29, -70, -16, 12, 4, 64, -128, 10, 9, -96, -126, 7, 16, 96, 5, 49, 56, -127, 17, 95, -80, 66, 33, 40, 113, -123, 31, 112, 65, 12, 84, 56, 69, 6, -72, -128, 1, 38, 48, -63, 3, 52, -48, 66, 6, 60, 32, 6, 12, -104, 97, 11, 64, 16, 3, 30, 10, 65, 3, 89, -36, 34, 8, 90, 64, -128, 49, -58, -96, -117, 95, 60, -31, 10, -102, -8, 70, 41, 122, -1, 49, 6, 32, -40, -94, 11, 102, -104, -126, 19, -28, 81, 3, 83, 68, 34, 9, -125, 56, -60, 18, 60, 64, 5, 37, -120, 65, 11, 84, -64, -128, 22, -108, -32, 4, 67, 48, -63, 11, 98, 80, -126, 22, -60, 64, -120, -105, 44, -63, 17, -45, -88, 1, 30, 62, 0, 1, 96, 100, -64, 10, 36, 89, 70, 26, -58, 0, -115, 90, -44, -128, 38, -61, -104, 72, 79, -126, 50, -120, 54, 28, -126, 11, 16, 112, -126, 23, 32, -128, 1, 41, 72, -28, 28, 63, -128, 0, 23, 104, -96, -105, 32, 64, -64, 13, 55, -8, 73, 25, -58, -80, -104, 8, 48, 1, 17, 111, 57, -124, 79, -82, -128, -103, -104, -60, 100, 41, -115, 57, 69, 80, -82, 32, -120, 8, -120, -95, 25, -87, -55, 0, 102, 118, 51, -116, -57, 124, -91, 41, -49, -120, -128, 21, 104, 50, 5, 42, -80, -28, 33, 17, -128, 73, 34, -26, -46, -123, -59, -100, 102, 49, 75, -64, 68, 6, 124, -128, -116, -28, 84, -127, 9, -124, 16, -51, 47, 78, -77, -116, 42, -120, 65, 10, 64, -119, -128, 22, -100, -128, -121, -37, -100, -26, 47, 81, -56, -53, 78, 6, 116, -96, -1, 36, -126, 9, 84, -96, -126, 21, -88, -96, -100, -35, -108, 34, 38, 121, 89, 2, 31, -110, -16, -103, 42, -40, 99, 56, -33, 88, 2, 21, 4, 17, -97, 50, -108, 102, 63, -123, 48, 80, 2, 120, -111, -99, -1, 38, 120, -91, 38, 53, 25, 81, -127, 102, -77, -111, -39, 44, 104, 53, -109, 41, 78, 35, 12, -76, -113, -20, 60, -63, 9, 74, -96, 76, 76, 14, 21, 1, -128, 116, -95, 6, 24, -96, 1, 16, -112, 113, -87, 67, 96, 0, 3, 32, 73, -124, -91, 82, -107, -87, 96, 76, 42, 30, -89, -118, 71, 13, 64, 21, -116, 78, 37, -93, 83, -117, 0, 2, -82, 46, 85, -85, 32, -16, -22, 10, -57, 90, -124, 19, 112, -107, -83, 110, 61, -85, 16, -108, -7, 67, 78, 60, 115, -104, 117, -51, -85, 94, -9, -54, -41, -66, -6, -11, -81, -128, 13, -84, 96, 7, 75, -40, -62, 26, -10, -80, -120, 77, -84, 98, 23, -53, -40, -58, 58, -10, -79, -112, -115, -84, 100, 39, 75, -39, -54, 90, -10, -78, -104, -51, -84, 102, 55, -53, -39, -50, 122, -10, -77, -96, 13, -83, 104, 71, 75, -38, -46, -102, -10, -76, -88, 77, -83, 106, 87, -53, -38, -42, -70, -10, -75, -80, -115, -83, 108, 103, 75, -37, -38, -38, -10, -74, -72, -51, -83, 110, 119, -53, -37, -34, -6, -10, -73, -64, 13, -82, 112, -121, 75, -36, -30, 26, -9, -72, -56, 77, -82, 114, -105, -53, -36, -26, 58, -9, -71, -48, -115, -82, 116, -89, 75, -35, -22, 90, -9, -70, -40, -51, -82, 118, -73, -53, -35, -18, 122, -9, -69, -32, 13, -81, 120, -57, 75, -34, -14, -102, -9, -68, -24, 77, -1, -81, 122, 27, -101, -128, 4, 68, 32, 6, 17, 72, 64, 5, 98, 112, -127, -10, 86, 64, -66, -12, 109, -81, 16, -20, -37, -34, 11, -60, -32, -66, -17, -115, 111, 2 };
    }
    
    public cf(final ck b) {
        this.a = new Vector();
        this.c = -1;
        this.d = -1;
        this.e = true;
        this.b = b;
    }
    
    public void a(final by by, final ck ck, final cg cg) {
        final boolean q = g.q;
        double n = 30.0;
        final double a = this.a(by);
        final int e = dt.e(by.a(a("x,\u0016i?f8\u000f")));
        cg.a((int)n, a("D\"\u0003exf*BQp|%BH\u007fn\"\u0010lp|$\ro"));
        this.c = dt.e(by.a(a("x,\u0016i?l(\u0004`dd9")));
        this.e = dt.g(by.a(a("x,\u0016i?i8\u0016nad,\u001b")));
        int n2 = 0;
        while (true) {
            Label_0717: {
                if (!q) {
                    break Label_0717;
                }
                final ce ce = new ce();
                final String string = "p" + n2;
                ce.h = by.a(String.valueOf(string) + a("&#\u0003lt"));
                ce.i = dt.e(by.a(String.valueOf(string) + a("&>\rt\u007fl")));
                final int a2 = by.a();
                if (!q) {
                    switch (a2) {
                        case 1: {
                            ce.i = -1;
                            break;
                        }
                    }
                    ce.j = dt.g(by.a(String.valueOf(string) + a("&!\rna")));
                    dt.e(by.a(String.valueOf(string) + a("&+\u0010`|mc\ft|")));
                }
                final int n3 = a2;
                int n4 = 0;
                while (true) {
                    Label_0678: {
                        if (!q) {
                            break Label_0678;
                        }
                        final cd cd = new cd();
                        final String string2 = String.valueOf(string) + a("&+") + n4;
                        cd.d = dt.e(by.a(String.valueOf(string2) + a("&>")));
                        cd.e = dt.e(by.a(String.valueOf(string2) + a("&9"))) * 1000;
                        final ea k = dt.k(by.a(String.valueOf(string2) + a("&;")));
                        cd.f = k.a;
                        cd.g = k.b;
                        cd.h = k.c;
                        final cj a3 = ck.a(cd.d);
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
                                        cd.f = cd.f * 3.141592653589793 / 180.0 + 3.141592653589793;
                                        cd.g = k.b * 3.141592653589793 / 180.0;
                                        cd.h = k.c * 3.141592653589793 / 180.0;
                                        break;
                                    }
                                }
                            }
                            dt.e(by.a(String.valueOf(string2) + a("&)")));
                        }
                        Label_0668: {
                            Label_0651: {
                                switch (d) {
                                    case 1: {
                                        cd.i = 1;
                                        if (q) {
                                            break Label_0651;
                                        }
                                        break Label_0668;
                                    }
                                    case 2: {
                                        cd.i = 2;
                                        if (q) {
                                            break;
                                        }
                                        break Label_0668;
                                    }
                                }
                            }
                            cd.i = 3;
                        }
                        ce.a(cd);
                        ++n4;
                    }
                    if (n4 < n3) {
                        continue;
                    }
                    break;
                }
                this.a.addElement(ce);
                cg.a((int)(n += a), a("D\"\u0003exf*BQp|%BH\u007fn\"\u0010lp|$\ro"));
                ++n2;
            }
            if (n2 >= e) {
                return;
            }
            continue;
        }
    }
    
    private double a(final by by) {
        double n = 10.0;
        final int e = dt.e(by.a(a("x,\u0016i?f8\u000f")));
        if (e > 0) {
            n = 10.0 / (e + 1);
        }
        return n;
    }
    
    public int a() {
        return this.a.size();
    }
    
    public ce a(int n) {
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
                            c2 = '\b';
                            break;
                        }
                        case 1: {
                            c2 = 'M';
                            break;
                        }
                        case 2: {
                            c2 = 'b';
                            break;
                        }
                        case 3: {
                            c2 = '\u0001';
                            break;
                        }
                        default: {
                            c2 = '\u0011';
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
