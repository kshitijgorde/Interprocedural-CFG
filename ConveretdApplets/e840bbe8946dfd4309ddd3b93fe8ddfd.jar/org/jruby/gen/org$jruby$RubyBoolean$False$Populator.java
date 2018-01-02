// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyBoolean$False$s$0$0$false_to_s;
import org.jruby.RubyBoolean$False$s$1$0$false_and;
import org.jruby.RubyBoolean$False$s$1$0$false_or;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBoolean;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBoolean$False$s$1$0$false_xor;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyBoolean$False$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyBoolean$False$s$1$0$false_xor(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "false_xor", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.False.class, "false_xor", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("^", javaMethod);
        javaMethod = new RubyBoolean$False$s$1$0$false_or(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "false_or", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.False.class, "false_or", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("|", javaMethod);
        javaMethod = new RubyBoolean$False$s$1$0$false_and(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "false_and", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.False.class, "false_and", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("&", javaMethod);
        javaMethod = new RubyBoolean$False$s$0$0$false_to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "false_to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBoolean.False.class, "false_to_s", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyBoolean.False.false_xor", "^");
        runtime.addBoundMethod("org.jruby.RubyBoolean.False.false_or", "|");
        runtime.addBoundMethod("org.jruby.RubyBoolean.False.false_and", "&");
        runtime.addBoundMethod("org.jruby.RubyBoolean.False.false_to_s", "to_s");
    }
}
