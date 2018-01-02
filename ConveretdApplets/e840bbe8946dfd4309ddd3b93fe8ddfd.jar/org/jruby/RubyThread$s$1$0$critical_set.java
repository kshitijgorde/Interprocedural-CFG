// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyThread$s$1$0$critical_set extends JavaMethodOne
{
    public RubyThread$s$1$0$critical_set(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject receiver, final RubyModule rubyModule, final String s, final IRubyObject value) {
        return RubyThread.critical_set(receiver, value);
    }
}
