// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.scanner;

import java.net.URL;
import java.net.MalformedURLException;
import org.jboss.net.protocol.URLLister;
import java.util.List;

public interface URLDeploymentScannerMBean extends DeploymentScannerMBean
{
    void setRecursiveSearch(final boolean p0);
    
    boolean getRecursiveSearch();
    
    void setURLList(final List p0);
    
    List getURLList();
    
    void setURLComparator(final String p0) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
    
    String getURLComparator();
    
    void setFilter(final String p0) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
    
    String getFilter();
    
    void setFilterInstance(final URLLister.URLFilter p0);
    
    URLLister.URLFilter getFilterInstance();
    
    void setURLs(final String p0) throws MalformedURLException;
    
    void addURL(final URL p0);
    
    void removeURL(final URL p0);
    
    boolean hasURL(final URL p0);
    
    void addURL(final String p0) throws MalformedURLException;
    
    void removeURL(final String p0) throws MalformedURLException;
    
    boolean hasURL(final String p0) throws MalformedURLException;
    
    void suspendDeployment(final URL p0);
    
    void resumeDeployment(final URL p0, final boolean p1);
    
    String listDeployedURLs();
}
