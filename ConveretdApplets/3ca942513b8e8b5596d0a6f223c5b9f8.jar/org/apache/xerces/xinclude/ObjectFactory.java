// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xinclude;

import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Properties;

final class ObjectFactory
{
    private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static Properties fXercesProperties;
    private static long fLastModified;
    static /* synthetic */ Class class$org$apache$xerces$xinclude$ObjectFactory;
    
    static Object createObject(final String s, final String s2) throws ConfigurationError {
        return createObject(s, null, s2);
    }
    
    static Object createObject(final String s, String string, final String s2) throws ConfigurationError {
        final SecuritySupport instance = SecuritySupport.getInstance();
        final ClassLoader classLoader = findClassLoader();
        try {
            final String systemProperty = instance.getSystemProperty(s);
            if (systemProperty != null) {
                return newInstance(systemProperty, classLoader, true);
            }
        }
        catch (SecurityException ex) {}
        String s3 = null;
        if (string == null) {
            File file = null;
            boolean fileExists = false;
            try {
                string = instance.getSystemProperty("java.home") + File.separator + "lib" + File.separator + "xerces.properties";
                file = new File(string);
                fileExists = instance.getFileExists(file);
            }
            catch (SecurityException ex2) {
                ObjectFactory.fLastModified = -1L;
                ObjectFactory.fXercesProperties = null;
            }
            final Class clazz = (ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory = class$("org.apache.xerces.xinclude.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory;
            synchronized (clazz) {
                boolean b = false;
                FileInputStream fileInputStream = null;
                try {
                    if (ObjectFactory.fLastModified >= 0L) {
                        if (fileExists && ObjectFactory.fLastModified < (ObjectFactory.fLastModified = instance.getLastModified(file))) {
                            b = true;
                        }
                        else if (!fileExists) {
                            ObjectFactory.fLastModified = -1L;
                            ObjectFactory.fXercesProperties = null;
                        }
                    }
                    else if (fileExists) {
                        b = true;
                        ObjectFactory.fLastModified = instance.getLastModified(file);
                    }
                    if (b) {
                        ObjectFactory.fXercesProperties = new Properties();
                        fileInputStream = instance.getFileInputStream(file);
                        ObjectFactory.fXercesProperties.load(fileInputStream);
                    }
                }
                catch (Exception ex3) {
                    ObjectFactory.fXercesProperties = null;
                    ObjectFactory.fLastModified = -1L;
                }
                finally {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        }
                        catch (IOException ex4) {}
                    }
                }
            }
            if (ObjectFactory.fXercesProperties != null) {
                s3 = ObjectFactory.fXercesProperties.getProperty(s);
            }
        }
        else {
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream2 = instance.getFileInputStream(new File(string));
                final Properties properties = new Properties();
                properties.load(fileInputStream2);
                s3 = properties.getProperty(s);
            }
            catch (Exception ex5) {}
            finally {
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    }
                    catch (IOException ex6) {}
                }
            }
        }
        if (s3 != null) {
            return newInstance(s3, classLoader, true);
        }
        final Object jarServiceProvider = findJarServiceProvider(s);
        if (jarServiceProvider != null) {
            return jarServiceProvider;
        }
        if (s2 == null) {
            throw new ConfigurationError("Provider for " + s + " cannot be found", null);
        }
        return newInstance(s2, classLoader, true);
    }
    
    private static void debugPrintln(final String s) {
    }
    
    static ClassLoader findClassLoader() throws ConfigurationError {
        SecuritySupport instance;
        ClassLoader contextClassLoader;
        ClassLoader classLoader2;
        ClassLoader classLoader;
        for (instance = SecuritySupport.getInstance(), contextClassLoader = instance.getContextClassLoader(), classLoader = (classLoader2 = instance.getSystemClassLoader()); contextClassLoader != classLoader2; classLoader2 = instance.getParentClassLoader(classLoader2)) {
            if (classLoader2 == null) {
                return contextClassLoader;
            }
        }
        for (ClassLoader classLoader3 = ((ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory = class$("org.apache.xerces.xinclude.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory).getClassLoader(), parentClassLoader = classLoader; classLoader3 != parentClassLoader; parentClassLoader = instance.getParentClassLoader(parentClassLoader)) {
            if (parentClassLoader == null) {
                return classLoader3;
            }
        }
        return classLoader;
    }
    
    static Object newInstance(final String s, final ClassLoader classLoader, final boolean b) throws ConfigurationError {
        try {
            return findProviderClass(s, classLoader, b).newInstance();
        }
        catch (ClassNotFoundException ex) {
            throw new ConfigurationError("Provider " + s + " not found", ex);
        }
        catch (Exception ex2) {
            throw new ConfigurationError("Provider " + s + " could not be instantiated: " + ex2, ex2);
        }
    }
    
    static Class findProviderClass(final String s, ClassLoader classLoader, final boolean b) throws ClassNotFoundException, ConfigurationError {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            final int lastIndex = s.lastIndexOf(".");
            String substring = s;
            if (lastIndex != -1) {
                substring = s.substring(0, lastIndex);
            }
            securityManager.checkPackageAccess(substring);
        }
        Class<?> clazz;
        if (classLoader == null) {
            clazz = Class.forName(s);
        }
        else {
            try {
                clazz = classLoader.loadClass(s);
            }
            catch (ClassNotFoundException ex) {
                if (!b) {
                    throw ex;
                }
                final ClassLoader classLoader2 = ((ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory = class$("org.apache.xerces.xinclude.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory).getClassLoader();
                if (classLoader2 == null) {
                    clazz = Class.forName(s);
                }
                else {
                    if (classLoader == classLoader2) {
                        throw ex;
                    }
                    classLoader = classLoader2;
                    clazz = classLoader.loadClass(s);
                }
            }
        }
        return clazz;
    }
    
    private static Object findJarServiceProvider(final String s) throws ConfigurationError {
        final SecuritySupport instance = SecuritySupport.getInstance();
        final String string = "META-INF/services/" + s;
        ClassLoader classLoader = findClassLoader();
        InputStream inputStream = instance.getResourceAsStream(classLoader, string);
        if (inputStream == null) {
            final ClassLoader classLoader2 = ((ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory == null) ? (ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory = class$("org.apache.xerces.xinclude.ObjectFactory")) : ObjectFactory.class$org$apache$xerces$xinclude$ObjectFactory).getClassLoader();
            if (classLoader != classLoader2) {
                classLoader = classLoader2;
                inputStream = instance.getResourceAsStream(classLoader, string);
            }
        }
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 80);
        }
        catch (UnsupportedEncodingException ex) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 80);
        }
        String line = null;
        try {
            line = bufferedReader.readLine();
        }
        catch (IOException ex2) {
            return null;
        }
        finally {
            try {
                bufferedReader.close();
            }
            catch (IOException ex3) {}
        }
        if (line != null && !"".equals(line)) {
            return newInstance(line, classLoader, false);
        }
        return null;
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
        ObjectFactory.fXercesProperties = null;
        ObjectFactory.fLastModified = -1L;
    }
    
    static final class ConfigurationError extends Error
    {
        static final long serialVersionUID = 5061904944269807898L;
        private Exception exception;
        
        ConfigurationError(final String s, final Exception exception) {
            super(s);
            this.exception = exception;
        }
        
        Exception getException() {
            return this.exception;
        }
    }
}
