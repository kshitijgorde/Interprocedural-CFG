// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.proxies.ArrayJavaProxy$i$2$0$op_aset;
import org.jruby.java.proxies.ArrayJavaProxy$i$1$0$at;
import org.jruby.java.proxies.ArrayJavaProxy$i$op_aref;
import org.jruby.java.proxies.ArrayJavaProxy$i$0$0$length;
import org.jruby.java.proxies.ArrayJavaProxy$i$1$0$op_plus;
import org.jruby.java.proxies.ArrayJavaProxy$i$0$0$to_a;
import org.jruby.java.proxies.ArrayJavaProxy$i$0$0$empty;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.proxies.ArrayJavaProxy;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.proxies.ArrayJavaProxy$i$0$0$each;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$proxies$ArrayJavaProxy$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ArrayJavaProxy$i$0$0$each(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "each", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each", javaMethod);
        javaMethod = new ArrayJavaProxy$i$0$0$empty(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "empty", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "empty", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("empty?", javaMethod);
        javaMethod = new ArrayJavaProxy$i$0$0$to_a(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_a", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "to_a", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_a", javaMethod);
        cls.addMethodAtBootTimeOnly("to_ary", javaMethod);
        javaMethod = new ArrayJavaProxy$i$1$0$op_plus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_plus", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "op_plus", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("+", javaMethod);
        javaMethod = new ArrayJavaProxy$i$0$0$length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "length", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "length", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new ArrayJavaProxy$i$op_aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "op_aref", false, CallConfiguration.FrameBacktraceScopeNone, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new ArrayJavaProxy$i$1$0$at(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "at", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "at", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("at", javaMethod);
        javaMethod = new ArrayJavaProxy$i$2$0$op_aset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "op_aset", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxy.class, "op_aset", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.each", "each");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.empty", "empty?");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.to_a", "to_a");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.op_plus", "+");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.length", "length");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.op_aref", "[]");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.at", "at");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxy.op_aset", "[]=");
    }
}
