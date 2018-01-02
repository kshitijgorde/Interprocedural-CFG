// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyStruct$StructMethods$s$newStruct extends JavaMethodZeroOrOneOrTwoOrThreeOrNBlock
{
    public RubyStruct$StructMethods$s$newStruct(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final Block block) {
        return RubyStruct.StructMethods.newStruct(recv, arg0, block);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block block) {
        return RubyStruct.StructMethods.newStruct(recv, args, block);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final Block block) {
        return RubyStruct.StructMethods.newStruct(recv, block);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final Block block) {
        return RubyStruct.StructMethods.newStruct(recv, arg0, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return RubyStruct.StructMethods.newStruct(recv, arg0, arg2, arg3, block);
    }
}
