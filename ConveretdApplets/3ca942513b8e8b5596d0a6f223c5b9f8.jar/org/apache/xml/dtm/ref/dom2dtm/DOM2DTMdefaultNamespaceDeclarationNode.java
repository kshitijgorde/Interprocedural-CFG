// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref.dom2dtm;

import org.w3c.dom.UserDataHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.Document;
import org.apache.xml.dtm.DTMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

public class DOM2DTMdefaultNamespaceDeclarationNode implements Attr
{
    final String NOT_SUPPORTED_ERR = "Unsupported operation on pseudonode";
    Element pseudoparent;
    String prefix;
    String uri;
    String nodename;
    int handle;
    
    DOM2DTMdefaultNamespaceDeclarationNode(final Element pseudoparent, final String prefix, final String uri, final int handle) {
        this.pseudoparent = pseudoparent;
        this.prefix = prefix;
        this.uri = uri;
        this.handle = handle;
        this.nodename = "xmlns:" + prefix;
    }
    
    public String getNodeName() {
        return this.nodename;
    }
    
    public String getName() {
        return this.nodename;
    }
    
    public String getNamespaceURI() {
        return "http://www.w3.org/2000/xmlns/";
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getLocalName() {
        return this.prefix;
    }
    
    public String getNodeValue() {
        return this.uri;
    }
    
    public String getValue() {
        return this.uri;
    }
    
    public Element getOwnerElement() {
        return this.pseudoparent;
    }
    
    public boolean isSupported(final String feature, final String version) {
        return false;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public boolean hasAttributes() {
        return false;
    }
    
    public Node getParentNode() {
        return null;
    }
    
    public Node getFirstChild() {
        return null;
    }
    
    public Node getLastChild() {
        return null;
    }
    
    public Node getPreviousSibling() {
        return null;
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public boolean getSpecified() {
        return false;
    }
    
    public void normalize() {
    }
    
    public NodeList getChildNodes() {
        return null;
    }
    
    public NamedNodeMap getAttributes() {
        return null;
    }
    
    public short getNodeType() {
        return 2;
    }
    
    public void setNodeValue(final String value) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public void setValue(final String value) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public void setPrefix(final String value) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public Node insertBefore(final Node a, final Node b) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public Node replaceChild(final Node a, final Node b) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public Node appendChild(final Node a) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public Node removeChild(final Node a) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public Document getOwnerDocument() {
        return this.pseudoparent.getOwnerDocument();
    }
    
    public Node cloneNode(final boolean deep) {
        throw new DTMException("Unsupported operation on pseudonode");
    }
    
    public int getHandleOfNode() {
        return this.handle;
    }
    
    public TypeInfo getSchemaTypeInfo() {
        return null;
    }
    
    public boolean isId() {
        return false;
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
}
