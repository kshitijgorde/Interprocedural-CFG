// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBasicSocket$i$1$0$set_do_not_reverse_lookup19 extends JavaMethodOne
{
    public RubyBasicSocket$i$1$0$set_do_not_reverse_lookup19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject flag) {
        return ((RubyBasicSocket)rubyObject).set_do_not_reverse_lookup19(context, flag);
    }
}
