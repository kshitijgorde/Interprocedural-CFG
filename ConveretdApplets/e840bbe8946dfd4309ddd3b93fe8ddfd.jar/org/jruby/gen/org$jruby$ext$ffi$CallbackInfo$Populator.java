// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.ffi.CallbackInfo$i$0$0$result_type;
import org.jruby.ext.ffi.CallbackInfo$i$0$0$to_s;
import org.jruby.ext.ffi.CallbackInfo$i$0$0$param_types;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.ffi.CallbackInfo;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.ffi.CallbackInfo$s$0$1$newCallbackInfo;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$ffi$CallbackInfo$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new CallbackInfo$s$0$1$newCallbackInfo(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newCallbackInfo", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(CallbackInfo.class, "newCallbackInfo", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.CallbackInfo.newCallbackInfo", "new");
        javaMethod = new CallbackInfo$i$0$0$param_types(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "param_types", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(CallbackInfo.class, "param_types", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("param_types", javaMethod);
        javaMethod = new CallbackInfo$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(CallbackInfo.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new CallbackInfo$i$0$0$result_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "result_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(CallbackInfo.class, "result_type", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("result_type", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.ffi.CallbackInfo.param_types", "param_types");
        runtime.addBoundMethod("org.jruby.ext.ffi.CallbackInfo.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.ext.ffi.CallbackInfo.result_type", "result_type");
    }
}
