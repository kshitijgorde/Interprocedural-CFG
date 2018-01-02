// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$2$0$add_parameter_annotations;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$2$0$add_method_signature;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$2$0$add_method_annotation;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$0$1$become_java_bang;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$0$1$subclasses;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$1$0$add_class_annotation;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$JRubyClassExtensions$s$0$0$java_class;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$JRubyClassExtensions$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyJRuby$JRubyClassExtensions$s$0$0$java_class(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "java_class", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("java_class", javaMethod);
        javaMethod = new RubyJRuby$JRubyClassExtensions$s$1$0$add_class_annotation(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "add_class_annotation", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "add_class_annotation", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("add_class_annotation", javaMethod);
        javaMethod = new RubyJRuby$JRubyClassExtensions$s$0$1$subclasses(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "subclasses", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "subclasses", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("subclasses", javaMethod);
        javaMethod = new RubyJRuby$JRubyClassExtensions$s$0$1$become_java_bang(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "become_java_bang", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "become_java_bang", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("become_java!", javaMethod);
        javaMethod = new RubyJRuby$JRubyClassExtensions$s$2$0$add_method_annotation(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "add_method_annotation", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "add_method_annotation", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("add_method_annotation", javaMethod);
        javaMethod = new RubyJRuby$JRubyClassExtensions$s$2$0$add_method_signature(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "add_method_signature", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "add_method_signature", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("add_method_signature", javaMethod);
        javaMethod = new RubyJRuby$JRubyClassExtensions$s$2$0$add_parameter_annotations(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "add_parameter_annotations", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyClassExtensions.class, "add_parameter_annotations", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("add_parameter_annotations", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.java_class", "java_class");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.add_class_annotation", "add_class_annotation");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.subclasses", "subclasses");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.become_java_bang", "become_java!");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.add_method_annotation", "add_method_annotation");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.add_method_signature", "add_method_signature");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyClassExtensions.add_parameter_annotations", "add_parameter_annotations");
    }
}
