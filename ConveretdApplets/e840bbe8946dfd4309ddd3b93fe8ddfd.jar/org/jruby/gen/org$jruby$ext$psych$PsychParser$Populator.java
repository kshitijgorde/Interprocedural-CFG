// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.psych.PsychParser;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.psych.PsychParser$i$1$0$parse;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$psych$PsychParser$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new PsychParser$i$1$0$parse(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "parse", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychParser.class, "parse", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("parse", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.psych.PsychParser.parse", "parse");
    }
}
