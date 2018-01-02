// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.proxies.JavaInterfaceTemplate$s$1$0$extended;
import org.jruby.java.proxies.JavaInterfaceTemplate$s$0$0$op_aref;
import org.jruby.java.proxies.JavaInterfaceTemplate$s$0$0$rbNew;
import org.jruby.java.proxies.JavaInterfaceTemplate$s$1$0$implement;
import org.jruby.java.proxies.JavaInterfaceTemplate$s$1$0$append_features;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.proxies.JavaInterfaceTemplate;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.proxies.JavaInterfaceTemplate$s$0$0$impl;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$proxies$JavaInterfaceTemplate$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaInterfaceTemplate$s$0$0$impl(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "impl", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(JavaInterfaceTemplate.class, "impl", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("impl", javaMethod);
        javaMethod = new JavaInterfaceTemplate$s$1$0$append_features(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "append_features", true, CallConfiguration.FrameFullScopeNone, false);
        javaMethod.setNativeCall(JavaInterfaceTemplate.class, "append_features", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("append_features", javaMethod);
        javaMethod = new JavaInterfaceTemplate$s$1$0$implement(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "implement", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(JavaInterfaceTemplate.class, "implement", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("implement", javaMethod);
        javaMethod = new JavaInterfaceTemplate$s$0$0$rbNew(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "rbNew", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(JavaInterfaceTemplate.class, "rbNew", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("new", javaMethod);
        javaMethod = new JavaInterfaceTemplate$s$0$0$op_aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "op_aref", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(JavaInterfaceTemplate.class, "op_aref", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new JavaInterfaceTemplate$s$1$0$extended(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "extended", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaInterfaceTemplate.class, "extended", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("extended", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.JavaInterfaceTemplate.impl", "impl");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaInterfaceTemplate.append_features", "append_features");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaInterfaceTemplate.implement", "implement");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaInterfaceTemplate.rbNew", "new");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaInterfaceTemplate.op_aref", "[]");
        runtime.addBoundMethod("org.jruby.java.proxies.JavaInterfaceTemplate.extended", "extended");
    }
}
