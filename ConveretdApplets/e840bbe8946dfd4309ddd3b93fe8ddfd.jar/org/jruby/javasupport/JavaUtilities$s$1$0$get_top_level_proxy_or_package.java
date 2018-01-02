// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JavaUtilities$s$1$0$get_top_level_proxy_or_package extends JavaMethodOne
{
    public JavaUtilities$s$1$0$get_top_level_proxy_or_package(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject arg0) {
        return JavaUtilities.get_top_level_proxy_or_package(context, recv, arg0);
    }
}
