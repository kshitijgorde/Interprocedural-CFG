// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Date;
import java.util.Observable;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.MenuContainer;
import java.net.URL;
import java.awt.CheckboxMenuItem;
import java.awt.Event;
import java.awt.TextComponent;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.List;
import java.util.Vector;
import java.util.Hashtable;

public class vau extends vam
{
    public String a;
    private int b;
    private int c;
    private String d;
    private String e;
    private Hashtable f;
    private Vector g;
    private Vector h;
    private Vector i;
    private Object j;
    private int k;
    private List l;
    private List m;
    private vax n;
    private vah o;
    private vay p;
    private vaz q;
    private vai r;
    private vaj s;
    private vak t;
    
    public vau(final vc vc, final Component component, final vd vd, final String[][] array, final String a, final int b, final int c, final String d, final String e, final Hashtable f) {
        super(vc, component, vd);
        this.g = new Vector(30);
        this.h = new Vector(30);
        this.i = new Vector(30);
        this.j = new Object();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.l = new List();
        for (int i = 0; i < array.length; ++i) {
            this.l.addItem(array[i][0]);
            this.g.addElement(array[i][0]);
            this.h.addElement(array[i]);
            this.i.insertElementAt(array[i], 0);
        }
        if (this.i.size() > vd.b1) {
            this.i.setSize(vd.b1);
        }
        if (b == 1 || vd.z) {
            super.k.a(vd.ev);
        }
        else {
            super.k.a(vd.ew);
        }
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", super.j);
        if (vd.x && vd.z && b == 2) {
            panel.add("Center", this.m = new List(10, false));
        }
        if (vd.aw.length() > 0) {
            (super.l = new vad(vd.b, vd.b2)).a(vd.aw, vd.ax);
            final Panel panel2 = new Panel();
            panel2.setLayout(new BorderLayout());
            panel2.add("North", super.k);
            panel2.add("South", super.l);
            panel.add("South", panel2);
        }
        else {
            panel.add("South", super.k);
        }
        this.setLayout(new BorderLayout());
        if (super.h != null) {
            this.add("North", super.h);
        }
        this.add("Center", super.i);
        this.add("South", panel);
        this.add("East", this.l);
        final MenuBar menuBar = new MenuBar();
        this.n = new vax(vd);
        this.o = new vah(vd);
        this.p = new vay(vd);
        this.q = new vaz(vd);
        this.r = new vai(vd);
        this.s = new vaj(vd);
        this.t = new vak(vd);
        if (vd.fj.length() > 0) {
            menuBar.add(this.n);
        }
        if (vd.e2.length() > 0) {
            menuBar.add(this.o);
        }
        if (vd.fl.length() > 0 && (b == 1 || vd.z)) {
            menuBar.add(this.p);
        }
        if (vd.fq.length() > 0 && (vd.n || vd.x)) {
            menuBar.add(this.q);
        }
        if (vd.fx.length() > 0) {
            menuBar.add(this.r);
        }
        if (vd.fu.length() > 0) {
            menuBar.add(this.s);
        }
        if (vd.fg.length() > 0) {
            menuBar.add(this.t);
        }
        if (menuBar.countMenus() > 0) {
            this.setMenuBar(menuBar);
        }
        super.f.put(super.i, this.l);
        super.f.put(this.l, super.j);
        super.f.put(super.j, super.k);
        super.f.put(super.k, super.i);
        super.g.put(super.k, super.j);
        super.g.put(super.j, this.l);
        super.g.put(this.l, super.i);
        super.g.put(super.i, super.k);
    }
    
    public boolean e() {
        return this.o.i.getState();
    }
    
    public boolean d(final String s) {
        return this.p.a(s);
    }
    
    public void c() {
        super.c();
        this.a(new vw(this.a, this.d));
    }
    
    public void a(final val val) {
        super.a(val);
        synchronized (this.j) {
            if (this.k > super.c.c0) {
                val.b(super.j, val);
            }
        }
        // monitorexit(this.j)
        this.r.a(val.n);
        this.o.a(val.m);
    }
    
    public void a(final String s) {
        super.a(s);
        this.o.a(s);
    }
    
    public void a(final int n) {
        super.a(n);
        this.o.a(n);
    }
    
    public void b(final boolean state) {
        this.o.i.setState(state);
    }
    
    public void c(final boolean state) {
        this.o.j.setState(state);
    }
    
    public void d(final boolean state) {
        this.o.k.setState(state);
    }
    
    public void e(final boolean state) {
        this.o.l.setState(state);
    }
    
    public void f(final boolean state) {
        this.o.m.setState(state);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.l) {
            if (this.b == 1 || super.c.z) {
                this.g((String)o);
            }
            return true;
        }
        if (event.target == this.m) {
            super.j.setText((String)o);
            super.j.requestFocus();
            return true;
        }
        if (event.target == this.n.a) {
            event.id = 201;
            event.target = this;
            return this.a(event);
        }
        if (event.target == this.p.e) {
            this.a(new vp(this.a, this.d, this.p.c));
            return true;
        }
        if (event.target == this.p.f) {
            this.p.a();
            return true;
        }
        if (event.target == this.p.g) {
            super.k.a(van.a(super.c.gz, new Integer(this.c)));
            return true;
        }
        if (event.target == this.q.a) {
            this.a(super.c.f0, this.a(this.h), 1);
            return true;
        }
        if (event.target == this.q.b) {
            this.a(super.c.f1, this.a(this.i), 2);
            return true;
        }
        if (event.target == this.q.c) {
            this.a(super.c.f2, this.a(this.i), 3);
            return true;
        }
        if (this.s.a(event.target)) {
            final URL b = this.s.b(event.target);
            if (b != null) {
                super.c.b.showDocument(b, "_blank");
            }
            return true;
        }
        if (event.target == this.t.a) {
            super.c.b.showDocument(super.c.da, "_blank");
            return true;
        }
        if (event.target == this.t.b) {
            super.c.b.showDocument(super.c.db, "_blank");
            return true;
        }
        if (event.target == this.o.g) {
            this.a();
            return true;
        }
        if (event.target == this.o.h) {
            this.b();
            return true;
        }
        if (event.target instanceof CheckboxMenuItem) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)event.target;
            final MenuContainer parent = checkboxMenuItem.getParent();
            if (parent == this.o.a) {
                this.a(checkboxMenuItem.getLabel());
            }
            else if (parent == this.o.b) {
                this.a(this.o.a(checkboxMenuItem));
            }
            else if (parent == this.r) {
                this.a(super.c.a(this.r.a((Object)checkboxMenuItem)));
            }
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.l && event.id == 701) {
            this.b(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void c(String s) {
        if (this.k <= super.c.c0 && super.e && s.trim().length() != 0) {
            if (this.b == 1 || super.c.z) {
                s = this.a(s, true);
            }
            else {
                s = this.a(s, false);
                super.k.a(super.c.ex);
            }
            synchronized (this.j) {
                this.a(new vr(this.a, this.d, s));
                if (++this.k > super.c.c0) {
                    val.b(super.j, super.d);
                }
            }
            // monitorexit(this.j)
        }
    }
    
    private void g(final String s) {
        if (super.e && !super.c.ar && !s.equals(this.d)) {
            if (super.c.ao != -1 && this.f.size() >= super.c.ao && !super.c.x && !super.c.w) {
                super.k.a(van.a(super.c.g1, new Integer(super.c.ao)));
                return;
            }
            super.k.a(van.a(super.c.gs, s));
            this.a(new vt(this.a, this.d, s));
        }
    }
    
    public void a(final String s, final String s2, final int n) {
        if (super.e && !s.equals(this.d)) {
            this.a(new vx(this.a, this.d, s, s2, n));
        }
    }
    
    private void b(final Event event) {
        final String[] array = this.h.elementAt((int)event.arg);
        this.a(super.c, array, this.d);
        this.p.a(array[0], array[2]);
        super.c.h.a(6);
    }
    
    public void e(final String s) {
        if (this.o.m.getState()) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                try {
                    super.c.b.showDocument(new URL(nextToken), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final vc vc = (vc)observable;
        if (o instanceof vq && o instanceof vm) {
            final vq vq = (vq)o;
            final vm vm = (vm)o;
            if (this.a.equals(vq.e()) && !vm.f) {
                switch (vm.e) {
                    case 2: {
                        if (vm instanceof vr) {
                            this.a(vc, (vr)vm);
                            return;
                        }
                        if (vm instanceof vu) {
                            this.a(vc, (vu)vm);
                            return;
                        }
                        if (vm instanceof vw) {
                            this.a(vc, (vw)vm);
                            return;
                        }
                        if (vm instanceof vp) {
                            this.a(vc, (vp)vm);
                            return;
                        }
                        break;
                    }
                    case 4: {
                        if (vm instanceof vr) {
                            this.b(vc, (vr)vm);
                            return;
                        }
                        if (vm instanceof vt) {
                            this.f();
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
                this.d();
            }
        }
    }
    
    private void a(final vc vc, final vr vr) {
        vr.d();
        final String f = vr.f;
        final String b = this.b(vr.g);
        if (f.length() == 0) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement((String)new Date());
            vector.addElement(b);
            super.i.a(van.a(super.c.g7, vector), "> ");
            return;
        }
        if (!this.p.a(f)) {
            if (this.m != null && vr.c) {
                this.m.addItem(f + ": " + b, 0);
                return;
            }
            super.i.a("<" + f + "> " + b, "> ");
            this.e(b);
        }
    }
    
    private void b(final vc vc, final vr vr) {
        vr.d();
        synchronized (this.j) {
            final int k = this.k - 1;
            this.k = k;
            if (k <= super.c.c0) {
                val.a(super.j, super.d);
            }
        }
        // monitorexit(this.j)
    }
    
    private void a(final vc vc, final vu vu) {
        ++this.c;
        if (this.o.l.getState()) {
            super.k.a(van.a(super.c.gz, new Integer(this.c)));
        }
        vu.d();
        final String d = vu.d;
        if (d.length() != 0) {
            this.a(d, vu.e, vu.f, vu.g, vu.h);
        }
    }
    
    private void a(final vc vc, final vw vw) {
        --this.c;
        if (this.o.l.getState()) {
            super.k.a(van.a(super.c.gz, new Integer(this.c)));
        }
        vw.d();
        final String d = vw.d;
        if (d.equals(this.d)) {
            this.a(new Event(this, 201, null));
            return;
        }
        if (d.length() != 0) {
            this.h(d);
        }
    }
    
    private void a(final vc vc, final vp vp) {
        final String d = vp.d;
        if (this.o.k.getState() && !this.p.a(d)) {
            final Vector<Date> vector = new Vector<Date>(2);
            vector.addElement(new Date());
            vector.addElement((Date)d);
            super.i.a(van.a(super.c.g6, vector), "> ");
            super.c.h.a();
        }
    }
    
    private void f() {
        super.k.a(super.c.ev);
    }
    
    private void a(final vc vc, final vap vap) {
        super.k.a("Server error (" + vap.a + ").");
    }
    
    private void a(final String s, final String s2, final String s3, final String s4, final String s5) {
        final String[] array = { s, s2, s3, s4, s5 };
        synchronized (this.h) {
            this.l.addItem(s);
            this.g.addElement(s);
            this.h.addElement(array);
            this.i.insertElementAt(array, 0);
            if (this.i.size() > super.c.b1) {
                this.i.setSize(super.c.b1);
            }
        }
        // monitorexit(this.h)
        if (this.o.j.getState()) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement((String)new Date());
            vector.addElement(vam.a(super.c, array));
            super.i.a(van.a(super.c.g5, vector), "> ");
            if (this.o.k.getState()) {
                super.c.h.a();
            }
        }
    }
    
    private void h(final String s) {
        synchronized (this.h) {
            final int index = this.g.indexOf(s);
            if (index != -1) {
                this.l.delItem(index);
                this.g.removeElementAt(index);
                this.h.removeElementAt(index);
            }
        }
        // monitorexit(this.h)
    }
    
    public String[] f(final String s) {
        String[] array = null;
        synchronized (this.h) {
            final int index = this.g.indexOf(s);
            if (index != -1) {
                array = (String[])this.h.elementAt(index);
            }
        }
        // monitorexit(this.h)
        return array;
    }
    
    private va0[] a(final Vector vector) {
        final Vector vector2 = new Vector<va0>(vector.size());
        synchronized (this.h) {
            final Enumeration<String[]> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final String[] array = elements.nextElement();
                vector2.addElement(new va0(array[0], array[2]));
            }
        }
        // monitorexit(this.h)
        final va0[] array2 = new va0[vector2.size()];
        vector2.copyInto(array2);
        return array2;
    }
    
    private void a(final String s, final va0[] array, final int n) {
        final va1 va1 = new va1(this, s, super.c, array, n);
        va1.pack();
        final Rectangle bounds = this.bounds();
        final Rectangle bounds2 = va1.bounds();
        va1.move(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
        va1.show();
    }
}
