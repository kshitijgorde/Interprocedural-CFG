// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.Pointer$i$0$1$to_s;
import org.jruby.ext.ffi.Pointer$i$1$0$initialize_copy;
import org.jruby.ext.ffi.Pointer$i$0$0$address;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Pointer;
import org.jruby.ext.ffi.Pointer$i$0$0$null_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Pointer$i$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Pointer$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Pointer$i$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new Pointer$i$0$0$null_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "null_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Pointer.class, "null_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("null?", javaMethod);
        javaMethod = new Pointer$i$0$0$address(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "address", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Pointer.class, "address", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("address", javaMethod);
        cls.addMethodAtBootTimeOnly("to_i", javaMethod);
        javaMethod = new Pointer$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Pointer.class, "initialize_copy", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        javaMethod = new Pointer$i$0$1$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Pointer.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Pointer.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.ffi.Pointer.null_p", "null?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Pointer.address", "address");
        runtime.addBoundMethod("org.jruby.ext.ffi.Pointer.initialize_copy", "initialize_copy");
        runtime.addBoundMethod("org.jruby.ext.ffi.Pointer.to_s", "to_s");
    }
}
