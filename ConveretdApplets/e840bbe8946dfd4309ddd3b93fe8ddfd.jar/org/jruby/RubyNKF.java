// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Iterator;
import java.util.ArrayList;
import org.jcodings.specific.ASCIIEncoding;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import org.jruby.util.Pack;
import org.jcodings.specific.UTF32BEEncoding;
import org.jcodings.specific.UTF16BEEncoding;
import org.jcodings.specific.UTF8Encoding;
import org.jcodings.specific.ISO8859_1Encoding;
import org.jcodings.specific.CP1251Encoding;
import org.jcodings.specific.EUCJPEncoding;
import org.jcodings.Encoding;
import org.jcodings.specific.SJISEncoding;
import java.util.HashMap;
import java.util.Map;
import org.jruby.util.KCode;
import org.jruby.anno.JRubyMethod;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "NKF" })
public class RubyNKF
{
    public static final NKFCharset AUTO;
    public static final NKFCharset JIS;
    public static final NKFCharset EUC;
    public static final NKFCharset SJIS;
    public static final NKFCharset BINARY;
    public static final NKFCharset NOCONV;
    public static final NKFCharset UNKNOWN;
    public static final NKFCharset ASCII;
    public static final NKFCharset UTF8;
    public static final NKFCharset UTF16;
    public static final NKFCharset UTF32;
    public static final NKFCharset OTHER;
    public static final NKFCharset BASE64;
    public static final NKFCharset QENCODE;
    public static final NKFCharset MIME_DETECT;
    private static final ByteList BEGIN_MIME_STRING;
    private static final ByteList END_MIME_STRING;
    private static final ByteList PACK_BASE64;
    private static final ByteList PACK_QENCODE;
    
    public static void createNKF(final Ruby runtime) {
        final RubyModule nkfModule = runtime.defineModule("NKF");
        nkfModule.defineConstant("AUTO", RubyFixnum.newFixnum(runtime, RubyNKF.AUTO.getValue()));
        nkfModule.defineConstant("JIS", RubyFixnum.newFixnum(runtime, RubyNKF.JIS.getValue()));
        nkfModule.defineConstant("EUC", RubyFixnum.newFixnum(runtime, RubyNKF.EUC.getValue()));
        nkfModule.defineConstant("SJIS", RubyFixnum.newFixnum(runtime, RubyNKF.SJIS.getValue()));
        nkfModule.defineConstant("BINARY", RubyFixnum.newFixnum(runtime, RubyNKF.BINARY.getValue()));
        nkfModule.defineConstant("NOCONV", RubyFixnum.newFixnum(runtime, RubyNKF.NOCONV.getValue()));
        nkfModule.defineConstant("UNKNOWN", RubyFixnum.newFixnum(runtime, RubyNKF.UNKNOWN.getValue()));
        nkfModule.defineConstant("ASCII", RubyFixnum.newFixnum(runtime, RubyNKF.ASCII.getValue()));
        nkfModule.defineConstant("UTF8", RubyFixnum.newFixnum(runtime, RubyNKF.UTF8.getValue()));
        nkfModule.defineConstant("UTF16", RubyFixnum.newFixnum(runtime, RubyNKF.UTF16.getValue()));
        nkfModule.defineConstant("UTF32", RubyFixnum.newFixnum(runtime, RubyNKF.UTF32.getValue()));
        nkfModule.defineConstant("OTHER", RubyFixnum.newFixnum(runtime, RubyNKF.OTHER.getValue()));
        final RubyString version = runtime.newString("2.0.7 (JRuby 2007-05-11)");
        final RubyString nkfVersion = runtime.newString("2.0.7");
        final RubyString nkfDate = runtime.newString("2007-05-11");
        final ThreadContext context = runtime.getCurrentContext();
        version.freeze(context);
        nkfVersion.freeze(context);
        nkfDate.freeze(context);
        nkfModule.defineAnnotatedMethods(RubyNKF.class);
    }
    
    @JRubyMethod(name = { "guess" }, required = 1, module = true)
    public static IRubyObject guess(final ThreadContext context, final IRubyObject recv, final IRubyObject s) {
        final Ruby runtime = context.getRuntime();
        if (!s.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + s.getMetaClass() + " into String");
        }
        final ByteList bytes = s.convertToString().getByteList();
        final ByteBuffer buf = ByteBuffer.wrap(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
        CharsetDecoder decoder;
        try {
            decoder = Charset.forName("x-JISAutoDetect").newDecoder();
        }
        catch (UnsupportedCharsetException e) {
            throw runtime.newStandardError("charsets.jar is required to use NKF#guess. Please install JRE which supports m17n.");
        }
        try {
            decoder.decode(buf);
        }
        catch (CharacterCodingException e2) {
            return runtime.newFixnum(RubyNKF.UNKNOWN.getValue());
        }
        if (!decoder.isCharsetDetected()) {
            return runtime.newFixnum(RubyNKF.UNKNOWN.getValue());
        }
        final Charset charset = decoder.detectedCharset();
        final String name = charset.name();
        if ("Shift_JIS".equals(name)) {
            return runtime.newFixnum(RubyNKF.SJIS.getValue());
        }
        if ("windows-31j".equals(name)) {
            return runtime.newFixnum(RubyNKF.SJIS.getValue());
        }
        if ("EUC-JP".equals(name)) {
            return runtime.newFixnum(RubyNKF.EUC.getValue());
        }
        if ("ISO-2022-JP".equals(name)) {
            return runtime.newFixnum(RubyNKF.JIS.getValue());
        }
        return runtime.newFixnum(RubyNKF.UNKNOWN.getValue());
    }
    
    @JRubyMethod(name = { "guess1" }, required = 1, module = true)
    public static IRubyObject guess1(final ThreadContext context, final IRubyObject recv, final IRubyObject str) {
        return guess(context, recv, str);
    }
    
    @JRubyMethod(name = { "guess2" }, required = 1, module = true)
    public static IRubyObject guess2(final ThreadContext context, final IRubyObject recv, final IRubyObject str) {
        return guess(context, recv, str);
    }
    
    @JRubyMethod(name = { "nkf" }, required = 2, module = true)
    public static IRubyObject nkf(final ThreadContext context, final IRubyObject recv, final IRubyObject opt, final IRubyObject str) {
        final Ruby runtime = context.getRuntime();
        if (!opt.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + opt.getMetaClass() + " into String");
        }
        if (!str.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + str.getMetaClass() + " into String");
        }
        final Map<String, NKFCharset> options = parseOpt(opt.convertToString().toString());
        if (options.get("input").getValue() == RubyNKF.AUTO.getValue()) {
            final KCode kcode = runtime.getKCode();
            if (kcode == KCode.SJIS) {
                options.put("input", RubyNKF.SJIS);
            }
            else if (kcode == KCode.EUC) {
                options.put("input", RubyNKF.EUC);
            }
            else if (kcode == KCode.UTF8) {
                options.put("input", RubyNKF.UTF8);
            }
        }
        final ByteList bstr = str.convertToString().getByteList();
        Converter converter = null;
        if (Converter.isMimeText(bstr, options)) {
            converter = new MimeConverter(context, options);
        }
        else {
            converter = new DefaultConverter(context, options);
        }
        RubyString result = converter.convert(bstr);
        if (options.get("mime-encode") == RubyNKF.BASE64) {
            result = encodeMimeString(runtime, result, RubyNKF.PACK_BASE64);
        }
        else if (options.get("mime-encode") == RubyNKF.QENCODE) {
            result = encodeMimeString(runtime, result, RubyNKF.PACK_QENCODE);
        }
        return result;
    }
    
    private static int optionUTF(final String s, final int pos) {
        int n = 8;
        final int first = pos + 1;
        final int second = pos + 2;
        if (first < s.length() && Character.isDigit(s.charAt(first))) {
            n = Character.digit(s.charAt(first), 10);
            if (second < s.length() && Character.isDigit(s.charAt(second))) {
                n *= 10;
                n += Character.digit(s.charAt(second), 10);
            }
        }
        return n;
    }
    
    private static Map<String, NKFCharset> parseOpt(final String s) {
        final Map<String, NKFCharset> options = new HashMap<String, NKFCharset>();
        options.put("input", RubyNKF.AUTO);
        options.put("output", RubyNKF.JIS);
        options.put("mime-decode", RubyNKF.MIME_DETECT);
        options.put("mime-encode", RubyNKF.NOCONV);
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case 'b': {}
                case 'j': {
                    options.put("output", RubyNKF.JIS);
                    break;
                }
                case 's': {
                    options.put("output", RubyNKF.SJIS);
                    break;
                }
                case 'e': {
                    options.put("output", RubyNKF.EUC);
                    break;
                }
                case 'w': {
                    final int n = optionUTF(s, i);
                    if (n == 32) {
                        options.put("output", RubyNKF.UTF32);
                    }
                    else if (n == 16) {
                        options.put("output", RubyNKF.UTF16);
                    }
                    else {
                        options.put("output", RubyNKF.UTF8);
                    }
                    break;
                }
                case 'J': {
                    options.put("input", RubyNKF.JIS);
                    break;
                }
                case 'S': {
                    options.put("input", RubyNKF.SJIS);
                    break;
                }
                case 'E': {
                    options.put("input", RubyNKF.EUC);
                    break;
                }
                case 'W': {
                    final int n = optionUTF(s, i);
                    if (n == 32) {
                        options.put("input", RubyNKF.UTF32);
                    }
                    else if (n == 16) {
                        options.put("input", RubyNKF.UTF16);
                    }
                    else {
                        options.put("input", RubyNKF.UTF8);
                    }
                }
                case 't': {}
                case 'r': {}
                case 'm': {
                    if (i + 1 >= s.length()) {
                        options.put("mime-decode", RubyNKF.MIME_DETECT);
                        break;
                    }
                    switch (s.charAt(i + 1)) {
                        case 'B': {
                            options.put("mime-decode", RubyNKF.BASE64);
                            break;
                        }
                        case 'Q': {
                            options.put("mime-decode", RubyNKF.QENCODE);
                        }
                        case '0': {
                            options.put("mime-decode", RubyNKF.NOCONV);
                            break;
                        }
                    }
                    break;
                }
                case 'M': {
                    if (i + 1 >= s.length()) {
                        options.put("mime-encode", RubyNKF.NOCONV);
                    }
                    switch (s.charAt(i + 1)) {
                        case 'B': {
                            options.put("mime-encode", RubyNKF.BASE64);
                            break;
                        }
                        case 'Q': {
                            options.put("mime-encode", RubyNKF.QENCODE);
                            break;
                        }
                    }
                }
                case 'l': {}
                case 'f': {}
                case 'F': {}
                case 'Z': {}
                case 'X': {}
                case 'x': {}
                case 'B': {}
                case 'T': {}
                case 'd': {}
                case 'c': {}
                case 'I': {}
                case '-': {
                    if (s.charAt(i + 1) == '-') {}
                    break;
                }
            }
        }
        return options;
    }
    
    static {
        AUTO = new NKFCharset(0, "x-JISAutoDetect", SJISEncoding.INSTANCE);
        JIS = new NKFCharset(1, "iso-2022-jp", SJISEncoding.INSTANCE);
        EUC = new NKFCharset(2, "EUC-JP", EUCJPEncoding.INSTANCE);
        SJIS = new NKFCharset(3, "Windows-31J", CP1251Encoding.INSTANCE);
        BINARY = new NKFCharset(4, null, null);
        NOCONV = new NKFCharset(4, null, null);
        UNKNOWN = new NKFCharset(0, null, null);
        ASCII = new NKFCharset(5, "iso-8859-1", ISO8859_1Encoding.INSTANCE);
        UTF8 = new NKFCharset(6, "UTF-8", UTF8Encoding.INSTANCE);
        UTF16 = new NKFCharset(8, "UTF-16", UTF16BEEncoding.INSTANCE);
        UTF32 = new NKFCharset(12, "UTF-32", UTF32BEEncoding.INSTANCE);
        OTHER = new NKFCharset(16, null, null);
        BASE64 = new NKFCharset(20, "base64", null);
        QENCODE = new NKFCharset(21, "qencode", null);
        MIME_DETECT = new NKFCharset(22, "MimeAutoDetect", null);
        BEGIN_MIME_STRING = new ByteList(ByteList.plain("=?"));
        END_MIME_STRING = new ByteList(ByteList.plain("?="));
        PACK_BASE64 = new ByteList(ByteList.plain("m"));
        PACK_QENCODE = new ByteList(ByteList.plain("M"));
    }
    
    public static class NKFCharset
    {
        private final int value;
        private final String charset;
        private final Encoding encoding;
        
        public NKFCharset(final int v, final String c, final Encoding encoding) {
            this.value = v;
            this.charset = c;
            this.encoding = encoding;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public String getCharset() {
            return this.charset;
        }
        
        public Encoding getEncoding() {
            return this.encoding;
        }
    }
    
    abstract static class Converter
    {
        protected ThreadContext context;
        protected Map<String, NKFCharset> options;
        
        public Converter(final ThreadContext ctx, final Map<String, NKFCharset> opt) {
            this.context = ctx;
            this.options = opt;
        }
        
        static boolean isMimeText(final ByteList str, final Map<String, NKFCharset> options) {
            return str.length() > 6 && options.get("mime-decode") != RubyNKF.NOCONV && str.indexOf(RubyNKF.BEGIN_MIME_STRING) >= 0 && str.lastIndexOf(RubyNKF.END_MIME_STRING) >= 0;
        }
        
        private static RubyString encodeMimeString(final Ruby runtime, final RubyString str, final ByteList format) {
            final RubyArray array = RubyArray.newArray(runtime, str);
            return Pack.pack(runtime, array, format).chomp(runtime.getCurrentContext());
        }
        
        abstract RubyString convert(final ByteList p0);
        
        ByteList convert_byte(final ByteList str, final String inputCharset, final NKFCharset output) {
            final String outputCharset = output.getCharset();
            CharsetDecoder decoder;
            CharsetEncoder encoder;
            try {
                decoder = Charset.forName(inputCharset).newDecoder();
                encoder = Charset.forName(outputCharset).newEncoder();
            }
            catch (UnsupportedCharsetException e) {
                throw this.context.getRuntime().newArgumentError("invalid charset");
            }
            ByteBuffer buf = ByteBuffer.wrap(str.getUnsafeBytes(), str.begin(), str.length());
            try {
                final CharBuffer cbuf = decoder.decode(buf);
                encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
                buf = encoder.encode(cbuf);
            }
            catch (CharacterCodingException e2) {
                throw this.context.getRuntime().newArgumentError("invalid encoding");
            }
            final byte[] arr = buf.array();
            final ByteList r = new ByteList(arr, 0, buf.limit());
            r.setEncoding(output.getEncoding());
            return r;
        }
    }
    
    static class DefaultConverter extends Converter
    {
        public DefaultConverter(final ThreadContext ctx, final Map<String, NKFCharset> opt) {
            super(ctx, opt);
        }
        
        RubyString convert(final ByteList str) {
            final NKFCharset input = this.options.get("input");
            final NKFCharset output = this.options.get("output");
            final ByteList b = this.convert_byte(str, input.getCharset(), output);
            return this.context.getRuntime().newString(b);
        }
    }
    
    static class MimeConverter extends Converter
    {
        public MimeConverter(final ThreadContext ctx, final Map<String, NKFCharset> opt) {
            super(ctx, opt);
        }
        
        private String detectCharset(final String charset) {
            if (charset.compareToIgnoreCase(RubyNKF.UTF8.getCharset()) == 0) {
                return RubyNKF.UTF8.getCharset();
            }
            if (charset.compareToIgnoreCase(RubyNKF.JIS.getCharset()) == 0) {
                return RubyNKF.JIS.getCharset();
            }
            if (charset.compareToIgnoreCase(RubyNKF.EUC.getCharset()) == 0) {
                return RubyNKF.EUC.getCharset();
            }
            return RubyNKF.ASCII.getCharset();
        }
        
        private ByteList decodeMimeString(final String str) {
            final String[] mime = str.split("^=\\?|\\?|\\?=$");
            final String charset = this.detectCharset(mime[1]);
            final int encode = mime[2].charAt(0);
            final ByteList body = new ByteList(mime[3].getBytes(), ASCIIEncoding.INSTANCE);
            RubyArray array = null;
            if (66 == encode || 98 == encode) {
                array = Pack.unpack(this.context.getRuntime(), body, RubyNKF.PACK_BASE64);
            }
            else {
                array = Pack.unpack(this.context.getRuntime(), body, RubyNKF.PACK_QENCODE);
            }
            final RubyString s = (RubyString)array.entry(0);
            final ByteList decodeStr = s.asString().getByteList();
            return this.convert_byte(decodeStr, charset, this.options.get("output"));
        }
        
        RubyString makeRubyString(final ArrayList<ByteList> list) {
            final ByteList r = new ByteList();
            for (final ByteList l : list) {
                r.append(l);
            }
            return this.context.getRuntime().newString(r);
        }
        
        RubyString convert(final ByteList str) {
            final String s = str.toString();
            final String[] token = s.split("\\s");
            final ArrayList<ByteList> raw_data = new ArrayList<ByteList>();
            for (int i = 0; i < token.length; ++i) {
                raw_data.add(this.decodeMimeString(token[i]));
            }
            return this.makeRubyString(raw_data);
        }
    }
}
