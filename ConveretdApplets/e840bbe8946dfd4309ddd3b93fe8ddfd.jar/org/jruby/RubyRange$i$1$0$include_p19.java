// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyRange$i$1$0$include_p19 extends JavaMethodOne
{
    public RubyRange$i$1$0$include_p19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject obj) {
        this.preFrameOnly(threadContext, self, name, Block.NULL_BLOCK);
        try {
            final IRubyObject include_p19 = ((RubyRange)self).include_p19(threadContext, obj);
            JavaMethod.postFrameOnly(threadContext);
            return include_p19;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
