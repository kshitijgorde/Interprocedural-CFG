// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.Type$Array$i$0$0$elem_type;
import org.jruby.ext.ffi.Type$Array$i$0$0$length;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Type;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Type$Array$s$2$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Type$Array$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Type$Array$s$2$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Array.class, "newInstance", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Array.newInstance", "new");
        javaMethod = new Type$Array$i$0$0$length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "length", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Array.class, "length", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        javaMethod = new Type$Array$i$0$0$elem_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "elem_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Array.class, "elem_type", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("elem_type", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Array.length", "length");
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Array.elem_type", "elem_type");
    }
}
