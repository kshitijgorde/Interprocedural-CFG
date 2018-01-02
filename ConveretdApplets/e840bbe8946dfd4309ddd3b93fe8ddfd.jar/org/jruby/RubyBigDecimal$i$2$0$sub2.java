// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBigDecimal$i$2$0$sub2 extends JavaMethodTwo
{
    public RubyBigDecimal$i$2$0$sub2(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject b, final IRubyObject n) {
        return ((RubyBigDecimal)rubyObject).sub2(context, b, n);
    }
}
