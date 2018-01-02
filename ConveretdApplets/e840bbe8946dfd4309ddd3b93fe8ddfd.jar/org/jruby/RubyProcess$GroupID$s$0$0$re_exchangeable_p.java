// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyProcess$GroupID$s$0$0$re_exchangeable_p extends JavaMethodZero
{
    public RubyProcess$GroupID$s$0$0$re_exchangeable_p(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String s) {
        return RubyProcess.GroupID.re_exchangeable_p(self);
    }
}
