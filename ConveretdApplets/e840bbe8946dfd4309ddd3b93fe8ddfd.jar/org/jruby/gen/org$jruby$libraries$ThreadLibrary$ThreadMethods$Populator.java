// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.ThreadLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.ThreadLibrary$ThreadMethods$s$0$0$exclusive;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$ThreadLibrary$ThreadMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            final JavaMethod javaMethod = new ThreadLibrary$ThreadMethods$s$0$0$exclusive(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "exclusive", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(ThreadLibrary.ThreadMethods.class, "exclusive", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
            singletonClass.addMethodAtBootTimeOnly("exclusive", javaMethod);
            runtime.addBoundMethod("org.jruby.libraries.ThreadLibrary.ThreadMethods.exclusive", "exclusive");
        }
    }
}
