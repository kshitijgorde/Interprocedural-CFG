// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.compliance;

import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;

public class RuleSet extends com.stonewall.cornerstone.entity.policy.RuleSet
{
    public RuleSet() {
    }
    
    public RuleSet(final IModelObject root) {
        super(root);
    }
    
    public RuleSet(final EntityReference ref, final IModelObject root) {
        super(ref, root);
    }
    
    @Override
    protected ComplianceRule buildRule(final IModelObject e) {
        return new ComplianceRule(e);
    }
    
    @Override
    public RuleSet clone() {
        return new RuleSet(this.root.cloneTree());
    }
}
