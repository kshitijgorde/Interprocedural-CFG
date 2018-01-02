// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.Type$i$0$0$size;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Type;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Type$i$0$0$alignment;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Type$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Type$i$0$0$alignment(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "alignment", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.class, "alignment", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("alignment", javaMethod);
        javaMethod = new Type$i$0$0$size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.class, "size", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.alignment", "alignment");
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.size", "size");
    }
}
