// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyProcess$UserID$s$1$0$grant_privilege extends JavaMethodOne
{
    public RubyProcess$UserID$s$1$0$grant_privilege(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return RubyProcess.UserID.grant_privilege(self, arg);
    }
}
