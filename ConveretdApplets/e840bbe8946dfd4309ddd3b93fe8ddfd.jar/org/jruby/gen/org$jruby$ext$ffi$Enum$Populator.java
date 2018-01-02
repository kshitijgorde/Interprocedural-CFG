// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.Enum$i$0$0$native_type;
import org.jruby.ext.ffi.Enum$i$2$0$to_native;
import org.jruby.ext.ffi.Enum$i$1$0$init_values;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Enum;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Enum$i$2$0$from_native;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Enum$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Enum$i$2$0$from_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "from_native", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Enum.class, "from_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("from_native", javaMethod);
        javaMethod = new Enum$i$1$0$init_values(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "init_values", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Enum.class, "init_values", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("init_values", javaMethod);
        javaMethod = new Enum$i$2$0$to_native(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "to_native", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Enum.class, "to_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("to_native", javaMethod);
        javaMethod = new Enum$i$0$0$native_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "native_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Enum.class, "native_type", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("native_type", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Enum.from_native", "from_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.Enum.init_values", "init_values");
        runtime.addBoundMethod("org.jruby.ext.ffi.Enum.to_native", "to_native");
        runtime.addBoundMethod("org.jruby.ext.ffi.Enum.native_type", "native_type");
    }
}
