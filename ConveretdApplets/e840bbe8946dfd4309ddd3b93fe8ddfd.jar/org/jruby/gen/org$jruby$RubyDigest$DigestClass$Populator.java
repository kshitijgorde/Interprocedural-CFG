// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyDigest$DigestClass$s$0$0$s_digest;
import org.jruby.RubyDigest$DigestClass$s$1$0$file;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyDigest;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyDigest$DigestClass$s$0$1$s_hexdigest;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyDigest$DigestClass$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyDigest$DigestClass$s$0$1$s_hexdigest(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "s_hexdigest", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestClass.class, "s_hexdigest", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("hexdigest", javaMethod);
        javaMethod = new RubyDigest$DigestClass$s$1$0$file(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "file", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestClass.class, "file", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("file", javaMethod);
        javaMethod = new RubyDigest$DigestClass$s$0$0$s_digest(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "s_digest", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyDigest.DigestClass.class, "s_digest", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("digest", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestClass.s_hexdigest", "hexdigest");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestClass.file", "file");
        runtime.addBoundMethod("org.jruby.RubyDigest.DigestClass.s_digest", "digest");
    }
}
