// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.validation;

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

class SchemaFactoryFinder
{
    private static boolean debug;
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static Properties jaxpProperties;
    private static long lastModified;
    static /* synthetic */ Class class$javax$xml$validation$SchemaFactoryFinder;
    
    static Object find(final String s, final String s2, final String s3) {
        final SecuritySupport instance = SecuritySupport.getInstance();
        ClassLoader classLoader = instance.getContextClassLoader();
        if (classLoader == null) {
            classLoader = ((SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder == null) ? (SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder = class$("javax.xml.validation.SchemaFactoryFinder")) : SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder).getClassLoader();
        }
        final String string = s + ":" + s2;
        if (SchemaFactoryFinder.debug) {
            dPrint("find factoryId=" + string);
        }
        try {
            final String systemProperty = instance.getSystemProperty(string);
            if (systemProperty != null) {
                if (SchemaFactoryFinder.debug) {
                    dPrint("found system property, value=" + systemProperty);
                }
                return newInstance(systemProperty, classLoader);
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
            SchemaFactoryFinder.lastModified = -1L;
            SchemaFactoryFinder.jaxpProperties = null;
        }
        final Class clazz = (SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder == null) ? (SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder = class$("javax.xml.validation.SchemaFactoryFinder")) : SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder;
        synchronized (clazz) {
            int n = 0;
            FileInputStream fileInputStream = null;
            try {
                if (SchemaFactoryFinder.lastModified >= 0L) {
                    if (fileExists && SchemaFactoryFinder.lastModified < (SchemaFactoryFinder.lastModified = instance.getLastModified(file))) {
                        n = 1;
                    }
                    else if (!fileExists) {
                        SchemaFactoryFinder.lastModified = -1L;
                        SchemaFactoryFinder.jaxpProperties = null;
                    }
                }
                else if (fileExists) {
                    n = 1;
                    SchemaFactoryFinder.lastModified = instance.getLastModified(file);
                }
                if (n == 1) {
                    SchemaFactoryFinder.jaxpProperties = new Properties();
                    fileInputStream = instance.getFileInputStream(file);
                    SchemaFactoryFinder.jaxpProperties.load(fileInputStream);
                }
            }
            catch (Exception ex3) {
                SchemaFactoryFinder.lastModified = -1L;
                SchemaFactoryFinder.jaxpProperties = null;
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
        if (SchemaFactoryFinder.jaxpProperties != null) {
            final String property = SchemaFactoryFinder.jaxpProperties.getProperty(string);
            if (property != null) {
                if (SchemaFactoryFinder.debug) {
                    dPrint("found in jaxp.properties, value=" + property);
                }
                return newInstance(property, classLoader);
            }
        }
        final Object jarServiceProvider = findJarServiceProvider(s, s2);
        if (jarServiceProvider != null) {
            return jarServiceProvider;
        }
        if (s2.equals("http://www.w3.org/2001/XMLSchema")) {
            if (SchemaFactoryFinder.debug) {
                dPrint("using fallback, value=" + s3);
            }
            return newInstance(s3, classLoader);
        }
        throw new IllegalArgumentException("No SchemaFactory implementation for the schema language:" + s2 + " is available.");
    }
    
    private static void dPrint(final String s) {
        System.err.println("JAXP: " + s);
    }
    
    private static Object newInstance(final String s, final ClassLoader classLoader) throws ConfigurationError {
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
                    throw ex;
                }
            }
            final Object instance = clazz.newInstance();
            if (SchemaFactoryFinder.debug) {
                dPrint("created new instance of " + clazz + " using ClassLoader: " + classLoader);
            }
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
                classLoader = ((SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder == null) ? (SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder = class$("javax.xml.validation.SchemaFactoryFinder")) : SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder).getClassLoader();
                enumeration = instance.getResources(classLoader, string);
            }
        }
        else {
            classLoader = ((SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder == null) ? (SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder = class$("javax.xml.validation.SchemaFactoryFinder")) : SchemaFactoryFinder.class$javax$xml$validation$SchemaFactoryFinder).getClassLoader();
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
                        final SchemaFactory schemaFactory = (SchemaFactory)newInstance(trim, classLoader);
                        if (schemaFactory.isSchemaLanguageSupported(s2)) {
                            o = schemaFactory;
                            break;
                        }
                        continue;
                    }
                    catch (Exception ex3) {}
                }
                try {
                    bufferedReader.close();
                }
                catch (IOException ex4) {}
                if (o != null) {
                    if (SchemaFactoryFinder.debug) {
                        dPrint("found jar resource=" + string + " using ClassLoader: " + classLoader);
                    }
                    return o;
                }
                continue;
            }
            catch (IOException ex5) {}
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
        SchemaFactoryFinder.jaxpProperties = null;
        SchemaFactoryFinder.lastModified = -1L;
        try {
            final String systemProperty = SecuritySupport.getInstance().getSystemProperty("jaxp.debug");
            SchemaFactoryFinder.debug = (systemProperty != null && !"false".equals(systemProperty));
        }
        catch (SecurityException ex) {
            SchemaFactoryFinder.debug = false;
        }
    }
    
    static class ConfigurationError extends Error
    {
        private static final long serialVersionUID = 3546360626067682354L;
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
