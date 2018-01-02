// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Block;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class SynchronizedDynamicMethod extends DelegatingDynamicMethod
{
    public SynchronizedDynamicMethod(final DynamicMethod delegate) {
        super(delegate);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, arg0);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, arg0, arg1);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, arg0, arg1, arg2);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, args);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, block);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, arg0, block);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, arg0, arg1, block);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, arg0, arg1, arg2, block);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        synchronized (self) {
            return this.delegate.call(context, self, clazz, name, args, block);
        }
    }
    
    public DynamicMethod dup() {
        return new SynchronizedDynamicMethod(this.delegate.dup());
    }
}
