// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import com.stonewall.cornerstone.entity.EntityReference;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.security.SecurityTunnel;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class SecurityTunnelDbAccess extends DbAccess
{
    static final String fetchAll = "select * from tunnel";
    static final String fetchById = "select * from tunnel where tunnel = $id";
    static final String fetchByIds = "select * from tunnel where tunnel in $ids";
    static final String deleteByEndpoint = "delete from tunnel where interface_a = $ref or interface_b = $ref";
    static final String deleteByEndpoints = "delete from tunnel where interface_a in $refs or interface_b in $refs";
    static final String fetchByEndpoints = "select * from tunnel\nwhere\n(interface_a = $refA or interface_b = $refA) and\n(interface_a = $refB or interface_b = $refB)";
    static final String insert = "insert into tunnel\n(tunnel, policy, interface_a, interface_b)\nvalues\n($tunnel, $policy, $interface_a, $interface_b)";
    static final String delete = "delete from tunnel where tunnel = $id";
    static final String deleteByPolicy = "delete from tunnel where policy = $id";
    
    public SecurityTunnelDbAccess() {
    }
    
    public SecurityTunnelDbAccess(final Label label) {
        super(label);
    }
    
    public SecurityTunnelDbAccess(final String label) {
        super(label);
    }
    
    public List<SecurityTunnel> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<SecurityTunnel> fetchAll(final boolean stub) throws DbException {
        final List<SecurityTunnel> list = new ArrayList<SecurityTunnel>();
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from tunnel");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public SecurityTunnel fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public SecurityTunnel fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from tunnel where tunnel = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<SecurityTunnel> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<SecurityTunnel> tunnels = new ArrayList<SecurityTunnel>();
        try {
            final DbStatement stmt = session.createStatement("select * from tunnel where tunnel in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                tunnels.add(build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return tunnels;
    }
    
    public SecurityTunnel fetch(final EntityReference endA, final EntityReference endB) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from tunnel\nwhere\n(interface_a = $refA or interface_b = $refA) and\n(interface_a = $refB or interface_b = $refB)");
            stmt.setString("refA", endA.getEncoded());
            stmt.setString("refB", endB.getEncoded());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return build(result, false);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public void insert(final SecurityTunnel tunnel, final SecurityPolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        final EntityReference[] endpoint = tunnel.getInterfaceRefs();
        try {
            final DbStatement stmt = db.createStatement("insert into tunnel\n(tunnel, policy, interface_a, interface_b)\nvalues\n($tunnel, $policy, $interface_a, $interface_b)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("tunnel", tunnel.getId());
            stmt.setString("policy", policy.getId());
            stmt.setString("interface_a", endpoint[0].getEncoded());
            stmt.setString("interface_b", endpoint[1].getEncoded());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final SecurityTunnel tunnel) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from tunnel where tunnel = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", tunnel.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteAllByPolicy(final SecurityPolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from tunnel where policy = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
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
            final DbStatement stmt = db.createStatement("delete from tunnel where interface_a = $ref or interface_b = $ref");
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
            final DbStatement stmt = db.createStatement("delete from tunnel where interface_a in $refs or interface_b in $refs");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("refs", refs);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private static SecurityTunnel build(final DbResultSet result, final boolean stub) throws DbException {
        final EntityReference ref1 = new EntityReference(result.getString("interface_a"));
        final EntityReference ref2 = new EntityReference(result.getString("interface_b"));
        final SecurityTunnel t = new SecurityTunnel(ref1, ref2);
        return t;
    }
}
