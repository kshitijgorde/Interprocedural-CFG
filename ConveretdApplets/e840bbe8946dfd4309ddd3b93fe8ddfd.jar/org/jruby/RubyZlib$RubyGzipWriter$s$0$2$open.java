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

public class RubyZlib$RubyGzipWriter$s$0$2$open extends JavaMethodNBlock
{
    public RubyZlib$RubyGzipWriter$s$0$2$open(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 1 || array.length > 3) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 3);
        }
        return RubyZlib.RubyGzipWriter.open(context, recv, array, block);
    }
}
