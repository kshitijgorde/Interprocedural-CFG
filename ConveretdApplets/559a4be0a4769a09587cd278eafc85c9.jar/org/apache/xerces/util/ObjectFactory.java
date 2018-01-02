// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.File;

public class ObjectFactory
{
    private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";
    private static final boolean DEBUG = false;
    static /* synthetic */ Class class$org$apache$xerces$util$ObjectFactory;
    
    public static Object createObject(final String factoryId, final String fallbackClassName) throws ConfigurationError {
        return createObject(factoryId, null, fallbackClassName);
    }
    
    public static Object createObject(final String factoryId, String propertiesFilename, final String fallbackClassName) throws ConfigurationError {
        debugPrintln("debug is on");
        final SecuritySupport ss = SecuritySupport.getInstance();
        final ClassLoader cl = findClassLoader();
        try {
            final String systemProp = ss.getSystemProperty(factoryId);
            if (systemProp != null) {
                debugPrintln("found system property, value=" + systemProp);
                return newInstance(systemProp, cl, true);
            }
        }
        catch (SecurityException ex) {}
        try {
            if (propertiesFilename == null) {
                final String javah = ss.getSystemProperty("java.home");
                propertiesFilename = javah + File.separator + "lib" + File.separator + "xerces.properties";
            }
            final FileInputStream fis = ss.getFileInputStream(new File(propertiesFilename));
            final Properties props = new Properties();
            props.load(fis);
            final String factoryClassName = props.getProperty(factoryId);
            if (factoryClassName != null) {
                debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
                return newInstance(factoryClassName, cl, true);
            }
        }
        catch (Exception ex2) {}
        final Object provider = findJarServiceProvider(factoryId);
        if (provider != null) {
            return provider;
        }
        if (fallbackClassName == null) {
            throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
        }
        debugPrintln("using fallback, value=" + fallbackClassName);
        return newInstance(fallbackClassName, cl, true);
    }
    
    private static void debugPrintln(final String msg) {
    }
    
    public static ClassLoader findClassLoader() throws ConfigurationError {
        final SecuritySupport ss = SecuritySupport.getInstance();
        ClassLoader cl = ss.getContextClassLoader();
        if (cl == null) {
            cl = ((ObjectFactory.class$org$apache$xerces$util$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$util$ObjectFactory = class$("org.apache.xerces.util.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$util$ObjectFactory).getClassLoader();
        }
        return cl;
    }
    
    public static Object newInstance(final String className, final ClassLoader cl, final boolean doFallback) throws ConfigurationError {
        try {
            final Class providerClass = findProviderClass(className, cl, doFallback);
            final Object instance = providerClass.newInstance();
            debugPrintln("created new instance of " + providerClass + " using ClassLoader: " + cl);
            return instance;
        }
        catch (ClassNotFoundException x) {
            throw new ConfigurationError("Provider " + className + " not found", x);
        }
        catch (Exception x2) {
            throw new ConfigurationError("Provider " + className + " could not be instantiated: " + x2, x2);
        }
    }
    
    public static Class findProviderClass(final String className, ClassLoader cl, final boolean doFallback) throws ClassNotFoundException, ConfigurationError {
        Class providerClass;
        if (cl == null) {
            providerClass = Class.forName(className);
        }
        else {
            try {
                providerClass = cl.loadClass(className);
            }
            catch (ClassNotFoundException x) {
                if (!doFallback) {
                    throw x;
                }
                cl = ((ObjectFactory.class$org$apache$xerces$util$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$util$ObjectFactory = class$("org.apache.xerces.util.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$util$ObjectFactory).getClassLoader();
                providerClass = cl.loadClass(className);
            }
        }
        return providerClass;
    }
    
    private static Object findJarServiceProvider(final String factoryId) throws ConfigurationError {
        final SecuritySupport ss = SecuritySupport.getInstance();
        final String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;
        ClassLoader cl = ss.getContextClassLoader();
        if (cl != null) {
            is = ss.getResourceAsStream(cl, serviceId);
            if (is == null) {
                cl = ((ObjectFactory.class$org$apache$xerces$util$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$util$ObjectFactory = class$("org.apache.xerces.util.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$util$ObjectFactory).getClassLoader();
                is = ss.getResourceAsStream(cl, serviceId);
            }
        }
        else {
            cl = ((ObjectFactory.class$org$apache$xerces$util$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$util$ObjectFactory = class$("org.apache.xerces.util.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$util$ObjectFactory).getClassLoader();
            is = ss.getResourceAsStream(cl, serviceId);
        }
        if (is == null) {
            return null;
        }
        debugPrintln("found jar resource=" + serviceId + " using ClassLoader: " + cl);
        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(is));
        }
        String factoryClassName = null;
        try {
            factoryClassName = rd.readLine();
            rd.close();
        }
        catch (IOException x) {
            return null;
        }
        if (factoryClassName != null && !"".equals(factoryClassName)) {
            debugPrintln("found in resource, value=" + factoryClassName);
            return newInstance(factoryClassName, cl, false);
        }
        return null;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    public static class ConfigurationError extends Error
    {
        private Exception exception;
        
        public ConfigurationError(final String msg, final Exception x) {
            super(msg);
            this.exception = x;
        }
        
        public Exception getException() {
            return this.exception;
        }
    }
}
