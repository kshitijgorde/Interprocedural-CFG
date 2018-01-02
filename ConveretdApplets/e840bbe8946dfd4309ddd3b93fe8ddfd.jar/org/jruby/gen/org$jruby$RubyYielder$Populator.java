// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyYielder$i$0$0$yield;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyYielder;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyYielder$i$0$0$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyYielder$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyYielder$i$0$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyYielder.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyYielder$i$0$0$yield(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "yield", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyYielder.class, "yield", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("yield", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyYielder.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyYielder.yield", "yield");
    }
}
