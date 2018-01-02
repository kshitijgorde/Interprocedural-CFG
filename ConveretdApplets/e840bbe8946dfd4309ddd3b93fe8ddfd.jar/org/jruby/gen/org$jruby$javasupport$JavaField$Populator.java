// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.JavaField$i$0$0$name;
import org.jruby.javasupport.JavaField$i$0$0$value_type;
import org.jruby.javasupport.JavaField$i$1$0$set_static_value;
import org.jruby.javasupport.JavaField$i$2$0$set_value;
import org.jruby.javasupport.JavaField$i$0$0$static_p;
import org.jruby.RubyString;
import org.jruby.javasupport.JavaField$i$0$0$to_generic_string;
import org.jruby.javasupport.JavaObject;
import org.jruby.javasupport.JavaField$i$0$0$static_value;
import org.jruby.javasupport.JavaField$i$0$0$public_p;
import org.jruby.javasupport.JavaField$i$0$0$enum_constant_p;
import org.jruby.javasupport.JavaField$i$1$0$op_equal;
import org.jruby.RubyBoolean;
import org.jruby.javasupport.JavaField$i$0$0$final_p;
import org.jruby.javasupport.JavaField$i$0$0$field_type;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaField;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaField$i$1$0$value;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaField$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaField$i$1$0$value(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "value", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "value", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("value", javaMethod);
        javaMethod = new JavaField$i$0$0$field_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "field_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "field_type", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("type", javaMethod);
        javaMethod = new JavaField$i$0$0$final_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "final_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "final_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("final?", javaMethod);
        javaMethod = new JavaField$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "op_equal", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        cls.addMethodAtBootTimeOnly("===", javaMethod);
        javaMethod = new JavaField$i$0$0$enum_constant_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "enum_constant_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "enum_constant_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("enum_constant?", javaMethod);
        javaMethod = new JavaField$i$0$0$public_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "public_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "public_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("public?", javaMethod);
        javaMethod = new JavaField$i$0$0$static_value(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "static_value", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "static_value", JavaObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("static_value", javaMethod);
        javaMethod = new JavaField$i$0$0$to_generic_string(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_generic_string", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "to_generic_string", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_generic_string", javaMethod);
        javaMethod = new JavaField$i$0$0$static_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "static_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "static_p", RubyBoolean.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("static?", javaMethod);
        javaMethod = new JavaField$i$2$0$set_value(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "set_value", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "set_value", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("set_value", javaMethod);
        javaMethod = new JavaField$i$1$0$set_static_value(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_static_value", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "set_static_value", JavaObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("set_static_value", javaMethod);
        javaMethod = new JavaField$i$0$0$value_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "value_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "value_type", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("value_type", javaMethod);
        javaMethod = new JavaField$i$0$0$name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaField.class, "name", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("name", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.value", "value");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.field_type", "type");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.final_p", "final?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.op_equal", "==");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.enum_constant_p", "enum_constant?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.public_p", "public?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.static_value", "static_value");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.to_generic_string", "to_generic_string");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.static_p", "static?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.set_value", "set_value");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.set_static_value", "set_static_value");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.value_type", "value_type");
        runtime.addBoundMethod("org.jruby.javasupport.JavaField.name", "name");
    }
}
