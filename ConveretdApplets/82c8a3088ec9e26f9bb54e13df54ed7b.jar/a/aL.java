// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class aL extends bM
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
    
    public aL(W q) {
        super(ak.q("Info"));
        this.q = q;
        q = null;
        try {
            q = (W)new p("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.q(cs.e + " " + cs.r, bd.t, false);
        final p p = new p(ak.q(ch.getCopyRight()));
        final String url = ch.getURL();
        try {
            q = (W)new p("http://www." + url, new URL("http://www." + url));
        }
        catch (MalformedURLException ex2) {}
        final p p2;
        (p2 = new p(s.q(ak.q("Developed by %1"), ch.getDevelopedBy()) + ".\n\n" + s.q(ak.q("Thank you to all of %1"), ch.getDevelopedBy()) + ".\n\n" + ((ch.q() != null) ? (s.q(ak.q("Phone: %1"), ch.q()) + ".\n") : "") + ((ch.w() != null) ? (s.q(ak.q("E-mail: %1"), ch.w()) + ".\n\n") : ""))).resize(350, 20);
        p.q(1);
        p.resize(300, 20);
        this.q(p, 2);
        ((p)q).q(1);
        this.q((Component)q, 2);
        this.q(p2, 2);
        this.q(ak.q("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.q(ak.q("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
