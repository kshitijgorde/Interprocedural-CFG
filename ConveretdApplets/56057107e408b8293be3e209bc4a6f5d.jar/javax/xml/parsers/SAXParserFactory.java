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
    private static boolean debug;
    static /* synthetic */ Class class$javax$xml$parsers$SAXParserFactory;
    
    protected SAXParserFactory() {
        this.validating = false;
        this.namespaceAware = false;
    }
    
    public static SAXParserFactory newInstance() {
        final String factoryImplName = findFactory("javax.xml.parsers.SAXParserFactory", "org.apache.crimson.jaxp.SAXParserFactoryImpl");
        if (factoryImplName == null) {
            throw new FactoryConfigurationError("No default implementation found");
        }
        SAXParserFactory factoryImpl = null;
        try {
            final Class clazz = Class.forName(factoryImplName);
            factoryImpl = clazz.newInstance();
        }
        catch (ClassNotFoundException cnfe) {
            throw new FactoryConfigurationError(cnfe);
        }
        catch (IllegalAccessException iae) {
            throw new FactoryConfigurationError(iae);
        }
        catch (InstantiationException ie) {
            throw new FactoryConfigurationError(ie);
        }
        return factoryImpl;
    }
    
    public abstract SAXParser newSAXParser() throws ParserConfigurationException, SAXException;
    
    public void setNamespaceAware(final boolean awareness) {
        this.namespaceAware = awareness;
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
    
    private static String findFactory(final String factoryId, final String defaultFactory) {
        if (SAXParserFactory.foundFactory != null) {
            return SAXParserFactory.foundFactory;
        }
        try {
            SAXParserFactory.foundFactory = System.getProperty(factoryId);
            if (SAXParserFactory.foundFactory != null) {
                if (SAXParserFactory.debug) {
                    System.err.println("JAXP: found system property" + SAXParserFactory.foundFactory);
                }
                return SAXParserFactory.foundFactory;
            }
        }
        catch (SecurityException ex3) {}
        try {
            final String javah = System.getProperty("java.home");
            final String configFile = javah + File.separator + "lib" + File.separator + "jaxp.properties";
            final File f = new File(configFile);
            if (f.exists()) {
                final Properties props = new Properties();
                props.load(new FileInputStream(f));
                SAXParserFactory.foundFactory = props.getProperty(factoryId);
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
        final String serviceId = "META-INF/services/" + factoryId;
        try {
            final ClassLoader cl = ((SAXParserFactory.class$javax$xml$parsers$SAXParserFactory == null) ? (SAXParserFactory.class$javax$xml$parsers$SAXParserFactory = class$("javax.xml.parsers.SAXParserFactory")) : SAXParserFactory.class$javax$xml$parsers$SAXParserFactory).getClassLoader();
            InputStream is = null;
            if (cl == null) {
                is = ClassLoader.getSystemResourceAsStream(serviceId);
            }
            else {
                is = cl.getResourceAsStream(serviceId);
            }
            if (is != null) {
                if (SAXParserFactory.debug) {
                    System.err.println("JAXP: found  " + serviceId);
                }
                final BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                SAXParserFactory.foundFactory = rd.readLine();
                rd.close();
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
        return defaultFactory;
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
        SAXParserFactory.foundFactory = null;
        SAXParserFactory.debug = false;
        try {
            SAXParserFactory.debug = (System.getProperty("jaxp.debug") != null);
        }
        catch (Exception ex) {}
    }
}
