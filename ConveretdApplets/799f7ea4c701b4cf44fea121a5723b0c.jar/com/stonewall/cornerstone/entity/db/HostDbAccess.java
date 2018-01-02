// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collections;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Host;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class HostDbAccess extends DbAccess
{
    static final String fetchAll = "select * from host order by name";
    static final String fetchAllStubs = "select id, network from host order by name";
    static final String fetchById = "select * from host where id = $id";
    static final String fetchStubById = "select id, network from host where id = $id";
    static final String fetchByIds = "select * from host where id in $ids";
    static final String fetchByIp = "select * from host where ip_address = $ip_address";
    static final String fetchByParent = "select * from host where network = $id";
    static final String fetchStubByParent = "select id, network from host where network = $id";
    static final String fetchByName = "select * from host where name = $name";
    static final String insert = "insert into host\n(id, name, ip_address, network, tags)\nvalues\n($id, $name, $ip_address, $network, $tags)";
    static final String update = "update host set\nip_address = $ip_address,\nname = $name,\ntags = $tags\nwhere id = $id";
    static final String updateNetwork = "update host set\nnetwork = $network\nwhere id = $id";
    static final String delete = "delete from host where id = $id \n";
    static final String fetchForSelection = "select id, name, ip_address from host";
    static final String fetchNameAndIP = "select id, name, ip_address from host where id in $ids";
    static final String isNameUnique = "select count(id) from host where network = $network and name = $name";
    static final String isIpUnique = "select count(id) from host where network = $network and ip_address = $address";
    
    public HostDbAccess() {
    }
    
    public HostDbAccess(final Label label) {
        super(label);
    }
    
    public HostDbAccess(final String label) {
        super(label);
    }
    
    public List<Host> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Host> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Host> list = new ArrayList<Host>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, network from host order by name");
            }
            else {
                stmt = db.createStatement("select * from host order by name");
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
    
    public Host fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Host fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, network from host where id = $id");
            }
            else {
                stmt = session.createStatement("select * from host where id = $id");
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
    
    public List<Host> fetchById(final Collection<String> list) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Host> resultList = new ArrayList<Host>();
        try {
            final DbStatement stmt = session.createStatement("select * from host where id in $ids");
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
    
    public Host fetchByIP(final IpAddr addr) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from host where ip_address = $ip_address");
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
    
    public Host fetchByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from host where name = $name");
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
    
    public List<Host> fetchByParent(final String id) throws DbException {
        return this.fetchByParent(id, false);
    }
    
    public List<Host> fetchByParent(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Host> resultList = new ArrayList<Host>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, network from host where network = $id");
            }
            else {
                stmt = session.createStatement("select * from host where network = $id");
            }
            stmt.setString("id", id);
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
    
    public void insert(final Host host) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into host\n(id, name, ip_address, network, tags)\nvalues\n($id, $name, $ip_address, $network, $tags)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", host.getId());
            stmt.setString("name", host.getName());
            stmt.setString("ip_address", host.getAddressString());
            stmt.setString("network", host.getParentId());
            stmt.setString("tags", host.getTagsRoot().cloneTree());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(this.getDb());
            tagAccess.insert(host.getTags(), host.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final Host host) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update host set\nip_address = $ip_address,\nname = $name,\ntags = $tags\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", host.getId());
            stmt.setString("name", host.getName());
            stmt.setString("ip_address", host.getIpAddress().getAddressString());
            stmt.setString("tags", host.getTagsRoot().cloneTree());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(this.getDb());
            tagAccess.deleteByEntity(host.getId());
            tagAccess.insert(host.getTags(), host.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void updateNetwork(final Host host, final String network) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update host set\nnetwork = $network\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", host.getId());
            stmt.setString("network", network);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Host host) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from host where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", host.getId());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(this.getDb());
            tagAccess.deleteByEntity(host.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Host build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final Host host = new Host(result.getString("id"));
            final String network = result.getString("network");
            if (network != null && !network.equals("")) {
                host.setParentId(network);
            }
            host.trim();
            return host;
        }
        final Host host = new Host(result.getString("id"));
        final String name = result.getString("name");
        if (name != null && !name.equals("")) {
            host.setName(name);
        }
        host.setIpAddress(result.getString("ip_address"));
        final String network2 = result.getString("network");
        if (network2 != null && !network2.equals("")) {
            host.setParentId(network2);
        }
        host.setTagsRoot(result.getElement("tags"));
        return host;
    }
    
    public List<Host> fetchForSelection() throws DbException {
        final DbSession db = this.getDbSession();
        final List<Host> list = new ArrayList<Host>();
        try {
            final DbStatement stmt = db.createStatement("select id, name, ip_address from host");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Host host = new Host(result.getString("id"));
                final String name = result.getString("name");
                if (name != null) {
                    host.setName(name);
                }
                host.setIpAddress(result.getString("ip_address"));
                list.add(host);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<Host> fetchNameAndIP(final Collection<String> ids) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Host> hosts = new ArrayList<Host>();
        try {
            final DbStatement stmt = db.createStatement("select id, name, ip_address from host where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Host host = new Host(result.getString("id"));
                final String name = result.getString("name");
                if (name != null) {
                    host.setName(name);
                }
                host.setIpAddress(result.getString("ip_address"));
                hosts.add(host);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return hosts;
    }
    
    public Host fetchNameAndIP(final String id) throws DbException {
        final List<Host> hosts = this.fetchNameAndIP(Collections.singletonList(id));
        if (hosts.isEmpty()) {
            return null;
        }
        return hosts.get(0);
    }
    
    public boolean isNameUnique(final String networkId, final Host host) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select count(id) from host where network = $network and name = $name");
            stmt.setString("network", networkId);
            stmt.setString("name", host.getName());
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
    
    public boolean isIpUnique(final String networkId, final Host host) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select count(id) from host where network = $network and ip_address = $address");
            stmt.setString("network", networkId);
            final IpAddr addr = host.getIpAddress();
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
