// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.WeakRef$i$0$0$weakref_alive_p;
import org.jruby.ext.WeakRef$i$0$0$getobj;
import org.jruby.ext.WeakRef$i$1$0$setobj;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.WeakRef$i$1$0$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.WeakRef;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.WeakRef$s$1$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$WeakRef$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new WeakRef$s$1$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(WeakRef.class, "newInstance", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.WeakRef.newInstance", "new");
        javaMethod = new WeakRef$i$1$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", false, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(WeakRef.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new WeakRef$i$1$0$setobj(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "setobj", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(WeakRef.class, "setobj", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("__setobj__", javaMethod);
        javaMethod = new WeakRef$i$0$0$getobj(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getobj", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(WeakRef.class, "getobj", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("__getobj__", javaMethod);
        javaMethod = new WeakRef$i$0$0$weakref_alive_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "weakref_alive_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(WeakRef.class, "weakref_alive_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("weakref_alive?", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.WeakRef.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.WeakRef.setobj", "__setobj__");
        runtime.addBoundMethod("org.jruby.ext.WeakRef.getobj", "__getobj__");
        runtime.addBoundMethod("org.jruby.ext.WeakRef.weakref_alive_p", "weakref_alive?");
    }
}
