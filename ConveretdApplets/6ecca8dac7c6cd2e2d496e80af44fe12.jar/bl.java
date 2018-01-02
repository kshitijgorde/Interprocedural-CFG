import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class bl
{
    bu a;
    br b;
    bq c;
    be d;
    bc e;
    n f;
    bk g;
    String h;
    Color i;
    
    bl(final bu a, final br b, final bq c, final be d, final bc e, final n f, final bk g, final String h) {
        this.i = new Color(255, 15, 237);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    String a(String a, final v v, final String s, final String s2) {
        final boolean dx = bm.dX;
        a = this.a(a);
        if (a.length() == 0) {
            return null;
        }
        Label_5407: {
            if (a.charAt(0) == '/' && irc.bw) {
                final int index = a.indexOf(32);
                String s3 = a;
                String s4 = null;
                if (index > 0) {
                    s3 = a.substring(0, index);
                    s4 = a.substring(index + 1).trim();
                    if (s4.equals("")) {
                        s4 = null;
                    }
                }
                int n = 0;
                while (n++ <= 5) {
                    String s5 = this.e.a(s3);
                    while (true) {
                        Label_0288: {
                            if (s5 == null) {
                                break Label_0288;
                            }
                            s5.indexOf(32);
                            final int index2;
                            final int n2 = index2;
                            if (n2 < 0) {
                                s3 = s5;
                                if (!dx) {
                                    break Label_0288;
                                }
                            }
                            s3 = s5.substring(0, n2);
                            s5 = s5.substring(n2 + 1);
                            if (s5.charAt(0) == '#') {
                                s4 = new String(s5 + s4);
                                if (!dx) {
                                    break Label_0288;
                                }
                            }
                            if (s4 != null) {
                                s4 = new String(s5 + " " + s4);
                                if (!dx) {
                                    break Label_0288;
                                }
                            }
                            s4 = s5;
                        }
                        if (s5 == null) {
                            final int index2;
                            final int n3 = index2 = s3.indexOf(32);
                            if (dx) {
                                continue;
                            }
                            if (index2 > 0) {
                                Label_0376: {
                                    if (s4 != null) {
                                        s4 = new String(s3.substring(n3 + 1) + s4);
                                        if (!dx) {
                                            break Label_0376;
                                        }
                                    }
                                    s4 = new String(s3.substring(n3 + 1));
                                }
                                s3 = s3.substring(0, n3);
                            }
                            if (s3.toUpperCase().equals(b("q3 #d\r"))) {
                                if (s4 == null) {
                                    this.e.a(v);
                                    if (!dx) {
                                        return null;
                                    }
                                }
                                this.e.c(s4);
                                return null;
                            }
                            if (s3.toUpperCase().equals(b("q3;+|"))) {
                                if (s4 == null) {
                                    this.f.a();
                                    return b("\u001f%-3/");
                                }
                                this.f.a.setText(s4);
                                this.f.a(s4);
                                return null;
                            }
                            else if (s3.toUpperCase().equals(b("q0-)n\u0019 #?k\u001a"))) {
                                if (s4 == null) {
                                    v.a(irc.R + b("q\u0010\r\tN9\u0000\u0003\u001fK:R\u0002"), bn.e, false);
                                    return null;
                                }
                                int int1;
                                try {
                                    int1 = Integer.parseInt(s4);
                                }
                                catch (NumberFormatException ex) {
                                    v.a(irc.R + b("q\u0010\r\tN9\u0000\u0003\u001fK:R\u0002"), bn.e, false);
                                    return null;
                                }
                                if (s != null) {
                                    this.d.a(s, int1);
                                }
                                if (s2 != null) {
                                    this.b.b(s2, int1);
                                }
                                return null;
                            }
                            else if (s3.toUpperCase().equals(b("q0-$h\u001f!'"))) {
                                if (s4 == null) {
                                    v.a(irc.R + bm.dV + irc.bN[irc.bM], bn.e, false);
                                    return null;
                                }
                                try {
                                    final int int2 = Integer.parseInt(s4);
                                    if (int2 >= 0 && int2 < irc.bN.length) {
                                        irc.bM = int2;
                                        v.a(irc.R + bm.dW + irc.bN[int2], bn.e, false);
                                    }
                                }
                                catch (NumberFormatException ex2) {}
                                return null;
                            }
                            else {
                                if (s3.toUpperCase().equals(b("q1 /d\f"))) {
                                    v.a();
                                    return null;
                                }
                                if (s3.toUpperCase().equals(b("q18)u"))) {
                                    final int index3 = s4.indexOf(32);
                                    if (index3 < 0) {
                                        return null;
                                    }
                                    String s6 = s4.substring(index3 + 1).toUpperCase();
                                    if (s6.equals(b("\u000e;\"-"))) {
                                        s6 = b("\u000e;\"-\u0005") + new Date().getTime() / 1000L;
                                    }
                                    return b("\u000e %<h\r5L") + s4.substring(0, index3) + b("~Hm") + s6 + b("_x");
                                }
                                else {
                                    if (s3.equalsIgnoreCase(b("q18)u\u00114*"))) {
                                        irc.O = false;
                                        return null;
                                    }
                                    if (s3.equalsIgnoreCase(b("q18)u\u0011<"))) {
                                        irc.O = true;
                                        return null;
                                    }
                                    if (s3.toUpperCase().equals(b("q4#$q"))) {
                                        if (s4 != null) {
                                            if (s != null) {
                                                this.d.a(s, s4.charAt(0));
                                                return null;
                                            }
                                            if (s2 != null) {
                                                this.b.a(s2, s4.charAt(0));
                                                return null;
                                            }
                                        }
                                        this.d.d.a(s4.charAt(0));
                                        return null;
                                    }
                                    if (s3.toUpperCase().equals(b("q4#$q\u00183!#i\u0007"))) {
                                        if (s4 != null) {
                                            if (s != null) {
                                                this.d.l(s, s4);
                                                return null;
                                            }
                                            if (s2 != null) {
                                                this.b.a(s2, s4);
                                                return null;
                                            }
                                        }
                                        this.d.d.b(s4);
                                        return null;
                                    }
                                    if (s3.toUpperCase().equals(b("q4#8`\u0019 #?k\u001a"))) {
                                        if (s4 == null) {
                                            v.a(irc.R + b("q\u0014\u0003\u0018@9\u0000\u0003\u001fK:R\u0002"), bn.e, false);
                                            return null;
                                        }
                                        int int3;
                                        try {
                                            int3 = Integer.parseInt(s4);
                                        }
                                        catch (NumberFormatException ex3) {
                                            v.a(irc.R + b("q\u0014\u0003\u0018@9\u0000\u0003\u001fK:R\u0002"), bn.e, false);
                                            return null;
                                        }
                                        if (s != null) {
                                            this.d.b(s, int3);
                                        }
                                        if (s2 != null) {
                                            this.b.a(s2, int3);
                                        }
                                        return null;
                                    }
                                    else {
                                        if (s3.equalsIgnoreCase(b("q:)&u")) && this.d.n != null) {
                                            this.d.m.showDocument(this.d.n, b("\u00143:\u0003W=:\t\u0006U"));
                                            return null;
                                        }
                                        if (s3.toUpperCase().equals(b("q;+$j\f7"))) {
                                            if (s4 == null) {
                                                this.g.b();
                                                return null;
                                            }
                                            this.g.a(s4);
                                            return null;
                                        }
                                        else {
                                            if (s3.toUpperCase().equals(b("q;\"<l\n7"))) {
                                                return b("\u0017<:#q\u001bR") + s4 + "\n";
                                            }
                                            if (s3.toUpperCase().equals(b("q8##k")) || s3.toUpperCase().equals(b("q1$+k\u00107 "))) {
                                                if (s4.length() > 1 && s4.charAt(1) == '#') {
                                                    s4 = s4.substring(1);
                                                }
                                                if (!irc.bB) {
                                                    v.a(irc.R + bm.bY, bn.e, false);
                                                    return null;
                                                }
                                                if (this.d.b() >= 0) {
                                                    return b("\u0014=%$\u0005") + s4 + "\n";
                                                }
                                                v.a(irc.R + bm.bZ, bn.e, false);
                                                return null;
                                            }
                                            else {
                                                if (s3.toUpperCase().equals(b("q9%)n"))) {
                                                    return b("\u0015;/!\u0005") + s4 + "\n";
                                                }
                                                if (!irc.bC && (s3.toUpperCase().equals(b("q>)+s\u001b")) || s3.toUpperCase().equals(b("q\"-8q")))) {
                                                    if (s4 == null) {
                                                        v.a(irc.R + bm.ca + "\n", bn.e, false);
                                                        return null;
                                                    }
                                                    return b("\u000e3>>\u0005") + s4 + "\n";
                                                }
                                                else if (s3.toUpperCase().equals(b("q>%9q")) && irc.n && irc.bB) {
                                                    if (irc.m < 0) {
                                                        return b("\u0012;?>/");
                                                    }
                                                    return b("\u0012;?>\u0005`") + irc.m + "\n";
                                                }
                                                else {
                                                    if (s3.toUpperCase().equals(b("q>99`\f!"))) {
                                                        return b("\u0012'?/w\rR") + ((s4 == null) ? "\n" : (s4 + "\n"));
                                                    }
                                                    if (s3.toUpperCase().equals(b("q>#-")) && (s != null || s2 != null)) {
                                                        if (s4 != null) {
                                                            if (!s4.toUpperCase().equals(b("\u0011<")) && !s4.toUpperCase().equals(b("\u00114*"))) {
                                                                v.a(irc.R + b("q\u001e\u0003\r\u0005\u0005\u001d\u0002JY~\u001d\n\fx"), bn.e, false);
                                                                return null;
                                                            }
                                                            if (irc.cf == null) {
                                                                v.a(irc.R + bm.dN, bn.e, false);
                                                                return null;
                                                            }
                                                        }
                                                        Label_2187: {
                                                            if (s != null) {
                                                                if (s4 == null) {
                                                                    if (this.d.c(s)) {
                                                                        v.a(irc.R + bm.dL, bn.e, false);
                                                                        if (!dx) {
                                                                            break Label_2187;
                                                                        }
                                                                    }
                                                                    v.a(irc.R + bm.dM, bn.e, false);
                                                                    if (!dx) {
                                                                        break Label_2187;
                                                                    }
                                                                }
                                                                if (s4.toUpperCase().equals(b("\u0011<"))) {
                                                                    if (irc.h) {
                                                                        this.d.c(s, true);
                                                                        if (!dx) {
                                                                            break Label_2187;
                                                                        }
                                                                    }
                                                                    v.a(irc.R + bm.dR, bn.e, false);
                                                                    if (!dx) {
                                                                        break Label_2187;
                                                                    }
                                                                }
                                                                this.d.c(s, false);
                                                            }
                                                        }
                                                        if (s2 != null) {
                                                            if (s4 == null) {
                                                                if (this.b.b(s2)) {
                                                                    v.a(irc.R + bm.dL, bn.e, false);
                                                                    if (!dx) {
                                                                        return null;
                                                                    }
                                                                }
                                                                v.a(irc.R + bm.dM, bn.e, false);
                                                                if (!dx) {
                                                                    return null;
                                                                }
                                                            }
                                                            if (s4.toUpperCase().equals(b("\u0011<"))) {
                                                                if (irc.h) {
                                                                    this.b.b(s2, true);
                                                                    if (!dx) {
                                                                        return null;
                                                                    }
                                                                }
                                                                v.a(irc.R + bm.dR, bn.e, false);
                                                                if (!dx) {
                                                                    return null;
                                                                }
                                                            }
                                                            this.b.b(s2, false);
                                                        }
                                                        return null;
                                                    }
                                                    if (s3.toUpperCase().equals(b("q?-:"))) {
                                                        return b("\u00133<J") + s4 + "\n";
                                                    }
                                                    if (s3.toUpperCase().equals(b("q?)"))) {
                                                        if (s2 != null) {
                                                            v.a(b("tR") + this.a.a() + " " + s4, bn.p, true);
                                                            return b("\u000e %<h\r5L") + s2 + b("~Hm+f\n;#$\u0005") + s4 + b("_x");
                                                        }
                                                        if (s != null) {
                                                            v.a(b("tR") + this.a.a() + " " + s4, bn.p, true);
                                                            return b("\u000e %<h\r5L") + s + b("~Hm+f\n;#$\u0005") + s4 + b("_x");
                                                        }
                                                        v.a(irc.R + bm.cb, bn.e, false);
                                                        return null;
                                                    }
                                                    else if (s3.toUpperCase().equals(b("q?#.`"))) {
                                                        if (s4 == null) {
                                                            v.a(irc.R + b("\u0013=(/\u0005dR") + bm.ca, bn.e, false);
                                                            return null;
                                                        }
                                                        return b("\u0013=(/\u0005") + s4 + "\n";
                                                    }
                                                    else {
                                                        if (s3.toUpperCase().equals(b("q?#>a"))) {
                                                            return b("\u0013=8.\u0005") + ((s4 == null) ? "\n" : (s4 + "\n"));
                                                        }
                                                        if (s3.toUpperCase().equals(b("q??-")) || s3.toUpperCase().equals(b("q<#>l\u001d7"))) {
                                                            if (s4 == null) {
                                                                return null;
                                                            }
                                                            final int index4 = s4.indexOf(32);
                                                            if (index4 < 0) {
                                                                return null;
                                                            }
                                                            if (s3.toUpperCase().equals(b("q??-"))) {
                                                                v.a(b("sLL@") + s4.substring(0, index4) + b("tR") + s4.substring(index4), bn.c, true);
                                                                return b("\u000e %<h\r5L") + s4.substring(0, index4) + b("~H") + s4.substring(index4).trim() + "\n";
                                                            }
                                                            v.a("-" + s4.substring(0, index4) + b("sR") + s4.substring(index4), bn.o, true);
                                                            return b("\u0010=8#f\u001bR") + s4.substring(0, index4) + b("~H") + s4.substring(index4).trim() + "\n";
                                                        }
                                                        else {
                                                            if (s3.toUpperCase().equals(b("q<-'`\r"))) {
                                                                return b("\u00103!/vT");
                                                            }
                                                            if (s3.toUpperCase().equals(b("q<)>h\u001b78#k\u0019")) && irc.bb) {
                                                                if (s4 != null) {
                                                                    if (s4.toUpperCase().equals(b("\u0011<"))) {
                                                                        irc.bc = true;
                                                                        return null;
                                                                    }
                                                                    if (s4.toUpperCase().equals(b("\u00114*"))) {
                                                                        irc.bc = false;
                                                                        return null;
                                                                    }
                                                                    v.a(irc.R + bm.cf, bn.e, false);
                                                                    if (!dx) {
                                                                        return null;
                                                                    }
                                                                }
                                                                v.a(irc.R + bm.ce, bn.e, false);
                                                                return null;
                                                            }
                                                            if (s3.toUpperCase().equals(b("q<%)n")) && irc.U) {
                                                                return b("\u0010;/!\u0005") + s4 + "\n";
                                                            }
                                                            if (s3.toUpperCase().equals(b("q<#>l\u0018+"))) {
                                                                if (s4 == null) {
                                                                    this.c.a();
                                                                    return null;
                                                                }
                                                                this.c.a(s4);
                                                                return this.c.b() + "\n";
                                                            }
                                                            else {
                                                                if (s3.equalsIgnoreCase(b("q<#>l\u001d7#,c"))) {
                                                                    irc.P = false;
                                                                    return null;
                                                                }
                                                                if (s3.equalsIgnoreCase(b("q<#>l\u001d7#$"))) {
                                                                    irc.P = true;
                                                                    return null;
                                                                }
                                                                if (s3.toUpperCase().equals(b("q=\"%q\u00171)"))) {
                                                                    if (s4 == null) {
                                                                        return null;
                                                                    }
                                                                    final int index5 = s4.indexOf(32);
                                                                    if (index5 >= 0) {
                                                                        return b("\t3 &f\u0016=<9\u0005") + s4.substring(0, index5) + b("~H") + s4.substring(index5 + 1) + "\n";
                                                                    }
                                                                    if (s == null) {
                                                                        return null;
                                                                    }
                                                                    return b("\t3 &f\u0016=<9\u0005") + s + b("~H") + s4 + "\n";
                                                                }
                                                                else {
                                                                    if (s3.toUpperCase().equals(b("q=</w"))) {
                                                                        return b("\u0011\")8\u0005") + s4 + "\n";
                                                                    }
                                                                    if (s3.toUpperCase().equals(b("q\"-9v"))) {
                                                                        return b("\u000e3?9\u0005") + s4 + "\n";
                                                                    }
                                                                    if (s3.toUpperCase().equals(b("q\" +|\r=9$a")) && !irc.e && s4 != null) {
                                                                        bw.c(s4);
                                                                        return b("\u000e %<h\r5L") + irc.cy.a() + b("~Hm9j\u000b<(J") + s4 + b("_x");
                                                                    }
                                                                    if (s3.toUpperCase().equals(b("q#9/w\u0007"))) {
                                                                        if (this.b.d() < 0) {
                                                                            v.a(irc.R + bm.cF + "\n", bn.e, false);
                                                                            if (!dx) {
                                                                                return null;
                                                                            }
                                                                        }
                                                                        this.b.c(s4, false);
                                                                        return null;
                                                                    }
                                                                    if (s3.equalsIgnoreCase(b("q#9/w\u0007=*,"))) {
                                                                        irc.Q = false;
                                                                        return null;
                                                                    }
                                                                    if (s3.equalsIgnoreCase(b("q#9/w\u0007=\""))) {
                                                                        irc.Q = true;
                                                                        return null;
                                                                    }
                                                                    if (s3.toUpperCase().equals(b("q#9#q"))) {
                                                                        if (s4 != null) {
                                                                            return b("\u000f'%>\u0005d") + s4 + "\n";
                                                                        }
                                                                        return b("\u000f'%>\u0005d") + this.h + "\n";
                                                                    }
                                                                    else if ((s3.toUpperCase().equals(b("q#9%q\u001b")) || s3.toUpperCase().equals(b("q -="))) && irc.q) {
                                                                        if (s4 != null && s4.length() > 3 && (!s4.toUpperCase().substring(0, 4).equals(b("\u0014=%$")) || irc.bB)) {
                                                                            v.a(irc.R + bm.cc + s4, bn.e, false);
                                                                            return s4 + "\n";
                                                                        }
                                                                        return null;
                                                                    }
                                                                    else if (s3.toUpperCase().equals(b("q!)>n\u001b+"))) {
                                                                        if (s4 == null) {
                                                                            return null;
                                                                        }
                                                                        final int index6 = s4.indexOf(32);
                                                                        String s7 = s4.trim().toUpperCase();
                                                                        String trim = null;
                                                                        if (index6 > 0) {
                                                                            s7 = s4.substring(0, index6).trim().toUpperCase();
                                                                            trim = s4.substring(index6).trim();
                                                                        }
                                                                        int n4 = 1;
                                                                        do {
                                                                            if (s7.equals("F" + n4)) {
                                                                                irc.bP[n4] = trim;
                                                                            }
                                                                        } while (++n4 < 13);
                                                                        return null;
                                                                    }
                                                                    else {
                                                                        if (s3.toUpperCase().equals(b("q!)>i\u00115(#w\u001b18%w\u0007"))) {
                                                                            if (s4 == null) {
                                                                                v.a(irc.R + b("q!\t\u001ei1\u0015(\u0003W;\u0011\u0018\u0005W'R\b\u0003W;\u0011\u0018\u0005W'"), bn.e, false);
                                                                                if (!dx) {
                                                                                    return null;
                                                                                }
                                                                            }
                                                                            irc.cf = s4;
                                                                            return null;
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q!)$a\u000b  ")) && irc.K && s4 != null) {
                                                                            if (s != null && this.d.isChannelOp(s, this.a.a())) {
                                                                                try {
                                                                                    this.d.m.showDocument(new URL(s4), b("\u00143:\u0003W=!\t\u0004A\u000b  "));
                                                                                }
                                                                                catch (MalformedURLException ex4) {
                                                                                    s4 = b("6\u0006\u0018\u001a\u001fq]") + s4;
                                                                                    try {
                                                                                        this.d.m.showDocument(new URL(s4), b("\u00143:\u0003W=!\t\u0004A\u000b  "));
                                                                                    }
                                                                                    catch (MalformedURLException ex5) {}
                                                                                }
                                                                                return b("\u000e %<h\r5L") + s + b("~Hm9`\u0010698i~") + s4 + b("_x");
                                                                            }
                                                                            if (s2 != null) {
                                                                                try {
                                                                                    this.d.m.showDocument(new URL(s4), b("\u00143:\u0003W=!\t\u0004A\u000b  "));
                                                                                }
                                                                                catch (MalformedURLException ex6) {
                                                                                    s4 = b("6\u0006\u0018\u001a\u001fq]") + s4;
                                                                                    try {
                                                                                        this.d.m.showDocument(new URL(s4), b("\u00143:\u0003W=!\t\u0004A\u000b  "));
                                                                                    }
                                                                                    catch (MalformedURLException ex7) {}
                                                                                }
                                                                                return b("\u000e %<h\r5L") + s2 + b("~Hm9`\u0010698i~") + s4 + b("_x");
                                                                            }
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q!$%r\u000b  "))) {
                                                                            if (s4 != null) {
                                                                                try {
                                                                                    this.d.m.showDocument(new URL(s4), b("\u00143:\u0003W=!$\u0005R\u000b  "));
                                                                                }
                                                                                catch (MalformedURLException ex8) {
                                                                                    try {
                                                                                        this.d.m.showDocument(new URL(b("6\u0006\u0018\u001a\u001fq]") + s4), b("\u00143:\u0003W=!$\u0005R\u000b  "));
                                                                                    }
                                                                                    catch (MalformedURLException ex9) {}
                                                                                }
                                                                            }
                                                                            return null;
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q!8+q\r"))) {
                                                                            return b("\r&->v~") + s4 + "\n";
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q!#?k\u001a"))) {
                                                                            if (s4 != null) {
                                                                                if (s4.toUpperCase().equals(b("\u0011<"))) {
                                                                                    irc.cg = true;
                                                                                    this.b.H.setState(true);
                                                                                    return null;
                                                                                }
                                                                                if (s4.toUpperCase().equals(b("\u00114*"))) {
                                                                                    irc.cg = false;
                                                                                    this.b.H.setState(false);
                                                                                    return null;
                                                                                }
                                                                                v.a(irc.R + bm.cf, bn.e, false);
                                                                                if (!dx) {
                                                                                    return null;
                                                                                }
                                                                            }
                                                                            v.a(irc.R + bm.ce, bn.e, false);
                                                                            return null;
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q!%&`\u00101)"))) {
                                                                            if (s4 != null && this.d != null) {
                                                                                if (s4.toUpperCase().equals(b("\u0011<"))) {
                                                                                    this.d.d.bo.setState(true);
                                                                                }
                                                                                if (s4.toUpperCase().equals(b("\u00114*"))) {
                                                                                    this.d.d.bo.setState(false);
                                                                                }
                                                                            }
                                                                            return null;
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q&%'`\r&-'u"))) {
                                                                            if (s4 != null) {
                                                                                if (s4.toUpperCase().equals(b("\u0011<"))) {
                                                                                    irc.bD = true;
                                                                                }
                                                                                if (s4.toUpperCase().equals(b("\u00114*"))) {
                                                                                    irc.bD = false;
                                                                                }
                                                                            }
                                                                            return null;
                                                                        }
                                                                        if (s3.toUpperCase().equals(b("q&#:l\u001d"))) {
                                                                            if (s4 == null || s4.equals("")) {
                                                                                return b("\n=<#fT");
                                                                            }
                                                                            final int index7 = s4.indexOf(" ");
                                                                            if (index7 < 0) {
                                                                                return b("\n=<#f~") + s4 + "\n";
                                                                            }
                                                                            return b("\n=<#f~") + s4.substring(0, index7) + b("~H") + s4.substring(index7 + 1).trim() + "\n";
                                                                        }
                                                                        else {
                                                                            if (s3.toUpperCase().equals(b("q%$%"))) {
                                                                                return b("\t:#J") + s4 + "\n";
                                                                            }
                                                                            if (s3.toUpperCase().equals(b("q%$%l\r"))) {
                                                                                return b("\t:##v~") + s4 + "\n";
                                                                            }
                                                                            v.a(irc.R + bm.cd, bn.e, false);
                                                                            if (dx) {
                                                                                break Label_5407;
                                                                            }
                                                                            return null;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                v.a(irc.R + bm.bX, bn.e, false);
                return null;
            }
        }
        if (s != null) {
            this.d.a(s, "<" + this.a.a() + b("`R") + a, bn.d, true);
            return b("\u000e %<h\r5L") + s + b("~H") + a + "\n";
        }
        if (s2 != null) {
            return b("\u000e %<h\r5L") + s2 + b("~H") + a + "\n";
        }
        v.a(irc.R + bm.cg, bn.e, false);
        return null;
    }
    
    String a(final String s) {
        final boolean dx = bm.dX;
        if (s == null || s.equals("")) {
            return s;
        }
        int n = 0;
        int n2 = 0;
        while (true) {
        Label_0056:
            while (true) {
                Label_0048: {
                    if (!dx) {
                        break Label_0048;
                    }
                    s.charAt(n);
                    if (n2 != 32 && s.charAt(n) != '\t') {
                        break Label_0056;
                    }
                    ++n;
                }
                if (n != s.length()) {
                    continue;
                }
                break;
            }
            n2 = n;
            if (dx) {
                continue;
            }
            break;
        }
        if (n2 != 0) {
            return s.substring(n);
        }
        return s;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '^';
                    break;
                }
                case 1: {
                    c2 = 'r';
                    break;
                }
                case 2: {
                    c2 = 'l';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = '%';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
