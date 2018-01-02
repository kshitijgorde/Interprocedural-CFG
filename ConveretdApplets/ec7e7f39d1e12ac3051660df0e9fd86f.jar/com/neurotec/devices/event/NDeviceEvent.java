// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices.event;

import com.neurotec.devices.NDevice;
import java.util.EventObject;

public final class NDeviceEvent extends EventObject
{
    private NDevice device;
    
    public NDeviceEvent(final Object source, final NDevice device) {
        super(source);
        this.device = device;
    }
    
    public NDevice getDevice() {
        return this.device;
    }
}
