// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyComplex$s$2$0$polar extends JavaMethodTwo
{
    public RubyComplex$s$2$0$polar(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject clazz, final RubyModule rubyModule, final String s, final IRubyObject abs, final IRubyObject arg) {
        return RubyComplex.polar(context, clazz, abs, arg);
    }
}
