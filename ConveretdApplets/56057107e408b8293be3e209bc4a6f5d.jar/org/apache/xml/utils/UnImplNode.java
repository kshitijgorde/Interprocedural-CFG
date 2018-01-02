// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.apache.xalan.res.XSLMessages;
import org.w3c.dom.Text;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.EntityReference;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Comment;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class UnImplNode implements Node, Element, NodeList, Document
{
    public Node appendChild(final Node newChild) throws DOMException {
        this.error(80);
        return null;
    }
    
    public void appendData(final String arg) throws DOMException {
        this.error(80);
    }
    
    public Node cloneNode(final boolean deep) {
        this.error(80);
        return null;
    }
    
    public Attr createAttribute(final String name) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        this.error(80);
        return null;
    }
    
    public CDATASection createCDATASection(final String data) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Comment createComment(final String data) {
        this.error(80);
        return null;
    }
    
    public DocumentFragment createDocumentFragment() {
        this.error(80);
        return null;
    }
    
    public Element createElement(final String tagName) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        this.error(80);
        return null;
    }
    
    public EntityReference createEntityReference(final String name) throws DOMException {
        this.error(80);
        return null;
    }
    
    public ProcessingInstruction createProcessingInstruction(final String target, final String data) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Text createTextNode(final String data) {
        this.error(80);
        return null;
    }
    
    public void deleteData(final int offset, final int count) throws DOMException {
        this.error(80);
    }
    
    public void error(final int msg) {
        System.out.println("DOM ERROR! class: " + this.getClass().getName());
        throw new RuntimeException(XSLMessages.createMessage(msg, null));
    }
    
    public void error(final int msg, final Object[] args) {
        System.out.println("DOM ERROR! class: " + this.getClass().getName());
        throw new RuntimeException(XSLMessages.createMessage(msg, args));
    }
    
    public String getAttribute(final String name) {
        this.error(80);
        return null;
    }
    
    public String getAttributeNS(final String namespaceURI, final String localName) {
        this.error(80);
        return null;
    }
    
    public Attr getAttributeNode(final String name) {
        this.error(80);
        return null;
    }
    
    public Attr getAttributeNodeNS(final String namespaceURI, final String localName) {
        this.error(80);
        return null;
    }
    
    public NamedNodeMap getAttributes() {
        this.error(80);
        return null;
    }
    
    public NodeList getChildNodes() {
        this.error(80);
        return null;
    }
    
    public DocumentType getDoctype() {
        this.error(80);
        return null;
    }
    
    public Element getDocumentElement() {
        this.error(80);
        return null;
    }
    
    public Element getElementById(final String elementId) {
        this.error(80);
        return null;
    }
    
    public NodeList getElementsByTagName(final String name) {
        this.error(80);
        return null;
    }
    
    public NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        this.error(80);
        return null;
    }
    
    public Node getFirstChild() {
        this.error(80);
        return null;
    }
    
    public DOMImplementation getImplementation() {
        this.error(80);
        return null;
    }
    
    public Node getLastChild() {
        this.error(80);
        return null;
    }
    
    public int getLength() {
        this.error(80);
        return 0;
    }
    
    public String getLocalName() {
        this.error(80);
        return null;
    }
    
    public String getNamespaceURI() {
        this.error(80);
        return null;
    }
    
    public Node getNextSibling() {
        this.error(80);
        return null;
    }
    
    public String getNodeName() {
        this.error(80);
        return null;
    }
    
    public short getNodeType() {
        this.error(80);
        return 0;
    }
    
    public String getNodeValue() throws DOMException {
        this.error(80);
        return null;
    }
    
    public Document getOwnerDocument() {
        this.error(80);
        return null;
    }
    
    public Element getOwnerElement() {
        this.error(80);
        return null;
    }
    
    public Node getParentNode() {
        this.error(80);
        return null;
    }
    
    public String getPrefix() {
        this.error(80);
        return null;
    }
    
    public Node getPreviousSibling() {
        this.error(80);
        return null;
    }
    
    public boolean getSpecified() {
        this.error(80);
        return false;
    }
    
    public String getTagName() {
        this.error(80);
        return null;
    }
    
    public boolean hasAttribute(final String name) {
        this.error(80);
        return false;
    }
    
    public boolean hasAttributeNS(final String name, final String x) {
        this.error(80);
        return false;
    }
    
    public boolean hasAttributes() {
        this.error(80);
        return false;
    }
    
    public boolean hasChildNodes() {
        this.error(80);
        return false;
    }
    
    public Node importNode(final Node importedNode, final boolean deep) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        this.error(80);
        return null;
    }
    
    public void insertData(final int offset, final String arg) throws DOMException {
        this.error(80);
    }
    
    public boolean isSupported(final String feature, final String version) {
        return false;
    }
    
    public Node item(final int index) {
        this.error(80);
        return null;
    }
    
    public void normalize() {
        this.error(80);
    }
    
    public void removeAttribute(final String name) throws DOMException {
        this.error(80);
    }
    
    public void removeAttributeNS(final String namespaceURI, final String localName) throws DOMException {
        this.error(80);
    }
    
    public Attr removeAttributeNode(final Attr oldAttr) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
        this.error(80);
        return null;
    }
    
    public void replaceData(final int offset, final int count, final String arg) throws DOMException {
        this.error(80);
    }
    
    public void setAttribute(final String name, final String value) throws DOMException {
        this.error(80);
    }
    
    public void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value) throws DOMException {
        this.error(80);
    }
    
    public Attr setAttributeNode(final Attr newAttr) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Attr setAttributeNodeNS(final Attr newAttr) throws DOMException {
        this.error(80);
        return null;
    }
    
    public void setData(final String data) throws DOMException {
        this.error(80);
    }
    
    public void setNodeValue(final String nodeValue) throws DOMException {
        this.error(80);
    }
    
    public void setPrefix(final String prefix) throws DOMException {
        this.error(80);
    }
    
    public void setValue(final String value) throws DOMException {
        this.error(80);
    }
    
    public Text splitText(final int offset) throws DOMException {
        this.error(80);
        return null;
    }
    
    public String substringData(final int offset, final int count) throws DOMException {
        this.error(80);
        return null;
    }
}
