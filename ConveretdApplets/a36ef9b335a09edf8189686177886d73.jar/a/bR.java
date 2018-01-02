// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Choice;

public final class bR extends D
{
    private Choice q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private bI q;
    private Checkbox y;
    private Color q;
    
    public final void q() {
        this.q.l = (this.q.getState() || !this.q.isEnabled());
        this.q.x = this.q.getSelectedIndex();
        this.q.k = this.e.getState();
        this.q.p = this.w.getState();
        if (this.q.a != this.y.getState() || this.q.r() != this.q.getRGB() || this.q.s != this.r.getState() || this.q.d != this.t.getState()) {
            this.q.a = this.y.getState();
            if (a.q()) {
                this.q.s = this.r.getState();
                this.q.d = this.t.getState();
            }
            this.q.q(bo.q(this.q.a, this.q.s, this.q.d, this.q.f, this.q.p, this.q.a, this.q.getRGB()));
        }
    }
    
    public final void w() {
        if (cJ.q(this.q.q, 61) || cJ.q(this.q.q, 62)) {
            this.q.setEnabled(false);
            this.q.setState(true);
        }
        else {
            this.q.setState(this.q.l);
        }
        this.e.setState(this.q.k);
        this.q.select(this.q.x);
        this.r.setState(this.q.s);
        this.t.setState(this.q.d);
        this.w.setState(this.q.p);
        this.y.setState(this.q.a);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return cv.q("Select how fast new chat messages will scroll.");
        }
        if (o == this.q) {
            if (this.q.isEnabled()) {
                return cv.q("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return cv.q("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else if (o == this.y) {
            if (this.y.getState()) {
                return cv.q("Uncheck this box to receive private messages from other users.");
            }
            return cv.q("Check this box to prevent other users from sending you private messages.");
        }
        else if (o == this.r) {
            if (this.r.getState()) {
                return cv.q("Uncheck this box to show star.");
            }
            return cv.q("Check this box to hide star.");
        }
        else {
            if (o != this.t) {
                return null;
            }
            if (this.t.getState()) {
                return cv.q("Uncheck this box to show icon.");
            }
            return cv.q("Check this box to hide icon.");
        }
    }
    
    public bR(final bI q) {
        super(cv.q("Options"));
        this.q = Color.black;
        this.q = new f();
        this.q = new Checkbox(cv.q("Enable ChatWatch"));
        this.w = new Checkbox(cv.q("Enable Buddies Tab"));
        this.e = new Checkbox(cv.q("Enable Auto-Popup of Private Messages"));
        this.y = new Checkbox(cv.q("Disable private messages from other users."));
        this.r = new Checkbox(cv.q("Hide Star"));
        this.t = new Checkbox(cv.q("Hide Icon"));
        ((Choice)(this.q = q)).addItem(cv.q("Very Slow"));
        this.q.addItem(cv.q("Slow"));
        this.q.addItem(cv.q("Normal"));
        this.q.addItem(cv.q("Fast"));
        this.q.addItem(cv.q("Very Fast"));
        this.q(cv.q("Message Scroll Speed"), this.q);
        this.q("", this.q);
        this.q("", this.e);
        this.q("", this.y);
        if (a.q()) {
            if (this.q.q(77)) {
                this.q("", this.r);
            }
            if (this.q.q(78)) {
                this.q("", this.t);
            }
        }
    }
}
