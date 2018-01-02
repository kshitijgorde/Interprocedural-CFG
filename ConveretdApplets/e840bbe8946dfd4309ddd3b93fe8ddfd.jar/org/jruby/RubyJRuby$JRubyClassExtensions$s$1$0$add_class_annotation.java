// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyClassExtensions$s$1$0$add_class_annotation extends JavaMethodOne
{
    public RubyJRuby$JRubyClassExtensions$s$1$0$add_class_annotation(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeClass, final RubyModule rubyModule, final String s, final IRubyObject annoMap) {
        return RubyJRuby.JRubyClassExtensions.add_class_annotation(context, maybeClass, annoMap);
    }
}
