// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.JavaMethod;

public class RubyBasicObject$s$1$0$singleton_method_undefined19 extends JavaMethodOneBlock
{
    public RubyBasicObject$s$1$0$singleton_method_undefined19(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject recv, final RubyModule rubyModule, final String s, final IRubyObject symbolId, final Block block) {
        return RubyBasicObject.singleton_method_undefined19(context, recv, symbolId, block);
    }
}
