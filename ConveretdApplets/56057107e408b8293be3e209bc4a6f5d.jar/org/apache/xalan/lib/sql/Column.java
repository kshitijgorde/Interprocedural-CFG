// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import java.sql.ResultSetMetaData;

public class Column extends StreamableNode
{
    int m_columnIndex;
    Row m_parent;
    private static final boolean DEBUG = false;
    ColumnData m_text;
    
    public Column(final XStatement statement, final Row parent, final int columnIndex, final ResultSetMetaData metadata) {
        super(statement);
        this.m_columnIndex = columnIndex;
        this.m_parent = parent;
        this.m_text = null;
    }
    
    public NamedNodeMap getAttributes() {
        return this.m_parent.m_parent.m_columnHeaders[this.m_columnIndex];
    }
    
    public Node getFirstChild() {
        if (this.m_text == null) {
            this.m_text = new ColumnData(this.getXStatement(), this);
        }
        return this.m_text;
    }
    
    public Node getNextSibling() {
        final int nextIndex = this.m_columnIndex + 1;
        return (nextIndex < this.m_parent.m_childCount) ? this.m_parent.m_columns[nextIndex] : null;
    }
    
    public String getNodeName() {
        return "col";
    }
    
    public Document getOwnerDocument() {
        return this.getXStatement();
    }
    
    public Node getParentNode() {
        return this.m_parent;
    }
    
    public boolean hasChildNodes() {
        return true;
    }
}
