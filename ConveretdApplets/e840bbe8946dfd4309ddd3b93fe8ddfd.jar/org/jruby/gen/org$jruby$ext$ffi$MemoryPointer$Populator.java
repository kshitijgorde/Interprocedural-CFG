// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.MemoryPointer$i$1$0$autorelease;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.MemoryPointer;
import org.jruby.ext.ffi.MemoryPointer$i$0$0$free;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.MemoryPointer$i$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$MemoryPointer$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new MemoryPointer$i$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new MemoryPointer$i$0$0$free(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "free", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MemoryPointer.class, "free", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("free", javaMethod);
        javaMethod = new MemoryPointer$i$1$0$autorelease(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "autorelease", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MemoryPointer.class, "autorelease", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("autorelease=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.MemoryPointer.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.ffi.MemoryPointer.free", "free");
        runtime.addBoundMethod("org.jruby.ext.ffi.MemoryPointer.autorelease", "autorelease=");
    }
}
