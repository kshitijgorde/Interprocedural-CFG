// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.IPInterface;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.util.IpAddr;

public class RemoteGateway extends Gateway
{
    public RemoteGateway(final IpAddr addr) {
        super("en:remote");
        this.root.setValue(addr.getAddressString());
    }
    
    public RemoteGateway(final IModelObject e) {
        super(e);
    }
    
    public RemoteGateway(final SecurityTunnel.Interface iface) {
        this(((IPInterface)iface.getEndpoint().getReferent()).getIpAddress());
    }
    
    public IpAddr getIpAddress() {
        try {
            return new IpAddr(Xlate.get(this.root, (String)null));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof RemoteGateway) {
            final RemoteGateway gateway = (RemoteGateway)o;
            if (this.getIpAddress().equals((Object)gateway.getIpAddress())) {
                return true;
            }
        }
        return false;
    }
}
