// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class FilterAction extends Action
{
    protected static String tag;
    
    static {
        FilterAction.tag = "en:filter";
    }
    
    public FilterAction() {
        super(FilterAction.tag);
    }
    
    public FilterAction(final IModelObject root) {
        super(root);
    }
    
    public FilterAction(final String access) {
        this();
        this.setAccess(access);
    }
    
    public FilterAction(final Access access) {
        this();
        this.setAccess(access.name());
    }
    
    @Override
    protected void init() {
        super.init();
        this.root.addChild(new Element("en:access"));
        this.root.getCreateChild("en:secure").setValue("true");
    }
    
    public void setAccess(final String value) {
        this.root.getFirstChild("en:access").setValue(value);
        if (Access.deny.equals(Access.valueOf(value))) {
            this.setSecure(false);
        }
    }
    
    public String getAccess() {
        return Xlate.get(this.root.getFirstChild("en:access"), (String)null);
    }
    
    public void setSecure(final boolean flag) {
        this.root.getFirstChild("en:secure").setValue(String.valueOf(flag));
    }
    
    public boolean isSecure() {
        return Boolean.parseBoolean(Xlate.get(this.root.getFirstChild("en:secure"), (String)null));
    }
    
    public void setServiceTimeout(final int value) {
        this.root.getFirstChild("en:serviceTimeout").setValue(String.valueOf(value));
    }
    
    public int getServiceTimeout() {
        return new Integer(Xlate.get(this.root.getFirstChild("en:serviceTimeout"), (String)null));
    }
    
    @Override
    public Type getType() {
        return Type.filter;
    }
    
    public void setGateway(final Gateway local, final Gateway remote) {
    }
    
    @Override
    public LocalGateway getLocalGateway() {
        return null;
    }
    
    @Override
    public RemoteGateway getRemoteGateway() {
        return null;
    }
    
    public enum Access
    {
        permit("permit", 0), 
        deny("deny", 1);
        
        private Access(final String s, final int n) {
        }
    }
}
