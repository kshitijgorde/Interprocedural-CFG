// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.util.Map;
import org.jruby.Ruby;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import org.jruby.embed.AttributeName;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.RubyInstanceConfig;
import java.util.HashMap;

public class SingletonLocalContextProvider extends AbstractLocalContextProvider
{
    private static LocalContext localContext;
    private static BiVariableMap varMap;
    private static HashMap attribute;
    
    public static LocalContext getLocalContextInstance(final RubyInstanceConfig config, final LocalVariableBehavior behavior, final boolean lazy) {
        if (SingletonLocalContextProvider.localContext == null) {
            synchronized (LocalContext.class) {
                SingletonLocalContextProvider.localContext = new LocalContext(config, behavior, lazy);
            }
        }
        return SingletonLocalContextProvider.localContext;
    }
    
    private static BiVariableMap getBiVariableInstance(final LocalContextProvider provider, final boolean lazy) {
        if (SingletonLocalContextProvider.varMap == null) {
            synchronized (BiVariableMap.class) {
                SingletonLocalContextProvider.varMap = new BiVariableMap(provider, lazy);
            }
        }
        return SingletonLocalContextProvider.varMap;
    }
    
    private static HashMap getAttributeInstance() {
        if (SingletonLocalContextProvider.attribute == null) {
            synchronized (HashMap.class) {
                (SingletonLocalContextProvider.attribute = new HashMap()).put(AttributeName.READER, new InputStreamReader(System.in));
                SingletonLocalContextProvider.attribute.put(AttributeName.WRITER, new PrintWriter(System.out, true));
                SingletonLocalContextProvider.attribute.put(AttributeName.ERROR_WRITER, new PrintWriter(System.err, true));
            }
        }
        return SingletonLocalContextProvider.attribute;
    }
    
    public static LocalVariableBehavior getLocalVariableBehaviorOrNull() {
        if (SingletonLocalContextProvider.localContext == null) {
            return null;
        }
        return SingletonLocalContextProvider.localContext.getLocalVariableBehavior();
    }
    
    public SingletonLocalContextProvider(final LocalVariableBehavior behavior, final boolean lazy) {
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
        return getBiVariableInstance(this, this.lazy);
    }
    
    public Map getAttributeMap() {
        return getAttributeInstance();
    }
    
    public boolean isRuntimeInitialized() {
        final LocalContext context = getLocalContextInstance(this.config, this.behavior, this.lazy);
        return context.initialized;
    }
    
    public void terminate() {
        LocalContext context = getLocalContextInstance(this.config, this.behavior, this.lazy);
        context.remove();
        context = null;
    }
    
    static {
        SingletonLocalContextProvider.localContext = null;
        SingletonLocalContextProvider.varMap = null;
        SingletonLocalContextProvider.attribute = null;
    }
}
