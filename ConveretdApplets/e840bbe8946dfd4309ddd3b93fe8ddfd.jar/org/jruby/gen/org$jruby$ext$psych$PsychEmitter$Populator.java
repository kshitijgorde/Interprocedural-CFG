// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.psych.PsychEmitter$i$1$0$canonical_set;
import org.jruby.ext.psych.PsychEmitter$i$1$0$start_mapping;
import org.jruby.ext.psych.PsychEmitter$i$0$0$end_mapping;
import org.jruby.ext.psych.PsychEmitter$i$1$0$start_stream;
import org.jruby.ext.psych.PsychEmitter$i$1$0$start_sequence;
import org.jruby.ext.psych.PsychEmitter$i$1$0$initialize;
import org.jruby.ext.psych.PsychEmitter$i$1$0$alias;
import org.jruby.ext.psych.PsychEmitter$i$1$0$scalar;
import org.jruby.ext.psych.PsychEmitter$i$0$0$indentation;
import org.jruby.ext.psych.PsychEmitter$i$0$0$end_stream;
import org.jruby.ext.psych.PsychEmitter$i$1$0$indentation_set;
import org.jruby.ext.psych.PsychEmitter$i$0$0$canonical;
import org.jruby.ext.psych.PsychEmitter$i$1$0$end_document;
import org.jruby.ext.psych.PsychEmitter$i$3$0$start_document;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.psych.PsychEmitter;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.psych.PsychEmitter$i$0$0$end_sequence;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$psych$PsychEmitter$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new PsychEmitter$i$0$0$end_sequence(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "end_sequence", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "end_sequence", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("end_sequence", javaMethod);
        javaMethod = new PsychEmitter$i$3$0$start_document(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "start_document", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "start_document", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("start_document", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$end_document(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "end_document", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "end_document", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("end_document", javaMethod);
        javaMethod = new PsychEmitter$i$0$0$canonical(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "canonical", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "canonical", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("canonical", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$indentation_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "indentation_set", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "indentation_set", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("indentation=", javaMethod);
        javaMethod = new PsychEmitter$i$0$0$end_stream(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "end_stream", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "end_stream", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("end_stream", javaMethod);
        javaMethod = new PsychEmitter$i$0$0$indentation(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "indentation", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "indentation", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("indentation", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$scalar(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "scalar", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "scalar", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("scalar", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$alias(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "alias", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "alias", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("alias", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$start_sequence(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "start_sequence", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "start_sequence", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("start_sequence", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$start_stream(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "start_stream", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "start_stream", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("start_stream", javaMethod);
        javaMethod = new PsychEmitter$i$0$0$end_mapping(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "end_mapping", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "end_mapping", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("end_mapping", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$start_mapping(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "start_mapping", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "start_mapping", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("start_mapping", javaMethod);
        javaMethod = new PsychEmitter$i$1$0$canonical_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "canonical_set", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(PsychEmitter.class, "canonical_set", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("canonical=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.end_sequence", "end_sequence");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.start_document", "start_document");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.end_document", "end_document");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.canonical", "canonical");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.indentation_set", "indentation=");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.end_stream", "end_stream");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.indentation", "indentation");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.scalar", "scalar");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.alias", "alias");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.start_sequence", "start_sequence");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.start_stream", "start_stream");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.end_mapping", "end_mapping");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.start_mapping", "start_mapping");
        runtime.addBoundMethod("org.jruby.ext.psych.PsychEmitter.canonical_set", "canonical=");
    }
}
