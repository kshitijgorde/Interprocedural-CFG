// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.proxies.InterfaceJavaProxy$JavaInterfaceExtender$s$1$0$extend_proxy;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.proxies.InterfaceJavaProxy;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.proxies.InterfaceJavaProxy$JavaInterfaceExtender$s$1$0$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$proxies$InterfaceJavaProxy$JavaInterfaceExtender$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new InterfaceJavaProxy$JavaInterfaceExtender$s$1$0$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(InterfaceJavaProxy.JavaInterfaceExtender.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new InterfaceJavaProxy$JavaInterfaceExtender$s$1$0$extend_proxy(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "extend_proxy", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(InterfaceJavaProxy.JavaInterfaceExtender.class, "extend_proxy", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("extend_proxy", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.InterfaceJavaProxy.JavaInterfaceExtender.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.java.proxies.InterfaceJavaProxy.JavaInterfaceExtender.extend_proxy", "extend_proxy");
    }
}
