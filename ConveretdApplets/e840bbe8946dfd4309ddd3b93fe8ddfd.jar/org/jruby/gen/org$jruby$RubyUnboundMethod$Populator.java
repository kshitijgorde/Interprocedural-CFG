// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyUnboundMethod$i$0$0$name19;
import org.jruby.RubyUnboundMethod$i$0$0$name;
import org.jruby.CompatVersion;
import org.jruby.RubyUnboundMethod$i$0$0$rbClone;
import org.jruby.RubyUnboundMethod$i$0$0$owner;
import org.jruby.RubyMethod;
import org.jruby.RubyUnboundMethod$i$1$0$bind;
import org.jruby.RubyUnboundMethod$i$0$0$call;
import org.jruby.RubyUnboundMethod$i$0$0$unbind;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyUnboundMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyUnboundMethod$i$0$0$to_proc;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyUnboundMethod$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyUnboundMethod$i$0$0$to_proc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_proc", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUnboundMethod.class, "to_proc", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("to_proc", javaMethod);
        javaMethod = new RubyUnboundMethod$i$0$0$unbind(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "unbind", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUnboundMethod.class, "unbind", RubyUnboundMethod.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("unbind", javaMethod);
        javaMethod = new RubyUnboundMethod$i$0$0$call(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "call", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUnboundMethod.class, "call", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("call", javaMethod);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new RubyUnboundMethod$i$1$0$bind(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "bind", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUnboundMethod.class, "bind", RubyMethod.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("bind", javaMethod);
        javaMethod = new RubyUnboundMethod$i$0$0$owner(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "owner", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUnboundMethod.class, "owner", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("owner", javaMethod);
        javaMethod = new RubyUnboundMethod$i$0$0$rbClone(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rbClone", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUnboundMethod.class, "rbClone", RubyMethod.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("clone", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyUnboundMethod.to_proc", "to_proc");
        runtime.addBoundMethod("org.jruby.RubyUnboundMethod.unbind", "unbind");
        runtime.addBoundMethod("org.jruby.RubyUnboundMethod.call", "call");
        runtime.addBoundMethod("org.jruby.RubyUnboundMethod.bind", "bind");
        runtime.addBoundMethod("org.jruby.RubyUnboundMethod.owner", "owner");
        runtime.addBoundMethod("org.jruby.RubyUnboundMethod.rbClone", "clone");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyUnboundMethod$i$0$0$name(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyUnboundMethod.class, "name", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("name", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyUnboundMethod.name", "name");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyUnboundMethod$i$0$0$name19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "name19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyUnboundMethod.class, "name19", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("name", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyUnboundMethod.name19", "name");
        }
    }
}
