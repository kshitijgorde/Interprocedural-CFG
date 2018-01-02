// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Date;
import java.util.Vector;
import java.util.Observable;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;

public class mae extends mab
{
    public int a;
    private String b;
    private String[] c;
    private boolean d;
    
    public mae(final mc mc, final Component component, final md md, final int a, final String b, final String[] c) {
        super(mc, component, md);
        this.d = false;
        this.a = a;
        this.b = b;
        this.a(md, this.c = c, b);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", super.j);
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
        super.f.put(super.i, super.j);
        super.f.put(super.j, super.k);
        super.f.put(super.k, super.i);
        super.g.put(super.i, super.k);
        super.g.put(super.k, super.j);
        super.g.put(super.j, super.i);
    }
    
    public void b() {
        super.b();
        this.a(new mv(this.a, this.b));
    }
    
    public void c(final String s) {
        if (!this.d && super.e && s.trim().length() != 0) {
            this.a(new mr(this.a, this.b, this.a(s, true)));
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof ms && o instanceof mm) {
            final ms ms = (ms)o;
            final mm mm = (mm)o;
            if (ms.f() == this.a && mm.e == 2) {
                final mc mc = (mc)observable;
                if (mm instanceof mr) {
                    this.a(mc, (mr)mm);
                    return;
                }
                if (mm instanceof mv) {
                    this.a(mc, (mv)mm);
                }
            }
        }
        else if (o == null) {
            this.e();
        }
    }
    
    private void a(final mc mc, final mr mr) {
        mr.d();
        super.i.a("<" + mr.f + "> " + this.f(mr.g), "> ");
    }
    
    private void a(final mc mc, final mv mv) {
        mv.d();
        this.d = true;
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement((String)new Date());
        vector.addElement(this.c[0]);
        super.i.a(mz.a(super.c.gw, vector), "> ");
    }
}
