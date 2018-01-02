// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.d;
import java.net.MalformedURLException;
import com.diginet.digichat.awt.an;
import com.esial.util.LanguageSupport;
import java.net.URL;
import java.awt.Event;
import com.diginet.digichat.awt.al;

public class cq extends al
{
    private g a;
    
    public final void a() {
    }
    
    public final void b() {
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.a.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public cq(final g a) {
        super(LanguageSupport.translate("Info"), a);
        this.a = a;
        an an = null;
        try {
            an = new an("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.a(DigiChatAppletAbstract.OEM_DigiChat + " " + com.diginet.digichat.common.d.a(), m.d, false);
        final an an2 = new an(LanguageSupport.translate("Copyright 1997-2005 by Digi-Net Technologies, Inc.\nAll rights reserved."));
        an2.a(1);
        an2.resize(300, 20);
        this.a(an2, 2);
        if (DigiChatAppletAbstract.OEM_DigiChat.equals("DigiChat")) {
            an.a(1);
            this.a(an, 2);
            final an an3 = new an(LanguageSupport.translate("Developed by Digi-Net Technologies.\n\n") + LanguageSupport.translate("Thank you to all of team Digi-Net.\n\n"));
            an3.resize(350, 20);
            this.a(an3, 2);
        }
        this.a(LanguageSupport.translate("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(LanguageSupport.translate("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
        if (a.z != null) {
            this.a(LanguageSupport.translate("Registered to: "), a.z);
        }
    }
}
