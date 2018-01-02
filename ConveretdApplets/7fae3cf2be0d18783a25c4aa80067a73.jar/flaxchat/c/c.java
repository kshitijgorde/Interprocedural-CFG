// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import java.util.Enumeration;
import java.awt.event.MouseEvent;
import flaxchat.i.k;
import flaxchat.FlaxChat;
import flaxchat.a.e;
import java.awt.Dimension;
import flaxchat.i.b;
import java.awt.Component;
import flaxchat.n;
import flaxchat.a.d;
import flaxchat.i.i;
import flaxchat.i.g;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class c extends a implements MouseListener, ActionListener
{
    private g m;
    private g n;
    private f o;
    private i p;
    private i q;
    private String r;
    private flaxchat.c.i s;
    public d t;
    private final h u;
    private flaxchat.b.h v;
    private static String[] z;
    
    public c(final n n, final String r) {
        int l = a.l;
        super(n);
        this.s = new flaxchat.c.i(this);
        this.t = new d();
        this.u = new h();
        this.r = r;
        this.n = new g(n.g(), new flaxchat.i.c(0, this));
        this.m = new g(n.g(), new b(0));
        this.v = new flaxchat.b.h(n, null, r, c.z[23]);
        this.n.setName(c.z[22] + r);
        this.n.d().addMouseListener(this);
        ((flaxchat.i.c)this.n.d()).a(this);
        this.t.a(new Dimension(400, 150));
        this.m.setName(c.z[21] + r);
        this.m.d().addMouseListener(this);
        ((b)this.m.d()).b(false);
        this.add(this.n, c.z[25]);
        this.add(this.m, c.z[24]);
        if (flaxchat.a.e.c != 0) {
            a.l = ++l;
        }
    }
    
    public void c(final String s) {
        this.a(flaxchat.d.b.b(c.z[17], c.z[16]), s);
    }
    
    public void a(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        super.k.t().e(this.l());
        if (flaxchat.d.b.a(c.z[27], true)) {
            this.n.a("[" + FlaxChat.e(c.z[26]) + c.z[5] + s + s2);
            return;
        }
        this.n.a(String.valueOf(s) + s2);
    }
    
    public void b(String a) {
        a = flaxchat.f.h.a(a, this.a().k());
        if (this.h(a)) {
            this.a(c.z[13], c.z[9]);
            return;
        }
        Label_0165: {
            if (a.startsWith(c.z[10])) {
                this.a(flaxchat.d.b.b(c.z[11], c.z[11]), "*" + this.a().g() + " " + a.substring(7));
                if (a.l == 0) {
                    break Label_0165;
                }
            }
            this.a(flaxchat.d.b.b(c.z[7], c.z[12]), "<" + this.a().g() + c.z[8] + a);
        }
        this.a().a(this.r, a);
    }
    
    public void a(final flaxchat.f.g g) {
        if (this.s.a(g.g()) != null) {
            return;
        }
        this.s.a(g.g(), g);
        ((b)this.m.d()).a(new k(g.g(), g), this.u);
        if (this.m.d().g() == null) {
            this.m.d().c(g.g());
            this.m.c().b(0);
        }
        this.j();
    }
    
    public flaxchat.f.g b(final flaxchat.f.g g) {
        if (this.s.a(g.g()) == null) {
            return null;
        }
        final flaxchat.f.g g2 = (flaxchat.f.g)this.s.b(g.g());
        this.m.d().b(g.g());
        this.j();
        return g2;
    }
    
    public void a() {
        this.s.clear();
        this.m.b();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != this.n.d()) {
            if (mouseEvent.getComponent() == this.m.d()) {
                if (flaxchat.a.h.b(mouseEvent)) {
                    if (mouseEvent.getClickCount() < 2) {
                        return;
                    }
                    final k g = this.m.d().g();
                    if (g == null) {
                        return;
                    }
                    this.a(this.n.d(), c.z[2], this.l(), g.b());
                }
                else {
                    this.a(this.m.d(), c.z[1], this.l(), this.m.d().g().b(), mouseEvent.getPoint());
                }
            }
            return;
        }
        if (!flaxchat.a.h.b(mouseEvent)) {
            this.a(this.n.d(), c.z[0], this.l(), this.m.d().g().b(), mouseEvent.getPoint());
            return;
        }
        if (mouseEvent.getClickCount() < 2) {
            return;
        }
        if (mouseEvent.isConsumed()) {
            return;
        }
        this.a(this.n.d(), c.z[3], this.l(), null);
    }
    
    public void e() {
        this.i();
        this.m.b();
        this.s.clear();
        this.t.removeAll();
        this.a().c(this.l());
    }
    
    public void g() {
        if (this.p == null) {
            this.p = new flaxchat.b.e(super.k);
        }
        if (this.q != null) {
            this.remove(this.q);
        }
        final Object a = this.a((Object)c.z[14]);
        if (a != null) {
            ((flaxchat.i.f)a).a(false);
        }
        this.add(this.p, c.z[15]);
        this.b();
        super.k.g().requestFocus();
    }
    
    public void m() {
        if (this.q != null) {
            this.remove(this.q);
        }
        if (this.p != null) {
            this.remove(this.p);
        }
        this.b();
        super.k.g().requestFocus();
    }
    
    public void h() {
        if (this.q == null) {
            this.q = new flaxchat.b.f(super.k);
        }
        final Object a = this.a((Object)c.z[18]);
        if (a != null) {
            ((flaxchat.i.f)a).a(false);
        }
        if (this.p != null) {
            this.remove(this.p);
        }
        this.add(this.q, c.z[15]);
        this.b();
        if (super.k.g().getCaretPosition() == flaxchat.f.d.e(super.k.g().getText())) {
            super.k.g().setText("");
        }
        super.k.g().requestFocus();
    }
    
    public void i(final String s) {
        if (this.o == null) {
            this.o = new f(this, this.n);
        }
        this.o.a(s);
    }
    
    public void i() {
        this.n.b();
        if (this.o != null) {
            this.o.setText("");
        }
    }
    
    public void f() {
        this.j();
        super.k.g().requestFocus();
    }
    
    private void j() {
        if (super.k.e() != this) {
            return;
        }
        final String a = this.a().a(this.r);
        if (a == null || a.trim().length() == 0) {
            super.k.l().c("[" + this.l() + c.z[6] + this.m.e() + c.z[4]);
            return;
        }
        super.k.l().c("[" + this.l() + c.z[6] + this.m.e() + c.z[5] + a + "]");
    }
    
    public void k() {
        if (super.k.e() != this) {
            return;
        }
        this.j();
    }
    
    public void e(final String s) {
        final k b = this.m.d().b(s);
        if (b == null) {
            return;
        }
        ((b)this.m.d()).a(b, this.u);
        this.m.a();
    }
    
    public String j() {
        return this.r;
    }
    
    public flaxchat.b.h k() {
        return this.v;
    }
    
    public String l() {
        return this.r;
    }
    
    public f n() {
        return this.o;
    }
    
    public String b(final String s, final String s2) {
        if (s2.equalsIgnoreCase(this.i())) {
            return c.z[20];
        }
        if (s2.equals(this.l())) {
            return c.z[19];
        }
        return null;
    }
    
    public flaxchat.h.c g(String lowerCase) {
        final int l = a.l;
        lowerCase = lowerCase.trim().toLowerCase();
        final Enumeration<Object> keys = this.s.keys();
        final flaxchat.h.c c = new flaxchat.h.c(this.s.size());
        while (true) {
            Label_0084: {
                if (l == 0) {
                    break Label_0084;
                }
                final String s = keys.nextElement();
                if (s.startsWith(lowerCase)) {
                    c.c(((flaxchat.f.g)this.s.a(s)).g());
                }
            }
            if (!keys.hasMoreElements()) {
                return c;
            }
            continue;
        }
    }
    
    static {
        c.z = new String[] { z(z("\u00142#A\u001f\u00126o_\u001e\u0007/2")), z(z("\u00193!D\\\u000752Z\u0001")), z(z("\u00193!D\u001f\u00167'\u0002\u001d\u001e)6")), z(z("\u00142#A\u001f\u00126oX\u0018\u0019>-X")), z(z("*\u0007")), z(z("*z")), z(z("M\u0001")), z(z("\u0004?,[<\u0004=\u0001@\u001d\u0018(")), z(z("Iz")), z(z("$/,Z\u0012\u000277U\u0015\u0016z ZQ\u0003;0UQ\u001c5,Z\u0002\u00167#U\u0002\u001e4+U_")), z(z("v\u001b\u0001{88\u0014b")), z(z("tjt")), z(z("tjq")), z(z("tjv")), z(z("\u00145.@\u0003")), z(z("$57[\u0019")), z(z("tkp")), z(z("\u0005?!F\u0014\u0001?&b\u0002\u0010\u0019-C\u001e\u0005")), z(z("\u00047+C\u0014")), z(z("\u001c;,N\u001d\u0013;bM\u0004W5;Z\u001fW5;A\u0010\u0019;/N\u000b")), z(z("\u001a5&Z\u001d\u001b?0\u000f\u0018\u001c3bD\u0018\u00043.F\u001a\u000330\u0001")), z(z("42#A\u001f\u00126bz\u0002\u0012(b")), z(z("42#A\u001f\u00126bb\u0002\u0010z")), z(z("\u00142#A\u001f\u00126o[\u001e\u00186 N\u0003")), z(z("2;1[")), z(z("4?,[\u0014\u0005")), z(z("?\u0012xB\u001cM)1")), z(z("\u00042-X<\u0004=\u0010L\u0007\u0013\u000e+B\u0014")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'q';
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
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = 'Z';
                    break;
                }
                case 2: {
                    c2 = 'B';
                    break;
                }
                case 3: {
                    c2 = '/';
                    break;
                }
                default: {
                    c2 = 'q';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
