// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Java$JavaProxyClassMethods$s$java_send extends JavaMethodOneOrTwoOrThreeOrN
{
    public Java$JavaProxyClassMethods$s$java_send(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 4) {
            Arity.checkArgumentCount(context.getRuntime(), array, 4, 4);
        }
        return Java.JavaProxyClassMethods.java_send(context, recv, array);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject rubyName) {
        return Java.JavaProxyClassMethods.java_send(context, recv, rubyName);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject rubyName, final IRubyObject argTypes) {
        return Java.JavaProxyClassMethods.java_send(context, recv, rubyName, argTypes);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject rubyName, final IRubyObject argTypes, final IRubyObject arg0) {
        return Java.JavaProxyClassMethods.java_send(context, recv, rubyName, argTypes, arg0);
    }
}
