// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.libraries.ThreadLibrary$Queue$i$0$0$shutdown;
import org.jruby.libraries.ThreadLibrary$Queue$i$0$0$num_waiting;
import org.jruby.libraries.ThreadLibrary$Queue$i$0$1$pop;
import org.jruby.libraries.ThreadLibrary$Queue$i$0$0$clear;
import org.jruby.RubyNumeric;
import org.jruby.libraries.ThreadLibrary$Queue$i$0$0$length;
import org.jruby.RubyBoolean;
import org.jruby.libraries.ThreadLibrary$Queue$i$0$0$empty_p;
import org.jruby.libraries.ThreadLibrary$Queue$i$1$0$push;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.ThreadLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.ThreadLibrary$Queue$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$ThreadLibrary$Queue$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ThreadLibrary$Queue$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "newInstance", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.newInstance", "new");
        javaMethod = new ThreadLibrary$Queue$i$1$0$push(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "push", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "push", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("push", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        cls.addMethodAtBootTimeOnly("enq", javaMethod);
        javaMethod = new ThreadLibrary$Queue$i$0$0$empty_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "empty_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "empty_p", RubyBoolean.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("empty?", javaMethod);
        javaMethod = new ThreadLibrary$Queue$i$0$0$length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "length", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "length", RubyNumeric.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new ThreadLibrary$Queue$i$0$0$clear(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "clear", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "clear", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("clear", javaMethod);
        javaMethod = new ThreadLibrary$Queue$i$0$1$pop(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "pop", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "pop", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("pop", javaMethod);
        cls.addMethodAtBootTimeOnly("deq", javaMethod);
        cls.addMethodAtBootTimeOnly("shift", javaMethod);
        javaMethod = new ThreadLibrary$Queue$i$0$0$num_waiting(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "num_waiting", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "num_waiting", RubyNumeric.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("num_waiting", javaMethod);
        javaMethod = new ThreadLibrary$Queue$i$0$0$shutdown(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "shutdown", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Queue.class, "shutdown", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("shutdown!", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.push", "push");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.empty_p", "empty?");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.length", "length");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.clear", "clear");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.pop", "pop");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.num_waiting", "num_waiting");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Queue.shutdown", "shutdown!");
    }
}
