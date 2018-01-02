// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubySocket$s$0$1$getnameinfo extends JavaMethodN
{
    public RubySocket$s$0$1$getnameinfo(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 2);
        }
        return RubySocket.getnameinfo(context, recv, array);
    }
}
