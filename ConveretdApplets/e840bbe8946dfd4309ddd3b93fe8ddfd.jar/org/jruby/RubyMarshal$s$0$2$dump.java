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

public class RubyMarshal$s$0$2$dump extends JavaMethodNBlock
{
    public RubyMarshal$s$0$2$dump(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block unusedBlock) {
        if (array.length < 1 || array.length > 3) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 3);
        }
        return RubyMarshal.dump(recv, array, unusedBlock);
    }
}
