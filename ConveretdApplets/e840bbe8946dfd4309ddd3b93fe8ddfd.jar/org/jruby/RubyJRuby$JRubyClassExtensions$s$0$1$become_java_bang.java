// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyClassExtensions$s$0$1$become_java_bang extends JavaMethodN
{
    public RubyJRuby$JRubyClassExtensions$s$0$1$become_java_bang(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeClass, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 1);
        }
        return RubyJRuby.JRubyClassExtensions.become_java_bang(context, maybeClass, array);
    }
}
