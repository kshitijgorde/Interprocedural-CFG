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

public class RubyTime$i$0$1$dump extends JavaMethodNBlock
{
    public RubyTime$i$0$1$dump(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block unusedBlock) {
        if (array.length > 1) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 0, 1);
        }
        return ((RubyTime)rubyObject).dump(array, unusedBlock);
    }
}
