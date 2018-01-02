// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.Platform$s$0$0$unix_p;
import org.jruby.ext.ffi.Platform$s$0$0$linux_p;
import org.jruby.ext.ffi.Platform$s$0$0$bsd_p;
import org.jruby.ext.ffi.Platform$s$0$0$solaris_p;
import org.jruby.ext.ffi.Platform$s$0$0$windows_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Platform;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Platform$s$0$0$mac_p;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Platform$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Platform$s$0$0$mac_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "mac_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Platform.class, "mac_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("mac?", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("mac?", moduleMethod);
        javaMethod = new Platform$s$0$0$windows_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "windows_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Platform.class, "windows_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("windows?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("windows?", moduleMethod);
        javaMethod = new Platform$s$0$0$solaris_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "solaris_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Platform.class, "solaris_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("solaris?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("solaris?", moduleMethod);
        javaMethod = new Platform$s$0$0$bsd_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "bsd_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Platform.class, "bsd_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("bsd?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("bsd?", moduleMethod);
        javaMethod = new Platform$s$0$0$linux_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "linux_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Platform.class, "linux_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("linux?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("linux?", moduleMethod);
        javaMethod = new Platform$s$0$0$unix_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "unix_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Platform.class, "unix_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("unix?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("unix?", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Platform.mac_p", "mac?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Platform.windows_p", "windows?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Platform.solaris_p", "solaris?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Platform.bsd_p", "bsd?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Platform.linux_p", "linux?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Platform.unix_p", "unix?");
    }
}
