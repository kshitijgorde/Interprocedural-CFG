// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;

public class WrapperMethod extends DynamicMethod
{
    private DynamicMethod method;
    
    public WrapperMethod(final RubyModule implementationClass, final DynamicMethod method, final Visibility visibility) {
        super(implementationClass, visibility, null);
        this.method = method;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.method.call(context, self, klazz, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg) {
        return this.method.call(context, self, klazz, name, arg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        return this.method.call(context, self, klazz, name, arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return this.method.call(context, self, klazz, name, arg1, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args) {
        return this.method.call(context, self, klazz, name, args);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final Block block) {
        return this.method.call(context, self, klazz, name, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final Block block) {
        return this.method.call(context, self, klazz, name, arg1, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.method.call(context, self, klazz, name, arg1, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return this.method.call(context, self, klazz, name, arg1, arg2, arg3, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        return this.method.call(context, self, klazz, name, args, block);
    }
    
    public DynamicMethod dup() {
        return new WrapperMethod(this.getImplementationClass(), this.method, this.getVisibility());
    }
    
    public long getSerialNumber() {
        return this.method.getSerialNumber();
    }
    
    public DynamicMethod getRealMethod() {
        return this.method;
    }
}
