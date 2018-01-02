// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.Numeric;
import org.jcodings.exception.EncodingException;
import org.jcodings.Encoding;
import org.jcodings.specific.USASCIIEncoding;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.ByteList;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Integer" }, parent = "Numeric", include = { "Precision" })
public abstract class RubyInteger extends RubyNumeric
{
    static final ByteList[] SINGLE_CHAR_BYTELISTS;
    static final ByteList[] SINGLE_CHAR_BYTELISTS19;
    
    public static RubyClass createIntegerClass(final Ruby runtime) {
        final RubyClass integer = runtime.defineClass("Integer", runtime.getNumeric(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setInteger(integer);
        integer.index = 16;
        integer.setReifiedClass(RubyInteger.class);
        integer.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyInteger;
            }
        };
        integer.getSingletonClass().undefineMethod("new");
        if (!runtime.is1_9()) {
            integer.includeModule(runtime.getPrecision());
        }
        integer.defineAnnotatedMethods(RubyInteger.class);
        return integer;
    }
    
    public RubyInteger(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
    }
    
    public RubyInteger(final RubyClass rubyClass) {
        super(rubyClass);
    }
    
    public RubyInteger(final Ruby runtime, final RubyClass rubyClass, final boolean useObjectSpace) {
        super(runtime, rubyClass, useObjectSpace);
    }
    
    public RubyInteger(final Ruby runtime, final RubyClass rubyClass, final boolean useObjectSpace, final boolean canBeTainted) {
        super(runtime, rubyClass, useObjectSpace, canBeTainted);
    }
    
    public RubyInteger convertToInteger() {
        return this;
    }
    
    protected RubyFloat toFloat() {
        return RubyFloat.newFloat(this.getRuntime(), this.getDoubleValue());
    }
    
    @JRubyMethod(name = { "integer?" })
    public IRubyObject integer_p() {
        return this.getRuntime().getTrue();
    }
    
    @JRubyMethod
    public IRubyObject upto(final ThreadContext context, final IRubyObject to, final Block block) {
        if (block.isGiven()) {
            if (this instanceof RubyFixnum && to instanceof RubyFixnum) {
                fixnumUpto(context, ((RubyFixnum)this).getLongValue(), ((RubyFixnum)to).getLongValue(), block);
            }
            else {
                duckUpto(context, this, to, block);
            }
            return this;
        }
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "upto", to);
    }
    
    private static void fixnumUpto(final ThreadContext context, final long from, final long to, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (block.getBody().getArgumentType() == 0) {
            final IRubyObject nil = runtime.getNil();
            for (long i = from; i <= to; ++i) {
                block.yield(context, nil);
            }
        }
        else {
            for (long j = from; j <= to; ++j) {
                block.yield(context, RubyFixnum.newFixnum(runtime, j));
            }
        }
    }
    
    private static void duckUpto(final ThreadContext context, final IRubyObject from, final IRubyObject to, final Block block) {
        final Ruby runtime = context.getRuntime();
        IRubyObject i = from;
        for (RubyFixnum one = RubyFixnum.one(runtime); !i.callMethod(context, ">", to).isTrue(); i = i.callMethod(context, "+", one)) {
            block.yield(context, i);
        }
    }
    
    @JRubyMethod
    public IRubyObject downto(final ThreadContext context, final IRubyObject to, final Block block) {
        if (block.isGiven()) {
            if (this instanceof RubyFixnum && to instanceof RubyFixnum) {
                fixnumDownto(context, ((RubyFixnum)this).getLongValue(), ((RubyFixnum)to).getLongValue(), block);
            }
            else {
                duckDownto(context, this, to, block);
            }
            return this;
        }
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "downto", to);
    }
    
    private static void fixnumDownto(final ThreadContext context, final long from, final long to, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (block.getBody().getArgumentType() == 0) {
            final IRubyObject nil = runtime.getNil();
            for (long i = from; i >= to; --i) {
                block.yield(context, nil);
            }
        }
        else {
            for (long j = from; j >= to; --j) {
                block.yield(context, RubyFixnum.newFixnum(runtime, j));
            }
        }
    }
    
    private static void duckDownto(final ThreadContext context, final IRubyObject from, final IRubyObject to, final Block block) {
        final Ruby runtime = context.getRuntime();
        IRubyObject i = from;
        for (RubyFixnum one = RubyFixnum.one(runtime); !i.callMethod(context, "<", to).isTrue(); i = i.callMethod(context, "-", one)) {
            block.yield(context, i);
        }
    }
    
    @JRubyMethod
    public IRubyObject times(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            final Ruby runtime = context.getRuntime();
            IRubyObject i = RubyFixnum.zero(runtime);
            for (RubyFixnum one = RubyFixnum.one(runtime); i.callMethod(context, "<", this).isTrue(); i = i.callMethod(context, "+", one)) {
                block.yield(context, i);
            }
            return this;
        }
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "times");
    }
    
    @JRubyMethod(name = { "succ", "next" })
    public IRubyObject succ(final ThreadContext context) {
        if (this instanceof RubyFixnum) {
            return RubyFixnum.newFixnum(context.getRuntime(), this.getLongValue() + 1L);
        }
        return this.callMethod(context, "+", RubyFixnum.one(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "chr" }, compat = CompatVersion.RUBY1_8)
    public RubyString chr(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final long value = this.getLongValue();
        if (value < 0L || value > 255L) {
            throw runtime.newRangeError(this.toString() + " out of char range");
        }
        return RubyString.newStringShared(runtime, RubyInteger.SINGLE_CHAR_BYTELISTS[(int)value]);
    }
    
    @JRubyMethod(name = { "chr" }, compat = CompatVersion.RUBY1_9)
    public RubyString chr19(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final int value = (int)this.getLongValue();
        if (value >= 0 && value <= 255) {
            final ByteList bytes = RubyInteger.SINGLE_CHAR_BYTELISTS19[value];
            return RubyString.newStringShared(runtime, bytes, bytes.getEncoding());
        }
        Encoding enc = runtime.getDefaultInternalEncoding();
        if (value > 255 && (enc == null || enc == ASCIIEncoding.INSTANCE)) {
            throw runtime.newRangeError(this.toString() + " out of char range");
        }
        if (enc == null) {
            enc = USASCIIEncoding.INSTANCE;
        }
        return RubyString.newStringNoCopy(runtime, this.fromEncodedBytes(runtime, enc, value), enc, 0);
    }
    
    @JRubyMethod(name = { "chr" }, compat = CompatVersion.RUBY1_9)
    public RubyString chr19(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        final long value = this.getLongValue();
        Encoding enc;
        if (arg instanceof RubyEncoding) {
            enc = ((RubyEncoding)arg).getEncoding();
        }
        else {
            enc = arg.convertToString().toEncoding(runtime);
        }
        if (enc == ASCIIEncoding.INSTANCE && value >= 128L) {
            return this.chr19(context);
        }
        return RubyString.newStringNoCopy(runtime, this.fromEncodedBytes(runtime, enc, (int)value), enc, 0);
    }
    
    private ByteList fromEncodedBytes(final Ruby runtime, final Encoding enc, final int value) {
        int n;
        try {
            n = ((value < 0) ? 0 : enc.codeToMbcLength(value));
        }
        catch (EncodingException ee) {
            n = 0;
        }
        if (n <= 0) {
            throw runtime.newRangeError(this.toString() + " out of char range");
        }
        final ByteList bytes = new ByteList(n);
        enc.codeToMbc(value, bytes.getUnsafeBytes(), 0);
        bytes.setRealSize(n);
        return bytes;
    }
    
    @JRubyMethod(name = { "ord" })
    public IRubyObject ord(final ThreadContext context) {
        return this;
    }
    
    @JRubyMethod(name = { "to_i", "to_int", "floor", "ceil", "truncate" })
    public IRubyObject to_i() {
        return this;
    }
    
    @JRubyMethod(name = { "round" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject round() {
        return this;
    }
    
    @JRubyMethod(name = { "round" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject round19() {
        return this;
    }
    
    @JRubyMethod(name = { "round" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject round19(final ThreadContext context, final IRubyObject arg) {
        int ndigits = RubyNumeric.num2int(arg);
        if (ndigits > 0) {
            return RubyKernel.new_float(this, this);
        }
        if (ndigits == 0) {
            return this;
        }
        ndigits = -ndigits;
        final Ruby runtime = context.getRuntime();
        if (ndigits < 0) {
            throw runtime.newArgumentError("ndigits out of range");
        }
        final IRubyObject f = Numeric.int_pow(context, 10L, ndigits);
        if (this instanceof RubyFixnum && f instanceof RubyFixnum) {
            long x = ((RubyFixnum)this).getLongValue();
            final long y = ((RubyFixnum)f).getLongValue();
            final boolean neg = x < 0L;
            if (neg) {
                x = -x;
            }
            x = (x + y / 2L) / y * y;
            if (neg) {
                x = -x;
            }
            return RubyFixnum.newFixnum(runtime, x);
        }
        final IRubyObject h = f.callMethod(context, "/", RubyFixnum.two(runtime));
        final IRubyObject r = this.callMethod(context, "%", f);
        IRubyObject n = this.callMethod(context, "-", r);
        if (!r.callMethod(context, "<", h).isTrue()) {
            n = n.callMethod(context, "+", f);
        }
        return n;
    }
    
    @JRubyMethod(name = { "to_r" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_r(final ThreadContext context) {
        return RubyRational.newRationalCanonicalize(context, this);
    }
    
    @JRubyMethod(name = { "rationalize" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject rationalize(final ThreadContext context, final IRubyObject[] args) {
        return this.to_r(context);
    }
    
    @JRubyMethod(name = { "odd?" })
    public RubyBoolean odd_p(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.callMethod(context, "%", RubyFixnum.two(runtime)) != RubyFixnum.zero(runtime)) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(name = { "even?" })
    public RubyBoolean even_p(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.callMethod(context, "%", RubyFixnum.two(runtime)) == RubyFixnum.zero(runtime)) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    @JRubyMethod(name = { "pred" })
    public IRubyObject pred(final ThreadContext context) {
        return this.callMethod(context, "-", RubyFixnum.one(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "gcd" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gcd(final ThreadContext context, final IRubyObject other) {
        Numeric.checkInteger(context, other);
        return Numeric.f_gcd(context, this, RubyRational.intValue(context, other));
    }
    
    @JRubyMethod(name = { "lcm" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject lcm(final ThreadContext context, final IRubyObject other) {
        Numeric.checkInteger(context, other);
        return Numeric.f_lcm(context, this, RubyRational.intValue(context, other));
    }
    
    @JRubyMethod(name = { "gcdlcm" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject gcdlcm(final ThreadContext context, IRubyObject other) {
        Numeric.checkInteger(context, other);
        other = RubyRational.intValue(context, other);
        return context.getRuntime().newArray(Numeric.f_gcd(context, this, other), Numeric.f_lcm(context, this, other));
    }
    
    @JRubyMethod(name = { "numerator" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject numerator(final ThreadContext context) {
        return this;
    }
    
    @JRubyMethod(name = { "denominator" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject denominator(final ThreadContext context) {
        return RubyFixnum.one(context.getRuntime());
    }
    
    @JRubyMethod(name = { "induced_from" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject induced_from(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            return other;
        }
        if (other instanceof RubyFloat || other instanceof RubyRational) {
            return other.callMethod(context, "to_i");
        }
        throw recv.getRuntime().newTypeError("failed to convert " + other.getMetaClass().getName() + " into Integer");
    }
    
    static {
        SINGLE_CHAR_BYTELISTS = new ByteList[256];
        SINGLE_CHAR_BYTELISTS19 = new ByteList[256];
        for (int i = 0; i < 256; ++i) {
            final ByteList usascii = new ByteList(new byte[] { (byte)i }, false);
            RubyInteger.SINGLE_CHAR_BYTELISTS[i] = usascii;
            final int n;
            RubyInteger.SINGLE_CHAR_BYTELISTS19[n] = (((n = i) < 128) ? new ByteList(new byte[] { (byte)i }, USASCIIEncoding.INSTANCE) : new ByteList(new byte[] { (byte)i }, ASCIIEncoding.INSTANCE));
        }
    }
}
