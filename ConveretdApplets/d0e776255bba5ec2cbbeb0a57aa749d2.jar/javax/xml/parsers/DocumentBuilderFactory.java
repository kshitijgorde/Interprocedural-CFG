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
    private static final boolean debug;
    static /* synthetic */ Class class$javax$xml$parsers$DocumentBuilderFactory;
    
    protected DocumentBuilderFactory() {
        this.validating = false;
        this.namespaceAware = false;
        this.whitespace = false;
        this.expandEntityRef = false;
        this.ignoreComments = false;
        this.coalescing = false;
    }
    
    public static DocumentBuilderFactory newInstance() {
        final String factory = findFactory("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
        if (factory == null) {
            throw new FactoryConfigurationError("No default implementation found");
        }
        DocumentBuilderFactory documentBuilderFactory;
        try {
            documentBuilderFactory = (DocumentBuilderFactory)Class.forName(factory).newInstance();
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
        return documentBuilderFactory;
    }
    
    public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;
    
    public void setNamespaceAware(final boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
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
    
    private static String findFactory(final String s, final String s2) {
        try {
            final String property = System.getProperty(s);
            if (property != null) {
                if (DocumentBuilderFactory.debug) {
                    System.err.println("JAXP: found system property" + property);
                }
                return property;
            }
        }
        catch (SecurityException ex3) {}
        if (DocumentBuilderFactory.foundFactory != null) {
            return DocumentBuilderFactory.foundFactory;
        }
        try {
            final File file = new File(System.getProperty("java.home") + File.separator + "lib" + File.separator + "jaxp.properties");
            if (file.exists()) {
                final Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                DocumentBuilderFactory.foundFactory = properties.getProperty(s);
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
        final String string = "META-INF/services/" + s;
        try {
            final ClassLoader classLoader = ((DocumentBuilderFactory.class$javax$xml$parsers$DocumentBuilderFactory == null) ? (DocumentBuilderFactory.class$javax$xml$parsers$DocumentBuilderFactory = class$("javax.xml.parsers.DocumentBuilderFactory")) : DocumentBuilderFactory.class$javax$xml$parsers$DocumentBuilderFactory).getClassLoader();
            InputStream inputStream;
            if (classLoader == null) {
                inputStream = ClassLoader.getSystemResourceAsStream(string);
            }
            else {
                inputStream = classLoader.getResourceAsStream(string);
            }
            if (inputStream != null) {
                if (DocumentBuilderFactory.debug) {
                    System.err.println("JAXP: found  " + string);
                }
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                DocumentBuilderFactory.foundFactory = bufferedReader.readLine();
                bufferedReader.close();
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
        DocumentBuilderFactory.foundFactory = null;
        debug = (System.getProperty("jaxp.debug") != null);
    }
}
