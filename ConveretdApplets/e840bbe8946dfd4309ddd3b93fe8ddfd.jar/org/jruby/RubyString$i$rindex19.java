// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyString$i$rindex19 extends JavaMethodOneOrTwo
{
    public RubyString$i$rindex19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2) {
        return ((RubyString)rubyObject).rindex19(context, arg0, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0) {
        return ((RubyString)rubyObject).rindex19(context, arg0);
    }
}
