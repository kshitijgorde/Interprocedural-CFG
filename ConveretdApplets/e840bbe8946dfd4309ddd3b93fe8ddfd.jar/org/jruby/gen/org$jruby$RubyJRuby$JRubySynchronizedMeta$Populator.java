// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyJRuby$JRubySynchronizedMeta$s$1$0$append_features;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$JRubySynchronizedMeta$s$1$0$extend_object;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$JRubySynchronizedMeta$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyJRuby$JRubySynchronizedMeta$s$1$0$extend_object(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "extend_object", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubySynchronizedMeta.class, "extend_object", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("extend_object", javaMethod);
        javaMethod = new RubyJRuby$JRubySynchronizedMeta$s$1$0$append_features(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "append_features", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubySynchronizedMeta.class, "append_features", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("append_features", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubySynchronizedMeta.extend_object", "extend_object");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubySynchronizedMeta.append_features", "append_features");
    }
}
