// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubyExecutionContextLocal$i$0$0$getDefaultProc extends JavaMethodZero
{
    public RubyJRuby$JRubyExecutionContextLocal$i$0$0$getDefaultProc(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((RubyJRuby.JRubyExecutionContextLocal)rubyObject).getDefaultProc(context);
    }
}
