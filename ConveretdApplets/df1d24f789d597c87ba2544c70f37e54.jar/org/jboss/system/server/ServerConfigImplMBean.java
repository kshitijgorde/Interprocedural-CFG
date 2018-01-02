// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.server;

import org.jboss.mx.util.ObjectNameFactory;
import java.net.URL;
import java.io.File;
import javax.management.ObjectName;

public interface ServerConfigImplMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:type=ServerConfig");
    
    String getSpecificationVersion();
    
    File getHomeDir();
    
    URL getHomeURL();
    
    URL getLibraryURL();
    
    URL getPatchURL();
    
    String getServerName();
    
    File getServerBaseDir();
    
    File getServerHomeDir();
    
    File getServerLogDir();
    
    File getServerTempDir();
    
    File getServerDataDir();
    
    File getServerNativeDir();
    
    File getServerTempDeployDir();
    
    URL getServerBaseURL();
    
    URL getServerHomeURL();
    
    URL getServerLibraryURL();
    
    URL getServerConfigURL();
    
    boolean getPlatformMBeanServer();
    
    void setExitOnShutdown(final boolean p0);
    
    boolean getExitOnShutdown();
    
    void setBlockingShutdown(final boolean p0);
    
    boolean getBlockingShutdown();
    
    void setRequireJBossURLStreamHandlerFactory(final boolean p0);
    
    boolean getRequireJBossURLStreamHandlerFactory();
    
    void setRootDeploymentFilename(final String p0);
    
    String getRootDeploymentFilename();
}
