// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.PreparedStatement;

public class BlobAccess
{
    private PreparedStatement A;
    
    public BlobAccess(final PreparedStatement a) {
        this.A = a;
    }
    
    public Blob getBlob() throws SQLException {
        final ResultSet executeQuery = this.A.executeQuery();
        executeQuery.first();
        return executeQuery.getBlob(1);
    }
}
