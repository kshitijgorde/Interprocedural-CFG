// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyDigest$DigestClass$s$0$1$s_hexdigest extends JavaMethodNBlock
{
    public RubyDigest$DigestClass$s$0$1$s_hexdigest(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext ctx, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block unusedBlock) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(ctx.getRuntime(), array, 1, 2);
        }
        return RubyDigest.DigestClass.s_hexdigest(ctx, recv, array, unusedBlock);
    }
}
