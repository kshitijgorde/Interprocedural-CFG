// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyInteger$i$0$0$numerator;
import org.jruby.RubyInteger$i$0$0$denominator;
import org.jruby.RubyInteger$i$chr19;
import org.jruby.RubyInteger$i$0$1$rationalize;
import org.jruby.RubyInteger$i$round19;
import org.jruby.RubyInteger$i$1$0$gcdlcm;
import org.jruby.RubyInteger$i$0$0$to_r;
import org.jruby.RubyInteger$i$1$0$lcm;
import org.jruby.RubyInteger$i$1$0$gcd;
import org.jruby.RubyString;
import org.jruby.RubyInteger$i$0$0$chr;
import org.jruby.RubyInteger$i$0$0$round;
import org.jruby.RubyInteger$i$0$0$even_p;
import org.jruby.RubyBoolean;
import org.jruby.RubyInteger$i$0$0$odd_p;
import org.jruby.RubyInteger$i$1$0$upto;
import org.jruby.RubyInteger$i$0$0$times;
import org.jruby.RubyInteger$i$0$0$succ;
import org.jruby.runtime.Block;
import org.jruby.RubyInteger$i$1$0$downto;
import org.jruby.RubyInteger$i$0$0$integer_p;
import org.jruby.RubyInteger$i$0$0$to_i;
import org.jruby.RubyInteger$i$0$0$ord;
import org.jruby.RubyInteger$i$0$0$pred;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyInteger;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyInteger$s$1$0$induced_from;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyInteger$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            final JavaMethod javaMethod = new RubyInteger$s$1$0$induced_from(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "induced_from", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "induced_from", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("induced_from", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyInteger.induced_from", "induced_from");
        }
        JavaMethod javaMethod = new RubyInteger$i$0$0$pred(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "pred", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "pred", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("pred", javaMethod);
        javaMethod = new RubyInteger$i$0$0$ord(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "ord", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "ord", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("ord", javaMethod);
        javaMethod = new RubyInteger$i$0$0$to_i(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_i", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "to_i", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_i", javaMethod);
        cls.addMethodAtBootTimeOnly("to_int", javaMethod);
        cls.addMethodAtBootTimeOnly("floor", javaMethod);
        cls.addMethodAtBootTimeOnly("ceil", javaMethod);
        cls.addMethodAtBootTimeOnly("truncate", javaMethod);
        javaMethod = new RubyInteger$i$0$0$integer_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "integer_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "integer_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("integer?", javaMethod);
        javaMethod = new RubyInteger$i$1$0$downto(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "downto", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "downto", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("downto", javaMethod);
        javaMethod = new RubyInteger$i$0$0$succ(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "succ", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "succ", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("succ", javaMethod);
        cls.addMethodAtBootTimeOnly("next", javaMethod);
        javaMethod = new RubyInteger$i$0$0$times(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "times", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "times", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("times", javaMethod);
        javaMethod = new RubyInteger$i$1$0$upto(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "upto", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "upto", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("upto", javaMethod);
        javaMethod = new RubyInteger$i$0$0$odd_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "odd_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "odd_p", RubyBoolean.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("odd?", javaMethod);
        javaMethod = new RubyInteger$i$0$0$even_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "even_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyInteger.class, "even_p", RubyBoolean.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("even?", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyInteger.pred", "pred");
        runtime.addBoundMethod("org.jruby.RubyInteger.ord", "ord");
        runtime.addBoundMethod("org.jruby.RubyInteger.to_i", "to_i");
        runtime.addBoundMethod("org.jruby.RubyInteger.integer_p", "integer?");
        runtime.addBoundMethod("org.jruby.RubyInteger.downto", "downto");
        runtime.addBoundMethod("org.jruby.RubyInteger.succ", "succ");
        runtime.addBoundMethod("org.jruby.RubyInteger.times", "times");
        runtime.addBoundMethod("org.jruby.RubyInteger.upto", "upto");
        runtime.addBoundMethod("org.jruby.RubyInteger.odd_p", "odd?");
        runtime.addBoundMethod("org.jruby.RubyInteger.even_p", "even?");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyInteger$i$0$0$round(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "round", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "round", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("round", javaMethod);
            javaMethod = new RubyInteger$i$0$0$chr(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "chr", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "chr", RubyString.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("chr", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyInteger.round", "round");
            runtime.addBoundMethod("org.jruby.RubyInteger.chr", "chr");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyInteger$i$1$0$gcd(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "gcd", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "gcd", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("gcd", javaMethod);
            javaMethod = new RubyInteger$i$1$0$lcm(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "lcm", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "lcm", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("lcm", javaMethod);
            javaMethod = new RubyInteger$i$0$0$to_r(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_r", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "to_r", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("to_r", javaMethod);
            javaMethod = new RubyInteger$i$1$0$gcdlcm(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "gcdlcm", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "gcdlcm", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("gcdlcm", javaMethod);
            javaMethod = new RubyInteger$i$round19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "round19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("round", javaMethod);
            javaMethod = new RubyInteger$i$0$1$rationalize(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "rationalize", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "rationalize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
            cls.addMethodAtBootTimeOnly("rationalize", javaMethod);
            javaMethod = new RubyInteger$i$chr19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "chr19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("chr", javaMethod);
            javaMethod = new RubyInteger$i$0$0$denominator(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "denominator", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "denominator", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("denominator", javaMethod);
            javaMethod = new RubyInteger$i$0$0$numerator(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "numerator", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyInteger.class, "numerator", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("numerator", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyInteger.gcd", "gcd");
            runtime.addBoundMethod("org.jruby.RubyInteger.lcm", "lcm");
            runtime.addBoundMethod("org.jruby.RubyInteger.to_r", "to_r");
            runtime.addBoundMethod("org.jruby.RubyInteger.gcdlcm", "gcdlcm");
            runtime.addBoundMethod("org.jruby.RubyInteger.round19", "round");
            runtime.addBoundMethod("org.jruby.RubyInteger.rationalize", "rationalize");
            runtime.addBoundMethod("org.jruby.RubyInteger.chr19", "chr");
            runtime.addBoundMethod("org.jruby.RubyInteger.denominator", "denominator");
            runtime.addBoundMethod("org.jruby.RubyInteger.numerator", "numerator");
        }
    }
}
