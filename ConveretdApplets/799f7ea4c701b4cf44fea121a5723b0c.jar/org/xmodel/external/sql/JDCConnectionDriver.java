// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import java.sql.DriverPropertyInfo;
import java.sql.Connection;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Driver;

public class JDCConnectionDriver implements Driver
{
    public static final String URL_PREFIX = "jdbc:jdc:";
    private JDCConnectionPool A;
    
    public JDCConnectionDriver(final String s, final String s2, final String s3, final String s4) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DriverManager.registerDriver(this);
        Class.forName(s).newInstance();
        this.A = new JDCConnectionPool(s2, s3, s4);
    }
    
    @Override
    public Connection connect(final String s, final Properties properties) throws SQLException {
        if (!s.startsWith("jdbc:jdc:")) {
            return null;
        }
        return this.A.getConnection();
    }
    
    @Override
    public boolean acceptsURL(final String s) {
        return s.startsWith("jdbc:jdc:");
    }
    
    @Override
    public int getMajorVersion() {
        return 1;
    }
    
    @Override
    public int getMinorVersion() {
        return 0;
    }
    
    @Override
    public DriverPropertyInfo[] getPropertyInfo(final String s, final Properties properties) {
        return new DriverPropertyInfo[0];
    }
    
    @Override
    public boolean jdbcCompliant() {
        return false;
    }
}
