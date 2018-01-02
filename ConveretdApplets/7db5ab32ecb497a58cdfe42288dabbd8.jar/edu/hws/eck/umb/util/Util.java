// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.util;

import java.util.prefs.Preferences;
import javax.swing.KeyStroke;

public class Util
{
    private static final String PREFS_PREFIX = "/edu/hws/edu/umb";
    private static char isMacOS;
    
    public static boolean isMacOS() {
        if (Util.isMacOS == '?') {
            try {
                if (System.getProperty("mrj.version") != null) {
                    Util.isMacOS = 'Y';
                }
                else {
                    Util.isMacOS = 'N';
                }
            }
            catch (Exception ex) {
                Util.isMacOS = 'N';
            }
        }
        return Util.isMacOS == 'Y';
    }
    
    public static KeyStroke getAccelerator(final String s) {
        String s2;
        if (isMacOS()) {
            s2 = "meta ";
        }
        else {
            s2 = "control ";
        }
        return KeyStroke.getKeyStroke(s2 + s);
    }
    
    public static String getPref(final String s) {
        return getPref(s, null);
    }
    
    public static String getPref(final String s, final String s2) {
        try {
            return Preferences.userRoot().node("/edu/hws/edu/umb").get(s, s2);
        }
        catch (Exception ex) {
            return s2;
        }
    }
    
    public static void setPref(final String s, final String s2) {
        try {
            final Preferences node = Preferences.userRoot().node("/edu/hws/edu/umb");
            if (s2 == null) {
                node.remove(s);
            }
            else {
                node.put(s, s2);
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        Util.isMacOS = '?';
    }
}
