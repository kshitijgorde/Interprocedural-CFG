// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObjectSpace$s$1$0$id2ref;
import org.jruby.RubyObjectSpace$s$1$0$undefine_finalizer;
import org.jruby.RubyObjectSpace$s$0$0$garbage_collect;
import org.jruby.RubyObjectSpace$s$0$1$define_finalizer;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObjectSpace;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyObjectSpace$s$0$1$each_object;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyObjectSpace$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyObjectSpace$s$0$1$each_object(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "each_object", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyObjectSpace.class, "each_object", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("each_object", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("each_object", moduleMethod);
        javaMethod = new RubyObjectSpace$s$0$1$define_finalizer(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "define_finalizer", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyObjectSpace.class, "define_finalizer", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("define_finalizer", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("define_finalizer", moduleMethod);
        javaMethod = new RubyObjectSpace$s$0$0$garbage_collect(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "garbage_collect", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyObjectSpace.class, "garbage_collect", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("garbage_collect", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("garbage_collect", moduleMethod);
        javaMethod = new RubyObjectSpace$s$1$0$undefine_finalizer(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "undefine_finalizer", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyObjectSpace.class, "undefine_finalizer", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("undefine_finalizer", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("undefine_finalizer", moduleMethod);
        javaMethod = new RubyObjectSpace$s$1$0$id2ref(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "id2ref", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyObjectSpace.class, "id2ref", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("_id2ref", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("_id2ref", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyObjectSpace.each_object", "each_object");
        runtime.addBoundMethod("org.jruby.RubyObjectSpace.define_finalizer", "define_finalizer");
        runtime.addBoundMethod("org.jruby.RubyObjectSpace.garbage_collect", "garbage_collect");
        runtime.addBoundMethod("org.jruby.RubyObjectSpace.undefine_finalizer", "undefine_finalizer");
        runtime.addBoundMethod("org.jruby.RubyObjectSpace.id2ref", "_id2ref");
    }
}
