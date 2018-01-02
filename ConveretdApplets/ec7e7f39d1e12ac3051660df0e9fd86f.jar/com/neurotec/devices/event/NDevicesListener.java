// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices.event;

import java.util.EventObject;
import com.neurotec.event.NChangeListener;

public interface NDevicesListener extends NChangeListener
{
    void deviceAdded(final NDeviceEvent p0);
    
    void deviceRemoved(final NDeviceEvent p0);
    
    void devicesRefreshed(final EventObject p0);
}
