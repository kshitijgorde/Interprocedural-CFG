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

public class RubyObjectSpace$s$0$1$define_finalizer extends JavaMethodNBlock
{
    public RubyObjectSpace$s$0$1$define_finalizer(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 2);
        }
        return RubyObjectSpace.define_finalizer(recv, array, block);
    }
}
