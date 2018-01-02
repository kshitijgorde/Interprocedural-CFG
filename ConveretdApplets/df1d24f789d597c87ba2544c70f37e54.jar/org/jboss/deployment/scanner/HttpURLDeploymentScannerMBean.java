// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.scanner;

import java.net.MalformedURLException;
import java.util.List;

public interface HttpURLDeploymentScannerMBean extends URLDeploymentScannerMBean
{
    String getDefaultHttpDirectoryListerUrl();
    
    void setDefaultHttpDirectoryListerUrl(final String p0);
    
    String getDefaultHttpDirectoryDownloadUrl();
    
    void setDefaultHttpDirectoryDownloadUrl(final String p0);
    
    void setURLList(final List p0);
    
    void setURLs(final String p0) throws MalformedURLException;
    
    void scan() throws Exception;
}
