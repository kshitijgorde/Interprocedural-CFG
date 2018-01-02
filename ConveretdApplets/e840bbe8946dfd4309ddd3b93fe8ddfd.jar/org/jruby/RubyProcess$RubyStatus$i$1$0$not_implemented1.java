// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyProcess$RubyStatus$i$1$0$not_implemented1 extends JavaMethodOne
{
    public RubyProcess$RubyStatus$i$1$0$not_implemented1(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject arg) {
        this.preFrameOnly(context, self, name, Block.NULL_BLOCK);
        try {
            final IRubyObject not_implemented1 = ((RubyProcess.RubyStatus)self).not_implemented1(arg);
            JavaMethod.postFrameOnly(context);
            return not_implemented1;
        }
        finally {
            JavaMethod.postFrameOnly(context);
        }
    }
}
