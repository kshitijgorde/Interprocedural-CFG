// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import org.jruby.util.ClassCache;
import java.util.List;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.RubyInstanceConfig;

public abstract class AbstractLocalContextProvider implements LocalContextProvider
{
    protected RubyInstanceConfig config;
    protected LocalVariableBehavior behavior;
    protected boolean lazy;
    
    public AbstractLocalContextProvider() {
        this.config = new RubyInstanceConfig();
        this.behavior = LocalVariableBehavior.TRANSIENT;
        this.lazy = true;
    }
    
    @Deprecated
    public void setLoadPaths(final List loadPaths) {
        if (this.config != null) {
            this.config.setLoadPaths(loadPaths);
        }
    }
    
    @Deprecated
    public void setClassCache(final ClassCache classCache) {
        if (this.config != null) {
            this.config.setClassCache(classCache);
        }
    }
    
    public RubyInstanceConfig getRubyInstanceConfig() {
        return this.config;
    }
    
    protected LocalContext getInstance() {
        return new LocalContext(this.config, this.behavior, this.lazy);
    }
    
    public LocalVariableBehavior getLocalVariableBehavior() {
        return this.behavior;
    }
}
