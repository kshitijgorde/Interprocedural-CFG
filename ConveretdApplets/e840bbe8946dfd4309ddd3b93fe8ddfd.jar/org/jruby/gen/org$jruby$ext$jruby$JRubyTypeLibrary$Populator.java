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
import org.jruby.ext.jruby.JRubyTypeLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.jruby.JRubyTypeLibrary$s$3$0$coerce_to;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$jruby$JRubyTypeLibrary$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new JRubyTypeLibrary$s$3$0$coerce_to(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "coerce_to", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyTypeLibrary.class, "coerce_to", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("coerce_to", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("coerce_to", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.jruby.JRubyTypeLibrary.coerce_to", "coerce_to");
    }
}
