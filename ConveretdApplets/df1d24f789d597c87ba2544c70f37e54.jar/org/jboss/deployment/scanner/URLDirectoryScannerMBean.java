// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment.scanner;

import org.w3c.dom.Element;
import java.io.FileFilter;
import java.util.Comparator;
import java.net.MalformedURLException;
import java.net.URL;

public interface URLDirectoryScannerMBean extends DeploymentScannerMBean
{
    void addScanURL(final URL p0);
    
    void addScanURL(final String p0) throws MalformedURLException;
    
    void addScanDir(final URL p0, final Comparator p1, final FileFilter p2);
    
    void addScanDir(final String p0, final String p1, final String p2) throws MalformedURLException;
    
    void removeScanURL(final URL p0);
    
    void setURLs(final Element p0);
    
    void setURLComparator(final String p0);
    
    String getURLComparator();
    
    void setFilter(final String p0);
    
    String getFilter();
    
    void scan();
}
