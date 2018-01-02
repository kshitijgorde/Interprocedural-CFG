// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public class A extends o
{
    private u b;
    
    public void c() {
    }
    
    public void d() {
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.b.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public A(final u b) {
        super(ao.e("Info"), b);
        this.b = b;
        c c = null;
        try {
            c = new c("http://www.doook.net", new URL("http://www.doook.net"));
        }
        catch (MalformedURLException ex) {}
        this.a(z.G + "  3.0.0", bL.g, false);
        final c c2 = new c(ao.e("Copyright 2002-2007 DoookNet Technologies, Inc.\nAll rights reserved."));
        c2.a(1);
        c2.resize(300, 20);
        this.a(c2, 2);
        c.a(1);
        this.a(c, 2);
        final c c3 = new c("Developed by DoookNet Technologies.\n\nThank you to all of team DoookNet.\n\n");
        c3.resize(350, 20);
        this.a(c3, 2);
        this.a(ao.e("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(ao.e("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
