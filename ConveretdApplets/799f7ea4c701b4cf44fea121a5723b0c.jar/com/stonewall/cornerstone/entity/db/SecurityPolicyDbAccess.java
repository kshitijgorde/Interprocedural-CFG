// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.QueryBuilder;
import com.stonewall.cornerstone.entity.policy.PolicyPart;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.policy.security.SecurityTunnel;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.security.DevicePolicy;
import com.stonewall.cornerstone.entity.policy.security.SitePolicy;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class SecurityPolicyDbAccess extends DeployablePolicyDbAccess
{
    static final String insertSite = "insert into policy\n(id, type, phase, pending, timestamp, parent, site, content)\nvalues\n($id, $type, $phase, $pending, $timestamp, $parent, $site, $content)";
    static final String insertDevice = "insert into policy\n(id, type, phase, pending, timestamp, parent, device, content)\nvalues\n($id, $type, $phase, $pending, $timestamp, $parent, $device, $content)";
    static final String update = "update policy set\npending = $pending, \ntimestamp = $timestamp, \ncontent = $content \nwhere id = $id ";
    static final String fetchAll = "select * from policy where type = 'site' or type = 'device'";
    static final String fetchAllStubs = "select id, parent, type, pending, phase, timestamp from policy where type = 'site' or type = 'device'";
    static final String fetchByParentAndPhase = "select * from policy where parent = $parent and phase = $phase and type = $type";
    static final String fetchStubByParentAndPhase = "select id, parent, type, pending, phase, timestamp from policy where parent = $parent and phase = $phase and type = $type";
    static final String fetchIdByParent = "select id from policy  \nwhere parent = $parent and phase = $phase and type = $type";
    static final String exists = "select id from policy where parent = $parent and phase = $phase and type = $type";
    static final String fetchSiteIds = "select distinct(site) from policy where id in $ids and type = $type";
    static final String fetchByPartIds = "select * from policy \nwhere id in (select distinct(policy) from policy_part where entity in $entityIds and entity_type = $entityType and policy_type = $policyType)\n";
    static final String insertStatus = "insert into policy_status\n(id, policy)\nvalues $values";
    static final String statusValues = "($id, $policy)";
    static final String deleteStatus = "delete from policy_status where policy = $policy";
    static final String[] enclosures;
    
    static {
        enclosures = new String[] { "", ",\n", "" };
    }
    
    public SecurityPolicyDbAccess() {
    }
    
    public SecurityPolicyDbAccess(final Label label) {
        super(label);
    }
    
    public SecurityPolicyDbAccess(final String label) {
        super(label);
    }
    
    public List<SecurityPolicy> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<SecurityPolicy> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<SecurityPolicy> list = new ArrayList<SecurityPolicy>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, parent, type, pending, phase, timestamp from policy where type = 'site' or type = 'device'");
            }
            else {
                stmt = db.createStatement("select * from policy where type = 'site' or type = 'device'");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add((SecurityPolicy)this.build(result, stub));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public SecurityPolicy fetchByParent(final String parent, final Policy.Type type, final DeployablePolicy.Phase phase, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, parent, type, pending, phase, timestamp from policy where parent = $parent and phase = $phase and type = $type");
            }
            else {
                stmt = session.createStatement("select * from policy where parent = $parent and phase = $phase and type = $type");
            }
            stmt.setString("parent", parent);
            stmt.setString("phase", phase.name());
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return (SecurityPolicy)this.build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public String fetchIdByParent(final String parentId, final Policy.Type type, final String phase) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select id from policy  \nwhere parent = $parent and phase = $phase and type = $type");
            stmt.setString("parent", parentId);
            stmt.setString("phase", phase);
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getString();
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
            final DbStatement stmt = session.createStatement("select distinct(site) from policy where id in $ids and type = $type");
            stmt.setString("ids", ids);
            stmt.setString("type", Policy.Type.site.name());
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
    
    public SitePolicy fetchWorkingSitePolicy(final String parent) throws DbException {
        return (SitePolicy)this.fetchByParent(parent, Policy.Type.site, DeployablePolicy.Phase.working, false);
    }
    
    public SitePolicy fetchWorkingSiteReference(final String parent) throws DbException {
        return (SitePolicy)this.fetchByParent(parent, Policy.Type.site, DeployablePolicy.Phase.working, true);
    }
    
    public SitePolicy fetchNetPolicy(final String parent) throws DbException {
        return (SitePolicy)this.fetchByParent(parent, Policy.Type.site, DeployablePolicy.Phase.net, false);
    }
    
    public SitePolicy fetchNetReference(final String parent) throws DbException {
        return (SitePolicy)this.fetchByParent(parent, Policy.Type.site, DeployablePolicy.Phase.net, true);
    }
    
    public SitePolicy fetchDeployedSitePolicy(final String parent) throws DbException {
        return (SitePolicy)this.fetchByParent(parent, Policy.Type.site, DeployablePolicy.Phase.deployed, false);
    }
    
    public SitePolicy fetchDeployedSiteReference(final String parent) throws DbException {
        return (SitePolicy)this.fetchByParent(parent, Policy.Type.site, DeployablePolicy.Phase.deployed, true);
    }
    
    public DevicePolicy fetchWorkingDevicePolicy(final String parent) throws DbException {
        return (DevicePolicy)this.fetchByParent(parent, Policy.Type.device, DeployablePolicy.Phase.working, false);
    }
    
    public DevicePolicy fetchWorkingDeviceReference(final String parent) throws DbException {
        return (DevicePolicy)this.fetchByParent(parent, Policy.Type.device, DeployablePolicy.Phase.working, true);
    }
    
    public DevicePolicy fetchDeployedDevicePolicy(final String parent) throws DbException {
        return (DevicePolicy)this.fetchByParent(parent, Policy.Type.device, DeployablePolicy.Phase.deployed, false);
    }
    
    public DevicePolicy fetchDeployedDeviceReference(final String parent) throws DbException {
        return (DevicePolicy)this.fetchByParent(parent, Policy.Type.device, DeployablePolicy.Phase.deployed, true);
    }
    
    public DevicePolicy fetchBaselinePolicy(final String device) throws DbException {
        return (DevicePolicy)this.fetchByParent(device, Policy.Type.device, DeployablePolicy.Phase.baseline, false);
    }
    
    public DevicePolicy fetchBaselineReference(final String device) throws DbException {
        return (DevicePolicy)this.fetchByParent(device, Policy.Type.device, DeployablePolicy.Phase.baseline, true);
    }
    
    public List<SecurityPolicy> fetchByPolicyPart(final EntityFactory.EntityType eType, final Collection<String> entityIds, final Policy.Type pType) throws DbException {
        final DbSession session = this.getDbSession();
        final List<SecurityPolicy> policies = new ArrayList<SecurityPolicy>();
        try {
            final DbStatement stmt = session.createStatement("select * from policy \nwhere id in (select distinct(policy) from policy_part where entity in $entityIds and entity_type = $entityType and policy_type = $policyType)\n");
            stmt.setString("entityIds", entityIds);
            stmt.setString("entityType", eType.name());
            stmt.setString("policyType", pType.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                policies.add(this.build(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return policies;
    }
    
    public void insert(final SecurityPolicy policy) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            final Policy.Type type = policy.getType();
            switch (type) {
                case site: {
                    stmt = session.createStatement("insert into policy\n(id, type, phase, pending, timestamp, parent, site, content)\nvalues\n($id, $type, $phase, $pending, $timestamp, $parent, $site, $content)");
                    stmt.setString("site", policy.getParentId());
                    break;
                }
                case device: {
                    stmt = session.createStatement("insert into policy\n(id, type, phase, pending, timestamp, parent, device, content)\nvalues\n($id, $type, $phase, $pending, $timestamp, $parent, $device, $content)");
                    stmt.setString("device", policy.getParentId());
                    break;
                }
                default: {
                    SecurityPolicyDbAccess.log.error("Unknown type:" + type.name());
                    break;
                }
            }
            stmt.setString("id", policy.getId());
            stmt.setString("parent", policy.getParentId());
            stmt.setString("type", type.name());
            stmt.setString("phase", policy.getPhase().name());
            stmt.setString("content", policy.getRoot());
            stmt.setString("pending", String.valueOf(policy.isPending()));
            stmt.set("timestamp", policy.getTimestamp());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            if (this.trackPolicyParts(policy)) {
                new PolicyPartDbAccess(this.getDb()).insert(policy.getParts());
            }
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void update(final SecurityPolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update policy set\npending = $pending, \ntimestamp = $timestamp, \ncontent = $content \nwhere id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
            stmt.setString("content", policy.getRoot());
            stmt.setString("pending", String.valueOf(policy.isPending()));
            stmt.set("timestamp", policy.getTimestamp());
            stmt.execute();
            if (this.trackPolicyParts(policy)) {
                new PolicyPartDbAccess(this.getDb()).deleteByPolicy(policy.getId());
                new PolicyPartDbAccess(this.getDb()).insert(policy.getParts());
                new SecurityTunnelDbAccess(this.getDb()).deleteAllByPolicy(policy);
                for (final SecurityTunnel tunnel : policy.getTunnels()) {
                    new SecurityTunnelDbAccess(this.getDb()).insert(tunnel, policy);
                }
            }
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public boolean exists(final String parent, final Policy.Type type, final DeployablePolicy.Phase phase) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select id from policy where parent = $parent and phase = $phase and type = $type");
            stmt.setString("device", parent);
            stmt.setString("phase", phase.name());
            stmt.setString("type", type.name());
            stmt.setString("parent", parent);
            final DbResultSet result = stmt.execute();
            return result.next();
        }
        finally {
            db.close();
        }
    }
    
    public void markPartsPending(final Collection<PolicyPart> refs) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final List<String> values = new ArrayList<String>();
            final QueryBuilder query = db.queryBuilder("($id, $policy)");
            for (final PolicyPart ref : refs) {
                query.setString("id", ref.getId());
                query.setString("policy", ref.getPolicy().getId());
                values.add(query.toString());
            }
            final DbStatement stmt = db.createStatement("insert into policy_status\n(id, policy)\nvalues $values");
            stmt.set(DbStatement.Mode.Update);
            stmt.queryBuilder().collectionEnclosure(SecurityPolicyDbAccess.enclosures);
            stmt.set("values", values);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void markPending(final Collection<String> ids, final String policyId) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final List<String> values = new ArrayList<String>();
            final QueryBuilder query = db.queryBuilder("($id, $policy)");
            for (final String id : ids) {
                query.setString("id", id);
                query.setString("policy", policyId);
                values.add(query.toString());
            }
            final DbStatement stmt = db.createStatement("insert into policy_status\n(id, policy)\nvalues $values");
            stmt.set(DbStatement.Mode.Update);
            stmt.queryBuilder().collectionEnclosure(SecurityPolicyDbAccess.enclosures);
            stmt.set("values", values);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteRuleStatus(final String policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from policy_status where policy = $policy");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("policy", policy);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public List<SecurityTunnel> fetchAllTunnels() throws DbException {
        return new SecurityTunnelDbAccess(this.getDb()).fetchAll();
    }
    
    private SecurityPolicy buildStub(final DbResultSet result) throws DbException {
        SecurityPolicy p = null;
        final Policy.Type type = Policy.Type.valueOf(result.getString("type"));
        switch (type) {
            case site: {
                p = new SitePolicy(result.getString("id"), DeployablePolicy.Phase.valueOf(result.getString("phase")));
                break;
            }
            case device: {
                p = new DevicePolicy(result.getString("id"), DeployablePolicy.Phase.valueOf(result.getString("phase")));
                break;
            }
            default: {
                SecurityPolicyDbAccess.log.error("Unknown type:" + type.name());
                break;
            }
        }
        p.setParentId(result.getString("parent"));
        p.setPending(result.getBoolean("pending"));
        p.setTimestamp(result.getLong("timestamp"));
        p.trim();
        return p;
    }
    
    @Override
    protected DeployablePolicy build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            return this.buildStub(result);
        }
        try {
            SecurityPolicy p = null;
            final Policy.Type type = Policy.Type.valueOf(result.getString("type"));
            switch (type) {
                case site: {
                    p = new SitePolicy(result.getElement("content"));
                    break;
                }
                case device: {
                    p = new DevicePolicy(result.getElement("content"));
                    break;
                }
                default: {
                    SecurityPolicyDbAccess.log.error("Unknown type:" + type.name());
                    break;
                }
            }
            p.setPending(Boolean.parseBoolean(result.getString("pending")));
            p.setTimestamp(result.getLong("timestamp"));
            return p;
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
}
