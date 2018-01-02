// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;

public abstract class TransformerFactory
{
    private static final String defaultPropName = "javax.xml.transform.TransformerFactory";
    private static String foundFactory;
    private static boolean debug;
    static /* synthetic */ Class class$javax$xml$transform$TransformerFactory;
    
    public static TransformerFactory newInstance() throws TransformerFactoryConfigurationError {
        final String classname = findFactory("javax.xml.transform.TransformerFactory", "org.apache.xalan.processor.TransformerFactoryImpl");
        if (classname == null) {
            throw new TransformerFactoryConfigurationError("No default implementation found");
        }
        TransformerFactory factoryImpl;
        try {
            final Class clazz = Class.forName(classname);
            factoryImpl = clazz.newInstance();
        }
        catch (ClassNotFoundException cnfe) {
            throw new TransformerFactoryConfigurationError(cnfe);
        }
        catch (IllegalAccessException iae) {
            throw new TransformerFactoryConfigurationError(iae);
        }
        catch (InstantiationException ie) {
            throw new TransformerFactoryConfigurationError(ie);
        }
        return factoryImpl;
    }
    
    public abstract Transformer newTransformer(final Source p0) throws TransformerConfigurationException;
    
    public abstract Transformer newTransformer() throws TransformerConfigurationException;
    
    public abstract Templates newTemplates(final Source p0) throws TransformerConfigurationException;
    
    public abstract Source getAssociatedStylesheet(final Source p0, final String p1, final String p2, final String p3) throws TransformerConfigurationException;
    
    public abstract void setURIResolver(final URIResolver p0);
    
    public abstract URIResolver getURIResolver();
    
    public abstract boolean getFeature(final String p0);
    
    public abstract void setAttribute(final String p0, final Object p1) throws IllegalArgumentException;
    
    public abstract Object getAttribute(final String p0) throws IllegalArgumentException;
    
    public abstract void setErrorListener(final ErrorListener p0) throws IllegalArgumentException;
    
    public abstract ErrorListener getErrorListener();
    
    private static String findFactory(final String factoryId, final String defaultFactory) {
        try {
            final String systemProp = System.getProperty(factoryId);
            if (systemProp != null) {
                if (TransformerFactory.debug) {
                    System.err.println("JAXP: found system property" + systemProp);
                }
                return systemProp;
            }
        }
        catch (SecurityException ex3) {}
        if (TransformerFactory.foundFactory != null) {
            return TransformerFactory.foundFactory;
        }
        try {
            final String javah = System.getProperty("java.home");
            final String configFile = javah + File.separator + "lib" + File.separator + "jaxp.properties";
            final File f = new File(configFile);
            if (f.exists()) {
                final Properties props = new Properties();
                props.load(new FileInputStream(f));
                TransformerFactory.foundFactory = props.getProperty(factoryId);
                if (TransformerFactory.debug) {
                    System.err.println("JAXP: found java.home property " + TransformerFactory.foundFactory);
                }
                if (TransformerFactory.foundFactory != null) {
                    return TransformerFactory.foundFactory;
                }
            }
        }
        catch (Exception ex) {
            if (TransformerFactory.debug) {
                ex.printStackTrace();
            }
        }
        final String serviceId = "META-INF/services/" + factoryId;
        try {
            final ClassLoader cl = ((TransformerFactory.class$javax$xml$transform$TransformerFactory == null) ? (TransformerFactory.class$javax$xml$transform$TransformerFactory = class$("javax.xml.transform.TransformerFactory")) : TransformerFactory.class$javax$xml$transform$TransformerFactory).getClassLoader();
            InputStream is = null;
            if (cl == null) {
                is = ClassLoader.getSystemResourceAsStream(serviceId);
            }
            else {
                is = cl.getResourceAsStream(serviceId);
            }
            if (is != null) {
                if (TransformerFactory.debug) {
                    System.err.println("JAXP: found  " + serviceId);
                }
                final BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                TransformerFactory.foundFactory = rd.readLine();
                rd.close();
                if (TransformerFactory.debug) {
                    System.err.println("JAXP: loaded from services: " + TransformerFactory.foundFactory);
                }
                if (TransformerFactory.foundFactory != null && !"".equals(TransformerFactory.foundFactory)) {
                    return TransformerFactory.foundFactory;
                }
            }
        }
        catch (Exception ex2) {
            if (TransformerFactory.debug) {
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
        TransformerFactory.foundFactory = null;
        TransformerFactory.debug = false;
        try {
            TransformerFactory.debug = (System.getProperty("jaxp.debug") != null);
        }
        catch (Exception ex) {}
    }
}
