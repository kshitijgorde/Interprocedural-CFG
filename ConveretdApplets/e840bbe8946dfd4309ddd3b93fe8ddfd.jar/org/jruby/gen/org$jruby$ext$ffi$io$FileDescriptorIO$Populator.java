// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyIO;
import org.jruby.ext.ffi.io.FileDescriptorIO$s$1$0$wrap;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.ffi.io.FileDescriptorIO;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.io.FileDescriptorIO$s$1$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$io$FileDescriptorIO$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new FileDescriptorIO$s$1$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(FileDescriptorIO.class, "newInstance", FileDescriptorIO.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        javaMethod = new FileDescriptorIO$s$1$0$wrap(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "wrap", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(FileDescriptorIO.class, "wrap", RubyIO.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("wrap", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.io.FileDescriptorIO.newInstance", "new");
        runtime.addBoundMethod("org.jruby.ext.ffi.io.FileDescriptorIO.wrap", "wrap");
    }
}
