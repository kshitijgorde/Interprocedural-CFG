// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.Ruby;
import org.jruby.RubyString;
import org.jruby.javasupport.JavaMethod$i$0$0$name;
import org.jruby.javasupport.JavaMethod$i$0$0$return_type;
import org.jruby.javasupport.JavaMethod$i$0$0$invoke;
import org.jruby.javasupport.JavaMethod$i$0$0$final_p;
import org.jruby.javasupport.JavaMethod$i$0$0$type_parameters;
import org.jruby.javasupport.JavaMethod$i$0$0$static_p;
import org.jruby.RubyBoolean;
import org.jruby.javasupport.JavaMethod$i$0$0$public_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaMethod$i$0$0$invoke_static;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaMethod$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        org.jruby.internal.runtime.methods.JavaMethod javaMethod = new JavaMethod$i$0$0$invoke_static(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "invoke_static", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "invoke_static", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("invoke_static", javaMethod);
        javaMethod = new JavaMethod$i$0$0$public_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "public_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "public_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("public?", javaMethod);
        javaMethod = new JavaMethod$i$0$0$static_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "static_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "static_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("static?", javaMethod);
        javaMethod = new JavaMethod$i$0$0$type_parameters(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "type_parameters", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "type_parameters", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("type_parameters", javaMethod);
        javaMethod = new JavaMethod$i$0$0$final_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "final_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "final_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("final?", javaMethod);
        javaMethod = new JavaMethod$i$0$0$invoke(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "invoke", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "invoke", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("invoke", javaMethod);
        javaMethod = new JavaMethod$i$0$0$return_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "return_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "return_type", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("return_type", javaMethod);
        javaMethod = new JavaMethod$i$0$0$name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaMethod.class, "name", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("name", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.invoke_static", "invoke_static");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.public_p", "public?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.static_p", "static?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.type_parameters", "type_parameters");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.final_p", "final?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.invoke", "invoke");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.return_type", "return_type");
        runtime.addBoundMethod("org.jruby.javasupport.JavaMethod.name", "name");
    }
}
