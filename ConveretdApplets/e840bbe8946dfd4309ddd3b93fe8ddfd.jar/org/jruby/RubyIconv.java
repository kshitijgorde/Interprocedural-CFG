// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.anno.JRubyModule;
import org.jcodings.Encoding;
import java.nio.CharBuffer;
import org.jcodings.EncodingDB;
import org.jruby.util.ByteList;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.UnmappableCharacterException;
import java.nio.charset.MalformedInputException;
import java.nio.ByteBuffer;
import org.jruby.runtime.Arity;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.Charset;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharsetDecoder;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Iconv" })
public class RubyIconv extends RubyObject
{
    private static final String IGNORE = "//ignore";
    private CharsetDecoder fromEncoding;
    private CharsetEncoder toEncoding;
    private int count;
    private String endian;
    private static final ObjectAllocator ICONV_ALLOCATOR;
    
    public RubyIconv(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.endian = "";
    }
    
    public static void createIconv(final Ruby runtime) {
        final RubyClass iconvClass = runtime.defineClass("Iconv", runtime.getObject(), RubyIconv.ICONV_ALLOCATOR);
        iconvClass.defineAnnotatedMethods(RubyIconv.class);
        final RubyModule failure = iconvClass.defineModuleUnder("Failure");
        final RubyClass argumentError = runtime.getArgumentError();
        final String[] iconvErrors = { "IllegalSequence", "InvalidCharacter", "InvalidEncoding", "OutOfRange", "BrokenLibrary" };
        for (int i = 0; i < iconvErrors.length; ++i) {
            final RubyClass subClass = iconvClass.defineClassUnder(iconvErrors[i], argumentError, RubyFailure.ICONV_FAILURE_ALLOCATOR);
            subClass.defineAnnotatedMethods(RubyFailure.class);
            subClass.includeModule(failure);
        }
    }
    
    private static String getCharset(final String encoding) {
        final int index = encoding.indexOf("//");
        if (index == -1) {
            return encoding;
        }
        return encoding.substring(0, index);
    }
    
    private static boolean isIgnore(final String encoding) {
        return encoding.toLowerCase().indexOf("//ignore") != -1;
    }
    
    @JRubyMethod(required = 2, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject to, final IRubyObject from, final Block block) {
        final Ruby runtime = context.getRuntime();
        final RubyIconv iconv = newIconv(context, recv, to, from);
        if (!block.isGiven()) {
            return iconv;
        }
        IRubyObject result = runtime.getNil();
        try {
            result = block.yield(context, iconv);
        }
        finally {
            iconv.close();
        }
        return result;
    }
    
    private static RubyIconv newIconv(final ThreadContext context, final IRubyObject recv, final IRubyObject to, final IRubyObject from) {
        final RubyClass klazz = (RubyClass)recv;
        return (RubyIconv)klazz.newInstance(context, new IRubyObject[] { to, from }, Block.NULL_BLOCK);
    }
    
    @JRubyMethod
    public IRubyObject initialize(final IRubyObject arg1, final IRubyObject arg2, final Block unusedBlock) {
        final Ruby runtime = this.getRuntime();
        if (!arg1.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + arg1.getMetaClass() + " into String");
        }
        if (!arg2.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + arg2.getMetaClass() + " into String");
        }
        final String to = arg1.convertToString().toString();
        final String from = arg2.convertToString().toString();
        try {
            this.fromEncoding = Charset.forName(getCharset(from)).newDecoder();
            this.toEncoding = Charset.forName(getCharset(to)).newEncoder();
            this.count = 0;
            if (isIgnore(to)) {
                this.fromEncoding.onUnmappableCharacter(CodingErrorAction.IGNORE);
                this.fromEncoding.onMalformedInput(CodingErrorAction.IGNORE);
                this.toEncoding.onUnmappableCharacter(CodingErrorAction.IGNORE);
                this.toEncoding.onMalformedInput(CodingErrorAction.IGNORE);
            }
            else {
                this.fromEncoding.onUnmappableCharacter(CodingErrorAction.REPORT);
                this.fromEncoding.onMalformedInput(CodingErrorAction.REPORT);
                this.toEncoding.onUnmappableCharacter(CodingErrorAction.REPORT);
                this.toEncoding.onMalformedInput(CodingErrorAction.REPORT);
            }
        }
        catch (IllegalCharsetNameException e2) {
            throw runtime.newInvalidEncoding("invalid encoding");
        }
        catch (UnsupportedCharsetException e3) {
            throw runtime.newInvalidEncoding("invalid encoding");
        }
        catch (Exception e) {
            throw runtime.newSystemCallError(e.toString());
        }
        return this;
    }
    
    @JRubyMethod(name = { "close" })
    public IRubyObject close() {
        if (this.toEncoding == null && this.fromEncoding == null) {
            return this.getRuntime().getNil();
        }
        this.toEncoding = null;
        this.fromEncoding = null;
        return RubyString.newEmptyString(this.getRuntime());
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject iconv(final IRubyObject str) {
        return this.iconv(str, 0, -1);
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject iconv(final IRubyObject str, final IRubyObject startArg) {
        int start = 0;
        if (!startArg.isNil()) {
            start = RubyNumeric.fix2int(startArg);
        }
        return this.iconv(str, start, -1);
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject iconv(final IRubyObject str, final IRubyObject startArg, final IRubyObject endArg) {
        int start = 0;
        int end = -1;
        if (!startArg.isNil()) {
            start = RubyNumeric.fix2int(startArg);
        }
        if (!endArg.isNil()) {
            end = RubyNumeric.fix2int(endArg);
        }
        return this.iconv(str, start, end);
    }
    
    private IRubyObject iconv(final IRubyObject str, final int start, final int end) {
        if (str.isNil()) {
            this.fromEncoding.reset();
            this.toEncoding.reset();
            return RubyString.newEmptyString(this.getRuntime());
        }
        return this._iconv(str.convertToString(), start, end);
    }
    
    public IRubyObject iconv(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.iconv(args[0]);
            }
            case 2: {
                return this.iconv(args[0], args[1]);
            }
            case 3: {
                return this.iconv(args[0], args[1], args[2]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    private IRubyObject _iconv(final RubyString str, int start, int length) {
        if (this.fromEncoding == null) {
            throw this.getRuntime().newArgumentError("closed iconv");
        }
        final ByteList bytes = str.getByteList();
        if (start < 0) {
            start += bytes.length();
        }
        if (start < 0 || start > bytes.length()) {
            return RubyString.newEmptyString(this.getRuntime());
        }
        if (length < 0 || length > bytes.length() - start) {
            length = bytes.length() - start;
        }
        ByteBuffer buf = ByteBuffer.wrap(bytes.getUnsafeBytes(), bytes.begin() + start, length);
        try {
            final CharBuffer cbuf = this.fromEncoding.decode(buf);
            buf = this.toEncoding.encode(cbuf);
        }
        catch (MalformedInputException e) {
            throw this.getRuntime().newIllegalSequence(str.toString());
        }
        catch (UnmappableCharacterException e2) {
            throw this.getRuntime().newIllegalSequence(str.toString());
        }
        catch (CharacterCodingException e3) {
            throw this.getRuntime().newInvalidEncoding("invalid sequence");
        }
        catch (IllegalStateException e4) {
            throw this.getRuntime().newIllegalSequence(str.toString());
        }
        final byte[] arr = buf.array();
        start = 0;
        String displayName = this.toEncoding.charset().displayName();
        if (arr.length >= 2) {
            if (displayName.toLowerCase().startsWith("utf-16")) {
                if (arr[0] == -2 && arr[1] == -1) {
                    if (this.count > 0) {
                        start = 2;
                    }
                    this.endian = "BE";
                }
                else if (arr[0] == -1 && arr[1] == -2) {
                    if (this.count > 0) {
                        start = 2;
                    }
                    this.endian = "LE";
                }
            }
            else if (displayName.toLowerCase().startsWith("utf-32") && arr.length >= 4) {
                if (arr[0] == 0 && arr[1] == 0 && arr[2] == -2 && arr[3] == -1) {
                    if (this.count > 0) {
                        start = 4;
                    }
                    this.endian = "BE";
                }
                else if (arr[0] == -1 && arr[1] == -2 && arr[2] == 0 && arr[3] == 0) {
                    if (this.count > 0) {
                        start = 4;
                    }
                    this.endian = "LE";
                }
            }
        }
        ++this.count;
        if (displayName.equalsIgnoreCase("utf-16") || displayName.equalsIgnoreCase("utf-32")) {
            displayName += this.endian;
        }
        final ByteList r = new ByteList(arr, start, buf.limit() - start);
        final EncodingDB.Entry entry = EncodingDB.getEncodings().get(displayName.getBytes());
        if (entry != null) {
            final Encoding charset = entry.getEncoding();
            r.setEncoding(charset);
        }
        return this.getRuntime().newString(r);
    }
    
    @JRubyMethod(name = { "iconv" }, required = 2, rest = true, meta = true, backtrace = true)
    public static IRubyObject iconv(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        return convertWithArgs(context, recv, args, "iconv");
    }
    
    @JRubyMethod(name = { "conv" }, required = 3, rest = true, meta = true, backtrace = true)
    public static IRubyObject conv(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        return convertWithArgs(context, recv, args, "conv").join(context, RubyString.newEmptyString(recv.getRuntime()));
    }
    
    @JRubyMethod(name = { "charset_map" }, meta = true)
    public static IRubyObject charset_map_get(final IRubyObject recv) {
        return recv.getRuntime().getCharsetMap();
    }
    
    private static String mapCharset(final ThreadContext context, IRubyObject val) {
        final RubyHash charset = val.getRuntime().getCharsetMap();
        if (charset.size() > 0) {
            final RubyString key = val.callMethod(context, "downcase").convertToString();
            final IRubyObject tryVal = charset.fastARef(key);
            if (tryVal != null) {
                val = tryVal;
            }
        }
        return val.convertToString().toString();
    }
    
    public static RubyArray convertWithArgs(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final String function) {
        assert args.length >= 2;
        final RubyArray array = context.getRuntime().newArray(args.length - 2);
        final RubyIconv iconv = newIconv(context, recv, args[0], args[1]);
        try {
            for (int i = 2; i < args.length; ++i) {
                array.append(iconv.iconv(args[i]));
            }
        }
        finally {
            iconv.close();
        }
        return array;
    }
    
    static {
        ICONV_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyIconv(runtime, klass);
            }
        };
    }
    
    @JRubyModule(name = { "Iconv::Failure" })
    public static class Failure
    {
    }
    
    @JRubyClass(name = { "Iconv::IllegalSequence" }, parent = "ArgumentError", include = { "Iconv::Failure" })
    public static class IllegalSequence
    {
    }
    
    @JRubyClass(name = { "Iconv::InvalidCharacter" }, parent = "ArgumentError", include = { "Iconv::Failure" })
    public static class InvalidCharacter
    {
    }
    
    @JRubyClass(name = { "Iconv::InvalidEncoding" }, parent = "ArgumentError", include = { "Iconv::Failure" })
    public static class InvalidEncoding
    {
    }
    
    @JRubyClass(name = { "Iconv::OutOfRange" }, parent = "ArgumentError", include = { "Iconv::Failure" })
    public static class OutOfRange
    {
    }
    
    @JRubyClass(name = { "Iconv::BrokenLibrary" }, parent = "ArgumentError", include = { "Iconv::Failure" })
    public static class BrokenLibrary
    {
    }
    
    public static class RubyFailure extends RubyException
    {
        private IRubyObject success;
        private IRubyObject failed;
        protected static final ObjectAllocator ICONV_FAILURE_ALLOCATOR;
        
        public static RubyFailure newInstance(final Ruby runtime, final RubyClass excptnClass, final String msg) {
            return new RubyFailure(runtime, excptnClass, msg);
        }
        
        protected RubyFailure(final Ruby runtime, final RubyClass rubyClass) {
            this(runtime, rubyClass, null);
        }
        
        public RubyFailure(final Ruby runtime, final RubyClass rubyClass, final String message) {
            super(runtime, rubyClass, message);
        }
        
        @JRubyMethod(required = 1, optional = 2)
        public IRubyObject initialize(final IRubyObject[] args, final Block block) {
            super.initialize(args, block);
            this.success = ((args.length >= 2) ? args[1] : this.getRuntime().getNil());
            this.failed = ((args.length == 3) ? args[2] : this.getRuntime().getNil());
            return this;
        }
        
        @JRubyMethod(name = { "success" })
        public IRubyObject success() {
            return this.success;
        }
        
        @JRubyMethod(name = { "failed" })
        public IRubyObject failed() {
            return this.failed;
        }
        
        @JRubyMethod(name = { "inspect" })
        public IRubyObject inspect() {
            final RubyModule rubyClass = this.getMetaClass();
            final StringBuilder buffer = new StringBuilder("#<");
            buffer.append(rubyClass.getName()).append(": ").append(this.success.inspect().toString());
            buffer.append(", ").append(this.failed.inspect().toString()).append(">");
            return this.getRuntime().newString(buffer.toString());
        }
        
        static {
            ICONV_FAILURE_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new RubyFailure(runtime, klass);
                }
            };
        }
    }
}
