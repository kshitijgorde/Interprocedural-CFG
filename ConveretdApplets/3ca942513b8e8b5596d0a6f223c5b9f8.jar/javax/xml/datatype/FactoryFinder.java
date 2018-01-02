// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.datatype;

import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Properties;

class FactoryFinder
{
    private static boolean debug;
    private static Properties jaxpProperties;
    private static long lastModified;
    private static final int DEFAULT_LINE_LENGTH = 80;
    static /* synthetic */ Class class$javax$xml$datatype$FactoryFinder;
    
    static Object find(final String s, final String s2) throws ConfigurationError {
        final SecuritySupport instance = SecuritySupport.getInstance();
        ClassLoader classLoader = instance.getContextClassLoader();
        if (classLoader == null) {
            classLoader = ((FactoryFinder.class$javax$xml$datatype$FactoryFinder == null) ? (FactoryFinder.class$javax$xml$datatype$FactoryFinder = class$("javax.xml.datatype.FactoryFinder")) : FactoryFinder.class$javax$xml$datatype$FactoryFinder).getClassLoader();
        }
        dPrint("find factoryId=" + s);
        try {
            final String systemProperty = instance.getSystemProperty(s);
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
            FactoryFinder.lastModified = -1L;
            FactoryFinder.jaxpProperties = null;
        }
        final Class clazz = (FactoryFinder.class$javax$xml$datatype$FactoryFinder == null) ? (FactoryFinder.class$javax$xml$datatype$FactoryFinder = class$("javax.xml.datatype.FactoryFinder")) : FactoryFinder.class$javax$xml$datatype$FactoryFinder;
        synchronized (clazz) {
            int n = 0;
            FileInputStream fileInputStream = null;
            try {
                if (FactoryFinder.lastModified >= 0L) {
                    if (fileExists && FactoryFinder.lastModified < (FactoryFinder.lastModified = instance.getLastModified(file))) {
                        n = 1;
                    }
                    else if (!fileExists) {
                        FactoryFinder.lastModified = -1L;
                        FactoryFinder.jaxpProperties = null;
                    }
                }
                else if (fileExists) {
                    n = 1;
                    FactoryFinder.lastModified = instance.getLastModified(file);
                }
                if (n == 1) {
                    FactoryFinder.jaxpProperties = new Properties();
                    fileInputStream = instance.getFileInputStream(file);
                    FactoryFinder.jaxpProperties.load(fileInputStream);
                }
            }
            catch (Exception ex3) {
                FactoryFinder.lastModified = -1L;
                FactoryFinder.jaxpProperties = null;
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
        if (FactoryFinder.jaxpProperties != null) {
            final String property = FactoryFinder.jaxpProperties.getProperty(s);
            if (property != null) {
                dPrint("found in jaxp.properties, value=" + property);
                return newInstance(property, classLoader, true);
            }
        }
        final Object jarServiceProvider = findJarServiceProvider(s);
        if (jarServiceProvider != null) {
            return jarServiceProvider;
        }
        if (s2 == null) {
            throw new ConfigurationError("Provider for " + s + " cannot be found", null);
        }
        dPrint("using fallback, value=" + s2);
        return newInstance(s2, classLoader, true);
    }
    
    private static void dPrint(final String s) {
        if (FactoryFinder.debug) {
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
                    classLoader = ((FactoryFinder.class$javax$xml$datatype$FactoryFinder == null) ? (FactoryFinder.class$javax$xml$datatype$FactoryFinder = class$("javax.xml.datatype.FactoryFinder")) : FactoryFinder.class$javax$xml$datatype$FactoryFinder).getClassLoader();
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
    
    private static Object findJarServiceProvider(final String s) throws ConfigurationError {
        final SecuritySupport instance = SecuritySupport.getInstance();
        final String string = "META-INF/services/" + s;
        ClassLoader classLoader = instance.getContextClassLoader();
        InputStream inputStream;
        if (classLoader != null) {
            inputStream = instance.getResourceAsStream(classLoader, string);
            if (inputStream == null) {
                classLoader = ((FactoryFinder.class$javax$xml$datatype$FactoryFinder == null) ? (FactoryFinder.class$javax$xml$datatype$FactoryFinder = class$("javax.xml.datatype.FactoryFinder")) : FactoryFinder.class$javax$xml$datatype$FactoryFinder).getClassLoader();
                inputStream = instance.getResourceAsStream(classLoader, string);
            }
        }
        else {
            classLoader = ((FactoryFinder.class$javax$xml$datatype$FactoryFinder == null) ? (FactoryFinder.class$javax$xml$datatype$FactoryFinder = class$("javax.xml.datatype.FactoryFinder")) : FactoryFinder.class$javax$xml$datatype$FactoryFinder).getClassLoader();
            inputStream = instance.getResourceAsStream(classLoader, string);
        }
        if (inputStream == null) {
            return null;
        }
        dPrint("found jar resource=" + string + " using ClassLoader: " + classLoader);
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
            dPrint("found in resource, value=" + line);
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
        FactoryFinder.jaxpProperties = null;
        FactoryFinder.lastModified = -1L;
        try {
            final String systemProperty = SecuritySupport.getInstance().getSystemProperty("jaxp.debug");
            FactoryFinder.debug = (systemProperty != null && !"false".equals(systemProperty));
        }
        catch (SecurityException ex) {
            FactoryFinder.debug = false;
        }
    }
    
    static class ConfigurationError extends Error
    {
        private static final long serialVersionUID = -3644413026244211347L;
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
