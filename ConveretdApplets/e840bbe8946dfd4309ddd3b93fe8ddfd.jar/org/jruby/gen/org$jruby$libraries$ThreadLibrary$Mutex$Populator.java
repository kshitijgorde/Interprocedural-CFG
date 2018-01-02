// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.libraries.ThreadLibrary$Mutex$i$sleep;
import org.jruby.libraries.ThreadLibrary$Mutex$i$0$0$unlock19;
import org.jruby.libraries.ThreadLibrary$Mutex$i$0$0$unlock;
import org.jruby.CompatVersion;
import org.jruby.libraries.ThreadLibrary$Mutex$i$0$0$locked_p;
import org.jruby.libraries.ThreadLibrary$Mutex$i$0$0$synchronize;
import org.jruby.RubyBoolean;
import org.jruby.libraries.ThreadLibrary$Mutex$i$0$0$try_lock;
import org.jruby.libraries.ThreadLibrary$Mutex$i$0$0$lock;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.libraries.ThreadLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.ThreadLibrary$Mutex$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$ThreadLibrary$Mutex$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ThreadLibrary$Mutex$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "newInstance", ThreadLibrary.Mutex.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.newInstance", "new");
        javaMethod = new ThreadLibrary$Mutex$i$0$0$lock(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "lock", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "lock", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("lock", javaMethod);
        javaMethod = new ThreadLibrary$Mutex$i$0$0$try_lock(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "try_lock", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "try_lock", RubyBoolean.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("try_lock", javaMethod);
        javaMethod = new ThreadLibrary$Mutex$i$0$0$synchronize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "synchronize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "synchronize", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("synchronize", javaMethod);
        javaMethod = new ThreadLibrary$Mutex$i$0$0$locked_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "locked_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "locked_p", RubyBoolean.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("locked?", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.lock", "lock");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.try_lock", "try_lock");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.synchronize", "synchronize");
        runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.locked_p", "locked?");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new ThreadLibrary$Mutex$i$0$0$unlock(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "unlock", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "unlock", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("unlock", javaMethod);
            runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.unlock", "unlock");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new ThreadLibrary$Mutex$i$0$0$unlock19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "unlock19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(ThreadLibrary.Mutex.class, "unlock19", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("unlock", javaMethod);
            javaMethod = new ThreadLibrary$Mutex$i$sleep(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "sleep", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("sleep", javaMethod);
            runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.unlock19", "unlock");
            runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.Mutex.sleep", "sleep");
        }
    }
}
