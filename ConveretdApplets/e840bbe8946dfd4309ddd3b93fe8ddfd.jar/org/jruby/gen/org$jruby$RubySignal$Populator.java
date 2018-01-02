// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubySignal$s$2$0$__jtrap_kernel;
import org.jruby.runtime.Block;
import org.jruby.RubySignal$s$0$1$trap;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubySignal;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubySignal$s$0$0$list;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubySignal$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubySignal$s$0$0$list(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "list", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySignal.class, "list", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("list", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("list", moduleMethod);
        javaMethod = new RubySignal$s$0$1$trap(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "trap", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySignal.class, "trap", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("trap", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("trap", moduleMethod);
        javaMethod = new RubySignal$s$2$0$__jtrap_kernel(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "__jtrap_kernel", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySignal.class, "__jtrap_kernel", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("__jtrap_kernel", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("__jtrap_kernel", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubySignal.list", "list");
        runtime.addBoundMethod("org.jruby.RubySignal.trap", "trap");
        runtime.addBoundMethod("org.jruby.RubySignal.__jtrap_kernel", "__jtrap_kernel");
    }
}
