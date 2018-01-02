// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.psych.PsychToRuby$s$2$0$build_exception;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.psych.PsychToRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.psych.PsychToRuby$s$1$0$path2class;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$psych$PsychToRuby$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new PsychToRuby$s$1$0$path2class(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "path2class", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychToRuby.class, "path2class", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("path2class", javaMethod);
        javaMethod = new PsychToRuby$s$2$0$build_exception(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 2, "build_exception", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychToRuby.class, "build_exception", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("build_exception", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.psych.PsychToRuby.path2class", "path2class");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychToRuby.build_exception", "build_exception");
    }
}
