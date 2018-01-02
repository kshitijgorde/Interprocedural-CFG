import java.util.StringTokenizer;
import java.util.Date;
import java.io.Reader;
import java.io.InputStreamReader;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.applet.AppletContext;
import java.awt.CheckboxMenuItem;
import java.io.BufferedReader;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

class bw extends Thread
{
    final int a = 1024;
    char[] b;
    char[] c;
    Socket d;
    bf e;
    BufferedReader f;
    String[] g;
    bu h;
    bv i;
    bh j;
    bf k;
    br l;
    bq m;
    be n;
    u o;
    bk p;
    v q;
    CheckboxMenuItem r;
    CheckboxMenuItem s;
    CheckboxMenuItem t;
    AppletContext u;
    String v;
    String w;
    boolean x;
    boolean y;
    o z;
    j A;
    
    void a() {
        p.d = false;
        final String string = irc.R + bm.bU;
        Label_0086: {
            if (irc.f) {
                this.q.a(string, bn.e, false);
                if (!bm.dX) {
                    break Label_0086;
                }
            }
            if (!irc.e) {
                this.q.a(true);
                this.q.a(string, bn.e, false);
                this.q.a(false);
            }
        }
        if (this.n != null) {
            this.n.a(string, false);
        }
        if (this.l != null) {
            this.l.a(string, false);
        }
    }
    
    String a(String s) {
        final int index = s.indexOf(32);
        final String substring = s.substring(0, index);
        s = s.substring(index + 1);
        final String substring2 = s.substring(0, s.indexOf(32));
        s = s.substring(s.indexOf(58) + 1);
        return substring + bm.ct + substring2 + " " + s.substring(0, s.indexOf(32));
    }
    
    void b() {
        final boolean dx = bm.dX;
        int n = 0;
        int n2 = 0;
        final int c = this.c();
        int n3 = 0;
        int n4 = 0;
        while (true) {
            while (true) {
                Label_0134: {
                    if (!dx) {
                        break Label_0134;
                    }
                    final bw bw = this;
                    final char c2 = bw.b[n4];
                    Label_0131: {
                        if (c2 == ' ' && n < 3) {
                            this.g[n] = new String(this.c, 0, n2);
                            ++n;
                            n2 = 0;
                            if (!dx) {
                                break Label_0131;
                            }
                        }
                        if (c2 == ':' && n2 == 0) {
                            if (n != 3) {
                                break Label_0131;
                            }
                            if (n3 == 0) {
                                n3 = 1;
                                if (!dx) {
                                    break Label_0131;
                                }
                            }
                        }
                        n3 = 0;
                        if (n2 < 1024) {
                            this.c[n2++] = c2;
                            if (!dx) {
                                break Label_0131;
                            }
                        }
                        this.stop();
                    }
                    ++n4;
                }
                if (n4 < c) {
                    continue;
                }
                break;
            }
            final bw bw = this;
            if (!dx) {
                this.g[n] = new String(this.c, 0, (n2 < 1024) ? n2 : 1024);
                if (n != 3) {
                    this.g[3] = "";
                }
                return;
            }
            continue;
        }
    }
    
    String b(final String s) {
        final int index = s.indexOf(33);
        if (index < 0) {
            return s;
        }
        return s.substring(0, index).trim();
    }
    
    static void c(String substring) {
        final int index = substring.indexOf(32);
        if (index >= 0) {
            substring = substring.substring(index + 1);
        }
        try {
            final int int1 = Integer.parseInt(substring);
            if (irc.cg && irc.cv != null && int1 >= 0 && int1 < irc.cv.length) {
                irc.cv[int1].play();
            }
        }
        catch (NumberFormatException ex) {}
    }
    
    bw(final Socket d, final bf e, final bu h, final bv i, final bh j, final bf k, final br l, final bq m, final be n, final u o, final j a, final bk p20, final v q, final CheckboxMenuItem r, final String v, final String w, final CheckboxMenuItem s, final boolean y, final CheckboxMenuItem t, final AppletContext u) {
        this.b = new char[1024];
        this.c = new char[1024];
        this.g = new String[4];
        this.x = true;
        this.z = new o();
        this.d = d;
        this.e = e;
        this.h = h;
        this.i = i;
        this.k = k;
        this.j = j;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.A = a;
        this.p = p20;
        this.y = y;
        this.q = q;
        this.r = r;
        this.v = v;
        this.w = w;
        this.s = s;
        this.t = t;
        this.u = u;
        this.z.a(e);
        m.a(this);
        p20.a(this);
        this.setPriority(1);
    }
    
    String d(String s) {
        final int index = s.indexOf(32);
        final String substring = s.substring(0, index);
        s = s.substring(index + 1);
        final int index2 = s.indexOf(32);
        final String substring2 = s.substring(0, index2);
        s = s.substring(index2 + 1);
        final int index3 = s.indexOf(32);
        final String substring3 = s.substring(0, index3);
        s = s.substring(index3 + 1);
        s = s.substring(s.indexOf(32) + 1);
        final int index4 = s.indexOf(32);
        final String substring4 = s.substring(0, index4);
        s = s.substring(index4 + 1);
        final int index5 = s.indexOf(32);
        final String substring5 = s.substring(0, index5);
        s = s.substring(index5 + 2);
        final int index6 = s.indexOf(32);
        final String substring6 = s.substring(0, index6);
        final String substring7 = s.substring(index6 + 1);
        if (irc.cA) {
            this.n.a(substring, substring4, substring7);
        }
        return substring + " " + substring4 + " " + substring5 + " " + substring2 + "@" + substring3 + " " + substring6 + i("/E") + substring7 + ")";
    }
    
    void a(final String s, final String s2, final String s3) {
        final int index = s2.indexOf(32);
        String s4 = null;
        Label_0037: {
            if (index < 0) {
                s4 = s2.trim();
                if (!bm.dX) {
                    break Label_0037;
                }
            }
            s4 = s2.substring(0, index).trim();
        }
        if (s4.equals(this.h.a())) {
            this.n.b(s3, true);
            this.q.a(irc.R + bm.bB + s3 + bm.bF + s + i("/E") + s2.substring(index + 2) + ")", bn.g, false);
            return;
        }
        this.n.m(s3, s4);
        this.n.a(s3, irc.R + s4 + bm.bC + s3 + bm.bF + s + i("/E") + s2.substring(index + 2).trim() + ")", bn.g, false);
    }
    
    int c() {
        int n = 0;
        try {
        Block_3:
            while (true) {
                this.f.read(this.b, n, 1);
                while (this.b[n++] == '\n') {
                    if (!bm.dX) {
                        break Block_3;
                    }
                }
            }
        }
        catch (Exception ex) {
            if (p.d) {
                this.a();
            }
        }
        return n - 1;
    }
    
    void a(final String s, final Color color, final boolean b) {
        if (!irc.e) {
            final String a = irc.cy.a();
            final t h = this.n.h(a);
            if (h != null) {
                h.a(s, color, b);
                return;
            }
            final y d = this.l.d(a);
            if (d != null) {
                d.a(s, color, b);
                return;
            }
        }
        this.q.a(s, color, b);
    }
    
    String e(String s) {
        final int index = s.indexOf(32);
        final String substring = s.substring(0, index);
        s = s.substring(index + 1);
        final int index2 = s.indexOf(32);
        final String substring2 = s.substring(0, index2);
        s = s.substring(index2 + 1);
        final String substring3 = s.substring(0, s.indexOf(32));
        final String trim = s.substring(s.indexOf(58) + 1).trim();
        if (irc.ci) {
            try {
                String substring4 = substring2;
                if (substring2.charAt(0) == '~') {
                    substring4 = substring2.substring(1);
                }
                this.u.showDocument(new URL(irc.ch + substring4 + irc.ck), i("_\u001f{AMc\b"));
            }
            catch (MalformedURLException ex) {}
        }
        if (irc.cj) {
            try {
                this.u.showDocument(new URL(irc.ch + trim + irc.ck), i("_\u001f{AMc\b"));
            }
            catch (MalformedURLException ex2) {}
        }
        if (irc.cq) {
            return substring + bm.cs + trim;
        }
        return substring + bm.cs + substring2 + "@" + substring3 + i("/E") + trim + ")";
    }
    
    void b(final String s, final String s2, final String s3) {
        String trim = "";
        final int index = s2.indexOf(32);
        String s4 = null;
        Label_0048: {
            if (index <= 0) {
                s4 = s2;
                if (!bm.dX) {
                    break Label_0048;
                }
            }
            s4 = s2.substring(0, index);
            trim = s2.substring(index + 1).trim();
        }
        final int n = s4.length() - 1;
        if (s4.charAt(n) == '\u0001') {
            s4 = s4.substring(0, n);
        }
        if (s4.equals(i("N.@nkA"))) {
            if (index > 0) {
                if (s3.charAt(0) == '#') {
                    final t h = this.n.h(s3);
                    if (h != null && h.i()) {
                        h.a(i("%M") + s + s2.substring(index), bn.p, true);
                    }
                }
                else {
                    y y = this.l.d(s);
                    if (y == null || !y.e()) {
                        y = this.g(s);
                    }
                    if (y != null) {
                        y.a(i("%M") + s + s2.substring(index), bn.p, true);
                        return;
                    }
                    this.q.a(i("%M") + s + s2.substring(index), bn.p, true);
                }
            }
            return;
        }
        if (s4.equals(i("L\"PbfN>Q"))) {
            final Applet applet = this.u.getApplet(irc.b);
            if (applet != null) {
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015dkK(VfwJM") + applet.getCodeBase() + i("/\u00114") + applet.getDocumentBase() + i("\u000eM\u001e"));
                return;
            }
            this.k.a(i("A\"@ngJM") + s + i("/W\u0015dkK(VfwJMaIOa\u0002cI%/g"));
        }
        else {
            if (s4.equals(i("\\(Zcq]!")) && irc.K) {
                if (trim.equals("") || irc.e) {
                    return;
                }
                final t h2 = this.n.h(irc.cy.a());
                if (h2 == null || !h2.isChannelOp(s)) {
                    return;
                }
                try {
                    this.q.a(irc.R + s2 + bm.bF + s, bn.m, false);
                    this.u.showDocument(new URL(trim), i("E,BNVl>qI@Z?X"));
                    return;
                }
                catch (MalformedURLException ex2) {
                    return;
                }
            }
            if (s4.equals(i("A(@jaJ9]ic"))) {
                if (!irc.bb || !irc.bc || this.l.d(s) == null) {
                    this.k.a(i("_?]qi\\*4") + s + i("/W\u0015ia[ QbpF#SaeF!Qc%\u0005"));
                    return;
                }
                try {
                    this.u.showDocument(new URL(new URL(irc.Z), i("|\u0019uUPa\b`JAj\u0019}IC!\u0005`JH")), i("A\b`jAj\u0019}IC]\bwBMy\b"));
                }
                catch (MalformedURLException ex3) {}
                this.k.a(i("_?]qi\\*4") + s + i("/W\u0015ia[ QbpF#Sho\u000eg"));
            }
            if (s4.equals(i("A(@jaJ9]icI,]kaK"))) {
                this.a(irc.R + i("A\b`JAj\u0019}IC/\u000buNHj\t"), bn.e, false);
                return;
            }
            if (s4.equals(i("A(@jaJ9]ic@&"))) {
                try {
                    this.u.showDocument(new URL(new URL(irc.Z), i("a\b`JAj\u0019}IC!\u0005`JH0$D\u001a") + this.l.e(s)), i("A\b`jAj\u0019}IC\\\bzC"));
                    return;
                }
                catch (MalformedURLException ex) {
                    System.err.println(ex);
                    return;
                }
            }
            if (s4.equals(i("[$Yb"))) {
                this.q.a(irc.R + bm.ck + s + "]", bn.m, false);
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015smB(4") + this.h.c() + i("\u000eM\u001e"));
                return;
            }
            if (s4.equals(i("Z>QumA+["))) {
                this.q.a(irc.R + bm.cl + s + "]", bn.m, false);
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015rwJ?]ib@M") + this.h.h() + i("\u000eM\u001e"));
                return;
            }
            if (s4.equals(i("L!]bj[$Zak"))) {
                this.q.a(irc.R + bm.cm + s + "]", bn.m, false);
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015dhF(ZsmA+[\u0007") + this.h.g() + i("\u000eg"));
                return;
            }
            if (s4.equals(i("Y(Ftm@#"))) {
                this.q.a(irc.R + bm.cn + s + "]", bn.m, false);
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015qa]>]hj/") + this.h.f() + i("/\u0005`ST5B;PSxC~FRf\u001fw\tG`\u0000\u0015-"));
                return;
            }
            if (s4.equals(i("_$Z`"))) {
                this.q.a(irc.R + bm.co + s + "]", bn.m, false);
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015wmA*4\u0007%\u0005"));
                return;
            }
            if (s4.equals(i("I$Z`a]"))) {
                this.q.a(irc.R + bm.cp + s + "]", bn.m, false);
                this.k.a(i("A\"@ngJM") + s + i("/W\u0015amA*Qu\u0004") + this.h.i() + i("\u000eg"));
                return;
            }
            if (s4.equals(i("\\\"Ai`"))) {
                this.q.a(irc.R + s2 + bm.bF + s, bn.m, false);
                c(s2);
            }
        }
    }
    
    String f(final String s) {
        final int index = s.indexOf(33);
        if (index < 0) {
            return "";
        }
        return s.substring(index + 1).trim();
    }
    
    public void run() {
        final boolean dx = bm.dX;
        Label_0103: {
            try {
                if (irc.bm == null) {
                    this.f = new BufferedReader(new InputStreamReader(this.d.getInputStream()));
                    if (!dx) {
                        break Label_0103;
                    }
                }
                this.f = new BufferedReader(new InputStreamReader(this.d.getInputStream(), irc.bm));
            }
            catch (Exception ex) {
                System.out.println(i("h\b`nJ\u007f\u0018`tP}\buJ\u0004i\f}KAkW4") + ex);
                return;
            }
        }
        this.l = this.k.b();
        String b;
        String b2;
        String string;
        String b3;
        String b4;
        int index;
        char c;
        String substring;
        String s;
        int n;
        int index2;
        int index3;
        String s2 = null;
        String b5;
        String b6;
        t h;
        y y;
        String b7;
        String b8;
        y y2;
        t h2;
        String b9;
        String b10;
        t h3;
        int index4;
        int index5;
        int index6;
        String s3;
        String trim;
        y d;
        int b11;
        int index7;
        int index8;
        int index9;
        String substring2;
        t h4;
        int index10;
        String substring3;
        t h5;
        int index11;
        String substring4;
        int index12;
        String d2;
        String substring5;
        int index13;
        String substring6;
        String trim2;
        t h6;
        String substring7;
        int index14;
        t h7;
        String substring8;
        t h8;
        int index15;
        v q = null;
        String ba = null;
        Color e = null;
        boolean b12 = false;
        v q2;
        Color e2;
        StringTokenizer stringTokenizer;
        String s4;
        int index16;
        String substring9;
        y d3;
        int index17;
        t h9;
        String s5;
        int n2;
        char char1;
        String s6;
        String string2;
        Label_8031_Outer:Label_8500_Outer:
        while (p.d) {
            this.b();
            if (this.g[0].equalsIgnoreCase(i("J?Fhv")) || this.g[0].equals("")) {
                System.err.println(this.g[3]);
                this.a();
                return;
            }
            if (this.g[1].equals(i("N8@o")) && (this.g[2] != null & this.g[2].length() != 0)) {
                this.e.a(i("N8@o\u0004") + bg.a(null, this.g[2].trim()) + "\n");
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("F#BnpJ"))) {
                b = this.b(this.g[0]);
                if (this.b(b, this.f(this.g[0]))) {
                    continue;
                }
                this.a(irc.R + b + bm.bw + this.g[3], bn.f, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("E\"]i"))) {
                b2 = this.b(this.g[0]);
                Label_0509: {
                    if (b2.equals(this.h.a())) {
                        this.n.d(this.g[2].substring(0, this.g[2].length()).trim());
                        this.e.a(i("B\"Pb\u0004") + this.g[2] + "\n");
                        if (!dx) {
                            break Label_0509;
                        }
                    }
                    this.n.f(this.g[2], b2);
                }
                string = "";
                if (irc.bz) {
                    string = i("/E") + this.h(this.g[0]) + i("&M");
                }
                Label_0752: {
                    if (this.s.getState()) {
                        if (irc.cC != null && !irc.cC.equals("")) {
                            this.n.a(this.g[2].trim(), i("%M") + b2 + " " + irc.cC, bn.h, false);
                        }
                        this.q.a(irc.R + b2 + string + bm.bx + this.g[2], bn.n, false);
                        if (!dx) {
                            break Label_0752;
                        }
                    }
                    this.n.a(this.g[2], irc.R + b2 + string + bm.bx + this.g[2], bn.n, false);
                }
                if (!this.t.getState()) {
                    continue;
                }
                irc.ct.play();
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("D$Wl"))) {
                this.a(this.b(this.g[0]), this.g[3], this.g[2]);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("D$Xk"))) {
                b3 = this.b(this.g[0]);
                this.q.a(irc.R + this.g[2] + bm.bz + b3, bn.g, false);
                this.n.a(this.g[2], bm.bA + b3, this.q, this.s.getState());
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("B\"Pb"))) {
                if (this.g[2].charAt(0) == '#') {
                    b4 = this.b(this.g[0]);
                    if (b4.equals("")) {
                        b4 = this.g[0];
                    }
                    Label_1193: {
                        if (this.s.getState()) {
                            this.q.a(irc.R + bm.bD + "\"" + this.g[3].trim() + "\"" + bm.bE + this.g[2] + bm.bF + b4, bn.h, false);
                            if (!dx) {
                                break Label_1193;
                            }
                        }
                        this.n.a(this.g[2], irc.R + bm.bD + "\"" + this.g[3].trim() + "\"" + bm.bE + this.g[2] + bm.bF + b4, bn.h, false);
                    }
                    index = this.g[3].indexOf(" ");
                    if (index > 0) {
                        c = this.g[3].charAt(0);
                        substring = this.g[3].substring(1, index);
                        s = this.g[3].substring(index + 1);
                        n = 0;
                        while (true) {
                            Label_2656: {
                                if (!dx) {
                                    break Label_2656;
                                }
                                Label_2653: {
                                    if (substring.charAt(n) == '+' || substring.charAt(n) == '-') {
                                        c = substring.charAt(n);
                                        if (!dx) {
                                            break Label_2653;
                                        }
                                    }
                                    if (substring.charAt(n) == 'b') {
                                        index2 = s.indexOf(32);
                                        if (index2 >= 0) {
                                            s = s.substring(index2 + 1);
                                            if (!dx) {
                                                break Label_2653;
                                            }
                                        }
                                        s = "";
                                        if (!dx) {
                                            break Label_2653;
                                        }
                                    }
                                    if (substring.charAt(n) == 'o' || substring.charAt(n) == 'v' || substring.charAt(n) == 'h' || substring.charAt(n) == 'a' || substring.charAt(n) == 'q') {
                                        index3 = s.indexOf(" ");
                                        Label_1443: {
                                            if (index3 < 0) {
                                                s2 = s.trim();
                                                if (!dx) {
                                                    break Label_1443;
                                                }
                                            }
                                            s2 = s.substring(0, index3).trim();
                                            s = s.substring(index3 + 1);
                                        }
                                        if (c == '+') {
                                            if (!this.n.isChannelOp(this.g[2], s2)) {
                                                this.n.m(this.g[2], s2);
                                                Label_1838: {
                                                    switch (substring.charAt(n)) {
                                                        case 'o': {
                                                            this.n.f(this.g[2], "@" + s2);
                                                            if (!this.h.a().equals(s2)) {
                                                                break Label_2653;
                                                            }
                                                            this.n.a(this.g[2]);
                                                            if (dx) {
                                                                break Label_1838;
                                                            }
                                                            break Label_2653;
                                                        }
                                                        case 'v': {
                                                            Label_1697: {
                                                                if (!this.n.e(this.g[2], s2)) {
                                                                    this.n.f(this.g[2], "+" + s2);
                                                                    if (!dx) {
                                                                        break Label_1697;
                                                                    }
                                                                }
                                                                this.n.f(this.g[2], "%" + s2);
                                                            }
                                                            this.n.c(this.g[2], s2);
                                                            if (dx) {
                                                                break Label_1838;
                                                            }
                                                            break Label_2653;
                                                        }
                                                        case 'h': {
                                                            this.n.f(this.g[2], "%" + s2);
                                                            this.n.g(this.g[2], s2);
                                                            if (!this.h.a().equals(s2)) {
                                                                break Label_2653;
                                                            }
                                                            this.n.b(this.g[2]);
                                                            if (dx) {
                                                                break Label_1838;
                                                            }
                                                            break Label_2653;
                                                        }
                                                        case 'a': {
                                                            this.n.f(this.g[2], "&" + s2);
                                                            if (dx) {
                                                                break Label_1838;
                                                            }
                                                            break Label_2653;
                                                        }
                                                        case 'q': {
                                                            this.n.f(this.g[2], "~" + s2);
                                                            if (dx) {
                                                                break;
                                                            }
                                                            break Label_2653;
                                                        }
                                                    }
                                                }
                                            }
                                            if (substring.charAt(n) == 'v') {
                                                this.n.c(this.g[2], s2);
                                            }
                                            if (substring.charAt(n) != 'h') {
                                                break Label_2653;
                                            }
                                            this.n.g(this.g[2], s2);
                                            if (!dx) {
                                                break Label_2653;
                                            }
                                        }
                                        Label_2586: {
                                            switch (substring.charAt(n)) {
                                                case 'o': {
                                                    this.n.m(this.g[2], "@" + s2);
                                                    Label_2154: {
                                                        if (this.n.e(this.g[2], s2)) {
                                                            this.n.f(this.g[2], "%" + s2);
                                                            if (!dx) {
                                                                break Label_2154;
                                                            }
                                                        }
                                                        if (this.n.i(this.g[2], s2)) {
                                                            this.n.f(this.g[2], "+" + s2);
                                                            if (!dx) {
                                                                break Label_2154;
                                                            }
                                                        }
                                                        this.n.f(this.g[2], s2);
                                                    }
                                                    if (!this.h.a().equals(s2)) {
                                                        break;
                                                    }
                                                    this.n.e(this.g[2]);
                                                    if (!this.n.e(this.g[2], s2)) {
                                                        break;
                                                    }
                                                    this.n.b(this.g[2]);
                                                    if (dx) {
                                                        break Label_2586;
                                                    }
                                                    break;
                                                }
                                                case 'v': {
                                                    this.n.h(this.g[2], s2);
                                                    if (this.n.isChannelOp(this.g[2], s2) || this.n.e(this.g[2], s2)) {
                                                        break;
                                                    }
                                                    this.n.m(this.g[2], "+" + s2);
                                                    this.n.f(this.g[2], s2);
                                                    if (dx) {
                                                        break Label_2586;
                                                    }
                                                    break;
                                                }
                                                case 'h': {
                                                    this.n.d(this.g[2], s2);
                                                    Label_2463: {
                                                        if (!this.n.isChannelOp(this.g[2], s2)) {
                                                            this.n.m(this.g[2], "%" + s2);
                                                            if (this.n.i(this.g[2], s2)) {
                                                                this.n.f(this.g[2], "+" + s2);
                                                                if (!dx) {
                                                                    break Label_2463;
                                                                }
                                                            }
                                                            this.n.f(this.g[2], s2);
                                                        }
                                                    }
                                                    if (!this.h.a().equals(s2) || this.n.isChannelOp(this.g[2], s2)) {
                                                        break;
                                                    }
                                                    this.n.f(this.g[2]);
                                                    if (dx) {
                                                        break Label_2586;
                                                    }
                                                    break;
                                                }
                                                case 'a': {
                                                    if (this.n.isChannelOp(this.g[2], s2)) {
                                                        break;
                                                    }
                                                    this.n.m(this.g[2], "&" + s2);
                                                    this.n.f(this.g[2], s2);
                                                    if (dx) {
                                                        break Label_2586;
                                                    }
                                                    break;
                                                }
                                                case 'q': {
                                                    if (!this.n.isChannelOp(this.g[2], s2)) {
                                                        this.n.m(this.g[2], "~" + s2);
                                                        this.n.f(this.g[2], s2);
                                                        break;
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                ++n;
                            }
                            if (n != substring.length()) {
                                continue;
                            }
                            break;
                        }
                    }
                    this.e.a(i("B\"Pb\u0004") + this.g[2] + "\n");
                    if (!dx) {
                        continue;
                    }
                }
                if (!this.h.a().equals(this.g[2])) {
                    continue;
                }
                this.e.a(i("B\"Pb\u0004") + this.g[2] + "\n");
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("A$Wl"))) {
                b5 = this.b(this.g[0]);
                if (this.h.a().trim().equals(b5)) {
                    this.h.e(this.g[2]);
                    this.j.a(this.h);
                    this.q.a(irc.R + b5 + bm.bG + this.g[2], bn.j, false);
                }
                this.g[2] = this.g[2].trim();
                this.n.b(b5, this.g[2]);
                this.l.b(b5, this.g[2]);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("A\"@ngJ"))) {
                if (this.x) {
                    continue;
                }
                if (this.g[3].charAt(0) == '\u0001' && irc.O) {
                    this.a(this.b(this.g[0]), this.g[3].substring(1, this.g[3].length() - 1));
                    if (!dx) {
                        continue;
                    }
                }
                if (!irc.P) {
                    continue;
                }
                b6 = this.b(this.g[0]);
                if (this.b(b6, this.f(this.g[0]))) {
                    continue;
                }
                if (b6.equals("")) {
                    this.a(irc.R + this.g[3], bn.o, true);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[2].charAt(0) == '$') {
                    this.n.a(bm.bH + b6 + i("/@4") + this.g[3], true);
                    this.q.a(bm.bH + b6 + i("/@4") + this.g[3], bn.q, true);
                    if (!dx) {
                        continue;
                    }
                }
                h = this.n.h(this.g[2]);
                if (h != null && h.i()) {
                    h.a("-" + b6 + i("\"M") + this.g[3], bn.o, true);
                    if (!dx) {
                        continue;
                    }
                }
                y = this.l.d(b6);
                if ((y == null || !y.e()) && irc.bE) {
                    y = this.g(b6);
                }
                if (y != null) {
                    if (!y.F) {
                        this.e.a(i("Z>Qul@>@\u0007\u001e") + b6 + "\n");
                    }
                    y.a("-" + b6 + i("\"M") + this.g[3], bn.o, true);
                    if (!dx) {
                        continue;
                    }
                }
                this.a("-" + b6 + i("\"M") + this.g[3], bn.o, true);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("_,Fs"))) {
                b7 = this.b(this.g[0]);
                if (this.h.a().equals(b7)) {
                    this.n.b(this.g[2].trim(), false);
                    if (!dx) {
                        continue;
                    }
                }
                this.g[2] = this.g[2].trim();
                Label_3754: {
                    if (this.s.getState()) {
                        if (irc.cD != null && !irc.cD.equals("")) {
                            this.n.a(this.g[2], i("%M") + b7 + " " + irc.cD, bn.h, false);
                        }
                        this.q.a(irc.R + b7 + bm.by + this.g[2], bn.h, false);
                        if (!dx) {
                            break Label_3754;
                        }
                    }
                    this.n.a(this.g[2], irc.R + b7 + bm.by + this.g[2], bn.h, false);
                }
                this.n.m(this.g[2], b7);
                if (!this.t.getState()) {
                    continue;
                }
                irc.cu.play();
                if (!dx) {
                    continue;
                }
            }
            if (this.g[0].equals(i("_$Z`"))) {
                this.e.a(i("_\"Z`\u0004") + this.g[1] + "\n");
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("_?]qi\\*"))) {
                b8 = this.b(this.g[0]);
                if (this.b(b8, this.f(this.g[0]))) {
                    continue;
                }
                if (this.g[3].charAt(0) == '\u0001') {
                    if (!irc.O) {
                        continue;
                    }
                    this.b(b8, this.g[3].substring(1, this.g[3].length() - 2), this.g[2]);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[2].equals(this.h.a())) {
                    if (!irc.Q) {
                        continue;
                    }
                    y2 = this.l.d(b8);
                    if (y2 == null || !y2.e()) {
                        y2 = this.g(b8);
                    }
                    if (y2 != null) {
                        if (!y2.F) {
                            this.e.a(i("Z>Qul@>@\u0007\u001e") + b8 + "\n");
                        }
                        y2.a("<" + b8 + i("1M") + this.g[3], bn.c, true);
                        if (!dx) {
                            continue;
                        }
                    }
                    this.q.a("*" + b8 + i("%M") + this.g[3], bn.c, true);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[2].charAt(0) == '$') {
                    this.n.a(bm.bI + b8 + i("1M") + this.g[3], true);
                    this.q.a(bm.bI + b8 + i("1M") + this.g[3], bn.q, true);
                    if (!dx) {
                        continue;
                    }
                }
                h2 = this.n.h(this.g[2]);
                if (h2 == null || !h2.i()) {
                    this.q.a("<" + b8 + i("1M") + this.g[3], bn.d, true);
                    if (!dx) {
                        continue;
                    }
                }
                h2.a("<" + b8 + i("1M") + this.g[3], bn.d, true);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("^8]s"))) {
                b9 = this.b(this.g[0]);
                this.g[2] = this.g[2].trim();
                Label_4554: {
                    if (this.g[2].equals(i("C\buQMa\n"))) {
                        this.n.a(b9, this.g[2], this.q, this.s.getState());
                        if (!dx) {
                            break Label_4554;
                        }
                    }
                    this.n.a(b9, this.g[2] + " " + this.g[3], this.q, this.s.getState());
                }
                if (!this.t.getState()) {
                    continue;
                }
                irc.cu.play();
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("[\"Dng"))) {
                b10 = this.b(this.g[0]);
                h3 = this.n.h(this.g[2]);
                if (h3 == null || !h3.i()) {
                    this.q.a(irc.R + b10 + bm.bJ + this.g[2] + i("/\u0019{\u0007") + this.g[3], bn.l, false);
                    if (!dx) {
                        continue;
                    }
                }
                h3.a(irc.R + b10 + bm.bJ + this.g[2] + i("/\u0019{\u0007") + this.g[3], bn.l, false);
                h3.f(this.g[3]);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("X,Xkk_>"))) {
                this.a(irc.R + this.g[0] + " " + this.g[1] + " " + this.g[2] + " " + this.g[3], bn.i, true);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("?]&"))) {
                this.h.e(this.g[2]);
                this.j.a(this.h);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("=_%"))) {
                this.h.a(this.g[3].trim());
                this.j.a(this.h);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("=\\-")) || this.g[1].equals(i("=Y-"))) {
                index4 = this.g[3].indexOf(58);
                if (index4 < 0) {
                    continue;
                }
                this.a(irc.R + this.g[3].substring(index4 + 1), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("=Y&")) || this.g[1].equals(i("=X$"))) {
                if (this.x) {
                    continue;
                }
                this.g[3] = this.g[3].trim();
                if (this.g[3].charAt(0) == ':') {
                    this.g[3] = this.g[3].substring(1);
                }
                this.a(irc.R + this.g[3] + bm.bK + this.g[0], bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("=X'")) && this.x) {
                continue;
            }
            if (this.g[1].equals(i("=X%")) || this.g[1].equals(i("=X ")) || this.g[1].equals(i("=X!"))) {
                if (this.x) {
                    continue;
                }
                this.a(irc.R + this.g[3], bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.x && this.g[1].equals(i("=X&"))) {
                continue;
            }
            if (this.g[1].equals(i("<]%"))) {
                if (irc.ci || irc.cj) {
                    continue;
                }
                index5 = this.g[3].indexOf(58);
                if (index5 <= 0) {
                    continue;
                }
                this.a(irc.R + this.g[3].substring(0, index5) + bm.bL + this.g[3].substring(index5 + 1), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<]&"))) {
                index6 = this.g[3].indexOf(61);
                if (index6 < 0) {
                    continue;
                }
                s3 = this.g[3].substring(0, index6);
                if (s3.charAt(s3.length() - 1) == '*') {
                    s3 = s3.substring(0, s3.length() - 1);
                }
                trim = this.g[3].substring(index6 + 2).trim();
                d = this.l.d(s3);
                if (d != null) {
                    d.a(trim);
                }
                b11 = this.p.b(s3);
                if (b11 >= 0) {
                    this.p.a(b11, trim);
                }
                this.n.a(s3, trim);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<]'"))) {
                this.m.e(this.g[3]);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<]!")) || this.g[1].equals(i("<]\""))) {
                continue;
            }
            if (this.g[1].equals(i("<]#"))) {
                index7 = this.g[3].indexOf(58);
                if (index7 <= 0) {
                    continue;
                }
                this.a(irc.R + this.g[3].substring(0, index7) + bm.ch, bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<\\%"))) {
                if (irc.ci || irc.cj) {
                    this.e(this.g[3]);
                    if (!dx) {
                        continue;
                    }
                }
                this.a(irc.R + this.e(this.g[3]), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<\\&"))) {
                if (irc.ci || irc.cj || irc.cq) {
                    continue;
                }
                this.a(irc.R + bm.bM + this.g[3].substring(this.g[3].indexOf(32)), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<\\!"))) {
                if (irc.cq || irc.cA) {
                    continue;
                }
                this.a(irc.R + this.g[3].substring(this.g[3].indexOf(58) + 1), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<\\#"))) {
                if (irc.ci || irc.cj || irc.cq) {
                    continue;
                }
                this.a(irc.R + this.a(this.g[3]), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<\\,"))) {
                if (irc.ci || irc.cj) {
                    irc.ci = false;
                    irc.cj = false;
                    if (!dx) {
                        continue;
                    }
                }
                this.a(irc.R + bm.dG, bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<\\-"))) {
                if (irc.ci || irc.cj || irc.cq) {
                    continue;
                }
                this.a(irc.R + bm.bN + this.g[3].substring(this.g[3].indexOf(58) + 1), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<_%"))) {
                this.o.b();
                this.o.d();
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<_&"))) {
                this.o.b(this.g[3].trim());
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<_'"))) {
                this.o.e();
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<_ "))) {
                index8 = this.g[3].indexOf(" ");
                this.n.k(this.g[3].substring(0, index8), this.g[3].substring(index8 + 1).replace(':', ' ').trim());
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<_,"))) {
                index9 = this.g[3].indexOf(58);
                if (index9 < 0) {
                    continue;
                }
                substring2 = this.g[3].substring(0, index9 - 1);
                if ((h4 = this.n.h(substring2)) != null) {
                    h4.a(irc.R + bm.bO + substring2 + i("5M") + this.g[3].substring(index9 + 1), bn.e, false);
                }
            }
            if (this.g[1].equals(i("<_-"))) {
                continue;
            }
            if (this.g[1].equals(i("<^&"))) {
                index10 = this.g[3].indexOf(":");
                substring3 = this.g[3].substring(0, index10 - 1);
                this.n.j(substring3, this.g[3].substring(index10 + 1));
                if ((h5 = this.n.h(substring3)) != null) {
                    h5.a(irc.R + bm.bP + this.g[3], bn.k, false);
                }
                this.q.a(irc.R + bm.bP + this.g[3], bn.k, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<^'"))) {
                index11 = this.g[3].indexOf(32);
                if (index11 < 0) {
                    continue;
                }
                substring4 = this.g[3].substring(0, index11);
                this.g[3] = this.g[3].substring(index11 + 1);
                index12 = this.g[3].indexOf(32);
                if (index12 < 0) {
                    continue;
                }
                this.q.a(irc.R + substring4 + bm.bQ + this.g[3].substring(0, index12) + bm.bR + new Date(1000L * Long.parseLong(this.g[3].substring(index12 + 1, this.g[3].length() - 1).trim())).toLocaleString(), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<X&"))) {
                d2 = this.d(this.g[3]);
                if (irc.cA) {
                    continue;
                }
                this.a(irc.R + d2, bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<X'"))) {
                if (this.g[3].charAt(0) == '=' || this.g[3].charAt(0) == '@' || this.g[3].charAt(0) == '*') {
                    substring5 = this.g[3].substring(2);
                    index13 = substring5.indexOf(" ");
                    substring6 = substring5.substring(0, index13);
                    trim2 = substring5.substring(index13 + 2).trim();
                    h6 = this.n.h(substring6);
                    if (h6 == null) {
                        continue;
                    }
                    h6.k(trim2);
                    if (!dx) {
                        continue;
                    }
                }
                this.q.a(i("_\u001fb\u0007\u001e/") + this.g[3].substring(2), bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<[\""))) {
                substring7 = this.g[3].substring(0, this.g[3].indexOf(":"));
                if (!this.n.isChannelOp(substring7, this.h.a())) {
                    continue;
                }
                this.n.a(substring7);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<[#"))) {
                index14 = this.g[3].indexOf(" ");
                h7 = this.n.h(this.g[3].substring(0, index14 + 1));
                if (h7 != null && h7.l()) {
                    if (this.z.e()) {
                        this.z.c();
                    }
                    this.z.b.add(this.g[3].substring(index14 + 1).trim());
                    if (!dx) {
                        continue;
                    }
                }
            }
            if (this.g[1].equals(i("<[,"))) {
                substring8 = this.g[3].substring(0, this.g[3].indexOf(" ") + 1);
                h8 = this.n.h(substring8);
                if (h8 != null && h8.l()) {
                    if (this.z.e()) {
                        this.z.c();
                    }
                    this.z.c(substring8);
                    this.z.a(h8.B);
                    this.z.d(h8.A);
                    this.z.a(h8.isChannelOp() || h8.h());
                    this.z.d();
                    this.z.b(true);
                    this.z.show(true);
                    h8.b(false);
                    if (!dx) {
                        continue;
                    }
                }
            }
            if (this.g[1].equals(i("<Z&")) || this.g[1].equals(i("<Z!"))) {
                if (this.x) {
                    continue;
                }
                this.a(irc.R + this.g[3], bn.e, false);
                if (!dx) {
                    continue;
                }
            }
            if (this.g[1].equals(i("<Z\"")) || this.g[1].equals(i(";_&"))) {
                Label_7791: {
                    if (this.g[1].equals(i("<Z\""))) {
                        this.a(irc.R + i("J\u0003p\u0007KiM;jk[)4DKb\u0000uI@"), bn.e, false);
                        if (!dx) {
                            break Label_7791;
                        }
                    }
                    this.a(irc.R + bm.bS, bn.e, false);
                }
                if (!this.x) {
                    continue Label_8031_Outer;
                }
                this.x = false;
                if (irc.ba != null) {
                    while (true) {
                        if ((index15 = irc.ba.indexOf(94)) < 0) {
                            q = this.q;
                            ba = irc.ba;
                            e = bn.e;
                            b12 = true;
                            if (!dx) {
                                break;
                            }
                        }
                        else {
                            q2 = this.q;
                            irc.ba.substring(0, index15);
                            e2 = bn.e;
                        }
                        q.a(ba, e, b12);
                        irc.ba = irc.ba.substring(index15 + 1);
                    }
                    q.a(ba, e, b12);
                }
                this.q.a(irc.R + bm.o + this.h.a(), bn.e, false);
                if (this.y) {
                    this.e.a(i("B\"Pb\u0004") + this.h.a() + i("/F}-"));
                }
                if (this.v == null || this.v.trim().length() == 0 || this.v == null || this.v.trim().length() == 0) {
                    continue Label_8031_Outer;
                }
                stringTokenizer = new StringTokenizer(this.v, ";");
                while (true) {
                    Label_8114: {
                        if (!dx) {
                            break Label_8114;
                        }
                        s4 = stringTokenizer.nextToken();
                        if (s4.toUpperCase().startsWith(i("E\"]i")) && this.w != null) {
                            s4 = s4 + " " + this.w;
                        }
                        this.e.a(s4 + "\n");
                    }
                    if (!stringTokenizer.hasMoreTokens()) {
                        continue Label_8031_Outer;
                    }
                    continue;
                }
            }
            else {
                if (this.g[1].equals(i(";]%"))) {
                    index16 = this.g[3].indexOf(58);
                    substring9 = null;
                    if (index16 > 0) {
                        substring9 = this.g[3].substring(0, index16 - 1);
                    }
                    d3 = this.l.d(substring9);
                    if (d3 == null) {
                        continue;
                    }
                    d3.a(irc.R + substring9 + bm.bT, bn.e, false);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[1].equals(i(";] "))) {
                    index17 = this.g[3].indexOf(":");
                    if (index17 > 0) {
                        h9 = this.n.h(this.g[3].substring(0, index17 - 1));
                        if (h9 != null) {
                            h9.a(irc.R + bm.dF, bn.e, false);
                            if (!dx) {
                                continue;
                            }
                        }
                        this.q.a(irc.R + this.g[3], bn.e, false);
                        if (!dx) {
                            continue;
                        }
                    }
                }
                if (this.g[1].equals(i(";]-")) || this.g[1].equals(i(";\\&"))) {
                    continue;
                }
                if (this.g[1].equals(i(";^&"))) {
                    this.g[3] = this.g[3].trim();
                    s5 = this.g[3].substring(0, this.g[3].indexOf(32));
                    if (Character.isDigit(s5.charAt(0))) {
                        s5 = "_" + s5.substring(1);
                    }
                    n2 = 0;
                    while (true) {
                        Label_8525: {
                            if (!dx) {
                                break Label_8525;
                            }
                            char1 = s5.charAt(n2);
                            if (!Character.isJavaLetterOrDigit(char1)) {
                                s5 = s5.replace(char1, '_');
                            }
                            ++n2;
                        }
                        if (n2 != s5.length()) {
                            continue;
                        }
                        break;
                    }
                    this.e.a(i("A$Wl\u0004") + s5 + "\n");
                    if (!dx) {
                        continue Label_8500_Outer;
                    }
                }
                if (this.g[1].equals(i(";^'"))) {
                    if (!irc.V) {
                        this.A.a(bm.cT);
                        this.A.show();
                        if (!dx) {
                            continue;
                        }
                    }
                    this.g[3] = this.g[3].trim();
                    s6 = this.g[3].substring(0, this.g[3].indexOf(32));
                    this.a(irc.R + bm.bu, bn.e, false);
                    if (s6.length() > irc.cp - 2) {
                        s6 = s6.substring(0, irc.cp - 2);
                    }
                    string2 = s6 + "_" + p.c(1);
                    this.e.a(i("A$Wl\u0004") + string2 + "\n");
                    this.a(irc.R + bm.bv + string2, bn.e, false);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[1].equals(i(";X%"))) {
                    System.out.println(this.g[3]);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[1].equals(i(";Z'"))) {
                    this.a(irc.R + this.g[3], bn.e, false);
                    if (!dx) {
                        continue;
                    }
                }
                if (this.g[1].equals(i(";Z "))) {
                    this.a(irc.R + bm.bV, bn.e, false);
                    if (!dx) {
                        continue;
                    }
                }
                this.a(irc.R + this.g[3], bn.e, false);
                if (dx) {
                    break;
                }
                continue;
            }
        }
    }
    
    y g(final String s) {
        if (this.l.d() != -1 && !this.r.getState()) {
            this.l.c(s, true);
            return this.l.d(s);
        }
        return null;
    }
    
    String h(final String s) {
        final int index = s.indexOf(33);
        if (index < 0) {
            return new String("");
        }
        return new String(s.substring(index + 1));
    }
    
    void a(final String s, final String s2) {
        final int index = s2.indexOf(32);
        if (index <= 0) {
            return;
        }
        final String substring = s2.substring(0, index);
        if (substring.equals(i("Z>QumA+[")) || substring.equals(i("L!]bj[$Zak")) || substring.equals(i("[$Yb")) || substring.equals(i("Y(Ftm@#")) || substring.equals(i("I$Z`a]")) || substring.equals(i("L\"PbfN>Q"))) {
            this.a(irc.R + i("L9Ww\u0004") + substring + bm.cq + s + i("/W4") + s2.substring(index + 1, s2.length() - 1).trim(), bn.m, false);
            return;
        }
        if (substring.equals(i("_$Z`"))) {
            final int index2 = s2.indexOf(32);
            String string = "0";
            if (index2 >= 0) {
                final String trim = s2.substring(index2 + 1).trim();
                try {
                    string = Long.toString(new Date().getTime() / 1000L - Long.parseLong(trim));
                }
                catch (NumberFormatException ex) {
                    string = trim;
                }
            }
            this.a(irc.R + bm.cr + s + i("/W4") + string, bn.m, false);
        }
    }
    
    boolean b(final String s, final String s2) {
        final int b = this.p.b(s);
        if (b < 0) {
            return false;
        }
        if (s2.equals(this.p.d[b])) {
            return true;
        }
        this.p.d(s);
        return false;
    }
    
    private static String i(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u000f';
                    break;
                }
                case 1: {
                    c2 = 'm';
                    break;
                }
                case 2: {
                    c2 = '\u0014';
                    break;
                }
                case 3: {
                    c2 = '\'';
                    break;
                }
                default: {
                    c2 = '$';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
