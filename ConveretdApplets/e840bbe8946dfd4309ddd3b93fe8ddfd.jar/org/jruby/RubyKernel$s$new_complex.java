// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$new_complex extends JavaMethodZeroOrOneOrTwo
{
    public RubyKernel$s$new_complex(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return RubyKernel.new_complex(context, recv, arg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s) {
        return RubyKernel.new_complex(context, recv);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2) {
        return RubyKernel.new_complex(context, recv, arg0, arg2);
    }
}
