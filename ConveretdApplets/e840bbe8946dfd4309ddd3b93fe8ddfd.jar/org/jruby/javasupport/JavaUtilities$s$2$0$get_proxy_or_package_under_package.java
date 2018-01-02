// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaUtilities$s$2$0$get_proxy_or_package_under_package extends JavaMethodTwo
{
    public JavaUtilities$s$2$0$get_proxy_or_package_under_package(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2) {
        return JavaUtilities.get_proxy_or_package_under_package(context, recv, arg0, arg2);
    }
}
