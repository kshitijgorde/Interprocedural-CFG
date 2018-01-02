// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBigDecimal$i$0$2$round extends JavaMethodN
{
    public RubyBigDecimal$i$0$2$round(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 2);
        }
        return ((RubyBigDecimal)rubyObject).round(context, array);
    }
}
