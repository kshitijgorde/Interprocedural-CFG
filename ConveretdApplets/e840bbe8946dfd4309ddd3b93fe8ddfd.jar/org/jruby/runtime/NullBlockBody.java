// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.parser.StaticScope;
import org.jruby.RubyModule;
import org.jruby.RubyLocalJumpError;
import org.jruby.runtime.builtin.IRubyObject;

public class NullBlockBody extends BlockBody
{
    public NullBlockBody() {
        super(0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().newArrayNoCopy(args), "yield called out of block");
    }
    
    public IRubyObject call(final ThreadContext context, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().newArrayNoCopy(IRubyObject.NULL_ARRAY), "yield called out of block");
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().getNil(), "yield called out of block");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().newArrayNoCopy(arg0), "yield called out of block");
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().getNil(), "yield called out of block");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().newArrayNoCopy(arg0, arg1), "yield called out of block");
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().getNil(), "yield called out of block");
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().newArrayNoCopy(arg0, arg1, arg2), "yield called out of block");
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, context.getRuntime().getNil(), "yield called out of block");
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, value, "yield called out of block");
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.NOREASON, value, "yield called out of block");
    }
    
    public StaticScope getStaticScope() {
        return null;
    }
    
    public void setStaticScope(final StaticScope newScope) {
    }
    
    public Block cloneBlock(final Binding binding) {
        return null;
    }
    
    public Arity arity() {
        return null;
    }
    
    public String getFile() {
        return "(null)";
    }
    
    public int getLine() {
        return -1;
    }
}
