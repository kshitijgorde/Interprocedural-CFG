// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$Deflate$i$2$0$params extends JavaMethodTwo
{
    public RubyZlib$Deflate$i$2$0$params(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject level, final IRubyObject strategy) {
        return ((RubyZlib.Deflate)rubyObject).params(context, level, strategy);
    }
}
