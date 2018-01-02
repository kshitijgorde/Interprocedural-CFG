// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyFixnum;
import org.jruby.javasupport.JavaCallable$i$0$0$arity;
import org.jruby.RubyArray;
import org.jruby.javasupport.JavaCallable$i$0$0$argument_types;
import org.jruby.javasupport.JavaCallable$i$0$0$varargs_p;
import org.jruby.javasupport.JavaCallable$i$0$0$generic_exception_types;
import org.jruby.javasupport.JavaCallable$i$0$0$parameter_annotations;
import org.jruby.RubyString;
import org.jruby.javasupport.JavaCallable$i$0$0$to_generic_string;
import org.jruby.javasupport.JavaCallable$i$0$0$parameter_types;
import org.jruby.RubyBoolean;
import org.jruby.javasupport.JavaCallable$i$0$0$public_p;
import org.jruby.javasupport.JavaCallable$i$0$0$generic_parameter_types;
import org.jruby.javasupport.JavaCallable$i$0$0$exception_types;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaCallable;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaCallable$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaCallable$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaCallable$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "inspect", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new JavaCallable$i$0$0$exception_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "exception_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "exception_types", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("exception_types", javaMethod);
        javaMethod = new JavaCallable$i$0$0$generic_parameter_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "generic_parameter_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "generic_parameter_types", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("generic_parameter_types", javaMethod);
        javaMethod = new JavaCallable$i$0$0$public_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "public_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "public_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("public?", javaMethod);
        javaMethod = new JavaCallable$i$0$0$parameter_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "parameter_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "parameter_types", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("parameter_types", javaMethod);
        javaMethod = new JavaCallable$i$0$0$to_generic_string(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_generic_string", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "to_generic_string", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_generic_string", javaMethod);
        javaMethod = new JavaCallable$i$0$0$parameter_annotations(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "parameter_annotations", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "parameter_annotations", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("parameter_annotations", javaMethod);
        javaMethod = new JavaCallable$i$0$0$generic_exception_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "generic_exception_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "generic_exception_types", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("generic_exception_types", javaMethod);
        javaMethod = new JavaCallable$i$0$0$varargs_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "varargs_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "varargs_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("varargs?", javaMethod);
        javaMethod = new JavaCallable$i$0$0$argument_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "argument_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "argument_types", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("argument_types", javaMethod);
        javaMethod = new JavaCallable$i$0$0$arity(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "arity", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaCallable.class, "arity", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("arity", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.exception_types", "exception_types");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.generic_parameter_types", "generic_parameter_types");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.public_p", "public?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.parameter_types", "parameter_types");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.to_generic_string", "to_generic_string");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.parameter_annotations", "parameter_annotations");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.generic_exception_types", "generic_exception_types");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.varargs_p", "varargs?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.argument_types", "argument_types");
        runtime.addBoundMethod("org.jruby.javasupport.JavaCallable.arity", "arity");
    }
}
