// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.jffi.Function$i$0$0$autorelease_p;
import org.jruby.ext.ffi.jffi.Function$i$1$0$autorelease;
import org.jruby.ext.ffi.jffi.Function$i$0$0$free;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.jffi.Function;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.jffi.Function$s$0$2$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$jffi$Function$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Function$s$0$2$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Function.class, "newInstance", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.Function.newInstance", "new");
        javaMethod = new Function$i$0$0$free(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "free", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Function.class, "free", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("free", javaMethod);
        javaMethod = new Function$i$1$0$autorelease(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "autorelease", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Function.class, "autorelease", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("autorelease=", javaMethod);
        javaMethod = new Function$i$0$0$autorelease_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "autorelease_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Function.class, "autorelease_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("autorelease?", javaMethod);
        cls.addMethodAtBootTimeOnly("autorelease", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.Function.free", "free");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.Function.autorelease", "autorelease=");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.Function.autorelease_p", "autorelease?");
    }
}
