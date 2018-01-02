// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyDigest$DigestInstance$s$0$0$hexdigest_bang;
import org.jruby.RubyDigest$DigestInstance$s$0$1$digest;
import org.jruby.RubyDigest$DigestInstance$s$0$0$finish;
import org.jruby.RubyDigest$DigestInstance$s$0$0$inspect;
import org.jruby.RubyDigest$DigestInstance$s$0$0$to_s;
import org.jruby.RubyDigest$DigestInstance$s$1$0$op_equal;
import org.jruby.RubyDigest$DigestInstance$s$0$0$digest_bang;
import org.jruby.RubyDigest$DigestInstance$s$0$0$newObject;
import org.jruby.RubyDigest$DigestInstance$s$0$0$reset;
import org.jruby.RubyDigest$DigestInstance$s$0$0$block_length;
import org.jruby.RubyDigest$DigestInstance$s$0$0$length;
import org.jruby.RubyDigest$DigestInstance$s$1$0$file;
import org.jruby.RubyDigest$DigestInstance$s$0$0$digest_length;
import org.jruby.RubyDigest$DigestInstance$s$1$0$update;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyDigest;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyDigest$DigestInstance$s$0$1$hexdigest;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyDigest$DigestInstance$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyDigest$DigestInstance$s$0$1$hexdigest(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "hexdigest", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "hexdigest", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("hexdigest", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$1$0$update(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "update", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "update", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("update", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$digest_length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "digest_length", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "digest_length", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("digest_length", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$1$0$file(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "file", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "file", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("file", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "length", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "length", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$block_length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "block_length", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "block_length", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("block_length", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$reset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "reset", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "reset", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("reset", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$newObject(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "newObject", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "newObject", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("new", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$digest_bang(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "digest_bang", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "digest_bang", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("digest!", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "to_s", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "inspect", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$finish(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "finish", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "finish", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("finish", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$1$digest(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "digest", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "digest", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("digest", javaMethod);
        javaMethod = new RubyDigest$DigestInstance$s$0$0$hexdigest_bang(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hexdigest_bang", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestInstance.class, "hexdigest_bang", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("hexdigest!", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.hexdigest", "hexdigest");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.update", "update");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.digest_length", "digest_length");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.file", "file");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.length", "length");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.block_length", "block_length");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.reset", "reset");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.newObject", "new");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.digest_bang", "digest!");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.finish", "finish");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.digest", "digest");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestInstance.hexdigest_bang", "hexdigest!");
    }
}
