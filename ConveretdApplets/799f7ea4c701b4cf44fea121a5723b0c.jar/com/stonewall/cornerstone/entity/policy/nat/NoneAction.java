// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import org.xmodel.IModelObject;

public class NoneAction extends Action
{
    protected static String tag;
    
    static {
        NoneAction.tag = "en:none";
    }
    
    public NoneAction() {
        super(NoneAction.tag);
    }
    
    public NoneAction(final IModelObject root) {
        super(root);
    }
}
