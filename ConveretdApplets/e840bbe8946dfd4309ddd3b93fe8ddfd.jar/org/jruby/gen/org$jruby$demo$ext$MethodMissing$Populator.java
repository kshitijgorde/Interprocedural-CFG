// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.demo.ext.MethodMissing;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.demo.ext.MethodMissing$i$0$0$method_missing;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$demo$ext$MethodMissing$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = (JavaMethod)new MethodMissing$i$0$0$method_missing(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "method_missing", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(MethodMissing.class, "method_missing", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("method_missing", javaMethod);
        runtime.addBoundMethod("org.jruby.demo.ext.MethodMissing.method_missing", "method_missing");
    }
}
