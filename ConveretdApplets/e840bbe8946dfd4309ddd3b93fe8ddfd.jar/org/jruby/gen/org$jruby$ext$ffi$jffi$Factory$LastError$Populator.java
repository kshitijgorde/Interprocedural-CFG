// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.jffi.Factory$LastError$s$0$0$error;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.jffi.Factory;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.jffi.Factory$LastError$s$1$0$error_set;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$jffi$Factory$LastError$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Factory$LastError$s$1$0$error_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "error_set", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Factory.LastError.class, "error_set", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("error=", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("error=", moduleMethod);
        javaMethod = new Factory$LastError$s$0$0$error(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "error", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Factory.LastError.class, "error", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("error", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("error", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.Factory.LastError.error_set", "error=");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.Factory.LastError.error", "error");
    }
}