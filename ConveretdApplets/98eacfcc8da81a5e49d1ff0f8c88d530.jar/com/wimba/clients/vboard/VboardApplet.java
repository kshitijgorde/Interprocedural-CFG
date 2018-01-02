// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.awt.Component;
import com.hw.client.util.a;
import java.text.MessageFormat;
import javax.swing.JApplet;
import java.applet.Applet;
import VT_6_1_0_11.ca;
import java.util.ResourceBundle;
import VT_6_1_0_11.cV;
import com.hw.client.util.AbstractApplet;

public class VboardApplet extends AbstractApplet implements cV
{
    private n d;
    private String e;
    private ResourceBundle f;
    
    public void b() {
        this.e = ca.a(this, "w_type", "").trim();
        this.d = new n(this);
        this.f = this.d.a();
        com.hw.client.util.a.d(MessageFormat.format(this.f.getString("vboard.applet.header"), this.e, VboardApplet.c, VboardApplet.b, VboardApplet.a));
        this.getContentPane().add(this.d);
        com.hw.client.util.a.d(MessageFormat.format(this.f.getString("applet.initialized"), this.e));
    }
    
    public final void c() {
        com.hw.client.util.a.d(MessageFormat.format(this.f.getString("applet.destroy"), this.e));
        if (this.d != null) {
            this.d.o();
            this.d = null;
        }
        com.hw.client.util.a.d(MessageFormat.format(this.f.getString("applet.destroyed"), this.e));
    }
}
