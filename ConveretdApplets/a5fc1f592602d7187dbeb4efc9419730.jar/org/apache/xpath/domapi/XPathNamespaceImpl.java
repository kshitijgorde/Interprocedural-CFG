// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.domapi;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.xpath.XPathNamespace;

public class XPathNamespaceImpl implements XPathNamespace
{
    Node m_attributeNode;
    
    public XPathNamespaceImpl(final Node node) {
        this.m_attributeNode = null;
        this.m_attributeNode = node;
    }
    
    public Element getOwnerElement() {
        return ((Attr)this.m_attributeNode).getOwnerElement();
    }
    
    public String getNodeName() {
        return "#namespace";
    }
    
    public String getNodeValue() throws DOMException {
        return this.m_attributeNode.getNodeValue();
    }
    
    public void setNodeValue(final String arg0) throws DOMException {
    }
    
    public short getNodeType() {
        return 13;
    }
    
    public Node getParentNode() {
        return this.m_attributeNode.getParentNode();
    }
    
    public NodeList getChildNodes() {
        return this.m_attributeNode.getChildNodes();
    }
    
    public Node getFirstChild() {
        return this.m_attributeNode.getFirstChild();
    }
    
    public Node getLastChild() {
        return this.m_attributeNode.getLastChild();
    }
    
    public Node getPreviousSibling() {
        return this.m_attributeNode.getPreviousSibling();
    }
    
    public Node getNextSibling() {
        return this.m_attributeNode.getNextSibling();
    }
    
    public NamedNodeMap getAttributes() {
        return this.m_attributeNode.getAttributes();
    }
    
    public Document getOwnerDocument() {
        return this.m_attributeNode.getOwnerDocument();
    }
    
    public Node insertBefore(final Node arg0, final Node arg1) throws DOMException {
        return null;
    }
    
    public Node replaceChild(final Node arg0, final Node arg1) throws DOMException {
        return null;
    }
    
    public Node removeChild(final Node arg0) throws DOMException {
        return null;
    }
    
    public Node appendChild(final Node arg0) throws DOMException {
        return null;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public Node cloneNode(final boolean arg0) {
        throw new DOMException((short)9, null);
    }
    
    public void normalize() {
        this.m_attributeNode.normalize();
    }
    
    public boolean isSupported(final String arg0, final String arg1) {
        return this.m_attributeNode.isSupported(arg0, arg1);
    }
    
    public String getNamespaceURI() {
        return this.m_attributeNode.getNodeValue();
    }
    
    public String getPrefix() {
        return this.m_attributeNode.getPrefix();
    }
    
    public void setPrefix(final String arg0) throws DOMException {
    }
    
    public String getLocalName() {
        return this.m_attributeNode.getPrefix();
    }
    
    public boolean hasAttributes() {
        return this.m_attributeNode.hasAttributes();
    }
}
