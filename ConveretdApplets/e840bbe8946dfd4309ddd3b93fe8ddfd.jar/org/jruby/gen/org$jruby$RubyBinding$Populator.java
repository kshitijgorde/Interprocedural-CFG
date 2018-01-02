// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyBinding$i$1$0$initialize_copy;
import org.jruby.RubyBinding$i$0$0$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBinding;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBinding$i$0$2$eval;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyBinding$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyBinding$i$0$2$eval(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "eval", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBinding.class, "eval", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("eval", javaMethod);
        javaMethod = new RubyBinding$i$0$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBinding.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyBinding$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBinding.class, "initialize_copy", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyBinding.eval", "eval");
        runtime.addBoundMethod("org.jruby.RubyBinding.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyBinding.initialize_copy", "initialize_copy");
    }
}
