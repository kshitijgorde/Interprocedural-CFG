// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.compiler.ASTInspector;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyRegexp$i$0$0$fixed_encoding_p;
import org.jruby.RubyRegexp$i$1$0$op_match19;
import org.jruby.RubyRegexp$i$0$0$names;
import org.jruby.RubyRegexp$i$1$0$eqq19;
import org.jruby.RubyRegexp$i$0$0$named_captures;
import org.jruby.RubyRegexp$i$0$0$encoding;
import org.jruby.RubyRegexp$i$initialize_m19;
import org.jruby.RubyRegexp$i$match_m19;
import org.jruby.RubyRegexp$i$0$0$inspect19;
import org.jruby.RubyRegexp$i$1$0$op_match;
import org.jruby.RubyRegexp$i$1$0$eqq;
import org.jruby.RubyRegexp$i$initialize_m;
import org.jruby.RubyRegexp$i$1$0$match_m;
import org.jruby.RubyRegexp$i$0$0$inspect;
import org.jruby.RubyRegexp$i$0$0$options;
import org.jruby.RubyFixnum;
import org.jruby.RubyRegexp$i$0$0$hash;
import org.jruby.RubyRegexp$i$0$0$kcode;
import org.jruby.RubyRegexp$i$1$0$op_equal;
import org.jruby.RubyRegexp$i$0$0$source;
import org.jruby.RubyRegexp$i$0$0$to_s;
import org.jruby.RubyRegexp$i$1$0$initialize_copy;
import org.jruby.RubyRegexp$i$0$0$casefold_p;
import org.jruby.RubyRegexp$i$0$0$op_match2;
import org.jruby.RubyRegexp$s$1$0$try_convert;
import org.jruby.RubyRegexp$s$0$0$union19;
import org.jruby.RubyRegexp$s$1$0$quote19;
import org.jruby.RubyRegexp$s$0$0$union;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyString;
import org.jruby.RubyRegexp$s$0$1$quote;
import org.jruby.CompatVersion;
import org.jruby.RubyRegexp$s$last_match_s;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyRegexp;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyRegexp$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyRegexp$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyRegexp$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "newInstance", RubyRegexp.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("compile", javaMethod);
        javaMethod = new RubyRegexp$s$last_match_s(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "last_match_s", true, CallConfiguration.FrameNoneScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("last_match", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyRegexp.newInstance", "new");
        runtime.addBoundMethod("org.jruby.RubyRegexp.last_match_s", "last_match");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyRegexp$s$0$1$quote(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "quote", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "quote", RubyString.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
            singletonClass.addMethodAtBootTimeOnly("quote", javaMethod);
            singletonClass.addMethodAtBootTimeOnly("escape", javaMethod);
            javaMethod = new RubyRegexp$s$0$0$union(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "union", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "union", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
            singletonClass.addMethodAtBootTimeOnly("union", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRegexp.quote", "quote");
            runtime.addBoundMethod("org.jruby.RubyRegexp.union", "union");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyRegexp$s$1$0$quote19(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "quote19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "quote19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("quote", javaMethod);
            singletonClass.addMethodAtBootTimeOnly("escape", javaMethod);
            javaMethod = new RubyRegexp$s$0$0$union19(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "union19", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "union19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
            singletonClass.addMethodAtBootTimeOnly("union", javaMethod);
            javaMethod = new RubyRegexp$s$1$0$try_convert(singletonClass, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "try_convert", true, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "try_convert", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
            singletonClass.addMethodAtBootTimeOnly("try_convert", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRegexp.quote19", "quote");
            runtime.addBoundMethod("org.jruby.RubyRegexp.union19", "union");
            runtime.addBoundMethod("org.jruby.RubyRegexp.try_convert", "try_convert");
        }
        javaMethod = new RubyRegexp$i$0$0$op_match2(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "op_match2", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "op_match2", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("~", javaMethod);
        javaMethod = new RubyRegexp$i$0$0$casefold_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "casefold_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "casefold_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("casefold?", javaMethod);
        javaMethod = new RubyRegexp$i$1$0$initialize_copy(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "initialize_copy", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        javaMethod = new RubyRegexp$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "to_s", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyRegexp$i$0$0$source(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "source", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "source", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("source", javaMethod);
        javaMethod = new RubyRegexp$i$1$0$op_equal(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_equal", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "op_equal", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("==", javaMethod);
        cls.addMethodAtBootTimeOnly("eql?", javaMethod);
        javaMethod = new RubyRegexp$i$0$0$kcode(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "kcode", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "kcode", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("kcode", javaMethod);
        javaMethod = new RubyRegexp$i$0$0$hash(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "hash", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "hash", RubyFixnum.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("hash", javaMethod);
        javaMethod = new RubyRegexp$i$0$0$options(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "options", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyRegexp.class, "options", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("options", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyRegexp.op_match2", "~");
        runtime.addBoundMethod("org.jruby.RubyRegexp.casefold_p", "casefold?");
        runtime.addBoundMethod("org.jruby.RubyRegexp.initialize_copy", "initialize_copy");
        runtime.addBoundMethod("org.jruby.RubyRegexp.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.RubyRegexp.source", "source");
        runtime.addBoundMethod("org.jruby.RubyRegexp.op_equal", "==");
        runtime.addBoundMethod("org.jruby.RubyRegexp.kcode", "kcode");
        runtime.addBoundMethod("org.jruby.RubyRegexp.hash", "hash");
        runtime.addBoundMethod("org.jruby.RubyRegexp.options", "options");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyRegexp$i$0$0$inspect(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "inspect", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("inspect", javaMethod);
            javaMethod = new RubyRegexp$i$1$0$match_m(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "match_m", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "match_m", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("match", javaMethod);
            javaMethod = new RubyRegexp$i$initialize_m(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize_m", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            javaMethod = new RubyRegexp$i$1$0$eqq(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "eqq", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "eqq", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("===", javaMethod);
            javaMethod = new RubyRegexp$i$1$0$op_match(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_match", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "op_match", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("=~", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRegexp.inspect", "inspect");
            runtime.addBoundMethod("org.jruby.RubyRegexp.match_m", "match");
            runtime.addBoundMethod("org.jruby.RubyRegexp.initialize_m", "initialize");
            runtime.addBoundMethod("org.jruby.RubyRegexp.eqq", "===");
            runtime.addBoundMethod("org.jruby.RubyRegexp.op_match", "=~");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyRegexp$i$0$0$inspect19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "inspect19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "inspect19", IRubyObject.class, new Class[0], false);
            cls.addMethodAtBootTimeOnly("inspect", javaMethod);
            javaMethod = new RubyRegexp$i$match_m19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, -1, "match_m19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("match", javaMethod);
            javaMethod = new RubyRegexp$i$initialize_m19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize_m19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            javaMethod = new RubyRegexp$i$0$0$encoding(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "encoding", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "encoding", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("encoding", javaMethod);
            javaMethod = new RubyRegexp$i$0$0$named_captures(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "named_captures", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "named_captures", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("named_captures", javaMethod);
            javaMethod = new RubyRegexp$i$1$0$eqq19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "eqq19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "eqq19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("===", javaMethod);
            javaMethod = new RubyRegexp$i$0$0$names(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "names", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "names", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("names", javaMethod);
            javaMethod = new RubyRegexp$i$1$0$op_match19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "op_match19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "op_match19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("=~", javaMethod);
            javaMethod = new RubyRegexp$i$0$0$fixed_encoding_p(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "fixed_encoding_p", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyRegexp.class, "fixed_encoding_p", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("fixed_encoding?", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyRegexp.inspect19", "inspect");
            runtime.addBoundMethod("org.jruby.RubyRegexp.match_m19", "match");
            runtime.addBoundMethod("org.jruby.RubyRegexp.initialize_m19", "initialize");
            runtime.addBoundMethod("org.jruby.RubyRegexp.encoding", "encoding");
            runtime.addBoundMethod("org.jruby.RubyRegexp.named_captures", "named_captures");
            runtime.addBoundMethod("org.jruby.RubyRegexp.eqq19", "===");
            runtime.addBoundMethod("org.jruby.RubyRegexp.names", "names");
            runtime.addBoundMethod("org.jruby.RubyRegexp.op_match19", "=~");
            runtime.addBoundMethod("org.jruby.RubyRegexp.fixed_encoding_p", "fixed_encoding?");
        }
    }
    
    static {
        ASTInspector.addScopeAwareMethods("match", "~", "===", "=~", "last_match");
    }
}
