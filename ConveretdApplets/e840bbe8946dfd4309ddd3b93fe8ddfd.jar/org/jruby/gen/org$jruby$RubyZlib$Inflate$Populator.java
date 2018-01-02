// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyZlib$Inflate$i$1$0$set_dictionary;
import org.jruby.RubyZlib$Inflate$i$1$0$sync;
import org.jruby.RubyZlib$Inflate$i$1$0$append;
import org.jruby.RubyZlib$Inflate$i$0$0$sync_point_p;
import org.jruby.RubyZlib$Inflate$i$1$0$inflate;
import org.jruby.RubyZlib$Inflate$i$0$1$_initialize;
import org.jruby.RubyZlib$Inflate$i$0$0$flush_next_out;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyZlib;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyZlib$Inflate$s$1$0$s_inflate;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyZlib$Inflate$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyZlib$Inflate$s$1$0$s_inflate(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "s_inflate", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "s_inflate", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("inflate", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.s_inflate", "inflate");
        javaMethod = new RubyZlib$Inflate$i$0$0$flush_next_out(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "flush_next_out", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "flush_next_out", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("flush_next_out", javaMethod);
        javaMethod = new RubyZlib$Inflate$i$0$1$_initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "_initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "_initialize", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyZlib$Inflate$i$1$0$inflate(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "inflate", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "inflate", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("inflate", javaMethod);
        javaMethod = new RubyZlib$Inflate$i$0$0$sync_point_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "sync_point_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "sync_point_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("sync_point?", javaMethod);
        javaMethod = new RubyZlib$Inflate$i$1$0$append(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "append", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "append", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new RubyZlib$Inflate$i$1$0$sync(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "sync", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "sync", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("sync", javaMethod);
        javaMethod = new RubyZlib$Inflate$i$1$0$set_dictionary(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_dictionary", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Inflate.class, "set_dictionary", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("set_dictionary", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.flush_next_out", "flush_next_out");
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate._initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.inflate", "inflate");
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.sync_point_p", "sync_point?");
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.append", "<<");
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.sync", "sync");
        runtime.addBoundMethod("org.jruby.RubyZlib.Inflate.set_dictionary", "set_dictionary");
    }
}
