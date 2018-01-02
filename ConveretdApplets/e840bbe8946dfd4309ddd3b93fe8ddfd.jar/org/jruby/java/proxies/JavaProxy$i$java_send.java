// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaProxy$i$java_send extends JavaMethodOneOrTwoOrThreeOrN
{
    public JavaProxy$i$java_send(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rubyName) {
        return ((JavaProxy)rubyObject).java_send(context, rubyName);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 4) {
            Arity.checkArgumentCount(context.getRuntime(), array, 4, 4);
        }
        return ((JavaProxy)rubyObject).java_send(context, array);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rubyName, final IRubyObject argTypes, final IRubyObject arg0) {
        return ((JavaProxy)rubyObject).java_send(context, rubyName, argTypes, arg0);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rubyName, final IRubyObject argTypes) {
        return ((JavaProxy)rubyObject).java_send(context, rubyName, argTypes);
    }
}
