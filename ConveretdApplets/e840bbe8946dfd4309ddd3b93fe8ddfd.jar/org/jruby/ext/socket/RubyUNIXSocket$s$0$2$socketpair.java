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

public class RubyUNIXSocket$s$0$2$socketpair extends JavaMethodN
{
    public RubyUNIXSocket$s$0$2$socketpair(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 2);
        }
        return RubyUNIXSocket.socketpair(context, recv, array);
    }
}
