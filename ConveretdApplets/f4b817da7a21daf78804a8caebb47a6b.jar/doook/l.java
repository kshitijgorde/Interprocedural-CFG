// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;

public class l extends aZ
{
    private Choice a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private aW c;
    private Checkbox e;
    
    public void a() {
        this.c.M = (this.b.getState() || !this.b.isEnabled());
        this.c.H = this.a.getSelectedIndex();
        this.c.K = this.d.getState();
        this.c.p = this.c.getState();
        if (this.c.R != this.e.getState()) {
            this.c.R = this.e.getState();
            final V v = new V(263680, 1);
            v.a(0, 49, this.c.R);
            this.c.F(v);
        }
    }
    
    public void c() {
        if (V.a(this.c.i, 61) || V.a(this.c.i, 62)) {
            this.b.setEnabled(false);
            this.b.setState(true);
        }
        else {
            this.b.setState(this.c.M);
        }
        this.d.setState(this.c.K);
        this.a.select(this.c.H);
        this.c.setState(this.c.p);
        this.e.setState(this.c.R);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return aG.a("Select how fast new chat messages will scroll.");
        }
        if (o == this.b) {
            if (this.b.isEnabled()) {
                return aG.a("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return aG.a("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else {
            if (o != this.e) {
                return null;
            }
            if (this.e.getState()) {
                return aG.a("Uncheck this box to receive private messages from other users.");
            }
            return aG.a("Check this box to prevent other users from sending you private messages.");
        }
    }
    
    public l(final aW c) {
        super(aG.a("Options"), c);
        this.a = new Choice();
        this.b = new Checkbox(aG.a("Enable ChatWatch"));
        this.c = new Checkbox(aG.a("Enable Buddies Tab"));
        this.d = new Checkbox(aG.a("Enable Auto-Popup of Private Messages"));
        this.e = new Checkbox(aG.a("Disable private messages from other users."));
        this.c = c;
        this.a.addItem(aG.a("Very Slow"));
        this.a.addItem(aG.a("Slow"));
        this.a.addItem(aG.a("Normal"));
        this.a.addItem(aG.a("Fast"));
        this.a.addItem(aG.a("Very Fast"));
        this.a(aG.a("Message Scroll Speed"), this.a);
        this.a("", this.b);
        this.a("", this.d);
        if (!c.J) {
            this.a("", this.c);
        }
        this.a("", this.e);
    }
}
