// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBasicObject$i$0$3$instance_exec19 extends JavaMethodNBlock
{
    public RubyBasicObject$i$0$3$instance_exec19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] args, final Block block) {
        return ((RubyBasicObject)rubyObject).instance_exec19(context, args, block);
    }
}