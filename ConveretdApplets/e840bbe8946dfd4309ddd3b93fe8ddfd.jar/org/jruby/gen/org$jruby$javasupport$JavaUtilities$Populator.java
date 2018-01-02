// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.javasupport.JavaUtilities$s$3$0$create_proxy_class;
import org.jruby.javasupport.JavaUtilities$s$1$0$get_package_module_dot_format;
import org.jruby.javasupport.JavaUtilities$s$1$0$get_java_class;
import org.jruby.javasupport.JavaUtilities$s$1$0$get_top_level_proxy_or_package;
import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.JavaUtilities$s$2$0$get_proxy_or_package_under_package;
import org.jruby.javasupport.JavaUtilities$s$1$0$get_proxy_class;
import org.jruby.javasupport.JavaUtilities$s$2$0$set_java_object;
import org.jruby.javasupport.JavaUtilities$s$1$0$get_interface_module;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtilities;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaUtilities$s$1$0$get_package_module;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaUtilities$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaUtilities$s$1$0$get_package_module(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "get_package_module", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_package_module", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_package_module", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_package_module", moduleMethod);
        javaMethod = new JavaUtilities$s$1$0$get_interface_module(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "get_interface_module", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_interface_module", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_interface_module", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_interface_module", moduleMethod);
        javaMethod = new JavaUtilities$s$2$0$set_java_object(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 2, "set_java_object", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "set_java_object", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("set_java_object", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("set_java_object", moduleMethod);
        javaMethod = new JavaUtilities$s$1$0$get_proxy_class(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "get_proxy_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_proxy_class", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_proxy_class", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_proxy_class", moduleMethod);
        javaMethod = new JavaUtilities$s$2$0$get_proxy_or_package_under_package(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 2, "get_proxy_or_package_under_package", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_proxy_or_package_under_package", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_proxy_or_package_under_package", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_proxy_or_package_under_package", moduleMethod);
        javaMethod = new JavaUtilities$s$1$0$get_top_level_proxy_or_package(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "get_top_level_proxy_or_package", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_top_level_proxy_or_package", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_top_level_proxy_or_package", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_top_level_proxy_or_package", moduleMethod);
        javaMethod = new JavaUtilities$s$1$0$get_java_class(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "get_java_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_java_class", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_java_class", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_java_class", moduleMethod);
        javaMethod = new JavaUtilities$s$1$0$get_package_module_dot_format(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "get_package_module_dot_format", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "get_package_module_dot_format", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("get_package_module_dot_format", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("get_package_module_dot_format", moduleMethod);
        javaMethod = new JavaUtilities$s$3$0$create_proxy_class(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 3, "create_proxy_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaUtilities.class, "create_proxy_class", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("create_proxy_class", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("create_proxy_class", moduleMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_package_module", "get_package_module");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_interface_module", "get_interface_module");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.set_java_object", "set_java_object");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_proxy_class", "get_proxy_class");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_proxy_or_package_under_package", "get_proxy_or_package_under_package");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_top_level_proxy_or_package", "get_top_level_proxy_or_package");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_java_class", "get_java_class");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.get_package_module_dot_format", "get_package_module_dot_format");
        runtime.addBoundMethod("org.jruby.javasupport.JavaUtilities.create_proxy_class", "create_proxy_class");
    }
}
