// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Choice;

public final class de extends G
{
    private Choice q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private cV q;
    private Checkbox y;
    private Color q;
    
    public final void q() {
        this.q.z = (this.q.getState() || !this.q.isEnabled());
        this.q.c = this.q.getSelectedIndex();
        this.q.l = this.e.getState();
        this.q.a = this.w.getState();
        if (this.q.s != this.y.getState() || this.q.y() != this.q.getRGB() || this.q.d != this.r.getState() || this.q.f != this.t.getState()) {
            this.q.s = this.y.getState();
            if (a.q()) {
                this.q.d = this.r.getState();
                this.q.f = this.t.getState();
            }
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, this.q.s, this.q.getRGB()));
        }
    }
    
    public final void w() {
        if (es.q(this.q.q, 61) || es.q(this.q.q, 62)) {
            this.q.setEnabled(false);
            this.q.setState(true);
        }
        else {
            this.q.setState(this.q.z);
        }
        this.e.setState(this.q.l);
        this.q.select(this.q.c);
        this.r.setState(this.q.d);
        this.t.setState(this.q.f);
        this.w.setState(this.q.a);
        this.y.setState(this.q.s);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return eb.q("Select how fast new chat messages will scroll.");
        }
        if (o == this.q) {
            if (this.q.isEnabled()) {
                return eb.q("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
            }
            return eb.q("This item is disabled because ChatWatch has been turned on for all users.");
        }
        else if (o == this.y) {
            if (this.y.getState()) {
                return eb.q("Uncheck this box to receive private messages from other users.");
            }
            return eb.q("Check this box to prevent other users from sending you private messages.");
        }
        else if (o == this.r) {
            if (this.r.getState()) {
                return eb.q("Uncheck this box to show star.");
            }
            return eb.q("Check this box to hide star.");
        }
        else {
            if (o != this.t) {
                return null;
            }
            if (this.t.getState()) {
                return eb.q("Uncheck this box to show icon.");
            }
            return eb.q("Check this box to hide icon.");
        }
    }
    
    public de(final cV q) {
        super(eb.q("Options"));
        this.q = Color.black;
        this.q = new h();
        this.q = new Checkbox(eb.q("Enable ChatWatch"));
        this.w = new Checkbox(eb.q("Enable Buddies Tab"));
        this.e = new Checkbox(eb.q("Enable Auto-Popup of Private Messages"));
        this.y = new Checkbox(eb.q("Disable private messages from other users."));
        this.r = new Checkbox(eb.q("Hide Star"));
        this.t = new Checkbox(eb.q("Hide Icon"));
        ((Choice)(this.q = q)).addItem(eb.q("Very Slow"));
        this.q.addItem(eb.q("Slow"));
        this.q.addItem(eb.q("Normal"));
        this.q.addItem(eb.q("Fast"));
        this.q.addItem(eb.q("Very Fast"));
        this.q(eb.q("Message Scroll Speed"), this.q);
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
