// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io.sql;

import java.sql.SQLException;
import java.sql.ResultSet;
import prefuse.data.Table;

public interface SQLDataHandler
{
    void process(final Table p0, final int p1, final ResultSet p2, final int p3) throws SQLException;
    
    Class getDataType(final String p0, final int p1);
}
