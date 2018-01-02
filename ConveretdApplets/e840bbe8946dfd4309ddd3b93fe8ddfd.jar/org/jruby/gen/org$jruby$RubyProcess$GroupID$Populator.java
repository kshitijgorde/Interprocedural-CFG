// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyProcess$GroupID$s$0$0$rid;
import org.jruby.runtime.Block;
import org.jruby.RubyProcess$GroupID$s$0$0$switch_rb;
import org.jruby.RubyProcess$GroupID$s$1$0$grant_privilege;
import org.jruby.RubyProcess$GroupID$s$0$0$eid;
import org.jruby.RubyProcess$GroupID$s$1$0$eid;
import org.jruby.RubyProcess$GroupID$s$0$0$re_exchangeable_p;
import org.jruby.RubyProcess$GroupID$s$0$0$sid_available_p;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyProcess$GroupID$s$0$0$re_exchange;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyProcess;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyProcess$GroupID$s$1$0$change_privilege;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyProcess$GroupID$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyProcess$GroupID$s$1$0$change_privilege(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "change_privilege", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "change_privilege", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("change_privilege", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("change_privilege", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$0$0$re_exchange(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "re_exchange", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "re_exchange", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("re_exchange", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("re_exchange", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$0$0$sid_available_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "sid_available_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "sid_available_p", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("sid_available?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("sid_available?", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$0$0$re_exchangeable_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "re_exchangeable_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "re_exchangeable_p", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("re_exchangeable?", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("re_exchangeable?", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$1$0$eid(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "eid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "eid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("eid=", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("eid=", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$0$0$eid(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "eid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "eid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("eid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("eid", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$1$0$grant_privilege(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "grant_privilege", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "grant_privilege", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("grant_privilege", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("grant_privilege", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$0$0$switch_rb(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "switch_rb", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "switch_rb", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("switch", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("switch", moduleMethod);
        javaMethod = new RubyProcess$GroupID$s$0$0$rid(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rid", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyProcess.GroupID.class, "rid", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("rid", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("rid", moduleMethod);
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.change_privilege", "change_privilege");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.re_exchange", "re_exchange");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.sid_available_p", "sid_available?");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.re_exchangeable_p", "re_exchangeable?");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.eid", "eid=");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.eid", "eid");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.grant_privilege", "grant_privilege");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.switch_rb", "switch");
        runtime.addBoundMethod("org.jruby.RubyProcess.GroupID.rid", "rid");
    }
}
