// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import org.xmodel.IModelObject;

public class SitePolicy extends SecurityPolicy
{
    public SitePolicy(final IModelObject root) {
        super(root);
    }
    
    public SitePolicy(final Phase phase) {
        super(phase, Type.site);
    }
    
    public SitePolicy(final String id, final Phase phase) {
        super(id, phase, Type.site);
    }
}
