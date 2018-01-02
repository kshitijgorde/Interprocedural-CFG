// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$with_object;
import org.jruby.RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$each_with_object;
import org.jruby.CompatVersion;
import org.jruby.RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$each_slice19;
import org.jruby.RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$enum_cons19;
import org.jruby.RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$enum_slice19;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyEnumerator;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$each_cons19;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyEnumerator$RubyEnumeratorEnumerable$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$each_cons19(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "each_cons19", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.RubyEnumeratorEnumerable.class, "each_cons19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("each_cons", javaMethod);
        javaMethod = new RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$enum_slice19(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "enum_slice19", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.RubyEnumeratorEnumerable.class, "enum_slice19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("enum_slice", javaMethod);
        javaMethod = new RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$enum_cons19(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "enum_cons19", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.RubyEnumeratorEnumerable.class, "enum_cons19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("enum_cons", javaMethod);
        javaMethod = new RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$each_slice19(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "each_slice19", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEnumerator.RubyEnumeratorEnumerable.class, "each_slice19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("each_slice", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorEnumerable.each_cons19", "each_cons");
        runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorEnumerable.enum_slice19", "enum_slice");
        runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorEnumerable.enum_cons19", "enum_cons");
        runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorEnumerable.each_slice19", "each_slice");
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$each_with_object(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "each_with_object", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEnumerator.RubyEnumeratorEnumerable.class, "each_with_object", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("each_with_object", javaMethod);
            javaMethod = new RubyEnumerator$RubyEnumeratorEnumerable$s$1$0$with_object(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "with_object", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEnumerator.RubyEnumeratorEnumerable.class, "with_object", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
            cls.addMethodAtBootTimeOnly("with_object", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorEnumerable.each_with_object", "each_with_object");
            runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorEnumerable.with_object", "with_object");
        }
    }
}
