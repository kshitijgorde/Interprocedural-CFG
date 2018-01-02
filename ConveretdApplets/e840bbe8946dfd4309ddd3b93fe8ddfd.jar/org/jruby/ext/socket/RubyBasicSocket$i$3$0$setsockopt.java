// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBasicSocket$i$3$0$setsockopt extends JavaMethodThree
{
    public RubyBasicSocket$i$3$0$setsockopt(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject lev, final IRubyObject optname, final IRubyObject val) {
        return ((RubyBasicSocket)rubyObject).setsockopt(context, lev, optname, val);
    }
}
