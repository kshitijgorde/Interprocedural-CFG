// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.PolicyIssueDbAccess;
import java.util.List;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.Entity;

public class PolicyIssue extends Entity
{
    protected Entity entity;
    private static final String tag;
    
    static {
        tag = EntityFactory.EntityType.policyIssue.getQualifiedName();
    }
    
    public static boolean allowPolicyPush(final List<String> sites) {
        try {
            final int issueCount = new PolicyIssueDbAccess().countIssuesWithSeverity(sites, Severity.error);
            return issueCount <= 0;
        }
        catch (DbException dbe) {
            return false;
        }
    }
    
    public PolicyIssue(final IModelObject e) {
        super(e);
    }
    
    public PolicyIssue(final String id) {
        super(PolicyIssue.tag, id);
    }
    
    public PolicyIssue(final Type type, final String id) {
        super(PolicyIssue.tag, id);
        this.setType(type);
    }
    
    public PolicyIssue(final Type type, final PolicyRule rule, final Category cat, final Severity severity, final Entity entity) {
        super(PolicyIssue.tag);
        this.setType(type);
        this.setCategory(cat);
        this.setSeverity(severity);
        this.setEntity(entity);
        this.setPolicy(rule.getParent());
        this.setRule(rule.getReference());
    }
    
    public PolicyIssue(final Type type, final EntityReference policy, final Category cat, final Severity severity, final Entity entity) {
        super(PolicyIssue.tag);
        this.setType(type);
        this.setCategory(cat);
        this.setSeverity(severity);
        this.setEntity(entity);
        this.setPolicy(policy);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new PolicyIssueDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new PolicyIssueDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new PolicyIssueDbAccess(label).delete(this);
    }
    
    public void setType(final Type type) {
        this.root.getCreateChild("en:type").setValue(type.name());
    }
    
    public Type getType() {
        final String type = Xlate.get(this.root.getFirstChild("en:type"), (String)null);
        return Type.valueOf(type);
    }
    
    public void setDescription(final String s) {
        this.root.getCreateChild("en:description").setValue(s);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void setCategory(final Category cat) {
        this.root.getCreateChild("en:category").setValue(cat.name());
    }
    
    public Category getCategory() {
        return Category.valueOf(Xlate.get(this.root.getFirstChild("en:category"), (String)null));
    }
    
    public void setSeverity(final Severity sev) {
        this.root.getCreateChild("en:severity").setValue(sev.name());
    }
    
    public Severity getSeverity() {
        return Severity.valueOf(Xlate.get(this.root.getFirstChild("en:severity"), (String)null));
    }
    
    public EntityReference getPolicy() {
        final IModelObject ref = this.root.getFirstChild("en:policy").getChildren().get(0);
        return new EntityReference(ref);
    }
    
    public void setPolicy(final EntityReference ref) {
        this.root.getCreateChild("en:policy").addChild(ref.cloneContent());
    }
    
    public EntityReference getRule() {
        final IModelObject ref = this.root.getFirstChild("en:rule").getChildren().get(0);
        return new EntityReference(ref);
    }
    
    public void setRule(final EntityReference ref) {
        this.root.getCreateChild("en:rule").addChild(ref.cloneContent());
    }
    
    public Entity getEntity() {
        if (this.entity == null) {
            this.entity = this.getEntityReference().getReferent();
        }
        return this.entity;
    }
    
    public EntityReference getEntityReference() {
        final IModelObject entity = this.root.getFirstChild("en:entity");
        return new EntityReference(entity.getChildren().get(0));
    }
    
    public void setEntity(final Entity entity) {
        this.entity = entity;
        if (entity != null) {
            this.setEntity(entity.getReference());
        }
    }
    
    public void setEntity(final EntityReference ref) {
        final IModelObject e = this.root.getCreateChild("en:entity");
        e.addChild(ref.cloneContent());
    }
    
    public PolicyIssueRepairer getRepairer() {
        return null;
    }
    
    public enum Category
    {
        content("content", 0), 
        dataIntegrity("dataIntegrity", 1), 
        admin("admin", 2);
        
        private Category(final String s, final int n) {
        }
    }
    
    public enum Severity
    {
        error("error", 0), 
        warning("warning", 1), 
        info("info", 2);
        
        private Severity(final String s, final int n) {
        }
    }
    
    public enum Type
    {
        invalidPath("invalidPath", 0), 
        ipEndpoint("ipEndpoint", 1), 
        missingPath("missingPath", 2), 
        nonSecureFilterPath("nonSecureFilterPath", 3), 
        sameEndpoint("sameEndpoint", 4), 
        secureFilterPath("secureFilterPath", 5), 
        secureRouting("secureRouting", 6), 
        unresolvedEndpoint("unresolvedEndpoint", 7), 
        unresolvedEntity("unresolvedEntity", 8), 
        unresolvedPath("unresolvedPath", 9);
        
        private Type(final String s, final int n) {
        }
    }
}
