// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.XDate;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.Job;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.log.Log;

public class JobDbAccess extends DbAccess
{
    static final Log log;
    static final String insert = "insert into job\n(id, type, persistent, status, created, userid, transaction, tasks)\nvalues\n($id, $type, $persistent, $status, $created, $userid, $transaction, $tasks)";
    static final String update = "update job set\nstatus = $status,\ntransaction = $transaction,tasks = $tasks\nwhere id = $id";
    static final String delete = "delete from job where id = $id";
    static final String fetchById = "select * from job where id = $id";
    static final String fetchForRestart = "select * from job\nwhere status in ('created', 'open') and\npersistent = 'true'";
    static final String deleteNonPersistent = "delete from job where persistent = 'false'";
    
    static {
        log = Log.getLog(Entity.class);
    }
    
    public JobDbAccess() {
        super(DbSession.DB.main);
    }
    
    public void insert(final Job jb) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into job\n(id, type, persistent, status, created, userid, transaction, tasks)\nvalues\n($id, $type, $persistent, $status, $created, $userid, $transaction, $tasks)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", jb.getId());
            stmt.setString("type", jb.getType().name());
            stmt.setString("persistent", String.valueOf(jb.getPersistent()));
            stmt.setString("status", jb.getStatus().name());
            stmt.setString("created", XDate.parse(jb.getCreated()));
            stmt.setString("userid", jb.getUserid());
            stmt.setString("transaction", jb.getTransaction());
            if (jb.getPersistent()) {
                stmt.setString("tasks", jb.getChildren());
            }
            else {
                stmt.set("tasks", "null");
            }
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final String id) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from job where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", id);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void replace(final Job jb) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update job set\nstatus = $status,\ntransaction = $transaction,tasks = $tasks\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", jb.getId());
            stmt.setString("status", jb.getStatus().name());
            stmt.setString("transaction", jb.getTransaction());
            if (jb.getPersistent()) {
                stmt.setString("tasks", jb.getChildren());
            }
            else {
                stmt.set("tasks", "null");
            }
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void silentReplace(final Job jb) {
        try {
            this.replace(jb);
        }
        catch (DbException e) {
            JobDbAccess.log.error(jb.getId(), e);
        }
    }
    
    public Job fetchById(final String id) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select * from job where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return null;
    }
    
    public List<Job> fetchForRestart() throws DbException {
        final DbSession db = this.getDbSession();
        final List<Job> list = new ArrayList<Job>();
        try {
            final DbStatement stmt = db.createStatement("select * from job\nwhere status in ('created', 'open') and\npersistent = 'true'");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<Job> deleteNonPersistent() throws DbException {
        final DbSession db = this.getDbSession();
        final List<Job> list = new ArrayList<Job>();
        try {
            final DbStatement stmt = db.createStatement("delete from job where persistent = 'false'");
            final DbResultSet result = stmt.execute();
            if (result != null) {
                while (result.next()) {
                    JobDbAccess.log.info("NonPersistent job: " + result.getString() + " - deleted.");
                }
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public void delete(final Job jb) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from job where id = $id");
            stmt.setString("id", jb.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void silentDelete(final Job jb) {
        try {
            this.delete(jb);
        }
        catch (Exception e) {
            JobDbAccess.log.error(jb.getId(), e);
        }
    }
    
    private Job build(final DbResultSet result) throws DbException {
        final String type = result.getString("type");
        final Job jb = new Job(Job.Type.valueOf(type));
        jb.setId(result.getString("id"));
        jb.setPersistent(Boolean.parseBoolean(result.getString("persistent")));
        jb.setStatus(Job.Status.valueOf(result.getString("status")));
        jb.setCreated(XDate.dateTime(result.getDate("created")));
        jb.setUserid(result.getString("userid"));
        jb.setTransaction(result.getString("transaction"));
        final IModelObject tasks = result.getElement("tasks");
        if (tasks != null) {
            jb.setChildren(tasks);
        }
        return jb;
    }
}
