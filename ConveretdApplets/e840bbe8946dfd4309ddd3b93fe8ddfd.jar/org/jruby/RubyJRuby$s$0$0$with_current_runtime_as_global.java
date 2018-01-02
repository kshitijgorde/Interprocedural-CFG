// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$s$0$0$with_current_runtime_as_global extends JavaMethodZeroBlock
{
    public RubyJRuby$s$0$0$with_current_runtime_as_global(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final Block block) {
        return RubyJRuby.with_current_runtime_as_global(context, recv, block);
    }
}
