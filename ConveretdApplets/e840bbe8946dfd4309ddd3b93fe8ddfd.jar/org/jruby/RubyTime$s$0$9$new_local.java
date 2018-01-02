// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyTime$s$0$9$new_local extends JavaMethodN
{
    public RubyTime$s$0$9$new_local(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1 || array.length > 10) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 10);
        }
        return RubyTime.new_local(recv, array);
    }
}
