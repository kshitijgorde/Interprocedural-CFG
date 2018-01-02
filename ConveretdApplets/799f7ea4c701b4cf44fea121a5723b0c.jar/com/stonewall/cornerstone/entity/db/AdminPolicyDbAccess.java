// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.policy.RuleSet;
import com.stonewall.cornerstone.entity.policy.security.SecurityRule;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.policy.security.AdminPolicy;
import com.stonewall.cornerstone.db.DbSession;

public class AdminPolicyDbAccess extends DbAccess
{
    static final String fetch = "select * from admin_policy";
    static final String insert = "insert into admin_policy\n(id)\nvalues\n($id)";
    static final String insertRule = "insert into admin_rule\n(id, policy, entity, content)\nvalues\n($id, $policy, $entity, $content)";
    static final String fetchRule = "select * from admin_rule where policy = 'APOLICY0' and entity = $entity \n";
    static final String deleteRules = "delete from admin_rule where policy = 'APOLICY0' and entity in $entityIds \n";
    static final String fetchRules = "select * from admin_rule where policy = $policy";
    
    public AdminPolicyDbAccess() {
        super(DbSession.DB.main);
    }
    
    public AdminPolicy fetch() throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from admin_policy");
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final AdminPolicy policy = this.buildPolicy(result);
                this.fetchRuleSet(policy);
                return policy;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public void insert(final AdminPolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into admin_policy\n(id)\nvalues\n($id)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public SecurityRule fetchRule(final String entityId) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from admin_rule where policy = 'APOLICY0' and entity = $entity \n");
            stmt.setString("entity", entityId);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.buildRule(result);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    private void fetchRuleSet(final AdminPolicy policy) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from admin_rule where policy = $policy");
            stmt.setString("policy", policy.getId());
            final DbResultSet result = stmt.execute();
            policy.setRuleSet(this.buildRuleSet(result, policy.getRuleSet()));
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void insertRule(final Entity entity, final SecurityRule rule) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into admin_rule\n(id, policy, entity, content)\nvalues\n($id, $policy, $entity, $content)");
            try {
                stmt.set(DbStatement.Mode.Update);
                stmt.setString("id", rule.getId());
                stmt.setString("policy", rule.getParent().getId());
                stmt.setString("entity", entity.getId());
                stmt.setString("content", rule.getRoot());
                stmt.execute();
            }
            finally {
                stmt.close();
            }
            stmt.close();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteRule(final String entityId) throws DbException {
        this.deleteRules(Collections.singletonList(entityId));
    }
    
    public void deleteRules(final List<String> entityIds) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("delete from admin_rule where policy = 'APOLICY0' and entity in $entityIds \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("entityIds", entityIds);
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    private AdminPolicy buildPolicy(final DbResultSet result) throws DbException {
        final AdminPolicy policy = new AdminPolicy();
        policy.setId(result.getString("id"));
        return policy;
    }
    
    private com.stonewall.cornerstone.entity.policy.security.RuleSet buildRuleSet(final DbResultSet result, final com.stonewall.cornerstone.entity.policy.security.RuleSet set) throws DbException {
        while (result.next()) {
            set.add(this.buildRule(result));
        }
        return set;
    }
    
    private SecurityRule buildRule(final DbResultSet result) throws DbException {
        final SecurityRule rule = com.stonewall.cornerstone.entity.policy.security.RuleSet.create(result.getElement("content"));
        rule.setParent(new EntityReference(EntityFactory.EntityType.securityPolicy, result.getString("policy")));
        return rule;
    }
}
