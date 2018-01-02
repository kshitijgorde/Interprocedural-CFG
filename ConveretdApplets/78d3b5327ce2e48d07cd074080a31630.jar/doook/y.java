// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;

public class y extends W
{
    private Choice a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private at d;
    private Checkbox a;
    
    public void f() {
        this.d.G = (this.b.getState() || !this.b.isEnabled());
        this.d.J = this.a.getSelectedIndex();
        this.d.E = this.d.getState();
        this.d.x = this.c.getState();
        if (this.d.L != this.a.getState()) {
            this.d.L = this.a.getState();
            final aJ aj = new aJ(263680, 1);
            aj.a(0, 49, this.d.L);
            this.d.q(aj);
        }
    }
    
    public void c() {
        if (aJ.a(this.d.e, 61) || aJ.a(this.d.e, 62)) {
            this.b.setEnabled(false);
            this.b.setState(true);
        }
        else {
            this.b.setState(this.d.G);
        }
        this.d.setState(this.d.E);
        this.a.select(this.d.J);
        this.c.setState(this.d.x);
        this.a.setState(this.d.L);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ar.b("Select how fast new chat messages will scroll.");
        }
        if (o == this.b) {
            if (this.b.isEnabled()) {
                return ar.b("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return ar.b("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else {
            if (o != this.a) {
                return null;
            }
            if (this.a.getState()) {
                return ar.b("Uncheck this box to receive private messages from other users.");
            }
            return ar.b("Check this box to prevent other users from sending you private messages.");
        }
    }
    
    public y(final at d) {
        super(ar.b("Options"), d);
        this.a = new Choice();
        this.b = new Checkbox(ar.b("Enable ChatWatch"));
        this.c = new Checkbox(ar.b("Enable Buddies Tab"));
        this.d = new Checkbox(ar.b("Enable Auto-Popup of Private Messages"));
        this.a = new Checkbox(ar.b("Disable private messages from other users."));
        this.d = d;
        this.a.addItem(ar.b("Very Slow"));
        this.a.addItem(ar.b("Slow"));
        this.a.addItem(ar.b("Normal"));
        this.a.addItem(ar.b("Fast"));
        this.a.addItem(ar.b("Very Fast"));
        this.a(ar.b("Message Scroll Speed"), this.a);
        this.a("", this.b);
        this.a("", this.d);
        if (!d.D) {
            this.a("", this.c);
        }
        this.a("", this.a);
    }
}
