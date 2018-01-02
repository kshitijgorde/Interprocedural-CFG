// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyContinuation;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyContinuation$i$0$0$call;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyContinuation$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new RubyContinuation$i$0$0$call(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "call", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyContinuation.class, "call", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("call", javaMethod);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyContinuation.call", "call");
    }
}
