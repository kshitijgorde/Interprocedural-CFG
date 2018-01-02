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

public class RubyUNIXSocket$i$0$1$recvfrom extends JavaMethodN
{
    public RubyUNIXSocket$i$0$1$recvfrom(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 2);
        }
        return ((RubyUNIXSocket)rubyObject).recvfrom(context, array);
    }
}
