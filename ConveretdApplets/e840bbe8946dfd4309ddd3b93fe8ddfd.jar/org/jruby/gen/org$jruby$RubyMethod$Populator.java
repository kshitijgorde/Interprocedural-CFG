// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyMethod$i$0$0$name19;
import org.jruby.RubyMethod$i$0$0$source_location;
import org.jruby.RubyMethod$i$1$0$op_eql19;
import org.jruby.RubyMethod$i$0$0$parameters;
import org.jruby.RubyMethod$i$0$0$name;
import org.jruby.CompatVersion;
import org.jruby.RubyFixnum;
import org.jruby.RubyMethod$i$0$0$arity;
import org.jruby.RubyBoolean;
import org.jruby.RubyMethod$i$1$0$op_equal;
import org.jruby.RubyMethod$i$0$0$rbClone;
import org.jruby.RubyMethod$i$0$0$owner;
import org.jruby.RubyMethod$i$0$0$receiver;
import org.jruby.RubyMethod$i$call;
import org.jruby.RubyUnboundMethod;
import org.jruby.RubyMethod$i$0$0$unbind;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyMethod$i$0$0$to_proc;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyMethod$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyMethod$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyMethod$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "inspect", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyMethod$i$0$0$to_proc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_proc", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "to_proc", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("to_proc", javaMethod);
        javaMethod = new RubyMethod$i$0$0$unbind(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "unbind", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "unbind", RubyUnboundMethod.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("unbind", javaMethod);
        javaMethod = new RubyMethod$i$call(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "call", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("call", javaMethod);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new RubyMethod$i$0$0$receiver(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "receiver", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "receiver", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("receiver", javaMethod);
        javaMethod = new RubyMethod$i$0$0$owner(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "owner", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "owner", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("owner", javaMethod);
        javaMethod = new RubyMethod$i$0$0$rbClone(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rbClone", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "rbClone", RubyMethod.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("clone", javaMethod);
        javaMethod = new RubyMethod$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "op_equal", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new RubyMethod$i$0$0$arity(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "arity", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMethod.class, "arity", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("arity", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyMethod.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyMethod.to_proc", "to_proc");
        runtime.addBoundMethod("org.jruby.RubyMethod.unbind", "unbind");
        runtime.addBoundMethod("org.jruby.RubyMethod.call", "call");
        runtime.addBoundMethod("org.jruby.RubyMethod.receiver", "receiver");
        runtime.addBoundMethod("org.jruby.RubyMethod.owner", "owner");
        runtime.addBoundMethod("org.jruby.RubyMethod.rbClone", "clone");
        runtime.addBoundMethod("org.jruby.RubyMethod.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyMethod.arity", "arity");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyMethod$i$0$0$name(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyMethod.class, "name", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("name", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyMethod.name", "name");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyMethod$i$0$0$parameters(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "parameters", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyMethod.class, "parameters", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("parameters", javaMethod);
            javaMethod = new RubyMethod$i$1$0$op_eql19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_eql19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyMethod.class, "op_eql19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("eql?", javaMethod);
            javaMethod = new RubyMethod$i$0$0$source_location(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "source_location", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyMethod.class, "source_location", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("source_location", javaMethod);
            javaMethod = new RubyMethod$i$0$0$name19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "name19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyMethod.class, "name19", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("name", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyMethod.parameters", "parameters");
            runtime.addBoundMethod("org.jruby.RubyMethod.op_eql19", "eql?");
            runtime.addBoundMethod("org.jruby.RubyMethod.source_location", "source_location");
            runtime.addBoundMethod("org.jruby.RubyMethod.name19", "name");
        }
    }
}
