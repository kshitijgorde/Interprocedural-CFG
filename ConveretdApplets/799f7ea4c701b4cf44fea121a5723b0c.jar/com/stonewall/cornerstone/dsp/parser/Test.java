// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import org.xmodel.IModelObject;
import java.io.InputStream;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.entity.DeviceConfig;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class Test extends TestCase
{
    public void test() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("com/stonewall/cornerstone/dsp/parser/newConfig.xml");
            final ModelBuilder builder = new ModelBuilder(ModelBuilder.Validation.none);
            final IModelObject doc1 = builder.buildModel(is);
            final DeviceConfig newC = new DeviceConfig(doc1);
            is = this.getClass().getClassLoader().getResourceAsStream("com/stonewall/cornerstone/dsp/parser/oldConfig.xml");
            final IModelObject doc2 = builder.buildModel(is);
            final DeviceConfig oldC = new DeviceConfig(doc2);
            final ConfigProcessor cp = new ConfigProcessor(new Loader("screenos_v5_1_0", "ns5gt"));
            cp.postParse(newC, oldC);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
