// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class MapJavaProxy$i$0$1$fetch extends JavaMethodNBlock
{
    public MapJavaProxy$i$0$1$fetch(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final IRubyObject[] array, final Block block) {
        if (array.length < 1 || array.length > 2) {
            Arity.checkArgumentCount(context.getRuntime(), array, 1, 2);
        }
        return ((MapJavaProxy)rubyObject).fetch(context, array, block);
    }
}
