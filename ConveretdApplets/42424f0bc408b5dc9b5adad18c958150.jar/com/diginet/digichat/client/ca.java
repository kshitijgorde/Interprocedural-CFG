// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.awt.p;
import com.diginet.digichat.common.e;
import java.net.MalformedURLException;
import com.diginet.digichat.awt.ai;
import com.esial.util.d;
import java.net.URL;
import java.awt.Event;
import com.diginet.digichat.awt.ag;

public class ca extends ag
{
    private h a;
    
    public void a() {
    }
    
    public void b() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.a.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public ca(final h a) {
        super(com.esial.util.d.a("Info"), a);
        this.a = a;
        ai ai = null;
        try {
            ai = new ai("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.a(DigiChatAppletAbstract.OEM_DigiChat + " " + com.diginet.digichat.common.e.a(), p.d, false);
        final ai ai2 = new ai(com.esial.util.d.a("Copyright 1997-2002 by Digi-Net Technologies, Inc.\nAll rights reserved."));
        ai2.a(1);
        ai2.resize(300, 20);
        this.a(ai2, 2);
        if (DigiChatAppletAbstract.OEM_DigiChat.equals("DigiChat")) {
            ai.a(1);
            this.a(ai, 2);
            final ai ai3 = new ai(com.esial.util.d.a("Developed by Digi-Net Technologies.\n\n") + com.esial.util.d.a("Thank you to all of team Digi-Net.\n\n"));
            ai3.resize(350, 20);
            this.a(ai3, 2);
        }
        this.a(com.esial.util.d.a("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(com.esial.util.d.a("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
        if (a.t != null) {
            this.a(com.esial.util.d.a("Registered to: "), a.t);
        }
    }
}
