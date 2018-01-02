// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyNKF$s$1$0$guess1;
import org.jruby.RubyNKF$s$1$0$guess;
import org.jruby.RubyNKF$s$2$0$nkf;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyNKF;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyNKF$s$1$0$guess2;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyNKF$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyNKF$s$1$0$guess2(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "guess2", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNKF.class, "guess2", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("guess2", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("guess2", moduleMethod);
        javaMethod = new RubyNKF$s$2$0$nkf(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "nkf", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNKF.class, "nkf", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("nkf", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("nkf", moduleMethod);
        javaMethod = new RubyNKF$s$1$0$guess(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "guess", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNKF.class, "guess", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("guess", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("guess", moduleMethod);
        javaMethod = new RubyNKF$s$1$0$guess1(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "guess1", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNKF.class, "guess1", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("guess1", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("guess1", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyNKF.guess2", "guess2");
        runtime.addBoundMethod("org.jruby.RubyNKF.nkf", "nkf");
        runtime.addBoundMethod("org.jruby.RubyNKF.guess", "guess");
        runtime.addBoundMethod("org.jruby.RubyNKF.guess1", "guess1");
    }
}
