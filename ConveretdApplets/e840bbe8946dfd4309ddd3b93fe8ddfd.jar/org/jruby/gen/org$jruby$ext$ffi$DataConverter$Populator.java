// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.DataConverter$s$0$1$native_type;
import org.jruby.ext.ffi.DataConverter$s$2$0$to_native;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.DataConverter;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.DataConverter$s$2$0$from_native;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$DataConverter$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new DataConverter$s$2$0$from_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "from_native", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DataConverter.class, "from_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("from_native", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("from_native", moduleMethod);
        javaMethod = new DataConverter$s$2$0$to_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "to_native", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DataConverter.class, "to_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_native", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("to_native", moduleMethod);
        javaMethod = new DataConverter$s$0$1$native_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "native_type", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DataConverter.class, "native_type", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("native_type", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("native_type", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.DataConverter.from_native", "from_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.DataConverter.to_native", "to_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.DataConverter.native_type", "native_type");
    }
}
