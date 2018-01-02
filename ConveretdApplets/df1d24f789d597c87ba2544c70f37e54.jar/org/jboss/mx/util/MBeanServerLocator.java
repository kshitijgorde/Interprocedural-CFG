// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Iterator;
import javax.management.MBeanServerFactory;
import javax.management.MBeanServer;

public class MBeanServerLocator
{
    private static MBeanServer instance;
    
    public static void setJBoss(final MBeanServer server) {
        synchronized (MBeanServerLocator.class) {
            MBeanServerLocator.instance = server;
        }
    }
    
    public static MBeanServer locate(final String agentID) {
        final MBeanServer server = MBeanServerFactory.findMBeanServer(agentID).iterator().next();
        return server;
    }
    
    public static MBeanServer locate() {
        return locate(null);
    }
    
    public static MBeanServer locateJBoss() {
        synchronized (MBeanServerLocator.class) {
            if (MBeanServerLocator.instance != null) {
                return MBeanServerLocator.instance;
            }
        }
        for (final MBeanServer server : MBeanServerFactory.findMBeanServer(null)) {
            if (server.getDefaultDomain().equals("jboss")) {
                return server;
            }
        }
        throw new IllegalStateException("No 'jboss' MBeanServer found!");
    }
    
    static {
        MBeanServerLocator.instance = null;
    }
}
