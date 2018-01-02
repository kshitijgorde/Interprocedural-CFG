// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyPrecision$s$0$0$prec_i extends JavaMethodZeroBlock
{
    public RubyPrecision$s$0$0$prec_i(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject receiver, final RubyModule rubyModule, final String s, final Block block) {
        return RubyPrecision.prec_i(context, receiver, block);
    }
}
