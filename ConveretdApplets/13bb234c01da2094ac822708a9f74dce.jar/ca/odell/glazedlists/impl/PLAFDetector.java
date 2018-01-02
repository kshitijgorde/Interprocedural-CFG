// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.security.AccessControlException;
import java.awt.Toolkit;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public final class PLAFDetector
{
    public static String a() {
        try {
            final MetalLookAndFeel metalLookAndFeel = (MetalLookAndFeel)UIManager.getLookAndFeel();
            return "Metal/" + ((MetalTheme)metalLookAndFeel.getClass().getMethod("getCurrentTheme", (Class<?>[])new Class[0]).invoke(metalLookAndFeel, new Object[0])).getName();
        }
        catch (NoSuchMethodException ex2) {
            return "Metal/Steel";
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "Metal/Steel";
        }
    }
    
    public static String b() {
        final String s = "Classic Windows";
        final String s2 = "Windows XP";
        final String s3 = "Windows Vista";
        final Boolean b = (Boolean)Toolkit.getDefaultToolkit().getDesktopProperty("win.xpstyle.themeActive");
        if (b == null) {
            return s;
        }
        if (!b) {
            return s;
        }
        final String s4 = "swing.noxp";
        try {
            if (System.getProperty(s4) != null) {
                return s;
            }
        }
        catch (AccessControlException ex) {}
        if (UIManager.getLookAndFeel().getClass().getName().equals("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel")) {
            return "Classic Windows";
        }
        try {
            final double double1 = Double.parseDouble(System.getProperty("os.version"));
            if (double1 >= 6.0) {
                return s3;
            }
            if (double1 >= 5.1) {
                return s2;
            }
            return s;
        }
        catch (AccessControlException ex2) {
            return s2;
        }
        catch (NumberFormatException ex3) {
            return s2;
        }
    }
}
