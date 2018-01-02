// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.MappedType$i$0$0$native_type;
import org.jruby.ext.ffi.MappedType$i$2$0$to_native;
import org.jruby.ext.ffi.MappedType$i$2$0$from_native;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.MappedType;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.MappedType$s$1$0$newMappedType;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$MappedType$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new MappedType$s$1$0$newMappedType(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "newMappedType", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MappedType.class, "newMappedType", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.MappedType.newMappedType", "new");
        javaMethod = new MappedType$i$2$0$from_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "from_native", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MappedType.class, "from_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("from_native", javaMethod);
        javaMethod = new MappedType$i$2$0$to_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "to_native", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MappedType.class, "to_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("to_native", javaMethod);
        javaMethod = new MappedType$i$0$0$native_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "native_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MappedType.class, "native_type", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("native_type", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.MappedType.from_native", "from_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.MappedType.to_native", "to_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.MappedType.native_type", "native_type");
    }
}
