// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class aM extends bN
{
    private W q;
    
    public final void q() {
    }
    
    public final void w() {
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.arg instanceof URL) {
            this.q.q((URL)event.arg, "_blank");
            return true;
        }
        return super.handleEvent(event);
    }
    
    public aM(W q) {
        super(al.q("Info"));
        this.q = q;
        q = null;
        try {
            q = (W)new q("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.q(cs.e + " " + cs.r, be.t, false);
        final q q2 = new q(al.q(ch.getCopyRight()));
        final String url = ch.getURL();
        try {
            q = (W)new q("http://www." + url, new URL("http://www." + url));
        }
        catch (MalformedURLException ex2) {}
        final q q3;
        (q3 = new q(t.q(al.q("Developed by %1"), ch.getDevelopedBy()) + ".\n\n" + t.q(al.q("Thank you to all of %1"), ch.getDevelopedBy()) + ".\n\n" + ((ch.q() != null) ? (t.q(al.q("Phone: %1"), ch.q()) + ".\n") : "") + ((ch.w() != null) ? (t.q(al.q("E-mail: %1"), ch.w()) + ".\n\n") : ""))).resize(350, 20);
        q2.q(1);
        q2.resize(300, 20);
        this.q(q2, 2);
        ((q)q).q(1);
        this.q((Component)q, 2);
        this.q(q3, 2);
        this.q(al.q("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.q(al.q("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
