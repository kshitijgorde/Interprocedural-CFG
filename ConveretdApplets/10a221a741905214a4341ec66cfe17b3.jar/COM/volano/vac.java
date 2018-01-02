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

public class vac extends Frame implements Observer
{
    private Object a;
    private vc b;
    private Component c;
    private vd d;
    private val e;
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
    private vad ag;
    private vad ah;
    private vag ai;
    private vah aj;
    private vai ak;
    private vaj al;
    private vak am;
    
    private static String a(final String s, final int n) {
        String trim = s;
        if (s.length() > n) {
            trim = s.substring(0, n).trim();
        }
        return trim;
    }
    
    public vac(final vc b, final Component c, final vd d, final String s, final String s2, final String[] array) {
        this.a = new Object();
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
        this.ag = new vad(d.b, d.b2);
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
        if (d.a2.length() > 0) {
            this.q = new vae(d.a, "logo.param.", d.a2, d.a3, d.a4, d.a5);
        }
        else {
            this.q = new vaf();
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
            this.x.a = d.cl;
            layout.setConstraints(this.x, gridBagConstraints);
            this.af.add(this.x);
        }
        this.y = new vg(30);
        this.y.a = d.cm;
        this.y.setText(this.h);
        this.y.setEditable(d.at);
        layout.setConstraints(this.y, gridBagConstraints);
        this.af.add(this.y);
        if (d.aq) {
            gridBagConstraints.gridwidth = 2;
        }
        this.z = new vg(30);
        this.z.a = d.ck;
        this.z.setText(this.i);
        this.z.setEditable(d.au);
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
        if (d.aw.length() > 0) {
            (this.ah = new vad(d.b, d.b2)).a(d.aw, d.ax);
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
        b.addObserver(this);
    }
    
    private void a(final vd d) {
        this.d = d;
        this.p.setText(d.ed);
        if (this.q instanceof vaf) {
            ((vaf)this.q).a(d.cc, d.cd, d.ce, d.cf);
        }
        if (this.r != null) {
            this.r.setText(d.en);
        }
        this.s.setText(d.eo);
        this.t.setText(d.ep);
        if (this.u != null) {
            this.u.setText(d.eq);
        }
        this.v.setText(d.ee);
        this.ab.setText(d.ei);
        this.ad.setLabel(d.er);
        this.ae.setLabel(d.es);
        this.ag.a("Copyright (c) 1996-2004 Volano LLC.  All rights reserved.");
        final MenuBar menuBar = new MenuBar();
        this.ai = new vag(d);
        this.aj = new vah(d);
        this.ak = new vai(d);
        this.al = new vaj(d);
        this.am = new vak(d);
        if (d.ey.length() > 0) {
            menuBar.add(this.ai);
        }
        if (d.e2.length() > 0) {
            menuBar.add(this.aj);
        }
        if (d.fx.length() > 0) {
            menuBar.add(this.ak);
        }
        if (d.fu.length() > 0) {
            menuBar.add(this.al);
        }
        if (d.fg.length() > 0) {
            menuBar.add(this.am);
        }
        if (menuBar.countMenus() > 0) {
            this.setMenuBar(menuBar);
        }
        this.aj.a(this.af.getFont());
        this.n.put(this.w, d.f8);
        this.n.put(this.ac, d.f9);
        if (this.x != null) {
            this.n.put(this.x, d.ga);
        }
        this.n.put(this.y, d.gb);
        this.n.put(this.z, d.gc);
        this.n.put(this.ad, d.gd);
        this.n.put(this.ae, d.ge);
        this.n.put(this.ag, "Copyright (c) 1996-2004 Volano LLC.  All rights reserved.");
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
    
    private void b() {
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
    
    private void c() {
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
    
    public void a(final val val) {
        try {
            this.e = (val)val.clone();
        }
        catch (CloneNotSupportedException ex) {}
        val.a(this, val);
        this.a(this.j, val);
        this.a(this.k, val);
        this.ak.a(val.n);
        this.aj.a(val.m);
    }
    
    private void a(final Hashtable hashtable, final val val) {
        synchronized (hashtable) {
            final Enumeration<vam> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(val);
            }
        }
    }
    
    private void a(final String s) {
        final Font m = this.e.m;
        val.a(this, this.e.m = new Font(s, m.getStyle(), m.getSize()));
        this.a(this.j, s);
        this.a(this.k, s);
        this.aj.a(s);
    }
    
    private void a(final Hashtable hashtable, final String s) {
        synchronized (hashtable) {
            final Enumeration<vam> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(s);
            }
        }
    }
    
    private void a(final int n) {
        final Font m = this.e.m;
        val.a(this, this.e.m = new Font(m.getName(), n, m.getSize()));
        this.a(this.j, n);
        this.a(this.k, n);
        this.aj.a(n);
    }
    
    private void a(final Hashtable hashtable, final int n) {
        synchronized (hashtable) {
            final Enumeration<vam> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(n);
            }
        }
    }
    
    private void d() {
        final Font m = this.e.m;
        val.a(this, this.e.m = new Font(m.getName(), m.getStyle(), m.getSize() + 1));
        this.a(this.j);
        this.a(this.k);
    }
    
    private void a(final Hashtable hashtable) {
        synchronized (hashtable) {
            final Enumeration<vam> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a();
            }
        }
    }
    
    private void e() {
        final Font m = this.e.m;
        val.a(this, this.e.m = new Font(m.getName(), m.getStyle(), m.getSize() - 1));
        this.b(this.j);
        this.b(this.k);
    }
    
    private void b(final Hashtable hashtable) {
        synchronized (hashtable) {
            final Enumeration<vam> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().b();
            }
        }
    }
    
    private void a(final boolean b) {
        synchronized (this.j) {
            final Enumeration<vau> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().b(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void b(final boolean b) {
        synchronized (this.j) {
            final Enumeration<vau> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().c(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void c(final boolean b) {
        synchronized (this.j) {
            final Enumeration<vau> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().d(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void d(final boolean b) {
        synchronized (this.j) {
            final Enumeration<vau> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().e(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void e(final boolean b) {
        synchronized (this.j) {
            final Enumeration<vau> elements = this.j.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().f(b);
            }
        }
        // monitorexit(this.j)
    }
    
    private void c(final Hashtable hashtable) {
        synchronized (hashtable) {
            final Enumeration<vam> elements = hashtable.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().c();
            }
        }
    }
    
    public void a() {
        this.ag.a(this.d.g4);
        this.c(this.k);
        this.c(this.j);
        this.b.d();
        this.dispose();
        this.d.h.a(1);
        final URL url = this.d.o ? this.d.dd : this.d.dc;
        if (url != null) {
            if (this.d.c1) {
                this.d.b.showDocument(url, "_blank");
            }
            else {
                this.d.b.showDocument(url);
            }
        }
        this.d.o = false;
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
            ((vac)(event.target = this)).c(event);
            return true;
        }
        if (event.target == this.am.a) {
            this.d.b.showDocument(this.d.da, "_blank");
            return true;
        }
        if (event.target == this.am.b) {
            this.d.b.showDocument(this.d.db, "_blank");
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
            this.d();
            return true;
        }
        if (event.target == this.aj.h) {
            this.e();
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
        this.ag.a(this.d.go);
        this.b();
        if (this.x != null) {
            this.a(new vz(this.x.getText().trim()));
        }
        else {
            this.a(new vz());
        }
        this.d.h.a(4);
    }
    
    private void g() {
        final String trim = this.aa.getText().trim();
        if (trim.length() != 0) {
            this.aa.setText("");
            this.a(new vr(trim));
        }
    }
    
    private boolean a(final String s, final String s2) {
        boolean b = false;
        if (s.length() == 0) {
            this.ag.a(this.d.gh);
        }
        else if (s2.length() == 0) {
            this.ag.a(van.a(this.d.gi, this.f));
        }
        else {
            final vau vau = this.j.get(s);
            if (vau != null) {
                this.ag.a(van.a(this.d.gv, s));
                vau.requestFocus();
            }
            else if (this.d.an != -1 && this.j.size() >= this.d.an) {
                this.ag.a(van.a(this.d.g0, new Integer(this.d.an)));
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
        if (s.length() == 0 || this.d.at) {
            s = a(this.y.getText().replace(' ', ' ').trim(), this.d.cm);
        }
        if (this.a(f, s)) {
            this.ag.a(van.a(this.d.gr, f));
            String s2 = this.i;
            if (s2.length() == 0 || this.d.au) {
                s2 = a(this.z.getText().trim(), this.d.ck);
            }
            this.b();
            this.a(new vu(f, s, s2));
        }
    }
    
    private void a(final Event event) {
        this.a(this.ac);
        this.ag.a(this.d.gp);
        this.f = this.w.getItem((int)event.arg);
        this.b();
        this.a(new vaa(this.f));
        this.d.h.a(5);
    }
    
    private void a(final vd vd, final String[] array) {
        if (vd.as.length() > 0 && Boolean.valueOf(array[3]) && Boolean.valueOf(array[4])) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(URLEncoder.encode(array[0]));
            vector.addElement(URLEncoder.encode(this.h));
            this.ag.a(vam.a(vd, array), vd.ci, van.a(vd.as, vector), vd.b4, vd.b3);
            return;
        }
        this.ag.a(vam.a(vd, array), vd.b4, vd.b3);
    }
    
    private void b(final Event event) {
        this.a(this.d, this.o[(int)event.arg]);
        this.d.h.a(6);
    }
    
    private boolean c(final Event event) {
        if (event.target == this) {
            this.a();
            this.c.deliverEvent(event);
            return true;
        }
        if (event.target instanceof vau) {
            this.j.remove(((vau)event.target).a);
            this.d.h.a(3);
            return true;
        }
        if (event.target instanceof vav) {
            this.k.remove(new Integer(((vav)event.target).a));
            this.d.h.a(3);
            return true;
        }
        return false;
    }
    
    private void a(final vm vm) {
        try {
            this.b.a(vm);
        }
        catch (IOException ex) {
            this.ag.a(van.a(this.d.g9, new Date()));
            this.b.deleteObserver(this);
            this.d.o = true;
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final vc vc = (vc)observable;
        if (o instanceof vm) {
            final vm vm = (vm)o;
            if (!vm.f) {
                switch (vm.e) {
                    case 4: {
                        if (vm instanceof vz) {
                            this.a(vc, (vz)vm);
                            return;
                        }
                        if (vm instanceof vaa) {
                            this.a(vc, (vaa)vm);
                            return;
                        }
                        if (vm instanceof vu) {
                            this.a(vc, (vu)vm);
                            return;
                        }
                        if (vm instanceof vt) {
                            this.a(vc, (vt)vm);
                            return;
                        }
                        break;
                    }
                    case 2: {
                        if (vm instanceof vt) {
                            this.a(vc, (vt)vm);
                            return;
                        }
                        break;
                    }
                }
            }
        }
        else {
            if (o instanceof vap) {
                this.a(vc, (vap)o);
                return;
            }
            if (o == null) {
                this.a(vc);
            }
        }
    }
    
    private void a(final vc vc, final vz vz) {
        this.a(vz.d);
        this.c();
        this.i();
    }
    
    private void a(final vc vc, final vaa vaa) {
        final int d = vaa.d;
        this.c();
        if (d != 1) {
            if (d == 2) {
                this.ab.setText(this.d.ei);
                this.ag.a(this.d.gt);
            }
            return;
        }
        this.a(this.g = vaa.e, vaa.g);
        final String f = vaa.f;
        if (f.startsWith("http://") || this.g == 2) {
            this.ag.a(f, this.d.b6, this.d.b5);
            return;
        }
        this.i();
    }
    
    private void a(final vc vc, final vu vu) {
        final String e = vu.e();
        final String d = vu.d;
        final String e2 = vu.e;
        final int i = vu.i;
        this.c();
        if (i == 1) {
            this.a(this.g = vu.j, vu.l);
            this.a(e + " (" + d + ")", e, this.g, vu.k, d, e2, vu.l);
            this.i();
            return;
        }
        if (i == 2) {
            this.ag.a(van.a(this.d.gy, e));
            return;
        }
        if (i == 3) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(d);
            vector.addElement(e);
            this.ag.a(van.a(this.d.gw, vector));
            return;
        }
        if (i == 4) {
            this.ag.a(van.a(this.d.gx, d));
            return;
        }
        if (i == 5) {
            this.ab.setText(this.d.ei);
            this.ag.a(this.d.gt);
        }
    }
    
    private void a(final vc vc, final vt vt) {
        final int f = vt.f();
        final vav vav = this.k.get(new Integer(f));
        if (vav != null) {
            vav.requestFocus();
            return;
        }
        final String f2 = vt.f;
        final String e = vt.e;
        final vau vau = this.j.get(vt.e());
        if (vau != null) {
            if (vt.e == 2) {
                final String[] f3 = vau.f(e);
                if (f3 == null || !vau.e() || vau.d(e) || (this.d.ao != -1 && this.k.size() >= this.d.ao)) {
                    this.a(new vv(f, f2));
                    return;
                }
                this.a(e + " (" + f2 + ")", f, f2, f3);
            }
            else {
                final String[] f4 = vau.f(f2);
                if (f4 == null) {
                    this.a(new vv(f, e));
                    return;
                }
                this.a(f2 + " (" + e + ")", f, e, f4);
            }
        }
    }
    
    private void a(final vc vc, final vap vap) {
        vap.d();
        this.ag.a("Server error (" + vap.a + ").");
        this.c();
    }
    
    private void a(final vc vc) {
        this.ag.a(van.a(this.d.g9, new Date()));
        vc.deleteObserver(this);
        this.b();
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
                this.v.setText(this.d.ef);
                break;
            }
            case 1: {
                this.v.setText(this.d.eg);
                break;
            }
            default: {
                this.v.setText(van.a(this.d.eh, new Integer(array.length)));
                break;
            }
        }
        this.ab.setText(this.d.ei);
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
            this.ab.setText(this.d.em);
            return;
        }
        switch (o.length) {
            case 0: {
                this.ab.setText(this.d.ej);
            }
            case 1: {
                this.ab.setText(this.d.ek);
            }
            default: {
                this.ab.setText(van.a(this.d.el, new Integer(o.length)));
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
        final vau vau = new vau(this.b, this, this.d, array, s2, n, n2, s3, s4, this.k);
        this.j.put(s2, vau);
        this.a(vau, s);
    }
    
    private void a(final String s, final int n, final String s2, final String[] array) {
        final vav vav = new vav(this.b, this, this.d, n, s2, array);
        this.k.put(new Integer(n), vav);
        this.a(vav, s);
    }
    
    private void a(final vam vam, final String title) {
        vam.setTitle(title);
        vam.a(this.e);
        vam.a(true);
        vam.pack();
        vam.show();
        vam.requestFocus();
        this.d.h.a(2);
    }
    
    private void i() {
        if (this.f.length() == 0) {
            this.ag.a(this.d.gh);
            return;
        }
        if (this.y.getText().replace(' ', ' ').trim().length() == 0) {
            this.ag.a(van.a(this.d.gi, this.f));
            return;
        }
        this.ag.a(van.a(this.d.gn, this.f));
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
