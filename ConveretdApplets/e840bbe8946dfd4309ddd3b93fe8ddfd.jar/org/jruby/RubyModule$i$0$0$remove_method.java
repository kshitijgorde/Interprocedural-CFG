// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyModule$i$0$0$remove_method extends JavaMethodN
{
    public RubyModule$i$0$0$remove_method(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 1);
        }
        return ((RubyModule)rubyObject).remove_method(context, array);
    }
}
