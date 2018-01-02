// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyArray$i$fill extends JavaMethodZeroOrOneOrTwoOrThreeBlock
{
    public RubyArray$i$fill(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return ((RubyArray)rubyObject).fill(context, arg1, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg, final Block block) {
        return ((RubyArray)rubyObject).fill(context, arg, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final Block block) {
        return ((RubyArray)rubyObject).fill(context, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return ((RubyArray)rubyObject).fill(context, arg1, arg2, arg3, block);
    }
}
