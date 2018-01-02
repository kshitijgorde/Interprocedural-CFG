// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;

public class GlobalVariable
{
    protected final Ruby runtime;
    protected final String name;
    private IRubyObject value;
    
    public static String variableName(final String name) {
        return "$" + name;
    }
    
    public GlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
        assert name.startsWith("$");
        this.runtime = runtime;
        this.name = name;
        this.value = value;
    }
    
    public String name() {
        return this.name;
    }
    
    public IRubyObject get() {
        return this.value;
    }
    
    public IRubyObject set(final IRubyObject value) {
        return this.value = value;
    }
    
    public static class Copy extends GlobalVariable
    {
        private GlobalVariable other;
        
        public Copy(final Ruby runtime, final String name, final GlobalVariable other) {
            super(runtime, name, other.get());
            this.other = other;
        }
        
        public IRubyObject get() {
            return this.other.get();
        }
        
        public IRubyObject set(final IRubyObject value) {
            return this.other.set(value);
        }
    }
}
