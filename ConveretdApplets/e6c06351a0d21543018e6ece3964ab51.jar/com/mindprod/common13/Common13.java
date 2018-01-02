// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common13;

import javax.swing.UnsupportedLookAndFeelException;
import com.mindprod.common11.VersionCheck;
import javax.swing.UIManager;

public final class Common13
{
    private static final boolean DEBUGGING = false;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2008-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-11-12";
    private static final String VERSION_STRING = "1.3";
    
    public static void setLaf() {
        if (setLaf("Mac OS X", 1, 4, 0, "ch.randelshofer.quaqua.QuaquaLookAndFeel")) {
            return;
        }
        if (setLaf("Mac OS X", 1, 3, 0, "com.sun.java.swing.plaf.mac.MacLookAndFeel")) {
            return;
        }
        if (setLaf("Mac OS", 1, 3, 0, UIManager.getSystemLookAndFeelClassName())) {
            return;
        }
        if (setLaf("Linux", 1, 5, 0, "com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) {
            return;
        }
        if (setLaf("Windows 7", 1, 3, 0, UIManager.getCrossPlatformLookAndFeelClassName())) {
            return;
        }
        if (setLaf("Windows Vista", 1, 3, 0, UIManager.getCrossPlatformLookAndFeelClassName())) {
            return;
        }
        if (setLaf("Windows XP", 1, 3, 0, UIManager.getCrossPlatformLookAndFeelClassName())) {
            return;
        }
        if (setLaf("Windows 2000", 1, 3, 0, UIManager.getCrossPlatformLookAndFeelClassName())) {
            return;
        }
        if (setLaf(null, 1, 6, 0, "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")) {
            return;
        }
        setLaf(null, 1, 3, 0, UIManager.getSystemLookAndFeelClassName());
    }
    
    private static boolean setLaf(final String os, final int wantedMajor, final int wantedMinor, final int wantedBugFix, final String lafClassName) {
        if (os != null && !System.getProperty("os.name", "unknown").equals(os)) {
            return false;
        }
        if (!VersionCheck.isJavaVersionOK(wantedMajor, wantedMinor, wantedBugFix)) {
            return false;
        }
        try {
            UIManager.setLookAndFeel(lafClassName);
        }
        catch (UnsupportedLookAndFeelException e) {
            return false;
        }
        catch (IllegalAccessException e2) {
            return false;
        }
        catch (InstantiationException e3) {
            return false;
        }
        catch (ClassNotFoundException e4) {
            return false;
        }
        catch (Exception e5) {
            return false;
        }
        return true;
    }
    
    public static void main(final String[] args) {
    }
}
