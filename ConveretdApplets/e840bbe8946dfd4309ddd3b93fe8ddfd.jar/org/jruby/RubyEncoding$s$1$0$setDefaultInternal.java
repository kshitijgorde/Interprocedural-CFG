// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyEncoding$s$1$0$setDefaultInternal extends JavaMethodOne
{
    public RubyEncoding$s$1$0$setDefaultInternal(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject encoding) {
        RubyEncoding.setDefaultInternal(recv, encoding);
        return threadContext.getRuntime().getNil();
    }
}