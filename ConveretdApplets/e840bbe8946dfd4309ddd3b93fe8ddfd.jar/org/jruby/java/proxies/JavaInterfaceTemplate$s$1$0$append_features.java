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

public class JavaInterfaceTemplate$s$1$0$append_features extends JavaMethodOneBlock
{
    public JavaInterfaceTemplate$s$1$0$append_features(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject clazz, final Block block) {
        this.preFrameOnly(threadContext, rubyObject, name, block);
        try {
            final IRubyObject append_features = JavaInterfaceTemplate.append_features(threadContext, rubyObject, clazz, block);
            JavaMethod.postFrameOnly(threadContext);
            return append_features;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
