// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.dsp.command.OperationInterpreter;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class CommandScriptTest extends TestCase
{
    public void testSendToDevice() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
            final Device device = new DeviceDbAccess().fetchById("66ALLKKGH");
            final OperationInterpreter oi = new OperationInterpreter(device);
            oi.generateOperation("command.test.xml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
