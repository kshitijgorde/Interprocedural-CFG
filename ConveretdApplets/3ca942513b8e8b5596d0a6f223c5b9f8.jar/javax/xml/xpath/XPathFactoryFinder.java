// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.xpath;

import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Properties;

class XPathFactoryFinder
{
    private static boolean debug;
    private static Properties jaxpProperties;
    private static long lastModified;
    private static final int DEFAULT_LINE_LENGTH = 80;
    static /* synthetic */ Class class$javax$xml$xpath$XPathFactoryFinder;
    
    static Object find(final String s, final String s2, final String s3) throws ConfigurationError {
        final SecuritySupport instance = SecuritySupport.getInstance();
        ClassLoader classLoader = instance.getContextClassLoader();
        if (classLoader == null) {
            classLoader = ((XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder == null) ? (XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder = class$("javax.xml.xpath.XPathFactoryFinder")) : XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder).getClassLoader();
        }
        final String string = s + ":" + s2;
        dPrint("find factoryId=" + string);
        try {
            final String systemProperty = instance.getSystemProperty(string);
            if (systemProperty != null) {
                dPrint("found system property, value=" + systemProperty);
                return newInstance(systemProperty, classLoader, true);
            }
        }
        catch (SecurityException ex) {}
        boolean fileExists = false;
        File file = null;
        try {
            file = new File(instance.getSystemProperty("java.home") + File.separator + "lib" + File.separator + "jaxp.properties");
            fileExists = instance.getFileExists(file);
        }
        catch (SecurityException ex2) {
            XPathFactoryFinder.lastModified = -1L;
            XPathFactoryFinder.jaxpProperties = null;
        }
        final Class clazz = (XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder == null) ? (XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder = class$("javax.xml.xpath.XPathFactoryFinder")) : XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder;
        synchronized (clazz) {
            int n = 0;
            FileInputStream fileInputStream = null;
            try {
                if (XPathFactoryFinder.lastModified >= 0L) {
                    if (fileExists && XPathFactoryFinder.lastModified < (XPathFactoryFinder.lastModified = instance.getLastModified(file))) {
                        n = 1;
                    }
                    else if (!fileExists) {
                        XPathFactoryFinder.lastModified = -1L;
                        XPathFactoryFinder.jaxpProperties = null;
                    }
                }
                else if (fileExists) {
                    n = 1;
                    XPathFactoryFinder.lastModified = instance.getLastModified(file);
                }
                if (n == 1) {
                    XPathFactoryFinder.jaxpProperties = new Properties();
                    fileInputStream = instance.getFileInputStream(file);
                    XPathFactoryFinder.jaxpProperties.load(fileInputStream);
                }
            }
            catch (Exception ex3) {
                XPathFactoryFinder.lastModified = -1L;
                XPathFactoryFinder.jaxpProperties = null;
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
        if (XPathFactoryFinder.jaxpProperties != null) {
            final String property = XPathFactoryFinder.jaxpProperties.getProperty(string);
            if (property != null) {
                dPrint("found in jaxp.properties, value=" + property);
                return newInstance(property, classLoader, true);
            }
        }
        final Object jarServiceProvider = findJarServiceProvider(s, s2);
        if (jarServiceProvider != null) {
            return jarServiceProvider;
        }
        if (s3 != null && s2.equals("http://java.sun.com/jaxp/xpath/dom")) {
            dPrint("using fallback, value=" + s3);
            return newInstance(s3, classLoader, true);
        }
        throw new ConfigurationError("Provider for " + string + " cannot be found", null);
    }
    
    private static void dPrint(final String s) {
        if (XPathFactoryFinder.debug) {
            System.err.println("JAXP: " + s);
        }
    }
    
    private static Object newInstance(final String s, ClassLoader classLoader, final boolean b) throws ConfigurationError {
        try {
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
                    classLoader = ((XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder == null) ? (XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder = class$("javax.xml.xpath.XPathFactoryFinder")) : XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder).getClassLoader();
                    if (classLoader != null) {
                        clazz = classLoader.loadClass(s);
                    }
                    else {
                        clazz = Class.forName(s);
                    }
                }
            }
            final Object instance = clazz.newInstance();
            dPrint("created new instance of " + clazz + " using ClassLoader: " + classLoader);
            return instance;
        }
        catch (ClassNotFoundException ex2) {
            throw new ConfigurationError("Provider " + s + " not found", ex2);
        }
        catch (Exception ex3) {
            throw new ConfigurationError("Provider " + s + " could not be instantiated: " + ex3, ex3);
        }
    }
    
    private static Object findJarServiceProvider(final String s, final String s2) throws ConfigurationError {
        final SecuritySupport instance = SecuritySupport.getInstance();
        final String string = "META-INF/services/" + s;
        ClassLoader classLoader = instance.getContextClassLoader();
        Enumeration enumeration;
        if (classLoader != null) {
            enumeration = instance.getResources(classLoader, string);
            if (!enumeration.hasMoreElements()) {
                classLoader = ((XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder == null) ? (XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder = class$("javax.xml.xpath.XPathFactoryFinder")) : XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder).getClassLoader();
                enumeration = instance.getResources(classLoader, string);
            }
        }
        else {
            classLoader = ((XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder == null) ? (XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder = class$("javax.xml.xpath.XPathFactoryFinder")) : XPathFactoryFinder.class$javax$xml$xpath$XPathFactoryFinder).getClassLoader();
            enumeration = instance.getResources(classLoader, string);
        }
        while (enumeration.hasMoreElements()) {
            final URL url = enumeration.nextElement();
            try {
                final InputStream openStream = url.openStream();
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(openStream, "UTF-8"), 80);
                }
                catch (UnsupportedEncodingException ex) {
                    bufferedReader = new BufferedReader(new InputStreamReader(openStream), 80);
                }
                Object o = null;
                while (true) {
                    String s3;
                    try {
                        s3 = bufferedReader.readLine();
                    }
                    catch (IOException ex2) {
                        break;
                    }
                    if (s3 == null) {
                        break;
                    }
                    final int index = s3.indexOf(35);
                    if (index != -1) {
                        s3 = s3.substring(0, index);
                    }
                    final String trim = s3.trim();
                    if (trim.length() == 0) {
                        continue;
                    }
                    try {
                        final XPathFactory xPathFactory = (XPathFactory)newInstance(trim, classLoader, false);
                        if (xPathFactory.isObjectModelSupported(s2)) {
                            o = xPathFactory;
                            break;
                        }
                        continue;
                    }
                    catch (Throwable t) {}
                }
                try {
                    bufferedReader.close();
                }
                catch (IOException ex3) {}
                if (o != null) {
                    if (XPathFactoryFinder.debug) {
                        dPrint("found jar resource=" + string + " using ClassLoader: " + classLoader);
                    }
                    return o;
                }
                continue;
            }
            catch (IOException ex4) {}
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
        XPathFactoryFinder.jaxpProperties = null;
        XPathFactoryFinder.lastModified = -1L;
        try {
            final String systemProperty = SecuritySupport.getInstance().getSystemProperty("jaxp.debug");
            XPathFactoryFinder.debug = (systemProperty != null && !"false".equals(systemProperty));
        }
        catch (SecurityException ex) {
            XPathFactoryFinder.debug = false;
        }
    }
    
    static class ConfigurationError extends Error
    {
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
