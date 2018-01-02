// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.PolicyIssue;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class PolicyIssueDbAccess extends DbAccess
{
    static final String fetchAll = "select * from policy_issue";
    static final String fetchAllStubs = "select id from policy_issue";
    static final String fetchById = "select * from policy_issue where id = $id";
    static final String fetchIdsByPolicy = "select id from policy_issue where policy = $policy";
    static final String fetchByPolicyAndCategory = "select * from policy_issue where policy = $policy and category = $category";
    static final String countIssues = "select count(id) from policy_issue \nwhere policy in (select distinct(id) from policy where phase = 'working' and site in $site) \n      and severity = $severity";
    private static String deleteIssuesByCategory;
    private static String insert;
    private static String delete;
    
    static {
        PolicyIssueDbAccess.deleteIssuesByCategory = "delete from policy_issue where policy = $policy and category = $category";
        PolicyIssueDbAccess.insert = "insert into policy_issue \n(id, policy, policy_type, type, category, severity, entity, description, rule) \nvalues \n($id, $policy, $policyType, $type, $category, $severity, $entity, $description, $rule)";
        PolicyIssueDbAccess.delete = "delete from policy_issue where id = $id";
    }
    
    public PolicyIssueDbAccess() {
    }
    
    public PolicyIssueDbAccess(final Label label) {
        super(label);
    }
    
    public PolicyIssueDbAccess(final String label) {
        super(label);
    }
    
    public List<PolicyIssue> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<PolicyIssue> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<PolicyIssue> l = new ArrayList<PolicyIssue>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from policy_issue");
            }
            else {
                stmt = session.createStatement("select * from policy_issue");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                l.add(this.build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return l;
    }
    
    public PolicyIssue fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public PolicyIssue fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final PolicyIssue p = new PolicyIssue(id);
                p.trim();
                return p;
            }
            final DbStatement stmt = session.createStatement("select * from policy_issue where id = $id");
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
    
    public List<String> fetchIdsByPolicy(final String policy) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> l = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from policy_issue where policy = $policy");
            stmt.setString("policy", policy);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                l.add(result.getString("id"));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return l;
    }
    
    public List<PolicyIssue> fetchByPolicyAndCategory(final String policy, final PolicyIssue.Category cat) throws DbException {
        final DbSession session = this.getDbSession();
        final List<PolicyIssue> l = new ArrayList<PolicyIssue>();
        try {
            final DbStatement stmt = session.createStatement("select * from policy_issue where policy = $policy and category = $category");
            stmt.setString("policy", policy);
            stmt.setString("category", cat.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                l.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return l;
    }
    
    public void insert(final PolicyIssue issue) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement(PolicyIssueDbAccess.insert);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", issue.getId());
            stmt.setString("policy", issue.getPolicy().getId());
            stmt.setString("policyType", issue.getPolicy().getEntityType().name());
            stmt.setString("type", issue.getType().name());
            stmt.setString("category", issue.getCategory().name());
            stmt.setString("severity", issue.getSeverity().name());
            stmt.setString("entity", issue.getEntityReference().getEncoded());
            stmt.setString("description", issue.getDescription());
            stmt.setString("rule", issue.getRule().getEncoded());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteIssuesByCategory(final String policy, final PolicyIssue.Category cat) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement(PolicyIssueDbAccess.deleteIssuesByCategory);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("policy", policy);
            stmt.setString("category", cat.name());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final PolicyIssue issue) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement(PolicyIssueDbAccess.delete);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", issue.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public int countIssuesWithSeverity(final List<String> sites, final PolicyIssue.Severity severity) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select count(id) from policy_issue \nwhere policy in (select distinct(id) from policy where phase = 'working' and site in $site) \n      and severity = $severity");
            stmt.setString("site", sites);
            stmt.setString("severity", severity.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger();
            }
        }
        finally {
            session.close();
        }
        session.close();
        return -1;
    }
    
    private PolicyIssue build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final PolicyIssue issue = new PolicyIssue(PolicyIssue.Type.valueOf(result.getString("type")), result.getString("id"));
            issue.trim();
            return issue;
        }
        try {
            final PolicyIssue issue = new PolicyIssue(PolicyIssue.Type.valueOf(result.getString("type")), result.getString("id"));
            issue.setPolicy(new EntityReference(EntityFactory.EntityType.valueOf(result.getString("policy_type")), result.getString("policy")));
            issue.setRule(new EntityReference(result.getString("rule")));
            issue.setCategory(PolicyIssue.Category.valueOf(result.getString("category")));
            issue.setSeverity(PolicyIssue.Severity.valueOf(result.getString("severity")));
            issue.setDescription(result.getString("description"));
            issue.setEntity(new EntityReference(result.getString("entity")));
            return issue;
        }
        catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }
}
