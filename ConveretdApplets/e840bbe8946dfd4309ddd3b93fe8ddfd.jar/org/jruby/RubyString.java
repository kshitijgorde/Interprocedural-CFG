// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Arity;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharacterCodingException;
import java.nio.ByteBuffer;
import org.jruby.util.Pack;
import java.io.IOException;
import org.jruby.runtime.marshal.UnmarshalStream;
import org.jruby.util.Numeric;
import org.jcodings.util.IntHash;
import org.jruby.util.RegexpOptions;
import org.jruby.util.ConvertBytes;
import org.joni.Region;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.DynamicScope;
import org.joni.Matcher;
import org.joni.Regex;
import org.jruby.util.string.JavaCrypt;
import java.util.Arrays;
import org.jruby.anno.FrameField;
import org.jcodings.ascii.AsciiTables;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import org.jruby.util.Sprintf;
import java.util.Locale;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import java.nio.charset.Charset;
import java.io.UnsupportedEncodingException;
import org.jcodings.specific.USASCIIEncoding;
import org.jruby.util.StringSupport;
import org.jcodings.Encoding;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.cext.RString;
import org.jruby.util.ByteList;
import org.jcodings.specific.UTF8Encoding;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.encoding.EncodingCapable;

@JRubyClass(name = { "String" }, include = { "Enumerable", "Comparable" })
public class RubyString extends RubyObject implements EncodingCapable
{
    private static final ASCIIEncoding ASCII;
    private static final UTF8Encoding UTF8;
    private static final byte[] EMPTY_BYTE_ARRAY;
    private static final int SHARE_LEVEL_NONE = 0;
    private static final int SHARE_LEVEL_BUFFER = 1;
    private static final int SHARE_LEVEL_BYTELIST = 2;
    private volatile int shareLevel;
    private ByteList value;
    private RString rstring;
    private static ObjectAllocator STRING_ALLOCATOR;
    private static EmptyByteListHolder[] EMPTY_BYTELISTS;
    private static final ByteList SPACE_BYTELIST;
    private static final int TRANS_SIZE = 256;
    
    public static RubyClass createStringClass(final Ruby runtime) {
        final RubyClass stringClass = runtime.defineClass("String", runtime.getObject(), RubyString.STRING_ALLOCATOR);
        runtime.setString(stringClass);
        stringClass.index = 4;
        stringClass.setReifiedClass(RubyString.class);
        stringClass.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyString;
            }
        };
        stringClass.includeModule(runtime.getComparable());
        if (!runtime.is1_9()) {
            stringClass.includeModule(runtime.getEnumerable());
        }
        stringClass.defineAnnotatedMethods(RubyString.class);
        return stringClass;
    }
    
    public Encoding getEncoding() {
        return this.value.getEncoding();
    }
    
    public void setEncoding(final Encoding encoding) {
        this.value.setEncoding(encoding);
    }
    
    public void associateEncoding(final Encoding enc) {
        if (this.value.getEncoding() != enc) {
            if (!this.isCodeRangeAsciiOnly() || !enc.isAsciiCompatible()) {
                this.clearCodeRange();
            }
            this.value.setEncoding(enc);
        }
    }
    
    public final void setEncodingAndCodeRange(final Encoding enc, final int cr) {
        this.value.setEncoding(enc);
        this.setCodeRange(cr);
    }
    
    public final Encoding toEncoding(final Ruby runtime) {
        return runtime.getEncodingService().findEncoding(this);
    }
    
    public final int getCodeRange() {
        return this.flags & 0x60;
    }
    
    public final void setCodeRange(final int codeRange) {
        this.clearCodeRange();
        this.flags |= (codeRange & 0x60);
    }
    
    public final RString getRString() {
        return this.rstring;
    }
    
    public final void setRString(final RString rstring) {
        this.rstring = rstring;
    }
    
    public final void clearCodeRange() {
        this.flags &= 0xFFFFFF9F;
    }
    
    private void keepCodeRange() {
        if (this.getCodeRange() == 96) {
            this.clearCodeRange();
        }
    }
    
    public final boolean isCodeRangeAsciiOnly() {
        return this.getCodeRange() == 32;
    }
    
    public final boolean isAsciiOnly() {
        return this.value.getEncoding().isAsciiCompatible() && this.scanForCodeRange() == 32;
    }
    
    public final boolean isCodeRangeValid() {
        return (this.flags & 0x40) != 0x0;
    }
    
    public final boolean isCodeRangeBroken() {
        return (this.flags & 0x60) != 0x0;
    }
    
    static int codeRangeAnd(final int cr1, final int cr2) {
        if (cr1 == 32) {
            return cr2;
        }
        if (cr1 == 64) {
            return (cr2 == 32) ? 64 : cr2;
        }
        return 0;
    }
    
    private void copyCodeRangeForSubstr(final RubyString from, final Encoding enc) {
        final int fromCr = from.getCodeRange();
        if (fromCr == 32) {
            this.setCodeRange(fromCr);
        }
        else if (fromCr == 64) {
            if (!enc.isAsciiCompatible() || StringSupport.searchNonAscii(this.value) != -1) {
                this.setCodeRange(64);
            }
            else {
                this.setCodeRange(32);
            }
        }
        else if (this.value.getRealSize() == 0) {
            this.setCodeRange(enc.isAsciiCompatible() ? 32 : 64);
        }
    }
    
    private void copyCodeRange(final RubyString from) {
        this.value.setEncoding(from.value.getEncoding());
        this.setCodeRange(from.getCodeRange());
    }
    
    final int scanForCodeRange() {
        int cr = this.getCodeRange();
        if (cr == 0) {
            cr = StringSupport.codeRangeScan(this.value.getEncoding(), this.value);
            this.setCodeRange(cr);
        }
        return cr;
    }
    
    final boolean singleByteOptimizable() {
        return this.getCodeRange() == 32 || this.value.getEncoding().isSingleByte();
    }
    
    final boolean singleByteOptimizable(final Encoding enc) {
        return this.getCodeRange() == 32 || enc.isSingleByte();
    }
    
    private Encoding isCompatibleWith(final RubyString other) {
        final Encoding enc1 = this.value.getEncoding();
        final Encoding enc2 = other.value.getEncoding();
        if (enc1 == enc2) {
            return enc1;
        }
        if (other.value.getRealSize() == 0) {
            return enc1;
        }
        if (this.value.getRealSize() == 0) {
            return enc2;
        }
        if (!enc1.isAsciiCompatible() || !enc2.isAsciiCompatible()) {
            return null;
        }
        return RubyEncoding.areCompatible(enc1, this.scanForCodeRange(), enc2, other.scanForCodeRange());
    }
    
    final Encoding isCompatibleWith(final EncodingCapable other) {
        if (other instanceof RubyString) {
            return this.checkEncoding((RubyString)other);
        }
        final Encoding enc1 = this.value.getEncoding();
        final Encoding enc2 = other.getEncoding();
        if (enc1 == enc2) {
            return enc1;
        }
        if (this.value.getRealSize() == 0) {
            return enc2;
        }
        if (!enc1.isAsciiCompatible() || !enc2.isAsciiCompatible()) {
            return null;
        }
        if (enc2 instanceof USASCIIEncoding) {
            return enc1;
        }
        if (this.scanForCodeRange() == 32) {
            return enc2;
        }
        return null;
    }
    
    final Encoding checkEncoding(final RubyString other) {
        final Encoding enc = this.isCompatibleWith(other);
        if (enc == null) {
            throw this.getRuntime().newEncodingCompatibilityError("incompatible character encodings: " + this.value.getEncoding() + " and " + other.value.getEncoding());
        }
        return enc;
    }
    
    final Encoding checkEncoding(final EncodingCapable other) {
        final Encoding enc = this.isCompatibleWith(other);
        if (enc == null) {
            throw this.getRuntime().newEncodingCompatibilityError("incompatible character encodings: " + this.value.getEncoding() + " and " + other.getEncoding());
        }
        return enc;
    }
    
    private Encoding checkDummyEncoding() {
        final Encoding enc = this.value.getEncoding();
        if (enc.isDummy()) {
            throw this.getRuntime().newEncodingCompatibilityError("incompatible encoding with this operation: " + enc);
        }
        return enc;
    }
    
    private boolean isComparableWith(final RubyString other) {
        final ByteList otherValue = other.value;
        return this.value.getEncoding() == otherValue.getEncoding() || this.value.getRealSize() == 0 || otherValue.getRealSize() == 0 || this.isComparableViaCodeRangeWith(other);
    }
    
    private boolean isComparableViaCodeRangeWith(final RubyString other) {
        final int cr1 = this.scanForCodeRange();
        final int cr2 = other.scanForCodeRange();
        return (cr1 == 32 && (cr2 == 32 || other.value.getEncoding().isAsciiCompatible())) || (cr2 == 32 && this.value.getEncoding().isAsciiCompatible());
    }
    
    private int strLength(final Encoding enc) {
        if (this.singleByteOptimizable(enc)) {
            return this.value.getRealSize();
        }
        return this.strLength(this.value, enc);
    }
    
    final int strLength() {
        if (this.singleByteOptimizable()) {
            return this.value.getRealSize();
        }
        return this.strLength(this.value);
    }
    
    private int strLength(final ByteList bytes) {
        return this.strLength(bytes, bytes.getEncoding());
    }
    
    private int strLength(final ByteList bytes, final Encoding enc) {
        if (this.isCodeRangeValid() && enc instanceof UTF8Encoding) {
            return StringSupport.utf8Length(this.value);
        }
        final long lencr = StringSupport.strLengthWithCodeRange(bytes, enc);
        final int cr = StringSupport.unpackArg(lencr);
        if (cr != 0) {
            this.setCodeRange(cr);
        }
        return StringSupport.unpackResult(lencr);
    }
    
    final int subLength(final int pos) {
        if (this.singleByteOptimizable() || pos < 0) {
            return pos;
        }
        return StringSupport.strLength(this.value.getEncoding(), this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + pos);
    }
    
    public final boolean eql(final IRubyObject other) {
        final Ruby runtime = this.getRuntime();
        if (this.getMetaClass() != runtime.getString() || this.getMetaClass() != other.getMetaClass()) {
            return super.eql(other);
        }
        return runtime.is1_9() ? this.eql19(runtime, other) : this.eql18(runtime, other);
    }
    
    private boolean eql18(final Ruby runtime, final IRubyObject other) {
        return this.value.equal(((RubyString)other).value);
    }
    
    private boolean eql19(final Ruby runtime, final IRubyObject other) {
        final RubyString otherString = (RubyString)other;
        return this.isComparableWith(otherString) && this.value.equal(((RubyString)other).value);
    }
    
    public RubyString(final Ruby runtime, final RubyClass rubyClass) {
        this(runtime, rubyClass, RubyString.EMPTY_BYTE_ARRAY);
    }
    
    public RubyString(final Ruby runtime, final RubyClass rubyClass, final CharSequence value) {
        super(runtime, rubyClass);
        this.shareLevel = 0;
        assert value != null;
        final byte[] bytes = RubyEncoding.encodeUTF8(value);
        (this.value = new ByteList(bytes, false)).setEncoding(RubyString.UTF8);
    }
    
    public RubyString(final Ruby runtime, final RubyClass rubyClass, final byte[] value) {
        super(runtime, rubyClass);
        this.shareLevel = 0;
        assert value != null;
        this.value = new ByteList(value);
    }
    
    public RubyString(final Ruby runtime, final RubyClass rubyClass, final ByteList value) {
        super(runtime, rubyClass);
        this.shareLevel = 0;
        assert value != null;
        this.value = value;
    }
    
    public RubyString(final Ruby runtime, final RubyClass rubyClass, final ByteList value, final boolean objectSpace) {
        super(runtime, rubyClass, objectSpace);
        this.shareLevel = 0;
        assert value != null;
        this.value = value;
    }
    
    protected RubyString(final Ruby runtime, final RubyClass rubyClass, final ByteList value, final Encoding enc, final int cr) {
        this(runtime, rubyClass, value);
        value.setEncoding(enc);
        this.flags |= cr;
    }
    
    protected RubyString(final Ruby runtime, final RubyClass rubyClass, final ByteList value, final Encoding enc) {
        this(runtime, rubyClass, value);
        value.setEncoding(enc);
    }
    
    protected RubyString(final Ruby runtime, final RubyClass rubyClass, final ByteList value, final int cr) {
        this(runtime, rubyClass, value);
        this.flags |= cr;
    }
    
    @Deprecated
    public RubyString newString(final CharSequence s) {
        return new RubyString(this.getRuntime(), this.getType(), s);
    }
    
    @Deprecated
    public RubyString newString(final ByteList s) {
        return new RubyString(this.getRuntime(), this.getMetaClass(), s);
    }
    
    @Deprecated
    public static RubyString newString(final Ruby runtime, final RubyClass clazz, final CharSequence str) {
        return new RubyString(runtime, clazz, str);
    }
    
    public static RubyString newStringLight(final Ruby runtime, final ByteList bytes) {
        return new RubyString(runtime, runtime.getString(), bytes, false);
    }
    
    public static RubyString newStringLight(final Ruby runtime, final int size) {
        return new RubyString(runtime, runtime.getString(), new ByteList(size), false);
    }
    
    public static RubyString newString(final Ruby runtime, final CharSequence str) {
        return new RubyString(runtime, runtime.getString(), str);
    }
    
    public static RubyString newString(final Ruby runtime, final String str) {
        return new RubyString(runtime, runtime.getString(), str);
    }
    
    public static RubyString newString(final Ruby runtime, final byte[] bytes) {
        return new RubyString(runtime, runtime.getString(), bytes);
    }
    
    public static RubyString newString(final Ruby runtime, final byte[] bytes, final int start, final int length) {
        final byte[] copy = new byte[length];
        System.arraycopy(bytes, start, copy, 0, length);
        return new RubyString(runtime, runtime.getString(), new ByteList(copy, false));
    }
    
    public static RubyString newString(final Ruby runtime, final ByteList bytes) {
        return new RubyString(runtime, runtime.getString(), bytes);
    }
    
    public static RubyString newString(final Ruby runtime, final ByteList bytes, final Encoding encoding) {
        return new RubyString(runtime, runtime.getString(), bytes, encoding);
    }
    
    public static RubyString newUnicodeString(final Ruby runtime, final String str) {
        final ByteList byteList = new ByteList(RubyEncoding.encodeUTF8(str), UTF8Encoding.INSTANCE, false);
        return new RubyString(runtime, runtime.getString(), byteList);
    }
    
    public static RubyString newStringShared(final Ruby runtime, final RubyString orig) {
        orig.shareLevel = 2;
        final RubyString str = new RubyString(runtime, runtime.getString(), orig.value);
        str.shareLevel = 2;
        return str;
    }
    
    public static RubyString newStringShared(final Ruby runtime, final ByteList bytes) {
        return newStringShared(runtime, runtime.getString(), bytes);
    }
    
    public static RubyString newStringShared(final Ruby runtime, final ByteList bytes, final Encoding encoding) {
        return newStringShared(runtime, runtime.getString(), bytes, encoding);
    }
    
    public static RubyString newStringShared(final Ruby runtime, final ByteList bytes, final int codeRange) {
        final RubyString str = new RubyString(runtime, runtime.getString(), bytes, codeRange);
        str.shareLevel = 2;
        return str;
    }
    
    public static RubyString newStringShared(final Ruby runtime, final RubyClass clazz, final ByteList bytes) {
        final RubyString str = new RubyString(runtime, clazz, bytes);
        str.shareLevel = 2;
        return str;
    }
    
    public static RubyString newStringShared(final Ruby runtime, final RubyClass clazz, final ByteList bytes, final Encoding encoding) {
        final RubyString str = new RubyString(runtime, clazz, bytes, encoding);
        str.shareLevel = 2;
        return str;
    }
    
    public static RubyString newStringShared(final Ruby runtime, final byte[] bytes) {
        return newStringShared(runtime, new ByteList(bytes, false));
    }
    
    public static RubyString newStringShared(final Ruby runtime, final byte[] bytes, final int start, final int length) {
        return newStringShared(runtime, new ByteList(bytes, start, length, false));
    }
    
    public static RubyString newEmptyString(final Ruby runtime) {
        return newEmptyString(runtime, runtime.getString());
    }
    
    public static RubyString newEmptyString(final Ruby runtime, final RubyClass metaClass) {
        final RubyString empty = new RubyString(runtime, metaClass, ByteList.EMPTY_BYTELIST);
        empty.shareLevel = 2;
        return empty;
    }
    
    public static RubyString newStringNoCopy(final Ruby runtime, final ByteList bytes) {
        return newStringNoCopy(runtime, runtime.getString(), bytes);
    }
    
    public static RubyString newStringNoCopy(final Ruby runtime, final RubyClass clazz, final ByteList bytes) {
        return new RubyString(runtime, clazz, bytes);
    }
    
    public static RubyString newStringNoCopy(final Ruby runtime, final byte[] bytes, final int start, final int length) {
        return newStringNoCopy(runtime, new ByteList(bytes, start, length, false));
    }
    
    public static RubyString newStringNoCopy(final Ruby runtime, final byte[] bytes) {
        return newStringNoCopy(runtime, new ByteList(bytes, false));
    }
    
    static EmptyByteListHolder getEmptyByteList(final Encoding enc) {
        final int index = enc.getIndex();
        final EmptyByteListHolder bytes;
        if (index < RubyString.EMPTY_BYTELISTS.length && (bytes = RubyString.EMPTY_BYTELISTS[index]) != null) {
            return bytes;
        }
        return prepareEmptyByteList(enc);
    }
    
    private static EmptyByteListHolder prepareEmptyByteList(final Encoding enc) {
        final int index = enc.getIndex();
        if (index >= RubyString.EMPTY_BYTELISTS.length) {
            final EmptyByteListHolder[] tmp = new EmptyByteListHolder[index + 4];
            System.arraycopy(RubyString.EMPTY_BYTELISTS, 0, tmp, 0, RubyString.EMPTY_BYTELISTS.length);
            RubyString.EMPTY_BYTELISTS = tmp;
        }
        return RubyString.EMPTY_BYTELISTS[index] = new EmptyByteListHolder(enc);
    }
    
    public static RubyString newEmptyString(final Ruby runtime, final RubyClass metaClass, final Encoding enc) {
        final EmptyByteListHolder holder = getEmptyByteList(enc);
        final RubyString empty = new RubyString(runtime, metaClass, holder.bytes, holder.cr);
        empty.shareLevel = 2;
        return empty;
    }
    
    public static RubyString newEmptyString(final Ruby runtime, final Encoding enc) {
        return newEmptyString(runtime, runtime.getString(), enc);
    }
    
    public static RubyString newStringNoCopy(final Ruby runtime, final RubyClass clazz, final ByteList bytes, final Encoding enc, final int cr) {
        return new RubyString(runtime, clazz, bytes, enc, cr);
    }
    
    public static RubyString newStringNoCopy(final Ruby runtime, final ByteList bytes, final Encoding enc, final int cr) {
        return newStringNoCopy(runtime, runtime.getString(), bytes, enc, cr);
    }
    
    public static RubyString newUsAsciiStringNoCopy(final Ruby runtime, final ByteList bytes) {
        return newStringNoCopy(runtime, bytes, USASCIIEncoding.INSTANCE, 32);
    }
    
    public static RubyString newUsAsciiStringShared(final Ruby runtime, final ByteList bytes) {
        final RubyString str = newStringNoCopy(runtime, bytes, USASCIIEncoding.INSTANCE, 32);
        str.shareLevel = 2;
        return str;
    }
    
    public static RubyString newUsAsciiStringShared(final Ruby runtime, final byte[] bytes, final int start, final int length) {
        final byte[] copy = new byte[length];
        System.arraycopy(bytes, start, copy, 0, length);
        return newUsAsciiStringShared(runtime, new ByteList(copy, false));
    }
    
    public int getNativeTypeIndex() {
        return 4;
    }
    
    public Class getJavaClass() {
        return String.class;
    }
    
    public RubyString convertToString() {
        return this;
    }
    
    public String toString() {
        return this.decodeString();
    }
    
    public String decodeString() {
        final Ruby runtime = this.getRuntime();
        final Encoding encoding = runtime.is1_9() ? this.getEncoding() : RubyString.UTF8;
        final Charset charset = encoding.getCharset();
        if (charset == null) {
            try {
                return new String(this.value.getUnsafeBytes(), this.value.begin(), this.value.length(), encoding.toString());
            }
            catch (UnsupportedEncodingException uee) {
                return this.value.toString();
            }
        }
        return RubyEncoding.decode(this.value.getUnsafeBytes(), this.value.begin(), this.value.length(), charset);
    }
    
    public IRubyObject dup() {
        final RubyClass mc = this.metaClass.getRealClass();
        if (mc.index != 4) {
            return super.dup();
        }
        return this.strDup(mc.getClassRuntime(), mc.getRealClass());
    }
    
    @Deprecated
    public final RubyString strDup() {
        return this.strDup(this.getRuntime(), this.getMetaClass());
    }
    
    public final RubyString strDup(final Ruby runtime) {
        return this.strDup(runtime, this.getMetaClass());
    }
    
    @Deprecated
    final RubyString strDup(final RubyClass clazz) {
        return this.strDup(this.getRuntime(), this.getMetaClass());
    }
    
    final RubyString strDup(final Ruby runtime, final RubyClass clazz) {
        this.shareLevel = 2;
        final RubyString dup = new RubyString(runtime, clazz, this.value);
        dup.shareLevel = 2;
        final RubyString rubyString = dup;
        rubyString.flags |= (this.flags & 0x78);
        return dup;
    }
    
    public final RubyString makeSharedString(final Ruby runtime, final int index, final int len) {
        return this.makeShared(runtime, runtime.getString(), index, len);
    }
    
    public final RubyString makeShared(final Ruby runtime, final int index, final int len) {
        return this.makeShared(runtime, this.getType(), index, len);
    }
    
    public final RubyString makeShared(final Ruby runtime, final RubyClass meta, final int index, final int len) {
        RubyString shared;
        if (len == 0) {
            shared = newEmptyString(runtime, meta);
        }
        else if (len == 1) {
            shared = newStringShared(runtime, meta, RubyInteger.SINGLE_CHAR_BYTELISTS[this.value.getUnsafeBytes()[this.value.getBegin() + index] & 0xFF]);
        }
        else {
            if (this.shareLevel == 0) {
                this.shareLevel = 1;
            }
            shared = new RubyString(runtime, meta, this.value.makeShared(index, len));
            shared.shareLevel = 1;
        }
        shared.infectBy(this);
        return shared;
    }
    
    public final RubyString makeShared19(final Ruby runtime, final int index, final int len) {
        return this.makeShared19(runtime, this.value, index, len);
    }
    
    private RubyString makeShared19(final Ruby runtime, final ByteList value, final int index, final int len) {
        final Encoding enc = value.getEncoding();
        final RubyClass meta = this.getType();
        RubyString shared;
        if (len == 0) {
            shared = newEmptyString(runtime, meta, enc);
        }
        else {
            if (this.shareLevel == 0) {
                this.shareLevel = 1;
            }
            shared = new RubyString(runtime, meta, value.makeShared(index, len));
            shared.shareLevel = 1;
            shared.copyCodeRangeForSubstr(this, enc);
        }
        shared.infectBy(this);
        return shared;
    }
    
    final void modifyCheck() {
        this.frozenCheck();
        if (!this.isTaint() && this.getRuntime().getSafeLevel() >= 4) {
            throw this.getRuntime().newSecurityError("Insecure: can't modify string");
        }
    }
    
    private void modifyCheck(final byte[] b, final int len) {
        if (this.value.getUnsafeBytes() != b || this.value.getRealSize() != len) {
            throw this.getRuntime().newRuntimeError("string modified");
        }
    }
    
    private void modifyCheck(final byte[] b, final int len, final Encoding enc) {
        if (this.value.getUnsafeBytes() != b || this.value.getRealSize() != len || this.value.getEncoding() != enc) {
            throw this.getRuntime().newRuntimeError("string modified");
        }
    }
    
    private void frozenCheck() {
        this.frozenCheck(false);
    }
    
    private void frozenCheck(final boolean runtimeError) {
        if (this.isFrozen()) {
            throw this.getRuntime().newFrozenError("string", runtimeError);
        }
    }
    
    public final void modify() {
        this.modifyCheck();
        if (this.shareLevel != 0) {
            if (this.shareLevel == 2) {
                this.value = this.value.dup();
            }
            else {
                this.value.unshare();
            }
            this.shareLevel = 0;
        }
        this.value.invalidate();
    }
    
    public final void modify19() {
        this.modify();
        this.clearCodeRange();
    }
    
    private void modifyAndKeepCodeRange() {
        this.modify();
        this.keepCodeRange();
    }
    
    public final void modify(final int length) {
        this.modifyCheck();
        if (this.shareLevel != 0) {
            if (this.shareLevel == 2) {
                this.value = this.value.dup(length);
            }
            else {
                this.value.unshare(length);
            }
            this.shareLevel = 0;
        }
        else {
            this.value.ensure(length);
        }
        this.value.invalidate();
    }
    
    public final void modify19(final int length) {
        this.modify(length);
        this.clearCodeRange();
    }
    
    public final void resize(final int length) {
        this.modify();
        if (this.value.getRealSize() > length) {
            this.value.setRealSize(length);
        }
        else if (this.value.length() < length) {
            this.value.length(length);
        }
    }
    
    final void view(final ByteList bytes) {
        this.modifyCheck();
        this.value = bytes;
        this.shareLevel = 0;
    }
    
    private void view(final byte[] bytes) {
        this.modifyCheck();
        this.value.replace(bytes);
        this.shareLevel = 0;
        this.value.invalidate();
    }
    
    private void view(final int index, final int len) {
        this.modifyCheck();
        if (this.shareLevel != 0) {
            if (this.shareLevel == 2) {
                this.value = this.value.makeShared(index, len);
                this.shareLevel = 1;
            }
            else {
                this.value.view(index, len);
            }
        }
        else {
            this.value.view(index, len);
            this.shareLevel = 1;
        }
        this.value.invalidate();
    }
    
    public static String bytesToString(final byte[] bytes, final int beg, final int len) {
        return new String(ByteList.plain(bytes, beg, len));
    }
    
    public static String byteListToString(final ByteList bytes) {
        return bytesToString(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
    }
    
    public static String bytesToString(final byte[] bytes) {
        return bytesToString(bytes, 0, bytes.length);
    }
    
    public static byte[] stringToBytes(final String string) {
        return ByteList.plain(string);
    }
    
    public RubyString asString() {
        return this;
    }
    
    public IRubyObject checkStringType() {
        return this;
    }
    
    public IRubyObject checkStringType19() {
        return this;
    }
    
    @JRubyMethod(name = { "try_convert" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject try_convert(final ThreadContext context, final IRubyObject recv, final IRubyObject str) {
        return str.checkStringType();
    }
    
    @JRubyMethod(name = { "to_s", "to_str" })
    public IRubyObject to_s() {
        final Ruby runtime = this.getRuntime();
        if (this.getMetaClass().getRealClass() != runtime.getString()) {
            return this.strDup(runtime, runtime.getString());
        }
        return this;
    }
    
    public final int compareTo(final IRubyObject other) {
        final Ruby runtime = this.getRuntime();
        if (other instanceof RubyString) {
            final RubyString otherString = (RubyString)other;
            return runtime.is1_9() ? this.op_cmp19(otherString) : this.op_cmp(otherString);
        }
        return (int)this.op_cmpCommon(runtime.getCurrentContext(), other).convertToInteger().getLongValue();
    }
    
    @JRubyMethod(name = { "<=>" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newFixnum(this.op_cmp((RubyString)other));
        }
        return this.op_cmpCommon(context, other);
    }
    
    @JRubyMethod(name = { "<=>" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_cmp19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newFixnum(this.op_cmp19((RubyString)other));
        }
        return this.op_cmpCommon(context, other);
    }
    
    private IRubyObject op_cmpCommon(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (!other.respondsTo("to_str") || !other.respondsTo("<=>")) {
            return runtime.getNil();
        }
        final IRubyObject result = RuntimeHelpers.invokedynamic(context, other, 4, this);
        if (result.isNil()) {
            return result;
        }
        if (result instanceof RubyFixnum) {
            return RubyFixnum.newFixnum(runtime, -((RubyFixnum)result).getLongValue());
        }
        return RubyFixnum.zero(runtime).callMethod(context, "-", result);
    }
    
    @JRubyMethod(name = { "==" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (this == other) {
            return runtime.getTrue();
        }
        if (other instanceof RubyString) {
            return this.value.equal(((RubyString)other).value) ? runtime.getTrue() : runtime.getFalse();
        }
        return this.op_equalCommon(context, other);
    }
    
    @JRubyMethod(name = { "==" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_equal19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (this == other) {
            return runtime.getTrue();
        }
        if (other instanceof RubyString) {
            final RubyString otherString = (RubyString)other;
            return (this.isComparableWith(otherString) && this.value.equal(otherString.value)) ? runtime.getTrue() : runtime.getFalse();
        }
        return this.op_equalCommon(context, other);
    }
    
    private IRubyObject op_equalCommon(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (!other.respondsTo("to_str")) {
            return runtime.getFalse();
        }
        return RuntimeHelpers.invokedynamic(context, other, 1, this).isTrue() ? runtime.getTrue() : runtime.getFalse();
    }
    
    @JRubyMethod(name = { "+" }, required = 1, compat = CompatVersion.RUBY1_8, argTypes = { RubyString.class })
    public IRubyObject op_plus(final ThreadContext context, final RubyString str) {
        final RubyString resultStr = newString(context.getRuntime(), this.addByteLists(this.value, str.value));
        resultStr.infectBy(this.flags | str.flags);
        return resultStr;
    }
    
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject other) {
        return this.op_plus(context, other.convertToString());
    }
    
    @JRubyMethod(name = { "+" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_plus19(final ThreadContext context, final RubyString str) {
        final Encoding enc = this.checkEncoding(str);
        final RubyString resultStr = newStringNoCopy(context.getRuntime(), this.addByteLists(this.value, str.value), enc, codeRangeAnd(this.getCodeRange(), str.getCodeRange()));
        resultStr.infectBy(this.flags | str.flags);
        return resultStr;
    }
    
    public IRubyObject op_plus19(final ThreadContext context, final IRubyObject other) {
        return this.op_plus19(context, other.convertToString());
    }
    
    private ByteList addByteLists(final ByteList value1, final ByteList value2) {
        final ByteList result = new ByteList(value1.getRealSize() + value2.getRealSize());
        result.setRealSize(value1.getRealSize() + value2.getRealSize());
        System.arraycopy(value1.getUnsafeBytes(), value1.getBegin(), result.getUnsafeBytes(), 0, value1.getRealSize());
        System.arraycopy(value2.getUnsafeBytes(), value2.getBegin(), result.getUnsafeBytes(), value1.getRealSize(), value2.getRealSize());
        return result;
    }
    
    @JRubyMethod(name = { "*" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject other) {
        return this.multiplyByteList(context, other);
    }
    
    @JRubyMethod(name = { "*" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_mul19(final ThreadContext context, final IRubyObject other) {
        final RubyString result = this.multiplyByteList(context, other);
        result.value.setEncoding(this.value.getEncoding());
        result.copyCodeRange(this);
        return result;
    }
    
    private RubyString multiplyByteList(final ThreadContext context, final IRubyObject arg) {
        int len = RubyNumeric.num2int(arg);
        if (len < 0) {
            throw context.getRuntime().newArgumentError("negative argument");
        }
        if (len > 0 && Integer.MAX_VALUE / len < this.value.getRealSize()) {
            throw context.getRuntime().newArgumentError("argument too big");
        }
        final ByteList bytes = new ByteList(len *= this.value.getRealSize());
        if (len > 0) {
            bytes.setRealSize(len);
            int n = this.value.getRealSize();
            System.arraycopy(this.value.getUnsafeBytes(), this.value.getBegin(), bytes.getUnsafeBytes(), 0, n);
            while (n <= len >> 1) {
                System.arraycopy(bytes.getUnsafeBytes(), 0, bytes.getUnsafeBytes(), n, n);
                n <<= 1;
            }
            System.arraycopy(bytes.getUnsafeBytes(), 0, bytes.getUnsafeBytes(), n, len - n);
        }
        final RubyString result = new RubyString(context.getRuntime(), this.getMetaClass(), bytes);
        result.infectBy(this);
        return result;
    }
    
    @JRubyMethod(name = { "%" }, required = 1)
    public IRubyObject op_format(final ThreadContext context, final IRubyObject arg) {
        return this.opFormatCommon(context, arg, context.getRuntime().getInstanceConfig().getCompatVersion());
    }
    
    private IRubyObject opFormatCommon(final ThreadContext context, final IRubyObject arg, final CompatVersion compat) {
        IRubyObject tmp = arg.checkArrayType();
        if (tmp.isNil()) {
            tmp = arg;
        }
        final ByteList out = new ByteList(this.value.getRealSize());
        boolean tainted = false;
        switch (compat) {
            case RUBY1_8: {
                tainted = Sprintf.sprintf(out, Locale.US, this.value, tmp);
                break;
            }
            case RUBY1_9: {
                tainted = Sprintf.sprintf1_9(out, Locale.US, this.value, tmp);
                break;
            }
            default: {
                throw new RuntimeException("invalid compat version for sprintf: " + compat);
            }
        }
        final RubyString str = newString(context.getRuntime(), out);
        str.setTaint(tainted || this.isTaint());
        return str;
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        final Ruby runtime = this.getRuntime();
        return RubyFixnum.newFixnum(runtime, this.strHashCode(runtime));
    }
    
    public int hashCode() {
        return this.strHashCode(this.getRuntime());
    }
    
    private int strHashCode(final Ruby runtime) {
        if (runtime.is1_9()) {
            return this.value.hashCode() ^ ((this.value.getEncoding().isAsciiCompatible() && this.scanForCodeRange() == 32) ? 0 : this.value.getEncoding().getIndex());
        }
        return this.value.hashCode();
    }
    
    public boolean equals(final Object other) {
        return this == other || (other instanceof RubyString && ((RubyString)other).value.equal(this.value));
    }
    
    public static RubyString objAsString(final ThreadContext context, final IRubyObject obj) {
        if (obj instanceof RubyString) {
            return (RubyString)obj;
        }
        final IRubyObject str = obj.callMethod(context, "to_s");
        if (!(str instanceof RubyString)) {
            return (RubyString)obj.anyToString();
        }
        if (obj.isTaint()) {
            str.setTaint(true);
        }
        return (RubyString)str;
    }
    
    public final int op_cmp(final RubyString other) {
        return this.value.cmp(other.value);
    }
    
    public final int op_cmp19(final RubyString other) {
        final int ret = this.value.cmp(other.value);
        if (ret == 0 && !this.isComparableWith(other)) {
            return (this.value.getEncoding().getIndex() > other.value.getEncoding().getIndex()) ? 1 : -1;
        }
        return ret;
    }
    
    public String asJavaString() {
        return this.toString();
    }
    
    public IRubyObject doClone() {
        return newString(this.getRuntime(), this.value.dup());
    }
    
    public final RubyString cat(final byte[] str) {
        this.modify(this.value.getRealSize() + str.length);
        System.arraycopy(str, 0, this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize(), str.length);
        this.value.setRealSize(this.value.getRealSize() + str.length);
        return this;
    }
    
    public final RubyString cat(final byte[] str, final int beg, final int len) {
        this.modify(this.value.getRealSize() + len);
        System.arraycopy(str, beg, this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize(), len);
        this.value.setRealSize(this.value.getRealSize() + len);
        return this;
    }
    
    public final RubyString cat19(final RubyString str) {
        final ByteList other = str.value;
        final int otherCr = this.cat(other.getUnsafeBytes(), other.getBegin(), other.getRealSize(), other.getEncoding(), str.getCodeRange());
        this.infectBy(str);
        str.setCodeRange(otherCr);
        return this;
    }
    
    public final RubyString cat(final ByteList str) {
        this.modify(this.value.getRealSize() + str.getRealSize());
        System.arraycopy(str.getUnsafeBytes(), str.getBegin(), this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize(), str.getRealSize());
        this.value.setRealSize(this.value.getRealSize() + str.getRealSize());
        return this;
    }
    
    public final RubyString cat(final byte ch) {
        this.modify(this.value.getRealSize() + 1);
        this.value.getUnsafeBytes()[this.value.getBegin() + this.value.getRealSize()] = ch;
        this.value.setRealSize(this.value.getRealSize() + 1);
        return this;
    }
    
    public final RubyString cat(final int ch) {
        return this.cat((byte)ch);
    }
    
    public final RubyString cat(final int code, final Encoding enc) {
        final int n = StringSupport.codeLength(this.getRuntime(), enc, code);
        this.modify(this.value.getRealSize() + n);
        enc.codeToMbc(code, this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize());
        this.value.setRealSize(this.value.getRealSize() + n);
        return this;
    }
    
    public final int cat(final byte[] bytes, final int p, final int len, final Encoding enc, int cr) {
        this.modify(this.value.getRealSize() + len);
        int toCr = this.getCodeRange();
        final Encoding toEnc = this.value.getEncoding();
        int cr2 = cr;
        if (toEnc == enc) {
            if (toCr == 0 || (toEnc == ASCIIEncoding.INSTANCE && toCr != 32)) {
                cr = 0;
            }
            else if (cr == 0) {
                cr = StringSupport.codeRangeScan(enc, bytes, p, len);
            }
        }
        else if (!toEnc.isAsciiCompatible() || !enc.isAsciiCompatible()) {
            if (len == 0) {
                return toCr;
            }
            if (this.value.getRealSize() == 0) {
                System.arraycopy(bytes, p, this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize(), len);
                this.value.setRealSize(this.value.getRealSize() + len);
                this.setEncodingAndCodeRange(enc, cr);
                return cr;
            }
            throw this.getRuntime().newEncodingCompatibilityError("incompatible character encodings: " + toEnc + " and " + enc);
        }
        else {
            if (cr == 0) {
                cr = StringSupport.codeRangeScan(enc, bytes, p, len);
            }
            if (toCr == 0 && (toEnc == ASCIIEncoding.INSTANCE || cr != 32)) {
                toCr = this.scanForCodeRange();
            }
        }
        if (cr2 != 0) {
            cr2 = cr;
        }
        if (toEnc != enc && toCr != 32 && cr != 32) {
            throw this.getRuntime().newEncodingCompatibilityError("incompatible character encodings: " + toEnc + " and " + enc);
        }
        Encoding resEnc;
        int resCr;
        if (toCr == 0) {
            resEnc = toEnc;
            resCr = 0;
        }
        else if (toCr == 32) {
            if (cr == 32) {
                resEnc = ((toEnc == ASCIIEncoding.INSTANCE) ? toEnc : enc);
                resCr = 32;
            }
            else {
                resEnc = enc;
                resCr = cr;
            }
        }
        else if (toCr == 64) {
            resEnc = toEnc;
            if (cr == 32 || cr == 64) {
                resCr = toCr;
            }
            else {
                resCr = cr;
            }
        }
        else {
            resEnc = toEnc;
            resCr = ((len > 0) ? 0 : toCr);
        }
        if (len < 0) {
            throw this.getRuntime().newArgumentError("negative string size (or size too big)");
        }
        System.arraycopy(bytes, p, this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize(), len);
        this.value.setRealSize(this.value.getRealSize() + len);
        this.setEncodingAndCodeRange(resEnc, resCr);
        return cr2;
    }
    
    public final int cat(final byte[] bytes, final int p, final int len, final Encoding enc) {
        return this.cat(bytes, p, len, enc, 0);
    }
    
    public final RubyString catAscii(final byte[] bytes, int p, final int len) {
        final Encoding enc = this.value.getEncoding();
        if (enc.isAsciiCompatible()) {
            this.cat(bytes, p, len, enc, 32);
        }
        else {
            final byte[] buf = new byte[enc.maxLength()];
            for (int end = p + len; p < end; ++p) {
                final int c = bytes[p];
                final int cl = StringSupport.codeLength(this.getRuntime(), enc, c);
                enc.codeToMbc(c, buf, 0);
                this.cat(buf, 0, cl, enc, 64);
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "replace", "initialize_copy" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject replace(final IRubyObject other) {
        if (this == other) {
            return this;
        }
        this.replaceCommon(other);
        return this;
    }
    
    @JRubyMethod(name = { "replace", "initialize_copy" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyString replace19(final IRubyObject other) {
        this.modifyCheck();
        if (this == other) {
            return this;
        }
        this.setCodeRange(this.replaceCommon(other).getCodeRange());
        return this;
    }
    
    private RubyString replaceCommon(final IRubyObject other) {
        this.modifyCheck();
        final RubyString convertToString;
        final RubyString otherStr = convertToString = other.convertToString();
        final int n = 2;
        this.shareLevel = n;
        convertToString.shareLevel = n;
        this.value = otherStr.value;
        this.infectBy(otherStr);
        return otherStr;
    }
    
    @JRubyMethod(name = { "clear" }, compat = CompatVersion.RUBY1_9)
    public RubyString clear() {
        this.modifyCheck();
        final Encoding enc = this.value.getEncoding();
        final EmptyByteListHolder holder = getEmptyByteList(enc);
        this.value = holder.bytes;
        this.shareLevel = 2;
        this.setCodeRange(holder.cr);
        return this;
    }
    
    @JRubyMethod(name = { "reverse" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject reverse(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() <= 1) {
            return this.strDup(context.getRuntime());
        }
        final byte[] bytes = this.value.getUnsafeBytes();
        final int p = this.value.getBegin();
        final int len = this.value.getRealSize();
        final byte[] obytes = new byte[len];
        for (int i = 0; i <= len >> 1; ++i) {
            obytes[i] = bytes[p + len - i - 1];
            obytes[len - i - 1] = bytes[p + i];
        }
        return new RubyString(runtime, this.getMetaClass(), new ByteList(obytes, false)).infectBy(this);
    }
    
    @JRubyMethod(name = { "reverse" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject reverse19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() <= 1) {
            return this.strDup(context.getRuntime());
        }
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int len = this.value.getRealSize();
        final byte[] obytes = new byte[len];
        boolean single = true;
        final Encoding enc = this.value.getEncoding();
        if (this.singleByteOptimizable(enc)) {
            for (int i = 0; i <= len >> 1; ++i) {
                obytes[i] = bytes[p + len - i - 1];
                obytes[len - i - 1] = bytes[p + i];
            }
        }
        else {
            final int end = p + len;
            int op = len;
            while (p < end) {
                final int cl = StringSupport.length(enc, bytes, p, end);
                if (cl > 1 || (bytes[p] & 0x80) != 0x0) {
                    single = false;
                    op -= cl;
                    System.arraycopy(bytes, p, obytes, op, cl);
                    p += cl;
                }
                else {
                    obytes[--op] = bytes[p++];
                }
            }
        }
        final RubyString result = new RubyString(runtime, this.getMetaClass(), new ByteList(obytes, false));
        if (this.getCodeRange() == 0) {
            this.setCodeRange(single ? 32 : 64);
        }
        final Encoding encoding = this.value.getEncoding();
        result.value.setEncoding(encoding);
        result.copyCodeRangeForSubstr(this, encoding);
        return result.infectBy(this);
    }
    
    @JRubyMethod(name = { "reverse!" }, compat = CompatVersion.RUBY1_8)
    public RubyString reverse_bang(final ThreadContext context) {
        if (this.value.getRealSize() > 1) {
            this.modify();
            final byte[] bytes = this.value.getUnsafeBytes();
            final int p = this.value.getBegin();
            for (int len = this.value.getRealSize(), i = 0; i < len >> 1; ++i) {
                final byte b = bytes[p + i];
                bytes[p + i] = bytes[p + len - i - 1];
                bytes[p + len - i - 1] = b;
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "reverse!" }, compat = CompatVersion.RUBY1_9)
    public RubyString reverse_bang19(final ThreadContext context) {
        this.modifyCheck();
        if (this.value.getRealSize() > 1) {
            this.modifyAndKeepCodeRange();
            final byte[] bytes = this.value.getUnsafeBytes();
            int p = this.value.getBegin();
            final int len = this.value.getRealSize();
            final Encoding enc = this.value.getEncoding();
            if (this.singleByteOptimizable(enc)) {
                for (int i = 0; i < len >> 1; ++i) {
                    final byte b = bytes[p + i];
                    bytes[p + i] = bytes[p + len - i - 1];
                    bytes[p + len - i - 1] = b;
                }
            }
            else {
                final int end = p + len;
                int op = len;
                final byte[] obytes = new byte[len];
                boolean single = true;
                while (p < end) {
                    final int cl = StringSupport.length(enc, bytes, p, end);
                    if (cl > 1 || (bytes[p] & 0x80) != 0x0) {
                        single = false;
                        op -= cl;
                        System.arraycopy(bytes, p, obytes, op, cl);
                        p += cl;
                    }
                    else {
                        obytes[--op] = bytes[p++];
                    }
                }
                this.value.setUnsafeBytes(obytes);
                if (this.getCodeRange() == 0) {
                    this.setCodeRange(single ? 32 : 64);
                }
            }
        }
        return this;
    }
    
    public static RubyString newInstance(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyString newString = newStringShared(recv.getRuntime(), ByteList.EMPTY_BYTELIST);
        newString.setMetaClass((RubyClass)recv);
        newString.callInit(args, block);
        return newString;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize() {
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public IRubyObject initialize(final IRubyObject arg0) {
        this.replace(arg0);
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19() {
        return this;
    }
    
    @JRubyMethod(name = { "initialize" }, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize19(final IRubyObject arg0) {
        this.replace19(arg0);
        return this;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject casecmp(final ThreadContext context, final IRubyObject other) {
        return RubyFixnum.newFixnum(context.getRuntime(), this.value.caseInsensitiveCmp(other.convertToString().value));
    }
    
    @JRubyMethod(name = { "casecmp" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject casecmp19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        final RubyString otherStr = other.convertToString();
        final Encoding enc = this.isCompatibleWith(otherStr);
        if (enc == null) {
            return runtime.getNil();
        }
        if (this.singleByteOptimizable() && otherStr.singleByteOptimizable()) {
            return RubyFixnum.newFixnum(runtime, this.value.caseInsensitiveCmp(otherStr.value));
        }
        return this.multiByteCasecmp(runtime, enc, this.value, otherStr.value);
    }
    
    private IRubyObject multiByteCasecmp(final Ruby runtime, final Encoding enc, final ByteList value, final ByteList otherValue) {
        final byte[] bytes = value.getUnsafeBytes();
        int p = value.getBegin();
        final int end = p + value.getRealSize();
        final byte[] obytes = otherValue.getUnsafeBytes();
        int op;
        int oend;
        int cl;
        int ocl;
        for (op = otherValue.getBegin(), oend = op + otherValue.getRealSize(); p < end && op < oend; p += cl, op += ocl) {
            int c;
            int oc;
            if (enc.isAsciiCompatible()) {
                c = (bytes[p] & 0xFF);
                oc = (obytes[op] & 0xFF);
            }
            else {
                c = StringSupport.preciseCodePoint(enc, bytes, p, end);
                oc = StringSupport.preciseCodePoint(enc, obytes, op, oend);
            }
            if (Encoding.isAscii(c) && Encoding.isAscii(oc)) {
                final byte uc = AsciiTables.ToUpperCaseTable[c];
                final byte uoc = AsciiTables.ToUpperCaseTable[oc];
                if (uc != uoc) {
                    return (uc < uoc) ? RubyFixnum.minus_one(runtime) : RubyFixnum.one(runtime);
                }
                ocl = (cl = 1);
            }
            else {
                cl = StringSupport.length(enc, bytes, p, end);
                ocl = StringSupport.length(enc, obytes, op, oend);
                final int ret = StringSupport.caseCmp(bytes, p, obytes, op, (cl < ocl) ? cl : ocl);
                if (ret != 0) {
                    return (ret < 0) ? RubyFixnum.minus_one(runtime) : RubyFixnum.one(runtime);
                }
                if (cl != ocl) {
                    return (cl < ocl) ? RubyFixnum.minus_one(runtime) : RubyFixnum.one(runtime);
                }
            }
        }
        if (end - p == oend - op) {
            return RubyFixnum.zero(runtime);
        }
        return (end - p > oend - op) ? RubyFixnum.one(runtime) : RubyFixnum.minus_one(runtime);
    }
    
    @JRubyMethod(name = { "=~" }, compat = CompatVersion.RUBY1_8, writes = { FrameField.BACKREF })
    public IRubyObject op_match(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyRegexp) {
            return ((RubyRegexp)other).op_match(context, this);
        }
        if (other instanceof RubyString) {
            throw context.getRuntime().newTypeError("type mismatch: String given");
        }
        return other.callMethod(context, "=~", this);
    }
    
    @JRubyMethod(name = { "=~" }, compat = CompatVersion.RUBY1_9, writes = { FrameField.BACKREF })
    public IRubyObject op_match19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyRegexp) {
            return ((RubyRegexp)other).op_match19(context, this);
        }
        if (other instanceof RubyString) {
            throw context.getRuntime().newTypeError("type mismatch: String given");
        }
        return other.callMethod(context, "=~", this);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8, reads = { FrameField.BACKREF })
    public IRubyObject match(final ThreadContext context, final IRubyObject pattern) {
        return this.getPattern(pattern).callMethod(context, "match", this);
    }
    
    @JRubyMethod(name = { "match" }, compat = CompatVersion.RUBY1_9, reads = { FrameField.BACKREF })
    public IRubyObject match19(final ThreadContext context, final IRubyObject pattern, final Block block) {
        final IRubyObject result = this.getPattern(pattern).callMethod(context, "match", this);
        return (block.isGiven() && !result.isNil()) ? block.yield(context, result) : result;
    }
    
    @JRubyMethod(name = { "match" }, required = 2, rest = true, compat = CompatVersion.RUBY1_9, reads = { FrameField.BACKREF })
    public IRubyObject match19(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final RubyRegexp pattern = this.getPattern(args[0]);
        args[0] = this;
        final IRubyObject result = pattern.callMethod(context, "match", args);
        return (block.isGiven() && !result.isNil()) ? block.yield(context, result) : result;
    }
    
    @JRubyMethod(name = { "capitalize" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject capitalize(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.capitalize_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "capitalize!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject capitalize_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modify();
        int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        boolean modify = false;
        int c = bytes[s] & 0xFF;
        if (RubyString.ASCII.isLower(c)) {
            bytes[s] = AsciiTables.ToUpperCaseTable[c];
            modify = true;
        }
        while (++s < end) {
            c = (bytes[s] & 0xFF);
            if (RubyString.ASCII.isUpper(c)) {
                bytes[s] = AsciiTables.ToLowerCaseTable[c];
                modify = true;
            }
        }
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { "capitalize" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject capitalize19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.capitalize_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "capitalize!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject capitalize_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = this.checkDummyEncoding();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modifyAndKeepCodeRange();
        int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        boolean modify = false;
        int c = StringSupport.codePoint(runtime, enc, bytes, s, end);
        if (enc.isLower(c)) {
            enc.codeToMbc(StringSupport.toUpper(enc, c), bytes, s);
            modify = true;
        }
        for (s += StringSupport.codeLength(runtime, enc, c); s < end; s += StringSupport.codeLength(runtime, enc, c)) {
            c = StringSupport.codePoint(runtime, enc, bytes, s, end);
            if (enc.isUpper(c)) {
                enc.codeToMbc(StringSupport.toLower(enc, c), bytes, s);
                modify = true;
            }
        }
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { ">=" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_ge(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp((RubyString)other) >= 0);
        }
        return RubyComparable.op_ge(context, this, other);
    }
    
    @JRubyMethod(name = { ">=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_ge19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp19((RubyString)other) >= 0);
        }
        return RubyComparable.op_ge(context, this, other);
    }
    
    @JRubyMethod(name = { ">" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_gt(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp((RubyString)other) > 0);
        }
        return RubyComparable.op_gt(context, this, other);
    }
    
    @JRubyMethod(name = { ">" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_gt19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp19((RubyString)other) > 0);
        }
        return RubyComparable.op_gt(context, this, other);
    }
    
    @JRubyMethod(name = { "<=" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_le(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp((RubyString)other) <= 0);
        }
        return RubyComparable.op_le(context, this, other);
    }
    
    @JRubyMethod(name = { "<=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_le19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp19((RubyString)other) <= 0);
        }
        return RubyComparable.op_le(context, this, other);
    }
    
    @JRubyMethod(name = { "<" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_lt(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp((RubyString)other) < 0);
        }
        return RubyComparable.op_lt(context, this, other);
    }
    
    @JRubyMethod(name = { "<" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_lt19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            return context.getRuntime().newBoolean(this.op_cmp19((RubyString)other) < 0);
        }
        return RubyComparable.op_lt(context, this, other);
    }
    
    @JRubyMethod(name = { "eql?" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject str_eql_p(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyString && this.value.equal(((RubyString)other).value)) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(name = { "eql?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject str_eql_p19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyString) {
            final RubyString otherString = (RubyString)other;
            if (this.isComparableWith(otherString) && this.value.equal(otherString.value)) {
                return runtime.getTrue();
            }
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(name = { "upcase" }, compat = CompatVersion.RUBY1_8)
    public RubyString upcase(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.upcase_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "upcase!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject upcase_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modify();
        return this.singleByteUpcase(runtime, this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "upcase" }, compat = CompatVersion.RUBY1_9)
    public RubyString upcase19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.upcase_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "upcase!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject upcase_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = this.checkDummyEncoding();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modifyAndKeepCodeRange();
        final int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        if (this.singleByteOptimizable(enc)) {
            return this.singleByteUpcase(runtime, bytes, s, end);
        }
        return this.multiByteUpcase(runtime, enc, bytes, s, end);
    }
    
    private IRubyObject singleByteUpcase(final Ruby runtime, final byte[] bytes, int s, final int end) {
        boolean modify = false;
        while (s < end) {
            final int c = bytes[s] & 0xFF;
            if (RubyString.ASCII.isLower(c)) {
                bytes[s] = AsciiTables.ToUpperCaseTable[c];
                modify = true;
            }
            ++s;
        }
        return modify ? this : runtime.getNil();
    }
    
    private IRubyObject multiByteUpcase(final Ruby runtime, final Encoding enc, final byte[] bytes, int s, final int end) {
        boolean modify = false;
        while (s < end) {
            int c;
            if (enc.isAsciiCompatible() && Encoding.isAscii(c = (bytes[s] & 0xFF))) {
                if (RubyString.ASCII.isLower(c)) {
                    bytes[s] = AsciiTables.ToUpperCaseTable[c];
                    modify = true;
                }
                ++s;
            }
            else {
                c = StringSupport.codePoint(runtime, enc, bytes, s, end);
                if (enc.isLower(c)) {
                    enc.codeToMbc(StringSupport.toUpper(enc, c), bytes, s);
                    modify = true;
                }
                s += StringSupport.codeLength(runtime, enc, c);
            }
        }
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { "downcase" }, compat = CompatVersion.RUBY1_8)
    public RubyString downcase(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.downcase_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "downcase!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject downcase_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modify();
        return this.singleByteDowncase(runtime, this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "downcase" }, compat = CompatVersion.RUBY1_9)
    public RubyString downcase19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.downcase_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "downcase!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject downcase_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = this.checkDummyEncoding();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modifyAndKeepCodeRange();
        final int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        if (this.singleByteOptimizable(enc)) {
            return this.singleByteDowncase(runtime, bytes, s, end);
        }
        return this.multiByteDowncase(runtime, enc, bytes, s, end);
    }
    
    private IRubyObject singleByteDowncase(final Ruby runtime, final byte[] bytes, int s, final int end) {
        boolean modify = false;
        while (s < end) {
            final int c = bytes[s] & 0xFF;
            if (RubyString.ASCII.isUpper(c)) {
                bytes[s] = AsciiTables.ToLowerCaseTable[c];
                modify = true;
            }
            ++s;
        }
        return modify ? this : runtime.getNil();
    }
    
    private IRubyObject multiByteDowncase(final Ruby runtime, final Encoding enc, final byte[] bytes, int s, final int end) {
        boolean modify = false;
        while (s < end) {
            int c;
            if (enc.isAsciiCompatible() && Encoding.isAscii(c = (bytes[s] & 0xFF))) {
                if (RubyString.ASCII.isUpper(c)) {
                    bytes[s] = AsciiTables.ToLowerCaseTable[c];
                    modify = true;
                }
                ++s;
            }
            else {
                c = StringSupport.codePoint(runtime, enc, bytes, s, end);
                if (enc.isUpper(c)) {
                    enc.codeToMbc(StringSupport.toLower(enc, c), bytes, s);
                    modify = true;
                }
                s += StringSupport.codeLength(runtime, enc, c);
            }
        }
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { "swapcase" }, compat = CompatVersion.RUBY1_8)
    public RubyString swapcase(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.swapcase_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "swapcase!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject swapcase_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modify();
        return this.singleByteSwapcase(runtime, this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "swapcase" }, compat = CompatVersion.RUBY1_9)
    public RubyString swapcase19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.swapcase_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "swapcase!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject swapcase_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = this.checkDummyEncoding();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        this.modifyAndKeepCodeRange();
        final int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        if (this.singleByteOptimizable(enc)) {
            return this.singleByteSwapcase(runtime, bytes, s, end);
        }
        return this.multiByteSwapcase(runtime, enc, bytes, s, end);
    }
    
    private IRubyObject singleByteSwapcase(final Ruby runtime, final byte[] bytes, int s, final int end) {
        boolean modify = false;
        while (s < end) {
            final int c = bytes[s] & 0xFF;
            if (RubyString.ASCII.isUpper(c)) {
                bytes[s] = AsciiTables.ToLowerCaseTable[c];
                modify = true;
            }
            else if (RubyString.ASCII.isLower(c)) {
                bytes[s] = AsciiTables.ToUpperCaseTable[c];
                modify = true;
            }
            ++s;
        }
        return modify ? this : runtime.getNil();
    }
    
    private IRubyObject multiByteSwapcase(final Ruby runtime, final Encoding enc, final byte[] bytes, int s, final int end) {
        boolean modify = false;
        while (s < end) {
            final int c = StringSupport.codePoint(runtime, enc, bytes, s, end);
            if (enc.isUpper(c)) {
                enc.codeToMbc(StringSupport.toLower(enc, c), bytes, s);
                modify = true;
            }
            else if (enc.isLower(c)) {
                enc.codeToMbc(StringSupport.toUpper(enc, c), bytes, s);
                modify = true;
            }
            s += StringSupport.codeLength(runtime, enc, c);
        }
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { "dump" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject dump() {
        return this.dumpCommon(false);
    }
    
    @JRubyMethod(name = { "dump" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject dump19() {
        return this.dumpCommon(true);
    }
    
    private IRubyObject dumpCommon(final boolean is1_9) {
        final Ruby runtime = this.getRuntime();
        ByteList buf = null;
        Encoding enc = this.value.getEncoding();
        int p = this.value.getBegin();
        int end = p + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        int len = 2;
        while (p < end) {
            final int c = bytes[p++] & 0xFF;
            switch (c) {
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 27:
                case 34:
                case 92: {
                    len += 2;
                    continue;
                }
                case 35: {
                    len += (this.isEVStr(bytes, p, end) ? 2 : 1);
                    continue;
                }
                default: {
                    if (RubyString.ASCII.isPrint(c)) {
                        ++len;
                        continue;
                    }
                    if (is1_9 && enc instanceof UTF8Encoding) {
                        final int n = StringSupport.preciseLength(enc, bytes, p - 1, end) - 1;
                        if (n > 0) {
                            if (buf == null) {
                                buf = new ByteList();
                            }
                            final int cc = StringSupport.codePoint(runtime, enc, bytes, p - 1, end);
                            Sprintf.sprintf(runtime, buf, "%x", cc);
                            len += buf.getRealSize() + 4;
                            buf.setRealSize(0);
                            p += n;
                            continue;
                        }
                    }
                    len += 4;
                    continue;
                }
            }
        }
        if (is1_9 && !enc.isAsciiCompatible()) {
            len += ".force_encoding(\"".length() + enc.getName().length + "\")".length();
        }
        final ByteList outBytes = new ByteList(len);
        final byte[] out = outBytes.getUnsafeBytes();
        int q = 0;
        p = this.value.getBegin();
        end = p + this.value.getRealSize();
        out[q++] = 34;
        while (p < end) {
            final int c2 = bytes[p++] & 0xFF;
            if (c2 == 34 || c2 == 92) {
                out[q++] = 92;
                out[q++] = (byte)c2;
            }
            else if (c2 == 35) {
                if (this.isEVStr(bytes, p, end)) {
                    out[q++] = 92;
                }
                out[q++] = 35;
            }
            else if (!is1_9 && RubyString.ASCII.isPrint(c2)) {
                out[q++] = (byte)c2;
            }
            else if (c2 == 10) {
                out[q++] = 92;
                out[q++] = 110;
            }
            else if (c2 == 13) {
                out[q++] = 92;
                out[q++] = 114;
            }
            else if (c2 == 9) {
                out[q++] = 92;
                out[q++] = 116;
            }
            else if (c2 == 12) {
                out[q++] = 92;
                out[q++] = 102;
            }
            else if (c2 == 11) {
                out[q++] = 92;
                out[q++] = 118;
            }
            else if (c2 == 8) {
                out[q++] = 92;
                out[q++] = 98;
            }
            else if (c2 == 7) {
                out[q++] = 92;
                out[q++] = 97;
            }
            else if (c2 == 27) {
                out[q++] = 92;
                out[q++] = 101;
            }
            else if (is1_9 && RubyString.ASCII.isPrint(c2)) {
                out[q++] = (byte)c2;
            }
            else {
                out[q++] = 92;
                if (is1_9) {
                    if (enc instanceof UTF8Encoding) {
                        final int n2 = StringSupport.preciseLength(enc, bytes, p - 1, end) - 1;
                        if (n2 > 0) {
                            final int cc2 = StringSupport.codePoint(runtime, enc, bytes, p - 1, end);
                            p += n2;
                            outBytes.setRealSize(q);
                            Sprintf.sprintf(runtime, outBytes, "u{%x}", cc2);
                            q = outBytes.getRealSize();
                            continue;
                        }
                    }
                    outBytes.setRealSize(q);
                    Sprintf.sprintf(runtime, outBytes, "x%02X", c2);
                    q = outBytes.getRealSize();
                }
                else {
                    outBytes.setRealSize(q);
                    Sprintf.sprintf(runtime, outBytes, "%03o", c2);
                    q = outBytes.getRealSize();
                }
            }
        }
        out[q++] = 34;
        outBytes.setRealSize(q);
        assert out == outBytes.getUnsafeBytes();
        final RubyString result = new RubyString(runtime, this.getMetaClass(), outBytes);
        if (is1_9) {
            if (!enc.isAsciiCompatible()) {
                result.cat(".force_encoding(\"".getBytes());
                result.cat(enc.getName());
                result.cat((byte)34).cat((byte)41);
                enc = RubyString.ASCII;
            }
            result.associateEncoding(enc);
            result.setCodeRange(32);
        }
        return result.infectBy(this);
    }
    
    @JRubyMethod(name = { "insert" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject insert(final ThreadContext context, final IRubyObject indexArg, final IRubyObject stringArg) {
        assert !context.getRuntime().is1_9();
        final RubyString str = stringArg.convertToString();
        int index = RubyNumeric.num2int(indexArg);
        if (index == -1) {
            return this.append(stringArg);
        }
        if (index < 0) {
            ++index;
        }
        this.replaceInternal(this.checkIndex(index, this.value.getRealSize()), 0, str);
        return this;
    }
    
    @JRubyMethod(name = { "insert" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject insert19(final ThreadContext context, final IRubyObject indexArg, final IRubyObject stringArg) {
        final RubyString str = stringArg.convertToString();
        int index = RubyNumeric.num2int(indexArg);
        if (index == -1) {
            return this.append19(stringArg);
        }
        if (index < 0) {
            ++index;
        }
        this.replaceInternal19(this.checkIndex(index, this.strLength()), 0, str);
        return this;
    }
    
    private int checkIndex(int beg, final int len) {
        if (beg > len) {
            this.raiseIndexOutOfString(beg);
        }
        if (beg < 0) {
            if (-beg > len) {
                this.raiseIndexOutOfString(beg);
            }
            beg += len;
        }
        return beg;
    }
    
    private int checkIndexForRef(int beg, final int len) {
        if (beg >= len) {
            this.raiseIndexOutOfString(beg);
        }
        if (beg < 0) {
            if (-beg > len) {
                this.raiseIndexOutOfString(beg);
            }
            beg += len;
        }
        return beg;
    }
    
    private int checkLength(final int len) {
        if (len < 0) {
            throw this.getRuntime().newIndexError("negative length " + len);
        }
        return len;
    }
    
    private void raiseIndexOutOfString(final int index) {
        throw this.getRuntime().newIndexError("index " + index + " out of string");
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject inspect() {
        final int start = this.value.getBegin();
        final int len = this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        try {
            return this.inspectCommon(false);
        }
        catch (ArrayIndexOutOfBoundsException x) {
            System.out.println("" + start + ", " + len + ", " + Arrays.toString(bytes));
            throw x;
        }
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject inspect19() {
        return this.inspectCommon(true);
    }
    
    private void prefixEscapeCat(final int c) {
        this.cat(92);
        this.cat(c);
    }
    
    private void escapeCodePointCat(final Ruby runtime, final byte[] bytes, final int p, final int n) {
        for (int q = p - n; q < p; ++q) {
            Sprintf.sprintf(runtime, this.value, "\\x%02X", bytes[q] & 0xFF);
        }
    }
    
    final IRubyObject inspectCommon(final boolean is1_9) {
        final Ruby runtime = this.getRuntime();
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final RubyString result = new RubyString(runtime, runtime.getString(), new ByteList(end - p));
        Encoding enc;
        if (is1_9) {
            enc = this.value.getEncoding();
            if (enc != runtime.getKCode().getEncoding()) {
                enc = runtime.getKCode().getEncoding();
            }
            if (!enc.isAsciiCompatible()) {
                enc = USASCIIEncoding.INSTANCE;
            }
            result.associateEncoding(enc);
        }
        else {
            enc = runtime.getKCode().getEncoding();
        }
        result.cat(34);
        while (p < end) {
            int n;
            int c;
            if (is1_9) {
                n = StringSupport.preciseLength(enc, bytes, p, end);
                if (n <= 0) {
                    ++p;
                    n = 1;
                    result.escapeCodePointCat(runtime, bytes, p, n);
                    continue;
                }
                c = StringSupport.codePoint(runtime, enc, bytes, p, end);
                n = StringSupport.codeLength(runtime, enc, c);
                p += n;
            }
            else {
                c = (bytes[p++] & 0xFF);
                n = enc.length((byte)c);
            }
            if (!is1_9 && n > 1 && p - 1 <= end - n) {
                try {
                    result.cat(bytes, p - 1, n);
                }
                catch (ArrayIndexOutOfBoundsException x) {
                    System.out.println("begin = " + (p - 1));
                    System.out.println("len = " + n);
                    System.out.println("bytes = " + Arrays.toString(bytes));
                    throw x;
                }
                p += n - 1;
            }
            else if (c == 34 || c == 92) {
                result.prefixEscapeCat(c);
            }
            else {
                if (c == 35) {
                    if (is1_9) {
                        int cc;
                        if (p < end && StringSupport.preciseLength(enc, bytes, p, end) > 0 && this.isEVStr(cc = StringSupport.codePoint(runtime, enc, bytes, p, end))) {
                            if ("$@{".indexOf(cc) != -1) {
                                cc = 35;
                            }
                            result.prefixEscapeCat(cc);
                            continue;
                        }
                    }
                    else if (this.isEVStr(bytes, p, end)) {
                        result.prefixEscapeCat(c);
                        continue;
                    }
                }
                if (!is1_9 && RubyString.ASCII.isPrint(c)) {
                    result.cat(c);
                }
                else if (c == 10) {
                    result.prefixEscapeCat(110);
                }
                else if (c == 13) {
                    result.prefixEscapeCat(114);
                }
                else if (c == 9) {
                    result.prefixEscapeCat(116);
                }
                else if (c == 12) {
                    result.prefixEscapeCat(102);
                }
                else if (c == 11) {
                    result.prefixEscapeCat(118);
                }
                else if (c == 8) {
                    result.prefixEscapeCat(98);
                }
                else if (c == 7) {
                    result.prefixEscapeCat(97);
                }
                else if (c == 27) {
                    result.prefixEscapeCat(101);
                }
                else if (is1_9 && enc.isPrint(c)) {
                    result.cat(bytes, p - n, n, enc);
                }
                else if (!is1_9) {
                    Sprintf.sprintf(runtime, result.value, "\\%03o", c & 0xFF);
                }
                else {
                    result.escapeCodePointCat(runtime, bytes, p, n);
                }
            }
        }
        result.cat(34);
        return result.infectBy(this);
    }
    
    private boolean isEVStr(final byte[] bytes, final int p, final int end) {
        return p < end && this.isEVStr(bytes[p] & 0xFF);
    }
    
    public boolean isEVStr(final int c) {
        return c == 36 || c == 64 || c == 123;
    }
    
    @JRubyMethod(name = { "length", "size" }, compat = CompatVersion.RUBY1_8)
    public RubyFixnum length() {
        return this.getRuntime().newFixnum(this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "length", "size" }, compat = CompatVersion.RUBY1_9)
    public RubyFixnum length19() {
        return this.getRuntime().newFixnum(this.strLength());
    }
    
    @JRubyMethod(name = { "bytesize" })
    public RubyFixnum bytesize() {
        return this.length();
    }
    
    @JRubyMethod(name = { "empty?" })
    public RubyBoolean empty_p(final ThreadContext context) {
        return this.isEmpty() ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    public boolean isEmpty() {
        return this.value.length() == 0;
    }
    
    public RubyString append(final IRubyObject other) {
        final RubyString otherStr = other.convertToString();
        this.infectBy(otherStr);
        return this.cat(otherStr.value);
    }
    
    public RubyString append19(final IRubyObject other) {
        return this.cat19(other.convertToString());
    }
    
    @JRubyMethod(name = { "concat", "<<" }, compat = CompatVersion.RUBY1_8)
    public RubyString concat(final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            final long longValue = ((RubyFixnum)other).getLongValue();
            if (longValue >= 0L && longValue < 256L) {
                return this.cat((byte)longValue);
            }
        }
        return this.append(other);
    }
    
    @JRubyMethod(name = { "concat", "<<" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public RubyString concat19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyFixnum) {
            final int c = RubyNumeric.num2int(other);
            if (c < 0) {
                throw runtime.newRangeError("negative string size (or size too big)");
            }
            return this.concatNumeric(runtime, c);
        }
        else {
            if (!(other instanceof RubyBignum)) {
                return this.append19(other);
            }
            if (((RubyBignum)other).getBigIntegerValue().signum() < 0) {
                throw runtime.newRangeError("negative string size (or size too big)");
            }
            final long c2 = ((RubyBignum)other).getLongValue();
            return this.concatNumeric(runtime, (int)c2);
        }
    }
    
    private RubyString concatNumeric(final Ruby runtime, final int c) {
        final Encoding enc = this.value.getEncoding();
        final int cl = StringSupport.codeLength(runtime, enc, c);
        this.modify19(this.value.getRealSize() + cl);
        enc.codeToMbc(c, this.value.getUnsafeBytes(), this.value.getBegin() + this.value.getRealSize());
        this.value.setRealSize(this.value.getRealSize() + cl);
        return this;
    }
    
    @JRubyMethod(name = { "crypt" })
    public RubyString crypt(final ThreadContext context, final IRubyObject other) {
        final RubyString otherStr = other.convertToString();
        ByteList salt = otherStr.getByteList();
        if (salt.getRealSize() < 2) {
            throw context.getRuntime().newArgumentError("salt too short(need >=2 bytes)");
        }
        salt = salt.makeShared(0, 2);
        final RubyString result = newStringShared(context.getRuntime(), JavaCrypt.crypt(salt, this.getByteList()));
        result.infectBy(this);
        result.infectBy(otherStr);
        return result;
    }
    
    public static RubyString stringValue(final IRubyObject object) {
        return (RubyString)((object instanceof RubyString) ? object : object.convertToString());
    }
    
    @JRubyMethod(reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject sub(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final RubyString str = this.strDup(context.getRuntime());
        str.sub_bang(context, arg0, block);
        return str;
    }
    
    @JRubyMethod(reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject sub(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final RubyString str = this.strDup(context.getRuntime());
        str.sub_bang(context, arg0, arg1, block);
        return str;
    }
    
    @JRubyMethod(name = { "sub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject sub_bang(final ThreadContext context, final IRubyObject arg0, final Block block) {
        if (block.isGiven()) {
            return this.subBangIter(context, this.getQuotedPattern(arg0), block);
        }
        throw context.getRuntime().newArgumentError(1, 2);
    }
    
    @JRubyMethod(name = { "sub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject sub_bang(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.subBangNoIter(context, this.getQuotedPattern(arg0), arg1.convertToString());
    }
    
    private IRubyObject subBangIter(final ThreadContext context, final Regex pattern, final Block block) {
        final int range = this.value.getBegin() + this.value.getRealSize();
        final Matcher matcher = pattern.matcher(this.value.getUnsafeBytes(), this.value.getBegin(), range);
        final DynamicScope scope = context.getCurrentScope();
        if (matcher.search(this.value.getBegin(), range, 0) >= 0) {
            this.frozenCheck(true);
            final byte[] bytes = this.value.getUnsafeBytes();
            final int size = this.value.getRealSize();
            final RubyMatchData match = RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
            final RubyString repl = objAsString(context, block.yield(context, this.makeShared(context.getRuntime(), matcher.getBegin(), matcher.getEnd() - matcher.getBegin())));
            this.modifyCheck(bytes, size);
            this.frozenCheck(true);
            scope.setBackRef(match);
            return this.subBangCommon(context, pattern, matcher, repl, repl.flags);
        }
        return scope.setBackRef(context.getRuntime().getNil());
    }
    
    private IRubyObject subBangNoIter(final ThreadContext context, final Regex pattern, RubyString repl) {
        final int tuFlags = repl.flags;
        final int range = this.value.getBegin() + this.value.getRealSize();
        final Matcher matcher = pattern.matcher(this.value.getUnsafeBytes(), this.value.getBegin(), range);
        final DynamicScope scope = context.getCurrentScope();
        if (matcher.search(this.value.getBegin(), range, 0) >= 0) {
            repl = RubyRegexp.regsub(repl, this, matcher, context.getRuntime().getKCode().getEncoding());
            RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
            return this.subBangCommon(context, pattern, matcher, repl, tuFlags);
        }
        return scope.setBackRef(context.getRuntime().getNil());
    }
    
    private IRubyObject subBangCommon(final ThreadContext context, final Regex pattern, final Matcher matcher, final RubyString repl, final int tuFlags) {
        final int beg = matcher.getBegin();
        final int plen = matcher.getEnd() - beg;
        final ByteList replValue = repl.value;
        if (replValue.getRealSize() > plen) {
            this.modify(this.value.getRealSize() + replValue.getRealSize() - plen);
        }
        else {
            this.modify();
        }
        if (replValue.getRealSize() != plen) {
            final int src = this.value.getBegin() + beg + plen;
            final int dst = this.value.getBegin() + beg + replValue.getRealSize();
            final int length = this.value.getRealSize() - beg - plen;
            System.arraycopy(this.value.getUnsafeBytes(), src, this.value.getUnsafeBytes(), dst, length);
        }
        System.arraycopy(replValue.getUnsafeBytes(), replValue.getBegin(), this.value.getUnsafeBytes(), this.value.getBegin() + beg, replValue.getRealSize());
        this.value.setRealSize(this.value.getRealSize() + replValue.getRealSize() - plen);
        this.infectBy(tuFlags);
        return this;
    }
    
    @JRubyMethod(name = { "sub" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sub19(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final RubyString str = this.strDup(context.getRuntime());
        str.sub_bang19(context, arg0, block);
        return str;
    }
    
    @JRubyMethod(name = { "sub" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sub19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final RubyString str = this.strDup(context.getRuntime());
        str.sub_bang19(context, arg0, arg1, block);
        return str;
    }
    
    @JRubyMethod(name = { "sub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sub_bang19(final ThreadContext context, final IRubyObject arg0, final Block block) {
        final Ruby runtime = context.getRuntime();
        this.frozenCheck();
        RubyRegexp regexp;
        Regex pattern;
        Regex prepared;
        if (arg0 instanceof RubyRegexp) {
            regexp = (RubyRegexp)arg0;
            pattern = regexp.getPattern();
            prepared = regexp.preparePattern(this);
        }
        else {
            regexp = null;
            pattern = this.getStringPattern19(runtime, arg0);
            prepared = RubyRegexp.preparePattern(runtime, pattern, this);
        }
        if (block.isGiven()) {
            return this.subBangIter19(runtime, context, pattern, prepared, null, block, regexp);
        }
        throw context.getRuntime().newArgumentError(1, 2);
    }
    
    @JRubyMethod(name = { "sub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject sub_bang19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject hash = TypeConverter.convertToTypeWithCheck(arg1, runtime.getHash(), "to_hash");
        this.frozenCheck();
        RubyRegexp regexp;
        Regex pattern;
        Regex prepared;
        if (arg0 instanceof RubyRegexp) {
            regexp = (RubyRegexp)arg0;
            pattern = regexp.getPattern();
            prepared = regexp.preparePattern(this);
        }
        else {
            regexp = null;
            pattern = this.getStringPattern19(runtime, arg0);
            prepared = RubyRegexp.preparePattern(runtime, pattern, this);
        }
        if (hash.isNil()) {
            return this.subBangNoIter19(runtime, context, pattern, prepared, arg1.convertToString(), regexp);
        }
        return this.subBangIter19(runtime, context, pattern, prepared, (RubyHash)hash, block, regexp);
    }
    
    private IRubyObject subBangIter19(final Ruby runtime, final ThreadContext context, final Regex pattern, final Regex prepared, final RubyHash hash, final Block block, final RubyRegexp regexp) {
        final int begin = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int range = begin + len;
        final byte[] bytes = this.value.getUnsafeBytes();
        final Encoding enc = this.value.getEncoding();
        final Matcher matcher = prepared.matcher(bytes, begin, range);
        final DynamicScope scope = context.getCurrentScope();
        if (matcher.search(begin, range, 0) >= 0) {
            final RubyMatchData match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
            match.regexp = regexp;
            final IRubyObject subStr = this.makeShared19(runtime, matcher.getBegin(), matcher.getEnd() - matcher.getBegin());
            int tuFlags;
            RubyString repl;
            if (hash == null) {
                tuFlags = 0;
                repl = objAsString(context, block.yield(context, subStr));
            }
            else {
                tuFlags = hash.flags;
                repl = objAsString(context, hash.op_aref(context, subStr));
            }
            this.modifyCheck(bytes, len, enc);
            this.frozenCheck();
            scope.setBackRef(match);
            return this.subBangCommon19(context, pattern, matcher, repl, tuFlags | repl.flags);
        }
        return scope.setBackRef(runtime.getNil());
    }
    
    private IRubyObject subBangNoIter19(final Ruby runtime, final ThreadContext context, final Regex pattern, final Regex prepared, RubyString repl, final RubyRegexp regexp) {
        final int begin = this.value.getBegin();
        final int range = begin + this.value.getRealSize();
        final Matcher matcher = prepared.matcher(this.value.getUnsafeBytes(), begin, range);
        final DynamicScope scope = context.getCurrentScope();
        if (matcher.search(begin, range, 0) >= 0) {
            repl = RubyRegexp.regsub19(repl, this, matcher, pattern);
            final RubyMatchData match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
            match.regexp = regexp;
            return this.subBangCommon19(context, pattern, matcher, repl, repl.flags);
        }
        return scope.setBackRef(runtime.getNil());
    }
    
    private IRubyObject subBangCommon19(final ThreadContext context, final Regex pattern, final Matcher matcher, final RubyString repl, final int tuFlags) {
        final int beg = matcher.getBegin();
        final int end = matcher.getEnd();
        Encoding enc = this.isCompatibleWith(repl);
        if (enc == null) {
            enc = this.subBangVerifyEncoding(context, repl, beg, end);
        }
        final int plen = end - beg;
        final ByteList replValue = repl.value;
        if (replValue.getRealSize() > plen) {
            this.modify19(this.value.getRealSize() + replValue.getRealSize() - plen);
        }
        else {
            this.modify19();
        }
        this.associateEncoding(enc);
        int cr = this.getCodeRange();
        if (cr > 0 && cr < 96) {
            final int cr2 = repl.getCodeRange();
            if (cr2 == 96 || (cr == 64 && cr2 == 32)) {
                cr = 0;
            }
            else {
                cr = cr2;
            }
        }
        if (replValue.getRealSize() != plen) {
            final int src = this.value.getBegin() + beg + plen;
            final int dst = this.value.getBegin() + beg + replValue.getRealSize();
            final int length = this.value.getRealSize() - beg - plen;
            System.arraycopy(this.value.getUnsafeBytes(), src, this.value.getUnsafeBytes(), dst, length);
        }
        System.arraycopy(replValue.getUnsafeBytes(), replValue.getBegin(), this.value.getUnsafeBytes(), this.value.getBegin() + beg, replValue.getRealSize());
        this.value.setRealSize(this.value.getRealSize() + replValue.getRealSize() - plen);
        this.setCodeRange(cr);
        return this.infectBy(tuFlags);
    }
    
    private Encoding subBangVerifyEncoding(final ThreadContext context, final RubyString repl, final int beg, final int end) {
        final byte[] bytes = this.value.getUnsafeBytes();
        final int p = this.value.getBegin();
        final int len = this.value.getRealSize();
        final Encoding strEnc = this.value.getEncoding();
        if (StringSupport.codeRangeScan(strEnc, bytes, p, beg) != 32 || StringSupport.codeRangeScan(strEnc, bytes, p + end, len - end) != 32) {
            throw context.getRuntime().newArgumentError("incompatible character encodings " + strEnc + " and " + repl.value.getEncoding());
        }
        return repl.value.getEncoding();
    }
    
    @JRubyMethod(reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject gsub(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.gsub(context, arg0, block, false);
    }
    
    @JRubyMethod(reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject gsub(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.gsub(context, arg0, arg1, block, false);
    }
    
    @JRubyMethod(name = { "gsub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject gsub_bang(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return this.gsub(context, arg0, block, true);
    }
    
    @JRubyMethod(name = { "gsub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject gsub_bang(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.gsub(context, arg0, arg1, block, true);
    }
    
    private IRubyObject gsub(final ThreadContext context, final IRubyObject arg0, final Block block, final boolean bang) {
        if (block.isGiven()) {
            return this.gsubCommon(context, bang, arg0, block, null, 0);
        }
        String method = "gsub";
        if (bang) {
            method += "!";
        }
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, method, arg0);
    }
    
    private IRubyObject gsub(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block, final boolean bang) {
        final RubyString repl = arg1.convertToString();
        return this.gsubCommon(context, bang, arg0, block, repl, repl.flags);
    }
    
    private IRubyObject gsubCommon(final ThreadContext context, final boolean bang, final IRubyObject arg, final Block block, final RubyString repl, int tuFlags) {
        final Ruby runtime = context.getRuntime();
        final DynamicScope scope = context.getCurrentScope();
        final Regex pattern = this.getQuotedPattern(arg);
        final int begin = this.value.getBegin();
        final int slen = this.value.getRealSize();
        final int range = begin + slen;
        final byte[] bytes = this.value.getUnsafeBytes();
        final Matcher matcher = pattern.matcher(bytes, begin, range);
        int beg = matcher.search(begin, range, 0);
        if (beg < 0) {
            scope.setBackRef(runtime.getNil());
            return bang ? runtime.getNil() : this.strDup(runtime);
        }
        if (repl == null && bang && this.isFrozen()) {
            throw this.getRuntime().newRuntimeError("can't modify frozen string");
        }
        int blen = slen + 30;
        final ByteList dest = new ByteList(blen);
        dest.setRealSize(blen);
        int offset = 0;
        final int buf = 0;
        int bp = 0;
        int cp = begin;
        final Encoding enc = this.getEncodingForKCodeDefault(runtime, pattern, arg);
        RubyMatchData match = null;
        while (beg >= 0) {
            final int begz = matcher.getBegin();
            final int endz = matcher.getEnd();
            RubyString val;
            if (repl == null) {
                match = RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
                val = objAsString(context, block.yield(context, this.substr(runtime, begz, endz - begz)));
                this.modifyCheck(bytes, slen);
                if (bang) {
                    this.frozenCheck();
                }
            }
            else {
                val = RubyRegexp.regsub(repl, this, matcher, enc);
            }
            tuFlags |= val.flags;
            final ByteList vbuf = val.value;
            int len = bp - buf + (beg - offset) + vbuf.getRealSize() + 3;
            if (blen < len) {
                while (blen < len) {
                    blen <<= 1;
                }
                len = bp - buf;
                dest.realloc(blen);
                dest.setRealSize(blen);
                bp = buf + len;
            }
            len = beg - offset;
            System.arraycopy(bytes, cp, dest.getUnsafeBytes(), bp, len);
            bp += len;
            System.arraycopy(vbuf.getUnsafeBytes(), vbuf.getBegin(), dest.getUnsafeBytes(), bp, vbuf.getRealSize());
            bp += vbuf.getRealSize();
            if (begz == (offset = endz)) {
                if (slen <= endz) {
                    break;
                }
                len = enc.length(bytes, begin + endz, range);
                System.arraycopy(bytes, begin + endz, dest.getUnsafeBytes(), bp, len);
                bp += len;
                offset = endz + len;
            }
            cp = begin + offset;
            if (offset > slen) {
                break;
            }
            beg = matcher.search(cp, range, 0);
        }
        if (repl == null) {
            scope.setBackRef(match);
        }
        else {
            RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
        }
        if (slen > offset) {
            final int len2 = bp - buf;
            if (blen - len2 < slen - offset) {
                blen = len2 + slen - offset;
                dest.realloc(blen);
                bp = buf + len2;
            }
            System.arraycopy(bytes, cp, dest.getUnsafeBytes(), bp, slen - offset);
            bp += slen - offset;
        }
        dest.setRealSize(bp - buf);
        if (bang) {
            this.view(dest);
            return this.infectBy(tuFlags);
        }
        return new RubyString(runtime, this.getMetaClass(), dest).infectBy(tuFlags | this.flags);
    }
    
    @JRubyMethod(name = { "gsub" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gsub19(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return block.isGiven() ? this.gsubCommon19(context, block, null, null, arg0, false, 0) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "gsub", arg0);
    }
    
    @JRubyMethod(name = { "gsub" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gsub19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.gsub19(context, arg0, arg1, block, false);
    }
    
    @JRubyMethod(name = { "gsub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gsub_bang19(final ThreadContext context, final IRubyObject arg0, final Block block) {
        this.checkFrozen();
        return block.isGiven() ? this.gsubCommon19(context, block, null, null, arg0, true, 0) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "gsub!", arg0);
    }
    
    @JRubyMethod(name = { "gsub!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gsub_bang19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        this.checkFrozen();
        return this.gsub19(context, arg0, arg1, block, true);
    }
    
    private IRubyObject gsub19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Block block, final boolean bang) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject tryHash = TypeConverter.convertToTypeWithCheck(arg1, runtime.getHash(), "to_hash");
        RubyHash hash;
        RubyString str;
        int tuFlags;
        if (tryHash.isNil()) {
            hash = null;
            str = arg1.convertToString();
            tuFlags = str.flags;
        }
        else {
            hash = (RubyHash)tryHash;
            str = null;
            tuFlags = (hash.flags & 0x8);
        }
        return this.gsubCommon19(context, block, str, hash, arg0, bang, tuFlags);
    }
    
    private IRubyObject gsubCommon19(final ThreadContext context, final Block block, final RubyString repl, final RubyHash hash, final IRubyObject arg0, final boolean bang, int tuFlags) {
        final Ruby runtime = context.getRuntime();
        RubyRegexp regexp;
        Regex pattern;
        Regex prepared;
        if (arg0 instanceof RubyRegexp) {
            regexp = (RubyRegexp)arg0;
            pattern = regexp.getPattern();
            prepared = regexp.preparePattern(this);
        }
        else {
            regexp = null;
            pattern = this.getStringPattern19(runtime, arg0);
            prepared = RubyRegexp.preparePattern(runtime, pattern, this);
        }
        final int begin = this.value.getBegin();
        final int slen = this.value.getRealSize();
        final int range = begin + slen;
        final byte[] bytes = this.value.getUnsafeBytes();
        final Matcher matcher = prepared.matcher(bytes, begin, range);
        final DynamicScope scope = context.getCurrentScope();
        int beg = matcher.search(begin, range, 0);
        if (beg < 0) {
            scope.setBackRef(runtime.getNil());
            return bang ? runtime.getNil() : this.strDup(runtime);
        }
        final RubyString dest = new RubyString(runtime, this.getMetaClass(), new ByteList(slen + 30));
        int offset = 0;
        int cp = begin;
        final Encoding enc = this.value.getEncoding();
        RubyMatchData match = null;
        do {
            final int begz = matcher.getBegin();
            final int endz = matcher.getEnd();
            RubyString val;
            if (repl != null) {
                val = RubyRegexp.regsub19(repl, this, matcher, pattern);
            }
            else {
                final RubyString substr = this.makeShared19(runtime, begz, endz - begz);
                if (hash != null) {
                    val = objAsString(context, hash.op_aref(context, substr));
                }
                else {
                    match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
                    match.regexp = regexp;
                    val = objAsString(context, block.yield(context, substr));
                }
                this.modifyCheck(bytes, slen, enc);
                if (bang) {
                    this.frozenCheck();
                }
            }
            tuFlags |= val.flags;
            int len = beg - offset;
            if (len != 0) {
                dest.cat(bytes, cp, len, enc);
            }
            dest.cat19(val);
            if (begz == (offset = endz)) {
                if (slen <= endz) {
                    break;
                }
                len = StringSupport.length(enc, bytes, begin + endz, range);
                dest.cat(bytes, begin + endz, len, enc);
                offset = endz + len;
            }
            cp = begin + offset;
            if (offset > slen) {
                break;
            }
            beg = matcher.search(cp, range, 0);
        } while (beg >= 0);
        if (slen > offset) {
            dest.cat(bytes, cp, slen - offset, enc);
        }
        if (match != null) {
            scope.setBackRef(match);
        }
        else {
            match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
            match.regexp = regexp;
        }
        if (bang) {
            this.view(dest.value);
            this.setCodeRange(dest.getCodeRange());
            return this.infectBy(tuFlags);
        }
        return dest.infectBy(tuFlags | this.flags);
    }
    
    @JRubyMethod(name = { "index" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject index(final ThreadContext context, final IRubyObject arg0) {
        return this.indexCommon(context.getRuntime(), context, arg0, 0);
    }
    
    @JRubyMethod(name = { "index" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject index(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        int pos = RubyNumeric.num2int(arg1);
        final Ruby runtime = context.getRuntime();
        if (pos < 0) {
            pos += this.value.getRealSize();
            if (pos < 0) {
                if (arg0 instanceof RubyRegexp) {
                    context.getCurrentScope().setBackRef(runtime.getNil());
                }
                return runtime.getNil();
            }
        }
        return this.indexCommon(runtime, context, arg0, pos);
    }
    
    private IRubyObject indexCommon(final Ruby runtime, final ThreadContext context, final IRubyObject sub, int pos) {
        if (sub instanceof RubyRegexp) {
            final RubyRegexp regSub = (RubyRegexp)sub;
            pos = regSub.adjustStartPos(this, pos, false);
            pos = regSub.search(context, this, pos, false);
        }
        else if (sub instanceof RubyFixnum) {
            final int c_int = RubyNumeric.fix2int((RubyFixnum)sub);
            if (c_int < 0 || c_int > 255) {
                return runtime.getNil();
            }
            final byte c = (byte)c_int;
            final byte[] bytes = this.value.getUnsafeBytes();
            int end;
            for (end = this.value.getBegin() + this.value.getRealSize(), pos += this.value.getBegin(); pos < end; ++pos) {
                if (bytes[pos] == c) {
                    return RubyFixnum.newFixnum(runtime, pos - this.value.getBegin());
                }
            }
            return runtime.getNil();
        }
        else if (sub instanceof RubyString) {
            pos = this.strIndex((RubyString)sub, pos);
        }
        else {
            final IRubyObject tmp = sub.checkStringType();
            if (tmp.isNil()) {
                throw runtime.newTypeError("type mismatch: " + sub.getMetaClass().getName() + " given");
            }
            pos = this.strIndex((RubyString)tmp, pos);
        }
        return (pos == -1) ? runtime.getNil() : RubyFixnum.newFixnum(runtime, pos);
    }
    
    private int strIndex(final RubyString sub, int offset) {
        final ByteList byteList = this.value;
        if (offset < 0) {
            offset += byteList.getRealSize();
            if (offset < 0) {
                return -1;
            }
        }
        final ByteList other = sub.value;
        if (sizeIsSmaller(byteList, offset, other)) {
            return -1;
        }
        if (other.getRealSize() == 0) {
            return offset;
        }
        return byteList.indexOf(other, offset);
    }
    
    private static boolean sizeIsSmaller(final ByteList byteList, final int offset, final ByteList other) {
        return byteList.getRealSize() - offset < other.getRealSize();
    }
    
    @JRubyMethod(name = { "index" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject index19(final ThreadContext context, final IRubyObject arg0) {
        return this.indexCommon19(context.getRuntime(), context, arg0, 0);
    }
    
    @JRubyMethod(name = { "index" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject index19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        int pos = RubyNumeric.num2int(arg1);
        final Ruby runtime = context.getRuntime();
        if (pos < 0) {
            pos += this.strLength();
            if (pos < 0) {
                if (arg0 instanceof RubyRegexp) {
                    context.getCurrentScope().setBackRef(runtime.getNil());
                }
                return runtime.getNil();
            }
        }
        return this.indexCommon19(runtime, context, arg0, pos);
    }
    
    private IRubyObject indexCommon19(final Ruby runtime, final ThreadContext context, final IRubyObject sub, int pos) {
        if (sub instanceof RubyRegexp) {
            final RubyRegexp regSub = (RubyRegexp)sub;
            pos = (this.singleByteOptimizable() ? pos : StringSupport.nth(this.checkEncoding(regSub), this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize(), pos));
            pos = regSub.adjustStartPos19(this, pos, false);
            pos = regSub.search19(context, this, pos, false);
            pos = this.subLength(pos);
        }
        else if (sub instanceof RubyString) {
            pos = this.strIndex19((RubyString)sub, pos);
            pos = this.subLength(pos);
        }
        else {
            final IRubyObject tmp = sub.checkStringType();
            if (tmp.isNil()) {
                throw runtime.newTypeError("type mismatch: " + sub.getMetaClass().getName() + " given");
            }
            pos = this.strIndex19((RubyString)tmp, pos);
            pos = this.subLength(pos);
        }
        return (pos == -1) ? runtime.getNil() : RubyFixnum.newFixnum(runtime, pos);
    }
    
    private int strIndex19(final RubyString sub, int offset) {
        final Encoding enc = this.checkEncoding(sub);
        if (sub.scanForCodeRange() == 96) {
            return -1;
        }
        int len = this.strLength(enc);
        final int slen = sub.strLength(enc);
        if (offset < 0) {
            offset += len;
            if (offset < 0) {
                return -1;
            }
        }
        if (len - offset < slen) {
            return -1;
        }
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        if (offset != 0) {
            offset = (this.singleByteOptimizable() ? offset : StringSupport.offset(enc, bytes, p, end, offset));
            p += offset;
        }
        if (slen == 0) {
            return offset;
        }
        while (true) {
            int pos = this.value.indexOf(sub.value, p - this.value.getBegin());
            if (pos < 0) {
                return pos;
            }
            pos -= p - this.value.getBegin();
            final int t = enc.rightAdjustCharHead(bytes, p, p + pos, end);
            if (t == p + pos) {
                return pos + offset;
            }
            if ((len -= t - p) <= 0) {
                return -1;
            }
            offset += t - p;
            p = t;
        }
    }
    
    @JRubyMethod(name = { "rindex" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject rindex(final ThreadContext context, final IRubyObject arg0) {
        return this.rindexCommon(context.getRuntime(), context, arg0, this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "rindex" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject rindex(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        int pos = RubyNumeric.num2int(arg1);
        final Ruby runtime = context.getRuntime();
        if (pos < 0) {
            pos += this.value.getRealSize();
            if (pos < 0) {
                if (arg0 instanceof RubyRegexp) {
                    context.getCurrentScope().setBackRef(runtime.getNil());
                }
                return runtime.getNil();
            }
        }
        if (pos > this.value.getRealSize()) {
            pos = this.value.getRealSize();
        }
        return this.rindexCommon(runtime, context, arg0, pos);
    }
    
    private IRubyObject rindexCommon(final Ruby runtime, final ThreadContext context, final IRubyObject sub, int pos) {
        if (sub instanceof RubyRegexp) {
            final RubyRegexp regSub = (RubyRegexp)sub;
            if (regSub.length() > 0) {
                pos = regSub.adjustStartPos(this, pos, true);
                pos = regSub.search(context, this, pos, true);
            }
        }
        else if (sub instanceof RubyString) {
            pos = this.strRindex((RubyString)sub, pos);
        }
        else if (sub instanceof RubyFixnum) {
            final int c_int = RubyNumeric.fix2int((RubyFixnum)sub);
            if (c_int < 0 || c_int > 255) {
                return runtime.getNil();
            }
            final byte c = (byte)c_int;
            final byte[] bytes = this.value.getUnsafeBytes();
            final int pbeg = this.value.getBegin();
            int p = pbeg + pos;
            if (pos == this.value.getRealSize()) {
                if (pos == 0) {
                    return runtime.getNil();
                }
                --p;
            }
            while (pbeg <= p) {
                if (bytes[p] == c) {
                    return RubyFixnum.newFixnum(runtime, p - this.value.getBegin());
                }
                --p;
            }
            return runtime.getNil();
        }
        else {
            final IRubyObject tmp = sub.checkStringType();
            if (tmp.isNil()) {
                throw runtime.newTypeError("type mismatch: " + sub.getMetaClass().getName() + " given");
            }
            pos = this.strRindex((RubyString)tmp, pos);
        }
        if (pos >= 0) {
            return RubyFixnum.newFixnum(runtime, pos);
        }
        return runtime.getNil();
    }
    
    private int strRindex(final RubyString sub, int pos) {
        final int subLength = sub.value.getRealSize();
        if (this.value.getRealSize() < subLength) {
            return -1;
        }
        if (this.value.getRealSize() - pos < subLength) {
            pos = this.value.getRealSize() - subLength;
        }
        return this.value.lastIndexOf(sub.value, pos);
    }
    
    @JRubyMethod(name = { "rindex" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rindex19(final ThreadContext context, final IRubyObject arg0) {
        return this.rindexCommon19(context.getRuntime(), context, arg0, this.strLength());
    }
    
    @JRubyMethod(name = { "rindex" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rindex19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        int pos = RubyNumeric.num2int(arg1);
        final Ruby runtime = context.getRuntime();
        final int length = this.strLength();
        if (pos < 0) {
            pos += length;
            if (pos < 0) {
                if (arg0 instanceof RubyRegexp) {
                    context.getCurrentScope().setBackRef(runtime.getNil());
                }
                return runtime.getNil();
            }
        }
        if (pos > length) {
            pos = length;
        }
        return this.rindexCommon19(runtime, context, arg0, pos);
    }
    
    private IRubyObject rindexCommon19(final Ruby runtime, final ThreadContext context, final IRubyObject sub, int pos) {
        if (sub instanceof RubyRegexp) {
            final RubyRegexp regSub = (RubyRegexp)sub;
            pos = (this.singleByteOptimizable() ? pos : StringSupport.nth(this.value.getEncoding(), this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize(), pos));
            if (regSub.length() > 0) {
                pos = regSub.adjustStartPos19(this, pos, true);
                pos = regSub.search19(context, this, pos, true);
                pos = this.subLength(pos);
            }
        }
        else if (sub instanceof RubyString) {
            pos = this.strRindex19((RubyString)sub, pos);
        }
        else {
            final IRubyObject tmp = sub.checkStringType();
            if (tmp.isNil()) {
                throw runtime.newTypeError("type mismatch: " + sub.getMetaClass().getName() + " given");
            }
            pos = this.strRindex19((RubyString)tmp, pos);
        }
        if (pos >= 0) {
            return RubyFixnum.newFixnum(runtime, pos);
        }
        return runtime.getNil();
    }
    
    private int strRindex19(final RubyString sub, int pos) {
        final Encoding enc = this.checkEncoding(sub);
        if (sub.scanForCodeRange() == 96) {
            return -1;
        }
        final int len = this.strLength(enc);
        int slen = sub.strLength(enc);
        if (len < slen) {
            return -1;
        }
        if (len - pos < slen) {
            pos = len - slen;
        }
        if (len == 0) {
            return pos;
        }
        final byte[] bytes = this.value.getUnsafeBytes();
        final int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final byte[] sbytes = sub.value.getUnsafeBytes();
        final int sp = sub.value.getBegin();
        slen = sub.value.getRealSize();
        final boolean singlebyte = this.singleByteOptimizable();
        while (true) {
            final int s = singlebyte ? (p + pos) : StringSupport.nth(enc, bytes, p, end, pos);
            if (s == -1) {
                return -1;
            }
            if (ByteList.memcmp(bytes, s, sbytes, sp, slen) == 0) {
                return pos;
            }
            if (pos == 0) {
                return -1;
            }
            --pos;
        }
    }
    
    @Deprecated
    public final IRubyObject substr(final int beg, final int len) {
        return this.substr(this.getRuntime(), beg, len);
    }
    
    public final IRubyObject substr(final Ruby runtime, int beg, final int len) {
        final int length = this.value.length();
        if (len < 0 || beg > length) {
            return runtime.getNil();
        }
        if (beg < 0) {
            beg += length;
            if (beg < 0) {
                return runtime.getNil();
            }
        }
        final int end = Math.min(length, beg + len);
        return this.makeShared(runtime, beg, end - beg);
    }
    
    public final IRubyObject substr19(final Ruby runtime, int beg, int len) {
        if (len < 0) {
            return runtime.getNil();
        }
        final int length = this.value.getRealSize();
        if (length == 0) {
            len = 0;
        }
        final Encoding enc = this.value.getEncoding();
        if (!this.singleByteOptimizable(enc)) {
            return this.multibyteSubstr19(runtime, enc, len, beg, length);
        }
        if (beg > length) {
            return runtime.getNil();
        }
        if (beg < 0) {
            beg += length;
            if (beg < 0) {
                return runtime.getNil();
            }
        }
        if (beg + len > length) {
            len = length - beg;
        }
        if (len <= 0) {
            beg = (len = 0);
        }
        return this.makeShared19(runtime, beg, len);
    }
    
    private IRubyObject multibyteSubstr19(final Ruby runtime, final Encoding enc, int len, int beg, final int length) {
        final int s = this.value.getBegin();
        final int end = s + length;
        final byte[] bytes = this.value.getUnsafeBytes();
        if (beg < 0) {
            if (len > -beg) {
                len = -beg;
            }
            if (-beg * enc.maxLength() < length >>> 3) {
                beg = -beg;
                int e = end;
                while (beg-- > len && (e = enc.prevCharHead(bytes, s, e, e)) != -1) {}
                int p = e;
                if (p == -1) {
                    return runtime.getNil();
                }
                while (len-- > 0 && (p = enc.prevCharHead(bytes, s, p, e)) != -1) {}
                if (p == -1) {
                    return runtime.getNil();
                }
                return this.makeShared19(runtime, p - s, e - p);
            }
            else {
                beg += this.strLength(enc);
                if (beg < 0) {
                    return runtime.getNil();
                }
            }
        }
        else if (beg > 0 && beg > this.strLength(enc)) {
            return runtime.getNil();
        }
        int p;
        if (len == 0) {
            p = 0;
        }
        else if (this.isCodeRangeValid() && enc instanceof UTF8Encoding) {
            p = StringSupport.utf8Nth(bytes, s, end, beg);
            len = StringSupport.utf8Offset(bytes, p, end, len);
        }
        else if (enc.isFixedWidth()) {
            final int w = enc.maxLength();
            p = s + beg * w;
            if (p > end) {
                p = end;
                len = 0;
            }
            else if (len * w > end - p) {
                len = end - p;
            }
            else {
                len *= w;
            }
        }
        else if ((p = StringSupport.nth(enc, bytes, s, end, beg)) == end) {
            len = 0;
        }
        else {
            len = StringSupport.offset(enc, bytes, p, end, len);
        }
        return this.makeShared19(runtime, p - s, len);
    }
    
    private IRubyObject replaceInternal(final int beg, int len, final RubyString repl) {
        final int oldLength = this.value.getRealSize();
        if (beg + len >= oldLength) {
            len = oldLength - beg;
        }
        final ByteList replBytes = repl.value;
        final int replLength = replBytes.getRealSize();
        final int newLength = oldLength + replLength - len;
        final byte[] oldBytes = this.value.getUnsafeBytes();
        final int oldBegin = this.value.getBegin();
        this.modify(newLength);
        if (replLength != len) {
            System.arraycopy(oldBytes, oldBegin + beg + len, this.value.getUnsafeBytes(), beg + replLength, oldLength - (beg + len));
        }
        if (replLength > 0) {
            System.arraycopy(replBytes.getUnsafeBytes(), replBytes.getBegin(), this.value.getUnsafeBytes(), beg, replLength);
        }
        this.value.setRealSize(newLength);
        return this.infectBy(repl);
    }
    
    private void replaceInternal19(final int beg, final int len, final RubyString repl) {
        final Encoding enc = this.checkEncoding(repl);
        int p = this.value.getBegin();
        int e;
        if (this.singleByteOptimizable()) {
            p += beg;
            e = p + len;
        }
        else {
            final int end = p + this.value.getRealSize();
            final byte[] bytes = this.value.getUnsafeBytes();
            p = StringSupport.nth(enc, bytes, p, end, beg);
            if (p == -1) {
                p = end;
            }
            e = StringSupport.nth(enc, bytes, p, end, len);
            if (e == -1) {
                e = end;
            }
        }
        int cr = this.getCodeRange();
        if (cr == 96) {
            this.clearCodeRange();
        }
        this.replaceInternal(p - this.value.getBegin(), e - p, repl);
        this.associateEncoding(enc);
        cr = codeRangeAnd(cr, repl.getCodeRange());
        if (cr != 96) {
            this.setCodeRange(cr);
        }
    }
    
    @JRubyMethod(name = { "[]", "slice" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        final Ruby runtime = context.getRuntime();
        if (arg1 instanceof RubyRegexp) {
            return this.subpat(runtime, context, (RubyRegexp)arg1, RubyNumeric.num2int(arg2));
        }
        return this.substr(runtime, RubyNumeric.num2int(arg1), RubyNumeric.num2int(arg2));
    }
    
    @JRubyMethod(name = { "[]", "slice" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (arg instanceof RubyFixnum) {
            return this.op_aref(runtime, RubyNumeric.fix2int((RubyFixnum)arg));
        }
        if (arg instanceof RubyRegexp) {
            return this.subpat(runtime, context, (RubyRegexp)arg, 0);
        }
        if (arg instanceof RubyString) {
            final RubyString str = (RubyString)arg;
            return (this.value.indexOf(str.value) != -1) ? str.strDup(runtime) : runtime.getNil();
        }
        if (arg instanceof RubyRange) {
            final int[] begLen = ((RubyRange)arg).begLenInt(this.value.length(), 0);
            return (begLen == null) ? runtime.getNil() : this.substr(runtime, begLen[0], begLen[1]);
        }
        return this.op_aref(runtime, RubyNumeric.num2int(arg));
    }
    
    private IRubyObject op_aref(final Ruby runtime, int idx) {
        if (idx < 0) {
            idx += this.value.getRealSize();
        }
        return (idx < 0 || idx >= this.value.getRealSize()) ? runtime.getNil() : runtime.newFixnum(this.value.get(idx) & 0xFF);
    }
    
    @JRubyMethod(name = { "[]", "slice" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aref19(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        final Ruby runtime = context.getRuntime();
        if (arg1 instanceof RubyRegexp) {
            return this.subpat19(runtime, context, (RubyRegexp)arg1, arg2);
        }
        return this.substr19(runtime, RubyNumeric.num2int(arg1), RubyNumeric.num2int(arg2));
    }
    
    @JRubyMethod(name = { "[]", "slice" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aref19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (arg instanceof RubyFixnum) {
            return this.op_aref19(runtime, RubyNumeric.fix2int((RubyFixnum)arg));
        }
        if (arg instanceof RubyRegexp) {
            return this.subpat19(runtime, context, (RubyRegexp)arg);
        }
        if (arg instanceof RubyString) {
            final RubyString str = (RubyString)arg;
            return (this.strIndex19(str, 0) != -1) ? str.strDup(runtime) : runtime.getNil();
        }
        if (arg instanceof RubyRange) {
            final int len = this.strLength();
            final int[] begLen = ((RubyRange)arg).begLenInt(len, 0);
            return (begLen == null) ? runtime.getNil() : this.substr19(runtime, begLen[0], begLen[1]);
        }
        return this.op_aref19(runtime, RubyNumeric.num2int(arg));
    }
    
    private IRubyObject op_aref19(final Ruby runtime, final int idx) {
        final IRubyObject str = this.substr19(runtime, idx, 1);
        return (!str.isNil() && ((RubyString)str).value.getRealSize() == 0) ? runtime.getNil() : str;
    }
    
    private void subpatSet(final ThreadContext context, final RubyRegexp regexp, int nth, final IRubyObject repl) {
        final Ruby runtime = context.getRuntime();
        if (regexp.search(context, this, 0, false) < 0) {
            throw runtime.newIndexError("regexp not matched");
        }
        final RubyMatchData match = (RubyMatchData)context.getCurrentScope().getBackRef(runtime);
        nth = this.subpatSetCheck(runtime, nth, match.regs);
        int start;
        int end;
        if (match.regs == null) {
            start = match.begin;
            end = match.end;
        }
        else {
            start = match.regs.beg[nth];
            end = match.regs.end[nth];
        }
        if (start == -1) {
            throw runtime.newIndexError("regexp group " + nth + " not matched");
        }
        this.replaceInternal(start, end - start, repl.convertToString());
    }
    
    private int subpatSetCheck(final Ruby runtime, final int nth, final Region regs) {
        final int numRegs = (regs == null) ? 1 : regs.numRegs;
        if (nth < numRegs) {
            if (nth >= 0) {
                return nth;
            }
            if (-nth < numRegs) {
                return nth + numRegs;
            }
        }
        throw runtime.newIndexError("index " + nth + " out of regexp");
    }
    
    private IRubyObject subpat(final Ruby runtime, final ThreadContext context, final RubyRegexp regex, final int nth) {
        if (regex.search(context, this, 0, false) >= 0) {
            return RubyRegexp.nth_match(nth, context.getCurrentScope().getBackRef(runtime));
        }
        return runtime.getNil();
    }
    
    private void subpatSet19(final ThreadContext context, final RubyRegexp regexp, final IRubyObject backref, final IRubyObject repl) {
        final Ruby runtime = context.getRuntime();
        if (regexp.search19(context, this, 0, false) < 0) {
            throw runtime.newIndexError("regexp not matched");
        }
        final RubyMatchData match = (RubyMatchData)context.getCurrentScope().getBackRef(runtime);
        final int nth = (backref == null) ? 0 : this.subpatSetCheck(runtime, match.backrefNumber(backref), match.regs);
        int start;
        int end;
        if (match.regs == null) {
            start = match.begin;
            end = match.end;
        }
        else {
            start = match.regs.beg[nth];
            end = match.regs.end[nth];
        }
        if (start == -1) {
            throw runtime.newIndexError("regexp group " + nth + " not matched");
        }
        final RubyString replStr = repl.convertToString();
        final Encoding enc = this.checkEncoding(replStr);
        this.replaceInternal(start, end - start, replStr);
        this.associateEncoding(enc);
    }
    
    private IRubyObject subpat19(final Ruby runtime, final ThreadContext context, final RubyRegexp regex, final IRubyObject backref) {
        if (regex.search19(context, this, 0, false) >= 0) {
            final RubyMatchData match = (RubyMatchData)context.getCurrentScope().getBackRef(runtime);
            return RubyRegexp.nth_match(match.backrefNumber(backref), context.getCurrentScope().getBackRef(runtime));
        }
        return runtime.getNil();
    }
    
    private IRubyObject subpat19(final Ruby runtime, final ThreadContext context, final RubyRegexp regex) {
        if (regex.search19(context, this, 0, false) >= 0) {
            return RubyRegexp.nth_match(0, context.getCurrentScope().getBackRef(runtime));
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "[]=" }, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        if (arg0 instanceof RubyFixnum) {
            return this.op_aset(context, RubyNumeric.fix2int((RubyFixnum)arg0), arg1);
        }
        if (arg0 instanceof RubyRegexp) {
            this.subpatSet(context, (RubyRegexp)arg0, 0, arg1.convertToString());
            return arg1;
        }
        if (arg0 instanceof RubyString) {
            final RubyString orig = (RubyString)arg0;
            final int beg = this.value.indexOf(orig.value);
            if (beg < 0) {
                throw context.getRuntime().newIndexError("string not matched");
            }
            this.replaceInternal(beg, orig.value.getRealSize(), arg1.convertToString());
            return arg1;
        }
        else {
            if (arg0 instanceof RubyRange) {
                final int[] begLen = ((RubyRange)arg0).begLenInt(this.value.getRealSize(), 2);
                this.replaceInternal(begLen[0], begLen[1], arg1.convertToString());
                return arg1;
            }
            return this.op_aset(context, RubyNumeric.num2int(arg0), arg1);
        }
    }
    
    private IRubyObject op_aset(final ThreadContext context, int idx, final IRubyObject arg1) {
        idx = this.checkIndexForRef(idx, this.value.getRealSize());
        if (arg1 instanceof RubyFixnum) {
            this.modify();
            this.value.set(idx, RubyNumeric.fix2int((RubyFixnum)arg1));
        }
        else {
            this.replaceInternal(idx, 1, arg1.convertToString());
        }
        return arg1;
    }
    
    @JRubyMethod(name = { "[]=" }, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (arg0 instanceof RubyRegexp) {
            this.subpatSet(context, (RubyRegexp)arg0, RubyNumeric.num2int(arg1), arg2);
        }
        else {
            final int beg = RubyNumeric.num2int(arg0);
            final int len = RubyNumeric.num2int(arg1);
            this.checkLength(len);
            final RubyString repl = arg2.convertToString();
            this.replaceInternal(this.checkIndex(beg, this.value.getRealSize()), len, repl);
        }
        return arg2;
    }
    
    @JRubyMethod(name = { "[]=" }, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aset19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        if (arg0 instanceof RubyFixnum) {
            return this.op_aset19(context, RubyNumeric.fix2int((RubyFixnum)arg0), arg1);
        }
        if (arg0 instanceof RubyRegexp) {
            this.subpatSet19(context, (RubyRegexp)arg0, null, arg1);
            return arg1;
        }
        if (arg0 instanceof RubyString) {
            final RubyString orig = (RubyString)arg0;
            int beg = this.strIndex19(orig, 0);
            if (beg < 0) {
                throw context.getRuntime().newIndexError("string not matched");
            }
            beg = this.subLength(beg);
            this.replaceInternal19(beg, orig.strLength(), arg1.convertToString());
            return arg1;
        }
        else {
            if (arg0 instanceof RubyRange) {
                final int[] begLen = ((RubyRange)arg0).begLenInt(this.strLength(), 2);
                this.replaceInternal19(begLen[0], begLen[1], arg1.convertToString());
                return arg1;
            }
            return this.op_aset19(context, RubyNumeric.num2int(arg0), arg1);
        }
    }
    
    private IRubyObject op_aset19(final ThreadContext context, final int idx, final IRubyObject arg1) {
        this.replaceInternal19(this.checkIndex(idx, this.strLength()), 1, arg1.convertToString());
        return arg1;
    }
    
    @JRubyMethod(name = { "[]=" }, reads = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aset19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        if (arg0 instanceof RubyRegexp) {
            this.subpatSet19(context, (RubyRegexp)arg0, arg1, arg2);
        }
        else {
            final int beg = RubyNumeric.num2int(arg0);
            final int len = RubyNumeric.num2int(arg1);
            this.checkLength(len);
            final RubyString repl = arg2.convertToString();
            this.replaceInternal19(this.checkIndex(beg, this.strLength()), len, repl);
        }
        return arg2;
    }
    
    private boolean isHeadSlice(final int beg, final int len) {
        return beg == 0 && len > 0 && len <= this.value.getRealSize();
    }
    
    private boolean isTailSlice(final int beg, final int len) {
        return beg >= 0 && len > 0 && beg + len == this.value.getRealSize();
    }
    
    private void exciseHead(final int len) {
        this.view(len, this.value.getRealSize() - len);
    }
    
    private void exciseTail(final int len) {
        this.view(0, this.value.getRealSize() - len);
    }
    
    @JRubyMethod(name = { "slice!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject slice_bang(final ThreadContext context, final IRubyObject arg0) {
        final IRubyObject result = this.op_aref(context, arg0);
        if (!result.isNil()) {
            int beg = -1;
            int len = 1;
            if (arg0 instanceof RubyFixnum) {
                beg = RubyNumeric.num2int(arg0);
            }
            else if (arg0 instanceof RubyRange) {
                final int[] begLen = ((RubyRange)arg0).begLenInt(this.value.getRealSize(), 2);
                beg = begLen[0];
                len = begLen[1];
            }
            if (this.isHeadSlice(beg, len)) {
                this.exciseHead(len);
            }
            else if (this.isTailSlice(beg, len)) {
                this.exciseTail(len);
            }
            else {
                this.op_aset(context, arg0, newEmptyString(context.getRuntime()));
            }
        }
        return result;
    }
    
    @JRubyMethod(name = { "slice!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject slice_bang(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        final IRubyObject result = this.op_aref(context, arg0, arg1);
        if (!result.isNil()) {
            int beg = -1;
            int len = 0;
            if (arg0 instanceof RubyFixnum && arg1 instanceof RubyFixnum) {
                beg = RubyNumeric.num2int(arg0);
                len = RubyNumeric.num2int(arg1);
            }
            if (this.isHeadSlice(beg, len)) {
                this.exciseHead(len);
            }
            else if (this.isTailSlice(beg, len)) {
                this.exciseTail(len);
            }
            else {
                this.op_aset(context, arg0, arg1, newEmptyString(context.getRuntime()));
            }
        }
        return result;
    }
    
    @JRubyMethod(name = { "slice!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject slice_bang19(final ThreadContext context, final IRubyObject arg0) {
        final IRubyObject result = this.op_aref19(context, arg0);
        if (result.isNil()) {
            this.modifyCheck();
        }
        else {
            this.op_aset19(context, arg0, newEmptyString(context.getRuntime()));
        }
        return result;
    }
    
    @JRubyMethod(name = { "slice!" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject slice_bang19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        final IRubyObject result = this.op_aref19(context, arg0, arg1);
        if (result.isNil()) {
            this.modifyCheck();
        }
        else {
            this.op_aset19(context, arg0, arg1, newEmptyString(context.getRuntime()));
        }
        return result;
    }
    
    @JRubyMethod(name = { "succ", "next" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject succ(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.succ_bang();
        return str;
    }
    
    @JRubyMethod(name = { "succ!", "next!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject succ_bang() {
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return this;
        }
        this.modify();
        boolean alnumSeen = false;
        int pos = -1;
        int n = 0;
        final int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        for (int i = end - 1; i >= p; --i) {
            final int c = bytes[i] & 0xFF;
            if (RubyString.ASCII.isAlnum(c)) {
                alnumSeen = true;
                if ((RubyString.ASCII.isDigit(c) && c < 57) || (RubyString.ASCII.isLower(c) && c < 122) || (RubyString.ASCII.isUpper(c) && c < 90)) {
                    bytes[i] = (byte)(c + 1);
                    pos = -1;
                    break;
                }
                pos = i;
                n = (RubyString.ASCII.isDigit(c) ? 49 : (RubyString.ASCII.isLower(c) ? 97 : 65));
                bytes[i] = (byte)(RubyString.ASCII.isDigit(c) ? 48 : (RubyString.ASCII.isLower(c) ? 97 : 65));
            }
        }
        if (!alnumSeen) {
            for (int i = end - 1; i >= p; --i) {
                final int c = bytes[i] & 0xFF;
                if (c < 255) {
                    bytes[i] = (byte)(c + 1);
                    pos = -1;
                    break;
                }
                pos = i;
                n = 1;
                bytes[i] = 0;
            }
        }
        if (pos > -1) {
            this.value.insert(pos, (byte)n);
        }
        return this;
    }
    
    private static NeighborChar succChar(final Encoding enc, final byte[] bytes, final int p, final int len) {
        while (true) {
            int i;
            for (i = len - 1; i >= 0 && bytes[p + i] == -1; --i) {
                bytes[p + i] = 0;
            }
            if (i < 0) {
                return NeighborChar.WRAPPED;
            }
            bytes[p + i] = (byte)((bytes[p + i] & 0xFF) + 1);
            final int cl = StringSupport.preciseLength(enc, bytes, p, p + len);
            if (cl > 0) {
                if (cl == len) {
                    return NeighborChar.FOUND;
                }
                for (int j = p + cl; j < p + len - cl; ++j) {
                    bytes[j] = -1;
                }
            }
            if (cl != -1 || i >= len - 1) {
                continue;
            }
            int len2;
            for (len2 = len - 1; len2 > 0 && StringSupport.preciseLength(enc, bytes, p, p + len2) == -1; --len2) {}
            for (int k = p + len2 + 1; k < p + len - (len2 + 1); ++k) {
                bytes[k] = -1;
            }
        }
    }
    
    private static NeighborChar predChar(final Encoding enc, final byte[] bytes, final int p, final int len) {
        while (true) {
            int i;
            for (i = len - 1; i >= 0 && bytes[p + i] == 0; --i) {
                bytes[p + i] = -1;
            }
            if (i < 0) {
                return NeighborChar.WRAPPED;
            }
            bytes[p + i] = (byte)((bytes[p + i] & 0xFF) - 1);
            final int cl = StringSupport.preciseLength(enc, bytes, p, p + len);
            if (cl > 0) {
                if (cl == len) {
                    return NeighborChar.FOUND;
                }
                for (int j = p + cl; j < p + len - cl; ++j) {
                    bytes[j] = 0;
                }
            }
            if (cl != -1 || i >= len - 1) {
                continue;
            }
            int len2;
            for (len2 = len - 1; len2 > 0 && StringSupport.preciseLength(enc, bytes, p, p + len2) == -1; --len2) {}
            for (int k = p + len2 + 1; k < p + len - (len2 + 1); ++k) {
                bytes[k] = 0;
            }
        }
    }
    
    private static NeighborChar succAlnumChar(final Encoding enc, final byte[] bytes, final int p, final int len, final byte[] carry, final int carryP) {
        final byte[] save = new byte[7];
        int c = enc.mbcToCode(bytes, p, p + len);
        int cType;
        if (enc.isDigit(c)) {
            cType = 4;
        }
        else {
            if (!enc.isAlpha(c)) {
                return NeighborChar.NOT_CHAR;
            }
            cType = 1;
        }
        System.arraycopy(bytes, p, save, 0, len);
        NeighborChar ret = succChar(enc, bytes, p, len);
        if (ret == NeighborChar.FOUND) {
            c = enc.mbcToCode(bytes, p, p + len);
            if (enc.isCodeCType(c, cType)) {
                return NeighborChar.FOUND;
            }
        }
        System.arraycopy(save, 0, bytes, p, len);
        int range = 1;
        while (true) {
            System.arraycopy(bytes, p, save, 0, len);
            ret = predChar(enc, bytes, p, len);
            if (ret != NeighborChar.FOUND) {
                System.arraycopy(save, 0, bytes, p, len);
                break;
            }
            c = enc.mbcToCode(bytes, p, p + len);
            if (!enc.isCodeCType(c, cType)) {
                System.arraycopy(save, 0, bytes, p, len);
                break;
            }
            ++range;
        }
        if (range == 1) {
            return NeighborChar.NOT_CHAR;
        }
        if (cType != 4) {
            System.arraycopy(bytes, p, carry, carryP, len);
            return NeighborChar.WRAPPED;
        }
        System.arraycopy(bytes, p, carry, carryP, len);
        succChar(enc, carry, carryP, len);
        return NeighborChar.WRAPPED;
    }
    
    @JRubyMethod(name = { "succ", "next" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject succ19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        RubyString str;
        if (this.value.getRealSize() > 0) {
            str = new RubyString(runtime, this.getMetaClass(), this.succCommon19(this.value));
        }
        else {
            str = newEmptyString(runtime, this.getType(), this.value.getEncoding());
        }
        return str.infectBy(this);
    }
    
    @JRubyMethod(name = { "succ!", "next!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject succ_bang19() {
        this.modifyCheck();
        if (this.value.getRealSize() > 0) {
            this.value = this.succCommon19(this.value);
            this.shareLevel = 0;
        }
        return this;
    }
    
    private ByteList succCommon19(final ByteList original) {
        final byte[] carry = new byte[7];
        int carryP = 0;
        carry[0] = 1;
        int carryLen = 1;
        final ByteList valueCopy = new ByteList(original);
        valueCopy.setEncoding(original.getEncoding());
        final Encoding enc = original.getEncoding();
        final int p = valueCopy.getBegin();
        int s;
        final int end = s = p + valueCopy.getRealSize();
        final byte[] bytes = valueCopy.getUnsafeBytes();
        NeighborChar neighbor = NeighborChar.FOUND;
        int lastAlnum = -1;
        boolean alnumSeen = false;
        while ((s = enc.prevCharHead(bytes, p, s, end)) != -1) {
            Label_0194: {
                if (neighbor == NeighborChar.NOT_CHAR && lastAlnum != -1) {
                    if (RubyString.ASCII.isAlpha(bytes[lastAlnum] & 0xFF)) {
                        if (!RubyString.ASCII.isDigit(bytes[s] & 0xFF)) {
                            break Label_0194;
                        }
                    }
                    else if (!RubyString.ASCII.isDigit(bytes[lastAlnum] & 0xFF) || !RubyString.ASCII.isAlpha(bytes[s] & 0xFF)) {
                        break Label_0194;
                    }
                    s = lastAlnum;
                    break;
                }
            }
            final int cl = StringSupport.preciseLength(enc, bytes, s, end);
            if (cl <= 0) {
                continue;
            }
            switch (neighbor = succAlnumChar(enc, bytes, s, cl, carry, 0)) {
                case NOT_CHAR: {
                    continue;
                }
                case FOUND: {
                    return valueCopy;
                }
                case WRAPPED: {
                    lastAlnum = s;
                    break;
                }
            }
            alnumSeen = true;
            carryP = s - p;
            carryLen = cl;
        }
        if (!alnumSeen) {
            s = end;
            while ((s = enc.prevCharHead(bytes, p, s, end)) != -1) {
                final int cl = StringSupport.preciseLength(enc, bytes, s, end);
                if (cl <= 0) {
                    continue;
                }
                neighbor = succChar(enc, bytes, s, cl);
                if (neighbor == NeighborChar.FOUND) {
                    return valueCopy;
                }
                if (StringSupport.preciseLength(enc, bytes, s, s + 1) != cl) {
                    succChar(enc, bytes, s, cl);
                }
                if (!enc.isAsciiCompatible()) {
                    System.arraycopy(bytes, s, carry, 0, cl);
                    carryLen = cl;
                }
                carryP = s - p;
            }
        }
        valueCopy.ensure(valueCopy.getBegin() + valueCopy.getRealSize() + carryLen);
        s = valueCopy.getBegin() + carryP;
        System.arraycopy(valueCopy.getUnsafeBytes(), s, valueCopy.getUnsafeBytes(), s + carryLen, valueCopy.getRealSize() - carryP);
        System.arraycopy(carry, 0, valueCopy.getUnsafeBytes(), s, carryLen);
        valueCopy.setRealSize(valueCopy.getRealSize() + carryLen);
        return valueCopy;
    }
    
    @JRubyMethod(name = { "upto" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject upto18(final ThreadContext context, final IRubyObject end, final Block block) {
        return this.uptoCommon18(context, end, false, block);
    }
    
    @JRubyMethod(name = { "upto" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject upto18(final ThreadContext context, final IRubyObject end, final IRubyObject excl, final Block block) {
        return this.uptoCommon18(context, end, excl.isTrue(), block);
    }
    
    final IRubyObject uptoCommon18(final ThreadContext context, final IRubyObject arg, final boolean excl, final Block block) {
        final RubyString end = arg.convertToString();
        this.checkEncoding(end);
        final int n = this.op_cmp19(end);
        if (n > 0 || (excl && n == 0)) {
            return this;
        }
        final IRubyObject afterEnd = end.callMethod(context, "succ");
        RubyString current = this;
        while (!current.op_equal19(context, afterEnd).isTrue()) {
            block.yield(context, current);
            if (!excl && current.op_equal19(context, end).isTrue()) {
                break;
            }
            current = current.callMethod(context, "succ").convertToString();
            if (excl && current.op_equal19(context, end).isTrue()) {
                break;
            }
            if (current.value.getRealSize() > end.value.getRealSize() || current.value.getRealSize() == 0) {
                break;
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "upto" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject upto19(final ThreadContext context, final IRubyObject end, final Block block) {
        final Ruby runtime = context.getRuntime();
        return block.isGiven() ? this.uptoCommon19(context, end, false, block) : RubyEnumerator.enumeratorize(runtime, this, "upto", end);
    }
    
    @JRubyMethod(name = { "upto" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject upto19(final ThreadContext context, final IRubyObject end, final IRubyObject excl, final Block block) {
        return block.isGiven() ? this.uptoCommon19(context, end, excl.isTrue(), block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "upto", new IRubyObject[] { end, excl });
    }
    
    final IRubyObject uptoCommon19(final ThreadContext context, final IRubyObject arg, final boolean excl, final Block block) {
        return this.uptoCommon19(context, arg, excl, block, false);
    }
    
    final IRubyObject uptoCommon19(final ThreadContext context, final IRubyObject arg, final boolean excl, final Block block, final boolean asASymbol) {
        final Ruby runtime = context.getRuntime();
        if (arg instanceof RubySymbol) {
            throw runtime.newTypeError("can't convert Symbol into String");
        }
        final RubyString end = arg.convertToString();
        final Encoding enc = this.checkEncoding(end);
        if (this.value.getRealSize() == 1 && end.value.getRealSize() == 1 && this.scanForCodeRange() == 32 && end.scanForCodeRange() == 32) {
            byte c = this.value.getUnsafeBytes()[this.value.getBegin()];
            final byte e = end.value.getUnsafeBytes()[end.value.getBegin()];
            if (c > e || (excl && c == e)) {
                return this;
            }
            do {
                final RubyString s = new RubyString(runtime, runtime.getString(), RubyInteger.SINGLE_CHAR_BYTELISTS[c & 0xFF], enc, 32);
                s.shareLevel = 2;
                IRubyObject argument = s;
                if (asASymbol) {
                    argument = runtime.newSymbol(s.toString());
                }
                block.yield(context, argument);
                if (!excl && c == e) {
                    break;
                }
                ++c;
            } while (!excl || c != e);
        }
        else {
            final int n = this.op_cmp19(end);
            if (n > 0 || (excl && n == 0)) {
                return this;
            }
            final IRubyObject afterEnd = end.callMethod(context, "succ");
            RubyString current = this;
            while (!current.op_equal19(context, afterEnd).isTrue() && current.value.getRealSize() <= end.value.getRealSize()) {
                if (current.value.getRealSize() == 0) {
                    break;
                }
                IRubyObject argument = current;
                if (asASymbol) {
                    argument = runtime.newSymbol(current.toString());
                }
                block.yield(context, argument);
                if (!excl && current.op_equal19(context, end).isTrue()) {
                    break;
                }
                current = current.callMethod(context, "succ").convertToString();
                if (excl && current.op_equal19(context, end).isTrue()) {
                    break;
                }
            }
        }
        return this;
    }
    
    @JRubyMethod(name = { "include?" }, compat = CompatVersion.RUBY1_8)
    public RubyBoolean include_p(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        if (obj instanceof RubyFixnum) {
            final int c = RubyNumeric.fix2int((RubyFixnum)obj);
            for (int i = 0; i < this.value.getRealSize(); ++i) {
                if (this.value.get(i) == (byte)c) {
                    return runtime.getTrue();
                }
            }
            return runtime.getFalse();
        }
        return (this.value.indexOf(obj.convertToString().value) == -1) ? runtime.getFalse() : runtime.getTrue();
    }
    
    @JRubyMethod(name = { "include?" }, compat = CompatVersion.RUBY1_9)
    public RubyBoolean include_p19(final ThreadContext context, final IRubyObject obj) {
        final Ruby runtime = context.getRuntime();
        return (this.strIndex19(obj.convertToString(), 0) == -1) ? runtime.getFalse() : runtime.getTrue();
    }
    
    @JRubyMethod(name = { "chr" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject chr(final ThreadContext context) {
        return this.substr19(context.getRuntime(), 0, 1);
    }
    
    @JRubyMethod(name = { "getbyte" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getbyte(final ThreadContext context, final IRubyObject index) {
        final Ruby runtime = context.getRuntime();
        int i = RubyNumeric.num2int(index);
        if (i < 0) {
            i += this.value.getRealSize();
        }
        if (i < 0 || i >= this.value.getRealSize()) {
            return runtime.getNil();
        }
        return RubyFixnum.newFixnum(runtime, this.value.getUnsafeBytes()[this.value.getBegin() + i] & 0xFF);
    }
    
    @JRubyMethod(name = { "setbyte" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject setbyte(final ThreadContext context, final IRubyObject index, final IRubyObject val) {
        this.modifyCheck();
        final int i = RubyNumeric.num2int(index);
        final int b = RubyNumeric.num2int(val);
        this.value.getUnsafeBytes()[this.checkIndexForRef(i, this.value.getRealSize())] = (byte)b;
        return val;
    }
    
    @JRubyMethod(name = { "to_i" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_i() {
        return this.stringToInum(10, false);
    }
    
    @JRubyMethod(name = { "to_i" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_i(final IRubyObject arg0) {
        final long base = this.checkBase(arg0);
        return this.stringToInum((int)base, false);
    }
    
    @JRubyMethod(name = { "to_i" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_i19() {
        return this.stringToInum19(10, false);
    }
    
    @JRubyMethod(name = { "to_i" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_i19(final IRubyObject arg0) {
        final long base = this.checkBase(arg0);
        return this.stringToInum19((int)base, false);
    }
    
    private long checkBase(final IRubyObject arg0) {
        final long base = arg0.convertToInteger().getLongValue();
        if (base < 0L) {
            throw this.getRuntime().newArgumentError("illegal radix " + base);
        }
        return base;
    }
    
    public IRubyObject stringToInum(final int base, final boolean badcheck) {
        final ByteList s = this.value;
        return ConvertBytes.byteListToInum(this.getRuntime(), s, base, badcheck);
    }
    
    public IRubyObject stringToInum19(final int base, final boolean badcheck) {
        final ByteList s = this.value;
        return ConvertBytes.byteListToInum19(this.getRuntime(), s, base, badcheck);
    }
    
    @JRubyMethod(name = { "oct" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject oct(final ThreadContext context) {
        return this.stringToInum(-8, false);
    }
    
    @JRubyMethod(name = { "oct" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject oct19(final ThreadContext context) {
        if (!this.value.getEncoding().isAsciiCompatible()) {
            throw context.getRuntime().newEncodingCompatibilityError("ASCII incompatible encoding: " + this.value.getEncoding());
        }
        return this.oct(context);
    }
    
    @JRubyMethod(name = { "hex" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject hex(final ThreadContext context) {
        return this.stringToInum(16, false);
    }
    
    @JRubyMethod(name = { "hex" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject hex19(final ThreadContext context) {
        if (!this.value.getEncoding().isAsciiCompatible()) {
            throw context.getRuntime().newEncodingCompatibilityError("ASCII incompatible encoding: " + this.value.getEncoding());
        }
        return this.stringToInum19(16, false);
    }
    
    @JRubyMethod(name = { "to_f" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_f() {
        return RubyNumeric.str2fnum(this.getRuntime(), this);
    }
    
    @JRubyMethod(name = { "to_f" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_f19() {
        return RubyNumeric.str2fnum19(this.getRuntime(), this, false);
    }
    
    @JRubyMethod(name = { "split" }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public RubyArray split(final ThreadContext context) {
        return this.split(context, context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "split" }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public RubyArray split(final ThreadContext context, final IRubyObject arg0) {
        return this.splitCommon(arg0, false, 0, 0, context);
    }
    
    @JRubyMethod(name = { "split" }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public RubyArray split(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        final int lim = RubyNumeric.num2int(arg1);
        if (lim <= 0) {
            return this.splitCommon(arg0, false, lim, 1, context);
        }
        if (lim == 1) {
            return (this.value.getRealSize() == 0) ? context.getRuntime().newArray() : context.getRuntime().newArray(this);
        }
        return this.splitCommon(arg0, true, lim, 1, context);
    }
    
    private RubyArray splitCommon(IRubyObject spat, final boolean limit, final int lim, final int i, final ThreadContext context) {
        RubyArray result;
        if (spat.isNil() && (spat = context.getRuntime().getGlobalVariables().get("$;")).isNil()) {
            result = this.awkSplit(limit, lim, i);
        }
        else if (spat instanceof RubyString && ((RubyString)spat).value.getRealSize() == 1) {
            final RubyString strSpat = (RubyString)spat;
            if (strSpat.value.getUnsafeBytes()[strSpat.value.getBegin()] == 32) {
                result = this.awkSplit(limit, lim, i);
            }
            else {
                result = this.regexSplit(context, spat, limit, lim, i);
            }
        }
        else {
            result = this.regexSplit(context, spat, limit, lim, i);
        }
        if (!limit && lim == 0) {
            while (result.size() > 0 && ((RubyString)result.eltInternal(result.size() - 1)).value.getRealSize() == 0) {
                result.pop(context);
            }
        }
        return result;
    }
    
    private RubyArray regexSplit(final ThreadContext context, final IRubyObject pat, final boolean limit, final int lim, int i) {
        final Ruby runtime = context.getRuntime();
        final Regex pattern = this.getQuotedPattern(pat);
        final int begin = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int range = begin + len;
        final byte[] bytes = this.value.getUnsafeBytes();
        final Matcher matcher = pattern.matcher(bytes, begin, range);
        final RubyArray result = runtime.newArray();
        final Encoding enc = this.getEncodingForKCodeDefault(runtime, pattern, pat);
        final boolean captures = pattern.numberOfCaptures() != 0;
        int beg = 0;
        boolean lastNull = false;
        int start = begin;
        int end;
        while ((end = matcher.search(start, range, 0)) >= 0) {
            if (start == end + begin && matcher.getBegin() == matcher.getEnd()) {
                if (len == 0) {
                    result.append(newEmptyString(runtime, this.getMetaClass()).infectBy(this));
                    break;
                }
                if (!lastNull) {
                    start += ((start == range) ? 1 : enc.length(bytes, start, range));
                    lastNull = true;
                    continue;
                }
                result.append(this.makeShared(runtime, beg, enc.length(bytes, begin + beg, range)));
                beg = start - begin;
            }
            else {
                result.append(this.makeShared(runtime, beg, end - beg));
                beg = matcher.getEnd();
                start = begin + beg;
            }
            lastNull = false;
            if (captures) {
                this.populateCapturesForSplit(runtime, result, matcher, false);
            }
            if (limit && lim <= ++i) {
                break;
            }
        }
        context.getCurrentScope().setBackRef(runtime.getNil());
        if (len > 0 && (limit || len > beg || lim < 0)) {
            result.append(this.makeShared(runtime, beg, len - beg));
        }
        return result;
    }
    
    private Encoding getEncodingForKCodeDefault(final Ruby runtime, final Regex pattern, final IRubyObject pat) {
        Encoding enc = pattern.getEncoding();
        if (enc != runtime.getKCode().getEncoding() && pat instanceof RubyRegexp) {
            final RubyRegexp regexp = (RubyRegexp)pat;
            if (regexp.isKCodeDefault()) {
                enc = runtime.getKCode().getEncoding();
            }
        }
        return enc;
    }
    
    private void populateCapturesForSplit(final Ruby runtime, final RubyArray result, final Matcher matcher, final boolean is19) {
        final Region region = matcher.getRegion();
        for (int i = 1; i < region.numRegs; ++i) {
            final int beg = region.beg[i];
            if (beg != -1) {
                result.append(is19 ? this.makeShared19(runtime, beg, region.end[i] - beg) : this.makeShared(runtime, beg, region.end[i] - beg));
            }
        }
    }
    
    private RubyArray awkSplit(final boolean limit, final int lim, int i) {
        final Ruby runtime = this.getRuntime();
        final RubyArray result = runtime.newArray();
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int end = p + len;
        boolean skip = true;
        int e = 0;
        int b = 0;
        while (p < end) {
            final int c = bytes[p++] & 0xFF;
            if (skip) {
                if (RubyString.ASCII.isSpace(c)) {
                    ++b;
                }
                else {
                    e = b + 1;
                    skip = false;
                    if (limit && lim <= i) {
                        break;
                    }
                    continue;
                }
            }
            else if (RubyString.ASCII.isSpace(c)) {
                result.append(this.makeShared(runtime, b, e - b));
                skip = true;
                b = e + 1;
                if (!limit) {
                    continue;
                }
                ++i;
            }
            else {
                ++e;
            }
        }
        if (len > 0 && (limit || len > b || lim < 0)) {
            result.append(this.makeShared(runtime, b, len - b));
        }
        return result;
    }
    
    @JRubyMethod(name = { "split" }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public RubyArray split19(final ThreadContext context) {
        return this.split19(context, context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "split" }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public RubyArray split19(final ThreadContext context, final IRubyObject arg0) {
        return this.splitCommon19(arg0, false, 0, 0, context);
    }
    
    @JRubyMethod(name = { "split" }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public RubyArray split19(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1) {
        final int lim = RubyNumeric.num2int(arg1);
        if (lim <= 0) {
            return this.splitCommon19(arg0, false, lim, 1, context);
        }
        if (lim == 1) {
            return (this.value.getRealSize() == 0) ? context.getRuntime().newArray() : context.getRuntime().newArray(this);
        }
        return this.splitCommon19(arg0, true, lim, 1, context);
    }
    
    private RubyArray splitCommon19(IRubyObject spat, final boolean limit, final int lim, final int i, final ThreadContext context) {
        RubyArray result;
        if (spat.isNil() && (spat = context.getRuntime().getGlobalVariables().get("$;")).isNil()) {
            result = this.awkSplit19(limit, lim, i);
        }
        else if (spat instanceof RubyString) {
            final ByteList spatValue = ((RubyString)spat).value;
            final int len = spatValue.getRealSize();
            final Encoding spatEnc = spatValue.getEncoding();
            if (len == 0) {
                final Regex pattern = RubyRegexp.getRegexpFromCache(context.getRuntime(), spatValue, spatEnc, new RegexpOptions());
                result = this.regexSplit19(context, pattern, pattern, limit, lim, i);
            }
            else {
                final byte[] bytes = spatValue.getUnsafeBytes();
                final int p = spatValue.getBegin();
                int c;
                if (spatEnc.isAsciiCompatible()) {
                    c = ((len == 1) ? (bytes[p] & 0xFF) : -1);
                }
                else {
                    c = ((len == StringSupport.preciseLength(spatEnc, bytes, p, p + len)) ? spatEnc.mbcToCode(bytes, p, p + len) : -1);
                }
                result = ((c == 32) ? this.awkSplit19(limit, lim, i) : this.stringSplit19(context, (RubyString)spat, limit, lim, i));
            }
        }
        else {
            final Ruby runtime = context.getRuntime();
            Regex pattern2;
            Regex prepared;
            if (spat instanceof RubyRegexp) {
                final RubyRegexp regexp = (RubyRegexp)spat;
                pattern2 = regexp.getPattern();
                prepared = regexp.preparePattern(this);
            }
            else {
                final RubyRegexp regexp = null;
                pattern2 = this.getStringPattern19(runtime, spat);
                prepared = RubyRegexp.preparePattern(runtime, pattern2, this);
            }
            result = this.regexSplit19(context, pattern2, prepared, limit, lim, i);
        }
        if (!limit && lim == 0) {
            while (result.size() > 0 && ((RubyString)result.eltInternal(result.size() - 1)).value.getRealSize() == 0) {
                result.pop(context);
            }
        }
        return result;
    }
    
    private RubyArray regexSplit19(final ThreadContext context, final Regex pattern, final Regex prepared, final boolean limit, final int lim, int i) {
        final Ruby runtime = context.getRuntime();
        final int begin = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int range = begin + len;
        final byte[] bytes = this.value.getUnsafeBytes();
        final Matcher matcher = prepared.matcher(bytes, begin, range);
        final RubyArray result = runtime.newArray();
        final Encoding enc = this.value.getEncoding();
        final boolean captures = pattern.numberOfCaptures() != 0;
        int beg = 0;
        boolean lastNull = false;
        int start = begin;
        int end;
        while ((end = matcher.search(start, range, 0)) >= 0) {
            if (start == end + begin && matcher.getBegin() == matcher.getEnd()) {
                if (len == 0) {
                    result.append(newEmptyString(runtime, this.getMetaClass()).infectBy(this));
                    break;
                }
                if (!lastNull) {
                    start += ((start == range) ? 1 : StringSupport.length(enc, bytes, start, range));
                    lastNull = true;
                    continue;
                }
                result.append(this.makeShared19(runtime, beg, StringSupport.length(enc, bytes, begin + beg, range)));
                beg = start - begin;
            }
            else {
                result.append(this.makeShared19(runtime, beg, end - beg));
                beg = matcher.getEnd();
                start = begin + beg;
            }
            lastNull = false;
            if (captures) {
                this.populateCapturesForSplit(runtime, result, matcher, true);
            }
            if (limit && lim <= ++i) {
                break;
            }
        }
        context.getCurrentScope().setBackRef(runtime.getNil());
        if (len > 0 && (limit || len > beg || lim < 0)) {
            result.append(this.makeShared19(runtime, beg, len - beg));
        }
        return result;
    }
    
    private RubyArray awkSplit19(final boolean limit, final int lim, int i) {
        final Ruby runtime = this.getRuntime();
        final RubyArray result = runtime.newArray();
        final byte[] bytes = this.value.getUnsafeBytes();
        final int ptr;
        int p = ptr = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int end = p + len;
        final Encoding enc = this.value.getEncoding();
        boolean skip = true;
        int e = 0;
        int b = 0;
        final boolean singlebyte = this.singleByteOptimizable(enc);
        while (p < end) {
            int c;
            if (singlebyte) {
                c = (bytes[p++] & 0xFF);
            }
            else {
                c = StringSupport.codePoint(runtime, enc, bytes, p, end);
                p += StringSupport.length(enc, bytes, p, end);
            }
            if (skip) {
                if (enc.isSpace(c)) {
                    b = p - ptr;
                }
                else {
                    e = p - ptr;
                    skip = false;
                    if (limit && lim <= i) {
                        break;
                    }
                    continue;
                }
            }
            else if (enc.isSpace(c)) {
                result.append(this.makeShared19(runtime, b, e - b));
                skip = true;
                b = p - ptr;
                if (!limit) {
                    continue;
                }
                ++i;
            }
            else {
                e = p - ptr;
            }
        }
        if (len > 0 && (limit || len > b || lim < 0)) {
            result.append(this.makeShared19(runtime, b, len - b));
        }
        return result;
    }
    
    private RubyArray stringSplit19(final ThreadContext context, final RubyString spat, final boolean limit, final int lim, int i) {
        final Ruby runtime = context.getRuntime();
        if (this.scanForCodeRange() == 96) {
            throw runtime.newArgumentError("invalid byte sequence in " + this.value.getEncoding());
        }
        if (spat.scanForCodeRange() == 96) {
            throw runtime.newArgumentError("invalid byte sequence in " + spat.value.getEncoding());
        }
        final RubyArray result = runtime.newArray();
        final Encoding enc = this.checkEncoding(spat);
        final ByteList pattern = spat.value;
        int p = 0;
        int e;
        while (p < this.value.getRealSize() && (e = this.value.indexOf(pattern, p)) >= 0) {
            final int t = enc.rightAdjustCharHead(this.value.getUnsafeBytes(), p + this.value.getBegin(), e, p + this.value.getRealSize());
            if (t != e) {
                p = t;
            }
            else {
                result.append(this.makeShared19(runtime, p, e - p));
                p = e + pattern.getRealSize();
                if (limit && lim <= ++i) {
                    break;
                }
                continue;
            }
        }
        if (this.value.getRealSize() > 0 && (limit || this.value.getRealSize() > p || lim < 0)) {
            result.append(this.makeShared19(runtime, p, this.value.getRealSize() - p));
        }
        return result;
    }
    
    private RubyString getStringForPattern(final IRubyObject obj) {
        if (obj instanceof RubyString) {
            return (RubyString)obj;
        }
        final IRubyObject val = obj.checkStringType();
        if (val.isNil()) {
            throw this.getRuntime().newTypeError("wrong argument type " + obj.getMetaClass() + " (expected Regexp)");
        }
        return (RubyString)val;
    }
    
    private RubyRegexp getPattern(final IRubyObject obj) {
        if (obj instanceof RubyRegexp) {
            return (RubyRegexp)obj;
        }
        return RubyRegexp.newRegexp(this.getRuntime(), this.getStringForPattern(obj).value);
    }
    
    private Regex getQuotedPattern(final IRubyObject obj) {
        if (obj instanceof RubyRegexp) {
            return ((RubyRegexp)obj).getPattern();
        }
        final Ruby runtime = this.getRuntime();
        return RubyRegexp.getQuotedRegexpFromCache(runtime, this.getStringForPattern(obj).value, runtime.getKCode().getEncoding(), new RegexpOptions());
    }
    
    private Regex getStringPattern(final Ruby runtime, final Encoding enc, final IRubyObject obj) {
        return RubyRegexp.getQuotedRegexpFromCache(runtime, this.getStringForPattern(obj).value, enc, new RegexpOptions());
    }
    
    private Regex getStringPattern19(final Ruby runtime, final IRubyObject obj) {
        final RubyString str = this.getStringForPattern(obj);
        if (str.scanForCodeRange() == 96) {
            throw runtime.newRegexpError("invalid multybyte character: " + RubyRegexp.regexpDescription19(runtime, str.value, new RegexpOptions(), str.value.getEncoding()).toString());
        }
        if (str.value.getEncoding().isDummy()) {
            throw runtime.newArgumentError("can't make regexp with dummy encoding");
        }
        return RubyRegexp.getQuotedRegexpFromCache19(runtime, str.value, new RegexpOptions(), str.isAsciiOnly());
    }
    
    @JRubyMethod(reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_8)
    public IRubyObject scan(final ThreadContext context, final IRubyObject arg, final Block block) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = runtime.getKCode().getEncoding();
        Regex pattern;
        int tuFlags;
        if (arg instanceof RubyRegexp) {
            final RubyRegexp regex = (RubyRegexp)arg;
            pattern = regex.getPattern();
            tuFlags = regex.flags;
        }
        else {
            pattern = this.getStringPattern(runtime, enc, arg);
            if (arg.isTaint()) {
                tuFlags = 8;
            }
            else {
                tuFlags = 0;
            }
        }
        final int begin = this.value.getBegin();
        final int range = begin + this.value.getRealSize();
        final Matcher matcher = pattern.matcher(this.value.getUnsafeBytes(), begin, range);
        if (block.isGiven()) {
            return this.scanIter(context, pattern, matcher, enc, block, begin, range, tuFlags);
        }
        return this.scanNoIter(context, pattern, matcher, enc, begin, range, tuFlags);
    }
    
    private IRubyObject scanIter(final ThreadContext context, final Regex pattern, final Matcher matcher, final Encoding enc, final Block block, final int begin, final int range, final int tuFlags) {
        final Ruby runtime = context.getRuntime();
        final byte[] bytes = this.value.getUnsafeBytes();
        final int size = this.value.getRealSize();
        RubyMatchData match = null;
        final DynamicScope scope = context.getCurrentScope();
        int end = 0;
        if (pattern.numberOfCaptures() == 0) {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                match = RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
                final RubyString substr = this.makeShared(runtime, matcher.getBegin(), matcher.getEnd() - matcher.getBegin());
                substr.infectBy(tuFlags);
                match.infectBy(tuFlags);
                block.yield(context, substr);
                this.modifyCheck(bytes, size);
            }
        }
        else {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                match = RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
                match.infectBy(tuFlags);
                block.yield(context, this.populateCapturesForScan(runtime, matcher, range, tuFlags, false));
                this.modifyCheck(bytes, size);
            }
        }
        scope.setBackRef((match == null) ? runtime.getNil() : match);
        return this;
    }
    
    private IRubyObject scanNoIter(final ThreadContext context, final Regex pattern, final Matcher matcher, final Encoding enc, final int begin, final int range, final int tuFlags) {
        final Ruby runtime = context.getRuntime();
        final RubyArray ary = runtime.newArray();
        int end = 0;
        if (pattern.numberOfCaptures() == 0) {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                final RubyString substr = this.makeShared(runtime, matcher.getBegin(), matcher.getEnd() - matcher.getBegin());
                substr.infectBy(tuFlags);
                ary.append(substr);
            }
        }
        else {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                ary.append(this.populateCapturesForScan(runtime, matcher, range, tuFlags, false));
            }
        }
        final DynamicScope scope = context.getCurrentScope();
        if (ary.size() > 0) {
            final RubyMatchData match = RubyRegexp.updateBackRef(context, this, scope, matcher, pattern);
            match.infectBy(tuFlags);
        }
        else {
            scope.setBackRef(runtime.getNil());
        }
        return ary;
    }
    
    private int positionEnd(final Matcher matcher, final Encoding enc, final int begin, final int range) {
        final int end = matcher.getEnd();
        if (matcher.getBegin() != end) {
            return end;
        }
        if (this.value.getRealSize() > end) {
            return end + enc.length(this.value.getUnsafeBytes(), begin + end, range);
        }
        return end + 1;
    }
    
    private IRubyObject populateCapturesForScan(final Ruby runtime, final Matcher matcher, final int range, final int tuFlags, final boolean is19) {
        final Region region = matcher.getRegion();
        final RubyArray result = this.getRuntime().newArray(region.numRegs);
        for (int i = 1; i < region.numRegs; ++i) {
            final int beg = region.beg[i];
            if (beg == -1) {
                result.append(runtime.getNil());
            }
            else {
                final RubyString substr = is19 ? this.makeShared19(runtime, beg, region.end[i] - beg) : this.makeShared(runtime, beg, region.end[i] - beg);
                substr.infectBy(tuFlags);
                result.append(substr);
            }
        }
        return result;
    }
    
    @JRubyMethod(name = { "scan" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject scan19(final ThreadContext context, final IRubyObject arg, final Block block) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = this.value.getEncoding();
        RubyRegexp regexp;
        int tuFlags;
        Regex pattern;
        Regex prepared;
        if (arg instanceof RubyRegexp) {
            regexp = (RubyRegexp)arg;
            tuFlags = regexp.flags;
            pattern = regexp.getPattern();
            prepared = regexp.preparePattern(this);
        }
        else {
            regexp = null;
            tuFlags = (arg.isTaint() ? 8 : 0);
            pattern = this.getStringPattern19(runtime, arg);
            prepared = RubyRegexp.preparePattern(runtime, pattern, this);
        }
        if (block.isGiven()) {
            return this.scanIter19(context, pattern, prepared, enc, block, regexp, tuFlags);
        }
        return this.scanNoIter19(context, pattern, prepared, enc, regexp, tuFlags);
    }
    
    private IRubyObject scanIter19(final ThreadContext context, final Regex pattern, final Regex prepared, final Encoding enc, final Block block, final RubyRegexp regexp, final int tuFlags) {
        final Ruby runtime = context.getRuntime();
        final byte[] bytes = this.value.getUnsafeBytes();
        final int begin = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int range = begin + len;
        final Matcher matcher = prepared.matcher(bytes, begin, range);
        final DynamicScope scope = context.getCurrentScope();
        int end = 0;
        RubyMatchData match = null;
        if (pattern.numberOfCaptures() == 0) {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
                match.regexp = regexp;
                final RubyString substr = this.makeShared19(runtime, matcher.getBegin(), matcher.getEnd() - matcher.getBegin());
                substr.infectBy(tuFlags);
                match.infectBy(tuFlags);
                block.yield(context, substr);
                this.modifyCheck(bytes, len, enc);
            }
        }
        else {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
                match.regexp = regexp;
                match.infectBy(tuFlags);
                block.yield(context, this.populateCapturesForScan(runtime, matcher, range, tuFlags, true));
                this.modifyCheck(bytes, len, enc);
            }
        }
        scope.setBackRef((match == null) ? runtime.getNil() : match);
        return this;
    }
    
    private IRubyObject scanNoIter19(final ThreadContext context, final Regex pattern, final Regex prepared, final Encoding enc, final RubyRegexp regexp, final int tuFlags) {
        final Ruby runtime = context.getRuntime();
        final byte[] bytes = this.value.getUnsafeBytes();
        final int begin = this.value.getBegin();
        final int range = begin + this.value.getRealSize();
        final Matcher matcher = prepared.matcher(bytes, begin, range);
        final RubyArray ary = runtime.newArray();
        int end = 0;
        if (pattern.numberOfCaptures() == 0) {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                final RubyString substr = this.makeShared19(runtime, matcher.getBegin(), matcher.getEnd() - matcher.getBegin());
                substr.infectBy(tuFlags);
                ary.append(substr);
            }
        }
        else {
            while (matcher.search(begin + end, range, 0) >= 0) {
                end = this.positionEnd(matcher, enc, begin, range);
                ary.append(this.populateCapturesForScan(runtime, matcher, range, tuFlags, true));
            }
        }
        final DynamicScope scope = context.getCurrentScope();
        if (ary.size() > 0) {
            final RubyMatchData match = RubyRegexp.updateBackRef19(context, this, scope, matcher, pattern);
            match.regexp = regexp;
            match.infectBy(tuFlags);
        }
        else {
            scope.setBackRef(runtime.getNil());
        }
        return ary;
    }
    
    @JRubyMethod(name = { "start_with?" })
    public IRubyObject start_with_p(final ThreadContext context) {
        return context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "start_with?" })
    public IRubyObject start_with_p(final ThreadContext context, final IRubyObject arg) {
        return this.start_with_pCommon(arg) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "start_with?" }, rest = true)
    public IRubyObject start_with_p(final ThreadContext context, final IRubyObject[] args) {
        for (int i = 0; i < args.length; ++i) {
            if (this.start_with_pCommon(args[i])) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    private boolean start_with_pCommon(final IRubyObject arg) {
        final IRubyObject tmp = arg.checkStringType();
        if (tmp.isNil()) {
            return false;
        }
        final RubyString otherString = (RubyString)tmp;
        this.checkEncoding(otherString);
        return this.value.getRealSize() >= otherString.value.getRealSize() && this.value.startsWith(otherString.value);
    }
    
    @JRubyMethod(name = { "end_with?" })
    public IRubyObject end_with_p(final ThreadContext context) {
        return context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "end_with?" })
    public IRubyObject end_with_p(final ThreadContext context, final IRubyObject arg) {
        return this.end_with_pCommon(arg) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "end_with?" }, rest = true)
    public IRubyObject end_with_p(final ThreadContext context, final IRubyObject[] args) {
        for (int i = 0; i < args.length; ++i) {
            if (this.end_with_pCommon(args[i])) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    private boolean end_with_pCommon(final IRubyObject arg) {
        final IRubyObject tmp = arg.checkStringType();
        if (tmp.isNil()) {
            return false;
        }
        final RubyString otherString = (RubyString)tmp;
        final Encoding enc = this.checkEncoding(otherString);
        if (this.value.getRealSize() < otherString.value.getRealSize()) {
            return false;
        }
        final int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final int s = end - otherString.value.getRealSize();
        return enc.leftAdjustCharHead(this.value.getUnsafeBytes(), p, s, end) == s && this.value.endsWith(otherString.value);
    }
    
    private IRubyObject justify(final IRubyObject arg0, final int jflag) {
        final Ruby runtime = this.getRuntime();
        return this.justifyCommon(runtime, RubyString.SPACE_BYTELIST, RubyNumeric.num2int(arg0), jflag);
    }
    
    private IRubyObject justify(final IRubyObject arg0, final IRubyObject arg1, final int jflag) {
        final Ruby runtime = this.getRuntime();
        final RubyString padStr = arg1.convertToString();
        final ByteList pad = padStr.value;
        if (pad.getRealSize() == 0) {
            throw runtime.newArgumentError("zero width padding");
        }
        final int width = RubyNumeric.num2int(arg0);
        final RubyString result = this.justifyCommon(runtime, pad, width, jflag);
        if (this.value.getRealSize() < width) {
            result.infectBy(padStr);
        }
        return result;
    }
    
    private RubyString justifyCommon(final Ruby runtime, final ByteList pad, final int width, final int jflag) {
        if (width < 0 || this.value.getRealSize() >= width) {
            return this.strDup(runtime);
        }
        final ByteList res = new ByteList(width);
        res.setRealSize(width);
        int padP = pad.getBegin();
        final int padLen = pad.getRealSize();
        final byte[] padBytes = pad.getUnsafeBytes();
        int p = res.getBegin();
        final byte[] bytes = res.getUnsafeBytes();
        if (jflag != 108) {
            final int n = width - this.value.getRealSize();
            final int end = p + ((jflag == 114) ? n : (n / 2));
            if (padLen <= 1) {
                while (p < end) {
                    bytes[p++] = padBytes[padP];
                }
            }
            else {
                int q = padP;
                while (p + padLen <= end) {
                    System.arraycopy(padBytes, padP, bytes, p, padLen);
                    p += padLen;
                }
                while (p < end) {
                    bytes[p++] = padBytes[q++];
                }
            }
        }
        System.arraycopy(this.value.getUnsafeBytes(), this.value.getBegin(), bytes, p, this.value.getRealSize());
        if (jflag != 114) {
            p += this.value.getRealSize();
            final int end2 = res.getBegin() + width;
            if (padLen <= 1) {
                while (p < end2) {
                    bytes[p++] = padBytes[padP];
                }
            }
            else {
                while (p + padLen <= end2) {
                    System.arraycopy(padBytes, padP, bytes, p, padLen);
                    p += padLen;
                }
                while (p < end2) {
                    bytes[p++] = padBytes[padP++];
                }
            }
        }
        final RubyString result = new RubyString(runtime, this.getMetaClass(), res);
        if ((!runtime.is1_9() && RubyNumeric.num2int(result.length()) > RubyNumeric.num2int(this.length())) || (runtime.is1_9() && RubyNumeric.num2int(result.length19()) > RubyNumeric.num2int(this.length19()))) {
            result.infectBy(this);
        }
        return result;
    }
    
    private IRubyObject justify19(final IRubyObject arg0, final int jflag) {
        final Ruby runtime = this.getRuntime();
        final RubyString result = this.justifyCommon(runtime, RubyString.SPACE_BYTELIST, 1, true, this.value.getEncoding(), RubyNumeric.num2int(arg0), jflag);
        if (this.getCodeRange() != 96) {
            result.setCodeRange(this.getCodeRange());
        }
        return result;
    }
    
    private IRubyObject justify19(final IRubyObject arg0, final IRubyObject arg1, final int jflag) {
        final Ruby runtime = this.getRuntime();
        final RubyString padStr = arg1.convertToString();
        final ByteList pad = padStr.value;
        final Encoding enc = this.checkEncoding(padStr);
        final int padCharLen = padStr.strLength(enc);
        if (pad.getRealSize() == 0 || padCharLen == 0) {
            throw runtime.newArgumentError("zero width padding");
        }
        final int width = RubyNumeric.num2int(arg0);
        final RubyString result = this.justifyCommon(runtime, pad, padCharLen, padStr.singleByteOptimizable(), enc, width, jflag);
        if (RubyNumeric.num2int(result.length19()) > RubyNumeric.num2int(this.length19())) {
            result.infectBy(padStr);
        }
        final int cr = codeRangeAnd(this.getCodeRange(), padStr.getCodeRange());
        if (cr != 96) {
            result.setCodeRange(cr);
        }
        return result;
    }
    
    private RubyString justifyCommon(final Ruby runtime, final ByteList pad, final int padCharLen, final boolean padSinglebyte, final Encoding enc, final int width, final int jflag) {
        final int len = this.strLength(enc);
        if (width < 0 || len >= width) {
            return this.strDup(runtime);
        }
        int n = width - len;
        int llen = (jflag == 108) ? 0 : ((jflag == 114) ? n : (n / 2));
        int rlen = n - llen;
        final int padP = pad.getBegin();
        final int padLen = pad.getRealSize();
        final byte[] padBytes = pad.getUnsafeBytes();
        final ByteList res = new ByteList(this.value.getRealSize() + n * padLen / padCharLen + 2);
        int p = res.getBegin();
        final byte[] bytes = res.getUnsafeBytes();
        while (llen > 0) {
            if (padLen <= 1) {
                bytes[p++] = padBytes[padP];
                --llen;
            }
            else {
                if (llen <= padCharLen) {
                    final int padPP = padSinglebyte ? (padP + llen) : StringSupport.nth(enc, padBytes, padP, padP + padLen, llen);
                    n = padPP - padP;
                    System.arraycopy(padBytes, padP, bytes, p, n);
                    p += n;
                    break;
                }
                System.arraycopy(padBytes, padP, bytes, p, padLen);
                p += padLen;
                llen -= padCharLen;
            }
        }
        System.arraycopy(this.value.getUnsafeBytes(), this.value.getBegin(), bytes, p, this.value.getRealSize());
        p += this.value.getRealSize();
        while (rlen > 0) {
            if (padLen <= 1) {
                bytes[p++] = padBytes[padP];
                --rlen;
            }
            else {
                if (rlen <= padCharLen) {
                    final int padPP = padSinglebyte ? (padP + rlen) : StringSupport.nth(enc, padBytes, padP, padP + padLen, rlen);
                    n = padPP - padP;
                    System.arraycopy(padBytes, padP, bytes, p, n);
                    p += n;
                    break;
                }
                System.arraycopy(padBytes, padP, bytes, p, padLen);
                p += padLen;
                rlen -= padCharLen;
            }
        }
        res.setRealSize(p);
        final RubyString result = new RubyString(runtime, this.getMetaClass(), res);
        if ((!runtime.is1_9() && RubyNumeric.num2int(result.length()) > RubyNumeric.num2int(this.length())) || (runtime.is1_9() && RubyNumeric.num2int(result.length19()) > RubyNumeric.num2int(this.length19()))) {
            result.infectBy(this);
        }
        result.associateEncoding(enc);
        return result;
    }
    
    @JRubyMethod(name = { "ljust" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject ljust(final IRubyObject arg0) {
        return this.justify(arg0, 108);
    }
    
    @JRubyMethod(name = { "ljust" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject ljust(final IRubyObject arg0, final IRubyObject arg1) {
        return this.justify(arg0, arg1, 108);
    }
    
    @JRubyMethod(name = { "ljust" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject ljust19(final IRubyObject arg0) {
        return this.justify19(arg0, 108);
    }
    
    @JRubyMethod(name = { "ljust" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject ljust19(final IRubyObject arg0, final IRubyObject arg1) {
        return this.justify19(arg0, arg1, 108);
    }
    
    @JRubyMethod(name = { "rjust" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject rjust(final IRubyObject arg0) {
        return this.justify(arg0, 114);
    }
    
    @JRubyMethod(name = { "rjust" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject rjust(final IRubyObject arg0, final IRubyObject arg1) {
        return this.justify(arg0, arg1, 114);
    }
    
    @JRubyMethod(name = { "rjust" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rjust19(final IRubyObject arg0) {
        return this.justify19(arg0, 114);
    }
    
    @JRubyMethod(name = { "rjust" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rjust19(final IRubyObject arg0, final IRubyObject arg1) {
        return this.justify19(arg0, arg1, 114);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject center(final IRubyObject arg0) {
        return this.justify(arg0, 99);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject center(final IRubyObject arg0, final IRubyObject arg1) {
        return this.justify(arg0, arg1, 99);
    }
    
    @JRubyMethod(name = { "center" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject center19(final IRubyObject arg0) {
        return this.justify19(arg0, 99);
    }
    
    @JRubyMethod(name = { "center" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject center19(final IRubyObject arg0, final IRubyObject arg1) {
        return this.justify19(arg0, arg1, 99);
    }
    
    @JRubyMethod
    public IRubyObject partition(final ThreadContext context, final Block block) {
        return RubyEnumerable.partition(context, this, block);
    }
    
    @JRubyMethod
    public IRubyObject partition(final ThreadContext context, final IRubyObject arg, final Block block) {
        final Ruby runtime = context.getRuntime();
        int pos;
        RubyString sep;
        if (arg instanceof RubyRegexp) {
            final RubyRegexp regex = (RubyRegexp)arg;
            pos = regex.search19(context, this, 0, false);
            if (pos < 0) {
                return this.partitionMismatch(runtime);
            }
            sep = (RubyString)this.subpat19(runtime, context, regex);
            if (pos == 0 && sep.value.getRealSize() == 0) {
                return this.partitionMismatch(runtime);
            }
        }
        else {
            final IRubyObject tmp = arg.checkStringType();
            if (tmp.isNil()) {
                throw runtime.newTypeError("type mismatch: " + arg.getMetaClass().getName() + " given");
            }
            sep = (RubyString)tmp;
            pos = this.strIndex19(sep, 0);
            if (pos < 0) {
                return this.partitionMismatch(runtime);
            }
        }
        return RubyArray.newArray(runtime, new IRubyObject[] { this.makeShared19(runtime, 0, pos), sep, this.makeShared19(runtime, pos + sep.value.getRealSize(), this.value.getRealSize() - pos - sep.value.getRealSize()) });
    }
    
    private IRubyObject partitionMismatch(final Ruby runtime) {
        return RubyArray.newArray(runtime, new IRubyObject[] { this, newEmptyString(runtime), newEmptyString(runtime) });
    }
    
    @JRubyMethod(name = { "rpartition" })
    public IRubyObject rpartition(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        int pos;
        RubyString sep;
        if (arg instanceof RubyRegexp) {
            final RubyRegexp regex = (RubyRegexp)arg;
            pos = regex.search19(context, this, this.value.getRealSize(), true);
            if (pos < 0) {
                return this.rpartitionMismatch(runtime);
            }
            sep = (RubyString)RubyRegexp.nth_match(0, context.getCurrentScope().getBackRef(runtime));
        }
        else {
            final IRubyObject tmp = arg.checkStringType();
            if (tmp.isNil()) {
                throw runtime.newTypeError("type mismatch: " + arg.getMetaClass().getName() + " given");
            }
            sep = (RubyString)tmp;
            pos = this.strRindex19(sep, this.subLength(this.value.getRealSize()));
            if (pos < 0) {
                return this.rpartitionMismatch(runtime);
            }
        }
        return RubyArray.newArray(runtime, new IRubyObject[] { this.substr19(runtime, 0, pos), sep, this.substr19(runtime, pos + sep.strLength(), this.value.getRealSize()) });
    }
    
    private IRubyObject rpartitionMismatch(final Ruby runtime) {
        return RubyArray.newArray(runtime, new IRubyObject[] { newEmptyString(runtime), newEmptyString(runtime), this });
    }
    
    @JRubyMethod(name = { "chop" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject chop(final ThreadContext context) {
        if (this.value.getRealSize() == 0) {
            return newEmptyString(context.getRuntime(), this.getMetaClass()).infectBy(this);
        }
        return this.makeShared(context.getRuntime(), 0, this.choppedLength());
    }
    
    @JRubyMethod(name = { "chop!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject chop_bang(final ThreadContext context) {
        if (this.value.getRealSize() == 0) {
            return context.getRuntime().getNil();
        }
        this.view(0, this.choppedLength());
        return this;
    }
    
    private int choppedLength() {
        int end = this.value.getRealSize() - 1;
        if (this.value.getUnsafeBytes()[this.value.getBegin() + end] == 10 && end > 0 && this.value.getUnsafeBytes()[this.value.getBegin() + end - 1] == 13) {
            --end;
        }
        return end;
    }
    
    @JRubyMethod(name = { "chop" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject chop19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return newEmptyString(runtime, this.getMetaClass(), this.value.getEncoding()).infectBy(this);
        }
        return this.makeShared19(runtime, 0, this.choppedLength19(runtime));
    }
    
    @JRubyMethod(name = { "chop!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject chop_bang19(final ThreadContext context) {
        this.modifyCheck();
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        this.keepCodeRange();
        this.view(0, this.choppedLength19(runtime));
        return this;
    }
    
    private int choppedLength19(final Ruby runtime) {
        final int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        if (p > end) {
            return 0;
        }
        final byte[] bytes = this.value.getUnsafeBytes();
        final Encoding enc = this.value.getEncoding();
        int s = enc.prevCharHead(bytes, p, end, end);
        if (s == -1) {
            return 0;
        }
        if (s > p && StringSupport.codePoint(runtime, enc, bytes, s, end) == 10) {
            final int s2 = enc.prevCharHead(bytes, p, s, end);
            if (s2 != -1 && StringSupport.codePoint(runtime, enc, bytes, s2, end) == 13) {
                s = s2;
            }
        }
        return s - p;
    }
    
    @JRubyMethod(name = { "chomp" }, compat = CompatVersion.RUBY1_8)
    public RubyString chomp(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.chomp_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "chomp" }, compat = CompatVersion.RUBY1_8)
    public RubyString chomp(final ThreadContext context, final IRubyObject arg0) {
        final RubyString str = this.strDup(context.getRuntime());
        str.chomp_bang(context, arg0);
        return str;
    }
    
    @JRubyMethod(name = { "chomp!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject chomp_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final IRubyObject rsObj = runtime.getGlobalVariables().get("$/");
        if (rsObj == runtime.getGlobalVariables().getDefaultSeparator()) {
            return this.smartChopBangCommon(runtime);
        }
        return this.chompBangCommon(runtime, rsObj);
    }
    
    @JRubyMethod(name = { "chomp!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject chomp_bang(final ThreadContext context, final IRubyObject arg0) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        return this.chompBangCommon(runtime, arg0);
    }
    
    private IRubyObject chompBangCommon(final Ruby runtime, final IRubyObject rsObj) {
        if (rsObj.isNil()) {
            return rsObj;
        }
        final RubyString rs = rsObj.convertToString();
        final int p = this.value.getBegin();
        int len = this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        final int rslen = rs.value.getRealSize();
        if (rslen == 0) {
            while (len > 0 && bytes[p + len - 1] == 10) {
                if (--len > 0 && bytes[p + len - 1] == 13) {
                    --len;
                }
            }
            if (len < this.value.getRealSize()) {
                this.view(0, len);
                return this;
            }
            return runtime.getNil();
        }
        else {
            if (rslen > len) {
                return runtime.getNil();
            }
            final byte newline = rs.value.getUnsafeBytes()[rslen - 1];
            if (rslen == 1 && newline == 10) {
                return this.smartChopBangCommon(runtime);
            }
            if ((bytes[p + len - 1] == newline && rslen <= 1) || this.value.endsWith(rs.value)) {
                this.view(0, this.value.getRealSize() - rslen);
                return this;
            }
            return runtime.getNil();
        }
    }
    
    private IRubyObject smartChopBangCommon(final Ruby runtime) {
        final ByteList value = this.value;
        int len = value.getRealSize();
        final int p = value.getBegin();
        final byte[] bytes = value.getUnsafeBytes();
        final byte b = bytes[p + len - 1];
        if (b == 10) {
            if (--len > 0 && bytes[p + len - 1] == 13) {
                --len;
            }
            this.view(0, len);
        }
        else {
            if (b != 13) {
                this.modifyCheck();
                return runtime.getNil();
            }
            --len;
            this.view(0, len);
        }
        return this;
    }
    
    @JRubyMethod(name = { "chomp" }, compat = CompatVersion.RUBY1_9)
    public RubyString chomp19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.chomp_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "chomp" }, compat = CompatVersion.RUBY1_9)
    public RubyString chomp19(final ThreadContext context, final IRubyObject arg0) {
        final RubyString str = this.strDup(context.getRuntime());
        str.chomp_bang19(context, arg0);
        return str;
    }
    
    @JRubyMethod(name = { "chomp!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject chomp_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final IRubyObject rsObj = runtime.getGlobalVariables().get("$/");
        if (rsObj == runtime.getGlobalVariables().getDefaultSeparator()) {
            return this.smartChopBangCommon19(runtime);
        }
        return this.chompBangCommon19(runtime, rsObj);
    }
    
    @JRubyMethod(name = { "chomp!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject chomp_bang19(final ThreadContext context, final IRubyObject arg0) {
        this.modifyCheck();
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        return this.chompBangCommon19(runtime, arg0);
    }
    
    private IRubyObject chompBangCommon19(final Ruby runtime, final IRubyObject rsObj) {
        if (rsObj.isNil()) {
            return rsObj;
        }
        final RubyString rs = rsObj.convertToString();
        final int p = this.value.getBegin();
        int len = this.value.getRealSize();
        final int end = p + len;
        final byte[] bytes = this.value.getUnsafeBytes();
        final int rslen = rs.value.getRealSize();
        if (rslen == 0) {
            while (len > 0 && bytes[p + len - 1] == 10) {
                if (--len > 0 && bytes[p + len - 1] == 13) {
                    --len;
                }
            }
            if (len < this.value.getRealSize()) {
                this.keepCodeRange();
                this.view(0, len);
                return this;
            }
            return runtime.getNil();
        }
        else {
            if (rslen > len) {
                return runtime.getNil();
            }
            final byte newline = rs.value.getUnsafeBytes()[rslen - 1];
            if (rslen == 1 && newline == 10) {
                return this.smartChopBangCommon19(runtime);
            }
            final Encoding enc = this.checkEncoding(rs);
            if (rs.scanForCodeRange() == 96) {
                return runtime.getNil();
            }
            final int pp = end - rslen;
            if ((bytes[p + len - 1] != newline || rslen > 1) && !this.value.endsWith(rs.value)) {
                return runtime.getNil();
            }
            if (enc.leftAdjustCharHead(bytes, p, pp, end) != pp) {
                return runtime.getNil();
            }
            if (this.getCodeRange() != 32) {
                this.clearCodeRange();
            }
            this.view(0, this.value.getRealSize() - rslen);
            return this;
        }
    }
    
    private IRubyObject smartChopBangCommon19(final Ruby runtime) {
        final int p = this.value.getBegin();
        int len = this.value.getRealSize();
        int end = p + len;
        final byte[] bytes = this.value.getUnsafeBytes();
        final Encoding enc = this.value.getEncoding();
        this.keepCodeRange();
        if (enc.minLength() > 1) {
            int pp = enc.leftAdjustCharHead(bytes, p, end - enc.minLength(), end);
            if (enc.isNewLine(bytes, pp, end)) {
                end = pp;
            }
            pp = end - enc.minLength();
            if (pp >= p) {
                pp = enc.leftAdjustCharHead(bytes, p, pp, end);
                if (StringSupport.preciseLength(enc, bytes, pp, end) > 0 && enc.mbcToCode(bytes, pp, end) == 13) {
                    end = pp;
                }
            }
            if (end == p + this.value.getRealSize()) {
                this.modifyCheck();
                return runtime.getNil();
            }
            len = end - p;
            this.view(0, len);
        }
        else if (bytes[p + len - 1] == 10) {
            if (--len > 0 && bytes[p + len - 1] == 13) {
                --len;
            }
            this.view(0, len);
        }
        else {
            if (bytes[p + len - 1] != 13) {
                this.modifyCheck();
                return runtime.getNil();
            }
            --len;
            this.view(0, len);
        }
        return this;
    }
    
    @JRubyMethod(name = { "lstrip" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject lstrip(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.lstrip_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "lstrip!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject lstrip_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        return this.singleByteLStrip(runtime, RubyString.ASCII, this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "lstrip" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject lstrip19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.lstrip_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "lstrip!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject lstrip_bang19(final ThreadContext context) {
        this.modifyCheck();
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final Encoding enc = this.value.getEncoding();
        final int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        IRubyObject result;
        if (this.singleByteOptimizable(enc)) {
            result = this.singleByteLStrip(runtime, enc, bytes, s, end);
        }
        else {
            result = this.multiByteLStrip(runtime, enc, bytes, s, end);
        }
        this.keepCodeRange();
        return result;
    }
    
    private IRubyObject singleByteLStrip(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int p;
        for (p = s; p < end && enc.isSpace(bytes[p] & 0xFF); ++p) {}
        if (p > s) {
            this.view(p - s, end - p);
            return this;
        }
        return runtime.getNil();
    }
    
    private IRubyObject multiByteLStrip(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int p;
        int c;
        for (p = s; p < end && enc.isSpace(c = StringSupport.codePoint(runtime, enc, bytes, p, end)); p += StringSupport.codeLength(runtime, enc, c)) {}
        if (p > s) {
            this.view(p - s, end - p);
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "rstrip" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject rstrip(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.rstrip_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "rstrip!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject rstrip_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        return this.singleByteRStrip(runtime, RubyString.ASCII, this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "rstrip" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rstrip19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.rstrip_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "rstrip!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rstrip_bang19(final ThreadContext context) {
        this.modifyCheck();
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final Encoding enc = this.value.getEncoding();
        final int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        IRubyObject result;
        if (this.singleByteOptimizable(enc)) {
            result = this.singleByteRStrip(runtime, enc, bytes, s, end);
        }
        else {
            result = this.multiByteRStrip(runtime, enc, bytes, s, end);
        }
        this.keepCodeRange();
        return result;
    }
    
    private IRubyObject singleByteRStrip2(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int endp;
        for (endp = end; endp - 1 >= s && bytes[endp - 1] == 0; --endp) {}
        while (endp - 1 >= s && enc.isSpace(bytes[endp - 1] & 0xFF)) {
            --endp;
        }
        if (endp < end) {
            this.view(0, endp - s);
            return this;
        }
        return runtime.getNil();
    }
    
    private IRubyObject singleByteRStrip(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int endp;
        for (endp = end - 1; endp >= s && bytes[endp] == 0; --endp) {}
        while (endp >= s && enc.isSpace(bytes[endp] & 0xFF)) {
            --endp;
        }
        if (endp < end - 1) {
            this.view(0, endp - s + 1);
            return this;
        }
        return runtime.getNil();
    }
    
    private IRubyObject multiByteRStrip(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int prev;
        for (int endp = end; (prev = enc.prevCharHead(bytes, s, endp, end)) != -1 && enc.isSpace(StringSupport.codePoint(runtime, enc, bytes, prev, end)); endp = prev) {}
        if (prev < end) {
            this.view(0, prev - s + 1);
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "strip" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject strip(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.strip_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "strip!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject strip_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        return this.singleByteStrip(runtime, RubyString.ASCII, this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize());
    }
    
    @JRubyMethod(name = { "strip" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject strip19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.strip_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "strip!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject strip_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        this.modifyCheck();
        final Encoding enc = this.value.getEncoding();
        final int s = this.value.getBegin();
        final int end = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        IRubyObject result;
        if (this.singleByteOptimizable(enc)) {
            result = this.singleByteStrip(runtime, enc, bytes, s, end);
        }
        else {
            result = this.multiByteStrip(runtime, enc, bytes, s, end);
        }
        this.keepCodeRange();
        return result;
    }
    
    private IRubyObject singleByteStrip(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int p;
        for (p = s; p < end && enc.isSpace(bytes[p] & 0xFF); ++p) {}
        int endp;
        for (endp = end - 1; endp >= p && bytes[endp] == 0; --endp) {}
        while (endp >= p && enc.isSpace(bytes[endp] & 0xFF)) {
            --endp;
        }
        if (p > s || endp < end - 1) {
            this.view(p - s, endp - p + 1);
            return this;
        }
        return runtime.getNil();
    }
    
    private IRubyObject multiByteStrip(final Ruby runtime, final Encoding enc, final byte[] bytes, final int s, final int end) {
        int p;
        int c;
        for (p = s; p < end && enc.isSpace(c = StringSupport.codePoint(runtime, enc, bytes, p, end)); p += StringSupport.codeLength(runtime, enc, c)) {}
        int endp;
        int prev;
        for (endp = end; (prev = enc.prevCharHead(bytes, s, endp, end)) != -1 && enc.isSpace(StringSupport.codePoint(runtime, enc, bytes, prev, end)); endp = prev) {}
        if (p > s || prev < end) {
            this.view(p - s, endp - p);
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "count" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject count(final ThreadContext context) {
        throw context.getRuntime().newArgumentError("wrong number of arguments");
    }
    
    @JRubyMethod(name = { "count" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject count(final ThreadContext context, final IRubyObject arg) {
        final boolean[] table = new boolean[256];
        arg.convertToString().trSetupTable(table, true);
        return this.countCommon(context.getRuntime(), table);
    }
    
    @JRubyMethod(name = { "count" }, required = 1, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject count(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return RubyFixnum.zero(runtime);
        }
        final boolean[] table = new boolean[256];
        args[0].convertToString().trSetupTable(table, true);
        for (int i = 1; i < args.length; ++i) {
            args[i].convertToString().trSetupTable(table, false);
        }
        return this.countCommon(runtime, table);
    }
    
    private IRubyObject countCommon(final Ruby runtime, final boolean[] table) {
        int i = 0;
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        while (p < end) {
            if (table[bytes[p++] & 0xFF]) {
                ++i;
            }
        }
        return runtime.newFixnum(i);
    }
    
    @JRubyMethod(name = { "count" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject count19(final ThreadContext context) {
        throw context.getRuntime().newArgumentError("wrong number of arguments");
    }
    
    @JRubyMethod(name = { "count" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject count19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return RubyFixnum.zero(runtime);
        }
        final RubyString otherStr = arg.convertToString();
        final Encoding enc = this.checkEncoding(otherStr);
        final boolean[] table = new boolean[256];
        final TrTables tables = otherStr.trSetupTable(context.getRuntime(), table, null, true, enc);
        return this.countCommon19(runtime, table, tables, enc);
    }
    
    @JRubyMethod(name = { "count" }, required = 1, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject count19(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return RubyFixnum.zero(runtime);
        }
        RubyString otherStr = args[0].convertToString();
        Encoding enc = this.checkEncoding(otherStr);
        final boolean[] table = new boolean[256];
        TrTables tables = otherStr.trSetupTable(runtime, table, null, true, enc);
        for (int i = 1; i < args.length; ++i) {
            otherStr = args[i].convertToString();
            enc = this.checkEncoding(otherStr);
            tables = otherStr.trSetupTable(runtime, table, tables, false, enc);
        }
        return this.countCommon19(runtime, table, tables, enc);
    }
    
    private IRubyObject countCommon19(final Ruby runtime, final boolean[] table, final TrTables tables, final Encoding enc) {
        int i = 0;
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        while (p < end) {
            int c;
            if (enc.isAsciiCompatible() && Encoding.isAscii(c = (bytes[p] & 0xFF))) {
                if (table[c]) {
                    ++i;
                }
                ++p;
            }
            else {
                c = StringSupport.codePoint(runtime, enc, bytes, p, end);
                final int cl = StringSupport.codeLength(runtime, enc, c);
                if (this.trFind(c, table, tables)) {
                    ++i;
                }
                p += cl;
            }
        }
        return runtime.newFixnum(i);
    }
    
    @JRubyMethod(name = { "delete" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject delete(final ThreadContext context) {
        throw context.getRuntime().newArgumentError("wrong number of arguments");
    }
    
    @JRubyMethod(name = { "delete" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject delete(final ThreadContext context, final IRubyObject arg) {
        final RubyString str = this.strDup(context.getRuntime());
        str.delete_bang(context, arg);
        return str;
    }
    
    @JRubyMethod(name = { "delete" }, required = 1, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject delete(final ThreadContext context, final IRubyObject[] args) {
        final RubyString str = this.strDup(context.getRuntime());
        str.delete_bang(context, args);
        return str;
    }
    
    @JRubyMethod(name = { "delete!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject delete_bang(final ThreadContext context) {
        throw context.getRuntime().newArgumentError("wrong number of arguments");
    }
    
    @JRubyMethod(name = { "delete!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject delete_bang(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final boolean[] squeeze = new boolean[256];
        arg.convertToString().trSetupTable(squeeze, true);
        return this.delete_bangCommon(runtime, squeeze);
    }
    
    @JRubyMethod(name = { "delete!" }, required = 1, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject delete_bang(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final boolean[] squeeze = new boolean[256];
        args[0].convertToString().trSetupTable(squeeze, true);
        for (int i = 1; i < args.length; ++i) {
            args[i].convertToString().trSetupTable(squeeze, false);
        }
        return this.delete_bangCommon(runtime, squeeze);
    }
    
    private IRubyObject delete_bangCommon(final Ruby runtime, final boolean[] squeeze) {
        this.modify();
        int t;
        int s = t = this.value.getBegin();
        final int send = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        boolean modify = false;
        while (s < send) {
            if (squeeze[bytes[s] & 0xFF]) {
                modify = true;
            }
            else {
                bytes[t++] = bytes[s];
            }
            ++s;
        }
        this.value.setRealSize(t - this.value.getBegin());
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { "delete" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject delete19(final ThreadContext context) {
        throw context.getRuntime().newArgumentError("wrong number of arguments");
    }
    
    @JRubyMethod(name = { "delete" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject delete19(final ThreadContext context, final IRubyObject arg) {
        final RubyString str = this.strDup(context.getRuntime());
        str.delete_bang19(context, arg);
        return str;
    }
    
    @JRubyMethod(name = { "delete" }, required = 1, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject delete19(final ThreadContext context, final IRubyObject[] args) {
        final RubyString str = this.strDup(context.getRuntime());
        str.delete_bang19(context, args);
        return str;
    }
    
    @JRubyMethod(name = { "delete!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject delete_bang19(final ThreadContext context) {
        throw context.getRuntime().newArgumentError("wrong number of arguments");
    }
    
    @JRubyMethod(name = { "delete!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject delete_bang19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final RubyString otherStr = arg.convertToString();
        final Encoding enc = this.checkEncoding(otherStr);
        final boolean[] squeeze = new boolean[256];
        final TrTables tables = otherStr.trSetupTable(runtime, squeeze, null, true, enc);
        return this.delete_bangCommon19(runtime, squeeze, tables, enc);
    }
    
    @JRubyMethod(name = { "delete!" }, required = 1, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject delete_bang19(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        RubyString otherStr = args[0].convertToString();
        Encoding enc = this.checkEncoding(otherStr);
        final boolean[] squeeze = new boolean[256];
        TrTables tables = otherStr.trSetupTable(runtime, squeeze, null, true, enc);
        for (int i = 1; i < args.length; ++i) {
            otherStr = args[i].convertToString();
            enc = this.checkEncoding(otherStr);
            tables = otherStr.trSetupTable(runtime, squeeze, tables, false, enc);
        }
        return this.delete_bangCommon19(runtime, squeeze, tables, enc);
    }
    
    private IRubyObject delete_bangCommon19(final Ruby runtime, final boolean[] squeeze, final TrTables tables, final Encoding enc) {
        this.modifyAndKeepCodeRange();
        int t;
        int s = t = this.value.getBegin();
        final int send = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        boolean modify = false;
        final boolean asciiCompatible = enc.isAsciiCompatible();
        int cr = asciiCompatible ? 32 : 64;
        while (s < send) {
            int c;
            if (asciiCompatible && Encoding.isAscii(c = (bytes[s] & 0xFF))) {
                if (squeeze[c]) {
                    modify = true;
                }
                else {
                    if (t != s) {
                        bytes[t] = (byte)c;
                    }
                    ++t;
                }
                ++s;
            }
            else {
                c = StringSupport.codePoint(runtime, enc, bytes, s, send);
                final int cl = StringSupport.codeLength(runtime, enc, c);
                if (this.trFind(c, squeeze, tables)) {
                    modify = true;
                }
                else {
                    if (t != s) {
                        enc.codeToMbc(c, bytes, t);
                    }
                    t += cl;
                    if (cr == 32) {
                        cr = 64;
                    }
                }
                s += cl;
            }
        }
        this.value.setRealSize(t - this.value.getBegin());
        this.setCodeRange(cr);
        return modify ? this : runtime.getNil();
    }
    
    @JRubyMethod(name = { "squeeze" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject squeeze(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.squeeze_bang(context);
        return str;
    }
    
    @JRubyMethod(name = { "squeeze" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject squeeze(final ThreadContext context, final IRubyObject arg) {
        final RubyString str = this.strDup(context.getRuntime());
        str.squeeze_bang(context, arg);
        return str;
    }
    
    @JRubyMethod(name = { "squeeze" }, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject squeeze(final ThreadContext context, final IRubyObject[] args) {
        final RubyString str = this.strDup(context.getRuntime());
        str.squeeze_bang(context, args);
        return str;
    }
    
    @JRubyMethod(name = { "squeeze!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject squeeze_bang(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final boolean[] squeeze = new boolean[256];
        for (int i = 0; i < 256; ++i) {
            squeeze[i] = true;
        }
        this.modify();
        return this.squeezeCommon(runtime, squeeze);
    }
    
    @JRubyMethod(name = { "squeeze!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject squeeze_bang(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final boolean[] squeeze = new boolean[256];
        arg.convertToString().trSetupTable(squeeze, true);
        this.modify();
        return this.squeezeCommon(runtime, squeeze);
    }
    
    @JRubyMethod(name = { "squeeze!" }, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject squeeze_bang(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final boolean[] squeeze = new boolean[256];
        args[0].convertToString().trSetupTable(squeeze, true);
        for (int i = 1; i < args.length; ++i) {
            args[i].convertToString().trSetupTable(squeeze, false);
        }
        this.modify();
        return this.squeezeCommon(runtime, squeeze);
    }
    
    private IRubyObject squeezeCommon(final Ruby runtime, final boolean[] squeeze) {
        int t;
        int s = t = this.value.getBegin();
        final int send = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        int save = -1;
        while (s < send) {
            final int c = bytes[s++] & 0xFF;
            if (c != save || !squeeze[c]) {
                bytes[t++] = (byte)(save = c);
            }
        }
        if (t - this.value.getBegin() != this.value.getRealSize()) {
            this.value.setRealSize(t - this.value.getBegin());
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "squeeze" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject squeeze19(final ThreadContext context) {
        final RubyString str = this.strDup(context.getRuntime());
        str.squeeze_bang19(context);
        return str;
    }
    
    @JRubyMethod(name = { "squeeze" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject squeeze19(final ThreadContext context, final IRubyObject arg) {
        final RubyString str = this.strDup(context.getRuntime());
        str.squeeze_bang19(context, arg);
        return str;
    }
    
    @JRubyMethod(name = { "squeeze" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject squeeze19(final ThreadContext context, final IRubyObject[] args) {
        final RubyString str = this.strDup(context.getRuntime());
        str.squeeze_bang19(context, args);
        return str;
    }
    
    @JRubyMethod(name = { "squeeze!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject squeeze_bang19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final boolean[] squeeze = new boolean[256];
        for (int i = 0; i < 256; ++i) {
            squeeze[i] = true;
        }
        this.modifyAndKeepCodeRange();
        if (this.singleByteOptimizable()) {
            return this.squeezeCommon(runtime, squeeze);
        }
        return this.squeezeCommon19(runtime, squeeze, null, this.value.getEncoding(), false);
    }
    
    @JRubyMethod(name = { "squeeze!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject squeeze_bang19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        final RubyString otherStr = arg.convertToString();
        final boolean[] squeeze = new boolean[256];
        final TrTables tables = otherStr.trSetupTable(runtime, squeeze, null, true, this.checkEncoding(otherStr));
        this.modifyAndKeepCodeRange();
        if (this.singleByteOptimizable() && otherStr.singleByteOptimizable()) {
            return this.squeezeCommon(runtime, squeeze);
        }
        return this.squeezeCommon19(runtime, squeeze, tables, this.value.getEncoding(), true);
    }
    
    @JRubyMethod(name = { "squeeze!" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject squeeze_bang19(final ThreadContext context, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            this.modifyCheck();
            return runtime.getNil();
        }
        RubyString otherStr = args[0].convertToString();
        Encoding enc = this.checkEncoding(otherStr);
        final boolean[] squeeze = new boolean[256];
        TrTables tables = otherStr.trSetupTable(runtime, squeeze, null, true, enc);
        boolean singlebyte = this.singleByteOptimizable() && otherStr.singleByteOptimizable();
        for (int i = 1; i < args.length; ++i) {
            otherStr = args[i].convertToString();
            enc = this.checkEncoding(otherStr);
            singlebyte = (singlebyte && otherStr.singleByteOptimizable());
            tables = otherStr.trSetupTable(runtime, squeeze, tables, false, enc);
        }
        this.modifyAndKeepCodeRange();
        if (singlebyte) {
            return this.squeezeCommon(runtime, squeeze);
        }
        return this.squeezeCommon19(runtime, squeeze, tables, enc, true);
    }
    
    private IRubyObject squeezeCommon19(final Ruby runtime, final boolean[] squeeze, final TrTables tables, final Encoding enc, final boolean isArg) {
        int t;
        int s = t = this.value.getBegin();
        final int send = s + this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        int save = -1;
        while (s < send) {
            int c;
            if (enc.isAsciiCompatible() && Encoding.isAscii(c = (bytes[s] & 0xFF))) {
                if (c != save || (isArg && !squeeze[c])) {
                    bytes[t++] = (byte)(save = c);
                }
                ++s;
            }
            else {
                c = StringSupport.codePoint(runtime, enc, bytes, s, send);
                final int cl = StringSupport.codeLength(runtime, enc, c);
                if (c != save || (isArg && !this.trFind(c, squeeze, tables))) {
                    if (t != s) {
                        enc.codeToMbc(c, bytes, t);
                    }
                    save = c;
                    t += cl;
                }
                s += cl;
            }
        }
        if (t - this.value.getBegin() != this.value.getRealSize()) {
            this.value.setRealSize(t - this.value.getBegin());
            return this;
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "tr" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject tr(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        final RubyString str = this.strDup(context.getRuntime());
        str.trTrans(context, src, repl, false);
        return str;
    }
    
    @JRubyMethod(name = { "tr!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject tr_bang(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        return this.trTrans(context, src, repl, false);
    }
    
    @JRubyMethod(name = { "tr" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject tr19(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        final RubyString str = this.strDup(context.getRuntime());
        str.trTrans19(context, src, repl, false);
        return str;
    }
    
    @JRubyMethod(name = { "tr!" })
    public IRubyObject tr_bang19(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        return this.trTrans19(context, src, repl, false);
    }
    
    private void trSetupTable(final boolean[] table, final boolean init) {
        final TR tr = new TR(this.value);
        boolean cflag = false;
        if (this.value.getRealSize() > 1 && this.value.getUnsafeBytes()[this.value.getBegin()] == 94) {
            cflag = true;
            final TR tr2 = tr;
            ++tr2.p;
        }
        if (init) {
            for (int i = 0; i < 256; ++i) {
                table[i] = true;
            }
        }
        final boolean[] buf = new boolean[256];
        for (int j = 0; j < 256; ++j) {
            buf[j] = cflag;
        }
        int c;
        while ((c = this.trNext(tr)) >= 0) {
            buf[c & 0xFF] = !cflag;
        }
        for (int k = 0; k < 256; ++k) {
            table[k] = (table[k] && buf[k]);
        }
    }
    
    private TrTables trSetupTable(final Ruby runtime, final boolean[] table, TrTables tables, final boolean init, final Encoding enc) {
        final TR tr = new TR(this.value);
        boolean cflag = false;
        if (this.value.getRealSize() > 1) {
            if (enc.isAsciiCompatible()) {
                if ((this.value.getUnsafeBytes()[this.value.getBegin()] & 0xFF) == 0x5E) {
                    cflag = true;
                    final TR tr2 = tr;
                    ++tr2.p;
                }
            }
            else {
                final int l = StringSupport.preciseLength(enc, tr.buf, tr.p, tr.pend);
                if (enc.mbcToCode(tr.buf, tr.p, tr.pend) == 94) {
                    cflag = true;
                    final TR tr3 = tr;
                    tr3.p += l;
                }
            }
        }
        if (init) {
            for (int i = 0; i < 256; ++i) {
                table[i] = true;
            }
        }
        final boolean[] buf = new boolean[256];
        for (int j = 0; j < 256; ++j) {
            buf[j] = cflag;
        }
        IntHash<IRubyObject> hash = null;
        IntHash<IRubyObject> phash = null;
        int c;
        while ((c = this.trNext(tr, runtime, enc)) >= 0) {
            if (c < 256) {
                buf[c & 0xFF] = !cflag;
            }
            else {
                if (hash == null) {
                    hash = new IntHash<IRubyObject>();
                    if (tables == null) {
                        tables = new TrTables();
                    }
                    if (cflag) {
                        phash = tables.noDel;
                        tables.noDel = hash;
                    }
                    else {
                        phash = tables.del;
                        tables.del = hash;
                    }
                }
                if (phash != null && phash.get(c) == null) {
                    continue;
                }
                hash.put(c, RubyString.NEVER);
            }
        }
        for (int k = 0; k < 256; ++k) {
            table[k] = (table[k] && buf[k]);
        }
        return tables;
    }
    
    private boolean trFind(final int c, final boolean[] table, final TrTables tables) {
        return (c < 256) ? table[c] : (tables != null && tables.del != null && tables.del.get(c) != null && (tables.noDel == null || tables.noDel.get(c) == null));
    }
    
    private IRubyObject trTrans(final ThreadContext context, final IRubyObject src, final IRubyObject repl, final boolean sflag) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final ByteList replList = repl.convertToString().value;
        if (replList.getRealSize() == 0) {
            return this.delete_bang(context, src);
        }
        final ByteList srcList = src.convertToString().value;
        final TR trSrc = new TR(srcList);
        boolean cflag = false;
        if (srcList.getRealSize() >= 2 && srcList.getUnsafeBytes()[srcList.getBegin()] == 94) {
            cflag = true;
            final TR tr = trSrc;
            ++tr.p;
        }
        final int[] trans = new int[256];
        final TR trRepl = new TR(replList);
        if (cflag) {
            for (int i = 0; i < 256; ++i) {
                trans[i] = 1;
            }
            int c;
            while ((c = this.trNext(trSrc)) >= 0) {
                trans[c & 0xFF] = -1;
            }
            while ((c = this.trNext(trRepl)) >= 0) {}
            for (int i = 0; i < 256; ++i) {
                if (trans[i] >= 0) {
                    trans[i] = trRepl.now;
                }
            }
        }
        else {
            for (int i = 0; i < 256; ++i) {
                trans[i] = -1;
            }
            int c;
            while ((c = this.trNext(trSrc)) >= 0) {
                int r = this.trNext(trRepl);
                if (r == -1) {
                    r = trRepl.now;
                }
                trans[c & 0xFF] = r;
            }
        }
        this.modify();
        int s = this.value.getBegin();
        final int send = s + this.value.getRealSize();
        final byte[] sbytes = this.value.getUnsafeBytes();
        boolean modify = false;
        if (sflag) {
            int t = s;
            int last = -1;
            while (s < send) {
                final int c2 = sbytes[s++];
                final int c;
                if ((c = trans[c2 & 0xFF]) >= 0) {
                    if (last == c) {
                        continue;
                    }
                    last = c;
                    sbytes[t++] = (byte)(c & 0xFF);
                    modify = true;
                }
                else {
                    last = -1;
                    sbytes[t++] = (byte)c2;
                }
            }
            if (this.value.getRealSize() > t - this.value.getBegin()) {
                this.value.setRealSize(t - this.value.getBegin());
                modify = true;
            }
        }
        else {
            while (s < send) {
                final int c;
                if ((c = trans[sbytes[s] & 0xFF]) >= 0) {
                    sbytes[s] = (byte)(c & 0xFF);
                    modify = true;
                }
                ++s;
            }
        }
        return modify ? this : runtime.getNil();
    }
    
    private IRubyObject trTrans19(final ThreadContext context, final IRubyObject src, final IRubyObject repl, final boolean sflag) {
        final Ruby runtime = context.getRuntime();
        if (this.value.getRealSize() == 0) {
            return runtime.getNil();
        }
        final RubyString replStr = repl.convertToString();
        final ByteList replList = replStr.value;
        if (replList.getRealSize() == 0) {
            return this.delete_bang19(context, src);
        }
        final RubyString srcStr = src.convertToString();
        final ByteList srcList = srcStr.value;
        final Encoding e1 = this.checkEncoding(srcStr);
        final Encoding e2 = this.checkEncoding(replStr);
        final Encoding enc = (e1 == e2) ? e1 : srcStr.checkEncoding(replStr);
        int cr = this.getCodeRange();
        final TR trSrc = new TR(srcList);
        boolean cflag = false;
        if (this.value.getRealSize() > 1) {
            if (enc.isAsciiCompatible()) {
                if (trSrc.buf.length > 0 && (trSrc.buf[trSrc.p] & 0xFF) == 0x5E && trSrc.p + 1 < trSrc.pend) {
                    cflag = true;
                    final TR tr = trSrc;
                    ++tr.p;
                }
            }
            else {
                final int cl = StringSupport.preciseLength(enc, trSrc.buf, trSrc.p, trSrc.pend);
                if (enc.mbcToCode(trSrc.buf, trSrc.p, trSrc.pend) == 94 && trSrc.p + cl < trSrc.pend) {
                    cflag = true;
                    final TR tr2 = trSrc;
                    tr2.p += cl;
                }
            }
        }
        boolean singlebyte = true;
        final int[] trans = new int[256];
        IntHash<Integer> hash = null;
        final TR trRepl = new TR(replList);
        if (cflag) {
            for (int i = 0; i < 256; ++i) {
                trans[i] = 1;
            }
            int c;
            while ((c = this.trNext(trSrc, runtime, enc)) >= 0) {
                if (c < 256) {
                    trans[c & 0xFF] = -1;
                }
                else {
                    if (hash == null) {
                        hash = new IntHash<Integer>();
                    }
                    hash.put(c, 1);
                }
            }
            while ((c = this.trNext(trRepl, runtime, enc)) >= 0) {}
            final int last = trRepl.now;
            for (int j = 0; j < 256; ++j) {
                if (trans[j] >= 0) {
                    trans[j] = last;
                }
            }
        }
        else {
            for (int i = 0; i < 256; ++i) {
                trans[i] = -1;
            }
            int c;
            while ((c = this.trNext(trSrc, runtime, enc)) >= 0) {
                int r = this.trNext(trRepl, runtime, enc);
                if (r == -1) {
                    r = trRepl.now;
                }
                if (c < 256) {
                    if ((trans[c & 0xFF] = r) <= 255) {
                        continue;
                    }
                    singlebyte = false;
                }
                else {
                    if (hash == null) {
                        hash = new IntHash<Integer>();
                    }
                    hash.put(c, r);
                }
            }
        }
        if (cr == 64) {
            cr = 32;
        }
        this.modifyAndKeepCodeRange();
        int s = this.value.getBegin();
        final int send = s + this.value.getRealSize();
        final byte[] sbytes = this.value.getUnsafeBytes();
        int max = this.value.getRealSize();
        boolean modify = false;
        final int last2 = -1;
        if (sflag) {
            int save = -1;
            byte[] buf = new byte[max];
            int t = 0;
            while (s < send) {
                boolean mayModify = false;
                final int c2;
                int c = c2 = StringSupport.codePoint(runtime, e1, sbytes, s, send);
                final int clen = StringSupport.codeLength(runtime, e1, c);
                int tlen = (enc == e1) ? clen : StringSupport.codeLength(runtime, enc, c);
                s += clen;
                c = this.trCode(c, trans, hash, cflag, last2);
                if (c != -1) {
                    if (save == c) {
                        if (cr == 32 && !Encoding.isAscii(c)) {
                            cr = 64;
                            continue;
                        }
                        continue;
                    }
                    else {
                        save = c;
                        tlen = StringSupport.codeLength(runtime, enc, c);
                        modify = true;
                    }
                }
                else {
                    save = -1;
                    c = c2;
                    if (enc != e1) {
                        mayModify = true;
                    }
                }
                while (t + tlen >= max) {
                    max <<= 1;
                    final byte[] tbuf = new byte[max];
                    System.arraycopy(buf, 0, tbuf, 0, buf.length);
                    buf = tbuf;
                }
                enc.codeToMbc(c, buf, t);
                Label_0922: {
                    if (mayModify) {
                        if (tlen == 1) {
                            if (sbytes[s] == buf[t]) {
                                break Label_0922;
                            }
                        }
                        else if (ByteList.memcmp(sbytes, s, buf, t, tlen) == 0) {
                            break Label_0922;
                        }
                        modify = true;
                    }
                }
                if (cr == 32 && !Encoding.isAscii(c)) {
                    cr = 64;
                }
                t += tlen;
            }
            this.value.setUnsafeBytes(buf);
            this.value.setRealSize(t);
        }
        else if (enc.isSingleByte() || (singlebyte && hash == null)) {
            while (s < send) {
                int c = sbytes[s] & 0xFF;
                if (trans[c] != -1) {
                    if (!cflag) {
                        c = trans[c];
                        sbytes[s] = (byte)c;
                    }
                    else {
                        sbytes[s] = (byte)last2;
                    }
                    modify = true;
                }
                if (cr == 32 && !Encoding.isAscii(c)) {
                    cr = 64;
                }
                ++s;
            }
        }
        else {
            max += max >> 1;
            byte[] buf2 = new byte[max];
            int clen;
            int tlen;
            int t2;
            for (t2 = 0; s < send; s += clen, t2 += tlen) {
                boolean mayModify2 = false;
                final int c2;
                int c = c2 = StringSupport.codePoint(runtime, e1, sbytes, s, send);
                clen = StringSupport.codeLength(runtime, e1, c);
                tlen = ((enc == e1) ? clen : StringSupport.codeLength(runtime, enc, c));
                c = this.trCode(c, trans, hash, cflag, last2);
                if (c != -1) {
                    tlen = StringSupport.codeLength(runtime, enc, c);
                    modify = true;
                }
                else {
                    c = c2;
                    if (enc != e1) {
                        mayModify2 = true;
                    }
                }
                while (t2 + tlen >= max) {
                    max <<= 1;
                    final byte[] tbuf2 = new byte[max];
                    System.arraycopy(buf2, 0, tbuf2, 0, buf2.length);
                    buf2 = tbuf2;
                }
                enc.codeToMbc(c, buf2, t2);
                Label_1308: {
                    if (mayModify2) {
                        if (tlen == 1) {
                            if (sbytes[s] == buf2[t2]) {
                                break Label_1308;
                            }
                        }
                        else if (ByteList.memcmp(sbytes, s, buf2, t2, tlen) == 0) {
                            break Label_1308;
                        }
                        modify = true;
                    }
                }
                if (cr == 32 && !Encoding.isAscii(c)) {
                    cr = 64;
                }
            }
            this.value.setUnsafeBytes(buf2);
            this.value.setRealSize(t2);
        }
        if (modify) {
            if (cr != 96) {
                this.setCodeRange(cr);
            }
            this.associateEncoding(enc);
            return this;
        }
        return runtime.getNil();
    }
    
    private int trCode(final int c, final int[] trans, final IntHash<Integer> hash, final boolean cflag, final int last) {
        if (c < 256) {
            return trans[c];
        }
        if (hash == null) {
            return -1;
        }
        final Integer tmp = hash.get(c);
        if (tmp == null) {
            return cflag ? last : -1;
        }
        return cflag ? -1 : tmp;
    }
    
    private int trNext(final TR t) {
        final byte[] buf = t.buf;
        while (!t.gen) {
            if (t.p == t.pend) {
                return -1;
            }
            if (t.p < t.pend - 1 && buf[t.p] == 92) {
                ++t.p;
            }
            t.now = (buf[t.p++] & 0xFF);
            if (t.p < t.pend - 1 && buf[t.p] == 45) {
                ++t.p;
                if (t.p < t.pend) {
                    if (t.now > (buf[t.p] & 0xFF)) {
                        ++t.p;
                        continue;
                    }
                    t.gen = true;
                    t.max = (buf[t.p++] & 0xFF);
                }
            }
            return t.now;
        }
        if (++t.now < t.max) {
            return t.now;
        }
        t.gen = false;
        return t.max;
    }
    
    private int trNext(final TR t, final Ruby runtime, final Encoding enc) {
        final byte[] buf = t.buf;
        if (!t.gen) {
            if (t.p == t.pend) {
                return -1;
            }
            if (t.p < t.pend - 1 && buf[t.p] == 92) {
                ++t.p;
            }
            t.now = StringSupport.codePoint(runtime, enc, buf, t.p, t.pend);
            t.p += StringSupport.codeLength(runtime, enc, t.now);
            if (t.p < t.pend - 1 && buf[t.p] == 45) {
                ++t.p;
                if (t.p < t.pend) {
                    final int c = StringSupport.codePoint(runtime, enc, buf, t.p, t.pend);
                    t.p += StringSupport.codeLength(runtime, enc, c);
                    if (t.now > c) {
                        if (t.now < 128 && c < 128) {
                            throw runtime.newArgumentError("invalid range \"" + (char)t.now + "-" + (char)c + "\" in string transliteration");
                        }
                        throw runtime.newArgumentError("invalid range in string transliteration");
                    }
                    else {
                        t.gen = true;
                        t.max = c;
                    }
                }
            }
            return t.now;
        }
        else {
            if (++t.now < t.max) {
                return t.now;
            }
            t.gen = false;
            return t.max;
        }
    }
    
    @JRubyMethod(name = { "tr_s" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject tr_s(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        final RubyString str = this.strDup(context.getRuntime());
        str.trTrans(context, src, repl, true);
        return str;
    }
    
    @JRubyMethod(name = { "tr_s!" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject tr_s_bang(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        return this.trTrans(context, src, repl, true);
    }
    
    @JRubyMethod(name = { "tr_s" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject tr_s19(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        final RubyString str = this.strDup(context.getRuntime());
        str.trTrans19(context, src, repl, true);
        return str;
    }
    
    @JRubyMethod(name = { "tr_s!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject tr_s_bang19(final ThreadContext context, final IRubyObject src, final IRubyObject repl) {
        return this.trTrans19(context, src, repl, true);
    }
    
    public IRubyObject each_line(final ThreadContext context, final Block block) {
        return this.each_lineCommon(context, context.getRuntime().getGlobalVariables().get("$/"), block);
    }
    
    public IRubyObject each_line(final ThreadContext context, final IRubyObject arg, final Block block) {
        return this.each_lineCommon(context, arg, block);
    }
    
    public IRubyObject each_lineCommon(final ThreadContext context, final IRubyObject sep, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (sep.isNil()) {
            block.yield(context, this);
            return this;
        }
        final RubyString sepStr = sep.convertToString();
        final ByteList sepValue = sepStr.value;
        final int rslen = sepValue.getRealSize();
        byte newline;
        if (rslen == 0) {
            newline = 10;
        }
        else {
            newline = sepValue.getUnsafeBytes()[sepValue.getBegin() + rslen - 1];
        }
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final int ptr = p;
        int s = p;
        final int len = this.value.getRealSize();
        final byte[] bytes = this.value.getUnsafeBytes();
        for (p += rslen; p < end; ++p) {
            if (rslen == 0 && bytes[p] == 10) {
                if (++p == end) {
                    continue;
                }
                if (bytes[p] != 10) {
                    continue;
                }
                while (p < end && bytes[p] == 10) {
                    ++p;
                }
            }
            if (ptr < p && bytes[p - 1] == newline && (rslen <= 1 || ByteList.memcmp(sepValue.getUnsafeBytes(), sepValue.getBegin(), rslen, bytes, p - rslen, rslen) == 0)) {
                block.yield(context, this.makeShared(runtime, s - ptr, p - s).infectBy(this));
                this.modifyCheck(bytes, len);
                s = p;
            }
        }
        if (s != end) {
            if (p > end) {
                p = end;
            }
            block.yield(context, this.makeShared(runtime, s - ptr, p - s).infectBy(this));
        }
        return this;
    }
    
    @JRubyMethod(name = { "each" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject each18(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_line(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each");
    }
    
    @JRubyMethod(name = { "each" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject each18(final ThreadContext context, final IRubyObject arg, final Block block) {
        return block.isGiven() ? this.each_lineCommon(context, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each", arg);
    }
    
    @JRubyMethod(name = { "each_line" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject each_line18(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_line(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line");
    }
    
    @JRubyMethod(name = { "each_line" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject each_line18(final ThreadContext context, final IRubyObject arg, final Block block) {
        return block.isGiven() ? this.each_lineCommon(context, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line", arg);
    }
    
    @JRubyMethod(name = { "lines" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject lines18(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_line(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "lines");
    }
    
    @JRubyMethod(name = { "lines" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject lines18(final ThreadContext context, final IRubyObject arg, final Block block) {
        return block.isGiven() ? this.each_lineCommon(context, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "lines", arg);
    }
    
    @JRubyMethod(name = { "each_line" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject each_line19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_lineCommon19(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line");
    }
    
    @JRubyMethod(name = { "each_line" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject each_line19(final ThreadContext context, final IRubyObject arg, final Block block) {
        return block.isGiven() ? this.each_lineCommon19(context, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_line", arg);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject lines(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_lineCommon19(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "lines");
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject lines(final ThreadContext context, final IRubyObject arg, final Block block) {
        return block.isGiven() ? this.each_lineCommon19(context, arg, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "lines", arg);
    }
    
    private IRubyObject each_lineCommon19(final ThreadContext context, final Block block) {
        return this.each_lineCommon19(context, context.getRuntime().getGlobalVariables().get("$/"), block);
    }
    
    private IRubyObject each_lineCommon19(final ThreadContext context, final IRubyObject sep, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (sep.isNil()) {
            block.yield(context, this);
            return this;
        }
        if (!sep.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + sep.getMetaClass() + " into String");
        }
        final ByteList val = this.value.shallowDup();
        int s;
        int p = s = val.getBegin();
        final int len = val.getRealSize();
        final int end = p + len;
        final byte[] bytes = val.getUnsafeBytes();
        final RubyString sepStr = sep.convertToString();
        if (sepStr == runtime.getGlobalVariables().getDefaultSeparator()) {
            final Encoding enc = val.getEncoding();
            while (p < end) {
                if (bytes[p] == 10) {
                    final int p2 = enc.leftAdjustCharHead(bytes, s, p, end);
                    if (enc.isNewLine(bytes, p2, end)) {
                        p = p2 + StringSupport.length(enc, bytes, p2, end);
                        block.yield(context, this.makeShared19(runtime, val, s, p - s).infectBy(this));
                        s = p;
                        continue;
                    }
                }
                ++p;
            }
        }
        else {
            final Encoding enc = this.checkEncoding(sepStr);
            final ByteList sepValue = sepStr.value;
            final int rslen = sepValue.getRealSize();
            int newLine;
            if (rslen == 0) {
                newLine = 10;
            }
            else {
                newLine = StringSupport.codePoint(runtime, enc, sepValue.getUnsafeBytes(), sepValue.getBegin(), sepValue.getBegin() + sepValue.getRealSize());
            }
            while (p < end) {
                int c = StringSupport.codePoint(runtime, enc, bytes, p, end);
                final int n = StringSupport.codeLength(runtime, enc, c);
                if (rslen == 0 && c == newLine) {
                    p += n;
                    if (p < end && (c = StringSupport.codePoint(runtime, enc, bytes, p, end)) != newLine) {
                        continue;
                    }
                    while (p < end && StringSupport.codePoint(runtime, enc, bytes, p, end) == newLine) {
                        p += n;
                    }
                    p -= n;
                }
                if (c == newLine && (rslen <= 1 || ByteList.memcmp(sepValue.getUnsafeBytes(), sepValue.getBegin(), rslen, bytes, p, rslen) == 0)) {
                    block.yield(context, this.makeShared19(runtime, val, s, p - s + ((rslen != 0) ? rslen : n)).infectBy(this));
                    s = p + ((rslen != 0) ? rslen : n);
                }
                p += n;
            }
        }
        if (s != end) {
            block.yield(context, this.makeShared19(runtime, val, s, end - s).infectBy(this));
        }
        return this;
    }
    
    public RubyString each_byte(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        for (int i = 0; i < this.value.length(); ++i) {
            block.yield(context, runtime.newFixnum(this.value.get(i) & 0xFF));
        }
        return this;
    }
    
    @JRubyMethod(name = { "each_byte" })
    public IRubyObject each_byte19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_byte(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_byte");
    }
    
    @JRubyMethod
    public IRubyObject bytes(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_byte(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "bytes");
    }
    
    @JRubyMethod(name = { "each_char" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject each_char18(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_charCommon18(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_char");
    }
    
    @JRubyMethod(name = { "chars" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject chars18(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_charCommon18(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "chars");
    }
    
    private IRubyObject each_charCommon18(final ThreadContext context, final Block block) {
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final Ruby runtime = context.getRuntime();
        final Encoding enc = runtime.getKCode().getEncoding();
        final ByteList val = this.value.shallowDup();
        while (p < end) {
            final int n = StringSupport.length(enc, bytes, p, end);
            block.yield(context, this.makeShared19(runtime, val, p - val.getBegin(), n));
            p += n;
        }
        return this;
    }
    
    @JRubyMethod(name = { "each_char" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject each_char19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_charCommon19(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_char");
    }
    
    @JRubyMethod(name = { "chars" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject chars19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_charCommon19(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "chars");
    }
    
    private IRubyObject each_charCommon19(final ThreadContext context, final Block block) {
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final Encoding enc = this.value.getEncoding();
        final Ruby runtime = context.getRuntime();
        final ByteList val = this.value.shallowDup();
        while (p < end) {
            final int n = StringSupport.length(enc, bytes, p, end);
            block.yield(context, this.makeShared19(runtime, val, p - this.value.getBegin(), n));
            p += n;
        }
        return this;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject each_codepoint(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_codepoint");
        }
        return this.singleByteOptimizable() ? this.each_byte(context, block) : this.each_codepointCommon(context, block);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject codepoints(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "codepoints");
        }
        return this.singleByteOptimizable() ? this.each_byte(context, block) : this.each_codepointCommon(context, block);
    }
    
    private IRubyObject each_codepointCommon(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int end = p + this.value.getRealSize();
        final Encoding enc = this.value.getEncoding();
        while (p < end) {
            final int c = StringSupport.codePoint(runtime, enc, bytes, p, end);
            final int n = StringSupport.codeLength(runtime, enc, c);
            block.yield(context, runtime.newFixnum(c));
            p += n;
        }
        return this;
    }
    
    private RubySymbol to_sym() {
        final RubySymbol symbol = this.getRuntime().getSymbolTable().getSymbol(this.value);
        if (symbol.getBytes() == this.value) {
            this.shareLevel = 2;
        }
        return symbol;
    }
    
    @JRubyMethod(name = { "to_sym", "intern" }, compat = CompatVersion.RUBY1_8)
    public RubySymbol intern() {
        if (this.value.getRealSize() == 0) {
            throw this.getRuntime().newArgumentError("interning empty string");
        }
        for (int i = 0; i < this.value.getRealSize(); ++i) {
            if (this.value.getUnsafeBytes()[this.value.getBegin() + i] == 0) {
                throw this.getRuntime().newArgumentError("symbol string may not contain '\\0'");
            }
        }
        return this.to_sym();
    }
    
    @JRubyMethod(name = { "to_sym", "intern" }, compat = CompatVersion.RUBY1_9)
    public RubySymbol intern19() {
        return this.to_sym();
    }
    
    @JRubyMethod(name = { "ord" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject ord(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return RubyFixnum.newFixnum(runtime, StringSupport.codePoint(runtime, this.value.getEncoding(), this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getBegin() + this.value.getRealSize()));
    }
    
    @JRubyMethod(name = { "sum" })
    public IRubyObject sum(final ThreadContext context) {
        return this.sumCommon(context, 16L);
    }
    
    @JRubyMethod(name = { "sum" })
    public IRubyObject sum(final ThreadContext context, final IRubyObject arg) {
        return this.sumCommon(context, RubyNumeric.num2long(arg));
    }
    
    public IRubyObject sumCommon(final ThreadContext context, final long bits) {
        final Ruby runtime = context.getRuntime();
        final byte[] bytes = this.value.getUnsafeBytes();
        int p = this.value.getBegin();
        final int len = this.value.getRealSize();
        final int end = p + len;
        if (bits >= 64L) {
            final IRubyObject one = RubyFixnum.one(runtime);
            IRubyObject sum;
            for (sum = RubyFixnum.zero(runtime); p < end; sum = sum.callMethod(context, "+", RubyFixnum.newFixnum(runtime, bytes[p++] & 0xFF))) {
                this.modifyCheck(bytes, len);
            }
            if (bits != 0L) {
                final IRubyObject mod = one.callMethod(context, "<<", RubyFixnum.newFixnum(runtime, bits));
                sum = sum.callMethod(context, "&", mod.callMethod(context, "-", one));
            }
            return sum;
        }
        long sum2;
        for (sum2 = 0L; p < end; sum2 += (bytes[p++] & 0xFF)) {
            this.modifyCheck(bytes, len);
        }
        return RubyFixnum.newFixnum(runtime, (bits == 0L) ? sum2 : (sum2 & (1L << (int)bits) - 1L));
    }
    
    @JRubyMethod(name = { "to_c" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_c(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final DynamicScope scope = context.getCurrentScope();
        final IRubyObject backref = scope.getBackRef(runtime);
        if (backref instanceof RubyMatchData) {
            ((RubyMatchData)backref).use();
        }
        final IRubyObject s = RuntimeHelpers.invoke(context, this, "gsub", RubyRegexp.newDummyRegexp(runtime, Numeric.ComplexPatterns.underscores_pat), runtime.newString(new ByteList(new byte[] { 95 })));
        final RubyArray a = RubyComplex.str_to_c_internal(context, s);
        scope.setBackRef(backref);
        if (!a.eltInternal(0).isNil()) {
            return a.eltInternal(0);
        }
        return RubyComplex.newComplexCanonicalize(context, RubyFixnum.zero(runtime));
    }
    
    @JRubyMethod(name = { "to_r" }, reads = { FrameField.BACKREF }, writes = { FrameField.BACKREF }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_r(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final DynamicScope scope = context.getCurrentScope();
        final IRubyObject backref = scope.getBackRef(runtime);
        if (backref instanceof RubyMatchData) {
            ((RubyMatchData)backref).use();
        }
        final IRubyObject s = RuntimeHelpers.invoke(context, this, "gsub", RubyRegexp.newDummyRegexp(runtime, Numeric.ComplexPatterns.underscores_pat), runtime.newString(new ByteList(new byte[] { 95 })));
        final RubyArray a = RubyRational.str_to_r_internal(context, s);
        scope.setBackRef(backref);
        if (!a.eltInternal(0).isNil()) {
            return a.eltInternal(0);
        }
        return RubyRational.newRationalCanonicalize(context, RubyFixnum.zero(runtime));
    }
    
    public static RubyString unmarshalFrom(final UnmarshalStream input) throws IOException {
        final RubyString result = newString(input.getRuntime(), input.unmarshalString());
        input.registerLinkTarget(result);
        return result;
    }
    
    @JRubyMethod(name = { "unpack" })
    public RubyArray unpack(final IRubyObject obj) {
        return Pack.unpack(this.getRuntime(), this.value, stringValue(obj).value);
    }
    
    public void empty() {
        this.value = ByteList.EMPTY_BYTELIST;
        this.shareLevel = 2;
    }
    
    @JRubyMethod(name = { "encoding" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encoding(final ThreadContext context) {
        return context.getRuntime().getEncodingService().getEncoding(this.value.getEncoding());
    }
    
    @JRubyMethod(name = { "encode!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode_bang(final ThreadContext context) {
        this.modify19();
        final Ruby runtime = context.getRuntime();
        final Encoding defaultInternal = runtime.getDefaultInternalEncoding();
        if (defaultInternal == null) {
            return this.dup();
        }
        this.value = transcode(context, this.value, null, defaultInternal, runtime.getNil());
        return this;
    }
    
    @JRubyMethod(name = { "encode!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode_bang(final ThreadContext context, final IRubyObject enc) {
        final Ruby runtime = context.getRuntime();
        this.modify19();
        this.value = transcode(context, this.value, null, getEncoding(runtime, enc), runtime.getNil());
        return this;
    }
    
    @JRubyMethod(name = { "encode!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode_bang(final ThreadContext context, final IRubyObject toEncoding, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        this.modify19();
        Encoding forceEncoding;
        IRubyObject options;
        if (arg instanceof RubyHash) {
            forceEncoding = null;
            options = arg;
        }
        else {
            forceEncoding = getEncoding(runtime, arg);
            options = runtime.getNil();
        }
        this.value = transcode(context, this.value, forceEncoding, getEncoding(runtime, toEncoding), options);
        return this;
    }
    
    @JRubyMethod(name = { "encode!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode_bang(final ThreadContext context, final IRubyObject toEncoding, final IRubyObject forceEncoding, final IRubyObject opts) {
        final Ruby runtime = context.getRuntime();
        this.modify19();
        this.value = transcode(context, this.value, getEncoding(runtime, forceEncoding), getEncoding(runtime, toEncoding), opts);
        return this;
    }
    
    @JRubyMethod(name = { "encode" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final Encoding defaultInternal = runtime.getDefaultInternalEncoding();
        if (defaultInternal == null) {
            return this.dup();
        }
        return runtime.newString(transcode(context, this.value, null, defaultInternal, runtime.getNil()));
    }
    
    @JRubyMethod(name = { "encode" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode(final ThreadContext context, final IRubyObject enc) {
        final Ruby runtime = context.getRuntime();
        return runtime.newString(transcode(context, this.value, null, getEncoding(runtime, enc), runtime.getNil()));
    }
    
    @JRubyMethod(name = { "encode" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode(final ThreadContext context, final IRubyObject toEncoding, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        Encoding forceEncoding;
        IRubyObject options;
        if (arg instanceof RubyHash) {
            forceEncoding = null;
            options = arg;
        }
        else {
            forceEncoding = getEncoding(runtime, arg);
            options = runtime.getNil();
        }
        return runtime.newString(transcode(context, this.value, forceEncoding, getEncoding(runtime, toEncoding), options));
    }
    
    @JRubyMethod(name = { "encode" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encode(final ThreadContext context, final IRubyObject toEncoding, final IRubyObject forcedEncoding, final IRubyObject opts) {
        final Ruby runtime = context.getRuntime();
        return runtime.newString(transcode(context, this.value, getEncoding(runtime, forcedEncoding), getEncoding(runtime, toEncoding), opts));
    }
    
    private static Charset lookupCharsetFor(final Ruby runtime, final Encoding encoding, final String fromName, final String toName) {
        Charset from = encoding.getCharset();
        if (from != null) {
            return from;
        }
        try {
            from = Charset.forName(encoding.toString());
        }
        catch (Exception ex) {}
        if (from == null) {
            throw runtime.newConverterNotFoundError("code converter not found (" + fromName + " to " + toName + ")");
        }
        return from;
    }
    
    public static ByteList transcode(final ThreadContext context, final ByteList value, final Encoding forceEncoding, final Encoding toEncoding, final IRubyObject opts) {
        final Ruby runtime = context.getRuntime();
        final Encoding fromEncoding = (forceEncoding != null) ? forceEncoding : value.getEncoding();
        final Charset from = lookupCharsetFor(runtime, fromEncoding, fromEncoding.toString(), toEncoding.toString());
        final Charset to = lookupCharsetFor(runtime, toEncoding, fromEncoding.toString(), toEncoding.toString());
        final CharsetEncoder encoder = getCharsetEncoder(context, runtime, to, opts);
        final ByteBuffer fromBytes = ByteBuffer.wrap(value.getUnsafeBytes(), value.begin(), value.length());
        try {
            final ByteBuffer toBytes = encoder.encode(from.decode(fromBytes));
            return new ByteList(toBytes.array(), toBytes.arrayOffset(), toBytes.limit() - toBytes.arrayOffset(), toEncoding, false);
        }
        catch (CharacterCodingException e) {
            throw runtime.newInvalidByteSequenceError("");
        }
    }
    
    private static CharsetEncoder getCharsetEncoder(final ThreadContext context, final Ruby runtime, final Charset charset, final IRubyObject opts) {
        final CharsetEncoder encoder = charset.newEncoder();
        if (!opts.isNil()) {
            final RubyHash hash = (RubyHash)opts;
            CodingErrorAction action = CodingErrorAction.REPLACE;
            final IRubyObject replace = hash.fastARef(runtime.newSymbol("replace"));
            if (replace != null && !replace.isNil()) {
                final String replaceWith = replace.toString();
                if (replaceWith.length() > 0) {
                    encoder.replaceWith(replaceWith.getBytes());
                }
                else {
                    action = CodingErrorAction.IGNORE;
                }
            }
            final IRubyObject invalid = hash.fastARef(runtime.newSymbol("invalid"));
            if (invalid != null && invalid.op_equal(context, runtime.newSymbol("replace")).isTrue()) {
                encoder.onMalformedInput(action);
            }
            final IRubyObject undef = hash.fastARef(runtime.newSymbol("undef"));
            if (undef != null && undef.op_equal(context, runtime.newSymbol("replace")).isTrue()) {
                encoder.onUnmappableCharacter(action);
            }
        }
        return encoder;
    }
    
    private static Encoding getEncoding(final Ruby runtime, final IRubyObject toEnc) {
        try {
            return runtime.getEncodingService().getEncodingFromObject(toEnc);
        }
        catch (Exception e) {
            throw runtime.newConverterNotFoundError("code converter not found (" + toEnc.toString() + ")");
        }
    }
    
    @JRubyMethod(name = { "force_encoding" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject force_encoding(final ThreadContext context, final IRubyObject enc) {
        this.modify19();
        final Encoding encoding = context.runtime.getEncodingService().getEncodingFromObject(enc);
        this.associateEncoding(encoding);
        return this;
    }
    
    @JRubyMethod(name = { "valid_encoding?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject valid_encoding_p(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return (this.scanForCodeRange() == 96) ? runtime.getFalse() : runtime.getTrue();
    }
    
    @JRubyMethod(name = { "ascii_only?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject ascii_only_p(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return (this.scanForCodeRange() == 32) ? runtime.getTrue() : runtime.getFalse();
    }
    
    public void setValue(final CharSequence value) {
        this.view(ByteList.plain(value));
    }
    
    public void setValue(final ByteList value) {
        this.view(value);
    }
    
    public CharSequence getValue() {
        return this.toString();
    }
    
    public byte[] getBytes() {
        return this.value.bytes();
    }
    
    public ByteList getByteList() {
        return this.value;
    }
    
    public String getUnicodeValue() {
        return RubyEncoding.decodeUTF8(this.value.getUnsafeBytes(), this.value.getBegin(), this.value.getRealSize());
    }
    
    public Object toJava(final Class target) {
        if (target.isAssignableFrom(String.class)) {
            return this.decodeString();
        }
        if (target.isAssignableFrom(ByteList.class)) {
            return this.value;
        }
        return super.toJava(target);
    }
    
    @Deprecated
    public IRubyObject initialize(final IRubyObject[] args, final Block unusedBlock) {
        switch (args.length) {
            case 0: {
                return this;
            }
            case 1: {
                return this.initialize(args[0]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject sub(final ThreadContext context, final IRubyObject[] args, final Block block) {
        final RubyString str = this.strDup(context.getRuntime());
        str.sub_bang(context, args, block);
        return str;
    }
    
    @Deprecated
    public IRubyObject sub_bang(final ThreadContext context, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 1: {
                return this.sub_bang(context, args[0], block);
            }
            case 2: {
                return this.sub_bang(context, args[0], args[1], block);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject gsub(final ThreadContext context, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 1: {
                return this.gsub(context, args[0], block);
            }
            case 2: {
                return this.gsub(context, args[0], args[1], block);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject gsub_bang(final ThreadContext context, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 1: {
                return this.gsub_bang(context, args[0], block);
            }
            case 2: {
                return this.gsub_bang(context, args[0], args[1], block);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject index(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.index(context, args[0]);
            }
            case 2: {
                return this.index(context, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject rindex(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.rindex(context, args[0]);
            }
            case 2: {
                return this.rindex(context, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.op_aref(context, args[0]);
            }
            case 2: {
                return this.op_aref(context, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 2: {
                return this.op_aset(context, args[0], args[1]);
            }
            case 3: {
                return this.op_aset(context, args[0], args[1], args[2]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 2, 3);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject slice_bang(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.slice_bang(context, args[0]);
            }
            case 2: {
                return this.slice_bang(context, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject to_i(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.to_i();
            }
            case 1: {
                return this.to_i(args[0]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    @Deprecated
    public RubyArray split(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.split(context);
            }
            case 1: {
                return this.split(context, args[0]);
            }
            case 2: {
                return this.split(context, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 0, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject ljust(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.ljust(args[0]);
            }
            case 2: {
                return this.ljust(args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject rjust(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.rjust(args[0]);
            }
            case 2: {
                return this.rjust(args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject center(final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return this.center(args[0]);
            }
            case 2: {
                return this.center(args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 1, 2);
                return null;
            }
        }
    }
    
    @Deprecated
    public RubyString chomp(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.chomp(this.getRuntime().getCurrentContext());
            }
            case 1: {
                return this.chomp(this.getRuntime().getCurrentContext(), args[0]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    @Deprecated
    public IRubyObject chomp_bang(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.chomp_bang(this.getRuntime().getCurrentContext());
            }
            case 1: {
                return this.chomp_bang(this.getRuntime().getCurrentContext(), args[0]);
            }
            default: {
                Arity.raiseArgumentError(this.getRuntime(), args.length, 0, 1);
                return null;
            }
        }
    }
    
    static {
        ASCII = ASCIIEncoding.INSTANCE;
        UTF8 = UTF8Encoding.INSTANCE;
        EMPTY_BYTE_ARRAY = new byte[0];
        RubyString.STRING_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return RubyString.newEmptyString(runtime, klass);
            }
        };
        RubyString.EMPTY_BYTELISTS = new EmptyByteListHolder[4];
        SPACE_BYTELIST = new ByteList(ByteList.plain(" "));
    }
    
    private static final class EmptyByteListHolder
    {
        final ByteList bytes;
        final int cr;
        
        EmptyByteListHolder(final Encoding enc) {
            this.bytes = new ByteList(ByteList.NULL_ARRAY, enc);
            this.cr = (this.bytes.getEncoding().isAsciiCompatible() ? 32 : 64);
        }
    }
    
    private enum NeighborChar
    {
        NOT_CHAR, 
        FOUND, 
        WRAPPED;
    }
    
    private static final class TR
    {
        int p;
        int pend;
        int now;
        int max;
        boolean gen;
        byte[] buf;
        
        TR(final ByteList bytes) {
            this.p = bytes.getBegin();
            this.pend = bytes.getRealSize() + this.p;
            this.buf = bytes.getUnsafeBytes();
            final boolean b = false;
            this.max = (b ? 1 : 0);
            this.now = (b ? 1 : 0);
            this.gen = false;
        }
    }
    
    private static final class TrTables
    {
        private IntHash<IRubyObject> del;
        private IntHash<IRubyObject> noDel;
    }
}
