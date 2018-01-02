// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io.sql;

import java.sql.SQLException;
import java.sql.ResultSet;
import prefuse.data.Table;
import java.util.HashMap;

public class CompositeSQLDataHandler implements SQLDataHandler
{
    private SQLDataHandler m_default;
    private HashMap m_overrides;
    
    public CompositeSQLDataHandler() {
        this(new DefaultSQLDataHandler());
    }
    
    public CompositeSQLDataHandler(final SQLDataHandler default1) {
        this.m_default = default1;
    }
    
    public void addHandler(final String s, final SQLDataHandler sqlDataHandler) {
        if (this.m_overrides == null) {
            this.m_overrides = new HashMap(3);
        }
        this.m_overrides.put(s, sqlDataHandler);
    }
    
    public boolean removeHandler(final String s) {
        return this.m_overrides != null && this.m_overrides.remove(s) != null;
    }
    
    public void process(final Table table, final int n, final ResultSet set, final int n2) throws SQLException {
        SQLDataHandler default1 = this.m_default;
        if (this.m_overrides != null && this.m_overrides.size() > 0) {
            final SQLDataHandler sqlDataHandler = this.m_overrides.get(set.getMetaData().getColumnName(n2));
            if (sqlDataHandler != null) {
                default1 = sqlDataHandler;
            }
        }
        default1.process(table, n, set, n2);
    }
    
    public Class getDataType(final String s, final int n) {
        SQLDataHandler default1 = this.m_default;
        if (this.m_overrides != null && this.m_overrides.size() > 0) {
            final SQLDataHandler sqlDataHandler = this.m_overrides.get(s);
            if (sqlDataHandler != null) {
                default1 = sqlDataHandler;
            }
        }
        return default1.getDataType(s, n);
    }
}
