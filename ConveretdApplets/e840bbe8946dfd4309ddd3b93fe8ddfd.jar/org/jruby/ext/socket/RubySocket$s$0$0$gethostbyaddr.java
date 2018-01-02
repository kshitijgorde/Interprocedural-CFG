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

public class RubySocket$s$0$0$gethostbyaddr extends JavaMethodN
{
    public RubySocket$s$0$0$gethostbyaddr(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 1);
        }
        return RubySocket.gethostbyaddr(context, recv, array);
    }
}
