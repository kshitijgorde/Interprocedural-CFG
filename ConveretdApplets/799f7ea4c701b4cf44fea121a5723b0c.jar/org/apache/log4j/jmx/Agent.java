// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.jmx;

import com.sun.jdmk.comm.CommunicatorServer;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import com.sun.jdmk.comm.HtmlAdaptorServer;
import javax.management.MBeanServerFactory;
import org.apache.log4j.Logger;

public class Agent
{
    static Logger log;
    static /* synthetic */ Class class$org$apache$log4j$jmx$Agent;
    
    public void start() {
        final MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();
        final HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        try {
            Agent.log.info("Registering HtmlAdaptorServer instance.");
            mBeanServer.registerMBean(htmlAdaptorServer, new ObjectName("Adaptor:name=html,port=8082"));
            Agent.log.info("Registering HierarchyDynamicMBean instance.");
            mBeanServer.registerMBean(new HierarchyDynamicMBean(), new ObjectName("log4j:hiearchy=default"));
        }
        catch (Exception t) {
            Agent.log.error("Problem while regitering MBeans instances.", t);
            return;
        }
        ((CommunicatorServer)htmlAdaptorServer).start();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Agent.log = Logger.getLogger((Agent.class$org$apache$log4j$jmx$Agent == null) ? (Agent.class$org$apache$log4j$jmx$Agent = class$("org.apache.log4j.jmx.Agent")) : Agent.class$org$apache$log4j$jmx$Agent);
    }
}
