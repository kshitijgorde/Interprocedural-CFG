// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Date;
import java.util.Vector;
import java.util.Observable;
import java.awt.Event;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;

public class va3 extends vap
{
    public int a;
    private String b;
    private String[] c;
    private boolean d;
    private va4 e;
    private van f;
    
    public va3(final vc vc, final Component component, final vd vd, final int a, final String b, final String[] c) {
        super(vc, component, vd);
        this.d = false;
        this.a = a;
        this.b = b;
        this.a(vd, this.c = c, b);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", super.j);
        if (vd.az.length() > 0 || vd.ax.length() > 0) {
            super.l = new vag(vd.b, vd.bt);
            if (vd.az.length() > 0) {
                super.l.a(vd.az, vd.a0, vd.a1, vd.ax, vd.ay);
            }
            else {
                super.l.a(vd.ax, vd.ay);
            }
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
        final MenuBar menuBar = new MenuBar();
        this.e = new va4(vd);
        this.f = new van(vd);
        if (vd.fb.length() > 0) {
            menuBar.add(this.e);
        }
        if (vd.e8.length() > 0) {
            menuBar.add(this.f);
        }
        if (menuBar.countMenus() > 0) {
            this.setMenuBar(menuBar);
        }
        super.f.put(super.i, super.j);
        super.f.put(super.j, super.k);
        super.f.put(super.k, super.i);
        super.g.put(super.i, super.k);
        super.g.put(super.k, super.j);
        super.g.put(super.j, super.i);
    }
    
    public void c() {
        super.c();
        this.a(new vy(this.a, this.b));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.e.a) {
            event.id = 201;
            event.target = this;
            return this.a(event);
        }
        if (event.target == this.f.a) {
            super.c.b.showDocument(super.c.c1, "_blank");
            return true;
        }
        if (event.target == this.f.b) {
            super.c.b.showDocument(super.c.c2, "_blank");
            return true;
        }
        return false;
    }
    
    public void c(final String s) {
        if (!this.d && super.e && s.trim().length() != 0) {
            this.a(new vu(this.a, this.b, this.a(s, true)));
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof vv && o instanceof vp) {
            final vv vv = (vv)o;
            final vp vp = (vp)o;
            if (vv.f() == this.a && vp.e == 2) {
                final vc vc = (vc)observable;
                if (vp instanceof vu) {
                    this.a(vc, (vu)vp);
                    return;
                }
                if (vp instanceof vy) {
                    this.a(vc, (vy)vp);
                }
            }
        }
        else if (o == null) {
            this.d();
        }
    }
    
    private void a(final vc vc, final vu vu) {
        vu.d();
        super.i.a("<" + vu.f + "> " + this.b(vu.g));
    }
    
    private void a(final vc vc, final vy vy) {
        vy.d();
        this.d = true;
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement((String)new Date());
        vector.addElement(this.c[0]);
        super.i.a(vaq.a(super.c.g1, vector));
    }
}
