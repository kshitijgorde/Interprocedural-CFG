// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyArray$i$insert19 extends JavaMethodOneOrTwoOrN
{
    public RubyArray$i$insert19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1) {
            Arity.checkArgumentCount(threadContext.getRuntime(), array, 1, 1);
        }
        return ((RubyArray)rubyObject).insert19(array);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg1, final IRubyObject arg2) {
        return ((RubyArray)rubyObject).insert19(arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg) {
        return ((RubyArray)rubyObject).insert19(arg);
    }
}
