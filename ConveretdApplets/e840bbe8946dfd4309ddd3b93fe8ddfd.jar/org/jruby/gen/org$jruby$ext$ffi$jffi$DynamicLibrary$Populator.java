// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.jffi.DynamicLibrary$i$0$0$name;
import org.jruby.ext.ffi.jffi.DynamicLibrary$i$1$0$findFunction;
import org.jruby.ext.ffi.jffi.DynamicLibrary$i$1$0$findVariable;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.jffi.DynamicLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.jffi.DynamicLibrary$s$2$0$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$jffi$DynamicLibrary$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new DynamicLibrary$s$2$0$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.open", "open");
        javaMethod = new DynamicLibrary$i$1$0$findVariable(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "findVariable", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.class, "findVariable", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("find_variable", javaMethod);
        cls.addMethodAtBootTimeOnly("find_symbol", javaMethod);
        javaMethod = new DynamicLibrary$i$1$0$findFunction(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "findFunction", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.class, "findFunction", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("find_function", javaMethod);
        javaMethod = new DynamicLibrary$i$0$0$name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DynamicLibrary.class, "name", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("name", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.findVariable", "find_variable");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.findFunction", "find_function");
        runtime.addBoundMethod("org.jruby.ext.ffi.jffi.DynamicLibrary.name", "name");
    }
}
