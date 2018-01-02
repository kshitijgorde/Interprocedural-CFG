// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyRandom$i$1$0$bytes;
import org.jruby.RubyRandom$i$1$0$op_equal_19;
import org.jruby.RubyRandom$i$0$0$seed;
import org.jruby.RubyRandom$i$1$0$marshal_load;
import org.jruby.RubyRandom$i$randObj;
import org.jruby.RubyRandom$i$0$0$marshal_dump;
import org.jruby.RubyRandom$i$initialize;
import org.jruby.RubyRandom$s$srand;
import org.jruby.RubyRandom$s$rand;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyRandom;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyRandom$s$0$0$newSeed;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyRandom$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            JavaMethod javaMethod = new RubyRandom$s$0$0$newSeed(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "newSeed", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRandom.class, "newSeed", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("new_seed", javaMethod);
            javaMethod = new RubyRandom$s$rand(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "rand", true, CallConfiguration.FrameNoneScopeNone, false);
            singletonClass.addMethodAtBootTimeOnly("rand", javaMethod);
            javaMethod = new RubyRandom$s$srand(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "srand", true, CallConfiguration.FrameNoneScopeNone, false);
            singletonClass.addMethodAtBootTimeOnly("srand", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRandom.newSeed", "new_seed");
            runtime.addBoundMethod("org.jruby.RubyRandom.rand", "rand");
            runtime.addBoundMethod("org.jruby.RubyRandom.srand", "srand");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            JavaMethod javaMethod = new RubyRandom$i$initialize(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            javaMethod = new RubyRandom$i$0$0$marshal_dump(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "marshal_dump", false, CallConfiguration.FrameBacktraceScopeNone, false);
            javaMethod.setNativeCall(RubyRandom.class, "marshal_dump", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("marshal_dump", javaMethod);
            javaMethod = new RubyRandom$i$randObj(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "randObj", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("rand", javaMethod);
            javaMethod = new RubyRandom$i$1$0$marshal_load(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "marshal_load", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRandom.class, "marshal_load", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("marshal_load", javaMethod);
            javaMethod = new RubyRandom$i$0$0$seed(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "seed", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRandom.class, "seed", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("seed", javaMethod);
            javaMethod = new RubyRandom$i$1$0$op_equal_19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_equal_19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRandom.class, "op_equal_19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("==", javaMethod);
            javaMethod = new RubyRandom$i$1$0$bytes(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "bytes", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRandom.class, "bytes", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("bytes", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRandom.initialize", "initialize");
            runtime.addBoundMethod("org.jruby.RubyRandom.marshal_dump", "marshal_dump");
            runtime.addBoundMethod("org.jruby.RubyRandom.randObj", "rand");
            runtime.addBoundMethod("org.jruby.RubyRandom.marshal_load", "marshal_load");
            runtime.addBoundMethod("org.jruby.RubyRandom.seed", "seed");
            runtime.addBoundMethod("org.jruby.RubyRandom.op_equal_19", "==");
            runtime.addBoundMethod("org.jruby.RubyRandom.bytes", "bytes");
        }
    }
}
