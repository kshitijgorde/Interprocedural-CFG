// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Visibility;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.regex.Matcher;
import java.math.RoundingMode;
import org.jruby.runtime.Arity;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import org.jruby.anno.JRubyConstant;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "BigDecimal" }, parent = "Numeric")
public class RubyBigDecimal extends RubyNumeric
{
    private static final ObjectAllocator BIGDECIMAL_ALLOCATOR;
    @JRubyConstant
    public static final int ROUND_DOWN = 1;
    @JRubyConstant
    public static final int ROUND_CEILING = 2;
    @JRubyConstant
    public static final int ROUND_UP = 0;
    @JRubyConstant
    public static final int ROUND_HALF_DOWN = 5;
    @JRubyConstant
    public static final int ROUND_HALF_EVEN = 6;
    @JRubyConstant
    public static final int ROUND_HALF_UP = 4;
    @JRubyConstant
    public static final int ROUND_FLOOR = 3;
    @JRubyConstant
    public static final int SIGN_POSITIVE_INFINITE = 3;
    @JRubyConstant
    public static final int EXCEPTION_OVERFLOW = 8;
    @JRubyConstant
    public static final int SIGN_POSITIVE_ZERO = 1;
    @JRubyConstant
    public static final int EXCEPTION_ALL = 255;
    @JRubyConstant
    public static final int SIGN_NEGATIVE_FINITE = -2;
    @JRubyConstant
    public static final int EXCEPTION_UNDERFLOW = 4;
    @JRubyConstant
    public static final int SIGN_NaN = 0;
    @JRubyConstant
    public static final int BASE = 10000;
    @JRubyConstant
    public static final int ROUND_MODE = 256;
    @JRubyConstant
    public static final int SIGN_POSITIVE_FINITE = 2;
    @JRubyConstant
    public static final int EXCEPTION_INFINITY = 1;
    @JRubyConstant
    public static final int SIGN_NEGATIVE_INFINITE = -3;
    @JRubyConstant
    public static final int EXCEPTION_ZERODIVIDE = 1;
    @JRubyConstant
    public static final int SIGN_NEGATIVE_ZERO = -1;
    @JRubyConstant
    public static final int EXCEPTION_NaN = 2;
    private static final BigDecimal TWO;
    private static final double SQRT_10 = 3.1622776601683795;
    private boolean isNaN;
    private int infinitySign;
    private int zeroSign;
    private BigDecimal value;
    private static final Pattern INFINITY_PATTERN;
    private static final Pattern NUMBER_PATTERN;
    
    public static RubyClass createBigDecimal(final Ruby runtime) {
        final RubyClass result = runtime.defineClass("BigDecimal", runtime.getNumeric(), RubyBigDecimal.BIGDECIMAL_ALLOCATOR);
        runtime.getKernel().defineAnnotatedMethods(BigDecimalKernelMethods.class);
        result.setInternalModuleVariable("vpPrecLimit", RubyFixnum.zero(runtime));
        result.setInternalModuleVariable("vpExceptionMode", RubyFixnum.zero(runtime));
        result.setInternalModuleVariable("vpRoundingMode", runtime.newFixnum(4));
        result.defineAnnotatedMethods(RubyBigDecimal.class);
        result.defineAnnotatedConstants(RubyBigDecimal.class);
        return result;
    }
    
    public BigDecimal getValue() {
        return this.value;
    }
    
    public RubyBigDecimal(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.isNaN = false;
        this.infinitySign = 0;
        this.zeroSign = 0;
    }
    
    public RubyBigDecimal(final Ruby runtime, final BigDecimal value) {
        super(runtime, runtime.fastGetClass("BigDecimal"));
        this.isNaN = false;
        this.infinitySign = 0;
        this.zeroSign = 0;
        this.value = value;
    }
    
    public static RubyBigDecimal newBigDecimal(final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
        return newInstance(recv.getRuntime().fastGetClass("BigDecimal"), args);
    }
    
    @JRubyMethod(name = { "ver" }, meta = true)
    public static IRubyObject ver(final IRubyObject recv) {
        return recv.getRuntime().newString("1.0.1");
    }
    
    @JRubyMethod(name = { "_dump" }, optional = 1)
    public IRubyObject dump(final IRubyObject[] args, final Block unusedBlock) {
        final RubyString precision = RubyString.newUnicodeString(this.getRuntime(), "0:");
        return precision.append(this.asString());
    }
    
    @JRubyMethod(name = { "_load" }, required = 1, meta = true)
    public static RubyBigDecimal load(final IRubyObject recv, final IRubyObject from, final Block block) {
        final RubyBigDecimal rubyBigDecimal = (RubyBigDecimal)((RubyClass)recv).allocate();
        final String precisionAndValue = from.convertToString().asJavaString();
        final String value = precisionAndValue.substring(precisionAndValue.indexOf(":") + 1);
        rubyBigDecimal.value = new BigDecimal(value);
        return rubyBigDecimal;
    }
    
    @JRubyMethod(name = { "double_fig" }, meta = true)
    public static IRubyObject double_fig(final IRubyObject recv) {
        return recv.getRuntime().newFixnum(20);
    }
    
    @JRubyMethod(name = { "limit" }, optional = 1, meta = true)
    public static IRubyObject limit(final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = recv.getRuntime();
        final RubyModule c = (RubyModule)recv;
        final IRubyObject nCur = c.searchInternalModuleVariable("vpPrecLimit");
        if (args.length > 0) {
            final IRubyObject arg = args[0];
            if (!arg.isNil()) {
                if (!(arg instanceof RubyFixnum)) {
                    throw runtime.newTypeError(arg, runtime.getFixnum());
                }
                if (0L > ((RubyFixnum)arg).getLongValue()) {
                    throw runtime.newArgumentError("argument must be positive");
                }
                c.setInternalModuleVariable("vpPrecLimit", arg);
            }
        }
        return nCur;
    }
    
    @JRubyMethod(name = { "mode" }, required = 1, optional = 1, meta = true)
    public static IRubyObject mode(final ThreadContext context, final IRubyObject recv, IRubyObject[] args) {
        final Ruby runtime = recv.getRuntime();
        final RubyClass clazz = runtime.fastGetClass("BigDecimal");
        final RubyModule c = (RubyModule)recv;
        args = Arity.scanArgs(runtime, args, 1, 1);
        final IRubyObject mode = args[0];
        final IRubyObject value = args[1];
        if (!(mode instanceof RubyFixnum)) {
            throw runtime.newTypeError("wrong argument type " + mode.getMetaClass() + " (expected Fixnum)");
        }
        final long longMode = ((RubyFixnum)mode).getLongValue();
        final long EXCEPTION_ALL = ((RubyFixnum)clazz.fastGetConstant("EXCEPTION_ALL")).getLongValue();
        if ((longMode & EXCEPTION_ALL) != 0x0L) {
            if (value.isNil()) {
                return c.searchInternalModuleVariable("vpExceptionMode");
            }
            if (!value.isNil() && !(value instanceof RubyBoolean)) {
                throw runtime.newTypeError("second argument must be true or false");
            }
            final RubyFixnum currentExceptionMode = (RubyFixnum)c.searchInternalModuleVariable("vpExceptionMode");
            RubyFixnum newExceptionMode = new RubyFixnum(runtime, currentExceptionMode.getLongValue());
            final RubyFixnum EXCEPTION_INFINITY = (RubyFixnum)clazz.fastGetConstant("EXCEPTION_INFINITY");
            if ((longMode & EXCEPTION_INFINITY.getLongValue()) != 0x0L) {
                newExceptionMode = (RubyFixnum)(value.isTrue() ? currentExceptionMode.callCoerced(context, "|", EXCEPTION_INFINITY) : ((RubyFixnum)currentExceptionMode.callCoerced(context, "&", new RubyFixnum(runtime, ~EXCEPTION_INFINITY.getLongValue()))));
            }
            final RubyFixnum EXCEPTION_NaN = (RubyFixnum)clazz.fastGetConstant("EXCEPTION_NaN");
            if ((longMode & EXCEPTION_NaN.getLongValue()) != 0x0L) {
                newExceptionMode = (RubyFixnum)(value.isTrue() ? currentExceptionMode.callCoerced(context, "|", EXCEPTION_NaN) : ((RubyFixnum)currentExceptionMode.callCoerced(context, "&", new RubyFixnum(runtime, ~EXCEPTION_NaN.getLongValue()))));
            }
            final RubyFixnum EXCEPTION_UNDERFLOW = (RubyFixnum)clazz.fastGetConstant("EXCEPTION_UNDERFLOW");
            if ((longMode & EXCEPTION_UNDERFLOW.getLongValue()) != 0x0L) {
                newExceptionMode = (RubyFixnum)(value.isTrue() ? currentExceptionMode.callCoerced(context, "|", EXCEPTION_UNDERFLOW) : ((RubyFixnum)currentExceptionMode.callCoerced(context, "&", new RubyFixnum(runtime, ~EXCEPTION_UNDERFLOW.getLongValue()))));
            }
            final RubyFixnum EXCEPTION_OVERFLOW = (RubyFixnum)clazz.fastGetConstant("EXCEPTION_OVERFLOW");
            if ((longMode & EXCEPTION_OVERFLOW.getLongValue()) != 0x0L) {
                newExceptionMode = (RubyFixnum)(value.isTrue() ? currentExceptionMode.callCoerced(context, "|", EXCEPTION_OVERFLOW) : ((RubyFixnum)currentExceptionMode.callCoerced(context, "&", new RubyFixnum(runtime, ~EXCEPTION_OVERFLOW.getLongValue()))));
            }
            c.setInternalModuleVariable("vpExceptionMode", newExceptionMode);
            return newExceptionMode;
        }
        else {
            final long ROUND_MODE = ((RubyFixnum)clazz.fastGetConstant("ROUND_MODE")).getLongValue();
            if (longMode != ROUND_MODE) {
                throw runtime.newTypeError("first argument for BigDecimal#mode invalid");
            }
            if (value.isNil()) {
                return c.searchInternalModuleVariable("vpRoundingMode");
            }
            if (!(value instanceof RubyFixnum)) {
                throw runtime.newTypeError("wrong argument type " + mode.getMetaClass() + " (expected Fixnum)");
            }
            final RubyFixnum roundingMode = (RubyFixnum)value;
            if (roundingMode == clazz.fastGetConstant("ROUND_UP") || roundingMode == clazz.fastGetConstant("ROUND_DOWN") || roundingMode == clazz.fastGetConstant("ROUND_FLOOR") || roundingMode == clazz.fastGetConstant("ROUND_CEILING") || roundingMode == clazz.fastGetConstant("ROUND_HALF_UP") || roundingMode == clazz.fastGetConstant("ROUND_HALF_DOWN") || roundingMode == clazz.fastGetConstant("ROUND_HALF_EVEN")) {
                c.setInternalModuleVariable("vpRoundingMode", roundingMode);
                return c.searchInternalModuleVariable("vpRoundingMode");
            }
            throw runtime.newTypeError("invalid rounding mode");
        }
    }
    
    private RoundingMode getRoundingMode(final Ruby runtime) {
        final RubyFixnum roundingMode = (RubyFixnum)runtime.fastGetClass("BigDecimal").searchInternalModuleVariable("vpRoundingMode");
        return RoundingMode.valueOf((int)roundingMode.getLongValue());
    }
    
    private static boolean isNaNExceptionMode(final Ruby runtime) {
        final RubyFixnum currentExceptionMode = (RubyFixnum)runtime.fastGetClass("BigDecimal").searchInternalModuleVariable("vpExceptionMode");
        final RubyFixnum EXCEPTION_NaN = (RubyFixnum)runtime.fastGetClass("BigDecimal").fastGetConstant("EXCEPTION_NaN");
        return (currentExceptionMode.getLongValue() & EXCEPTION_NaN.getLongValue()) != 0x0L;
    }
    
    private static boolean isInfinityExceptionMode(final Ruby runtime) {
        final RubyFixnum currentExceptionMode = (RubyFixnum)runtime.fastGetClass("BigDecimal").searchInternalModuleVariable("vpExceptionMode");
        final RubyFixnum EXCEPTION_INFINITY = (RubyFixnum)runtime.fastGetClass("BigDecimal").fastGetConstant("EXCEPTION_INFINITY");
        return (currentExceptionMode.getLongValue() & EXCEPTION_INFINITY.getLongValue()) != 0x0L;
    }
    
    private static boolean isOverflowExceptionMode(final Ruby runtime) {
        final RubyFixnum currentExceptionMode = (RubyFixnum)runtime.fastGetClass("BigDecimal").searchInternalModuleVariable("vpExceptionMode");
        final RubyFixnum EXCEPTION_OVERFLOW = (RubyFixnum)runtime.fastGetClass("BigDecimal").fastGetConstant("EXCEPTION_OVERFLOW");
        return (currentExceptionMode.getLongValue() & EXCEPTION_OVERFLOW.getLongValue()) != 0x0L;
    }
    
    private static RubyBigDecimal getVpValue(final IRubyObject v, final boolean must) {
        if (v instanceof RubyBigDecimal) {
            return (RubyBigDecimal)v;
        }
        if (v instanceof RubyFixnum || v instanceof RubyBignum) {
            final String s = v.toString();
            return newInstance(v.getRuntime().fastGetClass("BigDecimal"), new IRubyObject[] { v.getRuntime().newString(s) });
        }
        if (must) {
            String err;
            if (v.isImmediate()) {
                final ThreadContext context = v.getRuntime().getCurrentContext();
                err = RubyObject.inspect(context, v).toString();
            }
            else {
                err = v.getMetaClass().getBaseName();
            }
            throw v.getRuntime().newTypeError(err + " can't be coerced into BigDecimal");
        }
        return null;
    }
    
    @JRubyMethod(name = { "induced_from" }, required = 1, meta = true)
    public static IRubyObject induced_from(final IRubyObject recv, final IRubyObject arg) {
        return getVpValue(arg, true);
    }
    
    @JRubyMethod(name = { "new" }, required = 1, optional = 1, meta = true)
    public static RubyBigDecimal newInstance(final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = recv.getRuntime();
        BigDecimal decimal;
        if (args.length == 0) {
            decimal = new BigDecimal(0);
        }
        else {
            String strValue = args[0].convertToString().toString();
            strValue = strValue.trim();
            if ("NaN".equals(strValue)) {
                return newNaN(runtime);
            }
            final Matcher m = RubyBigDecimal.INFINITY_PATTERN.matcher(strValue);
            if (m.matches()) {
                int sign = 1;
                final String signGroup = m.group(1);
                if ("-".equals(signGroup)) {
                    sign = -1;
                }
                return newInfinity(runtime, sign);
            }
            strValue = strValue.replaceFirst("[dD]", "E");
            strValue = strValue.replaceAll("_", "");
            strValue = RubyBigDecimal.NUMBER_PATTERN.matcher(strValue).replaceFirst("$1");
            try {
                decimal = new BigDecimal(strValue);
            }
            catch (NumberFormatException e) {
                if (isOverflowExceptionMode(runtime)) {
                    throw runtime.newFloatDomainError("exponent overflow");
                }
                decimal = new BigDecimal(0);
            }
            if (decimal.signum() == 0) {
                if (strValue.matches("^\\s*-.*")) {
                    return newZero(runtime, -1);
                }
                return newZero(runtime, 1);
            }
        }
        return new RubyBigDecimal(runtime, decimal);
    }
    
    private static RubyBigDecimal newZero(final Ruby runtime, final int sign) {
        final RubyBigDecimal rbd = new RubyBigDecimal(runtime, BigDecimal.ZERO);
        if (sign < 0) {
            rbd.zeroSign = -1;
        }
        else {
            rbd.zeroSign = 1;
        }
        return rbd;
    }
    
    private static RubyBigDecimal newNaN(final Ruby runtime) {
        if (isNaNExceptionMode(runtime)) {
            throw runtime.newFloatDomainError("Computation results to 'NaN'(Not a Number)");
        }
        final RubyBigDecimal rbd = new RubyBigDecimal(runtime, BigDecimal.ZERO);
        rbd.isNaN = true;
        return rbd;
    }
    
    private static RubyBigDecimal newInfinity(final Ruby runtime, final int sign) {
        final RubyBigDecimal rbd = new RubyBigDecimal(runtime, BigDecimal.ZERO);
        if (isInfinityExceptionMode(runtime)) {
            throw runtime.newFloatDomainError("Computation results to 'Infinity'");
        }
        if (sign < 0) {
            rbd.infinitySign = -1;
        }
        else {
            rbd.infinitySign = 1;
        }
        return rbd;
    }
    
    private RubyBigDecimal setResult() {
        return this.setResult(0);
    }
    
    private RubyBigDecimal setResult(final int scale) {
        final int prec = RubyNumeric.fix2int(this.getRuntime().fastGetClass("BigDecimal").searchInternalModuleVariable("vpPrecLimit"));
        final int prec2 = Math.max(scale, prec);
        if (prec2 > 0 && this.value.scale() > prec2 - this.getExponent()) {
            this.value = this.value.setScale(prec2 - this.getExponent(), 4);
        }
        return this;
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.value.hashCode());
    }
    
    @JRubyMethod(name = { "%", "modulo" }, required = 1)
    public IRubyObject op_mod(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.isInfinity() || this.isNaN()) {
            return newNaN(runtime);
        }
        final RubyBigDecimal val = getVpValue(arg, false);
        if (val == null) {
            return this.callCoerced(context, "%", arg, true);
        }
        if (val.isInfinity() || val.isNaN() || val.isZero()) {
            return newNaN(runtime);
        }
        BigDecimal modulo = this.value.remainder(val.value);
        if (modulo.signum() * val.value.signum() < 0) {
            modulo = modulo.add(val.value);
        }
        return new RubyBigDecimal(runtime, modulo).setResult();
    }
    
    @JRubyMethod(name = { "remainder" }, required = 1)
    public IRubyObject remainder(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (this.isInfinity() || this.isNaN()) {
            return newNaN(runtime);
        }
        final RubyBigDecimal val = getVpValue(arg, false);
        if (val == null) {
            return this.callCoerced(context, "remainder", arg, true);
        }
        if (val.isInfinity() || val.isNaN() || val.isZero()) {
            return newNaN(runtime);
        }
        return new RubyBigDecimal(runtime, this.value.remainder(val.value)).setResult();
    }
    
    @JRubyMethod(name = { "*" }, required = 1)
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject arg) {
        return this.mult2(context, arg, this.getRuntime().fastGetClass("BigDecimal").searchInternalModuleVariable("vpPrecLimit"));
    }
    
    @JRubyMethod(name = { "mult" }, required = 2)
    public IRubyObject mult2(final ThreadContext context, final IRubyObject b, final IRubyObject n) {
        final Ruby runtime = context.getRuntime();
        final RubyBigDecimal val = getVpValue(b, false);
        if (val == null) {
            return this.callCoerced(context, "*", b);
        }
        final int digits = RubyNumeric.fix2int(n);
        if (this.isNaN() || val.isNaN()) {
            return newNaN(runtime);
        }
        if ((this.isInfinity() && val.isZero()) || (this.isZero() && val.isInfinity())) {
            return newNaN(runtime);
        }
        if (this.isZero() || val.isZero()) {
            final int sign1 = this.isZero() ? this.zeroSign : this.value.signum();
            final int sign2 = val.isZero() ? val.zeroSign : val.value.signum();
            return newZero(runtime, sign1 * sign2);
        }
        if (this.isInfinity() || val.isInfinity()) {
            final int sign1 = this.isInfinity() ? this.infinitySign : this.value.signum();
            final int sign2 = val.isInfinity() ? val.infinitySign : val.value.signum();
            return newInfinity(runtime, sign1 * sign2);
        }
        BigDecimal res = this.value.multiply(val.value);
        if (res.precision() > digits) {
            res = res.round(new MathContext(digits, RoundingMode.HALF_UP));
        }
        return new RubyBigDecimal(runtime, res).setResult();
    }
    
    @JRubyMethod(name = { "**", "power" }, required = 1)
    public IRubyObject op_pow(final IRubyObject arg) {
        if (!(arg instanceof RubyFixnum)) {
            throw this.getRuntime().newTypeError("wrong argument type " + arg.getMetaClass() + " (expected Fixnum)");
        }
        if (this.isNaN() || this.isInfinity()) {
            return newNaN(this.getRuntime());
        }
        final int times = RubyNumeric.fix2int(arg.convertToInteger());
        if (times >= 0) {
            return new RubyBigDecimal(this.getRuntime(), this.value.pow(times));
        }
        if (this.isZero()) {
            return newInfinity(this.getRuntime(), this.value.signum());
        }
        final int precision = (-times + 4) * (this.getAllDigits().length() + 4);
        return new RubyBigDecimal(this.getRuntime(), this.value.pow(times, new MathContext(precision, RoundingMode.HALF_UP)));
    }
    
    @JRubyMethod(name = { "+" })
    public IRubyObject op_plus(final ThreadContext context, final IRubyObject b) {
        return this.addInternal(context, b, "add", this.getRuntime().fastGetClass("BigDecimal").searchInternalModuleVariable("vpPrecLimit"));
    }
    
    @JRubyMethod(name = { "add" })
    public IRubyObject add2(final ThreadContext context, final IRubyObject b, final IRubyObject digits) {
        return this.addInternal(context, b, "add", digits);
    }
    
    private IRubyObject addInternal(final ThreadContext context, final IRubyObject b, final String op, final IRubyObject digits) {
        final Ruby runtime = context.getRuntime();
        final int prec = this.getPositiveInt(context, digits);
        final RubyBigDecimal val = getVpValue(b, false);
        if (val == null) {
            return this.callCoerced(context, "+", b, true);
        }
        final RubyBigDecimal res = this.handleAddSpecialValues(val);
        if (res != null) {
            return res;
        }
        final RoundingMode roundMode = this.getRoundingMode(runtime);
        return new RubyBigDecimal(runtime, this.value.add(val.value, new MathContext(prec, roundMode)));
    }
    
    private int getPositiveInt(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        if (!(arg instanceof RubyFixnum)) {
            throw runtime.newTypeError(arg, runtime.getFixnum());
        }
        final int value = RubyNumeric.fix2int(arg);
        if (value < 0) {
            throw runtime.newArgumentError("argument must be positive");
        }
        return value;
    }
    
    private RubyBigDecimal handleAddSpecialValues(final RubyBigDecimal val) {
        if (this.isNaN() || val.isNaN) {
            return newNaN(this.getRuntime());
        }
        if (this.infinitySign * val.infinitySign > 0) {
            return this.isInfinity() ? this : val;
        }
        if (this.infinitySign * val.infinitySign < 0) {
            return newNaN(this.getRuntime());
        }
        if (this.infinitySign * val.infinitySign == 0) {
            final int sign = this.infinitySign + val.infinitySign;
            if (sign != 0) {
                return newInfinity(this.getRuntime(), sign);
            }
        }
        return null;
    }
    
    @JRubyMethod(name = { "+@" })
    public IRubyObject op_uplus() {
        return this;
    }
    
    @JRubyMethod(name = { "-" }, required = 1)
    public IRubyObject op_minus(final ThreadContext context, final IRubyObject arg) {
        final RubyBigDecimal val = getVpValue(arg, false);
        if (val == null) {
            return this.callCoerced(context, "-", arg);
        }
        final RubyBigDecimal res = this.handleMinusSpecialValues(val);
        if (res != null) {
            return res;
        }
        return new RubyBigDecimal(this.getRuntime(), this.value.subtract(val.value)).setResult();
    }
    
    @JRubyMethod(name = { "sub" }, required = 2)
    public IRubyObject sub2(final ThreadContext context, final IRubyObject b, final IRubyObject n) {
        final RubyBigDecimal val = getVpValue(b, false);
        if (val == null) {
            return this.callCoerced(context, "-", b);
        }
        final RubyBigDecimal res = this.handleMinusSpecialValues(val);
        if (res != null) {
            return res;
        }
        return new RubyBigDecimal(this.getRuntime(), this.value.subtract(val.value)).setResult();
    }
    
    private RubyBigDecimal handleMinusSpecialValues(final RubyBigDecimal val) {
        if (this.isNaN() || val.isNaN()) {
            return newNaN(this.getRuntime());
        }
        if (this.infinitySign * val.infinitySign > 0) {
            return newNaN(this.getRuntime());
        }
        if (this.infinitySign * val.infinitySign < 0) {
            return this;
        }
        if (this.infinitySign * val.infinitySign == 0) {
            if (this.isInfinity()) {
                return this;
            }
            if (val.isInfinity()) {
                return newInfinity(this.getRuntime(), val.infinitySign * -1);
            }
            final int sign = this.infinitySign + val.infinitySign;
            if (sign != 0) {
                return newInfinity(this.getRuntime(), sign);
            }
        }
        return null;
    }
    
    @JRubyMethod(name = { "-@" })
    public IRubyObject op_uminus() {
        final Ruby runtime = this.getRuntime();
        if (this.isNaN()) {
            return newNaN(runtime);
        }
        if (this.isInfinity()) {
            return newInfinity(runtime, -this.infinitySign);
        }
        if (this.isZero()) {
            return newZero(runtime, -this.zeroSign);
        }
        return new RubyBigDecimal(this.getRuntime(), this.value.negate());
    }
    
    @JRubyMethod(name = { "/", "quo" })
    public IRubyObject op_quo(final ThreadContext context, final IRubyObject other) {
        return this.op_div(context, other, this.getRuntime().newFixnum(200));
    }
    
    @JRubyMethod(name = { "div" })
    public IRubyObject op_div(final ThreadContext context, final IRubyObject other) {
        final RubyBigDecimal val = getVpValue(other, false);
        if (val == null) {
            return this.callCoerced(context, "div", other);
        }
        if (this.isNaN() || val.isZero() || val.isNaN()) {
            return newNaN(this.getRuntime());
        }
        if (this.isInfinity() || val.isInfinity()) {
            return newNaN(this.getRuntime());
        }
        return new RubyBigDecimal(this.getRuntime(), this.value.divideToIntegralValue(val.value)).setResult();
    }
    
    @JRubyMethod(name = { "div" })
    public IRubyObject op_div(final ThreadContext context, final IRubyObject other, final IRubyObject digits) {
        final int scale = RubyNumeric.fix2int(digits);
        final RubyBigDecimal val = getVpValue(other, false);
        if (val == null) {
            return this.callCoerced(context, "/", other);
        }
        if (this.isNaN() || (this.isZero() && val.isZero()) || val.isNaN()) {
            return newNaN(this.getRuntime());
        }
        if (val.isZero()) {
            final int sign1 = this.isInfinity() ? this.infinitySign : this.value.signum();
            return newInfinity(this.getRuntime(), sign1 * val.zeroSign);
        }
        if (this.isInfinity() && !val.isInfinity()) {
            return newInfinity(this.getRuntime(), this.infinitySign * val.value.signum());
        }
        if (!this.isInfinity() && val.isInfinity()) {
            return newZero(this.getRuntime(), this.value.signum() * val.infinitySign);
        }
        if (this.isInfinity() && val.isInfinity()) {
            return newNaN(this.getRuntime());
        }
        if (scale == 0) {
            return this.op_quo(context, other);
        }
        final int prec = Math.max(200, scale);
        return new RubyBigDecimal(this.getRuntime(), this.value.divide(val.value, new MathContext(prec, RoundingMode.HALF_UP))).setResult(scale);
    }
    
    private IRubyObject cmp(final ThreadContext context, final IRubyObject r, final char op) {
        int e = 0;
        final RubyBigDecimal rb = getVpValue(r, false);
        if (rb == null) {
            final IRubyObject ee = this.callCoerced(context, "<=>", r);
            if (ee.isNil()) {
                if (op == '*') {
                    return this.getRuntime().getNil();
                }
                if (op == '=' || this.isNaN()) {
                    return this.getRuntime().getFalse();
                }
                throw this.getRuntime().newArgumentError("nil could not be coerced into a BigDecmil");
            }
            else {
                e = RubyNumeric.fix2int(ee);
            }
        }
        else {
            if (this.isNaN() | rb.isNaN()) {
                return (op == '*') ? this.getRuntime().getNil() : this.getRuntime().getFalse();
            }
            if (this.infinitySign != 0 || rb.infinitySign != 0) {
                e = this.infinitySign - rb.infinitySign;
            }
            else {
                e = this.value.compareTo(rb.value);
            }
        }
        switch (op) {
            case '*': {
                return this.getRuntime().newFixnum(e);
            }
            case '=': {
                return (e == 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            case '!': {
                return (e != 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            case 'G': {
                return (e >= 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            case '>': {
                return (e > 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            case 'L': {
                return (e <= 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            case '<': {
                return (e < 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
            }
            default: {
                return this.getRuntime().getNil();
            }
        }
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject arg) {
        return this.cmp(context, arg, '*');
    }
    
    @JRubyMethod(name = { "eql?", "==", "===" }, required = 1)
    public IRubyObject eql_p(final ThreadContext context, final IRubyObject arg) {
        return this.cmp(context, arg, '=');
    }
    
    @JRubyMethod(name = { "<" }, required = 1)
    public IRubyObject op_lt(final ThreadContext context, final IRubyObject arg) {
        return this.cmp(context, arg, '<');
    }
    
    @JRubyMethod(name = { "<=" }, required = 1)
    public IRubyObject op_le(final ThreadContext context, final IRubyObject arg) {
        return this.cmp(context, arg, 'L');
    }
    
    @JRubyMethod(name = { ">" }, required = 1)
    public IRubyObject op_gt(final ThreadContext context, final IRubyObject arg) {
        return this.cmp(context, arg, '>');
    }
    
    @JRubyMethod(name = { ">=" }, required = 1)
    public IRubyObject op_ge(final ThreadContext context, final IRubyObject arg) {
        return this.cmp(context, arg, 'G');
    }
    
    @JRubyMethod(name = { "abs" })
    public IRubyObject abs() {
        final Ruby runtime = this.getRuntime();
        if (this.isNaN) {
            return newNaN(runtime);
        }
        if (this.isInfinity()) {
            return newInfinity(runtime, 1);
        }
        return new RubyBigDecimal(this.getRuntime(), this.value.abs()).setResult();
    }
    
    @JRubyMethod(name = { "ceil" }, optional = 1)
    public IRubyObject ceil(final IRubyObject[] args) {
        if (this.isNaN) {
            return newNaN(this.getRuntime());
        }
        if (this.isInfinity()) {
            return newInfinity(this.getRuntime(), this.infinitySign);
        }
        int n = 0;
        if (args.length > 0) {
            n = RubyNumeric.fix2int(args[0]);
        }
        if (this.value.scale() > n) {
            return new RubyBigDecimal(this.getRuntime(), this.value.setScale(n, RoundingMode.CEILING));
        }
        return this;
    }
    
    @JRubyMethod(name = { "ceil" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject ceil19(final IRubyObject[] args) {
        this.checkFloatDomain();
        if (args.length != 0) {
            return this.ceil(args);
        }
        final BigInteger ceil = this.value.setScale(0, RoundingMode.CEILING).toBigInteger();
        if (ceil.compareTo(BigInteger.valueOf(ceil.intValue())) == 0) {
            return RubyNumeric.int2fix(this.getRuntime(), ceil.intValue());
        }
        return RubyBignum.newBignum(this.getRuntime(), ceil);
    }
    
    @JRubyMethod(name = { "coerce" }, required = 1)
    public IRubyObject coerce(final IRubyObject other) {
        IRubyObject obj;
        if (other instanceof RubyFloat) {
            obj = this.getRuntime().newArray(other, this.to_f());
        }
        else {
            obj = this.getRuntime().newArray(getVpValue(other, true), this);
        }
        return obj;
    }
    
    public double getDoubleValue() {
        return this.value.doubleValue();
    }
    
    public long getLongValue() {
        return this.value.longValue();
    }
    
    public BigInteger getBigIntegerValue() {
        return this.value.toBigInteger();
    }
    
    public RubyNumeric multiplyWith(final ThreadContext context, final RubyInteger value) {
        return (RubyNumeric)this.op_mul(context, value);
    }
    
    public RubyNumeric multiplyWith(final ThreadContext context, final RubyFloat value) {
        return (RubyNumeric)this.op_mul(context, value);
    }
    
    public RubyNumeric multiplyWith(final ThreadContext context, final RubyBignum value) {
        return (RubyNumeric)this.op_mul(context, value);
    }
    
    @JRubyMethod(name = { "divmod" }, required = 1)
    public IRubyObject divmod(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (this.isInfinity() || this.isNaN()) {
            return RubyArray.newArray(runtime, newNaN(runtime), newNaN(runtime));
        }
        final RubyBigDecimal val = getVpValue(other, false);
        if (val == null) {
            return this.callCoerced(context, "divmod", other, true);
        }
        if (val.isInfinity() || val.isNaN() || val.isZero()) {
            return RubyArray.newArray(runtime, newNaN(runtime), newNaN(runtime));
        }
        final BigDecimal[] divmod = this.value.divideAndRemainder(val.value);
        BigDecimal div = divmod[0];
        BigDecimal mod = divmod[1];
        if (mod.signum() * val.value.signum() < 0) {
            div = div.subtract(BigDecimal.ONE);
            mod = mod.add(val.value);
        }
        return RubyArray.newArray(runtime, new RubyBigDecimal(runtime, div), new RubyBigDecimal(runtime, mod));
    }
    
    @JRubyMethod(name = { "exponent" })
    public IRubyObject exponent() {
        return this.getRuntime().newFixnum(this.getExponent());
    }
    
    @JRubyMethod(name = { "finite?" })
    public IRubyObject finite_p() {
        if (this.isNaN()) {
            return this.getRuntime().getFalse();
        }
        return this.getRuntime().newBoolean(!this.isInfinity());
    }
    
    @JRubyMethod(name = { "floor" }, optional = 1)
    public IRubyObject floor(final IRubyObject[] args) {
        if (this.isNaN) {
            return newNaN(this.getRuntime());
        }
        if (this.isInfinity()) {
            return newInfinity(this.getRuntime(), this.infinitySign);
        }
        int n = 0;
        if (args.length > 0) {
            n = RubyNumeric.fix2int(args[0]);
        }
        if (this.value.scale() > n) {
            return new RubyBigDecimal(this.getRuntime(), this.value.setScale(n, RoundingMode.FLOOR));
        }
        return this;
    }
    
    @JRubyMethod(name = { "floor" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject floor19(final IRubyObject[] args) {
        if (this.isNaN || this.isInfinity()) {
            throw this.getRuntime().newFloatDomainError("Computation results to '" + this.to_s(args).asJavaString() + "'");
        }
        return this.floor(args);
    }
    
    @JRubyMethod(name = { "frac" })
    public IRubyObject frac() {
        if (this.isNaN) {
            return newNaN(this.getRuntime());
        }
        if (this.isInfinity()) {
            return newInfinity(this.getRuntime(), this.infinitySign);
        }
        if (this.value.scale() > 0 && this.value.precision() < this.value.scale()) {
            return new RubyBigDecimal(this.getRuntime(), this.value);
        }
        final BigDecimal val = this.value.subtract(((RubyBigDecimal)this.fix()).value);
        return new RubyBigDecimal(this.getRuntime(), val);
    }
    
    @JRubyMethod(name = { "infinite?" })
    public IRubyObject infinite_p() {
        if (this.infinitySign == 0) {
            return this.getRuntime().getNil();
        }
        return this.getRuntime().newFixnum(this.infinitySign);
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        final StringBuilder val = new StringBuilder("#<BigDecimal:").append(Integer.toHexString(System.identityHashCode(this))).append(",");
        val.append("'").append(this.callMethod(context, "to_s")).append("'").append(",");
        val.append(this.getSignificantDigits().length()).append("(");
        final int len = this.getAllDigits().length();
        final int pow = len / 4;
        val.append((pow + 1) * 4).append(")").append(">");
        return this.getRuntime().newString(val.toString());
    }
    
    @JRubyMethod(name = { "nan?" })
    public IRubyObject nan_p() {
        return this.getRuntime().newBoolean(this.isNaN);
    }
    
    @JRubyMethod(name = { "nonzero?" })
    public IRubyObject nonzero_p() {
        return this.isZero() ? this.getRuntime().getNil() : this;
    }
    
    @JRubyMethod(name = { "precs" })
    public IRubyObject precs() {
        final Ruby runtime = this.getRuntime();
        final IRubyObject[] array = { runtime.newFixnum(this.getSignificantDigits().length()), null };
        final int len = this.getAllDigits().length();
        final int pow = len / 4;
        array[1] = runtime.newFixnum((pow + 1) * 4);
        return RubyArray.newArrayNoCopy(runtime, array);
    }
    
    @Deprecated
    public IRubyObject round(final IRubyObject[] args) {
        return this.round(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(name = { "round" }, optional = 2)
    public IRubyObject round(final ThreadContext context, final IRubyObject[] args) {
        final int scale = (args.length > 0) ? RubyNumeric.num2int(args[0]) : 0;
        final RoundingMode mode = (args.length > 1) ? this.javaRoundingModeFromRubyRoundingMode(args[1]) : this.getRoundingMode(context.runtime);
        if (scale < 0) {
            final BigDecimal normalized = this.value.movePointRight(scale);
            final BigDecimal rounded = normalized.setScale(0, mode);
            return new RubyBigDecimal(this.getRuntime(), rounded.movePointLeft(scale));
        }
        return new RubyBigDecimal(this.getRuntime(), this.value.setScale(scale, mode));
    }
    
    private RoundingMode javaRoundingModeFromRubyRoundingMode(final IRubyObject arg) {
        return RoundingMode.valueOf(RubyNumeric.num2int(arg));
    }
    
    @JRubyMethod(name = { "sign" })
    public IRubyObject sign() {
        if (this.isNaN()) {
            return this.getMetaClass().fastGetConstant("SIGN_NaN");
        }
        if (this.isInfinity()) {
            if (this.infinitySign < 0) {
                return this.getMetaClass().fastGetConstant("SIGN_NEGATIVE_INFINITE");
            }
            return this.getMetaClass().fastGetConstant("SIGN_POSITIVE_INFINITE");
        }
        else if (this.isZero()) {
            if (this.zeroSign < 0) {
                return this.getMetaClass().fastGetConstant("SIGN_NEGATIVE_ZERO");
            }
            return this.getMetaClass().fastGetConstant("SIGN_POSITIVE_ZERO");
        }
        else {
            if (this.value.signum() < 0) {
                return this.getMetaClass().fastGetConstant("SIGN_NEGATIVE_FINITE");
            }
            return this.getMetaClass().fastGetConstant("SIGN_POSITIVE_FINITE");
        }
    }
    
    @JRubyMethod(name = { "split" })
    public RubyArray split() {
        final Ruby runtime = this.getRuntime();
        final IRubyObject[] array = new IRubyObject[4];
        RubyFixnum sign;
        if (this.isNaN) {
            sign = RubyFixnum.zero(runtime);
        }
        else if (this.isInfinity()) {
            sign = runtime.newFixnum(this.infinitySign);
        }
        else if (this.isZero()) {
            sign = runtime.newFixnum(this.zeroSign);
        }
        else {
            sign = runtime.newFixnum(this.value.signum());
        }
        array[0] = sign;
        RubyString digits;
        RubyFixnum exp;
        if (this.isNaN()) {
            digits = runtime.newString("NaN");
            exp = RubyFixnum.zero(runtime);
        }
        else if (this.isInfinity()) {
            digits = runtime.newString("Infinity");
            exp = RubyFixnum.zero(runtime);
        }
        else if (this.isZero()) {
            digits = runtime.newString("0");
            exp = RubyFixnum.zero(runtime);
        }
        else {
            digits = runtime.newString(this.getSignificantDigits());
            exp = runtime.newFixnum(this.getExponent());
        }
        array[1] = digits;
        array[3] = exp;
        array[2] = runtime.newFixnum(10);
        return RubyArray.newArrayNoCopy(runtime, array);
    }
    
    private String getSignificantDigits() {
        final BigDecimal val = this.value.abs().stripTrailingZeros();
        return val.unscaledValue().toString();
    }
    
    private String getAllDigits() {
        final BigDecimal val = this.value.abs();
        return val.unscaledValue().toString();
    }
    
    private int getExponent() {
        if (this.isZero()) {
            return 0;
        }
        final BigDecimal val = this.value.abs().stripTrailingZeros();
        return val.precision() - val.scale();
    }
    
    @JRubyMethod(name = { "sqrt" }, required = 1)
    public IRubyObject sqrt(final IRubyObject arg) {
        final Ruby runtime = this.getRuntime();
        if (this.isNaN()) {
            throw runtime.newFloatDomainError("(VpSqrt) SQRT(NaN value)");
        }
        if ((this.isInfinity() && this.infinitySign < 0) || this.value.signum() < 0) {
            throw runtime.newFloatDomainError("(VpSqrt) SQRT(negative value)");
        }
        if (this.isInfinity() && this.infinitySign > 0) {
            return newInfinity(runtime, 1);
        }
        int n = RubyNumeric.fix2int(arg);
        if (n < 0) {
            throw runtime.newArgumentError("argument must be positive");
        }
        n += 4;
        return new RubyBigDecimal(this.getRuntime(), bigSqrt(this.value, new MathContext(n, RoundingMode.HALF_UP))).setResult();
    }
    
    @JRubyMethod(name = { "to_f" })
    public IRubyObject to_f() {
        if (this.isNaN()) {
            return RubyFloat.newFloat(this.getRuntime(), Double.NaN);
        }
        if (this.isInfinity()) {
            return RubyFloat.newFloat(this.getRuntime(), (this.infinitySign < 0) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
        }
        if (this.isZero()) {
            return RubyFloat.newFloat(this.getRuntime(), (this.zeroSign < 0) ? -0.0 : 0.0);
        }
        if (-this.value.scale() > 308) {
            switch (this.value.signum()) {
                case -1: {
                    return RubyFloat.newFloat(this.getRuntime(), Double.NEGATIVE_INFINITY);
                }
                case 0: {
                    return RubyFloat.newFloat(this.getRuntime(), 0.0);
                }
                case 1: {
                    return RubyFloat.newFloat(this.getRuntime(), Double.POSITIVE_INFINITY);
                }
            }
        }
        return RubyFloat.newFloat(this.getRuntime(), this.value.doubleValue());
    }
    
    @JRubyMethod(name = { "to_i", "to_int" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_int() {
        if (this.isNaN() || this.infinitySign != 0) {
            return this.getRuntime().getNil();
        }
        try {
            return RubyNumeric.int2fix(this.getRuntime(), this.value.longValueExact());
        }
        catch (ArithmeticException ae) {
            return RubyBignum.bignorm(this.getRuntime(), this.value.toBigInteger());
        }
    }
    
    @JRubyMethod(name = { "to_i", "to_int" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_int19() {
        this.checkFloatDomain();
        return this.to_int();
    }
    
    private String removeTrailingZeroes(String in) {
        while (in.length() > 0 && in.charAt(in.length() - 1) == '0') {
            in = in.substring(0, in.length() - 1);
        }
        return in;
    }
    
    public static boolean formatHasLeadingPlus(final String format) {
        return format.startsWith("+");
    }
    
    public static boolean formatHasLeadingSpace(final String format) {
        return format.startsWith(" ");
    }
    
    public static boolean formatHasFloatingPointNotation(final String format) {
        return format.endsWith("F");
    }
    
    public static int formatFractionalDigitGroups(final String format) {
        int groups = 0;
        final Pattern p = Pattern.compile("(\\+| )?(\\d+)(E|F)?");
        final Matcher m = p.matcher(format);
        if (m.matches()) {
            groups = Integer.parseInt(m.group(2));
        }
        return groups;
    }
    
    private boolean hasArg(final IRubyObject[] args) {
        return args.length != 0 && !args[0].isNil();
    }
    
    private String format(final IRubyObject[] args) {
        return args[0].toString();
    }
    
    private String firstArgument(final IRubyObject[] args) {
        if (this.hasArg(args)) {
            return this.format(args);
        }
        return null;
    }
    
    private boolean posSpace(final String arg) {
        return null != arg && formatHasLeadingSpace(arg);
    }
    
    private boolean posSign(final String arg) {
        return null != arg && (formatHasLeadingPlus(arg) || this.posSpace(arg));
    }
    
    private boolean asEngineering(final String arg) {
        return null == arg || !formatHasFloatingPointNotation(arg);
    }
    
    private int groups(final String arg) {
        if (null != arg) {
            return formatFractionalDigitGroups(arg);
        }
        return 0;
    }
    
    private boolean isZero() {
        return !this.isNaN() && !this.isInfinity() && this.value.signum() == 0;
    }
    
    private boolean isNaN() {
        return this.isNaN;
    }
    
    private boolean isInfinity() {
        return this.infinitySign != 0;
    }
    
    private String unscaledValue() {
        return this.value.abs().unscaledValue().toString();
    }
    
    private IRubyObject engineeringValue(final String arg) {
        final int exponent = this.getExponent();
        final int signum = this.value.signum();
        final StringBuilder build = new StringBuilder();
        build.append((signum == -1) ? "-" : ((signum == 1) ? (this.posSign(arg) ? (this.posSpace(arg) ? " " : "+") : "") : ""));
        build.append("0.");
        if (0 == this.groups(arg)) {
            final String s = this.removeTrailingZeroes(this.unscaledValue());
            if ("".equals(s)) {
                build.append("0");
            }
            else {
                build.append(s);
            }
        }
        else {
            int index = 0;
            String sep = "";
            while (index < this.unscaledValue().length()) {
                int next = index + this.groups(arg);
                if (next > this.unscaledValue().length()) {
                    next = this.unscaledValue().length();
                }
                build.append(sep).append(this.unscaledValue().substring(index, next));
                sep = " ";
                index += this.groups(arg);
            }
        }
        build.append("E").append(exponent);
        return this.getRuntime().newString(build.toString());
    }
    
    private IRubyObject floatingPointValue(final String arg) {
        final String[] values = this.value.abs().stripTrailingZeros().toPlainString().split("\\.");
        String whole = "0";
        if (values.length > 0) {
            whole = values[0];
        }
        String after = "0";
        if (values.length > 1) {
            after = values[1];
        }
        final int signum = this.value.signum();
        final StringBuilder build = new StringBuilder();
        build.append((signum == -1) ? "-" : ((signum == 1) ? (this.posSign(arg) ? (this.posSpace(arg) ? " " : "+") : "") : ""));
        if (this.groups(arg) == 0) {
            build.append(whole);
            if (null != after) {
                build.append(".").append(after);
            }
        }
        else {
            int index = 0;
            String sep = "";
            while (index < whole.length()) {
                int next = index + this.groups(arg);
                if (next > whole.length()) {
                    next = whole.length();
                }
                build.append(sep).append(whole.substring(index, next));
                sep = " ";
                index += this.groups(arg);
            }
            if (null != after) {
                build.append(".");
                index = 0;
                sep = "";
                while (index < after.length()) {
                    int next = index + this.groups(arg);
                    if (next > after.length()) {
                        next = after.length();
                    }
                    build.append(sep).append(after.substring(index, next));
                    sep = " ";
                    index += this.groups(arg);
                }
            }
        }
        return this.getRuntime().newString(build.toString());
    }
    
    @JRubyMethod(name = { "to_s" }, optional = 1)
    public IRubyObject to_s(final IRubyObject[] args) {
        final String arg = this.firstArgument(args);
        if (this.isNaN()) {
            return this.getRuntime().newString("NaN");
        }
        if (this.infinitySign != 0) {
            if (this.infinitySign == -1) {
                return this.getRuntime().newString("-Infinity");
            }
            return this.getRuntime().newString("Infinity");
        }
        else {
            if (this.isZero()) {
                String zero = "0.0";
                if (this.zeroSign < 0) {
                    zero = "-" + zero;
                }
                return this.getRuntime().newString(zero);
            }
            if (this.asEngineering(arg)) {
                return this.engineeringValue(arg);
            }
            return this.floatingPointValue(arg);
        }
    }
    
    @JRubyMethod
    public IRubyObject fix() {
        return this.truncate(RubyFixnum.zero(this.getRuntime()));
    }
    
    @JRubyMethod
    public IRubyObject truncate() {
        return this.truncate(RubyFixnum.zero(this.getRuntime()));
    }
    
    @JRubyMethod
    public IRubyObject truncate(final IRubyObject arg) {
        if (this.isNaN) {
            return newNaN(this.getRuntime());
        }
        if (this.isInfinity()) {
            return newInfinity(this.getRuntime(), this.infinitySign);
        }
        final int n = RubyNumeric.fix2int(arg);
        final int precision = this.value.precision() - this.value.scale() + n;
        if (precision > 0) {
            return new RubyBigDecimal(this.getRuntime(), this.value.round(new MathContext(precision, RoundingMode.DOWN)));
        }
        return new RubyBigDecimal(this.getRuntime(), BigDecimal.ZERO);
    }
    
    @JRubyMethod(name = { "zero?" })
    public IRubyObject zero_p() {
        return this.getRuntime().newBoolean(this.isZero());
    }
    
    public static BigDecimal bigSqrt(final BigDecimal squarD, final MathContext rootMC) {
        final int sign = squarD.signum();
        if (sign == -1) {
            throw new ArithmeticException("Square root of a negative number: " + squarD);
        }
        if (sign == 0) {
            return squarD.round(rootMC);
        }
        final int prec = rootMC.getPrecision();
        if (prec == 0) {
            throw new IllegalArgumentException("Most roots won't have infinite precision = 0");
        }
        final int BITS = 62;
        final int nInit = 16;
        MathContext nMC = new MathContext(18, RoundingMode.HALF_DOWN);
        BigDecimal x = null;
        BigDecimal e = null;
        BigDecimal v = null;
        BigDecimal g = null;
        BigInteger bi = squarD.unscaledValue();
        final int biLen = bi.bitLength();
        final int shift = Math.max(0, biLen - BITS + ((biLen % 2 != 0) ? 1 : 0));
        bi = bi.shiftRight(shift);
        double root = Math.sqrt(bi.doubleValue());
        final BigDecimal halfBack = new BigDecimal(BigInteger.ONE.shiftLeft(shift / 2));
        int scale = squarD.scale();
        if (scale % 2 == 1) {
            root *= 3.1622776601683795;
        }
        scale = (int)Math.ceil(scale / 2.0);
        x = new BigDecimal(root, nMC);
        x = x.multiply(halfBack, nMC);
        if (scale != 0) {
            x = x.movePointLeft(scale);
        }
        if (prec < nInit) {
            return x.round(rootMC);
        }
        v = BigDecimal.ONE.divide(RubyBigDecimal.TWO.multiply(x), nMC);
        final List<Integer> nPrecs = new ArrayList<Integer>();
        assert nInit > 3 : "Never ending loop!";
        for (int m = prec + 1; m > nInit; m = m / 2 + ((m > 100) ? 1 : 2)) {
            nPrecs.add(m);
        }
        for (int i = nPrecs.size() - 1; i > -1; --i) {
            nMC = new MathContext(nPrecs.get(i), (i % 2 == 1) ? RoundingMode.HALF_UP : RoundingMode.HALF_DOWN);
            e = squarD.subtract(x.multiply(x, nMC), nMC);
            if (i == 0) {
                x = x.add(e.multiply(v, rootMC), rootMC);
                break;
            }
            x = x.add(e.multiply(v, nMC));
            g = BigDecimal.ONE.subtract(RubyBigDecimal.TWO.multiply(x).multiply(v, nMC));
            v = v.add(g.multiply(v, nMC));
        }
        return x;
    }
    
    private void checkFloatDomain() {
        if (this.isNaN) {
            throw this.getRuntime().newFloatDomainError("NaN");
        }
        if (this.infinitySign == 0) {
            return;
        }
        if (this.infinitySign == -1) {
            throw this.getRuntime().newFloatDomainError("-Infinity");
        }
        throw this.getRuntime().newFloatDomainError("Infinity");
    }
    
    static {
        BIGDECIMAL_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyBigDecimal(runtime, klass);
            }
        };
        TWO = new BigDecimal(2);
        INFINITY_PATTERN = Pattern.compile("^([+-])?Infinity$");
        NUMBER_PATTERN = Pattern.compile("^([+-]?\\d*\\.?\\d*([eE][+-]?)?\\d*).*");
    }
    
    public static class BigDecimalKernelMethods
    {
        @JRubyMethod(name = { "BigDecimal" }, rest = true, module = true, visibility = Visibility.PRIVATE)
        public static IRubyObject newBigDecimal(final IRubyObject recv, final IRubyObject[] args) {
            return RubyBigDecimal.newBigDecimal(recv, args, Block.NULL_BLOCK);
        }
    }
}
