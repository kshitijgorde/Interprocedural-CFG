// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import com.stonewall.cornerstone.component.ProductDescriptor;
import com.stonewall.cornerstone.rmi.Destination;
import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.component.Component;

public class ServerManagement implements Component
{
    static final Log log;
    
    static {
        log = Log.getLog(ServerManagement.class);
    }
    
    @Override
    public void init(final ComponentServer server) throws Exception {
        final CommManager cm = (CommManager)server.getComponent("*.CommManager");
        final String serialNo = System.getProperty("cornerstone.server.sn");
        boolean configured = false;
        while (!configured) {
            try {
                this.validateProductDescriptor(cm);
                final ConfigurationRequest r = new ConfigurationRequest(serialNo);
                final ServerConfiguration config = (ServerConfiguration)cm.send(r);
                if (config != null) {
                    ServerManagement.log.debug("Setting parameters: " + config);
                    server.setId(config.getId());
                    server.setName(config.getName());
                    new Destination(Destination.Type.rs, config.getQueue()).setLocal();
                    configured = true;
                }
            }
            catch (Exception e) {
                ServerManagement.log.error(this, e);
            }
            if (!configured) {
                ServerManagement.log.info("Server sn: (" + serialNo + "): received failed configuration reply - sleeping");
                try {
                    Thread.sleep(30000L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    @Override
    public void shutdown() {
    }
    
    @Override
    public void trace() {
        ServerManagement.log.warn("Not-Implemented");
    }
    
    protected void validateProductDescriptor(final CommManager cm) throws Exception {
        final ProductDescRequest r = new ProductDescRequest();
        final ProductDescriptor psDescriptor = (ProductDescriptor)cm.send(r);
        final ProductDescriptor descriptor = ProductDescriptor.getInstance();
        if (!descriptor.equals(psDescriptor)) {
            final String msg = "Product descriptor - mismatch [" + psDescriptor + "]";
            throw new Exception(msg);
        }
        ServerManagement.log.info("Product descriptor - matched.");
    }
    
    public void restartServer() {
        try {
            ComponentServer.getInstance().restart();
        }
        catch (Exception e) {
            ServerManagement.log.error(this, e);
        }
    }
}
