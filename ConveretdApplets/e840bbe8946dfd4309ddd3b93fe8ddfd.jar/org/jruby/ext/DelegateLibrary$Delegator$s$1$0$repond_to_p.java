// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class DelegateLibrary$Delegator$s$1$0$repond_to_p extends JavaMethodOne
{
    public DelegateLibrary$Delegator$s$1$0$repond_to_p(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject name) {
        return DelegateLibrary.Delegator.repond_to_p(context, self, name);
    }
}
