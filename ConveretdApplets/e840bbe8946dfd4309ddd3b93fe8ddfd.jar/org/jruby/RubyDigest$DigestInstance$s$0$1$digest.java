// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyDigest$DigestInstance$s$0$1$digest extends JavaMethodN
{
    public RubyDigest$DigestInstance$s$0$1$digest(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext ctx, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length > 1) {
            Arity.checkArgumentCount(ctx.getRuntime(), array, 0, 1);
        }
        return RubyDigest.DigestInstance.digest(ctx, self, array);
    }
}
