// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import com.stonewall.cornerstone.utility.Namespaces;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class Action
{
    protected IModelObject root;
    protected static final Log log;
    
    static {
        log = NatPolicy.log;
    }
    
    public Action(final String tag) {
        this.root = new Element(tag);
    }
    
    public Action(final IModelObject root) {
        this.root = root;
    }
    
    public Type getType() {
        return Type.valueOf(this.root);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public enum Type
    {
        direct("direct", 0, "Direct"), 
        dynamic("dynamic", 1, "Dynamic"), 
        egress("egress", 2, "Egress"), 
        none("none", 3, "None");
        
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
