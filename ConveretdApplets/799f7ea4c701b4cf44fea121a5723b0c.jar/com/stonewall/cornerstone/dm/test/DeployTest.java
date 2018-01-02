// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.test;

import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.rmi.RMIClient;
import com.stonewall.cornerstone.entity.DeviceManager;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.Protocol;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class DeployTest extends TestCase
{
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testDeployPass() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Protocol p = new Protocol(Protocol.Type.telnet);
            p.setUsername("netscreen");
            p.setPassword("cyberwerx");
            final Device device = new Device();
            device.setIpAddress(new IpAddr("192.168.10.11"));
            device.setProtocol(p);
            device.setDeviceManagerId("2");
            device.setHardware("ns5gt");
            device.setVendor("juniper");
            device.setSoftware("screenos_v5_1_0");
            final DeviceManager m = new DeviceManager();
            m.setQueue("2");
            final RMIClient client = new RMIClient(m);
            client.setTarget("~.dm.DeployAction", new Object[0]);
            client.invokeNoWait("deployDevice", device);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
