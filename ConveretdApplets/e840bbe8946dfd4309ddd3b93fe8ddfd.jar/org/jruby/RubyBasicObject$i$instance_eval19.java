// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBasicObject$i$instance_eval19 extends JavaMethodZeroOrOneOrTwoOrThreeBlock
{
    public RubyBasicObject$i$instance_eval19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final Block block) {
        return ((RubyBasicObject)rubyObject).instance_eval19(context, arg0, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return ((RubyBasicObject)rubyObject).instance_eval19(context, arg0, arg2, arg3, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final Block block) {
        return ((RubyBasicObject)rubyObject).instance_eval19(context, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject arg0, final IRubyObject arg2, final Block block) {
        return ((RubyBasicObject)rubyObject).instance_eval19(context, arg0, arg2, block);
    }
}
