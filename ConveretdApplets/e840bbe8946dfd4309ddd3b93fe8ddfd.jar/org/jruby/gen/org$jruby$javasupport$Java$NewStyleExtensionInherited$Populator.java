// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.Java;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.Java$NewStyleExtensionInherited$s$1$0$inherited;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$Java$NewStyleExtensionInherited$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new Java$NewStyleExtensionInherited$s$1$0$inherited(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "inherited", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Java.NewStyleExtensionInherited.class, "inherited", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("inherited", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.Java.NewStyleExtensionInherited.inherited", "inherited");
    }
}
