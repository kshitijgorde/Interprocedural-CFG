// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.util.Hashtable;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.dtm.ref.DTMManagerDefault;
import org.apache.xpath.XPathContext;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xml.dtm.ref.DTMNodeProxy;
import java.sql.SQLWarning;
import java.util.StringTokenizer;
import org.apache.xml.dtm.ref.DTMNodeIterator;
import org.apache.xml.dtm.DTM;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.xpath.objects.XBooleanStatic;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.xalan.extensions.ExpressionContext;
import java.util.Vector;
import java.sql.Connection;

public class XConnection
{
    private static final boolean DEBUG = false;
    private ConnectionPool m_ConnectionPool;
    private Connection m_Connection;
    private boolean m_DefaultPoolingEnabled;
    private Vector m_OpenSQLDocuments;
    private ConnectionPoolManager m_PoolMgr;
    private Vector m_ParameterList;
    private Exception m_Error;
    private SQLDocument m_LastSQLDocumentWithError;
    private boolean m_FullErrors;
    private SQLQueryParser m_QueryParser;
    private boolean m_IsDefaultPool;
    private boolean m_IsStreamingEnabled;
    private boolean m_InlineVariables;
    private boolean m_IsMultipleResultsEnabled;
    private boolean m_IsStatementCachingEnabled;
    
    public XConnection() {
        this.m_ConnectionPool = null;
        this.m_Connection = null;
        this.m_DefaultPoolingEnabled = false;
        this.m_OpenSQLDocuments = new Vector();
        this.m_PoolMgr = new ConnectionPoolManager();
        this.m_ParameterList = new Vector();
        this.m_Error = null;
        this.m_LastSQLDocumentWithError = null;
        this.m_FullErrors = false;
        this.m_QueryParser = new SQLQueryParser();
        this.m_IsDefaultPool = false;
        this.m_IsStreamingEnabled = true;
        this.m_InlineVariables = false;
        this.m_IsMultipleResultsEnabled = false;
        this.m_IsStatementCachingEnabled = false;
    }
    
    public XConnection(final ExpressionContext exprContext, final String ConnPoolName) {
        this.m_ConnectionPool = null;
        this.m_Connection = null;
        this.m_DefaultPoolingEnabled = false;
        this.m_OpenSQLDocuments = new Vector();
        this.m_PoolMgr = new ConnectionPoolManager();
        this.m_ParameterList = new Vector();
        this.m_Error = null;
        this.m_LastSQLDocumentWithError = null;
        this.m_FullErrors = false;
        this.m_QueryParser = new SQLQueryParser();
        this.m_IsDefaultPool = false;
        this.m_IsStreamingEnabled = true;
        this.m_InlineVariables = false;
        this.m_IsMultipleResultsEnabled = false;
        this.m_IsStatementCachingEnabled = false;
        this.connect(exprContext, ConnPoolName);
    }
    
    public XConnection(final ExpressionContext exprContext, final String driver, final String dbURL) {
        this.m_ConnectionPool = null;
        this.m_Connection = null;
        this.m_DefaultPoolingEnabled = false;
        this.m_OpenSQLDocuments = new Vector();
        this.m_PoolMgr = new ConnectionPoolManager();
        this.m_ParameterList = new Vector();
        this.m_Error = null;
        this.m_LastSQLDocumentWithError = null;
        this.m_FullErrors = false;
        this.m_QueryParser = new SQLQueryParser();
        this.m_IsDefaultPool = false;
        this.m_IsStreamingEnabled = true;
        this.m_InlineVariables = false;
        this.m_IsMultipleResultsEnabled = false;
        this.m_IsStatementCachingEnabled = false;
        this.connect(exprContext, driver, dbURL);
    }
    
    public XConnection(final ExpressionContext exprContext, final NodeList list) {
        this.m_ConnectionPool = null;
        this.m_Connection = null;
        this.m_DefaultPoolingEnabled = false;
        this.m_OpenSQLDocuments = new Vector();
        this.m_PoolMgr = new ConnectionPoolManager();
        this.m_ParameterList = new Vector();
        this.m_Error = null;
        this.m_LastSQLDocumentWithError = null;
        this.m_FullErrors = false;
        this.m_QueryParser = new SQLQueryParser();
        this.m_IsDefaultPool = false;
        this.m_IsStreamingEnabled = true;
        this.m_InlineVariables = false;
        this.m_IsMultipleResultsEnabled = false;
        this.m_IsStatementCachingEnabled = false;
        this.connect(exprContext, list);
    }
    
    public XConnection(final ExpressionContext exprContext, final String driver, final String dbURL, final String user, final String password) {
        this.m_ConnectionPool = null;
        this.m_Connection = null;
        this.m_DefaultPoolingEnabled = false;
        this.m_OpenSQLDocuments = new Vector();
        this.m_PoolMgr = new ConnectionPoolManager();
        this.m_ParameterList = new Vector();
        this.m_Error = null;
        this.m_LastSQLDocumentWithError = null;
        this.m_FullErrors = false;
        this.m_QueryParser = new SQLQueryParser();
        this.m_IsDefaultPool = false;
        this.m_IsStreamingEnabled = true;
        this.m_InlineVariables = false;
        this.m_IsMultipleResultsEnabled = false;
        this.m_IsStatementCachingEnabled = false;
        this.connect(exprContext, driver, dbURL, user, password);
    }
    
    public XConnection(final ExpressionContext exprContext, final String driver, final String dbURL, final Element protocolElem) {
        this.m_ConnectionPool = null;
        this.m_Connection = null;
        this.m_DefaultPoolingEnabled = false;
        this.m_OpenSQLDocuments = new Vector();
        this.m_PoolMgr = new ConnectionPoolManager();
        this.m_ParameterList = new Vector();
        this.m_Error = null;
        this.m_LastSQLDocumentWithError = null;
        this.m_FullErrors = false;
        this.m_QueryParser = new SQLQueryParser();
        this.m_IsDefaultPool = false;
        this.m_IsStreamingEnabled = true;
        this.m_InlineVariables = false;
        this.m_IsMultipleResultsEnabled = false;
        this.m_IsStatementCachingEnabled = false;
        this.connect(exprContext, driver, dbURL, protocolElem);
    }
    
    public XBooleanStatic connect(final ExpressionContext exprContext, final String ConnPoolName) {
        try {
            this.m_ConnectionPool = this.m_PoolMgr.getPool(ConnPoolName);
            if (this.m_ConnectionPool == null) {
                throw new IllegalArgumentException("Invalid Pool Name");
            }
            this.m_IsDefaultPool = false;
            return new XBooleanStatic(true);
        }
        catch (Exception e) {
            this.setError(e, exprContext);
            return new XBooleanStatic(false);
        }
    }
    
    public XBooleanStatic connect(final ExpressionContext exprContext, final String driver, final String dbURL) {
        try {
            this.init(driver, dbURL, new Properties());
            return new XBooleanStatic(true);
        }
        catch (SQLException e) {
            this.setError(e, exprContext);
            return new XBooleanStatic(false);
        }
        catch (Exception e2) {
            this.setError(e2, exprContext);
            return new XBooleanStatic(false);
        }
    }
    
    public XBooleanStatic connect(final ExpressionContext exprContext, final Element protocolElem) {
        try {
            this.initFromElement(protocolElem);
            return new XBooleanStatic(true);
        }
        catch (SQLException e) {
            this.setError(e, exprContext);
            return new XBooleanStatic(false);
        }
        catch (Exception e2) {
            this.setError(e2, exprContext);
            return new XBooleanStatic(false);
        }
    }
    
    public XBooleanStatic connect(final ExpressionContext exprContext, final NodeList list) {
        try {
            this.initFromElement((Element)list.item(0));
            return new XBooleanStatic(true);
        }
        catch (SQLException e) {
            this.setError(e, exprContext);
            return new XBooleanStatic(false);
        }
        catch (Exception e2) {
            this.setError(e2, exprContext);
            return new XBooleanStatic(false);
        }
    }
    
    public XBooleanStatic connect(final ExpressionContext exprContext, final String driver, final String dbURL, final String user, final String password) {
        try {
            final Properties prop = new Properties();
            ((Hashtable<String, String>)prop).put("user", user);
            ((Hashtable<String, String>)prop).put("password", password);
            this.init(driver, dbURL, prop);
            return new XBooleanStatic(true);
        }
        catch (SQLException e) {
            this.setError(e, exprContext);
            return new XBooleanStatic(false);
        }
        catch (Exception e2) {
            this.setError(e2, exprContext);
            return new XBooleanStatic(false);
        }
    }
    
    public XBooleanStatic connect(final ExpressionContext exprContext, final String driver, final String dbURL, final Element protocolElem) {
        try {
            final Properties prop = new Properties();
            final NamedNodeMap atts = protocolElem.getAttributes();
            for (int i = 0; i < atts.getLength(); ++i) {
                ((Hashtable<String, String>)prop).put(atts.item(i).getNodeName(), atts.item(i).getNodeValue());
            }
            this.init(driver, dbURL, prop);
            return new XBooleanStatic(true);
        }
        catch (SQLException e) {
            this.setError(e, exprContext);
            return new XBooleanStatic(false);
        }
        catch (Exception e2) {
            this.setError(e2, exprContext);
            return new XBooleanStatic(false);
        }
    }
    
    private void initFromElement(final Element e) throws SQLException {
        final Properties prop = new Properties();
        String driver = "";
        String dbURL = "";
        Node n = e.getFirstChild();
        if (null == n) {
            return;
        }
        do {
            final String nName = n.getNodeName();
            if (nName.equalsIgnoreCase("dbdriver")) {
                driver = "";
                final Node n2 = n.getFirstChild();
                if (null != n2) {
                    driver = n2.getNodeValue();
                }
            }
            if (nName.equalsIgnoreCase("dburl")) {
                dbURL = "";
                final Node n2 = n.getFirstChild();
                if (null != n2) {
                    dbURL = n2.getNodeValue();
                }
            }
            if (nName.equalsIgnoreCase("password")) {
                String s = "";
                final Node n3 = n.getFirstChild();
                if (null != n3) {
                    s = n3.getNodeValue();
                }
                ((Hashtable<String, String>)prop).put("password", s);
            }
            if (nName.equalsIgnoreCase("user")) {
                String s = "";
                final Node n3 = n.getFirstChild();
                if (null != n3) {
                    s = n3.getNodeValue();
                }
                ((Hashtable<String, String>)prop).put("user", s);
            }
            if (nName.equalsIgnoreCase("protocol")) {
                String Name = "";
                final NamedNodeMap attrs = n.getAttributes();
                final Node n4 = attrs.getNamedItem("name");
                if (null == n4) {
                    continue;
                }
                String s2 = "";
                Name = n4.getNodeValue();
                final Node n5 = n.getFirstChild();
                if (null != n5) {
                    s2 = n5.getNodeValue();
                }
                ((Hashtable<String, String>)prop).put(Name, s2);
            }
        } while ((n = n.getNextSibling()) != null);
        this.init(driver, dbURL, prop);
    }
    
    private void init(final String driver, final String dbURL, final Properties prop) throws SQLException {
        Connection con = null;
        String user = prop.getProperty("user");
        if (user == null) {
            user = "";
        }
        String passwd = prop.getProperty("password");
        if (passwd == null) {
            passwd = "";
        }
        final String poolName = driver + dbURL + user + passwd;
        final ConnectionPool cpool = this.m_PoolMgr.getPool(poolName);
        if (cpool == null) {
            final DefaultConnectionPool defpool = new DefaultConnectionPool();
            defpool.setDriver(driver);
            defpool.setURL(dbURL);
            defpool.setProtocol(prop);
            if (this.m_DefaultPoolingEnabled) {
                defpool.setPoolEnabled(true);
            }
            this.m_PoolMgr.registerPool(poolName, defpool);
            this.m_ConnectionPool = defpool;
        }
        else {
            this.m_ConnectionPool = cpool;
        }
        this.m_IsDefaultPool = true;
        try {
            con = this.m_ConnectionPool.getConnection();
        }
        catch (SQLException e) {
            if (con != null) {
                this.m_ConnectionPool.releaseConnectionOnError(con);
                con = null;
            }
            throw e;
        }
        finally {
            if (con != null) {
                this.m_ConnectionPool.releaseConnection(con);
            }
        }
    }
    
    public ConnectionPool getConnectionPool() {
        return this.m_ConnectionPool;
    }
    
    public DTM query(final ExpressionContext exprContext, final String queryString) {
        SQLDocument doc = null;
        try {
            if (null == this.m_ConnectionPool) {
                return null;
            }
            final SQLQueryParser query = this.m_QueryParser.parse(this, queryString, 1);
            doc = SQLDocument.getNewDocument(exprContext);
            doc.execute(this, query);
            this.m_OpenSQLDocuments.addElement(doc);
        }
        catch (Exception e) {
            if (doc != null) {
                if (doc.hasErrors()) {
                    this.setError(e, doc, doc.checkWarnings());
                }
                doc.close(this.m_IsDefaultPool);
                doc = null;
            }
        }
        return doc;
    }
    
    public DTM pquery(final ExpressionContext exprContext, final String queryString) {
        return this.pquery(exprContext, queryString, null);
    }
    
    public DTM pquery(final ExpressionContext exprContext, final String queryString, final String typeInfo) {
        SQLDocument doc = null;
        try {
            if (null == this.m_ConnectionPool) {
                return null;
            }
            final SQLQueryParser query = this.m_QueryParser.parse(this, queryString, 0);
            if (!this.m_InlineVariables) {
                this.addTypeToData(typeInfo);
                query.setParameters(this.m_ParameterList);
            }
            doc = SQLDocument.getNewDocument(exprContext);
            doc.execute(this, query);
            this.m_OpenSQLDocuments.addElement(doc);
        }
        catch (Exception e) {
            if (doc != null) {
                if (doc.hasErrors()) {
                    this.setError(e, doc, doc.checkWarnings());
                }
                doc.close(this.m_IsDefaultPool);
                doc = null;
            }
        }
        return doc;
    }
    
    public void skipRec(final ExpressionContext exprContext, final Object o, final int value) {
        SQLDocument sqldoc = null;
        final DTMNodeIterator nodei = null;
        sqldoc = this.locateSQLDocument(exprContext, o);
        if (sqldoc != null) {
            sqldoc.skip(value);
        }
    }
    
    private void addTypeToData(final String typeInfo) {
        if (typeInfo != null && this.m_ParameterList != null) {
            final StringTokenizer plist = new StringTokenizer(typeInfo);
            int indx = 0;
            while (plist.hasMoreTokens()) {
                final String value = plist.nextToken();
                final QueryParameter qp = this.m_ParameterList.elementAt(indx);
                if (null != qp) {
                    qp.setTypeName(value);
                }
                ++indx;
            }
        }
    }
    
    public void addParameter(final String value) {
        this.addParameterWithType(value, null);
    }
    
    public void addParameterWithType(final String value, final String Type) {
        this.m_ParameterList.addElement(new QueryParameter(value, Type));
    }
    
    public void addParameterFromElement(final Element e) {
        final NamedNodeMap attrs = e.getAttributes();
        final Node Type = attrs.getNamedItem("type");
        final Node n1 = e.getFirstChild();
        if (null != n1) {
            String value = n1.getNodeValue();
            if (value == null) {
                value = "";
            }
            this.m_ParameterList.addElement(new QueryParameter(value, Type.getNodeValue()));
        }
    }
    
    public void addParameterFromElement(final NodeList nl) {
        for (int count = nl.getLength(), x = 0; x < count; ++x) {
            this.addParameters((Element)nl.item(x));
        }
    }
    
    private void addParameters(final Element elem) {
        Node n = elem.getFirstChild();
        if (null == n) {
            return;
        }
        do {
            if (n.getNodeType() == 1) {
                final NamedNodeMap attrs = n.getAttributes();
                final Node Type = attrs.getNamedItem("type");
                String TypeStr;
                if (Type == null) {
                    TypeStr = "string";
                }
                else {
                    TypeStr = Type.getNodeValue();
                }
                final Node n2 = n.getFirstChild();
                if (null == n2) {
                    continue;
                }
                String value = n2.getNodeValue();
                if (value == null) {
                    value = "";
                }
                this.m_ParameterList.addElement(new QueryParameter(value, TypeStr));
            }
        } while ((n = n.getNextSibling()) != null);
    }
    
    public void clearParameters() {
        this.m_ParameterList.removeAllElements();
    }
    
    public void enableDefaultConnectionPool() {
        this.m_DefaultPoolingEnabled = true;
        if (this.m_ConnectionPool == null) {
            return;
        }
        if (this.m_IsDefaultPool) {
            return;
        }
        this.m_ConnectionPool.setPoolEnabled(true);
    }
    
    public void disableDefaultConnectionPool() {
        this.m_DefaultPoolingEnabled = false;
        if (this.m_ConnectionPool == null) {
            return;
        }
        if (!this.m_IsDefaultPool) {
            return;
        }
        this.m_ConnectionPool.setPoolEnabled(false);
    }
    
    public void enableStreamingMode() {
        this.m_IsStreamingEnabled = true;
    }
    
    public void disableStreamingMode() {
        this.m_IsStreamingEnabled = false;
    }
    
    public DTM getError() {
        if (this.m_FullErrors) {
            for (int idx = 0; idx < this.m_OpenSQLDocuments.size(); ++idx) {
                final SQLDocument doc = this.m_OpenSQLDocuments.elementAt(idx);
                final SQLWarning warn = doc.checkWarnings();
                if (warn != null) {
                    this.setError(null, doc, warn);
                }
            }
        }
        return this.buildErrorDocument();
    }
    
    public void close() throws SQLException {
        while (this.m_OpenSQLDocuments.size() != 0) {
            final SQLDocument d = this.m_OpenSQLDocuments.elementAt(0);
            try {
                d.close(this.m_IsDefaultPool);
            }
            catch (Exception ex) {}
            this.m_OpenSQLDocuments.removeElementAt(0);
        }
        if (null != this.m_Connection) {
            this.m_ConnectionPool.releaseConnection(this.m_Connection);
            this.m_Connection = null;
        }
    }
    
    public void close(final ExpressionContext exprContext, final Object doc) throws SQLException {
        final SQLDocument sqlDoc = this.locateSQLDocument(exprContext, doc);
        if (sqlDoc != null) {
            sqlDoc.close(this.m_IsDefaultPool);
            this.m_OpenSQLDocuments.remove(sqlDoc);
        }
    }
    
    private SQLDocument locateSQLDocument(final ExpressionContext exprContext, final Object doc) {
        try {
            if (doc instanceof DTMNodeIterator) {
                final DTMNodeIterator dtmIter = (DTMNodeIterator)doc;
                try {
                    final DTMNodeProxy root = (DTMNodeProxy)dtmIter.getRoot();
                    return (SQLDocument)root.getDTM();
                }
                catch (Exception e2) {
                    final XNodeSet xNS = (XNodeSet)dtmIter.getDTMIterator();
                    final DTMIterator iter = xNS.getContainedIter();
                    final DTM dtm = iter.getDTM(xNS.nextNode());
                    return (SQLDocument)dtm;
                }
            }
            this.setError(new Exception("SQL Extension:close - Can Not Identify SQLDocument"), exprContext);
            return null;
        }
        catch (Exception e) {
            this.setError(e, exprContext);
            return null;
        }
    }
    
    private SQLErrorDocument buildErrorDocument() {
        SQLErrorDocument eDoc = null;
        if (this.m_LastSQLDocumentWithError != null) {
            final ExpressionContext ctx = this.m_LastSQLDocumentWithError.getExpressionContext();
            final SQLWarning warn = this.m_LastSQLDocumentWithError.checkWarnings();
            try {
                final DTMManager mgr = ((XPathContext.XPathExpressionContext)ctx).getDTMManager();
                final DTMManagerDefault mgrDefault = (DTMManagerDefault)mgr;
                final int dtmIdent = mgrDefault.getFirstFreeDTMID();
                eDoc = new SQLErrorDocument(mgr, dtmIdent << 16, this.m_Error, warn, this.m_FullErrors);
                mgrDefault.addDTM(eDoc, dtmIdent);
                this.m_Error = null;
                this.m_LastSQLDocumentWithError = null;
            }
            catch (Exception e) {
                eDoc = null;
            }
        }
        return eDoc;
    }
    
    public void setError(final Exception excp, final ExpressionContext expr) {
        try {
            final ErrorListener listen = expr.getErrorListener();
            if (listen != null && excp != null) {
                listen.warning(new TransformerException(excp.toString(), expr.getXPathContext().getSAXLocator(), excp));
            }
        }
        catch (Exception ex) {}
    }
    
    public void setError(final Exception excp, final SQLDocument doc, final SQLWarning warn) {
        final ExpressionContext cont = doc.getExpressionContext();
        this.m_LastSQLDocumentWithError = doc;
        try {
            final ErrorListener listen = cont.getErrorListener();
            if (listen != null && excp != null) {
                listen.warning(new TransformerException(excp.toString(), cont.getXPathContext().getSAXLocator(), excp));
            }
            if (listen != null && warn != null) {
                listen.warning(new TransformerException(warn.toString(), cont.getXPathContext().getSAXLocator(), warn));
            }
            if (excp != null) {
                this.m_Error = excp;
            }
            if (warn != null) {
                final SQLWarning tw = new SQLWarning(warn.getMessage(), warn.getSQLState(), warn.getErrorCode());
                for (SQLWarning nw = warn.getNextWarning(); nw != null; nw = nw.getNextWarning()) {
                    tw.setNextWarning(new SQLWarning(nw.getMessage(), nw.getSQLState(), nw.getErrorCode()));
                }
                tw.setNextWarning(new SQLWarning(warn.getMessage(), warn.getSQLState(), warn.getErrorCode()));
            }
        }
        catch (Exception ex) {}
    }
    
    public void setFeature(final String feature, final String setting) {
        boolean value = false;
        if ("true".equalsIgnoreCase(setting)) {
            value = true;
        }
        if ("streaming".equalsIgnoreCase(feature)) {
            this.m_IsStreamingEnabled = value;
        }
        else if ("inline-variables".equalsIgnoreCase(feature)) {
            this.m_InlineVariables = value;
        }
        else if ("multiple-results".equalsIgnoreCase(feature)) {
            this.m_IsMultipleResultsEnabled = value;
        }
        else if ("cache-statements".equalsIgnoreCase(feature)) {
            this.m_IsStatementCachingEnabled = value;
        }
        else if ("default-pool-enabled".equalsIgnoreCase(feature)) {
            this.m_DefaultPoolingEnabled = value;
            if (this.m_ConnectionPool == null) {
                return;
            }
            if (this.m_IsDefaultPool) {
                return;
            }
            this.m_ConnectionPool.setPoolEnabled(value);
        }
        else if ("full-errors".equalsIgnoreCase(feature)) {
            this.m_FullErrors = value;
        }
    }
    
    public String getFeature(final String feature) {
        String value = null;
        if ("streaming".equalsIgnoreCase(feature)) {
            value = (this.m_IsStreamingEnabled ? "true" : "false");
        }
        else if ("inline-variables".equalsIgnoreCase(feature)) {
            value = (this.m_InlineVariables ? "true" : "false");
        }
        else if ("multiple-results".equalsIgnoreCase(feature)) {
            value = (this.m_IsMultipleResultsEnabled ? "true" : "false");
        }
        else if ("cache-statements".equalsIgnoreCase(feature)) {
            value = (this.m_IsStatementCachingEnabled ? "true" : "false");
        }
        else if ("default-pool-enabled".equalsIgnoreCase(feature)) {
            value = (this.m_DefaultPoolingEnabled ? "true" : "false");
        }
        else if ("full-errors".equalsIgnoreCase(feature)) {
            value = (this.m_FullErrors ? "true" : "false");
        }
        return value;
    }
    
    protected void finalize() {
        try {
            this.close();
        }
        catch (Exception ex) {}
    }
}
