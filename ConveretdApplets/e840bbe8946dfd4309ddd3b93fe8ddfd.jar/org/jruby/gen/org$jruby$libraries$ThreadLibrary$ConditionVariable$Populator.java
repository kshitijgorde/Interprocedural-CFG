// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.libraries.ThreadLibrary$ConditionVariable$i$0$1$wait_ruby;
import org.jruby.libraries.ThreadLibrary$ConditionVariable$i$0$0$broadcast;
import org.jruby.libraries.ThreadLibrary$ConditionVariable$i$0$0$signal;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.libraries.ThreadLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.ThreadLibrary$ConditionVariable$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$ThreadLibrary$ConditionVariable$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ThreadLibrary$ConditionVariable$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.ConditionVariable.class, "newInstance", ThreadLibrary.ConditionVariable.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.ConditionVariable.newInstance", "new");
        javaMethod = new ThreadLibrary$ConditionVariable$i$0$0$signal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "signal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.ConditionVariable.class, "signal", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("signal", javaMethod);
        javaMethod = new ThreadLibrary$ConditionVariable$i$0$0$broadcast(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "broadcast", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.ConditionVariable.class, "broadcast", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("broadcast", javaMethod);
        javaMethod = new ThreadLibrary$ConditionVariable$i$0$1$wait_ruby(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "wait_ruby", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.ConditionVariable.class, "wait_ruby", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("wait", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.ConditionVariable.signal", "signal");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.ConditionVariable.broadcast", "broadcast");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.ConditionVariable.wait_ruby", "wait");
    }
}
