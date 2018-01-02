// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Driver;

public class JDBCDriverUnloader implements Runnable, Iterable<Driver>
{
    public void run() {
        for (final Driver d : this) {
            try {
                DriverManager.deregisterDriver(d);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public Iterator<Driver> iterator() {
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        final ArrayList<Driver> driverList = new ArrayList<Driver>();
        while (drivers.hasMoreElements()) {
            final Driver d = drivers.nextElement();
            if (d.getClass().getClassLoader() == JDBCDriverUnloader.class.getClassLoader()) {
                driverList.add(d);
            }
        }
        return driverList.iterator();
    }
}
