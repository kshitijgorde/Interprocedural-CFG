// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyJRuby$JRubySynchronizedMeta$s$1$0$extend_object extends JavaMethodOne
{
    public RubyJRuby$JRubySynchronizedMeta$s$1$0$extend_object(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject self, final RubyModule rubyModule, final String s, final IRubyObject obj) {
        return RubyJRuby.JRubySynchronizedMeta.extend_object(self, obj);
    }
}