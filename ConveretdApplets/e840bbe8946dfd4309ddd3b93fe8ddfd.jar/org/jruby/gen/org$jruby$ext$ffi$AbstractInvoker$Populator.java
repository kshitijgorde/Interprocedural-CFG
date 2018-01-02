// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.AbstractInvoker;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.AbstractInvoker$i$2$0$attach;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$AbstractInvoker$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new AbstractInvoker$i$2$0$attach(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "attach", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(AbstractInvoker.class, "attach", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("attach", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.AbstractInvoker.attach", "attach");
    }
}
