// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.Node;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.xpath.axes.ContextNodeList;
import org.w3c.dom.traversal.NodeIterator;

public class XStatement extends StreamableNode implements NodeIterator, ContextNodeList, Cloneable
{
    private static final boolean DEBUG = false;
    private Statement m_statement;
    private int m_nodeCounter;
    ResultSet m_resultSet;
    private XConnection m_xconnection;
    private String m_queryString;
    RowSet m_rowset;
    boolean m_nextHasBeenCalled;
    static final String S_DOCELEMENTNAME = "row-set";
    static final String S_COLUMNHEADERNAME = "column-header";
    static final String S_ROWNAME = "row";
    static final String S_COLUMNNAME = "col";
    private int m_last;
    
    public XStatement(final XConnection connection, final String queryString) throws SQLException {
        super(null);
        this.m_nodeCounter = 0;
        this.m_nextHasBeenCalled = false;
        this.m_last = 0;
        this.m_xconnection = connection;
        this.m_statement = this.m_xconnection.m_connection.createStatement();
        this.m_queryString = queryString;
        this.m_resultSet = this.m_statement.executeQuery(this.m_queryString);
        this.m_rowset = new RowSet(this);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final XStatement clone = (XStatement)super.clone();
        return clone;
    }
    
    public NodeIterator cloneWithReset() throws CloneNotSupportedException {
        final XStatement clone = (XStatement)super.clone();
        clone.reset();
        return clone;
    }
    
    public void detach() {
        try {
            this.m_statement.close();
            this.m_statement = null;
            this.m_resultSet = null;
        }
        catch (SQLException ex) {}
    }
    
    int getAndIncrementNodeCounter() {
        final int c = this.m_nodeCounter;
        ++this.m_nodeCounter;
        return c;
    }
    
    public Node getCurrentNode() {
        return this.m_rowset;
    }
    
    public int getCurrentPos() {
        return 0;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public NodeFilter getFilter() {
        return null;
    }
    
    public Node getFirstChild() {
        try {
            if (this.getNodeTest().getNamespace() == null && this.getNodeTest().getLocalName().equals("row-set")) {
                return this.m_rowset;
            }
            return null;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public int getLast() {
        return this.m_last;
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public String getNodeName() {
        return "#Document";
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public Document getOwnerDocument() {
        return null;
    }
    
    public Node getParentNode() {
        return null;
    }
    
    public ResultSet getResultSet() {
        return this.m_resultSet;
    }
    
    public Node getRoot() {
        return this;
    }
    
    public Statement getStatement() {
        return this.m_statement;
    }
    
    public int getWhatToShow() {
        return -17;
    }
    
    public XStatement getXStatement() {
        return this;
    }
    
    public boolean hasChildNodes() {
        return true;
    }
    
    public boolean isFresh() {
        return this.m_nextHasBeenCalled ^ true;
    }
    
    public Node nextNode() throws DOMException {
        if (!this.m_nextHasBeenCalled) {
            this.m_nextHasBeenCalled = true;
            return this;
        }
        return null;
    }
    
    public Node previousNode() throws DOMException {
        this.error(80);
        return null;
    }
    
    public void reset() {
        this.m_nextHasBeenCalled = false;
    }
    
    public void runTo(final int index) {
    }
    
    public void setCurrentPos(final int i) {
    }
    
    public void setLast(final int last) {
        this.m_last = last;
    }
    
    public void setShouldCacheNodes(final boolean b) {
    }
    
    public int size() {
        return 1;
    }
    
    public String toString() {
        return "XStatement: " + this.m_queryString;
    }
}
