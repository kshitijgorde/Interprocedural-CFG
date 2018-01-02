// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import java.io.Reader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;
import java.io.InputStream;
import com.sun.java.util.collections.Iterator;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.ContentHandler;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.jdom.JDOMException;
import org.xml.sax.SAXParseException;
import org.jdom.Document;
import org.xml.sax.InputSource;
import com.sun.java.util.collections.HashMap;
import org.xml.sax.XMLFilter;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

public class SAXBuilder
{
    private static final String CVS_ID = "@(#) $RCSfile: SAXBuilder.java,v $ $Revision: 1.64 $ $Date: 2002/02/26 04:10:33 $ $Name: jdom_1_0_b8 $";
    private static final String DEFAULT_SAX_DRIVER = "org.apache.xerces.parsers.SAXParser";
    private boolean validate;
    private boolean expand;
    private String saxDriverClass;
    private ErrorHandler saxErrorHandler;
    private EntityResolver saxEntityResolver;
    private DTDHandler saxDTDHandler;
    private XMLFilter saxXMLFilter;
    protected JDOMFactory factory;
    private boolean ignoringWhite;
    private HashMap features;
    private HashMap properties;
    
    public SAXBuilder() {
        this(false);
    }
    
    public SAXBuilder(final boolean validate) {
        this.expand = true;
        this.saxErrorHandler = null;
        this.saxEntityResolver = null;
        this.saxDTDHandler = null;
        this.saxXMLFilter = null;
        this.factory = null;
        this.ignoringWhite = false;
        this.features = new HashMap(5);
        this.properties = new HashMap(5);
        this.validate = validate;
    }
    
    public SAXBuilder(final String saxDriverClass) {
        this(saxDriverClass, false);
    }
    
    public SAXBuilder(final String saxDriverClass, final boolean validate) {
        this.expand = true;
        this.saxErrorHandler = null;
        this.saxEntityResolver = null;
        this.saxDTDHandler = null;
        this.saxXMLFilter = null;
        this.factory = null;
        this.ignoringWhite = false;
        this.features = new HashMap(5);
        this.properties = new HashMap(5);
        this.saxDriverClass = saxDriverClass;
        this.validate = validate;
    }
    
    public void setFactory(final JDOMFactory factory) {
        this.factory = factory;
    }
    
    public void setValidation(final boolean validate) {
        this.validate = validate;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.saxErrorHandler = errorHandler;
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.saxEntityResolver = entityResolver;
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        this.saxDTDHandler = dtdHandler;
    }
    
    public void setXMLFilter(final XMLFilter xmlFilter) {
        this.saxXMLFilter = xmlFilter;
    }
    
    public void setIgnoringElementContentWhitespace(final boolean ignoringWhite) {
        this.ignoringWhite = ignoringWhite;
    }
    
    public void setFeature(final String name, final boolean value) {
        this.features.put(name, new Boolean(value));
    }
    
    public void setProperty(final String name, final Object value) {
        this.properties.put(name, value);
    }
    
    public Document build(final InputSource in) throws JDOMException {
        SAXHandler contentHandler = null;
        try {
            contentHandler = this.createContentHandler();
            this.configureContentHandler(contentHandler);
            XMLReader parser = this.createParser();
            if (this.saxXMLFilter != null) {
                XMLFilter root;
                for (root = this.saxXMLFilter; root.getParent() instanceof XMLFilter; root = (XMLFilter)root.getParent()) {}
                root.setParent(parser);
                parser = this.saxXMLFilter;
            }
            this.configureParser(parser, contentHandler);
            parser.parse(in);
            return contentHandler.getDocument();
        }
        catch (Exception e) {
            if (e instanceof SAXParseException) {
                final SAXParseException p = (SAXParseException)e;
                final String systemId = p.getSystemId();
                if (systemId != null) {
                    throw new JDOMException("Error on line " + p.getLineNumber() + " of document " + systemId, e);
                }
                throw new JDOMException("Error on line " + p.getLineNumber(), e);
            }
            else {
                if (e instanceof JDOMException) {
                    throw (JDOMException)e;
                }
                throw new JDOMException("Error in building", e);
            }
        }
        finally {
            contentHandler = null;
        }
    }
    
    protected SAXHandler createContentHandler() throws Exception {
        final SAXHandler contentHandler = new SAXHandler(this.factory);
        return contentHandler;
    }
    
    protected void configureContentHandler(final SAXHandler contentHandler) throws Exception {
        contentHandler.setExpandEntities(this.expand);
        contentHandler.setIgnoringElementContentWhitespace(this.ignoringWhite);
    }
    
    protected XMLReader createParser() throws Exception {
        XMLReader parser = null;
        if (this.saxDriverClass != null) {
            parser = XMLReaderFactory.createXMLReader(this.saxDriverClass);
        }
        else {
            try {
                final Class factoryClass = Class.forName("javax.xml.parsers.SAXParserFactory");
                final Method newParserInstance = factoryClass.getMethod("newInstance", (Class[])null);
                final Object factory = newParserInstance.invoke(null, (Object[])null);
                final Method setValidating = factoryClass.getMethod("setValidating", Boolean.TYPE);
                setValidating.invoke(factory, new Boolean(this.validate));
                final Method newSAXParser = factoryClass.getMethod("newSAXParser", (Class[])null);
                final Object jaxpParser = newSAXParser.invoke(factory, (Object[])null);
                final Class parserClass = jaxpParser.getClass();
                final Method getXMLReader = parserClass.getMethod("getXMLReader", (Class[])null);
                parser = (XMLReader)getXMLReader.invoke(jaxpParser, (Object[])null);
            }
            catch (ClassNotFoundException ex) {}
            catch (InvocationTargetException ex2) {}
            catch (NoSuchMethodException ex3) {}
            catch (IllegalAccessException ex4) {}
        }
        if (parser == null) {
            parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            this.saxDriverClass = parser.getClass().getName();
        }
        return parser;
    }
    
    protected void configureParser(final XMLReader parser, final SAXHandler contentHandler) throws Exception {
        parser.setContentHandler(contentHandler);
        if (this.saxEntityResolver != null) {
            parser.setEntityResolver(this.saxEntityResolver);
        }
        if (this.saxDTDHandler != null) {
            parser.setDTDHandler(this.saxDTDHandler);
        }
        else {
            parser.setDTDHandler(contentHandler);
        }
        if (this.saxErrorHandler != null) {
            parser.setErrorHandler(this.saxErrorHandler);
        }
        else {
            parser.setErrorHandler(new BuilderErrorHandler());
        }
        for (final String name : this.features.keySet()) {
            final Boolean value = (Boolean)this.features.get(name);
            this.internalSetFeature(parser, name, value, name);
        }
        for (final String name2 : this.properties.keySet()) {
            final Object value2 = this.properties.get(name2);
            this.internalSetProperty(parser, name2, value2, name2);
        }
        boolean lexicalReporting = false;
        try {
            parser.setProperty("http://xml.org/sax/handlers/LexicalHandler", contentHandler);
            lexicalReporting = true;
        }
        catch (SAXNotSupportedException ex) {}
        catch (SAXNotRecognizedException ex2) {}
        if (!lexicalReporting) {
            try {
                parser.setProperty("http://xml.org/sax/properties/lexical-handler", contentHandler);
                lexicalReporting = true;
            }
            catch (SAXNotSupportedException ex3) {}
            catch (SAXNotRecognizedException ex4) {}
        }
        if (!this.expand) {
            try {
                parser.setProperty("http://xml.org/sax/properties/declaration-handler", contentHandler);
            }
            catch (SAXNotSupportedException ex5) {}
            catch (SAXNotRecognizedException ex6) {}
        }
        try {
            this.internalSetFeature(parser, "http://xml.org/sax/features/validation", this.validate, "Validation");
        }
        catch (JDOMException e) {
            if (this.validate) {
                throw e;
            }
        }
        this.internalSetFeature(parser, "http://xml.org/sax/features/namespaces", true, "Namespaces");
        this.internalSetFeature(parser, "http://xml.org/sax/features/namespace-prefixes", false, "Namespace prefixes");
        try {
            if (parser.getFeature("http://xml.org/sax/features/external-general-entities") != this.expand) {
                parser.setFeature("http://xml.org/sax/features/external-general-entities", this.expand);
            }
        }
        catch (SAXNotRecognizedException ex7) {}
        catch (SAXNotSupportedException ex8) {}
    }
    
    private void internalSetFeature(final XMLReader parser, final String feature, final boolean value, final String displayName) throws JDOMException {
        try {
            parser.setFeature(feature, value);
        }
        catch (SAXNotSupportedException e) {
            throw new JDOMException(String.valueOf(displayName) + " feature not supported for SAX driver " + parser.getClass().getName());
        }
        catch (SAXNotRecognizedException e2) {
            throw new JDOMException(String.valueOf(displayName) + " feature not recognized for SAX driver " + parser.getClass().getName());
        }
    }
    
    private void internalSetProperty(final XMLReader parser, final String property, final Object value, final String displayName) throws JDOMException {
        try {
            parser.setProperty(property, value);
        }
        catch (SAXNotSupportedException e) {
            throw new JDOMException(String.valueOf(displayName) + " property not supported for SAX driver " + parser.getClass().getName());
        }
        catch (SAXNotRecognizedException e2) {
            throw new JDOMException(String.valueOf(displayName) + " property not recognized for SAX driver " + parser.getClass().getName());
        }
    }
    
    public Document build(final InputStream in) throws JDOMException {
        return this.build(new InputSource(in));
    }
    
    public Document build(final File file) throws JDOMException {
        try {
            final URL url = this.fileToURL(file);
            return this.build(url);
        }
        catch (MalformedURLException e) {
            throw new JDOMException("Error in building", e);
        }
    }
    
    public Document build(final URL url) throws JDOMException {
        final String systemID = url.toExternalForm();
        return this.build(new InputSource(systemID));
    }
    
    public Document build(final InputStream in, final String systemId) throws JDOMException {
        final InputSource src = new InputSource(in);
        src.setSystemId(systemId);
        return this.build(src);
    }
    
    public Document build(final Reader characterStream) throws JDOMException {
        return this.build(new InputSource(characterStream));
    }
    
    public Document build(final Reader characterStream, final String SystemId) throws JDOMException {
        final InputSource src = new InputSource(characterStream);
        src.setSystemId(SystemId);
        return this.build(src);
    }
    
    public Document build(final String systemId) throws JDOMException {
        return this.build(new InputSource(systemId));
    }
    
    protected URL fileToURL(final File f) throws MalformedURLException {
        String path = f.getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (!path.endsWith("/") && f.isDirectory()) {
            path = String.valueOf(path) + "/";
        }
        return new URL("file", "", path);
    }
    
    public void setExpandEntities(final boolean expand) {
        this.expand = expand;
    }
}
