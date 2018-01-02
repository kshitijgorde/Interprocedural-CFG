// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Java$JavaProxyClassMethods$s$java_method extends JavaMethodOneOrTwo
{
    public Java$JavaProxyClassMethods$s$java_method(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject proxyClass, final RubyModule rubyModule, final String s, final IRubyObject rubyName) {
        return Java.JavaProxyClassMethods.java_method(context, proxyClass, rubyName);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject proxyClass, final RubyModule rubyModule, final String s, final IRubyObject rubyName, final IRubyObject argTypes) {
        return Java.JavaProxyClassMethods.java_method(context, proxyClass, rubyName, argTypes);
    }
}
