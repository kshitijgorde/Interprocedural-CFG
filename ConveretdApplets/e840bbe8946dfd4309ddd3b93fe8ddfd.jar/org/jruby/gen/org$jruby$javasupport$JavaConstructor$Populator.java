// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.JavaConstructor$i$0$0$return_type;
import org.jruby.javasupport.JavaConstructor$i$0$0$type_parameters;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaConstructor;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaConstructor$i$0$0$new_instance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaConstructor$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaConstructor$i$0$0$new_instance(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "new_instance", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaConstructor.class, "new_instance", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("new_instance", javaMethod);
        javaMethod = new JavaConstructor$i$0$0$type_parameters(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "type_parameters", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaConstructor.class, "type_parameters", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("type_parameters", javaMethod);
        javaMethod = new JavaConstructor$i$0$0$return_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "return_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaConstructor.class, "return_type", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("return_type", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaConstructor.new_instance", "new_instance");
        runtime.addBoundMethod("org.jruby.javasupport.JavaConstructor.type_parameters", "type_parameters");
        runtime.addBoundMethod("org.jruby.javasupport.JavaConstructor.return_type", "return_type");
    }
}
