// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.Timeout$s$timeout;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$Timeout$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new Timeout$s$timeout(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "timeout", true, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("timeout", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("timeout", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.Timeout.timeout", "timeout");
    }
}
