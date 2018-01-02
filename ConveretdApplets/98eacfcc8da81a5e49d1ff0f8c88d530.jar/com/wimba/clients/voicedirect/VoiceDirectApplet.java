// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import java.awt.Component;
import javax.swing.JApplet;
import com.hw.client.util.a;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import VT_6_1_0_11.cV;
import com.hw.client.util.AbstractApplet;

public class VoiceDirectApplet extends AbstractApplet implements cV
{
    private g d;
    private ResourceBundle e;
    private final String f;
    
    public VoiceDirectApplet() {
        this.e = ResourceBundle.getBundle("strings");
        this.f = this.e.getString("voice.direct");
    }
    
    public void b() {
        com.hw.client.util.a.d(MessageFormat.format(this.e.getString("vboard.applet.header"), this.f, VoiceDirectApplet.c, VoiceDirectApplet.b, VoiceDirectApplet.a));
        this.d = new g(this);
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
