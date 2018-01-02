// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.utility.Namespaces;
import org.xmodel.Xlate;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.policy.Policy;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public abstract class Action
{
    protected IModelObject root;
    protected static final Log log;
    
    static {
        log = Policy.log;
    }
    
    public static Type getActionType(final IModelObject e) {
        final IModelObject action = e.getFirstChild("en:action");
        final IModelObject tE = action.getChildren().get(0);
        return Type.valueOf(tE);
    }
    
    public Action(final String tag) {
        this.root = new Element(tag);
        this.init();
    }
    
    public Action(final IModelObject root) {
        this.root = root;
    }
    
    protected void init() {
        this.root.getCreateChild("en:log").setValue("false");
    }
    
    public abstract Type getType();
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public void setLog(final boolean value) {
        this.root.getFirstChild("en:log").setValue(String.valueOf(value));
    }
    
    public boolean getLog() {
        return Xlate.get(this.root.getFirstChild("en:log"), false);
    }
    
    public void setGateway(final LocalGateway local, final RemoteGateway remote) {
        final IModelObject gateways = this.root.getCreateChild("en:gateways");
        gateways.removeChildren();
        gateways.addChild(local.getRoot());
        gateways.addChild(remote.getRoot());
    }
    
    public LocalGateway getLocalGateway() {
        try {
            return new LocalGateway(this.root.getFirstChild("en:gateways").getFirstChild("en:local"));
        }
        catch (Exception e) {
            Action.log.error(this, e);
            return null;
        }
    }
    
    public RemoteGateway getRemoteGateway() {
        try {
            return new RemoteGateway(this.root.getFirstChild("en:gateways").getFirstChild("en:remote"));
        }
        catch (Exception e) {
            Action.log.error(this, e);
            return null;
        }
    }
    
    public enum Type
    {
        filter("filter", 0, "Filter");
        
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
