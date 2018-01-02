// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import org.xmodel.IModelObject;

public class EgressAction extends Action
{
    protected static String tag;
    
    static {
        EgressAction.tag = "en:egress";
    }
    
    public EgressAction() {
        super(EgressAction.tag);
    }
    
    public EgressAction(final IModelObject root) {
        super(root);
    }
}
