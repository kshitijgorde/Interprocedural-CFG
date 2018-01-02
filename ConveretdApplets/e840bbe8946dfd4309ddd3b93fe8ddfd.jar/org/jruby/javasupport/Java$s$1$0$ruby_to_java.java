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

public class Java$s$1$0$ruby_to_java extends JavaMethodOneBlock
{
    public Java$s$1$0$ruby_to_java(final RubyModule implementationClass, final Visibility visibility) {
        super(implementationClass, visibility);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject rubyObject, final RubyModule rubyModule, final String name, final IRubyObject object, final Block block) {
        this.preFrameOnly(context, rubyObject, name, block);
        try {
            final IRubyObject ruby_to_java = Java.ruby_to_java(rubyObject, object, block);
            JavaMethod.postFrameOnly(context);
            return ruby_to_java;
        }
        finally {
            JavaMethod.postFrameOnly(context);
        }
    }
}
