// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyEnumerator$RubyEnumeratorKernel$s$obj_to_enum extends JavaMethodZeroOrOneOrTwoOrNBlock
{
    public RubyEnumerator$RubyEnumeratorKernel$s$obj_to_enum(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final Block block) {
        return RubyEnumerator.RubyEnumeratorKernel.obj_to_enum(context, self, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg, final Block block) {
        return RubyEnumerator.RubyEnumeratorKernel.obj_to_enum(context, self, arg, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final Block block) {
        return RubyEnumerator.RubyEnumeratorKernel.obj_to_enum(context, self, arg0, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block block) {
        return RubyEnumerator.RubyEnumeratorKernel.obj_to_enum(context, self, args, block);
    }
}
