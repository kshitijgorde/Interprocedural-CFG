// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.ModelObject;
import org.xmodel.IModelObject;

public class Gateway
{
    public IModelObject root;
    
    public Gateway(final String tag) {
        this.root = new ModelObject(tag);
    }
    
    public Gateway(final IModelObject e) {
        this.root = e;
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
}
