// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

import netscape.security.PrivilegeManager;
import java.applet.Applet;
import netscape.javascript.JSObject;

public class Util
{
    private static JSObject window;
    private static JSObject document;
    private static JSObject location;
    private static String expireNow;
    private static String cookie;
    private static String domain;
    private static String life;
    private static String insCookieName;
    private static String eraseCookieLife;
    private static String eraseInsCookie;
    
    public static String getInstallCookie(final Applet applet) {
        if (getJSClass() != null) {
            try {
                Util.window = JSObject.getWindow(applet);
                Util.document = (JSObject)Util.window.getMember("document");
                Util.cookie = (String)Util.document.getMember("cookie");
            }
            catch (Exception ex) {}
        }
        return Util.cookie;
    }
    
    public static boolean setInstallCookie(final String s, final Applet applet, final Boolean b) {
        if (getJSClass() != null) {
            try {
                Util.window = JSObject.getWindow(applet);
                if (b) {
                    PrivilegeManager.enablePrivilege("UniversalBrowserAccess");
                    Util.life = parseCookieLife((String)Util.window.eval("document.cookie;"));
                    Util.cookie = Util.insCookieName + "=true; " + Util.life + "; path=/;";
                    Util.window.eval("document.cookie=\"" + Util.eraseCookieLife + "\";");
                    Util.window.eval("document.cookie=\"" + Util.cookie + "\";");
                    PrivilegeManager.disablePrivilege("UniversalBrowserAccess");
                    return true;
                }
                Util.document = (JSObject)Util.window.getMember("document");
                Util.location = (JSObject)Util.window.getMember("location");
                if (getInstallCookie(applet).indexOf(Util.insCookieName + "=true") != -1) {
                    return true;
                }
                Util.life = parseCookieLife((String)Util.document.getMember("cookie"));
                Util.window.eval("document.cookie=\"" + Util.eraseCookieLife + "\";");
                Util.domain = parseDomain((String)Util.location.getMember("hostname"));
                Util.cookie = Util.insCookieName + "=" + s + "; " + Util.life + "; path=/;" + Util.domain;
                Util.document.setMember("cookie", (Object)Util.cookie);
                return true;
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    public static boolean UninstallCookie(final Applet applet, final Boolean b) {
        if (getJSClass() != null) {
            try {
                Util.window = JSObject.getWindow(applet);
                if (b) {
                    PrivilegeManager.enablePrivilege("UniversalBrowserAccess");
                    Util.window.eval("document.cookie=\"" + Util.eraseInsCookie + "\";");
                    PrivilegeManager.disablePrivilege("UniversalBrowserAccess");
                    return true;
                }
                Util.document = (JSObject)Util.window.getMember("document");
                Util.location = (JSObject)Util.window.getMember("location");
                Util.cookie = (String)Util.document.getMember("cookie");
                if (Util.cookie.indexOf("DominoApplets") != -1 && Util.cookie.indexOf("true") != -1) {
                    Util.domain = parseDomain((String)Util.location.getMember("hostname"));
                    Util.document.setMember("cookie", (Object)(Util.eraseInsCookie + Util.domain));
                    return true;
                }
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    private static Class getJSClass() {
        Class<?> forName;
        try {
            forName = Class.forName("netscape.javascript.JSObject");
        }
        catch (ClassNotFoundException ex) {
            forName = null;
        }
        return forName;
    }
    
    private static String parseCookieLife(String substring) {
        if (substring != null && substring.indexOf("CookieLife=") != -1) {
            substring = substring.substring(substring.indexOf("expires="), substring.length());
        }
        else {
            substring = "";
        }
        return substring;
    }
    
    private static String parseDomain(String string) {
        if (string != null) {
            final int index = string.indexOf(".");
            if (index != -1) {
                string = " domain=" + string.substring(index, string.length()) + ";";
            }
            else {
                string = "";
            }
        }
        else {
            string = "";
        }
        return string;
    }
    
    static {
        Util.expireNow = "expires=Thu, 01-Jan-1970 00:00:01 GMT;";
        Util.cookie = "";
        Util.domain = "";
        Util.life = "";
        Util.insCookieName = "DominoApplets";
        Util.eraseCookieLife = "CookieLife=null; " + Util.expireNow + " path=/;";
        Util.eraseInsCookie = Util.insCookieName + "=false; " + Util.expireNow + " path=/;";
    }
}
