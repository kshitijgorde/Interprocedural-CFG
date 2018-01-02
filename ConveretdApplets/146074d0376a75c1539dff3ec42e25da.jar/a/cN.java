// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class cN extends G
{
    private cV q;
    
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
    
    public cN(cV q) {
        super(eb.q("Info"));
        this.q = q;
        q = null;
        try {
            q = (cV)new H("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.q(a.e + " " + a.r, m.t, false);
        final H h = new H(eb.q(cJ.getCopyRight()));
        final String url = cJ.getURL();
        try {
            q = (cV)new H("http://www." + url, new URL("http://www." + url));
        }
        catch (MalformedURLException ex2) {}
        final H h2;
        (h2 = new H(ec.q(eb.q("Developed by %1"), cJ.getDevelopedBy()) + ".\n\n" + ec.q(eb.q("Thank you to all of %1"), cJ.getDevelopedBy()) + ".\n\n" + ((cJ.q() != null) ? (ec.q(eb.q("Phone: %1"), cJ.q()) + ".\n") : "") + ((cJ.w() != null) ? (ec.q(eb.q("E-mail: %1"), cJ.w()) + ".\n\n") : ""))).resize(350, 20);
        h.q(1);
        h.resize(300, 20);
        this.q(h, 2);
        ((H)q).q(1);
        this.q((Component)q, 2);
        this.q(h2, 2);
        this.q(eb.q("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.q(eb.q("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
