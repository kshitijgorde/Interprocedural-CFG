// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.w3c.dom.Node;

public class RowSet extends StreamableNode
{
    ColumnHeader[] m_columnHeaders;
    Row m_firstrow;
    private static final boolean DEBUG = false;
    
    public RowSet(final XStatement statement) {
        super(statement);
    }
    
    public Node getFirstChild() {
        try {
            if (this.getNodeTest().getNamespace() != null) {
                return null;
            }
            if (this.getNodeTest().getLocalName().equals("column-header")) {
                if (this.m_columnHeaders != null) {
                    return this.m_columnHeaders[0];
                }
                final ResultSetMetaData metaData = this.getXStatement().m_resultSet.getMetaData();
                final int columnCount = metaData.getColumnCount();
                if (columnCount > 0) {
                    this.m_columnHeaders = new ColumnHeader[columnCount];
                    return this.m_columnHeaders[0] = new ColumnHeader(this.getXStatement(), this, 0, metaData);
                }
                return null;
            }
            else {
                if (this.getNodeTest().getLocalName().equals("row")) {
                    if (this.m_firstrow == null) {
                        this.m_firstrow = new Row(this.getXStatement(), this);
                    }
                    return this.m_firstrow;
                }
                return null;
            }
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public String getNodeName() {
        return "row-set";
    }
    
    public Node getParentNode() {
        return this.getXStatement();
    }
    
    public boolean hasChildNodes() {
        return true;
    }
}
