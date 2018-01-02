// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.feature;

import java.net.URL;
import java.util.List;

public interface FeatureManager
{
    IFeature getFeature();
    
    List<URL> getResourcesInDirectory(final String p0, final List<String> p1);
    
    URL getResource(final String p0);
}
