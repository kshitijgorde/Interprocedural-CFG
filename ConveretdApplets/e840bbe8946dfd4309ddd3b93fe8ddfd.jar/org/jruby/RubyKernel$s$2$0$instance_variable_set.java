// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$2$0$instance_variable_set extends JavaMethodTwo
{
    public RubyKernel$s$2$0$instance_variable_set(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject name, final IRubyObject value) {
        return RubyKernel.instance_variable_set(self, name, value);
    }
}
