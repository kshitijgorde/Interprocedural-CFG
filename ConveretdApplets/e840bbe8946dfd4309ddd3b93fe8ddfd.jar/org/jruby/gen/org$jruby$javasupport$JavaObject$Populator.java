// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyString;
import org.jruby.javasupport.JavaObject$i$0$0$java_type;
import org.jruby.runtime.Block;
import org.jruby.javasupport.JavaObject$i$0$0$ruby_synchronized;
import org.jruby.javasupport.JavaObject$i$1$0$same;
import org.jruby.javasupport.JavaObject$i$0$0$is_java_proxy;
import org.jruby.javasupport.JavaObject$i$0$0$java_class;
import org.jruby.javasupport.JavaObject$i$2$0$aset;
import org.jruby.javasupport.JavaObject$i$0$0$hash;
import org.jruby.javasupport.JavaObject$i$1$0$op_equal;
import org.jruby.javasupport.JavaObject$i$0$0$to_s;
import org.jruby.javasupport.JavaObject$i$3$0$afill;
import org.jruby.javasupport.JavaObject$i$1$0$aref;
import org.jruby.javasupport.JavaObject$i$1$0$marshal_load;
import org.jruby.RubyFixnum;
import org.jruby.javasupport.JavaObject$i$0$0$length;
import org.jruby.javasupport.JavaObject$i$0$0$marshal_dump;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaObject;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaObject$s$1$0$wrap;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaObject$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaObject$s$1$0$wrap(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "wrap", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "wrap", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("wrap", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.wrap", "wrap");
        javaMethod = new JavaObject$i$0$0$marshal_dump(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "marshal_dump", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "marshal_dump", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("marshal_dump", javaMethod);
        javaMethod = new JavaObject$i$0$0$length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "length", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "length", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        javaMethod = new JavaObject$i$1$0$marshal_load(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "marshal_load", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "marshal_load", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("marshal_load", javaMethod);
        javaMethod = new JavaObject$i$1$0$aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "aref", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new JavaObject$i$3$0$afill(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "afill", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "afill", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("fill", javaMethod);
        javaMethod = new JavaObject$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "to_s", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new JavaObject$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "op_equal", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new JavaObject$i$0$0$hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "hash", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("hash", javaMethod);
        javaMethod = new JavaObject$i$2$0$aset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "aset", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "aset", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        javaMethod = new JavaObject$i$0$0$java_class(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_class", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "java_class", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("java_class", javaMethod);
        javaMethod = new JavaObject$i$0$0$is_java_proxy(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "is_java_proxy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "is_java_proxy", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("java_proxy?", javaMethod);
        javaMethod = new JavaObject$i$1$0$same(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "same", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "same", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("equal?", javaMethod);
        javaMethod = new JavaObject$i$0$0$ruby_synchronized(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "ruby_synchronized", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "ruby_synchronized", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("synchronized", javaMethod);
        javaMethod = new JavaObject$i$0$0$java_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "java_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaObject.class, "java_type", RubyString.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("java_type", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.marshal_dump", "marshal_dump");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.length", "length");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.marshal_load", "marshal_load");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.aref", "[]");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.afill", "fill");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.op_equal", "==");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.hash", "hash");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.aset", "[]=");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.java_class", "java_class");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.is_java_proxy", "java_proxy?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.same", "equal?");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.ruby_synchronized", "synchronized");
        runtime.addBoundMethod("org.jruby.javasupport.JavaObject.java_type", "java_type");
    }
}
