// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBasicSocket$s$1$0$set_do_not_reverse_lookup extends JavaMethodOne
{
    public RubyBasicSocket$s$1$0$set_do_not_reverse_lookup(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject flag) {
        return RubyBasicSocket.set_do_not_reverse_lookup(recv, flag);
    }
}