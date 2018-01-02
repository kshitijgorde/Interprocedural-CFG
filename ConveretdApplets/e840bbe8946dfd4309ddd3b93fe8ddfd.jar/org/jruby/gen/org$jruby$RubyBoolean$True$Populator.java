// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyBoolean$True$s$0$0$true_to_s;
import org.jruby.RubyBoolean$True$s$1$0$true_and;
import org.jruby.RubyBoolean$True$s$1$0$true_or;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBoolean;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBoolean$True$s$1$0$true_xor;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyBoolean$True$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyBoolean$True$s$1$0$true_xor(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "true_xor", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.True.class, "true_xor", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("^", javaMethod);
        javaMethod = new RubyBoolean$True$s$1$0$true_or(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "true_or", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.True.class, "true_or", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("|", javaMethod);
        javaMethod = new RubyBoolean$True$s$1$0$true_and(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "true_and", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.True.class, "true_and", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("&", javaMethod);
        javaMethod = new RubyBoolean$True$s$0$0$true_to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "true_to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.True.class, "true_to_s", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyBoolean.True.true_xor", "^");
        runtime.addBoundMethod("org.jruby.RubyBoolean.True.true_or", "|");
        runtime.addBoundMethod("org.jruby.RubyBoolean.True.true_and", "&");
        runtime.addBoundMethod("org.jruby.RubyBoolean.True.true_to_s", "to_s");
    }
}
