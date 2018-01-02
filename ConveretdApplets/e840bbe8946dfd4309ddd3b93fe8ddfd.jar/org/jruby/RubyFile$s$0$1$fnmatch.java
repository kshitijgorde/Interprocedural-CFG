// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyFile$s$0$1$fnmatch extends JavaMethodN
{
    public RubyFile$s$0$1$fnmatch(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 2 || array.length > 3) {
            Arity.checkArgumentCount(context.getRuntime(), array, 2, 3);
        }
        return RubyFile.fnmatch(context, recv, array);
    }
}
