// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.JavaMethod;

public class Java$s$1$0$java_to_primitive extends JavaMethodOneBlock
{
    public Java$s$1$0$java_to_primitive(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject object, final Block block) {
        this.preFrameOnly(context, rubyObject, name, block);
        try {
            final IRubyObject java_to_primitive = Java.java_to_primitive(rubyObject, object, block);
            JavaMethod.postFrameOnly(context);
            return java_to_primitive;
        }
        finally {
            JavaMethod.postFrameOnly(context);
        }
    }
}
