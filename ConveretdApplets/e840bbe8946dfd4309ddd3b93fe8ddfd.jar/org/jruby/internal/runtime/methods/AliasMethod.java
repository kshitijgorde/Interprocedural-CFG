// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule;

public class AliasMethod extends DynamicMethod
{
    private DynamicMethod oldMethod;
    private String oldName;
    
    public AliasMethod(final RubyModule implementationClass, final DynamicMethod oldMethod, final String oldName) {
        super(implementationClass, oldMethod.getVisibility(), null);
        this.oldName = oldName;
        this.oldMethod = oldMethod;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.oldMethod.call(context, self, klazz, this.oldName);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg) {
        return this.oldMethod.call(context, self, klazz, this.oldName, arg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        return this.oldMethod.call(context, self, klazz, this.oldName, arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return this.oldMethod.call(context, self, klazz, this.oldName, arg1, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args) {
        return this.oldMethod.call(context, self, klazz, this.oldName, args);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final Block block) {
        return this.oldMethod.call(context, self, klazz, this.oldName, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final Block block) {
        return this.oldMethod.call(context, self, klazz, this.oldName, arg1, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.oldMethod.call(context, self, klazz, this.oldName, arg1, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return this.oldMethod.call(context, self, klazz, this.oldName, arg1, arg2, arg3, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        return this.oldMethod.call(context, self, klazz, this.oldName, args, block);
    }
    
    public DynamicMethod dup() {
        return new AliasMethod(this.implementationClass, this.oldMethod, this.oldName);
    }
    
    public Arity getArity() {
        return this.oldMethod.getArity();
    }
    
    public DynamicMethod getRealMethod() {
        return this.oldMethod.getRealMethod();
    }
    
    public long getSerialNumber() {
        return this.oldMethod.getSerialNumber();
    }
}
