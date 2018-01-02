// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;

public class RuleSet extends com.stonewall.cornerstone.entity.policy.RuleSet
{
    public RuleSet() {
    }
    
    public RuleSet(final EntityReference ref, final IModelObject root) {
        super(ref, root);
    }
    
    public RuleSet(final IModelObject root) {
        super(root);
    }
    
    @Override
    public RuleSet clone() {
        return new RuleSet(this.root.cloneTree());
    }
    
    public static NatRule create(final IModelObject e) {
        return new NatRule(e);
    }
    
    @Override
    protected NatRule buildRule(final IModelObject e) {
        final NatRule rule = create(e);
        rule.setParent(this.parent);
        return rule;
    }
}
