// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class ColumnData extends StreamableNode implements Text
{
    Column m_parent;
    private static final boolean DEBUG = false;
    
    public ColumnData(final XStatement statement, final Column parent) {
        super(statement);
        this.m_parent = parent;
    }
    
    public void appendData(final String arg) throws DOMException {
        this.error(80);
    }
    
    public void deleteData(final int offset, final int count) throws DOMException {
        this.error(80);
    }
    
    public String getData() throws DOMException {
        try {
            final ResultSet rs = this.getXStatement().getResultSet();
            final int columnIndex = this.m_parent.m_columnIndex;
            if (columnIndex < this.m_parent.m_parent.m_childCount) {
                return rs.getString(columnIndex + 1);
            }
            return null;
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    public Node getFirstChild() {
        return null;
    }
    
    public int getLength() {
        final String s = this.getData();
        return (s != null) ? s.length() : 0;
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public String getNodeName() {
        return "#Text";
    }
    
    public short getNodeType() {
        return 3;
    }
    
    public String getNodeValue() throws DOMException {
        return this.getData();
    }
    
    public Document getOwnerDocument() {
        return this.getXStatement();
    }
    
    public Node getParentNode() {
        return this.m_parent;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public void insertData(final int offset, final String arg) throws DOMException {
        this.error(80);
    }
    
    public void replaceData(final int offset, final int count, final String arg) throws DOMException {
        this.error(80);
    }
    
    public void setData(final String data) throws DOMException {
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
