// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyKernel$s$respond_to_p19 extends JavaMethodOneOrTwo
{
    public RubyKernel$s$respond_to_p19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject mname, final IRubyObject includePrivate) {
        return RubyKernel.respond_to_p19(self, mname, includePrivate);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject mname) {
        return RubyKernel.respond_to_p19(self, mname);
    }
}
