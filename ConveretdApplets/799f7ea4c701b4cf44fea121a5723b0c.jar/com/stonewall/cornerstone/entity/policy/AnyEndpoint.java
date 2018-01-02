// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;

public class AnyEndpoint extends Endpoint
{
    public AnyEndpoint() {
        super(new EntityReference(EntityFactory.EntityType.any, "ANY"));
    }
    
    public AnyEndpoint(final String id) {
        super(new EntityReference(EntityFactory.EntityType.any, id));
    }
    
    public AnyEndpoint(final IModelObject root) {
        super(root);
    }
    
    @Override
    public String toErrorString() {
        return "Any";
    }
    
    @Override
    public boolean isInternet() {
        return false;
    }
    
    @Override
    public boolean isAny() {
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Endpoint)) {
            return false;
        }
        final Endpoint ep = (Endpoint)o;
        return this.isAny() && ep.isAny();
    }
}
