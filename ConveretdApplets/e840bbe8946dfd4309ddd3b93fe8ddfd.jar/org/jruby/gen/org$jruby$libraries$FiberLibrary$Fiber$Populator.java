// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.libraries.FiberLibrary$Fiber$i$0$0$transfer;
import org.jruby.libraries.FiberLibrary$Fiber$i$0$0$alive_p;
import org.jruby.libraries.FiberLibrary$Fiber$i$0$0$resume;
import org.jruby.CompatVersion;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.FiberLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.FiberLibrary$Fiber$i$0$0$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$FiberLibrary$Fiber$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new FiberLibrary$Fiber$i$0$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(FiberLibrary.Fiber.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.FiberLibrary.Fiber.initialize", "initialize");
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new FiberLibrary$Fiber$i$0$0$resume(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "resume", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(FiberLibrary.Fiber.class, "resume", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
            cls.addMethodAtBootTimeOnly("resume", javaMethod);
            javaMethod = new FiberLibrary$Fiber$i$0$0$alive_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "alive_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(FiberLibrary.Fiber.class, "alive_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("alive?", javaMethod);
            javaMethod = new FiberLibrary$Fiber$i$0$0$transfer(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "transfer", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(FiberLibrary.Fiber.class, "transfer", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
            cls.addMethodAtBootTimeOnly("transfer", javaMethod);
            runtime.addBoundMethod("org.jruby.libraries.FiberLibrary.Fiber.resume", "resume");
            runtime.addBoundMethod("org.jruby.libraries.FiberLibrary.Fiber.alive_p", "alive?");
            runtime.addBoundMethod("org.jruby.libraries.FiberLibrary.Fiber.transfer", "transfer");
        }
    }
}
