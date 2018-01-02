// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import org.jboss.mx.util.ObjectNameFactory;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collection;
import java.io.File;
import javax.management.ObjectName;
import org.jboss.system.ServiceMBean;

public interface MainDeployerMBean extends ServiceMBean, DeployerMBean, MainDeployerConstants
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:service=MainDeployer");
    
    boolean getCopyFiles();
    
    void setCopyFiles(final boolean p0);
    
    File getTempDir();
    
    void setTempDir(final File p0);
    
    String[] getEnhancedSuffixOrder();
    
    void setEnhancedSuffixOrder(final String[] p0);
    
    void setServiceController(final ObjectName p0);
    
    String getTempDirString();
    
    String[] getSuffixOrder();
    
    Collection listDeployed();
    
    Collection listDeployedModules();
    
    String listDeployedAsString();
    
    Collection listIncompletelyDeployed();
    
    Collection listWaitingForDeployer();
    
    void addDeployer(final SubDeployer p0);
    
    void removeDeployer(final SubDeployer p0);
    
    Collection listDeployers();
    
    void shutdown();
    
    void redeploy(final String p0) throws DeploymentException, MalformedURLException;
    
    void redeploy(final URL p0) throws DeploymentException;
    
    void redeploy(final DeploymentInfo p0) throws DeploymentException;
    
    void undeploy(final URL p0) throws DeploymentException;
    
    void undeploy(final String p0) throws DeploymentException, MalformedURLException;
    
    void undeploy(final DeploymentInfo p0);
    
    void deploy(final String p0) throws DeploymentException, MalformedURLException;
    
    void deploy(final URL p0) throws DeploymentException;
    
    void deploy(final DeploymentInfo p0) throws DeploymentException;
    
    void start(final String p0) throws DeploymentException, MalformedURLException;
    
    void stop(final String p0) throws DeploymentException, MalformedURLException;
    
    boolean isDeployed(final String p0) throws MalformedURLException;
    
    boolean isDeployed(final URL p0);
    
    DeploymentInfo getDeployment(final URL p0);
    
    URL getWatchUrl(final URL p0);
    
    void checkIncompleteDeployments() throws DeploymentException;
}
