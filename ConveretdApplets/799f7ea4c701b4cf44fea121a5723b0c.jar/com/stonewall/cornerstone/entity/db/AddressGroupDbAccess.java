// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.Entity;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.EntityReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.AddressGroup;
import com.stonewall.cornerstone.entity.Label;

public class AddressGroupDbAccess extends DbAccess
{
    static final String fetchAll = "select * from address_group ";
    static final String fetchById = "select * from address_group where id = $id";
    static final String fetchAllStubs = "select id from address_group ";
    static final String fetchStubsById = "select id from address_group where id = $id";
    static final String fetchByName = "select * from address_group where name = $name";
    static final String fetchByIds = "select * from address_group a where a.id in $ids";
    static final String exists = "select id from address_group where id = $id";
    static final String fetchEntries = "select * from address_group_entry where grp = $grpId";
    static final String fetchName = "select id, name from address_group a where a.id in $ids";
    static final String countByName = "select count(name) from address_group where a.name = $name";
    static final String insert = "insert into address_group\n(id, name, description)\nvalues\n($id, $name, $description)";
    static final String update = "update address_group set\nname = $name, \ndescription = $description\nwhere id = $id";
    static final String delete = "delete from address_group where id = $id \n";
    static final String insertEntries = "insert into address_group_entry\n(entity, grp)\nvalues\n($entity, $grp)";
    static final String deleteEntries = "delete from address_group_entry where grp = $grp\n";
    static final String fetchByReferenceIds = "select * from address_group where id in \n  (select distinct(grp) from address_group_entry where entity in $ids)";
    
    public AddressGroupDbAccess() {
    }
    
    public AddressGroupDbAccess(final Label label) {
        super(label);
    }
    
    public AddressGroupDbAccess(final String label) {
        super(label);
    }
    
    public AddressGroup fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public AddressGroup fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from address_group where id = $id");
            }
            else {
                stmt = session.createStatement("select * from address_group where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final AddressGroup group = this.buildAG(result, stub);
                return group;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public AddressGroup fetchByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from address_group where name = $name");
            stmt.setString("name", name);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final AddressGroup group = this.buildAG(result, false);
                return group;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<AddressGroup> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<AddressGroup> groups = new ArrayList<AddressGroup>();
        try {
            final DbStatement stmt = session.createStatement("select * from address_group a where a.id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressGroup group = this.buildAG(result, false);
                groups.add(group);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return groups;
    }
    
    public List<AddressGroup> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<AddressGroup> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<AddressGroup> groups = new ArrayList<AddressGroup>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from address_group ");
            }
            else {
                stmt = session.createStatement("select * from address_group ");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressGroup group = this.buildAG(result, stub);
                groups.add(group);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return groups;
    }
    
    public Collection<AddressGroup> fetchByReferences(final Collection<EntityReference> refs) throws DbException {
        final DbSession session = this.getDbSession();
        final List<AddressGroup> groups = new ArrayList<AddressGroup>();
        try {
            final DbStatement stmt = session.createStatement("select * from address_group where id in \n  (select distinct(grp) from address_group_entry where entity in $ids)");
            final List<String> encoded = new ArrayList<String>();
            for (final EntityReference ref : refs) {
                encoded.add(ref.getEncoded());
            }
            stmt.setString("ids", encoded);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressGroup group = this.buildAG(result, false);
                groups.add(group);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return groups;
    }
    
    public Collection<String> fetchIdsByReferences(final Collection<EntityReference> refs) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> ids = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select * from address_group where id in \n  (select distinct(grp) from address_group_entry where entity in $ids)");
            final List<String> encoded = new ArrayList<String>();
            for (final EntityReference ref : refs) {
                encoded.add(ref.getEncoded());
            }
            stmt.setString("ids", encoded);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                ids.add(result.getString("id"));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return ids;
    }
    
    public List<AddressGroup> fetchName(final Collection<String> ids) throws DbException {
        final DbSession db = this.getDbSession();
        final List<AddressGroup> ags = new ArrayList<AddressGroup>();
        try {
            final DbStatement stmt = db.createStatement("select id, name from address_group a where a.id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressGroup ag = new AddressGroup(result.getString("id"));
                final String name = result.getString("name");
                if (name != null) {
                    ag.setName(name);
                }
                ags.add(ag);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return ags;
    }
    
    public boolean exists(final String id) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select id from address_group where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return true;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return false;
    }
    
    private void fetchEntries(final AddressGroup group) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from address_group_entry where grp = $grpId");
            stmt.setString("grpId", group.getId());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                group.addPart(new EntityReference(result.getString("entity")));
            }
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    private void insertEntries(final AddressGroup group) throws DbException {
        final DbSession session = this.getDbSession();
        session.setAutoCommit(false);
        try {
            final DbStatement stmt = session.createStatement("insert into address_group_entry\n(entity, grp)\nvalues\n($entity, $grp)");
            for (final EntityReference entry : group.getParts()) {
                stmt.setString("entity", entry.getEncoded());
                stmt.setString("grp", group.getId());
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
    
    private void deleteEntries(final AddressGroup group) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("delete from address_group_entry where grp = $grp\n");
            stmt.setString("grp", group.getId());
            stmt.execute();
            stmt.close();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void cacheById(final EntityCache cache, final Collection<String> ids) throws Exception {
        final List<AddressGroup> groups = this.fetchById(ids);
        cache.cache(groups);
    }
    
    public int countByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        final int count = 0;
        try {
            final DbStatement stmt = session.createStatement("select count(name) from address_group where a.name = $name");
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
    
    public void insert(final AddressGroup group) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into address_group\n(id, name, description)\nvalues\n($id, $name, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", group.getId());
            stmt.setString("name", group.getName());
            stmt.setString("description", group.getDescription());
            stmt.execute();
            this.insertEntries(group);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final AddressGroup group) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update address_group set\nname = $name, \ndescription = $description\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", group.getId());
            stmt.setString("name", group.getName());
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
    
    public void delete(final AddressGroup group) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from address_group where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", group.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private AddressGroup buildAG(final DbResultSet result, final boolean stub) throws DbException {
        final AddressGroup group = new AddressGroup();
        group.setId(result.getString("id"));
        if (stub) {
            group.trim();
            return group;
        }
        group.setName(result.getString("name"));
        group.setDescription(result.getString("description"));
        this.fetchEntries(group);
        return group;
    }
}
