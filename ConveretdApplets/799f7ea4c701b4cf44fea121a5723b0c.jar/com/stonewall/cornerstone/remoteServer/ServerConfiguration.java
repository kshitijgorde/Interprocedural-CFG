// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class ServerConfiguration
{
    private IModelObject root;
    
    public ServerConfiguration(final String id, final String name, final String queue) {
        this.root = new Element("en:serverConfig");
        this.setId(id);
        this.setName(name);
        this.setQueue(queue);
    }
    
    public ServerConfiguration(final IModelObject root) {
        this.root = root;
    }
    
    public void setId(final String id) {
        this.root.getCreateChild("en:id").setValue(id);
    }
    
    public String getId() {
        return Xlate.get(this.root.getFirstChild("en:id"), (String)null);
    }
    
    public void setName(final String name) {
        this.root.getCreateChild("en:name").setValue(name);
    }
    
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setQueue(final String queue) {
        this.root.getCreateChild("en:queue").setValue(queue);
    }
    
    public String getQueue() {
        return Xlate.get(this.root.getFirstChild("en:queue"), (String)null);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
}
