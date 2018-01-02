// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Choice;

public final class aC extends bM
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
        this.q.z = this.q.getSelectedIndex();
        this.q.k = this.e.getState();
        this.q.p = this.w.getState();
        if (this.q.a != this.y.getState() || this.q.w() != this.q.getRGB() || this.q.s != this.r.getState() || this.q.d != this.t.getState()) {
            this.q.a = this.y.getState();
            if (cs.q()) {
                this.q.s = this.r.getState();
                this.q.d = this.t.getState();
            }
            this.q.r(bK.q(this.q.a, this.q.s, this.q.d, this.q.f, this.q.y, this.q.u, this.q.getRGB()));
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
        this.q.select(this.q.z);
        this.r.setState(this.q.s);
        this.t.setState(this.q.d);
        this.w.setState(this.q.p);
        this.y.setState(this.q.a);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return ak.q("Select how fast new chat messages will scroll.");
        }
        if (o == this.q) {
            if (this.q.isEnabled()) {
                return ak.q("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return ak.q("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else if (o == this.y) {
            if (this.y.getState()) {
                return ak.q("Uncheck this box to receive private messages from other users.");
            }
            return ak.q("Check this box to prevent other users from sending you private messages.");
        }
        else if (o == this.r) {
            if (this.r.getState()) {
                return ak.q("Uncheck this box to show star.");
            }
            return ak.q("Check this box to hide star.");
        }
        else {
            if (o != this.t) {
                return null;
            }
            if (this.t.getState()) {
                return ak.q("Uncheck this box to show icon.");
            }
            return ak.q("Check this box to hide icon.");
        }
    }
    
    public aC(final W q) {
        super(ak.q("Options"));
        this.q = Color.black;
        this.q = new cu();
        this.q = new Checkbox(ak.q("Enable ChatWatch"));
        this.w = new Checkbox(ak.q("Enable Buddies Tab"));
        this.e = new Checkbox(ak.q("Enable Auto-Popup of Private Messages"));
        this.y = new Checkbox(ak.q("Disable private messages from other users."));
        this.r = new Checkbox(ak.q("Hide Star"));
        this.t = new Checkbox(ak.q("Hide Icon"));
        ((Choice)(this.q = q)).addItem(ak.q("Very Slow"));
        this.q.addItem(ak.q("Slow"));
        this.q.addItem(ak.q("Normal"));
        this.q.addItem(ak.q("Fast"));
        this.q.addItem(ak.q("Very Fast"));
        this.q(ak.q("Message Scroll Speed"), this.q);
        this.q("", this.q);
        this.q("", this.e);
        this.q("", this.y);
        if (cs.q()) {
            this.q("", this.r);
            this.q("", this.t);
        }
    }
}
