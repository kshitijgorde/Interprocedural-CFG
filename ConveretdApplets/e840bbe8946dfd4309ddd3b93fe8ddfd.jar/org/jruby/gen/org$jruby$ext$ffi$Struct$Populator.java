// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.Struct$i$2$0$setFieldValue;
import org.jruby.ext.ffi.Struct$i$0$0$pointer;
import org.jruby.ext.ffi.Struct$i$order;
import org.jruby.ext.ffi.Struct$i$1$0$getFieldValue;
import org.jruby.ext.ffi.Struct$i$1$0$initialize_copy;
import org.jruby.ext.ffi.Struct$i$0$0$members;
import org.jruby.ext.ffi.Struct$i$0$0$null_p;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Struct;
import org.jruby.ext.ffi.Struct$i$0$0$getLayout;
import org.jruby.ext.ffi.Struct$i$initialize;
import org.jruby.ext.ffi.Struct$s$allocateOut;
import org.jruby.ext.ffi.Struct$s$allocateIn;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Struct$s$allocateInOut;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Struct$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Struct$s$allocateInOut(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "allocateInOut", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("new_inout", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("alloc_inout", javaMethod);
        javaMethod = new Struct$s$allocateIn(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "allocateIn", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("new_in", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("alloc_in", javaMethod);
        javaMethod = new Struct$s$allocateOut(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "allocateOut", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("new_out", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("alloc_out", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.allocateInOut", "new_inout");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.allocateIn", "new_in");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.allocateOut", "new_out");
        javaMethod = new Struct$i$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new Struct$i$0$0$getLayout(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getLayout", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "getLayout", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("cspec", javaMethod);
        cls.addMethodAtBootTimeOnly("layout", javaMethod);
        javaMethod = new Struct$i$0$0$null_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "null_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "null_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("null?", javaMethod);
        javaMethod = new Struct$i$0$0$members(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "members", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "members", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("members", javaMethod);
        javaMethod = new Struct$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "initialize_copy", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        javaMethod = new Struct$i$1$0$getFieldValue(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "getFieldValue", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "getFieldValue", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new Struct$i$order(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "order", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("order", javaMethod);
        javaMethod = new Struct$i$0$0$pointer(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "pointer", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "pointer", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("pointer", javaMethod);
        javaMethod = new Struct$i$2$0$setFieldValue(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "setFieldValue", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Struct.class, "setFieldValue", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.getLayout", "cspec");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.null_p", "null?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.members", "members");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.initialize_copy", "initialize_copy");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.getFieldValue", "[]");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.order", "order");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.pointer", "pointer");
        runtime.addBoundMethod("org.jruby.ext.ffi.Struct.setFieldValue", "[]=");
    }
}
