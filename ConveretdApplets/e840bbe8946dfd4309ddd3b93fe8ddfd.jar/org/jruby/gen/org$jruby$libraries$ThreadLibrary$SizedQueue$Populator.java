// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyNumeric;
import org.jruby.libraries.ThreadLibrary$SizedQueue$i$0$0$max;
import org.jruby.libraries.ThreadLibrary$SizedQueue$i$0$1$pop;
import org.jruby.libraries.ThreadLibrary$SizedQueue$i$0$0$clear;
import org.jruby.libraries.ThreadLibrary$SizedQueue$i$1$0$push;
import org.jruby.libraries.ThreadLibrary$SizedQueue$i$1$0$max_set;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.ThreadLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.ThreadLibrary$SizedQueue$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$ThreadLibrary$SizedQueue$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ThreadLibrary$SizedQueue$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.SizedQueue.class, "newInstance", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.SizedQueue.newInstance", "new");
        javaMethod = new ThreadLibrary$SizedQueue$i$1$0$max_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "max_set", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.SizedQueue.class, "max_set", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("max=", javaMethod);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new ThreadLibrary$SizedQueue$i$1$0$push(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "push", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.SizedQueue.class, "push", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("push", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new ThreadLibrary$SizedQueue$i$0$0$clear(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "clear", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.SizedQueue.class, "clear", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("clear", javaMethod);
        javaMethod = new ThreadLibrary$SizedQueue$i$0$1$pop(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "pop", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.SizedQueue.class, "pop", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("pop", javaMethod);
        cls.addMethodAtBootTimeOnly("deq", javaMethod);
        cls.addMethodAtBootTimeOnly("shift", javaMethod);
        javaMethod = new ThreadLibrary$SizedQueue$i$0$0$max(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "max", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.SizedQueue.class, "max", RubyNumeric.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("max", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.SizedQueue.max_set", "max=");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.SizedQueue.push", "push");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.SizedQueue.clear", "clear");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.SizedQueue.pop", "pop");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.SizedQueue.max", "max");
    }
}
