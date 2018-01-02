// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class E extends D
{
    private bl a;
    
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
    
    public E(final bl a) {
        super(ak.a(545), a);
        this.a = a;
        t t = null;
        try {
            t = new t("http://" + a.g, new URL("http://" + a.g));
        }
        catch (MalformedURLException ex) {}
        this.a(a.g + " " + aK.a(), ay.d, false);
        final t t2;
        (t2 = new t("Copyright 1997-2009 by " + a.g + " Technologies, Inc.\nAll rights reserved.")).a();
        t2.resize(300, 20);
        this.a(t2);
        t.a();
        this.a(t);
        final t t3;
        (t3 = new t("Developed by " + a.g + " Technologies.\n\n" + "Thank you to all of team " + a.g + ".\n\n")).resize(350, 20);
        this.a(t3);
        this.a(ak.a(546), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.a(ak.a(547), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
