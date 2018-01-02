// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$s$0$2$adler32 extends JavaMethodN
{
    public RubyZlib$s$0$2$adler32(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 2) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 0, 2);
        }
        return RubyZlib.adler32(recv, array);
    }
}
