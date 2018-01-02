// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.yaml.snakeyaml.emitter.EmitterException;
import java.io.IOException;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.ImplicitTuple;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.jruby.RubyArray;
import java.util.Map;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import java.util.HashMap;
import java.util.Collections;
import org.yaml.snakeyaml.events.StreamEndEvent;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.StreamStartEvent;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.jruby.util.IOOutputStream;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.emitter.Emitter;
import org.jruby.RubyObject;

public class PsychEmitter extends RubyObject
{
    Emitter emitter;
    DumperOptions options;
    private static final Mark NULL_MARK;
    
    public static void initPsychEmitter(final Ruby runtime, final RubyModule psych) {
        final RubyClass psychEmitter = runtime.defineClassUnder("Emitter", runtime.getObject(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new PsychEmitter(runtime, klazz);
            }
        }, psych);
        psychEmitter.defineAnnotatedMethods(PsychEmitter.class);
    }
    
    public PsychEmitter(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.options = new DumperOptions();
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject io) {
        (this.options = new DumperOptions()).setIndent(2);
        this.emitter = new Emitter(new OutputStreamWriter(new IOOutputStream(io)), this.options);
        return context.nil;
    }
    
    @JRubyMethod
    public IRubyObject start_stream(final ThreadContext context, final IRubyObject encoding) {
        final StreamStartEvent event = new StreamStartEvent(PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject end_stream(final ThreadContext context) {
        final StreamEndEvent event = new StreamEndEvent(PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject start_document(final ThreadContext context, final IRubyObject version, final IRubyObject tags, final IRubyObject implicit) {
        final Integer[] versionInts = { 1, 1 };
        final boolean implicitBool = implicit.isTrue();
        Map<String, String> tagsMap = (Map<String, String>)Collections.EMPTY_MAP;
        final RubyArray versionAry = version.convertToArray();
        if (versionAry.size() == 2) {
            versionInts[0] = (int)versionAry.eltInternal(0).convertToInteger().getLongValue();
            versionInts[1] = (int)versionAry.eltInternal(1).convertToInteger().getLongValue();
        }
        final RubyArray tagsAry = tags.convertToArray();
        if (tagsAry.size() > 0) {
            tagsMap = new HashMap<String, String>(tagsAry.size());
            for (int i = 0; i < tagsAry.size(); ++i) {
                final RubyArray tagsTuple = tagsAry.eltInternal(i).convertToArray();
                if (tagsTuple.size() != 2) {
                    throw context.runtime.newRuntimeError("tags tuple must be of length 2");
                }
                final IRubyObject key = tagsTuple.eltInternal(0);
                final IRubyObject value = tagsTuple.eltInternal(1);
                tagsMap.put(key.asJavaString(), value.asJavaString());
            }
        }
        final DocumentStartEvent event = new DocumentStartEvent(PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK, implicitBool, versionInts, tagsMap);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject end_document(final ThreadContext context, final IRubyObject implicit) {
        final DocumentEndEvent event = new DocumentEndEvent(PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK, implicit.isTrue());
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod(required = 6)
    public IRubyObject scalar(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject value = args[0];
        final IRubyObject anchor = args[1];
        final IRubyObject tag = args[2];
        final IRubyObject plain = args[3];
        final IRubyObject quoted = args[4];
        final IRubyObject style = args[5];
        final ScalarEvent event = new ScalarEvent(anchor.isNil() ? null : anchor.asJavaString(), tag.isNil() ? null : tag.asJavaString(), new ImplicitTuple(plain.isTrue(), quoted.isTrue()), value.asJavaString(), PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK, (char)style.convertToInteger().getLongValue());
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod(required = 4)
    public IRubyObject start_sequence(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject anchor = args[0];
        final IRubyObject tag = args[1];
        final IRubyObject implicit = args[2];
        final IRubyObject style = args[3];
        final SequenceStartEvent event = new SequenceStartEvent(anchor.isNil() ? null : anchor.asJavaString(), tag.isNil() ? null : tag.asJavaString(), implicit.isTrue(), PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK, style.convertToInteger().getLongValue() == 0L);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject end_sequence(final ThreadContext context) {
        final SequenceEndEvent event = new SequenceEndEvent(PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod(required = 4)
    public IRubyObject start_mapping(final ThreadContext context, final IRubyObject[] args) {
        final IRubyObject anchor = args[0];
        final IRubyObject tag = args[1];
        final IRubyObject implicit = args[2];
        final IRubyObject style = args[3];
        final MappingStartEvent event = new MappingStartEvent(anchor.isNil() ? null : anchor.asJavaString(), tag.isNil() ? null : tag.asJavaString(), implicit.isTrue(), PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK, style.convertToInteger().getLongValue() == 0L);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject end_mapping(final ThreadContext context) {
        final MappingEndEvent event = new MappingEndEvent(PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod
    public IRubyObject alias(final ThreadContext context, final IRubyObject anchor) {
        final AliasEvent event = new AliasEvent(anchor.asJavaString(), PsychEmitter.NULL_MARK, PsychEmitter.NULL_MARK);
        this.emit(context, event);
        return this;
    }
    
    @JRubyMethod(name = { "canonical=" })
    public IRubyObject canonical_set(final ThreadContext context, final IRubyObject style) {
        this.options.setCanonical(style.isTrue());
        return style;
    }
    
    @JRubyMethod
    public IRubyObject canonical(final ThreadContext context) {
        return context.runtime.newBoolean(this.options.isCanonical());
    }
    
    @JRubyMethod(name = { "indentation=" })
    public IRubyObject indentation_set(final ThreadContext context, final IRubyObject level) {
        this.options.setIndent((int)level.convertToInteger().getLongValue());
        return level;
    }
    
    @JRubyMethod
    public IRubyObject indentation(final ThreadContext context) {
        return context.runtime.newFixnum(this.options.getIndent());
    }
    
    private void emit(final ThreadContext context, final Event event) {
        try {
            this.emitter.emit(event);
        }
        catch (IOException ioe) {
            throw context.runtime.newIOErrorFromException(ioe);
        }
        catch (EmitterException ee) {
            throw context.runtime.newRuntimeError(ee.toString());
        }
    }
    
    static {
        NULL_MARK = new Mark(null, 0, 0, 0, null, 0);
    }
}
