// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system;

public interface ServiceMBean extends Service
{
    public static final String CREATE_EVENT = "org.jboss.system.ServiceMBean.create";
    public static final String START_EVENT = "org.jboss.system.ServiceMBean.start";
    public static final String STOP_EVENT = "org.jboss.system.ServiceMBean.stop";
    public static final String DESTROY_EVENT = "org.jboss.system.ServiceMBean.destroy";
    public static final String[] states = { "Stopped", "Stopping", "Starting", "Started", "Failed", "Destroyed", "Created", "Unregistered", "Registered" };
    public static final int STOPPED = 0;
    public static final int STOPPING = 1;
    public static final int STARTING = 2;
    public static final int STARTED = 3;
    public static final int FAILED = 4;
    public static final int DESTROYED = 5;
    public static final int CREATED = 6;
    public static final int UNREGISTERED = 7;
    public static final int REGISTERED = 8;
    
    String getName();
    
    int getState();
    
    String getStateString();
    
    void jbossInternalLifecycle(final String p0) throws Exception;
}
