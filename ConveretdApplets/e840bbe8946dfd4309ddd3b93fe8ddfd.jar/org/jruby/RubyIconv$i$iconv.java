// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyIconv$i$iconv extends JavaMethodOneOrTwoOrThree
{
    public RubyIconv$i$iconv(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject str) {
        return ((RubyIconv)rubyObject).iconv(str);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject str, final IRubyObject startArg, final IRubyObject endArg) {
        return ((RubyIconv)rubyObject).iconv(str, startArg, endArg);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject str, final IRubyObject startArg) {
        return ((RubyIconv)rubyObject).iconv(str, startArg);
    }
}
