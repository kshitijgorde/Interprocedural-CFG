// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.component.Component;

public class AdminAction implements Component
{
    protected DeviceRouter router;
    static final Log log;
    
    static {
        log = Log.getLog(AdminAction.class);
    }
    
    @Override
    public void init(final ComponentServer server) throws Exception {
        this.router = (DeviceRouter)server.getComponent(DeviceRouter.class);
    }
    
    @Override
    public void shutdown() {
    }
    
    @Override
    public void trace() {
        AdminAction.log.warn("Not Implemented");
    }
}
