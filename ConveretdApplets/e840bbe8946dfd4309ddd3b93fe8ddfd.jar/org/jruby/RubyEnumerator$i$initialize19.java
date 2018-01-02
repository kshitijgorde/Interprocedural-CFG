// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyEnumerator$i$initialize19 extends JavaMethodZeroOrOneOrTwoOrThreeBlock
{
    public RubyEnumerator$i$initialize19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject object, final IRubyObject method, final IRubyObject methodArg, final Block block) {
        return ((RubyEnumerator)rubyObject).initialize19(context, object, method, methodArg, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final Block block) {
        return ((RubyEnumerator)rubyObject).initialize19(context, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject object, final Block block) {
        return ((RubyEnumerator)rubyObject).initialize19(context, object, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject object, final IRubyObject method, final Block block) {
        return ((RubyEnumerator)rubyObject).initialize19(context, object, method, block);
    }
}
