// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.player;

import javax.swing.JComponent;
import javax.swing.JApplet;
import VT_6_1_0_11.l;

public final class a extends l
{
    private c w;
    
    public a(final JApplet applet) {
        super(applet);
        this.o = "recorder";
        this.setName(this.a().getString("voice.authoring"));
    }
    
    protected final void d() {
        this.n();
    }
    
    protected final boolean c() {
        final String parameter;
        return (parameter = this.t.getParameter("_debug")) != null && parameter.equals("_1234_");
    }
    
    protected final void p() {
        this.a(this.w = new c(this, this.b));
        this.f(null);
        final c w;
        (w = this.w).c().n().c();
        w.setVisible(true);
        this.revalidate();
    }
}
