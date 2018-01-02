// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class JRubyTypeLibrary$s$3$0$coerce_to extends JavaMethodThree
{
    public JRubyTypeLibrary$s$3$0$coerce_to(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject object, final IRubyObject clazz, final IRubyObject method) {
        return JRubyTypeLibrary.coerce_to(context, self, object, clazz, method);
    }
}
