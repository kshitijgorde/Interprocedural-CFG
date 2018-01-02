// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vmail;

import java.awt.Component;
import javax.swing.JApplet;
import com.hw.client.util.a;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import VT_6_1_0_11.cV;
import com.hw.client.util.AbstractApplet;

public class VmailApplet extends AbstractApplet implements cV
{
    private b d;
    private final ResourceBundle e;
    private final String f;
    
    public VmailApplet() {
        this.e = ResourceBundle.getBundle("strings");
        this.f = this.e.getString("voice.email");
    }
    
    public void b() {
        com.hw.client.util.a.d(MessageFormat.format(this.e.getString("vboard.applet.header"), this.f, VmailApplet.c, VmailApplet.b, VmailApplet.a));
        this.d = new b(this);
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
