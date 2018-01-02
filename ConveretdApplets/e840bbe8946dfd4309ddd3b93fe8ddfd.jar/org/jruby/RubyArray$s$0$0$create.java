// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyArray$s$0$0$create extends JavaMethodNBlock
{
    public RubyArray$s$0$0$create(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject klass, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block block) {
        return RubyArray.create(klass, args, block);
    }
}
