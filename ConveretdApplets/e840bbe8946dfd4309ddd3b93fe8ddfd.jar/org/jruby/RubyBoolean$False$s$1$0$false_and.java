// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBoolean$False$s$1$0$false_and extends JavaMethodOne
{
    public RubyBoolean$False$s$1$0$false_and(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject f, final RubyModule rubyModule, final String s, final IRubyObject oth) {
        return RubyBoolean.False.false_and(f, oth);
    }
}
