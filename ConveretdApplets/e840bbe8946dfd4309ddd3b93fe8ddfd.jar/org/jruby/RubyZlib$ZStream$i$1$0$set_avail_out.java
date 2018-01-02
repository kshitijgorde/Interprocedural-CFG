// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$ZStream$i$1$0$set_avail_out extends JavaMethodOne
{
    public RubyZlib$ZStream$i$1$0$set_avail_out(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject p5) {
        return ((RubyZlib.ZStream)rubyObject).set_avail_out(p5);
    }
}
