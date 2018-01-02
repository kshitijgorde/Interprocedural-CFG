// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBoolean$False$s$0$0$false_to_s extends JavaMethodZero
{
    public RubyBoolean$False$s$0$0$false_to_s(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject f, final RubyModule rubyModule, final String s) {
        return RubyBoolean.False.false_to_s(f);
    }
}
