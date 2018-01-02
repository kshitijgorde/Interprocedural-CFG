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

public class RubyTCPSocket$i$0$2$initialize extends JavaMethodN
{
    public RubyTCPSocket$i$0$2$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 2 || array.length > 4) {
            Arity.checkArgumentCount(context.getRuntime(), array, 2, 4);
        }
        return ((RubyTCPSocket)rubyObject).initialize(context, array);
    }
}
