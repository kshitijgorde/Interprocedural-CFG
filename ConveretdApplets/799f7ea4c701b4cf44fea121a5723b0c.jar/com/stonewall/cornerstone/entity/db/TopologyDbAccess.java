// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.db.DbSession;

public class TopologyDbAccess extends DbAccess
{
    static final String isAssociated = "select count(*) from segment where endpoint_a = $ref or endpoint_b = $ref";
    
    public TopologyDbAccess() {
        super(DbSession.DB.main);
    }
    
    public boolean isAssociated(final Entity entity) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select count(*) from segment where endpoint_a = $ref or endpoint_b = $ref");
            stmt.setString("ref", entity.getReference().getEncoded());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger() > 0;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return false;
    }
}
