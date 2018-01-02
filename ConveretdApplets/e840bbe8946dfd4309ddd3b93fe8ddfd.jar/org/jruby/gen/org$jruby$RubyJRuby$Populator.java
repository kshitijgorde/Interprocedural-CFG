// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyJRuby$s$1$0$reference;
import org.jruby.RubyJRuby$s$0$3$parse;
import org.jruby.RubyJRuby$s$0$0$with_current_runtime_as_global;
import org.jruby.RubyJRuby$s$0$3$compile;
import org.jruby.runtime.Block;
import org.jruby.RubyJRuby$s$0$0$runtime;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$s$1$0$dereference;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyJRuby$s$1$0$dereference(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "dereference", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.class, "dereference", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("dereference", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("dereference", moduleMethod);
        javaMethod = new RubyJRuby$s$0$0$runtime(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "runtime", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.class, "runtime", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("runtime", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("runtime", moduleMethod);
        javaMethod = new RubyJRuby$s$0$3$compile(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "compile", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.class, "compile", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("compile", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("compile", moduleMethod);
        javaMethod = new RubyJRuby$s$0$0$with_current_runtime_as_global(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "with_current_runtime_as_global", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.class, "with_current_runtime_as_global", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("with_current_runtime_as_global", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("with_current_runtime_as_global", moduleMethod);
        javaMethod = new RubyJRuby$s$0$3$parse(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "parse", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.class, "parse", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("parse", javaMethod);
        cls.addMethodAtBootTimeOnly("ast_for", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("parse", moduleMethod);
        singletonClass.addMethodAtBootTimeOnly("ast_for", moduleMethod);
        javaMethod = new RubyJRuby$s$1$0$reference(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "reference", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.class, "reference", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("reference", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("reference", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.dereference", "dereference");
        runtime.addBoundMethod("org.jruby.RubyJRuby.runtime", "runtime");
        runtime.addBoundMethod("org.jruby.RubyJRuby.compile", "compile");
        runtime.addBoundMethod("org.jruby.RubyJRuby.with_current_runtime_as_global", "with_current_runtime_as_global");
        runtime.addBoundMethod("org.jruby.RubyJRuby.parse", "parse");
        runtime.addBoundMethod("org.jruby.RubyJRuby.reference", "reference");
    }
}
