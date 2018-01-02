// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$i$module_exec extends JavaMethodZeroOrNBlock
{
    public RubyModule$i$module_exec(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject module_exec = ((RubyModule)self).module_exec(threadContext, block);
            JavaMethod.postFrameOnly(threadContext);
            return module_exec;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject[] args, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject module_exec = ((RubyModule)self).module_exec(threadContext, args, block);
            JavaMethod.postFrameOnly(threadContext);
            return module_exec;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
