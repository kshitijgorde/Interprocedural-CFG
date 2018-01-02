// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Observable;
import java.awt.Event;
import java.awt.Container;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.util.Hashtable;
import java.awt.Label;

public class VolanoChat extends vb
{
    private static boolean a;
    private Label b;
    private vg c;
    private Label d;
    private vg e;
    private Label f;
    private vg g;
    private String h;
    private Hashtable i;
    private Hashtable j;
    private Hashtable k;
    
    public vn a() {
        return new vm();
    }
    
    public void a(final vd vd) {
        vo.a(vd.cf);
        vr.a(vd.cg);
        vs.a(vd.ch);
        vu.a(vd.ci);
        vw.a(vd.cj);
        vx.a(vd.ck);
        vy.a(vd.cl);
        vz.a(vd.cm);
        vaa.a(vd.cn);
        vab.a(vd.co);
        vac.a(vd.cp);
        vad.a(vd.cq);
    }
    
    public void init() {
        super.init();
        if (!VolanoChat.a) {
            System.out.println(this.getAppletInfo());
            VolanoChat.a = true;
        }
        Container container = null;
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 2, 2));
        super.i = super.a.ai;
        super.j = super.a.ak;
        if (super.a.y && (super.a.ai.length() == 0 || super.a.ak.length() == 0)) {
            container = new Panel();
            final GridBagLayout layout = new GridBagLayout();
            container.setLayout(layout);
            final Insets insets = new Insets(2, 2, 2, 2);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = insets;
            gridBagConstraints.anchor = 13;
            (this.b = new Label(super.a.b7, 2)).setFont(super.a.bn);
            layout.setConstraints(this.b, gridBagConstraints);
            container.add(this.b);
            gridBagConstraints.gridy = 1;
            (this.d = new Label(super.a.b8, 2)).setFont(super.a.bn);
            layout.setConstraints(this.d, gridBagConstraints);
            container.add(this.d);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            this.c = new vg(32);
            this.c.a = super.a.cd;
            this.c.setFont(super.a.bn);
            this.c.setText(super.a.ai);
            layout.setConstraints(this.c, gridBagConstraints);
            container.add(this.c);
            gridBagConstraints.gridy = 1;
            (this.e = new vg(32)).setFont(super.a.bn);
            this.e.setEchoCharacter('*');
            this.e.setText(super.a.ak);
            layout.setConstraints(this.e, gridBagConstraints);
            container.add(this.e);
            this.k.put(this.c, super.a.f8);
            this.k.put(this.e, super.a.f9);
            this.i.put(this.c, this.e);
            this.i.put(this.e, this.c);
            this.j.put(this.c, this.e);
            this.j.put(this.e, this.c);
        }
        if (super.a.x || super.a.w) {
            this.f = new Label("", 2);
            this.g = new vg(16);
            this.f.setFont(super.a.bn);
            this.g.setFont(super.a.bn);
            if (super.a.x) {
                this.f.setText(super.a.d2);
            }
            else {
                this.f.setText(super.a.d3);
            }
            this.g.setEchoCharacter('*');
            panel.add(this.f);
            panel.add(this.g);
        }
        panel.add(super.c);
        if (container != null) {
            this.add("North", container);
            this.add("Center", panel);
            this.add("South", super.d);
            return;
        }
        this.add("North", panel);
        this.add("Center", super.d);
    }
    
    public void b() {
        if (this.c != null) {
            this.c.requestFocus();
            return;
        }
        if (this.g != null) {
            this.g.requestFocus();
            return;
        }
        if (super.c != null) {
            super.c.requestFocus();
        }
    }
    
    public void show() {
        super.show();
        this.b();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 9) {
            if (event.shiftDown()) {
                this.a(this.j, (Component)event.target);
            }
            else {
                this.a(this.i, (Component)event.target);
            }
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.c != null) {
            super.i = this.c.getText().trim();
            if (super.i.length() == 0) {
                super.d.setText(super.a.gd);
                this.c.requestFocus();
                return false;
            }
        }
        if (this.e != null) {
            super.j = this.e.getText().trim();
            if (super.j.length() == 0) {
                super.d.setText(super.a.ge);
                this.e.requestFocus();
                return false;
            }
        }
        if (this.g != null) {
            super.k = this.g.getText().trim();
            if (super.k.length() == 0) {
                super.d.setText(super.a.gc);
                this.g.requestFocus();
                return false;
            }
        }
        synchronized (this) {
            super.e = true;
            this.notify();
        }
        return true;
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        if (o instanceof vo) {
            this.a((vc)observable, (vo)o);
            return;
        }
        if (o instanceof vr) {
            this.a((vc)observable, (vr)o);
            return;
        }
        super.update(observable, o);
    }
    
    private void a(final vc vc, final vo vo) {
        final int o = vo.o;
        if (o == 1) {
            if (vo instanceof vae) {
                this.h = ((vae)vo).i;
                if (super.a.x || super.a.w || super.a.aw) {
                    super.a.n = true;
                }
            }
            final byte[] q = vo.q;
            if (q.length == 0) {
                this.a(vc, vo.p);
                return;
            }
            super.d.setText(super.a.d0);
            final byte[] a = this.a(q);
            this.a(vc, new vr(a));
            if (a.length == 0) {
                this.a(super.a.c0);
            }
        }
        else {
            this.a(o);
        }
    }
    
    private void a(final vc vc, final vr vr) {
        if (vr.d == 1) {
            this.a(vc, vr.e);
        }
    }
    
    private void a(final vc vc, final String[] array) {
        (super.g = new vaf(vc, this, super.a, super.i, this.h, array)).setTitle(super.a.d4);
        super.g.pack();
        super.g.show();
        super.a.h.a(0);
        super.d.setText("");
        super.f = 2;
    }
    
    private void a(final Hashtable hashtable, final Component component) {
        Component c = hashtable.get(component);
        if (c == null) {
            c = this.c;
        }
        if (c != null) {
            super.d.setText((String)this.k.get(c));
            c.requestFocus();
        }
    }
    
    public VolanoChat() {
        this.h = "";
        this.i = new Hashtable();
        this.j = new Hashtable();
        this.k = new Hashtable();
    }
}
