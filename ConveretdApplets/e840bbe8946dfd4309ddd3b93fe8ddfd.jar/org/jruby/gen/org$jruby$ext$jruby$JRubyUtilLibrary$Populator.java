// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.jruby.JRubyUtilLibrary$s$0$0$getObjectSpaceEnabled;
import org.jruby.ext.jruby.JRubyUtilLibrary$s$1$0$getClassLoaderResources;
import org.jruby.ext.jruby.JRubyUtilLibrary$s$0$0$gc;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.jruby.JRubyUtilLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.jruby.JRubyUtilLibrary$s$1$0$setObjectSpaceEnabled;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$jruby$JRubyUtilLibrary$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JRubyUtilLibrary$s$1$0$setObjectSpaceEnabled(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "setObjectSpaceEnabled", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyUtilLibrary.class, "setObjectSpaceEnabled", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("objectspace=", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("objectspace=", moduleMethod);
        javaMethod = new JRubyUtilLibrary$s$0$0$gc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "gc", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyUtilLibrary.class, "gc", Void.TYPE, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("gc", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("gc", moduleMethod);
        javaMethod = new JRubyUtilLibrary$s$1$0$getClassLoaderResources(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "getClassLoaderResources", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyUtilLibrary.class, "getClassLoaderResources", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("classloader_resources", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("classloader_resources", moduleMethod);
        javaMethod = new JRubyUtilLibrary$s$0$0$getObjectSpaceEnabled(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getObjectSpaceEnabled", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyUtilLibrary.class, "getObjectSpaceEnabled", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("objectspace", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("objectspace", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.jruby.JRubyUtilLibrary.setObjectSpaceEnabled", "objectspace=");
        runtime.addBoundMethod("org.jruby.ext.jruby.JRubyUtilLibrary.gc", "gc");
        runtime.addBoundMethod("org.jruby.ext.jruby.JRubyUtilLibrary.getClassLoaderResources", "classloader_resources");
        runtime.addBoundMethod("org.jruby.ext.jruby.JRubyUtilLibrary.getObjectSpaceEnabled", "objectspace");
    }
}
