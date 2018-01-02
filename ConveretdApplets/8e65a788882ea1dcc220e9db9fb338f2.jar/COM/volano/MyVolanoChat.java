// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;
import java.util.Observable;
import java.util.Enumeration;
import java.net.URL;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.util.Hashtable;
import java.awt.Label;

public class MyVolanoChat extends mb
{
    private static boolean a;
    private Label b;
    private mg c;
    private Label d;
    private mg e;
    private Hashtable f;
    private String g;
    private String h;
    private String i;
    private Hashtable j;
    private Hashtable k;
    private Hashtable l;
    
    private static String a(final String s, final int n) {
        String trim = s;
        if (s.length() > n) {
            trim = s.substring(0, n).trim();
        }
        return trim;
    }
    
    public String a() {
        if (super.a.aa) {
            return "2.6.3";
        }
        return "2.6.3-p";
    }
    
    public mk b() {
        return new mj();
    }
    
    public void a(final md md) {
        ml.a(md.cb);
        mo.a(md.cc);
        mp.a(md.cd);
        mr.a(md.ce);
        mt.a(md.cf);
        mu.a(md.cg);
        mv.a(md.ch);
        mw.a(md.ci);
        mx.a(md.cj);
        my.a(md.ck);
    }
    
    public void init() {
        super.init();
        if (!MyVolanoChat.a) {
            System.out.println(this.getAppletInfo());
            MyVolanoChat.a = true;
        }
        final Panel panel = new Panel();
        this.h = super.a.ai;
        this.i = super.a.aj;
        if (super.a.ab || super.a.ai.length() == 0) {
            final GridBagLayout layout = new GridBagLayout();
            panel.setLayout(layout);
            final Insets insets = new Insets(2, 2, 2, 2);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = insets;
            gridBagConstraints.anchor = 13;
            (this.b = new Label(super.a.eb, 2)).setFont(super.a.bj);
            layout.setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
            gridBagConstraints.gridy = 1;
            (this.d = new Label(super.a.ec, 2)).setFont(super.a.bj);
            layout.setConstraints(this.d, gridBagConstraints);
            panel.add(this.d);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.weightx = 1.0;
            this.c = new mg(40);
            this.c.a = super.a.b9;
            this.c.setFont(super.a.bj);
            this.c.setText(super.a.ai);
            this.c.setEditable(super.a.at);
            layout.setConstraints(this.c, gridBagConstraints);
            panel.add(this.c);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 2;
            this.e = new mg(40);
            this.e.a = super.a.b7;
            this.e.setFont(super.a.bj);
            this.e.setText(super.a.aj);
            this.e.setEditable(super.a.au);
            layout.setConstraints(this.e, gridBagConstraints);
            panel.add(this.e);
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            layout.setConstraints(super.c, gridBagConstraints);
            panel.add(super.c);
            this.l.put(this.c, super.a.fz);
            this.l.put(this.e, super.a.f0);
            this.j.put(this.c, this.e);
            this.j.put(this.e, this.c);
            this.k.put(this.c, this.e);
            this.k.put(this.e, this.c);
        }
        else {
            panel.setLayout(new FlowLayout(1, 2, 2));
            panel.add(super.c);
        }
        this.add("North", panel);
        this.add("Center", super.d);
        if (this.c != null) {
            this.c.requestFocus();
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 9) {
            if (event.shiftDown()) {
                this.a(this.k, (Component)event.target);
            }
            else {
                this.a(this.j, (Component)event.target);
            }
            return true;
        }
        if (n == 10 || n == 13) {
            this.e();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        this.e();
        return true;
    }
    
    public synchronized boolean handleEvent(final Event event) {
        boolean a = false;
        if (event.id == 201) {
            a = this.a(event);
        }
        return a || super.handleEvent(event);
    }
    
    private boolean a(final Event event) {
        if (event.target == super.g) {
            this.c();
            this.d();
            super.d.setText(super.a.ds);
            super.a.h.a(1);
            final URL url = super.a.o ? super.a.c0 : super.a.cz;
            if (url != null) {
                if (super.a.co) {
                    super.a.b.showDocument(url, "_blank");
                }
                else {
                    super.a.b.showDocument(url);
                }
            }
            super.a.o = false;
            return true;
        }
        if (event.target instanceof mae) {
            this.f.remove(new Integer(((mae)event.target).a));
            super.a.h.a(3);
            return true;
        }
        return false;
    }
    
    private void c() {
        if (super.g instanceof maa) {
            ((maa)super.g).b();
            super.g = null;
        }
        super.b.d();
        super.f = 0;
    }
    
    private synchronized void d() {
        final Enumeration<mae> elements = this.f.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().b();
        }
    }
    
    private void e() {
        if (this.c != null) {
            this.h = a(this.c.getText().replace(' ', ' ').trim(), super.a.b9);
        }
        if (this.e != null) {
            this.i = a(this.e.getText().trim(), super.a.b7);
        }
        if (this.a(this.h)) {
            synchronized (this) {
                super.e = true;
                this.notify();
            }
        }
    }
    
    private boolean a(final String s) {
        if (s.length() == 0) {
            super.d.setText(mz.a(super.a.f6, super.a.aa ? super.a.ae : super.a.d.toString()));
            if (this.c != null) {
                this.c.requestFocus();
            }
        }
        else {
            if (super.g == null) {
                return true;
            }
            super.d.setText(mz.a(super.a.gj, super.a.ae));
            super.g.requestFocus();
        }
        return false;
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        if (o instanceof mm) {
            final mm mm = (mm)o;
            if (!mm.f) {
                final mc mc = (mc)observable;
                switch (mm.e) {
                    case 4: {
                        if (mm instanceof mt) {
                            this.a(mc, (mt)mm);
                            return;
                        }
                        if (mm instanceof mu) {
                            this.a(mc, (mu)mm);
                            return;
                        }
                        if (o instanceof ml) {
                            this.a(mc, (ml)mm);
                            return;
                        }
                        if (o instanceof mo) {
                            this.a(mc, (mo)o);
                            return;
                        }
                        super.update(observable, o);
                    }
                    case 2: {
                        if (mm instanceof mt) {
                            this.a(mc, (mt)mm);
                            return;
                        }
                        super.update(observable, o);
                    }
                    default: {
                        super.update(observable, o);
                    }
                }
            }
        }
        else {
            super.update(observable, o);
        }
    }
    
    private void a(final mc mc, final ml ml) {
        final int o = ml.o;
        if (o == 1) {
            this.g = (super.a.aa ? super.a.ae : super.a.d.toString());
            final byte[] q = ml.q;
            if (q.length == 0) {
                this.a(mc, new mu(this.g, this.h, this.i));
                super.d.setText(mz.a(super.a.gf, super.a.ae));
                super.f = 2;
                return;
            }
            super.d.setText(super.a.dv);
            final byte[] a = this.a(q);
            this.a(mc, new mo(a));
            if (a.length == 0) {
                this.a(super.a.cw);
            }
        }
        else {
            this.a(o);
        }
    }
    
    private void a(final mc mc, final mo mo) {
        if (mo.d == 1) {
            this.a(mc, new mu(this.g, this.h, this.i));
            super.d.setText(mz.a(super.a.gf, super.a.ae));
            super.f = 2;
        }
    }
    
    private void a(final mc mc, final mu mu) {
        final String d = mu.d;
        final int i = mu.i;
        if (i == 1) {
            (super.g = new maa(mc, this, super.a, mu.l, mu.e(), mu.j, mu.k, d, mu.e, this.f)).setTitle(String.valueOf(super.a.ae) + " (" + d + ")");
            final mab mab = (mab)super.g;
            mab.a(super.a.a(0));
            mab.a(true);
            super.g.pack();
            super.g.show();
            super.d.setText("");
            super.a.h.a(2);
        }
        else if (i == 2) {
            super.d.setText(mz.a(super.a.gm, super.a.ae));
        }
        else if (i == 3) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(d);
            vector.addElement(super.a.ae);
            super.d.setText(mz.a(super.a.gk, vector));
        }
        else if (i == 4) {
            super.d.setText(mz.a(super.a.gl, d));
        }
        else if (i == 5) {
            super.d.setText(super.a.gh);
        }
        if (i != 1) {
            this.c();
        }
    }
    
    private void a(final mc mc, final mt mt) {
        final int f = mt.f();
        final mae mae = this.f.get(new Integer(f));
        if (mae != null) {
            mae.requestFocus();
            return;
        }
        final String f2 = mt.f;
        final String e = mt.e;
        final maa maa = (maa)super.g;
        if (maa != null) {
            if (mt.e == 2) {
                final String[] e2 = maa.e(e);
                if (e2 == null || !maa.a() || maa.a(e) || (super.a.ao != -1 && this.f.size() >= super.a.ao)) {
                    this.a(mc, new mv(f, f2));
                    return;
                }
                this.a(String.valueOf(e) + " (" + f2 + ")", f, f2, e2);
            }
            else {
                final String[] e3 = maa.e(f2);
                if (e3 == null) {
                    this.a(mc, new mv(f, e));
                    return;
                }
                this.a(String.valueOf(f2) + " (" + e + ")", f, e, e3);
            }
        }
    }
    
    private void a(final String title, final int n, final String s, final String[] array) {
        final mae mae = new mae(super.b, this, super.a, n, s, array);
        this.f.put(new Integer(n), mae);
        mae.setTitle(title);
        final mae mae2 = mae;
        mae2.a(super.a.a(0));
        mae2.a(true);
        mae.pack();
        mae.show();
        super.a.h.a(2);
    }
    
    private void a(final Hashtable hashtable, final Component component) {
        Component c = hashtable.get(component);
        if (c == null) {
            c = this.c;
        }
        if (c != null) {
            super.d.setText((String)this.l.get(c));
            c.requestFocus();
        }
    }
    
    public MyVolanoChat() {
        this.f = new Hashtable();
        this.j = new Hashtable();
        this.k = new Hashtable();
        this.l = new Hashtable();
    }
}
