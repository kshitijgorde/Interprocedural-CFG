// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.util.Vector;

public class cj
{
    by a;
    TWViewer b;
    Vector c;
    
    public cj(final by a, final TWViewer b) {
        this.c = new Vector(20, 10);
        this.a = a;
        this.b = b;
    }
    
    public void a(final bx bx, final cf cf) {
        final int q = h.q;
        double n = 20.0;
        final double a = this.a(bx);
        final int e = ds.e(bx.a(a("RWIZ<\u000fZYY")));
        cf.a((int)n, a("m[MP0OS\fg:DZI\u0014\u0010ORCF4@@E[7"));
        int n2 = 0;
        ci ci;
        String string;
        String s;
        boolean equalsIgnoreCase;
        boolean b;
        int n3;
        boolean equalsIgnoreCase2;
        ci ci2;
        String a2;
        ci ci3;
        int n5;
        int n4;
        int a3;
        ci ci4 = null;
        int n7;
        int n6;
        int a4;
        ci ci5 = null;
        int n9;
        int n8;
        int a5;
        ci ci6 = null;
        int n11;
        int n10;
        int a6;
        ci ci7 = null;
        int n13;
        int n12;
        int a7;
        ci ci8 = null;
        int n15;
        int n14;
        int a8;
        ci ci9 = null;
        int n17;
        int n16;
        int a9;
        ci ci10 = null;
        int n19;
        int n18;
        int a10;
        ci ci11 = null;
        int d;
        String a11;
        int e2;
        String s2;
        int n20;
        Rectangle b2;
        int width;
        int height;
        du du;
        du du2;
        int n21;
        Label_2415_Outer:Label_2445_Outer:
        while (true) {
            Label_2923: {
                if (q == 0) {
                    break Label_2923;
                }
                ci = new ci();
                string = "s" + n2;
                s = bx.a(String.valueOf(string) + a("\u000f@UD<"));
                equalsIgnoreCase = s.equalsIgnoreCase(a("BM@]7EQ^"));
                Label_0178: {
                    if (q == 0) {
                        if (equalsIgnoreCase) {
                            ci.d = 3;
                            if (q == 0) {
                                break Label_0178;
                            }
                        }
                        s.equalsIgnoreCase(a("GXM@"));
                    }
                    if (equalsIgnoreCase) {
                        ci.d = 1;
                        if (q == 0) {
                            break Label_0178;
                        }
                    }
                    ci.d = 2;
                }
                ci.e = bx.a(String.valueOf(string) + a("\u000fZMY<"));
                ci.f = ds.e(bx.a(String.valueOf(string) + a("\u000fGCA7E")));
                n3 = ((b = (bx.a() != 0)) ? 1 : 0);
                if (q == 0) {
                    switch (n3) {
                        case 1: {
                            ci.f = -1;
                            break;
                        }
                    }
                    ci.i = bx.a(String.valueOf(string) + a("\u000fPIG:S]\\@0NZ"));
                    s = bx.a(String.valueOf(string) + a("\u000f@^U7R]X]6OQJR<B@"));
                    b = (equalsIgnoreCase2 = s.equalsIgnoreCase(a("CXEZ=R")));
                }
                Label_0398: {
                    if (q == 0) {
                        if (n3 != 0) {
                            ci.g = 2;
                            if (q == 0) {
                                break Label_0398;
                            }
                        }
                        b = s.equalsIgnoreCase(a("[[CY"));
                    }
                    if (b) {
                        ci.g = 3;
                        if (q == 0) {
                            break Label_0398;
                        }
                    }
                    ci.g = 1;
                }
                ci.j = bx.a(String.valueOf(string) + a("\u000f]AS"));
                ci.k = ds.j(ci.j);
                ci2 = ci;
                a2 = bx.a(String.valueOf(string) + a("\u000fZM]5HYK"));
                Label_0614: {
                    if (q == 0) {
                        ci2.l = a2;
                        s = bx.a(String.valueOf(string) + a("\u000fDMZ4@L"));
                        switch (bx.a()) {
                            case 1: {
                                ci3 = ci;
                                if (q == 0) {
                                    Label_0577: {
                                        switch (ci3.d) {
                                            case 3: {
                                                ci.o = 180.0;
                                                if (q != 0) {
                                                    break Label_0577;
                                                }
                                                break Label_0614;
                                            }
                                            case 2: {
                                                ci.o = 180.0;
                                                if (q != 0) {
                                                    break;
                                                }
                                                break Label_0614;
                                            }
                                        }
                                    }
                                }
                                ci3.o = -1.0;
                                if (q != 0) {
                                    break;
                                }
                                break Label_0614;
                            }
                        }
                    }
                    ci2.o = ds.e(a2);
                }
                n4 = (n5 = ci.d);
                if (q == 0) {
                    switch (n4) {
                        case 2:
                        case 3: {
                            ci.o = ci.o * 3.141592653589793 / 180.0 + 3.141592653589793;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000fDMZ4HZ"));
                    n5 = (a3 = bx.a());
                }
                Label_0810: {
                    Label_0799: {
                        Label_0788: {
                            if (q == 0) {
                                switch (n4) {
                                    case 1: {
                                        ci4 = ci;
                                        if (q == 0) {
                                            n5 = ci4.d;
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
                                        ci.p = -180.0;
                                        if (q != 0) {
                                            break Label_0773;
                                        }
                                        break Label_0810;
                                    }
                                    case 2: {
                                        ci.p = -180.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_0810;
                                    }
                                }
                            }
                        }
                        ci4.p = -1.0;
                        if (q == 0) {
                            break Label_0810;
                        }
                    }
                    ci.p = ds.e(s);
                }
                n6 = (n7 = ci.d);
                if (q == 0) {
                    switch (n6) {
                        case 2:
                        case 3: {
                            ci.p = ci.p * 3.141592653589793 / 180.0 + 3.141592653589793;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000fDMZ"));
                    n7 = (a4 = bx.a());
                }
                Label_1002: {
                    Label_0991: {
                        Label_0980: {
                            if (q == 0) {
                                switch (n6) {
                                    case 1: {
                                        ci5 = ci;
                                        if (q == 0) {
                                            n7 = ci5.d;
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
                                        ci.q = 0.0;
                                        if (q != 0) {
                                            break Label_0967;
                                        }
                                        break Label_1002;
                                    }
                                    case 2: {
                                        ci.q = 0.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_1002;
                                    }
                                }
                            }
                        }
                        ci5.q = -1.0;
                        if (q == 0) {
                            break Label_1002;
                        }
                    }
                    ci.q = ds.e(s);
                }
                n8 = (n9 = ci.d);
                if (q == 0) {
                    switch (n8) {
                        case 2:
                        case 3: {
                            ci.q = ci.q * 3.141592653589793 / 180.0 + 3.141592653589793;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000f@EX-LUT"));
                    n9 = (a5 = bx.a());
                }
                Label_1198: {
                    Label_1187: {
                        Label_1176: {
                            if (q == 0) {
                                switch (n8) {
                                    case 1: {
                                        ci6 = ci;
                                        if (q == 0) {
                                            n9 = ci6.d;
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
                                        ci.r = 90.0;
                                        if (q != 0) {
                                            break Label_1161;
                                        }
                                        break Label_1198;
                                    }
                                    case 2: {
                                        ci.r = 90.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_1198;
                                    }
                                }
                            }
                        }
                        ci6.r = -1.0;
                        if (q == 0) {
                            break Label_1198;
                        }
                    }
                    ci.r = ds.e(s);
                }
                n10 = (n11 = ci.d);
                if (q == 0) {
                    switch (n10) {
                        case 2:
                        case 3: {
                            ci.r = ci.r * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000f@EX-L]B"));
                    n11 = (a6 = bx.a());
                }
                Label_1390: {
                    Label_1379: {
                        Label_1368: {
                            if (q == 0) {
                                switch (n10) {
                                    case 1: {
                                        ci7 = ci;
                                        if (q == 0) {
                                            n11 = ci7.d;
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
                                        ci.s = -90.0;
                                        if (q != 0) {
                                            break Label_1353;
                                        }
                                        break Label_1390;
                                    }
                                    case 2: {
                                        ci.s = -90.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_1390;
                                    }
                                }
                            }
                        }
                        ci7.s = -1.0;
                        if (q == 0) {
                            break Label_1390;
                        }
                    }
                    ci.s = ds.e(s);
                }
                n12 = (n13 = ci.d);
                if (q == 0) {
                    switch (n12) {
                        case 2:
                        case 3: {
                            ci.s = ci.s * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000f@EX-"));
                    n13 = (a7 = bx.a());
                }
                Label_1578: {
                    Label_1567: {
                        Label_1556: {
                            if (q == 0) {
                                switch (n12) {
                                    case 1: {
                                        ci8 = ci;
                                        if (q == 0) {
                                            n13 = ci8.d;
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
                                        ci.t = 0.0;
                                        if (q != 0) {
                                            break Label_1543;
                                        }
                                        break Label_1578;
                                    }
                                    case 2: {
                                        ci.t = 0.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_1578;
                                    }
                                }
                            }
                        }
                        ci8.t = -1.0;
                        if (q == 0) {
                            break Label_1578;
                        }
                    }
                    ci.t = ds.e(s);
                }
                n14 = (n15 = ci.d);
                if (q == 0) {
                    switch (n14) {
                        case 2:
                        case 3: {
                            ci.t = ci.t * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000fRCB4@L"));
                    n15 = (a8 = bx.a());
                }
                Label_1770: {
                    Label_1759: {
                        Label_1748: {
                            if (q == 0) {
                                switch (n14) {
                                    case 1: {
                                        ci9 = ci;
                                        if (q == 0) {
                                            n15 = ci9.d;
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
                                        ci.u = 180.0;
                                        if (q != 0) {
                                            break Label_1733;
                                        }
                                        break Label_1770;
                                    }
                                    case 2: {
                                        ci.u = 180.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_1770;
                                    }
                                }
                            }
                        }
                        ci9.u = 10000.0;
                        if (q == 0) {
                            break Label_1770;
                        }
                    }
                    ci.u = ds.e(s);
                }
                n16 = (n17 = ci.d);
                if (q == 0) {
                    switch (n16) {
                        case 2:
                        case 3: {
                            ci.u = ci.u * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000fRCB4HZ"));
                    n17 = (a9 = bx.a());
                }
                Label_1956: {
                    Label_1945: {
                        Label_1936: {
                            if (q == 0) {
                                switch (n16) {
                                    case 1: {
                                        ci10 = ci;
                                        if (q == 0) {
                                            n17 = ci10.d;
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
                                        ci.v = 1.0;
                                        if (q != 0) {
                                            break Label_1923;
                                        }
                                        break Label_1956;
                                    }
                                    case 2: {
                                        ci.v = 1.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_1956;
                                    }
                                }
                            }
                        }
                        ci10.v = 1.0;
                        if (q == 0) {
                            break Label_1956;
                        }
                    }
                    ci.v = ds.e(s);
                }
                n18 = (n19 = ci.d);
                if (q == 0) {
                    switch (n18) {
                        case 2:
                        case 3: {
                            ci.v = ci.v * 3.141592653589793 / 180.0;
                            break;
                        }
                    }
                    s = bx.a(String.valueOf(string) + a("\u000fRCB"));
                    n19 = (a10 = bx.a());
                }
                Label_2144: {
                    Label_2133: {
                        Label_2124: {
                            if (q == 0) {
                                switch (n18) {
                                    case 1: {
                                        ci11 = ci;
                                        if (q == 0) {
                                            n19 = ci11.d;
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
                                        ci.w = 70.0;
                                        if (q != 0) {
                                            break Label_2109;
                                        }
                                        break Label_2144;
                                    }
                                    case 2: {
                                        ci.w = 70.0;
                                        if (q != 0) {
                                            break;
                                        }
                                        break Label_2144;
                                    }
                                }
                            }
                        }
                        ci11.w = 1.0;
                        if (q == 0) {
                            break Label_2144;
                        }
                    }
                    ci.w = ds.e(s);
                }
                d = ci.d;
            Label_2273:
                while (true) {
                    Label_2262: {
                        if (q == 0) {
                            switch (d) {
                                case 2:
                                case 3: {
                                    ci.w = ci.w * 3.141592653589793 / 180.0;
                                    break;
                                }
                            }
                            s = bx.a(String.valueOf(string) + a("\u000fYCB8CXI"));
                            if (q != 0) {
                                break Label_2262;
                            }
                            bx.a();
                        }
                        switch (d) {
                            case 0: {
                                ci.x = ds.g(s);
                                break;
                            }
                            default: {
                                ci.x = true;
                                break Label_2273;
                            }
                        }
                    }
                    if (q != 0) {
                        continue Label_2415_Outer;
                    }
                    break;
                }
                a11 = bx.a(String.valueOf(string) + a("\u000fG\\Q<EFM@<"));
                Label_2350: {
                    Label_2343: {
                        if (q == 0) {
                            switch (bx.a()) {
                                case 0: {
                                    ci.y = ds.e(a11);
                                    break;
                                }
                                default: {
                                    break Label_2343;
                                }
                            }
                        }
                        if (q == 0) {
                            break Label_2350;
                        }
                    }
                    ci.y = 20;
                }
                e2 = ds.e(bx.a(String.valueOf(string) + a("\u000f\\C@*Q[X\u001a7TY")));
                s2 = String.valueOf(string) + a("\u000f\\_");
                n20 = 0;
                while (true) {
                    while (true) {
                        Label_2881: {
                            if (q == 0) {
                                break Label_2881;
                            }
                            s2 = String.valueOf(string) + a("\u000f\\_") + n20;
                            b2 = ds.b(bx.a(String.valueOf(s2) + a("\u000fVCA7EG")));
                            width = b2.width;
                            Label_2878: {
                                if (q == 0) {
                                    if (width <= 0) {
                                        break Label_2878;
                                    }
                                    height = b2.height;
                                }
                                if (width > 0) {
                                    du = new du();
                                    du.c((Image)this.a.d.a(bx.a(String.valueOf(s2) + a("\u000fYCA*DPCC7HYK")), 1));
                                    du.b((Image)this.a.d.a(bx.a(String.valueOf(s2) + a("\u000fYCA*D[ZQ+HYK")), 1));
                                    du.a((Image)this.a.d.a(bx.a(String.valueOf(s2) + a("\u000f]AS")), 1));
                                    du.a(ds.a(bx.a(String.valueOf(s2) + a("\u000fWCX6S"))));
                                    du.a(bx.a(String.valueOf(s2) + a("\u000f\\EZ-\u000f@IL-")), ds.a(bx.a(String.valueOf(s2) + a("\u000f\\EZ-\u000fVK"))));
                                    du.a(new ck(this, bx.a(String.valueOf(s2) + a("\u000fUO@0NZ"))));
                                    du2 = du;
                                    n21 = -100;
                                    Label_2869: {
                                        if (q == 0) {
                                            du2.a(n21, -100, b2.width, b2.height);
                                            if (bx.a(String.valueOf(s2) + a("\u000fU\\D<@FMZ:D")).equalsIgnoreCase(a("GXCU-"))) {
                                                du.a(1);
                                                if (q == 0) {
                                                    break Label_2869;
                                                }
                                            }
                                        }
                                        du2.a(n21);
                                    }
                                    ci.a(du, b2);
                                }
                            }
                            ++n20;
                        }
                        if (n20 < e2) {
                            continue Label_2445_Outer;
                        }
                        break;
                    }
                    this.c.addElement(ci);
                    cf.a((int)(n += a), a("m[MP0OS\fg:DZI\u0014\u0010ORCF4@@E[7"));
                    if (q != 0) {
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
    
    private double a(final bx bx) {
        double n = 10.0;
        final int e = ds.e(bx.a(a("RWIZ<\u000fZYY")));
        if (e > 0) {
            n = 10.0 / (e + 1);
        }
        return n;
    }
    
    public int a() {
        return this.c.size();
    }
    
    public ci a(int n) {
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
        final int q = h.q;
        int n = 0;
        while (true) {
            while (true) {
                Label_0027: {
                    if (q == 0) {
                        break Label_0027;
                    }
                    ((ci)this.c.elementAt(n)).destroyResource();
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
            if (q == 0) {
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
                            c2 = '!';
                            break;
                        }
                        case 1: {
                            c2 = '4';
                            break;
                        }
                        case 2: {
                            c2 = ',';
                            break;
                        }
                        case 3: {
                            c2 = '4';
                            break;
                        }
                        default: {
                            c2 = 'Y';
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
