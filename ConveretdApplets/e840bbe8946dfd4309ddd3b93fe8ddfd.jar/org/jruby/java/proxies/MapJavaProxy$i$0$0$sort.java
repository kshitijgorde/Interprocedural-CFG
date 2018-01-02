// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class MapJavaProxy$i$0$0$sort extends JavaMethodZeroBlock
{
    public MapJavaProxy$i$0$0$sort(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String s, final Block block) {
        return ((MapJavaProxy)rubyObject).sort(context, block);
    }
}
