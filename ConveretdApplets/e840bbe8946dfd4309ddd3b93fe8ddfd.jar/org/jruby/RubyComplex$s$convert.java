// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyComplex$s$convert extends JavaMethodOneOrTwo
{
    public RubyComplex$s$convert(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject a1, final IRubyObject a2) {
        return RubyComplex.convert(context, recv, a1, a2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject a1) {
        return RubyComplex.convert(context, recv, a1);
    }
}
