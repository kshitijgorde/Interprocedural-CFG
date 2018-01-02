// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyNil$i$0$0$nil_p;
import org.jruby.RubyNil$s$0$1$rationalize;
import org.jruby.RubyNil$s$0$0$to_c;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyNil$s$0$0$to_r;
import org.jruby.CompatVersion;
import org.jruby.RubyNil$s$0$0$to_s;
import org.jruby.RubyArray;
import org.jruby.RubyNil$s$0$0$to_a;
import org.jruby.RubyFloat;
import org.jruby.RubyNil$s$0$0$to_f;
import org.jruby.RubyNil$s$1$0$op_and;
import org.jruby.RubyClass;
import org.jruby.RubyNil$s$0$0$type;
import org.jruby.RubyFixnum;
import org.jruby.RubyNil$s$0$0$to_i;
import org.jruby.RubyNil$s$1$0$op_or;
import org.jruby.RubyBoolean;
import org.jruby.RubyNil$s$1$0$op_xor;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyString;
import org.jruby.RubyNil;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyNil$s$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyNil$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyNil$s$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "inspect", RubyString.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyNil$s$1$0$op_xor(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_xor", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "op_xor", RubyBoolean.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("^", javaMethod);
        javaMethod = new RubyNil$s$1$0$op_or(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_or", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "op_or", RubyBoolean.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("|", javaMethod);
        javaMethod = new RubyNil$s$0$0$to_i(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_i", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "to_i", RubyFixnum.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_i", javaMethod);
        javaMethod = new RubyNil$s$0$0$type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "type", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "type", RubyClass.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("type", javaMethod);
        javaMethod = new RubyNil$s$1$0$op_and(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_and", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "op_and", RubyBoolean.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("&", javaMethod);
        javaMethod = new RubyNil$s$0$0$to_f(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_f", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "to_f", RubyFloat.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_f", javaMethod);
        javaMethod = new RubyNil$s$0$0$to_a(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_a", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "to_a", RubyArray.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_a", javaMethod);
        javaMethod = new RubyNil$s$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "to_s", RubyString.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNil.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyNil.op_xor", "^");
        runtime.addBoundMethod("org.jruby.RubyNil.op_or", "|");
        runtime.addBoundMethod("org.jruby.RubyNil.to_i", "to_i");
        runtime.addBoundMethod("org.jruby.RubyNil.type", "type");
        runtime.addBoundMethod("org.jruby.RubyNil.op_and", "&");
        runtime.addBoundMethod("org.jruby.RubyNil.to_f", "to_f");
        runtime.addBoundMethod("org.jruby.RubyNil.to_a", "to_a");
        runtime.addBoundMethod("org.jruby.RubyNil.to_s", "to_s");
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyNil$s$0$0$to_r(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_r", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNil.class, "to_r", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("to_r", javaMethod);
            javaMethod = new RubyNil$s$0$0$to_c(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "to_c", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNil.class, "to_c", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("to_c", javaMethod);
            javaMethod = new RubyNil$s$0$1$rationalize(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "rationalize", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyNil.class, "rationalize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
            cls.addMethodAtBootTimeOnly("rationalize", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyNil.to_r", "to_r");
            runtime.addBoundMethod("org.jruby.RubyNil.to_c", "to_c");
            runtime.addBoundMethod("org.jruby.RubyNil.rationalize", "rationalize");
        }
        javaMethod = new RubyNil$i$0$0$nil_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "nil_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNil.class, "nil_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("nil?", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNil.nil_p", "nil?");
    }
}
