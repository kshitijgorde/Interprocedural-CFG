// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.remoteServer.Request;

public class DeployedDevicesRequest extends Request
{
    DeployedDevicesRequest(final String id) {
        super(null, true, "~.ps.nbi.core.DeviceManagerNBI", "getDeployedDevices", new Object[] { id });
    }
}
