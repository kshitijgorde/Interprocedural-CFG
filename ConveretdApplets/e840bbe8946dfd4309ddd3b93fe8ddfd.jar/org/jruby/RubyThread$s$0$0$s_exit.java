// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyThread$s$0$0$s_exit extends JavaMethodZeroBlock
{
    public RubyThread$s$0$0$s_exit(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject receiver, final RubyModule rubyModule, final String s, final Block block) {
        return RubyThread.s_exit(receiver, block);
    }
}
