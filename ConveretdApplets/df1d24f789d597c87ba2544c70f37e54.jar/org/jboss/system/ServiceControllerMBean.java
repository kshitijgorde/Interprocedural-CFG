// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system;

import org.jboss.mx.util.ObjectNameFactory;
import java.util.Collection;
import org.jboss.deployment.DeploymentException;
import org.w3c.dom.Element;
import org.jboss.deployment.DeploymentState;
import org.jboss.deployment.DeploymentInfo;
import java.util.List;
import javax.management.ObjectName;

public interface ServiceControllerMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss.system:service=ServiceController");
    
    void setServiceBinding(final ServiceBinding p0);
    
    List listDeployed();
    
    List listIncompletelyDeployed();
    
    List listDeployedNames();
    
    String listConfiguration(final ObjectName[] p0) throws Exception;
    
    void validateDeploymentState(final DeploymentInfo p0, final DeploymentState p1);
    
    List install(final Element p0, final ObjectName p1) throws DeploymentException;
    
    void register(final ObjectName p0) throws Exception;
    
    void register(final ObjectName p0, final Collection p1) throws Exception;
    
    void create(final ObjectName p0) throws Exception;
    
    void create(final ObjectName p0, final Collection p1) throws Exception;
    
    void start(final ObjectName p0) throws Exception;
    
    void restart(final ObjectName p0) throws Exception;
    
    void stop(final ObjectName p0) throws Exception;
    
    void destroy(final ObjectName p0) throws Exception;
    
    void remove(final ObjectName p0) throws Exception;
    
    void shutdown();
    
    ServiceContext getServiceContext(final ObjectName p0);
}
