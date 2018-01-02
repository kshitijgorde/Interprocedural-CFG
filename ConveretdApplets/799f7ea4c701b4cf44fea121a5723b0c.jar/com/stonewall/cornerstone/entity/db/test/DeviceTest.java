// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.entity.Device;
import java.util.Collection;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class DeviceTest extends TestCase
{
    public void testCountDevices() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final int count = new DeviceDbAccess().countDevices("1");
            assertTrue(count == 7);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testFetchAllByDeviceManager() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Collection<Device> devices = new DeviceDbAccess().fetchDeployedByDeviceManager("1");
            assertTrue(devices.size() == 7);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testFetchById() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Device device = new DeviceDbAccess().fetchById("101");
            assertNotNull((Object)device);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testFetchAllBySite() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Collection<Device> devices = new DeviceDbAccess().fetchAllBySite("123");
            assertTrue(devices.size() == 2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testFetchedDeployedBySite() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Collection<Device> devices = new DeviceDbAccess().fetchAllBySite("123");
            assertTrue(devices.size() == 2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testUpdateAlarmSeverity() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            new DeviceDbAccess().updateAlarmSeverity("101", Alarm.Severity.major);
            final Device device = new DeviceDbAccess().fetchById("101");
            assertTrue(device.getHighestAlarmSeverity().equals(Alarm.Severity.major));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
