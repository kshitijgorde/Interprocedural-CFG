// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;

public final class bA extends D
{
    private bI q;
    
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
    
    public bA(bI q) {
        super(cv.q("Info"));
        this.q = q;
        q = null;
        try {
            q = (bI)new E("http://www.digichat.com", new URL("http://www.digichat.com"));
        }
        catch (MalformedURLException ex) {}
        this.q(a.e + " " + a.r, k.t, false);
        final E e = new E(cv.q(bw.getCopyRight()));
        final String url = bw.getURL();
        try {
            q = (bI)new E("http://www." + url, new URL("http://www." + url));
        }
        catch (MalformedURLException ex2) {}
        final E e2;
        (e2 = new E(cv.q(cv.q("Developed by %1"), bw.getDevelopedBy()) + ".\n\n" + cv.q(cv.q("Thank you to all of %1"), bw.getDevelopedBy()) + ".\n\n" + ((bw.q() != null) ? (cv.q(cv.q("Phone: %1"), bw.q()) + ".\n") : "") + ((bw.w() != null) ? (cv.q(cv.q("E-mail: %1"), bw.w()) + ".\n\n") : ""))).resize(350, 20);
        e.q(1);
        e.resize(300, 20);
        this.q(e, 2);
        ((E)q).q(1);
        this.q((Component)q, 2);
        this.q(e2, 2);
        this.q(cv.q("Operating System:"), System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + System.getProperty("os.arch") + ")");
        this.q(cv.q("Java Version:"), System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
    }
}
