// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.common.IRubyWarnings;
import java.math.BigDecimal;
import org.jruby.runtime.ObjectAllocator;
import java.math.BigInteger;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Bignum" }, parent = "Integer")
public class RubyBignum extends RubyInteger
{
    private static final int BIT_SIZE = 64;
    private static final long MAX = Long.MAX_VALUE;
    public static final BigInteger LONG_MAX;
    public static final BigInteger LONG_MIN;
    public static final BigInteger ULONG_MAX;
    private final BigInteger value;
    
    public static RubyClass createBignumClass(final Ruby runtime) {
        final RubyClass bignum = runtime.defineClass("Bignum", runtime.getInteger(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setBignum(bignum);
        bignum.index = 2;
        bignum.setReifiedClass(RubyBignum.class);
        bignum.defineAnnotatedMethods(RubyBignum.class);
        return bignum;
    }
    
    public RubyBignum(final Ruby runtime, final BigInteger value) {
        super(runtime, runtime.getBignum());
        this.value = value;
    }
    
    public int getNativeTypeIndex() {
        return 2;
    }
    
    public Class<?> getJavaClass() {
        return BigInteger.class;
    }
    
    public static RubyBignum newBignum(final Ruby runtime, final long value) {
        return newBignum(runtime, BigInteger.valueOf(value));
    }
    
    public static RubyBignum newBignum(final Ruby runtime, final double value) {
        return newBignum(runtime, new BigDecimal(value).toBigInteger());
    }
    
    public static RubyBignum newBignum(final Ruby runtime, final BigInteger value) {
        return new RubyBignum(runtime, value);
    }
    
    public static RubyBignum newBignum(final Ruby runtime, final String value) {
        return new RubyBignum(runtime, new BigInteger(value));
    }
    
    public double getDoubleValue() {
        return big2dbl(this);
    }
    
    public long getLongValue() {
        return big2long(this);
    }
    
    public BigInteger getBigIntegerValue() {
        return this.value;
    }
    
    public BigInteger getValue() {
        return this.value;
    }
    
    public static RubyInteger bignorm(final Ruby runtime, final BigInteger bi) {
        if (bi.compareTo(RubyBignum.LONG_MIN) < 0 || bi.compareTo(RubyBignum.LONG_MAX) > 0) {
            return newBignum(runtime, bi);
        }
        return runtime.newFixnum(bi.longValue());
    }
    
    public static long big2long(final RubyBignum value) {
        final BigInteger big = value.getValue();
        if (big.compareTo(RubyBignum.LONG_MIN) < 0 || big.compareTo(RubyBignum.LONG_MAX) > 0) {
            throw value.getRuntime().newRangeError("bignum too big to convert into `long'");
        }
        return big.longValue();
    }
    
    public static long big2ulong(final RubyBignum value) {
        final BigInteger big = value.getValue();
        if (big.compareTo(RubyBignum.LONG_MIN) <= 0 || big.compareTo(RubyBignum.ULONG_MAX) > 0) {
            throw value.getRuntime().newRangeError("bignum too big to convert into `ulong'");
        }
        return value.getValue().longValue();
    }
    
    public static double big2dbl(final RubyBignum value) {
        final BigInteger big = value.getValue();
        final double dbl = convertToDouble(big);
        if (dbl == Double.NEGATIVE_INFINITY || dbl == Double.POSITIVE_INFINITY) {
            value.getRuntime().getWarnings().warn(IRubyWarnings.ID.BIGNUM_FROM_FLOAT_RANGE, "Bignum out of Float range", new Object[0]);
        }
        return dbl;
    }
    
    private IRubyObject checkShiftDown(final RubyBignum other) {
        if (other.value.signum() == 0) {
            return RubyFixnum.zero(this.getRuntime());
        }
        if (this.value.compareTo(RubyBignum.LONG_MIN) < 0 || this.value.compareTo(RubyBignum.LONG_MAX) > 0) {
            return (other.value.signum() >= 0) ? RubyFixnum.zero(this.getRuntime()) : RubyFixnum.minus_one(this.getRuntime());
        }
        return this.getRuntime().getNil();
    }
    
    static double convertToDouble(BigInteger bigint) {
        final long signum = (bigint.signum() == -1) ? Long.MIN_VALUE : 0L;
        bigint = bigint.abs();
        final int len = bigint.bitLength();
        if (len == 0) {
            return 0.0;
        }
        long exp = len + 1022;
        long frac = 0L;
        if (exp > 2047L) {
            exp = 2047L;
        }
        else {
            frac = bigint.shiftRight(len - 54).longValue() + 1L >> 1;
            if (frac == 9007199254740992L) {
                ++exp;
                if (exp > 2047L) {
                    exp = 2047L;
                }
            }
        }
        return Double.longBitsToDouble(signum | exp << 52 | (frac & 0xFFFFFFFFFFFFFL));
    }
    
    public static BigInteger fix2big(final RubyFixnum arg) {
        return long2big(arg.getLongValue());
    }
    
    public static BigInteger long2big(final long arg) {
        return BigInteger.valueOf(arg);
    }
    
    public IRubyObject to_s(final IRubyObject[] args) {
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
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        final int base = 10;
        return this.getRuntime().newString(this.getValue().toString(base));
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final IRubyObject arg0) {
        final int base = RubyNumeric.num2int(arg0);
        if (base < 2 || base > 36) {
            throw this.getRuntime().newArgumentError("illegal radix " + base);
        }
        return this.getRuntime().newString(this.getValue().toString(base));
    }
    
    @JRubyMethod(name = { "coerce" }, required = 1)
    public IRubyObject coerce(final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.getRuntime().newArray(newBignum(this.getRuntime(), ((RubyFixnum)other).getLongValue()), this);
        }
        if (other instanceof RubyBignum) {
            return this.getRuntime().newArray(newBignum(this.getRuntime(), ((RubyBignum)other).getValue()), this);
        }
        throw this.getRuntime().newTypeError("Can't coerce " + other.getMetaClass().getName() + " to Bignum");
    }
    
    @JRubyMethod(name = { "-@" })
    public IRubyObject op_uminus() {
        return bignorm(this.getRuntime(), this.value.negate());
    }
    
    @JRubyMethod(name = { "+" }, required = 1)
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.addFixnum(((RubyFixnum)other).getLongValue());
        }
        if (other instanceof RubyBignum) {
            return this.addBignum(((RubyBignum)other).value);
        }
        if (other instanceof RubyFloat) {
            return this.addFloat((RubyFloat)other);
        }
        return this.addOther(context, other);
    }
    
    public IRubyObject op_plus(final ThreadContext context, final long other) {
        return this.addFixnum(other);
    }
    
    private IRubyObject addFixnum(final long other) {
        final BigInteger result = this.value.add(BigInteger.valueOf(other));
        if (other > 0L && this.value.signum() > 0) {
            return new RubyBignum(this.getRuntime(), result);
        }
        return bignorm(this.getRuntime(), result);
    }
    
    private IRubyObject addBignum(final BigInteger other) {
        final BigInteger result = this.value.add(other);
        if (this.value.signum() > 0 && other.signum() > 0) {
            return new RubyBignum(this.getRuntime(), result);
        }
        return bignorm(this.getRuntime(), result);
    }
    
    private IRubyObject addFloat(final RubyFloat other) {
        return RubyFloat.newFloat(this.getRuntime(), big2dbl(this) + other.getDoubleValue());
    }
    
    private IRubyObject addOther(final ThreadContext context, final IRubyObject other) {
        return this.coerceBin(context, "+", other);
    }
    
    @JRubyMethod(name = { "-" }, required = 1)
    public IRubyObject op_minus(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            return this.subtractFixnum(((RubyFixnum)other).getLongValue());
        }
        if (other instanceof RubyBignum) {
            return this.subtractBignum(((RubyBignum)other).value);
        }
        if (other instanceof RubyFloat) {
            return this.subtractFloat((RubyFloat)other);
        }
        return this.subtractOther(context, other);
    }
    
    public IRubyObject op_minus(final ThreadContext context, final long other) {
        return this.subtractFixnum(other);
    }
    
    private IRubyObject subtractFixnum(final long other) {
        final BigInteger result = this.value.subtract(BigInteger.valueOf(other));
        if (this.value.signum() < 0 && other > 0L) {
            return new RubyBignum(this.getRuntime(), result);
        }
        return bignorm(this.getRuntime(), result);
    }
    
    private IRubyObject subtractBignum(final BigInteger other) {
        final BigInteger result = this.value.subtract(other);
        if (this.value.signum() < 0 && other.signum() > 0) {
            return new RubyBignum(this.getRuntime(), result);
        }
        return bignorm(this.getRuntime(), result);
    }
    
    private IRubyObject subtractFloat(final RubyFloat other) {
        return RubyFloat.newFloat(this.getRuntime(), big2dbl(this) - other.getDoubleValue());
    }
    
    private IRubyObject subtractOther(final ThreadContext context, final IRubyObject other) {
        return this.coerceBin(context, "-", other);
    }
    
    @JRubyMethod(name = { "*" }, required = 1)
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyFixnum) {
            final BigInteger result = this.value.multiply(fix2big((RubyFixnum)other));
            return (result.signum() == 0) ? RubyFixnum.zero(runtime) : new RubyBignum(runtime, result);
        }
        if (other instanceof RubyBignum) {
            final BigInteger result = this.value.multiply(((RubyBignum)other).value);
            return (result.signum() == 0) ? RubyFixnum.zero(runtime) : new RubyBignum(runtime, result);
        }
        return this.opMulOther(context, other);
    }
    
    @JRubyMethod(name = { "*" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_mul19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyFixnum) {
            return bignorm(runtime, this.value.multiply(fix2big((RubyFixnum)other)));
        }
        if (other instanceof RubyBignum) {
            return bignorm(runtime, this.value.multiply(((RubyBignum)other).value));
        }
        return this.opMulOther(context, other);
    }
    
    public IRubyObject op_mul(final ThreadContext context, final long other) {
        final Ruby runtime = context.getRuntime();
        final BigInteger result = this.value.multiply(long2big(other));
        return (result.signum() == 0) ? RubyFixnum.zero(runtime) : new RubyBignum(runtime, result);
    }
    
    public IRubyObject opMulOther(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFloat) {
            return RubyFloat.newFloat(this.getRuntime(), big2dbl(this) * ((RubyFloat)other).getDoubleValue());
        }
        return this.coerceBin(context, "*", other);
    }
    
    private IRubyObject op_divide(final ThreadContext context, final IRubyObject other, final boolean slash) {
        final Ruby runtime = context.getRuntime();
        BigInteger otherValue;
        if (other instanceof RubyFixnum) {
            otherValue = fix2big((RubyFixnum)other);
        }
        else if (other instanceof RubyBignum) {
            otherValue = ((RubyBignum)other).value;
        }
        else {
            if (!(other instanceof RubyFloat)) {
                return this.coerceBin(context, slash ? "/" : "div", other);
            }
            final double div = big2dbl(this) / ((RubyFloat)other).getDoubleValue();
            if (slash) {
                return RubyFloat.newFloat(runtime, div);
            }
            return RubyNumeric.dbl2num(runtime, div);
        }
        if (otherValue.signum() == 0) {
            throw runtime.newZeroDivisionError();
        }
        BigInteger result;
        if (this.value.signum() * otherValue.signum() == -1) {
            final BigInteger[] results = this.value.divideAndRemainder(otherValue);
            result = ((results[1].signum() != 0) ? results[0].subtract(BigInteger.ONE) : results[0]);
        }
        else {
            result = this.value.divide(otherValue);
        }
        return bignorm(this.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "/" }, required = 1)
    public IRubyObject op_div(final ThreadContext context, final IRubyObject other) {
        return this.op_divide(context, other, true);
    }
    
    @JRubyMethod(name = { "div" }, required = 1)
    public IRubyObject op_idiv(final ThreadContext context, final IRubyObject other) {
        return this.op_divide(context, other, false);
    }
    
    @JRubyMethod(name = { "divmod" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject divmod(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        BigInteger otherValue;
        if (other instanceof RubyFixnum) {
            otherValue = fix2big((RubyFixnum)other);
        }
        else {
            if (!(other instanceof RubyBignum)) {
                return this.coerceBin(context, "divmod", other);
            }
            otherValue = ((RubyBignum)other).value;
        }
        if (otherValue.signum() == 0) {
            throw runtime.newZeroDivisionError();
        }
        final BigInteger[] results = this.value.divideAndRemainder(otherValue);
        if (this.value.signum() * otherValue.signum() == -1 && results[1].signum() != 0) {
            results[0] = results[0].subtract(BigInteger.ONE);
            results[1] = otherValue.add(results[1]);
        }
        return RubyArray.newArray(runtime, bignorm(runtime, results[0]), bignorm(runtime, results[1]));
    }
    
    @JRubyMethod(name = { "divmod" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject divmod19(final ThreadContext context, final IRubyObject other) {
        if (!other.isNil() && other instanceof RubyFloat && ((RubyFloat)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.divmod(context, other);
    }
    
    @JRubyMethod(name = { "%", "modulo" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_mod(final ThreadContext context, final IRubyObject other) {
        BigInteger otherValue;
        if (other instanceof RubyFixnum) {
            otherValue = fix2big((RubyFixnum)other);
        }
        else {
            if (!(other instanceof RubyBignum)) {
                return this.coerceBin(context, "%", other);
            }
            otherValue = ((RubyBignum)other).value;
        }
        final Ruby runtime = context.getRuntime();
        if (otherValue.signum() == 0) {
            throw runtime.newZeroDivisionError();
        }
        BigInteger result = this.value.mod(otherValue.abs());
        if (otherValue.signum() == -1 && result.signum() != 0) {
            result = otherValue.add(result);
        }
        return bignorm(runtime, result);
    }
    
    @JRubyMethod(name = { "%", "modulo" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_mod19(final ThreadContext context, final IRubyObject other) {
        if (!other.isNil() && other instanceof RubyFloat && ((RubyFloat)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.op_mod(context, other);
    }
    
    @JRubyMethod(name = { "remainder" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject remainder(final ThreadContext context, final IRubyObject other) {
        BigInteger otherValue;
        if (other instanceof RubyFixnum) {
            otherValue = fix2big((RubyFixnum)other);
        }
        else {
            if (!(other instanceof RubyBignum)) {
                return this.coerceBin(context, "remainder", other);
            }
            otherValue = ((RubyBignum)other).value;
        }
        final Ruby runtime = context.getRuntime();
        if (otherValue.signum() == 0) {
            throw runtime.newZeroDivisionError();
        }
        return bignorm(runtime, this.value.remainder(otherValue));
    }
    
    @JRubyMethod(name = { "remainder" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject remainder19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFloat && ((RubyFloat)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.remainder(context, other);
    }
    
    @JRubyMethod(name = { "quo" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject quo(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyNumeric) {
            return RubyFloat.newFloat(this.getRuntime(), big2dbl(this) / ((RubyNumeric)other).getDoubleValue());
        }
        return this.coerceBin(context, "quo", other);
    }
    
    @JRubyMethod(name = { "quo" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject quo19(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyInteger && ((RubyInteger)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.quo(context, other);
    }
    
    @JRubyMethod(name = { "**", "power" }, required = 1)
    public IRubyObject op_pow(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum) {
            final RubyFixnum fix = (RubyFixnum)other;
            final long fixValue = fix.getLongValue();
            return this.op_pow(context, fixValue);
        }
        double d;
        if (other instanceof RubyBignum) {
            d = ((RubyBignum)other).getDoubleValue();
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.MAY_BE_TOO_BIG, "in a**b, b may be too big", d);
        }
        else {
            if (!(other instanceof RubyFloat)) {
                return this.coerceBin(context, "**", other);
            }
            d = ((RubyFloat)other).getDoubleValue();
        }
        return RubyFloat.newFloat(this.getRuntime(), Math.pow(big2dbl(this), d));
    }
    
    public IRubyObject op_pow(final ThreadContext context, final long other) {
        if ((this.value.bitLength() + 7) / 8 * 4 * Math.abs(other) > 1048576L) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.MAY_BE_TOO_BIG, "in a**b, b may be too big", other);
        }
        if (other >= 0L) {
            return bignorm(this.getRuntime(), this.value.pow((int)other));
        }
        return RubyFloat.newFloat(this.getRuntime(), Math.pow(big2dbl(this), other));
    }
    
    @JRubyMethod(name = { "**", "power" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_pow19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other == RubyFixnum.zero(runtime)) {
            return RubyFixnum.one(runtime);
        }
        if (other instanceof RubyFixnum) {
            final RubyFixnum fix = (RubyFixnum)other;
            final long fixValue = fix.getLongValue();
            if (fixValue < 0L) {
                return RubyRational.newRationalRaw(runtime, this).callMethod(context, "**", other);
            }
            if ((this.value.bitLength() + 7) / 8 * 4 * Math.abs(fixValue) > 1048576L) {
                this.getRuntime().getWarnings().warn(IRubyWarnings.ID.MAY_BE_TOO_BIG, "in a**b, b may be too big", fixValue);
            }
            if (fixValue >= 0L) {
                return bignorm(runtime, this.value.pow((int)fixValue));
            }
            return RubyFloat.newFloat(runtime, Math.pow(big2dbl(this), fixValue));
        }
        else {
            double d;
            if (other instanceof RubyBignum) {
                d = ((RubyBignum)other).getDoubleValue();
                this.getRuntime().getWarnings().warn(IRubyWarnings.ID.MAY_BE_TOO_BIG, "in a**b, b may be too big", d);
            }
            else {
                if (!(other instanceof RubyFloat)) {
                    return this.coerceBin(context, "**", other);
                }
                d = ((RubyFloat)other).getDoubleValue();
                if (this.compareTo(RubyFixnum.zero(runtime)) == -1 && d != Math.round(d)) {
                    return RubyComplex.newComplexRaw(this.getRuntime(), this).callMethod(context, "**", other);
                }
            }
            final double pow = Math.pow(big2dbl(this), d);
            if (Double.isInfinite(pow)) {
                return RubyFloat.newFloat(runtime, pow);
            }
            return RubyNumeric.dbl2num(runtime, pow);
        }
    }
    
    @JRubyMethod(name = { "&" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_and(final ThreadContext context, IRubyObject other) {
        other = other.convertToInteger();
        if (other instanceof RubyBignum) {
            return bignorm(this.getRuntime(), this.value.and(((RubyBignum)other).value));
        }
        if (other instanceof RubyFixnum) {
            return bignorm(this.getRuntime(), this.value.and(fix2big((RubyFixnum)other)));
        }
        return this.coerceBin(context, "&", other);
    }
    
    @JRubyMethod(name = { "&" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_and19(final ThreadContext context, final IRubyObject other) {
        return this.op_and(context, this.convertToInteger(context, other));
    }
    
    @JRubyMethod(name = { "|" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_or(final ThreadContext context, IRubyObject other) {
        other = other.convertToInteger();
        if (other instanceof RubyBignum) {
            return bignorm(this.getRuntime(), this.value.or(((RubyBignum)other).value));
        }
        if (other instanceof RubyFixnum) {
            return bignorm(this.getRuntime(), this.value.or(fix2big((RubyFixnum)other)));
        }
        return this.coerceBin(context, "|", other);
    }
    
    @JRubyMethod(name = { "|" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_or19(final ThreadContext context, final IRubyObject other) {
        return this.op_or(context, this.convertToInteger(context, other));
    }
    
    @JRubyMethod(name = { "^" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_xor(final ThreadContext context, IRubyObject other) {
        other = other.convertToInteger();
        if (other instanceof RubyBignum) {
            return bignorm(this.getRuntime(), this.value.xor(((RubyBignum)other).value));
        }
        if (other instanceof RubyFixnum) {
            return bignorm(this.getRuntime(), this.value.xor(BigInteger.valueOf(((RubyFixnum)other).getLongValue())));
        }
        return this.coerceBin(context, "^", other);
    }
    
    @JRubyMethod(name = { "^" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_xor19(final ThreadContext context, final IRubyObject other) {
        return this.op_xor(context, this.convertToInteger(context, other));
    }
    
    private IRubyObject convertToInteger(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFloat) {
            throw context.getRuntime().newTypeError("can't convert Float into Integer");
        }
        return other.convertToInteger();
    }
    
    @JRubyMethod(name = { "~" })
    public IRubyObject op_neg() {
        return newBignum(this.getRuntime(), this.value.not());
    }
    
    @JRubyMethod(name = { "<<" }, required = 1)
    public IRubyObject op_lshift(IRubyObject other) {
        boolean neg = false;
        while (!(other instanceof RubyFixnum)) {
            if (other instanceof RubyBignum) {
                final RubyBignum otherBignum = (RubyBignum)other;
                if (otherBignum.value.signum() < 0) {
                    final IRubyObject tmp = otherBignum.checkShiftDown(this);
                    if (!tmp.isNil()) {
                        return tmp;
                    }
                    neg = true;
                }
                final long shift = big2long(otherBignum);
                return bignorm(this.getRuntime(), neg ? this.value.shiftRight((int)shift) : this.value.shiftLeft((int)shift));
            }
            other = other.convertToInteger();
        }
        long shift = ((RubyFixnum)other).getLongValue();
        if (shift < 0L) {
            neg = true;
            shift = -shift;
            return bignorm(this.getRuntime(), neg ? this.value.shiftRight((int)shift) : this.value.shiftLeft((int)shift));
        }
        return bignorm(this.getRuntime(), neg ? this.value.shiftRight((int)shift) : this.value.shiftLeft((int)shift));
    }
    
    @JRubyMethod(name = { ">>" }, required = 1)
    public IRubyObject op_rshift(IRubyObject other) {
        boolean neg = false;
        while (!(other instanceof RubyFixnum)) {
            if (other instanceof RubyBignum) {
                final RubyBignum otherBignum = (RubyBignum)other;
                if (otherBignum.value.signum() >= 0) {
                    final IRubyObject tmp = otherBignum.checkShiftDown(this);
                    if (!tmp.isNil()) {
                        return tmp;
                    }
                }
                else {
                    neg = true;
                }
                final long shift = big2long(otherBignum);
                return bignorm(this.getRuntime(), neg ? this.value.shiftLeft((int)shift) : this.value.shiftRight((int)shift));
            }
            other = other.convertToInteger();
        }
        long shift = ((RubyFixnum)other).getLongValue();
        if (shift < 0L) {
            neg = true;
            shift = -shift;
            return bignorm(this.getRuntime(), neg ? this.value.shiftLeft((int)shift) : this.value.shiftRight((int)shift));
        }
        return bignorm(this.getRuntime(), neg ? this.value.shiftLeft((int)shift) : this.value.shiftRight((int)shift));
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public RubyFixnum op_aref(final IRubyObject other) {
        if (other instanceof RubyBignum) {
            if (((RubyBignum)other).value.signum() >= 0 || this.value.signum() == -1) {
                return RubyFixnum.zero(this.getRuntime());
            }
            return RubyFixnum.one(this.getRuntime());
        }
        else {
            final long position = RubyNumeric.num2long(other);
            if (position < 0L || position > 2147483647L) {
                return RubyFixnum.zero(this.getRuntime());
            }
            return this.value.testBit((int)position) ? RubyFixnum.one(this.getRuntime()) : RubyFixnum.zero(this.getRuntime());
        }
    }
    
    public final int compareTo(final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return this.value.compareTo(((RubyBignum)other).value);
        }
        return (int)this.coerceCmp(this.getRuntime().getCurrentContext(), "<=>", other).convertToInteger().getLongValue();
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        BigInteger otherValue;
        if (other instanceof RubyFixnum) {
            otherValue = fix2big((RubyFixnum)other);
        }
        else if (other instanceof RubyBignum) {
            otherValue = ((RubyBignum)other).value;
        }
        else {
            if (!(other instanceof RubyFloat)) {
                return this.coerceCmp(context, "<=>", other);
            }
            final RubyFloat flt = (RubyFloat)other;
            if (!flt.infinite_p().isTrue()) {
                return RubyNumeric.dbl_cmp(this.getRuntime(), big2dbl(this), flt.getDoubleValue());
            }
            if (flt.getDoubleValue() > 0.0) {
                return RubyFixnum.minus_one(this.getRuntime());
            }
            return RubyFixnum.one(this.getRuntime());
        }
        return RubyFixnum.newFixnum(this.getRuntime(), this.value.compareTo(otherValue));
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final IRubyObject other) {
        BigInteger otherValue;
        if (other instanceof RubyFixnum) {
            otherValue = fix2big((RubyFixnum)other);
        }
        else if (other instanceof RubyBignum) {
            otherValue = ((RubyBignum)other).value;
        }
        else {
            if (!(other instanceof RubyFloat)) {
                return other.op_eqq(this.getRuntime().getCurrentContext(), this);
            }
            final double a = ((RubyFloat)other).getDoubleValue();
            if (Double.isNaN(a)) {
                return this.getRuntime().getFalse();
            }
            return RubyBoolean.newBoolean(this.getRuntime(), a == big2dbl(this));
        }
        return RubyBoolean.newBoolean(this.getRuntime(), this.value.compareTo(otherValue) == 0);
    }
    
    @JRubyMethod(name = { "eql?", "===" }, required = 1)
    public IRubyObject eql_p(final IRubyObject other) {
        if (other instanceof RubyBignum) {
            return (this.value.compareTo(((RubyBignum)other).value) == 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.value.hashCode());
    }
    
    @JRubyMethod(name = { "to_f" })
    public IRubyObject to_f() {
        return RubyFloat.newFloat(this.getRuntime(), this.getDoubleValue());
    }
    
    @JRubyMethod(name = { "abs" })
    public IRubyObject abs() {
        return newBignum(this.getRuntime(), this.value.abs());
    }
    
    @JRubyMethod(name = { "size" })
    public IRubyObject size() {
        return this.getRuntime().newFixnum((this.value.bitLength() + 7) / 8);
    }
    
    public static void marshalTo(final RubyBignum bignum, final MarshalStream output) throws IOException {
        output.registerLinkTarget(bignum);
        output.write((bignum.value.signum() >= 0) ? 43 : 45);
        final BigInteger absValue = bignum.value.abs();
        final byte[] digits = absValue.toByteArray();
        final boolean oddLengthNonzeroStart = digits.length % 2 != 0 && digits[0] != 0;
        int shortLength = digits.length / 2;
        if (oddLengthNonzeroStart) {
            ++shortLength;
        }
        output.writeInt(shortLength);
        for (int i = 1; i <= shortLength * 2 && i <= digits.length; ++i) {
            output.write(digits[digits.length - i]);
        }
        if (oddLengthNonzeroStart) {
            output.write(0);
        }
    }
    
    public static RubyNumeric unmarshalFrom(final UnmarshalStream input) throws IOException {
        final boolean positive = input.readUnsignedByte() == 43;
        final int shortLength = input.unmarshalInt();
        final byte[] digits = new byte[shortLength * 2 + 1];
        for (int i = digits.length - 1; i >= 1; --i) {
            digits[i] = input.readSignedByte();
        }
        BigInteger value = new BigInteger(digits);
        if (!positive) {
            value = value.negate();
        }
        final RubyNumeric result = bignorm(input.getRuntime(), value);
        input.registerLinkTarget(result);
        return result;
    }
    
    static {
        LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);
        LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);
        ULONG_MAX = BigInteger.valueOf(1L).shiftLeft(64).subtract(BigInteger.valueOf(1L));
    }
}
