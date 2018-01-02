// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyArray$i$aset extends JavaMethodTwoOrThree
{
    public RubyArray$i$aset(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2) {
        return ((RubyArray)rubyObject).aset(arg0, arg2);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final IRubyObject arg3) {
        return ((RubyArray)rubyObject).aset(arg0, arg2, arg3);
    }
}
