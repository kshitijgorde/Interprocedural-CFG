// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.feature;

import java.net.URL;
import java.util.List;
import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.log.Log;

public abstract class AbstractFeature implements IFeature
{
    protected FeatureManager manager;
    static final Log log;
    
    static {
        log = Log.getLog(AbstractFeature.class);
    }
    
    public AbstractFeature(final FeatureManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void init(final ComponentServer container) {
    }
    
    @Override
    public void shutdown() {
    }
    
    @Override
    public List<URL> getResourcesInDirectory(final String dir, final List<String> extensions) {
        return this.manager.getResourcesInDirectory(dir, extensions);
    }
    
    @Override
    public URL getResource(final String name) {
        return this.manager.getResource(name);
    }
}
