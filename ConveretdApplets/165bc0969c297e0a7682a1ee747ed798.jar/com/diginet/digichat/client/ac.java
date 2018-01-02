// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.ah;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Frame;
import java.io.IOException;
import com.diginet.digichat.awt.ad;

public class ac extends ad
{
    private h a;
    private ct opt;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public void a() {
        try {
            this.a.c();
            this.a.saveProf();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enableBuddies(final boolean b) {
        this.opt.enableBuddies(b);
    }
    
    public ac(final Frame frame, final h a) {
        super(frame, a5.a(com.esial.util.c.a("%1 Settings"), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), a);
        this.a = a;
        if (!a.b8) {
            this.a(new cp(a));
        }
        this.a(this.opt = new ct(a));
        if (a.i(109)) {
            this.a(new Lists(a, this));
        }
        this.a(new cu(a));
        if (a.a1 != null) {
            this.a(new cy(a));
        }
        this.a(new cz(a));
    }
}
