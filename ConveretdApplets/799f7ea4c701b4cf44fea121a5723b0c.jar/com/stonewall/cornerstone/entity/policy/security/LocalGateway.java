// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.IPInterface;

public class LocalGateway extends Gateway
{
    public LocalGateway(final SecurityTunnel.Interface iface) {
        super("en:local");
        this.root.getCreateChild("en:ipInterface").setAttribute("id", iface.getEndpoint().getId());
    }
    
    public LocalGateway(final IPInterface iface) {
        super("en:local");
        this.root.getCreateChild("en:ipInterface").setAttribute("id", iface.getId());
    }
    
    public LocalGateway(final IModelObject e) {
        super(e);
    }
    
    public String getId() {
        return Xlate.get(this.root.getFirstChild("en:ipInterface"), "id", (String)null);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof LocalGateway) {
            final LocalGateway gateway = (LocalGateway)o;
            if (this.getId().equals(gateway.getId())) {
                return true;
            }
        }
        return false;
    }
}
