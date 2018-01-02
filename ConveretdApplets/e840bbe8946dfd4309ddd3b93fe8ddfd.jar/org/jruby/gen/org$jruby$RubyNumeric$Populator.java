// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyNumeric$i$1$0$divmod19;
import org.jruby.RubyNumeric$i$0$0$abs2;
import org.jruby.RubyNumeric$i$0$0$denominator;
import org.jruby.RubyNumeric$i$0$0$real;
import org.jruby.RubyNumeric$i$0$0$polar;
import org.jruby.RubyNumeric$i$1$0$quo_19;
import org.jruby.RubyNumeric$i$0$0$rect;
import org.jruby.RubyNumeric$i$0$0$conjugate;
import org.jruby.RubyNumeric$i$0$0$image;
import org.jruby.RubyNumeric$i$0$0$to_c;
import org.jruby.RubyNumeric$i$1$0$div19;
import org.jruby.RubyNumeric$i$0$0$scalar_p;
import org.jruby.RubyNumeric$i$0$0$num_imaginary;
import org.jruby.RubyNumeric$i$0$0$numerator;
import org.jruby.RubyNumeric$i$1$0$modulo19;
import org.jruby.RubyNumeric$i$1$0$fdiv;
import org.jruby.RubyNumeric$i$0$0$magnitude;
import org.jruby.RubyNumeric$i$0$0$arg;
import org.jruby.RubyNumeric$i$1$0$divmod;
import org.jruby.RubyNumeric$i$1$0$modulo;
import org.jruby.RubyNumeric$i$1$0$quo;
import org.jruby.RubyNumeric$i$1$0$div;
import org.jruby.CompatVersion;
import org.jruby.RubyNumeric$i$1$0$sadded;
import org.jruby.RubyNumeric$i$step;
import org.jruby.RubyNumeric$i$0$0$nonzero_p;
import org.jruby.RubyNumeric$i$1$0$remainder;
import org.jruby.RubyNumeric$i$1$0$initialize_copy;
import org.jruby.RubyNumeric$i$0$0$truncate;
import org.jruby.RubyNumeric$i$0$0$integer_p;
import org.jruby.RubyNumeric$i$0$0$op_uplus;
import org.jruby.RubyNumeric$i$1$0$eql_p;
import org.jruby.RubyNumeric$i$1$0$coerce;
import org.jruby.RubyNumeric$i$0$0$to_int;
import org.jruby.RubyNumeric$i$1$0$op_cmp;
import org.jruby.RubyNumeric$i$0$0$ceil;
import org.jruby.RubyNumeric$i$0$0$zero_p;
import org.jruby.RubyNumeric$i$0$0$floor;
import org.jruby.RubyNumeric$i$0$0$round;
import org.jruby.RubyNumeric$i$0$0$op_uminus;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyNumeric;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyNumeric$i$0$0$abs;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyNumeric$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyNumeric$i$0$0$abs(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "abs", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "abs", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("abs", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$op_uminus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "op_uminus", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "op_uminus", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("-@", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$round(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "round", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "round", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("round", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$floor(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "floor", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "floor", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("floor", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$zero_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "zero_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "zero_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("zero?", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$ceil(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "ceil", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "ceil", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("ceil", javaMethod);
        javaMethod = new RubyNumeric$i$1$0$op_cmp(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_cmp", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "op_cmp", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("<=>", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$to_int(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_int", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "to_int", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_int", javaMethod);
        javaMethod = new RubyNumeric$i$1$0$coerce(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "coerce", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "coerce", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("coerce", javaMethod);
        javaMethod = new RubyNumeric$i$1$0$eql_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "eql_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "eql_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$op_uplus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "op_uplus", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "op_uplus", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("+@", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$integer_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "integer_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "integer_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("integer?", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$truncate(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "truncate", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "truncate", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("truncate", javaMethod);
        javaMethod = new RubyNumeric$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "initialize_copy", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        javaMethod = new RubyNumeric$i$1$0$remainder(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "remainder", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "remainder", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("remainder", javaMethod);
        javaMethod = new RubyNumeric$i$0$0$nonzero_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "nonzero_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "nonzero_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("nonzero?", javaMethod);
        javaMethod = new RubyNumeric$i$step(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "step", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("step", javaMethod);
        javaMethod = new RubyNumeric$i$1$0$sadded(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "sadded", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNumeric.class, "sadded", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("singleton_method_added", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNumeric.abs", "abs");
        runtime.addBoundMethod("org.jruby.RubyNumeric.op_uminus", "-@");
        runtime.addBoundMethod("org.jruby.RubyNumeric.round", "round");
        runtime.addBoundMethod("org.jruby.RubyNumeric.floor", "floor");
        runtime.addBoundMethod("org.jruby.RubyNumeric.zero_p", "zero?");
        runtime.addBoundMethod("org.jruby.RubyNumeric.ceil", "ceil");
        runtime.addBoundMethod("org.jruby.RubyNumeric.op_cmp", "<=>");
        runtime.addBoundMethod("org.jruby.RubyNumeric.to_int", "to_int");
        runtime.addBoundMethod("org.jruby.RubyNumeric.coerce", "coerce");
        runtime.addBoundMethod("org.jruby.RubyNumeric.eql_p", "eql?");
        runtime.addBoundMethod("org.jruby.RubyNumeric.op_uplus", "+@");
        runtime.addBoundMethod("org.jruby.RubyNumeric.integer_p", "integer?");
        runtime.addBoundMethod("org.jruby.RubyNumeric.truncate", "truncate");
        runtime.addBoundMethod("org.jruby.RubyNumeric.initialize_copy", "initialize_copy");
        runtime.addBoundMethod("org.jruby.RubyNumeric.remainder", "remainder");
        runtime.addBoundMethod("org.jruby.RubyNumeric.nonzero_p", "nonzero?");
        runtime.addBoundMethod("org.jruby.RubyNumeric.step", "step");
        runtime.addBoundMethod("org.jruby.RubyNumeric.sadded", "singleton_method_added");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyNumeric$i$1$0$div(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "div", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "div", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("div", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$quo(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "quo", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "quo", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("quo", javaMethod);
            cls.addMethodAtBootTimeOnly("fdiv", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$modulo(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "modulo", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "modulo", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("modulo", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$divmod(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "divmod", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "divmod", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("divmod", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyNumeric.div", "div");
            runtime.addBoundMethod("org.jruby.RubyNumeric.quo", "quo");
            runtime.addBoundMethod("org.jruby.RubyNumeric.modulo", "modulo");
            runtime.addBoundMethod("org.jruby.RubyNumeric.divmod", "divmod");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyNumeric$i$0$0$arg(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "arg", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "arg", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("arg", javaMethod);
            cls.addMethodAtBootTimeOnly("angle", javaMethod);
            cls.addMethodAtBootTimeOnly("phase", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$magnitude(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "magnitude", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "magnitude", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("magnitude", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$fdiv(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "fdiv", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "fdiv", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("fdiv", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$modulo19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "modulo19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "modulo19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("modulo", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$numerator(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "numerator", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "numerator", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("numerator", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$num_imaginary(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "num_imaginary", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "num_imaginary", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("i", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$scalar_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "scalar_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "scalar_p", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("real?", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$div19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "div19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "div19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("div", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$to_c(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_c", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "to_c", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("to_c", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$image(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "image", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "image", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("imaginary", javaMethod);
            cls.addMethodAtBootTimeOnly("imag", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$conjugate(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "conjugate", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "conjugate", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("conjugate", javaMethod);
            cls.addMethodAtBootTimeOnly("conj", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$rect(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "rect", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "rect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("rectangular", javaMethod);
            cls.addMethodAtBootTimeOnly("rect", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$quo_19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "quo_19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "quo_19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("quo", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$polar(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "polar", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "polar", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("polar", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$real(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "real", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "real", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("real", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$denominator(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "denominator", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "denominator", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("denominator", javaMethod);
            javaMethod = new RubyNumeric$i$0$0$abs2(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "abs2", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "abs2", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("abs2", javaMethod);
            javaMethod = new RubyNumeric$i$1$0$divmod19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "divmod19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNumeric.class, "divmod19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("divmod", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyNumeric.arg", "arg");
            runtime.addBoundMethod("org.jruby.RubyNumeric.magnitude", "magnitude");
            runtime.addBoundMethod("org.jruby.RubyNumeric.fdiv", "fdiv");
            runtime.addBoundMethod("org.jruby.RubyNumeric.modulo19", "modulo");
            runtime.addBoundMethod("org.jruby.RubyNumeric.numerator", "numerator");
            runtime.addBoundMethod("org.jruby.RubyNumeric.num_imaginary", "i");
            runtime.addBoundMethod("org.jruby.RubyNumeric.scalar_p", "real?");
            runtime.addBoundMethod("org.jruby.RubyNumeric.div19", "div");
            runtime.addBoundMethod("org.jruby.RubyNumeric.to_c", "to_c");
            runtime.addBoundMethod("org.jruby.RubyNumeric.image", "imaginary");
            runtime.addBoundMethod("org.jruby.RubyNumeric.conjugate", "conjugate");
            runtime.addBoundMethod("org.jruby.RubyNumeric.rect", "rectangular");
            runtime.addBoundMethod("org.jruby.RubyNumeric.quo_19", "quo");
            runtime.addBoundMethod("org.jruby.RubyNumeric.polar", "polar");
            runtime.addBoundMethod("org.jruby.RubyNumeric.real", "real");
            runtime.addBoundMethod("org.jruby.RubyNumeric.denominator", "denominator");
            runtime.addBoundMethod("org.jruby.RubyNumeric.abs2", "abs2");
            runtime.addBoundMethod("org.jruby.RubyNumeric.divmod19", "divmod");
        }
    }
}
