// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaProxyReflectionObject$i$0$0$length extends JavaMethodZero
{
    public JavaProxyReflectionObject$i$0$0$length(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((JavaProxyReflectionObject)rubyObject).length();
    }
}
