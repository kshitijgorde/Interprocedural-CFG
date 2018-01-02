// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.utility.Namespaces;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.policy.Policy;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public abstract class TunnelDef
{
    protected IModelObject root;
    protected static final Log log;
    
    static {
        log = Policy.log;
    }
    
    public static Type getTunnelType(final IModelObject e) {
        final IModelObject action = e.getFirstChild("en:definition");
        final IModelObject tE = action.getChildren().get(0);
        return Type.valueOf(tE);
    }
    
    public TunnelDef(final String tag) {
        this.root = new Element(tag);
        this.init();
    }
    
    public TunnelDef(final IModelObject root) {
        this.root = root;
    }
    
    protected abstract void init();
    
    public abstract Type getType();
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public enum Type
    {
        ike("ike", 0, "Ike"), 
        manualKey("manualKey", 1, "Manual Key");
        
        private String display;
        
        private Type(final String s, final int n, final String display) {
            this.display = display;
        }
        
        public String getDisplayName() {
            return this.display;
        }
        
        public String getQualifiedName() {
            return Namespaces.enns.getQualifiedName(this.name());
        }
        
        public static Type valueOf(final IModelObject o) {
            final String tag = Namespaces.enns.getUnqualifiedName(o.getType());
            return valueOf(tag);
        }
    }
}
