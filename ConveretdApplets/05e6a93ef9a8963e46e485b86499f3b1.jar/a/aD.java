// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Choice;

public final class aD extends bN
{
    private Choice q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private W q;
    private Checkbox y;
    private Color q;
    
    public final void q() {
        this.q.l = (this.q.getState() || !this.q.isEnabled());
        this.q.l = this.q.getSelectedIndex();
        this.q.k = this.e.getState();
        this.q.p = this.w.getState();
        if (this.q.a != this.y.getState() || this.q.w() != this.q.getRGB() || this.q.s != this.r.getState() || this.q.d != this.t.getState()) {
            this.q.a = this.y.getState();
            if (cs.q()) {
                this.q.s = this.r.getState();
                this.q.d = this.t.getState();
            }
            this.q.r(bL.q(this.q.a, this.q.s, this.q.d, this.q.f, this.q.y, this.q.u, this.q.getRGB()));
        }
    }
    
    public final void w() {
        if (cp.q(this.q.e, 61) || cp.q(this.q.e, 62)) {
            this.q.setEnabled(false);
            this.q.setState(true);
        }
        else {
            this.q.setState(this.q.l);
        }
        this.e.setState(this.q.k);
        this.q.select(this.q.l);
        this.r.setState(this.q.s);
        this.t.setState(this.q.d);
        this.w.setState(this.q.p);
        this.y.setState(this.q.a);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return al.q("Select how fast new chat messages will scroll.");
        }
        if (o == this.q) {
            if (this.q.isEnabled()) {
                return al.q("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return al.q("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else if (o == this.y) {
            if (this.y.getState()) {
                return al.q("Uncheck this box to receive private messages from other users.");
            }
            return al.q("Check this box to prevent other users from sending you private messages.");
        }
        else if (o == this.r) {
            if (this.r.getState()) {
                return al.q("Uncheck this box to show star.");
            }
            return al.q("Check this box to hide star.");
        }
        else {
            if (o != this.t) {
                return null;
            }
            if (this.t.getState()) {
                return al.q("Uncheck this box to show icon.");
            }
            return al.q("Check this box to hide icon.");
        }
    }
    
    public aD(final W q) {
        super(al.q("Options"));
        this.q = Color.black;
        this.q = new Choice();
        this.q = new Checkbox(al.q("Enable ChatWatch"));
        this.w = new Checkbox(al.q("Enable Buddies Tab"));
        this.e = new Checkbox(al.q("Enable Auto-Popup of Private Messages"));
        this.y = new Checkbox(al.q("Disable private messages from other users."));
        this.r = new Checkbox(al.q("Hide Star"));
        this.t = new Checkbox(al.q("Hide Icon"));
        ((Choice)(this.q = q)).addItem(al.q("Very Slow"));
        this.q.addItem(al.q("Slow"));
        this.q.addItem(al.q("Normal"));
        this.q.addItem(al.q("Fast"));
        this.q.addItem(al.q("Very Fast"));
        this.q(al.q("Message Scroll Speed"), this.q);
        this.q("", this.q);
        this.q("", this.e);
        this.q("", this.y);
        if (cs.q()) {
            this.q("", this.r);
            this.q("", this.t);
        }
    }
}
