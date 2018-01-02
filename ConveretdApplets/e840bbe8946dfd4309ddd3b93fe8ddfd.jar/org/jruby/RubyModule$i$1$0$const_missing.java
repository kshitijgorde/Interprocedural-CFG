// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$i$1$0$const_missing extends JavaMethodOneBlock
{
    public RubyModule$i$1$0$const_missing(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject rubyName, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject const_missing = ((RubyModule)self).const_missing(threadContext, rubyName, block);
            JavaMethod.postFrameOnly(threadContext);
            return const_missing;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
