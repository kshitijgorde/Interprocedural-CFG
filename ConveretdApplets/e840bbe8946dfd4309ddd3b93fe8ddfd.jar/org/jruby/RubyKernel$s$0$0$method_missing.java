// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$0$0$method_missing extends JavaMethodNBlock
{
    public RubyKernel$s$0$0$method_missing(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject[] args, final Block block) {
        this.preFrameOnly(threadContext, rubyObject, name, block);
        try {
            final IRubyObject method_missing = RubyKernel.method_missing(threadContext, rubyObject, args, block);
            JavaMethod.postFrameOnly(threadContext);
            return method_missing;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
