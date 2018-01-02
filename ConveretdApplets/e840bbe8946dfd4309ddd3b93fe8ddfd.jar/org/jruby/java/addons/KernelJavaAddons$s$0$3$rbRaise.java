// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class KernelJavaAddons$s$0$3$rbRaise extends JavaMethodNBlock
{
    public KernelJavaAddons$s$0$3$rbRaise(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject[] array, final Block block) {
        if (array.length > 3) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 0, 3);
        }
        this.preFrameOnly(threadContext, rubyObject, name, block);
        try {
            final IRubyObject rbRaise = KernelJavaAddons.rbRaise(threadContext, rubyObject, array, block);
            JavaMethod.postFrameOnly(threadContext);
            return rbRaise;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
