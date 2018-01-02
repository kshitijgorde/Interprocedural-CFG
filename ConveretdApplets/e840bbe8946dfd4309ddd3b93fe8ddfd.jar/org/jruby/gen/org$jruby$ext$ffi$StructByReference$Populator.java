// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.StructByReference$i$0$0$native_type;
import org.jruby.ext.ffi.StructByReference$i$0$0$to_s;
import org.jruby.ext.ffi.StructByReference$i$2$0$to_native;
import org.jruby.ext.ffi.StructByReference$i$0$0$layout;
import org.jruby.ext.ffi.StructByReference$i$0$0$struct_class;
import org.jruby.ext.ffi.StructByReference$i$2$0$from_native;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.StructByReference;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.StructByReference$s$1$0$newStructByReference;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$StructByReference$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new StructByReference$s$1$0$newStructByReference(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "newStructByReference", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "newStructByReference", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.newStructByReference", "new");
        javaMethod = new StructByReference$i$2$0$from_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "from_native", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "from_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("from_native", javaMethod);
        javaMethod = new StructByReference$i$0$0$struct_class(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "struct_class", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "struct_class", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("struct_class", javaMethod);
        javaMethod = new StructByReference$i$0$0$layout(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "layout", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "layout", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("layout", javaMethod);
        javaMethod = new StructByReference$i$2$0$to_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "to_native", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "to_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("to_native", javaMethod);
        javaMethod = new StructByReference$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new StructByReference$i$0$0$native_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "native_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByReference.class, "native_type", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("native_type", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.from_native", "from_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.struct_class", "struct_class");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.layout", "layout");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.to_native", "to_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByReference.native_type", "native_type");
    }
}
