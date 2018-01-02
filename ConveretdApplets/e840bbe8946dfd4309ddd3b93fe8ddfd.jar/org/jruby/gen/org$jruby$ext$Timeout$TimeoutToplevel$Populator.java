// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.Timeout;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.Timeout$TimeoutToplevel$s$0$1$timeout;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$Timeout$TimeoutToplevel$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new Timeout$TimeoutToplevel$s$0$1$timeout(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "timeout", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Timeout.TimeoutToplevel.class, "timeout", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("timeout", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.Timeout.TimeoutToplevel.timeout", "timeout");
    }
}
