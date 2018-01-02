// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyArgsFile$s$0$2$read extends JavaMethodN
{
    public RubyArgsFile$s$0$2$read(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 2);
        }
        return RubyArgsFile.read(context, recv, array);
    }
}
