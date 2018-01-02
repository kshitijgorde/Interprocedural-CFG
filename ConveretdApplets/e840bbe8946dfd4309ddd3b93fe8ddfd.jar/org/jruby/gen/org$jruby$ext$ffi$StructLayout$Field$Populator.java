// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.StructLayout$Field$i$0$0$offset;
import org.jruby.ext.ffi.StructLayout$Field$i$0$0$name;
import org.jruby.ext.ffi.StructLayout$Field$i$0$0$size;
import org.jruby.ext.ffi.StructLayout$Field$i$0$0$alignment;
import org.jruby.ext.ffi.StructLayout$Field$i$0$0$type;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.StructLayout;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.StructLayout$Field$i$0$1$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$StructLayout$Field$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new StructLayout$Field$i$0$1$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.Field.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new StructLayout$Field$i$0$0$type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.Field.class, "type", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("type", javaMethod);
        cls.addMethodAtBootTimeOnly("ffi_type", javaMethod);
        javaMethod = new StructLayout$Field$i$0$0$alignment(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "alignment", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.Field.class, "alignment", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("alignment", javaMethod);
        javaMethod = new StructLayout$Field$i$0$0$size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.Field.class, "size", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new StructLayout$Field$i$0$0$name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.Field.class, "name", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("name", javaMethod);
        javaMethod = new StructLayout$Field$i$0$0$offset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "offset", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StructLayout.Field.class, "offset", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("offset", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.Field.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.Field.type", "type");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.Field.alignment", "alignment");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.Field.size", "size");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.Field.name", "name");
        runtime.addBoundMethod("org.jruby.ext.ffi.StructLayout.Field.offset", "offset");
    }
}
