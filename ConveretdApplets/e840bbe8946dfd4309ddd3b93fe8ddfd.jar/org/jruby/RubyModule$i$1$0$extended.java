// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$i$1$0$extended extends JavaMethodOneBlock
{
    public RubyModule$i$1$0$extended(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject other, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject extended = ((RubyModule)self).extended(threadContext, other, block);
            JavaMethod.postFrameOnly(threadContext);
            return extended;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
