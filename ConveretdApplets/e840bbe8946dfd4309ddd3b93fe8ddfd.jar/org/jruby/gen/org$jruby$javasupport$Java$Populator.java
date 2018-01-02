// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.javasupport.Java$s$1$0$ruby_to_java;
import org.jruby.javasupport.Java$s$1$0$java_to_primitive;
import org.jruby.javasupport.Java$s$1$0$java_to_ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.Java;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.Java$s$2$0$new_proxy_instance2;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$Java$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Java$s$2$0$new_proxy_instance2(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 2, "new_proxy_instance2", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(Java.class, "new_proxy_instance2", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("new_proxy_instance2", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("new_proxy_instance2", moduleMethod);
        javaMethod = new Java$s$1$0$java_to_ruby(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "java_to_ruby", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(Java.class, "java_to_ruby", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("java_to_ruby", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("java_to_ruby", moduleMethod);
        javaMethod = new Java$s$1$0$java_to_primitive(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "java_to_primitive", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(Java.class, "java_to_primitive", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("java_to_primitive", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("java_to_primitive", moduleMethod);
        javaMethod = new Java$s$1$0$ruby_to_java(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "ruby_to_java", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(Java.class, "ruby_to_java", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("ruby_to_java", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("ruby_to_java", moduleMethod);
        runtime.addBoundMethod("org.jruby.javasupport.Java.new_proxy_instance2", "new_proxy_instance2");
        runtime.addBoundMethod("org.jruby.javasupport.Java.java_to_ruby", "java_to_ruby");
        runtime.addBoundMethod("org.jruby.javasupport.Java.java_to_primitive", "java_to_primitive");
        runtime.addBoundMethod("org.jruby.javasupport.Java.ruby_to_java", "ruby_to_java");
    }
}
