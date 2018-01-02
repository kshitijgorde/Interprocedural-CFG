// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyJRuby$JRubyExtensions$s$2$0$steal_method;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$JRubyExtensions$s$0$0$steal_methods;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$JRubyExtensions$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyJRuby$JRubyExtensions$s$0$0$steal_methods(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "steal_methods", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExtensions.class, "steal_methods", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("steal_methods", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("steal_methods", moduleMethod);
        javaMethod = new RubyJRuby$JRubyExtensions$s$2$0$steal_method(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "steal_method", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExtensions.class, "steal_method", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("steal_method", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("steal_method", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExtensions.steal_methods", "steal_methods");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExtensions.steal_method", "steal_method");
    }
}
