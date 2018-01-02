// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyMarshal$s$0$1$load;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyMarshal;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyMarshal$s$0$2$dump;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyMarshal$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyMarshal$s$0$2$dump(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "dump", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMarshal.class, "dump", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("dump", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("dump", moduleMethod);
        javaMethod = new RubyMarshal$s$0$1$load(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "load", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyMarshal.class, "load", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("load", javaMethod);
        cls.addMethodAtBootTimeOnly("restore", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("load", moduleMethod);
        singletonClass.addMethodAtBootTimeOnly("restore", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyMarshal.dump", "dump");
        runtime.addBoundMethod("org.jruby.RubyMarshal.load", "load");
    }
}
