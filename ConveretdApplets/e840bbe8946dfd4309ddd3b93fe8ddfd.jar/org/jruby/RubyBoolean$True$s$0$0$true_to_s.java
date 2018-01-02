// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBoolean$True$s$0$0$true_to_s extends JavaMethodZero
{
    public RubyBoolean$True$s$0$0$true_to_s(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject t, final RubyModule rubyModule, final String s) {
        return RubyBoolean.True.true_to_s(t);
    }
}
