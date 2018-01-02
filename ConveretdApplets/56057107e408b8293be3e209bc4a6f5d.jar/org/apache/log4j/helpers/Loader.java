// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class Loader
{
    static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
    private static boolean java1;
    private static boolean ignoreTCL;
    static /* synthetic */ Class class$org$apache$log4j$helpers$Loader;
    static /* synthetic */ Class class$java$lang$Thread;
    
    public static URL getResource(final String s, final Class clazz) {
        return getResource(s);
    }
    
    public static URL getResource(final String s) {
        try {
            if (!Loader.java1) {
                final ClassLoader tcl = getTCL();
                if (tcl != null) {
                    LogLog.debug("Trying to find [" + s + "] using context classloader " + tcl + ".");
                    final URL resource = tcl.getResource(s);
                    if (resource != null) {
                        return resource;
                    }
                }
            }
            final ClassLoader classLoader = ((Loader.class$org$apache$log4j$helpers$Loader == null) ? (Loader.class$org$apache$log4j$helpers$Loader = class$("org.apache.log4j.helpers.Loader")) : Loader.class$org$apache$log4j$helpers$Loader).getClassLoader();
            if (classLoader != null) {
                LogLog.debug("Trying to find [" + s + "] using " + classLoader + " class loader.");
                final URL resource2 = classLoader.getResource(s);
                if (resource2 != null) {
                    return resource2;
                }
            }
        }
        catch (Throwable t) {
            LogLog.warn("Caught Exception while in Loader.getResource. This may be innocuous.", t);
        }
        LogLog.debug("Trying to find [" + s + "] using ClassLoader.getSystemResource().");
        return ClassLoader.getSystemResource(s);
    }
    
    public static boolean isJava1() {
        return Loader.java1;
    }
    
    private static ClassLoader getTCL() throws IllegalAccessException, InvocationTargetException {
        Method method;
        try {
            method = ((Loader.class$java$lang$Thread == null) ? (Loader.class$java$lang$Thread = class$("java.lang.Thread")) : Loader.class$java$lang$Thread).getMethod("getContextClassLoader", (Class[])null);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
        return (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
    }
    
    public static Class loadClass(final String s) throws ClassNotFoundException {
        if (Loader.java1 || Loader.ignoreTCL) {
            return Class.forName(s);
        }
        try {
            return getTCL().loadClass(s);
        }
        catch (Throwable t) {
            return Class.forName(s);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Loader.java1 = true;
        Loader.ignoreTCL = false;
        final String systemProperty = OptionConverter.getSystemProperty("java.version", null);
        if (systemProperty != null) {
            final int index = systemProperty.indexOf(46);
            if (index != -1 && systemProperty.charAt(index + 1) != '1') {
                Loader.java1 = false;
            }
        }
        final String systemProperty2 = OptionConverter.getSystemProperty("log4j.ignoreTCL", null);
        if (systemProperty2 != null) {
            Loader.ignoreTCL = OptionConverter.toBoolean(systemProperty2, true);
        }
    }
}
