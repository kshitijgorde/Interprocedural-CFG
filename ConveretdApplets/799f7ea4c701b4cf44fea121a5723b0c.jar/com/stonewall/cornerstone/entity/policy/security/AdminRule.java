// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.entity.policy.Selector;
import com.stonewall.cornerstone.tp.query.GraphQuery;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import org.xmodel.IModelObject;

public class AdminRule extends FilterRule
{
    public AdminRule(final IModelObject root) {
        super(root);
    }
    
    public AdminRule(final String name, final String description) {
        super(name, description, FilterAction.Access.permit, false);
        this.setRuleType(Type.adminACL);
        this.setParent(new EntityReference(EntityFactory.EntityType.securityPolicy, "APOLICY0"));
    }
    
    @Override
    public PolicyRule clone() {
        final IModelObject clone = this.root.cloneTree();
        final AdminRule rule = new AdminRule(clone);
        return rule;
    }
    
    @Override
    public boolean canRoute() {
        final Selector s = this.getFirstSelector();
        final Endpoint dst = s.getDestination();
        final GraphQuery query = new GraphQuery();
        return query.getInterface(dst.getId()) != null;
    }
    
    @Override
    public void setTerminating() {
        this.setRuleType(Type.adminTerminating);
    }
    
    @Override
    public boolean shouldTerminate() {
        return true;
    }
}
