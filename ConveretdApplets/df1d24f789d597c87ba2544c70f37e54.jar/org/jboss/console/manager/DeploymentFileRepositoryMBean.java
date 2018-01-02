// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager;

import java.io.IOException;
import org.jboss.system.ServiceMBean;

public interface DeploymentFileRepositoryMBean extends ServiceMBean
{
    void setBaseDir(final String p0) throws IOException;
    
    String getBaseDir();
    
    void store(final String p0, final String p1, final String p2, final String p3, final boolean p4) throws IOException;
    
    void remove(final String p0, final String p1, final String p2) throws IOException;
    
    boolean isStored(final String p0, final String p1, final String p2) throws IOException;
}
