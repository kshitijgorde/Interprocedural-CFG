// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$rbThrow extends JavaMethodOneOrTwoBlock
{
    public RubyKernel$s$rbThrow(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject tag, final Block block) {
        return RubyKernel.rbThrow(context, recv, tag, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject tag, final IRubyObject arg, final Block block) {
        return RubyKernel.rbThrow(context, recv, tag, arg, block);
    }
}
