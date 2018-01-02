// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.util.Hashtable;
import org.apache.xalan.transformer.TrAXFilter;
import org.xml.sax.XMLFilter;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.sax.TransformerHandler;
import org.apache.xalan.transformer.TransformerIdentityImpl;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.TemplatesHandler;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import java.io.File;
import javax.xml.transform.Templates;
import java.util.Enumeration;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Properties;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.TreeWalker;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import org.apache.xml.utils.DefaultErrorHandler;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXTransformerFactory;

public class TransformerFactoryImpl extends SAXTransformerFactory
{
    public static String XSLT_PROPERTIES;
    private static boolean isInited;
    private String m_DOMsystemID;
    URIResolver m_uriResolver;
    private ErrorListener m_errorListener;
    static /* synthetic */ Class class$java$lang$Process;
    
    static {
        TransformerFactoryImpl.XSLT_PROPERTIES = "/org/apache/xalan/res/XSLTInfo.properties";
        TransformerFactoryImpl.isInited = false;
    }
    
    public TransformerFactoryImpl() {
        this.m_DOMsystemID = null;
        this.m_errorListener = new DefaultErrorHandler();
        loadPropertyFileToSystem(TransformerFactoryImpl.XSLT_PROPERTIES);
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    public Source getAssociatedStylesheet(final Source source, final String media, final String title, final String charset) throws TransformerConfigurationException {
        InputSource isource = null;
        Node node = null;
        XMLReader reader = null;
        String baseID;
        if (source instanceof DOMSource) {
            final DOMSource dsource = (DOMSource)source;
            node = dsource.getNode();
            baseID = dsource.getSystemId();
        }
        else {
            isource = SAXSource.sourceToInputSource(source);
            baseID = isource.getSystemId();
        }
        final StylesheetPIHandler handler = new StylesheetPIHandler(baseID, media, title, charset);
        try {
            if (node != null) {
                final TreeWalker walker = new TreeWalker(handler);
                walker.traverse(node);
            }
            else {
                try {
                    final SAXParserFactory factory = SAXParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    final SAXParser jaxpParser = factory.newSAXParser();
                    reader = jaxpParser.getXMLReader();
                }
                catch (ParserConfigurationException ex) {
                    throw new SAXException(ex);
                }
                catch (FactoryConfigurationError ex2) {
                    throw new SAXException(ex2.toString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                catch (AbstractMethodError abstractMethodError) {}
                if (reader == null) {
                    reader = XMLReaderFactory.createXMLReader();
                }
                reader.setContentHandler(handler);
                reader.parse(isource);
            }
        }
        catch (StopParseException ex3) {}
        catch (SAXException se) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", se);
        }
        catch (IOException ioe) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", ioe);
        }
        return handler.getAssociatedStylesheet();
    }
    
    public Object getAttribute(final String name) throws IllegalArgumentException {
        throw new IllegalArgumentException(name);
    }
    
    String getDOMsystemID() {
        return this.m_DOMsystemID;
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }
    
    public boolean getFeature(final String name) {
        return name == "http://javax.xml.transform.dom.DOMResult/feature" || name == "http://javax.xml.transform.dom.DOMSource/feature" || name == "http://javax.xml.transform.sax.SAXResult/feature" || name == "http://javax.xml.transform.sax.SAXSource/feature" || name == "http://javax.xml.transform.stream.StreamResult/feature" || name == "http://javax.xml.transform.stream.StreamSource/feature" || name == "http://javax.xml.transform.sax.SAXTransformerFactory/feature" || name == "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter" || ("http://javax.xml.transform.dom.DOMResult/feature".equals(name) || "http://javax.xml.transform.dom.DOMSource/feature".equals(name) || "http://javax.xml.transform.sax.SAXResult/feature".equals(name) || "http://javax.xml.transform.sax.SAXSource/feature".equals(name) || "http://javax.xml.transform.stream.StreamResult/feature".equals(name) || "http://javax.xml.transform.stream.StreamSource/feature".equals(name) || "http://javax.xml.transform.sax.SAXTransformerFactory/feature".equals(name) || "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter".equals(name));
    }
    
    public URIResolver getURIResolver() {
        return this.m_uriResolver;
    }
    
    private static void loadPropertyFileToSystem(final String file) {
        if (!TransformerFactoryImpl.isInited) {
            try {
                try {
                    final Properties props = new Properties();
                    final InputStream is = ((TransformerFactoryImpl.class$java$lang$Process != null) ? TransformerFactoryImpl.class$java$lang$Process : (TransformerFactoryImpl.class$java$lang$Process = class$("java.lang.Process"))).getResourceAsStream(file);
                    final BufferedInputStream bis = new BufferedInputStream(is);
                    props.load(bis);
                    bis.close();
                    final Properties systemProps = System.getProperties();
                    final Enumeration propEnum = props.propertyNames();
                    while (propEnum.hasMoreElements()) {
                        final String prop = propEnum.nextElement();
                        if (!systemProps.containsKey(prop)) {
                            ((Hashtable<String, String>)systemProps).put(prop, props.getProperty(prop));
                        }
                    }
                    System.setProperties(systemProps);
                    TransformerFactoryImpl.isInited = true;
                }
                catch (Exception ex) {}
            }
            catch (SecurityException ex2) {}
        }
    }
    
    public Templates newTemplates(final Source source) throws TransformerConfigurationException {
        final TemplatesHandler builder = this.newTemplatesHandler();
        String baseID = source.getSystemId();
        if (baseID == null) {
            try {
                final String currentDir = System.getProperty("user.dir");
                baseID = "file:///" + currentDir + File.separatorChar + source.getClass().getName();
            }
            catch (SecurityException ex4) {}
        }
        builder.setSystemId(baseID);
        if (!(source instanceof DOMSource)) {
            try {
                final InputSource isource = SAXSource.sourceToInputSource(source);
                XMLReader reader = null;
                if (source instanceof SAXSource) {
                    reader = ((SAXSource)source).getXMLReader();
                }
                if (reader == null) {
                    try {
                        final SAXParserFactory factory = SAXParserFactory.newInstance();
                        factory.setNamespaceAware(true);
                        final SAXParser jaxpParser = factory.newSAXParser();
                        reader = jaxpParser.getXMLReader();
                    }
                    catch (ParserConfigurationException ex) {
                        throw new SAXException(ex);
                    }
                    catch (FactoryConfigurationError ex2) {
                        throw new SAXException(ex2.toString());
                    }
                    catch (NoSuchMethodError noSuchMethodError) {}
                    catch (AbstractMethodError abstractMethodError) {}
                }
                if (reader == null) {
                    reader = XMLReaderFactory.createXMLReader();
                }
                try {
                    reader.setFeature("http://apache.org/xml/features/validation/dynamic", true);
                }
                catch (SAXException ex5) {}
                reader.setContentHandler(builder);
                reader.parse(isource);
            }
            catch (IOException ioe) {
                if (this.m_errorListener != null) {
                    try {
                        this.m_errorListener.fatalError(new TransformerException(ioe));
                        return null;
                    }
                    catch (TransformerException ex3) {
                        throw new TransformerConfigurationException(ex3);
                    }
                }
                throw new TransformerConfigurationException(ioe.getMessage(), ioe);
            }
            catch (SAXException se) {
                if (this.m_errorListener != null) {
                    try {
                        this.m_errorListener.fatalError(new TransformerException(se));
                        return builder.getTemplates();
                    }
                    catch (TransformerException ex3) {
                        throw new TransformerConfigurationException(ex3);
                    }
                }
                throw new TransformerConfigurationException(se.getMessage(), se);
            }
            return builder.getTemplates();
        }
        final DOMSource dsource = (DOMSource)source;
        final Node node = dsource.getNode();
        if (node != null) {
            return this.processFromNode(node, baseID);
        }
        final String messageStr = XSLMessages.createMessage(108, null);
        throw new IllegalArgumentException(messageStr);
    }
    
    public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
        return new StylesheetHandler(this);
    }
    
    public Transformer newTransformer() throws TransformerConfigurationException {
        return new TransformerIdentityImpl();
    }
    
    public Transformer newTransformer(final Source source) throws TransformerConfigurationException {
        try {
            final Templates tmpl = this.newTemplates(source);
            if (tmpl == null) {
                return null;
            }
            return tmpl.newTransformer();
        }
        catch (TransformerConfigurationException ex) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(ex);
                    return null;
                }
                catch (TransformerException ex2) {
                    ex = new TransformerConfigurationException(ex2);
                }
            }
            throw ex;
        }
    }
    
    public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
        return new TransformerIdentityImpl();
    }
    
    public TransformerHandler newTransformerHandler(final Source src) throws TransformerConfigurationException {
        final Templates templates = this.newTemplates(src);
        if (templates == null) {
            return null;
        }
        return this.newTransformerHandler(templates);
    }
    
    public TransformerHandler newTransformerHandler(final Templates templates) throws TransformerConfigurationException {
        try {
            final TransformerImpl transformer = (TransformerImpl)templates.newTransformer();
            final TransformerHandler th = (TransformerHandler)transformer.getInputContentHandler(true);
            return th;
        }
        catch (TransformerConfigurationException ex) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(ex);
                    return null;
                }
                catch (TransformerException ex2) {
                    ex = new TransformerConfigurationException(ex2);
                }
            }
            throw ex;
        }
    }
    
    public XMLFilter newXMLFilter(final Source src) throws TransformerConfigurationException {
        final Templates templates = this.newTemplates(src);
        if (templates == null) {
            return null;
        }
        return this.newXMLFilter(templates);
    }
    
    public XMLFilter newXMLFilter(final Templates templates) throws TransformerConfigurationException {
        try {
            return new TrAXFilter(templates);
        }
        catch (TransformerConfigurationException ex) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(ex);
                    return null;
                }
                catch (TransformerException ex2) {
                    new TransformerConfigurationException(ex2);
                }
            }
            throw ex;
        }
    }
    
    public Templates processFromNode(final Node node) throws TransformerConfigurationException {
        try {
            final TemplatesHandler builder = this.newTemplatesHandler();
            final TreeWalker walker = new TreeWalker(builder);
            walker.traverse(node);
            return builder.getTemplates();
        }
        catch (SAXException se) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(new TransformerException(se));
                }
                catch (TransformerException ex) {
                    throw new TransformerConfigurationException(ex);
                }
                return null;
            }
            throw new TransformerConfigurationException("processFromNode failed", se);
        }
    }
    
    Templates processFromNode(final Node node, final String systemID) throws TransformerConfigurationException {
        this.m_DOMsystemID = systemID;
        return this.processFromNode(node);
    }
    
    public void setAttribute(final String name, final Object value) throws IllegalArgumentException {
        throw new IllegalArgumentException(name);
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        if (listener == null) {
            throw new IllegalArgumentException("ErrorListener");
        }
        this.m_errorListener = listener;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_uriResolver = resolver;
    }
}
