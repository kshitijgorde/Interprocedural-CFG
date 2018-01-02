// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.DOMOrder;
import org.w3c.dom.NamedNodeMap;
import org.apache.xpath.patterns.NodeTestFilter;
import org.apache.xml.utils.UnImplNode;

public class StreamableNode extends UnImplNode implements NodeTestFilter, NamedNodeMap, DOMOrder
{
    private XStatement m_statement;
    private NodeTest m_nodetest;
    int m_orderIndex;
    
    public StreamableNode(final XStatement statement) {
        this.m_orderIndex = -1;
        this.m_statement = statement;
        if (statement != null) {
            this.m_orderIndex = this.m_statement.getAndIncrementNodeCounter();
        }
    }
    
    public NamedNodeMap getAttributes() {
        return this;
    }
    
    public int getLength() {
        return 0;
    }
    
    public String getLocalName() {
        return this.getNodeName();
    }
    
    public Node getNamedItem(final String name) {
        return null;
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        return null;
    }
    
    public String getNamespaceURI() {
        return null;
    }
    
    public NodeTest getNodeTest() {
        return this.m_nodetest;
    }
    
    public short getNodeType() {
        return 1;
    }
    
    public Document getOwnerDocument() {
        return this.m_statement;
    }
    
    public String getPrefix() {
        return null;
    }
    
    public int getUid() {
        if (this.m_orderIndex == -1) {
            if (this.m_statement != null) {
                this.m_orderIndex = this.m_statement.getAndIncrementNodeCounter();
            }
            else {
                this.m_orderIndex = 0;
            }
        }
        return this.m_orderIndex;
    }
    
    public XStatement getXStatement() {
        return this.m_statement;
    }
    
    public boolean isSupported(final String feature, final String version) {
        return feature == "NodeTestFilter" || feature.equals("NodeTestFilter");
    }
    
    public Node item(final int index) {
        return null;
    }
    
    public Node removeNamedItem(final String name) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Node removeNamedItemNS(final String namespaceURI, final String localName) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Node setNamedItem(final Node arg) throws DOMException {
        this.error(80);
        return null;
    }
    
    public Node setNamedItemNS(final Node arg) throws DOMException {
        this.error(80);
        return null;
    }
    
    public void setNodeTest(final NodeTest nodeTest) {
        this.m_nodetest = nodeTest;
    }
}
