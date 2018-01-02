// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbSessionFactory;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.db.DbSession;
import org.xmodel.log.Log;

public abstract class DbAccess
{
    private String db;
    public static final Log log;
    
    static {
        log = Log.getLog(DbAccess.class);
    }
    
    public DbAccess() {
        this(DbSession.DB.latest);
    }
    
    public DbAccess(final DbSession.DB database) {
        this(database.name());
    }
    
    public DbAccess(final Label label) {
        if (label == null) {
            this.db = Label.latest.getDatabase();
        }
        else {
            this.db = label.getDatabase();
        }
    }
    
    DbAccess(final String db) {
        this.db = db;
    }
    
    public DbSession getDbSession() throws DbException {
        final DbSession session = DbSessionFactory.getConnection();
        session.setDatabase(this.db);
        return session;
    }
    
    protected String getDb() {
        return this.db;
    }
}
