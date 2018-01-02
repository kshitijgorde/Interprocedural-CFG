// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Comment;
import org.w3c.dom.EntityReference;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Node;
import org.apache.crimson.parser.AttributesEx;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXParseException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.Vector;
import java.util.Locale;
import org.xml.sax.Locator;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;

public class XmlDocumentBuilder implements ContentHandler, LexicalHandler, DeclHandler, DTDHandler
{
    private XmlDocument document;
    private Locator locator;
    private Locale locale;
    private ElementFactory factory;
    private Vector attrTmp;
    private ParentNode[] elementStack;
    private int topOfStack;
    private boolean inDTD;
    private boolean inCDataSection;
    private Doctype doctype;
    private boolean disableNamespaces;
    private boolean ignoreWhitespace;
    private boolean expandEntityRefs;
    private boolean ignoreComments;
    private boolean putCDATAIntoText;
    static /* synthetic */ Class class$org$apache$crimson$tree$TextNode;
    
    public XmlDocumentBuilder() {
        this.locale = Locale.getDefault();
        this.attrTmp = new Vector();
        this.disableNamespaces = true;
        this.ignoreWhitespace = false;
        this.expandEntityRefs = true;
        this.ignoreComments = false;
        this.putCDATAIntoText = false;
    }
    
    public boolean isIgnoringLexicalInfo() {
        return this.ignoreWhitespace && this.expandEntityRefs && this.ignoreComments && this.putCDATAIntoText;
    }
    
    public void setIgnoringLexicalInfo(final boolean value) {
        this.ignoreWhitespace = value;
        this.expandEntityRefs = value;
        this.ignoreComments = value;
        this.putCDATAIntoText = value;
    }
    
    public void setIgnoreWhitespace(final boolean value) {
        this.ignoreWhitespace = value;
    }
    
    public void setExpandEntityReferences(final boolean value) {
        this.expandEntityRefs = value;
    }
    
    public void setIgnoreComments(final boolean value) {
        this.ignoreComments = value;
    }
    
    public void setPutCDATAIntoText(final boolean value) {
        this.putCDATAIntoText = value;
    }
    
    public boolean getDisableNamespaces() {
        return this.disableNamespaces;
    }
    
    public void setDisableNamespaces(final boolean value) {
        this.disableNamespaces = value;
    }
    
    public XmlDocument getDocument() {
        return this.document;
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(Locale locale) throws SAXException {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.locale = locale;
    }
    
    public Locale chooseLocale(final String[] languages) throws SAXException {
        final Locale l = XmlDocument.catalog.chooseLocale(languages);
        if (l != null) {
            this.setLocale(l);
        }
        return l;
    }
    
    String getMessage(final String messageId) {
        return this.getMessage(messageId, null);
    }
    
    String getMessage(final String messageId, final Object[] parameters) {
        if (this.locale == null) {
            this.getLocale();
        }
        return XmlDocument.catalog.getMessage(this.locale, messageId, parameters);
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
    }
    
    public XmlDocument createDocument() {
        final XmlDocument retval = new XmlDocument();
        if (this.factory != null) {
            retval.setElementFactory(this.factory);
        }
        return retval;
    }
    
    public final void setElementFactory(final ElementFactory factory) {
        this.factory = factory;
    }
    
    public final ElementFactory getElementFactory() {
        return this.factory;
    }
    
    public void startDocument() throws SAXException {
        this.document = this.createDocument();
        if (this.locator != null) {
            this.document.setSystemId(this.locator.getSystemId());
        }
        this.elementStack = new ParentNode[200];
        this.topOfStack = 0;
        this.elementStack[this.topOfStack] = this.document;
        this.inDTD = false;
    }
    
    public void endDocument() throws SAXException {
        if (this.topOfStack != 0) {
            throw new IllegalStateException(this.getMessage("XDB-000"));
        }
        this.document.trimToSize();
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
    }
    
    public void startElement(final String namespaceURI, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        AttributeSet attSet = null;
        final int length = attributes.getLength();
        if (length != 0) {
            try {
                attSet = new AttributeSet(attributes);
            }
            catch (DOMException ex) {
                throw new SAXParseException(this.getMessage("XDB-002", new Object[] { ex.getMessage() }), this.locator, ex);
            }
        }
        ElementNode e = null;
        try {
            if (namespaceURI.equals("")) {
                e = (ElementNode)this.document.createElementEx(rawName);
            }
            else {
                e = (ElementNode)this.document.createElementNS(namespaceURI, rawName);
            }
        }
        catch (DOMException ex2) {
            throw new SAXParseException(this.getMessage("XDB-004", new Object[] { ex2.getMessage() }), this.locator, ex2);
        }
        if (attributes instanceof AttributesEx) {
            e.setIdAttributeName(((AttributesEx)attributes).getIdAttributeName());
        }
        if (length != 0) {
            e.setAttributes(attSet);
        }
        this.elementStack[this.topOfStack++].appendChild(e);
        this.elementStack[this.topOfStack] = e;
        if (!this.disableNamespaces) {}
    }
    
    public void endElement(final String namespaceURI, final String localName, final String rawName) throws SAXException {
        final ElementNode e = (ElementNode)this.elementStack[this.topOfStack];
        this.elementStack[this.topOfStack--] = null;
        e.reduceWaste();
    }
    
    public void characters(final char[] buf, final int offset, final int len) throws SAXException {
        final ParentNode top = this.elementStack[this.topOfStack];
        if (this.inCDataSection) {
            final String temp = new String(buf, offset, len);
            final CDATASection section = (CDATASection)top.getLastChild();
            section.appendData(temp);
            return;
        }
        try {
            final NodeBase lastChild = (NodeBase)top.getLastChild();
            if (lastChild != null && lastChild.getClass() == ((XmlDocumentBuilder.class$org$apache$crimson$tree$TextNode == null) ? (XmlDocumentBuilder.class$org$apache$crimson$tree$TextNode = class$("org.apache.crimson.tree.TextNode")) : XmlDocumentBuilder.class$org$apache$crimson$tree$TextNode)) {
                final String tmp = new String(buf, offset, len);
                ((TextNode)lastChild).appendData(tmp);
            }
            else {
                final TextNode text = this.document.newText(buf, offset, len);
                top.appendChild(text);
            }
        }
        catch (DOMException ex) {
            throw new SAXParseException(this.getMessage("XDB-004", new Object[] { ex.getMessage() }), this.locator, ex);
        }
    }
    
    public void ignorableWhitespace(final char[] buf, final int offset, final int len) throws SAXException {
        if (this.ignoreWhitespace) {
            return;
        }
        final ParentNode top = this.elementStack[this.topOfStack];
        if (this.inCDataSection) {
            final String temp = new String(buf, offset, len);
            final CDATASection section = (CDATASection)top.getLastChild();
            section.appendData(temp);
            return;
        }
        final TextNode text = this.document.newText(buf, offset, len);
        try {
            top.appendChild(text);
        }
        catch (DOMException ex) {
            throw new SAXParseException(this.getMessage("XDB-004", new Object[] { ex.getMessage() }), this.locator, ex);
        }
    }
    
    public void processingInstruction(final String name, final String instruction) throws SAXException {
        if (!this.disableNamespaces && name.indexOf(58) != -1) {
            throw new SAXParseException(this.getMessage("XDB-010"), this.locator);
        }
        if (this.inDTD) {
            return;
        }
        final ParentNode top = this.elementStack[this.topOfStack];
        try {
            final PINode pi = (PINode)this.document.createProcessingInstruction(name, instruction);
            top.appendChild(pi);
        }
        catch (DOMException ex) {
            throw new SAXParseException(this.getMessage("XDB-004", new Object[] { ex.getMessage() }), this.locator, ex);
        }
    }
    
    public void skippedEntity(final String name) throws SAXException {
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        final DOMImplementation impl = this.document.getImplementation();
        (this.doctype = (Doctype)impl.createDocumentType(name, publicId, systemId)).setOwnerDocument(this.document);
        this.inDTD = true;
    }
    
    public void endDTD() throws SAXException {
        this.document.appendChild(this.doctype);
        this.inDTD = false;
    }
    
    public void startEntity(final String name) throws SAXException {
        if (this.expandEntityRefs || this.inDTD) {
            return;
        }
        final EntityReference e = this.document.createEntityReference(name);
        this.elementStack[this.topOfStack++].appendChild(e);
        this.elementStack[this.topOfStack] = (ParentNode)e;
    }
    
    public void endEntity(final String name) throws SAXException {
        if (this.inDTD) {
            return;
        }
        final ParentNode entity = this.elementStack[this.topOfStack];
        if (!(entity instanceof EntityReference)) {
            return;
        }
        entity.setReadonly(true);
        this.elementStack[this.topOfStack--] = null;
        if (!name.equals(entity.getNodeName())) {
            throw new SAXParseException(this.getMessage("XDB-011", new Object[] { name, entity.getNodeName() }), this.locator);
        }
    }
    
    public void startCDATA() throws SAXException {
        if (this.putCDATAIntoText) {
            return;
        }
        final CDATASection text = this.document.createCDATASection("");
        final ParentNode top = this.elementStack[this.topOfStack];
        try {
            this.inCDataSection = true;
            top.appendChild(text);
        }
        catch (DOMException ex) {
            throw new SAXParseException(this.getMessage("XDB-004", new Object[] { ex.getMessage() }), this.locator, ex);
        }
    }
    
    public void endCDATA() throws SAXException {
        this.inCDataSection = false;
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (this.ignoreComments || this.inDTD) {
            return;
        }
        final String text = new String(ch, start, length);
        final Comment comment = this.document.createComment(text);
        final ParentNode top = this.elementStack[this.topOfStack];
        try {
            top.appendChild(comment);
        }
        catch (DOMException ex) {
            throw new SAXParseException(this.getMessage("XDB-004", new Object[] { ex.getMessage() }), this.locator, ex);
        }
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        if (!this.disableNamespaces && name.indexOf(58) != -1) {
            throw new SAXParseException(this.getMessage("XDB-012"), this.locator);
        }
        if (!name.startsWith("%")) {
            this.doctype.addEntityNode(name, value);
        }
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (!this.disableNamespaces && name.indexOf(58) != -1) {
            throw new SAXParseException(this.getMessage("XDB-012"), this.locator);
        }
        if (!name.startsWith("%")) {
            this.doctype.addEntityNode(name, publicId, systemId, null);
        }
    }
    
    public void notationDecl(final String n, final String p, final String s) throws SAXException {
        if (!this.disableNamespaces && n.indexOf(58) != -1) {
            throw new SAXParseException(this.getMessage("XDB-013"), this.locator);
        }
        this.doctype.addNotation(n, p, s);
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notation) throws SAXException {
        if (!this.disableNamespaces && name.indexOf(58) != -1) {
            throw new SAXParseException(this.getMessage("XDB-012"), this.locator);
        }
        this.doctype.addEntityNode(name, publicId, systemId, notation);
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
