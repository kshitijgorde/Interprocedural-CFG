// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class NatDbAccess extends DeployablePolicyDbAccess
{
    static final String fetchAllByPhase = "select * from policy where type = $type and phase = $phase";
    static final String fetchByDevice = "select * from policy where device = $device and type = $type";
    static final String fetchByDeviceAndPhase = "select * from policy where device = $device and type = $type and phase = $phase";
    static final String fetchStubByDeviceAndPhase = "select id, parent, phase, pending, timestamp from policy where device = $device and type = $type and phase = $phase";
    static final String insert = "insert into policy\n(id, type, phase, pending, timestamp, device, parent, content)\nvalues\n($id, $type, $phase, $pending, $timestamp, $device, $parent, $content)";
    static final String update = "update policy set\npending = $pending, \ntimestamp = $timestamp, \ncontent = $content \nwhere id = $id ";
    static final String fetchSiteIds = "select distinct(site) from device where id in (select device from policy where id in $ids)";
    
    public NatDbAccess() {
    }
    
    public NatDbAccess(final Label label) {
        super(label);
    }
    
    public NatDbAccess(final String label) {
        super(label);
    }
    
    public List<NatPolicy> fetchAll() throws DbException {
        return this.fetchAll(Policy.Type.nat, false);
    }
    
    public List<NatPolicy> fetchAll(final boolean stub) throws DbException {
        return this.fetchAll(Policy.Type.nat, stub);
    }
    
    public List<NatPolicy> fetchAll(final DeployablePolicy.Phase phase) throws DbException {
        final DbSession db = this.getDbSession();
        final List<NatPolicy> list = new ArrayList<NatPolicy>();
        try {
            final DbStatement stmt = db.createStatement("select * from policy where type = $type and phase = $phase");
            stmt.setString("type", Policy.Type.nat.name());
            stmt.setString("phase", phase.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public NatPolicy fetchWorkingPolicy(final String device) throws DbException {
        return this.fetchByDevice(device, DeployablePolicy.Phase.working, false);
    }
    
    public NatPolicy fetchWorkingReference(final String device) throws DbException {
        return this.fetchByDevice(device, DeployablePolicy.Phase.working, true);
    }
    
    public NatPolicy fetchDeployedPolicy(final String device) throws DbException {
        return this.fetchByDevice(device, DeployablePolicy.Phase.deployed, false);
    }
    
    public NatPolicy fetchDeployedReference(final String device) throws DbException {
        return this.fetchByDevice(device, DeployablePolicy.Phase.deployed, true);
    }
    
    public NatPolicy fetchBaselinedPolicy(final String device) throws DbException {
        return this.fetchByDevice(device, DeployablePolicy.Phase.baseline, false);
    }
    
    public NatPolicy fetchBaselinedReference(final String device) throws DbException {
        return this.fetchByDevice(device, DeployablePolicy.Phase.baseline, true);
    }
    
    public NatPolicy fetchByDevice(final String id, final DeployablePolicy.Phase phase, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, parent, phase, pending, timestamp from policy where device = $device and type = $type and phase = $phase");
            }
            else {
                stmt = session.createStatement("select * from policy where device = $device and type = $type and phase = $phase");
            }
            stmt.setString("device", id);
            stmt.setString("type", Policy.Type.nat.name());
            stmt.setString("phase", phase.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return (NatPolicy)this.build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<String> fetchSiteIds(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> siteIds = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select distinct(site) from device where id in (select device from policy where id in $ids)");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                siteIds.add(result.getString("site"));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return siteIds;
    }
    
    public void insert(final NatPolicy nat) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into policy\n(id, type, phase, pending, timestamp, device, parent, content)\nvalues\n($id, $type, $phase, $pending, $timestamp, $device, $parent, $content)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", nat.getId());
            stmt.setString("device", nat.getParentId());
            stmt.setString("parent", nat.getParentId());
            stmt.setString("type", Policy.Type.nat.name());
            stmt.setString("phase", nat.getPhase().name());
            stmt.setString("content", nat.getRoot());
            stmt.setString("pending", String.valueOf(nat.isPending()));
            stmt.set("timestamp", nat.getTimestamp());
            stmt.execute();
            if (this.trackPolicyParts(nat)) {
                new PolicyPartDbAccess(this.getDb()).insert(nat.getParts());
            }
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final NatPolicy nat) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update policy set\npending = $pending, \ntimestamp = $timestamp, \ncontent = $content \nwhere id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", nat.getId());
            stmt.setString("content", nat.getRoot());
            stmt.setString("pending", String.valueOf(nat.isPending()));
            stmt.set("timestamp", nat.getTimestamp());
            stmt.execute();
            if (this.trackPolicyParts(nat)) {
                new PolicyPartDbAccess(this.getDb()).deleteByPolicy(nat.getId());
                new PolicyPartDbAccess(this.getDb()).insert(nat.getParts());
            }
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    @Override
    protected DeployablePolicy build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final NatPolicy policy = new NatPolicy(result.getString("id"), DeployablePolicy.Phase.valueOf(result.getString("phase")));
            policy.setParentId(result.getString("parent"));
            policy.setPending(result.getBoolean("pending"));
            policy.setTimestamp(result.getLong("timestamp"));
            policy.trim();
            return policy;
        }
        return super.build(result);
    }
}
