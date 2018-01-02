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

public class maa extends mab
{
    private String a;
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
    private mak n;
    private mal o;
    private maf p;
    private mam q;
    private man r;
    private mao s;
    private map t;
    
    public maa(final mc mc, final Component component, final md md, final String[][] array, final String a, final int b, final int c, final String d, final String e, final Hashtable f) {
        super(mc, component, md);
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
        if (this.i.size() > md.fn) {
            this.i.setSize(md.fn);
        }
        if (b == 1 || md.z) {
            super.k.a(md.ei);
        }
        else {
            super.k.a(md.ej);
        }
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", super.j);
        if (md.x && md.z && b == 2) {
            panel.add("Center", this.m = new List(10, false));
        }
        if (md.aw.length() > 0) {
            (super.l = new mai(md.b, md.bp)).a(md.aw, md.ax);
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
        this.n = new mak(md);
        this.o = new mal(md);
        this.p = new maf(md);
        this.q = new mam(md);
        this.r = new man(md);
        this.s = new mao(md);
        this.t = new map(md);
        if (md.e6.length() > 0) {
            menuBar.add(this.n);
        }
        if (md.ep.length() > 0) {
            menuBar.add(this.o);
        }
        if (md.e8.length() > 0 && (b == 1 || md.z)) {
            menuBar.add(this.p);
        }
        if (md.fd.length() > 0 && (md.n || md.x)) {
            menuBar.add(this.q);
        }
        if (md.fk.length() > 0) {
            menuBar.add(this.r);
        }
        if (md.fh.length() > 0) {
            menuBar.add(this.s);
        }
        if (md.e3.length() > 0) {
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
    
    public boolean a() {
        return this.o.i.getState();
    }
    
    public boolean a(final String s) {
        return this.p.a(s);
    }
    
    public void b() {
        super.b();
        this.a(new mw(this.a, this.d));
    }
    
    public void a(final maj maj) {
        super.a(maj);
        synchronized (this.j) {
            if (this.k > super.c.cn) {
                maj.b(super.j, maj);
            }
        }
        // monitorexit(this.j)
        this.r.a(maj.n);
        this.o.a(maj.m);
    }
    
    public void b(final String s) {
        super.b(s);
        this.o.a(s);
    }
    
    public void a(final int n) {
        super.a(n);
        this.o.a(n);
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
            this.a(new mp(this.a, this.d, this.p.c));
            return true;
        }
        if (event.target == this.p.f) {
            this.p.a();
            return true;
        }
        if (event.target == this.p.g) {
            super.k.a(mz.a(super.c.gn, new Integer(this.c)));
            return true;
        }
        if (event.target == this.q.a) {
            this.a(super.c.fo, this.a(this.h), 1);
            return true;
        }
        if (event.target == this.q.b) {
            this.a(super.c.fp, this.a(this.i), 2);
            return true;
        }
        if (event.target == this.q.c) {
            this.a(super.c.fq, this.a(this.i), 3);
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
            super.c.b.showDocument(super.c.cx, "_blank");
            return true;
        }
        if (event.target == this.t.b) {
            super.c.b.showDocument(super.c.cy, "_blank");
            return true;
        }
        if (event.target == this.o.g) {
            this.c();
            return true;
        }
        if (event.target == this.o.h) {
            this.d();
            return true;
        }
        if (event.target instanceof CheckboxMenuItem) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)event.target;
            final MenuContainer parent = checkboxMenuItem.getParent();
            if (parent == this.o.a) {
                this.b(checkboxMenuItem.getLabel());
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
        if (this.k <= super.c.cn && super.e && s.trim().length() != 0) {
            if (this.b == 1 || super.c.z) {
                s = this.a(s, true);
            }
            else {
                s = this.a(s, false);
                super.k.a(super.c.ek);
            }
            synchronized (this.j) {
                this.a(new mr(this.a, this.d, s));
                if (++this.k > super.c.cn) {
                    maj.b(super.j, super.d);
                }
            }
            // monitorexit(this.j)
        }
    }
    
    private void g(final String s) {
        if (super.e && !super.c.ar && !s.equals(this.d)) {
            if (super.c.ao != -1 && this.f.size() >= super.c.ao && !super.c.x && !super.c.w) {
                super.k.a(mz.a(super.c.gp, new Integer(super.c.ao)));
                return;
            }
            super.k.a(mz.a(super.c.gg, s));
            this.a(new mt(this.a, this.d, s));
        }
    }
    
    public void a(final String s, final String s2, final int n) {
        if (super.e && !s.equals(this.d)) {
            this.a(new mx(this.a, this.d, s, s2, n));
        }
    }
    
    private void b(final Event event) {
        final String[] array = this.h.elementAt((int)event.arg);
        this.a(super.c, array, this.d);
        this.p.a(array[0], array[2]);
        super.c.h.a(6);
    }
    
    public void d(final String s) {
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
        final mc mc = (mc)observable;
        if (o instanceof mq && o instanceof mm) {
            final mq mq = (mq)o;
            final mm mm = (mm)o;
            if (this.a.equals(mq.e()) && !mm.f) {
                switch (mm.e) {
                    case 2: {
                        if (mm instanceof mr) {
                            this.a(mc, (mr)mm);
                            return;
                        }
                        if (mm instanceof mu) {
                            this.a(mc, (mu)mm);
                            return;
                        }
                        if (mm instanceof mw) {
                            this.a(mc, (mw)mm);
                            return;
                        }
                        if (mm instanceof mp) {
                            this.a(mc, (mp)mm);
                            return;
                        }
                        break;
                    }
                    case 4: {
                        if (mm instanceof mr) {
                            this.b(mc, (mr)mm);
                            return;
                        }
                        if (mm instanceof mt) {
                            this.f();
                            return;
                        }
                        break;
                    }
                }
            }
        }
        else {
            if (o instanceof maq) {
                this.a(mc, (maq)o);
                return;
            }
            if (o == null) {
                this.e();
            }
        }
    }
    
    private void a(final mc mc, final mr mr) {
        mr.d();
        final String f = mr.f;
        final String f2 = this.f(mr.g);
        if (f.length() == 0) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement((String)new Date());
            vector.addElement(f2);
            super.i.a(mz.a(super.c.gv, vector), "> ");
            return;
        }
        if (!this.p.a(f)) {
            if (this.m != null && mr.c) {
                this.m.addItem(String.valueOf(f) + ": " + f2, 0);
                return;
            }
            super.i.a("<" + f + "> " + f2, "> ");
            this.d(f2);
        }
    }
    
    private void b(final mc mc, final mr mr) {
        mr.d();
        synchronized (this.j) {
            final int k = this.k - 1;
            this.k = k;
            if (k <= super.c.cn) {
                maj.a(super.j, super.d);
            }
        }
        // monitorexit(this.j)
    }
    
    private void a(final mc mc, final mu mu) {
        ++this.c;
        if (this.o.l.getState()) {
            super.k.a(mz.a(super.c.gn, new Integer(this.c)));
        }
        mu.d();
        final String d = mu.d;
        if (d.length() != 0) {
            this.a(d, mu.e, mu.f, mu.g, mu.h);
        }
    }
    
    private void a(final mc mc, final mw mw) {
        --this.c;
        if (this.o.l.getState()) {
            super.k.a(mz.a(super.c.gn, new Integer(this.c)));
        }
        mw.d();
        final String d = mw.d;
        if (d.equals(this.d)) {
            this.a(new Event(this, 201, null));
            return;
        }
        if (d.length() != 0) {
            this.h(d);
        }
    }
    
    private void a(final mc mc, final mp mp) {
        final String d = mp.d;
        if (this.o.k.getState() && !this.p.a(d)) {
            final Vector<Date> vector = new Vector<Date>(2);
            vector.addElement(new Date());
            vector.addElement((Date)d);
            super.i.a(mz.a(super.c.gu, vector), "> ");
            super.c.h.a();
        }
    }
    
    private void f() {
        super.k.a(super.c.ei);
    }
    
    private void a(final mc mc, final maq maq) {
        super.k.a("Server error (" + maq.a + ").");
    }
    
    private void a(final String s, final String s2, final String s3, final String s4, final String s5) {
        final String[] array = { s, s2, s3, s4, s5 };
        synchronized (this.h) {
            this.l.addItem(s);
            this.g.addElement(s);
            this.h.addElement(array);
            this.i.insertElementAt(array, 0);
            if (this.i.size() > super.c.fn) {
                this.i.setSize(super.c.fn);
            }
        }
        // monitorexit(this.h)
        if (this.o.j.getState()) {
            final Vector<Date> vector = new Vector<Date>(4);
            vector.addElement(new Date());
            vector.addElement((Date)s);
            vector.addElement((Date)s3);
            vector.addElement((Date)s2);
            super.i.a(mz.a(super.c.gt, vector), "> ");
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
    
    public String[] e(final String s) {
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
    
    private mav[] a(final Vector vector) {
        final Vector vector2 = new Vector<mav>(vector.size());
        synchronized (this.h) {
            final Enumeration<String[]> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final String[] array = elements.nextElement();
                vector2.addElement(new mav(array[0], array[2]));
            }
        }
        // monitorexit(this.h)
        final mav[] array2 = new mav[vector2.size()];
        vector2.copyInto(array2);
        return array2;
    }
    
    private void a(final String s, final mav[] array, final int n) {
        final maw maw = new maw(this, s, super.c, array, n);
        maw.pack();
        final Rectangle bounds = this.bounds();
        final Rectangle bounds2 = maw.bounds();
        maw.move(bounds.x + (bounds.width - bounds2.width) / 2, bounds.y + (bounds.height - bounds2.height) / 2);
        maw.show();
    }
}
