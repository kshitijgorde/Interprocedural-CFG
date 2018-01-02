// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$rbThrow19 extends JavaMethodOneOrTwoBlock
{
    public RubyKernel$s$rbThrow19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject tag, final Block block) {
        this.preFrameOnly(threadContext, rubyObject, name, block);
        try {
            final IRubyObject rbThrow19 = RubyKernel.rbThrow19(threadContext, rubyObject, tag, block);
            JavaMethod.postFrameOnly(threadContext);
            return rbThrow19;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject tag, final IRubyObject arg, final Block block) {
        this.preFrameOnly(threadContext, rubyObject, name, block);
        try {
            final IRubyObject rbThrow19 = RubyKernel.rbThrow19(threadContext, rubyObject, tag, arg, block);
            JavaMethod.postFrameOnly(threadContext);
            return rbThrow19;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
