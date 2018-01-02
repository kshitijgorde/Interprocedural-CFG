// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.EntityReference;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Text;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.apache.xml.res.XMLMessages;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class UnImplNode implements Node, Element, NodeList, Document
{
    public void error(final String msg) {
        System.out.println("DOM ERROR! class: " + this.getClass().getName());
        throw new RuntimeException(XMLMessages.createXMLMessage(msg, null));
    }
    
    public void error(final String msg, final Object[] args) {
        System.out.println("DOM ERROR! class: " + this.getClass().getName());
        throw new RuntimeException(XMLMessages.createXMLMessage(msg, args));
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public boolean hasChildNodes() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }
    
    public short getNodeType() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return 0;
    }
    
    public Node getParentNode() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public NodeList getChildNodes() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node getFirstChild() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node getLastChild() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node getNextSibling() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public int getLength() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return 0;
    }
    
    public Node item(final int index) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Document getOwnerDocument() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public String getTagName() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public String getNodeName() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void normalize() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public NodeList getElementsByTagName(final String name) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Attr removeAttributeNode(final Attr oldAttr) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Attr setAttributeNode(final Attr newAttr) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public boolean hasAttribute(final String name) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }
    
    public boolean hasAttributeNS(final String name, final String x) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }
    
    public Attr getAttributeNode(final String name) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void removeAttribute(final String name) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public void setAttribute(final String name, final String value) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public String getAttribute(final String name) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public boolean hasAttributes() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }
    
    public NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Attr setAttributeNodeNS(final Attr newAttr) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Attr getAttributeNodeNS(final String namespaceURI, final String localName) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void removeAttributeNS(final String namespaceURI, final String localName) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public void setAttributeNS(final String namespaceURI, final String qualifiedName, final String value) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public String getAttributeNS(final String namespaceURI, final String localName) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node getPreviousSibling() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node cloneNode(final boolean deep) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public String getNodeValue() throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void setNodeValue(final String nodeValue) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public void setValue(final String value) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public Element getOwnerElement() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public boolean getSpecified() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }
    
    public NamedNodeMap getAttributes() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node replaceChild(final Node newChild, final Node oldChild) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public boolean isSupported(final String feature, final String version) {
        return false;
    }
    
    public String getNamespaceURI() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public String getPrefix() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void setPrefix(final String prefix) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public String getLocalName() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public DocumentType getDoctype() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public DOMImplementation getImplementation() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Element getDocumentElement() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Element createElement(final String tagName) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public DocumentFragment createDocumentFragment() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Text createTextNode(final String data) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Comment createComment(final String data) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public CDATASection createCDATASection(final String data) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public ProcessingInstruction createProcessingInstruction(final String target, final String data) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Attr createAttribute(final String name) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public EntityReference createEntityReference(final String name) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node importNode(final Node importedNode, final boolean deep) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Element getElementById(final String elementId) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void setData(final String data) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public String substringData(final int offset, final int count) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public void appendData(final String arg) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public void insertData(final int offset, final String arg) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public void deleteData(final int offset, final int count) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public void replaceData(final int offset, final int count, final String arg) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public Text splitText(final int offset) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public Node adoptNode(final Node source) throws DOMException {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return null;
    }
    
    public boolean getStrictErrorChecking() {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
        return false;
    }
    
    public void setStrictErrorChecking(final boolean strictErrorChecking) {
        this.error("ER_FUNCTION_NOT_SUPPORTED");
    }
    
    public String getBaseURI() {
        return null;
    }
    
    public short compareDocumentPosition(final Node arg0) throws DOMException {
        return 0;
    }
    
    public String getTextContent() throws DOMException {
        return null;
    }
    
    public void setTextContent(final String arg0) throws DOMException {
    }
    
    public boolean isSameNode(final Node arg0) {
        return false;
    }
    
    public String lookupPrefix(final String arg0) {
        return null;
    }
    
    public boolean isDefaultNamespace(final String arg0) {
        return false;
    }
    
    public String lookupNamespaceURI(final String arg0) {
        return null;
    }
    
    public boolean isEqualNode(final Node arg0) {
        return false;
    }
    
    public Object getFeature(final String arg0, final String arg1) {
        return null;
    }
    
    public Object setUserData(final String arg0, final Object arg1, final UserDataHandler arg2) {
        return null;
    }
    
    public Object getUserData(final String arg0) {
        return null;
    }
    
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }
    
    public void setIdAttribute(final String arg0, final boolean arg1) throws DOMException {
    }
    
    public void setIdAttributeNS(final String arg0, final String arg1, final boolean arg2) throws DOMException {
    }
    
    public void setIdAttributeNode(final Attr arg0, final boolean arg1) throws DOMException {
    }
    
    public String getInputEncoding() {
        return null;
    }
    
    public String getXmlEncoding() {
        return null;
    }
    
    public boolean getXmlStandalone() {
        return false;
    }
    
    public void setXmlStandalone(final boolean arg0) throws DOMException {
    }
    
    public String getXmlVersion() {
        return null;
    }
    
    public void setXmlVersion(final String arg0) throws DOMException {
    }
    
    public String getDocumentURI() {
        return null;
    }
    
    public void setDocumentURI(final String arg0) {
    }
    
    public DOMConfiguration getDomConfig() {
        return null;
    }
    
    public void normalizeDocument() {
    }
    
    public Node renameNode(final Node arg0, final String arg1, final String arg2) throws DOMException {
        return null;
    }
}
