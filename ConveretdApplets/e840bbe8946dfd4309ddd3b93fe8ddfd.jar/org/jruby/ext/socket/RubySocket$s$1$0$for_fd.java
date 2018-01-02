// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubySocket$s$1$0$for_fd extends JavaMethodOne
{
    public RubySocket$s$1$0$for_fd(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject socketClass, final RubyModule rubyModule, final String s, final IRubyObject fd) {
        return RubySocket.for_fd(context, socketClass, fd);
    }
}
