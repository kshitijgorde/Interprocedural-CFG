// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyIO$i$fcntl extends JavaMethodOneOrTwo
{
    public RubyIO$i$fcntl(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject cmd) {
        return ((RubyIO)rubyObject).fcntl(context, cmd);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject cmd, final IRubyObject arg) {
        return ((RubyIO)rubyObject).fcntl(context, cmd, arg);
    }
}
