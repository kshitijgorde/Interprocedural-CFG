// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import com.stonewall.cornerstone.dsp.command.OperationInterpreter;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.DeviceConfig;
import java.io.InputStream;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.io.FileInputStream;
import java.io.File;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class OperationTest extends TestCase
{
    public void test() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
            final File f1 = new File("c:/tmp/cornerstone/cachingPolicy/deviceConfig.xml");
            FileInputStream fis = new FileInputStream(f1);
            final ModelBuilder builder = new ModelBuilder(ModelBuilder.Validation.none);
            final IModelObject doc1 = builder.buildModel(fis);
            final DeviceConfig pending = new DeviceConfig(doc1);
            final File f2 = new File("c:/tmp/cornerstone/cachingPolicy/deviceConfig2.xml");
            fis = new FileInputStream(f2);
            final IModelObject doc2 = builder.buildModel(fis);
            final DeviceConfig current = new DeviceConfig(doc2);
            final Device device = new Device();
            device.setHardware("ns5gt");
            device.setSoftware("screenos_v5_1_0");
            final OperationInterpreter oi = new OperationInterpreter(device);
            final List<DeviceOperation> operations = oi.generateOperations(pending, current);
            for (final DeviceOperation op : operations) {
                for (final DeviceCommand c : op.getCommands()) {
                    System.out.println(c);
                }
            }
            System.out.println(pending);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
