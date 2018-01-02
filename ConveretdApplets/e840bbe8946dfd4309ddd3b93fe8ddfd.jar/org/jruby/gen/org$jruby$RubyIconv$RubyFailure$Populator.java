// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyIconv$RubyFailure$i$0$0$success;
import org.jruby.RubyIconv$RubyFailure$i$0$0$failed;
import org.jruby.runtime.Block;
import org.jruby.RubyIconv$RubyFailure$i$0$2$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyIconv;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyIconv$RubyFailure$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyIconv$RubyFailure$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyIconv$RubyFailure$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.RubyFailure.class, "inspect", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyIconv$RubyFailure$i$0$2$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.RubyFailure.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyIconv$RubyFailure$i$0$0$failed(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "failed", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.RubyFailure.class, "failed", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("failed", javaMethod);
        javaMethod = new RubyIconv$RubyFailure$i$0$0$success(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "success", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.RubyFailure.class, "success", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("success", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyIconv.RubyFailure.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyIconv.RubyFailure.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyIconv.RubyFailure.failed", "failed");
        runtime.addBoundMethod("org.jruby.RubyIconv.RubyFailure.success", "success");
    }
}
