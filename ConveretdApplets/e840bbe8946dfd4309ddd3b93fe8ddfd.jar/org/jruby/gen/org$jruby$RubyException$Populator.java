// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyException$i$1$0$op_equal;
import org.jruby.RubyException$i$0$0$to_str;
import org.jruby.CompatVersion;
import org.jruby.RubyException$i$0$0$to_s;
import org.jruby.RubyException$i$0$0$backtrace;
import org.jruby.RubyException$i$0$0$message;
import org.jruby.RubyException$i$0$1$exception;
import org.jruby.RubyException$i$0$2$initialize;
import org.jruby.RubyException$i$1$0$set_backtrace;
import org.jruby.RubyException$i$0$0$inspect;
import org.jruby.RubyException$s$1$0$op_eqq;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyException;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyException$s$0$1$exception;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyException$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyException$s$0$1$exception(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "exception", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "exception", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("exception", javaMethod);
        javaMethod = new RubyException$s$1$0$op_eqq(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_eqq", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "op_eqq", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("===", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyException.exception", "exception");
        runtime.addBoundMethod("org.jruby.RubyException.op_eqq", "===");
        javaMethod = new RubyException$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyException$i$1$0$set_backtrace(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_backtrace", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "set_backtrace", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("set_backtrace", javaMethod);
        javaMethod = new RubyException$i$0$2$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyException$i$0$1$exception(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "exception", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "exception", RubyException.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("exception", javaMethod);
        javaMethod = new RubyException$i$0$0$message(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "message", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "message", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("message", javaMethod);
        javaMethod = new RubyException$i$0$0$backtrace(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "backtrace", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "backtrace", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("backtrace", javaMethod);
        javaMethod = new RubyException$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyException.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyException.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyException.set_backtrace", "set_backtrace");
        runtime.addBoundMethod("org.jruby.RubyException.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyException.exception", "exception");
        runtime.addBoundMethod("org.jruby.RubyException.message", "message");
        runtime.addBoundMethod("org.jruby.RubyException.backtrace", "backtrace");
        runtime.addBoundMethod("org.jruby.RubyException.to_s", "to_s");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyException$i$0$0$to_str(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_str", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyException.class, "to_str", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("to_str", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyException.to_str", "to_str");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyException$i$1$0$op_equal(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyException.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("==", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyException.op_equal", "==");
        }
    }
}
