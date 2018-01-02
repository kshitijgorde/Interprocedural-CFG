// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import org.xmodel.IModelObject;

public class DevicePolicy extends SecurityPolicy
{
    public DevicePolicy(final IModelObject root) {
        super(root);
    }
    
    public DevicePolicy(final Phase phase) {
        super(phase, Type.device);
    }
    
    public DevicePolicy(final String id, final Phase phase) {
        super(id, phase, Type.device);
    }
}
