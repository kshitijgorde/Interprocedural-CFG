// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Java$s$2$0$new_proxy_instance2 extends JavaMethodTwoBlock
{
    public Java$s$2$0$new_proxy_instance2(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject wrapper, final IRubyObject ifcs, final Block block) {
        this.preFrameOnly(context, rubyObject, name, block);
        try {
            final IRubyObject new_proxy_instance2 = Java.new_proxy_instance2(rubyObject, wrapper, ifcs, block);
            JavaMethod.postFrameOnly(context);
            return new_proxy_instance2;
        }
        finally {
            JavaMethod.postFrameOnly(context);
        }
    }
}
