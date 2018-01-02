// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.util.Enumeration;
import java.awt.Image;
import flaxchat.c.a;
import flaxchat.a.g;
import flaxchat.a.h;
import flaxchat.a.c;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.TextComponent;
import java.awt.Component;
import java.awt.Panel;
import flaxchat.c.b;
import flaxchat.g.f;
import flaxchat.b.d;
import flaxchat.d.e;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import flaxchat.d.i;

public class m extends i implements ActionListener
{
    private Hashtable h;
    public flaxchat.d.e i;
    private FlaxChat j;
    private d k;
    private f l;
    private flaxchat.c.d m;
    private b n;
    private Panel o;
    private Component p;
    private n q;
    private TextComponent r;
    public static boolean s;
    private static String[] z;
    
    public m(final FlaxChat j) {
        super(false);
        this.h = new Hashtable();
        this.i = new flaxchat.d.e(flaxchat.m.z[3]);
        this.k = new d();
        this.j = j;
        flaxchat.i.b.a(this);
        this.d();
        this.q = new n(this, true);
        this.o = new e();
        this.l = new f(this.o);
        this.r = new p(this);
        this.p = this.a();
        this.i();
        this.setLayout(new flaxchat.g.e(this));
        this.add(this.q);
        this.add(this.p);
        this.add(this.k);
        this.p();
    }
    
    private Panel a() {
        final e e = new e();
        e.setLayout(new BorderLayout(0, 0));
        e.add(this.o, flaxchat.m.z[1]);
        e.add(this.b(), flaxchat.m.z[2]);
        return e;
    }
    
    private Panel b() {
        this.i.a(this);
        final Panel panel = new Panel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 0.0;
        panel.add(this.g(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints2.gridx = 4;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        panel.add(this.i, gridBagConstraints2);
        return panel;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.r.isEnabled()) {
            ((p)this.r).c();
            return;
        }
        this.i.a(flaxchat.m.z[3]);
        this.r.setEnabled(true);
        this.r.setText("");
    }
    
    private void d() {
        this.a(new flaxchat.a.f(this));
        this.a(new h(this));
        this.a(new flaxchat.a.i(this));
        this.a(new flaxchat.a.d(this));
        this.a(new g(this));
        this.a(new flaxchat.a.e(this));
    }
    
    public void a(final c c) {
        this.h.put(c.a().toLowerCase(), c);
    }
    
    public void a(final flaxchat.a.e e) {
        final boolean s = flaxchat.m.s;
        final String[] b = e.b();
        int n = 0;
        while (true) {
            Label_0035: {
                if (!s) {
                    break Label_0035;
                }
                this.h.put(b[n].toLowerCase(), e);
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
        final String text = this.r.getText();
        final int caretPosition = this.r.getCaretPosition();
        this.r.setText(String.valueOf(text.substring(0, caretPosition)) + s + text.substring(caretPosition));
        this.r.setCaretPosition(caretPosition + s.length());
    }
    
    public a c(final String s) {
        return (a)this.l.b(s);
    }
    
    public a e() {
        return (a)this.l.a();
    }
    
    public String f() {
        return this.l.b();
    }
    
    public synchronized void d(final String s) {
        this.q.c(s);
        final a c = this.c(s);
        this.k.a(c.k());
        if (this.l.b().equals(s)) {
            return;
        }
        this.l.a(s);
        this.b();
        c.f();
    }
    
    public void a(final int n) {
        this.d(this.q.a(n));
    }
    
    public TextComponent g() {
        return this.r;
    }
    
    public q h() {
        return this.j.o();
    }
    
    public void i() {
        this.q.b(this.j());
    }
    
    public String j() {
        final String c = flaxchat.i.b.c(flaxchat.m.z[16]);
        if (flaxchat.m.z[11].equalsIgnoreCase(c)) {
            return flaxchat.m.z[2];
        }
        if (flaxchat.m.z[14].equalsIgnoreCase(c)) {
            return flaxchat.m.z[7];
        }
        if (flaxchat.m.z[15].equalsIgnoreCase(c)) {
            return flaxchat.m.z[10];
        }
        if (flaxchat.m.z[13].equalsIgnoreCase(c)) {
            return flaxchat.m.z[9];
        }
        if (flaxchat.m.z[8].equalsIgnoreCase(c)) {
            return flaxchat.m.z[2];
        }
        if (flaxchat.m.z[6].equalsIgnoreCase(c)) {
            return flaxchat.m.z[7];
        }
        if (flaxchat.m.z[5].equalsIgnoreCase(c)) {
            return flaxchat.m.z[10];
        }
        if (flaxchat.m.z[12].equalsIgnoreCase(c)) {
            return flaxchat.m.z[9];
        }
        return flaxchat.m.z[2];
    }
    
    public a a(final int n, final Object o) {
        final boolean s = flaxchat.m.s;
        String d = null;
        a a = null;
        Image image = null;
        Label_0235: {
            if (n == 0) {
                final flaxchat.h.g g = (flaxchat.h.g)o;
                d = g.d();
                a = (a)this.l.b(d);
                if (a == null) {
                    a = new flaxchat.c.e(this, g);
                    this.o.add(a, d);
                }
                image = flaxchat.i.d.x();
                if (!s) {
                    break Label_0235;
                }
            }
            if (n == 2) {
                d = (String)o;
                a = (a)this.l.b(d);
                if (a == null) {
                    a = new flaxchat.c.c(this, d);
                    this.o.add(a, d);
                }
                image = flaxchat.i.d.w();
                if (!s) {
                    break Label_0235;
                }
            }
            if (n == 3) {
                d = (String)o;
                if (this.n == null) {
                    this.n = new b(this, d);
                    this.o.add(this.n, d);
                }
                a = this.n;
                image = flaxchat.i.d.w();
                if (!s) {
                    break Label_0235;
                }
            }
            d = (String)o;
            if (this.m == null) {
                this.m = new flaxchat.c.d(this);
                this.o.add(this.m, d);
            }
            a = this.m;
            image = flaxchat.i.d.w();
        }
        this.q.a(d, a, image);
        return a;
    }
    
    public flaxchat.c.d k() {
        return this.m;
    }
    
    public FlaxChat l() {
        return this.j;
    }
    
    public b m() {
        return this.n;
    }
    
    public void n() {
        this.q.removeAll();
        this.j.m();
        this.p().d();
    }
    
    public void a(final boolean b) {
        final boolean s = flaxchat.m.s;
        final int componentCount = this.o.getComponentCount();
        int n = 0;
        while (true) {
            Label_0051: {
                if (!s) {
                    break Label_0051;
                }
                final Component component = this.o.getComponent(n);
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
        final int a2 = this.q.a();
        if (a2 == 0) {
            return;
        }
        if (a2 == 1) {
            if (a instanceof flaxchat.c.d) {
                return;
            }
            this.q.a(a);
            a.e();
            this.q.b();
            this.p();
            this.d(flaxchat.m.z[4]);
        }
        else {
            final int a3 = this.q.a(a);
            a.e();
            this.q.b();
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
        return this.a(1, flaxchat.m.z[4]);
    }
    
    public void q() {
        this.n();
    }
    
    public void r() {
        final boolean s = flaxchat.m.s;
        final Enumeration c = this.l.c();
        while (true) {
            Label_0038: {
                if (!s) {
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
        final boolean s = flaxchat.m.s;
        final Enumeration c = this.l.c();
        while (true) {
            Label_0038: {
                if (!s) {
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
        final boolean s2 = flaxchat.m.s;
        final Enumeration c = this.l.c();
        while (true) {
            Label_0048: {
                if (!s2) {
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
    
    public n t() {
        return this.q;
    }
    
    public void a(final String s, final String s2) {
        final a a = (a)this.l.a(s, s2);
        if (a == null) {
            return;
        }
        if (a instanceof flaxchat.c.e) {
            ((flaxchat.c.e)a).j(s2);
        }
        final flaxchat.d.f d = this.q.d(s);
        if (d == null) {
            return;
        }
        d.a(s2);
        d.b(s2);
        this.q.b();
    }
    
    public Enumeration u() {
        return this.l.c();
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
    
    public void a(final Object o, final String s, final flaxchat.h.g g, String trim) {
        trim = trim.trim();
        final int index = trim.indexOf(32);
        if (index == -1) {
            this.a(o, s, g, trim, trim);
            return;
        }
        this.a(o, s, g, trim.substring(0, index), trim);
    }
    
    private void a(final Object o, final String s, final flaxchat.h.g g, final String s2, final String s3) {
        c c = this.h.get(s2.toLowerCase());
        if (c == null) {
            c = this.h.get(flaxchat.m.z[0]);
        }
        c.a(o, s, g, s3);
    }
    
    public boolean b(final a a) {
        return this.q.d(a.j()) != null;
    }
    
    public void a(final flaxchat.h.g g) {
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
        return this.p;
    }
    
    public Container w() {
        return this.k;
    }
    
    public void x() {
        final a e = this.e();
        if (e instanceof flaxchat.c.e) {
            ((flaxchat.c.e)e).a();
            return;
        }
        if (e instanceof flaxchat.c.c) {
            ((flaxchat.c.c)e).h();
        }
    }
    
    public void y() {
        int b = this.q.b(this.e());
        if (b == -1) {
            return;
        }
        ++b;
        if (b < this.q.getComponentCount()) {
            this.a(b);
            return;
        }
        this.a(0);
    }
    
    static {
        m.z = new String[] { z(z("Qg{\u001e-Yv")), z(z("vgs\u000b=G")), z(z("fmh\u000b0")), z(z("rms\u001b=G")), z(z("fv|\u000b-F")), z(z("Pcn\u000b")), z(z("[mo\u000b0")), z(z("{mo\u000b0")), z(z("Fmh\u000b0")), z(z("bgn\u000b")), z(z("pcn\u000b")), z(z("Wmi\u000b7X")), z(z("Bgn\u000b")), z(z("Gkz\u0017,")), z(z("Amm")), z(z("Yg{\u000b")), z(z("Vms\u000b*Zn\u007f\u001e*")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'X';
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
                    c2 = '5';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = '\u001d';
                    break;
                }
                case 3: {
                    c2 = '\u007f';
                    break;
                }
                default: {
                    c2 = 'X';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
