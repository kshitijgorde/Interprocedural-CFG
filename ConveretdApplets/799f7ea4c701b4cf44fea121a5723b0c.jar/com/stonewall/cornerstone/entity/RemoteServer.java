// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.rmi.Destination;
import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.entity.db.QueueDbAccess;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public abstract class RemoteServer extends Server
{
    public RemoteServer(final IModelObject root) {
        super(root);
    }
    
    public RemoteServer(final String tag) {
        super(tag);
    }
    
    public RemoteServer(final String tag, final String id) {
        super(tag, id);
    }
    
    @Override
    protected void init() {
        this.root.getCreateChild("en:name");
        this.root.getCreateChild("en:queue");
        final IModelObject state = this.root.getCreateChild("en:state");
        state.getCreateChild("en:admin").setValue(AdminState.enabled.name());
        state.getCreateChild("en:operational").setValue(OperationalState.connected.name());
        super.init();
    }
    
    public void setName(final String name) {
        this.setChild(this.root, "en:name", name);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setQueue(final String id) {
        this.setChild(this.root, "en:queue", id);
    }
    
    public String getQueue() {
        return Xlate.get(this.root.getFirstChild("en:queue"), (String)null);
    }
    
    public OperationalState getOperationalState() {
        return OperationalState.valueOf(Xlate.get(this.root.getFirstChild("en:state").getFirstChild("en:operational"), (String)null));
    }
    
    public void setOperationalState(final OperationalState state) {
        this.root.getCreateChild("en:state").getCreateChild("en:operational").setValue(state.name());
    }
    
    public AdminState getAdminState() {
        return AdminState.valueOf(Xlate.get(this.root.getFirstChild("en:state").getFirstChild("en:admin"), (String)null));
    }
    
    public void setAdminState(final AdminState state) {
        this.root.getCreateChild("en:state").getCreateChild("en:admin").setValue(state.name());
    }
    
    public boolean isEnabled() {
        return this.getAdminState().equals(AdminState.enabled);
    }
    
    public abstract QueueDbAccess.Pool getPoolType();
    
    public void setType(final ComponentServer.Type type) {
        this.root.setAttribute("type", type.name());
    }
    
    public ComponentServer.Type getType() {
        return ComponentServer.Type.valueOf(Xlate.get(this.root, "type", (String)null));
    }
    
    @Override
    public Destination getRMIDestination() {
        return new Destination(Destination.Type.rs, this.getQueue());
    }
    
    @Override
    public boolean isLocal(final ComponentServer server) {
        return this.getSerialNumber().equals(server.getSerialNumber());
    }
    
    public enum AdminState
    {
        enabled("enabled", 0), 
        disabled("disabled", 1);
        
        private AdminState(final String s, final int n) {
        }
    }
    
    public enum OperationalState
    {
        disconnected("disconnected", 0), 
        connected("connected", 1), 
        startingUp("startingUp", 2);
        
        private OperationalState(final String s, final int n) {
        }
    }
}
