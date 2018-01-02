// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyLocalJumpError$i$0$0$exit_value;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyLocalJumpError;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyLocalJumpError$i$0$0$reason;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyLocalJumpError$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyLocalJumpError$i$0$0$reason(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "reason", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyLocalJumpError.class, "reason", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("reason", javaMethod);
        javaMethod = new RubyLocalJumpError$i$0$0$exit_value(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "exit_value", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyLocalJumpError.class, "exit_value", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("exit_value", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyLocalJumpError.reason", "reason");
        runtime.addBoundMethod("org.jruby.RubyLocalJumpError.exit_value", "exit_value");
    }
}
