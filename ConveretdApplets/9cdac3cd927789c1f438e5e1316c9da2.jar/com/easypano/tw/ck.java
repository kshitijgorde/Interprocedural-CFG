// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.util.Vector;

public class ck
{
    bz a;
    TWViewer b;
    Vector c;
    public static byte[] d;
    
    static {
        ck.d = new byte[] { -118, -112, -128, 2, 20, 96, -64, 66, -72, 0, 6, 12, -116, 1, -1, -62, -41, -64, 5, 120, -17, 125, -25, -5, 95, -7, 2, -72, -64, 8, 126, -80, -127, 71, 32, -124, -8, 86, 32, 2, 6, 118, 112, 12, 70, 96, -32, -9, 26, 65, -63, 37, 30, 2, -118, 27, 44, -124, 11, 23, 0, 3, 49, 40, 48, -116, 55, 67, 2, 1, 116, 32, 6, 29, 16, 64, 6, 98, -128, 1, 1, 72, -96, 0, 58, -26, -79, 0, 4, 16, -127, 8, 12, -71, 2, 18, 16, 0, -116, 51, 96, 99, 28, 11, -128, 4, 68, -56, -15, -112, -101, 28, 3, 20, 76, 89, 0, 40, -8, 111, -110, -103, 44, 1, 16, -5, 88, 8, 76, 46, -64, 1, -80, 60, 101, 14, 71, 32, -55, 83, 102, 65, 12, -128, -100, 1, 52, 67, -7, 2, 86, -98, -14, -116, -121, -128, -28, 43, 115, -72, -50, 67, -106, -64, 124, -57, 28, 103, 1, -116, 121, -56, 5, -40, 76, -113, 119, -116, -26, 10, -28, -72, 3, 70, 38, 114, -115, 5, -16, -30, 32, 31, -70, 2, 121, -114, 1, -109, -25, -100, -128, 33, -73, -9, -56, 32, 62, -64, 8, 122, 44, -128, 2, 31, -71, 0, 35, -120, -64, 5, -1, -110, 28, -22, 33, 71, 96, -52, 18, -72, 0, -97, 99, -52, -126, 3, 68, 64, -54, 107, 6, -12, -94, 99, -99, 106, 32, 75, -96, 8, 99, -50, -64, 5, 72, 32, -127, 27, -41, -8, 0, 49, 24, 51, -108, 81, 61, 2, 46, -113, 32, -55, -64, -114, -52, -88, 25, 77, 102, 22, 36, 121, -64, 99, 6, -78, -107, 81, 80, -29, 64, 87, 90, 2, 44, 32, -77, -83, 69, 12, 100, 63, -1, -39, -38, 44, -24, -13, -91, -89, 76, -126, -9, 30, 26, -56, -61, -10, 115, -84, 5, 32, 4, 12, 116, -128, -55, 67, 94, -73, -68, -57, 12, -20, 74, 119, -102, 8, -128, 46, 66, -66, -69, 29, 108, 117, -45, -69, -33, -55, -114, 76, -114, -109, -116, -28, 45, -73, -37, -57, -116, -82, 113, -110, 77, -52, 101, 61, 35, 28, -47, 37, -18, 54, -124, 15, 108, -17, 70, -33, 59, -45, 67, -122, -78, -111, 37, 48, 102, 24, -1, -101, -33, -74, -58, 64, -74, -39, 13, -14, 120, -1, 58, -58, -106, 94, 48, -116, -13, 29, 1, 10, 39, 57, -48, -4, -2, -73, -52, -43, -67, 25, 123, -17, 88, -54, 106, -90, 47, -102, 35, -64, -23, 44, 11, -95, -37, 55, -122, -9, 8, -20, -19, 94, 64, -117, -71, 0, 23, -24, -10, 8, -2, -100, -128, 17, 116, -128, 4, 112, 126, -78, 16, -2, 124, -21, 126, 11, -96, -40, 88, -18, 119, 6, -80, 78, -109, -14, 120, -13, -101, -45, 35, -88, -15, -114, -1, 45, -10, 4, 48, 57, -53, 57, -50, -128, -39, -101, 60, 115, 96, -1, -69, 52, 76, 86, -13, 8, 76, 61, -124, 28, 103, -7, 2, 67, -98, 115, -94, 57, -100, -19, 29, 19, 93, -56, 114, -122, 47, -102, -91, -116, 1, 60, -21, -104, -62, 115, 23, 64, -50, 81, -99, -25, -7, 38, -34, -58, -90, 46, 57, -69, 99, 48, -14, -61, 3, -100, -66, 127, -74, -68, -86, -89, 124, 0, -1, -74, -3, -14, 121, -83, 111, 2, 68, 92, 95, 19, 19, -63, -66, 9, 6, 60, -123, -23, 93, 95, 17, -57, 87, -60, 75, -88, 47, -123, -113, 48, -31, 34, -60, -41, -12, -102, -123, -12, -112, 3, -2, 118, -27, 50, -104, 8, -75, 95, -81, -16, -121, 79, -4, -33, 6, 1, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, -46, 0, 29, 0, 10, 0, 10, 0, 0, 6, 25, 64, -103, 48, 64, 12, 8, -121, -59, 36, 50, 73, -108, 49, -97, -48, -88, -108, -23, -124, 46, -87, 71, -91, 48, 8, 0, 33, -7, 4, 5, 50, 0, 50, 0, 44, -19, 0, 56, 0, 10, 0, 10, 0, 0, 6 };
    }
    
    public ck(final bz a, final TWViewer b) {
        this.c = new Vector(20, 10);
        this.a = a;
        this.b = b;
    }
    
    public void a(final by by, final cg cg) {
        final boolean q = g.q;
        double n = 20.0;
        final double a = this.a(by);
        final int e = dt.e(by.a(a("<Q\u0011v\ta\\\u0001u")));
        cg.a((int)n, a("\u0003]\u0015|\u0005!UTK\u000f*\\\u00118%!T\u001bj\u0001.F\u001dw\u0002"));
        int n2 = 0;
        cj cj;
        String string;
        String s;
        boolean equalsIgnoreCase;
        boolean b;
        int n3;
        boolean equalsIgnoreCase2;
        cj cj2;
        String a2;
        cj cj3;
        int n5;
        int n4;
        int a3;
        cj cj4 = null;
        int n7;
        int n6;
        int a4;
        cj cj5 = null;
        int n9;
        int n8;
        int a5;
        cj cj6 = null;
        int n11;
        int n10;
        int a6;
        cj cj7 = null;
        int n13;
        int n12;
        int a7;
        cj cj8 = null;
        int n15;
        int n14;
        int a8;
        cj cj9 = null;
        int n17;
        int n16;
        int a9;
        cj cj10 = null;
        int n19;
        int n18;
        int a10;
        cj cj11 = null;
        int d;
        String a11;
        int e2;
        String s2;
        int n20;
        Rectangle b2;
        int width;
        int height;
        dv dv;
        dv dv2;
        int n21;
        Label_2415_Outer:Label_2445_Outer:
        while (true) {
            Label_2923: {
                if (!q) {
                    break Label_2923;
                }
                cj = new cj();
                string = "s" + n2;
                s = by.a(String.valueOf(string) + a("aF\rh\t"));
                equalsIgnoreCase = s.equalsIgnoreCase(a(",K\u0018q\u0002+W\u0006"));
                Label_0178: {
                    if (!q) {
                        if (equalsIgnoreCase) {
                            cj.d = 3;
                            if (!q) {
                                break Label_0178;
                            }
                        }
                        s.equalsIgnoreCase(a(")^\u0015l"));
                    }
                    if (equalsIgnoreCase) {
                        cj.d = 1;
                        if (!q) {
                            break Label_0178;
                        }
                    }
                    cj.d = 2;
                }
                cj.e = by.a(String.valueOf(string) + a("a\\\u0015u\t"));
                cj.f = dt.e(by.a(String.valueOf(string) + a("aA\u001bm\u0002+")));
                n3 = ((b = (by.a() != 0)) ? 1 : 0);
                if (!q) {
                    switch (n3) {
                        case 1: {
                            cj.f = -1;
                            break;
                        }
                    }
                    cj.i = by.a(String.valueOf(string) + a("aV\u0011k\u000f=[\u0004l\u0005 \\"));
                    s = by.a(String.valueOf(string) + a("aF\u0006y\u0002<[\u0000q\u0003!W\u0012~\t,F"));
                    b = (equalsIgnoreCase2 = s.equalsIgnoreCase(a("-^\u001dv\b<")));
                }
                Label_0398: {
                    if (!q) {
                        if (n3 != 0) {
                            cj.g = 2;
                            if (!q) {
                                break Label_0398;
                            }
                        }
                        b = s.equalsIgnoreCase(a("5]\u001bu"));
                    }
                    if (b) {
                        cj.g = 3;
                        if (!q) {
                            break Label_0398;
                        }
                    }
                    cj.g = 1;
                }
                cj.j = by.a(String.valueOf(string) + a("a[\u0019\u007f"));
                cj.k = dt.j(cj.j);
                cj2 = cj;
                a2 = by.a(String.valueOf(string) + a("a\\\u0015q\u0000&_\u0013"));
                Label_0614: {
                    if (!q) {
                        cj2.l = a2;
                        s = by.a(String.valueOf(string) + a("aB\u0015v\u0001.J"));
                        switch (by.a()) {
                            case 1: {
                                cj3 = cj;
                                if (!q) {
                                    Label_0577: {
                                        switch (cj3.d) {
                                            case 3: {
                                                cj.m = 180.0;
                                                if (q) {
                                                    break Label_0577;
                                                }
                                                break Label_0614;
                                            }
                                            case 2: {
                                                cj.m = 180.0;
                                                if (q) {
                                                    break;
                                                }
                                                break Label_0614;
                                            }
                                        }
                                    }
                                }
                                cj3.m = -1.0;
                                if (q) {
                                    break;
                                }
                                break Label_0614;
                            }
                        }
                    }
                    cj2.m = dt.e(a2);
                }
                n4 = (n5 = cj.d);
                if (!q) {
                    switch (n4) {
                        case 2:
                        case 3: {
                            cj.m = cj.m * 3.141592653589793 / 180.0 + 3.141592653589793;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aB\u0015v\u0001&\\"));
                    n5 = (a3 = by.a());
                }
                Label_0810: {
                    Label_0799: {
                        Label_0788: {
                            if (!q) {
                                switch (n4) {
                                    case 1: {
                                        cj4 = cj;
                                        if (!q) {
                                            n5 = cj4.d;
                                            break;
                                        }
                                        break Label_0788;
                                    }
                                    default: {
                                        break Label_0799;
                                    }
                                }
                            }
                            Label_0773: {
                                switch (n5) {
                                    case 3: {
                                        cj.n = -180.0;
                                        if (q) {
                                            break Label_0773;
                                        }
                                        break Label_0810;
                                    }
                                    case 2: {
                                        cj.n = -180.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_0810;
                                    }
                                }
                            }
                        }
                        cj4.n = -1.0;
                        if (!q) {
                            break Label_0810;
                        }
                    }
                    cj.n = dt.e(s);
                }
                n6 = (n7 = cj.d);
                if (!q) {
                    switch (n6) {
                        case 2:
                        case 3: {
                            cj.n = cj.n * 3.141592653589793 / 180.0 + 3.141592653589793;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aB\u0015v"));
                    n7 = (a4 = by.a());
                }
                Label_1002: {
                    Label_0991: {
                        Label_0980: {
                            if (!q) {
                                switch (n6) {
                                    case 1: {
                                        cj5 = cj;
                                        if (!q) {
                                            n7 = cj5.d;
                                            break;
                                        }
                                        break Label_0980;
                                    }
                                    default: {
                                        break Label_0991;
                                    }
                                }
                            }
                            Label_0967: {
                                switch (n7) {
                                    case 3: {
                                        cj.o = 0.0;
                                        if (q) {
                                            break Label_0967;
                                        }
                                        break Label_1002;
                                    }
                                    case 2: {
                                        cj.o = 0.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_1002;
                                    }
                                }
                            }
                        }
                        cj5.o = -1.0;
                        if (!q) {
                            break Label_1002;
                        }
                    }
                    cj.o = dt.e(s);
                }
                n8 = (n9 = cj.d);
                if (!q) {
                    switch (n8) {
                        case 2:
                        case 3: {
                            cj.o = cj.o * 3.141592653589793 / 180.0 + 3.141592653589793;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aF\u001dt\u0018\"S\f"));
                    n9 = (a5 = by.a());
                }
                Label_1198: {
                    Label_1187: {
                        Label_1176: {
                            if (!q) {
                                switch (n8) {
                                    case 1: {
                                        cj6 = cj;
                                        if (!q) {
                                            n9 = cj6.d;
                                            break;
                                        }
                                        break Label_1176;
                                    }
                                    default: {
                                        break Label_1187;
                                    }
                                }
                            }
                            Label_1161: {
                                switch (n9) {
                                    case 3: {
                                        cj.p = 90.0;
                                        if (q) {
                                            break Label_1161;
                                        }
                                        break Label_1198;
                                    }
                                    case 2: {
                                        cj.p = 90.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_1198;
                                    }
                                }
                            }
                        }
                        cj6.p = -1.0;
                        if (!q) {
                            break Label_1198;
                        }
                    }
                    cj.p = dt.e(s);
                }
                n10 = (n11 = cj.d);
                if (!q) {
                    switch (n10) {
                        case 2:
                        case 3: {
                            cj.p = cj.p * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aF\u001dt\u0018\"[\u001a"));
                    n11 = (a6 = by.a());
                }
                Label_1390: {
                    Label_1379: {
                        Label_1368: {
                            if (!q) {
                                switch (n10) {
                                    case 1: {
                                        cj7 = cj;
                                        if (!q) {
                                            n11 = cj7.d;
                                            break;
                                        }
                                        break Label_1368;
                                    }
                                    default: {
                                        break Label_1379;
                                    }
                                }
                            }
                            Label_1353: {
                                switch (n11) {
                                    case 3: {
                                        cj.q = -90.0;
                                        if (q) {
                                            break Label_1353;
                                        }
                                        break Label_1390;
                                    }
                                    case 2: {
                                        cj.q = -90.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_1390;
                                    }
                                }
                            }
                        }
                        cj7.q = -1.0;
                        if (!q) {
                            break Label_1390;
                        }
                    }
                    cj.q = dt.e(s);
                }
                n12 = (n13 = cj.d);
                if (!q) {
                    switch (n12) {
                        case 2:
                        case 3: {
                            cj.q = cj.q * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aF\u001dt\u0018"));
                    n13 = (a7 = by.a());
                }
                Label_1578: {
                    Label_1567: {
                        Label_1556: {
                            if (!q) {
                                switch (n12) {
                                    case 1: {
                                        cj8 = cj;
                                        if (!q) {
                                            n13 = cj8.d;
                                            break;
                                        }
                                        break Label_1556;
                                    }
                                    default: {
                                        break Label_1567;
                                    }
                                }
                            }
                            Label_1543: {
                                switch (n13) {
                                    case 3: {
                                        cj.r = 0.0;
                                        if (q) {
                                            break Label_1543;
                                        }
                                        break Label_1578;
                                    }
                                    case 2: {
                                        cj.r = 0.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_1578;
                                    }
                                }
                            }
                        }
                        cj8.r = -1.0;
                        if (!q) {
                            break Label_1578;
                        }
                    }
                    cj.r = dt.e(s);
                }
                n14 = (n15 = cj.d);
                if (!q) {
                    switch (n14) {
                        case 2:
                        case 3: {
                            cj.r = cj.r * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aT\u001bn\u0001.J"));
                    n15 = (a8 = by.a());
                }
                Label_1770: {
                    Label_1759: {
                        Label_1748: {
                            if (!q) {
                                switch (n14) {
                                    case 1: {
                                        cj9 = cj;
                                        if (!q) {
                                            n15 = cj9.d;
                                            break;
                                        }
                                        break Label_1748;
                                    }
                                    default: {
                                        break Label_1759;
                                    }
                                }
                            }
                            Label_1733: {
                                switch (n15) {
                                    case 3: {
                                        cj.s = 180.0;
                                        if (q) {
                                            break Label_1733;
                                        }
                                        break Label_1770;
                                    }
                                    case 2: {
                                        cj.s = 180.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_1770;
                                    }
                                }
                            }
                        }
                        cj9.s = 10000.0;
                        if (!q) {
                            break Label_1770;
                        }
                    }
                    cj.s = dt.e(s);
                }
                n16 = (n17 = cj.d);
                if (!q) {
                    switch (n16) {
                        case 2:
                        case 3: {
                            cj.s = cj.s * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aT\u001bn\u0001&\\"));
                    n17 = (a9 = by.a());
                }
                Label_1956: {
                    Label_1945: {
                        Label_1936: {
                            if (!q) {
                                switch (n16) {
                                    case 1: {
                                        cj10 = cj;
                                        if (!q) {
                                            n17 = cj10.d;
                                            break;
                                        }
                                        break Label_1936;
                                    }
                                    default: {
                                        break Label_1945;
                                    }
                                }
                            }
                            Label_1923: {
                                switch (n17) {
                                    case 3: {
                                        cj.t = 1.0;
                                        if (q) {
                                            break Label_1923;
                                        }
                                        break Label_1956;
                                    }
                                    case 2: {
                                        cj.t = 1.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_1956;
                                    }
                                }
                            }
                        }
                        cj10.t = 1.0;
                        if (!q) {
                            break Label_1956;
                        }
                    }
                    cj.t = dt.e(s);
                }
                n18 = (n19 = cj.d);
                if (!q) {
                    switch (n18) {
                        case 2:
                        case 3: {
                            cj.t = cj.t * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = by.a(String.valueOf(string) + a("aT\u001bn"));
                    n19 = (a10 = by.a());
                }
                Label_2144: {
                    Label_2133: {
                        Label_2124: {
                            if (!q) {
                                switch (n18) {
                                    case 1: {
                                        cj11 = cj;
                                        if (!q) {
                                            n19 = cj11.d;
                                            break;
                                        }
                                        break Label_2124;
                                    }
                                    default: {
                                        break Label_2133;
                                    }
                                }
                            }
                            Label_2109: {
                                switch (n19) {
                                    case 3: {
                                        cj.u = 70.0;
                                        if (q) {
                                            break Label_2109;
                                        }
                                        break Label_2144;
                                    }
                                    case 2: {
                                        cj.u = 70.0;
                                        if (q) {
                                            break;
                                        }
                                        break Label_2144;
                                    }
                                }
                            }
                        }
                        cj11.u = 1.0;
                        if (!q) {
                            break Label_2144;
                        }
                    }
                    cj.u = dt.e(s);
                }
                d = cj.d;
            Label_2273:
                while (true) {
                    Label_2262: {
                        if (!q) {
                            switch (d) {
                                case 2:
                                case 3: {
                                    cj.u = cj.u * 3.141592653589793 / 180.0;
                                    break;
                                }
                            }
                            s = by.a(String.valueOf(string) + a("a_\u001bn\r-^\u0011"));
                            if (q) {
                                break Label_2262;
                            }
                            by.a();
                        }
                        switch (d) {
                            case 0: {
                                cj.v = dt.g(s);
                                break;
                            }
                            default: {
                                cj.v = true;
                                break Label_2273;
                            }
                        }
                    }
                    if (q) {
                        continue Label_2415_Outer;
                    }
                    break;
                }
                a11 = by.a(String.valueOf(string) + a("aA\u0004}\t+@\u0015l\t"));
                Label_2350: {
                    Label_2343: {
                        if (!q) {
                            switch (by.a()) {
                                case 0: {
                                    cj.w = dt.e(a11);
                                    break;
                                }
                                default: {
                                    break Label_2343;
                                }
                            }
                        }
                        if (!q) {
                            break Label_2350;
                        }
                    }
                    cj.w = 20;
                }
                e2 = dt.e(by.a(String.valueOf(string) + a("aZ\u001bl\u001f?]\u00006\u0002:_")));
                s2 = String.valueOf(string) + a("aZ\u0007");
                n20 = 0;
                while (true) {
                    while (true) {
                        Label_2881: {
                            if (!q) {
                                break Label_2881;
                            }
                            s2 = String.valueOf(string) + a("aZ\u0007") + n20;
                            b2 = dt.b(by.a(String.valueOf(s2) + a("aP\u001bm\u0002+A")));
                            width = b2.width;
                            Label_2878: {
                                if (!q) {
                                    if (width <= 0) {
                                        break Label_2878;
                                    }
                                    height = b2.height;
                                }
                                if (width > 0) {
                                    dv = new dv();
                                    dv.c((Image)this.a.d.a(by.a(String.valueOf(s2) + a("a_\u001bm\u001f*V\u001bo\u0002&_\u0013")), 1));
                                    dv.b((Image)this.a.d.a(by.a(String.valueOf(s2) + a("a_\u001bm\u001f*]\u0002}\u001e&_\u0013")), 1));
                                    dv.a((Image)this.a.d.a(by.a(String.valueOf(s2) + a("a[\u0019\u007f")), 1));
                                    dv.a(dt.a(by.a(String.valueOf(s2) + a("aQ\u001bt\u0003="))));
                                    dv.a(by.a(String.valueOf(s2) + a("aZ\u001dv\u0018aF\u0011`\u0018")), dt.a(by.a(String.valueOf(s2) + a("aZ\u001dv\u0018aP\u0013"))));
                                    dv.a(new cl(this, by.a(String.valueOf(s2) + a("aS\u0017l\u0005 \\"))));
                                    dv2 = dv;
                                    n21 = -100;
                                    Label_2869: {
                                        if (!q) {
                                            dv2.a(n21, -100, b2.width, b2.height);
                                            if (by.a(String.valueOf(s2) + a("aS\u0004h\t.@\u0015v\u000f*")).equalsIgnoreCase(a(")^\u001by\u0018"))) {
                                                dv.a(1);
                                                if (!q) {
                                                    break Label_2869;
                                                }
                                            }
                                        }
                                        dv2.a(n21);
                                    }
                                    cj.a(dv, b2);
                                }
                            }
                            ++n20;
                        }
                        if (n20 < e2) {
                            continue Label_2445_Outer;
                        }
                        break;
                    }
                    this.c.addElement(cj);
                    cg.a((int)(n += a), a("\u0003]\u0015|\u0005!UTK\u000f*\\\u00118%!T\u001bj\u0001.F\u001dw\u0002"));
                    if (q) {
                        continue;
                    }
                    break;
                }
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
        final int e = dt.e(by.a(a("<Q\u0011v\ta\\\u0001u")));
        if (e > 0) {
            n = 10.0 / (e + 1);
        }
        return n;
    }
    
    public int a() {
        return this.c.size();
    }
    
    public cj a(int n) {
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
            if (n2 >= this.c.size()) {
                n = this.c.size() - 1;
            }
            n3 = n;
        }
        if (n3 >= 0) {
            return this.c.elementAt(n);
        }
        return null;
    }
    
    public void b() {
        final boolean q = g.q;
        int n = 0;
        while (true) {
            while (true) {
                Label_0027: {
                    if (!q) {
                        break Label_0027;
                    }
                    ((cj)this.c.elementAt(n)).destroyResource();
                    ++n;
                }
                if (n < this.c.size()) {
                    continue;
                }
                break;
            }
            this.c.removeAllElements();
            this.c = null;
            this.a = null;
            this.b = null;
            if (!q) {
                return;
            }
            continue;
        }
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
                            c2 = 'O';
                            break;
                        }
                        case 1: {
                            c2 = '2';
                            break;
                        }
                        case 2: {
                            c2 = 't';
                            break;
                        }
                        case 3: {
                            c2 = '\u0018';
                            break;
                        }
                        default: {
                            c2 = 'l';
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
