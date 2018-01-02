// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyEnumerator$RubyEnumeratorKernel$s$obj_to_enum;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyEnumerator$RubyEnumeratorKernel$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new RubyEnumerator$RubyEnumeratorKernel$s$obj_to_enum(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "obj_to_enum", true, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("to_enum", javaMethod);
        cls.addMethodAtBootTimeOnly("enum_for", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyEnumerator.RubyEnumeratorKernel.obj_to_enum", "to_enum");
    }
}
