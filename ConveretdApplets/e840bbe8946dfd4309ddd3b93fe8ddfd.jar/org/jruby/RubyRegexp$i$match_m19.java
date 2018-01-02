// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyRegexp$i$match_m19 extends JavaMethodOneOrTwoBlock
{
    public RubyRegexp$i$match_m19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject str, final IRubyObject pos, final Block block) {
        return ((RubyRegexp)rubyObject).match_m19(context, str, pos, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject str, final Block block) {
        return ((RubyRegexp)rubyObject).match_m19(context, str, block);
    }
}
