// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyConverter;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyConverter$i$0$0$convpath;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyConverter$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            final JavaMethod javaMethod = new RubyConverter$i$0$0$convpath(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "convpath", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyConverter.class, "convpath", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("convpath", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyConverter.convpath", "convpath");
        }
    }
}
