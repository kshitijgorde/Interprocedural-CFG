// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xml.utils.SystemIDResolver;
import javax.xml.transform.Transformer;
import org.apache.xalan.transformer.TransformerIdentityImpl;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.sax.TransformerHandler;
import org.apache.xalan.transformer.TrAXFilter;
import org.xml.sax.XMLFilter;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import java.io.IOException;
import org.apache.xml.utils.StopParseException;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.xml.utils.StylesheetPIHandler;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.sax.TemplatesHandler;
import org.xml.sax.SAXException;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.StylesheetRootProxy;
import org.apache.xml.utils.DOMHelper;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.TreeWalker;
import org.apache.xml.utils.DOM2Helper;
import javax.xml.transform.Templates;
import org.w3c.dom.Node;
import org.apache.xml.utils.DefaultErrorHandler;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXTransformerFactory;

public class TransformerFactoryImpl extends SAXTransformerFactory
{
    private boolean m_serializable;
    public static final String XSLT_PROPERTIES = "org/apache/xalan/res/XSLTInfo.properties";
    public static final String FEATURE_INCREMENTAL = "http://xml.apache.org/xalan/features/incremental";
    public static final String FEATURE_OPTIMIZE = "http://xml.apache.org/xalan/features/optimize";
    public static final String FEATURE_SOURCE_LOCATION = "http://xml.apache.org/xalan/properties/source-location";
    public static final String FEATURE_SERIALIZABLE = "http://www.ibm.com/xmlns/prod/xslt4j/serializable-templates";
    private String m_DOMsystemID;
    private boolean m_isSecureProcessing;
    private boolean m_optimize;
    private boolean m_source_location;
    private boolean m_incremental;
    URIResolver m_uriResolver;
    private ErrorListener m_errorListener;
    
    public TransformerFactoryImpl() {
        this.m_serializable = false;
        this.m_DOMsystemID = null;
        this.m_isSecureProcessing = false;
        this.m_optimize = true;
        this.m_source_location = false;
        this.m_incremental = false;
        this.m_errorListener = new DefaultErrorHandler(false);
    }
    
    public Templates processFromNode(final Node node) throws TransformerConfigurationException {
        try {
            final TemplatesHandler builder = this.newTemplatesHandler();
            if (this.m_serializable) {
                final TreeWalker walker = new TreeWalker(builder, new DOM2Helper(), builder.getSystemId());
                walker.traverse(node);
                return builder.getTemplates();
            }
            return new StylesheetRootProxy(builder, node, this.m_isSecureProcessing);
        }
        catch (SAXException se) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(new TransformerException(se));
                }
                catch (TransformerConfigurationException ex) {
                    throw ex;
                }
                catch (TransformerException ex2) {
                    throw new TransformerConfigurationException(ex2);
                }
                return null;
            }
            throw new TransformerConfigurationException(XSLMessages.createMessage("ER_PROCESSFROMNODE_FAILED", null), se);
        }
        catch (TransformerConfigurationException tce) {
            throw tce;
        }
        catch (Exception e) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(new TransformerException(e));
                }
                catch (TransformerConfigurationException ex3) {
                    throw ex3;
                }
                catch (TransformerException ex4) {
                    throw new TransformerConfigurationException(ex4);
                }
                return null;
            }
            throw new TransformerConfigurationException(XSLMessages.createMessage("ER_PROCESSFROMNODE_FAILED", null), e);
        }
    }
    
    String getDOMsystemID() {
        return this.m_DOMsystemID;
    }
    
    Templates processFromNode(final Node node, final String systemID) throws TransformerConfigurationException {
        this.m_DOMsystemID = systemID;
        return this.processFromNode(node);
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
        if (this.m_uriResolver != null) {
            handler.setURIResolver(this.m_uriResolver);
        }
        try {
            if (null != node) {
                final TreeWalker walker = new TreeWalker(handler, new DOM2Helper(), baseID);
                walker.traverse(node);
            }
            else {
                try {
                    final SAXParserFactory factory = SAXParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    if (this.m_isSecureProcessing) {
                        try {
                            factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                        }
                        catch (SAXException ex4) {}
                    }
                    final SAXParser jaxpParser = factory.newSAXParser();
                    reader = jaxpParser.getXMLReader();
                }
                catch (ParserConfigurationException ex) {
                    throw new SAXException(ex);
                }
                catch (FactoryConfigurationError ex2) {
                    throw new SAXException(ex2.toString());
                }
                catch (NoSuchMethodError ex3) {}
                catch (AbstractMethodError abstractMethodError) {}
                if (null == reader) {
                    reader = XMLReaderFactory.createXMLReader();
                }
                reader.setContentHandler(handler);
                reader.parse(isource);
            }
        }
        catch (StopParseException spe) {}
        catch (SAXException se) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", se);
        }
        catch (IOException ioe) {
            throw new TransformerConfigurationException("getAssociatedStylesheets failed", ioe);
        }
        return handler.getAssociatedStylesheet();
    }
    
    public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
        return new StylesheetHandler(this);
    }
    
    public boolean getFeature(final String name) {
        if (name == null) {
            throw new NullPointerException(XSLMessages.createMessage("ER_GET_FEATURE_NULL_NAME", null));
        }
        if ("http://javax.xml.transform.dom.DOMResult/feature" == name || "http://javax.xml.transform.dom.DOMSource/feature" == name || "http://javax.xml.transform.sax.SAXResult/feature" == name || "http://javax.xml.transform.sax.SAXSource/feature" == name || "http://javax.xml.transform.stream.StreamResult/feature" == name || "http://javax.xml.transform.stream.StreamSource/feature" == name || "http://javax.xml.transform.sax.SAXTransformerFactory/feature" == name || "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter" == name) {
            return true;
        }
        if ("http://javax.xml.transform.dom.DOMResult/feature".equals(name) || "http://javax.xml.transform.dom.DOMSource/feature".equals(name) || "http://javax.xml.transform.sax.SAXResult/feature".equals(name) || "http://javax.xml.transform.sax.SAXSource/feature".equals(name) || "http://javax.xml.transform.stream.StreamResult/feature".equals(name) || "http://javax.xml.transform.stream.StreamSource/feature".equals(name) || "http://javax.xml.transform.sax.SAXTransformerFactory/feature".equals(name) || "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter".equals(name)) {
            return true;
        }
        try {
            return name.equals("http://javax.xml.XMLConstants/feature/secure-processing") && this.m_isSecureProcessing;
        }
        catch (NoClassDefFoundError e) {
            return false;
        }
    }
    
    public void setFeature(final String name, final boolean value) throws TransformerConfigurationException {
        if (name == null) {
            throw new NullPointerException(XSLMessages.createMessage("ER_SET_FEATURE_NULL_NAME", null));
        }
        if (name.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this.m_isSecureProcessing = value;
            return;
        }
        throw new TransformerConfigurationException(XSLMessages.createMessage("ER_UNSUPPORTED_FEATURE", new Object[] { name }));
    }
    
    public void setAttribute(final String name, final Object value) throws IllegalArgumentException {
        if (name.equals("http://xml.apache.org/xalan/features/incremental")) {
            if (value instanceof Boolean) {
                this.m_incremental = (boolean)value;
            }
            else {
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
                }
                this.m_incremental = new Boolean((String)value);
            }
        }
        else if (name.equals("http://xml.apache.org/xalan/features/optimize")) {
            if (value instanceof Boolean) {
                this.m_optimize = (boolean)value;
            }
            else {
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
                }
                this.m_optimize = new Boolean((String)value);
            }
        }
        else if (name.equals("http://xml.apache.org/xalan/properties/source-location")) {
            if (value instanceof Boolean) {
                this.m_source_location = (boolean)value;
            }
            else {
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
                }
                this.m_source_location = new Boolean((String)value);
            }
        }
        else {
            if (!name.equals("http://www.ibm.com/xmlns/prod/xslt4j/serializable-templates")) {
                throw new IllegalArgumentException(XSLMessages.createMessage("ER_NOT_SUPPORTED", new Object[] { name }));
            }
            if (value instanceof Boolean) {
                this.m_serializable = (boolean)value;
            }
            else {
                if (!(value instanceof String)) {
                    throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
                }
                this.m_serializable = new Boolean((String)value);
            }
        }
    }
    
    public Object getAttribute(final String name) throws IllegalArgumentException {
        if (name.equals("http://xml.apache.org/xalan/features/incremental")) {
            return new Boolean(this.m_incremental);
        }
        if (name.equals("http://xml.apache.org/xalan/features/optimize")) {
            return new Boolean(this.m_optimize);
        }
        if (name.equals("http://xml.apache.org/xalan/properties/source-location")) {
            return new Boolean(this.m_source_location);
        }
        if (name.equals("http://www.ibm.com/xmlns/prod/xslt4j/serializable-templates")) {
            return new Boolean(this.m_serializable);
        }
        throw new IllegalArgumentException(XSLMessages.createMessage("ER_ATTRIB_VALUE_NOT_RECOGNIZED", new Object[] { name }));
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
                catch (TransformerConfigurationException ex2) {
                    throw ex2;
                }
                catch (TransformerException ex3) {
                    throw new TransformerConfigurationException(ex3);
                }
            }
            throw ex;
        }
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
            transformer.setURIResolver(this.m_uriResolver);
            final TransformerHandler th = (TransformerHandler)transformer.getInputContentHandler(true);
            return th;
        }
        catch (TransformerConfigurationException ex) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(ex);
                    return null;
                }
                catch (TransformerConfigurationException ex2) {
                    throw ex2;
                }
                catch (TransformerException ex3) {
                    throw new TransformerConfigurationException(ex3);
                }
            }
            throw ex;
        }
    }
    
    public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
        return new TransformerIdentityImpl(this.m_isSecureProcessing);
    }
    
    public Transformer newTransformer(final Source source) throws TransformerConfigurationException {
        try {
            final Templates tmpl = this.newTemplates(source);
            if (tmpl == null) {
                return null;
            }
            final Transformer transformer = tmpl.newTransformer();
            transformer.setURIResolver(this.m_uriResolver);
            return transformer;
        }
        catch (TransformerConfigurationException ex) {
            if (this.m_errorListener != null) {
                try {
                    this.m_errorListener.fatalError(ex);
                    return null;
                }
                catch (TransformerConfigurationException ex2) {
                    throw ex2;
                }
                catch (TransformerException ex3) {
                    throw new TransformerConfigurationException(ex3);
                }
            }
            throw ex;
        }
    }
    
    public Transformer newTransformer() throws TransformerConfigurationException {
        return new TransformerIdentityImpl(this.m_isSecureProcessing);
    }
    
    public Templates newTemplates(final Source source) throws TransformerConfigurationException {
        String baseID = source.getSystemId();
        if (null != baseID) {
            baseID = SystemIDResolver.getAbsoluteURI(baseID);
        }
        if (!(source instanceof DOMSource)) {
            final TemplatesHandler builder = this.newTemplatesHandler();
            builder.setSystemId(baseID);
            try {
                final InputSource isource = SAXSource.sourceToInputSource(source);
                isource.setSystemId(baseID);
                XMLReader reader = null;
                if (source instanceof SAXSource) {
                    reader = ((SAXSource)source).getXMLReader();
                }
                if (null == reader) {
                    try {
                        final SAXParserFactory factory = SAXParserFactory.newInstance();
                        factory.setNamespaceAware(true);
                        if (this.m_isSecureProcessing) {
                            try {
                                factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                            }
                            catch (SAXException ex8) {}
                        }
                        final SAXParser jaxpParser = factory.newSAXParser();
                        reader = jaxpParser.getXMLReader();
                    }
                    catch (ParserConfigurationException ex) {
                        throw new SAXException(ex);
                    }
                    catch (FactoryConfigurationError ex2) {
                        throw new SAXException(ex2.toString());
                    }
                    catch (NoSuchMethodError ex7) {}
                    catch (AbstractMethodError abstractMethodError) {}
                }
                if (null == reader) {
                    reader = XMLReaderFactory.createXMLReader();
                }
                reader.setContentHandler(builder);
                if (this.m_serializable) {
                    reader.parse(isource);
                    return builder.getTemplates();
                }
                return new StylesheetRootProxy(reader, isource, this.m_isSecureProcessing);
            }
            catch (SAXException se) {
                if (this.m_errorListener == null) {
                    throw new TransformerConfigurationException(se.getMessage(), se);
                }
                try {
                    this.m_errorListener.fatalError(new TransformerException(se));
                }
                catch (TransformerConfigurationException ex3) {
                    throw ex3;
                }
                catch (TransformerException ex4) {
                    throw new TransformerConfigurationException(ex4);
                }
            }
            catch (Exception e) {
                if (this.m_errorListener != null) {
                    try {
                        this.m_errorListener.fatalError(new TransformerException(e));
                        return null;
                    }
                    catch (TransformerConfigurationException ex5) {
                        throw ex5;
                    }
                    catch (TransformerException ex6) {
                        throw new TransformerConfigurationException(ex6);
                    }
                }
                throw new TransformerConfigurationException(e.getMessage(), e);
            }
            return null;
        }
        final DOMSource dsource = (DOMSource)source;
        final Node node = dsource.getNode();
        if (null != node) {
            return this.processFromNode(node, baseID);
        }
        final String messageStr = XSLMessages.createMessage("ER_ILLEGAL_DOMSOURCE_INPUT", null);
        throw new IllegalArgumentException(messageStr);
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_uriResolver = resolver;
    }
    
    public URIResolver getURIResolver() {
        return this.m_uriResolver;
    }
    
    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        if (null == listener) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_ERRORLISTENER", null));
        }
        this.m_errorListener = listener;
    }
    
    public boolean isSecureProcessing() {
        return this.m_isSecureProcessing;
    }
}
