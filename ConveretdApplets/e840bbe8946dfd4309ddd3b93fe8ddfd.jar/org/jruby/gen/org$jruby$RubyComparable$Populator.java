// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyComparable$s$1$0$op_equal19;
import org.jruby.RubyComparable$s$1$0$op_equal;
import org.jruby.CompatVersion;
import org.jruby.RubyComparable$s$1$0$op_le;
import org.jruby.RubyComparable$s$1$0$op_lt;
import org.jruby.RubyComparable$s$2$0$between_p;
import org.jruby.RubyComparable$s$1$0$op_ge;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyBoolean;
import org.jruby.RubyComparable;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyComparable$s$1$0$op_gt;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyComparable$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyComparable$s$1$0$op_gt(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_gt", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyComparable.class, "op_gt", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly(">", javaMethod);
        javaMethod = new RubyComparable$s$1$0$op_ge(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_ge", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyComparable.class, "op_ge", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly(">=", javaMethod);
        javaMethod = new RubyComparable$s$2$0$between_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "between_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyComparable.class, "between_p", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("between?", javaMethod);
        javaMethod = new RubyComparable$s$1$0$op_lt(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_lt", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyComparable.class, "op_lt", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("<", javaMethod);
        javaMethod = new RubyComparable$s$1$0$op_le(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_le", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyComparable.class, "op_le", RubyBoolean.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("<=", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyComparable.op_gt", ">");
        runtime.addBoundMethod("org.jruby.RubyComparable.op_ge", ">=");
        runtime.addBoundMethod("org.jruby.RubyComparable.between_p", "between?");
        runtime.addBoundMethod("org.jruby.RubyComparable.op_lt", "<");
        runtime.addBoundMethod("org.jruby.RubyComparable.op_le", "<=");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyComparable$s$1$0$op_equal(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_equal", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyComparable.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("==", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyComparable.op_equal", "==");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyComparable$s$1$0$op_equal19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_equal19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyComparable.class, "op_equal19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("==", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyComparable.op_equal19", "==");
        }
    }
}
