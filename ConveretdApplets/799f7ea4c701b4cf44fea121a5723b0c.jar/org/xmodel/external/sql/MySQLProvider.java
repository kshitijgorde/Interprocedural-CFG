// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import org.xmodel.external.CachingException;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.HashMap;
import java.util.Map;

public class MySQLProvider implements ISQLProvider
{
    private static Map<String, JDCConnectionDriver> F;
    private boolean J;
    private String I;
    private String G;
    private String H;
    
    public MySQLProvider() {
        MySQLProvider.F = new HashMap<String, JDCConnectionDriver>();
    }
    
    @Override
    public void configure(final IModelObject modelObject) throws CachingException {
        final String childGet = Xlate.childGet(modelObject, "database", (String)null);
        if (childGet == null) {
            throw new CachingException("Database not defined in annotation: " + modelObject);
        }
        this.I = String.format("jdbc:mysql://%s/%s", Xlate.childGet(modelObject, "host", "127.0.0.1"), childGet);
        this.G = Xlate.childGet(modelObject, "login", (String)null);
        if (childGet == null) {
            throw new CachingException("Login not defined in annotation: " + modelObject);
        }
        this.H = Xlate.childGet(modelObject, "password", (String)null);
        if (childGet == null) {
            throw new CachingException("Password not defined in annotation: " + modelObject);
        }
    }
    
    @Override
    public Connection getConnection() throws CachingException {
        try {
            if (!this.J) {
                this.J = true;
                Class.forName("com.mysql.jdbc.Driver");
            }
            synchronized (MySQLProvider.F) {
                if (MySQLProvider.F.get(this.I) == null) {
                    MySQLProvider.F.put(this.I, new JDCConnectionDriver("com.mysql.jdbc.Driver", this.I, this.G, this.H));
                }
            }
            // monitorexit(MySQLProvider.F)
            return DriverManager.getConnection(this.I, this.G, this.H);
        }
        catch (Exception ex) {
            throw new CachingException("Unable to open connection.", ex);
        }
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s) throws CachingException {
        try {
            return this.getConnection().prepareStatement(s);
        }
        catch (Exception ex) {
            throw new CachingException("Unable to create prepared statement for sql: " + s, ex);
        }
    }
    
    @Override
    public PreparedStatement prepareStatement(final String s, final int n, final int n2, final int n3) throws CachingException {
        try {
            return this.getConnection().prepareStatement(s, n, n2, n3);
        }
        catch (Exception ex) {
            throw new CachingException("Unable to create prepared statement for sql: " + s, ex);
        }
    }
}
