// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.apache.xpath.functions.Function;
import org.apache.xalan.templates.FuncFormatNumb;
import org.apache.xpath.Expression;
import org.apache.xpath.compiler.FunctionTable;
import org.apache.xalan.templates.FuncDocument;
import javax.xml.transform.Templates;
import org.xml.sax.helpers.NamespaceSupport;
import java.util.EmptyStackException;
import org.apache.xalan.templates.ElemTemplateElement;
import org.xml.sax.SAXParseException;
import org.apache.xml.utils.SAXSourceLocator;
import javax.xml.transform.TransformerException;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import org.apache.xpath.XPath;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.apache.xml.utils.XMLCharacterRecognizer;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.xml.utils.BoolStack;
import org.w3c.dom.Node;
import org.apache.xalan.res.XSLMessages;
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
    private static boolean m_xpathFunctionsInited;
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
    private boolean m_foundNotImport;
    private static XSLMessages m_XSLMessages;
    Stack m_baseIdentifiers;
    private Stack m_stylesheetLocatorStack;
    private Stack m_importStack;
    private boolean warnedAboutOldXSLTNamespace;
    Stack m_nsSupportStack;
    private Node m_originatingNode;
    private BoolStack m_spacePreserveStack;
    
    static {
        StylesheetHandler.m_xpathFunctionsInited = false;
        StylesheetHandler.m_XSLMessages = new XSLMessages();
    }
    
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
        this.m_foundNotImport = false;
        this.m_baseIdentifiers = new Stack();
        this.m_stylesheetLocatorStack = new Stack();
        this.m_importStack = new Stack();
        this.warnedAboutOldXSLTNamespace = false;
        this.m_nsSupportStack = new Stack();
        this.m_spacePreserveStack = new BoolStack();
        this.init(processor);
    }
    
    private void assert(final boolean condition, final String msg) throws RuntimeException {
        if (!condition) {
            throw new RuntimeException(msg);
        }
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
        if (elemProcessor == null) {
            if (!XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
                this.error("Non-whitespace text is not allowed in this position in the stylesheet!", null);
            }
        }
        else {
            elemProcessor.characters(this, ch, start, length);
        }
    }
    
    private void checkForFragmentID(final Attributes attributes) {
        if (!this.m_shouldProcess && attributes != null && this.m_fragmentIDString != null) {
            for (int n = attributes.getLength(), i = 0; i < n; ++i) {
                final String type = attributes.getType(i);
                if (type.equalsIgnoreCase("ID")) {
                    final String val = attributes.getValue(i);
                    if (val.equalsIgnoreCase(this.m_fragmentIDString)) {
                        this.m_shouldProcess = true;
                        this.m_fragmentID = this.m_elementID;
                    }
                }
            }
        }
    }
    
    XPath createMatchPatternXPath(final String str) throws TransformerException {
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        return new XPath(str, this.getLocator(), this, 1, handler);
    }
    
    public XPath createXPath(final String str) throws TransformerException {
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        return new XPath(str, this.getLocator(), this, 0, handler);
    }
    
    public void endDocument() throws SAXException {
        try {
            if (this.getStylesheetRoot() == null) {
                throw new TransformerException("Did not find the stylesheet root!");
            }
            if (this.m_stylesheetLevel == 0) {
                this.getStylesheetRoot().recompose();
            }
            if (this.getLastPoppedStylesheet() != null) {
                this.getLastPoppedStylesheet().resolvePrefixTables();
            }
            final XSLTElementProcessor elemProcessor = this.getCurrentProcessor();
            if (elemProcessor != null) {
                elemProcessor.startNonText(this);
            }
            --this.m_stylesheetLevel;
            this.popSpaceHandling();
            this.m_parsingComplete = (this.m_stylesheetLevel < 0);
        }
        catch (TransformerException e) {
            throw new SAXException(e);
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
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    protected void error(final int msg, final Object[] args, final Exception e) throws SAXException {
        final String formattedMsg = XSLMessages.createMessage(msg, args);
        this.error(formattedMsg, e);
    }
    
    protected void error(final String msg, final Exception e) throws SAXException {
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        TransformerException pe;
        if (!(e instanceof TransformerException)) {
            pe = ((e == null) ? new TransformerException(msg, locator) : new TransformerException(msg, locator, e));
        }
        else {
            pe = (TransformerException)e;
        }
        if (handler != null) {
            try {
                handler.error(pe);
                return;
            }
            catch (TransformerException te) {
                throw new SAXException(te);
            }
            throw new SAXException(pe);
        }
        throw new SAXException(pe);
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
    
    private void flushCharacters() throws SAXException {
        final XSLTElementProcessor elemProcessor = this.getCurrentProcessor();
        if (elemProcessor != null) {
            elemProcessor.startNonText(this);
        }
    }
    
    public String getBaseIdentifier() {
        String base = this.m_baseIdentifiers.isEmpty() ? null : this.m_baseIdentifiers.peek();
        if (base == null) {
            final SourceLocator locator = this.getLocator();
            base = ((locator == null) ? "" : locator.getSystemId());
        }
        return base;
    }
    
    XSLTElementProcessor getCurrentProcessor() {
        return this.m_processors.peek();
    }
    
    ElemTemplateElement getElemTemplateElement() {
        try {
            return this.m_elems.peek();
        }
        catch (EmptyStackException ex) {
            return null;
        }
    }
    
    Stylesheet getLastPoppedStylesheet() {
        return this.m_lastPoppedStylesheet;
    }
    
    public SAXSourceLocator getLocator() {
        if (this.m_stylesheetLocatorStack.isEmpty()) {
            final SAXSourceLocator locator = new SAXSourceLocator();
            locator.setSystemId(this.getStylesheetProcessor().getDOMsystemID());
            return locator;
        }
        return this.m_stylesheetLocatorStack.peek();
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        return this.getNamespaceSupport().getURI(prefix);
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node context) {
        this.assert(true, "can't process a context node in StylesheetHandler!");
        return null;
    }
    
    NamespaceSupport getNamespaceSupport() {
        return this.m_nsSupportStack.peek();
    }
    
    public Node getOriginatingNode() {
        return this.m_originatingNode;
    }
    
    XSLTElementProcessor getProcessorFor(final String uri, final String localName, final String rawName) throws SAXException {
        final XSLTElementProcessor currentProcessor = this.getCurrentProcessor();
        final XSLTElementDef def = currentProcessor.getElemDef();
        XSLTElementProcessor elemProcessor = def.getProcessorFor(uri, localName);
        if (elemProcessor == null && (this.getStylesheet() == null || Double.valueOf(this.getStylesheet().getVersion()) > 1.0 || currentProcessor instanceof ProcessorStylesheetElement)) {
            elemProcessor = def.getProcessorForUnknown(uri, localName);
        }
        if (elemProcessor == null) {
            this.error(String.valueOf(rawName) + " is not allowed in this position in the stylesheet!", null);
        }
        return elemProcessor;
    }
    
    XSLTSchema getSchema() {
        return this.m_schema;
    }
    
    Stylesheet getStylesheet() {
        return (this.m_stylesheets.size() == 0) ? null : this.m_stylesheets.peek();
    }
    
    TransformerFactoryImpl getStylesheetProcessor() {
        return this.m_stylesheetProcessor;
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_stylesheetRoot;
    }
    
    int getStylesheetType() {
        return this.m_stylesheetType;
    }
    
    public String getSystemId() {
        return this.getBaseIdentifier();
    }
    
    public Templates getTemplates() {
        return this.getStylesheetRoot();
    }
    
    XSLMessages getXSLMessages() {
        return StylesheetHandler.m_XSLMessages;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        this.getCurrentProcessor().ignorableWhitespace(this, ch, start, length);
    }
    
    boolean importStackContains(final String hrefUrl) {
        return this.stackContains(this.m_importStack, hrefUrl);
    }
    
    void init(final TransformerFactoryImpl processor) {
        if (!StylesheetHandler.m_xpathFunctionsInited) {
            synchronized (this) {
                if (!StylesheetHandler.m_xpathFunctionsInited) {
                    StylesheetHandler.m_xpathFunctionsInited = true;
                    Function func = new FuncDocument();
                    FunctionTable.installFunction("document", func);
                    func = new FuncFormatNumb();
                    FunctionTable.installFunction("format-number", func);
                }
            }
        }
        this.m_stylesheetProcessor = processor;
        this.m_processors.push(this.m_schema.getElementProcessor());
        this.pushNewNamespaceSupport();
    }
    
    boolean isSpacePreserve() {
        return this.m_spacePreserveStack.peek();
    }
    
    public boolean isStylesheetParsingComplete() {
        return this.m_parsingComplete;
    }
    
    int nextUid() {
        return this.m_docOrderCount++;
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) {
        this.getCurrentProcessor().notationDecl(this, name, publicId, systemId);
    }
    
    String popBaseIndentifier() {
        return this.m_baseIdentifiers.pop();
    }
    
    ElemTemplateElement popElemTemplateElement() {
        return this.m_elems.pop();
    }
    
    String popImportURL() {
        return this.m_importStack.pop();
    }
    
    void popNamespaceSupport() {
        this.m_nsSupportStack.pop();
    }
    
    XSLTElementProcessor popProcessor() {
        return this.m_processors.pop();
    }
    
    void popSpaceHandling() {
        this.m_spacePreserveStack.pop();
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
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        this.flushCharacters();
        this.getCurrentProcessor().processingInstruction(this, target, data);
    }
    
    void pushBaseIndentifier(final String baseID) {
        if (baseID != null) {
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
    
    void pushElemTemplateElement(final ElemTemplateElement elem) {
        if (elem.getUid() == -1) {
            elem.setUid(this.nextUid());
        }
        this.m_elems.push(elem);
    }
    
    void pushImportURL(final String hrefUrl) {
        this.m_importStack.push(hrefUrl);
    }
    
    void pushNewNamespaceSupport() {
        this.m_nsSupportStack.push(new NamespaceSupport());
    }
    
    void pushProcessor(final XSLTElementProcessor processor) {
        this.m_processors.push(processor);
    }
    
    void pushSpaceHandling(final Attributes attrs) throws SAXParseException {
        final String value = attrs.getValue("xml:space");
        if (value == null) {
            this.m_spacePreserveStack.push(this.m_spacePreserveStack.peek());
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
                handler.error(new TransformerException("Illegal value for xml:space", locator));
            }
            catch (TransformerException te) {
                throw new SAXParseException(te.getMessage(), locator, te);
            }
            this.m_spacePreserveStack.push(this.m_spacePreserveStack.peek());
        }
    }
    
    void pushSpaceHandling(final boolean b) throws SAXParseException {
        this.m_spacePreserveStack.push(b);
    }
    
    public void pushStylesheet(final Stylesheet s) {
        if (this.m_stylesheets.size() == 0) {
            this.m_stylesheetRoot = (StylesheetRoot)s;
        }
        this.m_stylesheets.push(s);
    }
    
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException {
        return this.getCurrentProcessor().resolveEntity(this, publicId, systemId);
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.m_stylesheetLocatorStack.push(new SAXSourceLocator(locator));
    }
    
    public void setOriginatingNode(final Node n) {
        this.m_originatingNode = n;
    }
    
    void setStylesheetType(final int type) {
        this.m_stylesheetType = type;
    }
    
    public void setSystemId(final String baseID) {
        this.pushBaseIndentifier(baseID);
    }
    
    public void skippedEntity(final String name) throws SAXException {
        if (!this.m_shouldProcess) {
            return;
        }
        this.getCurrentProcessor().skippedEntity(this, name);
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
    
    public void startDocument() throws SAXException {
        ++this.m_stylesheetLevel;
        this.pushSpaceHandling(false);
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
        this.pushProcessor(elemProcessor);
        elemProcessor.startElement(this, uri, localName, rawName, attributes);
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.m_prefixMappings.addElement(prefix);
        this.m_prefixMappings.addElement(uri);
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) {
        this.getCurrentProcessor().unparsedEntityDecl(this, name, publicId, systemId, notationName);
    }
    
    public void warn(final int msg, final Object[] args) throws SAXException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final SAXSourceLocator locator = this.getLocator();
        final ErrorListener handler = this.m_stylesheetProcessor.getErrorListener();
        try {
            if (handler != null) {
                handler.warning(new TransformerException(formattedMsg, locator));
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
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
}
