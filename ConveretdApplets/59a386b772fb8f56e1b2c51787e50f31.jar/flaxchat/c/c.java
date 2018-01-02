// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import java.util.Enumeration;
import java.awt.event.MouseEvent;
import flaxchat.d.k;
import flaxchat.FlaxChat;
import flaxchat.e.e;
import java.awt.Dimension;
import flaxchat.d.b;
import java.awt.Component;
import flaxchat.m;
import flaxchat.e.d;
import flaxchat.d.i;
import flaxchat.d.g;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class c extends a implements MouseListener, ActionListener
{
    private g j;
    private g k;
    private f l;
    private i m;
    private i n;
    private String o;
    private flaxchat.c.i p;
    public d q;
    private final h r;
    private flaxchat.b.h s;
    private static String[] z;
    
    public c(final m m, final String o) {
        final int i = a.i;
        super(m);
        this.p = new flaxchat.c.i(this);
        this.q = new d();
        this.r = new h();
        this.o = o;
        this.k = new g(m.g(), new flaxchat.d.c(0, this));
        this.j = new g(m.g(), new b(0));
        this.s = new flaxchat.b.h(m, null, o, c.z[24]);
        this.k.setName(c.z[27] + o);
        this.k.d().addMouseListener(this);
        ((flaxchat.d.c)this.k.d()).a(this);
        this.q.a(new Dimension(400, 150));
        this.j.setName(c.z[26] + o);
        this.j.d().addMouseListener(this);
        ((b)this.j.d()).b(false);
        this.add(this.k, c.z[25]);
        this.add(this.j, c.z[23]);
        if (i != 0) {
            flaxchat.e.e.c = !flaxchat.e.e.c;
        }
    }
    
    public void c(final String s) {
        this.a(flaxchat.i.b.b(c.z[0], c.z[1]), s);
    }
    
    public void a(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        super.h.t().e(this.n());
        if (flaxchat.i.b.a(c.z[5], true)) {
            this.k.a("[" + FlaxChat.e(c.z[4]) + c.z[6] + s + s2);
            return;
        }
        this.k.a(String.valueOf(s) + s2);
    }
    
    public void b(String a) {
        a = flaxchat.h.h.a(a, this.a().k());
        if (this.h(a)) {
            this.a(c.z[11], c.z[13]);
            return;
        }
        Label_0165: {
            if (a.startsWith(c.z[15])) {
                this.a(flaxchat.i.b.b(c.z[16], c.z[16]), "*" + this.a().g() + " " + a.substring(7));
                if (a.i == 0) {
                    break Label_0165;
                }
            }
            this.a(flaxchat.i.b.b(c.z[10], c.z[14]), "<" + this.a().g() + c.z[12] + a);
        }
        this.a().a(this.o, a);
    }
    
    public void a(final flaxchat.h.g g) {
        if (this.p.a(g.d()) != null) {
            return;
        }
        this.p.a(g.d(), g);
        ((b)this.j.d()).a(new k(g.d(), g), this.r);
        if (this.j.d().h() == null) {
            this.j.d().c(g.d());
            this.j.c().b(0);
        }
        this.l();
    }
    
    public flaxchat.h.g b(final flaxchat.h.g g) {
        if (this.p.a(g.d()) == null) {
            return null;
        }
        final flaxchat.h.g g2 = (flaxchat.h.g)this.p.b(g.d());
        this.j.d().b(g.d());
        this.l();
        return g2;
    }
    
    public void a() {
        this.p.clear();
        this.j.b();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != this.k.d()) {
            if (mouseEvent.getComponent() == this.j.d()) {
                if (flaxchat.e.g.b(mouseEvent)) {
                    if (mouseEvent.getClickCount() < 2) {
                        return;
                    }
                    final k h = this.j.d().h();
                    if (h == null) {
                        return;
                    }
                    this.a(this.k.d(), c.z[19], this.n(), h.b());
                }
                else {
                    this.a(this.j.d(), c.z[20], this.n(), this.j.d().h().b(), mouseEvent.getPoint());
                }
            }
            return;
        }
        if (!flaxchat.e.g.b(mouseEvent)) {
            this.a(this.k.d(), c.z[21], this.n(), this.j.d().h().b(), mouseEvent.getPoint());
            return;
        }
        if (mouseEvent.getClickCount() < 2) {
            return;
        }
        if (mouseEvent.isConsumed()) {
            return;
        }
        this.a(this.k.d(), c.z[22], this.n(), null);
    }
    
    public void e() {
        this.k();
        this.j.b();
        this.p.clear();
        this.q.removeAll();
        this.a().c(this.n());
    }
    
    public void g() {
        if (this.m == null) {
            this.m = new flaxchat.b.e(super.h);
        }
        if (this.n != null) {
            this.remove(this.n);
        }
        final Object a = this.a((Object)c.z[7]);
        if (a != null) {
            ((flaxchat.d.f)a).b(false);
        }
        this.add(this.m, c.z[2]);
        this.b();
        super.h.g().requestFocus();
    }
    
    public void h() {
        if (this.n != null) {
            this.remove(this.n);
        }
        if (this.m != null) {
            this.remove(this.m);
        }
        this.b();
        super.h.g().requestFocus();
    }
    
    public void i() {
        if (this.n == null) {
            this.n = new flaxchat.b.f(super.h);
        }
        final Object a = this.a((Object)c.z[3]);
        if (a != null) {
            ((flaxchat.d.f)a).b(false);
        }
        if (this.m != null) {
            this.remove(this.m);
        }
        this.add(this.n, c.z[2]);
        this.b();
        super.h.g().requestFocus();
    }
    
    public void j() {
        if (this.l == null) {
            this.l = new f(this, this.k);
        }
        this.l.a();
    }
    
    public void k() {
        this.k.b();
        if (this.l != null) {
            this.l.setText("");
        }
    }
    
    public void f() {
        this.l();
        super.h.g().requestFocus();
    }
    
    private void l() {
        if (super.h.e() != this) {
            return;
        }
        final String a = this.a().a(this.o);
        if (a == null || a.trim().length() == 0) {
            super.h.l().c("[" + this.n() + c.z[18] + this.j.e() + c.z[17]);
            return;
        }
        super.h.l().c("[" + this.n() + c.z[18] + this.j.e() + c.z[6] + a + "]");
    }
    
    public void m() {
        if (super.h.e() != this) {
            return;
        }
        this.l();
    }
    
    public void e(final String s) {
        final k b = this.j.d().b(s);
        if (b == null) {
            return;
        }
        ((b)this.j.d()).a(b, this.r);
        this.j.a();
    }
    
    public String j() {
        return this.o;
    }
    
    public flaxchat.b.h k() {
        return this.s;
    }
    
    public String n() {
        return this.o;
    }
    
    public f o() {
        return this.l;
    }
    
    public String b(final String s, final String s2) {
        if (s2.equalsIgnoreCase(this.i())) {
            return c.z[8];
        }
        if (s2.equals(this.n())) {
            return c.z[9] + s;
        }
        return null;
    }
    
    public flaxchat.f.c g(String lowerCase) {
        final int i = a.i;
        lowerCase = lowerCase.trim().toLowerCase();
        final Enumeration<Object> keys = this.p.keys();
        final flaxchat.f.c c = new flaxchat.f.c(this.p.size());
        while (true) {
            Label_0084: {
                if (i == 0) {
                    break Label_0084;
                }
                final String s = keys.nextElement();
                if (s.startsWith(lowerCase)) {
                    c.c(((flaxchat.h.g)this.p.a(s)).d());
                }
            }
            if (!keys.hasMoreElements()) {
                return c;
            }
            continue;
        }
    }
    
    static {
        c.z = new String[] { z(z("]\u0019UZ]Y\u0019R~KH?Y_W]")), z(z(",M\u0004")), z(z("|\u0013CGP")), z(z("\\\u0011__]")), z(z("g4\f^U\u0015\u000fE")), z(z("\\\u0014YDu\\\u001bdPNK(_^]")), z(z("r\\")), z(z("L\u0013Z\\J")), z(z("|\u0019ZU\u0018_\u0010WJQA\u001b\u0016WWJ\u000f\u0016]W[\\EFH_\u0013DG]KR\u0016d]\u000f\u000eSTJJ\b\u0016UW]\\_][@\u0012@VVF\u0019XPA\u0001")), z(z("l\u0014W]VJ\u0010\u0016WWJ\u000f\u0016]W[\\EFH_\u0013DG\u0018[\u0014_@\u0018H\u001d[V\u0018\u0015\\")), z(z("\\\u0019XGu\\\u001bu\\T@\u000e")), z(z(",L\u0002")), z(z("\u0011\\")), z(z("|\tXF[Z\u0011CI\\N\\TF\u0018[\u001dDI\u0018D\u0013XFKN\u0011WIKF\u0012_I\u0016")), z(z(",L\u0005")), z(z(".=ugq`2\u0016")), z(z(",L\u0000")), z(z("r!")), z(z("\u0015'")), z(z("A\u0015UXVN\u0011S\u001eTF\u000fB")), z(z("A\u0015UX\u0015_\u0013FFH")), z(z("L\u0014W]VJ\u0010\u001bCW_\tF")), z(z("L\u0014W]VJ\u0010\u001bDQA\u0018YD")), z(z("j\u001dEG")), z(z("L\u0014W]VJ\u0010\u001bGW@\u0010TRJ")), z(z("l\u0019XG]]")), z(z("l\u0014W]VJ\u0010\u0016fKJ\u000e\u0016")), z(z("l\u0014W]VJ\u0010\u0016~KH\\")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '8';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '/';
                    break;
                }
                case 1: {
                    c2 = '|';
                    break;
                }
                case 2: {
                    c2 = '6';
                    break;
                }
                case 3: {
                    c2 = '3';
                    break;
                }
                default: {
                    c2 = '8';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
