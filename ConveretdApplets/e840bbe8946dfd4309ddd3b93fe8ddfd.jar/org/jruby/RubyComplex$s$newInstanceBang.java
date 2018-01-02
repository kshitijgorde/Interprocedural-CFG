// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyComplex$s$newInstanceBang extends JavaMethodOneOrTwo
{
    public RubyComplex$s$newInstanceBang(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject real, final IRubyObject image) {
        return RubyComplex.newInstanceBang(context, recv, real, image);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject real) {
        return RubyComplex.newInstanceBang(context, recv, real);
    }
}
