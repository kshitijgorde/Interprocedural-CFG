// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.cache;

import java.net.URL;
import java.io.IOException;
import java.io.File;

public interface FileDeploymentStoreMBean extends DeploymentStoreMBean
{
    void setDirectory(final File p0) throws IOException;
    
    File getDirectory();
    
    void setDirectoryName(final String p0) throws IOException;
    
    String getDirectoryName();
    
    URL get(final URL p0);
    
    URL put(final URL p0) throws Exception;
}
