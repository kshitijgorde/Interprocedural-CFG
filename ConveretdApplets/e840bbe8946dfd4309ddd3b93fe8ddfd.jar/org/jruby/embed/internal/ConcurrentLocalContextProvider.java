// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.util.Map;
import org.jruby.RubyInstanceConfig;
import org.jruby.Ruby;
import org.jruby.embed.LocalVariableBehavior;

public class ConcurrentLocalContextProvider extends AbstractLocalContextProvider
{
    private ThreadLocal<LocalContext> contextHolder;
    
    public ConcurrentLocalContextProvider(final LocalVariableBehavior behavior, final boolean lazy) {
        this.contextHolder = new ThreadLocal<LocalContext>() {
            public LocalContext initialValue() {
                return ConcurrentLocalContextProvider.this.getInstance();
            }
            
            public void remove() {
                final LocalContext localContext = this.get();
                localContext.remove();
            }
        };
        if (Ruby.isGlobalRuntimeReady()) {
            this.config = Ruby.getGlobalRuntime().getInstanceConfig();
        }
        else {
            this.config = new RubyInstanceConfig();
        }
        this.behavior = behavior;
        this.lazy = lazy;
    }
    
    public Ruby getRuntime() {
        if (!Ruby.isGlobalRuntimeReady()) {
            return Ruby.newInstance(this.config);
        }
        return Ruby.getGlobalRuntime();
    }
    
    public RubyInstanceConfig getRubyInstanceConfig() {
        if (Ruby.isGlobalRuntimeReady()) {
            return Ruby.getGlobalRuntime().getInstanceConfig();
        }
        return this.config;
    }
    
    public BiVariableMap getVarMap() {
        return this.contextHolder.get().getVarMap(this);
    }
    
    public Map getAttributeMap() {
        return this.contextHolder.get().getAttributeMap();
    }
    
    public boolean isRuntimeInitialized() {
        return Ruby.isGlobalRuntimeReady();
    }
    
    public void terminate() {
        this.contextHolder.remove();
        this.contextHolder.set(null);
    }
}
