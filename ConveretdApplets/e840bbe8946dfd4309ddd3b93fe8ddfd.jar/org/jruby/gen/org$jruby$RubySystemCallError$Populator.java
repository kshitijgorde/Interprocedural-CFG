// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubySystemCallError$i$0$0$errno;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubySystemCallError;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubySystemCallError$i$0$2$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubySystemCallError$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubySystemCallError$i$0$2$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySystemCallError.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubySystemCallError$i$0$0$errno(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "errno", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySystemCallError.class, "errno", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("errno", javaMethod);
        runtime.addBoundMethod("org.jruby.RubySystemCallError.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubySystemCallError.errno", "errno");
    }
}
