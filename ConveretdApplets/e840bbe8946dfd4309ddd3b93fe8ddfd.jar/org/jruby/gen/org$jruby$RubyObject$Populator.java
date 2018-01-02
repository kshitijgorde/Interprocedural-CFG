// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyObject$i$0$0$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyObject$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            final JavaMethod javaMethod = new RubyObject$i$0$0$initialize(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 0, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyObject.class, "initialize", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyObject.initialize", "initialize");
        }
    }
}
