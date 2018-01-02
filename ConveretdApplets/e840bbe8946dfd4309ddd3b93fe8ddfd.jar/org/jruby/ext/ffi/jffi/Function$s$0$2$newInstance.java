// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Function$s$0$2$newInstance extends JavaMethodNBlock
{
    public Function$s$0$2$newInstance(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 2 || array.length > 4) {
            Arity.checkArgumentCount(context.getRuntime(), array, 2, 4);
        }
        return Function.newInstance(context, recv, array, block);
    }
}
