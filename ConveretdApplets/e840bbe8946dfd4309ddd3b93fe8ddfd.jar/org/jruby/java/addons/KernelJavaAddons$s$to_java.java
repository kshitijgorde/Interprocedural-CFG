// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class KernelJavaAddons$s$to_java extends JavaMethodZeroOrOne
{
    public KernelJavaAddons$s$to_java(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject fromObject, final RubyModule rubyModule, final String s) {
        return KernelJavaAddons.to_java(context, fromObject);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject fromObject, final RubyModule rubyModule, final String s, final IRubyObject type) {
        return KernelJavaAddons.to_java(context, fromObject, type);
    }
}
