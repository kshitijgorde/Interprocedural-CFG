// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.StructLayout$i$0$0$fields;
import org.jruby.ext.ffi.StructLayout$i$1$0$aref;
import org.jruby.ext.ffi.StructLayout$i$3$0$put;
import org.jruby.ext.ffi.StructLayout$i$0$0$members;
import org.jruby.ext.ffi.StructLayout$i$2$0$get;
import org.jruby.ext.ffi.StructLayout$i$0$0$offsets;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.StructLayout;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.StructLayout$s$0$1$newStructLayout;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$StructLayout$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new StructLayout$s$0$1$newStructLayout(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newStructLayout", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "newStructLayout", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.newStructLayout", "new");
        javaMethod = new StructLayout$i$0$0$offsets(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "offsets", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "offsets", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("offsets", javaMethod);
        javaMethod = new StructLayout$i$2$0$get(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "get", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "get", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("get", javaMethod);
        javaMethod = new StructLayout$i$0$0$members(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "members", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "members", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("members", javaMethod);
        javaMethod = new StructLayout$i$3$0$put(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "put", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "put", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("put", javaMethod);
        javaMethod = new StructLayout$i$1$0$aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "aref", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new StructLayout$i$0$0$fields(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "fields", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.class, "fields", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("fields", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.offsets", "offsets");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.get", "get");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.members", "members");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.put", "put");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.aref", "[]");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.fields", "fields");
    }
}
