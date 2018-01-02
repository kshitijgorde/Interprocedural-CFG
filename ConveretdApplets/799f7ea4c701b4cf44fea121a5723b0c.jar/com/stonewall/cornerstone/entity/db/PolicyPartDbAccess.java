// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collections;
import java.util.Iterator;
import com.stonewall.cornerstone.db.QueryBuilder;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import com.stonewall.cornerstone.entity.policy.nat.NatRulePart;
import com.stonewall.cornerstone.entity.policy.security.SitePolicy;
import com.stonewall.cornerstone.entity.policy.security.DevicePolicy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicyPart;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.policy.PolicyPart;
import java.util.List;
import java.util.Collection;
import com.stonewall.cornerstone.entity.EntityFactory;

public class PolicyPartDbAccess extends DbAccess
{
    static final String insertPart = "insert into policy_part \n(id, policy, policy_type, entity, entity_type )\nvalues $values";
    static final String deletePartsByPolicy = "delete from policy_part where policy = $policy ";
    static final String fetchPartsById = "select * from policy_part where entity_type = $entityType and entity in $entities";
    static final String policyPartValues = "($id, $policy, $policyType, $entity, $entityType )";
    static final String inUse = "select * from policy_part where entity in $entities";
    static final String[] enclosures;
    
    static {
        enclosures = new String[] { "", ",\n", "" };
    }
    
    public PolicyPartDbAccess() {
    }
    
    PolicyPartDbAccess(final String db) {
        super(db);
    }
    
    public List<PolicyPart> fetchPartById(final EntityFactory.EntityType type, final Collection<String> entityIds) throws DbException {
        final DbSession session = this.getDbSession();
        final List<PolicyPart> parts = new ArrayList<PolicyPart>();
        try {
            final DbStatement stmt = session.createStatement("select * from policy_part where entity_type = $entityType and entity in $entities");
            stmt.setString("entities", entityIds);
            stmt.setString("entityType", type.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                parts.add(this.buildPart(result));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return parts;
    }
    
    private PolicyPart buildPart(final DbResultSet result) throws DbException {
        PolicyPart part = null;
        final Policy.Type pType = Policy.Type.valueOf(result.getString("policy_type"));
        Policy policy = null;
        switch (pType) {
            case device: {
                part = new SecurityPolicyPart();
                policy = new DevicePolicy(result.getString("policy"), DeployablePolicy.Phase.working);
                break;
            }
            case site: {
                part = new SecurityPolicyPart();
                policy = new SitePolicy(result.getString("policy"), DeployablePolicy.Phase.working);
                break;
            }
            case nat: {
                part = new NatRulePart();
                policy = new NatPolicy(result.getString("policy"), DeployablePolicy.Phase.working);
                break;
            }
            default: {
                PolicyPartDbAccess.log.error("Unknown policy type" + pType);
                break;
            }
        }
        part.setPolicy(policy);
        part.setId(result.getString("id"));
        final EntityReference eRef = new EntityReference(EntityFactory.EntityType.valueOf(result.getString("entity_type")), result.getString("entity"));
        part.setEntity(eRef);
        return part;
    }
    
    void insert(final List<PolicyPart> parts) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final List<String> values = new ArrayList<String>();
            final QueryBuilder query = db.queryBuilder("($id, $policy, $policyType, $entity, $entityType )");
            for (final PolicyPart part : parts) {
                values.add(this.buildPartValues(query, part));
            }
            if (values.size() > 0) {
                final DbStatement stmt = db.createStatement("insert into policy_part \n(id, policy, policy_type, entity, entity_type )\nvalues $values");
                stmt.set(DbStatement.Mode.Update);
                stmt.queryBuilder().collectionEnclosure(PolicyPartDbAccess.enclosures);
                stmt.set("values", values);
                stmt.execute();
            }
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private String buildPartValues(final QueryBuilder query, final PolicyPart part) {
        query.setString("id", part.getId());
        query.setString("policy", part.getPolicy().getId());
        query.setString("policyType", part.getPolicy().getType().name());
        final EntityReference eRef = part.getEntity();
        query.setString("entity", eRef.getId());
        query.setString("entityType", eRef.getEntityType().name());
        return query.toString();
    }
    
    void deleteByPolicy(final String policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from policy_part where policy = $policy ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("policy", policy);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public boolean inUse(final String id) throws DbException {
        return this.inUse(Collections.singletonList(id));
    }
    
    public boolean inUse(final List<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from policy_part where entity in $entities");
            stmt.setString("entities", ids);
            final DbResultSet result = stmt.execute();
            return result.next();
        }
        finally {
            session.close();
        }
    }
}
