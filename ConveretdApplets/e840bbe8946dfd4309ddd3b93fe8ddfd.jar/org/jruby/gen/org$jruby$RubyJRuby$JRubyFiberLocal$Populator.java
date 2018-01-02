// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$JRubyFiberLocal$i$1$0$withValue;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$JRubyFiberLocal$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new RubyJRuby$JRubyFiberLocal$i$1$0$withValue(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "withValue", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyFiberLocal.class, "withValue", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("with_value", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyFiberLocal.withValue", "with_value");
    }
}
