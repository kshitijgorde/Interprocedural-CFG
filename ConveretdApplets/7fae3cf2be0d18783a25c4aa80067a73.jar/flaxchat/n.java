// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.util.Enumeration;
import java.awt.Image;
import flaxchat.c.a;
import flaxchat.g.g;
import flaxchat.g.h;
import flaxchat.g.c;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import flaxchat.i.j;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.TextComponent;
import java.awt.Component;
import java.awt.Panel;
import flaxchat.c.b;
import flaxchat.e.f;
import flaxchat.b.d;
import flaxchat.i.e;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import flaxchat.i.i;

public class n extends i implements ActionListener
{
    private Hashtable k;
    public flaxchat.i.e l;
    private FlaxChat m;
    private d n;
    private f o;
    private flaxchat.c.d p;
    private b q;
    private Panel r;
    private Component s;
    private o t;
    private TextComponent u;
    public flaxchat.a.f v;
    public static int w;
    private static String[] z;
    
    public n(final FlaxChat m) {
        super(false);
        this.k = new Hashtable();
        this.l = new flaxchat.i.e(flaxchat.n.z[15]);
        this.n = new d();
        this.v = new flaxchat.a.f();
        this.m = m;
        flaxchat.d.b.a(this);
        this.d();
        this.t = new o(this, true);
        this.r = new e();
        this.o = new f(this.r);
        this.u = new q(this);
        this.s = this.a();
        this.i();
        this.setLayout(new flaxchat.e.e(this));
        this.add(this.t);
        this.add(this.s);
        this.add(this.n);
        this.p();
    }
    
    private Panel a() {
        final e e = new e();
        e.setLayout(new BorderLayout(0, 0));
        e.add(this.r, flaxchat.n.z[16]);
        e.add(this.b(), flaxchat.n.z[13]);
        return e;
    }
    
    private Panel b() {
        this.l.a(this);
        final j j = new j();
        j.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        j.add(this.g(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        j.add(this.l, gridBagConstraints2);
        return j;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.u.isEnabled()) {
            ((q)this.u).d();
            return;
        }
        this.l.a(flaxchat.n.z[15]);
        this.u.setEnabled(true);
        this.u.setText("");
    }
    
    private void d() {
        this.a(new flaxchat.g.f(this));
        this.a(new h(this));
        this.a(new flaxchat.g.i(this));
        this.a(new flaxchat.g.d(this));
        this.a(new g(this));
        this.a(new flaxchat.g.e(this));
    }
    
    public void a(final c c) {
        this.k.put(c.a().toLowerCase(), c);
    }
    
    public void a(final flaxchat.g.e e) {
        final int w = flaxchat.n.w;
        final String[] b = e.b();
        int n = 0;
        while (true) {
            Label_0035: {
                if (w == 0) {
                    break Label_0035;
                }
                this.k.put(b[n].toLowerCase(), e);
                ++n;
            }
            if (n >= b.length) {
                return;
            }
            continue;
        }
    }
    
    public void a(final char c) {
        this.b(String.valueOf(c));
    }
    
    public void b(final String s) {
        final String text = this.u.getText();
        final int caretPosition = this.u.getCaretPosition();
        this.u.setText(String.valueOf(text.substring(0, caretPosition)) + s + text.substring(caretPosition));
        this.u.setCaretPosition(caretPosition + s.length());
    }
    
    public a c(final String s) {
        return (a)this.o.b(s);
    }
    
    public a e() {
        return (a)this.o.a();
    }
    
    public String f() {
        return this.o.b();
    }
    
    public synchronized void d(final String s) {
        this.t.c(s);
        final a c = this.c(s);
        this.n.a(c.k());
        if (this.o.b().equals(s)) {
            return;
        }
        this.o.a(s);
        this.b();
        c.f();
    }
    
    public void a(final int n) {
        this.d(this.t.a(n));
    }
    
    public TextComponent g() {
        return this.u;
    }
    
    public r h() {
        return this.m.p();
    }
    
    public void i() {
        this.t.b(this.j());
    }
    
    public String j() {
        final String c = flaxchat.d.b.c(flaxchat.n.z[11]);
        if (flaxchat.n.z[4].equalsIgnoreCase(c)) {
            return flaxchat.n.z[13];
        }
        if (flaxchat.n.z[9].equalsIgnoreCase(c)) {
            return flaxchat.n.z[1];
        }
        if (flaxchat.n.z[5].equalsIgnoreCase(c)) {
            return flaxchat.n.z[2];
        }
        if (flaxchat.n.z[3].equalsIgnoreCase(c)) {
            return flaxchat.n.z[8];
        }
        if (flaxchat.n.z[12].equalsIgnoreCase(c)) {
            return flaxchat.n.z[13];
        }
        if (flaxchat.n.z[6].equalsIgnoreCase(c)) {
            return flaxchat.n.z[1];
        }
        if (flaxchat.n.z[10].equalsIgnoreCase(c)) {
            return flaxchat.n.z[2];
        }
        if (flaxchat.n.z[7].equalsIgnoreCase(c)) {
            return flaxchat.n.z[8];
        }
        return flaxchat.n.z[13];
    }
    
    public a a(final int n, final Object o) {
        final int w = n.w;
        String g2 = null;
        a a = null;
        Image image = null;
        Label_0235: {
            if (n == 0) {
                final flaxchat.f.g g = (flaxchat.f.g)o;
                g2 = g.g();
                a = (a)this.o.b(g2);
                if (a == null) {
                    a = new flaxchat.c.e(this, g);
                    this.r.add(a, g2);
                }
                image = flaxchat.d.d.x();
                if (w == 0) {
                    break Label_0235;
                }
            }
            if (n == 2) {
                g2 = (String)o;
                a = (a)this.o.b(g2);
                if (a == null) {
                    a = new flaxchat.c.c(this, g2);
                    this.r.add(a, g2);
                }
                image = flaxchat.d.d.w();
                if (w == 0) {
                    break Label_0235;
                }
            }
            if (n == 3) {
                g2 = (String)o;
                if (this.q == null) {
                    this.q = new b(this, g2);
                    this.r.add(this.q, g2);
                }
                a = this.q;
                image = flaxchat.d.d.w();
                if (w == 0) {
                    break Label_0235;
                }
            }
            g2 = (String)o;
            if (this.p == null) {
                this.p = new flaxchat.c.d(this);
                this.r.add(this.p, g2);
            }
            a = this.p;
            image = flaxchat.d.d.w();
        }
        this.t.a(g2, a, image);
        return a;
    }
    
    public flaxchat.c.d k() {
        return this.p;
    }
    
    public FlaxChat l() {
        return this.m;
    }
    
    public b m() {
        return this.q;
    }
    
    public void n() {
        this.t.removeAll();
        this.m.n();
        this.p().d();
    }
    
    public void a(final boolean b) {
        final int w = flaxchat.n.w;
        final int componentCount = this.r.getComponentCount();
        int n = 0;
        while (true) {
            Label_0051: {
                if (w == 0) {
                    break Label_0051;
                }
                final Component component = this.r.getComponent(n);
                if (!(component instanceof a)) {
                    return;
                }
                ((a)component).a(b);
                ++n;
            }
            if (n >= componentCount) {
                return;
            }
            continue;
        }
    }
    
    public void o() {
        this.a(this.e());
    }
    
    public void e(final String s) {
        this.a(this.c(s));
    }
    
    public void a(final a a) {
        if (a == null) {
            return;
        }
        final int a2 = this.t.a();
        if (a2 == 0) {
            return;
        }
        if (a2 == 1) {
            if (a instanceof flaxchat.c.d) {
                return;
            }
            this.t.a(a);
            a.e();
            this.t.b();
            this.p();
            this.d(flaxchat.n.z[0]);
        }
        else {
            final int a3 = this.t.a(a);
            a.e();
            this.t.b();
            if (a3 == -1) {
                return;
            }
            if (a3 == a2 - 1) {
                this.a(a3 - 1);
                return;
            }
            this.a(a3);
        }
    }
    
    private a p() {
        return this.a(1, flaxchat.n.z[0]);
    }
    
    public void q() {
        this.n();
    }
    
    public void r() {
        final int w = flaxchat.n.w;
        final Enumeration c = this.o.c();
        while (true) {
            Label_0038: {
                if (w == 0) {
                    break Label_0038;
                }
                final a a = c.nextElement();
                if (a instanceof flaxchat.c.c) {
                    this.a(a);
                }
            }
            if (!c.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public void s() {
        final int w = flaxchat.n.w;
        final Enumeration c = this.o.c();
        while (true) {
            Label_0038: {
                if (w == 0) {
                    break Label_0038;
                }
                final a a = c.nextElement();
                if (a instanceof flaxchat.c.e) {
                    this.a(a);
                }
            }
            if (!c.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public void f(final String s) {
        final int w = flaxchat.n.w;
        final Enumeration c = this.o.c();
        while (true) {
            Label_0048: {
                if (w == 0) {
                    break Label_0048;
                }
                final a a = c.nextElement();
                if (!s.equals(a.j())) {
                    this.a(a);
                }
            }
            if (!c.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public o t() {
        return this.t;
    }
    
    public void a(final String s, final String s2) {
        final a a = (a)this.o.a(s, s2);
        if (a == null) {
            return;
        }
        if (a instanceof flaxchat.c.e) {
            ((flaxchat.c.e)a).j(s2);
        }
        final flaxchat.i.f d = this.t.d(s);
        if (d == null) {
            return;
        }
        d.a(s2);
        d.b(s2);
        this.t.b();
    }
    
    public Enumeration u() {
        return this.o.c();
    }
    
    public void g(final String s) {
        this.h().b(s);
    }
    
    public flaxchat.c.c a(final String s, final boolean b) {
        final a a = this.a(2, s);
        if (b) {
            a.d();
        }
        return (flaxchat.c.c)a;
    }
    
    public void a(final Object o, final String s, final flaxchat.f.g g, String trim, final Hashtable hashtable) {
        trim = trim.trim();
        final int index = trim.indexOf(32);
        if (index == -1) {
            this.a(o, s, g, trim, trim, hashtable);
            return;
        }
        this.a(o, s, g, trim.substring(0, index), trim, hashtable);
    }
    
    private void a(final Object o, final String s, final flaxchat.f.g g, final String s2, final String s3, final Hashtable hashtable) {
        c c = this.k.get(s2.toLowerCase());
        if (c == null) {
            c = this.k.get(flaxchat.n.z[14]);
        }
        c.a(o, s, g, s3, hashtable);
    }
    
    public boolean b(final a a) {
        return this.t.d(a.j()) != null;
    }
    
    public void a(final flaxchat.f.g g) {
        if (g == null) {
            return;
        }
        final a a = this.a(0, g);
        if (a == null) {
            return;
        }
        a.d();
    }
    
    public Component v() {
        return this.s;
    }
    
    public Container w() {
        return this.n;
    }
    
    public void x() {
        final a e = this.e();
        e.m();
        if (e.n() != null) {
            e.n().a();
        }
    }
    
    public void y() {
        int b = this.t.b(this.e());
        if (b == -1) {
            return;
        }
        ++b;
        if (b < this.t.getComponentCount()) {
            this.a(b);
            return;
        }
        this.a(0);
    }
    
    static {
        n.z = new String[] { z(z("._#kQ\u000e")), z(z("3D0kL")), z(z("8J1k")), z(z("\u000fB%wP")), z(z("\u001fD6kK\u0010")), z(z("\u0011N$k")), z(z("\u0013D0kL")), z(z("\nN1k")), z(z("*N1k")), z(z("\tD2")), z(z("\u0018J1k")), z(z("\u001eD,kV\u0012G ~V")), z(z("\u000eD7kL")), z(z(".D7kL")), z(z("\u0019N$~Q\u0011_")), z(z(":D,{A\u000f")), z(z(">N,kA\u000f")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '$';
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
                    c2 = '}';
                    break;
                }
                case 1: {
                    c2 = '+';
                    break;
                }
                case 2: {
                    c2 = 'B';
                    break;
                }
                case 3: {
                    c2 = '\u001f';
                    break;
                }
                default: {
                    c2 = '$';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
