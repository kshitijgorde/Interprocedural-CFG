// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyEncoding$i$0$0$to_s;
import org.jruby.RubyEncoding$i$0$0$names;
import org.jruby.RubyEncoding$i$0$0$asciiCompatible_p;
import org.jruby.RubyEncoding$i$0$0$dummy_p;
import org.jruby.RubyEncoding$i$1$0$_dump;
import org.jruby.RubyEncoding$i$0$0$inspect;
import org.jruby.RubyEncoding$s$0$0$getDefaultInternal;
import org.jruby.RubyEncoding$s$0$0$getDefaultExternal;
import org.jruby.RubyEncoding$s$1$0$setDefaultExternal;
import org.jruby.RubyEncoding$s$1$0$setDefaultInternal;
import org.jruby.CompatVersion;
import org.jruby.RubyEncoding$s$0$0$aliases;
import org.jruby.RubyEncoding$s$1$0$find;
import org.jruby.RubyEncoding$s$0$0$locale_charmap;
import org.jruby.RubyEncoding$s$2$0$compatible_p;
import org.jruby.RubyEncoding$s$0$0$name_list;
import org.jruby.RubyEncoding$s$1$0$_load;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyEncoding;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyEncoding$s$0$0$list;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyEncoding$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyEncoding$s$0$0$list(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "list", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "list", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("list", javaMethod);
        javaMethod = new RubyEncoding$s$1$0$_load(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "_load", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "_load", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("_load", javaMethod);
        javaMethod = new RubyEncoding$s$0$0$name_list(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "name_list", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "name_list", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("name_list", javaMethod);
        javaMethod = new RubyEncoding$s$2$0$compatible_p(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "compatible_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "compatible_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("compatible?", javaMethod);
        javaMethod = new RubyEncoding$s$0$0$locale_charmap(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "locale_charmap", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "locale_charmap", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("locale_charmap", javaMethod);
        javaMethod = new RubyEncoding$s$1$0$find(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "find", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "find", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("find", javaMethod);
        javaMethod = new RubyEncoding$s$0$0$aliases(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "aliases", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "aliases", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("aliases", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyEncoding.list", "list");
        runtime.addBoundMethod("org.jruby.RubyEncoding._load", "_load");
        runtime.addBoundMethod("org.jruby.RubyEncoding.name_list", "name_list");
        runtime.addBoundMethod("org.jruby.RubyEncoding.compatible_p", "compatible?");
        runtime.addBoundMethod("org.jruby.RubyEncoding.locale_charmap", "locale_charmap");
        runtime.addBoundMethod("org.jruby.RubyEncoding.find", "find");
        runtime.addBoundMethod("org.jruby.RubyEncoding.aliases", "aliases");
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyEncoding$s$1$0$setDefaultInternal(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "setDefaultInternal", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEncoding.class, "setDefaultInternal", Void.TYPE, new Class[] { IRubyObject.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("default_internal=", javaMethod);
            javaMethod = new RubyEncoding$s$1$0$setDefaultExternal(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "setDefaultExternal", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEncoding.class, "setDefaultExternal", Void.TYPE, new Class[] { IRubyObject.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("default_external=", javaMethod);
            javaMethod = new RubyEncoding$s$0$0$getDefaultExternal(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "getDefaultExternal", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEncoding.class, "getDefaultExternal", IRubyObject.class, new Class[] { IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("default_external", javaMethod);
            javaMethod = new RubyEncoding$s$0$0$getDefaultInternal(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "getDefaultInternal", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyEncoding.class, "getDefaultInternal", IRubyObject.class, new Class[] { IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("default_internal", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyEncoding.setDefaultInternal", "default_internal=");
            runtime.addBoundMethod("org.jruby.RubyEncoding.setDefaultExternal", "default_external=");
            runtime.addBoundMethod("org.jruby.RubyEncoding.getDefaultExternal", "default_external");
            runtime.addBoundMethod("org.jruby.RubyEncoding.getDefaultInternal", "default_internal");
        }
        javaMethod = new RubyEncoding$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyEncoding$i$1$0$_dump(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "_dump", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "_dump", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("_dump", javaMethod);
        javaMethod = new RubyEncoding$i$0$0$dummy_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "dummy_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "dummy_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("dummy?", javaMethod);
        javaMethod = new RubyEncoding$i$0$0$asciiCompatible_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "asciiCompatible_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "asciiCompatible_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("ascii_compatible?", javaMethod);
        javaMethod = new RubyEncoding$i$0$0$names(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "names", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "names", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("names", javaMethod);
        javaMethod = new RubyEncoding$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyEncoding.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        cls.addMethodAtBootTimeOnly("name", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyEncoding.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyEncoding._dump", "_dump");
        runtime.addBoundMethod("org.jruby.RubyEncoding.dummy_p", "dummy?");
        runtime.addBoundMethod("org.jruby.RubyEncoding.asciiCompatible_p", "ascii_compatible?");
        runtime.addBoundMethod("org.jruby.RubyEncoding.names", "names");
        runtime.addBoundMethod("org.jruby.RubyEncoding.to_s", "to_s");
    }
}
