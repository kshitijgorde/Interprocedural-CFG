// 
// Decompiled by Procyon v0.5.30
// 

package wordfall;

import java.util.Vector;
import sexy.gui.SexyApplet;

public class WordFallApplet extends SexyApplet implements b
{
    public static final int[] a;
    public static final double[] b;
    public static final int[][] c;
    public static final int[][] d;
    public p e;
    public r f;
    public s g;
    public t h;
    public v i;
    public boolean j;
    public w k;
    public z l;
    public ai m;
    public boolean n;
    public ar o;
    public int p;
    public int q;
    public Vector r;
    public c s;
    public aa t;
    public aa u;
    public aa v;
    public aa w;
    public aa x;
    public al y;
    public as z;
    public Vector aa;
    public Vector ab;
    public int ac;
    public boolean ad;
    public boolean ae;
    public boolean af;
    public boolean ag;
    public boolean ah;
    public boolean ai;
    public boolean aj;
    public boolean ak;
    public boolean al;
    public boolean am;
    public boolean an;
    public boolean ao;
    public boolean ap;
    public boolean aq;
    public boolean ar;
    public boolean as;
    public boolean at;
    public int au;
    public int av;
    public boolean aw;
    public ab ax;
    public boolean ay;
    public boolean az;
    public boolean a0;
    public boolean a1;
    public String[] a2;
    public String[] a3;
    public String[] a4;
    public String[] a5;
    
    public void a() {
        final int max = Math.max(0, WordFallApplet.a[Math.min(this.k.aj, WordFallApplet.a.length - 1)] - 3);
        for (int i = this.f.g[max].size(); i > 0; --i) {
            final int q = this.k.p.a() % this.f.g[max].size();
            if (!this.r.contains(this.f.g[max].elementAt(q))) {
                this.q = q;
                this.p = max;
                return;
            }
        }
    }
    
    public void b() {
        if (this.m != null) {
            super.h.f(this.m);
            this.m = null;
        }
        if (this.k != null) {
            super.h.f(this.k);
            this.k = null;
        }
        if (this.l != null) {
            super.h.f(this.l);
            this.l = null;
        }
        (this.k = new w(this)).a(0, 0, super.i, super.j);
        super.h.c(this.k);
        super.h.e(this.k);
        super.h.h(this.k);
        super.h.e(this.k);
        this.k.a(0, 0, super.i, super.j);
        this.k.b(true);
        this.k.c(false);
        (this.l = new z(this)).a(0, 0, 0, 0);
        super.h.c(this.l);
        this.k.q();
        if (this.a0) {
            if (Math.random() < 0.75) {
                this.n();
            }
            this.a0 = false;
        }
    }
    
    public void c() {
        if (this.z != null) {
            super.h.f(this.z);
            this.z = null;
        }
        if (!this.f.o.j() || !this.f.q.j() || !this.f.p.j() || !this.f.s.j()) {
            this.a("New Game?", "Your game will be aborted.\n\nAre you sure you want\nto start a new game?", null, 1);
            this.av = 1;
            return;
        }
        final j o = this.f.o;
        (this.z = new as(this, "\nYour current game will be aborted.\n\nAre you sure you want to start a new game?")).a(145, 36, o.h(), o.c());
        super.h.c(this.z);
        this.k.c(true);
        this.z.b(true);
        this.z.c(false);
    }
    
    public void a(final String s, final String s2, final String s3, final int n) {
        this.e();
        (this.y = new an(super.h, this, s, s2, s3, n)).a(WordFallApplet.c);
        this.y.b(WordFallApplet.d);
        this.y.k = 8;
        this.y.a(146, 60, 250, this.y.a(250));
        super.h.c(this.y);
        super.h.a(this.y);
        if (this.k != null) {
            this.k.e(true);
        }
    }
    
    public void d() {
        super.s = true;
        this.e = new p();
        super.e = "BookWorm";
        super.a = 139;
        super.c = "Wed Jul 16 12:56:14 2003";
        if (!this.ab()) {
            return;
        }
        System.out.println("Build #" + 139 + " (" + "Wed Jul 16 12:56:14 2003" + ")");
        System.out.println("Initialized at: " + Integer.toString((int)System.currentTimeMillis()));
        super.l = 10;
        this.e(0);
        this.az = this.a("ShowAds", true);
        this.ay = this.a("ScoreUpload", false);
        this.a1 = this.a("LogUser", false);
        final String parameter = this.getParameter("resbase");
        this.f = new r(this);
        if (parameter != null) {
            this.f.o = parameter;
        }
        super.d = this.f;
        this.g = new s();
        super.d();
        this.a2 = new String[5];
        this.a3 = new String[2];
        this.a5 = new String[2];
        this.a4 = new String[2];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        do {
            this.a2[n3] = super.h.b.getParameter("ScoreName" + n3);
            if (this.a2[n3] != null) {
                ++n;
            }
        } while (++n3 < 5);
        int n4 = 0;
        do {
            this.a3[n4] = super.h.b.getParameter("ScoreWord" + n4);
            if (this.a3[n4] != null) {
                ++n2;
                final int lastIndex = this.a3[n4].lastIndexOf(32);
                final int index = this.a3[n4].indexOf(40);
                final int lastIndex2 = this.a3[n4].lastIndexOf(41);
                if (lastIndex == -1 || index == -1 || lastIndex2 == -1) {
                    continue;
                }
                this.a4[n4] = this.a3[n4].substring(0, lastIndex);
                this.a5[n4] = this.a3[n4].substring(index + 1, lastIndex2);
            }
        } while (++n4 < 2);
        if (n > 0 || n2 > 0) {
            (this.h = new t(this, super.e)).l();
            super.h.c(this.h);
        }
        else {
            (this.i = new v(super.h, super.e)).l();
            super.h.c(this.i);
        }
        this.k();
        this.aa = new Vector();
        this.ab = new Vector();
    }
    
    public void e() {
        if (this.y != null) {
            super.h.f(this.y);
            this.y = null;
            if (this.k != null) {
                this.k.e(false);
            }
        }
    }
    
    public void f() {
        if (this.m != null) {
            super.h.f(this.m);
            this.m = null;
        }
        super.h.h(this.k);
        super.h.e(this.k);
        this.k.b(true);
        this.k.c(false);
        if (this.k.aj >= 2 && this.k.v()) {
            this.k.f(true);
        }
        if (this.k.aj >= 3) {
            if (!this.ak && !this.ap) {
                this.k.ar = 100;
                this.k.as = 5;
                this.ak = true;
            }
            else if (!this.ao && !this.at) {
                this.k.ar = 100;
                this.k.as = 10;
                this.ao = true;
            }
            else if (!this.am && !this.ar) {
                this.k.ar = 100;
                this.k.as = 6;
                this.am = true;
            }
            else if (!this.al && !this.aq) {
                this.k.ar = 100;
                this.k.as = 7;
                this.al = true;
            }
            else if (!this.an && !this.as) {
                this.k.ar = 100;
                this.k.as = 8;
                this.an = true;
            }
        }
        this.f.a6.a((10 + this.k.aj * 5) % 100 / 100.0);
    }
    
    public synchronized void g() {
        if (!super.k) {
            super.g();
            this.d("SessionStats");
            if (this.k != null && this.k.z != 5 && this.k.z != 3 && this.k.ak > 0) {
                this.f("Abandoned");
            }
            if (this.aa != null) {
                String string = new String("");
                for (int i = 0; i < this.aa.size(); ++i) {
                    string = string + this.aa.elementAt(i) + ((i < this.aa.size() - 1) ? "," : "");
                }
                if (string.length() > 0) {
                    this.b("WordList", string);
                }
            }
            if (this.ab != null) {
                String string2 = new String("");
                for (int j = 0; j < this.ab.size(); ++j) {
                    string2 = string2 + this.ab.elementAt(j) + ((j < this.ab.size() - 1) ? "," : "");
                }
                if (string2.length() > 0) {
                    this.b("FailList", string2);
                }
            }
            this.s();
        }
    }
    
    public void h() {
        if (this.o != null) {
            super.h.f(this.o);
            this.o = null;
            if (this.k != null) {
                this.k.e(false);
            }
        }
    }
    
    public void i() {
        final ac ac = (this.k.z == 5) ? this.k.q : this.k.r;
        this.k.m();
        final int aj = this.k.aj;
        if (this.m == null) {
            this.m = new ai(this, ac);
            super.h.c(this.m);
            super.h.e(this.m);
            final int n = 130;
            int n2 = 36;
            if (this.k.z == 5) {
                n2 -= 20;
            }
            if (this.f.t.j()) {
                this.m.a(n, n2, this.f.t.h(), this.m.k());
            }
            else {
                this.m.a(n, n2, this.m.l(), this.m.k());
            }
            this.m.c(aj);
            return;
        }
        this.m.c(aj);
        this.m.b(true);
        this.m.c(false);
        super.h.e(this.m);
        super.h.d(this.m);
    }
    
    public WordFallApplet() {
        this.n = true;
        this.r = new Vector();
        this.s = new c(this);
        this.ac = 1;
        this.ad = true;
        this.au = 3;
        this.ay = false;
    }
    
    public String j() {
        String s = null;
        if (this.f.g[this.p] != null && this.f.g[this.p].size() > 0) {
            s = this.f.g[this.p].elementAt(this.q);
        }
        if (s == null && this.f.f != null && this.f.f.size() > 0) {
            s = this.f.f.elementAt(this.k.p.a() % this.f.f.size());
        }
        return s;
    }
    
    public void a(final int n) {
        if (n != this.v.a || !this.n) {
            this.c(21);
        }
    }
    
    public void b(final int n) {
    }
    
    public void k() {
        if (this.h != null) {
            this.h.a(0, 0, super.i, super.j);
        }
        if (this.i != null) {
            this.i.a(0, 0, super.i, super.j);
        }
    }
    
    public synchronized void l() {
        super.l();
        if (!this.f.f && ((this.h != null && this.h.m()) || (this.i != null && this.i.m()))) {
            this.f.m();
        }
        if (!this.j && this.f.g && ((this.h != null && this.h.k()) || (this.i != null && this.i.k()))) {
            this.m();
        }
    }
    
    public void c(final int n) {
        if (!this.n) {
            return;
        }
        if (n >= this.f.d) {
            return;
        }
        this.g.a(this.f.b[n], 0);
    }
    
    public void m() {
        this.j = true;
        if (this.h != null) {
            super.h.f(this.h);
            this.h = null;
        }
        if (this.i != null) {
            super.h.f(this.i);
            this.i = null;
        }
        (this.k = new w(this)).a(0, 0, super.i, super.j);
        super.h.c(this.k);
        super.h.e(this.k);
        (this.l = new z(this)).a(0, 0, 0, 0);
        super.h.c(this.l);
        final j[] a = this.f.a(this.f.a[13], 2);
        final int h = a[0].h();
        final int c = a[0].c();
        (this.t = new aa(super.h, 0, this)).a(60 - h / 2, 117 - c / 2, h, c);
        this.t.d = a[1];
        this.t.e = a[0];
        this.t.p = true;
        super.h.c(this.t);
        this.x = new aa(super.h, 4, this);
        (this.x.d = new j()).a(this.f.ak.h(), this.f.ak.c());
        this.x.a(28, 175, this.f.ak.h(), this.f.ak.c());
        this.x.p = true;
        super.h.c(this.x);
        this.f.as = this.t.d;
        this.f.at = this.t.e;
        final j[] a2 = this.f.a(this.f.a[14], 2);
        final int h2 = a2[0].h();
        final int c2 = a2[0].c();
        final int n = a2[0].h() / 2;
        final int n2 = a2[0].c() / 2;
        (this.u = new aa(super.h, 1, this)).a(61 - h2 / 2, 319 - c2 / 2, h2, c2);
        this.u.d = a2[1];
        this.u.e = a2[0];
        this.u.p = true;
        super.h.c(this.u);
        (this.v = new aa(super.h, 2, this)).a(4, 311, this.f.aw[0].h(), this.f.aw[0].c());
        this.v.d = this.f.aw[1];
        this.v.e = this.f.aw[0];
        this.v.p = true;
        super.h.c(this.v);
        (this.w = new aa(super.h, 3, this)).a(102, 311, this.f.ay[0].h(), this.f.ay[0].c());
        this.w.d = this.f.ay[1];
        this.w.e = this.f.ay[0];
        this.w.p = true;
        super.h.c(this.w);
        (this.ax = new ab(this)).a(0, 0, super.i, super.j);
        super.h.c(this.ax);
        this.k.q();
    }
    
    static {
        a = new int[] { 3, 3, 3, 3, 3, 4, 4, 4, 5, 5 };
        b = new double[] { 0.1, 0.5 };
        c = new int[][] { { 177, 237, 63 }, { 255, 255, 255 }, { 255, 235, 30 }, { 177, 237, 63 }, { 45, 118, 3 }, { 0, 0, 0, 128 } };
        d = new int[][] { { 177, 237, 63 }, { 61, 153, 5 }, { 68, 173, 5 }, { 100, 240, 6 }, { 255, 255, 255 } };
    }
    
    public void d(final int n) {
        switch (n) {
            case 201: {
                this.e();
                this.aw = true;
                break;
            }
            case 20: {
                switch (this.av) {
                    case 1: {
                        this.b();
                        break;
                    }
                }
                this.e();
                break;
            }
            default: {
                this.e();
                break;
            }
        }
        if (this.t != null && n == this.t.a) {
            if (this.k.l.size() > 0) {
                final ad ad = this.k.l.elementAt(this.k.l.size() - 1);
                if (ad != null) {
                    final int n2 = (int)ad.c + 20;
                    final int n3 = (int)ad.d + 20;
                    this.k.aa = 3;
                    this.k.g(n2, n3);
                    this.k.aa = 1;
                    this.ap = true;
                }
            }
        }
        else {
            if (this.u != null && n == this.u.a) {
                this.c();
                return;
            }
            if (this.v != null && n == this.v.a) {
                this.n = !this.n;
                if (this.n) {
                    this.v.d = this.f.aw[1];
                    this.v.e = this.f.aw[0];
                    this.c(21);
                    return;
                }
                this.v.d = this.f.ax[1];
                this.v.e = this.f.ax[0];
            }
            else {
                if (this.w != null && n == this.w.a) {
                    this.a(null, "<DialogText>\t<BR/><Color Value=FFFFFF>\t<Font Name=SansSerif Style=Bold Size=14>\t\t<Center><Shadow>RULES</Shadow></Center>\t</Font>\t\tLink letters together to form words. Click<BR/>\t<Color Value=F8FF0F><Shadow>SUBMIT</Shadow></Color>&nbsp;when you have a complete word.<BR/>\t\tYou can also&nbsp;<Color Value=BBBBBB><Shadow>double click</Shadow></Color>&nbsp;on the last letter<BR/>\t\tin the word to submit it.<BR/><BR/>\t\tWatch out for&nbsp;<Color Value=FF5550><Shadow>red tiles!</Shadow></Color>&nbsp;&nbsp;If they burn down to<BR/>\t\tthe bottom of the screen, they will set your<BR/>\t\tlibrary on fire and end the game.<BR/><BR/>\t</Color>\t<Font Name=SansSerif Style=Bold Size=8>\t\t<Color Value=55FF10>\t\t\t<Font Name=SansSerif Style=Bold Size=11>\t\t\t\t<Color Value=D0FF40><Shadow>SCORING:</Shadow></Color>&nbsp;\t\t\t</Font>\t\t\tLong words are worth more points. Letters<BR/>\t\t\ton plain tiles are the most common, and worth the<BR/>\t\t\tleast points. Tiles with dots in the corner contain<BR/>\t\t\trare letters and are worth more points.<BR/><BR/>\t\t\t<Font Name=SansSerif Style=Plain Size=12>\t\t\t\t<Color Value=FFFFFF>\t\t\t\t\t&nbsp;&nbsp;-&nbsp;Each&nbsp;<Color Value=55FF10><Shadow>GREEN TILE</Shadow></Color>&nbsp;is worth a&nbsp;<Shadow>+2</Shadow>&nbsp;tile bonus!<BR/>\t\t\t\t\t&nbsp;&nbsp;&nbsp;-&nbsp;Each&nbsp;<Color Value=F8FF0F><Shadow>GOLD TILE</Shadow></Color>&nbsp;is worth a&nbsp;<Shadow>+4</Shadow>&nbsp;tile bonus!<BR/><BR/>\t\t\t\t</Color>\t\t\t</Font>\t\t</Color>\t</Font></DialogText>", "Click here to continue", 2);
                    this.y.k = 0;
                    this.y.a(125, 1, 285, 328);
                    this.y.g = this.f.j;
                    ((an)this.y).a.c(14);
                    ((an)this.y).a.b(14);
                    ((an)this.y).b("<DialogText>\t<BR/><Color Value=FFFFFF>\t<Font Name=SansSerif Style=Bold Size=14>\t\t<Center><Shadow>RULES</Shadow></Center>\t</Font>\t\tLink letters together to form words. Click<BR/>\t<Color Value=F8FF0F><Shadow>SUBMIT</Shadow></Color>&nbsp;when you have a complete word.<BR/>\t\tYou can also&nbsp;<Color Value=BBBBBB><Shadow>double click</Shadow></Color>&nbsp;on the last letter<BR/>\t\tin the word to submit it.<BR/><BR/>\t\tWatch out for&nbsp;<Color Value=FF5550><Shadow>red tiles!</Shadow></Color>&nbsp;&nbsp;If they burn down to<BR/>\t\tthe bottom of the screen, they will set your<BR/>\t\tlibrary on fire and end the game.<BR/><BR/>\t</Color>\t<Font Name=SansSerif Style=Bold Size=8>\t\t<Color Value=55FF10>\t\t\t<Font Name=SansSerif Style=Bold Size=11>\t\t\t\t<Color Value=D0FF40><Shadow>SCORING:</Shadow></Color>&nbsp;\t\t\t</Font>\t\t\tLong words are worth more points. Letters<BR/>\t\t\ton plain tiles are the most common, and worth the<BR/>\t\t\tleast points. Tiles with dots in the corner contain<BR/>\t\t\trare letters and are worth more points.<BR/><BR/>\t\t\t<Font Name=SansSerif Style=Plain Size=12>\t\t\t\t<Color Value=FFFFFF>\t\t\t\t\t&nbsp;&nbsp;-&nbsp;Each&nbsp;<Color Value=55FF10><Shadow>GREEN TILE</Shadow></Color>&nbsp;is worth a&nbsp;<Shadow>+2</Shadow>&nbsp;tile bonus!<BR/>\t\t\t\t\t&nbsp;&nbsp;&nbsp;-&nbsp;Each&nbsp;<Color Value=F8FF0F><Shadow>GOLD TILE</Shadow></Color>&nbsp;is worth a&nbsp;<Shadow>+4</Shadow>&nbsp;tile bonus!<BR/><BR/>\t\t\t\t</Color>\t\t\t</Font>\t\t</Color>\t</Font></DialogText>", 0, 0);
                    this.av = 0;
                    return;
                }
                if (this.x != null && n == this.x.a) {
                    this.k.u();
                }
            }
        }
    }
    
    public void e(final int n) {
    }
    
    public void b(final String s, final String s2, final String s3, final int n) {
        if (this.aw) {
            return;
        }
        this.e();
        (this.y = new ak(super.h, this, s, s2, s3, n)).a(WordFallApplet.c);
        this.y.b(WordFallApplet.d);
        this.y.k = 8;
        this.y.a(146, 80, 250, this.y.a(250));
        super.h.c(this.y);
        super.h.a(this.y);
        if (this.k != null) {
            this.k.e(true);
        }
        this.av = 0;
    }
    
    public void f(final int n) {
    }
    
    public void n() {
        boolean b = true;
        for (int i = 0; i < this.f.n.length; ++i) {
            if (!this.f.n[i].j()) {
                b = false;
            }
        }
        if (b) {
            if (this.k != null) {
                this.k.e(true);
            }
            (this.o = new ar(this)).a(0, 0, super.i, super.j);
            super.h.c(this.o);
        }
    }
    
    public void g(final int n) {
    }
    
    public boolean o() {
        return this.ax != null && this.ax.j != this.ax.i;
    }
}
