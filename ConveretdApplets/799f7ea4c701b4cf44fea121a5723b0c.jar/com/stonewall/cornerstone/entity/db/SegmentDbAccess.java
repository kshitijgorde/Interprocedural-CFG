// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import java.util.Collections;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.Entity;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Segment;
import com.stonewall.cornerstone.db.DbSession;

public class SegmentDbAccess extends DbAccess
{
    static final String fetchAll = "select * from segment";
    static final String fetchById = "select * from segment where id = $id";
    static final String fetchAllStubs = "select id from segment";
    static final String fetchByIds = "select * from segment where id in $ids";
    static final String insert = "insert into segment (id, endpoint_a, endpoint_b) values ($id, $endpoint_a, $endpoint_b)";
    static final String delete = "delete from segment where id in $ids";
    static final String deleteByEndpoint = "delete from segment where endpoint_a = $ref or endpoint_b = $ref";
    static final String deleteByEndpoints = "delete from segment where endpoint_a in $refs or endpoint_b in $refs";
    static final String fetchByEndpoint = "select * from segment where endpoint_a = $ref or endpoint_b = $ref";
    static final String fetchByEndpoints = "select * from segment\nwhere\n(endpoint_a = $refA or endpoint_b = $refA) and\n(endpoint_a = $refB or endpoint_b = $refB)";
    static final String fetchOtherEnds = "select endpoint_a e from segment where endpoint_b = $ref\nand endpoint_a like '%$type%'\nunion\nselect endpoint_b e from segment where endpoint_a = $ref\nand endpoint_b like '%$type%'\n";
    
    public SegmentDbAccess() {
        super(DbSession.DB.main);
    }
    
    SegmentDbAccess(final String db) {
        this();
    }
    
    public Segment fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Segment fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final Segment s = new Segment(id);
                s.trim();
                return s;
            }
            final DbStatement stmt = session.createStatement("select * from segment where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<Segment> fetchById(final List<String> list) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Segment> resultList = new ArrayList<Segment>();
        try {
            final DbStatement stmt = session.createStatement("select * from segment where id in $ids");
            stmt.setString("ids", list);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                resultList.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return resultList;
    }
    
    public List<Segment> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Segment> fetchAll(final boolean stub) throws DbException {
        final List<Segment> resultList = new ArrayList<Segment>();
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from segment");
            }
            else {
                stmt = session.createStatement("select * from segment");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                resultList.add(this.build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return resultList;
    }
    
    public List<Segment> fetch(final Entity end) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Segment> segments = new ArrayList<Segment>();
        try {
            final DbStatement stmt = session.createStatement("select * from segment where endpoint_a = $ref or endpoint_b = $ref");
            stmt.setString("ref", end.getReference().getEncoded());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                segments.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return segments;
    }
    
    public List<Segment> fetch(final Entity endA, final Entity endB) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Segment> segments = new ArrayList<Segment>();
        try {
            final DbStatement stmt = session.createStatement("select * from segment\nwhere\n(endpoint_a = $refA or endpoint_b = $refA) and\n(endpoint_a = $refB or endpoint_b = $refB)");
            stmt.setString("refA", endA.getReference().getEncoded());
            stmt.setString("refB", endB.getReference().getEncoded());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                segments.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return segments;
    }
    
    public List<String> fetchOtherEnds(final Entity end, final EntityFactory.EntityType type) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> list = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select endpoint_a e from segment where endpoint_b = $ref\nand endpoint_a like '%$type%'\nunion\nselect endpoint_b e from segment where endpoint_a = $ref\nand endpoint_b like '%$type%'\n");
            stmt.setString("ref", end.getReference().getEncoded());
            stmt.set("type", type.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(new EntityReference(result.getString()).getId());
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public void insert(final Segment segment) throws DbException {
        final DbSession db = this.getDbSession();
        final EntityReference[] endpoint = segment.getEndpoints();
        try {
            final DbStatement stmt = db.createStatement("insert into segment (id, endpoint_a, endpoint_b) values ($id, $endpoint_a, $endpoint_b)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", segment.getId());
            stmt.setString("endpoint_a", endpoint[0].getEncoded());
            stmt.setString("endpoint_b", endpoint[1].getEncoded());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Segment segment) throws DbException {
        this.delete(Collections.singletonList(segment.getId()));
    }
    
    public void delete(final List<String> segments) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from segment where id in $ids");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("ids", segments);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteByEndpoint(final Entity endpoint) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from segment where endpoint_a = $ref or endpoint_b = $ref");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("ref", endpoint.getReference().getEncoded());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteByEndpoint(final List<? extends Entity> endpoints) throws DbException {
        final DbSession db = this.getDbSession();
        final List<String> refs = new ArrayList<String>(endpoints.size());
        for (final Entity e : endpoints) {
            refs.add(e.getReference().getEncoded());
        }
        try {
            final DbStatement stmt = db.createStatement("delete from segment where endpoint_a in $refs or endpoint_b in $refs");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("refs", refs);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Segment build(final DbResultSet result, final boolean stub) throws DbException {
        final Segment s = new Segment(result.getString("id"));
        if (stub) {
            s.trim();
            return s;
        }
        s.setEndpoints(new EntityReference(result.getString("endpoint_a")), new EntityReference(result.getString("endpoint_b")));
        return s;
    }
}
