// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Choice;

public final class bh extends cV
{
    private Choice q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private ap q;
    private Checkbox y;
    private Color q;
    
    public final void q() {
        this.q.z = (this.q.getState() || !this.q.isEnabled());
        this.q.A = this.q.getSelectedIndex();
        this.q.l = this.e.getState();
        this.q.a = this.w.getState();
        if (this.q.s != this.y.getState() || this.q.w() != this.q.getRGB() || this.q.d != this.r.getState() || this.q.f != this.t.getState()) {
            this.q.s = this.y.getState();
            if (dN.q()) {
                this.q.d = this.r.getState();
                this.q.f = this.t.getState();
            }
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, this.q.u, this.q.getRGB()));
        }
    }
    
    public final void w() {
        if (dI.q(this.q.u, 61) || dI.q(this.q.u, 62)) {
            this.q.setEnabled(false);
            this.q.setState(true);
        }
        else {
            this.q.setState(this.q.z);
        }
        this.e.setState(this.q.l);
        this.q.select(this.q.A);
        this.r.setState(this.q.d);
        this.t.setState(this.q.f);
        this.w.setState(this.q.a);
        this.y.setState(this.q.s);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return be.w("Select how fast new chat messages will scroll.");
        }
        if (o == this.q) {
            if (this.q.isEnabled()) {
                return be.w("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return be.w("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else if (o == this.y) {
            if (this.y.getState()) {
                return be.w("Uncheck this box to receive private messages from other users.");
            }
            return be.w("Check this box to prevent other users from sending you private messages.");
        }
        else if (o == this.r) {
            if (this.r.getState()) {
                return be.w("Uncheck this box to show star.");
            }
            return be.w("Check this box to hide star.");
        }
        else {
            if (o != this.t) {
                return null;
            }
            if (this.t.getState()) {
                return be.w("Uncheck this box to show icon.");
            }
            return be.w("Check this box to hide icon.");
        }
    }
    
    public bh(final ap q) {
        super(be.w("Options"));
        this.q = Color.black;
        this.q = new dR();
        this.q = new Checkbox(be.w("Enable ChatWatch"));
        this.w = new Checkbox(be.w("Enable Buddies Tab"));
        this.e = new Checkbox(be.w("Enable Auto-Popup of Private Messages"));
        this.y = new Checkbox(be.w("Disable private messages from other users."));
        this.r = new Checkbox(be.w("Hide Star"));
        this.t = new Checkbox(be.w("Hide Icon"));
        ((Choice)(this.q = q)).addItem(be.w("Very Slow"));
        this.q.addItem(be.w("Slow"));
        this.q.addItem(be.w("Normal"));
        this.q.addItem(be.w("Fast"));
        this.q.addItem(be.w("Very Fast"));
        this.q(be.w("Message Scroll Speed"), this.q);
        this.q("", this.q);
        this.q("", this.e);
        this.q("", this.y);
        if (dN.q()) {
            this.q("", this.r);
            this.q("", this.t);
        }
    }
}
