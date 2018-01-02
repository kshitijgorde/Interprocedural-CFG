// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.libraries.RbConfigLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.libraries.RbConfigLibrary$s$0$0$ruby;
import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$libraries$RbConfigLibrary$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            final JavaMethod javaMethod = new RbConfigLibrary$s$0$0$ruby(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "ruby", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RbConfigLibrary.class, "ruby", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
            cls.addMethodAtBootTimeOnly("ruby", javaMethod);
            final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
            singletonClass.addMethodAtBootTimeOnly("ruby", moduleMethod);
            runtime.addBoundMethod("org.jruby.libraries.RbConfigLibrary.ruby", "ruby");
        }
    }
}
