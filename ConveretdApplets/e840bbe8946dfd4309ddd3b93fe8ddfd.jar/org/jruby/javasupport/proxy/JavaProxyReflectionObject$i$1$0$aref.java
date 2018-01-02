// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaProxyReflectionObject$i$1$0$aref extends JavaMethodOne
{
    public JavaProxyReflectionObject$i$1$0$aref(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject index) {
        return ((JavaProxyReflectionObject)rubyObject).aref(index);
    }
}
