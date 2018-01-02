// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.Device;
import java.util.List;
import org.xmodel.log.Log;

public class DeployAction extends AdminAction
{
    static final Log log;
    
    static {
        log = Log.getLog(DeployAction.class);
    }
    
    public void deployDevice(final List<Device> devices) throws Exception {
        for (final Device device : devices) {
            try {
                if (this.router.hasDevice(device)) {
                    continue;
                }
                this.router.addDevice(device);
            }
            catch (Exception e) {
                DeployAction.log.error("Cannot deploy device: " + device.getId(), e);
                throw e;
            }
        }
    }
}
