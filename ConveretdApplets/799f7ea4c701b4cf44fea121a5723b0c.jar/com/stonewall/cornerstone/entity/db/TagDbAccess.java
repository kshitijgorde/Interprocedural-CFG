// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import com.stonewall.cornerstone.db.QueryBuilder;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Tag;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.entity.Label;

public class TagDbAccess extends DbAccess
{
    static final String fetchById = "select * from tag where id = $id";
    static final String fetchAll = "select * from tag";
    static final String fetchAllStubs = "select id from tag";
    static final String fetchByNames = "select * from tag where name in $names";
    static final String insert = "insert into tag\n(id,\nname,\ncategory,\nmutable, \nicon) \nvalues\n($id,\n$name,\n$category,\n$mutable, \n$icon)";
    private static String update;
    static final String delete = "delete from tag where id = $id";
    static final String fetchByEntity = "select * from tag where id in (select distinct(tag) from entity_tag where entity = $entity)";
    static final String insertEntityTag = "insert into entity_tag \n(tag, entity )\nvalues $values";
    static final String entityTagValues = "($tag, $entity )";
    static final String deleteByEntity = "delete from entity_tag where entity = $entity";
    static final String[] enclosures;
    
    static {
        TagDbAccess.update = "update tag set\nname = $name,\ncategory = $category, \nicon = $icon \nwhere id = $id";
        enclosures = new String[] { "", ",\n", "" };
    }
    
    public TagDbAccess() {
    }
    
    public TagDbAccess(final Label label) {
        super(label);
    }
    
    public TagDbAccess(final String db) {
        super(db);
    }
    
    public TagDbAccess(final DbSession.DB db) {
        super(db);
    }
    
    public List<Tag> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Tag> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Tag> tags = new ArrayList<Tag>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from tag");
            }
            else {
                stmt = session.createStatement("select * from tag");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Tag t = this.build(result, stub);
                tags.add(t);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return tags;
    }
    
    public Tag fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Tag fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final Tag t = new Tag(id);
                t.trim();
                return t;
            }
            final DbStatement stmt = session.createStatement("select * from tag where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final Tag t2 = this.build(result, stub);
                return t2;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<Tag> fetchByNames(final List<String> names) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Tag> tags = new ArrayList<Tag>();
        try {
            final DbStatement stmt = session.createStatement("select * from tag where name in $names");
            stmt.setString("names", names);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Tag t = this.build(result, false);
                tags.add(t);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return tags;
    }
    
    public void insert(final Tag tag) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into tag\n(id,\nname,\ncategory,\nmutable, \nicon) \nvalues\n($id,\n$name,\n$category,\n$mutable, \n$icon)");
            stmt.setString("id", tag.getId());
            stmt.setString("name", tag.getName());
            stmt.setString("category", tag.getCategory().name());
            stmt.setString("mutable", String.valueOf(tag.isMutable()));
            stmt.setString("icon", tag.getIcon());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final Tag tag) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement(TagDbAccess.update);
            stmt.setString("id", tag.getId());
            stmt.setString("name", tag.getName());
            stmt.setString("category", tag.getCategory().name());
            stmt.setString("icon", tag.getIcon());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Tag tag) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from tag where id = $id");
            stmt.setString("id", tag.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public List<Tag> fetchByEntity(final String entity) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Tag> tags = new ArrayList<Tag>();
        try {
            final DbStatement stmt = session.createStatement("select * from tag where id in (select distinct(tag) from entity_tag where entity = $entity)");
            stmt.setString("entity", entity);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Tag t = this.build(result, false);
                tags.add(t);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return tags;
    }
    
    public void insert(final List<Tag> tags, final String entity) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final List<String> values = new ArrayList<String>();
            final QueryBuilder query = session.queryBuilder("($tag, $entity )");
            for (final Tag tag : tags) {
                values.add(this.buildEntityTagValues(query, tag, entity));
            }
            if (values.size() > 0) {
                final DbStatement stmt = session.createStatement("insert into entity_tag \n(tag, entity )\nvalues $values");
                stmt.set(DbStatement.Mode.Update);
                stmt.queryBuilder().collectionEnclosure(TagDbAccess.enclosures);
                stmt.set("values", values);
                stmt.execute();
            }
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void deleteByEntity(final String entity) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("delete from entity_tag where entity = $entity");
            stmt.setString("entity", entity);
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    private String buildEntityTagValues(final QueryBuilder query, final Tag tag, final String entity) {
        query.setString("tag", tag.getId());
        query.setString("entity", entity);
        return query.toString();
    }
    
    private Tag build(final DbResultSet result, final boolean stub) throws DbException {
        final Tag t = new Tag(result.getString("id"));
        if (stub) {
            t.trim();
            return t;
        }
        t.setName(result.getString("name"));
        t.setCategory(Tag.Category.valueOf(result.getString("category")));
        t.setIcon(result.getString("icon"));
        t.setMutable(result.getBoolean("mutable"));
        return t;
    }
}
