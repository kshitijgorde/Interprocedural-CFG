// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.entity.IPInterface;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;

public class IPInterfaceEndpoint extends Endpoint
{
    public IPInterfaceEndpoint(final EntityReference ref) {
        super(ref);
    }
    
    public IPInterfaceEndpoint(final IModelObject root) {
        super(root);
    }
    
    @Override
    public String toErrorString() {
        final IPInterface iface = (IPInterface)this.getReferent();
        return "IP Interface:" + iface.getIdentifier();
    }
    
    public EntityReference getInterface() {
        return new EntityReference(this.root);
    }
}
