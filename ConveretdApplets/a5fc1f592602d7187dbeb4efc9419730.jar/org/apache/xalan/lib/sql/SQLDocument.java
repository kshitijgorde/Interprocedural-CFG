// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.sql.SQLWarning;
import java.util.Vector;
import java.sql.ResultSetMetaData;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.ref.DTMManagerDefault;
import org.apache.xpath.XPathContext;
import org.apache.xml.dtm.DTMManager;
import java.sql.ResultSet;
import org.apache.xalan.extensions.ExpressionContext;
import java.sql.Statement;

public class SQLDocument extends DTMDocument
{
    private boolean DEBUG;
    private static final String S_NAMESPACE = "http://xml.apache.org/xalan/SQLExtension";
    private static final String S_SQL = "sql";
    private static final String S_ROW_SET = "row-set";
    private static final String S_METADATA = "metadata";
    private static final String S_COLUMN_HEADER = "column-header";
    private static final String S_ROW = "row";
    private static final String S_COL = "col";
    private static final String S_OUT_PARAMETERS = "out-parameters";
    private static final String S_CATALOGUE_NAME = "catalogue-name";
    private static final String S_DISPLAY_SIZE = "column-display-size";
    private static final String S_COLUMN_LABEL = "column-label";
    private static final String S_COLUMN_NAME = "column-name";
    private static final String S_COLUMN_TYPE = "column-type";
    private static final String S_COLUMN_TYPENAME = "column-typename";
    private static final String S_PRECISION = "precision";
    private static final String S_SCALE = "scale";
    private static final String S_SCHEMA_NAME = "schema-name";
    private static final String S_TABLE_NAME = "table-name";
    private static final String S_CASESENSITIVE = "case-sensitive";
    private static final String S_DEFINITELYWRITABLE = "definitely-writable";
    private static final String S_ISNULLABLE = "nullable";
    private static final String S_ISSIGNED = "signed";
    private static final String S_ISWRITEABLE = "writable";
    private static final String S_ISSEARCHABLE = "searchable";
    private int m_SQL_TypeID;
    private int m_MetaData_TypeID;
    private int m_ColumnHeader_TypeID;
    private int m_RowSet_TypeID;
    private int m_Row_TypeID;
    private int m_Col_TypeID;
    private int m_OutParameter_TypeID;
    private int m_ColAttrib_CATALOGUE_NAME_TypeID;
    private int m_ColAttrib_DISPLAY_SIZE_TypeID;
    private int m_ColAttrib_COLUMN_LABEL_TypeID;
    private int m_ColAttrib_COLUMN_NAME_TypeID;
    private int m_ColAttrib_COLUMN_TYPE_TypeID;
    private int m_ColAttrib_COLUMN_TYPENAME_TypeID;
    private int m_ColAttrib_PRECISION_TypeID;
    private int m_ColAttrib_SCALE_TypeID;
    private int m_ColAttrib_SCHEMA_NAME_TypeID;
    private int m_ColAttrib_TABLE_NAME_TypeID;
    private int m_ColAttrib_CASESENSITIVE_TypeID;
    private int m_ColAttrib_DEFINITELYWRITEABLE_TypeID;
    private int m_ColAttrib_ISNULLABLE_TypeID;
    private int m_ColAttrib_ISSIGNED_TypeID;
    private int m_ColAttrib_ISWRITEABLE_TypeID;
    private int m_ColAttrib_ISSEARCHABLE_TypeID;
    private Statement m_Statement;
    private ExpressionContext m_ExpressionContext;
    private ConnectionPool m_ConnectionPool;
    private ResultSet m_ResultSet;
    private SQLQueryParser m_QueryParser;
    private int[] m_ColHeadersIdx;
    private int m_ColCount;
    private int m_MetaDataIdx;
    private int m_RowSetIdx;
    private int m_SQLIdx;
    private int m_FirstRowIdx;
    private int m_LastRowIdx;
    private boolean m_StreamingMode;
    private boolean m_MultipleResults;
    private boolean m_HasErrors;
    private boolean m_IsStatementCachingEnabled;
    private XConnection m_XConnection;
    
    public SQLDocument(final DTMManager mgr, final int ident) {
        super(mgr, ident);
        this.DEBUG = false;
        this.m_SQL_TypeID = 0;
        this.m_MetaData_TypeID = 0;
        this.m_ColumnHeader_TypeID = 0;
        this.m_RowSet_TypeID = 0;
        this.m_Row_TypeID = 0;
        this.m_Col_TypeID = 0;
        this.m_OutParameter_TypeID = 0;
        this.m_ColAttrib_CATALOGUE_NAME_TypeID = 0;
        this.m_ColAttrib_DISPLAY_SIZE_TypeID = 0;
        this.m_ColAttrib_COLUMN_LABEL_TypeID = 0;
        this.m_ColAttrib_COLUMN_NAME_TypeID = 0;
        this.m_ColAttrib_COLUMN_TYPE_TypeID = 0;
        this.m_ColAttrib_COLUMN_TYPENAME_TypeID = 0;
        this.m_ColAttrib_PRECISION_TypeID = 0;
        this.m_ColAttrib_SCALE_TypeID = 0;
        this.m_ColAttrib_SCHEMA_NAME_TypeID = 0;
        this.m_ColAttrib_TABLE_NAME_TypeID = 0;
        this.m_ColAttrib_CASESENSITIVE_TypeID = 0;
        this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID = 0;
        this.m_ColAttrib_ISNULLABLE_TypeID = 0;
        this.m_ColAttrib_ISSIGNED_TypeID = 0;
        this.m_ColAttrib_ISWRITEABLE_TypeID = 0;
        this.m_ColAttrib_ISSEARCHABLE_TypeID = 0;
        this.m_Statement = null;
        this.m_ExpressionContext = null;
        this.m_ConnectionPool = null;
        this.m_ResultSet = null;
        this.m_QueryParser = null;
        this.m_MetaDataIdx = -1;
        this.m_RowSetIdx = -1;
        this.m_SQLIdx = -1;
        this.m_FirstRowIdx = -1;
        this.m_LastRowIdx = -1;
        this.m_StreamingMode = true;
        this.m_MultipleResults = false;
        this.m_HasErrors = false;
        this.m_IsStatementCachingEnabled = false;
        this.m_XConnection = null;
    }
    
    public static SQLDocument getNewDocument(final ExpressionContext exprContext) {
        final DTMManager mgr = ((XPathContext.XPathExpressionContext)exprContext).getDTMManager();
        final DTMManagerDefault mgrDefault = (DTMManagerDefault)mgr;
        final int dtmIdent = mgrDefault.getFirstFreeDTMID();
        final SQLDocument doc = new SQLDocument(mgr, dtmIdent << 16);
        mgrDefault.addDTM(doc, dtmIdent);
        doc.setExpressionContext(exprContext);
        return doc;
    }
    
    protected void setExpressionContext(final ExpressionContext expr) {
        this.m_ExpressionContext = expr;
    }
    
    public ExpressionContext getExpressionContext() {
        return this.m_ExpressionContext;
    }
    
    public void execute(final XConnection xconn, final SQLQueryParser query) throws SQLException {
        try {
            this.m_StreamingMode = "true".equals(xconn.getFeature("streaming"));
            this.m_MultipleResults = "true".equals(xconn.getFeature("multiple-results"));
            this.m_IsStatementCachingEnabled = "true".equals(xconn.getFeature("cache-statements"));
            this.m_XConnection = xconn;
            this.m_QueryParser = query;
            this.executeSQLStatement();
            this.createExpandedNameTable();
            super.m_DocumentIdx = this.addElement(0, super.m_Document_TypeID, -1, -1);
            this.m_SQLIdx = this.addElement(1, this.m_SQL_TypeID, super.m_DocumentIdx, -1);
            if (!this.m_MultipleResults) {
                this.extractSQLMetaData(this.m_ResultSet.getMetaData());
            }
            this.addRowToDTMFromResultSet();
        }
        catch (SQLException e) {
            this.m_HasErrors = true;
            throw e;
        }
    }
    
    private void executeSQLStatement() throws SQLException {
        this.m_ConnectionPool = this.m_XConnection.getConnectionPool();
        final Connection conn = this.m_ConnectionPool.getConnection();
        if (!this.m_QueryParser.hasParameters()) {
            this.m_Statement = conn.createStatement();
            if (!this.m_Statement.execute(this.m_QueryParser.getSQLQuery())) {
                throw new SQLException("Error in Query");
            }
        }
        else if (this.m_QueryParser.isCallable()) {
            final CallableStatement cstmt = conn.prepareCall(this.m_QueryParser.getSQLQuery());
            this.m_QueryParser.registerOutputParameters(cstmt);
            this.m_QueryParser.populateStatement(cstmt, this.m_ExpressionContext);
            this.m_Statement = cstmt;
            if (!cstmt.execute()) {
                throw new SQLException("Error in Callable Statement");
            }
        }
        else {
            final PreparedStatement stmt = conn.prepareStatement(this.m_QueryParser.getSQLQuery());
            this.m_QueryParser.populateStatement(stmt, this.m_ExpressionContext);
            this.m_Statement = stmt;
            if (!stmt.execute()) {
                throw new SQLException("Error in Prepared Statement");
            }
        }
        this.m_ResultSet = this.m_Statement.getResultSet();
    }
    
    private void extractSQLMetaData(final ResultSetMetaData meta) {
        this.m_MetaDataIdx = this.addElement(1, this.m_MetaData_TypeID, this.m_MultipleResults ? this.m_RowSetIdx : this.m_SQLIdx, -1);
        try {
            this.m_ColCount = meta.getColumnCount();
            this.m_ColHeadersIdx = new int[this.m_ColCount];
        }
        catch (Exception e) {
            this.m_XConnection.setError(e, this, this.checkWarnings());
        }
        int lastColHeaderIdx = -1;
        int i;
        for (i = 1, i = 1; i <= this.m_ColCount; ++i) {
            this.m_ColHeadersIdx[i - 1] = this.addElement(2, this.m_ColumnHeader_TypeID, this.m_MetaDataIdx, lastColHeaderIdx);
            lastColHeaderIdx = this.m_ColHeadersIdx[i - 1];
            try {
                this.addAttributeToNode(meta.getColumnName(i), this.m_ColAttrib_COLUMN_NAME_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_NAME_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.getColumnLabel(i), this.m_ColAttrib_COLUMN_LABEL_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_LABEL_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.getCatalogName(i), this.m_ColAttrib_CATALOGUE_NAME_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_CATALOGUE_NAME_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(new Integer(meta.getColumnDisplaySize(i)), this.m_ColAttrib_DISPLAY_SIZE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_DISPLAY_SIZE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(new Integer(meta.getColumnType(i)), this.m_ColAttrib_COLUMN_TYPE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_TYPE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.getColumnTypeName(i), this.m_ColAttrib_COLUMN_TYPENAME_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_TYPENAME_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(new Integer(meta.getPrecision(i)), this.m_ColAttrib_PRECISION_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_PRECISION_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(new Integer(meta.getScale(i)), this.m_ColAttrib_SCALE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_SCALE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.getSchemaName(i), this.m_ColAttrib_SCHEMA_NAME_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_SCHEMA_NAME_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.getTableName(i), this.m_ColAttrib_TABLE_NAME_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_TABLE_NAME_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.isCaseSensitive(i) ? "true" : "false", this.m_ColAttrib_CASESENSITIVE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_CASESENSITIVE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.isDefinitelyWritable(i) ? "true" : "false", this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode((meta.isNullable(i) != 0) ? "true" : "false", this.m_ColAttrib_ISNULLABLE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_ISNULLABLE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.isSigned(i) ? "true" : "false", this.m_ColAttrib_ISSIGNED_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_ISSIGNED_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.isWritable(i) ? "true" : "false", this.m_ColAttrib_ISWRITEABLE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_ISWRITEABLE_TypeID, lastColHeaderIdx);
            }
            try {
                this.addAttributeToNode(meta.isSearchable(i) ? "true" : "false", this.m_ColAttrib_ISSEARCHABLE_TypeID, lastColHeaderIdx);
            }
            catch (Exception e2) {
                this.addAttributeToNode("Not Supported", this.m_ColAttrib_ISSEARCHABLE_TypeID, lastColHeaderIdx);
            }
        }
    }
    
    protected void createExpandedNameTable() {
        super.createExpandedNameTable();
        this.m_SQL_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "sql", 1);
        this.m_MetaData_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "metadata", 1);
        this.m_ColumnHeader_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-header", 1);
        this.m_RowSet_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "row-set", 1);
        this.m_Row_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "row", 1);
        this.m_Col_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "col", 1);
        this.m_OutParameter_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "out-parameters", 1);
        this.m_ColAttrib_CATALOGUE_NAME_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "catalogue-name", 2);
        this.m_ColAttrib_DISPLAY_SIZE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-display-size", 2);
        this.m_ColAttrib_COLUMN_LABEL_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-label", 2);
        this.m_ColAttrib_COLUMN_NAME_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-name", 2);
        this.m_ColAttrib_COLUMN_TYPE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-type", 2);
        this.m_ColAttrib_COLUMN_TYPENAME_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-typename", 2);
        this.m_ColAttrib_PRECISION_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "precision", 2);
        this.m_ColAttrib_SCALE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "scale", 2);
        this.m_ColAttrib_SCHEMA_NAME_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "schema-name", 2);
        this.m_ColAttrib_TABLE_NAME_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "table-name", 2);
        this.m_ColAttrib_CASESENSITIVE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "case-sensitive", 2);
        this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "definitely-writable", 2);
        this.m_ColAttrib_ISNULLABLE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "nullable", 2);
        this.m_ColAttrib_ISSIGNED_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "signed", 2);
        this.m_ColAttrib_ISWRITEABLE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "writable", 2);
        this.m_ColAttrib_ISSEARCHABLE_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "searchable", 2);
    }
    
    private boolean addRowToDTMFromResultSet() {
        try {
            if (this.m_FirstRowIdx == -1) {
                this.m_RowSetIdx = this.addElement(1, this.m_RowSet_TypeID, this.m_SQLIdx, this.m_MultipleResults ? this.m_RowSetIdx : this.m_MetaDataIdx);
                if (this.m_MultipleResults) {
                    this.extractSQLMetaData(this.m_ResultSet.getMetaData());
                }
            }
            if (!this.m_ResultSet.next()) {
                if (this.m_StreamingMode && this.m_LastRowIdx != -1) {
                    super.m_nextsib.setElementAt(-1, this.m_LastRowIdx);
                }
                this.m_ResultSet.close();
                if (this.m_MultipleResults) {
                    while (!this.m_Statement.getMoreResults() && this.m_Statement.getUpdateCount() >= 0) {}
                    this.m_ResultSet = this.m_Statement.getResultSet();
                }
                else {
                    this.m_ResultSet = null;
                }
                if (this.m_ResultSet != null) {
                    this.m_FirstRowIdx = -1;
                    this.addRowToDTMFromResultSet();
                }
                else {
                    final Vector parameters = this.m_QueryParser.getParameters();
                    if (parameters != null) {
                        final int outParamIdx = this.addElement(1, this.m_OutParameter_TypeID, this.m_SQLIdx, this.m_RowSetIdx);
                        int lastColID = -1;
                        for (int indx = 0; indx < parameters.size(); ++indx) {
                            final QueryParameter parm = parameters.elementAt(indx);
                            if (parm.isOutput()) {
                                final Object rawobj = ((CallableStatement)this.m_Statement).getObject(indx + 1);
                                lastColID = this.addElementWithData(rawobj, 2, this.m_Col_TypeID, outParamIdx, lastColID);
                                this.addAttributeToNode(parm.getName(), this.m_ColAttrib_COLUMN_NAME_TypeID, lastColID);
                                this.addAttributeToNode(parm.getName(), this.m_ColAttrib_COLUMN_LABEL_TypeID, lastColID);
                                this.addAttributeToNode(new Integer(parm.getType()), this.m_ColAttrib_COLUMN_TYPE_TypeID, lastColID);
                                this.addAttributeToNode(parm.getTypeName(), this.m_ColAttrib_COLUMN_TYPENAME_TypeID, lastColID);
                            }
                        }
                    }
                    final SQLWarning warn = this.checkWarnings();
                    if (warn != null) {
                        this.m_XConnection.setError(null, null, warn);
                    }
                }
                return false;
            }
            if (this.m_FirstRowIdx == -1) {
                this.m_FirstRowIdx = this.addElement(2, this.m_Row_TypeID, this.m_RowSetIdx, this.m_MultipleResults ? this.m_MetaDataIdx : -1);
                this.m_LastRowIdx = this.m_FirstRowIdx;
                if (this.m_StreamingMode) {
                    super.m_nextsib.setElementAt(this.m_LastRowIdx, this.m_LastRowIdx);
                }
            }
            else if (!this.m_StreamingMode) {
                this.m_LastRowIdx = this.addElement(2, this.m_Row_TypeID, this.m_RowSetIdx, this.m_LastRowIdx);
            }
            int colID = this._firstch(this.m_LastRowIdx);
            int pcolID = -1;
            for (int i = 1; i <= this.m_ColCount; ++i) {
                final Object o = this.m_ResultSet.getObject(i);
                if (colID == -1) {
                    pcolID = this.addElementWithData(o, 3, this.m_Col_TypeID, this.m_LastRowIdx, pcolID);
                    this.cloneAttributeFromNode(pcolID, this.m_ColHeadersIdx[i - 1]);
                }
                else {
                    final int dataIdent = this._firstch(colID);
                    if (dataIdent == -1) {
                        this.error("Streaming Mode, Data Error");
                    }
                    else {
                        super.m_ObjectArray.setAt(dataIdent, o);
                    }
                }
                if (colID != -1) {
                    colID = this._nextsib(colID);
                }
            }
        }
        catch (Exception e) {
            if (this.DEBUG) {
                System.out.println("SQL Error Fetching next row [" + e.getLocalizedMessage() + "]");
            }
            this.m_XConnection.setError(e, this, this.checkWarnings());
            this.m_HasErrors = true;
        }
        return true;
    }
    
    public boolean hasErrors() {
        return this.m_HasErrors;
    }
    
    public void close() {
        try {
            final SQLWarning warn = this.checkWarnings();
            if (warn != null) {
                this.m_XConnection.setError(null, null, warn);
            }
        }
        catch (Exception ex) {}
        try {
            if (null != this.m_ResultSet) {
                this.m_ResultSet.close();
                this.m_ResultSet = null;
            }
        }
        catch (Exception ex2) {}
        Connection conn = null;
        try {
            if (null != this.m_Statement) {
                conn = this.m_Statement.getConnection();
                this.m_Statement.close();
                this.m_Statement = null;
            }
        }
        catch (Exception ex3) {}
        try {
            if (conn != null) {
                if (this.m_HasErrors) {
                    this.m_ConnectionPool.releaseConnectionOnError(conn);
                }
                else {
                    this.m_ConnectionPool.releaseConnection(conn);
                }
            }
        }
        catch (Exception ex4) {}
        this.getManager().release(this, true);
    }
    
    protected boolean nextNode() {
        if (this.DEBUG) {
            System.out.println("nextNode()");
        }
        try {
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    protected int _nextsib(final int identity) {
        if (this.m_ResultSet != null) {
            final int id = this._exptype(identity);
            if (id == this.m_Row_TypeID && identity >= this.m_LastRowIdx) {
                if (this.DEBUG) {
                    System.out.println("reading from the ResultSet");
                }
                this.addRowToDTMFromResultSet();
            }
            else if (this.m_MultipleResults && identity == this.m_RowSetIdx) {
                if (this.DEBUG) {
                    System.out.println("reading for next ResultSet");
                }
                final int startIdx = this.m_RowSetIdx;
                while (startIdx == this.m_RowSetIdx && this.m_ResultSet != null) {
                    this.addRowToDTMFromResultSet();
                }
            }
        }
        return super._nextsib(identity);
    }
    
    public void documentRegistration() {
        if (this.DEBUG) {
            System.out.println("Document Registration");
        }
    }
    
    public void documentRelease() {
        if (this.DEBUG) {
            System.out.println("Document Release");
        }
    }
    
    public SQLWarning checkWarnings() {
        SQLWarning warn = null;
        if (this.m_Statement != null) {
            try {
                warn = this.m_Statement.getWarnings();
                this.m_Statement.clearWarnings();
            }
            catch (SQLException ex) {}
        }
        return warn;
    }
}
