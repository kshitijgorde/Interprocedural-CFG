// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.java.proxies.JavaProxy$i$1$0$marshal_load;
import org.jruby.java.proxies.JavaProxy$i$1$0$equal_p;
import org.jruby.java.proxies.JavaProxy$i$0$0$marshal_dump;
import org.jruby.java.proxies.JavaProxy$i$java_method;
import org.jruby.java.proxies.JavaProxy$i$java_send;
import org.jruby.java.proxies.JavaProxy$s$1$0$inherited;
import org.jruby.java.proxies.JavaProxy$s$0$0$op_aref;
import org.jruby.java.proxies.JavaProxy$s$0$0$field_accessor;
import org.jruby.java.proxies.JavaProxy$s$0$0$singleton_class;
import org.jruby.java.proxies.JavaProxy$s$0$0$field_reader;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.proxies.JavaProxy;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.proxies.JavaProxy$s$0$0$field_writer;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$proxies$JavaProxy$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaProxy$s$0$0$field_writer(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "field_writer", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "field_writer", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("field_writer", javaMethod);
        javaMethod = new JavaProxy$s$0$0$field_reader(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "field_reader", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "field_reader", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("field_reader", javaMethod);
        javaMethod = new JavaProxy$s$0$0$singleton_class(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "singleton_class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "singleton_class", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("singleton_class", javaMethod);
        javaMethod = new JavaProxy$s$0$0$field_accessor(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "field_accessor", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "field_accessor", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("field_accessor", javaMethod);
        javaMethod = new JavaProxy$s$0$0$op_aref(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "op_aref", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "op_aref", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new JavaProxy$s$1$0$inherited(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "inherited", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "inherited", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("inherited", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.field_writer", "field_writer");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.field_reader", "field_reader");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.singleton_class", "singleton_class");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.field_accessor", "field_accessor");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.op_aref", "[]");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.inherited", "inherited");
        javaMethod = new JavaProxy$i$java_send(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_send", false, CallConfiguration.FrameBacktraceScopeNone, false);
        cls.addMethodAtBootTimeOnly("java_send", javaMethod);
        javaMethod = new JavaProxy$i$java_method(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_method", false, CallConfiguration.FrameBacktraceScopeNone, false);
        cls.addMethodAtBootTimeOnly("java_method", javaMethod);
        javaMethod = new JavaProxy$i$0$0$marshal_dump(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "marshal_dump", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "marshal_dump", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("marshal_dump", javaMethod);
        javaMethod = new JavaProxy$i$1$0$equal_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "equal_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "equal_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("equal?", javaMethod);
        javaMethod = new JavaProxy$i$1$0$marshal_load(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "marshal_load", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaProxy.class, "marshal_load", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("marshal_load", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.java_send", "java_send");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.java_method", "java_method");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.marshal_dump", "marshal_dump");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.equal_p", "equal?");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaProxy.marshal_load", "marshal_load");
    }
}
