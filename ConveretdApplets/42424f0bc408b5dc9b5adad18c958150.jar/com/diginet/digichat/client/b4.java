// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.esial.util.d;
import com.diginet.digichat.network.v;
import java.awt.Checkbox;
import java.awt.Choice;
import com.diginet.digichat.awt.ag;

public class b4 extends ag
{
    private Choice a;
    private Checkbox b;
    Checkbox c;
    Checkbox d;
    private h e;
    
    public void a() {
        this.e.b5 = (this.b.getState() || !this.b.isEnabled());
        this.e.bo = this.a.getSelectedIndex();
        this.e.a9 = this.d.getState();
        this.e.o = this.c.getState();
    }
    
    public void b() {
        if (v.a(this.e.as, 61) || v.a(this.e.as, 62)) {
            this.b.disable();
            this.b.setState(true);
        }
        else {
            this.b.setState(this.e.b5);
        }
        this.d.setState(this.e.a9);
        this.a.select(this.e.bo);
        this.c.setState(this.e.o);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return com.esial.util.d.a("Select how fast new chat messages will scroll.");
        }
        if (o != this.b) {
            return null;
        }
        if (this.b.isEnabled()) {
            return com.esial.util.d.a("Check this box to enable ChatWatch.  ChatWatch replaces vulgarities with less offensive words.");
        }
        return com.esial.util.d.a("This item is disabled because ChatWatch has been turned on for all users.");
    }
    
    public b4(final h e) {
        super(com.esial.util.d.a("Options"), e);
        this.a = new Choice();
        this.b = new Checkbox(com.esial.util.d.a("Enable ChatWatch"));
        this.c = new Checkbox(com.esial.util.d.a("Enable Buddies Tab"));
        this.d = new Checkbox(com.esial.util.d.a("Enable Auto-Popup of Private Messages"));
        this.e = e;
        this.a.addItem(com.esial.util.d.a("Very Slow"));
        this.a.addItem(com.esial.util.d.a("Slow"));
        this.a.addItem(com.esial.util.d.a("Normal"));
        this.a.addItem(com.esial.util.d.a("Fast"));
        this.a.addItem(com.esial.util.d.a("Very Fast"));
        this.a(com.esial.util.d.a("Message Scroll Speed"), this.a);
        this.a("", this.b);
        this.a("", this.d);
        if (!e.a7) {
            this.a("", this.c);
        }
    }
}
