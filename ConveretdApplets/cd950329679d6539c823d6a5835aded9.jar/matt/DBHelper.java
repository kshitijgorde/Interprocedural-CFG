// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;

public class DBHelper
{
    public static Connection getConnection() {
        try {
            final Connection conn = DriverManager.getConnection(MattProperties.getString("dburl"), MattProperties.getString("dbuser"), MattProperties.getString("dbpassword"));
            return conn;
        }
        catch (Exception e) {
            e.printStackTrace();
            Logger.log("Caould not get a database connection");
            return null;
        }
    }
    
    public static void safeClose(final Connection c, final Statement s, final ResultSet r) {
        if (r != null) {
            try {
                r.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (s != null) {
            try {
                s.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (c != null) {
            try {
                c.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    static {
        try {
            Class.forName(MattProperties.getString("dbdriver"));
        }
        catch (Exception e) {
            Logger.log("Could not load driver");
            e.printStackTrace();
        }
    }
}
