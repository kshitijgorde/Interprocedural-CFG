// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

public class Row extends StreamableNode
{
    int m_childCount;
    Column[] m_columns;
    ResultSetMetaData m_metadata;
    boolean m_isStreamable;
    Row m_next;
    private static final boolean DEBUG = false;
    RowSet m_parent;
    
    public Row(final XStatement statement, final RowSet parent) {
        super(statement);
        this.m_isStreamable = false;
        try {
            this.m_parent = parent;
            final XStatement xstatement = this.getXStatement();
            final ResultSet resultSet = xstatement.getResultSet();
            final ResultSetMetaData metadata = resultSet.getMetaData();
            this.m_metadata = metadata;
            this.m_childCount = metadata.getColumnCount();
            this.m_columns = new Column[this.m_childCount];
            for (int i = 0; i < this.m_childCount; ++i) {
                this.m_columns[i] = new Column(xstatement, this, i, metadata);
            }
        }
        catch (SQLException ex) {}
    }
    
    public Node getFirstChild() {
        if (this.hasChildNodes()) {
            return this.m_columns[0];
        }
        return null;
    }
    
    public Node getNextSibling() {
        final XStatement xstatement = this.getXStatement();
        final ResultSet resultSet = xstatement.getResultSet();
        try {
            if (!this.m_isStreamable) {
                if (this.m_next == null) {
                    try {
                        if (resultSet.next()) {
                            this.m_next = new Row(this.getXStatement(), this.m_parent);
                        }
                    }
                    catch (SQLException ex) {}
                }
                return this.m_next;
            }
            if (resultSet.next()) {
                return this;
            }
            return null;
        }
        catch (SQLException ex2) {
            return null;
        }
    }
    
    public String getNodeName() {
        return "row";
    }
    
    public Document getOwnerDocument() {
        return this.getXStatement();
    }
    
    public Node getParentNode() {
        return this.m_parent;
    }
    
    public boolean hasChildNodes() {
        return this.m_childCount > 0;
    }
}
