// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyPrecision$s$1$0$prec extends JavaMethodOneBlock
{
    public RubyPrecision$s$1$0$prec(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject receiver, final RubyModule rubyModule, final String s, final IRubyObject type, final Block block) {
        return RubyPrecision.prec(context, receiver, type, block);
    }
}
