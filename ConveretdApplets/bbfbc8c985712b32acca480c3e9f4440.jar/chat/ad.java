// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;

public final class ad extends S
{
    private Choice a;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private cx a;
    private Checkbox e;
    
    public final void b() {
        this.a.r = (this.a.getState() || !this.a.isEnabled());
        this.a.u = this.a.getSelectedIndex();
        this.a.p = this.b.getState();
        if (this.a.i != this.e.getState() || this.a.j != this.c.getState() || (this.a.a(67) && this.a.k != this.d.getState())) {
            this.a.i = this.e.getState();
            this.a.j = this.c.getState();
            this.a.k = this.d.getState();
            final r r;
            (r = new r(196865, 1)).a(0, 0, this.a.i);
            r.a(0, 1, this.a.i);
            r.a(0, 2, this.a.j);
            if (this.a.a(67)) {
                r.a(0, 3, this.a.k);
            }
            this.a.o(r);
        }
    }
    
    public final void a() {
        if (r.a(this.a.e, 61) || r.a(this.a.e, 62)) {
            this.a.disable();
            this.a.setState(true);
        }
        else {
            this.a.setState(this.a.r);
        }
        this.b.setState(this.a.p);
        this.a.select(this.a.u);
        this.e.setState(this.a.i);
        this.c.setState(this.a.j);
        this.d.setState(this.a.k);
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return aS.a(395);
        }
        if (o == this.a) {
            if (this.a.isEnabled()) {
                return aS.a(396);
            }
            return aS.a(397);
        }
        else {
            if (o != this.e) {
                return null;
            }
            if (this.e.getState()) {
                return aS.a(398);
            }
            return aS.a(399);
        }
    }
    
    public ad(final cx a) {
        super(aS.a(105), a);
        this.a = new Choice();
        this.a = new Checkbox(aS.a(400));
        this.b = new Checkbox(aS.a(401));
        this.e = new Checkbox(aS.a(402));
        this.c = new Checkbox(aS.a(633));
        this.d = new Checkbox(aS.a(634));
        ((Choice)(this.a = a)).addItem(aS.a(403));
        this.a.addItem(aS.a(404));
        this.a.addItem(aS.a(405));
        this.a.addItem(aS.a(406));
        this.a.addItem(aS.a(407));
        this.a(aS.a(408), this.a);
        this.a("", this.a);
        this.a("", this.b);
        this.a("", this.e);
        this.a("", this.c);
        if (this.a.a(67)) {
            this.a("", this.d);
        }
        this.c.setEnabled(a.c != null && a.c.trim().length() > 0);
    }
}
