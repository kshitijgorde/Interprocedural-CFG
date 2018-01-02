// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class X extends S
{
    private cx a;
    
    public final void b() {
    }
    
    public final void a() {
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.a.a((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public X(final cx a) {
        super(aS.a(545), a);
        this.a = a;
        A a2 = null;
        try {
            a2 = new A("http://" + a.p, new URL("http://" + a.p));
        }
        catch (MalformedURLException ex) {}
        this.a(a.p + " " + bB.a(), bk.d, false);
        final A a3;
        (a3 = new A("Copyright 1997-2009 by " + a.p + " Technologies, Inc.\nAll rights reserved.")).a();
        a3.resize(300, 20);
        this.a(a3);
        a2.a();
        this.a(a2);
        final A a4;
        (a4 = new A("Developed by " + a.p + " Technologies.\n\n" + "Thank you to all of team " + a.p + ".\n\n")).resize(350, 20);
        this.a(a4);
        this.a(aS.a(546), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(aS.a(547), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
