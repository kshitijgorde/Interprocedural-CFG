// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.psych.PsychYamlTree;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.psych.PsychYamlTree$s$2$0$private_iv_get;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$psych$PsychYamlTree$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new PsychYamlTree$s$2$0$private_iv_get(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 2, "private_iv_get", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychYamlTree.class, "private_iv_get", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("private_iv_get", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.psych.PsychYamlTree.private_iv_get", "private_iv_get");
    }
}
