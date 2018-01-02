// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xpath.functions.Function;
import org.apache.xalan.templates.FuncFormatNumb;
import org.apache.xpath.Expression;
import org.apache.xpath.compiler.FunctionTable;
import org.apache.xalan.templates.FuncDocument;
import org.apache.xml.utils.NamespaceSupport2;
import java.util.EmptyStackException;
import org.xml.sax.SAXParseException;
import org.apache.xalan.templates.ElemForEach;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.Attributes;
import org.apache.xml.utils.SAXSourceLocator;
import org.xml.sax.Locator;
import org.apache.xalan.res.XSLMessages;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerException;
import javax.xml.transform.ErrorListener;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xalan.extensions.ExpressionVisitor;
import javax.xml.transform.SourceLocator;
import org.apache.xpath.XPath;
import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.xml.utils.BoolStack;
import org.w3c.dom.Node;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.StylesheetRoot;
import java.util.Stack;
import java.util.Vector;
import org.apache.xml.utils.NodeConsumer;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.sax.TemplatesHandler;
import org.xml.sax.helpers.DefaultHandler;

public class StylesheetHandler extends DefaultHandler implements TemplatesHandler, PrefixResolver, NodeConsumer
{
    private int m_stylesheetLevel;
    private boolean m_parsingComplete;
    private Vector m_prefixMappings;
    private boolean m_shouldProcess;
    private String m_fragmentIDString;
    private int m_elementID;
    private int m_fragmentID;
    private TransformerFactoryImpl m_stylesheetProcessor;
    static final int STYPE_ROOT = 1;
    static final int STYPE_INCLUDE = 2;
    static final int STYPE_IMPORT = 3;
    private int m_stylesheetType;
    private Stack m_stylesheets;
    StylesheetRoot m_stylesheetRoot;
    Stylesheet m_lastPoppedStylesheet;
    private Stack m_processors;
    private XSLTSchema m_schema;
    private Stack m_elems;
    private int m_docOrderCount;
    Stack m_baseIdentifiers;
    private Stack m_stylesheetLocatorStack;
    private Stack m_importStack;
    private boolean warnedAboutOldXSLTNamespace;
    Stack m_nsSupportStack;
    private Node m_originatingNode;
    private BoolStack m_spacePreserveStack;
    
    public StylesheetHandler(final TransformerFactoryImpl processor) throws TransformerConfigurationException {
        this.m_stylesheetLevel = -1;
        this.m_parsingComplete = false;
        this.m_prefixMappings = new Vector();
        this.m_shouldProcess = true;
        this.m_elementID = 0;
        this.m_fragmentID = 0;
        this.m_stylesheetType = 1;
        this.m_stylesheets = new Stack();
        this.m_processors = new Stack();
        this.m_schema = new XSLTSchema();
        this.m_elems = new Stack();
        this.m_docOrderCount = 0;
        this.m_baseIdentifiers = new Stack();
        this.m_stylesheetLocatorStack = new Stack();
        this.m_importStack = new Stack();
        this.warnedAboutOldXSLTNamespace = false;
        this.m_nsSupportStack = new Stack();
        this.m_spacePreserveStack = new BoolStack();
        this.init(processor);
    }
    
    void init(final TransformerFactoryImpl processor) {
        this.m_stylesheetProcessor = processor;
        this.m_processors.push(this.m_schema.getElementProcessor());
        this.pushNewNamespaceSupport();
    }
    
    public XPath createXPath(final String str, final ElemTemplateElement owningTemplate) throws TransformerException {
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        final XPath xpath = new XPath(str, owningTemplate, this, 0, handler);
        xpath.callVisitors(xpath, new ExpressionVisitor(this.getStylesheetRoot()));
        return xpath;
    }
    
    XPath createMatchPatternXPath(final String str, final ElemTemplateElement owningTemplate) throws TransformerException {
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        final XPath xpath = new XPath(str, owningTemplate, this, 1, handler);
        xpath.callVisitors(xpath, new ExpressionVisitor(this.getStylesheetRoot()));
        return xpath;
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        return this.getNamespaceSupport().getURI(prefix);
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node context) {
        this.assertion(true, "can't process a context node in StylesheetHandler!");
        return null;
    }
    
    private boolean stackContains(final Stack stack, final String url) {
        final int n = stack.size();
        boolean contains = false;
        for (int i = 0; i < n; ++i) {
            final String url2 = stack.elementAt(i);
            if (url2.equals(url)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
    
    public Templates getTemplates() {
        return this.getStylesheetRoot();
    }
    
    public void setSystemId(final String baseID) {
        this.pushBaseIndentifier(baseID);
    }
    
    public String getSystemId() {
        return this.getBaseIdentifier();
    }
    
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException {
        return this.getCurrentProcessor().resolveEntity(this, publicId, systemId);
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) {
        this.getCurrentProcessor().notationDecl(this, name, publicId, systemId);
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) {
        this.getCurrentProcessor().unparsedEntityDecl(this, name, publicId, systemId, notationName);
    }
    
    XSLTElementProcessor getProcessorFor(final String uri, final String localName, final String rawName) throws SAXException {
        final XSLTElementProcessor currentProcessor = this.getCurrentProcessor();
        final XSLTElementDef def = currentProcessor.getElemDef();
        XSLTElementProcessor elemProcessor = def.getProcessorFor(uri, localName);
        if (null == elemProcessor && !(currentProcessor instanceof ProcessorStylesheetDoc) && (null == this.getStylesheet() || Double.valueOf(this.getStylesheet().getVersion()) > 1.0 || (!uri.equals("http://www.w3.org/1999/XSL/Transform") && currentProcessor instanceof ProcessorStylesheetElement) || this.getElemVersion() > 1.0)) {
            elemProcessor = def.getProcessorForUnknown(uri, localName);
        }
        if (null == elemProcessor) {
            this.error(XSLMessages.createMessage("ER_NOT_ALLOWED_IN_POSITION", new Object[] { rawName }), null);
        }
        return elemProcessor;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.m_stylesheetLocatorStack.push(new SAXSourceLocator(locator));
    }
    
    public void startDocument() throws SAXException {
        ++this.m_stylesheetLevel;
        this.pushSpaceHandling(false);
    }
    
    public boolean isStylesheetParsingComplete() {
        return this.m_parsingComplete;
    }
    
    public void endDocument() throws SAXException {
        try {
            if (null == this.getStylesheetRoot()) {
                throw new TransformerException(XSLMessages.createMessage("ER_NO_STYLESHEETROOT", null));
            }
            if (0 == this.m_stylesheetLevel) {
                this.getStylesheetRoot().recompose();
            }
            final XSLTElementProcessor elemProcessor = this.getCurrentProcessor();
            if (null != elemProcessor) {
                elemProcessor.startNonText(this);
            }
            --this.m_stylesheetLevel;
            this.popSpaceHandling();
            this.m_parsingComplete = (this.m_stylesheetLevel < 0);
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.m_prefixMappings.addElement(prefix);
        this.m_prefixMappings.addElement(uri);
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    private void flushCharacters() throws SAXException {
        final XSLTElementProcessor elemProcessor = this.getCurrentProcessor();
        if (null != elemProcessor) {
            elemProcessor.startNonText(this);
        }
    }
    
    public void startElement(final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final NamespaceSupport nssupport = this.getNamespaceSupport();
        nssupport.pushContext();
        for (int n = this.m_prefixMappings.size(), i = 0; i < n; ++i) {
            final String prefix = this.m_prefixMappings.elementAt(i++);
            final String nsURI = this.m_prefixMappings.elementAt(i);
            nssupport.declarePrefix(prefix, nsURI);
        }
        this.m_prefixMappings.removeAllElements();
        ++this.m_elementID;
        this.checkForFragmentID(attributes);
        if (!this.m_shouldProcess) {
            return;
        }
        this.flushCharacters();
        this.pushSpaceHandling(attributes);
        final XSLTElementProcessor elemProcessor = this.getProcessorFor(uri, localName, rawName);
        if (null != elemProcessor) {
            this.pushProcessor(elemProcessor);
            elemProcessor.startElement(this, uri, localName, rawName, attributes);
        }
        else {
            this.m_shouldProcess = false;
            this.popSpaceHandling();
        }
    }
    
    public void endElement(final String uri, final String localName, final String rawName) throws SAXException {
        --this.m_elementID;
        if (!this.m_shouldProcess) {
            return;
        }
        if (this.m_elementID + 1 == this.m_fragmentID) {
            this.m_shouldProcess = false;
        }
        this.flushCharacters();
        this.popSpaceHandling();
        final XSLTElementProcessor p = this.getCurrentProcessor();
        p.endElement(this, uri, localName, rawName);
        this.popProcessor();
        this.getNamespaceSupport().popContext();
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        XSLTElementProcessor elemProcessor = this.getCurrentProcessor();
        final XSLTElementDef def = elemProcessor.getElemDef();
        if (def.getType() != 2) {
            elemProcessor = def.getProcessorFor(null, "text()");
        }
        if (null == elemProcessor) {
            if (!XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
                this.error(XSLMessages.createMessage("ER_NONWHITESPACE_NOT_ALLOWED_IN_POSITION", null), null);
            }
        }
        else {
            elemProcessor.characters(this, ch, start, length);
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        this.getCurrentProcessor().ignorableWhitespace(this, ch, start, length);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        String prefix = "";
        String ns = "";
        String localName = target;
        final int colon = target.indexOf(58);
        if (colon >= 0) {
            ns = this.getNamespaceForPrefix(prefix = target.substring(0, colon));
            localName = target.substring(colon + 1);
        }
        try {
            if ("xalan-doc-cache-off".equals(target) || "xalan:doc-cache-off".equals(target) || ("doc-cache-off".equals(localName) && ns.equals("org.apache.xalan.xslt.extensions.Redirect"))) {
                if (!(this.m_elems.peek() instanceof ElemForEach)) {
                    throw new TransformerException("xalan:doc-cache-off not allowed here!", this.getLocator());
                }
                final ElemForEach elem = this.m_elems.peek();
                elem.m_doc_cache_off = true;
            }
        }
        catch (Exception ex) {}
        this.flushCharacters();
        this.getCurrentProcessor().processingInstruction(this, target, data);
    }
    
    public void skippedEntity(final String name) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        this.getCurrentProcessor().skippedEntity(this, name);
    }
    
    public void warn(final String msg, final Object[] args) throws SAXException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        try {
            if (null != handler) {
                handler.warning(new TransformerException(formattedMsg, locator));
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    private void assertion(final boolean condition, final String msg) throws RuntimeException {
        if (!condition) {
            throw new RuntimeException(msg);
        }
    }
    
    protected void error(final String msg, final Exception e) throws SAXException {
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        TransformerException pe;
        if (!(e instanceof TransformerException)) {
            pe = ((null == e) ? new TransformerException(msg, locator) : new TransformerException(msg, locator, e));
        }
        else {
            pe = (TransformerException)e;
        }
        if (null != handler) {
            try {
                handler.error(pe);
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
            return;
        }
        throw new SAXException(pe);
    }
    
    protected void error(final String msg, final Object[] args, final Exception e) throws SAXException {
        final String formattedMsg = XSLMessages.createMessage(msg, args);
        this.error(formattedMsg, e);
    }
    
    public void warning(final SAXParseException e) throws SAXException {
        final String formattedMsg = e.getMessage();
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        try {
            handler.warning(new TransformerException(formattedMsg, locator));
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    public void error(final SAXParseException e) throws SAXException {
        final String formattedMsg = e.getMessage();
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        try {
            handler.error(new TransformerException(formattedMsg, locator));
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    public void fatalError(final SAXParseException e) throws SAXException {
        final String formattedMsg = e.getMessage();
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        try {
            handler.fatalError(new TransformerException(formattedMsg, locator));
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    private void checkForFragmentID(final Attributes attributes) {
        if (!this.m_shouldProcess && null != attributes && null != this.m_fragmentIDString) {
            for (int n = attributes.getLength(), i = 0; i < n; ++i) {
                final String name = attributes.getQName(i);
                if (name.equals("id")) {
                    final String val = attributes.getValue(i);
                    if (val.equalsIgnoreCase(this.m_fragmentIDString)) {
                        this.m_shouldProcess = true;
                        this.m_fragmentID = this.m_elementID;
                    }
                }
            }
        }
    }
    
    TransformerFactoryImpl getStylesheetProcessor() {
        return this.m_stylesheetProcessor;
    }
    
    int getStylesheetType() {
        return this.m_stylesheetType;
    }
    
    void setStylesheetType(final int type) {
        this.m_stylesheetType = type;
    }
    
    Stylesheet getStylesheet() {
        return (this.m_stylesheets.size() == 0) ? null : this.m_stylesheets.peek();
    }
    
    Stylesheet getLastPoppedStylesheet() {
        return this.m_lastPoppedStylesheet;
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_stylesheetRoot;
    }
    
    public void pushStylesheet(final Stylesheet s) {
        if (this.m_stylesheets.size() == 0) {
            this.m_stylesheetRoot = (StylesheetRoot)s;
        }
        this.m_stylesheets.push(s);
    }
    
    Stylesheet popStylesheet() {
        if (!this.m_stylesheetLocatorStack.isEmpty()) {
            this.m_stylesheetLocatorStack.pop();
        }
        if (!this.m_stylesheets.isEmpty()) {
            this.m_lastPoppedStylesheet = this.m_stylesheets.pop();
        }
        return this.m_lastPoppedStylesheet;
    }
    
    XSLTElementProcessor getCurrentProcessor() {
        return this.m_processors.peek();
    }
    
    void pushProcessor(final XSLTElementProcessor processor) {
        this.m_processors.push(processor);
    }
    
    XSLTElementProcessor popProcessor() {
        return this.m_processors.pop();
    }
    
    XSLTSchema getSchema() {
        return this.m_schema;
    }
    
    ElemTemplateElement getElemTemplateElement() {
        try {
            return this.m_elems.peek();
        }
        catch (EmptyStackException ese) {
            return null;
        }
    }
    
    int nextUid() {
        return this.m_docOrderCount++;
    }
    
    void pushElemTemplateElement(final ElemTemplateElement elem) {
        if (elem.getUid() == -1) {
            elem.setUid(this.nextUid());
        }
        this.m_elems.push(elem);
    }
    
    ElemTemplateElement popElemTemplateElement() {
        return this.m_elems.pop();
    }
    
    void pushBaseIndentifier(final String baseID) {
        if (null != baseID) {
            final int posOfHash = baseID.indexOf(35);
            if (posOfHash > -1) {
                this.m_fragmentIDString = baseID.substring(posOfHash + 1);
                this.m_shouldProcess = false;
            }
            else {
                this.m_shouldProcess = true;
            }
        }
        else {
            this.m_shouldProcess = true;
        }
        this.m_baseIdentifiers.push(baseID);
    }
    
    String popBaseIndentifier() {
        return this.m_baseIdentifiers.pop();
    }
    
    public String getBaseIdentifier() {
        String base = this.m_baseIdentifiers.isEmpty() ? null : this.m_baseIdentifiers.peek();
        if (null == base) {
            final SourceLocator locator = this.getLocator();
            base = ((null == locator) ? "" : locator.getSystemId());
        }
        return base;
    }
    
    public SAXSourceLocator getLocator() {
        if (this.m_stylesheetLocatorStack.isEmpty()) {
            final SAXSourceLocator locator = new SAXSourceLocator();
            locator.setSystemId(this.getStylesheetProcessor().getDOMsystemID());
            return locator;
        }
        return this.m_stylesheetLocatorStack.peek();
    }
    
    void pushImportURL(final String hrefUrl) {
        this.m_importStack.push(hrefUrl);
    }
    
    boolean importStackContains(final String hrefUrl) {
        return this.stackContains(this.m_importStack, hrefUrl);
    }
    
    String popImportURL() {
        return this.m_importStack.pop();
    }
    
    void pushNewNamespaceSupport() {
        this.m_nsSupportStack.push(new NamespaceSupport2());
    }
    
    void popNamespaceSupport() {
        this.m_nsSupportStack.pop();
    }
    
    NamespaceSupport getNamespaceSupport() {
        return this.m_nsSupportStack.peek();
    }
    
    public void setOriginatingNode(final Node n) {
        this.m_originatingNode = n;
    }
    
    public Node getOriginatingNode() {
        return this.m_originatingNode;
    }
    
    boolean isSpacePreserve() {
        return this.m_spacePreserveStack.peek();
    }
    
    void popSpaceHandling() {
        this.m_spacePreserveStack.pop();
    }
    
    void pushSpaceHandling(final boolean b) throws SAXParseException {
        this.m_spacePreserveStack.push(b);
    }
    
    void pushSpaceHandling(final Attributes attrs) throws SAXParseException {
        final String value = attrs.getValue("xml:space");
        if (null == value) {
            this.m_spacePreserveStack.push(this.m_spacePreserveStack.peekOrFalse());
        }
        else if (value.equals("preserve")) {
            this.m_spacePreserveStack.push(true);
        }
        else if (value.equals("default")) {
            this.m_spacePreserveStack.push(false);
        }
        else {
            final SAXSourceLocator locator = this.getLocator();
            final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
            try {
                handler.error(new TransformerException(XSLMessages.createMessage("ER_ILLEGAL_XMLSPACE_VALUE", null), locator));
            }
            catch (TransformerException te) {
                throw new SAXParseException(te.getMessage(), locator, te);
            }
            this.m_spacePreserveStack.push(this.m_spacePreserveStack.peek());
        }
    }
    
    private double getElemVersion() {
        ElemTemplateElement elem;
        double version;
        for (elem = this.getElemTemplateElement(), version = -1.0; (version == -1.0 || version == 1.0) && elem != null; elem = elem.getParentElem()) {
            try {
                version = Double.valueOf(elem.getVersion());
            }
            catch (Exception ex) {
                version = -1.0;
            }
        }
        return (version == -1.0) ? 1.0 : version;
    }
    
    public boolean handlesNullPrefixes() {
        return false;
    }
    
    static {
        Function func = new FuncDocument();
        FunctionTable.installFunction("document", func);
        func = new FuncFormatNumb();
        FunctionTable.installFunction("format-number", func);
    }
}
