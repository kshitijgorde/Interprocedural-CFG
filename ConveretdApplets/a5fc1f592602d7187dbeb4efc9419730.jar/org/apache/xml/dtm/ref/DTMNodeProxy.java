// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.w3c.dom.EntityReference;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.apache.xml.dtm.DTMDOMException;
import org.w3c.dom.DOMImplementation;
import org.apache.xml.dtm.DTM;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Comment;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DTMNodeProxy implements Node, Document, Text, Element, Attr, ProcessingInstruction, Comment, DocumentFragment
{
    public DTM dtm;
    int node;
    static final DOMImplementation implementation;
    
    public DTMNodeProxy(final DTM dtm, final int node) {
        this.dtm = dtm;
        this.node = node;
    }
    
    public final DTM getDTM() {
        return this.dtm;
    }
    
    public final int getDTMNodeNumber() {
        return this.node;
    }
    
    public final boolean equals(final Node node) {
        try {
            final DTMNodeProxy dtmp = (DTMNodeProxy)node;
            return dtmp.node == this.node && dtmp.dtm == this.dtm;
        }
        catch (ClassCastException cce) {
            return false;
        }
    }
    
    public final boolean equals(final Object node) {
        try {
            return this.equals((Node)node);
        }
        catch (ClassCastException cce) {
            return false;
        }
    }
    
    public final boolean sameNodeAs(final Node other) {
        if (!(other instanceof DTMNodeProxy)) {
            return false;
        }
        final DTMNodeProxy that = (DTMNodeProxy)other;
        return this.dtm == that.dtm && this.node == that.node;
    }
    
    public final String getNodeName() {
        return this.dtm.getNodeName(this.node);
    }
    
    public final String getTarget() {
        return this.dtm.getNodeName(this.node);
    }
    
    public final String getLocalName() {
        return this.dtm.getLocalName(this.node);
    }
    
    public final String getPrefix() {
        return this.dtm.getPrefix(this.node);
    }
    
    public final void setPrefix(final String prefix) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final String getNamespaceURI() {
        return this.dtm.getNamespaceURI(this.node);
    }
    
    public final boolean supports(final String feature, final String version) {
        return DTMNodeProxy.implementation.hasFeature(feature, version);
    }
    
    public final boolean isSupported(final String feature, final String version) {
        return DTMNodeProxy.implementation.hasFeature(feature, version);
    }
    
    public final String getNodeValue() throws DOMException {
        return this.dtm.getNodeValue(this.node);
    }
    
    public final String getStringValue() throws DOMException {
        return this.dtm.getStringValue(this.node).toString();
    }
    
    public final void setNodeValue(final String nodeValue) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final short getNodeType() {
        return this.dtm.getNodeType(this.node);
    }
    
    public final Node getParentNode() {
        if (this.getNodeType() == 2) {
            return null;
        }
        final int newnode = this.dtm.getParent(this.node);
        return (newnode == -1) ? null : this.dtm.getNode(newnode);
    }
    
    public final Node getOwnerNode() {
        final int newnode = this.dtm.getParent(this.node);
        return (newnode == -1) ? null : this.dtm.getNode(newnode);
    }
    
    public final NodeList getChildNodes() {
        return new DTMChildIterNodeList(this.dtm, this.node);
    }
    
    public final Node getFirstChild() {
        final int newnode = this.dtm.getFirstChild(this.node);
        return (newnode == -1) ? null : this.dtm.getNode(newnode);
    }
    
    public final Node getLastChild() {
        final int newnode = this.dtm.getLastChild(this.node);
        return (newnode == -1) ? null : this.dtm.getNode(newnode);
    }
    
    public final Node getPreviousSibling() {
        final int newnode = this.dtm.getPreviousSibling(this.node);
        return (newnode == -1) ? null : this.dtm.getNode(newnode);
    }
    
    public final Node getNextSibling() {
        if (this.dtm.getNodeType(this.node) == 2) {
            return null;
        }
        final int newnode = this.dtm.getNextSibling(this.node);
        return (newnode == -1) ? null : this.dtm.getNode(newnode);
    }
    
    public final NamedNodeMap getAttributes() {
        return new DTMNamedNodeMap(this.dtm, this.node);
    }
    
    public boolean hasAttribute(final String name) {
        return -1 != this.dtm.getAttributeNode(this.node, null, name);
    }
    
    public boolean hasAttributeNS(final String name, final String x) {
        return -1 != this.dtm.getAttributeNode(this.node, x, name);
    }
    
    public final Document getOwnerDocument() {
        return (Document)this.dtm.getNode(this.dtm.getOwnerDocument(this.node));
    }
    
    public final Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final Node removeChild(final Node oldChild) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final Node appendChild(final Node newChild) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final boolean hasChildNodes() {
        return -1 != this.dtm.getFirstChild(this.node);
    }
    
    public final Node cloneNode(final boolean deep) {
        throw new DTMDOMException((short)9);
    }
    
    public final DocumentType getDoctype() {
        return null;
    }
    
    public final DOMImplementation getImplementation() {
        return DTMNodeProxy.implementation;
    }
    
    public final Element getDocumentElement() {
        final int dochandle = this.dtm.getDocument();
        int elementhandle = -1;
        for (int kidhandle = this.dtm.getFirstChild(dochandle); kidhandle != -1; kidhandle = this.dtm.getNextSibling(kidhandle)) {
            switch (this.dtm.getNodeType(kidhandle)) {
                case 1: {
                    if (elementhandle != -1) {
                        elementhandle = -1;
                        kidhandle = this.dtm.getLastChild(dochandle);
                        break;
                    }
                    elementhandle = kidhandle;
                    break;
                }
                case 7:
                case 8:
                case 10: {
                    break;
                }
                default: {
                    elementhandle = -1;
                    kidhandle = this.dtm.getLastChild(dochandle);
                    break;
                }
            }
        }
        if (elementhandle == -1) {
            throw new DTMDOMException((short)9);
        }
        return (Element)this.dtm.getNode(elementhandle);
    }
    
    public final Element createElement(final String tagName) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final DocumentFragment createDocumentFragment() {
        throw new DTMDOMException((short)9);
    }
    
    public final Text createTextNode(final String data) {
        throw new DTMDOMException((short)9);
    }
    
    public final Comment createComment(final String data) {
        throw new DTMDOMException((short)9);
    }
    
    public final CDATASection createCDATASection(final String data) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final ProcessingInstruction createProcessingInstruction(final String target, final String data) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final Attr createAttribute(final String name) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final EntityReference createEntityReference(final String name) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final NodeList getElementsByTagName(final String tagname) {
        throw new DTMDOMException((short)9);
    }
    
    public final Node importNode(final Node importedNode, final boolean deep) throws DOMException {
        throw new DTMDOMException((short)7);
    }
    
    public final Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        throw new DTMDOMException((short)9);
    }
    
    public final Element getElementById(final String elementId) {
        throw new DTMDOMException((short)9);
    }
    
    public final Text splitText(final int offset) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final String getData() throws DOMException {
        return this.dtm.getNodeValue(this.node);
    }
    
    public final void setData(final String data) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final int getLength() {
        return this.dtm.getNodeValue(this.node).length();
    }
    
    public final String substringData(final int offset, final int count) throws DOMException {
        return this.getData().substring(offset, offset + count);
    }
    
    public final void appendData(final String arg) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final void insertData(final int offset, final String arg) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final void deleteData(final int offset, final int count) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final void replaceData(final int offset, final int count, final String arg) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final String getTagName() {
        return this.dtm.getNodeName(this.node);
    }
    
    public final String getAttribute(final String name) {
        final DTMNamedNodeMap map = new DTMNamedNodeMap(this.dtm, this.node);
        final Node node = map.getNamedItem(name);
        return (null == node) ? null : node.getNodeValue();
    }
    
    public final void setAttribute(final String name, final String value) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final void removeAttribute(final String name) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final Attr getAttributeNode(final String name) {
        final DTMNamedNodeMap map = new DTMNamedNodeMap(this.dtm, this.node);
        return (Attr)map.getNamedItem(name);
    }
    
    public final Attr setAttributeNode(final Attr newAttr) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final Attr removeAttributeNode(final Attr oldAttr) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public boolean hasAttributes() {
        return -1 != this.dtm.getFirstAttribute(this.node);
    }
    
    public final void normalize() {
        throw new DTMDOMException((short)9);
    }
    
    public final String getAttributeNS(final String namespaceURI, final String localName) {
        final DTMNamedNodeMap map = new DTMNamedNodeMap(this.dtm, this.node);
        final Node node = map.getNamedItemNS(namespaceURI, localName);
        return (null == node) ? null : node.getNodeValue();
    }
    
    public final void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final void removeAttributeNS(final String namespaceURI, final String localName) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final Attr getAttributeNodeNS(final String namespaceURI, final String localName) {
        throw new DTMDOMException((short)9);
    }
    
    public final Attr setAttributeNodeNS(final Attr newAttr) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public final String getName() {
        return this.dtm.getNodeName(this.node);
    }
    
    public final boolean getSpecified() {
        return true;
    }
    
    public final String getValue() {
        return this.dtm.getNodeValue(this.node);
    }
    
    public final void setValue(final String value) {
        throw new DTMDOMException((short)9);
    }
    
    public final Element getOwnerElement() {
        if (this.getNodeType() != 2) {
            return null;
        }
        final int newnode = this.dtm.getParent(this.node);
        return (newnode == -1) ? null : ((Element)this.dtm.getNode(newnode));
    }
    
    public Node adoptNode(final Node source) throws DOMException {
        throw new DTMDOMException((short)9);
    }
    
    public String getEncoding() {
        throw new DTMDOMException((short)9);
    }
    
    public void setEncoding(final String encoding) {
        throw new DTMDOMException((short)9);
    }
    
    public boolean getStandalone() {
        throw new DTMDOMException((short)9);
    }
    
    public void setStandalone(final boolean standalone) {
        throw new DTMDOMException((short)9);
    }
    
    public boolean getStrictErrorChecking() {
        throw new DTMDOMException((short)9);
    }
    
    public void setStrictErrorChecking(final boolean strictErrorChecking) {
        throw new DTMDOMException((short)9);
    }
    
    public String getVersion() {
        throw new DTMDOMException((short)9);
    }
    
    public void setVersion(final String version) {
        throw new DTMDOMException((short)9);
    }
    
    static {
        implementation = new DTMNodeProxyImplementation();
    }
    
    static class DTMNodeProxyImplementation implements DOMImplementation
    {
        public DocumentType createDocumentType(final String qualifiedName, final String publicId, final String systemId) {
            throw new DTMDOMException((short)9);
        }
        
        public Document createDocument(final String namespaceURI, final String qualfiedName, final DocumentType doctype) {
            throw new DTMDOMException((short)9);
        }
        
        public boolean hasFeature(final String feature, final String version) {
            return ("CORE".equals(feature.toUpperCase()) || "XML".equals(feature.toUpperCase())) && ("1.0".equals(version) || "2.0".equals(version));
        }
    }
}
