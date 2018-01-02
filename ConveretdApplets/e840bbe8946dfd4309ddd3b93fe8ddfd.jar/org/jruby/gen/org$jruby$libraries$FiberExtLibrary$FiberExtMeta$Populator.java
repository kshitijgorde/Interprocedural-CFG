// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.FiberExtLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.FiberExtLibrary$FiberExtMeta$s$0$0$current;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$FiberExtLibrary$FiberExtMeta$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            final JavaMethod javaMethod = new FiberExtLibrary$FiberExtMeta$s$0$0$current(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "current", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(FiberExtLibrary.FiberExtMeta.class, "current", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("current", javaMethod);
            runtime.addBoundMethod("org.jruby.libraries.FiberExtLibrary.FiberExtMeta.current", "current");
        }
    }
}
