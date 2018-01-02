// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyGC$s$0$0$count;
import org.jruby.CompatVersion;
import org.jruby.RubyGC$s$0$0$enable;
import org.jruby.RubyGC$s$0$0$stress;
import org.jruby.RubyGC$s$0$0$garbage_collect;
import org.jruby.RubyGC$s$0$0$disable;
import org.jruby.RubyGC$s$0$0$start;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyGC;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyGC$s$1$0$stress_set;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyGC$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyGC$s$1$0$stress_set(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "stress_set", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGC.class, "stress_set", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("stress=", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("stress=", moduleMethod);
        javaMethod = new RubyGC$s$0$0$start(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "start", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGC.class, "start", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("start", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("start", moduleMethod);
        javaMethod = new RubyGC$s$0$0$disable(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "disable", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGC.class, "disable", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("disable", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("disable", moduleMethod);
        javaMethod = new RubyGC$s$0$0$garbage_collect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "garbage_collect", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGC.class, "garbage_collect", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("garbage_collect", javaMethod);
        javaMethod = new RubyGC$s$0$0$stress(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "stress", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGC.class, "stress", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("stress", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("stress", moduleMethod);
        javaMethod = new RubyGC$s$0$0$enable(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "enable", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGC.class, "enable", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("enable", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("enable", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyGC.stress_set", "stress=");
        runtime.addBoundMethod("org.jruby.RubyGC.start", "start");
        runtime.addBoundMethod("org.jruby.RubyGC.disable", "disable");
        runtime.addBoundMethod("org.jruby.RubyGC.garbage_collect", "garbage_collect");
        runtime.addBoundMethod("org.jruby.RubyGC.stress", "stress");
        runtime.addBoundMethod("org.jruby.RubyGC.enable", "enable");
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyGC$s$0$0$count(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 0, "count", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyGC.class, "count", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("count", javaMethod);
            moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
            singletonClass.addMethodAtBootTimeOnly("count", moduleMethod);
            runtime.addBoundMethod("org.jruby.RubyGC.count", "count");
        }
    }
}
