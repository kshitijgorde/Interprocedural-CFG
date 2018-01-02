// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubySignal$s$2$0$__jtrap_kernel extends JavaMethodTwo
{
    public RubySignal$s$2$0$__jtrap_kernel(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject block, final IRubyObject sig) {
        return RubySignal.__jtrap_kernel(recv, block, sig);
    }
}
