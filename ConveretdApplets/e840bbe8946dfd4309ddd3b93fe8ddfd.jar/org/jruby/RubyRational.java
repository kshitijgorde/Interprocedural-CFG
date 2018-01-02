// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.ByteList;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.util.Numeric;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Rational" }, parent = "Numeric", include = { "Precision" })
public class RubyRational extends RubyNumeric
{
    private static ObjectAllocator RATIONAL_ALLOCATOR;
    private IRubyObject num;
    private IRubyObject den;
    private static boolean canonicalization;
    private static long ML;
    
    public static RubyClass createRationalClass(final Ruby runtime) {
        final RubyClass rationalc = runtime.defineClass("Rational", runtime.getNumeric(), RubyRational.RATIONAL_ALLOCATOR);
        runtime.setRational(rationalc);
        rationalc.index = 21;
        rationalc.setReifiedClass(RubyRational.class);
        rationalc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyRational;
            }
        };
        rationalc.defineAnnotatedMethods(RubyRational.class);
        rationalc.getSingletonClass().undefineMethod("allocate");
        rationalc.getSingletonClass().undefineMethod("new");
        return rationalc;
    }
    
    private RubyRational(final Ruby runtime, final IRubyObject clazz, final IRubyObject num, final IRubyObject den) {
        super(runtime, (RubyClass)clazz);
        this.num = num;
        this.den = den;
    }
    
    static RubyRational newRationalRaw(final Ruby runtime, final IRubyObject x, final IRubyObject y) {
        return new RubyRational(runtime, runtime.getRational(), x, y);
    }
    
    static RubyRational newRationalRaw(final Ruby runtime, final IRubyObject x) {
        return new RubyRational(runtime, runtime.getRational(), x, RubyFixnum.one(runtime));
    }
    
    static IRubyObject newRationalCanonicalize(final ThreadContext context, final IRubyObject x) {
        return newRationalCanonicalize(context, x, RubyFixnum.one(context.getRuntime()));
    }
    
    private static IRubyObject newRationalCanonicalize(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return canonicalizeInternal(context, context.getRuntime().getRational(), x, y);
    }
    
    private static IRubyObject newRational(final ThreadContext context, final IRubyObject clazz, final IRubyObject x, final IRubyObject y) {
        assert !(x instanceof RubyRational) && !(y instanceof RubyRational);
        return canonicalizeInternal(context, clazz, x, y);
    }
    
    private static IRubyObject newRationalNoReduce(final ThreadContext context, final IRubyObject clazz, final IRubyObject x, final IRubyObject y) {
        assert !(x instanceof RubyRational) && !(y instanceof RubyRational);
        return canonicalizeInternalNoReduce(context, clazz, x, y);
    }
    
    private static RubyRational newRationalBang(final ThreadContext context, final IRubyObject clazz, final IRubyObject x, final IRubyObject y) {
        assert !Numeric.f_negative_p(context, y) && !Numeric.f_zero_p(context, y);
        return new RubyRational(context.getRuntime(), clazz, x, y);
    }
    
    private static RubyRational newRationalBang(final ThreadContext context, final IRubyObject clazz, final IRubyObject x) {
        return newRationalBang(context, clazz, x, RubyFixnum.one(context.getRuntime()));
    }
    
    @Deprecated
    public static IRubyObject newInstanceBang(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return newInstanceBang(context, recv, args[0]);
            }
            case 2: {
                return newInstanceBang(context, recv, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 1);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "new!" }, meta = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject newInstanceBang(final ThreadContext context, final IRubyObject recv, final IRubyObject num) {
        return newInstanceBang(context, recv, num, RubyFixnum.one(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "new!" }, meta = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static IRubyObject newInstanceBang(final ThreadContext context, final IRubyObject recv, IRubyObject num, IRubyObject den) {
        if (!(num instanceof RubyInteger)) {
            num = Numeric.f_to_i(context, num);
        }
        if (!(den instanceof RubyInteger)) {
            den = Numeric.f_to_i(context, den);
        }
        final Ruby runtime = context.getRuntime();
        final IRubyObject res = Numeric.f_cmp(context, den, RubyFixnum.zero(runtime));
        if (res == RubyFixnum.minus_one(runtime)) {
            num = Numeric.f_negate(context, num);
            den = Numeric.f_negate(context, den);
        }
        else if (res == RubyFixnum.zero(runtime)) {
            throw runtime.newZeroDivisionError();
        }
        return new RubyRational(runtime, recv, num, den);
    }
    
    public static void setCanonicalization(final boolean canonical) {
        RubyRational.canonicalization = canonical;
    }
    
    static void intCheck(final ThreadContext context, final IRubyObject num) {
        if (num instanceof RubyFixnum || num instanceof RubyBignum) {
            return;
        }
        if (num instanceof RubyNumeric && num.callMethod(context, "integer?").isTrue()) {
            return;
        }
        final Ruby runtime = num.getRuntime();
        if (runtime.is1_9()) {
            throw runtime.newTypeError("can't convert " + num.getMetaClass().getName() + " into Rational");
        }
        throw runtime.newArgumentError("not an integer");
    }
    
    static IRubyObject intValue(final ThreadContext context, IRubyObject num) {
        intCheck(context, num);
        if (!(num instanceof RubyInteger)) {
            num = num.callMethod(context, "to_f");
        }
        return num;
    }
    
    private static IRubyObject canonicalizeInternal(final ThreadContext context, final IRubyObject clazz, IRubyObject num, IRubyObject den) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject res = Numeric.f_cmp(context, den, RubyFixnum.zero(runtime));
        if (res == RubyFixnum.minus_one(runtime)) {
            num = Numeric.f_negate(context, num);
            den = Numeric.f_negate(context, den);
        }
        else if (res == RubyFixnum.zero(runtime)) {
            throw runtime.newZeroDivisionError();
        }
        final IRubyObject gcd = Numeric.f_gcd(context, num, den);
        num = Numeric.f_idiv(context, num, gcd);
        den = Numeric.f_idiv(context, den, gcd);
        if (Numeric.f_one_p(context, den) && RubyRational.canonicalization) {
            return num;
        }
        return new RubyRational(context.getRuntime(), clazz, num, den);
    }
    
    private static IRubyObject canonicalizeInternalNoReduce(final ThreadContext context, final IRubyObject clazz, IRubyObject num, IRubyObject den) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject res = Numeric.f_cmp(context, den, RubyFixnum.zero(runtime));
        if (res == RubyFixnum.minus_one(runtime)) {
            num = Numeric.f_negate(context, num);
            den = Numeric.f_negate(context, den);
        }
        else if (res == RubyFixnum.zero(runtime)) {
            throw runtime.newZeroDivisionError();
        }
        if (Numeric.f_one_p(context, den) && RubyRational.canonicalization) {
            return num;
        }
        return new RubyRational(context.getRuntime(), clazz, num, den);
    }
    
    @Deprecated
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject clazz, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return newInstance(context, clazz, args[0]);
            }
            case 2: {
                return newInstance(context, clazz, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 1);
                return null;
            }
        }
    }
    
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject clazz, IRubyObject num) {
        num = intValue(context, num);
        return canonicalizeInternal(context, clazz, num, RubyFixnum.one(context.getRuntime()));
    }
    
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject clazz, IRubyObject num, IRubyObject den) {
        num = intValue(context, num);
        den = intValue(context, den);
        return canonicalizeInternal(context, clazz, num, den);
    }
    
    public static IRubyObject newRationalConvert(final ThreadContext context, final IRubyObject x) {
        return newRationalConvert(context, x, RubyFixnum.one(context.getRuntime()));
    }
    
    public static IRubyObject newRationalConvert(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return convert(context, context.getRuntime().getRational(), x, y);
    }
    
    @Deprecated
    public static IRubyObject convert(final ThreadContext context, final IRubyObject clazz, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return convert(context, clazz, args[0]);
            }
            case 2: {
                return convert(context, clazz, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 1);
                return null;
            }
        }
    }
    
    @JRubyMethod(name = { "convert" }, meta = true, visibility = Visibility.PRIVATE)
    public static IRubyObject convert(final ThreadContext context, final IRubyObject recv, final IRubyObject a1) {
        if (a1.isNil()) {
            throw context.getRuntime().newTypeError("can't convert nil into Rational");
        }
        return convertCommon(context, recv, a1, context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "convert" }, meta = true, visibility = Visibility.PRIVATE)
    public static IRubyObject convert(final ThreadContext context, final IRubyObject recv, final IRubyObject a1, final IRubyObject a2) {
        if (a1.isNil() || a2.isNil()) {
            throw context.getRuntime().newTypeError("can't convert nil into Rational");
        }
        return convertCommon(context, recv, a1, a2);
    }
    
    private static IRubyObject convertCommon(final ThreadContext context, final IRubyObject recv, IRubyObject a1, IRubyObject a2) {
        if (a1 instanceof RubyComplex) {
            final RubyComplex a1Complex = (RubyComplex)a1;
            if (Numeric.k_exact_p(a1Complex.getImage()) && Numeric.f_zero_p(context, a1Complex.getImage())) {
                a1 = a1Complex.getReal();
            }
        }
        if (a2 instanceof RubyComplex) {
            final RubyComplex a2Complex = (RubyComplex)a2;
            if (Numeric.k_exact_p(a2Complex.getImage()) && Numeric.f_zero_p(context, a2Complex.getImage())) {
                a2 = a2Complex.getReal();
            }
        }
        final DynamicScope scope = context.getCurrentScope();
        final IRubyObject backref = scope.getBackRef(context.getRuntime());
        if (backref instanceof RubyMatchData) {
            ((RubyMatchData)backref).use();
        }
        if (a1 instanceof RubyFloat) {
            a1 = Numeric.f_to_r(context, a1);
        }
        else if (a1 instanceof RubyString) {
            a1 = str_to_r_strict(context, a1);
        }
        else if (a1.respondsTo("to_r")) {
            a1 = Numeric.f_to_r(context, a1);
        }
        if (a2 instanceof RubyFloat) {
            a2 = Numeric.f_to_r(context, a2);
        }
        else if (a2 instanceof RubyString) {
            a2 = str_to_r_strict(context, a2);
        }
        scope.setBackRef(backref);
        if (a1 instanceof RubyRational && (a2.isNil() || (Numeric.k_exact_p(a2) && Numeric.f_one_p(context, a2)))) {
            return a1;
        }
        if (a2.isNil()) {
            if (a1 instanceof RubyNumeric && !Numeric.f_integer_p(context, a1).isTrue()) {
                return a1;
            }
            return newInstance(context, recv, a1);
        }
        else {
            if (a1 instanceof RubyNumeric && a2 instanceof RubyNumeric && (!Numeric.f_integer_p(context, a1).isTrue() || !Numeric.f_integer_p(context, a2).isTrue())) {
                return Numeric.f_div(context, a1, a2);
            }
            return newInstance(context, recv, a1, a2);
        }
    }
    
    @JRubyMethod(name = { "numerator" })
    public IRubyObject numerator(final ThreadContext context) {
        return this.num;
    }
    
    @JRubyMethod(name = { "denominator" })
    public IRubyObject denominator(final ThreadContext context) {
        return this.den;
    }
    
    private static IRubyObject f_imul(final ThreadContext context, final long a, final long b) {
        final Ruby runtime = context.getRuntime();
        if (a == 0L || b == 0L) {
            return RubyFixnum.zero(runtime);
        }
        if (a == 1L) {
            return RubyFixnum.newFixnum(runtime, b);
        }
        if (b == 1L) {
            return RubyFixnum.newFixnum(runtime, a);
        }
        final long c = a * b;
        if (c / a != b) {
            return RubyBignum.newBignum(runtime, a).op_mul(context, RubyBignum.newBignum(runtime, b));
        }
        return RubyFixnum.newFixnum(runtime, c);
    }
    
    private IRubyObject f_addsub(final ThreadContext context, final IRubyObject anum, final IRubyObject aden, final IRubyObject bnum, final IRubyObject bden, final boolean plus) {
        final Ruby runtime = context.getRuntime();
        IRubyObject g;
        IRubyObject a;
        IRubyObject b;
        if (anum instanceof RubyFixnum && aden instanceof RubyFixnum && bnum instanceof RubyFixnum && bden instanceof RubyFixnum) {
            final long an = ((RubyFixnum)anum).getLongValue();
            final long ad = ((RubyFixnum)aden).getLongValue();
            final long bn = ((RubyFixnum)bnum).getLongValue();
            final long bd = ((RubyFixnum)bden).getLongValue();
            final long ig = Numeric.i_gcd(ad, bd);
            g = RubyFixnum.newFixnum(runtime, ig);
            a = f_imul(context, an, bd / ig);
            b = f_imul(context, bn, ad / ig);
        }
        else {
            g = Numeric.f_gcd(context, aden, bden);
            a = Numeric.f_mul(context, anum, Numeric.f_idiv(context, bden, g));
            b = Numeric.f_mul(context, bnum, Numeric.f_idiv(context, aden, g));
        }
        final IRubyObject c = plus ? Numeric.f_add(context, a, b) : Numeric.f_sub(context, a, b);
        b = Numeric.f_idiv(context, aden, g);
        g = Numeric.f_gcd(context, c, g);
        final IRubyObject newNum = Numeric.f_idiv(context, c, g);
        a = Numeric.f_idiv(context, bden, g);
        final IRubyObject newDen = Numeric.f_mul(context, a, b);
        return newRationalNoReduce(context, this.getMetaClass(), newNum, newDen);
    }
    
    @JRubyMethod(name = { "+" })
    public IRubyObject op_add(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            return this.f_addsub(context, this.num, this.den, other, RubyFixnum.one(context.getRuntime()), true);
        }
        if (other instanceof RubyFloat) {
            return Numeric.f_add(context, Numeric.f_to_f(context, this), other);
        }
        if (other instanceof RubyRational) {
            final RubyRational otherRational = (RubyRational)other;
            return this.f_addsub(context, this.num, this.den, otherRational.num, otherRational.den, true);
        }
        return this.coerceBin(context, "+", other);
    }
    
    @JRubyMethod(name = { "-" })
    public IRubyObject op_sub(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            return this.f_addsub(context, this.num, this.den, other, RubyFixnum.one(context.getRuntime()), false);
        }
        if (other instanceof RubyFloat) {
            return Numeric.f_sub(context, Numeric.f_to_f(context, this), other);
        }
        if (other instanceof RubyRational) {
            final RubyRational otherRational = (RubyRational)other;
            return this.f_addsub(context, this.num, this.den, otherRational.num, otherRational.den, false);
        }
        return this.coerceBin(context, "-", other);
    }
    
    private IRubyObject f_muldiv(final ThreadContext context, IRubyObject anum, final IRubyObject aden, IRubyObject bnum, IRubyObject bden, final boolean mult) {
        if (!mult) {
            if (Numeric.f_negative_p(context, bnum)) {
                anum = Numeric.f_negate(context, anum);
                bnum = Numeric.f_negate(context, bnum);
            }
            final IRubyObject tmp = bnum;
            bnum = bden;
            bden = tmp;
        }
        IRubyObject newNum;
        IRubyObject newDen;
        if (anum instanceof RubyFixnum && aden instanceof RubyFixnum && bnum instanceof RubyFixnum && bden instanceof RubyFixnum) {
            final long an = ((RubyFixnum)anum).getLongValue();
            final long ad = ((RubyFixnum)aden).getLongValue();
            final long bn = ((RubyFixnum)bnum).getLongValue();
            final long bd = ((RubyFixnum)bden).getLongValue();
            final long g1 = Numeric.i_gcd(an, bd);
            final long g2 = Numeric.i_gcd(ad, bn);
            newNum = f_imul(context, an / g1, bn / g2);
            newDen = f_imul(context, ad / g2, bd / g1);
        }
        else {
            final IRubyObject g3 = Numeric.f_gcd(context, anum, bden);
            final IRubyObject g4 = Numeric.f_gcd(context, aden, bnum);
            newNum = Numeric.f_mul(context, Numeric.f_idiv(context, anum, g3), Numeric.f_idiv(context, bnum, g4));
            newDen = Numeric.f_mul(context, Numeric.f_idiv(context, aden, g4), Numeric.f_idiv(context, bden, g3));
        }
        return newRationalNoReduce(context, this.getMetaClass(), newNum, newDen);
    }
    
    @JRubyMethod(name = { "*" })
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            return this.f_muldiv(context, this.num, this.den, other, RubyFixnum.one(context.getRuntime()), true);
        }
        if (other instanceof RubyFloat) {
            return Numeric.f_mul(context, Numeric.f_to_f(context, this), other);
        }
        if (other instanceof RubyRational) {
            final RubyRational otherRational = (RubyRational)other;
            return this.f_muldiv(context, this.num, this.den, otherRational.num, otherRational.den, true);
        }
        return this.coerceBin(context, "*", other);
    }
    
    @JRubyMethod(name = { "/", "quo" }, backtrace = true)
    public IRubyObject op_div(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            if (Numeric.f_zero_p(context, other)) {
                throw context.getRuntime().newZeroDivisionError();
            }
            return this.f_muldiv(context, this.num, this.den, other, RubyFixnum.one(context.getRuntime()), false);
        }
        else {
            if (other instanceof RubyFloat) {
                return Numeric.f_to_f(context, this).callMethod(context, "/", other);
            }
            if (!(other instanceof RubyRational)) {
                return this.coerceBin(context, "/", other);
            }
            if (Numeric.f_zero_p(context, other)) {
                throw context.getRuntime().newZeroDivisionError();
            }
            final RubyRational otherRational = (RubyRational)other;
            return this.f_muldiv(context, this.num, this.den, otherRational.num, otherRational.den, false);
        }
    }
    
    @JRubyMethod(name = { "fdiv" }, backtrace = true)
    public IRubyObject op_fdiv(final ThreadContext context, final IRubyObject other) {
        return Numeric.f_div(context, Numeric.f_to_f(context, this), other);
    }
    
    @JRubyMethod(name = { "**" })
    public IRubyObject op_expt(final ThreadContext context, IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (Numeric.k_exact_p(other) && Numeric.f_zero_p(context, other)) {
            return newRationalBang(context, this.getMetaClass(), RubyFixnum.one(runtime));
        }
        if (other instanceof RubyRational) {
            final RubyRational otherRational = (RubyRational)other;
            if (Numeric.f_one_p(context, otherRational.den)) {
                other = otherRational.num;
            }
        }
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            final IRubyObject res = Numeric.f_cmp(context, other, RubyFixnum.zero(runtime));
            IRubyObject tnum;
            IRubyObject tden;
            if (res == RubyFixnum.one(runtime)) {
                tnum = Numeric.f_expt(context, this.num, other);
                tden = Numeric.f_expt(context, this.den, other);
            }
            else if (res == RubyFixnum.minus_one(runtime)) {
                tnum = Numeric.f_expt(context, this.den, Numeric.f_negate(context, other));
                tden = Numeric.f_expt(context, this.num, Numeric.f_negate(context, other));
            }
            else {
                tden = (tnum = RubyFixnum.one(runtime));
            }
            return newRational(context, this.getMetaClass(), tnum, tden);
        }
        if (other instanceof RubyFloat || other instanceof RubyRational) {
            return Numeric.f_expt(context, Numeric.f_to_f(context, this), other);
        }
        return this.coerceBin(context, "**", other);
    }
    
    @JRubyMethod(name = { "<=>" })
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            if (this.den instanceof RubyFixnum && ((RubyFixnum)this.den).getLongValue() == 1L) {
                return Numeric.f_cmp(context, this.num, other);
            }
            return Numeric.f_cmp(context, this, newRationalBang(context, this.getMetaClass(), other));
        }
        else {
            if (other instanceof RubyFloat) {
                return Numeric.f_cmp(context, Numeric.f_to_f(context, this), other);
            }
            if (other instanceof RubyRational) {
                final RubyRational otherRational = (RubyRational)other;
                IRubyObject num1;
                IRubyObject num2;
                if (this.num instanceof RubyFixnum && this.den instanceof RubyFixnum && otherRational.num instanceof RubyFixnum && otherRational.den instanceof RubyFixnum) {
                    num1 = f_imul(context, ((RubyFixnum)this.num).getLongValue(), ((RubyFixnum)otherRational.den).getLongValue());
                    num2 = f_imul(context, ((RubyFixnum)otherRational.num).getLongValue(), ((RubyFixnum)this.den).getLongValue());
                }
                else {
                    num1 = Numeric.f_mul(context, this.num, otherRational.den);
                    num2 = Numeric.f_mul(context, otherRational.num, this.den);
                }
                return Numeric.f_cmp(context, Numeric.f_sub(context, num1, num2), RubyFixnum.zero(context.getRuntime()));
            }
            return this.coerceBin(context, "<=>", other);
        }
    }
    
    @JRubyMethod(name = { "==" })
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            if (Numeric.f_zero_p(context, this.num) && Numeric.f_zero_p(context, other)) {
                return runtime.getTrue();
            }
            if (!(this.den instanceof RubyFixnum) || ((RubyFixnum)this.den).getLongValue() != 1L) {
                return runtime.getFalse();
            }
            return Numeric.f_equal(context, this.num, other);
        }
        else {
            if (other instanceof RubyFloat) {
                return Numeric.f_equal(context, Numeric.f_to_f(context, this), other);
            }
            if (!(other instanceof RubyRational)) {
                return Numeric.f_equal(context, other, this);
            }
            final RubyRational otherRational = (RubyRational)other;
            if (Numeric.f_zero_p(context, this.num) && Numeric.f_zero_p(context, otherRational.num)) {
                return runtime.getTrue();
            }
            return runtime.newBoolean(Numeric.f_equal(context, this.num, otherRational.num).isTrue() && Numeric.f_equal(context, this.den, otherRational.den).isTrue());
        }
    }
    
    @JRubyMethod(name = { "coerce" }, backtrace = true)
    public IRubyObject op_coerce(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubyFixnum || other instanceof RubyBignum) {
            return runtime.newArray(newRationalBang(context, this.getMetaClass(), other), this);
        }
        if (other instanceof RubyFloat) {
            return runtime.newArray(other, Numeric.f_to_f(context, this));
        }
        if (other instanceof RubyRational) {
            return runtime.newArray(other, this);
        }
        throw runtime.newTypeError(other.getMetaClass() + " can't be coerced into " + this.getMetaClass());
    }
    
    @JRubyMethod(name = { "div" }, backtrace = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_idiv(final ThreadContext context, final IRubyObject other) {
        return Numeric.f_floor(context, Numeric.f_div(context, this, other));
    }
    
    @JRubyMethod(name = { "div" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_idiv19(final ThreadContext context, final IRubyObject other) {
        if (RubyNumeric.num2dbl(other) == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.op_idiv(context, other);
    }
    
    @JRubyMethod(name = { "modulo", "%" }, backtrace = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_mod(final ThreadContext context, final IRubyObject other) {
        final IRubyObject val = Numeric.f_floor(context, Numeric.f_div(context, this, other));
        return Numeric.f_sub(context, this, Numeric.f_mul(context, other, val));
    }
    
    @JRubyMethod(name = { "modulo", "%" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_mod19(final ThreadContext context, final IRubyObject other) {
        if (RubyNumeric.num2dbl(other) == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.op_mod(context, other);
    }
    
    @JRubyMethod(name = { "divmod" }, backtrace = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_divmod(final ThreadContext context, final IRubyObject other) {
        final IRubyObject val = Numeric.f_floor(context, Numeric.f_div(context, this, other));
        return context.getRuntime().newArray(val, Numeric.f_sub(context, this, Numeric.f_mul(context, other, val)));
    }
    
    @JRubyMethod(name = { "divmod" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_divmod19(final ThreadContext context, final IRubyObject other) {
        if (RubyNumeric.num2dbl(other) == 0.0) {
            throw context.getRuntime().newZeroDivisionError();
        }
        return this.op_divmod(context, other);
    }
    
    @JRubyMethod(name = { "remainder" })
    public IRubyObject op_rem(final ThreadContext context, final IRubyObject other) {
        final IRubyObject val = Numeric.f_truncate(context, Numeric.f_div(context, this, other));
        return Numeric.f_sub(context, this, Numeric.f_mul(context, other, val));
    }
    
    @JRubyMethod(name = { "abs" })
    public IRubyObject op_abs(final ThreadContext context) {
        if (!Numeric.f_negative_p(context, this)) {
            return this;
        }
        return Numeric.f_negate(context, this);
    }
    
    private IRubyObject op_roundCommonPre(final ThreadContext context, final IRubyObject n) {
        Numeric.checkInteger(context, n);
        final Ruby runtime = context.getRuntime();
        return Numeric.f_expt(context, RubyFixnum.newFixnum(runtime, 10L), n);
    }
    
    private IRubyObject op_roundCommonPost(final ThreadContext context, IRubyObject s, final IRubyObject n, final IRubyObject b) {
        s = Numeric.f_div(context, newRationalBang(context, this.getMetaClass(), s), b);
        if (Numeric.f_lt_p(context, n, RubyFixnum.one(context.getRuntime())).isTrue()) {
            s = Numeric.f_to_i(context, s);
        }
        return s;
    }
    
    @JRubyMethod(name = { "floor" })
    public IRubyObject op_floor(final ThreadContext context) {
        return Numeric.f_idiv(context, this.num, this.den);
    }
    
    @JRubyMethod(name = { "floor" })
    public IRubyObject op_floor(final ThreadContext context, final IRubyObject n) {
        final IRubyObject b = this.op_roundCommonPre(context, n);
        return this.op_roundCommonPost(context, ((RubyRational)Numeric.f_mul(context, this, b)).op_floor(context), n, b);
    }
    
    @JRubyMethod(name = { "ceil" })
    public IRubyObject op_ceil(final ThreadContext context) {
        return Numeric.f_negate(context, Numeric.f_idiv(context, Numeric.f_negate(context, this.num), this.den));
    }
    
    @JRubyMethod(name = { "ceil" })
    public IRubyObject op_ceil(final ThreadContext context, final IRubyObject n) {
        final IRubyObject b = this.op_roundCommonPre(context, n);
        return this.op_roundCommonPost(context, ((RubyRational)Numeric.f_mul(context, this, b)).op_ceil(context), n, b);
    }
    
    @JRubyMethod(name = { "truncate", "to_i" })
    public IRubyObject op_truncate(final ThreadContext context) {
        if (Numeric.f_negative_p(context, this.num)) {
            return Numeric.f_negate(context, Numeric.f_idiv(context, Numeric.f_negate(context, this.num), this.den));
        }
        return Numeric.f_idiv(context, this.num, this.den);
    }
    
    @JRubyMethod(name = { "truncate" })
    public IRubyObject op_truncate(final ThreadContext context, final IRubyObject n) {
        final IRubyObject b = this.op_roundCommonPre(context, n);
        return this.op_roundCommonPost(context, ((RubyRational)Numeric.f_mul(context, this, b)).op_truncate(context), n, b);
    }
    
    @JRubyMethod(name = { "round" })
    public IRubyObject op_round(final ThreadContext context) {
        IRubyObject myNum = this.num;
        final boolean neg = Numeric.f_negative_p(context, myNum);
        if (neg) {
            myNum = Numeric.f_negate(context, myNum);
        }
        IRubyObject myDen = this.den;
        final IRubyObject two = RubyFixnum.two(context.getRuntime());
        myNum = Numeric.f_add(context, Numeric.f_mul(context, myNum, two), myDen);
        myDen = Numeric.f_mul(context, myDen, two);
        myNum = Numeric.f_idiv(context, myNum, myDen);
        if (neg) {
            myNum = Numeric.f_negate(context, myNum);
        }
        return myNum;
    }
    
    @JRubyMethod(name = { "round" })
    public IRubyObject op_round(final ThreadContext context, final IRubyObject n) {
        final IRubyObject b = this.op_roundCommonPre(context, n);
        return this.op_roundCommonPost(context, ((RubyRational)Numeric.f_mul(context, this, b)).op_round(context), n, b);
    }
    
    @JRubyMethod(name = { "to_f" })
    public IRubyObject to_f(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (Numeric.f_zero_p(context, this.num)) {
            return runtime.newFloat(0.0);
        }
        IRubyObject myNum = this.num;
        IRubyObject myDen = this.den;
        boolean minus = false;
        if (Numeric.f_negative_p(context, myNum)) {
            myNum = Numeric.f_negate(context, myNum);
            minus = true;
        }
        final long nl = Numeric.i_ilog2(context, myNum);
        final long dl = Numeric.i_ilog2(context, myDen);
        long ne = 0L;
        if (nl > RubyRational.ML) {
            ne = nl - RubyRational.ML;
            myNum = Numeric.f_rshift(context, myNum, RubyFixnum.newFixnum(runtime, ne));
        }
        long de = 0L;
        if (dl > RubyRational.ML) {
            de = dl - RubyRational.ML;
            myDen = Numeric.f_rshift(context, myDen, RubyFixnum.newFixnum(runtime, de));
        }
        final long e = ne - de;
        if (e > 1023L || e < -1022L) {
            runtime.getWarnings().warn(IRubyWarnings.ID.FLOAT_OUT_OF_RANGE, "out of Float range", this.getMetaClass());
            return runtime.newFloat((e > 0L) ? Double.MAX_VALUE : 0.0);
        }
        double f = RubyNumeric.num2dbl(myNum) / RubyNumeric.num2dbl(myDen);
        if (minus) {
            f = -f;
        }
        f = Numeric.ldexp(f, e);
        if (Double.isInfinite(f) || Double.isNaN(f)) {
            runtime.getWarnings().warn(IRubyWarnings.ID.FLOAT_OUT_OF_RANGE, "out of Float range", this.getMetaClass());
        }
        return runtime.newFloat(f);
    }
    
    @JRubyMethod(name = { "to_r" })
    public IRubyObject to_r(final ThreadContext context) {
        return this;
    }
    
    @JRubyMethod(name = { "rationalize" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject rationalize(final ThreadContext context, final IRubyObject[] args) {
        if (args.length == 0) {
            return this.to_r(context);
        }
        if (Numeric.f_negative_p(context, this)) {
            return Numeric.f_negate(context, ((RubyRational)Numeric.f_abs(context, this)).rationalize(context, args));
        }
        final IRubyObject eps = Numeric.f_abs(context, args[0]);
        final IRubyObject a = Numeric.f_sub(context, this, eps);
        final IRubyObject b = Numeric.f_add(context, this, eps);
        if (Numeric.f_equal(context, a, b).isTrue()) {
            return this;
        }
        final IRubyObject[] ary = { a, b };
        final IRubyObject[] ans = Numeric.nurat_rationalize_internal(context, ary);
        return newRational(context, this.metaClass, ans[0], ans[1]);
    }
    
    @JRubyMethod(name = { "hash" })
    public IRubyObject hash(final ThreadContext context) {
        return Numeric.f_xor(context, RuntimeHelpers.invokedynamic(context, this.num, 3), RuntimeHelpers.invokedynamic(context, this.den, 3));
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        final RubyString str = context.getRuntime().newString();
        str.append(Numeric.f_to_s(context, this.num));
        str.cat((byte)47);
        str.append(Numeric.f_to_s(context, this.den));
        return str;
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        final RubyString str = context.getRuntime().newString();
        str.cat((byte)40);
        str.append(Numeric.f_inspect(context, this.num));
        str.cat((byte)47);
        str.append(Numeric.f_inspect(context, this.den));
        str.cat((byte)41);
        return str;
    }
    
    @JRubyMethod(name = { "marshal_dump" })
    public IRubyObject marshal_dump(final ThreadContext context) {
        final RubyArray dump = context.getRuntime().newArray(this.num, this.den);
        if (this.hasVariables()) {
            dump.syncVariables(this);
        }
        return dump;
    }
    
    @JRubyMethod(name = { "marshal_load" }, backtrace = true)
    public IRubyObject marshal_load(final ThreadContext context, final IRubyObject arg) {
        final RubyArray load = arg.convertToArray();
        this.num = ((load.size() > 0) ? load.eltInternal(0) : context.getRuntime().getNil());
        this.den = ((load.size() > 1) ? load.eltInternal(1) : context.getRuntime().getNil());
        if (Numeric.f_zero_p(context, this.den)) {
            throw context.getRuntime().newZeroDivisionError();
        }
        if (load.hasVariables()) {
            this.syncVariables((IRubyObject)load);
        }
        return this;
    }
    
    static RubyArray str_to_r_internal(final ThreadContext context, final IRubyObject recv) {
        final RubyString s = recv.convertToString();
        ByteList bytes = s.getByteList();
        final Ruby runtime = context.getRuntime();
        if (bytes.getRealSize() == 0) {
            return runtime.newArray(runtime.getNil(), recv);
        }
        final IRubyObject m = RubyRegexp.newDummyRegexp(runtime, Numeric.RationalPatterns.rat_pat).callMethod(context, "match", s);
        if (!m.isNil()) {
            final IRubyObject si = m.callMethod(context, "[]", RubyFixnum.one(runtime));
            final IRubyObject nu = m.callMethod(context, "[]", RubyFixnum.two(runtime));
            final IRubyObject de = m.callMethod(context, "[]", RubyFixnum.three(runtime));
            final IRubyObject re = m.callMethod(context, "post_match");
            RubyArray a = nu.callMethod(context, "split", RubyRegexp.newDummyRegexp(runtime, Numeric.RationalPatterns.an_e_pat)).convertToArray();
            final IRubyObject ifp = a.eltInternal(0);
            final IRubyObject exp = (a.size() != 2) ? runtime.getNil() : a.eltInternal(1);
            a = ifp.callMethod(context, "split", RubyRegexp.newDummyRegexp(runtime, Numeric.RationalPatterns.a_dot_pat)).convertToArray();
            final IRubyObject ip = a.eltInternal(0);
            final IRubyObject fp = (a.size() != 2) ? runtime.getNil() : a.eltInternal(1);
            IRubyObject v = newRationalCanonicalize(context, Numeric.f_to_i(context, ip));
            if (!fp.isNil()) {
                bytes = fp.convertToString().getByteList();
                int count = 0;
                final byte[] buf = bytes.getUnsafeBytes();
                for (int i = bytes.getBegin(), end = i + bytes.getRealSize(); i < end; ++i) {
                    if (ASCIIEncoding.INSTANCE.isDigit(buf[i])) {
                        ++count;
                    }
                }
                final IRubyObject l = Numeric.f_expt(context, RubyFixnum.newFixnum(runtime, 10L), RubyFixnum.newFixnum(runtime, count));
                v = Numeric.f_mul(context, v, l);
                v = Numeric.f_add(context, v, Numeric.f_to_i(context, fp));
                v = Numeric.f_div(context, v, l);
            }
            if (!si.isNil()) {
                final ByteList siBytes = si.convertToString().getByteList();
                if (siBytes.length() > 0 && siBytes.get(0) == 45) {
                    v = Numeric.f_negate(context, v);
                }
            }
            if (!exp.isNil()) {
                v = Numeric.f_mul(context, v, Numeric.f_expt(context, RubyFixnum.newFixnum(runtime, 10L), Numeric.f_to_i(context, exp)));
            }
            if (!de.isNil()) {
                v = Numeric.f_div(context, v, Numeric.f_to_i(context, de));
            }
            return runtime.newArray(v, re);
        }
        return runtime.newArray(runtime.getNil(), recv);
    }
    
    private static IRubyObject str_to_r_strict(final ThreadContext context, final IRubyObject recv) {
        final RubyArray a = str_to_r_internal(context, recv);
        if (a.eltInternal(0).isNil() || a.eltInternal(1).convertToString().getByteList().length() > 0) {
            final IRubyObject s = recv.callMethod(context, "inspect");
            throw context.getRuntime().newArgumentError("invalid value for convert(): " + s.convertToString());
        }
        return a.eltInternal(0);
    }
    
    static {
        RubyRational.RATIONAL_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyFixnum zero = RubyFixnum.zero(runtime);
                return new RubyRational(runtime, klass, zero, zero, null);
            }
        };
        RubyRational.canonicalization = false;
        RubyRational.ML = (long)(Math.log(Double.MAX_VALUE) / Math.log(2.0) - 1.0);
    }
}
