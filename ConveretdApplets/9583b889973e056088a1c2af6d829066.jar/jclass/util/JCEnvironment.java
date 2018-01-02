// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.applet.AppletContext;
import java.awt.Container;
import java.applet.Applet;
import java.awt.Component;

public class JCEnvironment
{
    static int NOVALUE;
    static int os;
    static int java_version;
    public static final int OS_OTHER = 0;
    public static final int OS_UNIX = 1;
    public static final int BROWSER_UNKNOWN;
    public static final int BROWSER_OTHER = 1;
    public static final int BROWSER_INTERPRETER = 1;
    public static final int BROWSER_APPLETVIEWER = 2;
    public static final int BROWSER_NETSCAPE = 3;
    public static final int BROWSER_EXPLORER = 4;
    public static final int BROWSER_VISUALAGE = 5;
    public static String versionString;
    public static String vendorString;
    public static final boolean JAVA_OS;
    
    public static Applet getApplet(final Component component) {
        if (component == null) {
            return null;
        }
        if (component instanceof Applet) {
            return (Applet)component;
        }
        Container parent;
        Container parent2;
        for (parent = component.getParent(), parent2 = component.getParent(); parent2 != null && !(parent2 instanceof Applet); parent2 = parent, parent = ((parent == null) ? null : parent.getParent())) {}
        return (Applet)parent2;
    }
    
    public static AppletContext getAppletContext(final Applet applet) {
        if (applet != null) {
            try {
                return applet.getAppletContext();
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public static boolean inBrowser(final Component component) {
        return getAppletContext(getApplet(component)) != null;
    }
    
    public static int getOS() {
        if (JCEnvironment.os != JCEnvironment.NOVALUE) {
            return JCEnvironment.os;
        }
        final String property = System.getProperty("os.name");
        JCEnvironment.os = 0;
        if (property != null && (property.equalsIgnoreCase("SunOS") || property.equalsIgnoreCase("Solaris"))) {
            JCEnvironment.os = 1;
        }
        return JCEnvironment.os;
    }
    
    public static boolean isBrowserExplorer(final Component component) {
        return getBrowser(component) == 4;
    }
    
    public static boolean isBrowserAppletViewer(final Component component) {
        return getBrowser(component) == 2;
    }
    
    public static boolean isBrowserVisualAge(final Component component) {
        return getBrowser(component) == 5;
    }
    
    public static boolean isVisualAge() {
        return getVendorString().indexOf("IBM") != -1;
    }
    
    public static boolean isJBuilder() {
        return getVersionString().indexOf("Borland") != -1;
    }
    
    public static synchronized int getBrowser(final Component component) {
        if (component.getPeer() == null) {
            return JCEnvironment.BROWSER_UNKNOWN;
        }
        if (!inBrowser(component)) {
            return 1;
        }
        final String property = System.getProperty("java.vendor");
        if (property.indexOf("Sun") != -1) {
            return 2;
        }
        if (property.indexOf("Netscape") != -1) {
            return 3;
        }
        if (property.indexOf("Microsoft") != -1) {
            return 4;
        }
        if (property.indexOf("IBM") != -1) {
            return 5;
        }
        return 1;
    }
    
    public static int getJavaVersion() {
        if (JCEnvironment.java_version != JCEnvironment.NOVALUE) {
            return JCEnvironment.java_version;
        }
        final String versionString = getVersionString();
        String string = "";
        for (int i = 0; i < versionString.length(); ++i) {
            final char char1 = versionString.charAt(i);
            if (Character.isDigit(char1)) {
                string = String.valueOf(string) + char1;
            }
        }
        try {
            JCEnvironment.java_version = Integer.parseInt(string);
            if (JCEnvironment.java_version > 0 && JCEnvironment.java_version < 100) {
                JCEnvironment.java_version *= 10;
            }
        }
        catch (NumberFormatException ex) {
            JCEnvironment.java_version = JCEnvironment.NOVALUE;
        }
        return JCEnvironment.java_version;
    }
    
    public static String getVendorString() {
        if (JCEnvironment.vendorString == null) {
            try {
                JCEnvironment.vendorString = System.getProperty("java.vendor");
            }
            catch (Exception ex) {
                JCEnvironment.vendorString = null;
            }
        }
        return JCEnvironment.vendorString;
    }
    
    public static String getVersionString() {
        if (JCEnvironment.versionString == null) {
            try {
                JCEnvironment.versionString = System.getProperty("java.version");
            }
            catch (Exception ex) {
                JCEnvironment.versionString = null;
            }
        }
        return JCEnvironment.versionString;
    }
    
    public static boolean isJavaOS() {
        return JCEnvironment.JAVA_OS;
    }
    
    static {
        JCEnvironment.NOVALUE = -999;
        JCEnvironment.os = JCEnvironment.NOVALUE;
        JCEnvironment.java_version = JCEnvironment.NOVALUE;
        BROWSER_UNKNOWN = JCEnvironment.NOVALUE;
        JCEnvironment.versionString = null;
        JCEnvironment.vendorString = null;
        JAVA_OS = (System.getProperty("os.name").indexOf("JavaOS") > -1);
    }
}
