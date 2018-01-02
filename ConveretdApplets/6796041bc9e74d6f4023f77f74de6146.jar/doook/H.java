// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;

public class H extends o
{
    private Choice a;
    private Checkbox g;
    Checkbox h;
    Checkbox i;
    private u e;
    private Checkbox d;
    private Checkbox j;
    private Checkbox k;
    
    public void c() {
        this.e.D = (this.g.getState() || !this.g.isEnabled());
        this.e.t = this.a.getSelectedIndex();
        this.e.B = this.i.getState();
        this.e.v = this.h.getState();
        if (this.e.I != this.d.getState()) {
            this.e.I = this.d.getState();
            final cD cd = new cD(263680, 1);
            cd.a(0, 49, this.e.I);
            this.e.o(cd);
        }
        if (this.e.K != this.j.getState()) {
            this.e.K = this.j.getState();
            final cD cd2 = new cD(66305, 1);
            cd2.o = -1;
            cd2.j = this.e.h();
            cd2.a(0, 0, -1);
            cd2.a(0, 0, "*toggleimage");
            this.e.o(cd2);
        }
        if (this.e.L != this.k.getState()) {
            this.e.L = this.k.getState();
            final cD cd3 = new cD(66305, 1);
            cd3.o = -1;
            cd3.j = this.e.h();
            cd3.a(0, 0, -1);
            cd3.a(0, 0, "*togglestar");
            this.e.o(cd3);
        }
    }
    
    public void d() {
        if (cD.a(this.e.d, 61) || cD.a(this.e.d, 62)) {
            this.g.disable();
            this.g.setState(true);
        }
        else {
            this.g.setState(this.e.D);
        }
        this.i.setState(this.e.B);
        this.a.select(this.e.t);
        this.h.setState(this.e.v);
        this.d.setState(this.e.I);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ao.e("Select how fast new chat messages will scroll.");
        }
        if (o == this.g) {
            if (this.g.isEnabled()) {
                return ao.e("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return ao.e("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else {
            if (o != this.d) {
                return null;
            }
            if (this.d.getState()) {
                return ao.e("Uncheck this box to receive private messages from other users.");
            }
            return ao.e("Check this box to prevent other users from sending you private messages.");
        }
    }
    
    public H(final u e) {
        super(ao.e("Options"), e);
        this.a = new Choice();
        this.g = new Checkbox(ao.e("Enable ChatWatch"));
        this.h = new Checkbox(ao.e("Enable Buddies Tab"));
        this.i = new Checkbox(ao.e("Enable Auto-Popup of Private Messages"));
        this.d = new Checkbox(ao.e("Disable private messages from other users."));
        this.j = new Checkbox(ao.e("Hide my image."));
        this.k = new Checkbox(ao.e("Hide my star."));
        this.e = e;
        this.a.addItem(ao.e("Very Slow"));
        this.a.addItem(ao.e("Slow"));
        this.a.addItem(ao.e("Normal"));
        this.a.addItem(ao.e("Fast"));
        this.a.addItem(ao.e("Very Fast"));
        this.a(ao.e("Message Scroll Speed"), this.a);
        this.a("", this.g);
        this.a("", this.i);
        if (!e.A) {
            this.a("", this.h);
        }
        this.a("", this.d);
        this.a("", this.j);
        if (e.d(50)) {
            this.a("", this.k);
        }
    }
}
