// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.server;

import java.util.Properties;

public interface Server
{
    public static final String START_NOTIFICATION_TYPE = "org.jboss.system.server.started";
    public static final String STOP_NOTIFICATION_TYPE = "org.jboss.system.server.stopped";
    
    void init(final Properties p0) throws IllegalStateException, Exception;
    
    ServerConfig getConfig() throws IllegalStateException;
    
    void start() throws IllegalStateException, Exception;
    
    boolean isStarted();
    
    void shutdown() throws IllegalStateException;
    
    void exit(final int p0);
    
    void halt(final int p0);
}
