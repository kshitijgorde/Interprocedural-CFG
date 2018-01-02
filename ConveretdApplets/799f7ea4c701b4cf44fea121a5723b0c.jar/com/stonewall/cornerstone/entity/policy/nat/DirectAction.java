// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.Host;
import org.xmodel.IModelObject;

public class DirectAction extends Action
{
    protected static String tag;
    
    static {
        DirectAction.tag = "en:direct";
    }
    
    public DirectAction() {
        super(DirectAction.tag);
    }
    
    public DirectAction(final IModelObject root) {
        super(root);
    }
    
    public void setHost(final Host h) {
        this.root.addChild(h.getReference().getRoot());
    }
    
    public String getHostId() {
        return this.root.getFirstChild("en:host").getID();
    }
    
    public boolean translatePort() {
        return this.root.getFirstChild("en:port") != null;
    }
    
    public int getPort() {
        return Xlate.childGet(this.root, "en:port", 0);
    }
    
    public void setPort(final int value) {
        this.root.getCreateChild("en:port").setValue(value);
    }
}
