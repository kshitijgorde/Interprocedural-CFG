// 
// Decompiled by Procyon v0.5.30
// 

package securitywarningapplet;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.geom.Point2D;
import java.awt.Window;
import java.lang.reflect.Method;

public class SecurityWarningWrapper
{
    private static Class<?> securityWarningClass;
    private static Method mSetPosition;
    
    static void init() {
        try {
            SecurityWarningWrapper.securityWarningClass = Class.forName("com.sun.awt.SecurityWarning");
            SecurityWarningWrapper.mSetPosition = SecurityWarningWrapper.securityWarningClass.getMethod("setPosition", Window.class, Point2D.class, Float.TYPE, Float.TYPE);
        }
        catch (NoSuchMethodException ex) {
            Logger.getLogger(SecurityWarningWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SecurityException ex2) {
            Logger.getLogger(SecurityWarningWrapper.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (ClassNotFoundException ex3) {
            Logger.getLogger(SecurityWarningWrapper.class.getName()).log(Level.SEVERE, null, ex3);
        }
    }
    
    public static boolean isSupported() {
        return SecurityWarningWrapper.securityWarningClass != null && SecurityWarningWrapper.mSetPosition != null;
    }
    
    public static void setPosition(final Window window, final Point2D point, final float alignmentX, final float alignmentY) {
        if (!isSupported()) {
            return;
        }
        try {
            SecurityWarningWrapper.mSetPosition.invoke(null, window, point, alignmentX, alignmentY);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(SecurityWarningWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex2) {
            Logger.getLogger(SecurityWarningWrapper.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (InvocationTargetException ex3) {
            Logger.getLogger(SecurityWarningWrapper.class.getName()).log(Level.SEVERE, null, ex3);
        }
    }
    
    static {
        init();
    }
}
