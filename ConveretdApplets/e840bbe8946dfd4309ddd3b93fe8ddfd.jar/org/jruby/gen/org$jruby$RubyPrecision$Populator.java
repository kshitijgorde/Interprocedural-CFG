// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyPrecision$s$0$0$prec_f;
import org.jruby.RubyPrecision$s$1$0$prec;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyPrecision$s$0$0$prec_i;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyPrecision;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyPrecision$s$1$0$append_features;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyPrecision$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyPrecision$s$1$0$append_features(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "append_features", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyPrecision.class, "append_features", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("append_features", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("append_features", moduleMethod);
        javaMethod = new RubyPrecision$s$0$0$prec_i(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "prec_i", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyPrecision.class, "prec_i", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("prec_i", javaMethod);
        javaMethod = new RubyPrecision$s$1$0$prec(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "prec", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyPrecision.class, "prec", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("prec", javaMethod);
        javaMethod = new RubyPrecision$s$0$0$prec_f(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "prec_f", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyPrecision.class, "prec_f", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("prec_f", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyPrecision.append_features", "append_features");
        runtime.addBoundMethod("org.jruby.RubyPrecision.prec_i", "prec_i");
        runtime.addBoundMethod("org.jruby.RubyPrecision.prec", "prec");
        runtime.addBoundMethod("org.jruby.RubyPrecision.prec_f", "prec_f");
    }
}
