// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule$ModuleKernelMethods$s$1$0$autoload_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyModule$ModuleKernelMethods$s$2$0$autoload;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyModule$ModuleKernelMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyModule$ModuleKernelMethods$s$2$0$autoload(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "autoload", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyModule.ModuleKernelMethods.class, "autoload", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("autoload", javaMethod);
        javaMethod = new RubyModule$ModuleKernelMethods$s$1$0$autoload_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "autoload_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyModule.ModuleKernelMethods.class, "autoload_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("autoload?", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyModule.ModuleKernelMethods.autoload", "autoload");
        runtime.addBoundMethod("org.jruby.RubyModule.ModuleKernelMethods.autoload_p", "autoload?");
    }
}
