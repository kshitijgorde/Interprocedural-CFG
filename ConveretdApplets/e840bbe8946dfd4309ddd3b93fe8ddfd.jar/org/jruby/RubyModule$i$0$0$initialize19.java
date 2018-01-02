// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$i$0$0$initialize19 extends JavaMethodZeroBlock
{
    public RubyModule$i$0$0$initialize19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject initialize19 = ((RubyModule)self).initialize19(threadContext, block);
            JavaMethod.postFrameOnly(threadContext);
            return initialize19;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
