// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$RubyGzipFile$i$1$0$set_sync extends JavaMethodOne
{
    public RubyZlib$RubyGzipFile$i$1$0$set_sync(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject ignored) {
        return ((RubyZlib.RubyGzipFile)rubyObject).set_sync(ignored);
    }
}
