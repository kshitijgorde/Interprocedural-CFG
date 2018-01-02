// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyIconv$i$2$0$initialize extends JavaMethodTwoBlock
{
    public RubyIconv$i$2$0$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg1, final IRubyObject arg2, final Block unusedBlock) {
        return ((RubyIconv)rubyObject).initialize(arg1, arg2, unusedBlock);
    }
}
