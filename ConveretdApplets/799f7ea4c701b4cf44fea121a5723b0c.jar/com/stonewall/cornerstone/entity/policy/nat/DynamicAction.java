// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import org.xmodel.IModelObject;

public class DynamicAction extends Action
{
    protected static String tag;
    
    static {
        DynamicAction.tag = "en:dynamic";
    }
    
    public DynamicAction() {
        super(DynamicAction.tag);
    }
    
    public DynamicAction(final IModelObject root) {
        super(root);
    }
    
    public boolean isGroup() {
        return this.root.getFirstChild("en:addressGroup") != null;
    }
    
    public String getId() {
        IModelObject o = null;
        if (this.isGroup()) {
            o = this.root.getFirstChild("en:addressGroup");
        }
        else {
            o = this.root.getFirstChild("en:addressRange");
        }
        return o.getID();
    }
}
