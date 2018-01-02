// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyDigest$s$1$0$const_missing;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyDigest;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyDigest$s$1$0$s_hexencode;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyDigest$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyDigest$s$1$0$s_hexencode(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "s_hexencode", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.class, "s_hexencode", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("hexencode", javaMethod);
        javaMethod = new RubyDigest$s$1$0$const_missing(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "const_missing", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.class, "const_missing", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("const_missing", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("const_missing", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyDigest.s_hexencode", "hexencode");
        runtime.addBoundMethod("org.jruby.RubyDigest.const_missing", "const_missing");
    }
}
