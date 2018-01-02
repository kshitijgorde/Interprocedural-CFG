// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Element;
import org.xmodel.IModelObject;

public abstract class Transform
{
    protected IModelObject root;
    
    public static Transform createTransform(final IModelObject root) {
        if (root.getType().equals(ESPTransform.tag)) {
            return new ESPTransform(root);
        }
        if (root.getType().equals(AHTransform.tag)) {
            return new AHTransform(root);
        }
        return null;
    }
    
    public Transform(final String tag) {
        this.root = new Element(tag);
    }
    
    public Transform(final IModelObject root) {
        this.root = root;
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public abstract Type getType();
    
    public enum Type
    {
        ah("ah", 0), 
        esp("esp", 1);
        
        private Type(final String s, final int n) {
        }
    }
}
