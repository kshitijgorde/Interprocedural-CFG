// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.util.Map;
import org.jruby.Ruby;
import org.jruby.embed.LocalVariableBehavior;

public class ThreadSafeLocalContextProvider extends AbstractLocalContextProvider
{
    private ThreadLocal<LocalContext> contextHolder;
    
    public ThreadSafeLocalContextProvider(final LocalVariableBehavior behavior, final boolean lazy) {
        this.contextHolder = new ThreadLocal<LocalContext>() {
            public LocalContext initialValue() {
                return ThreadSafeLocalContextProvider.this.getInstance();
            }
            
            public void remove() {
                final LocalContext localContext = this.get();
                localContext.remove();
            }
        };
        this.behavior = behavior;
        this.lazy = lazy;
    }
    
    public Ruby getRuntime() {
        return this.contextHolder.get().getThreadSafeRuntime();
    }
    
    public BiVariableMap getVarMap() {
        return this.contextHolder.get().getVarMap(this);
    }
    
    public Map getAttributeMap() {
        return this.contextHolder.get().getAttributeMap();
    }
    
    public boolean isRuntimeInitialized() {
        return this.contextHolder.get().initialized;
    }
    
    public void terminate() {
        this.contextHolder.remove();
        this.contextHolder.set(null);
    }
}
