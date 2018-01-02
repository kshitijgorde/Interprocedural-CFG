// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.net.URL;
import netscape.javascript.JSObject;
import java.applet.Applet;

public class BsscHelpRedirector
{
    private static Applet m_applet;
    private static String m_sFrameName;
    
    private static void doJS(final String s) {
        if (BsscHelpRedirector.m_applet != null) {
            try {
                JSObject.getWindow(BsscHelpRedirector.m_applet).eval(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static boolean showDoc(final URL url) {
        return showDoc(url, BsscHelpRedirector.m_sFrameName);
    }
    
    public static boolean showDoc(final URL url, final String s) {
        if (BsscHelpRedirector.m_applet != null) {
            if (s != null) {
                if (System.getProperty("java.vendor").indexOf("Netscape") == -1 && !System.getProperty("os.name").startsWith("MacOS")) {
                    redirect(url, s);
                }
                else {
                    BsscHelpRedirector.m_applet.getAppletContext().showDocument(url, s);
                }
            }
            else {
                BsscHelpRedirector.m_applet.getAppletContext().showDocument(url, "_self");
            }
            return true;
        }
        return false;
    }
    
    public static boolean doJavaScript(final String s) {
        if (BsscHelpRedirector.m_applet != null) {
            if (!System.getProperty("os.name").startsWith("MacOS")) {
                doJS(s);
            }
            return true;
        }
        return false;
    }
    
    public static void showStatus(final String s) {
        try {
            if (BsscHelpRedirector.m_applet != null) {
                BsscHelpRedirector.m_applet.showStatus(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public static void initRedirector(final Applet applet, final String sFrameName) {
        BsscHelpRedirector.m_applet = applet;
        BsscHelpRedirector.m_sFrameName = sFrameName;
    }
    
    private static void redirect(final URL url, final String s) {
        if (BsscHelpRedirector.m_applet != null) {
            try {
                JSObject.getWindow(BsscHelpRedirector.m_applet).eval("window.open(\"" + url.toString() + "\", \"" + s + "\");");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
