// 
// Decompiled by Procyon v0.5.30
// 

package securitywarningapplet;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Shape;
import java.awt.Window;
import java.awt.GraphicsConfiguration;
import java.lang.reflect.Method;

public class AWTUtilitiesWrapper
{
    private static Class<?> awtUtilitiesClass;
    private static Class<?> translucencyClass;
    private static Method mIsTranslucencySupported;
    private static Method mIsTranslucencyCapable;
    private static Method mSetWindowShape;
    private static Method mSetWindowOpacity;
    private static Method mSetWindowOpaque;
    private static Method mGetWindowShape;
    public static Object PERPIXEL_TRANSPARENT;
    public static Object TRANSLUCENT;
    public static Object PERPIXEL_TRANSLUCENT;
    
    static void init() {
        try {
            AWTUtilitiesWrapper.awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
            AWTUtilitiesWrapper.translucencyClass = Class.forName("com.sun.awt.AWTUtilities$Translucency");
            if (AWTUtilitiesWrapper.translucencyClass.isEnum()) {
                final Object[] kinds = (Object[])AWTUtilitiesWrapper.translucencyClass.getEnumConstants();
                if (kinds != null) {
                    AWTUtilitiesWrapper.PERPIXEL_TRANSPARENT = kinds[0];
                    AWTUtilitiesWrapper.TRANSLUCENT = kinds[1];
                    AWTUtilitiesWrapper.PERPIXEL_TRANSLUCENT = kinds[2];
                }
            }
            AWTUtilitiesWrapper.mIsTranslucencySupported = AWTUtilitiesWrapper.awtUtilitiesClass.getMethod("isTranslucencySupported", AWTUtilitiesWrapper.translucencyClass);
            AWTUtilitiesWrapper.mIsTranslucencyCapable = AWTUtilitiesWrapper.awtUtilitiesClass.getMethod("isTranslucencyCapable", GraphicsConfiguration.class);
            AWTUtilitiesWrapper.mSetWindowShape = AWTUtilitiesWrapper.awtUtilitiesClass.getMethod("setWindowShape", Window.class, Shape.class);
            AWTUtilitiesWrapper.mSetWindowOpacity = AWTUtilitiesWrapper.awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, Float.TYPE);
            AWTUtilitiesWrapper.mSetWindowOpaque = AWTUtilitiesWrapper.awtUtilitiesClass.getMethod("setWindowOpaque", Window.class, Boolean.TYPE);
            AWTUtilitiesWrapper.mGetWindowShape = AWTUtilitiesWrapper.awtUtilitiesClass.getMethod("getWindowShape", Window.class);
        }
        catch (NoSuchMethodException ex) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SecurityException ex2) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (ClassNotFoundException ex3) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex3);
        }
    }
    
    private static boolean isSupported(final Method method, final Object kind) {
        if (AWTUtilitiesWrapper.awtUtilitiesClass == null || method == null) {
            return false;
        }
        try {
            final Object ret = method.invoke(null, kind);
            if (ret instanceof Boolean) {
                return (boolean)ret;
            }
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex2) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (InvocationTargetException ex3) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex3);
        }
        return false;
    }
    
    public static boolean isTranslucencySupported(final Object kind) {
        return AWTUtilitiesWrapper.translucencyClass != null && isSupported(AWTUtilitiesWrapper.mIsTranslucencySupported, kind);
    }
    
    public static boolean isTranslucencyCapable(final GraphicsConfiguration gc) {
        return isSupported(AWTUtilitiesWrapper.mIsTranslucencyCapable, gc);
    }
    
    private static void set(final Method method, final Window window, final Object value) {
        if (AWTUtilitiesWrapper.awtUtilitiesClass == null || method == null) {
            return;
        }
        try {
            method.invoke(null, window, value);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex2) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (InvocationTargetException ex3) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex3);
        }
    }
    
    public static void setWindowShape(final Window window, final Shape shape) {
        set(AWTUtilitiesWrapper.mSetWindowShape, window, shape);
    }
    
    public static void setWindowOpacity(final Window window, final float opacity) {
        set(AWTUtilitiesWrapper.mSetWindowOpacity, window, opacity);
    }
    
    public static void setWindowOpaque(final Window window, final boolean opaque) {
        set(AWTUtilitiesWrapper.mSetWindowOpaque, window, opaque);
    }
    
    public static Shape getWindowShape(final Window window) {
        final Method method = AWTUtilitiesWrapper.mGetWindowShape;
        if (AWTUtilitiesWrapper.awtUtilitiesClass == null || method == null) {
            return null;
        }
        try {
            final Object ret = method.invoke(null, window);
            if (ret instanceof Shape) {
                return (Shape)ret;
            }
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex2) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (InvocationTargetException ex3) {
            Logger.getLogger(AWTUtilitiesWrapper.class.getName()).log(Level.SEVERE, null, ex3);
        }
        return null;
    }
    
    static {
        init();
    }
}
