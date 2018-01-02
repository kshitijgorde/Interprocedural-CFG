// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$instance_eval extends JavaMethodZeroOrOneOrTwoOrThreeBlock
{
    public RubyKernel$s$instance_eval(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return RubyKernel.instance_eval(context, self, arg0, arg2, arg3, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final Block block) {
        return RubyKernel.instance_eval(context, self, arg0, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg0, final Block block) {
        return RubyKernel.instance_eval(context, self, arg0, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final Block block) {
        return RubyKernel.instance_eval(context, self, block);
    }
}
