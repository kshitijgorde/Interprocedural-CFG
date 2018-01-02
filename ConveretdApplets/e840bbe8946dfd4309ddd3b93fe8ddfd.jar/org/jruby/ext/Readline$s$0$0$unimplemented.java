// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Readline$s$0$0$unimplemented extends JavaMethodZero
{
    public Readline$s$0$0$unimplemented(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name) {
        this.preFrameOnly(threadContext, rubyObject, name, Block.NULL_BLOCK);
        try {
            final IRubyObject unimplemented = Readline.unimplemented(threadContext, rubyObject);
            JavaMethod.postFrameOnly(threadContext);
            return unimplemented;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
