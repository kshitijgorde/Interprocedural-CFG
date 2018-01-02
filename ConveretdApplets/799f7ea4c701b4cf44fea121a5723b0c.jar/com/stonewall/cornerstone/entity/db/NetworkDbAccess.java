// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Network;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class NetworkDbAccess extends DbAccess
{
    static final String fetchAll = "select * from network order by name";
    static final String fetchAllStubs = "select id, parent from network order by name";
    static final String fetchById = "select * from network where id = $id";
    static final String fetchStubById = "select id, parent from network where id = $id";
    static final String fetchByIds = "select * from network where id in $ids";
    static final String fetchByParentIds = "select * from network where parent in $parentIds";
    static final String fetchByIp = "select * from network where ip_address = $ip_address";
    static final String fetchByName = "select * from network where name = $name";
    static final String insertWithAddr = "insert into network\n(id, managed, public, toplevel, name, ip_address, parent, tags)\nvalues\n($id, $managed, $public, $toplevel, $name, $ip_address, $parent, $tags)";
    static final String insertWithoutAddr = "insert into network\n(id, managed, public, toplevel, name, parent, tags)\nvalues\n($id, $managed, $public, $toplevel, $name, $parent, $tags)";
    static final String update = "update network set\nip_address = $ip_address,\nname = $name,\ntags = $tags\nwhere id = $id";
    static final String reparent = "update network set\nparent = $parent,\ntoplevel = $toplevel\nwhere id = $id";
    static final String delete = "delete from network where id = $id \n";
    static final String fetchForSelection = "select id, name, ip_address from network";
    static final String fetchNameAndIP = "select id, name, ip_address from network where id in $ids";
    static final String isNameUnique = "select count(id) from network where parent = $parent and name = $name";
    static final String isIpUnique = "select count(id) from network where parent = $parent and ip_address = $address";
    
    public NetworkDbAccess() {
    }
    
    public NetworkDbAccess(final Label label) {
        super(label);
    }
    
    public NetworkDbAccess(final String label) {
        super(label);
    }
    
    public List<Network> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Network> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Network> list = new ArrayList<Network>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, parent from network order by name");
            }
            else {
                stmt = db.createStatement("select * from network order by name");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result, stub));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public Network fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Network fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, parent from network where id = $id");
            }
            else {
                stmt = session.createStatement("select * from network where id = $id");
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
    
    public List<Network> fetchTreeById(final String id) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Network> networks = new ArrayList<Network>();
        try {
            final DbStatement stmt = session.createStatement("select * from network where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final Network n = this.build(result, false);
                networks.add(n);
                final List<String> parentIds = new ArrayList<String>();
                parentIds.add(n.getId());
                networks.addAll(this.fetchChildren(session, parentIds));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return networks;
    }
    
    List<Network> fetchChildren(final DbSession session, List<String> parentIds) throws DbException {
        final List<Network> children = new ArrayList<Network>();
        final DbStatement stmt = session.createStatement("select * from network where parent in $parentIds");
        stmt.setString("parentIds", parentIds);
        final DbResultSet result = stmt.execute();
        while (result.next()) {
            children.add(this.build(result, false));
        }
        if (children.isEmpty()) {
            return Collections.emptyList();
        }
        for (final Network child : children) {
            parentIds = new ArrayList<String>();
            parentIds.add(child.getId());
        }
        children.addAll(this.fetchChildren(session, parentIds));
        return children;
    }
    
    public List<Network> fetchById(final Collection<String> list) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Network> resultList = new ArrayList<Network>();
        try {
            final DbStatement stmt = session.createStatement("select * from network where id in $ids");
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
    
    public Network fetchByIP(final IpAddr addr) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from network where ip_address = $ip_address");
            stmt.setString("ip_address", addr.toString());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, false);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public Network fetchByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from network where name = $name");
            stmt.setString("name", name);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, false);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public void insert(final Network network) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = null;
            final IpAddr addr = network.getIpAddress();
            if (addr != null) {
                stmt = db.createStatement("insert into network\n(id, managed, public, toplevel, name, ip_address, parent, tags)\nvalues\n($id, $managed, $public, $toplevel, $name, $ip_address, $parent, $tags)");
                stmt.setString("ip_address", addr.toString());
            }
            else {
                stmt = db.createStatement("insert into network\n(id, managed, public, toplevel, name, parent, tags)\nvalues\n($id, $managed, $public, $toplevel, $name, $parent, $tags)");
            }
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", network.getId());
            stmt.setString("managed", String.valueOf(network.isManaged()));
            stmt.setString("public", String.valueOf(network.isPublic()));
            stmt.setString("toplevel", String.valueOf(network.isToplevel()));
            stmt.setString("name", network.getName());
            stmt.setString("parent", network.getParentId());
            stmt.setString("tags", network.getTagsRoot().cloneTree());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(this.getDb());
            tagAccess.insert(network.getTags(), network.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final Network network) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update network set\nip_address = $ip_address,\nname = $name,\ntags = $tags\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", network.getId());
            stmt.setString("name", network.getName());
            final IpAddr addr = network.getIpAddress();
            if (addr != null) {
                stmt.setString("ip_address", addr.toString());
            }
            stmt.setString("tags", network.getTagsRoot().cloneTree());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(this.getDb());
            tagAccess.deleteByEntity(network.getId());
            tagAccess.insert(network.getTags(), network.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void reparent(final Network network, final String parentId) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update network set\nparent = $parent,\ntoplevel = $toplevel\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", network.getId());
            stmt.setString("parent", parentId);
            stmt.setString("toplevel", String.valueOf(network.isToplevel()));
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Network network) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            new SegmentDbAccess(this.getDb()).deleteByEndpoint(network);
            final DbStatement stmt = db.createStatement("delete from network where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", network.getId());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(this.getDb());
            tagAccess.deleteByEntity(network.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Network build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final Network network = new Network(result.getString("id"));
            final String pId = result.getString("parent");
            if (pId != null) {
                network.setParentId(pId);
            }
            network.trim();
            return network;
        }
        final Network network = new Network(result.getString("id"));
        network.setManaged(Boolean.parseBoolean(result.getString("managed")));
        network.setPublic(Boolean.parseBoolean(result.getString("public")));
        network.setTopLevel(Boolean.parseBoolean(result.getString("toplevel")));
        final String name = result.getString("name");
        if (name != null && !name.equals("")) {
            network.setName(name);
        }
        try {
            if (!network.isInternet()) {
                network.setIpAddress(result.getString("ip_address"));
            }
        }
        catch (Exception ex) {}
        final String pId2 = result.getString("parent");
        if (pId2 != null) {
            network.setParentId(pId2);
        }
        network.setTagsRoot(result.getElement("tags"));
        return network;
    }
    
    public List<Network> fetchForSelection() throws DbException {
        final DbSession db = this.getDbSession();
        final List<Network> list = new ArrayList<Network>();
        try {
            final DbStatement stmt = db.createStatement("select id, name, ip_address from network");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Network n = new Network(result.getString("id"));
                final String name = result.getString("name");
                if (name != null) {
                    n.setName(name);
                }
                n.setIpAddress(result.getString("ip_address"));
                list.add(n);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<Network> fetchNameAndIP(final Collection<String> ids) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Network> networks = new ArrayList<Network>();
        try {
            final DbStatement stmt = db.createStatement("select id, name, ip_address from network where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Network n = new Network(result.getString("id"));
                final String name = result.getString("name");
                if (name != null) {
                    n.setName(name);
                }
                n.setIpAddress(result.getString("ip_address"));
                networks.add(n);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return networks;
    }
    
    public Network fetchNameAndIP(final String id) throws DbException {
        final List<Network> nets = this.fetchNameAndIP(Collections.singleton(id));
        if (nets.isEmpty()) {
            return null;
        }
        return nets.get(0);
    }
    
    public boolean isNameUnique(final String parentId, final Network network) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select count(id) from network where parent = $parent and name = $name");
            stmt.setString("parent", parentId);
            stmt.setString("name", network.getName());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger() <= 0;
            }
        }
        finally {
            db.close();
        }
        db.close();
        return true;
    }
    
    public boolean isIpUnique(final String parentId, final Network network) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select count(id) from network where parent = $parent and ip_address = $address");
            stmt.setString("parent", parentId);
            final IpAddr addr = network.getIpAddress();
            stmt.setString("address", addr.toString());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger() <= 0;
            }
        }
        finally {
            db.close();
        }
        db.close();
        return true;
    }
}
