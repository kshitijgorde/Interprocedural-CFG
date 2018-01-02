// 
// Decompiled by Procyon v0.5.30
// 

package jd;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.applet.Applet;

public class JD extends Applet
{
    public void start() {
        final String version = System.getProperty("java.version");
        final String vendor = System.getProperty("java.vendor");
        final String osarch = System.getProperty("os.arch");
        final String osname = System.getProperty("os.name");
        final String osversion = System.getProperty("os.version");
        final String[] props = { version, vendor, osarch + " " + osname + " " + osversion };
        try {
            JSObject.getWindow((Applet)this).call(this.getParameter("cb"), (Object[])props);
        }
        catch (JSException ex) {
            ((Throwable)ex).printStackTrace();
        }
    }
}
