// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.ConvertDouble;
import org.jruby.javasupport.JavaUtil;
import org.jruby.util.Numeric;
import org.jruby.runtime.Block;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.ByteList;
import org.jruby.util.ConvertBytes;
import org.jruby.exceptions.RaiseException;
import java.math.BigInteger;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Numeric" }, include = { "Comparable" })
public class RubyNumeric extends RubyObject
{
    protected static final ObjectAllocator NUMERIC_ALLOCATOR;
    public static final double DBL_EPSILON = 2.220446049250313E-16;
    private static final ByteListCaller18 biteListCaller18;
    private static final ByteListCaller19 biteListCaller19;
    
    public static RubyClass createNumericClass(final Ruby runtime) {
        final RubyClass numeric = runtime.defineClass("Numeric", runtime.getObject(), RubyNumeric.NUMERIC_ALLOCATOR);
        runtime.setNumeric(numeric);
        numeric.index = 17;
        numeric.setReifiedClass(RubyNumeric.class);
        numeric.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyNumeric;
            }
        };
        numeric.includeModule(runtime.getComparable());
        numeric.defineAnnotatedMethods(RubyNumeric.class);
        return numeric;
    }
    
    private static IRubyObject convertToNum(final double val, final Ruby runtime) {
        if (val >= 9.223372036854776E18 || val < -9.223372036854776E18) {
            return RubyBignum.newBignum(runtime, val);
        }
        return RubyFixnum.newFixnum(runtime, (long)val);
    }
    
    public RubyNumeric(final Ruby runtime, final RubyClass metaClass) {
        super(runtime, metaClass);
    }
    
    public RubyNumeric(final RubyClass metaClass) {
        super(metaClass);
    }
    
    public RubyNumeric(final Ruby runtime, final RubyClass metaClass, final boolean useObjectSpace) {
        super(runtime, metaClass, useObjectSpace);
    }
    
    public RubyNumeric(final Ruby runtime, final RubyClass metaClass, final boolean useObjectSpace, final boolean canBeTainted) {
        super(runtime, metaClass, useObjectSpace, canBeTainted);
    }
    
    public double getDoubleValue() {
        return 0.0;
    }
    
    public long getLongValue() {
        return 0L;
    }
    
    public BigInteger getBigIntegerValue() {
        return BigInteger.ZERO;
    }
    
    public static RubyNumeric newNumeric(final Ruby runtime) {
        return new RubyNumeric(runtime, runtime.getNumeric());
    }
    
    public static int num2int(final IRubyObject arg) {
        final long num = num2long(arg);
        checkInt(arg, num);
        return (int)num;
    }
    
    public static void checkInt(final IRubyObject arg, final long num) {
        if (num < -2147483648L) {
            tooSmall(arg, num);
        }
        else {
            if (num <= 2147483647L) {
                return;
            }
            tooBig(arg, num);
        }
    }
    
    private static void tooSmall(final IRubyObject arg, final long num) {
        throw arg.getRuntime().newRangeError("integer " + num + " too small to convert to `int'");
    }
    
    private static void tooBig(final IRubyObject arg, final long num) {
        throw arg.getRuntime().newRangeError("integer " + num + " too big to convert to `int'");
    }
    
    public static byte num2chr(final IRubyObject arg) {
        if (arg instanceof RubyString) {
            final String value = ((RubyString)arg).toString();
            if (value != null && value.length() > 0) {
                return (byte)value.charAt(0);
            }
        }
        return (byte)num2int(arg);
    }
    
    public static long num2long(final IRubyObject arg) {
        if (arg instanceof RubyFixnum) {
            return ((RubyFixnum)arg).getLongValue();
        }
        return other2long(arg);
    }
    
    private static long other2long(final IRubyObject arg) throws RaiseException {
        if (arg.isNil()) {
            throw arg.getRuntime().newTypeError("no implicit conversion from nil to integer");
        }
        if (arg instanceof RubyFloat) {
            return float2long((RubyFloat)arg);
        }
        if (arg instanceof RubyBignum) {
            return RubyBignum.big2long((RubyBignum)arg);
        }
        return arg.convertToInteger().getLongValue();
    }
    
    private static long float2long(final RubyFloat flt) {
        final double aFloat = flt.getDoubleValue();
        if (aFloat <= 9.223372036854776E18 && aFloat >= -9.223372036854776E18) {
            return (long)aFloat;
        }
        throw flt.getRuntime().newRangeError("float " + aFloat + " out of range of integer");
    }
    
    public static IRubyObject dbl2num(final Ruby runtime, final double val) {
        if (Double.isInfinite(val)) {
            throw runtime.newFloatDomainError((val < 0.0) ? "-Infinity" : "Infinity");
        }
        if (Double.isNaN(val)) {
            throw runtime.newFloatDomainError("NaN");
        }
        return convertToNum(val, runtime);
    }
    
    public static double num2dbl(final IRubyObject arg) {
        if (arg instanceof RubyFloat) {
            return ((RubyFloat)arg).getDoubleValue();
        }
        if (arg instanceof RubyString) {
            throw arg.getRuntime().newTypeError("no implicit conversion to float from string");
        }
        if (arg == arg.getRuntime().getNil()) {
            throw arg.getRuntime().newTypeError("no implicit conversion to float from nil");
        }
        return RubyKernel.new_float(arg, arg).getDoubleValue();
    }
    
    public static IRubyObject dbl_cmp(final Ruby runtime, final double a, final double b) {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            return runtime.getNil();
        }
        return (a == b) ? RubyFixnum.zero(runtime) : ((a > b) ? RubyFixnum.one(runtime) : RubyFixnum.minus_one(runtime));
    }
    
    public static long fix2long(final IRubyObject arg) {
        return ((RubyFixnum)arg).getLongValue();
    }
    
    public static int fix2int(final IRubyObject arg) {
        final long num = (arg instanceof RubyFixnum) ? fix2long(arg) : num2long(arg);
        checkInt(arg, num);
        return (int)num;
    }
    
    public static int fix2int(final RubyFixnum arg) {
        final long num = arg.getLongValue();
        checkInt(arg, num);
        return (int)num;
    }
    
    public static RubyInteger str2inum(final Ruby runtime, final RubyString str, final int base) {
        return str2inum(runtime, str, base, false);
    }
    
    public static RubyNumeric int2fix(final Ruby runtime, final long val) {
        return RubyFixnum.newFixnum(runtime, val);
    }
    
    public static IRubyObject num2fix(final IRubyObject val) {
        if (val instanceof RubyFixnum) {
            return val;
        }
        if (val instanceof RubyBignum) {
            throw val.getRuntime().newRangeError("integer " + val + " out of range of fixnum");
        }
        return RubyFixnum.newFixnum(val.getRuntime(), num2long(val));
    }
    
    public static RubyInteger str2inum(final Ruby runtime, final RubyString str, final int base, final boolean strict) {
        final ByteList s = str.getByteList();
        return ConvertBytes.byteListToInum(runtime, s, base, strict);
    }
    
    public static RubyFloat str2fnum(final Ruby runtime, final RubyString arg) {
        return str2fnum(runtime, arg, false);
    }
    
    public static RubyFloat str2fnum(final Ruby runtime, final RubyString arg, final boolean strict) {
        return str2fnumCommon(runtime, arg, strict, RubyNumeric.biteListCaller18);
    }
    
    public static RubyFloat str2fnum19(final Ruby runtime, final RubyString arg, final boolean strict) {
        return str2fnumCommon(runtime, arg, strict, RubyNumeric.biteListCaller19);
    }
    
    private static RubyFloat str2fnumCommon(final Ruby runtime, final RubyString arg, final boolean strict, final ByteListCaller caller) {
        final double ZERO = 0.0;
        try {
            return new RubyFloat(runtime, caller.yield(arg, strict));
        }
        catch (NumberFormatException e) {
            if (strict) {
                throw runtime.newArgumentError("invalid value for Float(): " + arg.callMethod(runtime.getCurrentContext(), "inspect").toString());
            }
            return new RubyFloat(runtime, 0.0);
        }
    }
    
    protected IRubyObject[] getCoerced(final ThreadContext context, final IRubyObject other, final boolean error) {
        IRubyObject result;
        try {
            result = other.callMethod(context, "coerce", this);
        }
        catch (RaiseException e) {
            if (error) {
                throw this.getRuntime().newTypeError(other.getMetaClass().getName() + " can't be coerced into " + this.getMetaClass().getName());
            }
            return null;
        }
        if (!(result instanceof RubyArray) || ((RubyArray)result).getLength() != 2) {
            throw this.getRuntime().newTypeError("coerce must return [x, y]");
        }
        return ((RubyArray)result).toJavaArray();
    }
    
    protected IRubyObject callCoerced(final ThreadContext context, final String method, final IRubyObject other, final boolean err) {
        final IRubyObject[] args = this.getCoerced(context, other, err);
        if (args == null) {
            return this.getRuntime().getNil();
        }
        return args[0].callMethod(context, method, args[1]);
    }
    
    protected IRubyObject callCoerced(final ThreadContext context, final String method, final IRubyObject other) {
        final IRubyObject[] args = this.getCoerced(context, other, false);
        if (args == null) {
            return this.getRuntime().getNil();
        }
        return args[0].callMethod(context, method, args[1]);
    }
    
    protected final IRubyObject coerceBody(final ThreadContext context, final IRubyObject other) {
        return other.callMethod(context, "coerce", this);
    }
    
    protected final RubyArray doCoerce(final ThreadContext context, final IRubyObject other, final boolean err) {
        IRubyObject result;
        try {
            result = this.coerceBody(context, other);
        }
        catch (RaiseException e) {
            if (err) {
                throw this.getRuntime().newTypeError(other.getMetaClass().getName() + " can't be coerced into " + this.getMetaClass().getName());
            }
            return null;
        }
        if (!(result instanceof RubyArray) || ((RubyArray)result).getLength() != 2) {
            throw this.getRuntime().newTypeError("coerce must return [x, y]");
        }
        return (RubyArray)result;
    }
    
    protected final IRubyObject coerceBin(final ThreadContext context, final String method, final IRubyObject other) {
        final RubyArray ary = this.doCoerce(context, other, true);
        return ary.eltInternal(0).callMethod(context, method, ary.eltInternal(1));
    }
    
    protected final IRubyObject coerceCmp(final ThreadContext context, final String method, final IRubyObject other) {
        final RubyArray ary = this.doCoerce(context, other, false);
        if (ary == null) {
            return this.getRuntime().getNil();
        }
        return ary.eltInternal(0).callMethod(context, method, ary.eltInternal(1));
    }
    
    protected final IRubyObject coerceRelOp(final ThreadContext context, final String method, final IRubyObject other) {
        final RubyArray ary = this.doCoerce(context, other, false);
        if (ary == null) {
            return RubyComparable.cmperr(this, other);
        }
        return this.unwrapCoerced(context, method, other, ary);
    }
    
    private final IRubyObject unwrapCoerced(final ThreadContext context, final String method, final IRubyObject other, final RubyArray ary) {
        final IRubyObject result = ary.eltInternal(0).callMethod(context, method, ary.eltInternal(1));
        if (result.isNil()) {
            return RubyComparable.cmperr(this, other);
        }
        return result;
    }
    
    public RubyNumeric asNumeric() {
        return this;
    }
    
    @JRubyMethod(name = { "singleton_method_added" })
    public IRubyObject sadded(IRubyObject name) {
        throw this.getRuntime().newTypeError("can't define singleton method " + name + " for " + this.getType().getName());
    }
    
    @JRubyMethod(name = { "initialize_copy" }, visibility = Visibility.PRIVATE)
    public IRubyObject initialize_copy(final IRubyObject arg) {
        throw this.getRuntime().newTypeError("can't copy " + this.getType().getName());
    }
    
    @JRubyMethod(name = { "coerce" })
    public IRubyObject coerce(final IRubyObject other) {
        if (this.getMetaClass() == other.getMetaClass()) {
            return this.getRuntime().newArray(other, this);
        }
        final IRubyObject cdr = RubyKernel.new_float(this, this);
        final IRubyObject car = RubyKernel.new_float(this, other);
        return this.getRuntime().newArray(car, cdr);
    }
    
    @JRubyMethod(name = { "+@" })
    public IRubyObject op_uplus() {
        return this;
    }
    
    @JRubyMethod(name = { "i" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject num_imaginary(final ThreadContext context) {
        return RubyComplex.newComplexRaw(context.getRuntime(), RubyFixnum.zero(context.getRuntime()), this);
    }
    
    @JRubyMethod(name = { "-@" })
    public IRubyObject op_uminus(final ThreadContext context) {
        final RubyArray ary = RubyFixnum.zero(context.getRuntime()).doCoerce(context, this, true);
        return ary.eltInternal(0).callMethod(context, "-", ary.eltInternal(1));
    }
    
    @JRubyMethod(name = { "<=>" })
    public IRubyObject op_cmp(final IRubyObject other) {
        if (this == other) {
            return RubyFixnum.zero(this.getRuntime());
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "eql?" })
    public IRubyObject eql_p(final ThreadContext context, final IRubyObject other) {
        if (this.getClass() != other.getClass()) {
            return this.getRuntime().getFalse();
        }
        return RubyObject.equalInternal(context, this, other) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "quo", "fdiv" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject quo(final ThreadContext context, final IRubyObject other) {
        return this.callMethod(context, "/", other);
    }
    
    @JRubyMethod(name = { "quo" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject quo_19(final ThreadContext context, final IRubyObject other) {
        return RubyRational.newRationalRaw(context.getRuntime(), this).callMethod(context, "/", other);
    }
    
    @JRubyMethod(name = { "div" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject div(final ThreadContext context, final IRubyObject other) {
        return this.callMethod(context, "/", other).convertToFloat().floor();
    }
    
    @JRubyMethod(name = { "div" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject div19(final ThreadContext context, final IRubyObject other) {
        return this.callMethod(context, "/", other).callMethod(context, "floor");
    }
    
    @JRubyMethod(name = { "divmod" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject divmod(final ThreadContext context, final IRubyObject other) {
        return RubyArray.newArray(this.getRuntime(), this.div(context, other), this.modulo(context, other));
    }
    
    @JRubyMethod(name = { "divmod" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject divmod19(final ThreadContext context, final IRubyObject other) {
        return RubyArray.newArray(this.getRuntime(), this.div(context, other), this.modulo19(context, other));
    }
    
    @JRubyMethod(name = { "fdiv" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject fdiv(final ThreadContext context, final IRubyObject other) {
        return RuntimeHelpers.invoke(context, this.convertToFloat(), "/", other);
    }
    
    @JRubyMethod(name = { "modulo" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject modulo(final ThreadContext context, final IRubyObject other) {
        return this.callMethod(context, "%", other);
    }
    
    @JRubyMethod(name = { "modulo" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject modulo19(final ThreadContext context, final IRubyObject other) {
        return this.callMethod(context, "-", other.callMethod(context, "*", this.callMethod(context, "div", other)));
    }
    
    @JRubyMethod(name = { "remainder" })
    public IRubyObject remainder(final ThreadContext context, final IRubyObject dividend) {
        final IRubyObject z = this.callMethod(context, "%", dividend);
        final RubyFixnum zero = RubyFixnum.zero(this.getRuntime());
        if (!RubyObject.equalInternal(context, z, zero) && ((super.callMethod(context, "<", zero).isTrue() && dividend.callMethod(context, ">", zero).isTrue()) || (super.callMethod(context, ">", zero).isTrue() && dividend.callMethod(context, "<", zero).isTrue()))) {
            return z.callMethod(context, "-", dividend);
        }
        return z;
    }
    
    @JRubyMethod(name = { "abs" })
    public IRubyObject abs(final ThreadContext context) {
        if (this.callMethod(context, "<", RubyFixnum.zero(this.getRuntime())).isTrue()) {
            return this.callMethod(context, "-@");
        }
        return this;
    }
    
    @JRubyMethod(name = { "magnitude" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject magnitude(final ThreadContext context) {
        return this.abs(context);
    }
    
    @JRubyMethod(name = { "to_int" })
    public IRubyObject to_int(final ThreadContext context) {
        return RuntimeHelpers.invoke(context, this, "to_i");
    }
    
    @JRubyMethod(name = { "real?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject scalar_p() {
        return this.getRuntime().getTrue();
    }
    
    @JRubyMethod(name = { "integer?" })
    public IRubyObject integer_p() {
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "zero?" })
    public IRubyObject zero_p(final ThreadContext context) {
        return RubyObject.equalInternal(context, this, RubyFixnum.zero(this.getRuntime())) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "nonzero?" })
    public IRubyObject nonzero_p(final ThreadContext context) {
        if (this.callMethod(context, "zero?").isTrue()) {
            return this.getRuntime().getNil();
        }
        return this;
    }
    
    @JRubyMethod(name = { "floor" })
    public IRubyObject floor() {
        return this.convertToFloat().floor();
    }
    
    @JRubyMethod(name = { "ceil" })
    public IRubyObject ceil() {
        return this.convertToFloat().ceil();
    }
    
    @JRubyMethod(name = { "round" })
    public IRubyObject round() {
        return this.convertToFloat().round();
    }
    
    @JRubyMethod(name = { "truncate" })
    public IRubyObject truncate() {
        return this.convertToFloat().truncate();
    }
    
    @JRubyMethod
    public IRubyObject step(final ThreadContext context, final IRubyObject arg0, final Block block) {
        return block.isGiven() ? this.stepCommon(context, arg0, RubyFixnum.one(context.getRuntime()), block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "step", arg0);
    }
    
    @JRubyMethod
    public IRubyObject step(final ThreadContext context, final IRubyObject to, final IRubyObject step, final Block block) {
        return block.isGiven() ? this.stepCommon(context, to, step, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "step", new IRubyObject[] { to, step });
    }
    
    private IRubyObject stepCommon(final ThreadContext context, final IRubyObject to, final IRubyObject step, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (this instanceof RubyFixnum && to instanceof RubyFixnum && step instanceof RubyFixnum) {
            fixnumStep(context, runtime, ((RubyFixnum)this).getLongValue(), ((RubyFixnum)to).getLongValue(), ((RubyFixnum)step).getLongValue(), block);
        }
        else if (this instanceof RubyFloat || to instanceof RubyFloat || step instanceof RubyFloat) {
            floatStep19(context, runtime, this, to, step, false, block);
        }
        else {
            duckStep(context, runtime, this, to, step, block);
        }
        return this;
    }
    
    private static void fixnumStep(final ThreadContext context, final Ruby runtime, final long value, final long end, final long diff, final Block block) {
        if (diff == 0L) {
            throw runtime.newArgumentError("step cannot be 0");
        }
        if (diff > 0L) {
            for (long i = value; i <= end; i += diff) {
                block.yield(context, RubyFixnum.newFixnum(runtime, i));
            }
        }
        else {
            for (long i = value; i >= end; i += diff) {
                block.yield(context, RubyFixnum.newFixnum(runtime, i));
            }
        }
    }
    
    protected static void floatStep(final ThreadContext context, final Ruby runtime, final IRubyObject from, final IRubyObject to, final IRubyObject step, final Block block) {
        final double beg = num2dbl(from);
        final double end = num2dbl(to);
        final double unit = num2dbl(step);
        if (unit == 0.0) {
            throw runtime.newArgumentError("step cannot be 0");
        }
        double n = (end - beg) / unit;
        double err = (Math.abs(beg) + Math.abs(end) + Math.abs(end - beg)) / Math.abs(unit) * 2.220446049250313E-16;
        if (err > 0.5) {
            err = 0.5;
        }
        n = Math.floor(n + err) + 1.0;
        for (long i = 0L; i < n; ++i) {
            block.yield(context, RubyFloat.newFloat(runtime, i * unit + beg));
        }
    }
    
    static void floatStep19(final ThreadContext context, final Ruby runtime, final IRubyObject from, final IRubyObject to, final IRubyObject step, final boolean excl, final Block block) {
        final double beg = num2dbl(from);
        final double end = num2dbl(to);
        final double unit = num2dbl(step);
        if (unit == 0.0) {
            throw runtime.newArgumentError("step cannot be 0");
        }
        double n = (end - beg) / unit;
        double err = (Math.abs(beg) + Math.abs(end) + Math.abs(end - beg)) / Math.abs(unit) * 2.220446049250313E-16;
        if (Double.isInfinite(unit)) {
            if (unit > 0.0) {
                block.yield(context, RubyFloat.newFloat(runtime, beg));
            }
        }
        else {
            if (err > 0.5) {
                err = 0.5;
            }
            n = Math.floor(n + err);
            if (!excl) {
                ++n;
            }
            for (long i = 0L; i < n; ++i) {
                block.yield(context, RubyFloat.newFloat(runtime, i * unit + beg));
            }
        }
    }
    
    private static void duckStep(final ThreadContext context, final Ruby runtime, final IRubyObject from, final IRubyObject to, final IRubyObject step, final Block block) {
        IRubyObject i = from;
        for (String cmpString = step.callMethod(context, ">", RubyFixnum.zero(runtime)).isTrue() ? ">" : "<"; !i.callMethod(context, cmpString, to).isTrue(); i = i.callMethod(context, "+", step)) {
            block.yield(context, i);
        }
    }
    
    protected final IRubyObject op_num_equal(final ThreadContext context, final IRubyObject other) {
        if (this == other) {
            return this.getRuntime().getTrue();
        }
        return RuntimeHelpers.invokedynamic(context, other, 1, this);
    }
    
    @JRubyMethod(name = { "numerator" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject numerator(final ThreadContext context) {
        return RubyRational.newRationalConvert(context, this).callMethod(context, "numerator");
    }
    
    @JRubyMethod(name = { "denominator" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject denominator(final ThreadContext context) {
        return RubyRational.newRationalConvert(context, this).callMethod(context, "denominator");
    }
    
    @JRubyMethod(name = { "to_c" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_c(final ThreadContext context) {
        return RubyComplex.newComplexCanonicalize(context, this);
    }
    
    @JRubyMethod(name = { "real" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject real(final ThreadContext context) {
        return this;
    }
    
    @JRubyMethod(name = { "imaginary", "imag" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject image(final ThreadContext context) {
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(name = { "abs2" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject abs2(final ThreadContext context) {
        return Numeric.f_mul(context, this, this);
    }
    
    @JRubyMethod(name = { "arg", "angle", "phase" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject arg(final ThreadContext context) {
        final double value = this.getDoubleValue();
        if (Double.isNaN(value)) {
            return RubyFloat.newFloat(context.getRuntime(), Double.NaN);
        }
        if (Numeric.f_negative_p(context, this) || (value == 0.0 && 1.0 / value == Double.NEGATIVE_INFINITY)) {
            return context.getRuntime().getMath().fastGetConstant("PI");
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(name = { "rectangular", "rect" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rect(final ThreadContext context) {
        return context.getRuntime().newArray(this, RubyFixnum.zero(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "polar" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject polar(final ThreadContext context) {
        return context.getRuntime().newArray(Numeric.f_abs(context, this), Numeric.f_arg(context, this));
    }
    
    @JRubyMethod(name = { "conjugate", "conj" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject conjugate(final ThreadContext context) {
        return this;
    }
    
    public Object toJava(final Class target) {
        return JavaUtil.getNumericConverter(target).coerce(this, target);
    }
    
    static {
        NUMERIC_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyNumeric(runtime, klass);
            }
        };
        biteListCaller18 = new ByteListCaller18();
        biteListCaller19 = new ByteListCaller19();
    }
    
    private static class ByteListCaller18 implements ByteListCaller
    {
        public double yield(final RubyString arg, final boolean strict) {
            return ConvertDouble.byteListToDouble(arg.getByteList(), strict);
        }
    }
    
    private static class ByteListCaller19 implements ByteListCaller
    {
        public double yield(final RubyString arg, final boolean strict) {
            return ConvertDouble.byteListToDouble19(arg.getByteList(), strict);
        }
    }
    
    public static class InvalidIntegerException extends NumberFormatException
    {
        private static final long serialVersionUID = 55019452543252148L;
        
        public InvalidIntegerException() {
        }
        
        public InvalidIntegerException(final String message) {
            super(message);
        }
        
        public Throwable fillInStackTrace() {
            return this;
        }
    }
    
    public static class NumberTooLargeException extends NumberFormatException
    {
        private static final long serialVersionUID = -1835120694982699449L;
        
        public NumberTooLargeException() {
        }
        
        public NumberTooLargeException(final String message) {
            super(message);
        }
        
        public Throwable fillInStackTrace() {
            return this;
        }
    }
    
    private interface ByteListCaller
    {
        double yield(final RubyString p0, final boolean p1);
    }
}
