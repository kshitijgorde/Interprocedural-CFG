// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.entity.IPInterface;
import java.util.List;
import com.stonewall.cornerstone.remoteServer.Request;

public class UpdateInterfacesRequest extends Request
{
    UpdateInterfacesRequest(final String id, final List<IPInterface> ifaces) {
        super(null, false, "~.ps.nbi.core.DeviceManagerNBI", "updateInterfaces", new Object[] { id, ifaces });
    }
}
