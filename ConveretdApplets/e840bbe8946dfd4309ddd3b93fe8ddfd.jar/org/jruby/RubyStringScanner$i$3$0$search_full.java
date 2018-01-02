// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyStringScanner$i$3$0$search_full extends JavaMethodThree
{
    public RubyStringScanner$i$3$0$search_full(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject regex, final IRubyObject s2, final IRubyObject f) {
        return ((RubyStringScanner)rubyObject).search_full(regex, s2, f);
    }
}
