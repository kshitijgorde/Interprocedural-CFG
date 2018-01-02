// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.rmi.RMIClient;
import com.stonewall.cornerstone.entity.PolicyServer;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class OperationOnDeviceTest extends TestCase
{
    public void testSendToDevice() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
            final Device device = new DeviceDbAccess().fetchById("643U20N7Q");
            final RMIClient client = new RMIClient(new PolicyServer());
            client.setTarget("~.ps.nbi.test.TestNBI", new Object[0]);
            client.invoke("testDeviceOperations", device, "c:/tmp/cornerstone/cachingPolicy/deviceConfig.xml", "c:/tmp/cornerstone/cachingPolicy/deviceConfig.xml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
