// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.server;

import java.net.URL;
import java.io.File;

public interface ServerConfig
{
    public static final String DEFAULT_PARITION_NAME = "DefaultPartition";
    public static final String PARTITION_NAME_PROPERTY = "jboss.partition.name";
    public static final String PARTITION_UDP_PROPERTY = "jboss.partition.udpGroup";
    public static final String NATIVE_LOAD_PROPERTY = "jboss.native.load";
    public static final String NATIVE_DIR_PROPERTY = "jboss.native.dir";
    public static final String BOOT_LIBRARY_LIST = "jboss.boot.library.list";
    public static final String SERVER_TYPE = "jboss.server.type";
    public static final String ROOT_DEPLOYMENT_FILENAME = "jboss.server.root.deployment.filename";
    public static final String HOME_DIR = "jboss.home.dir";
    public static final String HOME_URL = "jboss.home.url";
    public static final String LIBRARY_URL = "jboss.lib.url";
    public static final String PATCH_URL = "jboss.patch.url";
    public static final String SERVER_NAME = "jboss.server.name";
    public static final String SERVER_BASE_DIR = "jboss.server.base.dir";
    public static final String SERVER_HOME_DIR = "jboss.server.home.dir";
    public static final String SERVER_LOG_DIR = "jboss.server.log.dir";
    public static final String SERVER_TEMP_DIR = "jboss.server.temp.dir";
    public static final String SERVER_DATA_DIR = "jboss.server.data.dir";
    public static final String SERVER_BASE_URL = "jboss.server.base.url";
    public static final String SERVER_HOME_URL = "jboss.server.home.url";
    public static final String SERVER_CONFIG_URL = "jboss.server.config.url";
    public static final String SERVER_LIBRARY_URL = "jboss.server.lib.url";
    public static final String SERVER_BIND_ADDRESS = "jboss.bind.address";
    public static final String EXIT_ON_SHUTDOWN = "jboss.server.exitonshutdown";
    public static final String BLOCKING_SHUTDOWN = "jboss.server.blockingshutdown";
    public static final String REQUIRE_JBOSS_URL_STREAM_HANDLER_FACTORY = "jboss.server.requirejbossurlstreamhandlerfactory";
    public static final String PLATFORM_MBEANSERVER = "jboss.platform.mbeanserver";
    public static final String LIBRARY_URL_SUFFIX = "lib/";
    public static final String SERVER_CONFIG_URL_SUFFIX = "conf/";
    public static final String SERVER_BASE_DIR_SUFFIX = "server";
    public static final String SERVER_BASE_URL_SUFFIX = "server/";
    public static final String SERVER_DATA_DIR_SUFFIX = "data";
    public static final String SERVER_LOG_DIR_SUFFIX = "log";
    public static final String SERVER_TEMP_DIR_SUFFIX = "tmp";
    public static final String DEFAULT_SERVER_NAME = "default";
    public static final boolean DEFAULT_EXIT_ON_SHUTDOWN = true;
    public static final boolean DEFAULT_BLOCKING_SHUTDOWN = false;
    public static final boolean DEFAULT_REQUIRE_JBOSS_URL_STREAM_HANDLER_FACTORY = true;
    public static final boolean DEFAULT_PLATFORM_MBEANSERVER = false;
    public static final String DEFAULT_ROOT_DEPLOYMENT_FILENAME = "jboss-service.xml";
    
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
    
    URL getServerBaseURL();
    
    URL getServerHomeURL();
    
    URL getServerLibraryURL();
    
    URL getServerConfigURL();
    
    File getServerNativeDir();
    
    File getServerTempDeployDir();
    
    boolean getPlatformMBeanServer();
    
    void setExitOnShutdown(final boolean p0);
    
    boolean getExitOnShutdown();
    
    boolean getBlockingShutdown();
    
    void setBlockingShutdown(final boolean p0);
    
    boolean getRequireJBossURLStreamHandlerFactory();
    
    void setRequireJBossURLStreamHandlerFactory(final boolean p0);
    
    void setRootDeploymentFilename(final String p0);
    
    String getRootDeploymentFilename();
}
