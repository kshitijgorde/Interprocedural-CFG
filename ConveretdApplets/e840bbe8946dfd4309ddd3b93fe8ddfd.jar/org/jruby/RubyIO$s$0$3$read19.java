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

public class RubyIO$s$0$3$read19 extends JavaMethodNBlock
{
    public RubyIO$s$0$3$read19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block unusedBlock) {
        if (array.length < 1 || array.length > 4) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 4);
        }
        return RubyIO.read19(context, recv, array, unusedBlock);
    }
}
