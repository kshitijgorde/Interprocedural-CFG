// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubySystemExit$i$0$0$status;
import org.jruby.RubySystemExit$i$0$0$success_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubySystemExit;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubySystemExit$i$0$2$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubySystemExit$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubySystemExit$i$0$2$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySystemExit.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubySystemExit$i$0$0$success_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "success_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySystemExit.class, "success_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("success?", javaMethod);
        javaMethod = new RubySystemExit$i$0$0$status(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "status", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySystemExit.class, "status", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("status", javaMethod);
        runtime.addBoundMethod("org.jruby.RubySystemExit.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubySystemExit.success_p", "success?");
        runtime.addBoundMethod("org.jruby.RubySystemExit.status", "status");
    }
}
