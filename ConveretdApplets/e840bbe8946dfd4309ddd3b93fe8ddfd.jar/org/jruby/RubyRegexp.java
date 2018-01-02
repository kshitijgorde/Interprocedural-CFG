// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.SoftReference;
import org.jruby.runtime.marshal.MarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.UnmarshalStream;
import org.joni.Region;
import java.util.Iterator;
import org.joni.NameEntry;
import org.joni.exception.JOniException;
import org.joni.Matcher;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Visibility;
import org.jruby.anno.FrameField;
import org.jruby.runtime.Arity;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jcodings.specific.UTF8Encoding;
import org.jruby.util.Pack;
import org.jruby.util.Sprintf;
import org.jruby.util.StringSupport;
import org.jruby.common.IRubyWarnings;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jcodings.specific.USASCIIEncoding;
import java.util.Map;
import org.joni.WarnCallback;
import org.joni.Syntax;
import org.jcodings.Encoding;
import org.jruby.util.KCode;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.RegexpOptions;
import org.jruby.util.ByteList;
import org.joni.Regex;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.encoding.EncodingCapable;
import org.jruby.parser.ReOptions;

@JRubyClass(name = { "Regexp" })
public class RubyRegexp extends RubyObject implements ReOptions, EncodingCapable
{
    private Regex pattern;
    private ByteList str;
    private RegexpOptions options;
    public static final int ARG_ENCODING_FIXED = 16;
    private static final RegexpCache patternCache;
    private static final RegexpCache quotedPatternCache;
    private static final RegexpCache preprocessedPatternCache;
    private static ObjectAllocator REGEXP_ALLOCATOR;
    private static final int EMBEDDABLE = 7;
    private static String[] NO_NAMES;
    
    public void setLiteral() {
        this.options.setLiteral(true);
    }
    
    public void clearLiteral() {
        this.options.setLiteral(false);
    }
    
    public boolean isLiteral() {
        return this.options.isLiteral();
    }
    
    public boolean isKCodeDefault() {
        return this.options.isKcodeDefault();
    }
    
    public void setEncodingNone() {
        this.options.setEncodingNone(true);
    }
    
    public void clearEncodingNone() {
        this.options.setEncodingNone(false);
    }
    
    public boolean isEncodingNone() {
        return this.options.isEncodingNone();
    }
    
    public KCode getKCode() {
        return this.options.getKCode();
    }
    
    public Encoding getEncoding() {
        return this.pattern.getEncoding();
    }
    
    public void setEncoding(final Encoding encoding) {
    }
    
    private static Regex makeRegexp(final Ruby runtime, final ByteList bytes, final RegexpOptions options, final Encoding enc) {
        try {
            final int p = bytes.getBegin();
            return new Regex(bytes.getUnsafeBytes(), p, p + bytes.getRealSize(), options.toJoniOptions(), enc, Syntax.DEFAULT, runtime.getWarnings());
        }
        catch (Exception e) {
            if (runtime.is1_9()) {
                raiseRegexpError19(runtime, bytes, enc, options, e.getMessage());
            }
            else {
                raiseRegexpError(runtime, bytes, enc, options, e.getMessage());
            }
            return null;
        }
    }
    
    static Regex getRegexpFromCache(final Ruby runtime, final ByteList bytes, final Encoding enc, final RegexpOptions options) {
        final Map<ByteList, Regex> cache = RubyRegexp.patternCache.get();
        Regex regex = cache.get(bytes);
        if (regex != null && regex.getEncoding() == enc && regex.getOptions() == options.toJoniOptions()) {
            return regex;
        }
        regex = makeRegexp(runtime, bytes, options, enc);
        cache.put(bytes, regex);
        return regex;
    }
    
    static Regex getQuotedRegexpFromCache(final Ruby runtime, final ByteList bytes, final Encoding enc, final RegexpOptions options) {
        final Map<ByteList, Regex> cache = RubyRegexp.quotedPatternCache.get();
        Regex regex = cache.get(bytes);
        if (regex != null && regex.getEncoding() == enc && regex.getOptions() == options.toJoniOptions()) {
            return regex;
        }
        regex = makeRegexp(runtime, quote(bytes, enc), options, enc);
        cache.put(bytes, regex);
        return regex;
    }
    
    static Regex getQuotedRegexpFromCache19(final Ruby runtime, final ByteList bytes, final RegexpOptions options, final boolean asciiOnly) {
        final Map<ByteList, Regex> cache = RubyRegexp.quotedPatternCache.get();
        Regex regex = cache.get(bytes);
        final Encoding enc = asciiOnly ? USASCIIEncoding.INSTANCE : bytes.getEncoding();
        if (regex != null && regex.getEncoding() == enc && regex.getOptions() == options.toJoniOptions()) {
            return regex;
        }
        final ByteList quoted = quote19(bytes, asciiOnly);
        regex = makeRegexp(runtime, quoted, options, quoted.getEncoding());
        regex.setUserObject(quoted);
        cache.put(bytes, regex);
        return regex;
    }
    
    private static Regex getPreprocessedRegexpFromCache(final Ruby runtime, final ByteList bytes, final Encoding enc, final RegexpOptions options, final ErrorMode mode) {
        final Map<ByteList, Regex> cache = RubyRegexp.preprocessedPatternCache.get();
        Regex regex = cache.get(bytes);
        if (regex != null && regex.getEncoding() == enc && regex.getOptions() == options.toJoniOptions()) {
            return regex;
        }
        final ByteList preprocessed = preprocess(runtime, bytes, enc, new Encoding[] { null }, ErrorMode.RAISE);
        regex = makeRegexp(runtime, preprocessed, options, enc);
        regex.setUserObject(preprocessed);
        cache.put(bytes, regex);
        return regex;
    }
    
    public static RubyClass createRegexpClass(final Ruby runtime) {
        final RubyClass regexpClass = runtime.defineClass("Regexp", runtime.getObject(), RubyRegexp.REGEXP_ALLOCATOR);
        runtime.setRegexp(regexpClass);
        regexpClass.index = 9;
        regexpClass.setReifiedClass(RubyRegexp.class);
        regexpClass.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyRegexp;
            }
        };
        regexpClass.defineConstant("IGNORECASE", runtime.newFixnum(1));
        regexpClass.defineConstant("EXTENDED", runtime.newFixnum(2));
        regexpClass.defineConstant("MULTILINE", runtime.newFixnum(4));
        if (runtime.is1_9()) {
            regexpClass.defineConstant("FIXEDENCODING", runtime.newFixnum(16));
        }
        regexpClass.defineAnnotatedMethods(RubyRegexp.class);
        return regexpClass;
    }
    
    public int getNativeTypeIndex() {
        return 9;
    }
    
    private RubyRegexp(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.str = ByteList.EMPTY_BYTELIST;
        this.options = new RegexpOptions();
    }
    
    private RubyRegexp(final Ruby runtime) {
        super(runtime, runtime.getRegexp());
        this.str = ByteList.EMPTY_BYTELIST;
        this.options = new RegexpOptions();
    }
    
    private RubyRegexp(final Ruby runtime, final ByteList str) {
        this(runtime);
        this.str = str;
        this.pattern = getRegexpFromCache(runtime, str, this.getEncoding(runtime, str), RegexpOptions.NULL_OPTIONS);
    }
    
    private RubyRegexp(final Ruby runtime, final ByteList str, final RegexpOptions options) {
        this(runtime);
        if (runtime.is1_9()) {
            this.initializeCommon19(str, str.getEncoding(), options);
        }
        else {
            this.options = options;
            this.str = str;
            this.pattern = getRegexpFromCache(runtime, str, this.getEncoding(runtime, str), options);
        }
    }
    
    private Encoding getEncoding(final Ruby runtime, final ByteList str) {
        if (runtime.is1_9()) {
            return str.getEncoding();
        }
        if (this.options.isKcodeDefault()) {
            return runtime.getKCode().getEncoding();
        }
        return this.options.getKCode().getEncoding();
    }
    
    public static RubyRegexp newRegexp(final Ruby runtime, final String pattern, final RegexpOptions options) {
        return newRegexp(runtime, ByteList.create(pattern), options);
    }
    
    public static RubyRegexp newRegexp(final Ruby runtime, final ByteList pattern, final RegexpOptions options) {
        try {
            return new RubyRegexp(runtime, pattern, options);
        }
        catch (RaiseException re) {
            throw runtime.newSyntaxError(re.getMessage());
        }
    }
    
    public static RubyRegexp newDRegexp(final Ruby runtime, final RubyString pattern, final RegexpOptions options) {
        try {
            return new RubyRegexp(runtime, pattern.getByteList(), options);
        }
        catch (RaiseException re) {
            throw runtime.newRegexpError(re.getMessage());
        }
    }
    
    public static RubyRegexp newDRegexp(final Ruby runtime, final RubyString pattern, final int joniOptions) {
        try {
            final RegexpOptions options = RegexpOptions.fromJoniOptions(joniOptions);
            return new RubyRegexp(runtime, pattern.getByteList(), options);
        }
        catch (RaiseException re) {
            throw runtime.newRegexpError(re.getMessage());
        }
    }
    
    public static RubyRegexp newRegexp(final Ruby runtime, final ByteList pattern) {
        return new RubyRegexp(runtime, pattern);
    }
    
    static RubyRegexp newRegexp(final Ruby runtime, final ByteList str, final Regex pattern) {
        final RubyRegexp regexp = new RubyRegexp(runtime);
        regexp.str = str;
        regexp.options = RegexpOptions.fromJoniOptions(pattern.getOptions());
        regexp.pattern = pattern;
        return regexp;
    }
    
    static RubyRegexp newDummyRegexp(final Ruby runtime, final Regex regex) {
        final RubyRegexp regexp = new RubyRegexp(runtime);
        regexp.pattern = regex;
        regexp.str = ByteList.EMPTY_BYTELIST;
        regexp.options.setFixed(true);
        return regexp;
    }
    
    private RegexpOptions getOptions() {
        this.check();
        return this.options;
    }
    
    final Regex getPattern() {
        this.check();
        return this.pattern;
    }
    
    private static void encodingMatchError(final Ruby runtime, final Regex pattern, final Encoding strEnc) {
        throw runtime.newEncodingCompatibilityError("incompatible encoding regexp match (" + pattern.getEncoding() + " regexp with " + strEnc + " string)");
    }
    
    private Encoding checkEncoding(final RubyString str, final boolean warn) {
        if (str.scanForCodeRange() == 96) {}
        this.check();
        Encoding enc = str.getEncoding();
        if (!enc.isAsciiCompatible()) {
            if (enc != this.pattern.getEncoding()) {
                encodingMatchError(this.getRuntime(), this.pattern, enc);
            }
        }
        else if (this.options.isFixed()) {
            if (enc != this.pattern.getEncoding() && (!this.pattern.getEncoding().isAsciiCompatible() || str.scanForCodeRange() != 32)) {
                encodingMatchError(this.getRuntime(), this.pattern, enc);
            }
            enc = this.pattern.getEncoding();
        }
        if (warn && this.isEncodingNone() && enc != ASCIIEncoding.INSTANCE && str.scanForCodeRange() != 32) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.REGEXP_MATCH_AGAINST_STRING, "regexp match /.../n against to " + enc + " string", new Object[0]);
        }
        return enc;
    }
    
    final Regex preparePattern(final RubyString str) {
        this.check();
        final Encoding enc = this.checkEncoding(str, true);
        if (enc == this.pattern.getEncoding()) {
            return this.pattern;
        }
        return getPreprocessedRegexpFromCache(this.getRuntime(), this.str, enc, this.options, ErrorMode.PREPROCESS);
    }
    
    static Regex preparePattern(final Ruby runtime, final Regex pattern, final RubyString str) {
        if (str.scanForCodeRange() == 96) {
            throw runtime.newArgumentError("invalid byte sequence in " + str.getEncoding());
        }
        final Encoding enc = str.getEncoding();
        if (!enc.isAsciiCompatible() && enc != pattern.getEncoding()) {
            encodingMatchError(runtime, pattern, enc);
        }
        if (enc == pattern.getEncoding()) {
            return pattern;
        }
        return getPreprocessedRegexpFromCache(runtime, (ByteList)pattern.getUserObject(), enc, RegexpOptions.fromJoniOptions(pattern.getOptions()), ErrorMode.PREPROCESS);
    }
    
    private static int raisePreprocessError(final Ruby runtime, final ByteList str, final String err, final ErrorMode mode) {
        switch (mode) {
            case RAISE: {
                raiseRegexpError19(runtime, str, str.getEncoding(), RegexpOptions.NULL_OPTIONS, err);
            }
            case PREPROCESS: {
                throw runtime.newArgumentError("regexp preprocess failed: " + err);
            }
            default: {
                return 0;
            }
        }
    }
    
    private static int readEscapedByte(final Ruby runtime, final byte[] to, final int toP, final byte[] bytes, int p, final int end, final ByteList str, final ErrorMode mode) {
        if (p == end || bytes[p++] != 92) {
            raisePreprocessError(runtime, str, "too short escaped multibyte character", mode);
        }
        boolean metaPrefix = false;
        boolean ctrlPrefix = false;
        int code = 0;
        Label_0764: {
        Label_0752:
            while (true) {
                if (p == end) {
                    raisePreprocessError(runtime, str, "too short escape sequence", mode);
                }
                Label_0640: {
                    switch (bytes[p++]) {
                        case 92: {
                            code = 92;
                            break Label_0764;
                        }
                        case 110: {
                            code = 10;
                            break Label_0764;
                        }
                        case 116: {
                            code = 9;
                            break Label_0764;
                        }
                        case 114: {
                            code = 13;
                            break Label_0764;
                        }
                        case 102: {
                            code = 12;
                            break Label_0764;
                        }
                        case 118: {
                            code = 11;
                            break Label_0764;
                        }
                        case 97: {
                            code = 7;
                            break Label_0764;
                        }
                        case 101: {
                            code = 27;
                            break Label_0764;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55: {
                            --p;
                            final int olen = (end < p + 3) ? (end - p) : 3;
                            code = StringSupport.scanOct(bytes, p, olen);
                            p += StringSupport.octLength(bytes, p, olen);
                            break Label_0764;
                        }
                        case 120: {
                            final int hlen = (end < p + 2) ? (end - p) : 2;
                            code = StringSupport.scanHex(bytes, p, hlen);
                            final int len = StringSupport.hexLength(bytes, p, hlen);
                            if (len < 1) {
                                raisePreprocessError(runtime, str, "invalid hex escape", mode);
                            }
                            p += len;
                            break Label_0764;
                        }
                        case 77: {
                            if (metaPrefix) {
                                raisePreprocessError(runtime, str, "duplicate meta escape", mode);
                            }
                            metaPrefix = true;
                            if (p + 1 >= end || bytes[p++] != 45 || (bytes[p] & 0x80) != 0x0) {
                                raisePreprocessError(runtime, str, "too short meta escape", mode);
                                break Label_0640;
                            }
                            if (bytes[p] == 92) {
                                ++p;
                                continue;
                            }
                            code = (bytes[p++] & 0xFF);
                            break Label_0764;
                        }
                        case 67: {
                            if (p == end || bytes[p++] != 45) {
                                raisePreprocessError(runtime, str, "too short control escape", mode);
                            }
                        }
                        case 99: {
                            if (ctrlPrefix) {
                                raisePreprocessError(runtime, str, "duplicate control escape", mode);
                            }
                            ctrlPrefix = true;
                            if (p >= end || (bytes[p] & 0x80) != 0x0) {
                                raisePreprocessError(runtime, str, "too short control escape", mode);
                                break Label_0752;
                            }
                            if (bytes[p] == 92) {
                                ++p;
                                continue;
                            }
                            code = (bytes[p++] & 0xFF);
                            break Label_0764;
                        }
                        default: {
                            break Label_0752;
                        }
                    }
                }
            }
            raisePreprocessError(runtime, str, "unexpected escape sequence", mode);
        }
        if (code < 0 || code > 255) {
            raisePreprocessError(runtime, str, "invalid escape code", mode);
        }
        if (ctrlPrefix) {
            code &= 0x1F;
        }
        if (metaPrefix) {
            code |= 0x80;
        }
        to[toP] = (byte)code;
        return p;
    }
    
    private static int unescapeEscapedNonAscii(final Ruby runtime, final ByteList to, final byte[] bytes, int p, final int end, final Encoding enc, final Encoding[] encp, final ByteList str, final ErrorMode mode) {
        byte[] chBuf;
        int chLen;
        for (chBuf = new byte[enc.maxLength()], chLen = 0, p = readEscapedByte(runtime, chBuf, chLen++, bytes, p, end, str, mode); chLen < enc.maxLength() && StringSupport.preciseLength(enc, chBuf, 0, chLen) < -1; p = readEscapedByte(runtime, chBuf, chLen++, bytes, p, end, str, mode)) {}
        final int cl = StringSupport.preciseLength(enc, chBuf, 0, chLen);
        if (cl == -1) {
            raisePreprocessError(runtime, str, "invalid multibyte escape", mode);
        }
        if (chLen > 1 || (chBuf[0] & 0x80) != 0x0) {
            to.append(chBuf, 0, chLen);
            if (encp[0] == null) {
                encp[0] = enc;
            }
            else if (encp[0] != enc) {
                raisePreprocessError(runtime, str, "escaped non ASCII character in UTF-8 regexp", mode);
            }
        }
        else {
            Sprintf.sprintf(runtime, to, "\\x%02X", chBuf[0] & 0xFF);
        }
        return p;
    }
    
    private static void checkUnicodeRange(final Ruby runtime, final int code, final ByteList str, final ErrorMode mode) {
        if ((55296 <= code && code <= 57343) || 1114111 < code) {
            raisePreprocessError(runtime, str, "invalid Unicode range", mode);
        }
    }
    
    private static void appendUtf8(final Ruby runtime, final ByteList to, final int code, final Encoding[] enc, final ByteList str, final ErrorMode mode) {
        checkUnicodeRange(runtime, code, str, mode);
        if (code < 128) {
            Sprintf.sprintf(runtime, to, "\\x%02X", code);
        }
        else {
            to.ensure(to.getRealSize() + 6);
            to.setRealSize(to.getRealSize() + Pack.utf8Decode(runtime, to.getUnsafeBytes(), to.getBegin() + to.getRealSize(), code));
            if (enc[0] == null) {
                enc[0] = UTF8Encoding.INSTANCE;
            }
            else if (!(enc[0] instanceof UTF8Encoding)) {
                raisePreprocessError(runtime, str, "UTF-8 character in non UTF-8 regexp", mode);
            }
        }
    }
    
    private static int unescapeUnicodeList(final Ruby runtime, final ByteList to, final byte[] bytes, int p, final int end, final Encoding[] encp, final ByteList str, final ErrorMode mode) {
        while (p < end && ASCIIEncoding.INSTANCE.isSpace(bytes[p] & 0xFF)) {
            ++p;
        }
        boolean hasUnicode = false;
        while (true) {
            final int code = StringSupport.scanHex(bytes, p, end - p);
            final int len = StringSupport.hexLength(bytes, p, end - p);
            if (len == 0) {
                break;
            }
            if (len > 6) {
                raisePreprocessError(runtime, str, "invalid Unicode range", mode);
            }
            p += len;
            appendUtf8(runtime, to, code, encp, str, mode);
            hasUnicode = true;
            while (p < end && ASCIIEncoding.INSTANCE.isSpace(bytes[p] & 0xFF)) {
                ++p;
            }
        }
        if (!hasUnicode) {
            raisePreprocessError(runtime, str, "invalid Unicode list", mode);
        }
        return p;
    }
    
    private static int unescapeUnicodeBmp(final Ruby runtime, final ByteList to, final byte[] bytes, final int p, final int end, final Encoding[] encp, final ByteList str, final ErrorMode mode) {
        if (p + 4 > end) {
            raisePreprocessError(runtime, str, "invalid Unicode escape", mode);
        }
        final int code = StringSupport.scanHex(bytes, p, 4);
        final int len = StringSupport.hexLength(bytes, p, 4);
        if (len != 4) {
            raisePreprocessError(runtime, str, "invalid Unicode escape", mode);
        }
        appendUtf8(runtime, to, code, encp, str, mode);
        return p + 4;
    }
    
    private static boolean unescapeNonAscii(final Ruby runtime, final ByteList to, final byte[] bytes, int p, final int end, final Encoding enc, final Encoding[] encp, final ByteList str, final ErrorMode mode) {
        boolean hasProperty = false;
        while (p < end) {
            final int cl = StringSupport.preciseLength(enc, bytes, p, end);
            if (cl <= 0) {
                raisePreprocessError(runtime, str, "invalid multibyte character", mode);
            }
            if (cl > 1 || (bytes[p] & 0x80) != 0x0) {
                to.append(bytes, p, cl);
                p += cl;
                if (encp[0] == null) {
                    encp[0] = enc;
                }
                else {
                    if (encp[0] == enc) {
                        continue;
                    }
                    raisePreprocessError(runtime, str, "non ASCII character in UTF-8 regexp", mode);
                }
            }
            else {
                int c;
                switch (c = (bytes[p++] & 0xFF)) {
                    case 92: {
                        if (p == end) {
                            raisePreprocessError(runtime, str, "too short escape sequence", mode);
                        }
                        switch (c = (bytes[p++] & 0xFF)) {
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55: {
                                if (StringSupport.scanOct(bytes, p - 1, end - (p - 1)) <= 127) {
                                    to.append(92).append(c);
                                    continue;
                                }
                            }
                            case 48:
                            case 67:
                            case 77:
                            case 99:
                            case 120: {
                                p = unescapeEscapedNonAscii(runtime, to, bytes, p - 2, end, enc, encp, str, mode);
                                continue;
                            }
                            case 117: {
                                if (p == end) {
                                    raisePreprocessError(runtime, str, "too short escape sequence", mode);
                                }
                                if (bytes[p] != 123) {
                                    p = unescapeUnicodeBmp(runtime, to, bytes, p, end, encp, str, mode);
                                    continue;
                                }
                                ++p;
                                p = unescapeUnicodeList(runtime, to, bytes, p, end, encp, str, mode);
                                if (p == end || bytes[p++] != 125) {
                                    raisePreprocessError(runtime, str, "invalid Unicode list", mode);
                                    continue;
                                }
                                continue;
                            }
                            case 112: {
                                if (encp[0] == null) {
                                    hasProperty = true;
                                }
                                to.append(92).append(c);
                                continue;
                            }
                            default: {
                                to.append(92).append(c);
                                continue;
                            }
                        }
                        break;
                    }
                    default: {
                        to.append(c);
                        continue;
                    }
                }
            }
        }
        return hasProperty;
    }
    
    private static ByteList preprocess(final Ruby runtime, final ByteList str, final Encoding enc, final Encoding[] fixedEnc, final ErrorMode mode) {
        final ByteList to = new ByteList(str.getRealSize());
        if (enc.isAsciiCompatible()) {
            fixedEnc[0] = null;
        }
        else {
            to.setEncoding(fixedEnc[0] = enc);
        }
        final boolean hasProperty = unescapeNonAscii(runtime, to, str.getUnsafeBytes(), str.getBegin(), str.getBegin() + str.getRealSize(), enc, fixedEnc, str, mode);
        if (hasProperty && fixedEnc[0] == null) {
            fixedEnc[0] = enc;
        }
        if (fixedEnc[0] != null) {
            to.setEncoding(fixedEnc[0]);
        }
        return to;
    }
    
    public static void preprocessCheck(final Ruby runtime, final ByteList bytes) {
        preprocess(runtime, bytes, bytes.getEncoding(), new Encoding[] { null }, ErrorMode.RAISE);
    }
    
    private void check() {
        if (this.pattern == null) {
            throw this.getRuntime().newTypeError("uninitialized Regexp");
        }
    }
    
    @JRubyMethod(name = { "new", "compile" }, rest = true, meta = true)
    public static RubyRegexp newInstance(final IRubyObject recv, final IRubyObject[] args) {
        final RubyClass klass = (RubyClass)recv;
        final RubyRegexp re = (RubyRegexp)klass.allocate();
        re.callInit(args, Block.NULL_BLOCK);
        return re;
    }
    
    @JRubyMethod(name = { "try_convert" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject try_convert(final ThreadContext context, final IRubyObject recv, final IRubyObject args) {
        return TypeConverter.convertToTypeWithCheck(args, context.getRuntime().getRegexp(), "to_regexp");
    }
    
    @JRubyMethod(name = { "quote", "escape" }, required = 1, optional = 1, meta = true, compat = CompatVersion.RUBY1_8)
    public static RubyString quote(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        KCode code;
        if (args.length == 1 || args[1].isNil()) {
            code = runtime.getKCode();
        }
        else {
            code = KCode.create(runtime, args[1].toString());
        }
        final RubyString src = args[0].convertToString();
        final RubyString dst = RubyString.newStringShared(runtime, quote(src.getByteList(), code.getEncoding()));
        dst.infectBy(src);
        return dst;
    }
    
    @JRubyMethod(name = { "quote", "escape" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject quote19(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final RubyString str = operandCheck(runtime, arg);
        return RubyString.newStringShared(runtime, quote19(str.getByteList(), str.isAsciiOnly()));
    }
    
    private static ByteList quote(final ByteList bs, final Encoding enc) {
        int p = bs.getBegin();
        final int end = p + bs.getRealSize();
        final byte[] bytes = bs.getUnsafeBytes();
        while (p < end) {
            final int c = bytes[p] & 0xFF;
            int cl = enc.length(bytes, p, end);
            if (cl != 1) {
                while (cl-- > 0 && p < end) {
                    ++p;
                }
                --p;
            }
            else {
                switch (c) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case 32:
                    case 35:
                    case 36:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 45:
                    case 46:
                    case 63:
                    case 91:
                    case 92:
                    case 93:
                    case 94:
                    case 123:
                    case 124:
                    case 125: {
                        final ByteList result = new ByteList(end * 2);
                        final byte[] obytes = result.getUnsafeBytes();
                        int op = p - bs.getBegin();
                        System.arraycopy(bytes, bs.getBegin(), obytes, 0, op);
                        while (p < end) {
                            final int c2 = bytes[p] & 0xFF;
                            int cl2 = enc.length(bytes, p, end);
                            Label_0699: {
                                if (cl2 != 1) {
                                    while (cl2-- > 0 && p < end) {
                                        obytes[op++] = bytes[p++];
                                    }
                                    --p;
                                }
                                else {
                                    switch (c2) {
                                        case 35:
                                        case 36:
                                        case 40:
                                        case 41:
                                        case 42:
                                        case 43:
                                        case 45:
                                        case 46:
                                        case 63:
                                        case 91:
                                        case 92:
                                        case 93:
                                        case 94:
                                        case 123:
                                        case 124:
                                        case 125: {
                                            obytes[op++] = 92;
                                            break;
                                        }
                                        case 32: {
                                            obytes[op++] = 92;
                                            obytes[op++] = 32;
                                            break Label_0699;
                                        }
                                        case 9: {
                                            obytes[op++] = 92;
                                            obytes[op++] = 116;
                                            break Label_0699;
                                        }
                                        case 10: {
                                            obytes[op++] = 92;
                                            obytes[op++] = 110;
                                            break Label_0699;
                                        }
                                        case 13: {
                                            obytes[op++] = 92;
                                            obytes[op++] = 114;
                                            break Label_0699;
                                        }
                                        case 12: {
                                            obytes[op++] = 92;
                                            obytes[op++] = 102;
                                            break Label_0699;
                                        }
                                    }
                                    obytes[op++] = (byte)c2;
                                }
                            }
                            ++p;
                        }
                        result.setRealSize(op);
                        return result;
                    }
                }
            }
            ++p;
        }
        return bs;
    }
    
    static ByteList quote19(final ByteList bs, final boolean asciiOnly) {
        int p = bs.getBegin();
        final int end = p + bs.getRealSize();
        final byte[] bytes = bs.getUnsafeBytes();
        final Encoding enc = bs.getEncoding();
        while (p < end) {
            int cl;
            int c;
            if (enc.isAsciiCompatible()) {
                cl = 1;
                c = (bytes[p] & 0xFF);
            }
            else {
                cl = StringSupport.preciseLength(enc, bytes, p, end);
                c = enc.mbcToCode(bytes, p, end);
            }
            if (!Encoding.isAscii(c)) {
                p += StringSupport.length(enc, bytes, p, end);
            }
            else {
                switch (c) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case 32:
                    case 35:
                    case 36:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 45:
                    case 46:
                    case 63:
                    case 91:
                    case 92:
                    case 93:
                    case 94:
                    case 123:
                    case 124:
                    case 125: {
                        final ByteList result = new ByteList(end * 2);
                        result.setEncoding(asciiOnly ? USASCIIEncoding.INSTANCE : bs.getEncoding());
                        final byte[] obytes = result.getUnsafeBytes();
                        int op = p - bs.getBegin();
                        System.arraycopy(bytes, bs.getBegin(), obytes, 0, op);
                        while (p < end) {
                            int cl2;
                            int c2;
                            if (enc.isAsciiCompatible()) {
                                cl2 = 1;
                                c2 = (bytes[p] & 0xFF);
                            }
                            else {
                                cl2 = StringSupport.preciseLength(enc, bytes, p, end);
                                c2 = enc.mbcToCode(bytes, p, end);
                            }
                            if (!Encoding.isAscii(c2)) {
                                int n = StringSupport.length(enc, bytes, p, end);
                                while (n-- > 0) {
                                    obytes[op++] = bytes[p++];
                                }
                            }
                            else {
                                p += cl2;
                                switch (c2) {
                                    case 35:
                                    case 36:
                                    case 40:
                                    case 41:
                                    case 42:
                                    case 43:
                                    case 45:
                                    case 46:
                                    case 63:
                                    case 91:
                                    case 92:
                                    case 93:
                                    case 94:
                                    case 123:
                                    case 124:
                                    case 125: {
                                        op += enc.codeToMbc(92, obytes, op);
                                        break;
                                    }
                                    case 32: {
                                        op += enc.codeToMbc(92, obytes, op);
                                        op += enc.codeToMbc(32, obytes, op);
                                        continue;
                                    }
                                    case 9: {
                                        op += enc.codeToMbc(92, obytes, op);
                                        op += enc.codeToMbc(116, obytes, op);
                                        continue;
                                    }
                                    case 10: {
                                        op += enc.codeToMbc(92, obytes, op);
                                        op += enc.codeToMbc(110, obytes, op);
                                        continue;
                                    }
                                    case 13: {
                                        op += enc.codeToMbc(92, obytes, op);
                                        op += enc.codeToMbc(114, obytes, op);
                                        continue;
                                    }
                                    case 12: {
                                        op += enc.codeToMbc(92, obytes, op);
                                        op += enc.codeToMbc(102, obytes, op);
                                        continue;
                                    }
                                }
                                op += enc.codeToMbc(c2, obytes, op);
                            }
                        }
                        result.setRealSize(op);
                        return result;
                    }
                    default: {
                        p += cl;
                        continue;
                    }
                }
            }
        }
        if (asciiOnly) {
            final ByteList tmp = bs.shallowDup();
            tmp.setEncoding(USASCIIEncoding.INSTANCE);
            return tmp;
        }
        return bs;
    }
    
    public static IRubyObject last_match_s(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return last_match_s(context, recv);
            }
            case 1: {
                return last_match_s(context, recv, args[0]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "last_match" }, meta = true, reads = { FrameField.BACKREF })
    public static IRubyObject last_match_s(final ThreadContext context, final IRubyObject recv) {
        final IRubyObject match = context.getCurrentScope().getBackRef(context.getRuntime());
        if (match instanceof RubyMatchData) {
            ((RubyMatchData)match).use();
        }
        return match;
    }
    
    @JRubyMethod(name = { "last_match" }, meta = true, reads = { FrameField.BACKREF })
    public static IRubyObject last_match_s(final ThreadContext context, final IRubyObject recv, final IRubyObject nth) {
        final IRubyObject match = context.getCurrentScope().getBackRef(context.getRuntime());
        if (match.isNil()) {
            return match;
        }
        return nth_match(((RubyMatchData)match).backrefNumber(nth), match);
    }
    
    @JRubyMethod(name = { "union" }, rest = true, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject union(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length == 0) {
            return newRegexp(runtime, ByteList.create("(?!)"));
        }
        IRubyObject[] realArgs = args;
        if (args.length == 1) {
            final IRubyObject v = TypeConverter.convertToTypeWithCheck(args[0], runtime.getRegexp(), "to_regexp");
            if (!v.isNil()) {
                return v;
            }
            final IRubyObject a = TypeConverter.convertToTypeWithCheck(args[0], runtime.getArray(), "to_ary");
            if (a.isNil()) {
                return newRegexp(runtime, quote(context, recv, args).getByteList());
            }
            final RubyArray aa = (RubyArray)a;
            final int len = aa.getLength();
            realArgs = new IRubyObject[len];
            for (int i = 0; i < len; ++i) {
                realArgs[i] = aa.entry(i);
            }
        }
        KCode kcode = null;
        IRubyObject kcode_re = runtime.getNil();
        final RubyString source = runtime.newString();
        for (int j = 0; j < realArgs.length; ++j) {
            if (0 < j) {
                source.cat((byte)124);
            }
            IRubyObject v2 = TypeConverter.convertToTypeWithCheck(realArgs[j], runtime.getRegexp(), "to_regexp");
            if (!v2.isNil()) {
                if (!((RubyRegexp)v2).isKCodeDefault()) {
                    if (kcode == null) {
                        kcode_re = v2;
                        kcode = ((RubyRegexp)v2).options.getKCode();
                    }
                    else if (((RubyRegexp)v2).options.getKCode() != kcode) {
                        final IRubyObject str1 = kcode_re.inspect();
                        final IRubyObject str2 = v2.inspect();
                        throw runtime.newArgumentError("mixed kcode " + str1 + " and " + str2);
                    }
                }
                v2 = ((RubyRegexp)v2).to_s();
            }
            else {
                v2 = quote(context, recv, new IRubyObject[] { realArgs[j] });
            }
            source.append(v2);
        }
        final IRubyObject[] _args = { source, runtime.getNil(), null };
        if (kcode == null) {
            _args[2] = runtime.getNil();
        }
        else if (kcode == KCode.NONE) {
            _args[2] = runtime.newString("n");
        }
        else if (kcode == KCode.EUC) {
            _args[2] = runtime.newString("e");
        }
        else if (kcode == KCode.SJIS) {
            _args[2] = runtime.newString("s");
        }
        else if (kcode == KCode.UTF8) {
            _args[2] = runtime.newString("u");
        }
        return recv.callMethod(context, "new", _args);
    }
    
    @JRubyMethod(name = { "union" }, rest = true, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject union19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (args.length == 0) {
            return newRegexp(runtime, ByteList.create("(?!)"));
        }
        IRubyObject[] realArgs = args;
        if (args.length == 1) {
            final IRubyObject v = TypeConverter.convertToTypeWithCheck(args[0], runtime.getRegexp(), "to_regexp");
            if (!v.isNil()) {
                return v;
            }
            final IRubyObject a = TypeConverter.convertToTypeWithCheck(args[0], runtime.getArray(), "to_ary");
            if (a.isNil()) {
                return newRegexp(runtime, ((RubyString)quote19(context, recv, args[0])).getByteList());
            }
            final RubyArray aa = (RubyArray)a;
            final int len = aa.getLength();
            realArgs = new IRubyObject[len];
            for (int i = 0; i < len; ++i) {
                realArgs[i] = aa.entry(i);
            }
        }
        boolean hasAsciiOnly = false;
        final RubyString source = runtime.newString();
        Encoding hasAsciiCompatFixed = null;
        Encoding hasAsciiIncompat = null;
        for (int i = 0; i < realArgs.length; ++i) {
            if (0 < i) {
                source.cat((byte)124);
            }
            IRubyObject v2 = TypeConverter.convertToTypeWithCheck(realArgs[i], runtime.getRegexp(), "to_regexp");
            Encoding enc;
            if (!v2.isNil()) {
                final RubyRegexp regex = (RubyRegexp)v2;
                enc = regex.getEncoding();
                if (!enc.isAsciiCompatible()) {
                    if (hasAsciiIncompat == null) {
                        hasAsciiIncompat = enc;
                    }
                    else if (hasAsciiIncompat != enc) {
                        throw runtime.newArgumentError("incompatible encodings: " + hasAsciiIncompat + " and " + enc);
                    }
                }
                else if (regex.getOptions().isFixed()) {
                    if (hasAsciiCompatFixed == null) {
                        hasAsciiCompatFixed = enc;
                    }
                    else if (hasAsciiCompatFixed != enc) {
                        throw runtime.newArgumentError("incompatible encodings: " + hasAsciiCompatFixed + " and " + enc);
                    }
                }
                else {
                    hasAsciiOnly = true;
                }
                v2 = regex.to_s();
            }
            else {
                final RubyString str = realArgs[i].convertToString();
                enc = str.getEncoding();
                if (!enc.isAsciiCompatible()) {
                    if (hasAsciiIncompat == null) {
                        hasAsciiIncompat = enc;
                    }
                    else if (hasAsciiIncompat != enc) {
                        throw runtime.newArgumentError("incompatible encodings: " + hasAsciiIncompat + " and " + enc);
                    }
                }
                else if (str.isAsciiOnly()) {
                    hasAsciiOnly = true;
                }
                else if (hasAsciiCompatFixed == null) {
                    hasAsciiCompatFixed = enc;
                }
                else if (hasAsciiCompatFixed != enc) {
                    throw runtime.newArgumentError("incompatible encodings: " + hasAsciiCompatFixed + " and " + enc);
                }
                v2 = quote(context, recv, new IRubyObject[] { str });
            }
            if (hasAsciiIncompat != null) {
                if (hasAsciiOnly) {
                    throw runtime.newArgumentError("ASCII incompatible encoding: " + hasAsciiIncompat);
                }
                if (hasAsciiCompatFixed != null) {
                    throw runtime.newArgumentError("incompatible encodings: " + hasAsciiIncompat + " and " + hasAsciiCompatFixed);
                }
            }
            if (i == 0) {
                source.setEncoding(enc);
            }
            source.append(v2);
        }
        if (hasAsciiIncompat != null) {
            source.setEncoding(hasAsciiIncompat);
        }
        else if (hasAsciiCompatFixed != null) {
            source.setEncoding(hasAsciiCompatFixed);
        }
        else {
            source.setEncoding(ASCIIEncoding.INSTANCE);
        }
        return recv.callMethod(context, "new", new IRubyObject[] { source });
    }
    
    private static void raiseRegexpError(final Ruby runtime, final ByteList bytes, final Encoding enc, final RegexpOptions options, final String err) {
        throw runtime.newRegexpError(err + ": " + (Object)regexpDescription(runtime, bytes, enc, options));
    }
    
    private static ByteList regexpDescription(final Ruby runtime, final ByteList bytes, final Encoding enc, final RegexpOptions options) {
        return regexpDescription(runtime, bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getRealSize(), enc, options);
    }
    
    private static ByteList regexpDescription(final Ruby runtime, final byte[] bytes, final int start, final int len, final Encoding enc, final RegexpOptions options) {
        final ByteList description = new ByteList();
        description.append((byte)47);
        appendRegexpString(runtime, description, bytes, start, len, enc);
        description.append((byte)47);
        appendOptions(description, options);
        return description;
    }
    
    private static void raiseRegexpError19(final Ruby runtime, final ByteList bytes, final Encoding enc, final RegexpOptions options, final String err) {
        throw runtime.newRegexpError(err + ": " + (Object)regexpDescription19(runtime, bytes, options, enc));
    }
    
    static ByteList regexpDescription19(final Ruby runtime, final ByteList bytes, final RegexpOptions options, final Encoding enc) {
        return regexpDescription19(runtime, bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getRealSize(), options, enc);
    }
    
    private static ByteList regexpDescription19(final Ruby runtime, final byte[] s, final int start, final int len, final RegexpOptions options, final Encoding enc) {
        final ByteList description = new ByteList();
        description.setEncoding(enc);
        description.append((byte)47);
        appendRegexpString19(runtime, description, s, start, len, enc);
        description.append((byte)47);
        appendOptions(description, options);
        return description;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1)
    public IRubyObject initialize_copy(final IRubyObject re) {
        if (this == re) {
            return this;
        }
        this.checkFrozen();
        if (this.getMetaClass().getRealClass() != re.getMetaClass().getRealClass()) {
            throw this.getRuntime().newTypeError("wrong argument type");
        }
        final RubyRegexp regexp = (RubyRegexp)re;
        regexp.check();
        return this.getRuntime().is1_9() ? this.initializeCommon19(regexp.str, regexp.str.getEncoding(), regexp.getOptions()) : this.initializeCommon(regexp.str, regexp.getOptions());
    }
    
    private int objectAsJoniOptions(final IRubyObject arg) {
        if (arg instanceof RubyFixnum) {
            return RubyNumeric.fix2int(arg);
        }
        if (arg.isTrue()) {
            return 1;
        }
        return 0;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize_m(final IRubyObject arg) {
        if (arg instanceof RubyRegexp) {
            return this.initializeByRegexp((RubyRegexp)arg);
        }
        return this.initializeCommon(arg.convertToString().getByteList(), new RegexpOptions());
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize_m(final IRubyObject arg0, final IRubyObject arg1) {
        if (arg0 instanceof RubyRegexp) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.REGEXP_IGNORED_FLAGS, "flags ignored", new Object[0]);
            return this.initializeByRegexp((RubyRegexp)arg0);
        }
        this.options = RegexpOptions.fromJoniOptions(this.objectAsJoniOptions(arg1));
        return this.initializeCommon(arg0.convertToString().getByteList(), this.options);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize_m(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (arg0 instanceof RubyRegexp) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.REGEXP_IGNORED_FLAGS, "flags and encoding ignored", new Object[0]);
            return this.initializeByRegexp((RubyRegexp)arg0);
        }
        final int optionsInt = this.objectAsJoniOptions(arg1);
        this.options = RegexpOptions.fromJoniOptions(optionsInt);
        if (!arg2.isNil()) {
            final ByteList kcodeBytes = arg2.convertToString().getByteList();
            final char first = (kcodeBytes.length() > 0) ? kcodeBytes.charAt(0) : '\0';
            switch (first) {
                case 'N':
                case 'n': {
                    this.options.setExplicitKCode(KCode.NONE);
                    break;
                }
                case 'E':
                case 'e': {
                    this.options.setExplicitKCode(KCode.EUC);
                    break;
                }
                case 'S':
                case 's': {
                    this.options.setExplicitKCode(KCode.SJIS);
                    break;
                }
                case 'U':
                case 'u': {
                    this.options.setExplicitKCode(KCode.UTF8);
                    break;
                }
            }
        }
        return this.initializeCommon(arg0.convertToString().getByteList(), this.options);
    }
    
    private IRubyObject initializeByRegexp(final RubyRegexp regexp) {
        regexp.check();
        return this.initializeCommon(regexp.str, regexp.options);
    }
    
    private RubyRegexp initializeCommon(final ByteList bytes, final RegexpOptions newOptions) {
        final Ruby runtime = this.getRuntime();
        if (!this.isTaint() && runtime.getSafeLevel() >= 4) {
            throw runtime.newSecurityError("Insecure: can't modify regexp");
        }
        this.checkFrozen();
        if (this.isLiteral()) {
            throw runtime.newSecurityError("can't modify literal regexp");
        }
        this.options = newOptions;
        this.pattern = getRegexpFromCache(runtime, bytes, this.options.getKCode().getEncoding(), this.options);
        this.str = bytes;
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize_m19(final IRubyObject arg) {
        if (arg instanceof RubyRegexp) {
            return this.initializeByRegexp19((RubyRegexp)arg);
        }
        return this.initializeCommon19(arg.convertToString(), new RegexpOptions());
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize_m19(final IRubyObject arg0, final IRubyObject arg1) {
        if (arg0 instanceof RubyRegexp) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.REGEXP_IGNORED_FLAGS, "flags ignored", new Object[0]);
            return this.initializeByRegexp19((RubyRegexp)arg0);
        }
        this.options = RegexpOptions.fromJoniOptions(this.objectAsJoniOptions(arg1));
        return this.initializeCommon19(arg0.convertToString(), this.options);
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize_m19(final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (arg0 instanceof RubyRegexp) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.REGEXP_IGNORED_FLAGS, "flags ignored", new Object[0]);
            return this.initializeByRegexp19((RubyRegexp)arg0);
        }
        final int optionsInt = this.objectAsJoniOptions(arg1);
        final RegexpOptions newOptions = RegexpOptions.fromJoniOptions(optionsInt);
        if (!arg2.isNil()) {
            final ByteList kcodeBytes = arg2.convertToString().getByteList();
            if ((kcodeBytes.getRealSize() > 0 && kcodeBytes.getUnsafeBytes()[kcodeBytes.getBegin()] == 110) || (kcodeBytes.getRealSize() > 1 && kcodeBytes.getUnsafeBytes()[kcodeBytes.getBegin() + 1] == 78)) {
                return this.initializeCommon19(arg0.convertToString().getByteList(), ASCIIEncoding.INSTANCE, newOptions);
            }
            this.getRuntime().getWarnings().warn("encoding option is ignored - " + (Object)kcodeBytes);
        }
        return this.initializeCommon19(arg0.convertToString(), newOptions);
    }
    
    private IRubyObject initializeByRegexp19(final RubyRegexp regexp) {
        regexp.check();
        final RegexpOptions newOptions = (RegexpOptions)regexp.getOptions().clone();
        newOptions.setLiteral(false);
        return this.initializeCommon19(regexp.str, regexp.getEncoding(), newOptions);
    }
    
    private RubyRegexp initializeCommon19(final RubyString str, final RegexpOptions options) {
        final ByteList bytes = str.getByteList();
        Encoding enc = bytes.getEncoding();
        if (options.isEncodingNone() && enc != ASCIIEncoding.INSTANCE) {
            if (str.scanForCodeRange() != 32) {
                raiseRegexpError19(this.getRuntime(), bytes, enc, options, "/.../n has a non escaped non ASCII character in non ASCII-8BIT script");
            }
            enc = ASCIIEncoding.INSTANCE;
        }
        return this.initializeCommon19(bytes, enc, options);
    }
    
    private RubyRegexp initializeCommon19(final ByteList bytes, Encoding enc, final RegexpOptions options) {
        final Ruby runtime = this.getRuntime();
        this.options = options;
        if (!this.isTaint() && runtime.getSafeLevel() >= 4) {
            throw runtime.newSecurityError("Insecure: can't modify regexp");
        }
        this.checkFrozen();
        if (this.pattern != null) {
            throw runtime.newTypeError("already initialized regexp");
        }
        if (enc.isDummy()) {
            raiseRegexpError19(runtime, bytes, enc, options, "can't make regexp with dummy encoding");
        }
        final Encoding[] fixedEnc = { null };
        final ByteList unescaped = preprocess(runtime, bytes, enc, fixedEnc, ErrorMode.RAISE);
        if (fixedEnc[0] != null) {
            if ((fixedEnc[0] != enc && options.isFixed()) || (fixedEnc[0] != ASCIIEncoding.INSTANCE && options.isEncodingNone())) {
                raiseRegexpError19(runtime, bytes, enc, options, "incompatible character encoding");
            }
            if (fixedEnc[0] != ASCIIEncoding.INSTANCE) {
                options.setFixed(true);
                enc = fixedEnc[0];
            }
        }
        else if (!options.isFixed()) {
            enc = USASCIIEncoding.INSTANCE;
        }
        if (fixedEnc[0] != null) {
            options.setFixed(true);
        }
        if (options.isEncodingNone()) {
            this.setEncodingNone();
        }
        this.pattern = getRegexpFromCache(runtime, unescaped, enc, options);
        this.str = bytes;
        return this;
    }
    
    @JRubyMethod(name = { "kcode" })
    public IRubyObject kcode(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final String kcodeName = this.options.getKCodeName();
        return (kcodeName == null) ? runtime.getNil() : runtime.newString(kcodeName);
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        this.check();
        int hash = this.pattern.getOptions();
        int len = this.str.getRealSize();
        int p = this.str.getBegin();
        final byte[] bytes = this.str.getUnsafeBytes();
        while (len-- > 0) {
            hash = hash * 33 + bytes[p++];
        }
        return this.getRuntime().newFixnum(hash + (hash >> 5));
    }
    
    @JRubyMethod(name = { "==", "eql?" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return context.getRuntime().getTrue();
        }
        if (!(other instanceof RubyRegexp)) {
            return context.getRuntime().getFalse();
        }
        final RubyRegexp otherRegex = (RubyRegexp)other;
        this.check();
        otherRegex.check();
        return context.getRuntime().newBoolean(this.str.equal(otherRegex.str) && this.getOptions().equals(otherRegex.options));
    }
    
    @JRubyMethod(name = { "~" }, reads = { FrameField.LASTLINE, FrameField.BACKREF }, writes = { FrameField.BACKREF })
    public IRubyObject op_match2(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject line = context.getCurrentScope().getLastLine(runtime);
        if (!(line instanceof RubyString)) {
            context.getCurrentScope().setBackRef(runtime.getNil());
            return runtime.getNil();
        }
        final int start = this.search(context, (RubyString)line, 0, false);
        if (start < 0) {
            return runtime.getNil();
        }
        return runtime.newFixnum(start);
    }
    
    @JRubyMethod(name = { "===" }, required = 1, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject eqq(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        RubyString str;
        if (arg instanceof RubyString) {
            str = (RubyString)arg;
        }
        else {
            final IRubyObject tmp = arg.checkStringType();
            if (tmp.isNil()) {
                context.getCurrentScope().setBackRef(tmp);
                return runtime.getFalse();
            }
            str = (RubyString)tmp;
        }
        final int start = this.search(context, str, 0, false);
        return (start < 0) ? runtime.getFalse() : runtime.getTrue();
    }
    
    @JRubyMethod(name = { "===" }, required = 1, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject eqq19(final ThreadContext context, IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        arg = operandNoCheck(arg);
        if (arg.isNil()) {
            context.getCurrentScope().setBackRef(arg);
            return runtime.getFalse();
        }
        final int start = this.search19(context, (RubyString)arg, 0, false);
        return (start < 0) ? runtime.getFalse() : runtime.getTrue();
    }
    
    @JRubyMethod(name = { "=~" }, required = 1, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_match(final ThreadContext context, final IRubyObject str) {
        final Ruby runtime = context.getRuntime();
        if (str.isNil()) {
            context.getCurrentScope().setBackRef(str);
            return str;
        }
        final int start = this.search(context, str.convertToString(), 0, false);
        if (start < 0) {
            return runtime.getNil();
        }
        return RubyFixnum.newFixnum(runtime, start);
    }
    
    @JRubyMethod(name = { "=~" }, required = 1, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_match19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (arg.isNil()) {
            context.getCurrentScope().setBackRef(arg);
            return arg;
        }
        final RubyString str = operandCheck(runtime, arg);
        final int pos = this.matchPos(context, str, 0);
        if (pos < 0) {
            return runtime.getNil();
        }
        return RubyFixnum.newFixnum(runtime, str.subLength(pos));
    }
    
    @JRubyMethod(name = { "match" }, required = 1, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject match_m(final ThreadContext context, final IRubyObject str) {
        IRubyObject result = this.op_match(context, str);
        if (result.isNil()) {
            return result;
        }
        result = context.getCurrentScope().getBackRef(context.getRuntime());
        ((RubyMatchData)result).use();
        return result;
    }
    
    @JRubyMethod(name = { "match" }, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject match_m19(final ThreadContext context, final IRubyObject str, final Block block) {
        return this.match19Common(context, str, 0, block);
    }
    
    @JRubyMethod(name = { "match" }, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject match_m19(final ThreadContext context, final IRubyObject str, final IRubyObject pos, final Block block) {
        return this.match19Common(context, str, RubyNumeric.num2int(pos), block);
    }
    
    private IRubyObject match19Common(final ThreadContext context, final IRubyObject arg, final int pos, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (arg.isNil()) {
            scope.setBackRef(arg);
            return arg;
        }
        final Ruby runtime = context.getRuntime();
        final RubyString str = operandCheck(runtime, arg);
        if (this.matchPos(context, str, pos) < 0) {
            scope.setBackRef(runtime.getNil());
            return runtime.getNil();
        }
        final IRubyObject backref = scope.getBackRef(runtime);
        ((RubyMatchData)backref).use();
        if (block.isGiven()) {
            return block.yield(context, backref);
        }
        return backref;
    }
    
    private int matchPos(final ThreadContext context, final RubyString str, int pos) {
        if (pos != 0) {
            if (pos < 0) {
                pos += str.strLength();
                if (pos < 0) {
                    return pos;
                }
            }
            pos = this.adjustStartPos19(str, pos, false);
        }
        return this.search19(context, str, pos, false);
    }
    
    public final int search(final ThreadContext context, final RubyString str, final int pos, final boolean reverse) {
        this.check();
        final DynamicScope scope = context.getCurrentScope();
        final ByteList value = str.getByteList();
        if (pos <= value.getRealSize() && pos >= 0) {
            final int realSize = value.getRealSize();
            final int begin = value.getBegin();
            final Matcher matcher = this.pattern.matcher(value.getUnsafeBytes(), begin, begin + realSize);
            final int result = matcher.search(begin + pos, begin + (reverse ? 0 : realSize), 0);
            if (result >= 0) {
                this.updateBackRef(context, str, scope, matcher);
                return result;
            }
        }
        scope.setBackRef(context.getRuntime().getNil());
        return -1;
    }
    
    private RubyMatchData updateBackRef(final ThreadContext context, final RubyString str, final DynamicScope scope, final Matcher matcher) {
        final RubyMatchData match = updateBackRef(context, str, scope, matcher, this.pattern);
        match.infectBy(match.regexp = this);
        return match;
    }
    
    static final RubyMatchData updateBackRef(final ThreadContext context, final RubyString str, final DynamicScope scope, final Matcher matcher, final Regex pattern) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject backref = scope.getBackRef(runtime);
        boolean setBackRef = false;
        RubyMatchData match;
        if (backref.isNil() || ((RubyMatchData)backref).used()) {
            match = new RubyMatchData(runtime);
            setBackRef = true;
        }
        else {
            match = (RubyMatchData)backref;
            match.setTaint(runtime.getSafeLevel() >= 3);
        }
        match.regs = matcher.getRegion();
        match.begin = matcher.getBegin();
        match.end = matcher.getEnd();
        match.pattern = pattern;
        match.str = (RubyString)str.strDup(runtime).freeze(context);
        match.infectBy(str);
        if (setBackRef) {
            scope.setBackRef(match);
        }
        return match;
    }
    
    public final int search19(final ThreadContext context, final RubyString str, final int pos, final boolean reverse) {
        this.check();
        final DynamicScope scope = context.getCurrentScope();
        final ByteList value = str.getByteList();
        if (pos <= value.getRealSize() && pos >= 0) {
            final int realSize = value.getRealSize();
            final int begin = value.getBegin();
            final Matcher matcher = this.preparePattern(str).matcher(value.getUnsafeBytes(), begin, begin + realSize);
            final int result = matcher.search(begin + pos, begin + (reverse ? 0 : realSize), 0);
            if (result >= 0) {
                this.updateBackRef(context, str, scope, matcher).charOffsetUpdated = false;
                return result;
            }
        }
        scope.setBackRef(context.getRuntime().getNil());
        return -1;
    }
    
    static final RubyMatchData updateBackRef19(final ThreadContext context, final RubyString str, final DynamicScope scope, final Matcher matcher, final Regex pattern) {
        final RubyMatchData match = updateBackRef(context, str, scope, matcher, pattern);
        match.charOffsetUpdated = false;
        return match;
    }
    
    @JRubyMethod(name = { "options" })
    public IRubyObject options() {
        return this.getRuntime().newFixnum(this.getOptions().toJoniOptions());
    }
    
    @JRubyMethod(name = { "casefold?" })
    public IRubyObject casefold_p(final ThreadContext context) {
        this.check();
        return context.getRuntime().newBoolean(this.getOptions().isIgnorecase());
    }
    
    @JRubyMethod(name = { "source" })
    public IRubyObject source() {
        this.check();
        final RubyString str = RubyString.newStringShared(this.getRuntime(), this.str);
        if (this.isTaint()) {
            str.setTaint(true);
        }
        return str;
    }
    
    final int length() {
        return this.str.getRealSize();
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject inspect() {
        this.check();
        final ByteList result = regexpDescription(this.getRuntime(), this.str, this.options.getKCode().getEncoding(), this.options);
        if (!this.isKCodeDefault()) {
            result.append((byte)this.options.getKCodeName().charAt(0));
        }
        return RubyString.newString(this.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject inspect19() {
        if (this.pattern == null) {
            return this.anyToString();
        }
        return RubyString.newString(this.getRuntime(), regexpDescription19(this.getRuntime(), this.str, this.options, this.str.getEncoding()));
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        this.check();
        final Ruby runtime = this.getRuntime();
        RegexpOptions newOptions = (RegexpOptions)this.options.clone();
        int p = this.str.getBegin();
        int len = this.str.getRealSize();
        final byte[] bytes = this.str.getUnsafeBytes();
        final ByteList result = new ByteList(len);
        result.append((byte)40).append((byte)63);
        while (len >= 4 && bytes[p] == 40 && bytes[p + 1] == 63) {
            boolean err = true;
            p += 2;
            len -= 2;
            if (len > 0) {
                do {
                    if (bytes[p] == 109) {
                        newOptions.setMultiline(true);
                    }
                    else if (bytes[p] == 105) {
                        newOptions.setIgnorecase(true);
                    }
                    else {
                        if (bytes[p] != 120) {
                            break;
                        }
                        newOptions.setExtended(true);
                    }
                    ++p;
                } while (--len > 0);
            }
            if (len > 1 && bytes[p] == 45) {
                ++p;
                --len;
                do {
                    if (bytes[p] == 109) {
                        newOptions.setMultiline(false);
                    }
                    else if (bytes[p] == 105) {
                        newOptions.setIgnorecase(false);
                    }
                    else {
                        if (bytes[p] != 120) {
                            break;
                        }
                        newOptions.setExtended(false);
                    }
                    ++p;
                } while (--len > 0);
            }
            if (bytes[p] == 41) {
                --len;
                ++p;
            }
            else {
                if (bytes[p] == 58 && bytes[p + len - 1] == 41) {
                    try {
                        final byte[] bytes2 = bytes;
                        final int p2 = ++p;
                        final int n = p;
                        len -= 2;
                        final Regex regex = new Regex(bytes2, p2, n + len, 0, this.getEncoding(runtime, this.str), Syntax.DEFAULT);
                        err = false;
                    }
                    catch (JOniException e) {
                        err = true;
                    }
                }
                if (err) {
                    newOptions = this.options;
                    p = this.str.getBegin();
                    len = this.str.getRealSize();
                    break;
                }
                break;
            }
        }
        appendOptions(result, newOptions);
        if (!newOptions.isEmbeddable()) {
            result.append((byte)45);
            if (!newOptions.isMultiline()) {
                result.append((byte)109);
            }
            if (!newOptions.isIgnorecase()) {
                result.append((byte)105);
            }
            if (!newOptions.isExtended()) {
                result.append((byte)120);
            }
        }
        result.append((byte)58);
        appendRegexpString(this.getRuntime(), result, bytes, p, len, this.getEncoding(runtime, this.str));
        result.append((byte)41);
        return RubyString.newString(this.getRuntime(), result).infectBy(this);
    }
    
    private static void appendRegexpString(final Ruby runtime, final ByteList to, final byte[] bytes, final int start, final int len, final Encoding enc) {
        int p = start;
        final int end = p + len;
        boolean needEscape = false;
        while (p < end) {
            final int c = bytes[p] & 0xFF;
            if (c == 47 || (!enc.isPrint(c) && enc.length(bytes, p, end) == 1)) {
                needEscape = true;
                break;
            }
            p += enc.length(bytes, p, end);
        }
        if (!needEscape) {
            to.append(bytes, start, len);
        }
        else {
            p = start;
            while (p < end) {
                final int c = bytes[p] & 0xFF;
                if (c == 92) {
                    final int n = enc.length(bytes, p + 1, end) + 1;
                    to.append(bytes, p, n);
                    p += n;
                }
                else {
                    if (c == 47) {
                        to.append((byte)92);
                        to.append(bytes, p, 1);
                    }
                    else {
                        if (enc.length(bytes, p, end) != 1) {
                            to.append(bytes, p, enc.length(bytes, p, end));
                            p += enc.length(bytes, p, end);
                            continue;
                        }
                        if (enc.isPrint(c)) {
                            to.append(bytes, p, 1);
                        }
                        else if (!enc.isSpace(c)) {
                            Sprintf.sprintf(runtime, to, "\\%03o", bytes[p] & 0xFF);
                        }
                        else {
                            to.append(bytes, p, 1);
                        }
                    }
                    ++p;
                }
            }
        }
    }
    
    private static void appendRegexpString19(final Ruby runtime, final ByteList to, final byte[] bytes, final int start, final int len, final Encoding enc) {
        int p = start;
        final int end = p + len;
        boolean needEscape = false;
        while (p < end) {
            int cl;
            int c;
            if (enc.isAsciiCompatible()) {
                cl = 1;
                c = (bytes[p] & 0xFF);
            }
            else {
                cl = StringSupport.preciseLength(enc, bytes, p, end);
                c = enc.mbcToCode(bytes, p, end);
            }
            if (!Encoding.isAscii(c)) {
                p += StringSupport.length(enc, bytes, p, end);
            }
            else {
                if (c == 47 || !enc.isPrint(c)) {
                    needEscape = true;
                    break;
                }
                p += cl;
            }
        }
        if (!needEscape) {
            to.append(bytes, start, len);
        }
        else {
            p = start;
            while (p < end) {
                int cl;
                int c;
                if (enc.isAsciiCompatible()) {
                    cl = 1;
                    c = (bytes[p] & 0xFF);
                }
                else {
                    cl = StringSupport.preciseLength(enc, bytes, p, end);
                    c = enc.mbcToCode(bytes, p, end);
                }
                if (c == 92 && p + cl < end) {
                    final int n = cl + StringSupport.length(enc, bytes, p + cl, end);
                    to.append(bytes, p, n);
                    p += n;
                }
                else {
                    if (c == 47) {
                        to.append((byte)92);
                        to.append(bytes, p, cl);
                    }
                    else {
                        if (!Encoding.isAscii(c)) {
                            final int l = StringSupport.length(enc, bytes, p, end);
                            to.append(bytes, p, l);
                            p += l;
                            continue;
                        }
                        if (enc.isPrint(c)) {
                            to.append(bytes, p, cl);
                        }
                        else if (!enc.isSpace(c)) {
                            Sprintf.sprintf(runtime, to, "\\x%02X", c);
                        }
                        else {
                            to.append(bytes, p, cl);
                        }
                    }
                    p += cl;
                }
            }
        }
    }
    
    private static void appendOptions(final ByteList to, final RegexpOptions options) {
        if (options.isMultiline()) {
            to.append((byte)109);
        }
        if (options.isIgnorecase()) {
            to.append((byte)105);
        }
        if (options.isExtended()) {
            to.append((byte)120);
        }
    }
    
    public String[] getNames() {
        final int nameLength = this.pattern.numberOfNames();
        if (nameLength == 0) {
            return RubyRegexp.NO_NAMES;
        }
        final String[] names = new String[nameLength];
        int j = 0;
        final Iterator<NameEntry> i = this.pattern.namedBackrefIterator();
        while (i.hasNext()) {
            final NameEntry e = i.next();
            names[j++] = new String(e.name, e.nameP, e.nameEnd - e.nameP).intern();
        }
        return names;
    }
    
    @JRubyMethod(name = { "names" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject names(final ThreadContext context) {
        if (this.pattern.numberOfNames() == 0) {
            return this.getRuntime().newEmptyArray();
        }
        final RubyArray ary = context.getRuntime().newArray(this.pattern.numberOfNames());
        final Iterator<NameEntry> i = this.pattern.namedBackrefIterator();
        while (i.hasNext()) {
            final NameEntry e = i.next();
            ary.append(RubyString.newStringShared(this.getRuntime(), e.name, e.nameP, e.nameEnd - e.nameP));
        }
        return ary;
    }
    
    @JRubyMethod(name = { "named_captures" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject named_captures(final ThreadContext context) {
        final RubyHash hash = RubyHash.newHash(this.getRuntime());
        if (this.pattern.numberOfNames() == 0) {
            return hash;
        }
        final Iterator<NameEntry> i = this.pattern.namedBackrefIterator();
        while (i.hasNext()) {
            final NameEntry e = i.next();
            final int[] backrefs = e.getBackRefs();
            final RubyArray ary = this.getRuntime().newArray(backrefs.length);
            for (final int backref : backrefs) {
                ary.append(RubyFixnum.newFixnum(this.getRuntime(), backref));
            }
            hash.fastASet(RubyString.newStringShared(this.getRuntime(), e.name, e.nameP, e.nameEnd - e.nameP).freeze(context), ary);
        }
        return hash;
    }
    
    @JRubyMethod(name = { "encoding" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encoding(final ThreadContext context) {
        return context.getRuntime().getEncodingService().getEncoding(this.pattern.getEncoding());
    }
    
    @JRubyMethod(name = { "fixed_encoding?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject fixed_encoding_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.options.isFixed());
    }
    
    public static IRubyObject nth_match(int nth, final IRubyObject match) {
        if (match.isNil()) {
            return match;
        }
        final RubyMatchData m = (RubyMatchData)match;
        final Ruby runtime = m.getRuntime();
        int start;
        int end;
        if (m.regs == null) {
            if (nth >= 1 || (nth < 0 && ++nth <= 0)) {
                return runtime.getNil();
            }
            start = m.begin;
            end = m.end;
        }
        else {
            if (nth >= m.regs.numRegs || (nth < 0 && (nth += m.regs.numRegs) <= 0)) {
                return runtime.getNil();
            }
            start = m.regs.beg[nth];
            end = m.regs.end[nth];
        }
        if (start == -1) {
            return runtime.getNil();
        }
        final RubyString str = m.str.makeShared(runtime, m.str.getType(), start, end - start);
        str.infectBy(m);
        return str;
    }
    
    public static IRubyObject last_match(final IRubyObject match) {
        return nth_match(0, match);
    }
    
    public static IRubyObject match_pre(final IRubyObject match) {
        if (match.isNil()) {
            return match;
        }
        final RubyMatchData m = (RubyMatchData)match;
        final Ruby runtime = m.getRuntime();
        if (m.begin == -1) {
            runtime.getNil();
        }
        return m.str.makeShared(runtime, m.str.getType(), 0, m.begin).infectBy(m);
    }
    
    public static IRubyObject match_post(final IRubyObject match) {
        if (match.isNil()) {
            return match;
        }
        final RubyMatchData m = (RubyMatchData)match;
        final Ruby runtime = m.getRuntime();
        if (m.begin == -1) {
            return runtime.getNil();
        }
        return m.str.makeShared(runtime, m.str.getType(), m.end, m.str.getByteList().getRealSize() - m.end).infectBy(m);
    }
    
    public static IRubyObject match_last(final IRubyObject match) {
        if (match.isNil()) {
            return match;
        }
        final RubyMatchData m = (RubyMatchData)match;
        if (m.regs == null || m.regs.beg[0] == -1) {
            return match.getRuntime().getNil();
        }
        int i;
        for (i = m.regs.numRegs - 1; m.regs.beg[i] == -1 && i > 0; --i) {}
        if (i == 0) {
            return match.getRuntime().getNil();
        }
        return nth_match(i, match);
    }
    
    static RubyString regsub(final RubyString str, final RubyString src, final Matcher matcher, final Encoding enc) {
        final Region regs = matcher.getRegion();
        int no = -1;
        final ByteList bs = str.getByteList();
        int s;
        int p = s = bs.getBegin();
        final int end = p + bs.getRealSize();
        final byte[] bytes = bs.getUnsafeBytes();
        final ByteList srcbs = src.getByteList();
        ByteList val = null;
        while (s < end) {
            final int ss = s;
            int c = bytes[s] & 0xFF;
            final int l = enc.length(bytes, s++, end);
            if (l != 1) {
                s += l - 1;
            }
            else {
                if (c != 92) {
                    continue;
                }
                if (s == end) {
                    continue;
                }
                if (val == null) {
                    val = new ByteList(ss - p);
                }
                val.append(bytes, p, ss - p);
                c = (bytes[s++] & 0xFF);
                p = s;
                switch (c) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57: {
                        no = c - 48;
                        break;
                    }
                    case 38: {
                        no = 0;
                        break;
                    }
                    case 96: {
                        val.append(srcbs.getUnsafeBytes(), srcbs.getBegin(), matcher.getBegin());
                        continue;
                    }
                    case 39: {
                        val.append(srcbs.getUnsafeBytes(), srcbs.getBegin() + matcher.getEnd(), srcbs.getRealSize() - matcher.getEnd());
                        continue;
                    }
                    case 43: {
                        if (regs == null) {
                            if (matcher.getBegin() == -1) {
                                no = 0;
                                continue;
                            }
                            break;
                        }
                        else {
                            for (no = regs.numRegs - 1; regs.beg[no] == -1 && no > 0; --no) {}
                            if (no == 0) {
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                    case 92: {
                        val.append(bytes, s - 1, 1);
                        continue;
                    }
                    default: {
                        val.append(bytes, s - 2, 2);
                        continue;
                    }
                }
                if (regs != null) {
                    if (no < 0) {
                        continue;
                    }
                    if (no >= regs.numRegs) {
                        continue;
                    }
                    if (regs.beg[no] == -1) {
                        continue;
                    }
                    val.append(srcbs.getUnsafeBytes(), srcbs.getBegin() + regs.beg[no], regs.end[no] - regs.beg[no]);
                }
                else {
                    if (no != 0) {
                        continue;
                    }
                    if (matcher.getBegin() == -1) {
                        continue;
                    }
                    val.append(srcbs.getUnsafeBytes(), srcbs.getBegin() + matcher.getBegin(), matcher.getEnd() - matcher.getBegin());
                }
            }
        }
        if (p < end) {
            if (val == null) {
                return RubyString.newString(str.getRuntime(), bs.makeShared(p - bs.getBegin(), end - p));
            }
            val.append(bytes, p, end - p);
        }
        if (val == null) {
            return str;
        }
        return RubyString.newString(str.getRuntime(), val);
    }
    
    static RubyString regsub19(final RubyString str, final RubyString src, final Matcher matcher, final Regex pattern) {
        final Region regs = matcher.getRegion();
        int no = -1;
        final ByteList bs = str.getByteList();
        int s;
        int p = s = bs.getBegin();
        final int end = p + bs.getRealSize();
        final byte[] bytes = bs.getUnsafeBytes();
        final Encoding strEnc = bs.getEncoding();
        final ByteList srcbs = src.getByteList();
        final Encoding srcEnc = srcbs.getEncoding();
        RubyString val = null;
        while (s < end) {
            int cl;
            int c;
            if (strEnc.isAsciiCompatible()) {
                cl = 1;
                c = (bytes[s] & 0xFF);
            }
            else {
                cl = StringSupport.preciseLength(strEnc, bytes, s, end);
                c = strEnc.mbcToCode(bytes, s, end);
            }
            if (!Encoding.isAscii(c)) {
                s += StringSupport.length(strEnc, bytes, s, end);
            }
            else {
                final int ss = s;
                s += cl;
                if (c != 92) {
                    continue;
                }
                if (s == end) {
                    continue;
                }
                if (val == null) {
                    val = RubyString.newString(str.getRuntime(), new ByteList(ss - p));
                }
                val.cat(bytes, p, ss - p, strEnc);
                if (strEnc.isAsciiCompatible()) {
                    cl = 1;
                    c = (bytes[s] & 0xFF);
                }
                else {
                    cl = StringSupport.preciseLength(strEnc, bytes, s, end);
                    c = strEnc.mbcToCode(bytes, s, end);
                }
                if (!Encoding.isAscii(c)) {
                    s += StringSupport.length(strEnc, bytes, s, end);
                    val.cat(bytes, ss, s - ss, strEnc);
                    p = s;
                }
                else {
                    s = (p = s + cl);
                    switch (c) {
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            if (pattern.noNameGroupIsActive(Syntax.RUBY)) {
                                no = c - 48;
                                break;
                            }
                            continue;
                        }
                        case 107: {
                            if (s < end) {
                                if (strEnc.isAsciiCompatible()) {
                                    cl = 1;
                                    c = (bytes[s] & 0xFF);
                                }
                                else {
                                    cl = StringSupport.preciseLength(strEnc, bytes, s, end);
                                    c = strEnc.mbcToCode(bytes, s, end);
                                }
                                if (c == 60) {
                                    int nameEnd;
                                    int name;
                                    for (name = (nameEnd = s + cl); nameEnd < end; nameEnd += (Encoding.isAscii(c) ? cl : StringSupport.length(strEnc, bytes, nameEnd, end))) {
                                        if (strEnc.isAsciiCompatible()) {
                                            cl = 1;
                                            c = (bytes[nameEnd] & 0xFF);
                                        }
                                        else {
                                            cl = StringSupport.preciseLength(strEnc, bytes, nameEnd, end);
                                            c = strEnc.mbcToCode(bytes, nameEnd, end);
                                        }
                                        if (c == 62) {
                                            break;
                                        }
                                    }
                                    if (nameEnd < end) {
                                        try {
                                            no = pattern.nameToBackrefNumber(bytes, name, nameEnd, regs);
                                        }
                                        catch (JOniException je) {
                                            throw str.getRuntime().newIndexError(je.getMessage());
                                        }
                                        s = (p = nameEnd + cl);
                                        break;
                                    }
                                    throw str.getRuntime().newRuntimeError("invalid group name reference format");
                                }
                            }
                            val.cat(bytes, ss, s - ss, strEnc);
                            continue;
                        }
                        case 38:
                        case 48: {
                            no = 0;
                            break;
                        }
                        case 96: {
                            val.cat(srcbs.getUnsafeBytes(), srcbs.getBegin(), matcher.getBegin(), srcEnc);
                            continue;
                        }
                        case 39: {
                            val.cat(srcbs.getUnsafeBytes(), srcbs.getBegin() + matcher.getEnd(), srcbs.getRealSize() - matcher.getEnd(), srcEnc);
                            continue;
                        }
                        case 43: {
                            if (regs == null) {
                                if (matcher.getBegin() == -1) {
                                    no = 0;
                                    continue;
                                }
                                break;
                            }
                            else {
                                for (no = regs.numRegs - 1; regs.beg[no] == -1 && no > 0; --no) {}
                                if (no == 0) {
                                    continue;
                                }
                                break;
                            }
                            break;
                        }
                        case 92: {
                            val.cat(bytes, s - cl, cl, strEnc);
                            continue;
                        }
                        default: {
                            val.cat(bytes, ss, s - ss, strEnc);
                            continue;
                        }
                    }
                    if (regs != null) {
                        if (no < 0) {
                            continue;
                        }
                        if (no >= regs.numRegs) {
                            continue;
                        }
                        if (regs.beg[no] == -1) {
                            continue;
                        }
                        val.cat(srcbs.getUnsafeBytes(), srcbs.getBegin() + regs.beg[no], regs.end[no] - regs.beg[no], srcEnc);
                    }
                    else {
                        if (no != 0) {
                            continue;
                        }
                        if (matcher.getBegin() == -1) {
                            continue;
                        }
                        val.cat(srcbs.getUnsafeBytes(), srcbs.getBegin() + matcher.getBegin(), matcher.getEnd() - matcher.getBegin(), srcEnc);
                    }
                }
            }
        }
        if (val == null) {
            return str;
        }
        if (p < end) {
            val.cat(bytes, p, end - p, strEnc);
        }
        return val;
    }
    
    final int adjustStartPos19(final RubyString str, final int pos, final boolean reverse) {
        return this.adjustStartPosInternal(str, this.checkEncoding(str, false), pos, reverse);
    }
    
    final int adjustStartPos(final RubyString str, final int pos, final boolean reverse) {
        return this.adjustStartPosInternal(str, this.pattern.getEncoding(), pos, reverse);
    }
    
    private final int adjustStartPosInternal(final RubyString str, final Encoding enc, final int pos, final boolean reverse) {
        this.check();
        final ByteList value = str.getByteList();
        final int len = value.getRealSize();
        if (pos <= 0 || enc.maxLength() == 1 || pos >= len) {
            return pos;
        }
        final int start = value.getBegin();
        if ((reverse ? (-pos) : (len - pos)) > 0) {
            return enc.rightAdjustCharHead(value.getUnsafeBytes(), start, start + pos, start + len) - start;
        }
        return enc.leftAdjustCharHead(value.getUnsafeBytes(), start, start + pos, start + len) - start;
    }
    
    private static IRubyObject operandNoCheck(final IRubyObject str) {
        if (str instanceof RubySymbol) {
            return ((RubySymbol)str).to_s();
        }
        return str.checkStringType();
    }
    
    private static RubyString operandCheck(final Ruby runtime, final IRubyObject str) {
        if (str instanceof RubySymbol) {
            return (RubyString)((RubySymbol)str).to_s();
        }
        final IRubyObject tmp = str.checkStringType();
        if (tmp.isNil()) {
            throw runtime.newTypeError("can't convert " + str.getMetaClass() + "to String");
        }
        return (RubyString)tmp;
    }
    
    public static RubyRegexp unmarshalFrom(final UnmarshalStream input) throws IOException {
        final RubyRegexp result = newRegexp(input.getRuntime(), input.unmarshalString(), RegexpOptions.fromJoniOptions(input.readSignedByte()));
        input.registerLinkTarget(result);
        return result;
    }
    
    public static void marshalTo(final RubyRegexp regexp, final MarshalStream output) throws IOException {
        output.registerLinkTarget(regexp);
        output.writeString(regexp.str);
        output.writeByte(regexp.pattern.getOptions() & 0x7);
    }
    
    static {
        patternCache = new RegexpCache();
        quotedPatternCache = new RegexpCache();
        preprocessedPatternCache = new RegexpCache();
        RubyRegexp.REGEXP_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyRegexp(runtime, klass, null);
            }
        };
        RubyRegexp.NO_NAMES = new String[0];
    }
    
    private static final class RegexpCache
    {
        private volatile SoftReference<Map<ByteList, Regex>> cache;
        
        private RegexpCache() {
            this.cache = new SoftReference<Map<ByteList, Regex>>(null);
        }
        
        private Map<ByteList, Regex> get() {
            Map<ByteList, Regex> patternCache = this.cache.get();
            if (patternCache == null) {
                patternCache = new ConcurrentHashMap<ByteList, Regex>(5);
                this.cache = new SoftReference<Map<ByteList, Regex>>(patternCache);
            }
            return patternCache;
        }
    }
    
    private enum ErrorMode
    {
        RAISE, 
        PREPROCESS, 
        DESC;
    }
}
