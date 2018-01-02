// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.apache.xml.utils.QName;
import org.w3c.dom.DocumentFragment;
import org.apache.xml.utils.TreeWalker;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.apache.xml.utils.MutableAttrListImpl;
import org.apache.xalan.trace.GenerateEvent;
import java.util.Enumeration;
import org.w3c.dom.NamedNodeMap;
import org.apache.xpath.DOMHelper;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.trace.TraceManager;
import org.apache.xalan.templates.StylesheetRoot;
import org.xml.sax.helpers.NamespaceSupport;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public class ResultTreeHandler extends QueuedEvents implements ContentHandler, LexicalHandler
{
    private static final boolean DEBUG = false;
    boolean m_nsContextPushed;
    private NamespaceSupport m_nsSupport;
    private TransformerImpl m_transformer;
    private ContentHandler m_contentHandler;
    private LexicalHandler m_lexicalHandler;
    private StylesheetRoot m_stylesheetRoot;
    private int m_uniqueNSValue;
    private static final String S_NAMESPACEPREFIX = "ns";
    public ClonerToResultTree m_cloner;
    private TraceManager m_tracer;
    private static final int EVT_SETDOCUMENTLOCATOR = 1;
    private static final int EVT_STARTDOCUMENT = 2;
    private static final int EVT_ENDDOCUMENT = 3;
    private static final int EVT_STARTPREFIXMAPPING = 4;
    private static final int EVT_ENDPREFIXMAPPING = 5;
    private static final int EVT_STARTELEMENT = 6;
    private static final int EVT_ENDELEMENT = 7;
    private static final int EVT_CHARACTERS = 8;
    private static final int EVT_IGNORABLEWHITESPACE = 9;
    private static final int EVT_PROCESSINGINSTRUCTION = 10;
    private static final int EVT_SKIPPEDENTITY = 11;
    private static final int EVT_COMMENT = 12;
    private static final int EVT_ENTITYREF = 13;
    private static final int EVT_STARTENTITY = 14;
    private static final int EVT_ENDENTITY = 15;
    private static final int EVT_STARTDTD = 16;
    private static final int EVT_ENDDTD = 17;
    private static final int EVT_STARTCDATA = 22;
    private static final int EVT_ENDCDATA = 23;
    private static final int EVT_NODE = 24;
    
    public ResultTreeHandler() {
        this.m_nsContextPushed = false;
        this.m_nsSupport = new NamespaceSupport();
        this.m_stylesheetRoot = null;
        this.m_uniqueNSValue = 0;
    }
    
    public ResultTreeHandler(final TransformerImpl transformer, final ContentHandler realHandler) {
        this.m_nsContextPushed = false;
        this.m_nsSupport = new NamespaceSupport();
        this.m_stylesheetRoot = null;
        this.m_uniqueNSValue = 0;
        this.init(transformer, realHandler);
    }
    
    public void addAttribute(String uri, final String localName, final String rawName, final String type, final String value) throws TransformerException {
        final QueuedStartElement qe = this.getQueuedElem();
        if (!qe.nsDeclsHaveBeenAdded()) {
            this.addNSDeclsToAttrs();
        }
        if (uri == null) {
            uri = "";
        }
        try {
            if (!rawName.equals("xmlns")) {
                this.ensurePrefixIsDeclared(uri, rawName);
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        if (!this.isDefinedNSDecl(rawName, value)) {
            qe.addAttribute(uri, localName, rawName, type, value);
        }
    }
    
    public void addAttribute(final Attr attr) throws TransformerException {
        if (this.isDefinedNSDecl(attr)) {
            return;
        }
        final DOMHelper helper = this.m_transformer.getXPathContext().getDOMHelper();
        String ns = helper.getNamespaceOfNode(attr);
        if (ns == null) {
            ns = "";
        }
        this.addAttribute(ns, helper.getLocalNameOfNode(attr), attr.getNodeName(), "CDATA", attr.getValue());
    }
    
    public void addAttributes(final Node src) throws TransformerException {
        final NamedNodeMap nnm = src.getAttributes();
        for (int nAttrs = nnm.getLength(), i = 0; i < nAttrs; ++i) {
            final Attr node = (Attr)nnm.item(i);
            this.addAttribute(node);
        }
    }
    
    protected void addNSDeclsToAttrs() {
        final Enumeration prefixes = this.m_nsSupport.getDeclaredPrefixes();
        final QueuedStartElement qe = this.getQueuedElem();
        while (prefixes.hasMoreElements()) {
            final String prefix = prefixes.nextElement();
            final boolean isDefault = prefix.length() == 0;
            String name;
            if (isDefault) {
                name = "xmlns";
            }
            else {
                name = "xmlns:" + prefix;
            }
            String uri = this.m_nsSupport.getURI(prefix);
            if (uri == null) {
                uri = "";
            }
            qe.addAttribute("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
        }
        qe.setNSDeclsHaveBeenAdded(true);
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.flushPending(8);
        this.m_contentHandler.characters(ch, start, length);
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 5, ch, start, length);
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    private void checkForSerializerSwitch(final String ns, final String localName) throws SAXException {
        try {
            final QueuedStartDocument qdab = this.getQueuedDocAtBottom();
            if (qdab.isPending) {
                SerializerSwitcher.switchSerializerIfHTML(this.m_transformer, ns, localName);
            }
        }
        catch (TransformerException e) {
            throw new SAXException(e);
        }
    }
    
    public void cloneToResultTree(final Node node, final boolean shouldCloneAttributes) throws SAXException {
        try {
            this.m_cloner.cloneToResultTree(node, shouldCloneAttributes);
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    public void comment(final String data) throws SAXException {
        this.flushPending(12);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.comment(data.toCharArray(), 0, data.length());
        }
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 8, data);
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.flushPending(12);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.comment(ch, start, length);
        }
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 8, new String(ch, start, length));
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    public void endCDATA() throws SAXException {
        this.flushPending(23);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.endCDATA();
        }
    }
    
    public void endDTD() throws SAXException {
        this.flushPending(17);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.endDTD();
        }
    }
    
    public void endDocument() throws SAXException {
        this.flushPending(3);
        final QueuedStartDocument qsd = this.getQueuedDocAtBottom();
        if (!qsd.isEnded) {
            this.m_contentHandler.endDocument();
            if (this.m_tracer != null) {
                final GenerateEvent ge = new GenerateEvent(this.m_transformer, 2, null);
                this.m_tracer.fireGenerateEvent(ge);
            }
            qsd.setPending(false);
        }
    }
    
    public void endElement(final String ns, final String localName, final String name) throws SAXException {
        this.flushPending(7);
        this.m_contentHandler.endElement(ns, localName, name);
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 4, name);
            this.m_tracer.fireGenerateEvent(ge);
        }
        this.sendEndPrefixMappings();
        this.popEvent();
        this.m_nsSupport.popContext();
    }
    
    public void endEntity(final String name) throws SAXException {
        this.flushPending(15);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.endEntity(name);
        }
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 9, name);
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    void ensurePrefixIsDeclared(final String ns, final String rawName) throws SAXException {
        if (ns != null && ns.length() > 0) {
            final int index;
            final String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
            if (prefix != null) {
                final String foundURI = this.m_nsSupport.getURI(prefix);
                if (foundURI == null || !foundURI.equals(ns)) {
                    this.startPrefixMapping(prefix, ns, false);
                }
            }
        }
    }
    
    public void entityReference(final String name) throws SAXException {
        this.flushPending(13);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.startEntity(name);
            this.m_lexicalHandler.endEntity(name);
        }
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 9, name);
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    public void flushPending() throws SAXException {
        this.flushPending(24);
    }
    
    public void flushPending(final int type) throws SAXException {
        final QueuedStartElement qe = this.getQueuedElem();
        final QueuedStartDocument qdab = this.getQueuedDocAtBottom();
        if (type != 4 && qdab.isPending) {
            qdab.flush();
        }
        if (qe != null && qe.isPending) {
            if (!qe.nsDeclsHaveBeenAdded()) {
                this.addNSDeclsToAttrs();
            }
            this.sendStartPrefixMappings();
            qe.flush();
            this.m_nsContextPushed = false;
        }
    }
    
    public ContentHandler getContentHandler() {
        return this.m_contentHandler;
    }
    
    public NamespaceSupport getNamespaceSupport() {
        return this.m_nsSupport;
    }
    
    public String getNewUniqueNSPrefix() {
        return "ns" + String.valueOf(this.getUniqueNSValue());
    }
    
    public MutableAttrListImpl getPendingAttributes() {
        return this.getQueuedElem().getAttrs();
    }
    
    public String getPrefix(final String namespace) {
        final Enumeration enum1 = this.m_nsSupport.getPrefixes();
        while (enum1.hasMoreElements()) {
            final String prefix = enum1.nextElement();
            if (this.m_nsSupport.getURI(prefix).equals(namespace)) {
                return prefix;
            }
        }
        return null;
    }
    
    public String getURI(final String prefix) {
        return this.m_nsSupport.getURI(prefix);
    }
    
    public int getUniqueNSValue() {
        return this.m_uniqueNSValue++;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        final QueuedStartDocument qsd = this.getQueuedDoc();
        if (qsd != null && qsd.isPending && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
            return;
        }
        this.flushPending(9);
        this.m_contentHandler.ignorableWhitespace(ch, start, length);
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 6, ch, start, length);
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    public void init(final TransformerImpl transformer, final ContentHandler realHandler) {
        this.m_transformer = transformer;
        final TraceManager tracer = transformer.getTraceManager();
        if (tracer != null && tracer.hasTraceListeners()) {
            this.m_tracer = tracer;
        }
        else {
            this.m_tracer = null;
        }
        this.m_contentHandler = realHandler;
        if (this.m_contentHandler instanceof LexicalHandler) {
            this.m_lexicalHandler = (LexicalHandler)this.m_contentHandler;
        }
        else {
            this.m_lexicalHandler = null;
        }
        this.m_cloner = new ClonerToResultTree(transformer, this);
        if (this.m_transformer != null) {
            this.m_stylesheetRoot = this.m_transformer.getStylesheet();
        }
        this.pushDocumentEvent();
    }
    
    protected void initQSE(final QueuedSAXEvent qse) {
        qse.setContentHandler(this.m_contentHandler);
        qse.setTransformer(this.m_transformer);
        qse.setTraceManager(this.m_tracer);
    }
    
    public boolean isDefinedNSDecl(final String rawName, final String value) {
        if (rawName.equals("xmlns") || rawName.startsWith("xmlns:")) {
            final int index;
            final String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
            final String definedURI = this.m_nsSupport.getURI(prefix);
            return definedURI != null && definedURI.equals(value);
        }
        return false;
    }
    
    public boolean isDefinedNSDecl(final Attr attr) {
        final String rawName = attr.getNodeName();
        if (rawName.equals("xmlns") || rawName.startsWith("xmlns:")) {
            final int index;
            final String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
            final String uri = this.getURI(prefix);
            if (uri != null && uri.equals(attr.getValue())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isElementPending() {
        final QueuedStartElement qse = this.getQueuedElem();
        return qse != null && qse.isPending;
    }
    
    public void outputResultTreeFragment(final XObject obj, final XPathContext support) throws SAXException {
        final DocumentFragment docFrag = obj.rtree(support);
        final TreeWalker tw = new TreeWalker(this);
        for (Node n = docFrag.getFirstChild(); n != null; n = n.getNextSibling()) {
            this.flushPending(24);
            tw.traverse(n);
        }
    }
    
    public void processNSDecls(final Node src) throws TransformerException {
        try {
            final int type;
            final Node parent;
            if (((type = src.getNodeType()) == 1 || type == 5) && (parent = src.getParentNode()) != null) {
                this.processNSDecls(parent);
            }
            if (type == 1) {
                final NamedNodeMap nnm = src.getAttributes();
                for (int nAttrs = nnm.getLength(), i = 0; i < nAttrs; ++i) {
                    final Node attr = nnm.item(i);
                    final String aname = attr.getNodeName();
                    if (QName.isXMLNSDecl(aname)) {
                        final String prefix = QName.getPrefixFromXMLNSDecl(aname);
                        final String desturi = this.getURI(prefix);
                        final String srcURI = attr.getNodeValue();
                        if (!srcURI.equalsIgnoreCase(desturi)) {
                            this.startPrefixMapping(prefix, srcURI, false);
                        }
                    }
                }
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.flushPending(10);
        this.m_contentHandler.processingInstruction(target, data);
        if (this.m_tracer != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, 7, target, data);
            this.m_tracer.fireGenerateEvent(ge);
        }
    }
    
    protected void sendEndPrefixMappings() throws SAXException {
        final Enumeration prefixes = this.m_nsSupport.getDeclaredPrefixes();
        final ContentHandler handler = this.m_contentHandler;
        while (prefixes.hasMoreElements()) {
            final String prefix = prefixes.nextElement();
            handler.endPrefixMapping(prefix);
        }
    }
    
    protected void sendStartPrefixMappings() throws SAXException {
        final Enumeration prefixes = this.m_nsSupport.getDeclaredPrefixes();
        final ContentHandler handler = this.m_contentHandler;
        while (prefixes.hasMoreElements()) {
            final String prefix = prefixes.nextElement();
            handler.startPrefixMapping(prefix, this.m_nsSupport.getURI(prefix));
        }
    }
    
    public void setContentHandler(final ContentHandler ch) {
        this.m_contentHandler = ch;
        this.reInitEvents();
    }
    
    public void setDocumentLocator(final Locator locator) {
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
        this.flushPending(22);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.startCDATA();
        }
    }
    
    public void startDTD(final String s1, final String s2, final String s3) throws SAXException {
        this.flushPending(16);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.startDTD(s1, s2, s3);
        }
    }
    
    public void startDocument() throws SAXException {
    }
    
    public void startElement(final String ns, final String localName, final String name) throws SAXException {
        this.startElement(ns, localName, name, null);
    }
    
    public void startElement(final String ns, final String localName, final String name, final Attributes atts) throws SAXException {
        final QueuedStartElement qse = this.getQueuedElem();
        this.checkForSerializerSwitch(ns, localName);
        this.flushPending(6);
        if (!this.m_nsContextPushed) {
            this.m_nsSupport.pushContext();
        }
        this.ensurePrefixIsDeclared(ns, name);
        this.pushElementEvent(ns, localName, name, atts);
    }
    
    public void startEntity(final String name) throws SAXException {
        this.flushPending(14);
        if (this.m_lexicalHandler != null) {
            this.m_lexicalHandler.startEntity(name);
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.startPrefixMapping(prefix, uri, true);
    }
    
    public void startPrefixMapping(String prefix, String uri, final boolean shouldFlush) throws SAXException {
        if (shouldFlush) {
            this.flushPending(4);
        }
        if (!this.m_nsContextPushed) {
            this.m_nsSupport.pushContext();
            this.m_nsContextPushed = true;
        }
        if (prefix == null) {
            prefix = "";
        }
        String existingURI = this.m_nsSupport.getURI(prefix);
        if (existingURI == null) {
            existingURI = "";
        }
        if (uri == null) {
            uri = "";
        }
        if (!existingURI.equals(uri)) {
            this.m_nsSupport.declarePrefix(prefix, uri);
        }
    }
}
