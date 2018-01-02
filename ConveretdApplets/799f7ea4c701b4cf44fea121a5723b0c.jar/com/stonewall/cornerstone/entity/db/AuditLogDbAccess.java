// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.jms.msg.event.EventFactory;
import java.util.Iterator;
import com.stonewall.cornerstone.db.DuplicateKeyException;
import com.stonewall.cornerstone.jms.msg.event.ServiceEvent;
import com.stonewall.cornerstone.jms.msg.event.Event;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.jms.msg.event.ProcessEvent;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.log.Log;

public class AuditLogDbAccess extends DbAccess
{
    static final Log log;
    static final String insertStmt = "insert into audit_log \n(id, timestamp, userid, type, action, status, correlation, subtype, content) \nvalues \n($id, $timestamp, $userid, $type, $action, $status, $correlation, $subtype, $content)";
    static final String fetchToCancel = " select * from audit_log where type = 'process' and correlation in \n   (select distinct(transaction) from job where persistent = 'false' and transaction in \n     (select distinct(correlation) from audit_log where status = 'started' and type = 'process' and correlation not in \n             (select distinct(correlation) from audit_log where type = 'process' and status in ( 'completed', 'cancelled', 'failed'))))";
    
    static {
        log = Log.getLog(Entity.class);
    }
    
    public AuditLogDbAccess() {
        super(DbSession.DB.main);
    }
    
    public List<ProcessEvent> fetchProcessesToCancel() throws DbException {
        final DbSession db = this.getDbSession();
        final List<ProcessEvent> events = new ArrayList<ProcessEvent>();
        try {
            final DbStatement stmt = db.createStatement(" select * from audit_log where type = 'process' and correlation in \n   (select distinct(transaction) from job where persistent = 'false' and transaction in \n     (select distinct(correlation) from audit_log where status = 'started' and type = 'process' and correlation not in \n             (select distinct(correlation) from audit_log where type = 'process' and status in ( 'completed', 'cancelled', 'failed'))))");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                events.add((ProcessEvent)this.build(result));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return events;
    }
    
    public void insert(final List<Event> events) throws DbException {
        final DbSession db = this.getDbSession();
        db.setAutoCommit(false);
        try {
            final DbStatement stmt = db.createStatement("insert into audit_log \n(id, timestamp, userid, type, action, status, correlation, subtype, content) \nvalues \n($id, $timestamp, $userid, $type, $action, $status, $correlation, $subtype, $content)");
            for (final Event event : events) {
                try {
                    stmt.setString("id", event.getId());
                    stmt.setString("userid", event.getUserid());
                    stmt.set("timestamp", event.getTimestamp());
                    stmt.setString("type", event.getType().name());
                    stmt.setString("correlation", event.getCorrelation());
                    stmt.setString("action", event.getAction().name());
                    stmt.setString("status", event.getStatus().name());
                    stmt.setString("subtype", "");
                    if (event instanceof ServiceEvent) {
                        stmt.setString("subtype", ((ServiceEvent)event).getSubtype().name());
                    }
                    stmt.setString("content", event.getRoot().cloneTree());
                    stmt.set(DbStatement.Mode.Update);
                    stmt.execute();
                }
                catch (DuplicateKeyException ex) {}
                catch (Exception ex2) {}
            }
            db.commit();
        }
        catch (Exception e) {
            db.rollback();
            throw new DbException(e);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public Event build(final DbResultSet result) throws DbException {
        try {
            final Event event = EventFactory.getInstance().createEvent(result.getElement("content"));
            return event;
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
}
