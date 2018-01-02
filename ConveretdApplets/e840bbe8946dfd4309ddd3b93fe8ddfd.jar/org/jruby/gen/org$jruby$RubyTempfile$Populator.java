// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyTempfile$i$2$0$make_tmpname;
import org.jruby.RubyTempfile$i$0$0$unlink;
import org.jruby.RubyTempfile$i$0$0$size;
import org.jruby.RubyTempfile$i$0$0$_close;
import org.jruby.RubyTempfile$i$0$1$close;
import org.jruby.RubyTempfile$i$0$0$close_bang;
import org.jruby.RubyTempfile$i$0$0$open;
import org.jruby.RubyTempfile$i$0$1$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyTempfile;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyTempfile$s$0$1$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyTempfile$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyTempfile$s$0$1$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyTempfile.open", "open");
        javaMethod = new RubyTempfile$i$0$1$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyTempfile$i$0$0$open(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "open", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "open", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("open", javaMethod);
        javaMethod = new RubyTempfile$i$0$0$close_bang(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "close_bang", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "close_bang", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("close!", javaMethod);
        javaMethod = new RubyTempfile$i$0$1$close(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "close", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "close", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("close", javaMethod);
        javaMethod = new RubyTempfile$i$0$0$_close(cls, Visibility.PROTECTED);
        TypePopulator.populateMethod(javaMethod, 0, "_close", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "_close", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("_close", javaMethod);
        javaMethod = new RubyTempfile$i$0$0$size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "size", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        javaMethod = new RubyTempfile$i$0$0$unlink(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "unlink", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "unlink", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("unlink", javaMethod);
        cls.addMethodAtBootTimeOnly("delete", javaMethod);
        javaMethod = new RubyTempfile$i$2$0$make_tmpname(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 2, "make_tmpname", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTempfile.class, "make_tmpname", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("make_tmpname", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyTempfile.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyTempfile.open", "open");
        runtime.addBoundMethod("org.jruby.RubyTempfile.close_bang", "close!");
        runtime.addBoundMethod("org.jruby.RubyTempfile.close", "close");
        runtime.addBoundMethod("org.jruby.RubyTempfile._close", "_close");
        runtime.addBoundMethod("org.jruby.RubyTempfile.size", "size");
        runtime.addBoundMethod("org.jruby.RubyTempfile.unlink", "unlink");
        runtime.addBoundMethod("org.jruby.RubyTempfile.make_tmpname", "make_tmpname");
    }
}
