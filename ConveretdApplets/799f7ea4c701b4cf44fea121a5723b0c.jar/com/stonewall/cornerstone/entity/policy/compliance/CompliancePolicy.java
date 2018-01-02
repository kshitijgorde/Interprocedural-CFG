// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.compliance;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.CompliancePolicyDbAccess;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.Label;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.Policy;

public class CompliancePolicy extends Policy
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.compliancePolicy.getQualifiedName();
    }
    
    public CompliancePolicy() {
        super(CompliancePolicy.tag, Type.compliance);
    }
    
    public CompliancePolicy(final IModelObject root) {
        super(root);
    }
    
    public CompliancePolicy(final String id) {
        super(CompliancePolicy.tag, id, Type.compliance);
    }
    
    @Override
    public RuleSet getRuleSet() {
        return new RuleSet(this.getReference(), this.root.getFirstChild("en:ruleSet"));
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new CompliancePolicyDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new CompliancePolicyDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new CompliancePolicyDbAccess().update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new CompliancePolicyDbAccess().delete(this);
    }
    
    @Override
    public String getName() {
        return Xlate.childGet(this.root, "en:name", "");
    }
    
    public void setName(final String name) {
        this.root.getCreateChild("en:name").setValue(name);
    }
    
    @Override
    public Policy clone() {
        return new CompliancePolicy(this.root);
    }
}
