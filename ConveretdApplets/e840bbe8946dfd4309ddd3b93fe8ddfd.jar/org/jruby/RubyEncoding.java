// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jcodings.EncodingDB;
import org.jcodings.util.Hash;
import org.jcodings.util.CaseInsensitiveBytesHash;
import org.jruby.runtime.encoding.EncodingService;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import java.nio.ByteBuffer;
import org.jcodings.specific.ASCIIEncoding;
import org.jcodings.specific.USASCIIEncoding;
import org.jruby.runtime.encoding.EncodingCapable;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jcodings.Encoding;
import org.jruby.util.ByteList;
import java.nio.charset.Charset;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Encoding" })
public class RubyEncoding extends RubyObject
{
    public static final Charset UTF8;
    public static final ByteList LOCALE;
    public static final ByteList EXTERNAL;
    private Encoding encoding;
    private final ByteList name;
    private final boolean isDummy;
    
    public static RubyClass createEncodingClass(final Ruby runtime) {
        final RubyClass encodingc = runtime.defineClass("Encoding", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setEncoding(encodingc);
        encodingc.index = 22;
        encodingc.setReifiedClass(RubyEncoding.class);
        encodingc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyEncoding;
            }
        };
        encodingc.getSingletonClass().undefineMethod("allocate");
        encodingc.defineAnnotatedMethods(RubyEncoding.class);
        return encodingc;
    }
    
    private RubyEncoding(final Ruby runtime, final byte[] name, final int p, final int end, final boolean isDummy) {
        super(runtime, runtime.getEncoding());
        this.name = new ByteList(name, p, end);
        this.isDummy = isDummy;
    }
    
    private RubyEncoding(final Ruby runtime, final byte[] name, final boolean isDummy) {
        this(runtime, name, 0, name.length, isDummy);
    }
    
    private RubyEncoding(final Ruby runtime, final Encoding encoding) {
        super(runtime, runtime.getEncoding());
        this.name = new ByteList(encoding.getName());
        this.isDummy = false;
        this.encoding = encoding;
    }
    
    public static RubyEncoding newEncoding(final Ruby runtime, final byte[] name, final int p, final int end, final boolean isDummy) {
        return new RubyEncoding(runtime, name, p, end, isDummy);
    }
    
    public static RubyEncoding newEncoding(final Ruby runtime, final byte[] name, final boolean isDummy) {
        return new RubyEncoding(runtime, name, isDummy);
    }
    
    public static RubyEncoding newEncoding(final Ruby runtime, final Encoding encoding) {
        return new RubyEncoding(runtime, encoding);
    }
    
    public final Encoding getEncoding() {
        if (this.encoding == null) {
            this.encoding = this.getRuntime().getEncodingService().loadEncoding(this.name);
        }
        return this.encoding;
    }
    
    public static Encoding areCompatible(IRubyObject obj1, final IRubyObject obj2) {
        if (obj1 instanceof EncodingCapable && obj2 instanceof EncodingCapable) {
            Encoding enc1 = ((EncodingCapable)obj1).getEncoding();
            Encoding enc2 = ((EncodingCapable)obj2).getEncoding();
            if (enc1 == enc2) {
                return enc1;
            }
            if (obj2 instanceof RubyString && ((RubyString)obj2).getByteList().getRealSize() == 0) {
                return enc1;
            }
            if (obj1 instanceof RubyString && ((RubyString)obj1).getByteList().getRealSize() == 0) {
                return enc2;
            }
            if (!enc1.isAsciiCompatible() || !enc2.isAsciiCompatible()) {
                return null;
            }
            if (!(obj2 instanceof RubyString) && enc2 instanceof USASCIIEncoding) {
                return enc1;
            }
            if (!(obj1 instanceof RubyString) && enc1 instanceof USASCIIEncoding) {
                return enc2;
            }
            if (!(obj1 instanceof RubyString)) {
                final IRubyObject objTmp = obj1;
                obj1 = obj2;
                obj1 = objTmp;
                final Encoding encTmp = enc1;
                enc1 = enc2;
                enc2 = encTmp;
            }
            if (obj1 instanceof RubyString) {
                final int cr1 = ((RubyString)obj1).scanForCodeRange();
                if (obj2 instanceof RubyString) {
                    final int cr2 = ((RubyString)obj2).scanForCodeRange();
                    return areCompatible(enc1, cr1, enc2, cr2);
                }
                if (cr1 == 32) {
                    return enc2;
                }
            }
        }
        return null;
    }
    
    static Encoding areCompatible(final Encoding enc1, final int cr1, final Encoding enc2, final int cr2) {
        if (cr1 != cr2) {
            if (cr1 == 32) {
                return enc2;
            }
            if (cr2 == 32) {
                return enc1;
            }
        }
        if (cr2 == 32) {
            if (enc1 instanceof ASCIIEncoding) {
                return enc2;
            }
            return enc1;
        }
        else {
            if (cr1 == 32) {
                return enc2;
            }
            return null;
        }
    }
    
    public static byte[] encodeUTF8(final CharSequence cs) {
        return encode(cs, RubyEncoding.UTF8);
    }
    
    public static byte[] encodeUTF8(final String str) {
        return encode(str, RubyEncoding.UTF8);
    }
    
    public static byte[] encode(final CharSequence cs, final Charset charset) {
        final ByteBuffer buffer = charset.encode(cs.toString());
        final byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }
    
    public static byte[] encode(final String str, final Charset charset) {
        final ByteBuffer buffer = charset.encode(str);
        final byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }
    
    public static String decodeUTF8(final byte[] bytes, final int start, final int length) {
        return decode(bytes, start, length, RubyEncoding.UTF8);
    }
    
    public static String decodeUTF8(final byte[] bytes) {
        return decode(bytes, RubyEncoding.UTF8);
    }
    
    public static String decode(final byte[] bytes, final int start, final int length, final Charset charset) {
        return charset.decode(ByteBuffer.wrap(bytes, start, length)).toString();
    }
    
    public static String decode(final byte[] bytes, final Charset charset) {
        return charset.decode(ByteBuffer.wrap(bytes)).toString();
    }
    
    @JRubyMethod(name = { "list" }, meta = true)
    public static IRubyObject list(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        return RubyArray.newArrayNoCopy(runtime, runtime.getEncodingService().getEncodingList(), 0);
    }
    
    @JRubyMethod(name = { "locale_charmap" }, meta = true)
    public static IRubyObject locale_charmap(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final EncodingService service = runtime.getEncodingService();
        final ByteList name = new ByteList(service.getLocaleEncoding().getName());
        return RubyString.newUsAsciiStringNoCopy(runtime, name);
    }
    
    @JRubyMethod(name = { "name_list" }, meta = true)
    public static IRubyObject name_list(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final EncodingService service = runtime.getEncodingService();
        final RubyArray result = runtime.newArray(service.getEncodings().size() + service.getAliases().size());
        Hash.HashEntryIterator i = service.getEncodings().entryIterator();
        while (i.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)i.next();
            result.append(RubyString.newUsAsciiStringShared(runtime, e.bytes, e.p, e.end - e.p).freeze(context));
        }
        i = service.getAliases().entryIterator();
        while (i.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)i.next();
            result.append(RubyString.newUsAsciiStringShared(runtime, e.bytes, e.p, e.end - e.p).freeze(context));
        }
        result.append(runtime.newString(RubyEncoding.EXTERNAL));
        result.append(runtime.newString(RubyEncoding.LOCALE));
        return result;
    }
    
    @JRubyMethod(name = { "aliases" }, meta = true)
    public static IRubyObject aliases(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        final EncodingService service = runtime.getEncodingService();
        final IRubyObject[] list = service.getEncodingList();
        final Hash.HashEntryIterator i = service.getAliases().entryIterator();
        final RubyHash result = RubyHash.newHash(runtime);
        while (i.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)i.next();
            final IRubyObject alias = RubyString.newUsAsciiStringShared(runtime, e.bytes, e.p, e.end - e.p).freeze(context);
            final IRubyObject name = RubyString.newUsAsciiStringShared(runtime, ((RubyEncoding)list[e.value.getIndex()]).name).freeze(context);
            result.fastASet(alias, name);
        }
        result.fastASet(runtime.newString(RubyEncoding.EXTERNAL), runtime.newString(new ByteList(runtime.getDefaultExternalEncoding().getName())));
        result.fastASet(runtime.newString(RubyEncoding.LOCALE), runtime.newString(new ByteList(service.getLocaleEncoding().getName())));
        return result;
    }
    
    @JRubyMethod(name = { "find" }, meta = true)
    public static IRubyObject find(final ThreadContext context, final IRubyObject recv, final IRubyObject str) {
        final Ruby runtime = context.getRuntime();
        return runtime.getEncodingService().rubyEncodingFromObject(str);
    }
    
    @JRubyMethod(name = { "_dump" })
    public IRubyObject _dump(final ThreadContext context, final IRubyObject arg) {
        return this.to_s(context);
    }
    
    @JRubyMethod(name = { "_load" }, meta = true)
    public static IRubyObject _load(final ThreadContext context, final IRubyObject recv, final IRubyObject str) {
        return find(context, recv, str);
    }
    
    @JRubyMethod(name = { "ascii_compatible?" })
    public IRubyObject asciiCompatible_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.getEncoding().isAsciiCompatible());
    }
    
    @JRubyMethod(name = { "to_s", "name" })
    public IRubyObject to_s(final ThreadContext context) {
        return RubyString.newUsAsciiStringShared(context.getRuntime(), this.name);
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        final ByteList bytes = new ByteList();
        bytes.append("#<Encoding:".getBytes());
        bytes.append(this.name);
        if (this.isDummy) {
            bytes.append(" (dummy)".getBytes());
        }
        bytes.append(62);
        return RubyString.newUsAsciiStringNoCopy(context.getRuntime(), bytes);
    }
    
    @JRubyMethod(name = { "names" })
    public IRubyObject names(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final EncodingService service = runtime.getEncodingService();
        final EncodingDB.Entry entry = service.findEncodingOrAliasEntry(this.name);
        final RubyArray result = runtime.newArray();
        Hash.HashEntryIterator i = service.getEncodings().entryIterator();
        while (i.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)i.next();
            if (e.value == entry) {
                result.append(RubyString.newUsAsciiStringShared(runtime, e.bytes, e.p, e.end - e.p).freeze(context));
            }
        }
        i = service.getAliases().entryIterator();
        while (i.hasNext()) {
            final CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry> e = (CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry<EncodingDB.Entry>)(CaseInsensitiveBytesHash.CaseInsensitiveBytesHashEntry)i.next();
            if (e.value == entry) {
                result.append(RubyString.newUsAsciiStringShared(runtime, e.bytes, e.p, e.end - e.p).freeze(context));
            }
        }
        result.append(runtime.newString(RubyEncoding.EXTERNAL));
        result.append(runtime.newString(RubyEncoding.LOCALE));
        return result;
    }
    
    @JRubyMethod(name = { "dummy?" })
    public IRubyObject dummy_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isDummy);
    }
    
    @JRubyMethod(name = { "compatible?" }, meta = true)
    public static IRubyObject compatible_p(final ThreadContext context, final IRubyObject self, final IRubyObject first, final IRubyObject second) {
        final Ruby runtime = context.getRuntime();
        final Encoding enc = areCompatible(first, second);
        return (enc == null) ? runtime.getNil() : runtime.getEncodingService().getEncoding(enc);
    }
    
    @JRubyMethod(name = { "default_external" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject getDefaultExternal(final IRubyObject recv) {
        return recv.getRuntime().getEncodingService().getDefaultExternal();
    }
    
    @JRubyMethod(name = { "default_external=" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static void setDefaultExternal(final IRubyObject recv, final IRubyObject encoding) {
        final Ruby runtime = recv.getRuntime();
        final EncodingService service = runtime.getEncodingService();
        if (encoding.isNil()) {
            throw recv.getRuntime().newArgumentError("default_external can not be nil");
        }
        runtime.setDefaultExternalEncoding(service.getEncodingFromObject(encoding));
    }
    
    @JRubyMethod(name = { "default_internal" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject getDefaultInternal(final IRubyObject recv) {
        return recv.getRuntime().getEncodingService().getDefaultInternal();
    }
    
    @JRubyMethod(name = { "default_internal=" }, required = 1, meta = true, compat = CompatVersion.RUBY1_9)
    public static void setDefaultInternal(final IRubyObject recv, final IRubyObject encoding) {
        final Ruby runtime = recv.getRuntime();
        final EncodingService service = runtime.getEncodingService();
        if (encoding.isNil()) {
            recv.getRuntime().newArgumentError("default_internal can not be nil");
        }
        recv.getRuntime().setDefaultInternalEncoding(service.getEncodingFromObject(encoding));
    }
    
    @Deprecated
    public static IRubyObject getDefaultExternal(final Ruby runtime) {
        return runtime.getEncodingService().getDefaultExternal();
    }
    
    @Deprecated
    public static IRubyObject getDefaultInternal(final Ruby runtime) {
        return runtime.getEncodingService().getDefaultInternal();
    }
    
    @Deprecated
    public static IRubyObject convertEncodingToRubyEncoding(final Ruby runtime, final Encoding defaultEncoding) {
        return runtime.getEncodingService().convertEncodingToRubyEncoding(defaultEncoding);
    }
    
    @Deprecated
    public static Encoding getEncodingFromObject(final Ruby runtime, final IRubyObject arg) {
        return runtime.getEncodingService().getEncodingFromObject(arg);
    }
    
    static {
        UTF8 = Charset.forName("UTF-8");
        LOCALE = ByteList.create("locale");
        EXTERNAL = ByteList.create("external");
    }
}
