// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyBignum$i$1$0$divmod19;
import org.jruby.RubyBignum$i$1$0$op_mul19;
import org.jruby.RubyBignum$i$1$0$op_mod19;
import org.jruby.RubyBignum$i$1$0$op_pow19;
import org.jruby.RubyBignum$i$1$0$remainder19;
import org.jruby.RubyBignum$i$1$0$quo19;
import org.jruby.RubyBignum$i$1$0$op_and19;
import org.jruby.RubyBignum$i$1$0$op_or19;
import org.jruby.RubyBignum$i$1$0$op_xor19;
import org.jruby.RubyBignum$i$1$0$divmod;
import org.jruby.RubyBignum$i$1$0$op_mod;
import org.jruby.RubyBignum$i$1$0$remainder;
import org.jruby.RubyBignum$i$1$0$quo;
import org.jruby.RubyBignum$i$1$0$op_and;
import org.jruby.RubyBignum$i$1$0$op_or;
import org.jruby.RubyBignum$i$1$0$op_xor;
import org.jruby.CompatVersion;
import org.jruby.RubyBignum$i$1$0$op_mul;
import org.jruby.RubyBignum$i$0$0$size;
import org.jruby.RubyBignum$i$1$0$op_lshift;
import org.jruby.RubyBignum$i$1$0$op_idiv;
import org.jruby.RubyBignum$i$1$0$op_rshift;
import org.jruby.RubyBignum$i$1$0$eql_p;
import org.jruby.RubyBignum$i$1$0$coerce;
import org.jruby.RubyBignum$i$1$0$op_minus;
import org.jruby.RubyBignum$i$1$0$op_cmp;
import org.jruby.RubyBignum$i$0$0$hash;
import org.jruby.RubyBignum$i$1$0$op_pow;
import org.jruby.RubyBignum$i$to_s;
import org.jruby.RubyBignum$i$1$0$op_equal;
import org.jruby.RubyFixnum;
import org.jruby.RubyBignum$i$1$0$op_aref;
import org.jruby.RubyBignum$i$1$0$op_plus;
import org.jruby.RubyBignum$i$0$0$op_uminus;
import org.jruby.RubyBignum$i$0$0$to_f;
import org.jruby.RubyBignum$i$0$0$abs;
import org.jruby.RubyBignum$i$0$0$op_neg;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBignum;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBignum$i$1$0$op_div;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyBignum$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyBignum$i$1$0$op_div(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_div", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_div", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("/", javaMethod);
        javaMethod = new RubyBignum$i$0$0$op_neg(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "op_neg", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_neg", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("~", javaMethod);
        javaMethod = new RubyBignum$i$0$0$abs(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "abs", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "abs", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("abs", javaMethod);
        javaMethod = new RubyBignum$i$0$0$to_f(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_f", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "to_f", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_f", javaMethod);
        javaMethod = new RubyBignum$i$0$0$op_uminus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "op_uminus", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_uminus", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("-@", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_plus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_plus", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_plus", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("+", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_aref", RubyFixnum.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_equal", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new RubyBignum$i$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_pow(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_pow", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_pow", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("**", javaMethod);
        cls.addMethodAtBootTimeOnly("power", javaMethod);
        javaMethod = new RubyBignum$i$0$0$hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "hash", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("hash", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_cmp(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_cmp", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_cmp", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("<=>", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_minus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_minus", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_minus", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("-", javaMethod);
        javaMethod = new RubyBignum$i$1$0$coerce(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "coerce", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "coerce", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("coerce", javaMethod);
        javaMethod = new RubyBignum$i$1$0$eql_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "eql_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "eql_p", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        cls.addMethodAtBootTimeOnly("===", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_rshift(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_rshift", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_rshift", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly(">>", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_idiv(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_idiv", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_idiv", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("div", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_lshift(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_lshift", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_lshift", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new RubyBignum$i$0$0$size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "size", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new RubyBignum$i$1$0$op_mul(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_mul", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBignum.class, "op_mul", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("*", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyBignum.op_div", "/");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_neg", "~");
        runtime.addBoundMethod("org.jruby.RubyBignum.abs", "abs");
        runtime.addBoundMethod("org.jruby.RubyBignum.to_f", "to_f");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_uminus", "-@");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_plus", "+");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_aref", "[]");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyBignum.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_pow", "**");
        runtime.addBoundMethod("org.jruby.RubyBignum.hash", "hash");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_cmp", "<=>");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_minus", "-");
        runtime.addBoundMethod("org.jruby.RubyBignum.coerce", "coerce");
        runtime.addBoundMethod("org.jruby.RubyBignum.eql_p", "eql?");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_rshift", ">>");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_idiv", "div");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_lshift", "<<");
        runtime.addBoundMethod("org.jruby.RubyBignum.size", "size");
        runtime.addBoundMethod("org.jruby.RubyBignum.op_mul", "*");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyBignum$i$1$0$op_xor(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_xor", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_xor", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("^", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_or(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_or", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_or", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("|", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_and(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_and", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_and", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("&", javaMethod);
            javaMethod = new RubyBignum$i$1$0$quo(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "quo", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "quo", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("quo", javaMethod);
            javaMethod = new RubyBignum$i$1$0$remainder(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "remainder", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "remainder", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("remainder", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_mod(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_mod", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_mod", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("%", javaMethod);
            cls.addMethodAtBootTimeOnly("modulo", javaMethod);
            javaMethod = new RubyBignum$i$1$0$divmod(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "divmod", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "divmod", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("divmod", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyBignum.op_xor", "^");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_or", "|");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_and", "&");
            runtime.addBoundMethod("org.jruby.RubyBignum.quo", "quo");
            runtime.addBoundMethod("org.jruby.RubyBignum.remainder", "remainder");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_mod", "%");
            runtime.addBoundMethod("org.jruby.RubyBignum.divmod", "divmod");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyBignum$i$1$0$op_xor19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_xor19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_xor19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("^", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_or19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_or19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_or19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("|", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_and19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_and19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_and19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("&", javaMethod);
            javaMethod = new RubyBignum$i$1$0$quo19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "quo19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "quo19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("quo", javaMethod);
            javaMethod = new RubyBignum$i$1$0$remainder19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "remainder19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "remainder19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("remainder", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_pow19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_pow19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_pow19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("**", javaMethod);
            cls.addMethodAtBootTimeOnly("power", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_mod19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_mod19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_mod19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("%", javaMethod);
            cls.addMethodAtBootTimeOnly("modulo", javaMethod);
            javaMethod = new RubyBignum$i$1$0$op_mul19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_mul19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "op_mul19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("*", javaMethod);
            javaMethod = new RubyBignum$i$1$0$divmod19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "divmod19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBignum.class, "divmod19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("divmod", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyBignum.op_xor19", "^");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_or19", "|");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_and19", "&");
            runtime.addBoundMethod("org.jruby.RubyBignum.quo19", "quo");
            runtime.addBoundMethod("org.jruby.RubyBignum.remainder19", "remainder");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_pow19", "**");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_mod19", "%");
            runtime.addBoundMethod("org.jruby.RubyBignum.op_mul19", "*");
            runtime.addBoundMethod("org.jruby.RubyBignum.divmod19", "divmod");
        }
    }
}
