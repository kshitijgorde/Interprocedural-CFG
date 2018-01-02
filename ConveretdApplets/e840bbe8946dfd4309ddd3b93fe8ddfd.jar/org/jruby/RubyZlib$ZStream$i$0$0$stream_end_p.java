// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$ZStream$i$0$0$stream_end_p extends JavaMethodZero
{
    public RubyZlib$ZStream$i$0$0$stream_end_p(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((RubyZlib.ZStream)rubyObject).stream_end_p();
    }
}
