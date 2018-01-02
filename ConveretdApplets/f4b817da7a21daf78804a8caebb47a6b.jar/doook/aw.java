// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public class aw extends aZ
{
    private aW g;
    
    public void a() {
    }
    
    public void c() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.g.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public aw(final aW g) {
        super(aG.a("Info"), g);
        this.g = g;
        D d = null;
        try {
            d = new D("http://www.doook.net", new URL("http://www.doook.net"));
        }
        catch (MalformedURLException ex) {}
        this.a(t.a + " 3.0.0", aK.g, false);
        final D d2 = new D(aG.a("Copyright 2002-2007 by DoookNet Technologies, Inc.\nAll rights reserved."));
        d2.b(1);
        d2.resize(300, 20);
        this.a(d2, 2);
        d.b(1);
        this.a(d, 2);
        final D d3 = new D(aG.a("Developed by DoookNet Technologies.\n\nThank you to all of team DoookNet.\n\n"));
        d3.resize(350, 20);
        this.a(d3, 2);
        this.a(aG.a("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(aG.a("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
