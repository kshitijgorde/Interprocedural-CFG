// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Site;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class SiteDbAccess extends DbAccess
{
    static final String fetchAll = "select * from site order by name";
    static final String fetchById = "select * from site where id = $id";
    static final String fetchAllStubs = "select id, parent, pending from site order by name";
    static final String fetchStubById = "select id, parent, pending from site where id = $id";
    static final String insert = "insert into site \n(id, name, pending, parent, description) \nvalues\n($id, $name, $pending, $parent, $description)";
    static final String update = "update site set \nname = $name, \ndescription = $description \nwhere id = $id";
    static final String delete = "delete from site where id = $id";
    static final String updatePending = "update site set \npending = $pending \nwhere id in $ids";
    
    public SiteDbAccess() {
    }
    
    public SiteDbAccess(final Label label) {
        super(label);
    }
    
    public SiteDbAccess(final String label) {
        super(label);
    }
    
    public List<Site> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Site> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Site> sites = new ArrayList<Site>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, parent, pending from site order by name");
            }
            else {
                stmt = session.createStatement("select * from site order by name");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                sites.add(this.build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return sites;
    }
    
    public Site fetchSiteTree(final String id) throws DbException {
        final DbSession db = this.getDbSession();
        final Map<String, Site> map = new HashMap<String, Site>();
        try {
            final DbStatement stmt = db.createStatement("select * from site order by name");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Site site = this.build(result, false);
                map.put(site.getId(), site);
            }
            final Iterator<Site> iterator = map.values().iterator();
            while (iterator.hasNext()) {
                final Site site = iterator.next();
                final String parentId = site.getParentId();
                if (parentId != null) {
                    final Site parent = map.get(parentId);
                    parent.addChild(site);
                }
            }
        }
        finally {
            db.close();
        }
        db.close();
        return map.get(id);
    }
    
    public Site fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Site fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, parent, pending from site where id = $id");
            }
            else {
                stmt = session.createStatement("select * from site where id = $id");
            }
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
    
    public void insert(final Site site) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into site \n(id, name, pending, parent, description) \nvalues\n($id, $name, $pending, $parent, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", site.getId());
            stmt.setString("name", site.getName());
            stmt.setString("parent", site.getParentId());
            stmt.setString("description", site.getDescription());
            stmt.setString("pending", String.valueOf(site.isPending()));
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final Site site) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update site set \nname = $name, \ndescription = $description \nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", site.getId());
            stmt.setString("name", site.getName());
            stmt.setString("description", site.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void updatePending(final Collection<String> siteIds, final boolean flag) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update site set \npending = $pending \nwhere id in $ids");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("ids", siteIds);
            stmt.setString("pending", String.valueOf(flag));
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void clearPendingStatus(final String siteId) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update site set \npending = $pending \nwhere id in $ids");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("ids", Collections.singletonList(siteId));
            stmt.setString("pending", "false");
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Site site) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from site where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", site.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Site build(final DbResultSet result, final boolean stub) throws DbException {
        final Site site = new Site(result.getString("id"));
        final String parent = result.getString("parent");
        if (parent != null) {
            site.setParentId(parent);
        }
        site.setPending(Boolean.parseBoolean(result.getString("pending")));
        if (stub) {
            site.trim();
            return site;
        }
        site.setName(result.getString("name"));
        site.setDescription(result.getString("description"));
        return site;
    }
}
