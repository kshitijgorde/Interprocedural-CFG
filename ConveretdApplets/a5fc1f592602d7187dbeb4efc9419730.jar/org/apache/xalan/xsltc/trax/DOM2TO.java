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
import org.apache.xml.serializer.NamespaceMappings;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.ContentHandler;
import org.apache.xml.serializer.SerializationHandler;
import org.w3c.dom.Node;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;

public class DOM2TO implements XMLReader, Locator
{
    private static final String EMPTYSTRING = "";
    private static final String XMLNS_PREFIX = "xmlns";
    private Node _dom;
    private SerializationHandler _handler;
    
    public DOM2TO(final Node root, final SerializationHandler handler) {
        this._dom = root;
        this._handler = handler;
    }
    
    public ContentHandler getContentHandler() {
        return null;
    }
    
    public void setContentHandler(final ContentHandler handler) {
    }
    
    public void parse(final InputSource unused) throws IOException, SAXException {
        this.parse(this._dom);
    }
    
    public void parse() throws IOException, SAXException {
        if (this._dom != null) {
            final boolean isIncomplete = this._dom.getNodeType() != 9;
            if (isIncomplete) {
                this._handler.startDocument();
                this.parse(this._dom);
                this._handler.endDocument();
            }
            else {
                this.parse(this._dom);
            }
        }
    }
    
    private void parse(final Node node) throws IOException, SAXException {
        if (node == null) {
            return;
        }
        switch (node.getNodeType()) {
            case 4: {
                this._handler.startCDATA();
                this._handler.characters(node.getNodeValue());
                this._handler.endCDATA();
                break;
            }
            case 8: {
                this._handler.comment(node.getNodeValue());
                break;
            }
            case 9: {
                this._handler.startDocument();
                for (Node next = node.getFirstChild(); next != null; next = next.getNextSibling()) {
                    this.parse(next);
                }
                this._handler.endDocument();
                break;
            }
            case 11: {
                for (Node next = node.getFirstChild(); next != null; next = next.getNextSibling()) {
                    this.parse(next);
                }
                break;
            }
            case 1: {
                final String qname = node.getNodeName();
                this._handler.startElement(null, null, qname);
                final NamedNodeMap map = node.getAttributes();
                final int length = map.getLength();
                final NamespaceMappings nm = new NamespaceMappings();
                for (int i = 0; i < length; ++i) {
                    final Node attr = map.item(i);
                    final String qnameAttr = attr.getNodeName();
                    if (qnameAttr.startsWith("xmlns")) {
                        final String uriAttr = attr.getNodeValue();
                        final int colon = qnameAttr.lastIndexOf(58);
                        final String prefix = (colon > 0) ? qnameAttr.substring(colon + 1) : "";
                        this._handler.namespaceAfterStartElement(prefix, uriAttr);
                    }
                    else {
                        final String uriAttr = attr.getNamespaceURI();
                        if (uriAttr != null && !uriAttr.equals("")) {
                            final int colon = qnameAttr.lastIndexOf(58);
                            String newPrefix = nm.lookupPrefix(uriAttr);
                            if (newPrefix == null) {
                                newPrefix = nm.generateNextPrefix();
                            }
                            final String prefix = (colon > 0) ? qnameAttr.substring(0, colon) : newPrefix;
                            this._handler.namespaceAfterStartElement(prefix, uriAttr);
                            this._handler.addAttribute(prefix + ":" + qnameAttr, attr.getNodeValue());
                        }
                        else {
                            this._handler.addAttribute(qnameAttr, attr.getNodeValue());
                        }
                    }
                }
                final String uri = node.getNamespaceURI();
                final String localName = node.getLocalName();
                if (uri != null) {
                    final int colon2 = qname.lastIndexOf(58);
                    final String prefix = (colon2 > 0) ? qname.substring(0, colon2) : "";
                    this._handler.namespaceAfterStartElement(prefix, uri);
                }
                else if (uri == null && localName != null) {
                    final String prefix = "";
                    this._handler.namespaceAfterStartElement(prefix, "");
                }
                for (Node next = node.getFirstChild(); next != null; next = next.getNextSibling()) {
                    this.parse(next);
                }
                this._handler.endElement(qname);
                break;
            }
            case 7: {
                this._handler.processingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 3: {
                this._handler.characters(node.getNodeValue());
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
