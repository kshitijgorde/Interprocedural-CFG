// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Timeout$TimeoutToplevel$s$0$1$timeout extends JavaMethodNBlock
{
    public Timeout$TimeoutToplevel$s$0$1$timeout(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 2);
        }
        return Timeout.TimeoutToplevel.timeout(context, self, array, block);
    }
}
