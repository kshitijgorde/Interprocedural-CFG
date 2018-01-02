// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.compiler.ASTInspector;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyRange$i$0$0$max;
import org.jruby.RubyRange$i$step19;
import org.jruby.RubyRange$i$first;
import org.jruby.RubyRange$i$1$0$eqq_p19;
import org.jruby.RubyRange$i$1$0$cover_p;
import org.jruby.RubyRange$i$0$0$min;
import org.jruby.RubyRange$i$1$0$include_p19;
import org.jruby.RubyRange$i$0$0$each19;
import org.jruby.RubyRange$i$last;
import org.jruby.RubyRange$i$step;
import org.jruby.RubyRange$i$1$0$include_p;
import org.jruby.RubyRange$i$0$0$each;
import org.jruby.CompatVersion;
import org.jruby.RubyFixnum;
import org.jruby.RubyRange$i$0$0$hash;
import org.jruby.RubyRange$i$0$0$to_s;
import org.jruby.RubyRange$i$1$0$op_equal;
import org.jruby.RubyRange$i$0$0$first;
import org.jruby.RubyRange$i$0$0$to_a;
import org.jruby.RubyBoolean;
import org.jruby.RubyRange$i$0$0$exclude_end_p;
import org.jruby.RubyRange$i$0$0$last;
import org.jruby.RubyRange$i$1$0$eql_p;
import org.jruby.runtime.Block;
import org.jruby.RubyRange$i$0$1$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyRange;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyRange$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyRange$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyRange$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyRange$i$0$1$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyRange$i$1$0$eql_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "eql_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "eql_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new RubyRange$i$0$0$last(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "last", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "last", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("last", javaMethod);
        cls.addMethodAtBootTimeOnly("end", javaMethod);
        javaMethod = new RubyRange$i$0$0$exclude_end_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "exclude_end_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "exclude_end_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("exclude_end?", javaMethod);
        javaMethod = new RubyRange$i$0$0$to_a(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_a", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "to_a", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("to_a", javaMethod);
        javaMethod = new RubyRange$i$0$0$first(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "first", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "first", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("first", javaMethod);
        cls.addMethodAtBootTimeOnly("begin", javaMethod);
        javaMethod = new RubyRange$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new RubyRange$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyRange$i$0$0$hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRange.class, "hash", RubyFixnum.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("hash", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyRange.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyRange.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyRange.eql_p", "eql?");
        runtime.addBoundMethod("org.jruby.RubyRange.last", "last");
        runtime.addBoundMethod("org.jruby.RubyRange.exclude_end_p", "exclude_end?");
        runtime.addBoundMethod("org.jruby.RubyRange.to_a", "to_a");
        runtime.addBoundMethod("org.jruby.RubyRange.first", "first");
        runtime.addBoundMethod("org.jruby.RubyRange.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyRange.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.RubyRange.hash", "hash");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyRange$i$0$0$each(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "each", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "each", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("each", javaMethod);
            javaMethod = new RubyRange$i$1$0$include_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "include_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "include_p", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("include?", javaMethod);
            cls.addMethodAtBootTimeOnly("member?", javaMethod);
            cls.addMethodAtBootTimeOnly("===", javaMethod);
            javaMethod = new RubyRange$i$step(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "step", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("step", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRange.each", "each");
            runtime.addBoundMethod("org.jruby.RubyRange.include_p", "include?");
            runtime.addBoundMethod("org.jruby.RubyRange.step", "step");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyRange$i$last(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "last", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("last", javaMethod);
            javaMethod = new RubyRange$i$0$0$each19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "each19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "each19", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("each", javaMethod);
            javaMethod = new RubyRange$i$1$0$include_p19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "include_p19", false, CallConfiguration.FrameFullScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "include_p19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("include?", javaMethod);
            cls.addMethodAtBootTimeOnly("member?", javaMethod);
            javaMethod = new RubyRange$i$0$0$min(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "min", false, CallConfiguration.FrameFullScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "min", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("min", javaMethod);
            javaMethod = new RubyRange$i$1$0$cover_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "cover_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "cover_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("cover?", javaMethod);
            javaMethod = new RubyRange$i$1$0$eqq_p19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "eqq_p19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "eqq_p19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("===", javaMethod);
            javaMethod = new RubyRange$i$first(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "first", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("first", javaMethod);
            javaMethod = new RubyRange$i$step19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "step19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("step", javaMethod);
            javaMethod = new RubyRange$i$0$0$max(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "max", false, CallConfiguration.FrameFullScopeNone, false);
            javaMethod.setNativeCall(RubyRange.class, "max", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
            cls.addMethodAtBootTimeOnly("max", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRange.last", "last");
            runtime.addBoundMethod("org.jruby.RubyRange.each19", "each");
            runtime.addBoundMethod("org.jruby.RubyRange.include_p19", "include?");
            runtime.addBoundMethod("org.jruby.RubyRange.min", "min");
            runtime.addBoundMethod("org.jruby.RubyRange.cover_p", "cover?");
            runtime.addBoundMethod("org.jruby.RubyRange.eqq_p19", "===");
            runtime.addBoundMethod("org.jruby.RubyRange.first", "first");
            runtime.addBoundMethod("org.jruby.RubyRange.step19", "step");
            runtime.addBoundMethod("org.jruby.RubyRange.max", "max");
        }
    }
    
    static {
        ASTInspector.addFrameAwareMethods("member?", "include?");
    }
}
