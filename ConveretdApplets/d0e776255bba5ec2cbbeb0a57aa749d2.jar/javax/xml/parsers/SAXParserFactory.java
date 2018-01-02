// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.parsers;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXException;

public abstract class SAXParserFactory
{
    private static final String defaultPropName = "javax.xml.parsers.SAXParserFactory";
    private boolean validating;
    private boolean namespaceAware;
    private static String foundFactory;
    private static final boolean debug;
    static /* synthetic */ Class class$javax$xml$parsers$SAXParserFactory;
    
    protected SAXParserFactory() {
        this.validating = false;
        this.namespaceAware = false;
    }
    
    public static SAXParserFactory newInstance() {
        final String factory = findFactory("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
        if (factory == null) {
            throw new FactoryConfigurationError("No default implementation found");
        }
        SAXParserFactory saxParserFactory;
        try {
            saxParserFactory = (SAXParserFactory)Class.forName(factory).newInstance();
        }
        catch (ClassNotFoundException ex) {
            throw new FactoryConfigurationError(ex);
        }
        catch (IllegalAccessException ex2) {
            throw new FactoryConfigurationError(ex2);
        }
        catch (InstantiationException ex3) {
            throw new FactoryConfigurationError(ex3);
        }
        return saxParserFactory;
    }
    
    public abstract SAXParser newSAXParser() throws ParserConfigurationException, SAXException;
    
    public void setNamespaceAware(final boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
    }
    
    public void setValidating(final boolean validating) {
        this.validating = validating;
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public boolean isValidating() {
        return this.validating;
    }
    
    public abstract void setFeature(final String p0, final boolean p1) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException;
    
    public abstract boolean getFeature(final String p0) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException;
    
    private static String findFactory(final String s, final String s2) {
        try {
            final String property = System.getProperty(s);
            if (property != null) {
                if (SAXParserFactory.debug) {
                    System.err.println("JAXP: found system property" + property);
                }
                return property;
            }
        }
        catch (SecurityException ex3) {}
        if (SAXParserFactory.foundFactory != null) {
            return SAXParserFactory.foundFactory;
        }
        try {
            final File file = new File(System.getProperty("java.home") + File.separator + "lib" + File.separator + "jaxp.properties");
            if (file.exists()) {
                final Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                SAXParserFactory.foundFactory = properties.getProperty(s);
                if (SAXParserFactory.debug) {
                    System.err.println("JAXP: found java.home property " + SAXParserFactory.foundFactory);
                }
                if (SAXParserFactory.foundFactory != null) {
                    return SAXParserFactory.foundFactory;
                }
            }
        }
        catch (Exception ex) {
            if (SAXParserFactory.debug) {
                ex.printStackTrace();
            }
        }
        final String string = "META-INF/services/" + s;
        try {
            final ClassLoader classLoader = ((SAXParserFactory.class$javax$xml$parsers$SAXParserFactory == null) ? (SAXParserFactory.class$javax$xml$parsers$SAXParserFactory = class$("javax.xml.parsers.SAXParserFactory")) : SAXParserFactory.class$javax$xml$parsers$SAXParserFactory).getClassLoader();
            InputStream inputStream;
            if (classLoader == null) {
                inputStream = ClassLoader.getSystemResourceAsStream(string);
            }
            else {
                inputStream = classLoader.getResourceAsStream(string);
            }
            if (inputStream != null) {
                if (SAXParserFactory.debug) {
                    System.err.println("JAXP: found  " + string);
                }
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                SAXParserFactory.foundFactory = bufferedReader.readLine();
                bufferedReader.close();
                if (SAXParserFactory.debug) {
                    System.err.println("JAXP: loaded from services: " + SAXParserFactory.foundFactory);
                }
                if (SAXParserFactory.foundFactory != null && !"".equals(SAXParserFactory.foundFactory)) {
                    return SAXParserFactory.foundFactory;
                }
            }
        }
        catch (Exception ex2) {
            if (SAXParserFactory.debug) {
                ex2.printStackTrace();
            }
        }
        return s2;
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
        SAXParserFactory.foundFactory = null;
        debug = (System.getProperty("jaxp.debug") != null);
    }
}
