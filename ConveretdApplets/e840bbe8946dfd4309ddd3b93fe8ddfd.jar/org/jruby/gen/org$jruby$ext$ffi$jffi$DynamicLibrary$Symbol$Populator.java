// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.jffi.DynamicLibrary$Symbol$i$0$1$to_s;
import org.jruby.ext.ffi.jffi.DynamicLibrary$Symbol$i$0$0$library;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.jffi.DynamicLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.jffi.DynamicLibrary$Symbol$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$jffi$DynamicLibrary$Symbol$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new DynamicLibrary$Symbol$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.Symbol.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new DynamicLibrary$Symbol$i$0$0$library(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "library", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.Symbol.class, "library", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("library", javaMethod);
        javaMethod = new DynamicLibrary$Symbol$i$0$1$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.Symbol.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.Symbol.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.Symbol.library", "library");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.Symbol.to_s", "to_s");
    }
}
