// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Observable;
import java.io.IOException;
import java.util.Date;
import java.net.URLEncoder;
import java.util.Vector;
import java.awt.MenuContainer;
import java.awt.CheckboxMenuItem;
import java.awt.Event;
import java.net.URL;
import java.awt.Font;
import java.util.Enumeration;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Button;
import java.awt.List;
import java.awt.Label;
import java.util.Hashtable;
import java.awt.Component;
import java.util.Observer;
import java.awt.Frame;

public class vaf extends Frame implements Observer
{
    private Object a;
    private vc b;
    private Component c;
    private vd d;
    private vao e;
    private String f;
    private int g;
    private String h;
    private String i;
    private Hashtable j;
    private Hashtable k;
    private Hashtable l;
    private Hashtable m;
    private Hashtable n;
    private String[][] o;
    private Label p;
    private Component q;
    private Label r;
    private Label s;
    private Label t;
    private Label u;
    private Label v;
    private List w;
    private vg x;
    private vg y;
    private vg z;
    private vg aa;
    private Label ab;
    private List ac;
    private Button ad;
    private Button ae;
    private Panel af;
    private vag ag;
    private vag ah;
    private vaj ai;
    private vak aj;
    private val ak;
    private vam al;
    private van am;
    
    private static String a(final String s, final int n) {
        String trim = s;
        if (s.length() > n) {
            trim = s.substring(0, n).trim();
        }
        return trim;
    }
    
    public vaf(final vc b, final Component c, final vd d, final String s, final String s2, final String[] array) {
        this.a = new Object();
        this.g = 0;
        this.j = new Hashtable();
        this.k = new Hashtable();
        this.l = new Hashtable();
        this.m = new Hashtable();
        this.n = new Hashtable();
        this.b = b;
        this.c = c;
        this.d = d;
        this.h = ((s.length() == 0) ? d.ai : s);
        this.i = ((s2.length() == 0) ? d.aj : s2);
        this.f = d.ae;
        this.ag = new vag(d.b, d.bt);
        final Insets insets = new Insets(2, 2, 2, 2);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        (this.af = new Panel()).setLayout(layout);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = insets;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 0.0;
        (this.p = new Label()).setAlignment(0);
        layout.setConstraints(this.p, gridBagConstraints);
        this.af.add(this.p);
        gridBagConstraints.gridy = -1;
        gridBagConstraints.fill = 3;
        gridBagConstraints.weighty = 1.0;
        if (d.a6.length() > 0) {
            this.q = new vah(d.a, "logo.param.", d.a6, d.a7, d.a8, d.a9);
        }
        else {
            this.q = new vai();
        }
        layout.setConstraints(this.q, gridBagConstraints);
        this.af.add(this.q);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        if (d.aq) {
            (this.r = new Label()).setAlignment(2);
            layout.setConstraints(this.r, gridBagConstraints);
            this.af.add(this.r);
        }
        (this.s = new Label()).setAlignment(2);
        layout.setConstraints(this.s, gridBagConstraints);
        this.af.add(this.s);
        (this.t = new Label()).setAlignment(2);
        layout.setConstraints(this.t, gridBagConstraints);
        this.af.add(this.t);
        if (d.x) {
            (this.u = new Label()).setAlignment(2);
            layout.setConstraints(this.u, gridBagConstraints);
            this.af.add(this.u);
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.75;
        (this.v = new Label()).setAlignment(0);
        layout.setConstraints(this.v, gridBagConstraints);
        this.af.add(this.v);
        gridBagConstraints.gridy = -1;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.w = new List(), gridBagConstraints);
        this.af.add(this.w);
        gridBagConstraints.fill = 2;
        if (d.aq) {
            this.x = new vg(30);
            this.x.a = d.cc;
            layout.setConstraints(this.x, gridBagConstraints);
            this.af.add(this.x);
        }
        this.y = new vg(30);
        this.y.a = d.cd;
        this.y.setText(this.h);
        this.y.setEditable(d.au);
        layout.setConstraints(this.y, gridBagConstraints);
        this.af.add(this.y);
        if (d.aq) {
            gridBagConstraints.gridwidth = 2;
        }
        this.z = new vg(30);
        this.z.a = d.cb;
        this.z.setText(this.i);
        this.z.setEditable(d.av);
        layout.setConstraints(this.z, gridBagConstraints);
        this.af.add(this.z);
        if (d.x) {
            layout.setConstraints(this.aa = new vg(30), gridBagConstraints);
            this.af.add(this.aa);
        }
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.25;
        (this.ab = new Label()).setAlignment(0);
        layout.setConstraints(this.ab, gridBagConstraints);
        this.af.add(this.ab);
        gridBagConstraints.gridy = -1;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.ac = new List(), gridBagConstraints);
        this.af.add(this.ac);
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.ad = new Button(), gridBagConstraints);
        this.af.add(this.ad);
        layout.setConstraints(this.ae = new Button(), gridBagConstraints);
        this.af.add(this.ae);
        this.setLayout(new BorderLayout(2, 2));
        this.add("Center", this.af);
        if (d.az.length() > 0 || d.ax.length() > 0) {
            this.ah = new vag(d.b, d.bt);
            if (d.az.length() > 0) {
                this.ah.a(d.az, d.a0, d.a1, d.ax, d.ay);
            }
            else {
                this.ah.a(d.ax, d.ay);
            }
            final Panel panel = new Panel();
            panel.setLayout(new BorderLayout());
            panel.add("North", this.ag);
            panel.add("South", this.ah);
            this.add("South", panel);
        }
        else {
            this.add("South", this.ag);
        }
        this.l.put(this.w, this.ac);
        if (this.x != null) {
            this.l.put(this.ac, this.x);
            this.l.put(this.x, this.y);
        }
        else {
            this.l.put(this.ac, this.y);
        }
        this.l.put(this.y, this.z);
        this.l.put(this.z, this.ad);
        this.l.put(this.ad, this.ae);
        this.l.put(this.ae, this.ag);
        this.l.put(this.ag, this.w);
        this.m.put(this.ag, this.ae);
        this.m.put(this.ae, this.ad);
        this.m.put(this.ad, this.z);
        this.m.put(this.z, this.y);
        if (this.x != null) {
            this.m.put(this.y, this.x);
            this.m.put(this.x, this.ac);
        }
        else {
            this.m.put(this.y, this.ac);
        }
        this.m.put(this.ac, this.w);
        this.m.put(this.w, this.ag);
        this.a(d);
        this.a(array);
        this.w.setFont(this.w.getFont());
        d.o = false;
        b.addObserver(this);
    }
    
    private void a(final vd d) {
        this.d = d;
        this.p.setText(d.d5);
        if (this.q instanceof vai) {
            ((vai)this.q).a(d.b3, d.b4, d.b5, d.b6);
        }
        if (this.r != null) {
            this.r.setText(d.ef);
        }
        this.s.setText(d.eg);
        this.t.setText(d.eh);
        if (this.u != null) {
            this.u.setText(d.ei);
        }
        this.v.setText(d.d6);
        this.ab.setText(d.ea);
        this.ad.setLabel(d.ej);
        this.ae.setLabel(d.ek);
        this.ag.a("Copyright (c) 1996-2010 Volano Software");
        final MenuBar menuBar = new MenuBar();
        this.ai = new vaj(d);
        this.aj = new vak(d);
        this.ak = new val(d);
        this.al = new vam(d);
        this.am = new van(d);
        if (d.eq.length() > 0) {
            menuBar.add(this.ai);
        }
        if (d.eu.length() > 0) {
            menuBar.add(this.aj);
        }
        if (d.fp.length() > 0) {
            menuBar.add(this.ak);
        }
        if (d.fm.length() > 0) {
            menuBar.add(this.al);
        }
        if (d.e8.length() > 0) {
            menuBar.add(this.am);
        }
        if (menuBar.countMenus() > 0) {
            this.setMenuBar(menuBar);
        }
        this.aj.a(this.af.getFont());
        this.n.put(this.w, d.f1);
        this.n.put(this.ac, d.f2);
        if (this.x != null) {
            this.n.put(this.x, d.f3);
        }
        this.n.put(this.y, d.f4);
        this.n.put(this.z, d.f5);
        this.n.put(this.ad, d.f6);
        this.n.put(this.ae, d.f7);
        this.n.put(this.ag, "Copyright (c) 1996-2010 Volano Software");
        this.a(d.a(0));
        this.ag.h = true;
    }
    
    public void show() {
        super.show();
        this.y.requestFocus();
    }
    
    public void requestFocus() {
        super.requestFocus();
        this.y.requestFocus();
    }
    
    private void a() {
        synchronized (this.a) {
            this.ai.a.disable();
            this.ai.b.disable();
            this.w.disable();
            this.ad.disable();
            this.ae.disable();
            if (this.x != null) {
                this.x.disable();
            }
            this.y.disable();
            this.z.disable();
        }
        // monitorexit(this.a)
    }
    
    private void b() {
        synchronized (this.a) {
            this.ai.a.enable();
            this.ai.b.enable();
            this.w.enable();
            this.ad.enable();
            this.ae.enable();
            if (this.x != null) {
                this.x.enable();
            }
            this.y.enable();
            this.z.enable();
            this.w.setFont(this.w.getFont());
        }
        // monitorexit(this.a)
    }
    
    public void a(final vao vao) {
        try {
            this.e = (vao)vao.clone();
        }
        catch (CloneNotSupportedException ex) {}
        vao.a(this, vao);
        this.a(this.j, vao);
        this.a(this.k, vao);
        this.ak.a(vao.n);
        this.aj.a(vao.m);
    }
    
    private void a(final Hashtable hashtable, final vao vao) {
        synchronized (hashtable) {
            final Enumeration<vap> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(vao);
            }
        }
    }
    
    private void a(final String s) {
        final Font m = this.e.m;
        vao.a(this, this.e.m = new Font(s, m.getStyle(), m.getSize()));
        this.a(this.j, s);
        this.a(this.k, s);
        this.aj.a(s);
    }
    
    private void a(final Hashtable hashtable, final String s) {
        synchronized (hashtable) {
            final Enumeration<vap> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(s);
            }
        }
    }
    
    private void a(final int n) {
        final Font m = this.e.m;
        vao.a(this, this.e.m = new Font(m.getName(), n, m.getSize()));
        this.a(this.j, n);
        this.a(this.k, n);
        this.aj.a(n);
    }
    
    private void a(final Hashtable hashtable, final int n) {
        synchronized (hashtable) {
            final Enumeration<vap> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(n);
            }
        }
    }
    
    private void c() {
        final Font m = this.e.m;
        vao.a(this, this.e.m = new Font(m.getName(), m.getStyle(), m.getSize() + 1));
        this.a(this.j);
        this.a(this.k);
    }
    
    private void a(final Hashtable hashtable) {
        synchronized (hashtable) {
            final Enumeration<vap> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a();
            }
        }
    }
    
    private void d() {
        final Font m = this.e.m;
        vao.a(this, this.e.m = new Font(m.getName(), m.getStyle(), m.getSize() - 1));
        this.b(this.j);
        this.b(this.k);
    }
    
    private void b(final Hashtable hashtable) {
        synchronized (hashtable) {
            final Enumeration<vap> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().b();
            }
        }
    }
    
    private void a(final boolean b) {
        synchronized (this.j) {
            final Enumeration<va2> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().b(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void b(final boolean b) {
        synchronized (this.j) {
            final Enumeration<va2> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().c(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void c(final boolean b) {
        synchronized (this.j) {
            final Enumeration<va2> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().d(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void d(final boolean b) {
        synchronized (this.j) {
            final Enumeration<va2> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().e(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void e(final boolean b) {
        synchronized (this.j) {
            final Enumeration<va2> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().f(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void c(final Hashtable hashtable) {
        synchronized (hashtable) {
            final Enumeration<vap> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().c();
            }
        }
    }
    
    private void e() {
        this.ag.a(this.d.gx);
        this.c(this.k);
        this.c(this.j);
        final URL url = this.d.o ? this.d.c4 : this.d.c3;
        this.b.d();
        this.dispose();
        this.d.h.a(1);
        if (url != null && this.d.a.isActive()) {
            System.out.println("See " + url);
            if (this.d.cs) {
                this.d.b.showDocument(url, "_blank");
                return;
            }
            this.d.b.showDocument(url);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 9) {
            if (event.shiftDown()) {
                this.a(this.m, (Component)event.target);
            }
            else {
                this.a(this.l, (Component)event.target);
            }
            return true;
        }
        if (n == 10 || n == 13) {
            if (event.target == this.x && this.x != null && this.x.isEnabled()) {
                this.f();
            }
            else if (event.target == this.ad && this.ad.isEnabled()) {
                this.f();
            }
            else if (event.target == this.aa && this.aa.isEnabled()) {
                this.g();
            }
            else if (this.ae.isEnabled()) {
                this.h();
            }
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.ae || event.target == this.ai.b) {
            this.h();
            return true;
        }
        if (event.target == this.ad || event.target == this.ai.a) {
            this.f();
            return true;
        }
        if (event.target == this.ai.c) {
            event.id = 201;
            ((vaf)(event.target = this)).c(event);
            return true;
        }
        if (event.target == this.am.a) {
            this.d.b.showDocument(this.d.c1, "_blank");
            return true;
        }
        if (event.target == this.am.b) {
            this.d.b.showDocument(this.d.c2, "_blank");
            return true;
        }
        if (event.target == this.aj.i) {
            this.a(this.d.i = this.aj.i.getState());
            return true;
        }
        if (event.target == this.aj.j) {
            this.b(this.d.j = this.aj.j.getState());
            return true;
        }
        if (event.target == this.aj.k) {
            this.c(this.d.k = this.aj.k.getState());
            return true;
        }
        if (event.target == this.aj.l) {
            this.d(this.d.l = this.aj.l.getState());
            return true;
        }
        if (event.target == this.aj.m) {
            this.e(this.d.m = this.aj.m.getState());
            return true;
        }
        if (event.target == this.aj.g) {
            this.c();
            return true;
        }
        if (event.target == this.aj.h) {
            this.d();
            return true;
        }
        if (this.al.a(event.target)) {
            final URL b = this.al.b(event.target);
            if (b != null) {
                this.d.b.showDocument(b, "_blank");
            }
            return true;
        }
        if (event.target instanceof CheckboxMenuItem) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)event.target;
            final MenuContainer parent = checkboxMenuItem.getParent();
            if (parent == this.aj.a) {
                this.a(checkboxMenuItem.getLabel());
            }
            else if (parent == this.aj.b) {
                this.a(this.aj.a(checkboxMenuItem));
            }
            else if (parent == this.ak) {
                this.a(this.d.a(this.ak.a((Object)checkboxMenuItem)));
            }
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        boolean c = false;
        if (event.id == 201) {
            c = this.c(event);
        }
        else if (event.target == this.w && event.id == 701) {
            this.a(event);
            c = true;
        }
        else if (event.target == this.ac && event.id == 701) {
            this.b(event);
            c = true;
        }
        return c || super.handleEvent(event);
    }
    
    private void f() {
        this.ag.a(this.d.gh);
        this.a();
        if (this.x != null) {
            this.a(new vac(this.x.getText().trim()));
        }
        else {
            this.a(new vac());
        }
        this.d.h.a(4);
    }
    
    private void g() {
        final String trim = this.aa.getText().trim();
        if (trim.length() != 0) {
            this.aa.setText("");
            this.a(new vu(trim));
        }
    }
    
    private boolean a(final String s, final String s2) {
        boolean b = false;
        if (s.length() == 0) {
            this.ag.a(this.d.ga);
        }
        else if (s2.length() == 0) {
            this.ag.a(vaq.a(this.d.gb, this.f));
        }
        else {
            final va2 va2 = this.j.get(s);
            if (va2 != null) {
                this.ag.a(vaq.a(this.d.go, s));
                va2.requestFocus();
            }
            else if (this.d.an != -1 && this.j.size() >= this.d.an) {
                this.ag.a(vaq.a(this.d.gt, new Integer(this.d.an)));
            }
            else {
                b = true;
            }
        }
        return b;
    }
    
    private void h() {
        final String f = this.f;
        String s = this.h;
        if (s.length() == 0 || this.d.au) {
            s = a(this.y.getText().replace(' ', ' ').trim(), this.d.cd);
        }
        if (this.a(f, s)) {
            this.ag.a(vaq.a(this.d.gk, f));
            String s2 = this.i;
            if (s2.length() == 0 || this.d.av) {
                s2 = a(this.z.getText().trim(), this.d.cb);
            }
            this.a();
            this.a(new vx(f, s, s2));
        }
    }
    
    private void a(final Event event) {
        this.a(this.ac);
        this.ag.a(this.d.gi);
        this.f = this.w.getItem((int)event.arg);
        this.a();
        this.a(new vad(this.f));
        this.d.h.a(5);
    }
    
    private void a(final vd vd, final String[] array) {
        if (vd.at.length() > 0 && Boolean.valueOf(array[3]) && Boolean.valueOf(array[4])) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(URLEncoder.encode(array[0]));
            vector.addElement(URLEncoder.encode(this.h));
            this.ag.a(vap.a(vd, array), vd.b9, vaq.a(vd.at, vector), vd.bv, vd.bu);
            return;
        }
        this.ag.a(vap.a(vd, array), vd.bv, vd.bu);
    }
    
    private void b(final Event event) {
        this.a(this.d, this.o[(int)event.arg]);
        this.d.h.a(6);
    }
    
    private boolean c(final Event event) {
        if (event.target == this) {
            this.e();
            this.c.deliverEvent(event);
            return true;
        }
        if (event.target instanceof va2) {
            this.j.remove(((va2)event.target).a);
            this.d.h.a(3);
            return true;
        }
        if (event.target instanceof va3) {
            this.k.remove(new Integer(((va3)event.target).a));
            this.d.h.a(3);
            return true;
        }
        return false;
    }
    
    private void a(final vp vp) {
        try {
            this.b.a(vp);
        }
        catch (IOException ex) {
            this.ag.a(vaq.a(this.d.g2, new Date()));
            this.b.deleteObserver(this);
            this.d.o = true;
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final vc vc = (vc)observable;
        if (o instanceof vp) {
            final vp vp = (vp)o;
            if (!vp.f) {
                switch (vp.e) {
                    case 4: {
                        if (vp instanceof vac) {
                            this.a(vc, (vac)vp);
                            return;
                        }
                        if (vp instanceof vad) {
                            this.a(vc, (vad)vp);
                            return;
                        }
                        if (vp instanceof vx) {
                            this.a(vc, (vx)vp);
                            return;
                        }
                        if (vp instanceof vw) {
                            this.a(vc, (vw)vp);
                            return;
                        }
                        break;
                    }
                    case 2: {
                        if (vp instanceof vw) {
                            this.a(vc, (vw)vp);
                            return;
                        }
                        break;
                    }
                }
            }
        }
        else {
            if (o instanceof vas) {
                this.a(vc, (vas)o);
                return;
            }
            if (o == null) {
                this.a(vc);
            }
        }
    }
    
    private void a(final vc vc, final vac vac) {
        this.a(vac.d);
        this.b();
        this.i();
    }
    
    private void a(final vc vc, final vad vad) {
        final int d = vad.d;
        this.b();
        if (d != 1) {
            if (d == 2) {
                this.ab.setText(this.d.ea);
                this.ag.a(this.d.gm);
            }
            return;
        }
        this.a(this.g = vad.e, vad.g);
        final String f = vad.f;
        if (f.startsWith("http://") || this.g == 2) {
            this.ag.a(f, this.d.bx, this.d.bw);
            return;
        }
        this.i();
    }
    
    private void a(final vc vc, final vx vx) {
        final String e = vx.e();
        final String d = vx.d;
        final String e2 = vx.e;
        final int i = vx.i;
        this.b();
        if (i == 1) {
            this.a(this.g = vx.j, vx.l);
            this.a(String.valueOf(e) + " (" + d + ")", e, this.g, vx.k, d, e2, vx.l);
            this.i();
            return;
        }
        if (i == 2) {
            this.ag.a(vaq.a(this.d.gr, e));
            return;
        }
        if (i == 3) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(d);
            vector.addElement(e);
            this.ag.a(vaq.a(this.d.gp, vector));
            return;
        }
        if (i == 4) {
            this.ag.a(vaq.a(this.d.gq, d));
            return;
        }
        if (i == 5) {
            this.ab.setText(this.d.ea);
            this.ag.a(this.d.gm);
        }
    }
    
    private void a(final vc vc, final vw vw) {
        final int f = vw.f();
        final va3 va3 = this.k.get(new Integer(f));
        if (va3 != null) {
            va3.requestFocus();
            return;
        }
        final String f2 = vw.f;
        final String e = vw.e;
        final va2 va4 = this.j.get(vw.e());
        if (va4 != null) {
            if (vw.e == 2) {
                final String[] f3 = va4.f(e);
                if (f3 == null || !va4.e() || va4.d(e) || (this.d.ao != -1 && this.k.size() >= this.d.ao)) {
                    this.a(new vy(f, f2));
                    return;
                }
                this.a(String.valueOf(e) + " (" + f2 + ")", f, f2, f3);
            }
            else {
                final String[] f4 = va4.f(f2);
                if (f4 == null) {
                    this.a(new vy(f, e));
                    return;
                }
                this.a(String.valueOf(f2) + " (" + e + ")", f, e, f4);
            }
        }
    }
    
    private void a(final vc vc, final vas vas) {
        vas.d();
        this.ag.a("Server error (" + vas.a + ").");
        this.b();
    }
    
    private void a(final vc vc) {
        this.ag.a(vaq.a(this.d.g2, new Date()));
        vc.deleteObserver(this);
        this.a();
        this.d.o = true;
    }
    
    private void a(final String[] array) {
        this.a(this.ac);
        this.a(this.w);
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            this.w.addItem(array[i]);
            if (n == 0 && this.f.equals(array[i])) {
                n = 1;
                n2 = i;
            }
        }
        switch (array.length) {
            case 0: {
                this.v.setText(this.d.d7);
                break;
            }
            case 1: {
                this.v.setText(this.d.d8);
                break;
            }
            default: {
                this.v.setText(vaq.a(this.d.d9, new Integer(array.length)));
                break;
            }
        }
        this.ab.setText(this.d.ea);
        if (n != 0) {
            this.w.select(n2);
            return;
        }
        this.f = "";
    }
    
    private void a(final int n, final String[][] o) {
        this.o = o;
        this.a(this.ac);
        for (int i = 0; i < this.o.length; ++i) {
            this.ac.addItem(this.o[i][0]);
        }
        if (n == 2) {
            this.ab.setText(this.d.ee);
            return;
        }
        switch (o.length) {
            case 0: {
                this.ab.setText(this.d.eb);
            }
            case 1: {
                this.ab.setText(this.d.ec);
            }
            default: {
                this.ab.setText(vaq.a(this.d.ed, new Integer(o.length)));
            }
        }
    }
    
    private void a(final List list) {
        final int countItems = list.countItems();
        if (countItems > 0) {
            if (this.d.r.equals("1.021") && this.d.p.startsWith("Netscape")) {
                list.delItems(0, countItems - 1);
                return;
            }
            list.clear();
        }
    }
    
    private void a(final String s, final String s2, final int n, final int n2, final String s3, final String s4, final String[][] array) {
        final va2 va2 = new va2(this.b, this, this.d, array, s2, n, n2, s3, s4, this.k);
        this.j.put(s2, va2);
        this.a(va2, s);
    }
    
    private void a(final String s, final int n, final String s2, final String[] array) {
        final va3 va3 = new va3(this.b, this, this.d, n, s2, array);
        this.k.put(new Integer(n), va3);
        this.a(va3, s);
    }
    
    private void a(final vap vap, final String title) {
        vap.setTitle(title);
        vap.a(this.e);
        vap.a(true);
        vap.pack();
        vap.show();
        vap.requestFocus();
        this.d.h.a(2);
    }
    
    private void i() {
        if (this.f.length() == 0) {
            this.ag.a(this.d.ga);
            return;
        }
        if (this.y.getText().replace(' ', ' ').trim().length() == 0) {
            this.ag.a(vaq.a(this.d.gb, this.f));
            return;
        }
        this.ag.a(vaq.a(this.d.gg, this.f));
    }
    
    private void a(final Hashtable hashtable, final Component component) {
        Component y = hashtable.get(component);
        if (y == null) {
            y = this.y;
        }
        this.ag.a((String)this.n.get(y));
        y.requestFocus();
    }
}
