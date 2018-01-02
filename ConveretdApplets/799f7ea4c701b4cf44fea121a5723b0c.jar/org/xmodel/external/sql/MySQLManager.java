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

public class MySQLManager implements SQLManager
{
    private static Map<String, JDCConnectionDriver> A;
    private boolean E;
    private String D;
    private String B;
    private String C;
    
    public MySQLManager() {
        MySQLManager.A = new HashMap<String, JDCConnectionDriver>();
    }
    
    @Override
    public void configure(final IModelObject modelObject) throws CachingException {
        final String childGet = Xlate.childGet(modelObject, "database", (String)null);
        if (childGet == null) {
            throw new CachingException("Database not defined in annotation: " + modelObject);
        }
        this.D = "jdbc:mysql://127.0.0.1/" + childGet;
        this.B = Xlate.childGet(modelObject, "login", (String)null);
        if (childGet == null) {
            throw new CachingException("Login not defined in annotation: " + modelObject);
        }
        this.C = Xlate.childGet(modelObject, "password", (String)null);
        if (childGet == null) {
            throw new CachingException("Password not defined in annotation: " + modelObject);
        }
    }
    
    @Override
    public Connection getConnection() throws CachingException {
        try {
            if (!this.E) {
                this.E = true;
                Class.forName("com.mysql.jdbc.Driver");
            }
            synchronized (MySQLManager.A) {
                if (MySQLManager.A.get(this.D) == null) {
                    MySQLManager.A.put(this.D, new JDCConnectionDriver("com.mysql.jdbc.Driver", this.D, this.B, this.C));
                }
            }
            // monitorexit(MySQLManager.A)
            return DriverManager.getConnection(this.D, this.B, this.C);
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
