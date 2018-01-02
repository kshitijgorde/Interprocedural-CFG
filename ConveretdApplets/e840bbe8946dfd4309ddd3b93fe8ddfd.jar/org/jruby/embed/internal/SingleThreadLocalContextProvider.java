// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.util.Map;
import org.jruby.Ruby;
import org.jruby.embed.LocalVariableBehavior;

public class SingleThreadLocalContextProvider extends AbstractLocalContextProvider
{
    private LocalContext localContext;
    
    public SingleThreadLocalContextProvider(final LocalVariableBehavior behavior, final boolean lazy) {
        this.behavior = behavior;
        this.lazy = lazy;
        this.localContext = null;
    }
    
    private void initializeLocalContext() {
        if (this.localContext == null) {
            this.localContext = this.getInstance();
        }
    }
    
    public Ruby getRuntime() {
        this.initializeLocalContext();
        if (this.localContext.runtime == null) {
            this.localContext.runtime = Ruby.newInstance(this.config);
            this.localContext.initialized = true;
        }
        return this.localContext.runtime;
    }
    
    public BiVariableMap getVarMap() {
        this.initializeLocalContext();
        return this.localContext.getVarMap(this);
    }
    
    public Map getAttributeMap() {
        this.initializeLocalContext();
        return this.localContext.getAttributeMap();
    }
    
    public boolean isRuntimeInitialized() {
        this.initializeLocalContext();
        return this.localContext.initialized;
    }
    
    public void terminate() {
        this.initializeLocalContext();
        this.localContext.remove();
        this.localContext = null;
    }
}
