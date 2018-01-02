// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.NetworkDbAccess;
import com.stonewall.cornerstone.entity.Network;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;

public class NetworkEndpoint extends Endpoint
{
    public NetworkEndpoint(final EntityReference ref) {
        super(ref);
    }
    
    public NetworkEndpoint(final IModelObject root) {
        super(root);
    }
    
    public NetworkEndpoint(final Network network) {
        super(network.getRoot());
    }
    
    @Override
    public String toErrorString() {
        try {
            final Network network = new NetworkDbAccess().fetchNameAndIP(this.getId());
            return "Network:" + network.getDisplayName();
        }
        catch (DbException dbe) {
            NetworkEndpoint.log.error(this, dbe);
            return "";
        }
    }
    
    @Override
    public boolean isInternet() {
        return this.getId().equals("INTERNET");
    }
}
