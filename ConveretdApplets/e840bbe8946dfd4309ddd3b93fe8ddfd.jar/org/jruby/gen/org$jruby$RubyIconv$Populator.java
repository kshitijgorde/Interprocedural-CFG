// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyIconv$i$0$0$close;
import org.jruby.RubyIconv$i$iconv;
import org.jruby.RubyIconv$i$2$0$initialize;
import org.jruby.RubyIconv$s$0$0$conv;
import org.jruby.RubyIconv$s$0$0$charset_map_get;
import org.jruby.RubyIconv$s$0$0$iconv;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyIconv;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyIconv$s$2$0$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyIconv$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyIconv$s$2$0$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        javaMethod = new RubyIconv$s$0$0$iconv(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "iconv", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.class, "iconv", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("iconv", javaMethod);
        javaMethod = new RubyIconv$s$0$0$charset_map_get(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "charset_map_get", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.class, "charset_map_get", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("charset_map", javaMethod);
        javaMethod = new RubyIconv$s$0$0$conv(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "conv", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.class, "conv", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("conv", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyIconv.open", "open");
        runtime.addBoundMethod("org.jruby.RubyIconv.iconv", "iconv");
        runtime.addBoundMethod("org.jruby.RubyIconv.charset_map_get", "charset_map");
        runtime.addBoundMethod("org.jruby.RubyIconv.conv", "conv");
        javaMethod = new RubyIconv$i$2$0$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.class, "initialize", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyIconv$i$iconv(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "iconv", false, CallConfiguration.FrameBacktraceScopeNone, false);
        cls.addMethodAtBootTimeOnly("iconv", javaMethod);
        javaMethod = new RubyIconv$i$0$0$close(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "close", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIconv.class, "close", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("close", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyIconv.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyIconv.iconv", "iconv");
        runtime.addBoundMethod("org.jruby.RubyIconv.close", "close");
    }
}
