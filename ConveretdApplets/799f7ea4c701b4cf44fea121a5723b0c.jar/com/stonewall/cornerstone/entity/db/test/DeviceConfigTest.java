// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import java.util.List;
import com.stonewall.cornerstone.entity.DeviceConfig;
import com.stonewall.cornerstone.entity.db.DeviceConfigDbAccess;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class DeviceConfigTest extends TestCase
{
    public void testFetchLatestForParent() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DeviceConfig config = new DeviceConfigDbAccess().fetchByParent("COVKZ027X");
            assertTrue(config != null);
            System.out.println(config);
            config = new DeviceConfigDbAccess().fetchByParent("COVKZ027X", true);
            assertTrue(config != null);
            System.out.println(config);
            List<DeviceConfig> configs = new DeviceConfigDbAccess().fetchAll();
            assertTrue(config != null);
            System.out.println(configs);
            configs = new DeviceConfigDbAccess().fetchAll(true);
            assertTrue(config != null);
            System.out.println(configs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
