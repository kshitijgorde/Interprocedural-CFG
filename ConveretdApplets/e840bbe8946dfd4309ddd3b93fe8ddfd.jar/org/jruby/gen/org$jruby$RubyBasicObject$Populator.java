// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyBasicObject$i$send19;
import org.jruby.RubyBasicObject$i$1$0$op_equal_19;
import org.jruby.RubyBasicObject$i$0$3$instance_exec19;
import org.jruby.RubyBasicObject$i$0$0$op_not;
import org.jruby.RubyBasicObject$i$1$0$op_not_equal;
import org.jruby.RubyBasicObject$i$1$0$equal_p19;
import org.jruby.RubyBasicObject$i$initialize19;
import org.jruby.RubyBasicObject$i$instance_eval19;
import org.jruby.RubyBasicObject$s$1$0$singleton_method_added19;
import org.jruby.RubyBasicObject$s$0$0$method_missing19;
import org.jruby.RubyBasicObject$s$1$0$singleton_method_undefined19;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBasicObject;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBasicObject$s$1$0$singleton_method_removed19;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyBasicObject$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            JavaMethod javaMethod = new RubyBasicObject$s$1$0$singleton_method_removed19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 1, "singleton_method_removed19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "singleton_method_removed19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("singleton_method_removed", javaMethod);
            DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
            singletonClass.addMethodAtBootTimeOnly("singleton_method_removed", moduleMethod);
            javaMethod = new RubyBasicObject$s$1$0$singleton_method_undefined19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 1, "singleton_method_undefined19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "singleton_method_undefined19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("singleton_method_undefined", javaMethod);
            moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
            singletonClass.addMethodAtBootTimeOnly("singleton_method_undefined", moduleMethod);
            javaMethod = new RubyBasicObject$s$0$0$method_missing19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "method_missing19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "method_missing19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("method_missing", javaMethod);
            moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
            singletonClass.addMethodAtBootTimeOnly("method_missing", moduleMethod);
            javaMethod = new RubyBasicObject$s$1$0$singleton_method_added19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, 1, "singleton_method_added19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "singleton_method_added19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("singleton_method_added", javaMethod);
            moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
            singletonClass.addMethodAtBootTimeOnly("singleton_method_added", moduleMethod);
            runtime.addBoundMethod("org.jruby.RubyBasicObject.singleton_method_removed19", "singleton_method_removed");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.singleton_method_undefined19", "singleton_method_undefined");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.method_missing19", "method_missing");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.singleton_method_added19", "singleton_method_added");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            JavaMethod javaMethod = new RubyBasicObject$i$instance_eval19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "instance_eval19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("instance_eval", javaMethod);
            javaMethod = new RubyBasicObject$i$initialize19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            javaMethod = new RubyBasicObject$i$1$0$equal_p19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "equal_p19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "equal_p19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("equal?", javaMethod);
            javaMethod = new RubyBasicObject$i$1$0$op_not_equal(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_not_equal", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "op_not_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("!=", javaMethod);
            javaMethod = new RubyBasicObject$i$0$0$op_not(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "op_not", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "op_not", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("!", javaMethod);
            javaMethod = new RubyBasicObject$i$0$3$instance_exec19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "instance_exec19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "instance_exec19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("instance_exec", javaMethod);
            javaMethod = new RubyBasicObject$i$1$0$op_equal_19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_equal_19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicObject.class, "op_equal_19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("==", javaMethod);
            javaMethod = new RubyBasicObject$i$send19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "send19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("__send__", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyBasicObject.instance_eval19", "instance_eval");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.initialize19", "initialize");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.equal_p19", "equal?");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.op_not_equal", "!=");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.op_not", "!");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.instance_exec19", "instance_exec");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.op_equal_19", "==");
            runtime.addBoundMethod("org.jruby.RubyBasicObject.send19", "__send__");
        }
    }
}
