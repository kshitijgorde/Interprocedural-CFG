// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyProc$i$0$0$to_s19;
import org.jruby.RubyProc$i$0$0$lambda_p;
import org.jruby.RubyProc$i$0$0$source_location;
import org.jruby.RubyProc$i$0$0$call19;
import org.jruby.RubyProc$i$0$0$parameters;
import org.jruby.RubyProc$i$0$0$to_s;
import org.jruby.RubyProc$i$0$0$call;
import org.jruby.CompatVersion;
import org.jruby.RubyFixnum;
import org.jruby.RubyProc$i$0$0$arity;
import org.jruby.RubyProc$i$1$0$op_equal;
import org.jruby.RubyProc$i$0$0$binding;
import org.jruby.RubyProc$i$0$0$rbClone;
import org.jruby.RubyProc$i$0$0$dup;
import org.jruby.RubyProc$i$0$0$to_proc;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyProc;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyProc$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyProc$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyProc$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "newInstance", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyProc.newInstance", "new");
        javaMethod = new RubyProc$i$0$0$to_proc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_proc", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "to_proc", RubyProc.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_proc", javaMethod);
        javaMethod = new RubyProc$i$0$0$dup(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "dup", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "dup", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("dup", javaMethod);
        javaMethod = new RubyProc$i$0$0$rbClone(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rbClone", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "rbClone", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("clone", javaMethod);
        javaMethod = new RubyProc$i$0$0$binding(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "binding", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "binding", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("binding", javaMethod);
        javaMethod = new RubyProc$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "op_equal", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new RubyProc$i$0$0$arity(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "arity", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProc.class, "arity", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("arity", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyProc.to_proc", "to_proc");
        runtime.addBoundMethod("org.jruby.RubyProc.dup", "dup");
        runtime.addBoundMethod("org.jruby.RubyProc.rbClone", "clone");
        runtime.addBoundMethod("org.jruby.RubyProc.binding", "binding");
        runtime.addBoundMethod("org.jruby.RubyProc.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyProc.arity", "arity");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyProc$i$0$0$call(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "call", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "call", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("call", javaMethod);
            cls.addMethodAtBootTimeOnly("[]", javaMethod);
            javaMethod = new RubyProc$i$0$0$to_s(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "to_s", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("to_s", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyProc.call", "call");
            runtime.addBoundMethod("org.jruby.RubyProc.to_s", "to_s");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyProc$i$0$0$parameters(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "parameters", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "parameters", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("parameters", javaMethod);
            javaMethod = new RubyProc$i$0$0$call19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "call19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "call19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("call", javaMethod);
            cls.addMethodAtBootTimeOnly("[]", javaMethod);
            cls.addMethodAtBootTimeOnly("yield", javaMethod);
            cls.addMethodAtBootTimeOnly("===", javaMethod);
            javaMethod = new RubyProc$i$0$0$source_location(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "source_location", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "source_location", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("source_location", javaMethod);
            javaMethod = new RubyProc$i$0$0$lambda_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "lambda_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "lambda_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("lambda?", javaMethod);
            javaMethod = new RubyProc$i$0$0$to_s19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_s19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyProc.class, "to_s19", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("to_s", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyProc.parameters", "parameters");
            runtime.addBoundMethod("org.jruby.RubyProc.call19", "call");
            runtime.addBoundMethod("org.jruby.RubyProc.source_location", "source_location");
            runtime.addBoundMethod("org.jruby.RubyProc.lambda_p", "lambda?");
            runtime.addBoundMethod("org.jruby.RubyProc.to_s19", "to_s");
        }
    }
}
