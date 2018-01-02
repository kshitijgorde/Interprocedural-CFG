// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.compiler.ASTInspector;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyProcess$RubyStatus$i$0$0$pid;
import org.jruby.RubyProcess$RubyStatus$i$1$0$op_equal;
import org.jruby.RubyProcess$RubyStatus$i$0$0$to_s;
import org.jruby.RubyProcess$RubyStatus$i$1$0$not_implemented1;
import org.jruby.RubyProcess$RubyStatus$i$0$0$success_p;
import org.jruby.RubyProcess$RubyStatus$i$0$0$exitstatus;
import org.jruby.RubyProcess$RubyStatus$i$0$0$to_i;
import org.jruby.RubyProcess$RubyStatus$i$1$0$op_rshift;
import org.jruby.RubyProcess$RubyStatus$i$0$0$not_implemented;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyProcess;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyProcess$RubyStatus$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyProcess$RubyStatus$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyProcess$RubyStatus$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$0$0$not_implemented(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "not_implemented", false, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "not_implemented", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_int", javaMethod);
        cls.addMethodAtBootTimeOnly("stopped?", javaMethod);
        cls.addMethodAtBootTimeOnly("stopsig", javaMethod);
        cls.addMethodAtBootTimeOnly("signaled?", javaMethod);
        cls.addMethodAtBootTimeOnly("termsig?", javaMethod);
        cls.addMethodAtBootTimeOnly("exited?", javaMethod);
        cls.addMethodAtBootTimeOnly("coredump?", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$1$0$op_rshift(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_rshift", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "op_rshift", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly(">>", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$0$0$to_i(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_i", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "to_i", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_i", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$0$0$exitstatus(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "exitstatus", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "exitstatus", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("exitstatus", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$0$0$success_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "success_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "success_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("success?", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$1$0$not_implemented1(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "not_implemented1", false, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "not_implemented1", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("&", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new RubyProcess$RubyStatus$i$0$0$pid(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "pid", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.RubyStatus.class, "pid", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("pid", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.not_implemented", "to_int");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.op_rshift", ">>");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.to_i", "to_i");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.exitstatus", "exitstatus");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.success_p", "success?");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.not_implemented1", "&");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyProcess.RubyStatus.pid", "pid");
    }
    
    static {
        ASTInspector.addFrameAwareMethods("to_int", "coredump?", "signaled?", "stopped?", "&", "stopsig", "termsig?", "exited?");
    }
}
