// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.javasupport.proxy.JavaProxyClass$i$0$0$methods;
import org.jruby.javasupport.proxy.JavaProxyClass$i$0$0$interfaces;
import org.jruby.RubyArray;
import org.jruby.javasupport.proxy.JavaProxyClass$i$0$0$constructors;
import org.jruby.javasupport.proxy.JavaProxyClass$i$0$0$superclass;
import org.jruby.javasupport.proxy.JavaProxyClass$s$1$0$get;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObject;
import org.jruby.javasupport.proxy.JavaProxyClass;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.proxy.JavaProxyClass$s$1$0$get_with_class;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$proxy$JavaProxyClass$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaProxyClass$s$1$0$get_with_class(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "get_with_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.class, "get_with_class", RubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("get_with_class", javaMethod);
        javaMethod = new JavaProxyClass$s$1$0$get(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "get", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.class, "get", RubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("get", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.get_with_class", "get_with_class");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.get", "get");
        javaMethod = new JavaProxyClass$i$0$0$superclass(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "superclass", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.class, "superclass", RubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("superclass", javaMethod);
        javaMethod = new JavaProxyClass$i$0$0$constructors(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "constructors", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.class, "constructors", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("constructors", javaMethod);
        javaMethod = new JavaProxyClass$i$0$0$interfaces(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "interfaces", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.class, "interfaces", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("interfaces", javaMethod);
        javaMethod = new JavaProxyClass$i$0$0$methods(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "methods", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.class, "methods", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("methods", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.superclass", "superclass");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.constructors", "constructors");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.interfaces", "interfaces");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.methods", "methods");
    }
}
