// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.applet.AppletContext;
import java.net.URL;
import java.applet.AppletStub;

public class NFAppletStub implements AppletStub
{
    URL a;
    URL b;
    public static boolean DEBUG;
    
    public NFAppletStub(final URL url) {
        this(url, null);
    }
    
    public NFAppletStub(final URL a, final URL b) {
        this.a = null;
        this.b = null;
        this.a = a;
        this.b = b;
    }
    
    private void a(final String s) {
        if (NFAppletStub.DEBUG) {
            System.out.println(s);
        }
    }
    
    public boolean isActive() {
        this.a(this + " isActive");
        return false;
    }
    
    public URL getDocumentBase() {
        this.a(this + " getDocumentBase");
        this.a("returning: " + this.a);
        return this.a;
    }
    
    public URL getCodeBase() {
        this.a(this + " getCodeBase");
        this.a("returning: " + this.b);
        return this.b;
    }
    
    public String getParameter(final String s) {
        this.a(this + " getParameter(" + s + ")");
        return null;
    }
    
    public AppletContext getAppletContext() {
        this.a(this + " getAppletContext()");
        return null;
    }
    
    public void appletResize(final int n, final int n2) {
        this.a(this + " appletResize(" + n + "," + n2 + ")");
    }
    
    static {
        NFAppletStub.DEBUG = false;
    }
}
