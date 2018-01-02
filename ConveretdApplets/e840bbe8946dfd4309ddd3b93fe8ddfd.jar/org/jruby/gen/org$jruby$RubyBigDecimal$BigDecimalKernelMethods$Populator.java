// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyBigDecimal;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBigDecimal$BigDecimalKernelMethods$s$0$0$newBigDecimal;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyBigDecimal$BigDecimalKernelMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new RubyBigDecimal$BigDecimalKernelMethods$s$0$0$newBigDecimal(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "newBigDecimal", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBigDecimal.BigDecimalKernelMethods.class, "newBigDecimal", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("BigDecimal", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("BigDecimal", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyBigDecimal.BigDecimalKernelMethods.newBigDecimal", "BigDecimal");
    }
}
