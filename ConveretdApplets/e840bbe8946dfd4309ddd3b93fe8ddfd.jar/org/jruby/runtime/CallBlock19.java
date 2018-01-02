// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.parser.StaticScope;
import org.jruby.RubyArray;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;

public class CallBlock19 extends BlockBody
{
    private final Arity arity;
    private final BlockCallback callback;
    
    public static Block newCallClosure(final IRubyObject self, final RubyModule imClass, final Arity arity, final BlockCallback callback, final ThreadContext context) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        final BlockBody body = new CallBlock19(imClass, arity, callback, context);
        return new Block(body, binding);
    }
    
    private CallBlock19(final RubyModule imClass, final Arity arity, final BlockCallback callback, final ThreadContext context) {
        super(3);
        this.arity = arity;
        this.callback = callback;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type) {
        return this.callback.call(context, args, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type, final Block block) {
        return this.callback.call(context, args, block);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        return this.callback.call(context, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        return this.callback.call(context, new IRubyObject[] { arg0 }, Block.NULL_BLOCK);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        return this.callback.call(context, new IRubyObject[] { arg0, arg1 }, Block.NULL_BLOCK);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        return this.callback.call(context, new IRubyObject[] { arg0, arg1, arg2 }, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        return this.callback.call(context, new IRubyObject[] { value }, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        if (aValue) {
            return this.callback.call(context, ((RubyArray)value).toJavaArray(), Block.NULL_BLOCK);
        }
        return this.callback.call(context, new IRubyObject[] { value }, Block.NULL_BLOCK);
    }
    
    public StaticScope getStaticScope() {
        throw new RuntimeException("CallBlock does not have a static scope; this should not be called");
    }
    
    public void setStaticScope(final StaticScope newScope) {
        throw new RuntimeException("CallBlock does not have a static scope; this should not be called");
    }
    
    public Block cloneBlock(Binding binding) {
        binding = binding.clone(Visibility.PUBLIC);
        return new Block(this, binding);
    }
    
    public Arity arity() {
        return this.arity;
    }
    
    public String getFile() {
        return "(internal)";
    }
    
    public int getLine() {
        return -1;
    }
}
