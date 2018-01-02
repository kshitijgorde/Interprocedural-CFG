// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;

public final class J extends D
{
    private Choice a;
    private Checkbox a;
    private Checkbox b;
    private bl a;
    private Checkbox c;
    
    public final void a() {
        this.a.m = (this.a.getState() || !this.a.isEnabled());
        this.a.n = this.a.getSelectedIndex();
        this.a.l = this.b.getState();
        if (this.a.g != this.c.getState()) {
            this.a.g = this.c.getState();
            final m m;
            (m = new m(196865, 1)).a(0, 0, this.a.g);
            m.a(0, 1, this.a.g);
            this.a.m(m);
        }
    }
    
    public final void b() {
        if (m.a(this.a.c, 61) || m.a(this.a.c, 62)) {
            this.a.disable();
            this.a.setState(true);
        }
        else {
            this.a.setState(this.a.m);
        }
        this.b.setState(this.a.l);
        this.a.select(this.a.n);
        this.c.setState(this.a.g);
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return ak.a(395);
        }
        if (o == this.a) {
            if (this.a.isEnabled()) {
                return ak.a(396);
            }
            return ak.a(397);
        }
        else {
            if (o != this.c) {
                return null;
            }
            if (this.c.getState()) {
                return ak.a(398);
            }
            return ak.a(399);
        }
    }
    
    public J(final bl a) {
        super(ak.a(105), a);
        this.a = new Choice();
        this.a = new Checkbox(ak.a(400));
        this.b = new Checkbox(ak.a(401));
        this.c = new Checkbox(ak.a(402));
        ((Choice)(this.a = a)).addItem(ak.a(403));
        this.a.addItem(ak.a(404));
        this.a.addItem(ak.a(405));
        this.a.addItem(ak.a(406));
        this.a.addItem(ak.a(407));
        this.a(ak.a(408), this.a);
        this.a("", this.a);
        this.a("", this.b);
        this.a("", this.c);
    }
}
