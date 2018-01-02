// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyDigest$s$1$0$const_missing extends JavaMethodOne
{
    public RubyDigest$s$1$0$const_missing(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext ctx, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject symbol) {
        return RubyDigest.const_missing(ctx, recv, symbol);
    }
}
