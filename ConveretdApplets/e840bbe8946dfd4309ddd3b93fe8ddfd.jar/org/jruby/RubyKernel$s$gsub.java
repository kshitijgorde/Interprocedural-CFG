// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$gsub extends JavaMethodOneOrTwoBlock
{
    public RubyKernel$s$gsub(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final Block block) {
        return RubyKernel.gsub(context, recv, arg0, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final Block block) {
        return RubyKernel.gsub(context, recv, arg0, block);
    }
}
