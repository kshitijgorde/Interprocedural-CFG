// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.xml.sax.EntityResolver;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import java.util.Vector;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.util.Stack;
import java.util.Hashtable;
import org.apache.xalan.xsltc.dom.SAXImpl;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.w3c.dom.Node;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;

public class DOM2SAX implements XMLReader, Locator
{
    private static final String EMPTYSTRING = "";
    private static final String XMLNS_PREFIX = "xmlns";
    private Node _dom;
    private ContentHandler _sax;
    private LexicalHandler _lex;
    private SAXImpl _saxImpl;
    private Hashtable _nsPrefixes;
    
    public DOM2SAX(final Node root) {
        this._dom = null;
        this._sax = null;
        this._lex = null;
        this._saxImpl = null;
        this._nsPrefixes = new Hashtable();
        this._dom = root;
    }
    
    public ContentHandler getContentHandler() {
        return this._sax;
    }
    
    public void setContentHandler(final ContentHandler handler) throws NullPointerException {
        this._sax = handler;
        if (handler instanceof LexicalHandler) {
            this._lex = (LexicalHandler)handler;
        }
        if (handler instanceof SAXImpl) {
            this._saxImpl = (SAXImpl)handler;
        }
    }
    
    private boolean startPrefixMapping(final String prefix, final String uri) throws SAXException {
        boolean pushed = true;
        Stack uriStack = this._nsPrefixes.get(prefix);
        if (uriStack != null) {
            if (uriStack.isEmpty()) {
                this._sax.startPrefixMapping(prefix, uri);
                uriStack.push(uri);
            }
            else {
                final String lastUri = uriStack.peek();
                if (!lastUri.equals(uri)) {
                    this._sax.startPrefixMapping(prefix, uri);
                    uriStack.push(uri);
                }
                else {
                    pushed = false;
                }
            }
        }
        else {
            this._sax.startPrefixMapping(prefix, uri);
            this._nsPrefixes.put(prefix, uriStack = new Stack());
            uriStack.push(uri);
        }
        return pushed;
    }
    
    private void endPrefixMapping(final String prefix) throws SAXException {
        final Stack uriStack = this._nsPrefixes.get(prefix);
        if (uriStack != null) {
            this._sax.endPrefixMapping(prefix);
            uriStack.pop();
        }
    }
    
    private static String getLocalName(final Node node) {
        final String localName = node.getLocalName();
        if (localName == null) {
            final String qname = node.getNodeName();
            final int col = qname.lastIndexOf(58);
            return (col > 0) ? qname.substring(col + 1) : qname;
        }
        return localName;
    }
    
    public void parse(final InputSource unused) throws IOException, SAXException {
        this.parse(this._dom);
    }
    
    public void parse() throws IOException, SAXException {
        if (this._dom != null) {
            final boolean isIncomplete = this._dom.getNodeType() != 9;
            if (isIncomplete) {
                this._sax.startDocument();
                this.parse(this._dom);
                this._sax.endDocument();
            }
            else {
                this.parse(this._dom);
            }
        }
    }
    
    private void parse(final Node node) throws IOException, SAXException {
        final Node first = null;
        if (node == null) {
            return;
        }
        switch (node.getNodeType()) {
            case 4: {
                final String cdata = node.getNodeValue();
                if (this._lex != null) {
                    this._lex.startCDATA();
                    this._sax.characters(cdata.toCharArray(), 0, cdata.length());
                    this._lex.endCDATA();
                    break;
                }
                this._sax.characters(cdata.toCharArray(), 0, cdata.length());
                break;
            }
            case 8: {
                if (this._lex != null) {
                    final String value = node.getNodeValue();
                    this._lex.comment(value.toCharArray(), 0, value.length());
                    break;
                }
                break;
            }
            case 9: {
                this._sax.setDocumentLocator(this);
                this._sax.startDocument();
                for (Node next = node.getFirstChild(); next != null; next = next.getNextSibling()) {
                    this.parse(next);
                }
                this._sax.endDocument();
                break;
            }
            case 1: {
                final Vector pushedPrefixes = new Vector();
                final AttributesImpl attrs = new AttributesImpl();
                final NamedNodeMap map = node.getAttributes();
                final int length = map.getLength();
                for (int i = 0; i < length; ++i) {
                    final Node attr = map.item(i);
                    final String qnameAttr = attr.getNodeName();
                    if (qnameAttr.startsWith("xmlns")) {
                        final String uriAttr = attr.getNodeValue();
                        final int colon = qnameAttr.lastIndexOf(58);
                        final String prefix = (colon > 0) ? qnameAttr.substring(colon + 1) : "";
                        if (this.startPrefixMapping(prefix, uriAttr)) {
                            pushedPrefixes.addElement(prefix);
                        }
                    }
                }
                for (int j = 0; j < length; ++j) {
                    final Node attr2 = map.item(j);
                    final String qnameAttr2 = attr2.getNodeName();
                    if (!qnameAttr2.startsWith("xmlns")) {
                        final String uriAttr2 = attr2.getNamespaceURI();
                        final String localNameAttr = getLocalName(attr2);
                        if (uriAttr2 != null) {
                            final int colon2 = qnameAttr2.lastIndexOf(58);
                            final String prefix = (colon2 > 0) ? qnameAttr2.substring(0, colon2) : "";
                            if (this.startPrefixMapping(prefix, uriAttr2)) {
                                pushedPrefixes.addElement(prefix);
                            }
                        }
                        attrs.addAttribute(attr2.getNamespaceURI(), getLocalName(attr2), qnameAttr2, "CDATA", attr2.getNodeValue());
                    }
                }
                final String qname = node.getNodeName();
                final String uri = node.getNamespaceURI();
                final String localName = getLocalName(node);
                if (uri != null) {
                    final int colon3 = qname.lastIndexOf(58);
                    final String prefix = (colon3 > 0) ? qname.substring(0, colon3) : "";
                    if (this.startPrefixMapping(prefix, uri)) {
                        pushedPrefixes.addElement(prefix);
                    }
                }
                if (this._saxImpl != null) {
                    this._saxImpl.startElement(uri, localName, qname, attrs, node);
                }
                else {
                    this._sax.startElement(uri, localName, qname, attrs);
                }
                for (Node next = node.getFirstChild(); next != null; next = next.getNextSibling()) {
                    this.parse(next);
                }
                this._sax.endElement(uri, localName, qname);
                for (int nPushedPrefixes = pushedPrefixes.size(), k = 0; k < nPushedPrefixes; ++k) {
                    this.endPrefixMapping(pushedPrefixes.elementAt(k));
                }
                break;
            }
            case 7: {
                this._sax.processingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 3: {
                final String data = node.getNodeValue();
                this._sax.characters(data.toCharArray(), 0, data.length());
                break;
            }
        }
    }
    
    public DTDHandler getDTDHandler() {
        return null;
    }
    
    public ErrorHandler getErrorHandler() {
        return null;
    }
    
    public boolean getFeature(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }
    
    public void setFeature(final String name, final boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
    }
    
    public void parse(final String sysId) throws IOException, SAXException {
        throw new IOException("This method is not yet implemented.");
    }
    
    public void setDTDHandler(final DTDHandler handler) throws NullPointerException {
    }
    
    public void setEntityResolver(final EntityResolver resolver) throws NullPointerException {
    }
    
    public EntityResolver getEntityResolver() {
        return null;
    }
    
    public void setErrorHandler(final ErrorHandler handler) throws NullPointerException {
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
    }
    
    public int getColumnNumber() {
        return 0;
    }
    
    public int getLineNumber() {
        return 0;
    }
    
    public String getPublicId() {
        return null;
    }
    
    public String getSystemId() {
        return null;
    }
    
    private String getNodeTypeFromCode(final short code) {
        String retval = null;
        switch (code) {
            case 2: {
                retval = "ATTRIBUTE_NODE";
                break;
            }
            case 4: {
                retval = "CDATA_SECTION_NODE";
                break;
            }
            case 8: {
                retval = "COMMENT_NODE";
                break;
            }
            case 11: {
                retval = "DOCUMENT_FRAGMENT_NODE";
                break;
            }
            case 9: {
                retval = "DOCUMENT_NODE";
                break;
            }
            case 10: {
                retval = "DOCUMENT_TYPE_NODE";
                break;
            }
            case 1: {
                retval = "ELEMENT_NODE";
                break;
            }
            case 6: {
                retval = "ENTITY_NODE";
                break;
            }
            case 5: {
                retval = "ENTITY_REFERENCE_NODE";
                break;
            }
            case 12: {
                retval = "NOTATION_NODE";
                break;
            }
            case 7: {
                retval = "PROCESSING_INSTRUCTION_NODE";
                break;
            }
            case 3: {
                retval = "TEXT_NODE";
                break;
            }
        }
        return retval;
    }
}
