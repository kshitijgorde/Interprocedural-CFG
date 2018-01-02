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

public class RubyIconv$RubyFailure$i$0$2$initialize extends JavaMethodNBlock
{
    public RubyIconv$RubyFailure$i$0$2$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 1 || array.length > 3) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 3);
        }
        return ((RubyIconv.RubyFailure)rubyObject).initialize(array, block);
    }
}
