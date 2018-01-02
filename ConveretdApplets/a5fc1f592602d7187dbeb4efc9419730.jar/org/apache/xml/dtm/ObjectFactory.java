// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.util.Properties;

class ObjectFactory
{
    private static final String DEFAULT_PROPERTIES_FILENAME = "xalan.properties";
    private static final String SERVICES_PATH = "META-INF/services/";
    private static final boolean DEBUG = false;
    private static Properties fXalanProperties;
    private static long fLastModified;
    static /* synthetic */ Class class$org$apache$xml$dtm$ObjectFactory;
    
    static Object createObject(final String factoryId, final String fallbackClassName) throws ConfigurationError {
        return createObject(factoryId, null, fallbackClassName);
    }
    
    static Object createObject(final String factoryId, final String propertiesFilename, final String fallbackClassName) throws ConfigurationError {
        final Class factoryClass = lookUpFactoryClass(factoryId, propertiesFilename, fallbackClassName);
        if (factoryClass == null) {
            throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
        }
        try {
            final Object instance = factoryClass.newInstance();
            debugPrintln("created new instance of factory " + factoryId);
            return instance;
        }
        catch (Exception x) {
            throw new ConfigurationError("Provider for factory " + factoryId + " could not be instantiated: " + x, x);
        }
    }
    
    static Class lookUpFactoryClass(final String factoryId) throws ConfigurationError {
        return lookUpFactoryClass(factoryId, null, null);
    }
    
    static Class lookUpFactoryClass(final String factoryId, final String propertiesFilename, final String fallbackClassName) throws ConfigurationError {
        String factoryClassName = lookUpFactoryClassName(factoryId, propertiesFilename, fallbackClassName);
        final ClassLoader cl = findClassLoader();
        if (factoryClassName == null) {
            factoryClassName = fallbackClassName;
        }
        try {
            final Class providerClass = findProviderClass(factoryClassName, cl, true);
            debugPrintln("created new instance of " + providerClass + " using ClassLoader: " + cl);
            return providerClass;
        }
        catch (ClassNotFoundException x) {
            throw new ConfigurationError("Provider " + factoryClassName + " not found", x);
        }
        catch (Exception x2) {
            throw new ConfigurationError("Provider " + factoryClassName + " could not be instantiated: " + x2, x2);
        }
    }
    
    static String lookUpFactoryClassName(final String factoryId, String propertiesFilename, final String fallbackClassName) {
        final SecuritySupport ss = SecuritySupport.getInstance();
        try {
            final String systemProp = ss.getSystemProperty(factoryId);
            if (systemProp != null) {
                debugPrintln("found system property, value=" + systemProp);
                return systemProp;
            }
        }
        catch (SecurityException ex) {}
        String factoryClassName = null;
        if (propertiesFilename == null) {
            File propertiesFile = null;
            boolean propertiesFileExists = false;
            try {
                final String javah = ss.getSystemProperty("java.home");
                propertiesFilename = javah + File.separator + "lib" + File.separator + "xalan.properties";
                propertiesFile = new File(propertiesFilename);
                propertiesFileExists = ss.getFileExists(propertiesFile);
            }
            catch (SecurityException e) {
                ObjectFactory.fLastModified = -1L;
                ObjectFactory.fXalanProperties = null;
            }
            final Class clazz = (ObjectFactory.class$org$apache$xml$dtm$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xml$dtm$ObjectFactory = class$("org.apache.xml.dtm.ObjectFactory")) : ObjectFactory.class$org$apache$xml$dtm$ObjectFactory;
            synchronized (clazz) {
                boolean loadProperties = false;
                try {
                    if (ObjectFactory.fLastModified >= 0L) {
                        if (propertiesFileExists && ObjectFactory.fLastModified < (ObjectFactory.fLastModified = ss.getLastModified(propertiesFile))) {
                            loadProperties = true;
                        }
                        else if (!propertiesFileExists) {
                            ObjectFactory.fLastModified = -1L;
                            ObjectFactory.fXalanProperties = null;
                        }
                    }
                    else if (propertiesFileExists) {
                        loadProperties = true;
                        ObjectFactory.fLastModified = ss.getLastModified(propertiesFile);
                    }
                    if (loadProperties) {
                        ObjectFactory.fXalanProperties = new Properties();
                        final FileInputStream fis = ss.getFileInputStream(propertiesFile);
                        ObjectFactory.fXalanProperties.load(fis);
                        fis.close();
                    }
                }
                catch (Exception x) {
                    ObjectFactory.fXalanProperties = null;
                    ObjectFactory.fLastModified = -1L;
                }
            }
            if (ObjectFactory.fXalanProperties != null) {
                factoryClassName = ObjectFactory.fXalanProperties.getProperty(factoryId);
            }
        }
        else {
            try {
                final FileInputStream fis2 = ss.getFileInputStream(new File(propertiesFilename));
                final Properties props = new Properties();
                props.load(fis2);
                fis2.close();
                factoryClassName = props.getProperty(factoryId);
            }
            catch (Exception ex2) {}
        }
        if (factoryClassName != null) {
            debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
            return factoryClassName;
        }
        return findJarServiceProviderName(factoryId);
    }
    
    private static void debugPrintln(final String msg) {
    }
    
    static ClassLoader findClassLoader() throws ConfigurationError {
        SecuritySupport ss;
        ClassLoader context;
        ClassLoader chain;
        ClassLoader system;
        for (ss = SecuritySupport.getInstance(), context = ss.getContextClassLoader(), system = (chain = ss.getSystemClassLoader()); context != chain; chain = ss.getParentClassLoader(chain)) {
            if (chain == null) {
                return context;
            }
        }
        ClassLoader current;
        for (current = ((ObjectFactory.class$org$apache$xml$dtm$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xml$dtm$ObjectFactory = class$("org.apache.xml.dtm.ObjectFactory")) : ObjectFactory.class$org$apache$xml$dtm$ObjectFactory).getClassLoader(), chain = system; current != chain; chain = ss.getParentClassLoader(chain)) {
            if (chain == null) {
                return current;
            }
        }
        return system;
    }
    
    static Object newInstance(final String className, final ClassLoader cl, final boolean doFallback) throws ConfigurationError {
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
    
    static Class findProviderClass(final String className, ClassLoader cl, final boolean doFallback) throws ClassNotFoundException, ConfigurationError {
        final SecurityManager security = System.getSecurityManager();
        try {
            if (security != null) {
                security.checkPackageAccess(className);
            }
        }
        catch (SecurityException e) {
            throw e;
        }
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
                final ClassLoader current = ((ObjectFactory.class$org$apache$xml$dtm$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xml$dtm$ObjectFactory = class$("org.apache.xml.dtm.ObjectFactory")) : ObjectFactory.class$org$apache$xml$dtm$ObjectFactory).getClassLoader();
                if (current == null) {
                    providerClass = Class.forName(className);
                }
                else {
                    if (cl == current) {
                        throw x;
                    }
                    cl = current;
                    providerClass = cl.loadClass(className);
                }
            }
        }
        return providerClass;
    }
    
    private static String findJarServiceProviderName(final String factoryId) {
        final SecuritySupport ss = SecuritySupport.getInstance();
        final String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;
        ClassLoader cl = findClassLoader();
        is = ss.getResourceAsStream(cl, serviceId);
        if (is == null) {
            final ClassLoader current = ((ObjectFactory.class$org$apache$xml$dtm$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xml$dtm$ObjectFactory = class$("org.apache.xml.dtm.ObjectFactory")) : ObjectFactory.class$org$apache$xml$dtm$ObjectFactory).getClassLoader();
            if (cl != current) {
                cl = current;
                is = ss.getResourceAsStream(cl, serviceId);
            }
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
            return factoryClassName;
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
    
    static {
        ObjectFactory.fXalanProperties = null;
        ObjectFactory.fLastModified = -1L;
    }
    
    static class ConfigurationError extends Error
    {
        private Exception exception;
        
        ConfigurationError(final String msg, final Exception x) {
            super(msg);
            this.exception = x;
        }
        
        Exception getException() {
            return this.exception;
        }
    }
}
