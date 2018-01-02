// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaProxy$i$java_method extends JavaMethodOneOrTwo
{
    public JavaProxy$i$java_method(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rubyName, final IRubyObject argTypes) {
        return ((JavaProxy)rubyObject).java_method(context, rubyName, argTypes);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject rubyName) {
        return ((JavaProxy)rubyObject).java_method(context, rubyName);
    }
}
