// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyProcess$RubyStatus$i$0$0$not_implemented extends JavaMethodZero
{
    public RubyProcess$RubyStatus$i$0$0$not_implemented(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String name) {
        this.preFrameOnly(context, self, name, Block.NULL_BLOCK);
        try {
            final IRubyObject not_implemented = ((RubyProcess.RubyStatus)self).not_implemented();
            JavaMethod.postFrameOnly(context);
            return not_implemented;
        }
        finally {
            JavaMethod.postFrameOnly(context);
        }
    }
}
