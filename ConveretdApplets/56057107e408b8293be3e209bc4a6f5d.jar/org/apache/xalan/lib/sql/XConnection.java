// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.util.Hashtable;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.NamedNodeMap;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.w3c.dom.Element;
import java.sql.Connection;
import java.util.Properties;

public class XConnection
{
    private static final boolean DEBUG = false;
    public String m_driver;
    public String m_dbURL;
    public String m_user;
    public String m_password;
    public Properties m_protocol;
    public Connection m_connection;
    
    public XConnection(final String driver, final String dbURL) {
        this.m_connection = null;
        this.init(driver, dbURL, null, null, null, 1);
    }
    
    public XConnection(final String driver, final String dbURL, final String user, final String password) {
        this.m_connection = null;
        this.init(driver, dbURL, user, password, null, 3);
    }
    
    public XConnection(final String driver, final String dbURL, final Element protocolElem) {
        this.m_connection = null;
        this.init(driver, dbURL, null, null, protocolElem, 2);
    }
    
    public void close() throws SQLException {
        if (this.m_connection != null) {
            this.m_connection.close();
            this.m_connection = null;
        }
    }
    
    public void connect(final String driver, final String dbURL, final String user, final String password, final Properties protocol, final int getConnectionArgs) {
        try {
            Class.forName(driver).newInstance();
            switch (getConnectionArgs) {
                case 1: {
                    this.m_connection = DriverManager.getConnection(dbURL);
                    break;
                }
                case 2: {
                    this.m_connection = DriverManager.getConnection(dbURL, protocol);
                    break;
                }
                case 3: {
                    this.m_connection = DriverManager.getConnection(dbURL, user, password);
                    break;
                }
            }
            try {
                this.m_connection.setAutoCommit(false);
            }
            catch (SQLException ex) {}
            final DatabaseMetaData dma = this.m_connection.getMetaData();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    private void init(final String driver, final String dbURL, final String user, final String password, final Element protocolElem, final int getConnectionArgs) {
        this.m_driver = driver;
        this.m_dbURL = dbURL;
        this.m_user = user;
        this.m_password = password;
        if (protocolElem == null) {
            this.m_protocol = null;
        }
        else {
            this.m_protocol = new Properties();
            final NamedNodeMap atts = protocolElem.getAttributes();
            for (int i = 0; i < atts.getLength(); ++i) {
                ((Hashtable<String, String>)this.m_protocol).put(atts.item(i).getNodeName(), atts.item(i).getNodeValue());
            }
        }
        this.connect(driver, dbURL, user, password, this.m_protocol, getConnectionArgs);
    }
    
    public NodeIterator query(final String queryString) throws SQLException {
        return new XStatement(this, queryString);
    }
}
