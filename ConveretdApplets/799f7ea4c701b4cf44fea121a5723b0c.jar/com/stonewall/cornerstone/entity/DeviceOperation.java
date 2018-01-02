// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Element;
import org.xmodel.IModelObject;

public class DeviceOperation extends Entity
{
    public static String tag;
    
    static {
        DeviceOperation.tag = EntityFactory.EntityType.deviceOperation.getQualifiedName();
    }
    
    public DeviceOperation() {
        super(DeviceOperation.tag);
        this.init();
    }
    
    public DeviceOperation(final String id) {
        super(DeviceOperation.tag, id);
        this.init();
    }
    
    public DeviceOperation(final IModelObject root) {
        super(root);
    }
    
    protected void init() {
        this.root.addChild(new Element("en:commands"));
        this.root.addChild(new Element("en:attachment"));
    }
}
