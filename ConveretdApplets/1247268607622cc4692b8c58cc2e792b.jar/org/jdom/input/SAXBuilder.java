// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import java.io.Reader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.ContentHandler;
import java.lang.reflect.Method;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.jdom.Document;
import org.xml.sax.InputSource;
import org.jdom.DefaultJDOMFactory;
import org.xml.sax.XMLReader;
import java.util.HashMap;
import org.jdom.JDOMFactory;
import org.xml.sax.XMLFilter;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

public class SAXBuilder
{
    private static final String CVS_ID = "@(#) $RCSfile: SAXBuilder.java,v $ $Revision: 1.93 $ $Date: 2009/07/23 06:26:26 $ $Name: jdom_1_1_1 $";
    private static final String DEFAULT_SAX_DRIVER = "org.apache.xerces.parsers.SAXParser";
    private boolean validate;
    private boolean expand;
    private String saxDriverClass;
    private ErrorHandler saxErrorHandler;
    private EntityResolver saxEntityResolver;
    private DTDHandler saxDTDHandler;
    private XMLFilter saxXMLFilter;
    private JDOMFactory factory;
    private boolean ignoringWhite;
    private boolean ignoringBoundaryWhite;
    private HashMap features;
    private HashMap properties;
    private boolean fastReconfigure;
    private boolean skipNextLexicalReportingConfig;
    private boolean skipNextEntityExpandConfig;
    private boolean reuseParser;
    private XMLReader saxParser;
    static /* synthetic */ Class class$java$util$Map;
    
    public SAXBuilder() {
        this(false);
    }
    
    public SAXBuilder(final boolean validate) {
        this.expand = true;
        this.saxErrorHandler = null;
        this.saxEntityResolver = null;
        this.saxDTDHandler = null;
        this.saxXMLFilter = null;
        this.factory = new DefaultJDOMFactory();
        this.ignoringWhite = false;
        this.ignoringBoundaryWhite = false;
        this.features = new HashMap(5);
        this.properties = new HashMap(5);
        this.fastReconfigure = false;
        this.skipNextLexicalReportingConfig = false;
        this.skipNextEntityExpandConfig = false;
        this.reuseParser = true;
        this.saxParser = null;
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
        this.factory = new DefaultJDOMFactory();
        this.ignoringWhite = false;
        this.ignoringBoundaryWhite = false;
        this.features = new HashMap(5);
        this.properties = new HashMap(5);
        this.fastReconfigure = false;
        this.skipNextLexicalReportingConfig = false;
        this.skipNextEntityExpandConfig = false;
        this.reuseParser = true;
        this.saxParser = null;
        this.saxDriverClass = saxDriverClass;
        this.validate = validate;
    }
    
    public String getDriverClass() {
        return this.saxDriverClass;
    }
    
    public JDOMFactory getFactory() {
        return this.factory;
    }
    
    public void setFactory(final JDOMFactory factory) {
        this.factory = factory;
    }
    
    public boolean getValidation() {
        return this.validate;
    }
    
    public void setValidation(final boolean validate) {
        this.validate = validate;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.saxErrorHandler;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.saxErrorHandler = errorHandler;
    }
    
    public EntityResolver getEntityResolver() {
        return this.saxEntityResolver;
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.saxEntityResolver = entityResolver;
    }
    
    public DTDHandler getDTDHandler() {
        return this.saxDTDHandler;
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        this.saxDTDHandler = dtdHandler;
    }
    
    public XMLFilter getXMLFilter() {
        return this.saxXMLFilter;
    }
    
    public void setXMLFilter(final XMLFilter xmlFilter) {
        this.saxXMLFilter = xmlFilter;
    }
    
    public boolean getIgnoringElementContentWhitespace() {
        return this.ignoringWhite;
    }
    
    public void setIgnoringElementContentWhitespace(final boolean ignoringWhite) {
        this.ignoringWhite = ignoringWhite;
    }
    
    public boolean getIgnoringBoundaryWhitespace() {
        return this.ignoringBoundaryWhite;
    }
    
    public void setIgnoringBoundaryWhitespace(final boolean ignoringBoundaryWhite) {
        this.ignoringBoundaryWhite = ignoringBoundaryWhite;
    }
    
    public boolean getReuseParser() {
        return this.reuseParser;
    }
    
    public void setReuseParser(final boolean reuseParser) {
        this.reuseParser = reuseParser;
        this.saxParser = null;
    }
    
    public void setFastReconfigure(final boolean fastReconfigure) {
        if (this.reuseParser) {
            this.fastReconfigure = fastReconfigure;
        }
    }
    
    public void setFeature(final String name, final boolean value) {
        this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public void setProperty(final String name, final Object value) {
        this.properties.put(name, value);
    }
    
    public Document build(final InputSource in) throws JDOMException, IOException {
        SAXHandler contentHandler = null;
        try {
            contentHandler = this.createContentHandler();
            this.configureContentHandler(contentHandler);
            XMLReader parser = this.saxParser;
            if (parser == null) {
                parser = this.createParser();
                if (this.saxXMLFilter != null) {
                    XMLFilter root;
                    for (root = this.saxXMLFilter; root.getParent() instanceof XMLFilter; root = (XMLFilter)root.getParent()) {}
                    root.setParent(parser);
                    parser = this.saxXMLFilter;
                }
                this.configureParser(parser, contentHandler);
                if (this.reuseParser) {
                    this.saxParser = parser;
                }
            }
            else {
                this.configureParser(parser, contentHandler);
            }
            parser.parse(in);
            return contentHandler.getDocument();
        }
        catch (SAXParseException e) {
            Document doc = contentHandler.getDocument();
            if (!doc.hasRootElement()) {
                doc = null;
            }
            final String systemId = e.getSystemId();
            if (systemId != null) {
                throw new JDOMParseException("Error on line " + e.getLineNumber() + " of document " + systemId, e, doc);
            }
            throw new JDOMParseException("Error on line " + e.getLineNumber(), e, doc);
        }
        catch (SAXException e2) {
            throw new JDOMParseException("Error in building: " + e2.getMessage(), e2, contentHandler.getDocument());
        }
        finally {
            contentHandler = null;
        }
    }
    
    protected SAXHandler createContentHandler() {
        final SAXHandler contentHandler = new SAXHandler(this.factory);
        return contentHandler;
    }
    
    protected void configureContentHandler(final SAXHandler contentHandler) {
        contentHandler.setExpandEntities(this.expand);
        contentHandler.setIgnoringElementContentWhitespace(this.ignoringWhite);
        contentHandler.setIgnoringBoundaryWhitespace(this.ignoringBoundaryWhite);
    }
    
    protected XMLReader createParser() throws JDOMException {
        XMLReader parser = null;
        Label_0192: {
            if (this.saxDriverClass != null) {
                try {
                    parser = XMLReaderFactory.createXMLReader(this.saxDriverClass);
                    this.setFeaturesAndProperties(parser, true);
                    break Label_0192;
                }
                catch (SAXException e) {
                    throw new JDOMException("Could not load " + this.saxDriverClass, e);
                }
            }
            try {
                final Class factoryClass = Class.forName("org.jdom.input.JAXPParserFactory");
                final Method createParser = factoryClass.getMethod("createParser", Boolean.TYPE, (SAXBuilder.class$java$util$Map == null) ? (SAXBuilder.class$java$util$Map = class$("java.util.Map")) : SAXBuilder.class$java$util$Map, (SAXBuilder.class$java$util$Map == null) ? (SAXBuilder.class$java$util$Map = class$("java.util.Map")) : SAXBuilder.class$java$util$Map);
                parser = (XMLReader)createParser.invoke(null, this.validate ? Boolean.TRUE : Boolean.FALSE, this.features, this.properties);
                this.setFeaturesAndProperties(parser, false);
            }
            catch (JDOMException e2) {
                throw e2;
            }
            catch (NoClassDefFoundError e3) {}
            catch (Exception ex) {}
        }
        if (parser == null) {
            try {
                parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
                this.saxDriverClass = parser.getClass().getName();
                this.setFeaturesAndProperties(parser, true);
            }
            catch (SAXException e) {
                throw new JDOMException("Could not load default SAX parser: org.apache.xerces.parsers.SAXParser", e);
            }
        }
        return parser;
    }
    
    protected void configureParser(final XMLReader parser, final SAXHandler contentHandler) throws JDOMException {
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
        if (!this.skipNextLexicalReportingConfig) {
            boolean success = false;
            try {
                parser.setProperty("http://xml.org/sax/handlers/LexicalHandler", contentHandler);
                success = true;
            }
            catch (SAXNotSupportedException e) {}
            catch (SAXNotRecognizedException ex) {}
            if (!success) {
                try {
                    parser.setProperty("http://xml.org/sax/properties/lexical-handler", contentHandler);
                    success = true;
                }
                catch (SAXNotSupportedException e) {}
                catch (SAXNotRecognizedException ex2) {}
            }
            if (!success && this.fastReconfigure) {
                this.skipNextLexicalReportingConfig = true;
            }
        }
        if (!this.skipNextEntityExpandConfig) {
            boolean success = false;
            if (!this.expand) {
                try {
                    parser.setProperty("http://xml.org/sax/properties/declaration-handler", contentHandler);
                    success = true;
                }
                catch (SAXNotSupportedException e) {}
                catch (SAXNotRecognizedException ex3) {}
            }
            if (!success && this.fastReconfigure) {
                this.skipNextEntityExpandConfig = true;
            }
        }
    }
    
    private void setFeaturesAndProperties(final XMLReader parser, final boolean coreFeatures) throws JDOMException {
        for (final String name : this.features.keySet()) {
            final Boolean value = this.features.get(name);
            this.internalSetFeature(parser, name, value, name);
        }
        for (final String name : this.properties.keySet()) {
            this.internalSetProperty(parser, name, this.properties.get(name), name);
        }
        if (coreFeatures) {
            try {
                this.internalSetFeature(parser, "http://xml.org/sax/features/validation", this.validate, "Validation");
            }
            catch (JDOMException e) {
                if (this.validate) {
                    throw e;
                }
            }
            this.internalSetFeature(parser, "http://xml.org/sax/features/namespaces", true, "Namespaces");
            this.internalSetFeature(parser, "http://xml.org/sax/features/namespace-prefixes", true, "Namespace prefixes");
        }
        try {
            if (parser.getFeature("http://xml.org/sax/features/external-general-entities") != this.expand) {
                parser.setFeature("http://xml.org/sax/features/external-general-entities", this.expand);
            }
        }
        catch (SAXNotRecognizedException e2) {}
        catch (SAXNotSupportedException ex) {}
    }
    
    private void internalSetFeature(final XMLReader parser, final String feature, final boolean value, final String displayName) throws JDOMException {
        try {
            parser.setFeature(feature, value);
        }
        catch (SAXNotSupportedException e) {
            throw new JDOMException(displayName + " feature not supported for SAX driver " + parser.getClass().getName());
        }
        catch (SAXNotRecognizedException e2) {
            throw new JDOMException(displayName + " feature not recognized for SAX driver " + parser.getClass().getName());
        }
    }
    
    private void internalSetProperty(final XMLReader parser, final String property, final Object value, final String displayName) throws JDOMException {
        try {
            parser.setProperty(property, value);
        }
        catch (SAXNotSupportedException e) {
            throw new JDOMException(displayName + " property not supported for SAX driver " + parser.getClass().getName());
        }
        catch (SAXNotRecognizedException e2) {
            throw new JDOMException(displayName + " property not recognized for SAX driver " + parser.getClass().getName());
        }
    }
    
    public Document build(final InputStream in) throws JDOMException, IOException {
        return this.build(new InputSource(in));
    }
    
    public Document build(final File file) throws JDOMException, IOException {
        try {
            final URL url = fileToURL(file);
            return this.build(url);
        }
        catch (MalformedURLException e) {
            throw new JDOMException("Error in building", e);
        }
    }
    
    public Document build(final URL url) throws JDOMException, IOException {
        final String systemID = url.toExternalForm();
        return this.build(new InputSource(systemID));
    }
    
    public Document build(final InputStream in, final String systemId) throws JDOMException, IOException {
        final InputSource src = new InputSource(in);
        src.setSystemId(systemId);
        return this.build(src);
    }
    
    public Document build(final Reader characterStream) throws JDOMException, IOException {
        return this.build(new InputSource(characterStream));
    }
    
    public Document build(final Reader characterStream, final String systemId) throws JDOMException, IOException {
        final InputSource src = new InputSource(characterStream);
        src.setSystemId(systemId);
        return this.build(src);
    }
    
    public Document build(final String systemId) throws JDOMException, IOException {
        return this.build(new InputSource(systemId));
    }
    
    private static URL fileToURL(final File file) throws MalformedURLException {
        final StringBuffer buffer = new StringBuffer();
        String path = file.getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            buffer.append('/');
        }
        for (int len = path.length(), i = 0; i < len; ++i) {
            final char c = path.charAt(i);
            if (c == ' ') {
                buffer.append("%20");
            }
            else if (c == '#') {
                buffer.append("%23");
            }
            else if (c == '%') {
                buffer.append("%25");
            }
            else if (c == '&') {
                buffer.append("%26");
            }
            else if (c == ';') {
                buffer.append("%3B");
            }
            else if (c == '<') {
                buffer.append("%3C");
            }
            else if (c == '=') {
                buffer.append("%3D");
            }
            else if (c == '>') {
                buffer.append("%3E");
            }
            else if (c == '?') {
                buffer.append("%3F");
            }
            else if (c == '~') {
                buffer.append("%7E");
            }
            else {
                buffer.append(c);
            }
        }
        if (!path.endsWith("/") && file.isDirectory()) {
            buffer.append('/');
        }
        return new URL("file", "", buffer.toString());
    }
    
    public boolean getExpandEntities() {
        return this.expand;
    }
    
    public void setExpandEntities(final boolean expand) {
        this.expand = expand;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
