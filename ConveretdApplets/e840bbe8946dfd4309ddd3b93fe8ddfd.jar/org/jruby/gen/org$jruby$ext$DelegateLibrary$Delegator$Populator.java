// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.DelegateLibrary$Delegator$s$1$0$method;
import org.jruby.ext.DelegateLibrary$Delegator$s$1$0$repond_to_p;
import org.jruby.ext.DelegateLibrary$Delegator$s$1$0$marshal_load;
import org.jruby.ext.DelegateLibrary$Delegator$s$0$0$method_missing;
import org.jruby.ext.DelegateLibrary$Delegator$s$0$0$__getobj__;
import org.jruby.ext.DelegateLibrary$Delegator$s$0$0$marshal_dump;
import org.jruby.ext.DelegateLibrary$Delegator$s$1$0$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.DelegateLibrary;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.DelegateLibrary$Delegator$s$0$0$send;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$DelegateLibrary$Delegator$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new DelegateLibrary$Delegator$s$0$0$send(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "send", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "send", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("send", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$1$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$0$0$marshal_dump(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "marshal_dump", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "marshal_dump", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("marshal_dump", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$0$0$__getobj__(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "__getobj__", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "__getobj__", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("__getobj__", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$0$0$method_missing(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "method_missing", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "method_missing", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("method_missing", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$1$0$marshal_load(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "marshal_load", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "marshal_load", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("marshal_load", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$1$0$repond_to_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "repond_to_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "repond_to_p", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("respond_to?", javaMethod);
        javaMethod = new DelegateLibrary$Delegator$s$1$0$method(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "method", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(DelegateLibrary.Delegator.class, "method", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("method", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.send", "send");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.marshal_dump", "marshal_dump");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.__getobj__", "__getobj__");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.method_missing", "method_missing");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.marshal_load", "marshal_load");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.repond_to_p", "respond_to?");
        runtime.addBoundMethod("org.jruby.ext.DelegateLibrary.Delegator.method", "method");
    }
}
