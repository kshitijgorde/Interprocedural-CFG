// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.IPProtocol;
import com.stonewall.cornerstone.entity.Entity;
import java.util.Collection;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.db.DbResultSet;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.util.IpPortRange;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.IPService;
import com.stonewall.cornerstone.entity.Label;

public class IPServiceDbAccess extends DbAccess
{
    static final String fetchAll = "select * from ip_service";
    static final String fetchAllStubs = "select id from ip_service";
    static final String fetchById = "select * from ip_service where id = $id";
    static final String fetchByIds = "select * from ip_service where id in $ids ";
    static final String fetchPredefinedByGroup = "select id from ip_service \nwhere grp = $grp and mutable = 'false'";
    static final String fetchPredefined = "select * from ip_service \nwhere mutable = 'false'";
    static final String fetchByName = "select * from ip_service where name = $name";
    static final String countByName = "select count(name) from ip_service where name = $name";
    static final String insert = "insert into ip_service\n(id,\n name,\n  mutable,\n  protocol,\n src_start,\n src_end,\n dst_start,\n dst_end,\n icmp_type,\n icmp_code,\n grp,\n description)\nvalues\n($id,\n $name,\n $mutable,\n $protocol,\n $src_start,\n $src_end,\n $dst_start,\n $dst_end,\n $icmp_type,\n $icmp_code,\n $grp,\n $description)";
    static final String update = "update ip_service set\nname = $name, \nprotocol = $protocol,\nsrc_start = $src_start,\nsrc_end = $src_end,\ndst_start = $dst_start,\ndst_end = $dst_end,\nicmp_type = $icmp_type,\nicmp_code = $icmp_code,\ngrp = $grp, \ndescription = $description\nwhere id = $id ";
    static final String delete = "delete from ip_service where id = $id \n";
    
    public IPServiceDbAccess() {
    }
    
    public IPServiceDbAccess(final Label label) {
        super(label);
    }
    
    public IPServiceDbAccess(final String label) {
        super(label);
    }
    
    public void insert(final IPService service) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into ip_service\n(id,\n name,\n  mutable,\n  protocol,\n src_start,\n src_end,\n dst_start,\n dst_end,\n icmp_type,\n icmp_code,\n grp,\n description)\nvalues\n($id,\n $name,\n $mutable,\n $protocol,\n $src_start,\n $src_end,\n $dst_start,\n $dst_end,\n $icmp_type,\n $icmp_code,\n $grp,\n $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", service.getId());
            stmt.setString("name", service.getName());
            stmt.setString("mutable", String.valueOf(service.isMutable()));
            stmt.setString("grp", service.getGroup());
            stmt.setString("description", service.getDescription());
            stmt.set("protocol", service.getProtocol().getNumber());
            final IpPortRange src = service.getSource();
            if (src == null) {
                stmt.set("src_start", -1);
                stmt.set("src_end", -1);
            }
            else {
                stmt.set("src_start", src.sp());
                stmt.set("src_end", src.ep());
            }
            final IpPortRange dst = service.getDestination();
            if (dst == null) {
                stmt.set("dst_start", -1);
                stmt.set("dst_end", -1);
            }
            else {
                stmt.set("dst_start", dst.sp());
                stmt.set("dst_end", dst.ep());
            }
            stmt.set("icmp_type", service.getICMPType());
            stmt.set("icmp_code", service.getICMPCode());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final IPService service) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update ip_service set\nname = $name, \nprotocol = $protocol,\nsrc_start = $src_start,\nsrc_end = $src_end,\ndst_start = $dst_start,\ndst_end = $dst_end,\nicmp_type = $icmp_type,\nicmp_code = $icmp_code,\ngrp = $grp, \ndescription = $description\nwhere id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", service.getId());
            stmt.setString("name", service.getName());
            stmt.setString("grp", service.getGroup());
            stmt.setString("description", service.getDescription());
            stmt.set("protocol", service.getProtocol().getNumber());
            final IpPortRange src = service.getSource();
            stmt.set("src_start", src.sp());
            stmt.set("src_end", src.ep());
            final IpPortRange dst = service.getDestination();
            stmt.set("dst_start", dst.sp());
            stmt.set("dst_end", dst.ep());
            stmt.set("icmp_type", service.getICMPType());
            stmt.set("icmp_code", service.getICMPCode());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final IPService service) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from ip_service where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", service.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public List<IPService> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<IPService> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPService> services = new ArrayList<IPService>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from ip_service");
            }
            else {
                stmt = session.createStatement("select * from ip_service");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final IPService service = this.buildService(result, stub);
                services.add(service);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return services;
    }
    
    public List<EntityReference> fetchPredefinedByGroup(final String group) throws DbException {
        final DbSession session = this.getDbSession();
        final List<EntityReference> services = new ArrayList<EntityReference>();
        try {
            final DbStatement stmt = session.createStatement("select id from ip_service \nwhere grp = $grp and mutable = 'false'");
            stmt.setString("grp", group);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                services.add(new EntityReference(EntityFactory.EntityType.ipService, result.getString()));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return services;
    }
    
    public List<IPService> fetchPredefined() throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPService> services = new ArrayList<IPService>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service \nwhere mutable = 'false'");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final IPService service = this.buildService(result, false);
                services.add(service);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return services;
    }
    
    public IPService fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public IPService fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final IPService s = new IPService(id);
                s.trim();
                return s;
            }
            final DbStatement stmt = session.createStatement("select * from ip_service where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final IPService service = this.buildService(result, stub);
                return service;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public IPService fetchByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service where name = $name");
            stmt.setString("name", name);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final IPService service = this.buildService(result, false);
                return service;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<IPService> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<IPService> services = new ArrayList<IPService>();
        try {
            final DbStatement stmt = session.createStatement("select * from ip_service where id in $ids ");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final IPService service = this.buildService(result, false);
                services.add(service);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return services;
    }
    
    public void cacheById(final EntityCache cache, final Collection<String> ids) throws Exception {
        final List<IPService> services = this.fetchById(ids);
        cache.cache(services);
    }
    
    public void cachePredefined(final EntityCache cache) throws Exception {
        final List<IPService> services = this.fetchPredefined();
        cache.cache(services);
    }
    
    public int countByName(final String name) throws DbException {
        final DbSession session = this.getDbSession();
        final int count = 0;
        try {
            final DbStatement stmt = session.createStatement("select count(name) from ip_service where name = $name");
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
    
    private IPService buildService(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final IPService service = new IPService(result.getString("id"));
            service.trim();
            return service;
        }
        try {
            final IPService service = new IPService(result.getString("id"));
            service.setName(result.getString("name"));
            service.setMutable(Boolean.parseBoolean(result.getString("mutable")));
            service.setGroup(result.getString("grp"));
            service.setDescription(result.getString("description"));
            try {
                final IpPortRange src = new IpPortRange(result.getInteger("src_start"), result.getInteger("src_end"));
                service.setSource(src);
            }
            catch (Exception ex) {}
            try {
                final IpPortRange dst = new IpPortRange(result.getInteger("dst_start"), result.getInteger("dst_end"));
                service.setDestination(dst);
            }
            catch (Exception ex2) {}
            service.setProtocol(IPProtocol.get(result.getInteger("protocol")));
            service.setICMPType(result.getInteger("icmp_type"));
            service.setICMPCode(result.getInteger("icmp_code"));
            return service;
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
}
