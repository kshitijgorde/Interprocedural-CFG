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

public class vav extends vam
{
    public int a;
    private String b;
    private String[] c;
    private boolean d;
    
    public vav(final vc vc, final Component component, final vd vd, final int a, final String b, final String[] c) {
        super(vc, component, vd);
        this.d = false;
        this.a = a;
        this.b = b;
        this.a(vd, this.c = c, b);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", super.j);
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
        super.f.put(super.i, super.j);
        super.f.put(super.j, super.k);
        super.f.put(super.k, super.i);
        super.g.put(super.i, super.k);
        super.g.put(super.k, super.j);
        super.g.put(super.j, super.i);
    }
    
    public void c() {
        super.c();
        this.a(new vv(this.a, this.b));
    }
    
    public void c(final String s) {
        if (!this.d && super.e && s.trim().length() != 0) {
            this.a(new vr(this.a, this.b, this.a(s, true)));
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof vs && o instanceof vm) {
            final vs vs = (vs)o;
            final vm vm = (vm)o;
            if (vs.f() == this.a && vm.e == 2) {
                final vc vc = (vc)observable;
                if (vm instanceof vr) {
                    this.a(vc, (vr)vm);
                    return;
                }
                if (vm instanceof vv) {
                    this.a(vc, (vv)vm);
                }
            }
        }
        else if (o == null) {
            this.d();
        }
    }
    
    private void a(final vc vc, final vr vr) {
        vr.d();
        super.i.a("<" + vr.f + "> " + this.b(vr.g), "> ");
    }
    
    private void a(final vc vc, final vv vv) {
        vv.d();
        this.d = true;
        final Vector<String> vector = new Vector<String>(2);
        vector.addElement((String)new Date());
        vector.addElement(this.c[0]);
        super.i.a(van.a(super.c.g8, vector), "> ");
    }
}
