// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyProcess$Sys$s$1$0$setgid;
import org.jruby.RubyProcess$Sys$s$0$0$getgid;
import org.jruby.RubyProcess$Sys$s$1$0$setuid;
import org.jruby.RubyProcess$Sys$s$0$0$geteuid;
import org.jruby.RubyProcess$Sys$s$1$0$setegid;
import org.jruby.RubyProcess$Sys$s$0$0$getuid;
import org.jruby.RubyProcess$Sys$s$0$0$getegid;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyProcess;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyProcess$Sys$s$1$0$seteuid;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyProcess$Sys$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyProcess$Sys$s$1$0$seteuid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "seteuid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "seteuid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("seteuid", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("seteuid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$0$0$getegid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "getegid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "getegid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("getegid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("getegid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$0$0$getuid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "getuid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "getuid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("getuid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("getuid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$1$0$setegid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "setegid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "setegid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("setegid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("setegid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$0$0$geteuid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "geteuid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "geteuid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("geteuid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("geteuid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$1$0$setuid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "setuid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "setuid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("setuid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("setuid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$0$0$getgid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "getgid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "getgid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("getgid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("getgid", moduleMethod);
        javaMethod = new RubyProcess$Sys$s$1$0$setgid(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "setgid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.Sys.class, "setgid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("setgid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("setgid", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.seteuid", "seteuid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.getegid", "getegid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.getuid", "getuid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.setegid", "setegid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.geteuid", "geteuid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.setuid", "setuid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.getgid", "getgid");
        runtime.addBoundMethod("org.jruby.RubyProcess.Sys.setgid", "setgid");
    }
}
