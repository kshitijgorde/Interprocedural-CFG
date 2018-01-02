// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubySocket$s$2$0$pack_sockaddr_in extends JavaMethodTwo
{
    public RubySocket$s$2$0$pack_sockaddr_in(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject port, final IRubyObject host) {
        return RubySocket.pack_sockaddr_in(context, recv, port, host);
    }
}
