// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.Transaction;
import com.stonewall.cornerstone.entity.Entity;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.Collections;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.IPInterface;
import java.util.List;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbSession;

public class IPInterfaceDbAccess extends DbAccess
{
    static final String fetchById = "select * from ip_interface where id in $ids";
    static final String fetchByIpAddr = "select * from ip_interface where ip_address = $ip";
    static final String fetchByParent = "select * from ip_interface where parent = $parent";
    static final String fetchByParents = "select * from ip_interface where parent in $parents";
    static final String fetchAll = "select * from ip_interface";
    static final String insert = "insert into ip_interface\n(id, parent, name, identifier, traffic, ip_address, zone, fqdn)\nvalues\n($id, $parent, $name, $identifier, $traffic, $ip_address, $zone, $fqdn)";
    static final String delete = "delete from ip_interface where id = $id";
    static final String deleteByParent = "delete from ip_interface where parent = $parent";
    
    public IPInterfaceDbAccess() {
        super(DbSession.DB.main);
    }
    
    public IPInterfaceDbAccess(final String db) {
        super(db);
    }
    
    public List<IPInterface> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPInterface> l = new ArrayList<IPInterface>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_interface where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                l.add(this.build(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return l;
    }
    
    public IPInterface fetchById(final String id) throws DbException {
        final List<IPInterface> l = this.fetchById(Collections.singletonList(id));
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }
    
    public IPInterface fetchByIpAddr(final IpAddr addr) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_interface where ip_address = $ip");
            stmt.setString("ip", addr.toString());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<IPInterface> fetchAll() throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPInterface> list = new ArrayList<IPInterface>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_interface");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public List<IPInterface> fetchByParent(final String parent) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPInterface> list = new ArrayList<IPInterface>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_interface where parent = $parent");
            stmt.setString("parent", parent);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public List<IPInterface> fetchByParent(final Collection<String> parents) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPInterface> list = new ArrayList<IPInterface>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_interface where parent in $parents");
            stmt.setString("parents", parents);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public void insert(final IPInterface intf) throws DbException {
        this.insert(Collections.singletonList(intf));
    }
    
    public void insert(final List<IPInterface> intfs) throws DbException {
        final DbSession session = this.getDbSession();
        session.setAutoCommit(false);
        try {
            final DbStatement stmt = session.createStatement("insert into ip_interface\n(id, parent, name, identifier, traffic, ip_address, zone, fqdn)\nvalues\n($id, $parent, $name, $identifier, $traffic, $ip_address, $zone, $fqdn)");
            for (final IPInterface intf : intfs) {
                stmt.set(DbStatement.Mode.Update);
                stmt.setString("id", intf.getId());
                stmt.setString("parent", intf.getParentId());
                stmt.setString("name", intf.getName());
                stmt.setString("identifier", intf.getIdentifier());
                stmt.setString("ip_address", intf.getIpAddress().toString());
                stmt.setString("traffic", String.valueOf(intf.isTraffic()));
                stmt.setString("zone", intf.getZone());
                stmt.setString("fqdn", intf.getFQDN());
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
    
    public void delete(final IPInterface intf) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            new SegmentDbAccess().deleteByEndpoint(intf);
            final DbStatement stmt = session.createStatement("delete from ip_interface where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", intf.getId());
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void deleteByParent(final String parent) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            new SecurityTunnelDbAccess().deleteByEndpoint(this.fetchByParent(parent));
            new SegmentDbAccess().deleteByEndpoint(this.fetchByParent(parent));
            final DbStatement stmt = session.createStatement("delete from ip_interface where parent = $parent");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("parent", parent);
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void replaceForParent(final String parent, final List<IPInterface> intfs) throws DbException {
        final Transaction tran = new Transaction();
        tran.begin();
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("delete from ip_interface where parent = $parent");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("parent", parent);
            stmt.execute();
            this.insert(intfs);
            tran.commit();
        }
        catch (DbException e) {
            tran.rollback();
            throw e;
        }
    }
    
    private IPInterface build(final DbResultSet result) throws DbException {
        final IPInterface d = new IPInterface();
        d.setId(result.getString("id"));
        d.setParentId(result.getString("parent"));
        try {
            final IpAddr addr = new IpAddr(result.getString("ip_address"));
            d.setIpAddress(addr);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
        d.setTraffic(Boolean.parseBoolean(result.getString("traffic")));
        d.setZone(result.getString("zone"));
        d.setFQDN(result.getString("fqdn"));
        d.setIdentifier(result.getString("identifier"));
        d.setName(result.getString("name"));
        return d;
    }
}
