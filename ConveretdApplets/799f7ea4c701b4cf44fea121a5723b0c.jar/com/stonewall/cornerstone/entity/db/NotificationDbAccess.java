// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Notification;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;

public class NotificationDbAccess extends DbAccess
{
    static final String fetchById = "select * from notification where id = $id";
    static final String fetchAll = "select * from notification";
    static final String fetchAllStubs = "select id from notification";
    static final String insert = "insert into notification\n(id,\ncontent) \nvalues \n($id, $content)";
    private static String update;
    static final String delete = "delete from notification where id = $id";
    
    static {
        NotificationDbAccess.update = "update notification set\ncontent = $content \nwhere id = $id";
    }
    
    public NotificationDbAccess() {
        super(DbSession.DB.main);
    }
    
    public List<Notification> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Notification> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Notification> notifications = new ArrayList<Notification>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from notification");
            }
            else {
                stmt = session.createStatement("select * from notification");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Notification t = this.build(result, stub);
                notifications.add(t);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return notifications;
    }
    
    public Notification fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Notification fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final Notification t = new Notification(id);
                t.trim();
                return t;
            }
            final DbStatement stmt = session.createStatement("select * from notification where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final Notification t2 = this.build(result, stub);
                return t2;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public void insert(final Notification notification) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into notification\n(id,\ncontent) \nvalues \n($id, $content)");
            stmt.setString("id", notification.getId());
            stmt.setString("content", notification.getRoot().cloneTree());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final Notification notification) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement(NotificationDbAccess.update);
            stmt.setString("id", notification.getId());
            stmt.setString("content", notification.getRoot().cloneTree());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Notification notification) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from notification where id = $id");
            stmt.setString("id", notification.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Notification build(final DbResultSet result, final boolean stub) throws DbException {
        final Notification t = new Notification(result.getElement("content"));
        if (stub) {
            t.trim();
            return t;
        }
        return t;
    }
}
