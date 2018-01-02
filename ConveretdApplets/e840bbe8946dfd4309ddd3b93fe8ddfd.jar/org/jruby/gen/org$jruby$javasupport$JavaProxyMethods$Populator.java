// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$hash;
import org.jruby.javasupport.JavaProxyMethods$s$1$0$op_equal;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$to_s;
import org.jruby.javasupport.JavaProxyMethods$s$1$0$java_object_set;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$java_object;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$to_java_object;
import org.jruby.runtime.Block;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$rbSynchronized;
import org.jruby.javasupport.JavaProxyMethods$s$1$0$op_eql;
import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$java_class;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaProxyMethods;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaProxyMethods$s$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaProxyMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaProxyMethods$s$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "inspect", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new JavaProxyMethods$s$0$0$java_class(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "java_class", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("java_class", javaMethod);
        javaMethod = new JavaProxyMethods$s$1$0$op_eql(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_eql", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "op_eql", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new JavaProxyMethods$s$0$0$rbSynchronized(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rbSynchronized", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "rbSynchronized", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("synchronized", javaMethod);
        javaMethod = new JavaProxyMethods$s$0$0$to_java_object(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_java_object", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "to_java_object", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_java_object", javaMethod);
        javaMethod = new JavaProxyMethods$s$0$0$java_object(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_object", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "java_object", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("java_object", javaMethod);
        javaMethod = new JavaProxyMethods$s$1$0$java_object_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "java_object_set", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "java_object_set", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("java_object=", javaMethod);
        javaMethod = new JavaProxyMethods$s$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "to_s", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new JavaProxyMethods$s$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "op_equal", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new JavaProxyMethods$s$0$0$hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hash", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxyMethods.class, "hash", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("hash", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.java_class", "java_class");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.op_eql", "eql?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.rbSynchronized", "synchronized");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.to_java_object", "to_java_object");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.java_object", "java_object");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.java_object_set", "java_object=");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.op_equal", "==");
        runtime.addBoundMethod("org.jruby.javasupport.JavaProxyMethods.hash", "hash");
    }
}
