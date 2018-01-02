// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import org.jruby.embed.AttributeName;
import java.util.HashMap;
import org.jruby.Ruby;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.RubyInstanceConfig;

public class LocalContext
{
    private RubyInstanceConfig config;
    private LocalVariableBehavior behavior;
    private boolean lazy;
    Ruby runtime;
    private BiVariableMap varMap;
    private HashMap attribute;
    boolean initialized;
    
    public LocalContext(final RubyInstanceConfig config, final LocalVariableBehavior behavior, final boolean lazy) {
        this.runtime = null;
        this.varMap = null;
        this.attribute = null;
        this.initialized = false;
        this.initialize(config, behavior, lazy);
    }
    
    private void initialize(final RubyInstanceConfig config, final LocalVariableBehavior behavior, final boolean lazy) {
        this.config = config;
        this.behavior = behavior;
        this.lazy = lazy;
    }
    
    public Ruby getThreadSafeRuntime() {
        if (this.runtime == null) {
            this.runtime = Ruby.newInstance(this.config);
            this.initialized = true;
        }
        return this.runtime;
    }
    
    public BiVariableMap getVarMap(final LocalContextProvider provider) {
        if (this.varMap == null) {
            this.varMap = new BiVariableMap(provider, this.lazy);
        }
        return this.varMap;
    }
    
    public LocalVariableBehavior getLocalVariableBehavior() {
        return this.behavior;
    }
    
    public HashMap getAttributeMap() {
        if (this.attribute == null) {
            (this.attribute = new HashMap()).put(AttributeName.READER, new InputStreamReader(System.in));
            this.attribute.put(AttributeName.WRITER, new PrintWriter(System.out, true));
            this.attribute.put(AttributeName.ERROR_WRITER, new PrintWriter(System.err, true));
        }
        return this.attribute;
    }
    
    public void remove() {
        if (this.attribute == null) {
            return;
        }
        this.attribute.clear();
        this.varMap.clear();
    }
}
