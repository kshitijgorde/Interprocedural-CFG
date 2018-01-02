// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.jffi.VariadicInvoker$i$2$0$invoke;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.ffi.jffi.VariadicInvoker;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.jffi.VariadicInvoker$s$0$1$newInvoker;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$jffi$VariadicInvoker$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new VariadicInvoker$s$0$1$newInvoker(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInvoker", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(VariadicInvoker.class, "newInvoker", VariadicInvoker.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("__new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.VariadicInvoker.newInvoker", "__new");
        javaMethod = new VariadicInvoker$i$2$0$invoke(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "invoke", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(VariadicInvoker.class, "invoke", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("invoke", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.VariadicInvoker.invoke", "invoke");
    }
}
