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

public class RubyStruct$s$0$0$newInstance extends JavaMethodNBlock
{
    public RubyStruct$s$0$0$newInstance(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 1) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 1);
        }
        return RubyStruct.newInstance(recv, array, block);
    }
}
