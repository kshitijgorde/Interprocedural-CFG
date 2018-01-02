// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.StructByValue$i$0$0$to_s;
import org.jruby.ext.ffi.StructByValue$i$0$0$layout;
import org.jruby.ext.ffi.StructByValue$i$0$0$struct_class;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.StructByValue;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.StructByValue$s$1$0$newStructByValue;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$StructByValue$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new StructByValue$s$1$0$newStructByValue(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "newStructByValue", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByValue.class, "newStructByValue", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByValue.newStructByValue", "new");
        javaMethod = new StructByValue$i$0$0$struct_class(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "struct_class", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByValue.class, "struct_class", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("struct_class", javaMethod);
        javaMethod = new StructByValue$i$0$0$layout(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "layout", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByValue.class, "layout", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("layout", javaMethod);
        javaMethod = new StructByValue$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructByValue.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByValue.struct_class", "struct_class");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByValue.layout", "layout");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructByValue.to_s", "to_s");
    }
}
