// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public interface DeviceListener
{
    void addDeviceObserver(final DeviceObserver p0, final String p1, final Object p2);
    
    void removeDeviceObserver(final DeviceObserver p0, final String p1);
}
