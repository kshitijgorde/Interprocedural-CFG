// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.rmi.dgc.VMID;
import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;
import org.jboss.mx.server.ServerConstants;

public class AgentID implements ServerConstants
{
    private static SynchronizedLong id;
    private static final Random rand;
    
    public static String create() {
        String ipAddress = null;
        try {
            ipAddress = AccessController.doPrivileged((PrivilegedExceptionAction<String>)new PrivilegedExceptionAction() {
                public Object run() throws Exception {
                    return InetAddress.getLocalHost().getHostAddress();
                }
            });
        }
        catch (PrivilegedActionException e) {
            ipAddress = "127.0.0.1";
        }
        final String vmid = new VMID().toString().replace(':', 'x').replace('-', 'X') + AgentID.rand.nextInt(100);
        return ipAddress + "/" + System.currentTimeMillis() + "/" + vmid + "/" + AgentID.id.increment();
    }
    
    public static void main(final String[] args) {
        for (int c = 0; c < 10; ++c) {
            System.out.println(create());
        }
    }
    
    public static String get(final MBeanServer server) {
        try {
            final ObjectName name = new ObjectName("JMImplementation:type=MBeanServerDelegate");
            final String agentID = (String)server.getAttribute(name, "MBeanServerId");
            return agentID;
        }
        catch (Throwable t) {
            throw new Error("Cannot find the MBean server delegate: " + t.toString());
        }
    }
    
    static {
        AgentID.id = new SynchronizedLong(0L);
        rand = new Random(System.currentTimeMillis());
    }
}
