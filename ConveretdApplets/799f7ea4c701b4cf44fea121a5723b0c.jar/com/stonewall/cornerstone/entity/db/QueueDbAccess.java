// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;

public class QueueDbAccess extends DbAccess
{
    static final String insert = "insert into jms_queue (id, available, pool, owner) values ($id, 'true', $pool, null)";
    static final String fetchPoolCount = "select count(*) from jms_queue where pool = $pool";
    static final String nextAvailable = "select * from jms_queue where pool = $pool and available = 'true' order by id limit 1";
    static final String updateQueue = "update jms_queue set available = $available, owner = $owner where id = $id";
    
    public QueueDbAccess() {
        super(DbSession.DB.main);
    }
    
    public int fetchCount(final Pool pool) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select count(*) from jms_queue where pool = $pool");
            stmt.setString("pool", pool.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger();
            }
        }
        finally {
            db.close();
        }
        db.close();
        return 0;
    }
    
    public void insert(final Pool pool, final int qty) throws DbException {
        final DbSession db = this.getDbSession();
        db.setAutoCommit(false);
        try {
            final DbStatement stmt = db.createStatement("insert into jms_queue (id, available, pool, owner) values ($id, 'true', $pool, null)");
            for (int i = 1; i <= qty; ++i) {
                stmt.set(DbStatement.Mode.Update);
                stmt.setString("id", String.valueOf(pool.name()) + i);
                stmt.setString("pool", pool.name());
                stmt.execute();
            }
            db.commit();
        }
        catch (DbException e) {
            db.rollback();
            throw e;
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public String nextAvailable(final Pool pool, final String owner) throws DbException {
        String queue = null;
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = db.createStatement("select * from jms_queue where pool = $pool and available = 'true' order by id limit 1");
            stmt.setString("pool", pool.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                queue = result.getString();
            }
            if (queue != null) {
                stmt = db.createStatement("update jms_queue set available = $available, owner = $owner where id = $id");
                stmt.set(DbStatement.Mode.Update);
                stmt.setString("id", queue);
                stmt.setString("available", "false");
                stmt.setString("owner", owner);
                stmt.execute();
            }
        }
        catch (Exception e) {
            queue = null;
            throw new DbException(e);
        }
        finally {
            db.close();
        }
        db.close();
        return queue;
    }
    
    public void markQueueAvailable(final String id) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update jms_queue set available = $available, owner = $owner where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", id);
            stmt.set("owner", "null");
            stmt.setString("available", "true");
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public enum Pool
    {
        DM("DM", 0), 
        CP("CP", 1);
        
        private Pool(final String s, final int n) {
        }
    }
}
