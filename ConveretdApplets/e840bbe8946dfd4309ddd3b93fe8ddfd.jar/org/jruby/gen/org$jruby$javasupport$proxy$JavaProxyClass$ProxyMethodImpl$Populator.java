// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.proxy.JavaProxyClass$ProxyMethodImpl$i$0$0$getDeclaringClass;
import org.jruby.javasupport.proxy.JavaProxyClass$ProxyMethodImpl$i$0$0$super_p;
import org.jruby.RubyFixnum;
import org.jruby.javasupport.proxy.JavaProxyClass$ProxyMethodImpl$i$0$0$arity;
import org.jruby.RubyArray;
import org.jruby.javasupport.proxy.JavaProxyClass$ProxyMethodImpl$i$0$0$argument_types;
import org.jruby.javasupport.proxy.JavaProxyClass$ProxyMethodImpl$i$0$0$do_invoke;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.proxy.JavaProxyClass;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.proxy.JavaProxyClass$ProxyMethodImpl$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$proxy$JavaProxyClass$ProxyMethodImpl$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaProxyClass$ProxyMethodImpl$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.ProxyMethodImpl.class, "inspect", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new JavaProxyClass$ProxyMethodImpl$i$0$0$do_invoke(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "do_invoke", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.ProxyMethodImpl.class, "do_invoke", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("invoke", javaMethod);
        javaMethod = new JavaProxyClass$ProxyMethodImpl$i$0$0$argument_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "argument_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.ProxyMethodImpl.class, "argument_types", RubyArray.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("argument_types", javaMethod);
        javaMethod = new JavaProxyClass$ProxyMethodImpl$i$0$0$arity(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "arity", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.ProxyMethodImpl.class, "arity", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("arity", javaMethod);
        javaMethod = new JavaProxyClass$ProxyMethodImpl$i$0$0$super_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "super_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.ProxyMethodImpl.class, "super_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("super?", javaMethod);
        javaMethod = new JavaProxyClass$ProxyMethodImpl$i$0$0$getDeclaringClass(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getDeclaringClass", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyClass.ProxyMethodImpl.class, "getDeclaringClass", JavaProxyClass.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("declaring_class", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.ProxyMethodImpl.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.ProxyMethodImpl.do_invoke", "invoke");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.ProxyMethodImpl.argument_types", "argument_types");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.ProxyMethodImpl.arity", "arity");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.ProxyMethodImpl.super_p", "super?");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyClass.ProxyMethodImpl.getDeclaringClass", "declaring_class");
    }
}
