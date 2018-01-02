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

public class JavaProxy$s$1$0$inherited extends JavaMethodOne
{
    public JavaProxy$s$1$0$inherited(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext threadContext, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject subclass) {
        this.preFrameOnly(threadContext, rubyObject, name, Block.NULL_BLOCK);
        try {
            final IRubyObject inherited = JavaProxy.inherited(threadContext, rubyObject, subclass);
            JavaMethod.postFrameOnly(threadContext);
            return inherited;
        }
        finally {
            JavaMethod.postFrameOnly(threadContext);
        }
    }
}
