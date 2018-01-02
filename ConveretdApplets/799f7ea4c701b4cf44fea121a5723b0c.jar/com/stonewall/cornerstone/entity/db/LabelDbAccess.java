// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Label;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;

public class LabelDbAccess extends DbAccess
{
    static final String fetchAll = "select * from label order by timestamp";
    static final String fetchAllStubs = "select id, type, timestamp from label order by timestamp";
    static final String fetchById = "select * from label where id = $id";
    static final String fetchStubById = "select id, type, timestamp from label where id = $id";
    static final String insert = "insert into label\n(id, name, timestamp, userid, scope, visible, type, description)\nvalues\n($id, $name, $timestamp, $userid, $scope, $visible, $type, $description)";
    static final String delete = "delete from label where id = $id \n";
    
    public LabelDbAccess() {
        super(DbSession.DB.main);
    }
    
    public List<Label> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Label> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Label> list = new ArrayList<Label>();
        final Label latest = Label.latest;
        if (stub) {
            latest.trim();
        }
        list.add(latest);
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, type, timestamp from label order by timestamp");
            }
            else {
                stmt = db.createStatement("select * from label order by timestamp");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Label label = this.build(result, stub);
                list.add(label);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public Label fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Label fetchById(final String id, final boolean stub) throws DbException {
        if (id.equals(DbSession.DB.latest.name())) {
            final Label label = Label.latest;
            if (stub) {
                label.trim();
            }
            return label;
        }
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, type, timestamp from label where id = $id");
            }
            else {
                stmt = session.createStatement("select * from label where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final Label label2 = this.build(result, stub);
                return label2;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public void insert(final Label label) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into label\n(id, name, timestamp, userid, scope, visible, type, description)\nvalues\n($id, $name, $timestamp, $userid, $scope, $visible, $type, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", label.getId());
            stmt.setString("name", label.getName());
            stmt.set("timestamp", label.getTimestamp());
            stmt.setString("userid", label.getUser());
            stmt.setString("description", label.getDescription());
            stmt.setString("scope", label.getScope().name());
            stmt.set("visible", label.getVisible());
            stmt.setString("type", label.getType().name());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Label label) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from label where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", label.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Label build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final Label label = new Label(result.getString("id"));
            label.setTimestamp(result.getLong("timestamp"));
            label.setType(Label.Type.valueOf(result.getString("type")));
            label.trim();
            return label;
        }
        final Label label = new Label(result.getString("id"));
        label.setName(result.getString("name"));
        label.setTimestamp(result.getLong("timestamp"));
        label.setUser(result.getString("userid"));
        label.setDescription(result.getString("description"));
        label.setScope(Label.Scope.valueOf(result.getString("scope")));
        label.setVisible(result.getBoolean("visible"));
        label.setType(Label.Type.valueOf(result.getString("type")));
        return label;
    }
}
