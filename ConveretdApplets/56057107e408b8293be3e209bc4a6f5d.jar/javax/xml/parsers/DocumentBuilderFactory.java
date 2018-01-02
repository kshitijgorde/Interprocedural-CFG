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

public abstract class DocumentBuilderFactory
{
    private static final String defaultPropName = "javax.xml.parsers.DocumentBuilderFactory";
    private boolean validating;
    private boolean namespaceAware;
    private boolean whitespace;
    private boolean expandEntityRef;
    private boolean ignoreComments;
    private boolean coalescing;
    private static String foundFactory;
    private static boolean debug;
    static /* synthetic */ Class class$javax$xml$parsers$DocumentBuilderFactory;
    
    protected DocumentBuilderFactory() {
        this.validating = false;
        this.namespaceAware = false;
        this.whitespace = false;
        this.expandEntityRef = true;
        this.ignoreComments = false;
        this.coalescing = false;
    }
    
    public static DocumentBuilderFactory newInstance() {
        final String factoryImplName = findFactory("javax.xml.parsers.DocumentBuilderFactory", "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl");
        if (factoryImplName == null) {
            throw new FactoryConfigurationError("No default implementation found");
        }
        DocumentBuilderFactory factoryImpl;
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
    
    public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;
    
    public void setNamespaceAware(final boolean awareness) {
        this.namespaceAware = awareness;
    }
    
    public void setValidating(final boolean validating) {
        this.validating = validating;
    }
    
    public void setIgnoringElementContentWhitespace(final boolean whitespace) {
        this.whitespace = whitespace;
    }
    
    public void setExpandEntityReferences(final boolean expandEntityRef) {
        this.expandEntityRef = expandEntityRef;
    }
    
    public void setIgnoringComments(final boolean ignoreComments) {
        this.ignoreComments = ignoreComments;
    }
    
    public void setCoalescing(final boolean coalescing) {
        this.coalescing = coalescing;
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public boolean isValidating() {
        return this.validating;
    }
    
    public boolean isIgnoringElementContentWhitespace() {
        return this.whitespace;
    }
    
    public boolean isExpandEntityReferences() {
        return this.expandEntityRef;
    }
    
    public boolean isIgnoringComments() {
        return this.ignoreComments;
    }
    
    public boolean isCoalescing() {
        return this.coalescing;
    }
    
    public abstract void setAttribute(final String p0, final Object p1) throws IllegalArgumentException;
    
    public abstract Object getAttribute(final String p0) throws IllegalArgumentException;
    
    private static String findFactory(final String factoryId, final String defaultFactory) {
        try {
            final String systemProp = System.getProperty(factoryId);
            if (systemProp != null) {
                if (DocumentBuilderFactory.debug) {
                    System.err.println("JAXP: found system property" + systemProp);
                }
                return systemProp;
            }
        }
        catch (SecurityException ex3) {}
        if (DocumentBuilderFactory.foundFactory != null) {
            return DocumentBuilderFactory.foundFactory;
        }
        try {
            final String javah = System.getProperty("java.home");
            final String configFile = javah + File.separator + "lib" + File.separator + "jaxp.properties";
            final File f = new File(configFile);
            if (f.exists()) {
                final Properties props = new Properties();
                props.load(new FileInputStream(f));
                DocumentBuilderFactory.foundFactory = props.getProperty(factoryId);
                if (DocumentBuilderFactory.debug) {
                    System.err.println("JAXP: found java.home property " + DocumentBuilderFactory.foundFactory);
                }
                if (DocumentBuilderFactory.foundFactory != null) {
                    return DocumentBuilderFactory.foundFactory;
                }
            }
        }
        catch (Exception ex) {
            if (DocumentBuilderFactory.debug) {
                ex.printStackTrace();
            }
        }
        final String serviceId = "META-INF/services/" + factoryId;
        try {
            final ClassLoader cl = ((DocumentBuilderFactory.class$javax$xml$parsers$DocumentBuilderFactory == null) ? (DocumentBuilderFactory.class$javax$xml$parsers$DocumentBuilderFactory = class$("javax.xml.parsers.DocumentBuilderFactory")) : DocumentBuilderFactory.class$javax$xml$parsers$DocumentBuilderFactory).getClassLoader();
            InputStream is = null;
            if (cl == null) {
                is = ClassLoader.getSystemResourceAsStream(serviceId);
            }
            else {
                is = cl.getResourceAsStream(serviceId);
            }
            if (is != null) {
                if (DocumentBuilderFactory.debug) {
                    System.err.println("JAXP: found  " + serviceId);
                }
                final BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                DocumentBuilderFactory.foundFactory = rd.readLine();
                rd.close();
                if (DocumentBuilderFactory.debug) {
                    System.err.println("JAXP: loaded from services: " + DocumentBuilderFactory.foundFactory);
                }
                if (DocumentBuilderFactory.foundFactory != null && !"".equals(DocumentBuilderFactory.foundFactory)) {
                    return DocumentBuilderFactory.foundFactory;
                }
            }
        }
        catch (Exception ex2) {
            if (DocumentBuilderFactory.debug) {
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
        DocumentBuilderFactory.foundFactory = null;
        DocumentBuilderFactory.debug = false;
        try {
            DocumentBuilderFactory.debug = (System.getProperty("jaxp.debug") != null);
        }
        catch (Exception ex) {}
    }
}
