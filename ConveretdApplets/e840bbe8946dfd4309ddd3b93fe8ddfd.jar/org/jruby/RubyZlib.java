// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.io.OutputStream;
import org.jruby.util.IOOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.EOFException;
import java.util.zip.CRC32;
import java.util.zip.InflaterInputStream;
import org.jruby.util.IOInputStream;
import org.jruby.anno.FrameField;
import java.io.BufferedInputStream;
import org.jruby.javasupport.util.RuntimeHelpers;
import java.io.InputStream;
import org.joda.time.DateTime;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyClass;
import org.jruby.exceptions.RaiseException;
import java.util.List;
import java.util.ArrayList;
import org.jruby.util.Adler32Ext;
import org.jruby.util.ByteList;
import org.jruby.util.CRC32Ext;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Zlib" })
public class RubyZlib
{
    public static final String ZLIB_VERSION = "1.2.3.3";
    public static final String VERSION = "0.6.0";
    public static final int MIN_WBITS = 8;
    public static final int MAX_WBITS = 15;
    public static final byte Z_NO_FLUSH = 0;
    public static final byte Z_SYNC_FLUSH = 2;
    public static final byte Z_FULL_FLUSH = 3;
    public static final byte Z_FINISH = 4;
    public static final byte Z_NO_COMPRESSION = 0;
    public static final byte Z_BEST_SPEED = 1;
    public static final byte Z_BEST_COMPRESSION = 9;
    public static final byte Z_DEFAULT_COMPRESSION = -1;
    public static final byte OS_MSDOS = 0;
    public static final byte OS_AMIGA = 1;
    public static final byte OS_VMS = 2;
    public static final byte OS_UNIX = 3;
    public static final byte OS_ATARI = 5;
    public static final byte OS_OS2 = 6;
    public static final byte OS_MACOS = 7;
    public static final byte OS_TOPS20 = 10;
    public static final byte OS_WIN32 = 11;
    public static final byte OS_VMCMS = 4;
    public static final byte OS_ZSYSTEM = 8;
    public static final byte OS_CPM = 9;
    public static final byte OS_QDOS = 12;
    public static final byte OS_RISCOS = 13;
    public static final byte OS_UNKNOWN = -1;
    public static final byte OS_CODE = 11;
    public static final byte Z_FILTERED = 1;
    public static final byte Z_HUFFMAN_ONLY = 2;
    public static final byte Z_DEFAULT_STRATEGY = 0;
    public static final byte Z_BINARY = 0;
    public static final byte Z_ASCII = 1;
    public static final byte Z_UNKNOWN = 2;
    private static final long[] crctab;
    
    public static RubyModule createZlibModule(final Ruby runtime) {
        final RubyModule mZlib = runtime.defineModule("Zlib");
        mZlib.defineAnnotatedMethods(RubyZlib.class);
        final RubyClass cStandardError = runtime.getStandardError();
        final RubyClass cZlibError = mZlib.defineClassUnder("Error", cStandardError, cStandardError.getAllocator());
        mZlib.defineClassUnder("StreamEnd", cZlibError, cZlibError.getAllocator());
        mZlib.defineClassUnder("StreamError", cZlibError, cZlibError.getAllocator());
        mZlib.defineClassUnder("BufError", cZlibError, cZlibError.getAllocator());
        mZlib.defineClassUnder("NeedDict", cZlibError, cZlibError.getAllocator());
        mZlib.defineClassUnder("MemError", cZlibError, cZlibError.getAllocator());
        mZlib.defineClassUnder("VersionError", cZlibError, cZlibError.getAllocator());
        mZlib.defineClassUnder("DataError", cZlibError, cZlibError.getAllocator());
        final RubyClass cGzFile = mZlib.defineClassUnder("GzipFile", runtime.getObject(), RubyGzipFile.GZIPFILE_ALLOCATOR);
        cGzFile.defineAnnotatedMethods(RubyGzipFile.class);
        cGzFile.defineClassUnder("Error", cZlibError, cZlibError.getAllocator());
        final RubyClass cGzError = cGzFile.defineClassUnder("Error", cZlibError, cZlibError.getAllocator());
        cGzFile.defineClassUnder("CRCError", cGzError, cGzError.getAllocator());
        cGzFile.defineClassUnder("NoFooter", cGzError, cGzError.getAllocator());
        cGzFile.defineClassUnder("LengthError", cGzError, cGzError.getAllocator());
        final RubyClass cGzReader = mZlib.defineClassUnder("GzipReader", cGzFile, RubyGzipReader.GZIPREADER_ALLOCATOR);
        cGzReader.includeModule(runtime.getEnumerable());
        cGzReader.defineAnnotatedMethods(RubyGzipReader.class);
        final RubyClass cGzWriter = mZlib.defineClassUnder("GzipWriter", cGzFile, RubyGzipWriter.GZIPWRITER_ALLOCATOR);
        cGzWriter.defineAnnotatedMethods(RubyGzipWriter.class);
        mZlib.defineConstant("ZLIB_VERSION", runtime.newString("1.2.3.3"));
        mZlib.defineConstant("VERSION", runtime.newString("0.6.0"));
        mZlib.defineConstant("BINARY", runtime.newFixnum(0));
        mZlib.defineConstant("ASCII", runtime.newFixnum(1));
        mZlib.defineConstant("UNKNOWN", runtime.newFixnum(2));
        mZlib.defineConstant("DEF_MEM_LEVEL", runtime.newFixnum(8));
        mZlib.defineConstant("MAX_MEM_LEVEL", runtime.newFixnum(9));
        mZlib.defineConstant("OS_UNIX", runtime.newFixnum(3));
        mZlib.defineConstant("OS_UNKNOWN", runtime.newFixnum(-1));
        mZlib.defineConstant("OS_CODE", runtime.newFixnum(11));
        mZlib.defineConstant("OS_ZSYSTEM", runtime.newFixnum(8));
        mZlib.defineConstant("OS_VMCMS", runtime.newFixnum(4));
        mZlib.defineConstant("OS_VMS", runtime.newFixnum(2));
        mZlib.defineConstant("OS_RISCOS", runtime.newFixnum(13));
        mZlib.defineConstant("OS_MACOS", runtime.newFixnum(7));
        mZlib.defineConstant("OS_OS2", runtime.newFixnum(6));
        mZlib.defineConstant("OS_AMIGA", runtime.newFixnum(1));
        mZlib.defineConstant("OS_QDOS", runtime.newFixnum(12));
        mZlib.defineConstant("OS_WIN32", runtime.newFixnum(11));
        mZlib.defineConstant("OS_ATARI", runtime.newFixnum(5));
        mZlib.defineConstant("OS_MSDOS", runtime.newFixnum(0));
        mZlib.defineConstant("OS_CPM", runtime.newFixnum(9));
        mZlib.defineConstant("OS_TOPS20", runtime.newFixnum(10));
        mZlib.defineConstant("DEFAULT_STRATEGY", runtime.newFixnum(0));
        mZlib.defineConstant("FILTERED", runtime.newFixnum(1));
        mZlib.defineConstant("HUFFMAN_ONLY", runtime.newFixnum(2));
        mZlib.defineConstant("NO_FLUSH", runtime.newFixnum(0));
        mZlib.defineConstant("SYNC_FLUSH", runtime.newFixnum(2));
        mZlib.defineConstant("FULL_FLUSH", runtime.newFixnum(3));
        mZlib.defineConstant("FINISH", runtime.newFixnum(4));
        mZlib.defineConstant("NO_COMPRESSION", runtime.newFixnum(0));
        mZlib.defineConstant("BEST_SPEED", runtime.newFixnum(1));
        mZlib.defineConstant("DEFAULT_COMPRESSION", runtime.newFixnum(-1));
        mZlib.defineConstant("BEST_COMPRESSION", runtime.newFixnum(9));
        mZlib.defineConstant("MAX_WBITS", runtime.newFixnum(15));
        final RubyClass cZStream = mZlib.defineClassUnder("ZStream", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        cZStream.defineAnnotatedMethods(ZStream.class);
        cZStream.undefineMethod("new");
        final RubyClass cInflate = mZlib.defineClassUnder("Inflate", cZStream, Inflate.INFLATE_ALLOCATOR);
        cInflate.defineAnnotatedMethods(Inflate.class);
        final RubyClass cDeflate = mZlib.defineClassUnder("Deflate", cZStream, Deflate.DEFLATE_ALLOCATOR);
        cDeflate.defineAnnotatedMethods(Deflate.class);
        runtime.getKernel().callMethod(runtime.getCurrentContext(), "require", runtime.newString("stringio"));
        return mZlib;
    }
    
    @JRubyMethod(name = { "zlib_version" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject zlib_version(final IRubyObject recv) {
        final RubyBasicObject res = (RubyBasicObject)((RubyModule)recv).fastGetConstant("ZLIB_VERSION");
        res.taint(recv.getRuntime());
        return res;
    }
    
    @JRubyMethod(name = { "crc32" }, optional = 2, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject crc32(final IRubyObject recv, IRubyObject[] args) {
        args = Arity.scanArgs(recv.getRuntime(), args, 0, 2);
        long crc = 0L;
        ByteList bytes = null;
        if (!args[0].isNil()) {
            bytes = args[0].convertToString().getByteList();
        }
        if (!args[1].isNil()) {
            crc = RubyNumeric.num2long(args[1]);
        }
        final CRC32Ext ext = new CRC32Ext((int)crc);
        if (bytes != null) {
            ext.update(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
        }
        return recv.getRuntime().newFixnum(ext.getValue());
    }
    
    @JRubyMethod(name = { "adler32" }, optional = 2, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject adler32(final IRubyObject recv, IRubyObject[] args) {
        args = Arity.scanArgs(recv.getRuntime(), args, 0, 2);
        int adler = 1;
        ByteList bytes = null;
        if (!args[0].isNil()) {
            bytes = args[0].convertToString().getByteList();
        }
        if (!args[1].isNil()) {
            adler = RubyNumeric.fix2int(args[1]);
        }
        final Adler32Ext ext = new Adler32Ext(adler);
        if (bytes != null) {
            ext.update(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
        }
        return recv.getRuntime().newFixnum(ext.getValue());
    }
    
    @JRubyMethod(name = { "crc_table" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject crc_table(final IRubyObject recv) {
        final List<IRubyObject> ll = new ArrayList<IRubyObject>(RubyZlib.crctab.length);
        for (int i = 0; i < RubyZlib.crctab.length; ++i) {
            ll.add(recv.getRuntime().newFixnum(RubyZlib.crctab[i]));
        }
        return recv.getRuntime().newArray(ll);
    }
    
    private static RaiseException newZlibError(final Ruby runtime, final String klass, final String message) {
        final RubyClass errorClass = runtime.fastGetModule("Zlib").fastGetClass(klass);
        return new RaiseException(RubyException.newException(runtime, errorClass, message), true);
    }
    
    static RaiseException newZlibError(final Ruby runtime, final String message) {
        return newZlibError(runtime, "Error", message);
    }
    
    static RaiseException newStreamError(final Ruby runtime, final String message) {
        return newZlibError(runtime, "StreamError", message);
    }
    
    static RaiseException newDataError(final Ruby runtime, final String message) {
        return newZlibError(runtime, "DataError", message);
    }
    
    static void resetBuffer(final ByteList l) {
        l.setBegin(0);
        l.setRealSize(0);
        l.invalidate();
    }
    
    static {
        crctab = new long[] { 0L, 1996959894L, 3993919788L, 2567524794L, 124634137L, 1886057615L, 3915621685L, 2657392035L, 249268274L, 2044508324L, 3772115230L, 2547177864L, 162941995L, 2125561021L, 3887607047L, 2428444049L, 498536548L, 1789927666L, 4089016648L, 2227061214L, 450548861L, 1843258603L, 4107580753L, 2211677639L, 325883990L, 1684777152L, 4251122042L, 2321926636L, 335633487L, 1661365465L, 4195302755L, 2366115317L, 997073096L, 1281953886L, 3579855332L, 2724688242L, 1006888145L, 1258607687L, 3524101629L, 2768942443L, 901097722L, 1119000684L, 3686517206L, 2898065728L, 853044451L, 1172266101L, 3705015759L, 2882616665L, 651767980L, 1373503546L, 3369554304L, 3218104598L, 565507253L, 1454621731L, 3485111705L, 3099436303L, 671266974L, 1594198024L, 3322730930L, 2970347812L, 795835527L, 1483230225L, 3244367275L, 3060149565L, 1994146192L, 31158534L, 2563907772L, 4023717930L, 1907459465L, 112637215L, 2680153253L, 3904427059L, 2013776290L, 251722036L, 2517215374L, 3775830040L, 2137656763L, 141376813L, 2439277719L, 3865271297L, 1802195444L, 476864866L, 2238001368L, 4066508878L, 1812370925L, 453092731L, 2181625025L, 4111451223L, 1706088902L, 314042704L, 2344532202L, 4240017532L, 1658658271L, 366619977L, 2362670323L, 4224994405L, 1303535960L, 984961486L, 2747007092L, 3569037538L, 1256170817L, 1037604311L, 2765210733L, 3554079995L, 1131014506L, 879679996L, 2909243462L, 3663771856L, 1141124467L, 855842277L, 2852801631L, 3708648649L, 1342533948L, 654459306L, 3188396048L, 3373015174L, 1466479909L, 544179635L, 3110523913L, 3462522015L, 1591671054L, 702138776L, 2966460450L, 3352799412L, 1504918807L, 783551873L, 3082640443L, 3233442989L, 3988292384L, 2596254646L, 62317068L, 1957810842L, 3939845945L, 2647816111L, 81470997L, 1943803523L, 3814918930L, 2489596804L, 225274430L, 2053790376L, 3826175755L, 2466906013L, 167816743L, 2097651377L, 4027552580L, 2265490386L, 503444072L, 1762050814L, 4150417245L, 2154129355L, 426522225L, 1852507879L, 4275313526L, 2312317920L, 282753626L, 1742555852L, 4189708143L, 2394877945L, 397917763L, 1622183637L, 3604390888L, 2714866558L, 953729732L, 1340076626L, 3518719985L, 2797360999L, 1068828381L, 1219638859L, 3624741850L, 2936675148L, 906185462L, 1090812512L, 3747672003L, 2825379669L, 829329135L, 1181335161L, 3412177804L, 3160834842L, 628085408L, 1382605366L, 3423369109L, 3138078467L, 570562233L, 1426400815L, 3317316542L, 2998733608L, 733239954L, 1555261956L, 3268935591L, 3050360625L, 752459403L, 1541320221L, 2607071920L, 3965973030L, 1969922972L, 40735498L, 2617837225L, 3943577151L, 1913087877L, 83908371L, 2512341634L, 3803740692L, 2075208622L, 213261112L, 2463272603L, 3855990285L, 2094854071L, 198958881L, 2262029012L, 4057260610L, 1759359992L, 534414190L, 2176718541L, 4139329115L, 1873836001L, 414664567L, 2282248934L, 4279200368L, 1711684554L, 285281116L, 2405801727L, 4167216745L, 1634467795L, 376229701L, 2685067896L, 3608007406L, 1308918612L, 956543938L, 2808555105L, 3495958263L, 1231636301L, 1047427035L, 2932959818L, 3654703836L, 1088359270L, 936918000L, 2847714899L, 3736837829L, 1202900863L, 817233897L, 3183342108L, 3401237130L, 1404277552L, 615818150L, 3134207493L, 3453421203L, 1423857449L, 601450431L, 3009837614L, 3294710456L, 1567103746L, 711928724L, 3020668471L, 3272380065L, 1510334235L, 755167117L };
    }
    
    @JRubyClass(name = { "Zlib::Error" }, parent = "StandardError")
    public static class Error
    {
    }
    
    @JRubyClass(name = { "Zlib::StreamEnd" }, parent = "Zlib::Error")
    public static class StreamEnd extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::StreamError" }, parent = "Zlib::Error")
    public static class StreamError extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::BufError" }, parent = "Zlib::Error")
    public static class BufError extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::NeedDict" }, parent = "Zlib::Error")
    public static class NeedDict extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::MemError" }, parent = "Zlib::Error")
    public static class MemError extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::VersionError" }, parent = "Zlib::Error")
    public static class VersionError extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::DataError" }, parent = "Zlib::Error")
    public static class DataError extends Error
    {
    }
    
    @JRubyClass(name = { "Zlib::ZStream" })
    public abstract static class ZStream extends RubyObject
    {
        protected boolean closed;
        
        protected abstract int internalTotalIn();
        
        protected abstract int internalTotalOut();
        
        protected abstract boolean internalStreamEndP();
        
        protected abstract void internalReset();
        
        protected abstract boolean internalFinished();
        
        protected abstract int internalAdler();
        
        protected abstract IRubyObject internalFinish();
        
        protected abstract void internalClose();
        
        public ZStream(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.closed = false;
        }
        
        @JRubyMethod(visibility = Visibility.PRIVATE)
        public IRubyObject initialize(final Block unusedBlock) {
            return this;
        }
        
        @JRubyMethod
        public IRubyObject flush_next_out(final ThreadContext context) {
            return RubyString.newEmptyString(context.getRuntime());
        }
        
        @JRubyMethod
        public IRubyObject total_out() {
            this.checkClosed();
            return this.getRuntime().newFixnum(this.internalTotalOut());
        }
        
        @JRubyMethod(name = { "stream_end?" })
        public IRubyObject stream_end_p() {
            return this.internalStreamEndP() ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
        }
        
        @JRubyMethod(name = { "data_type" })
        public IRubyObject data_type() {
            this.checkClosed();
            return this.getRuntime().fastGetModule("Zlib").fastGetConstant("UNKNOWN");
        }
        
        @JRubyMethod(name = { "closed?", "ended?" })
        public IRubyObject closed_p() {
            return this.closed ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
        }
        
        @JRubyMethod(name = { "reset" })
        public IRubyObject reset() {
            this.checkClosed();
            this.internalReset();
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "avail_out" })
        public IRubyObject avail_out() {
            return RubyFixnum.zero(this.getRuntime());
        }
        
        @JRubyMethod(name = { "avail_out=" }, required = 1)
        public IRubyObject set_avail_out(final IRubyObject p1) {
            this.checkClosed();
            return p1;
        }
        
        @JRubyMethod(name = { "adler" })
        public IRubyObject adler() {
            this.checkClosed();
            return this.getRuntime().newFixnum(this.internalAdler());
        }
        
        @JRubyMethod(name = { "finish" }, backtrace = true)
        public IRubyObject finish(final ThreadContext context) {
            this.checkClosed();
            final IRubyObject result = this.internalFinish();
            return result;
        }
        
        @JRubyMethod(name = { "avail_in" })
        public IRubyObject avail_in() {
            return RubyFixnum.zero(this.getRuntime());
        }
        
        @JRubyMethod(name = { "flush_next_in" })
        public IRubyObject flush_next_in(final ThreadContext context) {
            return RubyString.newEmptyString(context.getRuntime());
        }
        
        @JRubyMethod(name = { "total_in" })
        public IRubyObject total_in() {
            this.checkClosed();
            return this.getRuntime().newFixnum(this.internalTotalIn());
        }
        
        @JRubyMethod(name = { "finished?" })
        public IRubyObject finished_p(final ThreadContext context) {
            this.checkClosed();
            final Ruby runtime = context.getRuntime();
            return this.internalFinished() ? runtime.getTrue() : runtime.getFalse();
        }
        
        @JRubyMethod(name = { "close", "end" })
        public IRubyObject close() {
            this.checkClosed();
            this.internalClose();
            this.closed = true;
            return this.getRuntime().getNil();
        }
        
        void checkClosed() {
            if (this.closed) {
                throw RubyZlib.newZlibError(this.getRuntime(), "stream is not ready");
            }
        }
        
        static void checkLevel(final Ruby runtime, final int level) {
            if ((level < 0 || level > 9) && level != -1) {
                throw RubyZlib.newStreamError(runtime, "stream error: invalid level");
            }
        }
        
        static void checkWindowBits(final Ruby runtime, int wbits) {
            wbits = Math.abs(wbits);
            if (wbits < 8 || wbits > 15) {
                throw RubyZlib.newStreamError(runtime, "stream error: invalid window bits");
            }
        }
        
        static void checkStrategy(final Ruby runtime, final int strategy) {
            switch (strategy) {
                case 0:
                case 1:
                case 2: {}
                default: {
                    throw RubyZlib.newStreamError(runtime, "stream error: invalid strategy");
                }
            }
        }
    }
    
    @JRubyClass(name = { "Zlib::Inflate" }, parent = "Zlib::ZStream")
    public static class Inflate extends ZStream
    {
        public static final int BASE_SIZE = 100;
        private Inflater flater;
        private ByteList collected;
        private ByteList input;
        protected static final ObjectAllocator INFLATE_ALLOCATOR;
        
        public Inflate(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        @JRubyMethod(name = { "inflate" }, required = 1, meta = true, backtrace = true)
        public static IRubyObject s_inflate(final ThreadContext context, final IRubyObject recv, final IRubyObject string) {
            final RubyClass klass = (RubyClass)recv;
            final Inflate inflate = (Inflate)klass.allocate();
            inflate.init(15);
            IRubyObject result = null;
            try {
                inflate.append(string.convertToString().getByteList());
            }
            finally {
                result = inflate.finish(context);
                inflate.close();
            }
            return result;
        }
        
        @JRubyMethod(name = { "initialize" }, optional = 1, visibility = Visibility.PRIVATE)
        public IRubyObject _initialize(final IRubyObject[] args) {
            int window_bits = 15;
            if (args.length > 0 && !args[0].isNil()) {
                window_bits = RubyNumeric.fix2int(args[0]);
                ZStream.checkWindowBits(this.getRuntime(), window_bits);
            }
            this.init(window_bits);
            return this;
        }
        
        private void init(final int window_bits) {
            this.flater = new Inflater(window_bits < 0);
            this.collected = new ByteList(100);
            this.input = new ByteList();
        }
        
        @JRubyMethod(name = { "flush_next_out" })
        public IRubyObject flush_next_out(final ThreadContext context) {
            return this.flushOutput(context.getRuntime());
        }
        
        private IRubyObject flushOutput(final Ruby runtime) {
            if (this.collected.getRealSize() > 0) {
                final IRubyObject res = RubyString.newString(runtime, this.collected.getUnsafeBytes(), this.collected.getBegin(), this.collected.getRealSize());
                RubyZlib.resetBuffer(this.collected);
                return res;
            }
            return RubyString.newEmptyString(runtime);
        }
        
        @JRubyMethod(name = { "<<" }, required = 1)
        public IRubyObject append(final ThreadContext context, final IRubyObject arg) {
            this.checkClosed();
            if (arg.isNil()) {
                this.run(true);
            }
            else {
                this.append(arg.convertToString().getByteList());
            }
            return this;
        }
        
        public void append(final ByteList obj) {
            if (!this.internalFinished()) {
                final byte[] bytes = obj.bytes();
                this.flater.setInput(bytes);
                this.input = new ByteList(bytes, false);
                this.run(false);
            }
            else {
                this.input.append(obj);
            }
        }
        
        @JRubyMethod(name = { "sync_point?" })
        public IRubyObject sync_point_p() {
            return this.sync_point();
        }
        
        public IRubyObject sync_point() {
            return this.getRuntime().getFalse();
        }
        
        @JRubyMethod(name = { "set_dictionary" }, required = 1, backtrace = true)
        public IRubyObject set_dictionary(final ThreadContext context, final IRubyObject arg) {
            try {
                return this.set_dictionary(arg);
            }
            catch (IllegalArgumentException iae) {
                throw RubyZlib.newStreamError(context.getRuntime(), "stream error: " + iae.getMessage());
            }
        }
        
        private IRubyObject set_dictionary(final IRubyObject str) {
            this.flater.setDictionary(str.convertToString().getBytes());
            this.run(false);
            return str;
        }
        
        @JRubyMethod(name = { "inflate" }, required = 1, backtrace = true)
        public IRubyObject inflate(final ThreadContext context, final IRubyObject string) {
            ByteList data = null;
            if (!string.isNil()) {
                data = string.convertToString().getByteList();
            }
            return this.inflate(context, data);
        }
        
        public IRubyObject inflate(final ThreadContext context, final ByteList str) {
            if (null == str) {
                return this.internalFinish();
            }
            this.append(str);
            return this.flushOutput(context.getRuntime());
        }
        
        @JRubyMethod(name = { "sync" }, required = 1)
        public IRubyObject sync(final ThreadContext context, final IRubyObject string) {
            try {
                this.append(context, string);
            }
            catch (RaiseException re) {
                if (!re.getException().getMetaClass().getRealClass().getName().equals("Zlib::DataError")) {
                    throw re;
                }
            }
            return context.getRuntime().getFalse();
        }
        
        private void run(final boolean finish) {
            byte[] outp = new byte[1024];
            int resultLength = -1;
            while (!this.internalFinished() && resultLength != 0) {
                final Ruby runtime = this.getRuntime();
                if (finish && this.flater.needsInput()) {
                    final RubyClass errorClass = runtime.fastGetModule("Zlib").fastGetClass("BufError");
                    throw new RaiseException(RubyException.newException(runtime, errorClass, "buffer error"), true);
                }
                try {
                    resultLength = this.flater.inflate(outp);
                    if (this.flater.needsDictionary()) {
                        final RubyClass errorClass = runtime.fastGetModule("Zlib").fastGetClass("NeedDict");
                        throw new RaiseException(RubyException.newException(runtime, errorClass, "need dictionary"));
                    }
                    if (this.input.getRealSize() > 0) {
                        final int remaining = this.flater.getRemaining();
                        if (remaining > 0) {
                            this.input.view(this.input.getRealSize() - remaining, remaining);
                        }
                        else {
                            RubyZlib.resetBuffer(this.input);
                        }
                    }
                }
                catch (DataFormatException ex) {
                    throw RubyZlib.newDataError(runtime, "data error: " + ex.getMessage());
                }
                this.collected.append(outp, 0, resultLength);
                if (resultLength != outp.length) {
                    continue;
                }
                outp = new byte[outp.length * 2];
            }
            if (this.internalFinished() && finish && this.input.getRealSize() > 0) {
                this.collected.append(this.input);
                RubyZlib.resetBuffer(this.input);
            }
            if (finish) {
                this.flater.end();
            }
        }
        
        protected int internalTotalIn() {
            return this.flater.getTotalIn();
        }
        
        protected int internalTotalOut() {
            return this.flater.getTotalOut();
        }
        
        protected boolean internalStreamEndP() {
            return this.flater.finished();
        }
        
        protected void internalReset() {
            this.flater.reset();
        }
        
        protected boolean internalFinished() {
            return this.flater.finished();
        }
        
        protected int internalAdler() {
            return this.flater.getAdler();
        }
        
        protected IRubyObject internalFinish() {
            this.run(true);
            this.flater.end();
            RubyZlib.resetBuffer(this.input);
            return this.flushOutput(this.getRuntime());
        }
        
        protected void internalClose() {
            this.flater.end();
        }
        
        static {
            INFLATE_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new Inflate(runtime, klass);
                }
            };
        }
    }
    
    @JRubyClass(name = { "Zlib::Deflate" }, parent = "Zlib::ZStream")
    public static class Deflate extends ZStream
    {
        public static final int BASE_SIZE = 100;
        private Deflater flater;
        private ByteList collected;
        protected static final ObjectAllocator DEFLATE_ALLOCATOR;
        
        @JRubyMethod(name = { "deflate" }, required = 1, optional = 1, meta = true, backtrace = true)
        public static IRubyObject s_deflate(final IRubyObject recv, IRubyObject[] args) {
            final Ruby runtime = recv.getRuntime();
            args = Arity.scanArgs(runtime, args, 1, 1);
            int level = -1;
            if (!args[1].isNil()) {
                level = RubyNumeric.fix2int(args[1]);
                ZStream.checkLevel(runtime, level);
            }
            final RubyClass klass = (RubyClass)recv;
            final Deflate deflate = (Deflate)klass.allocate();
            deflate.init(level, 15, 8, 0);
            try {
                final IRubyObject result = deflate.deflate(args[0].convertToString().getByteList(), 4);
                deflate.close();
                return result;
            }
            catch (IOException ioe) {
                throw runtime.newIOErrorFromException(ioe);
            }
        }
        
        public Deflate(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        @JRubyMethod(name = { "initialize" }, optional = 4, visibility = Visibility.PRIVATE, backtrace = true)
        public IRubyObject _initialize(IRubyObject[] args) {
            args = Arity.scanArgs(this.getRuntime(), args, 0, 4);
            int level = -1;
            int window_bits = 15;
            int memlevel = 8;
            int strategy = 0;
            if (!args[0].isNil()) {
                level = RubyNumeric.fix2int(args[0]);
                ZStream.checkLevel(this.getRuntime(), level);
            }
            if (!args[1].isNil()) {
                window_bits = RubyNumeric.fix2int(args[1]);
                ZStream.checkWindowBits(this.getRuntime(), window_bits);
            }
            if (!args[2].isNil()) {
                memlevel = RubyNumeric.fix2int(args[2]);
            }
            if (!args[3].isNil()) {
                strategy = RubyNumeric.fix2int(args[3]);
            }
            this.init(level, window_bits, memlevel, strategy);
            return this;
        }
        
        private void init(final int level, final int win_bits, final int memlevel, final int strategy) {
            (this.flater = new Deflater(level, win_bits < 0)).setStrategy(strategy);
            this.collected = new ByteList(100);
        }
        
        @JRubyMethod(visibility = Visibility.PRIVATE)
        public IRubyObject initialize_copy(final IRubyObject other) {
            if (this == other) {
                return this;
            }
            throw this.getRuntime().newNotImplementedError("Zlib::Deflate#dup is not supported");
        }
        
        @JRubyMethod(name = { "<<" }, required = 1)
        public IRubyObject append(final IRubyObject arg) {
            this.checkClosed();
            try {
                this.append(arg.convertToString().getByteList());
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
            return this;
        }
        
        @JRubyMethod(name = { "params" }, required = 2)
        public IRubyObject params(final ThreadContext context, final IRubyObject level, final IRubyObject strategy) {
            final int l = RubyNumeric.fix2int(level);
            ZStream.checkLevel(this.getRuntime(), l);
            final int s = RubyNumeric.fix2int(strategy);
            ZStream.checkStrategy(this.getRuntime(), s);
            this.flater.setLevel(l);
            this.flater.setStrategy(s);
            this.run();
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "set_dictionary" }, required = 1, backtrace = true)
        public IRubyObject set_dictionary(final ThreadContext context, final IRubyObject arg) {
            try {
                this.flater.setDictionary(arg.convertToString().getBytes());
                this.run();
                return arg;
            }
            catch (IllegalArgumentException iae) {
                throw RubyZlib.newStreamError(context.getRuntime(), "stream error: " + iae.getMessage());
            }
        }
        
        @JRubyMethod(name = { "flush" }, optional = 1)
        public IRubyObject flush(final IRubyObject[] args) {
            int flush = 2;
            if (args.length == 1 && !args[0].isNil()) {
                flush = RubyNumeric.fix2int(args[0]);
            }
            return this.flush(flush);
        }
        
        @JRubyMethod(name = { "deflate" }, required = 1, optional = 1)
        public IRubyObject deflate(IRubyObject[] args) {
            args = Arity.scanArgs(this.getRuntime(), args, 1, 1);
            if (this.internalFinished()) {
                throw RubyZlib.newStreamError(this.getRuntime(), "stream error");
            }
            ByteList data = null;
            if (!args[0].isNil()) {
                data = args[0].convertToString().getByteList();
            }
            int flush = 0;
            if (!args[1].isNil()) {
                flush = RubyNumeric.fix2int(args[1]);
            }
            try {
                return this.deflate(data, flush);
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        protected int internalTotalIn() {
            return this.flater.getTotalIn();
        }
        
        protected int internalTotalOut() {
            return this.flater.getTotalOut();
        }
        
        protected boolean internalStreamEndP() {
            return this.flater.finished();
        }
        
        protected void internalReset() {
            this.flater.reset();
            this.collected = new ByteList(100);
        }
        
        public boolean internalFinished() {
            return this.flater.finished();
        }
        
        protected int internalAdler() {
            return this.flater.getAdler();
        }
        
        protected IRubyObject internalFinish() {
            return this.finish();
        }
        
        protected void internalClose() {
            this.flater.end();
        }
        
        private void append(final ByteList obj) throws IOException {
            this.flater.setInput(obj.getUnsafeBytes(), obj.getBegin(), obj.getRealSize());
            this.run();
        }
        
        private IRubyObject flush(final int flush) {
            if (flush == 0) {
                return RubyString.newEmptyString(this.getRuntime());
            }
            if (flush == 4) {
                this.flater.finish();
            }
            this.run();
            final IRubyObject obj = RubyString.newString(this.getRuntime(), this.collected);
            this.collected = new ByteList(100);
            return obj;
        }
        
        private IRubyObject deflate(final ByteList str, final int flush) throws IOException {
            if (null != str) {
                this.append(str);
            }
            return this.flush(flush);
        }
        
        private IRubyObject finish() {
            return this.flush(4);
        }
        
        private void run() {
            if (this.flater.finished()) {
                return;
            }
            byte[] outp = new byte[1024];
            while (!this.flater.finished()) {
                final int resultLength = this.flater.deflate(outp);
                if (resultLength == 0) {
                    break;
                }
                this.collected.append(outp, 0, resultLength);
                if (resultLength != outp.length) {
                    continue;
                }
                outp = new byte[outp.length * 2];
            }
        }
        
        static {
            DEFLATE_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new Deflate(runtime, klass);
                }
            };
        }
    }
    
    @JRubyClass(name = { "Zlib::GzipFile" })
    public static class RubyGzipFile extends RubyObject
    {
        static final byte GZ_MAGIC_ID_1 = 31;
        static final byte GZ_MAGIC_ID_2 = -117;
        static final byte GZ_METHOD_DEFLATE = 8;
        static final byte GZ_FLAG_MULTIPART = 2;
        static final byte GZ_FLAG_EXTRA = 4;
        static final byte GZ_FLAG_ORIG_NAME = 8;
        static final byte GZ_FLAG_COMMENT = 16;
        static final byte GZ_FLAG_ENCRYPT = 32;
        static final byte GZ_FLAG_UNKNOWN_MASK = -64;
        static final byte GZ_EXTRAFLAG_FAST = 4;
        static final byte GZ_EXTRAFLAG_SLOW = 2;
        protected static final ObjectAllocator GZIPFILE_ALLOCATOR;
        protected boolean closed;
        protected boolean finished;
        protected byte osCode;
        protected int level;
        protected RubyString nullFreeOrigName;
        protected RubyString nullFreeComment;
        protected IRubyObject realIo;
        protected RubyTime mtime;
        
        private static IRubyObject wrapBlock(final ThreadContext context, final RubyGzipFile instance, final Block block) {
            if (block.isGiven()) {
                try {
                    return block.yield(context, instance);
                }
                finally {
                    if (!instance.isClosed()) {
                        instance.close();
                    }
                }
            }
            return instance;
        }
        
        @JRubyMethod(meta = true)
        public static IRubyObject wrap(final ThreadContext context, final IRubyObject recv, final IRubyObject io, final Block block) {
            final Ruby runtime = recv.getRuntime();
            RubyGzipFile instance;
            if (recv == runtime.getModule("Zlib").getClass("GzipWriter")) {
                instance = RubyGzipWriter.newGzipWriter(recv, new IRubyObject[] { io }, block);
            }
            else {
                instance = RubyGzipReader.newInstance(recv, new IRubyObject[] { io }, block);
            }
            return wrapBlock(context, instance, block);
        }
        
        @JRubyMethod(name = { "new" }, meta = true)
        public static RubyGzipFile newInstance(final IRubyObject recv, final Block block) {
            final RubyClass klass = (RubyClass)recv;
            final RubyGzipFile result = (RubyGzipFile)klass.allocate();
            result.callInit(new IRubyObject[0], block);
            return result;
        }
        
        public RubyGzipFile(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.closed = false;
            this.finished = false;
            this.osCode = -1;
            this.level = -1;
            this.mtime = RubyTime.newTime(runtime, new DateTime());
        }
        
        @JRubyMethod(name = { "os_code" })
        public IRubyObject os_code() {
            return this.getRuntime().newFixnum(this.osCode & 0xFF);
        }
        
        @JRubyMethod(name = { "closed?" })
        public IRubyObject closed_p() {
            return this.closed ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
        }
        
        protected boolean isClosed() {
            return this.closed;
        }
        
        @JRubyMethod(name = { "orig_name" })
        public IRubyObject orig_name() {
            if (this.closed) {
                throw newGzipFileError(this.getRuntime(), "closed gzip stream");
            }
            return (this.nullFreeOrigName == null) ? this.getRuntime().getNil() : this.nullFreeOrigName;
        }
        
        @JRubyMethod(name = { "to_io" })
        public IRubyObject to_io() {
            return this.realIo;
        }
        
        @JRubyMethod(name = { "comment" })
        public IRubyObject comment() {
            if (this.closed) {
                throw newGzipFileError(this.getRuntime(), "closed gzip stream");
            }
            return (this.nullFreeComment == null) ? this.getRuntime().getNil() : this.nullFreeComment;
        }
        
        @JRubyMethod(name = { "crc" })
        public IRubyObject crc() {
            return this.getRuntime().newFixnum(0);
        }
        
        @JRubyMethod(name = { "mtime" })
        public IRubyObject mtime() {
            return this.mtime;
        }
        
        @JRubyMethod(name = { "sync" })
        public IRubyObject sync() {
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "finish" })
        public IRubyObject finish() {
            if (!this.finished) {}
            this.finished = true;
            return this.realIo;
        }
        
        @JRubyMethod(name = { "close" })
        public IRubyObject close() {
            return null;
        }
        
        @JRubyMethod(name = { "level" })
        public IRubyObject level() {
            return this.getRuntime().newFixnum(this.level);
        }
        
        @JRubyMethod(name = { "sync=" }, required = 1)
        public IRubyObject set_sync(final IRubyObject ignored) {
            return this.getRuntime().getNil();
        }
        
        static void checkLevel(final Ruby runtime, final int level) {
            if (level < 0 || level > 9) {
                throw RubyZlib.newStreamError(runtime, "stream error: invalid level");
            }
        }
        
        static RaiseException newGzipFileError(final Ruby runtime, final String message) {
            return newGzipFileError(runtime, "Error", message);
        }
        
        static RaiseException newCRCError(final Ruby runtime, final String message) {
            return newGzipFileError(runtime, "CRCError", message);
        }
        
        static RaiseException newNoFooter(final Ruby runtime, final String message) {
            return newGzipFileError(runtime, "NoFooter", message);
        }
        
        static RaiseException newLengthError(final Ruby runtime, final String message) {
            return newGzipFileError(runtime, "LengthError", message);
        }
        
        private static RaiseException newGzipFileError(final Ruby runtime, final String klass, final String message) {
            final RubyClass errorClass = runtime.fastGetModule("Zlib").fastGetClass("GzipFile").fastGetClass(klass);
            return new RaiseException(RubyException.newException(runtime, errorClass, message), true);
        }
        
        static {
            GZIPFILE_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new RubyGzipFile(runtime, klass);
                }
            };
        }
        
        @JRubyClass(name = { "Zlib::GzipFile::Error" }, parent = "Zlib::Error")
        public static class Error
        {
        }
        
        @JRubyClass(name = { "Zlib::GzipFile::CRCError" }, parent = "Zlib::GzipFile::Error")
        public static class CRCError extends Error
        {
        }
        
        @JRubyClass(name = { "Zlib::GzipFile::NoFooter" }, parent = "Zlib::GzipFile::Error")
        public static class NoFooter extends Error
        {
        }
        
        @JRubyClass(name = { "Zlib::GzipFile::LengthError" }, parent = "Zlib::GzipFile::Error")
        public static class LengthError extends Error
        {
        }
    }
    
    @JRubyClass(name = { "Zlib::GzipReader" }, parent = "Zlib::GzipFile", include = { "Enumerable" })
    public static class RubyGzipReader extends RubyGzipFile
    {
        protected static final ObjectAllocator GZIPREADER_ALLOCATOR;
        private int line;
        private long position;
        private HeaderReadableGZIPInputStream io;
        private InputStream bufferedStream;
        private static final int BUFF_SIZE = 4096;
        
        @JRubyMethod(name = { "new" }, rest = true, meta = true)
        public static RubyGzipReader newInstance(final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final RubyClass klass = (RubyClass)recv;
            final RubyGzipReader result = (RubyGzipReader)klass.allocate();
            result.callInit(args, block);
            return result;
        }
        
        @JRubyMethod(meta = true)
        public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject filename, final Block block) {
            final Ruby runtime = recv.getRuntime();
            final IRubyObject io = RuntimeHelpers.invoke(context, runtime.getFile(), "open", filename, runtime.newString("rb"));
            final RubyGzipReader gzio = newInstance(recv, new IRubyObject[] { io }, block);
            return wrapBlock(context, gzio, block);
        }
        
        public RubyGzipReader(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        @JRubyMethod(visibility = Visibility.PRIVATE)
        public IRubyObject initialize(final IRubyObject arg, final Block unusedBlock) {
            this.realIo = arg;
            this.line = 0;
            this.position = 0L;
            this.io = new HeaderReadableGZIPInputStream(new CountingIOInputStream(this.realIo));
            this.bufferedStream = new BufferedInputStream(this.io);
            return this;
        }
        
        @JRubyMethod
        public IRubyObject rewind() {
            final Ruby rt = this.getRuntime();
            this.realIo.callMethod(rt.getCurrentContext(), "seek", new IRubyObject[] { rt.newFixnum(-this.io.pos()), rt.newFixnum(1) });
            this.initialize(this.realIo, Block.NULL_BLOCK);
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "lineno" })
        public IRubyObject lineno() {
            return this.getRuntime().newFixnum(this.line);
        }
        
        @JRubyMethod(name = { "readline" }, writes = { FrameField.LASTLINE })
        public IRubyObject readline(final ThreadContext context) {
            final IRubyObject dst = this.gets(context, new IRubyObject[0]);
            if (dst.isNil()) {
                throw this.getRuntime().newEOFError();
            }
            return dst;
        }
        
        private IRubyObject internalGets(final IRubyObject[] args) throws IOException {
            ByteList sep = ((RubyString)this.getRuntime().getGlobalVariables().get("$/")).getByteList();
            if (args.length > 0) {
                sep = args[0].convertToString().getByteList();
            }
            return this.internalSepGets(sep);
        }
        
        private IRubyObject internalSepGets(final ByteList sep) throws IOException {
            final ByteList result = new ByteList();
            int ce;
            for (ce = this.bufferedStream.read(); ce != -1 && sep.indexOf(ce) == -1; ce = this.bufferedStream.read()) {
                result.append((byte)ce);
            }
            if (0 == result.length() && -1 == ce) {
                return this.getRuntime().getNil();
            }
            ++this.line;
            this.position = result.length();
            result.append(sep);
            return RubyString.newString(this.getRuntime(), result);
        }
        
        @JRubyMethod(name = { "gets" }, optional = 1, writes = { FrameField.LASTLINE })
        public IRubyObject gets(final ThreadContext context, final IRubyObject[] args) {
            try {
                final IRubyObject result = this.internalGets(args);
                if (!result.isNil()) {
                    context.getCurrentScope().setLastLine(result);
                }
                return result;
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        @JRubyMethod(name = { "read" }, optional = 1)
        public IRubyObject read(final IRubyObject[] args) {
            try {
                if (args.length == 0 || args[0].isNil()) {
                    final ByteList val = new ByteList(10);
                    final byte[] buffer = new byte[4096];
                    for (int read = this.bufferedStream.read(buffer); read != -1; read = this.bufferedStream.read(buffer)) {
                        val.append(buffer, 0, read);
                    }
                    this.position += val.length();
                    return RubyString.newString(this.getRuntime(), val);
                }
                final int len = RubyNumeric.fix2int(args[0]);
                if (len < 0) {
                    throw this.getRuntime().newArgumentError("negative length " + len + " given");
                }
                if (len > 0) {
                    final byte[] buffer = new byte[len];
                    int toRead = len;
                    int offset = 0;
                    int read2 = 0;
                    while (toRead > 0) {
                        read2 = this.bufferedStream.read(buffer, offset, toRead);
                        if (read2 == -1) {
                            if (offset == 0) {
                                return this.getRuntime().getNil();
                            }
                            break;
                        }
                        else {
                            toRead -= read2;
                            offset += read2;
                        }
                    }
                    this.position += buffer.length;
                    return RubyString.newString(this.getRuntime(), new ByteList(buffer, 0, len - toRead, false));
                }
                return RubyString.newEmptyString(this.getRuntime());
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        @JRubyMethod(name = { "lineno=" }, required = 1)
        public IRubyObject set_lineno(final IRubyObject lineArg) {
            this.line = RubyNumeric.fix2int(lineArg);
            return lineArg;
        }
        
        @JRubyMethod(name = { "pos", "tell" })
        public IRubyObject pos() {
            return RubyNumeric.int2fix(this.getRuntime(), this.position);
        }
        
        @JRubyMethod(name = { "readchar" })
        public IRubyObject readchar() {
            try {
                final int value = this.bufferedStream.read();
                if (value == -1) {
                    throw this.getRuntime().newEOFError();
                }
                ++this.position;
                return this.getRuntime().newFixnum(value);
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        @JRubyMethod(name = { "getc" })
        public IRubyObject getc() {
            try {
                final int value = this.bufferedStream.read();
                if (value == -1) {
                    return this.getRuntime().getNil();
                }
                ++this.position;
                return this.getRuntime().newFixnum(value);
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        private boolean isEof() throws IOException {
            if (this.bufferedStream.available() == 0) {
                return true;
            }
            this.bufferedStream.mark(16);
            this.bufferedStream.read();
            this.bufferedStream.reset();
            return this.bufferedStream.available() == 0;
        }
        
        @JRubyMethod(name = { "close" })
        public IRubyObject close() {
            if (!this.closed) {
                try {
                    this.bufferedStream.close();
                }
                catch (IOException ioe) {
                    throw this.getRuntime().newIOErrorFromException(ioe);
                }
            }
            this.closed = true;
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "eof" })
        public IRubyObject eof() {
            try {
                return this.isEof() ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        @JRubyMethod(name = { "eof?" })
        public IRubyObject eof_p() {
            return this.eof();
        }
        
        @JRubyMethod
        public IRubyObject unused() {
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod
        public IRubyObject crc() {
            return this.getRuntime().newFixnum(this.io.crc());
        }
        
        @JRubyMethod(optional = 1)
        public IRubyObject each(final ThreadContext context, final IRubyObject[] args, final Block block) {
            ByteList sep = ((RubyString)this.getRuntime().getGlobalVariables().get("$/")).getByteList();
            if (args.length > 0 && !args[0].isNil()) {
                sep = args[0].convertToString().getByteList();
            }
            try {
                for (IRubyObject result = this.internalSepGets(sep); !result.isNil(); result = this.internalSepGets(sep)) {
                    block.yield(context, result);
                }
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(optional = 1)
        public IRubyObject each_line(final ThreadContext context, final IRubyObject[] args, final Block block) {
            return this.each(context, args, block);
        }
        
        @JRubyMethod
        public IRubyObject ungetc(final IRubyObject arg) {
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(optional = 1)
        public IRubyObject readlines(final IRubyObject[] args) {
            final List<IRubyObject> array = new ArrayList<IRubyObject>();
            if (args.length != 0 && args[0].isNil()) {
                array.add(this.read(new IRubyObject[0]));
            }
            else {
                ByteList sep = ((RubyString)this.getRuntime().getGlobalVariables().get("$/")).getByteList();
                if (args.length > 0) {
                    sep = args[0].convertToString().getByteList();
                }
                try {
                    for (IRubyObject result = this.internalSepGets(sep); !result.isNil(); result = this.internalSepGets(sep)) {
                        array.add(result);
                    }
                }
                catch (IOException ioe) {
                    throw this.getRuntime().newIOErrorFromException(ioe);
                }
            }
            return this.getRuntime().newArray(array);
        }
        
        @JRubyMethod
        public IRubyObject each_byte(final ThreadContext context, final Block block) {
            try {
                for (int value = this.bufferedStream.read(); value != -1; value = this.bufferedStream.read()) {
                    ++this.position;
                    block.yield(context, this.getRuntime().newFixnum(value));
                }
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
            return this.getRuntime().getNil();
        }
        
        static {
            GZIPREADER_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new RubyGzipReader(runtime, klass);
                }
            };
        }
        
        @JRubyClass(name = { "Zlib::GzipReader::Error" }, parent = "Zlib::GzipReader")
        public static class Error
        {
        }
        
        private static class CountingIOInputStream extends IOInputStream
        {
            private int position;
            private IRubyObject io;
            
            public CountingIOInputStream(final IRubyObject io) {
                super(io);
                this.io = io;
                this.position = 0;
            }
            
            public int read() throws IOException {
                final int ret = super.read();
                if (ret != -1) {
                    ++this.position;
                }
                return ret;
            }
            
            public int read(final byte[] b) throws IOException {
                final int ret = super.read(b);
                if (ret != -1) {
                    this.position += ret;
                }
                return ret;
            }
            
            public int read(final byte[] b, final int off, final int len) throws IOException {
                final int ret = super.read(b, off, len);
                if (ret != -1) {
                    this.position += ret;
                }
                return ret;
            }
            
            int pos() {
                return this.position;
            }
            
            Ruby getRuntime() {
                return this.io.getRuntime();
            }
        }
        
        private class HeaderReadableGZIPInputStream extends InflaterInputStream
        {
            private static final int DEFAULT_BUFFER_SIZE = 512;
            private CountingIOInputStream countingStream;
            private CRC32 checksum;
            private boolean eof;
            
            public HeaderReadableGZIPInputStream(final CountingIOInputStream io) {
                super(new BufferedInputStream(io), new Inflater(true), 512);
                this.checksum = new CRC32();
                this.eof = false;
                this.countingStream = io;
                this.readHeader();
                this.eof = false;
                this.checksum.reset();
            }
            
            public int read() throws IOException {
                if (this.eof) {
                    return -1;
                }
                final int ret = super.read();
                if (ret == -1) {
                    this.readTrailer();
                }
                else {
                    this.checksum.update((byte)(ret & 0xFF));
                }
                return ret;
            }
            
            public int read(final byte[] b) throws IOException {
                if (this.eof) {
                    return -1;
                }
                final int ret = super.read(b);
                if (ret == -1) {
                    this.readTrailer();
                }
                else {
                    this.checksum.update(b, 0, ret);
                }
                return ret;
            }
            
            public int read(final byte[] b, final int off, final int len) throws IOException {
                if (this.eof) {
                    return -1;
                }
                final int ret = super.read(b, off, len);
                if (ret == -1) {
                    this.readTrailer();
                }
                else {
                    this.checksum.update(b, off, ret);
                }
                return ret;
            }
            
            public void close() throws IOException {
                if (!RubyGzipReader.this.closed) {
                    RubyGzipReader.this.closed = true;
                }
                if (this.countingStream.io.respondsTo("close")) {
                    this.countingStream.io.callMethod(this.countingStream.getRuntime().getCurrentContext(), "close");
                }
                this.eof = true;
            }
            
            public int pos() {
                return this.countingStream.pos();
            }
            
            public long crc() {
                return this.checksum.getValue();
            }
            
            private void readHeader() {
                this.checksum.reset();
                try {
                    if ((byte)this.readUByte() != 31) {
                        throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), "not in gzip format");
                    }
                    if ((byte)this.readUByte() != -117) {
                        throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), "not in gzip format");
                    }
                    final byte b = (byte)this.readUByte();
                    if (b != 8) {
                        throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), "unsupported compression method " + b);
                    }
                    final int flags = this.readUByte();
                    if ((flags & 0x2) != 0x0) {
                        throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), "multi-part gzip file is not supported");
                    }
                    if ((flags & 0x20) != 0x0) {
                        throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), "encrypted gzip file is not supported");
                    }
                    if ((flags & 0xFFFFFFC0) != 0x0) {
                        throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), "unknown flags " + flags);
                    }
                    RubyGzipReader.this.mtime.setDateTime(new DateTime(this.readUInt() * 1000L));
                    final int extraflags = this.readUByte();
                    if ((extraflags & 0x4) != 0x0) {
                        RubyGzipReader.this.level = 1;
                    }
                    else if ((extraflags & 0x2) != 0x0) {
                        RubyGzipReader.this.level = 9;
                    }
                    else {
                        RubyGzipReader.this.level = -1;
                    }
                    RubyGzipReader.this.osCode = (byte)this.readUByte();
                    if ((flags & 0x4) != 0x0) {
                        final int size = this.readUShort();
                        final byte[] extra = new byte[2 + size];
                        this.readBytes(extra);
                    }
                    if ((flags & 0x8) != 0x0) {
                        (RubyGzipReader.this.nullFreeOrigName = this.countingStream.getRuntime().newString(this.readNullTerminateString())).setTaint(true);
                    }
                    if ((flags & 0x10) != 0x0) {
                        (RubyGzipReader.this.nullFreeComment = this.countingStream.getRuntime().newString(this.readNullTerminateString())).setTaint(true);
                    }
                }
                catch (IOException ioe) {
                    throw RubyGzipFile.newGzipFileError(this.countingStream.getRuntime(), ioe.getMessage());
                }
            }
            
            private int readUByte() throws IOException {
                final int ret = this.in.read();
                if (ret == -1) {
                    throw new EOFException();
                }
                this.checksum.update((byte)(ret & 0xFF));
                return ret & 0xFF;
            }
            
            private int readUShort() throws IOException {
                return (this.readUByte() | this.readUByte() << 8) & 0xFFFF;
            }
            
            private long readUInt() throws IOException {
                return (this.readUShort() | this.readUShort() << 16) & 0xFFFFFFFFL;
            }
            
            private void readBytes(final byte[] bytes) throws IOException {
                this.readBytes(bytes, 0, bytes.length, true);
            }
            
            private void readBytes(final byte[] bytes, int pos, int len, final boolean updateChecksum) throws IOException {
                if (bytes.length < pos + len) {
                    throw new IllegalArgumentException();
                }
                while (len > 0) {
                    final int ret = this.in.read(bytes, pos, len);
                    if (ret == -1) {
                        throw new EOFException();
                    }
                    if (updateChecksum) {
                        this.checksum.update(bytes, pos, ret);
                    }
                    pos += ret;
                    len -= ret;
                }
            }
            
            private String readNullTerminateString() throws IOException {
                final StringBuilder builder = new StringBuilder();
                int c;
                while ((c = this.readUByte()) != 0) {
                    builder.append((char)c);
                }
                return builder.toString();
            }
            
            private void readTrailer() throws IOException {
                try {
                    this.eof = true;
                    int rest = 8;
                    final byte[] trailer = new byte[8];
                    final int remaining = super.inf.getRemaining();
                    if (remaining > 0) {
                        System.arraycopy(super.buf, super.len - remaining, trailer, 0, (remaining > 8) ? 8 : remaining);
                        rest -= remaining;
                    }
                    if (rest > 0) {
                        this.readBytes(trailer, 8 - rest, rest, false);
                    }
                    long uint = this.bytesToUInt(trailer, 0);
                    if (uint != this.checksum.getValue()) {
                        throw RubyGzipFile.newCRCError(this.countingStream.getRuntime(), "invalid compressed data -- crc error");
                    }
                    uint = this.bytesToUInt(trailer, 4);
                    if (uint != (super.inf.getBytesWritten() & 0xFFFFFFFFL)) {
                        throw RubyGzipFile.newLengthError(this.countingStream.getRuntime(), "invalid compressed data -- length error");
                    }
                }
                catch (IOException ignored) {
                    throw RubyGzipFile.newNoFooter(this.countingStream.getRuntime(), "footer is not found");
                }
            }
            
            private long bytesToUInt(final byte[] bytes, int pos) {
                if (bytes.length < pos + 4) {
                    throw new IllegalArgumentException();
                }
                return ((bytes[pos++] & 0xFF) | (bytes[pos++] & 0xFF) << 8 | (bytes[pos++] & 0xFF) << 16 | (bytes[pos++] & 0xFF) << 24) & 0xFFFFFFFFL;
            }
        }
    }
    
    @JRubyClass(name = { "Zlib::GzipWriter" }, parent = "Zlib::GzipFile")
    public static class RubyGzipWriter extends RubyGzipFile
    {
        protected static final ObjectAllocator GZIPWRITER_ALLOCATOR;
        private HeaderModifyableGZIPOutputStream io;
        
        @JRubyMethod(name = { "new" }, rest = true, meta = true)
        public static RubyGzipWriter newGzipWriter(final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final RubyClass klass = (RubyClass)recv;
            final RubyGzipWriter result = (RubyGzipWriter)klass.allocate();
            result.callInit(args, block);
            return result;
        }
        
        @JRubyMethod(required = 1, optional = 2, meta = true)
        public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
            final Ruby runtime = recv.getRuntime();
            IRubyObject level = runtime.getNil();
            IRubyObject strategy = runtime.getNil();
            if (args.length > 1) {
                level = args[1];
                RubyGzipFile.checkLevel(context.getRuntime(), RubyNumeric.fix2int(level));
                if (args.length > 2) {
                    strategy = args[2];
                }
            }
            final IRubyObject io = RuntimeHelpers.invoke(context, runtime.getFile(), "open", args[0], runtime.newString("wb"));
            final RubyGzipWriter gzio = newGzipWriter(recv, new IRubyObject[] { io, level, strategy }, block);
            return wrapBlock(context, gzio, block);
        }
        
        public RubyGzipWriter(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        @JRubyMethod(required = 1, rest = true, visibility = Visibility.PRIVATE)
        public IRubyObject initialize(final IRubyObject[] args, final Block unusedBlock) {
            this.realIo = args[0];
            try {
                this.io = new HeaderModifyableGZIPOutputStream(this.realIo);
                return this;
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        @JRubyMethod(name = { "close" })
        public IRubyObject close() {
            if (!this.closed) {
                try {
                    this.io.close();
                }
                catch (IOException ioe) {
                    throw this.getRuntime().newIOErrorFromException(ioe);
                }
            }
            this.closed = true;
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "append", "<<" }, required = 1)
        public IRubyObject append(final IRubyObject p1) {
            this.write(p1);
            return this;
        }
        
        @JRubyMethod(name = { "printf" }, required = 1, rest = true)
        public IRubyObject printf(final ThreadContext context, final IRubyObject[] args) {
            this.write(RubyKernel.sprintf(context, this, args));
            return context.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "print" }, rest = true)
        public IRubyObject print(final IRubyObject[] args) {
            if (args.length != 0) {
                for (int i = 0, j = args.length; i < j; ++i) {
                    this.write(args[i]);
                }
            }
            final IRubyObject sep = this.getRuntime().getGlobalVariables().get("$\\");
            if (!sep.isNil()) {
                this.write(sep);
            }
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "pos", "tell" })
        public IRubyObject pos() {
            return RubyNumeric.int2fix(this.getRuntime(), this.io.pos());
        }
        
        @JRubyMethod(name = { "orig_name=" }, required = 1)
        public IRubyObject set_orig_name(final IRubyObject obj) {
            if (this.io.headerIsWritten()) {
                throw RubyGzipFile.newGzipFileError(this.getRuntime(), "header is already written");
            }
            this.ensureNonNull(this.nullFreeOrigName = obj.convertToString());
            return obj;
        }
        
        @JRubyMethod(name = { "comment=" }, required = 1)
        public IRubyObject set_comment(final IRubyObject obj) {
            if (this.io.headerIsWritten()) {
                throw RubyGzipFile.newGzipFileError(this.getRuntime(), "header is already written");
            }
            this.ensureNonNull(this.nullFreeComment = obj.convertToString());
            return obj;
        }
        
        private void ensureNonNull(final RubyString obj) {
            final String str = obj.toString();
            if (str.indexOf(0) >= 0) {
                final String trim = str.substring(0, str.toString().indexOf(0));
                obj.setValue(new ByteList(trim.getBytes()));
            }
        }
        
        @JRubyMethod(name = { "putc" }, required = 1)
        public IRubyObject putc(final IRubyObject p1) {
            try {
                this.io.write(RubyNumeric.fix2int(p1));
                return p1;
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        @JRubyMethod(name = { "puts" }, rest = true)
        public IRubyObject puts(final ThreadContext context, final IRubyObject[] args) {
            final RubyStringIO sio = (RubyStringIO)this.getRuntime().fastGetClass("StringIO").newInstance(context, new IRubyObject[0], Block.NULL_BLOCK);
            sio.puts(context, args);
            this.write(sio.string());
            return this.getRuntime().getNil();
        }
        
        public IRubyObject finish() {
            if (!this.finished) {
                try {
                    this.io.finish();
                }
                catch (IOException ioe) {
                    throw this.getRuntime().newIOErrorFromException(ioe);
                }
            }
            this.finished = true;
            return this.realIo;
        }
        
        @JRubyMethod(name = { "flush" }, optional = 1)
        public IRubyObject flush(final IRubyObject[] args) {
            if (args.length != 0 && !args[0].isNil()) {
                if (RubyNumeric.fix2int(args[0]) == 0) {
                    return this.getRuntime().getNil();
                }
            }
            try {
                this.io.flush();
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "mtime=" }, required = 1)
        public IRubyObject set_mtime(final IRubyObject arg) {
            if (this.io.headerIsWritten()) {
                throw RubyGzipFile.newGzipFileError(this.getRuntime(), "header is already written");
            }
            if (arg instanceof RubyTime) {
                this.mtime = (RubyTime)arg;
            }
            else if (!arg.isNil()) {
                this.mtime.setDateTime(new DateTime(RubyNumeric.fix2long(arg) * 1000L));
            }
            this.io.setModifiedTime(this.mtime.to_i().getLongValue());
            return this.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "crc" })
        public IRubyObject crc() {
            return this.getRuntime().newFixnum(this.io.crc());
        }
        
        @JRubyMethod(name = { "write" }, required = 1)
        public IRubyObject write(final IRubyObject p1) {
            final ByteList bytes = p1.asString().getByteList();
            try {
                this.io.write(bytes.getUnsafeBytes(), bytes.begin(), bytes.length());
                return this.getRuntime().newFixnum(bytes.length());
            }
            catch (IOException ioe) {
                throw this.getRuntime().newIOErrorFromException(ioe);
            }
        }
        
        static {
            GZIPWRITER_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new RubyGzipWriter(runtime, klass);
                }
            };
        }
        
        public class HeaderModifyableGZIPOutputStream extends DeflaterOutputStream
        {
            private IRubyObject io;
            private long position;
            private CRC32 checksum;
            private boolean headerIsWritten;
            private long modifiedTime;
            private static final int DEFAULT_BUFFER_SIZE = 512;
            
            public HeaderModifyableGZIPOutputStream(final IRubyObject io) throws IOException {
                super(new IOOutputStream(io, false, false), new Deflater(-1, true), 512);
                this.checksum = new CRC32();
                this.headerIsWritten = false;
                this.modifiedTime = System.currentTimeMillis();
                this.io = io;
                this.position = 0L;
            }
            
            public void close() throws IOException {
                if (!RubyGzipWriter.this.closed) {
                    this.finish();
                    RubyGzipWriter.this.closed = true;
                }
                if (this.io.respondsTo("close")) {
                    this.io.callMethod(this.io.getRuntime().getCurrentContext(), "close");
                }
            }
            
            public synchronized void write(final byte[] bytes, final int offset, final int length) throws IOException {
                this.writeHeaderIfNeeded();
                super.write(bytes, offset, length);
                this.checksum.update(bytes, offset, length);
                this.position += length;
            }
            
            public void finish() throws IOException {
                this.writeHeaderIfNeeded();
                super.finish();
                this.writeTrailer();
            }
            
            public void setModifiedTime(final long newModifiedTime) {
                this.modifiedTime = newModifiedTime;
            }
            
            public boolean headerIsWritten() {
                return this.headerIsWritten;
            }
            
            public long crc() {
                return this.checksum.getValue();
            }
            
            public long pos() {
                return this.position;
            }
            
            private void writeHeaderIfNeeded() throws IOException {
                if (!this.headerIsWritten) {
                    this.writeHeader();
                    this.headerIsWritten = true;
                }
            }
            
            private void writeHeader() throws IOException {
                byte flags = 0;
                byte extraflags = 0;
                if (RubyGzipWriter.this.nullFreeOrigName != null) {
                    flags |= 0x8;
                }
                if (RubyGzipWriter.this.nullFreeComment != null) {
                    flags |= 0x10;
                }
                if (RubyGzipWriter.this.level == 1) {
                    extraflags |= 0x4;
                }
                else if (RubyGzipWriter.this.level == 9) {
                    extraflags |= 0x2;
                }
                final byte[] header = { 31, -117, 8, flags, (byte)this.modifiedTime, (byte)(this.modifiedTime >> 8), (byte)(this.modifiedTime >> 16), (byte)(this.modifiedTime >> 24), extraflags, 11 };
                this.out.write(header);
                if (RubyGzipWriter.this.nullFreeOrigName != null) {
                    this.out.write(RubyGzipWriter.this.nullFreeOrigName.toString().getBytes());
                    this.out.write(0);
                }
                if (RubyGzipWriter.this.nullFreeComment != null) {
                    this.out.write(RubyGzipWriter.this.nullFreeComment.toString().getBytes());
                    this.out.write(0);
                }
            }
            
            private void writeTrailer() throws IOException {
                final int originalDataSize = this.def.getTotalIn();
                final int checksumInt = (int)this.checksum.getValue();
                final byte[] trailer = { (byte)checksumInt, (byte)(checksumInt >> 8), (byte)(checksumInt >> 16), (byte)(checksumInt >> 24), (byte)originalDataSize, (byte)(originalDataSize >> 8), (byte)(originalDataSize >> 16), (byte)(originalDataSize >> 24) };
                this.out.write(trailer);
            }
        }
    }
}
