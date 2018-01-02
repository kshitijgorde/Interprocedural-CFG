// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyClassExtensions$s$2$0$add_parameter_annotations extends JavaMethodTwo
{
    public RubyJRuby$JRubyClassExtensions$s$2$0$add_parameter_annotations(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject maybeClass, final RubyModule rubyModule, final String s, final IRubyObject methodName, final IRubyObject paramAnnoMaps) {
        return RubyJRuby.JRubyClassExtensions.add_parameter_annotations(context, maybeClass, methodName, paramAnnoMaps);
    }
}
