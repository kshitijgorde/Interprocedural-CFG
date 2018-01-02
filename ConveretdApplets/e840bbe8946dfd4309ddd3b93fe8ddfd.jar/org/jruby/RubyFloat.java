// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.text.DecimalFormatSymbols;
import org.jruby.util.ConvertDouble;
import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.util.Numeric;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.util.Sprintf;
import java.util.Locale;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import java.math.BigInteger;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.ByteList;
import java.text.DecimalFormat;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Float" }, parent = "Numeric", include = { "Precision" })
public class RubyFloat extends RubyNumeric
{
    public static final int ROUNDS = 1;
    public static final int RADIX = 2;
    public static final int MANT_DIG = 53;
    public static final int DIG = 15;
    public static final int MIN_EXP = -1021;
    public static final int MAX_EXP = 1024;
    public static final int MAX_10_EXP = 308;
    public static final int MIN_10_EXP = -307;
    public static final double EPSILON = 2.220446049250313E-16;
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    public static final double NAN = Double.NaN;
    private final double value;
    private static final DecimalFormat FORMAT;
    static final int DBL_MANT_DIG = 53;
    static final int FLT_RADIX = 2;
    private static final ByteList NAN_BYTELIST;
    private static final ByteList NEGATIVE_INFINITY_BYTELIST;
    private static final ByteList INFINITY_BYTELIST;
    
    public static RubyClass createFloatClass(final Ruby runtime) {
        final RubyClass floatc = runtime.defineClass("Float", runtime.getNumeric(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setFloat(floatc);
        floatc.index = 11;
        floatc.setReifiedClass(RubyFloat.class);
        floatc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyFloat;
            }
        };
        floatc.getSingletonClass().undefineMethod("new");
        if (!runtime.is1_9()) {
            floatc.includeModule(runtime.getPrecision());
        }
        floatc.defineConstant("ROUNDS", RubyFixnum.newFixnum(runtime, 1L));
        floatc.defineConstant("RADIX", RubyFixnum.newFixnum(runtime, 2L));
        floatc.defineConstant("MANT_DIG", RubyFixnum.newFixnum(runtime, 53L));
        floatc.defineConstant("DIG", RubyFixnum.newFixnum(runtime, 15L));
        floatc.defineConstant("MIN_EXP", RubyFixnum.newFixnum(runtime, -1021L));
        floatc.defineConstant("MAX_EXP", RubyFixnum.newFixnum(runtime, 1024L));
        floatc.defineConstant("MIN_10_EXP", RubyFixnum.newFixnum(runtime, -307L));
        floatc.defineConstant("MAX_10_EXP", RubyFixnum.newFixnum(runtime, 308L));
        floatc.defineConstant("MIN", newFloat(runtime, Double.MIN_VALUE));
        floatc.defineConstant("MAX", newFloat(runtime, Double.MAX_VALUE));
        floatc.defineConstant("EPSILON", newFloat(runtime, 2.220446049250313E-16));
        if (runtime.is1_9()) {
            floatc.defineConstant("INFINITY", newFloat(runtime, Double.POSITIVE_INFINITY));
            floatc.defineConstant("NAN", newFloat(runtime, Double.NaN));
        }
        floatc.defineAnnotatedMethods(RubyFloat.class);
        return floatc;
    }
    
    public int getNativeTypeIndex() {
        return 11;
    }
    
    public RubyFloat(final Ruby runtime) {
        this(runtime, 0.0);
    }
    
    public RubyFloat(final Ruby runtime, final double value) {
        super(runtime, runtime.getFloat());
        this.value = value;
    }
    
    public Class<?> getJavaClass() {
        return Double.TYPE;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public double getDoubleValue() {
        return this.value;
    }
    
    public long getLongValue() {
        return (long)this.value;
    }
    
    public BigInteger getBigIntegerValue() {
        return BigInteger.valueOf((long)this.value);
    }
    
    public RubyFloat convertToFloat() {
        return this;
    }
    
    protected int compareValue(final RubyNumeric other) {
        final double otherVal = other.getDoubleValue();
        return (this.getValue() > otherVal) ? 1 : ((this.getValue() < otherVal) ? -1 : 0);
    }
    
    public static RubyFloat newFloat(final Ruby runtime, final double value) {
        return new RubyFloat(runtime, value);
    }
    
    @JRubyMethod(name = { "induced_from" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject induced_from(final ThreadContext context, final IRubyObject recv, final IRubyObject number) {
        if (number instanceof RubyFixnum || number instanceof RubyBignum || number instanceof RubyRational) {
            return number.callMethod(context, "to_f");
        }
        if (number instanceof RubyFloat) {
            return number;
        }
        throw recv.getRuntime().newTypeError("failed to convert " + number.getMetaClass() + " into Float");
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s() {
        final Ruby runtime = this.getRuntime();
        if (Double.isInfinite(this.value)) {
            return RubyString.newString(runtime, (this.value < 0.0) ? "-Infinity" : "Infinity");
        }
        if (Double.isNaN(this.value)) {
            return RubyString.newString(runtime, "NaN");
        }
        final ByteList buf = new ByteList();
        Sprintf.sprintf(buf, Locale.US, "%#.15g", this);
        int e = buf.indexOf(101);
        if (e == -1) {
            e = buf.getRealSize();
        }
        final ASCIIEncoding ascii = ASCIIEncoding.INSTANCE;
        if (!ascii.isDigit(buf.get(e - 1))) {
            buf.setRealSize(0);
            Sprintf.sprintf(buf, Locale.US, "%#.14e", this);
            e = buf.indexOf(101);
            if (e == -1) {
                e = buf.getRealSize();
            }
        }
        int p;
        for (p = e; buf.get(p - 1) == 48 && ascii.isDigit(buf.get(p - 2)); --p) {}
        System.arraycopy(buf.getUnsafeBytes(), e, buf.getUnsafeBytes(), p, buf.getRealSize() - e);
        buf.setRealSize(p + buf.getRealSize() - e);
        return runtime.newString(buf);
    }
    
    @JRubyMethod(name = { "coerce" }, required = 1)
    public IRubyObject coerce(final IRubyObject other) {
        return this.getRuntime().newArray(RubyKernel.new_float(this, other), this);
    }
    
    @JRubyMethod(name = { "-@" })
    public IRubyObject op_uminus() {
        return newFloat(this.getRuntime(), -this.value);
    }
    
    @JRubyMethod(name = { "+" }, required = 1)
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return newFloat(this.getRuntime(), this.value + ((RubyNumeric)other).getDoubleValue());
            }
            default: {
                return this.coerceBin(context, "+", other);
            }
        }
    }
    
    public IRubyObject op_plus(final ThreadContext context, final double other) {
        return newFloat(this.getRuntime(), this.value + other);
    }
    
    @JRubyMethod(name = { "-" }, required = 1)
    public IRubyObject op_minus(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return newFloat(this.getRuntime(), this.value - ((RubyNumeric)other).getDoubleValue());
            }
            default: {
                return this.coerceBin(context, "-", other);
            }
        }
    }
    
    public IRubyObject op_minus(final ThreadContext context, final double other) {
        return newFloat(this.getRuntime(), this.value - other);
    }
    
    @JRubyMethod(name = { "*" }, required = 1)
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return newFloat(this.getRuntime(), this.value * ((RubyNumeric)other).getDoubleValue());
            }
            default: {
                return this.coerceBin(context, "*", other);
            }
        }
    }
    
    public IRubyObject op_mul(final ThreadContext context, final double other) {
        return newFloat(this.getRuntime(), this.value * other);
    }
    
    @JRubyMethod(name = { "/" }, required = 1)
    public IRubyObject op_fdiv(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return newFloat(this.getRuntime(), this.value / ((RubyNumeric)other).getDoubleValue());
            }
            default: {
                return this.coerceBin(context, "/", other);
            }
        }
    }
    
    public IRubyObject op_fdiv(final ThreadContext context, final double other) {
        return newFloat(this.getRuntime(), this.value / other);
    }
    
    @JRubyMethod(name = { "quo" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject magnitude(final ThreadContext context, final IRubyObject other) {
        return this.callMethod(context, "/", other);
    }
    
    @JRubyMethod(name = { "%", "modulo" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_mod(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                final double y = ((RubyNumeric)other).getDoubleValue();
                return this.op_mod(context, y);
            }
            default: {
                return this.coerceBin(context, "%", other);
            }
        }
    }
    
    public IRubyObject op_mod(final ThreadContext context, final double other) {
        final double x = this.value;
        double mod = Math.IEEEremainder(x, other);
        if (other * mod < 0.0) {
            mod += other;
        }
        return newFloat(this.getRuntime(), mod);
    }
    
    @JRubyMethod(name = { "%", "modulo" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_mod19(final ThreadContext context, final IRubyObject other) {
        if (!other.isNil() && other instanceof RubyNumeric && ((RubyNumeric)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.op_mod(context, other);
    }
    
    @JRubyMethod(name = { "divmod" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject divmod(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                final double y = ((RubyNumeric)other).getDoubleValue();
                final double x = this.value;
                double mod = Math.IEEEremainder(x, y);
                if (Double.isNaN(mod)) {
                    throw this.getRuntime().newFloatDomainError("NaN");
                }
                final double div = Math.floor(x / y);
                if (y * mod < 0.0) {
                    mod += y;
                }
                final Ruby runtime = this.getRuntime();
                final IRubyObject car = RubyNumeric.dbl2num(runtime, div);
                final RubyFloat cdr = newFloat(runtime, mod);
                return RubyArray.newArray(runtime, car, cdr);
            }
            default: {
                return this.coerceBin(context, "divmod", other);
            }
        }
    }
    
    @JRubyMethod(name = { "divmod" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject divmod19(final ThreadContext context, final IRubyObject other) {
        if (!other.isNil() && other instanceof RubyNumeric && ((RubyNumeric)other).getDoubleValue() == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.divmod(context, other);
    }
    
    @JRubyMethod(name = { "**" }, required = 1)
    public IRubyObject op_pow(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return newFloat(this.getRuntime(), Math.pow(this.value, ((RubyNumeric)other).getDoubleValue()));
            }
            default: {
                return this.coerceBin(context, "**", other);
            }
        }
    }
    
    public IRubyObject op_pow(final ThreadContext context, final double other) {
        return newFloat(this.getRuntime(), Math.pow(this.value, other));
    }
    
    @JRubyMethod(name = { "**" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_pow19(final ThreadContext context, final IRubyObject other) {
        final double d_other = ((RubyNumeric)other).getDoubleValue();
        if (this.value < 0.0 && d_other != Math.round(d_other)) {
            return RubyComplex.newComplexRaw(this.getRuntime(), this).callMethod(context, "**", other);
        }
        return this.op_pow(context, other);
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (Double.isNaN(this.value)) {
            return this.getRuntime().getFalse();
        }
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return RubyBoolean.newBoolean(this.getRuntime(), this.value == ((RubyNumeric)other).getDoubleValue());
            }
            default: {
                return super.op_num_equal(context, other);
            }
        }
    }
    
    public IRubyObject op_equal(final ThreadContext context, final double other) {
        if (Double.isNaN(this.value)) {
            return this.getRuntime().getFalse();
        }
        return RubyBoolean.newBoolean(this.getRuntime(), this.value == other);
    }
    
    public boolean fastEqual(final RubyFloat other) {
        return !Double.isNaN(this.value) && this.value == other.value;
    }
    
    public final int compareTo(final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                return Double.compare(this.value, ((RubyNumeric)other).getDoubleValue());
            }
            default: {
                return (int)this.coerceCmp(this.getRuntime().getCurrentContext(), "<=>", other).convertToInteger().getLongValue();
            }
        }
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2: {
                if (Double.isInfinite(this.value)) {
                    return (this.value > 0.0) ? RubyFixnum.one(this.getRuntime()) : RubyFixnum.minus_one(this.getRuntime());
                }
            }
            case 11: {
                final double b = ((RubyNumeric)other).getDoubleValue();
                return RubyNumeric.dbl_cmp(this.getRuntime(), this.value, b);
            }
            default: {
                return this.coerceCmp(context, "<=>", other);
            }
        }
    }
    
    public IRubyObject op_cmp(final ThreadContext context, final double other) {
        return RubyNumeric.dbl_cmp(this.getRuntime(), this.value, other);
    }
    
    @JRubyMethod(name = { ">" }, required = 1)
    public IRubyObject op_gt(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                final double b = ((RubyNumeric)other).getDoubleValue();
                return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(b) && this.value > b);
            }
            default: {
                return this.coerceRelOp(context, ">", other);
            }
        }
    }
    
    public IRubyObject op_gt(final ThreadContext context, final double other) {
        return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(other) && this.value > other);
    }
    
    @JRubyMethod(name = { ">=" }, required = 1)
    public IRubyObject op_ge(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                final double b = ((RubyNumeric)other).getDoubleValue();
                return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(b) && this.value >= b);
            }
            default: {
                return this.coerceRelOp(context, ">=", other);
            }
        }
    }
    
    public IRubyObject op_ge(final ThreadContext context, final double other) {
        return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(other) && this.value >= other);
    }
    
    @JRubyMethod(name = { "<" }, required = 1)
    public IRubyObject op_lt(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                final double b = ((RubyNumeric)other).getDoubleValue();
                return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(b) && this.value < b);
            }
            default: {
                return this.coerceRelOp(context, "<", other);
            }
        }
    }
    
    public IRubyObject op_lt(final ThreadContext context, final double other) {
        return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(other) && this.value < other);
    }
    
    @JRubyMethod(name = { "<=" }, required = 1)
    public IRubyObject op_le(final ThreadContext context, final IRubyObject other) {
        switch (other.getMetaClass().index) {
            case 1:
            case 2:
            case 11: {
                final double b = ((RubyNumeric)other).getDoubleValue();
                return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(b) && this.value <= b);
            }
            default: {
                return this.coerceRelOp(context, "<=", other);
            }
        }
    }
    
    public IRubyObject op_le(final ThreadContext context, final double other) {
        return RubyBoolean.newBoolean(this.getRuntime(), !Double.isNaN(other) && this.value <= other);
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1)
    public IRubyObject eql_p(final IRubyObject other) {
        if (other instanceof RubyFloat) {
            final double b = ((RubyFloat)other).value;
            if (Double.isNaN(this.value) || Double.isNaN(b)) {
                return this.getRuntime().getFalse();
            }
            if (this.value == b) {
                return this.getRuntime().getTrue();
            }
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.hashCode());
    }
    
    public final int hashCode() {
        final long l = Double.doubleToLongBits(this.value);
        return (int)(l ^ l >>> 32);
    }
    
    @JRubyMethod(name = { "to_f" })
    public IRubyObject to_f() {
        return this;
    }
    
    @JRubyMethod(name = { "abs" })
    public IRubyObject abs(final ThreadContext context) {
        if (Double.doubleToLongBits(this.value) < 0L) {
            return newFloat(context.getRuntime(), Math.abs(this.value));
        }
        return this;
    }
    
    @JRubyMethod(name = { "magnitude" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject magnitude(final ThreadContext context) {
        return this.abs(context);
    }
    
    @JRubyMethod(name = { "zero?" })
    public IRubyObject zero_p() {
        return RubyBoolean.newBoolean(this.getRuntime(), this.value == 0.0);
    }
    
    @JRubyMethod(name = { "truncate", "to_i", "to_int" })
    public IRubyObject truncate() {
        double f = this.value;
        if (f > 0.0) {
            f = Math.floor(f);
        }
        if (f < 0.0) {
            f = Math.ceil(f);
        }
        return RubyNumeric.dbl2num(this.getRuntime(), f);
    }
    
    @JRubyMethod(name = { "numerator" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject numerator(final ThreadContext context) {
        if (Double.isInfinite(this.value) || Double.isNaN(this.value)) {
            return this;
        }
        return super.numerator(context);
    }
    
    @JRubyMethod(name = { "denominator" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject denominator(final ThreadContext context) {
        if (Double.isInfinite(this.value) || Double.isNaN(this.value)) {
            return RubyFixnum.one(context.getRuntime());
        }
        return super.denominator(context);
    }
    
    @JRubyMethod(name = { "to_r" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_r(final ThreadContext context) {
        final long[] exp = { 0L };
        double f = Numeric.frexp(this.value, exp);
        f = Numeric.ldexp(f, 53L);
        final long n = exp[0] - 53L;
        final Ruby runtime = context.getRuntime();
        final IRubyObject rf = RubyNumeric.dbl2num(runtime, f);
        final IRubyObject rn = RubyFixnum.newFixnum(runtime, n);
        return Numeric.f_mul(context, rf, Numeric.f_expt(context, RubyFixnum.newFixnum(runtime, 2L), rn));
    }
    
    @JRubyMethod(name = { "rationalize" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject rationalize(final ThreadContext context, final IRubyObject[] args) {
        if (Numeric.f_negative_p(context, this)) {
            return Numeric.f_negate(context, ((RubyFloat)Numeric.f_abs(context, this)).rationalize(context, args));
        }
        final Ruby runtime = context.getRuntime();
        final RubyFixnum one = RubyFixnum.one(runtime);
        final RubyFixnum two = RubyFixnum.two(runtime);
        IRubyObject a;
        IRubyObject b;
        if (args.length != 0) {
            final IRubyObject eps = Numeric.f_abs(context, args[0]);
            a = Numeric.f_sub(context, this, eps);
            b = Numeric.f_add(context, this, eps);
        }
        else {
            final long[] exp = { 0L };
            double f = Numeric.frexp(this.value, exp);
            f = Numeric.ldexp(f, 53L);
            final long n = exp[0] - 53L;
            final IRubyObject rf = RubyNumeric.dbl2num(runtime, f);
            final IRubyObject rn = RubyFixnum.newFixnum(runtime, n);
            if (Numeric.f_zero_p(context, rf) || (!Numeric.f_negative_p(context, rn) && !Numeric.f_zero_p(context, rn))) {
                return RubyRational.newRationalRaw(runtime, Numeric.f_lshift(context, rf, rn));
            }
            a = RubyRational.newRationalRaw(runtime, Numeric.f_sub(context, Numeric.f_mul(context, two, rf), one), Numeric.f_lshift(context, one, Numeric.f_sub(context, one, rn)));
            b = RubyRational.newRationalRaw(runtime, Numeric.f_add(context, Numeric.f_mul(context, two, rf), one), Numeric.f_lshift(context, one, Numeric.f_sub(context, one, rn)));
        }
        if (RuntimeHelpers.invokedynamic(context, a, 1, b).isTrue()) {
            return Numeric.f_to_r(context, this);
        }
        final IRubyObject[] ary = { a, b };
        final IRubyObject[] ans = Numeric.nurat_rationalize_internal(context, ary);
        return RubyRational.newRationalRaw(runtime, ans[0], ans[1]);
    }
    
    @JRubyMethod(name = { "floor" })
    public IRubyObject floor() {
        return RubyNumeric.dbl2num(this.getRuntime(), Math.floor(this.value));
    }
    
    @JRubyMethod(name = { "ceil" })
    public IRubyObject ceil() {
        return RubyNumeric.dbl2num(this.getRuntime(), Math.ceil(this.value));
    }
    
    @JRubyMethod(name = { "round" })
    public IRubyObject round() {
        return RubyNumeric.dbl2num(this.getRuntime(), this.val2dbl());
    }
    
    @JRubyMethod(name = { "round" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject round(final ThreadContext context, final IRubyObject[] args) {
        if (args.length == 0) {
            return this.round();
        }
        final double digits = RubyNumeric.num2dbl(args[0]);
        final double magnifier = Math.pow(10.0, Math.abs(digits));
        double number = this.value;
        if (Double.isInfinite(magnifier)) {
            if (digits < 0.0) {
                number = 0.0;
            }
        }
        else {
            if (digits < 0.0) {
                number /= magnifier;
            }
            else {
                number *= magnifier;
            }
            number = Math.round(number);
            if (digits < 0.0) {
                number *= magnifier;
            }
            else {
                number /= magnifier;
            }
        }
        if (digits > 0.0) {
            return newFloat(context.getRuntime(), number);
        }
        return RubyNumeric.dbl2num(context.getRuntime(), (long)number);
    }
    
    private double val2dbl() {
        double f = this.value;
        if (f > 0.0) {
            f = Math.floor(f);
            if (this.value - f >= 0.5) {
                ++f;
            }
        }
        else if (f < 0.0) {
            f = Math.ceil(f);
            if (f - this.value >= 0.5) {
                --f;
            }
        }
        return f;
    }
    
    @JRubyMethod(name = { "nan?" })
    public IRubyObject nan_p() {
        return RubyBoolean.newBoolean(this.getRuntime(), Double.isNaN(this.value));
    }
    
    @JRubyMethod(name = { "infinite?" })
    public IRubyObject infinite_p() {
        if (Double.isInfinite(this.value)) {
            return RubyFixnum.newFixnum(this.getRuntime(), (this.value < 0.0) ? -1L : 1L);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "finite?" })
    public IRubyObject finite_p() {
        if (Double.isInfinite(this.value) || Double.isNaN(this.value)) {
            return this.getRuntime().getFalse();
        }
        return this.getRuntime().getTrue();
    }
    
    private static ByteList formatDouble(final RubyFloat x) {
        final ByteList byteList = new ByteList();
        Sprintf.sprintf(byteList, "%.17g", RubyArray.newArray(x.getRuntime(), x, x));
        return byteList;
    }
    
    private ByteList marshalDump() {
        if (Double.isInfinite(this.value)) {
            return (this.value < 0.0) ? RubyFloat.NEGATIVE_INFINITY_BYTELIST : RubyFloat.INFINITY_BYTELIST;
        }
        if (Double.isNaN(this.value)) {
            return RubyFloat.NAN_BYTELIST;
        }
        return formatDouble(this);
    }
    
    public static void marshalTo(final RubyFloat aFloat, final MarshalStream output) throws IOException {
        output.registerLinkTarget(aFloat);
        output.writeString(aFloat.marshalDump());
    }
    
    public static RubyFloat unmarshalFrom(final UnmarshalStream input) throws IOException {
        final ByteList value = input.unmarshalString();
        RubyFloat result;
        if (value.equals(RubyFloat.NAN_BYTELIST)) {
            result = newFloat(input.getRuntime(), Double.NaN);
        }
        else if (value.equals(RubyFloat.NEGATIVE_INFINITY_BYTELIST)) {
            result = newFloat(input.getRuntime(), Double.NEGATIVE_INFINITY);
        }
        else if (value.equals(RubyFloat.INFINITY_BYTELIST)) {
            result = newFloat(input.getRuntime(), Double.POSITIVE_INFINITY);
        }
        else {
            result = newFloat(input.getRuntime(), ConvertDouble.byteListToDouble(value, false));
        }
        input.registerLinkTarget(result);
        return result;
    }
    
    static {
        FORMAT = new DecimalFormat("##############0.0##############", new DecimalFormatSymbols(Locale.ENGLISH));
        NAN_BYTELIST = new ByteList("nan".getBytes());
        NEGATIVE_INFINITY_BYTELIST = new ByteList("-inf".getBytes());
        INFINITY_BYTELIST = new ByteList("inf".getBytes());
    }
}
