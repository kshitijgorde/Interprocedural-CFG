// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.util.a5;
import java.util.Calendar;
import com.diginet.digichat.awt.dw;
import java.net.MalformedURLException;
import com.diginet.digichat.awt.aw;
import com.esial.util.c;
import java.net.URL;
import java.awt.Event;
import com.diginet.digichat.awt.ah;

public class cz extends ah
{
    private h a;
    
    public void b() {
    }
    
    public void c() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.a.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public cz(final h a) {
        super(com.esial.util.c.a("Info"), a);
        this.a = a;
        aw aw = null;
        String strHome;
        if ((strHome = a.strHome) == null) {
            strHome = "http://www.digi-plus.com";
        }
        try {
            aw = new aw(strHome, new URL(strHome));
        }
        catch (MalformedURLException ex) {}
        this.a((a.strVers == null) ? "DigiPlus 2010 Version 9.0" : a.strVers, dw.d, false);
        final aw aw2 = new aw(String.valueOf(String.valueOf(a5.a(com.esial.util.c.a("Copyright 2005-%1 by %2, Inc."), new String[] { Integer.toString(Calendar.getInstance().get(1)), DigiChatAppletAbstract.OEM_DigiChat })).concat(String.valueOf('\n'))).concat(String.valueOf(com.esial.util.c.a("All rights reserved."))));
        aw2.a(1);
        aw2.resize(300, 20);
        this.a(aw2, 2);
        aw.a(1);
        this.a(aw, 2);
        final aw aw3 = new aw(String.valueOf(String.valueOf(String.valueOf(a5.a(com.esial.util.c.a("Developed by %1 Development Team."), new String[] { DigiChatAppletAbstract.OEM_DigiChat })).concat(String.valueOf("\n\n"))).concat(String.valueOf(a5.a(com.esial.util.c.a("Thank you to all of %1 Development Team."), new String[] { DigiChatAppletAbstract.OEM_DigiChat })))).concat(String.valueOf("\n\n")));
        aw3.resize(350, 20);
        this.a(aw3, 2);
        this.a(com.esial.util.c.a("Operating System:"), String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(System.getProperty("os.name")).concat(String.valueOf(" "))).concat(String.valueOf(System.getProperty("os.version")))).concat(String.valueOf(" ("))).concat(String.valueOf(System.getProperty("os.arch")))).concat(String.valueOf(")")));
        this.a(com.esial.util.c.a("Java Version:"), String.valueOf(String.valueOf(System.getProperty("java.vendor")).concat(String.valueOf(" "))).concat(String.valueOf(System.getProperty("java.version"))));
    }
}
