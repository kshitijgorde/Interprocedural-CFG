// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.proxies.ArrayJavaProxyCreator$i$0$0$_new;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.proxies.ArrayJavaProxyCreator;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.proxies.ArrayJavaProxyCreator$i$0$0$op_aref;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$proxies$ArrayJavaProxyCreator$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ArrayJavaProxyCreator$i$0$0$op_aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "op_aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxyCreator.class, "op_aref", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("op_aref", javaMethod);
        javaMethod = new ArrayJavaProxyCreator$i$0$0$_new(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "_new", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaProxyCreator.class, "_new", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxyCreator.op_aref", "op_aref");
        runtime.addBoundMethod("org.jruby.java.proxies.ArrayJavaProxyCreator._new", "new");
    }
}
