// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBigDecimal$s$0$1$newInstance extends JavaMethodN
{
    public RubyBigDecimal$s$0$1$newInstance(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 2);
        }
        return RubyBigDecimal.newInstance(recv, array);
    }
}
