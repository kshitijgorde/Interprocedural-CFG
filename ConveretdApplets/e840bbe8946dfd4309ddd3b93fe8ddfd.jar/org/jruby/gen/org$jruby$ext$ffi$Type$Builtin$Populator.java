// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.ffi.Type$Builtin$i$0$0$to_s;
import org.jruby.ext.ffi.Type$Builtin$i$0$0$to_sym;
import org.jruby.ext.ffi.Type$Builtin$i$1$0$op_equal;
import org.jruby.ext.ffi.Type$Builtin$i$1$0$equal_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.Type;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.Type$Builtin$i$1$0$eql_p;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$Type$Builtin$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Type$Builtin$i$1$0$eql_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "eql_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Builtin.class, "eql_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new Type$Builtin$i$1$0$equal_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "equal_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Builtin.class, "equal_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("equal?", javaMethod);
        javaMethod = new Type$Builtin$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Builtin.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new Type$Builtin$i$0$0$to_sym(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_sym", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Builtin.class, "to_sym", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_sym", javaMethod);
        javaMethod = new Type$Builtin$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Type.Builtin.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Builtin.eql_p", "eql?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Builtin.equal_p", "equal?");
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Builtin.op_equal", "==");
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Builtin.to_sym", "to_sym");
        runtime.addBoundMethod("org.jruby.ext.ffi.Type.Builtin.to_s", "to_s");
    }
}
