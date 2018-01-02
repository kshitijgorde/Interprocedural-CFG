// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.server;

import org.jboss.mx.util.ObjectNameFactory;
import javax.management.ObjectName;

public interface ServerInfoMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:type=ServerInfo");
    
    String getJavaVersion();
    
    String getJavaVendor();
    
    String getJavaVMName();
    
    String getJavaVMVersion();
    
    String getJavaVMVendor();
    
    String getOSName();
    
    String getOSVersion();
    
    String getOSArch();
    
    Integer getActiveThreadCount();
    
    Integer getActiveThreadGroupCount();
    
    Long getMaxMemory();
    
    Long getTotalMemory();
    
    Long getFreeMemory();
    
    Integer getAvailableProcessors();
    
    String getHostName();
    
    String getHostAddress();
    
    String listMemoryPools(final boolean p0);
    
    String listThreadDump();
    
    String listThreadCpuUtilization();
    
    String displayPackageInfo(final String p0);
}
