// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.jcodings.specific.UTF16BEEncoding;
import org.jcodings.specific.UTF16LEEncoding;
import org.jcodings.specific.UTF8Encoding;
import org.jruby.anno.JRubyMethod;
import java.util.Iterator;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.scanner.ScannerException;
import org.yaml.snakeyaml.parser.ParserException;
import org.jruby.RubyKernel;
import org.jruby.runtime.Block;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.jruby.RubyString;
import java.util.Map;
import org.jruby.RubyArray;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.parser.ParserImpl;
import java.io.StringReader;
import java.io.Reader;
import org.yaml.snakeyaml.reader.StreamReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.jruby.util.IOInputStream;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.RubyObject;

public class PsychParser extends RubyObject
{
    public static final int YAML_ANY_ENCODING = 0;
    public static final int YAML_UTF8_ENCODING;
    public static final int YAML_UTF16LE_ENCODING;
    public static final int YAML_UTF16BE_ENCODING;
    
    public static void initPsychParser(final Ruby runtime, final RubyModule psych) {
        final RubyClass psychParser = runtime.defineClassUnder("Parser", runtime.getObject(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new PsychParser(runtime, klazz);
            }
        }, psych);
        psychParser.defineConstant("ANY", runtime.newFixnum(0));
        psychParser.defineConstant("UTF8", runtime.newFixnum(PsychParser.YAML_UTF8_ENCODING));
        psychParser.defineConstant("UTF16LE", runtime.newFixnum(PsychParser.YAML_UTF16LE_ENCODING));
        psychParser.defineConstant("UTF16BE", runtime.newFixnum(PsychParser.YAML_UTF16BE_ENCODING));
        psychParser.defineAnnotatedMethods(PsychParser.class);
        psych.defineClassUnder("SyntaxError", runtime.getSyntaxError(), RubyException.EXCEPTION_ALLOCATOR);
    }
    
    public PsychParser(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
    }
    
    @JRubyMethod
    public IRubyObject parse(final ThreadContext context, final IRubyObject target) {
        final Ruby runtime = context.runtime;
        StreamReader reader;
        if (target.respondsTo("read")) {
            reader = new StreamReader(new InputStreamReader(new IOInputStream(target)));
        }
        else {
            reader = new StreamReader(new StringReader(target.convertToString().asJavaString()));
        }
        Parser parser = new ParserImpl(reader);
        final IRubyObject handler = this.getInstanceVariable("@handler");
        while (true) {
            try {
                while (true) {
                    final Event event = parser.getEvent();
                    if (event.is(Event.ID.StreamStart)) {
                        RuntimeHelpers.invoke(context, handler, "start_stream", runtime.newFixnum(0));
                    }
                    else if (event.is(Event.ID.DocumentStart)) {
                        final DocumentStartEvent dse = (DocumentStartEvent)event;
                        final Integer[] versionInts = dse.getVersion();
                        final IRubyObject version = (versionInts == null) ? runtime.getNil() : RubyArray.newArray(runtime, runtime.newFixnum(versionInts[0]), runtime.newFixnum(versionInts[1]));
                        final Map<String, String> tagsMap = dse.getTags();
                        final RubyArray tags = RubyArray.newArray(runtime);
                        if (tags.size() > 0) {
                            for (final Map.Entry<String, String> tag : tagsMap.entrySet()) {
                                tags.append(RubyArray.newArray(runtime, RubyString.newString(runtime, tag.getKey()), RubyString.newString(runtime, tag.getValue())));
                            }
                        }
                        RuntimeHelpers.invoke(context, handler, "start_document", version, tags, runtime.newBoolean(dse.getExplicit()));
                    }
                    else if (event.is(Event.ID.DocumentEnd)) {
                        final DocumentEndEvent dee = (DocumentEndEvent)event;
                        RuntimeHelpers.invoke(context, handler, "end_document", runtime.newBoolean(dee.getExplicit()));
                    }
                    else if (event.is(Event.ID.Alias)) {
                        final AliasEvent ae = (AliasEvent)event;
                        IRubyObject alias = runtime.getNil();
                        if (ae.getAnchor() != null) {
                            alias = RubyString.newString(runtime, ae.getAnchor());
                        }
                        RuntimeHelpers.invoke(context, handler, "alias", alias);
                    }
                    else if (event.is(Event.ID.Scalar)) {
                        final ScalarEvent se = (ScalarEvent)event;
                        final IRubyObject anchor = (se.getAnchor() == null) ? runtime.getNil() : RubyString.newString(runtime, se.getAnchor());
                        final IRubyObject tag2 = (se.getTag() == null) ? runtime.getNil() : RubyString.newString(runtime, se.getTag());
                        final IRubyObject plain_implicit = runtime.newBoolean(se.getImplicit().isFirst());
                        final IRubyObject quoted_implicit = runtime.newBoolean(se.getImplicit().isSecond());
                        final IRubyObject style = runtime.newFixnum(se.getStyle());
                        final IRubyObject val = RubyString.newString(runtime, se.getValue());
                        RuntimeHelpers.invoke(context, handler, "scalar", val, anchor, tag2, plain_implicit, quoted_implicit, style);
                    }
                    else if (event.is(Event.ID.SequenceStart)) {
                        final SequenceStartEvent sse = (SequenceStartEvent)event;
                        final IRubyObject anchor = (sse.getAnchor() == null) ? runtime.getNil() : RubyString.newString(runtime, sse.getAnchor());
                        final IRubyObject tag2 = (sse.getTag() == null) ? runtime.getNil() : RubyString.newString(runtime, sse.getTag());
                        final IRubyObject implicit = runtime.newBoolean(sse.getImplicit());
                        final IRubyObject style2 = runtime.newFixnum(((boolean)sse.getFlowStyle()) ? 1 : 0);
                        RuntimeHelpers.invoke(context, handler, "start_sequence", anchor, tag2, implicit, style2);
                    }
                    else if (event.is(Event.ID.SequenceEnd)) {
                        RuntimeHelpers.invoke(context, handler, "end_sequence");
                    }
                    else if (event.is(Event.ID.MappingStart)) {
                        final MappingStartEvent mse = (MappingStartEvent)event;
                        final IRubyObject anchor = (mse.getAnchor() == null) ? runtime.getNil() : RubyString.newString(runtime, mse.getAnchor());
                        final IRubyObject tag2 = (mse.getTag() == null) ? runtime.getNil() : RubyString.newString(runtime, mse.getTag());
                        final IRubyObject implicit = runtime.newBoolean(mse.getImplicit());
                        final IRubyObject style2 = runtime.newFixnum(((boolean)mse.getFlowStyle()) ? 1 : 0);
                        RuntimeHelpers.invoke(context, handler, "start_mapping", anchor, tag2, implicit, style2);
                    }
                    else if (event.is(Event.ID.MappingEnd)) {
                        RuntimeHelpers.invoke(context, handler, "end_mapping");
                    }
                    else {
                        if (event.is(Event.ID.StreamEnd)) {
                            break;
                        }
                        continue;
                    }
                }
                RuntimeHelpers.invoke(context, handler, "end_stream");
            }
            catch (ParserException pe) {
                parser = null;
                RubyKernel.raise(context, runtime.getKernel(), new IRubyObject[] { runtime.getModule("Psych").getConstant("SyntaxError"), runtime.newString(pe.getLocalizedMessage()) }, Block.NULL_BLOCK);
                continue;
            }
            catch (ScannerException se2) {
                parser = null;
                final StringBuilder message = new StringBuilder("syntax error");
                if (se2.getProblemMark() != null) {
                    message.append(se2.getProblemMark().toString());
                }
                throw runtime.newArgumentError(message.toString());
            }
            break;
        }
        return this;
    }
    
    static {
        YAML_UTF8_ENCODING = UTF8Encoding.INSTANCE.getIndex();
        YAML_UTF16LE_ENCODING = UTF16LEEncoding.INSTANCE.getIndex();
        YAML_UTF16BE_ENCODING = UTF16BEEncoding.INSTANCE.getIndex();
    }
}
