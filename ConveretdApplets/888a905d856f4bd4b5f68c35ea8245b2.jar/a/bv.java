// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class bv extends cV
{
    private ap q;
    
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
    
    public bv(ap q) {
        super(be.w("Info"));
        this.q = q;
        q = null;
        try {
            q = (ap)new u("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.q(dN.e + " " + dN.r, cb.t, false);
        final u u = new u(be.w(dz.getCopyRight()));
        final String url = dz.getURL();
        try {
            q = (ap)new u("http://www." + url, new URL("http://www." + url));
        }
        catch (MalformedURLException ex2) {}
        final u u2;
        (u2 = new u(B.q(be.w("Developed by %1"), dz.getDevelopedBy()) + ".\n\n" + B.q(be.w("Thank you to all of %1"), dz.getDevelopedBy()) + ".\n\n" + ((dz.q() != null) ? (B.q(be.w("Phone: %1"), dz.q()) + ".\n") : "") + ((dz.w() != null) ? (B.q(be.w("E-mail: %1"), dz.w()) + ".\n\n") : ""))).resize(350, 20);
        u.q(1);
        u.resize(300, 20);
        this.q(u, 2);
        ((u)q).q(1);
        this.q((Component)q, 2);
        this.q(u2, 2);
        this.q(be.w("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.q(be.w("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
