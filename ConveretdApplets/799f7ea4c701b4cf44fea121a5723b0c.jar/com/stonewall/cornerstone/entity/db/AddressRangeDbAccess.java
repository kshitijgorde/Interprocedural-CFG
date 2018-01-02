// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.EntityReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.AddressRange;
import com.stonewall.cornerstone.entity.Label;

public class AddressRangeDbAccess extends DbAccess
{
    static final String fetchAll = "select * from address_range ";
    static final String fetchById = "select * from address_range where id = $id";
    static final String fetchAllStubs = "select id from address_range ";
    static final String fetchStubsById = "select id from address_range where id = $id";
    static final String fetchByIds = "select * from address_range where id in $ids";
    static final String exists = "select id from address_range where id = $id";
    static final String insert = "insert into address_range\n(id, name, start, end, description)\nvalues\n($id, $name, $start, $end, $description)";
    static final String update = "update address_range set\nname = $name, \nstart = $start, \nend = $end, \ndescription = $description\nwhere id = $id";
    static final String delete = "delete from address_range where id = $id \n";
    static final String fetchByReference = "select * from address_range where start = $ref or end = $ref";
    
    public AddressRangeDbAccess() {
    }
    
    public AddressRangeDbAccess(final Label label) {
        super(label);
    }
    
    public AddressRangeDbAccess(final String label) {
        super(label);
    }
    
    public AddressRange fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public AddressRange fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from address_range where id = $id");
            }
            else {
                stmt = session.createStatement("select * from address_range where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final AddressRange range = this.build(result, stub);
                return range;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<AddressRange> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<AddressRange> ranges = new ArrayList<AddressRange>();
        try {
            final DbStatement stmt = session.createStatement("select * from address_range where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressRange range = this.build(result, false);
                ranges.add(range);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return ranges;
    }
    
    public List<AddressRange> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<AddressRange> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<AddressRange> ranges = new ArrayList<AddressRange>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from address_range ");
            }
            else {
                stmt = session.createStatement("select * from address_range ");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressRange range = this.build(result, stub);
                ranges.add(range);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return ranges;
    }
    
    public Collection<AddressRange> fetchByReference(final EntityReference ref) throws DbException {
        final DbSession session = this.getDbSession();
        final List<AddressRange> ranges = new ArrayList<AddressRange>();
        try {
            final DbStatement stmt = session.createStatement("select * from address_range where start = $ref or end = $ref");
            stmt.setString("ref", ref.getEncoded());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final AddressRange range = this.build(result, false);
                ranges.add(range);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return ranges;
    }
    
    public boolean exists(final String id) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select id from address_range where id = $id");
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
    
    public void insert(final AddressRange range) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into address_range\n(id, name, start, end, description)\nvalues\n($id, $name, $start, $end, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", range.getId());
            stmt.setString("name", range.getName());
            stmt.setString("start", range.getStart().getEncoded());
            stmt.setString("end", range.getEnd().getEncoded());
            stmt.setString("description", range.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final AddressRange range) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update address_range set\nname = $name, \nstart = $start, \nend = $end, \ndescription = $description\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", range.getId());
            stmt.setString("name", range.getName());
            stmt.setString("start", range.getStart().getEncoded());
            stmt.setString("end", range.getEnd().getEncoded());
            stmt.setString("description", range.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final AddressRange range) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from address_range where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", range.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private AddressRange build(final DbResultSet result, final boolean stub) throws DbException {
        final AddressRange range = new AddressRange();
        range.setId(result.getString("id"));
        if (stub) {
            range.trim();
            return range;
        }
        range.setName(result.getString("name"));
        range.setDescription(result.getString("description"));
        range.setStart(new EntityReference(result.getString("start")));
        range.setEnd(new EntityReference(result.getString("end")));
        return range;
    }
}
