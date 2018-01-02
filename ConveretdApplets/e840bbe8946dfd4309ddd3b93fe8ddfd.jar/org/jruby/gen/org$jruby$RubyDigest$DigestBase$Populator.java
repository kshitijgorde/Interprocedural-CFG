// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyDigest$DigestBase$i$1$0$initialize_copy;
import org.jruby.RubyDigest$DigestBase$i$0$0$reset;
import org.jruby.RubyDigest$DigestBase$i$0$0$block_length;
import org.jruby.RubyDigest$DigestBase$i$0$0$digest_length;
import org.jruby.RubyDigest$DigestBase$i$1$0$update;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyDigest;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyDigest$DigestBase$i$0$0$finish;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyDigest$DigestBase$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyDigest$DigestBase$i$0$0$finish(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "finish", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestBase.class, "finish", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("finish", javaMethod);
        javaMethod = new RubyDigest$DigestBase$i$1$0$update(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "update", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestBase.class, "update", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("update", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new RubyDigest$DigestBase$i$0$0$digest_length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "digest_length", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestBase.class, "digest_length", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("digest_length", javaMethod);
        javaMethod = new RubyDigest$DigestBase$i$0$0$block_length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "block_length", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestBase.class, "block_length", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("block_length", javaMethod);
        javaMethod = new RubyDigest$DigestBase$i$0$0$reset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "reset", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestBase.class, "reset", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("reset", javaMethod);
        javaMethod = new RubyDigest$DigestBase$i$1$0$initialize_copy(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestBase.class, "initialize_copy", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestBase.finish", "finish");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestBase.update", "update");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestBase.digest_length", "digest_length");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestBase.block_length", "block_length");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestBase.reset", "reset");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestBase.initialize_copy", "initialize_copy");
    }
}
