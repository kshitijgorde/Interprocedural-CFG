// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyZlib$RubyGzipReader$i$0$0$lineno extends JavaMethodZero
{
    public RubyZlib$RubyGzipReader$i$0$0$lineno(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((RubyZlib.RubyGzipReader)rubyObject).lineno();
    }
}
