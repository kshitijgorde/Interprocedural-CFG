// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyZlib$ZStream$i$0$0$avail_out;
import org.jruby.RubyZlib$ZStream$i$0$0$finished_p;
import org.jruby.RubyZlib$ZStream$i$0$0$total_in;
import org.jruby.RubyZlib$ZStream$i$0$0$adler;
import org.jruby.RubyZlib$ZStream$i$0$0$finish;
import org.jruby.RubyZlib$ZStream$i$0$0$stream_end_p;
import org.jruby.RubyZlib$ZStream$i$0$0$total_out;
import org.jruby.RubyZlib$ZStream$i$0$0$flush_next_out;
import org.jruby.runtime.Block;
import org.jruby.RubyZlib$ZStream$i$0$0$initialize;
import org.jruby.RubyZlib$ZStream$i$0$0$data_type;
import org.jruby.RubyZlib$ZStream$i$1$0$set_avail_out;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyZlib$ZStream$i$0$0$flush_next_in;
import org.jruby.RubyZlib$ZStream$i$0$0$reset;
import org.jruby.RubyZlib$ZStream$i$0$0$close;
import org.jruby.RubyZlib$ZStream$i$0$0$closed_p;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyZlib;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyZlib$ZStream$i$0$0$avail_in;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyZlib$ZStream$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyZlib$ZStream$i$0$0$avail_in(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "avail_in", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "avail_in", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("avail_in", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$closed_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "closed_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "closed_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("closed?", javaMethod);
        cls.addMethodAtBootTimeOnly("ended?", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$close(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "close", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "close", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("close", javaMethod);
        cls.addMethodAtBootTimeOnly("end", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$reset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "reset", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "reset", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("reset", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$flush_next_in(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "flush_next_in", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "flush_next_in", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("flush_next_in", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$1$0$set_avail_out(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_avail_out", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "set_avail_out", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("avail_out=", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$data_type(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "data_type", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "data_type", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("data_type", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 0, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "initialize", IRubyObject.class, new Class[] { Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$flush_next_out(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "flush_next_out", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "flush_next_out", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("flush_next_out", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$total_out(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "total_out", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "total_out", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("total_out", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$stream_end_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "stream_end_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "stream_end_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("stream_end?", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$finish(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "finish", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "finish", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("finish", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$adler(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "adler", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "adler", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("adler", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$total_in(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "total_in", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "total_in", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("total_in", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$finished_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "finished_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "finished_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("finished?", javaMethod);
        javaMethod = new RubyZlib$ZStream$i$0$0$avail_out(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "avail_out", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.ZStream.class, "avail_out", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("avail_out", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.avail_in", "avail_in");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.closed_p", "closed?");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.close", "close");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.reset", "reset");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.flush_next_in", "flush_next_in");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.set_avail_out", "avail_out=");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.data_type", "data_type");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.flush_next_out", "flush_next_out");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.total_out", "total_out");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.stream_end_p", "stream_end?");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.finish", "finish");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.adler", "adler");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.total_in", "total_in");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.finished_p", "finished?");
        runtime.addBoundMethod("org.jruby.RubyZlib.ZStream.avail_out", "avail_out");
    }
}