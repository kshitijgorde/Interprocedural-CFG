// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.HashMap;
import java.io.IOException;
import org.jruby.runtime.marshal.UnmarshalStream;
import org.jruby.common.IRubyWarnings;
import org.jruby.util.Numeric;
import org.jruby.util.ConvertBytes;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import java.math.BigInteger;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.TypeCoercer;
import java.util.Map;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Fixnum" }, parent = "Integer", include = { "Precision" })
public class RubyFixnum extends RubyInteger
{
    private final long value;
    private static final int BIT_SIZE = 64;
    public static final long SIGN_BIT = Long.MIN_VALUE;
    public static final long MAX = Long.MAX_VALUE;
    public static final long MIN = Long.MIN_VALUE;
    public static final long MAX_MARSHAL_FIXNUM = 1073741823L;
    public static final long MIN_MARSHAL_FIXNUM = -1073741824L;
    public static final int CACHE_OFFSET = 256;
    private static final Map<Class, TypeCoercer> JAVA_COERCERS;
    
    public static RubyClass createFixnumClass(final Ruby runtime) {
        final RubyClass fixnum = runtime.defineClass("Fixnum", runtime.getInteger(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setFixnum(fixnum);
        fixnum.index = 1;
        fixnum.setReifiedClass(RubyFixnum.class);
        fixnum.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyFixnum;
            }
        };
        if (!runtime.is1_9()) {
            fixnum.includeModule(runtime.getPrecision());
        }
        fixnum.defineAnnotatedMethods(RubyFixnum.class);
        for (int i = 0; i < runtime.fixnumCache.length; ++i) {
            runtime.fixnumCache[i] = new RubyFixnum(fixnum, i - 256);
        }
        return fixnum;
    }
    
    private static IRubyObject fixCoerce(IRubyObject x) {
        do {
            x = x.convertToInteger();
        } while (!(x instanceof RubyFixnum) && !(x instanceof RubyBignum));
        return x;
    }
    
    public RubyFixnum(final Ruby runtime) {
        this(runtime, 0L);
    }
    
    public RubyFixnum(final Ruby runtime, final long value) {
        super(runtime.getFixnum());
        this.value = value;
    }
    
    private RubyFixnum(final RubyClass klazz, final long value) {
        super(klazz);
        this.value = value;
    }
    
    public int getNativeTypeIndex() {
        return 1;
    }
    
    public final boolean eql(final IRubyObject other) {
        return other instanceof RubyFixnum && this.value == ((RubyFixnum)other).value;
    }
    
    public IRubyObject equal_p(final ThreadContext context, final IRubyObject obj) {
        return context.getRuntime().newBoolean(this == obj || this.eql(obj));
    }
    
    public boolean isImmediate() {
        return true;
    }
    
    public RubyClass getSingletonClass() {
        throw this.getRuntime().newTypeError("can't define singleton");
    }
    
    public Class<?> getJavaClass() {
        return Long.TYPE;
    }
    
    public double getDoubleValue() {
        return this.value;
    }
    
    public long getLongValue() {
        return this.value;
    }
    
    public BigInteger getBigIntegerValue() {
        return BigInteger.valueOf(this.value);
    }
    
    public static RubyFixnum newFixnum(final Ruby runtime, final long value) {
        if (isInCacheRange(value)) {
            return runtime.fixnumCache[(int)value + 256];
        }
        return new RubyFixnum(runtime, value);
    }
    
    private static boolean isInCacheRange(final long value) {
        return value <= 255L && value >= -256L;
    }
    
    public RubyFixnum newFixnum(final long newValue) {
        return newFixnum(this.getRuntime(), newValue);
    }
    
    public static RubyFixnum zero(final Ruby runtime) {
        return runtime.fixnumCache[256];
    }
    
    public static RubyFixnum one(final Ruby runtime) {
        return runtime.fixnumCache[257];
    }
    
    public static RubyFixnum two(final Ruby runtime) {
        return runtime.fixnumCache[258];
    }
    
    public static RubyFixnum three(final Ruby runtime) {
        return runtime.fixnumCache[259];
    }
    
    public static RubyFixnum four(final Ruby runtime) {
        return runtime.fixnumCache[260];
    }
    
    public static RubyFixnum five(final Ruby runtime) {
        return runtime.fixnumCache[261];
    }
    
    public static RubyFixnum minus_one(final Ruby runtime) {
        return runtime.fixnumCache[255];
    }
    
    public RubyFixnum hash() {
        return this.newFixnum(this.hashCode());
    }
    
    public final int hashCode() {
        return (int)(this.value ^ this.value >>> 32);
    }
    
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof RubyFixnum) {
            final RubyFixnum num = (RubyFixnum)other;
            if (num.value == this.value) {
                return true;
            }
        }
        return false;
    }
    
    @JRubyMethod
    public IRubyObject times(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            final Ruby runtime = context.getRuntime();
            final long lvalue = this.value;
            if (block.getBody().getArgumentType() == 0) {
                final IRubyObject nil = runtime.getNil();
                for (long i = 0L; i < lvalue; ++i) {
                    block.yield(context, nil);
                }
            }
            else {
                for (long j = 0L; j < lvalue; ++j) {
                    block.yield(context, newFixnum(runtime, j));
                }
            }
            return this;
        }
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "times");
    }
    
    public RubyString to_s(final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.to_s();
            }
            case 1: {
                return this.to_s(args[0]);
            }
            default: {
                throw this.getRuntime().newArgumentError(args.length, 1);
            }
        }
    }
    
    @JRubyMethod
    public RubyString to_s() {
        final int base = 10;
        return this.getRuntime().newString(ConvertBytes.longToByteList(this.value, base));
    }
    
    @JRubyMethod
    public RubyString to_s(final IRubyObject arg0) {
        final int base = RubyNumeric.num2int(arg0);
        if (base < 2 || base > 36) {
            throw this.getRuntime().newArgumentError("illegal radix " + base);
        }
        return this.getRuntime().newString(ConvertBytes.longToByteList(this.value, base));
    }
    
    @JRubyMethod
    public IRubyObject id2name() {
        final RubySymbol symbol = RubySymbol.getSymbolLong(this.getRuntime(), this.value);
        if (symbol != null) {
            return this.getRuntime().newString(symbol.asJavaString());
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject to_sym() {
        final RubySymbol symbol = RubySymbol.getSymbolLong(this.getRuntime(), this.value);
        return (symbol != null) ? symbol : this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "-@" })
    public IRubyObject op_uminus() {
        if (this.value == Long.MIN_VALUE) {
            return RubyBignum.newBignum(this.getRuntime(), BigInteger.valueOf(this.value).negate());
        }
        return newFixnum(this.getRuntime(), -this.value);
    }
    
    @JRubyMethod(name = { "+" })
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.addFixnum(context, (RubyFixnum)other);
        }
        return this.addOther(context, other);
    }
    
    public IRubyObject op_plus(final ThreadContext context, final long otherValue) {
        final long result = this.value + otherValue;
        if (additionOverflowed(this.value, otherValue, result)) {
            return this.addAsBignum(context, otherValue);
        }
        return newFixnum(context.getRuntime(), result);
    }
    
    private IRubyObject addFixnum(final ThreadContext context, final RubyFixnum other) {
        final long otherValue = other.value;
        final long result = this.value + otherValue;
        if (additionOverflowed(this.value, otherValue, result)) {
            return this.addAsBignum(context, other);
        }
        return newFixnum(context.getRuntime(), result);
    }
    
    private static boolean additionOverflowed(final long original, final long other, final long result) {
        return (~(original ^ other) & (original ^ result) & Long.MIN_VALUE) != 0x0L;
    }
    
    private static boolean subtractionOverflowed(final long original, final long other, final long result) {
        return (~(original ^ ~other) & (original ^ result) & Long.MIN_VALUE) != 0x0L;
    }
    
    private IRubyObject addAsBignum(final ThreadContext context, final RubyFixnum other) {
        return RubyBignum.newBignum(context.getRuntime(), this.value).op_plus(context, other);
    }
    
    private IRubyObject addAsBignum(final ThreadContext context, final long other) {
        return RubyBignum.newBignum(context.getRuntime(), this.value).op_plus(context, other);
    }
    
    private IRubyObject addOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return ((RubyBignum)other).op_plus(context, this);
        }
        if (other instanceof RubyFloat) {
            return context.getRuntime().newFloat(this.value + ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceBin(context, "+", other);
    }
    
    @JRubyMethod(name = { "-" })
    public IRubyObject op_minus(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.subtractFixnum(context, (RubyFixnum)other);
        }
        return this.subtractOther(context, other);
    }
    
    public IRubyObject op_minus(final ThreadContext context, final long otherValue) {
        final long result = this.value - otherValue;
        if (subtractionOverflowed(this.value, otherValue, result)) {
            return this.subtractAsBignum(context, otherValue);
        }
        return newFixnum(context.getRuntime(), result);
    }
    
    private IRubyObject subtractFixnum(final ThreadContext context, final RubyFixnum other) {
        final long otherValue = other.value;
        final long result = this.value - otherValue;
        if (subtractionOverflowed(this.value, otherValue, result)) {
            return this.subtractAsBignum(context, other);
        }
        return newFixnum(context.getRuntime(), result);
    }
    
    private IRubyObject subtractAsBignum(final ThreadContext context, final RubyFixnum other) {
        return RubyBignum.newBignum(context.getRuntime(), this.value).op_minus(context, other);
    }
    
    private IRubyObject subtractAsBignum(final ThreadContext context, final long other) {
        return RubyBignum.newBignum(context.getRuntime(), this.value).op_minus(context, other);
    }
    
    private IRubyObject subtractOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return RubyBignum.newBignum(context.getRuntime(), this.value).op_minus(context, other);
        }
        if (other instanceof RubyFloat) {
            return context.getRuntime().newFloat(this.value - ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceBin(context, "-", other);
    }
    
    @JRubyMethod(name = { "*" })
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.multiplyFixnum(context, other);
        }
        return this.multiplyOther(context, other);
    }
    
    private IRubyObject multiplyFixnum(final ThreadContext context, final IRubyObject other) {
        return this.op_mul(context, ((RubyFixnum)other).value);
    }
    
    private IRubyObject multiplyOther(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyBignum) {
            return ((RubyBignum)other).op_mul(context, this);
        }
        if (other instanceof RubyFloat) {
            return runtime.newFloat(this.value * ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceBin(context, "*", other);
    }
    
    public IRubyObject op_mul(final ThreadContext context, final long otherValue) {
        final Ruby runtime = context.getRuntime();
        final long result = this.value * otherValue;
        if (this.value != 0L && result / this.value != otherValue) {
            return RubyBignum.newBignum(runtime, this.value).op_mul(context, otherValue);
        }
        return newFixnum(runtime, result);
    }
    
    @JRubyMethod(name = { "div" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject div_div(final ThreadContext context, final IRubyObject other) {
        return this.idiv(context, other, "div");
    }
    
    @JRubyMethod(name = { "div" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject div_div19(final ThreadContext context, final IRubyObject other) {
        this.checkZeroDivisionError(context, other);
        return this.div_div(context, other);
    }
    
    @JRubyMethod(name = { "/" })
    public IRubyObject op_div(final ThreadContext context, final IRubyObject other) {
        return this.idiv(context, other, "/");
    }
    
    public IRubyObject op_div(final ThreadContext context, final long other) {
        return this.idiv(context, other, "/");
    }
    
    @JRubyMethod(name = { "odd?" }, compat = CompatVersion.RUBY1_9)
    public RubyBoolean odd_p() {
        if (this.value % 2L != 0L) {
            return this.getRuntime().getTrue();
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "even?" }, compat = CompatVersion.RUBY1_9)
    public RubyBoolean even_p() {
        if (this.value % 2L == 0L) {
            return this.getRuntime().getTrue();
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject pred() {
        return this.getRuntime().newFixnum(this.value - 1L);
    }
    
    public IRubyObject idiv(final ThreadContext context, final IRubyObject other, final String method) {
        if (other instanceof RubyFixnum) {
            return this.idivLong(context, this.value, ((RubyFixnum)other).value);
        }
        return this.coerceBin(context, method, other);
    }
    
    public IRubyObject idiv(final ThreadContext context, final long y, final String method) {
        final long x = this.value;
        return this.idivLong(context, x, y);
    }
    
    private IRubyObject idivLong(final ThreadContext context, final long x, final long y) {
        if (y == 0L) {
            throw context.getRuntime().newZeroDivisionError();
        }
        long div = x / y;
        final long mod = x % y;
        if ((mod < 0L && y > 0L) || (mod > 0L && y < 0L)) {
            --div;
        }
        return context.getRuntime().newFixnum(div);
    }
    
    @JRubyMethod(name = { "%", "modulo" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_mod(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.moduloFixnum(context, (RubyFixnum)other);
        }
        return this.coerceBin(context, "%", other);
    }
    
    public IRubyObject op_mod(final ThreadContext context, final long other) {
        return this.moduloFixnum(context, other);
    }
    
    @JRubyMethod(name = { "%", "modulo" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_mod19(final ThreadContext context, final IRubyObject other) {
        if (context.runtime.is1_9()) {
            this.checkZeroDivisionError(context, other);
        }
        return this.op_mod(context, other);
    }
    
    private IRubyObject moduloFixnum(final ThreadContext context, final RubyFixnum other) {
        return this.moduloFixnum(context, other.value);
    }
    
    private IRubyObject moduloFixnum(final ThreadContext context, final long other) {
        final long x = this.value;
        if (other == 0L) {
            throw context.getRuntime().newZeroDivisionError();
        }
        long mod = x % other;
        if ((mod < 0L && other > 0L) || (mod > 0L && other < 0L)) {
            mod += other;
        }
        return context.getRuntime().newFixnum(mod);
    }
    
    @JRubyMethod(name = { "divmod" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject divmod(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.divmodFixnum(context, other);
        }
        return this.coerceBin(context, "divmod", other);
    }
    
    @JRubyMethod(name = { "divmod" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject divmod19(final ThreadContext context, final IRubyObject other) {
        this.checkZeroDivisionError(context, other);
        return this.divmod(context, other);
    }
    
    private IRubyObject divmodFixnum(final ThreadContext context, final IRubyObject other) {
        final long x = this.value;
        final long y = ((RubyFixnum)other).value;
        final Ruby runtime = context.getRuntime();
        if (y == 0L) {
            throw runtime.newZeroDivisionError();
        }
        long div = x / y;
        long mod = x % y;
        if ((mod < 0L && y > 0L) || (mod > 0L && y < 0L)) {
            --div;
            mod += y;
        }
        final IRubyObject fixDiv = newFixnum(runtime, div);
        final IRubyObject fixMod = newFixnum(runtime, mod);
        return RubyArray.newArray(runtime, fixDiv, fixMod);
    }
    
    @JRubyMethod(name = { "quo" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject quo(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyFloat.newFloat(context.getRuntime(), this.value / ((RubyFixnum)other).value);
        }
        return this.coerceBin(context, "quo", other);
    }
    
    @JRubyMethod(name = { "**" })
    public IRubyObject op_pow(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.powerFixnum(context, ((RubyFixnum)other).value);
        }
        return this.powerOther(context, other);
    }
    
    public IRubyObject op_pow(final ThreadContext context, final long other) {
        return this.powerFixnum(context, other);
    }
    
    private IRubyObject powerFixnum(final ThreadContext context, final long other) {
        final Ruby runtime = context.getRuntime();
        if (other == 0L) {
            return one(runtime);
        }
        if (other == 1L) {
            return this;
        }
        if (other > 0L) {
            return RubyBignum.newBignum(runtime, this.value).op_pow(context, other);
        }
        return RubyFloat.newFloat(runtime, Math.pow(this.value, other));
    }
    
    private IRubyObject powerOther(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyFloat) {
            return RubyFloat.newFloat(runtime, Math.pow(this.value, ((RubyFloat)other).getDoubleValue()));
        }
        return this.coerceBin(context, "**", other);
    }
    
    @JRubyMethod(name = { "**" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_pow_19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyNumeric) {
            final double d_other = ((RubyNumeric)other).getDoubleValue();
            if (this.value < 0L && d_other != Math.round(d_other)) {
                return RubyComplex.newComplexRaw(this.getRuntime(), this).callMethod(context, "**", other);
            }
            if (other instanceof RubyFixnum) {
                return this.powerFixnum19(context, other);
            }
        }
        return this.powerOther19(context, other);
    }
    
    private IRubyObject powerOther19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        final long a = this.value;
        if (other instanceof RubyBignum) {
            if (other.callMethod(context, "<", zero(runtime)).isTrue()) {
                return RubyRational.newRationalRaw(runtime, this).callMethod(context, "**", other);
            }
            if (a == 0L) {
                return zero(runtime);
            }
            if (a == 1L) {
                return one(runtime);
            }
            if (a == -1L) {
                return ((RubyBignum)other).even_p(context).isTrue() ? one(runtime) : minus_one(runtime);
            }
            RubyBignum.newBignum(runtime, RubyBignum.fix2big(this)).op_pow(context, other);
        }
        else if (other instanceof RubyFloat) {
            final double b = ((RubyFloat)other).getValue();
            if (b == 0.0 || a == 1L) {
                return runtime.newFloat(1.0);
            }
            if (a == 0L) {
                return runtime.newFloat((b < 0.0) ? Double.POSITIVE_INFINITY : 0.0);
            }
            return RubyFloat.newFloat(runtime, Math.pow(a, b));
        }
        return this.coerceBin(context, "**", other);
    }
    
    private IRubyObject powerFixnum19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        final long a = this.value;
        final long b = ((RubyFixnum)other).value;
        if (b < 0L) {
            return RubyRational.newRationalRaw(runtime, this).callMethod(context, "**", other);
        }
        if (b == 0L) {
            return one(runtime);
        }
        if (b == 1L) {
            return this;
        }
        if (a == 0L) {
            return (b > 0L) ? zero(runtime) : RubyNumeric.dbl2num(runtime, Double.POSITIVE_INFINITY);
        }
        if (a == 1L) {
            return one(runtime);
        }
        if (a == -1L) {
            return (b % 2L == 0L) ? one(runtime) : minus_one(runtime);
        }
        return Numeric.int_pow(context, a, b);
    }
    
    @JRubyMethod
    public IRubyObject abs(final ThreadContext context) {
        if (this.value >= 0L) {
            return this;
        }
        if (this.value == Long.MIN_VALUE) {
            return RubyBignum.newBignum(context.getRuntime(), BigInteger.valueOf(this.value).negate());
        }
        return newFixnum(context.getRuntime(), -this.value);
    }
    
    @JRubyMethod(name = { "magnitude" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject magnitude(final ThreadContext context) {
        return this.abs(context);
    }
    
    @JRubyMethod(name = { "==" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.op_equal(context, ((RubyFixnum)other).value);
        }
        return super.op_num_equal(context, other);
    }
    
    public IRubyObject op_equal(final ThreadContext context, final long other) {
        return RubyBoolean.newBoolean(context.getRuntime(), this.value == other);
    }
    
    public boolean fastEqual(final RubyFixnum other) {
        return this.value == other.value;
    }
    
    @JRubyMethod(name = { "==" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_equal19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.op_equal(context, ((RubyFixnum)other).value);
        }
        return this.op_equalOther(context, other);
    }
    
    private IRubyObject op_equalOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return RubyBoolean.newBoolean(context.getRuntime(), BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue()) == 0);
        }
        if (other instanceof RubyFloat) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value == ((RubyFloat)other).getDoubleValue());
        }
        return super.op_num_equal(context, other);
    }
    
    public final int compareTo(final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            final long otherValue = ((RubyFixnum)other).value;
            return (this.value == otherValue) ? 0 : ((this.value > otherValue) ? 1 : -1);
        }
        return this.compareToOther(other);
    }
    
    private int compareToOther(final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue());
        }
        if (other instanceof RubyFloat) {
            return Double.compare(this.value, ((RubyFloat)other).getDoubleValue());
        }
        return (int)this.coerceCmp(this.getRuntime().getCurrentContext(), "<=>", other).convertToInteger().getLongValue();
    }
    
    @JRubyMethod(name = { "<=>" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.op_cmp(context, ((RubyFixnum)other).value);
        }
        return this.coerceCmp(context, "<=>", other);
    }
    
    public IRubyObject op_cmp(final ThreadContext context, final long other) {
        final Ruby runtime = context.runtime;
        return (this.value == other) ? zero(runtime) : ((this.value > other) ? one(runtime) : minus_one(runtime));
    }
    
    @JRubyMethod(name = { "<=>" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_cmp19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.op_cmp(context, ((RubyFixnum)other).value);
        }
        return this.compareOther(context, other);
    }
    
    private IRubyObject compareOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return newFixnum(context.getRuntime(), BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue()));
        }
        if (other instanceof RubyFloat) {
            return RubyNumeric.dbl_cmp(context.getRuntime(), this.value, ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceCmp(context, "<=>", other);
    }
    
    @JRubyMethod(name = { ">" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_gt(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value > ((RubyFixnum)other).value);
        }
        return this.coerceRelOp(context, ">", other);
    }
    
    public IRubyObject op_gt(final ThreadContext context, final long other) {
        return RubyBoolean.newBoolean(context.getRuntime(), this.value > other);
    }
    
    @JRubyMethod(name = { ">" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_gt19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value > ((RubyFixnum)other).value);
        }
        return this.op_gtOther(context, other);
    }
    
    private IRubyObject op_gtOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return RubyBoolean.newBoolean(context.getRuntime(), BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue()) > 0);
        }
        if (other instanceof RubyFloat) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value > ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceRelOp(context, ">", other);
    }
    
    @JRubyMethod(name = { ">=" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_ge(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value >= ((RubyFixnum)other).value);
        }
        return this.coerceRelOp(context, ">=", other);
    }
    
    public IRubyObject op_ge(final ThreadContext context, final long other) {
        return RubyBoolean.newBoolean(context.getRuntime(), this.value >= other);
    }
    
    @JRubyMethod(name = { ">=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_ge19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value >= ((RubyFixnum)other).value);
        }
        return this.op_geOther(context, other);
    }
    
    private IRubyObject op_geOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return RubyBoolean.newBoolean(context.getRuntime(), BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue()) >= 0);
        }
        if (other instanceof RubyFloat) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value >= ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceRelOp(context, ">=", other);
    }
    
    @JRubyMethod(name = { "<" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_lt(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value < ((RubyFixnum)other).value);
        }
        return this.coerceRelOp(context, "<", other);
    }
    
    public IRubyObject op_lt(final ThreadContext context, final long other) {
        return RubyBoolean.newBoolean(context.getRuntime(), this.value < other);
    }
    
    @JRubyMethod(name = { "<" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_lt19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value < ((RubyFixnum)other).value);
        }
        return this.op_ltOther(context, other);
    }
    
    private IRubyObject op_ltOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return RubyBoolean.newBoolean(context.getRuntime(), BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue()) < 0);
        }
        if (other instanceof RubyFloat) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value < ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceRelOp(context, "<", other);
    }
    
    @JRubyMethod(name = { "<=" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_le(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value <= ((RubyFixnum)other).value);
        }
        return this.coerceRelOp(context, "<=", other);
    }
    
    public IRubyObject op_le(final ThreadContext context, final long other) {
        return RubyBoolean.newBoolean(context.getRuntime(), this.value <= other);
    }
    
    @JRubyMethod(name = { "<=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_le19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value <= ((RubyFixnum)other).value);
        }
        return this.op_leOther(context, other);
    }
    
    private IRubyObject op_leOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return RubyBoolean.newBoolean(context.getRuntime(), BigInteger.valueOf(this.value).compareTo(((RubyBignum)other).getValue()) <= 0);
        }
        if (other instanceof RubyFloat) {
            return RubyBoolean.newBoolean(context.getRuntime(), this.value <= ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceRelOp(context, "<=", other);
    }
    
    @JRubyMethod(name = { "~" })
    public IRubyObject op_neg() {
        return this.newFixnum(~this.value);
    }
    
    @JRubyMethod(name = { "&" })
    public IRubyObject op_and(final ThreadContext context, IRubyObject other) {
        if (other instanceof RubyFixnum || (other = fixCoerce(other)) instanceof RubyFixnum) {
            return newFixnum(context.getRuntime(), this.value & ((RubyFixnum)other).value);
        }
        return ((RubyBignum)other).op_and(context, this);
    }
    
    public IRubyObject op_and(final ThreadContext context, final long other) {
        return newFixnum(context.getRuntime(), this.value & other);
    }
    
    @JRubyMethod(name = { "|" })
    public IRubyObject op_or(final ThreadContext context, IRubyObject other) {
        if (other instanceof RubyFixnum || (other = fixCoerce(other)) instanceof RubyFixnum) {
            return newFixnum(context.getRuntime(), this.value | ((RubyFixnum)other).value);
        }
        return ((RubyBignum)other).op_or(context, this);
    }
    
    public IRubyObject op_or(final ThreadContext context, final long other) {
        return newFixnum(context.getRuntime(), this.value | other);
    }
    
    @JRubyMethod(name = { "^" })
    public IRubyObject op_xor(final ThreadContext context, IRubyObject other) {
        if (other instanceof RubyFixnum || (other = fixCoerce(other)) instanceof RubyFixnum) {
            return newFixnum(context.getRuntime(), this.value ^ ((RubyFixnum)other).value);
        }
        return ((RubyBignum)other).op_xor(context, this);
    }
    
    public IRubyObject op_xor(final ThreadContext context, final long other) {
        return newFixnum(context.getRuntime(), this.value ^ other);
    }
    
    @JRubyMethod(name = { "[]" })
    public IRubyObject op_aref(IRubyObject other) {
        if (!(other instanceof RubyFixnum) && !((other = fixCoerce(other)) instanceof RubyFixnum)) {
            final RubyBignum big = (RubyBignum)other;
            final RubyObject tryFix = RubyBignum.bignorm(this.getRuntime(), big.getValue());
            if (!(tryFix instanceof RubyFixnum)) {
                return (big.getValue().signum() == 0 || this.value >= 0L) ? zero(this.getRuntime()) : one(this.getRuntime());
            }
        }
        final long otherValue = RubyNumeric.fix2long(other);
        if (otherValue < 0L) {
            return zero(this.getRuntime());
        }
        if (63L < otherValue) {
            return (this.value < 0L) ? one(this.getRuntime()) : zero(this.getRuntime());
        }
        return ((this.value & 1L << (int)otherValue) == 0x0L) ? zero(this.getRuntime()) : one(this.getRuntime());
    }
    
    @JRubyMethod(name = { "<<" })
    public IRubyObject op_lshift(final IRubyObject other) {
        if (!(other instanceof RubyFixnum)) {
            return RubyBignum.newBignum(this.getRuntime(), this.value).op_lshift(other);
        }
        return this.op_lshift(((RubyFixnum)other).getLongValue());
    }
    
    public IRubyObject op_lshift(final long width) {
        return (width < 0L) ? this.rshift(-width) : this.lshift(width);
    }
    
    private IRubyObject lshift(final long width) {
        if (width > 63L || (-1L << (int)(64L - width - 1L) & this.value) != 0x0L) {
            return RubyBignum.newBignum(this.getRuntime(), this.value).op_lshift(newFixnum(this.getRuntime(), width));
        }
        return newFixnum(this.getRuntime(), this.value << (int)width);
    }
    
    @JRubyMethod(name = { ">>" })
    public IRubyObject op_rshift(final IRubyObject other) {
        if (!(other instanceof RubyFixnum)) {
            return RubyBignum.newBignum(this.getRuntime(), this.value).op_rshift(other);
        }
        return this.op_rshift(((RubyFixnum)other).getLongValue());
    }
    
    public IRubyObject op_rshift(final long width) {
        if (width == 0L) {
            return this;
        }
        return (width < 0L) ? this.lshift(-width) : this.rshift(width);
    }
    
    private IRubyObject rshift(final long width) {
        if (width >= 63L) {
            return (this.value < 0L) ? minus_one(this.getRuntime()) : zero(this.getRuntime());
        }
        return newFixnum(this.getRuntime(), this.value >> (int)width);
    }
    
    @JRubyMethod
    public IRubyObject to_f() {
        return RubyFloat.newFloat(this.getRuntime(), this.value);
    }
    
    @JRubyMethod
    public IRubyObject size() {
        return this.newFixnum(8L);
    }
    
    @JRubyMethod(name = { "zero?" })
    public IRubyObject zero_p() {
        return RubyBoolean.newBoolean(this.getRuntime(), this.value == 0L);
    }
    
    @JRubyMethod
    public IRubyObject id() {
        if (this.value <= 4611686018427387903L && this.value >= -4611686018427387904L) {
            return this.newFixnum(2L * this.value + 1L);
        }
        return super.id();
    }
    
    public IRubyObject taint(final ThreadContext context) {
        return this;
    }
    
    public String asJavaString() {
        final Ruby runtime = this.getRuntime();
        if (runtime.is1_9()) {
            throw runtime.newTypeError(this.inspect().toString() + " is not a symbol");
        }
        runtime.getWarnings().warn(IRubyWarnings.ID.FIXNUMS_NOT_SYMBOLS, "do not use Fixnums as Symbols", new Object[0]);
        final RubySymbol symbol = RubySymbol.getSymbolLong(runtime, this.value);
        if (symbol == null) {
            throw runtime.newArgumentError("" + this.value + " is not a symbol");
        }
        return symbol.asJavaString();
    }
    
    public static RubyFixnum unmarshalFrom(final UnmarshalStream input) throws IOException {
        return input.getRuntime().newFixnum(input.unmarshalInt());
    }
    
    @JRubyMethod(name = { "induced_from" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject induced_from(final IRubyObject recv, final IRubyObject other) {
        return RubyNumeric.num2fix(other);
    }
    
    private static Object coerceToJavaType(final Ruby ruby, final RubyFixnum self, final Class javaClass) {
        if (!Number.class.isAssignableFrom(javaClass)) {
            throw ruby.newTypeError(javaClass.getCanonicalName() + " is not a numeric type");
        }
        final TypeCoercer coercer = RubyFixnum.JAVA_COERCERS.get(javaClass);
        if (coercer == null) {
            throw ruby.newTypeError("Cannot coerce Fixnum to " + javaClass.getCanonicalName());
        }
        return coercer.coerce(self);
    }
    
    private void checkZeroDivisionError(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFloat && ((RubyFloat)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
    }
    
    static {
        JAVA_COERCERS = new HashMap<Class, TypeCoercer>();
        final TypeCoercer intCoercer = new TypeCoercer() {
            public Object coerce(final IRubyObject self) {
                final RubyFixnum fixnum = (RubyFixnum)self;
                if (fixnum.value > 2147483647L) {
                    throw self.getRuntime().newRangeError("Fixnum " + fixnum.value + " is too large for Java int");
                }
                return (int)fixnum.value;
            }
        };
        RubyFixnum.JAVA_COERCERS.put(Integer.TYPE, intCoercer);
        RubyFixnum.JAVA_COERCERS.put(Integer.class, intCoercer);
    }
}
