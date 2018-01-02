// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.libraries.IOWaitLibrary$s$0$0$io_wait;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.IOWaitLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.IOWaitLibrary$s$0$0$ready;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$IOWaitLibrary$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new IOWaitLibrary$s$0$0$ready(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "ready", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(IOWaitLibrary.class, "ready", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("ready?", javaMethod);
        javaMethod = new IOWaitLibrary$s$0$0$io_wait(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "io_wait", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(IOWaitLibrary.class, "io_wait", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("io_wait", javaMethod);
        runtime.addBoundMethod("org.jruby.libraries.IOWaitLibrary.ready", "ready?");
        runtime.addBoundMethod("org.jruby.libraries.IOWaitLibrary.io_wait", "io_wait");
    }
}
