// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyGlobal;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyGlobal$CaseInsensitiveStringOnlyRubyHash$i$0$0$to_s;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyGlobal$CaseInsensitiveStringOnlyRubyHash$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new RubyGlobal$CaseInsensitiveStringOnlyRubyHash$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyGlobal.CaseInsensitiveStringOnlyRubyHash.class, "to_s", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyGlobal.CaseInsensitiveStringOnlyRubyHash.to_s", "to_s");
    }
}
