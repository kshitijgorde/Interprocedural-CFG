// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.NativeException;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.NativeException$i$0$0$cause;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$NativeException$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new NativeException$i$0$0$cause(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "cause", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(NativeException.class, "cause", IRubyObject.class, new Class[] { Block.class }, false);
        cls.addMethodAtBootTimeOnly("cause", javaMethod);
        runtime.addBoundMethod("org.jruby.NativeException.cause", "cause");
    }
}
