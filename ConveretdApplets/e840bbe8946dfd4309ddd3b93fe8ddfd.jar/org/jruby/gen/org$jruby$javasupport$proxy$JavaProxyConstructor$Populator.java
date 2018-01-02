// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.proxy.JavaProxyClass;
import org.jruby.javasupport.proxy.JavaProxyConstructor$i$0$0$getDeclaringClass;
import org.jruby.RubyFixnum;
import org.jruby.javasupport.proxy.JavaProxyConstructor$i$0$0$arity;
import org.jruby.RubyArray;
import org.jruby.javasupport.proxy.JavaProxyConstructor$i$0$0$argument_types;
import org.jruby.javasupport.proxy.JavaProxyConstructor$i$0$0$new_instance2;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;
import org.jruby.javasupport.proxy.JavaProxyConstructor;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.proxy.JavaProxyConstructor$i$0$1$new_instance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$proxy$JavaProxyConstructor$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaProxyConstructor$i$0$1$new_instance(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "new_instance", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyConstructor.class, "new_instance", RubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("new_instance", javaMethod);
        javaMethod = new JavaProxyConstructor$i$0$0$new_instance2(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "new_instance2", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyConstructor.class, "new_instance2", RubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("new_instance2", javaMethod);
        javaMethod = new JavaProxyConstructor$i$0$0$argument_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "argument_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyConstructor.class, "argument_types", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("argument_types", javaMethod);
        javaMethod = new JavaProxyConstructor$i$0$0$arity(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "arity", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyConstructor.class, "arity", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("arity", javaMethod);
        javaMethod = new JavaProxyConstructor$i$0$0$getDeclaringClass(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getDeclaringClass", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyConstructor.class, "getDeclaringClass", JavaProxyClass.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("declaring_class", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyConstructor.new_instance", "new_instance");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyConstructor.new_instance2", "new_instance2");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyConstructor.argument_types", "argument_types");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyConstructor.arity", "arity");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyConstructor.getDeclaringClass", "declaring_class");
    }
}
