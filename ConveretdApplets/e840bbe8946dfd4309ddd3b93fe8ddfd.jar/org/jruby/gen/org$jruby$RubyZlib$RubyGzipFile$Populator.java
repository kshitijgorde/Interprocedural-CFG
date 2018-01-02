// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$os_code;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$mtime;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$comment;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$sync;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$crc;
import org.jruby.RubyZlib$RubyGzipFile$i$1$0$set_sync;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$close;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$closed_p;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$orig_name;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$finish;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$to_io;
import org.jruby.RubyZlib$RubyGzipFile$i$0$0$level;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyZlib$RubyGzipFile$s$1$0$wrap;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyZlib;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyZlib$RubyGzipFile$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyZlib$RubyGzipFile$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyZlib$RubyGzipFile$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "newInstance", RubyZlib.RubyGzipFile.class, new Class[] { IRubyObject.class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$s$1$0$wrap(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "wrap", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "wrap", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("wrap", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.newInstance", "new");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.wrap", "wrap");
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$level(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "level", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "level", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("level", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$to_io(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_io", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "to_io", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_io", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$finish(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "finish", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "finish", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("finish", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$orig_name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "orig_name", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "orig_name", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("orig_name", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$closed_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "closed_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "closed_p", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("closed?", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$close(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "close", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "close", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("close", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$1$0$set_sync(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_sync", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "set_sync", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("sync=", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$crc(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "crc", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "crc", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("crc", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$sync(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "sync", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "sync", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("sync", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$comment(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "comment", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "comment", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("comment", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$mtime(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "mtime", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "mtime", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("mtime", javaMethod);
        javaMethod = new RubyZlib$RubyGzipFile$i$0$0$os_code(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "os_code", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.RubyGzipFile.class, "os_code", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("os_code", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.level", "level");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.to_io", "to_io");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.finish", "finish");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.orig_name", "orig_name");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.closed_p", "closed?");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.close", "close");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.set_sync", "sync=");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.crc", "crc");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.sync", "sync");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.comment", "comment");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.mtime", "mtime");
        runtime.addBoundMethod("org.jruby.RubyZlib.RubyGzipFile.os_code", "os_code");
    }
}
