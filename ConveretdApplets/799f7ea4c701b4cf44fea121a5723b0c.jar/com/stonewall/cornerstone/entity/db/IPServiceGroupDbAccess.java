// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collection;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.IPServiceGroup;
import com.stonewall.cornerstone.entity.Label;

public class IPServiceGroupDbAccess extends DbAccess
{
    static final String fetchAll = "select * from ip_service_group";
    static final String fetchAllStubs = "select id from ip_service_group";
    static final String fetchById = "select * from ip_service_group where id = $id";
    static final String fetchByIds = "select * from ip_service_group where id in $ids ";
    static final String fetchPredefinedByGroup = "select id from ip_service_group \nwhere grp = $grp and mutable = 'false'";
    static final String fetchPredefined = "select * from ip_service_group \nwhere mutable = 'false'";
    static final String fetchByName = "select * from ip_service_group where name = $name";
    static final String countByName = "select count(name) from ip_service_group where name = $name";
    static final String insert = "insert into ip_service_group\n(id, name, mutable, grp, description)\nvalues\n($id, $name, $mutable, $grp, $description)";
    static final String update = "update ip_service_group set\nname = $name, \ngrp = $grp, \ndescription = $description\nwhere id = $id ";
    static final String delete = "delete from ip_service_group where id = $id \n";
    static final String insertEntries = "insert into ip_service_group_entry\n(id, svc_grp)\nvalues\n($id, $svc_grp)";
    static final String deleteEntries = "delete from ip_service_group_entry where svc_grp = $svc_grp\n";
    static final String fetchEntries = "select * from ip_service_group_entry where svc_grp = $svc_grp";
    
    public IPServiceGroupDbAccess() {
    }
    
    public IPServiceGroupDbAccess(final Label label) {
        super(label);
    }
    
    public IPServiceGroupDbAccess(final String label) {
        super(label);
    }
    
    public void insert(final IPServiceGroup group) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into ip_service_group\n(id, name, mutable, grp, description)\nvalues\n($id, $name, $mutable, $grp, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", group.getId());
            stmt.setString("name", group.getName());
            stmt.setString("mutable", String.valueOf(group.isMutable()));
            stmt.setString("grp", group.getGroup());
            stmt.setString("description", group.getDescription());
            stmt.execute();
            this.insertEntries(group);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final IPServiceGroup group) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update ip_service_group set\nname = $name, \ngrp = $grp, \ndescription = $description\nwhere id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", group.getId());
            stmt.setString("name", group.getName());
            stmt.setString("grp", group.getGroup());
            stmt.setString("description", group.getDescription());
            stmt.execute();
            this.deleteEntries(group);
            this.insertEntries(group);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final IPServiceGroup group) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            this.deleteEntries(group);
            final DbStatement stmt = db.createStatement("delete from ip_service_group where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", group.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private void fetchEntries(final IPServiceGroup group) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service_group_entry where svc_grp = $svc_grp");
            stmt.setString("svc_grp", group.getId());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                group.addPart(this.buildEntry(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    private void insertEntries(final IPServiceGroup group) throws DbException {
        final DbSession session = this.getDbSession();
        session.setAutoCommit(false);
        try {
            final DbStatement stmt = session.createStatement("insert into ip_service_group_entry\n(id, svc_grp)\nvalues\n($id, $svc_grp)");
            for (final EntityReference entry : group.getParts()) {
                stmt.setString("id", entry.getId());
                stmt.setString("svc_grp", group.getId());
                stmt.execute();
            }
            stmt.close();
            session.commit();
        }
        catch (DbException e) {
            session.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    private void deleteEntries(final IPServiceGroup group) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("delete from ip_service_group_entry where svc_grp = $svc_grp\n");
            stmt.setString("svc_grp", group.getId());
            stmt.execute();
            stmt.close();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public List<IPServiceGroup> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<IPServiceGroup> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPServiceGroup> groups = new ArrayList<IPServiceGroup>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from ip_service_group");
            }
            else {
                stmt = session.createStatement("select * from ip_service_group");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final IPServiceGroup group = this.build(result, stub);
                groups.add(group);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return groups;
    }
    
    public List<EntityReference> fetchPredefinedByGroup(final String group) throws DbException {
        final DbSession session = this.getDbSession();
        final List<EntityReference> services = new ArrayList<EntityReference>();
        try {
            final DbStatement stmt = session.createStatement("select id from ip_service_group \nwhere grp = $grp and mutable = 'false'");
            stmt.setString("grp", group);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                services.add(new EntityReference(EntityFactory.EntityType.ipServiceGroup, result.getString()));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return services;
    }
    
    public List<IPServiceGroup> fetchPredefined() throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPServiceGroup> groups = new ArrayList<IPServiceGroup>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service_group \nwhere mutable = 'false'");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final IPServiceGroup group = this.build(result, false);
                groups.add(group);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return groups;
    }
    
    public IPServiceGroup fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public IPServiceGroup fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final IPServiceGroup group = new IPServiceGroup(id);
                group.trim();
                return group;
            }
            final DbStatement stmt = session.createStatement("select * from ip_service_group where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final IPServiceGroup group2 = this.build(result, stub);
                return group2;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public IPServiceGroup fetchByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service_group where name = $name");
            stmt.setString("name", name);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final IPServiceGroup group = this.build(result, false);
                return group;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<IPServiceGroup> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPServiceGroup> groups = new ArrayList<IPServiceGroup>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service_group where id in $ids ");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final IPServiceGroup group = this.build(result, false);
                groups.add(group);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return groups;
    }
    
    public int countByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        final int count = 0;
        try {
            final DbStatement stmt = session.createStatement("select count(name) from ip_service_group where name = $name");
            stmt.setString("name", name);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger();
            }
        }
        finally {
            session.close();
        }
        session.close();
        return count;
    }
    
    private IPServiceGroup build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final IPServiceGroup group = new IPServiceGroup();
            group.setId(result.getString("id"));
            group.trim();
            return group;
        }
        final IPServiceGroup group = new IPServiceGroup();
        group.setId(result.getString("id"));
        group.setName(result.getString("name"));
        group.setMutable(Boolean.parseBoolean(result.getString("mutable")));
        group.setGroup(result.getString("grp"));
        group.setDescription(result.getString("description"));
        this.fetchEntries(group);
        return group;
    }
    
    private EntityReference buildEntry(final DbResultSet result) throws DbException {
        try {
            final EntityReference entry = new EntityReference(EntityFactory.EntityType.ipService, result.getString("id"));
            return entry;
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
}
