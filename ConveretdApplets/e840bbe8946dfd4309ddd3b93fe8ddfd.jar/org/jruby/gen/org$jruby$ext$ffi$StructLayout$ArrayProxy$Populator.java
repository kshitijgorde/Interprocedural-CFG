// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.StructLayout$ArrayProxy$i$2$0$put;
import org.jruby.ext.ffi.StructLayout$ArrayProxy$i$0$0$size;
import org.jruby.ext.ffi.StructLayout$ArrayProxy$i$1$0$get;
import org.jruby.ext.ffi.StructLayout$ArrayProxy$i$0$0$to_ptr;
import org.jruby.ext.ffi.StructLayout$ArrayProxy$i$0$0$get;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.StructLayout;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.StructLayout$ArrayProxy$i$0$0$each;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$StructLayout$ArrayProxy$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new StructLayout$ArrayProxy$i$0$0$each(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.ArrayProxy.class, "each", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("each", javaMethod);
        javaMethod = new StructLayout$ArrayProxy$i$0$0$get(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "get", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.ArrayProxy.class, "get", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_a", javaMethod);
        cls.addMethodAtBootTimeOnly("to_ary", javaMethod);
        javaMethod = new StructLayout$ArrayProxy$i$0$0$to_ptr(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_ptr", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.ArrayProxy.class, "to_ptr", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_ptr", javaMethod);
        javaMethod = new StructLayout$ArrayProxy$i$1$0$get(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "get", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.ArrayProxy.class, "get", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new StructLayout$ArrayProxy$i$0$0$size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.ArrayProxy.class, "size", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new StructLayout$ArrayProxy$i$2$0$put(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "put", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.ArrayProxy.class, "put", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.ArrayProxy.each", "each");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.ArrayProxy.get", "to_a");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.ArrayProxy.to_ptr", "to_ptr");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.ArrayProxy.get", "[]");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.ArrayProxy.size", "size");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.ArrayProxy.put", "[]=");
    }
}
