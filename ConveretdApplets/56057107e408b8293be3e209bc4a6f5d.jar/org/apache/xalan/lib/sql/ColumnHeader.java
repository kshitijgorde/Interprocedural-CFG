// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.sql.ResultSetMetaData;
import org.w3c.dom.NamedNodeMap;

public class ColumnHeader extends StreamableNode implements NamedNodeMap
{
    private static final boolean DEBUG = false;
    int m_columnIndex;
    ResultSetMetaData m_metaData;
    RowSet m_parent;
    ColumnAttribute[] m_attributes;
    
    public ColumnHeader(final XStatement statement, final RowSet parent, final int columnIndex, final ResultSetMetaData metaData) {
        super(statement);
        this.m_attributes = null;
        this.m_columnIndex = columnIndex;
        this.m_metaData = metaData;
        this.m_parent = parent;
    }
    
    private ColumnAttribute allocAttr(final int pos, final String name) {
        if (this.m_attributes[pos] == null) {
            final ColumnAttribute attr = new ColumnAttribute(this.getXStatement(), this, this.m_columnIndex, pos, this.m_metaData);
            attr.m_name = name;
            this.m_attributes[pos] = attr;
        }
        return this.m_attributes[pos];
    }
    
    private void allocAttrs() {
        this.m_attributes = new ColumnAttribute[16];
    }
    
    public NamedNodeMap getAttributes() {
        return this;
    }
    
    public Node getFirstChild() {
        return null;
    }
    
    public int getLength() {
        return 16;
    }
    
    public Node getNamedItem(final String name) {
        if (this.m_attributes == null) {
            this.allocAttrs();
        }
        final int pos = ColumnAttribute.getAttrPosFromName(name);
        if (pos >= 0) {
            return this.allocAttr(pos, name);
        }
        return null;
    }
    
    public Node getNamedItemNS(final String namespaceURI, final String localName) {
        if (namespaceURI == null) {
            return this.getNamedItem(localName);
        }
        return null;
    }
    
    public Node getNextSibling() {
        if (this.getNodeTest().getNamespace() != null || !this.getNodeTest().getLocalName().equals("column-header")) {
            return null;
        }
        final int nextIndex = this.m_columnIndex + 1;
        if (nextIndex < this.m_parent.m_columnHeaders.length) {
            if (this.m_parent.m_columnHeaders[nextIndex] == null) {
                this.m_parent.m_columnHeaders[nextIndex] = new ColumnHeader(this.getXStatement(), this.m_parent, nextIndex, this.m_metaData);
            }
            return this.m_parent.m_columnHeaders[nextIndex];
        }
        return null;
    }
    
    public String getNodeName() {
        return "column-header";
    }
    
    public Node getParentNode() {
        return this.m_parent;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public Node item(final int index) {
        if (this.m_attributes == null) {
            this.allocAttrs();
        }
        final String name = ColumnAttribute.getAttrNameFromPos(index);
        if (name != null) {
            return this.allocAttr(index, name);
        }
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
}
