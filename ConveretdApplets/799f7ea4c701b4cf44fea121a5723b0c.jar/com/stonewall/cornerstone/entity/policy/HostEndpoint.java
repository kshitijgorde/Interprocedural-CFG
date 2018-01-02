// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.HostDbAccess;
import com.stonewall.cornerstone.entity.Host;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;

public class HostEndpoint extends Endpoint
{
    public HostEndpoint(final EntityReference ref) {
        super(ref);
    }
    
    public HostEndpoint(final IModelObject root) {
        super(root);
    }
    
    public HostEndpoint(final Host host) {
        super(host.getRoot());
    }
    
    @Override
    public String toErrorString() {
        try {
            final Host host = new HostDbAccess().fetchNameAndIP(this.getId());
            return "Host:" + host.getDisplayName();
        }
        catch (DbException dbe) {
            HostEndpoint.log.error(this, dbe);
            return "";
        }
    }
}
