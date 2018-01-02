// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.IOModule;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.IOModule$s$3$0$native_read;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$IOModule$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new IOModule$s$3$0$native_read(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "native_read", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(IOModule.class, "native_read", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("native_read", javaMethod);
        final DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("native_read", moduleMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.IOModule.native_read", "native_read");
    }
}
