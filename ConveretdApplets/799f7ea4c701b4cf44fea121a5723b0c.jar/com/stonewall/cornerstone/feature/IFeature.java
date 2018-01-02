// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.feature;

import java.net.URL;
import java.util.List;
import com.stonewall.cornerstone.component.ComponentServer;

public interface IFeature
{
    void init(final ComponentServer p0);
    
    void shutdown();
    
    List<URL> getResourcesInDirectory(final String p0, final List<String> p1);
    
    URL getResource(final String p0);
}
