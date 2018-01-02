// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyNoMethodError$i$0$0$args;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyNoMethodError;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyNoMethodError$i$0$3$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyNoMethodError$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyNoMethodError$i$0$3$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNoMethodError.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyNoMethodError$i$0$0$args(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "args", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNoMethodError.class, "args", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("args", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNoMethodError.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyNoMethodError.args", "args");
    }
}
