// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$0$1$private_methods extends JavaMethodN
{
    public RubyKernel$s$0$1$private_methods(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 1);
        }
        return RubyKernel.private_methods(context, self, array);
    }
}
