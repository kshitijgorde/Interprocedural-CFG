// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyEnumerator$i$initialize extends JavaMethodZeroOrOneOrTwoOrThreeOrN
{
    public RubyEnumerator$i$initialize(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject object, final IRubyObject method, final IRubyObject methodArg) {
        return ((RubyEnumerator)rubyObject).initialize(context, object, method, methodArg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject object, final IRubyObject method) {
        return ((RubyEnumerator)rubyObject).initialize(context, object, method);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject object) {
        return ((RubyEnumerator)rubyObject).initialize(context, object);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s) {
        return ((RubyEnumerator)rubyObject).initialize(context);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array) {
        if (array.length < 1) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 1);
        }
        return ((RubyEnumerator)rubyObject).initialize(context, array);
    }
}
