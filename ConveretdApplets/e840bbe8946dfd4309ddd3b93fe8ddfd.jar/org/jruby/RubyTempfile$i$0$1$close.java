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

public class RubyTempfile$i$0$1$close extends JavaMethodNBlock
{
    public RubyTempfile$i$0$1$close(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length > 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 0, 1);
        }
        return ((RubyTempfile)rubyObject).close(context, array, block);
    }
}
