// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyArray$i$aset19 extends JavaMethodTwoOrThree
{
    public RubyArray$i$aset19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final IRubyObject arg3) {
        return ((RubyArray)rubyObject).aset19(arg0, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2) {
        return ((RubyArray)rubyObject).aset19(arg0, arg2);
    }
}
