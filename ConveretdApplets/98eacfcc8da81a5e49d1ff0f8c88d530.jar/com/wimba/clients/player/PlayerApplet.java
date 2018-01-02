// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.player;

import java.awt.Component;
import java.text.MessageFormat;
import javax.swing.JApplet;
import java.util.ResourceBundle;
import VT_6_1_0_11.cV;
import com.hw.client.util.AbstractApplet;

public class PlayerApplet extends AbstractApplet implements cV
{
    private a d;
    private ResourceBundle e;
    private String f;
    
    public void b() {
        this.d = new a(this);
        this.e = this.d.a();
        this.f = this.e.getString("player");
        com.hw.client.util.a.d(MessageFormat.format(this.e.getString("vboard.applet.header"), this.f, PlayerApplet.c, PlayerApplet.b, PlayerApplet.a));
        this.getContentPane().add(this.d);
        com.hw.client.util.a.d(MessageFormat.format(this.e.getString("applet.initialized"), this.f));
    }
    
    public final void c() {
        com.hw.client.util.a.d(MessageFormat.format(this.e.getString("applet.destroy"), this.f));
        if (this.d != null) {
            this.d.o();
            this.d = null;
        }
        com.hw.client.util.a.d(MessageFormat.format(this.e.getString("applet.destroyed"), this.f));
    }
}
