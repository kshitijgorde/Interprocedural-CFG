// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.lang;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

public class Context
{
    private static Applet applet;
    private static URL documentBase;
    private static boolean initialized;
    
    public static boolean isApplet() {
        return Context.applet != null;
    }
    
    public static boolean isApplication() {
        return Context.applet == null;
    }
    
    public static void setApplet(final Applet a) {
        Context.applet = a;
        Context.initialized = true;
    }
    
    public static Applet getApplet() {
        return Context.applet;
    }
    
    public static void setDocumentBase(final URL db) {
        if (System.getProperty("java.vendor").startsWith("Netscape") && db.getHost() == "" && System.getProperty("os.name").startsWith("Windows")) {
            String f = db.getFile();
            final String hd = f.substring(0, f.indexOf(58) + 1);
            f = f.substring(f.indexOf(58) + 1);
            try {
                Context.documentBase = new URL(db.getProtocol(), hd, f);
            }
            catch (MalformedURLException ex) {}
        }
        else {
            Context.documentBase = db;
        }
        Context.initialized = true;
    }
    
    private static void initializeApp() {
        final StringBuffer p = new StringBuffer(System.getProperty("user.dir"));
        final int pl = p.length();
        try {
            final char ps = System.getProperty("file.separator").charAt(0);
            if (ps != '/') {
                for (int counter = 0; counter < pl; ++counter) {
                    if (ps == p.charAt(counter)) {
                        p.setCharAt(counter, '/');
                    }
                }
            }
        }
        catch (StringIndexOutOfBoundsException ex) {}
        try {
            Context.documentBase = new URL("file:///" + (Object)p + "/");
        }
        catch (MalformedURLException ex2) {}
    }
    
    public static URL getDocumentBase() {
        if (!Context.initialized) {
            initializeApp();
            Context.initialized = true;
        }
        if (Context.documentBase != null) {
            return Context.documentBase;
        }
        if (Context.applet != null) {
            return Context.applet.getDocumentBase();
        }
        return null;
    }
}
