// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.entity.AddressGroup;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.AddressGroupDbAccess;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;

public class AddressGroupEndpoint extends Endpoint
{
    public AddressGroupEndpoint(final EntityReference ref) {
        super(ref);
    }
    
    public AddressGroupEndpoint(final IModelObject root) {
        super(root);
    }
    
    @Override
    public String toErrorString() {
        try {
            final AddressGroup ag = new AddressGroupDbAccess().fetchById(this.getId());
            return "Address Group:" + ag.getDisplayName();
        }
        catch (DbException dbe) {
            AddressGroupEndpoint.log.error(this, dbe);
            return "";
        }
    }
}
