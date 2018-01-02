// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.output;

import org.xml.sax.helpers.DefaultHandler;
import java.lang.reflect.Method;
import org.xml.sax.helpers.XMLReaderFactory;
import java.lang.reflect.InvocationTargetException;
import org.xml.sax.XMLReader;
import org.jdom.EntityRef;
import org.jdom.Text;
import org.jdom.CDATA;
import org.jdom.Attribute;
import org.xml.sax.helpers.AttributesImpl;
import org.jdom.Namespace;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.jdom.DocType;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.Reader;
import org.xml.sax.InputSource;
import java.io.StringReader;
import org.jdom.Content;
import java.util.List;
import org.jdom.JDOMException;
import java.util.Iterator;
import org.jdom.Comment;
import org.jdom.ProcessingInstruction;
import org.jdom.Element;
import org.jdom.Document;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;

public class SAXOutputter
{
    private static final String CVS_ID = "@(#) $RCSfile: SAXOutputter.java,v $ $Revision: 1.40 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
    private static final String NAMESPACES_SAX_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String NS_PREFIXES_SAX_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    private static final String VALIDATION_SAX_FEATURE = "http://xml.org/sax/features/validation";
    private static final String LEXICAL_HANDLER_SAX_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    private static final String DECL_HANDLER_SAX_PROPERTY = "http://xml.org/sax/properties/declaration-handler";
    private static final String LEXICAL_HANDLER_ALT_PROPERTY = "http://xml.org/sax/handlers/LexicalHandler";
    private static final String DECL_HANDLER_ALT_PROPERTY = "http://xml.org/sax/handlers/DeclHandler";
    private static final String[] attrTypeToNameMap;
    private ContentHandler contentHandler;
    private ErrorHandler errorHandler;
    private DTDHandler dtdHandler;
    private EntityResolver entityResolver;
    private LexicalHandler lexicalHandler;
    private DeclHandler declHandler;
    private boolean declareNamespaces;
    private boolean reportDtdEvents;
    private JDOMLocator locator;
    
    public SAXOutputter() {
        this.declareNamespaces = false;
        this.reportDtdEvents = true;
        this.locator = null;
    }
    
    public SAXOutputter(final ContentHandler contentHandler) {
        this(contentHandler, null, null, null, null);
    }
    
    public SAXOutputter(final ContentHandler contentHandler, final ErrorHandler errorHandler, final DTDHandler dtdHandler, final EntityResolver entityResolver) {
        this(contentHandler, errorHandler, dtdHandler, entityResolver, null);
    }
    
    public SAXOutputter(final ContentHandler contentHandler, final ErrorHandler errorHandler, final DTDHandler dtdHandler, final EntityResolver entityResolver, final LexicalHandler lexicalHandler) {
        this.declareNamespaces = false;
        this.reportDtdEvents = true;
        this.locator = null;
        this.contentHandler = contentHandler;
        this.errorHandler = errorHandler;
        this.dtdHandler = dtdHandler;
        this.entityResolver = entityResolver;
        this.lexicalHandler = lexicalHandler;
    }
    
    public void setContentHandler(final ContentHandler contentHandler) {
        this.contentHandler = contentHandler;
    }
    
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public void setDTDHandler(final DTDHandler dtdHandler) {
        this.dtdHandler = dtdHandler;
    }
    
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setLexicalHandler(final LexicalHandler lexicalHandler) {
        this.lexicalHandler = lexicalHandler;
    }
    
    public LexicalHandler getLexicalHandler() {
        return this.lexicalHandler;
    }
    
    public void setDeclHandler(final DeclHandler declHandler) {
        this.declHandler = declHandler;
    }
    
    public DeclHandler getDeclHandler() {
        return this.declHandler;
    }
    
    public boolean getReportNamespaceDeclarations() {
        return this.declareNamespaces;
    }
    
    public void setReportNamespaceDeclarations(final boolean declareNamespaces) {
        this.declareNamespaces = declareNamespaces;
    }
    
    public boolean getReportDTDEvents() {
        return this.reportDtdEvents;
    }
    
    public void setReportDTDEvents(final boolean reportDtdEvents) {
        this.reportDtdEvents = reportDtdEvents;
    }
    
    public void setFeature(final String name, final boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://xml.org/sax/features/namespace-prefixes".equals(name)) {
            this.setReportNamespaceDeclarations(value);
        }
        else if ("http://xml.org/sax/features/namespaces".equals(name)) {
            if (!value) {
                throw new SAXNotSupportedException(name);
            }
        }
        else {
            if (!"http://xml.org/sax/features/validation".equals(name)) {
                throw new SAXNotRecognizedException(name);
            }
            this.setReportDTDEvents(value);
        }
    }
    
    public boolean getFeature(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://xml.org/sax/features/namespace-prefixes".equals(name)) {
            return this.declareNamespaces;
        }
        if ("http://xml.org/sax/features/namespaces".equals(name)) {
            return true;
        }
        if ("http://xml.org/sax/features/validation".equals(name)) {
            return this.reportDtdEvents;
        }
        throw new SAXNotRecognizedException(name);
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://xml.org/sax/properties/lexical-handler".equals(name) || "http://xml.org/sax/handlers/LexicalHandler".equals(name)) {
            this.setLexicalHandler((LexicalHandler)value);
        }
        else {
            if (!"http://xml.org/sax/properties/declaration-handler".equals(name) && !"http://xml.org/sax/handlers/DeclHandler".equals(name)) {
                throw new SAXNotRecognizedException(name);
            }
            this.setDeclHandler((DeclHandler)value);
        }
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://xml.org/sax/properties/lexical-handler".equals(name) || "http://xml.org/sax/handlers/LexicalHandler".equals(name)) {
            return this.getLexicalHandler();
        }
        if ("http://xml.org/sax/properties/declaration-handler".equals(name) || "http://xml.org/sax/handlers/DeclHandler".equals(name)) {
            return this.getDeclHandler();
        }
        throw new SAXNotRecognizedException(name);
    }
    
    public void output(final Document document) throws JDOMException {
        if (document == null) {
            return;
        }
        this.documentLocator(document);
        this.startDocument();
        if (this.reportDtdEvents) {
            this.dtdEvents(document);
        }
        for (final Object obj : document.getContent()) {
            this.locator.setNode(obj);
            if (obj instanceof Element) {
                this.element(document.getRootElement(), new NamespaceStack());
            }
            else if (obj instanceof ProcessingInstruction) {
                this.processingInstruction((ProcessingInstruction)obj);
            }
            else {
                if (!(obj instanceof Comment)) {
                    continue;
                }
                this.comment(((Comment)obj).getText());
            }
        }
        this.endDocument();
    }
    
    public void output(final List nodes) throws JDOMException {
        if (nodes == null || nodes.size() == 0) {
            return;
        }
        this.documentLocator(null);
        this.startDocument();
        this.elementContent(nodes, new NamespaceStack());
        this.endDocument();
    }
    
    public void output(final Element node) throws JDOMException {
        if (node == null) {
            return;
        }
        this.documentLocator(null);
        this.startDocument();
        this.elementContent(node, new NamespaceStack());
        this.endDocument();
    }
    
    public void outputFragment(final List nodes) throws JDOMException {
        if (nodes == null || nodes.size() == 0) {
            return;
        }
        this.elementContent(nodes, new NamespaceStack());
    }
    
    public void outputFragment(final Content node) throws JDOMException {
        if (node == null) {
            return;
        }
        this.elementContent(node, new NamespaceStack());
    }
    
    private void dtdEvents(final Document document) throws JDOMException {
        final DocType docType = document.getDocType();
        if (docType != null && (this.dtdHandler != null || this.declHandler != null)) {
            final String dtdDoc = new XMLOutputter().outputString(docType);
            try {
                this.createDTDParser().parse(new InputSource(new StringReader(dtdDoc)));
            }
            catch (SAXParseException e3) {}
            catch (SAXException e) {
                throw new JDOMException("DTD parsing error", e);
            }
            catch (IOException e2) {
                throw new JDOMException("DTD parsing error", e2);
            }
        }
    }
    
    private void documentLocator(final Document document) {
        this.locator = new JDOMLocator();
        String publicID = null;
        String systemID = null;
        if (document != null) {
            final DocType docType = document.getDocType();
            if (docType != null) {
                publicID = docType.getPublicID();
                systemID = docType.getSystemID();
            }
        }
        this.locator.setPublicId(publicID);
        this.locator.setSystemId(systemID);
        this.locator.setLineNumber(-1);
        this.locator.setColumnNumber(-1);
        this.contentHandler.setDocumentLocator(this.locator);
    }
    
    private void startDocument() throws JDOMException {
        try {
            this.contentHandler.startDocument();
        }
        catch (SAXException se) {
            throw new JDOMException("Exception in startDocument", se);
        }
    }
    
    private void endDocument() throws JDOMException {
        try {
            this.contentHandler.endDocument();
            this.locator = null;
        }
        catch (SAXException se) {
            throw new JDOMException("Exception in endDocument", se);
        }
    }
    
    private void processingInstruction(final ProcessingInstruction pi) throws JDOMException {
        if (pi != null) {
            final String target = pi.getTarget();
            final String data = pi.getData();
            try {
                this.contentHandler.processingInstruction(target, data);
            }
            catch (SAXException se) {
                throw new JDOMException("Exception in processingInstruction", se);
            }
        }
    }
    
    private void element(final Element element, final NamespaceStack namespaces) throws JDOMException {
        final int previouslyDeclaredNamespaces = namespaces.size();
        final Attributes nsAtts = this.startPrefixMapping(element, namespaces);
        this.startElement(element, nsAtts);
        this.elementContent(element.getContent(), namespaces);
        if (this.locator != null) {
            this.locator.setNode(element);
        }
        this.endElement(element);
        this.endPrefixMapping(namespaces, previouslyDeclaredNamespaces);
    }
    
    private Attributes startPrefixMapping(final Element element, final NamespaceStack namespaces) throws JDOMException {
        AttributesImpl nsAtts = null;
        Namespace ns = element.getNamespace();
        if (ns != Namespace.XML_NAMESPACE) {
            final String prefix = ns.getPrefix();
            final String uri = namespaces.getURI(prefix);
            if (!ns.getURI().equals(uri)) {
                namespaces.push(ns);
                nsAtts = this.addNsAttribute(nsAtts, ns);
                try {
                    this.contentHandler.startPrefixMapping(prefix, ns.getURI());
                }
                catch (SAXException se) {
                    throw new JDOMException("Exception in startPrefixMapping", se);
                }
            }
        }
        final List additionalNamespaces = element.getAdditionalNamespaces();
        if (additionalNamespaces != null) {
            final Iterator itr = additionalNamespaces.iterator();
            while (itr.hasNext()) {
                ns = itr.next();
                final String prefix2 = ns.getPrefix();
                final String uri2 = namespaces.getURI(prefix2);
                if (!ns.getURI().equals(uri2)) {
                    namespaces.push(ns);
                    nsAtts = this.addNsAttribute(nsAtts, ns);
                    try {
                        this.contentHandler.startPrefixMapping(prefix2, ns.getURI());
                    }
                    catch (SAXException se2) {
                        throw new JDOMException("Exception in startPrefixMapping", se2);
                    }
                }
            }
        }
        return nsAtts;
    }
    
    private void endPrefixMapping(final NamespaceStack namespaces, final int previouslyDeclaredNamespaces) throws JDOMException {
        while (namespaces.size() > previouslyDeclaredNamespaces) {
            final String prefix = namespaces.pop();
            try {
                this.contentHandler.endPrefixMapping(prefix);
            }
            catch (SAXException se) {
                throw new JDOMException("Exception in endPrefixMapping", se);
            }
        }
    }
    
    private void startElement(final Element element, final Attributes nsAtts) throws JDOMException {
        final String namespaceURI = element.getNamespaceURI();
        final String localName = element.getName();
        final String rawName = element.getQualifiedName();
        final AttributesImpl atts = (nsAtts != null) ? new AttributesImpl(nsAtts) : new AttributesImpl();
        final List attributes = element.getAttributes();
        for (final Attribute a : attributes) {
            atts.addAttribute(a.getNamespaceURI(), a.getName(), a.getQualifiedName(), getAttributeTypeName(a.getAttributeType()), a.getValue());
        }
        try {
            this.contentHandler.startElement(namespaceURI, localName, rawName, atts);
        }
        catch (SAXException se) {
            throw new JDOMException("Exception in startElement", se);
        }
    }
    
    private void endElement(final Element element) throws JDOMException {
        final String namespaceURI = element.getNamespaceURI();
        final String localName = element.getName();
        final String rawName = element.getQualifiedName();
        try {
            this.contentHandler.endElement(namespaceURI, localName, rawName);
        }
        catch (SAXException se) {
            throw new JDOMException("Exception in endElement", se);
        }
    }
    
    private void elementContent(final List content, final NamespaceStack namespaces) throws JDOMException {
        for (final Object obj : content) {
            if (obj instanceof Content) {
                this.elementContent((Content)obj, namespaces);
            }
            else {
                this.handleError(new JDOMException("Invalid element content: " + obj));
            }
        }
    }
    
    private void elementContent(final Content node, final NamespaceStack namespaces) throws JDOMException {
        if (this.locator != null) {
            this.locator.setNode(node);
        }
        if (node instanceof Element) {
            this.element((Element)node, namespaces);
        }
        else if (node instanceof CDATA) {
            this.cdata(((CDATA)node).getText());
        }
        else if (node instanceof Text) {
            this.characters(((Text)node).getText());
        }
        else if (node instanceof ProcessingInstruction) {
            this.processingInstruction((ProcessingInstruction)node);
        }
        else if (node instanceof Comment) {
            this.comment(((Comment)node).getText());
        }
        else if (node instanceof EntityRef) {
            this.entityRef((EntityRef)node);
        }
        else {
            this.handleError(new JDOMException("Invalid element content: " + node));
        }
    }
    
    private void cdata(final String cdataText) throws JDOMException {
        try {
            if (this.lexicalHandler != null) {
                this.lexicalHandler.startCDATA();
                this.characters(cdataText);
                this.lexicalHandler.endCDATA();
            }
            else {
                this.characters(cdataText);
            }
        }
        catch (SAXException se) {
            throw new JDOMException("Exception in CDATA", se);
        }
    }
    
    private void characters(final String elementText) throws JDOMException {
        final char[] c = elementText.toCharArray();
        try {
            this.contentHandler.characters(c, 0, c.length);
        }
        catch (SAXException se) {
            throw new JDOMException("Exception in characters", se);
        }
    }
    
    private void comment(final String commentText) throws JDOMException {
        if (this.lexicalHandler != null) {
            final char[] c = commentText.toCharArray();
            try {
                this.lexicalHandler.comment(c, 0, c.length);
            }
            catch (SAXException se) {
                throw new JDOMException("Exception in comment", se);
            }
        }
    }
    
    private void entityRef(final EntityRef entity) throws JDOMException {
        if (entity != null) {
            try {
                this.contentHandler.skippedEntity(entity.getName());
            }
            catch (SAXException se) {
                throw new JDOMException("Exception in entityRef", se);
            }
        }
    }
    
    private AttributesImpl addNsAttribute(AttributesImpl atts, final Namespace ns) {
        if (this.declareNamespaces) {
            if (atts == null) {
                atts = new AttributesImpl();
            }
            final String prefix = ns.getPrefix();
            if (prefix.equals("")) {
                atts.addAttribute("", "", "xmlns", "CDATA", ns.getURI());
            }
            else {
                atts.addAttribute("", "", "xmlns:" + ns.getPrefix(), "CDATA", ns.getURI());
            }
        }
        return atts;
    }
    
    private static String getAttributeTypeName(int type) {
        if (type < 0 || type >= SAXOutputter.attrTypeToNameMap.length) {
            type = 0;
        }
        return SAXOutputter.attrTypeToNameMap[type];
    }
    
    private void handleError(final JDOMException exception) throws JDOMException {
        if (this.errorHandler != null) {
            try {
                this.errorHandler.error(new SAXParseException(exception.getMessage(), null, exception));
                return;
            }
            catch (SAXException se) {
                if (se.getException() instanceof JDOMException) {
                    throw (JDOMException)se.getException();
                }
                throw new JDOMException(se.getMessage(), se);
            }
            throw exception;
        }
        throw exception;
    }
    
    protected XMLReader createParser() throws Exception {
        XMLReader parser = null;
        try {
            final Class factoryClass = Class.forName("javax.xml.parsers.SAXParserFactory");
            final Method newParserInstance = factoryClass.getMethod("newInstance", (Class[])null);
            final Object factory = newParserInstance.invoke(null, (Object[])null);
            final Method newSAXParser = factoryClass.getMethod("newSAXParser", (Class[])null);
            final Object jaxpParser = newSAXParser.invoke(factory, (Object[])null);
            final Class parserClass = jaxpParser.getClass();
            final Method getXMLReader = parserClass.getMethod("getXMLReader", (Class[])null);
            parser = (XMLReader)getXMLReader.invoke(jaxpParser, (Object[])null);
        }
        catch (ClassNotFoundException e) {}
        catch (InvocationTargetException e2) {}
        catch (NoSuchMethodException e3) {}
        catch (IllegalAccessException ex) {}
        if (parser == null) {
            parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        }
        return parser;
    }
    
    private XMLReader createDTDParser() throws JDOMException {
        XMLReader parser = null;
        try {
            parser = this.createParser();
        }
        catch (Exception ex1) {
            throw new JDOMException("Error in SAX parser allocation", ex1);
        }
        if (this.getDTDHandler() != null) {
            parser.setDTDHandler(this.getDTDHandler());
        }
        if (this.getEntityResolver() != null) {
            parser.setEntityResolver(this.getEntityResolver());
        }
        if (this.getLexicalHandler() != null) {
            try {
                parser.setProperty("http://xml.org/sax/properties/lexical-handler", this.getLexicalHandler());
            }
            catch (SAXException ex2) {
                try {
                    parser.setProperty("http://xml.org/sax/handlers/LexicalHandler", this.getLexicalHandler());
                }
                catch (SAXException ex3) {}
            }
        }
        if (this.getDeclHandler() != null) {
            try {
                parser.setProperty("http://xml.org/sax/properties/declaration-handler", this.getDeclHandler());
            }
            catch (SAXException ex2) {
                try {
                    parser.setProperty("http://xml.org/sax/handlers/DeclHandler", this.getDeclHandler());
                }
                catch (SAXException ex4) {}
            }
        }
        parser.setErrorHandler(new DefaultHandler());
        return parser;
    }
    
    public JDOMLocator getLocator() {
        return (this.locator != null) ? new JDOMLocator(this.locator) : null;
    }
    
    static {
        attrTypeToNameMap = new String[] { "CDATA", "CDATA", "ID", "IDREF", "IDREFS", "ENTITY", "ENTITIES", "NMTOKEN", "NMTOKENS", "NOTATION", "NMTOKEN" };
    }
}
