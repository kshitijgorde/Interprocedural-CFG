// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyNameError$RubyNameErrorMessage$i$1$0$dump extends JavaMethodOne
{
    public RubyNameError$RubyNameErrorMessage$i$1$0$dump(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return ((RubyNameError.RubyNameErrorMessage)rubyObject).dump(context, arg);
    }
}
