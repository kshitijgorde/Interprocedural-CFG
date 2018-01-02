// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$0$0$hash;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$2$0$aset;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$0$0$to_s;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$1$0$op_equal;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$1$0$aref;
import org.jruby.RubyString;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$0$0$java_type;
import org.jruby.RubyFixnum;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$0$0$length;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$1$0$same;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$0$0$is_java_proxy;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.proxy.JavaProxyReflectionObject$i$0$0$java_class;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$proxy$JavaProxyReflectionObject$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaProxyReflectionObject$i$0$0$java_class(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_class", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "java_class", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("java_class", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$0$0$is_java_proxy(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "is_java_proxy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "is_java_proxy", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("java_proxy?", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$1$0$same(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "same", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "same", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("equal?", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$0$0$length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "length", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "length", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$0$0$java_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "java_type", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("java_type", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$1$0$aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "aref", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "op_equal", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "to_s", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$2$0$aset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "aset", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "aset", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        javaMethod = new JavaProxyReflectionObject$i$0$0$hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyReflectionObject.class, "hash", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("hash", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.java_class", "java_class");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.is_java_proxy", "java_proxy?");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.same", "equal?");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.length", "length");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.java_type", "java_type");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.aref", "[]");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.op_equal", "==");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.aset", "[]=");
        runtime.addBoundMethod("org.jruby.javasupport.proxy.JavaProxyReflectionObject.hash", "hash");
    }
}
