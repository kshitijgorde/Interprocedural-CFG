// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$i$module_eval extends JavaMethodZeroOrOneOrTwoOrThreeBlock
{
    public RubyModule$i$module_eval(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject arg0, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject module_eval = ((RubyModule)self).module_eval(threadContext, arg0, arg2, arg3, block);
            JavaMethod.postFrameOnly(threadContext);
            return module_eval;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject arg0, final IRubyObject arg2, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject module_eval = ((RubyModule)self).module_eval(threadContext, arg0, arg2, block);
            JavaMethod.postFrameOnly(threadContext);
            return module_eval;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final IRubyObject arg0, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject module_eval = ((RubyModule)self).module_eval(threadContext, arg0, block);
            JavaMethod.postFrameOnly(threadContext);
            return module_eval;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String name, final Block block) {
        this.preFrameOnly(threadContext, self, name, block);
        try {
            final IRubyObject module_eval = ((RubyModule)self).module_eval(threadContext, block);
            JavaMethod.postFrameOnly(threadContext);
            return module_eval;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
