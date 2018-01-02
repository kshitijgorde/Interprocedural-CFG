// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyExtensions$s$2$0$steal_method extends JavaMethodTwo
{
    public RubyJRuby$JRubyExtensions$s$2$0$steal_method(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject type, final IRubyObject methodName) {
        return RubyJRuby.JRubyExtensions.steal_method(recv, type, methodName);
    }
}
