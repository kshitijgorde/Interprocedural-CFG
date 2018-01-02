// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyJRuby$JRubyExecutionContextLocal$i$0$0$getDefault;
import org.jruby.RubyJRuby$JRubyExecutionContextLocal$i$0$0$getDefaultProc;
import org.jruby.RubyJRuby$JRubyExecutionContextLocal$i$0$0$getValue;
import org.jruby.runtime.Block;
import org.jruby.RubyJRuby$JRubyExecutionContextLocal$i$0$1$rubyInitialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$JRubyExecutionContextLocal$i$1$0$setValue;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$JRubyExecutionContextLocal$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyJRuby$JRubyExecutionContextLocal$i$1$0$setValue(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "setValue", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExecutionContextLocal.class, "setValue", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("value=", javaMethod);
        javaMethod = new RubyJRuby$JRubyExecutionContextLocal$i$0$1$rubyInitialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "rubyInitialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExecutionContextLocal.class, "rubyInitialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyJRuby$JRubyExecutionContextLocal$i$0$0$getValue(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getValue", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExecutionContextLocal.class, "getValue", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("value", javaMethod);
        javaMethod = new RubyJRuby$JRubyExecutionContextLocal$i$0$0$getDefaultProc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getDefaultProc", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExecutionContextLocal.class, "getDefaultProc", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("default_proc", javaMethod);
        javaMethod = new RubyJRuby$JRubyExecutionContextLocal$i$0$0$getDefault(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getDefault", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyExecutionContextLocal.class, "getDefault", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("default", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExecutionContextLocal.setValue", "value=");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExecutionContextLocal.rubyInitialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExecutionContextLocal.getValue", "value");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExecutionContextLocal.getDefaultProc", "default_proc");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyExecutionContextLocal.getDefault", "default");
    }
}
