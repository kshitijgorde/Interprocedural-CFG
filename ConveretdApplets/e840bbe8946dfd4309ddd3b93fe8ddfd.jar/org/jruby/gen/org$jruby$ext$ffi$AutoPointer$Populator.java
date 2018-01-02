// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.AutoPointer$i$1$0$autorelease;
import org.jruby.ext.ffi.AutoPointer$i$0$0$free;
import org.jruby.ext.ffi.AutoPointer$i$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.AutoPointer;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.AutoPointer$s$2$0$from_native;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$AutoPointer$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new AutoPointer$s$2$0$from_native(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "from_native", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(AutoPointer.class, "from_native", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("from_native", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.AutoPointer.from_native", "from_native");
        javaMethod = new AutoPointer$i$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new AutoPointer$i$0$0$free(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "free", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(AutoPointer.class, "free", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("free", javaMethod);
        javaMethod = new AutoPointer$i$1$0$autorelease(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "autorelease", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(AutoPointer.class, "autorelease", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("autorelease=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.AutoPointer.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.ffi.AutoPointer.free", "free");
        runtime.addBoundMethod("org.jruby.ext.ffi.AutoPointer.autorelease", "autorelease=");
    }
}
