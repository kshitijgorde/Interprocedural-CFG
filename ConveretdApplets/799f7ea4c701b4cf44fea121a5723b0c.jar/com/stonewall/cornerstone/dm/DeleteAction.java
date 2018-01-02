// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import java.util.Iterator;
import java.util.List;

public class DeleteAction extends AdminAction
{
    public boolean deleteDevice(final List<String> deviceIds) {
        for (final String deviceId : deviceIds) {
            this.router.removeDevice(deviceId);
        }
        return true;
    }
}
