// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.libraries;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class FiberLibrary$Fiber$i$0$0$initialize extends JavaMethodNBlock
{
    public FiberLibrary$Fiber$i$0$0$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block block) {
        return ((FiberLibrary.Fiber)rubyObject).initialize(context, args, block);
    }
}
