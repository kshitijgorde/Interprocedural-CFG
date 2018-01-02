// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class InterfaceJavaProxy$JavaInterfaceExtender$s$1$0$extend_proxy extends JavaMethodOne
{
    public InterfaceJavaProxy$JavaInterfaceExtender$s$1$0$extend_proxy(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject proxyClass) {
        return InterfaceJavaProxy.JavaInterfaceExtender.extend_proxy(context, self, proxyClass);
    }
}
