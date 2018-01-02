// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyEnumerator$i$initialize19;
import org.jruby.RubyEnumerator$i$0$0$inspect19;
import org.jruby.RubyEnumerator$i$initialize;
import org.jruby.RubyEnumerator$i$0$0$dup;
import org.jruby.RubyEnumerator$i$0$0$each;
import org.jruby.RubyEnumerator$s$with_index19;
import org.jruby.RubyEnumerator$s$0$0$peek;
import org.jruby.RubyEnumerator$s$0$0$with_index;
import org.jruby.CompatVersion;
import org.jruby.RubyEnumerator$s$0$0$next;
import org.jruby.runtime.Block;
import org.jruby.RubyEnumerator$s$0$0$each_with_index;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyEnumerator;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyEnumerator$s$0$0$rewind;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyEnumerator$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyEnumerator$s$0$0$rewind(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rewind", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.class, "rewind", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("rewind", javaMethod);
        javaMethod = new RubyEnumerator$s$0$0$each_with_index(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each_with_index", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.class, "each_with_index", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("each_with_index", javaMethod);
        javaMethod = new RubyEnumerator$s$0$0$next(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "next", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.class, "next", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("next", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyEnumerator.rewind", "rewind");
        runtime.addBoundMethod("org.jruby.RubyEnumerator.each_with_index", "each_with_index");
        runtime.addBoundMethod("org.jruby.RubyEnumerator.next", "next");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyEnumerator$s$0$0$with_index(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "with_index", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEnumerator.class, "with_index", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("with_index", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyEnumerator.with_index", "with_index");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyEnumerator$s$0$0$peek(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "peek", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEnumerator.class, "peek", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("peek", javaMethod);
            javaMethod = new RubyEnumerator$s$with_index19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "with_index19", true, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("with_index", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyEnumerator.peek", "peek");
            runtime.addBoundMethod("org.jruby.RubyEnumerator.with_index19", "with_index");
        }
        javaMethod = new RubyEnumerator$i$0$0$each(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.class, "each", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each", javaMethod);
        javaMethod = new RubyEnumerator$i$0$0$dup(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "dup", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.class, "dup", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("dup", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyEnumerator.each", "each");
        runtime.addBoundMethod("org.jruby.RubyEnumerator.dup", "dup");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyEnumerator$i$initialize(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyEnumerator.initialize", "initialize");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyEnumerator$i$0$0$inspect19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "inspect19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEnumerator.class, "inspect19", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("inspect", javaMethod);
            javaMethod = new RubyEnumerator$i$initialize19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyEnumerator.inspect19", "inspect");
            runtime.addBoundMethod("org.jruby.RubyEnumerator.initialize19", "initialize");
        }
    }
}
