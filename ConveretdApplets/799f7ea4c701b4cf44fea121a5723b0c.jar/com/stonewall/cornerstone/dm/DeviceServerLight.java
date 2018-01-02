// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.HeartbeatComponent;
import com.stonewall.cornerstone.rmi.RMIComponent;
import com.stonewall.cornerstone.remoteServer.CommManager;
import com.stonewall.cornerstone.component.Component;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.component.ComponentServer;

public class DeviceServerLight extends ComponentServer
{
    static final Log log;
    private IModelObject bootstrap;
    
    static {
        log = Log.getLog(DeviceServerLight.class);
    }
    
    public DeviceServerLight() {
    }
    
    public DeviceServerLight(final IModelObject bootstrap) {
        this.bootstrap = bootstrap;
    }
    
    @Override
    public void init() throws Exception {
        super.init();
    }
    
    @Override
    protected void createComponents() throws Exception {
        this.addComponent(new DeviceRouter());
        this.addComponent(new DeleteAction());
        this.addComponent(new DeployAction());
        this.addComponent(new DiscoverAction());
        this.addComponent(new CommManager());
        this.addComponent(new ServerManagement());
        this.addComponent(new RMIComponent(true));
        this.addComponent(new HeartbeatComponent());
    }
    
    @Override
    protected void initComponents() throws Exception {
        DSP.init();
        super.initComponents();
    }
    
    @Override
    protected Bootstrap getBootstrap() {
        return new Bootstrap(this.bootstrap);
    }
    
    public static void main(final String[] args) {
        DeviceServerLight server = null;
        try {
            server = new DeviceServerLight();
            server.init();
            DeviceServerLight.log.info(String.valueOf(serverName(server)) + " - configured");
            Thread.currentThread().join();
            DeviceServerLight.log.info(String.valueOf(serverName(server)) + " - finished");
        }
        catch (Exception e) {
            DeviceServerLight.log.fatal(String.valueOf(serverName(server)) + " - terminated", e);
            server.halt(1);
        }
    }
    
    protected static String serverName(final ComponentServer server) {
        return "Server (" + server.getName() + ") sn: " + server.getSerialNumber();
    }
}
