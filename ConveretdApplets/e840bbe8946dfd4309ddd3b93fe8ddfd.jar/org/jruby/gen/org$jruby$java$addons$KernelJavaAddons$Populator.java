// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.compiler.ASTInspector;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.java.addons.KernelJavaAddons$s$0$3$rbRaise;
import org.jruby.java.addons.KernelJavaAddons$s$0$0$java_name;
import org.jruby.java.addons.KernelJavaAddons$s$to_java;
import org.jruby.java.addons.KernelJavaAddons$s$0$0$java_require;
import org.jruby.java.addons.KernelJavaAddons$s$0$0$java_signature;
import org.jruby.java.addons.KernelJavaAddons$s$0$0$java_implements;
import org.jruby.java.addons.KernelJavaAddons$s$0$0$java_package;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.addons.KernelJavaAddons;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.addons.KernelJavaAddons$s$0$0$java_annotation;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$addons$KernelJavaAddons$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new KernelJavaAddons$s$0$0$java_annotation(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_annotation", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "java_annotation", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("java_annotation", javaMethod);
        javaMethod = new KernelJavaAddons$s$0$0$java_package(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_package", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "java_package", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("java_package", javaMethod);
        javaMethod = new KernelJavaAddons$s$0$0$java_implements(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_implements", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "java_implements", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("java_implements", javaMethod);
        javaMethod = new KernelJavaAddons$s$0$0$java_signature(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_signature", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "java_signature", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("java_signature", javaMethod);
        javaMethod = new KernelJavaAddons$s$0$0$java_require(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_require", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "java_require", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("java_require", javaMethod);
        javaMethod = new KernelJavaAddons$s$to_java(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "to_java", true, CallConfiguration.FrameBacktraceScopeNone, false);
        cls.addMethodAtBootTimeOnly("to_java", javaMethod);
        javaMethod = new KernelJavaAddons$s$0$0$java_name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_name", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "java_name", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("java_name", javaMethod);
        javaMethod = new KernelJavaAddons$s$0$3$rbRaise(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "rbRaise", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(KernelJavaAddons.class, "rbRaise", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("raise", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("raise", moduleMethod);
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.java_annotation", "java_annotation");
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.java_package", "java_package");
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.java_implements", "java_implements");
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.java_signature", "java_signature");
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.java_require", "java_require");
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.to_java", "to_java");
        runtime.addBoundMethod("org.jruby.java.addons.KernelJavaAddons.java_name", "java_name");
    }
    
    static {
        ASTInspector.addFrameAwareMethods("raise");
    }
}
