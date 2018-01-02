// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$s$0$0$nesting extends JavaMethodZeroBlock
{
    public RubyModule$s$0$0$nesting(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final Block block) {
        this.preFrameOnly(threadContext, rubyObject, name, block);
        try {
            final RubyArray nesting = RubyModule.nesting(threadContext, rubyObject, block);
            JavaMethod.postFrameOnly(threadContext);
            return nesting;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
