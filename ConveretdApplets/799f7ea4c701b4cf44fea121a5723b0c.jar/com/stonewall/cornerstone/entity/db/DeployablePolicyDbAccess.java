// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.Label;

public class DeployablePolicyDbAccess extends DbAccess
{
    static final String fetchAll = "select * from policy where type = $type";
    static final String fetchAllStubs = "select id, type, phase, pending, timestamp, parent from policy where type = $type";
    static final String fetchById = "select * from policy where id = $id ";
    static final String fetchByIds = "select * from policy where id in $ids ";
    static final String fetchStubById = "select id, type, phase, pending, timestamp, parent from policy where id = $id ";
    static final String fetchStubsByIds = "select id, type, phase, pending, timestamp, parent from policy where id in $ids ";
    static final String updatePending = "update policy set \npending = $pending \nwhere id = $id";
    static final String delete = "delete from policy where id = $id \n";
    static final String determinePendingRuleIds = "select distinct(rule) from policy_status where rule in $ids";
    
    public DeployablePolicyDbAccess() {
    }
    
    public DeployablePolicyDbAccess(final Label label) {
        super(label);
    }
    
    public DeployablePolicyDbAccess(final String label) {
        super(label);
    }
    
    public <T extends DeployablePolicy> List<T> fetchAll(final Policy.Type type) throws DbException {
        return this.fetchAll(type, false);
    }
    
    public <T extends DeployablePolicy> List<T> fetchAll(final Policy.Type type, final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<T> list = new ArrayList<T>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, type, phase, pending, timestamp, parent from policy where type = $type");
            }
            else {
                stmt = db.createStatement("select * from policy where type = $type");
            }
            stmt.setString("type", type.name());
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
    
    public <T extends DeployablePolicy> T fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public <T extends DeployablePolicy> T fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, type, phase, pending, timestamp, parent from policy where id = $id ");
            }
            else {
                stmt = session.createStatement("select * from policy where id = $id ");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final T policy = this.build(result, stub);
                return policy;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public <T extends DeployablePolicy> List<T> fetchById(final Collection<String> ids) throws DbException {
        return this.fetchById(ids, false);
    }
    
    public <T extends DeployablePolicy> List<T> fetchById(final Collection<String> ids, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<T> policies = new ArrayList<T>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, type, phase, pending, timestamp, parent from policy where id in $ids ");
            }
            else {
                stmt = session.createStatement("select * from policy where id in $ids ");
            }
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final T policy = this.build(result, stub);
                policies.add(policy);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return policies;
    }
    
    public void updatePending(final String id, final boolean flag) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update policy set \npending = $pending \nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", id);
            stmt.setString("pending", String.valueOf(flag));
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final DeployablePolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from policy where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public List<String> determinePendingRuleIds(final Collection<String> ruleIds) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> rules = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select distinct(rule) from policy_status where rule in $ids");
            stmt.setString("ids", ruleIds);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                rules.add(result.getString("rule"));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return rules;
    }
    
    protected <T extends DeployablePolicy> T build(final DbResultSet result, final boolean stub) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    protected <T extends DeployablePolicy> T build(final DbResultSet result) throws DbException {
        final T policy = (T)EntityFactory.getInstance().createEntity(result.getElement("content"));
        policy.setPending(Boolean.parseBoolean(result.getString("pending")));
        policy.setTimestamp(result.getLong("timestamp"));
        return policy;
    }
    
    protected boolean trackPolicyParts(final DeployablePolicy policy) {
        return policy.getPhase().equals(DeployablePolicy.Phase.working);
    }
}
