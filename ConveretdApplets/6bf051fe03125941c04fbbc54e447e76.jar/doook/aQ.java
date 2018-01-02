// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public class aQ extends W
{
    private at a;
    
    public void f() {
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
    
    public aQ(final at a) {
        super(ar.b("Info"), a);
        this.a = a;
        R r = null;
        try {
            r = new R("http://www.doook.net", new URL("http://www.doook.net"));
        }
        catch (MalformedURLException ex) {}
        this.a(bi.Q + " 3.0.0", ay.b, false);
        final R r2 = new R(ar.b("Copyright 2002-2007 by DoookNet Technologies, Inc.\nAll rights reserved."));
        r2.a(1);
        r2.resize(300, 20);
        this.a(r2, 2);
        r.a(1);
        this.a(r, 2);
        final R r3 = new R(ar.b("Developed by DoookNet Technologies.\n\nThank you to all of team DoookNet.\n\n"));
        r3.resize(350, 20);
        this.a(r3, 2);
        this.a(ar.b("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(ar.b("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
