// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io.sql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory
{
    public static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    public static final String DRIVER_JDBC_OBDC = "sun.jdbc.odbc.JdbcOdbcDriver";
    public static final String PROTOCOL_JDBC = "jdbc:";
    public static final String SUBPROTOCOL_MYSQL = "mysql:";
    public static final String SUBPROTOCOL_JDBC_ODBC = "odbc:";
    
    public static SQLDataHandler getDefaultHandler() {
        return new DefaultSQLDataHandler();
    }
    
    public static DatabaseDataSource getDatabaseConnection(final Connection connection, final SQLDataHandler sqlDataHandler) throws SQLException {
        return new DatabaseDataSource(connection, sqlDataHandler);
    }
    
    public static DatabaseDataSource getDatabaseConnection(final Connection connection) throws SQLException {
        return getDatabaseConnection(connection, getDefaultHandler());
    }
    
    public static DatabaseDataSource getDatabaseConnection(final String s, final String s2, final String s3, final String s4, final SQLDataHandler sqlDataHandler) throws SQLException, ClassNotFoundException {
        Class.forName(s);
        return getDatabaseConnection(DriverManager.getConnection(s2, s3, s4), sqlDataHandler);
    }
    
    public static DatabaseDataSource getDatabaseConnection(final String s, final String s2, final String s3, final String s4) throws SQLException, ClassNotFoundException {
        return getDatabaseConnection(s, s2, s3, s4, getDefaultHandler());
    }
    
    public static DatabaseDataSource getMySQLConnection(final String s, final String s2, final String s3, final String s4, final SQLDataHandler sqlDataHandler) throws SQLException, ClassNotFoundException {
        return getDatabaseConnection("com.mysql.jdbc.Driver", "jdbc:mysql://" + s + "/" + s2, s3, s4, sqlDataHandler);
    }
    
    public static DatabaseDataSource getMySQLConnection(final String s, final String s2, final String s3, final String s4) throws SQLException, ClassNotFoundException {
        return getMySQLConnection(s, s2, s3, s4, getDefaultHandler());
    }
}
