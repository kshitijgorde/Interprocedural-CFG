// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.Buffer$i$1$0$initialize_copy;
import org.jruby.ext.ffi.Buffer$i$initialize;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Buffer;
import org.jruby.ext.ffi.Buffer$i$0$0$inspect;
import org.jruby.ext.ffi.Buffer$s$allocateOutput;
import org.jruby.ext.ffi.Buffer$s$allocateInput;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Buffer$s$allocateInOut;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Buffer$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Buffer$s$allocateInOut(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "allocateInOut", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("alloc_inout", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("__alloc_inout", javaMethod);
        javaMethod = new Buffer$s$allocateInput(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "allocateInput", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("new_in", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("alloc_in", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("__alloc_in", javaMethod);
        javaMethod = new Buffer$s$allocateOutput(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "allocateOutput", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("new_out", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("alloc_out", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("__alloc_out", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Buffer.allocateInOut", "alloc_inout");
        runtime.addBoundMethod("org.jruby.ext.ffi.Buffer.allocateInput", "new_in");
        runtime.addBoundMethod("org.jruby.ext.ffi.Buffer.allocateOutput", "new_out");
        javaMethod = new Buffer$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Buffer.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new Buffer$i$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new Buffer$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Buffer.class, "initialize_copy", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Buffer.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.ext.ffi.Buffer.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.ffi.Buffer.initialize_copy", "initialize_copy");
    }
}
