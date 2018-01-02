// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyClassExtensions$s$0$0$java_class extends JavaMethodZero
{
    public RubyJRuby$JRubyClassExtensions$s$0$0$java_class(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeClass, final RubyModule rubyModule, final String s) {
        return RubyJRuby.JRubyClassExtensions.java_class(context, maybeClass);
    }
}
