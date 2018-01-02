// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$respond_to_missing_p extends JavaMethodOneOrTwo
{
    public RubyKernel$s$respond_to_missing_p(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject symbol) {
        return RubyKernel.respond_to_missing_p(context, recv, symbol);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject symbol, final IRubyObject isPrivate) {
        return RubyKernel.respond_to_missing_p(context, recv, symbol, isPrivate);
    }
}
