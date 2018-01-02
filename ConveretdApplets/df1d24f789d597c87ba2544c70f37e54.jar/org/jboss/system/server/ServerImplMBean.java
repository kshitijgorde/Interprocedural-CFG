// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.server;

import org.jboss.mx.util.ObjectNameFactory;
import java.util.Date;
import javax.management.ObjectName;

public interface ServerImplMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:type=Server");
    
    Date getStartDate();
    
    String getVersion();
    
    String getVersionName();
    
    String getVersionNumber();
    
    String getBuildNumber();
    
    String getBuildJVM();
    
    String getBuildOS();
    
    String getBuildID();
    
    String getBuildDate();
    
    boolean isStarted();
    
    boolean isInShutdown();
    
    void shutdown() throws IllegalStateException;
    
    void exit(final int p0);
    
    void exit();
    
    void halt(final int p0);
    
    void halt();
    
    void runGarbageCollector();
    
    void runFinalization();
    
    void traceMethodCalls(final Boolean p0);
    
    void traceInstructions(final Boolean p0);
}
