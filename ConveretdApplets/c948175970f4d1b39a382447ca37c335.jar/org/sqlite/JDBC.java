// 
// Decompiled by Procyon v0.5.30
// 

package org.sqlite;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverPropertyInfo;
import java.util.Properties;
import java.sql.Driver;

public class JDBC implements Driver
{
    private static final String PREFIX = "jdbc:sqlite:";
    
    public int getMajorVersion() {
        return 1;
    }
    
    public int getMinorVersion() {
        return 1;
    }
    
    public boolean jdbcCompliant() {
        return false;
    }
    
    public boolean acceptsURL(final String s) {
        return s != null && s.toLowerCase().startsWith("jdbc:sqlite:");
    }
    
    public DriverPropertyInfo[] getPropertyInfo(final String s, final Properties properties) throws SQLException {
        final DriverPropertyInfo driverPropertyInfo = new DriverPropertyInfo("shared_cache", "false");
        driverPropertyInfo.choices = new String[] { "true", "false" };
        driverPropertyInfo.description = "Enable SQLite Shared-Cache mode, native driver only.";
        driverPropertyInfo.required = false;
        return new DriverPropertyInfo[] { driverPropertyInfo };
    }
    
    public Connection connect(String trim, final Properties properties) throws SQLException {
        if (!this.acceptsURL(trim)) {
            return null;
        }
        trim = trim.trim();
        final String s = "jdbc:sqlite:".equalsIgnoreCase(trim) ? ":memory:" : trim.substring("jdbc:sqlite:".length());
        if (properties.getProperty("shared_cache") == null) {
            return new Conn(trim, s);
        }
        return new Conn(trim, s, Boolean.parseBoolean(properties.getProperty("shared_cache")));
    }
    
    static {
        try {
            DriverManager.registerDriver(new JDBC());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
