// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.util.ByteList;
import org.jcodings.specific.ASCIIEncoding;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.util.Numeric;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Complex" }, parent = "Numeric")
public class RubyComplex extends RubyNumeric
{
    private static ObjectAllocator COMPLEX_ALLOCATOR;
    private IRubyObject real;
    private IRubyObject image;
    private static boolean canonicalization;
    private static final boolean CL_CANON = true;
    
    public static RubyClass createComplexClass(final Ruby runtime) {
        final RubyClass complexc = runtime.defineClass("Complex", runtime.getNumeric(), RubyComplex.COMPLEX_ALLOCATOR);
        runtime.setComplex(complexc);
        complexc.index = 20;
        complexc.setReifiedClass(RubyComplex.class);
        complexc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyComplex;
            }
        };
        complexc.defineAnnotatedMethods(RubyComplex.class);
        complexc.getSingletonClass().undefineMethod("allocate");
        complexc.getSingletonClass().undefineMethod("new");
        final String[] arr$;
        final String[] undefined = arr$ = new String[] { "<", "<=", "<=>", ">", ">=", "between?", "divmod", "floor", "ceil", "modulo", "round", "step", "truncate" };
        for (final String undef : arr$) {
            complexc.undefineMethod(undef);
        }
        complexc.defineConstant("I", newComplexConvert(runtime.getCurrentContext(), RubyFixnum.zero(runtime), RubyFixnum.one(runtime)));
        return complexc;
    }
    
    private RubyComplex(final Ruby runtime, final IRubyObject clazz, final IRubyObject real, final IRubyObject image) {
        super(runtime, (RubyClass)clazz);
        this.real = real;
        this.image = image;
    }
    
    static RubyComplex newComplexRaw(final Ruby runtime, final IRubyObject x, final RubyObject y) {
        return new RubyComplex(runtime, runtime.getComplex(), x, y);
    }
    
    static RubyComplex newComplexRaw(final Ruby runtime, final IRubyObject x) {
        return new RubyComplex(runtime, runtime.getComplex(), x, RubyFixnum.zero(runtime));
    }
    
    public static IRubyObject newComplexCanonicalize(final ThreadContext context, final IRubyObject x) {
        return newComplexCanonicalize(context, x, RubyFixnum.zero(context.getRuntime()));
    }
    
    public static IRubyObject newComplexCanonicalize(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return canonicalizeInternal(context, context.getRuntime().getComplex(), x, y);
    }
    
    static IRubyObject newComplexPolar(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return polar(context, context.getRuntime().getComplex(), x, y);
    }
    
    static IRubyObject newComplex(final ThreadContext context, final IRubyObject clazz, final IRubyObject x) {
        return newComplex(context, clazz, x, RubyFixnum.zero(context.getRuntime()));
    }
    
    static IRubyObject newComplex(final ThreadContext context, final IRubyObject clazz, final IRubyObject x, final IRubyObject y) {
        assert !(x instanceof RubyComplex);
        return canonicalizeInternal(context, clazz, x, y);
    }
    
    static RubyComplex newComplexBang(final ThreadContext context, final IRubyObject clazz, final IRubyObject x, final IRubyObject y) {
        return new RubyComplex(context.getRuntime(), clazz, x, y);
    }
    
    public static RubyComplex newComplexBang(final ThreadContext context, final IRubyObject clazz, final IRubyObject x) {
        return newComplexBang(context, clazz, x, RubyFixnum.zero(context.getRuntime()));
    }
    
    IRubyObject getImage() {
        return this.image;
    }
    
    IRubyObject getReal() {
        return this.real;
    }
    
    private static IRubyObject m_cos(final ThreadContext context, final IRubyObject x) {
        if (Numeric.f_real_p(context, x).isTrue()) {
            return RubyMath.cos(x, x);
        }
        final RubyComplex complex = (RubyComplex)x;
        return newComplex(context, context.getRuntime().getComplex(), Numeric.f_mul(context, RubyMath.cos(x, complex.real), RubyMath.cosh(x, complex.image)), Numeric.f_mul(context, Numeric.f_negate(context, RubyMath.sin(x, complex.real)), RubyMath.sinh(x, complex.image)));
    }
    
    private static IRubyObject m_sin(final ThreadContext context, final IRubyObject x) {
        if (Numeric.f_real_p(context, x).isTrue()) {
            return RubyMath.sin(x, x);
        }
        final RubyComplex complex = (RubyComplex)x;
        return newComplex(context, context.getRuntime().getComplex(), Numeric.f_mul(context, RubyMath.sin(x, complex.real), RubyMath.cosh(x, complex.image)), Numeric.f_mul(context, RubyMath.cos(x, complex.real), RubyMath.sinh(x, complex.image)));
    }
    
    private static IRubyObject m_sqrt(final ThreadContext context, final IRubyObject x) {
        if (Numeric.f_real_p(context, x).isTrue()) {
            if (!Numeric.f_negative_p(context, x)) {
                return RubyMath.sqrt(x, x);
            }
            return newComplex(context, context.getRuntime().getComplex(), RubyFixnum.zero(context.getRuntime()), RubyMath.sqrt(x, Numeric.f_negate(context, x)));
        }
        else {
            final RubyComplex complex = (RubyComplex)x;
            if (Numeric.f_negative_p(context, complex.image)) {
                return Numeric.f_conjugate(context, m_sqrt(context, Numeric.f_conjugate(context, x)));
            }
            final IRubyObject a = Numeric.f_abs(context, x);
            final IRubyObject two = RubyFixnum.two(context.getRuntime());
            return newComplex(context, context.getRuntime().getComplex(), RubyMath.sqrt(x, Numeric.f_div(context, Numeric.f_add(context, a, complex.real), two)), RubyMath.sqrt(x, Numeric.f_div(context, Numeric.f_sub(context, a, complex.real), two)));
        }
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
    
    @JRubyMethod(name = { "new!" }, meta = true, visibility = Visibility.PRIVATE)
    public static IRubyObject newInstanceBang(final ThreadContext context, final IRubyObject recv, IRubyObject real) {
        if (!(real instanceof RubyNumeric)) {
            real = Numeric.f_to_i(context, real);
        }
        return new RubyComplex(context.getRuntime(), recv, real, RubyFixnum.zero(context.getRuntime()));
    }
    
    @JRubyMethod(name = { "new!" }, meta = true, visibility = Visibility.PRIVATE)
    public static IRubyObject newInstanceBang(final ThreadContext context, final IRubyObject recv, IRubyObject real, IRubyObject image) {
        if (!(real instanceof RubyNumeric)) {
            real = Numeric.f_to_i(context, real);
        }
        if (!(image instanceof RubyNumeric)) {
            image = Numeric.f_to_i(context, image);
        }
        return new RubyComplex(context.getRuntime(), recv, real, image);
    }
    
    public static void setCanonicalization(final boolean canonical) {
        RubyComplex.canonicalization = canonical;
    }
    
    private static void realCheck(final ThreadContext context, final IRubyObject num) {
        switch (num.getMetaClass().index) {
            case 1:
            case 2:
            case 11:
            case 21: {
                break;
            }
            default: {
                if (!(num instanceof RubyNumeric) || !Numeric.f_real_p(context, num).isTrue()) {
                    throw context.getRuntime().newTypeError("not a real");
                }
                break;
            }
        }
    }
    
    private static IRubyObject canonicalizeInternal(final ThreadContext context, final IRubyObject clazz, final IRubyObject real, final IRubyObject image) {
        if (Numeric.f_zero_p(context, image) && Numeric.k_exact_p(image) && RubyComplex.canonicalization) {
            return real;
        }
        if (Numeric.f_real_p(context, real).isTrue() && Numeric.f_real_p(context, image).isTrue()) {
            return new RubyComplex(context.getRuntime(), clazz, real, image);
        }
        if (Numeric.f_real_p(context, real).isTrue()) {
            final RubyComplex complex = (RubyComplex)image;
            return new RubyComplex(context.getRuntime(), clazz, Numeric.f_sub(context, real, complex.image), Numeric.f_add(context, RubyFixnum.zero(context.getRuntime()), complex.real));
        }
        if (Numeric.f_real_p(context, image).isTrue()) {
            final RubyComplex complex = (RubyComplex)real;
            return new RubyComplex(context.getRuntime(), clazz, complex.real, Numeric.f_add(context, complex.image, image));
        }
        final RubyComplex complex2 = (RubyComplex)real;
        final RubyComplex complex3 = (RubyComplex)image;
        return new RubyComplex(context.getRuntime(), clazz, Numeric.f_sub(context, complex2.real, complex3.image), Numeric.f_add(context, complex2.image, complex3.real));
    }
    
    @Deprecated
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        switch (args.length) {
            case 1: {
                return newInstance(context, recv, args[0]);
            }
            case 2: {
                return newInstance(context, recv, args[0], args[1]);
            }
            default: {
                Arity.raiseArgumentError(context.getRuntime(), args.length, 1, 1);
                return null;
            }
        }
    }
    
    public static IRubyObject newInstanceNew(final ThreadContext context, final IRubyObject recv, final IRubyObject real) {
        return newInstance(context, recv, real);
    }
    
    @JRubyMethod(name = { "rect", "rectangular" }, meta = true)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject real) {
        realCheck(context, real);
        return canonicalizeInternal(context, recv, real, RubyFixnum.zero(context.getRuntime()));
    }
    
    public static IRubyObject newInstanceNew(final ThreadContext context, final IRubyObject recv, final IRubyObject real, final IRubyObject image) {
        return newInstance(context, recv, real, image);
    }
    
    @JRubyMethod(name = { "rect", "rectangular" }, meta = true)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject real, final IRubyObject image) {
        realCheck(context, real);
        realCheck(context, image);
        return canonicalizeInternal(context, recv, real, image);
    }
    
    private static IRubyObject f_complex_polar(final ThreadContext context, final IRubyObject clazz, final IRubyObject x, final IRubyObject y) {
        assert !(x instanceof RubyComplex) && !(y instanceof RubyComplex);
        return canonicalizeInternal(context, clazz, Numeric.f_mul(context, x, m_cos(context, y)), Numeric.f_mul(context, x, m_sin(context, y)));
    }
    
    @JRubyMethod(name = { "polar" }, meta = true)
    public static IRubyObject polar(final ThreadContext context, final IRubyObject clazz, final IRubyObject abs, final IRubyObject arg) {
        return f_complex_polar(context, clazz, abs, arg);
    }
    
    @JRubyMethod(name = { "polar" }, meta = true, required = 1, optional = 1, compat = CompatVersion.RUBY1_9)
    public static IRubyObject polar19(final ThreadContext context, final IRubyObject clazz, final IRubyObject[] args) {
        final IRubyObject abs = args[0];
        IRubyObject arg;
        if (args.length < 2) {
            arg = RubyFixnum.zero(context.getRuntime());
        }
        else {
            arg = args[1];
        }
        realCheck(context, abs);
        realCheck(context, arg);
        return f_complex_polar(context, clazz, abs, arg);
    }
    
    public static IRubyObject newComplexConvert(final ThreadContext context, final IRubyObject x) {
        return newComplexConvert(context, x, RubyFixnum.zero(context.getRuntime()));
    }
    
    public static IRubyObject newComplexConvert(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return convert(context, context.getRuntime().getComplex(), x, y);
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
        return convertCommon(context, recv, a1, context.getRuntime().getNil());
    }
    
    @JRubyMethod(name = { "convert" }, meta = true, visibility = Visibility.PRIVATE)
    public static IRubyObject convert(final ThreadContext context, final IRubyObject recv, final IRubyObject a1, final IRubyObject a2) {
        return convertCommon(context, recv, a1, a2);
    }
    
    private static IRubyObject convertCommon(final ThreadContext context, final IRubyObject recv, IRubyObject a1, IRubyObject a2) {
        final DynamicScope scope = context.getCurrentScope();
        final IRubyObject backref = scope.getBackRef(context.getRuntime());
        if (backref instanceof RubyMatchData) {
            ((RubyMatchData)backref).use();
        }
        if (a1 instanceof RubyString) {
            a1 = str_to_c_strict(context, a1);
        }
        if (a2 instanceof RubyString) {
            a2 = str_to_c_strict(context, a2);
        }
        scope.setBackRef(backref);
        if (a1 instanceof RubyComplex) {
            final RubyComplex a1Complex = (RubyComplex)a1;
            if (Numeric.k_exact_p(a1Complex.image) && Numeric.f_zero_p(context, a1Complex.image)) {
                a1 = a1Complex.real;
            }
        }
        if (a2 instanceof RubyComplex) {
            final RubyComplex a2Complex = (RubyComplex)a2;
            if (Numeric.k_exact_p(a2Complex.image) && Numeric.f_zero_p(context, a2Complex.image)) {
                a2 = a2Complex.real;
            }
        }
        if (a1 instanceof RubyComplex && (a2.isNil() || (Numeric.k_exact_p(a2) && Numeric.f_zero_p(context, a2)))) {
            return a1;
        }
        if (a2.isNil()) {
            if (a1 instanceof RubyNumeric && !Numeric.f_real_p(context, a1).isTrue()) {
                return a1;
            }
            return newInstance(context, recv, a1);
        }
        else {
            if (a1 instanceof RubyNumeric && a2 instanceof RubyNumeric && (!Numeric.f_real_p(context, a1).isTrue() || !Numeric.f_real_p(context, a2).isTrue())) {
                final Ruby runtime = context.getRuntime();
                return Numeric.f_add(context, a1, Numeric.f_mul(context, a2, newComplexBang(context, runtime.getComplex(), RubyFixnum.zero(runtime), RubyFixnum.one(runtime))));
            }
            return newInstance(context, recv, a1, a2);
        }
    }
    
    @JRubyMethod(name = { "real" })
    public IRubyObject real() {
        return this.real;
    }
    
    @JRubyMethod(name = { "imaginary", "imag" })
    public IRubyObject image() {
        return this.image;
    }
    
    @JRubyMethod(name = { "-@" })
    public IRubyObject negate(final ThreadContext context) {
        return newComplex(context, this.getMetaClass(), Numeric.f_negate(context, this.real), Numeric.f_negate(context, this.image));
    }
    
    @JRubyMethod(name = { "+" })
    public IRubyObject op_add(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyComplex) {
            final RubyComplex otherComplex = (RubyComplex)other;
            return newComplex(context, this.getMetaClass(), Numeric.f_add(context, this.real, otherComplex.real), Numeric.f_add(context, this.image, otherComplex.image));
        }
        if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
            return newComplex(context, this.getMetaClass(), Numeric.f_add(context, this.real, other), this.image);
        }
        return this.coerceBin(context, "+", other);
    }
    
    @JRubyMethod(name = { "-" })
    public IRubyObject op_sub(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyComplex) {
            final RubyComplex otherComplex = (RubyComplex)other;
            return newComplex(context, this.getMetaClass(), Numeric.f_sub(context, this.real, otherComplex.real), Numeric.f_sub(context, this.image, otherComplex.image));
        }
        if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
            return newComplex(context, this.getMetaClass(), Numeric.f_sub(context, this.real, other), this.image);
        }
        return this.coerceBin(context, "-", other);
    }
    
    @JRubyMethod(name = { "*" })
    public IRubyObject op_mul(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyComplex) {
            final RubyComplex otherComplex = (RubyComplex)other;
            final IRubyObject realp = Numeric.f_sub(context, Numeric.f_mul(context, this.real, otherComplex.real), Numeric.f_mul(context, this.image, otherComplex.image));
            final IRubyObject imagep = Numeric.f_add(context, Numeric.f_mul(context, this.real, otherComplex.image), Numeric.f_mul(context, this.image, otherComplex.real));
            return newComplex(context, this.getMetaClass(), realp, imagep);
        }
        if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
            return newComplex(context, this.getMetaClass(), Numeric.f_mul(context, this.real, other), Numeric.f_mul(context, this.image, other));
        }
        return this.coerceBin(context, "*", other);
    }
    
    @JRubyMethod(name = { "/", "quo" })
    public IRubyObject op_div(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyComplex) {
            final RubyComplex otherComplex = (RubyComplex)other;
            if (this.real instanceof RubyFloat || this.image instanceof RubyFloat || otherComplex.real instanceof RubyFloat || otherComplex.image instanceof RubyFloat) {
                final IRubyObject magn = RubyMath.hypot(this, otherComplex.real, otherComplex.image);
                final IRubyObject tmp = newComplexBang(context, this.getMetaClass(), Numeric.f_quo(context, otherComplex.real, magn), Numeric.f_quo(context, otherComplex.image, magn));
                return Numeric.f_quo(context, Numeric.f_mul(context, this, Numeric.f_conjugate(context, tmp)), magn);
            }
            return Numeric.f_quo(context, Numeric.f_mul(context, this, Numeric.f_conjugate(context, other)), Numeric.f_abs2(context, other));
        }
        else {
            if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
                return newComplex(context, this.getMetaClass(), Numeric.f_quo(context, this.real, other), Numeric.f_quo(context, this.image, other));
            }
            return this.coerceBin(context, "/", other);
        }
    }
    
    @JRubyMethod(name = { "fdiv" })
    public IRubyObject fdiv(final ThreadContext context, final IRubyObject other) {
        final IRubyObject complex = newComplex(context, this.getMetaClass(), Numeric.f_to_f(context, this.real), Numeric.f_to_f(context, this.image));
        return Numeric.f_div(context, complex, other);
    }
    
    @JRubyMethod(name = { "**" })
    public IRubyObject op_expt(final ThreadContext context, IRubyObject other) {
        if (Numeric.k_exact_p(other) && Numeric.f_zero_p(context, other)) {
            return newComplexBang(context, this.getMetaClass(), RubyFixnum.one(context.getRuntime()));
        }
        if (other instanceof RubyRational && Numeric.f_one_p(context, Numeric.f_denominator(context, other))) {
            other = Numeric.f_numerator(context, other);
        }
        if (other instanceof RubyComplex) {
            final RubyArray a = Numeric.f_polar(context, this).convertToArray();
            final IRubyObject r = a.eltInternal(0);
            final IRubyObject theta = a.eltInternal(1);
            final RubyComplex otherComplex = (RubyComplex)other;
            final IRubyObject nr = RubyMath.exp(this, Numeric.f_sub(context, Numeric.f_mul(context, otherComplex.real, RubyMath.log(this, r)), Numeric.f_mul(context, otherComplex.image, theta)));
            final IRubyObject ntheta = Numeric.f_add(context, Numeric.f_mul(context, theta, otherComplex.real), Numeric.f_mul(context, otherComplex.image, RubyMath.log(this, r)));
            return polar(context, this.getMetaClass(), nr, ntheta);
        }
        if (other instanceof RubyInteger) {
            final IRubyObject one = RubyFixnum.one(context.getRuntime());
            if (Numeric.f_gt_p(context, other, RubyFixnum.zero(context.getRuntime())).isTrue()) {
                IRubyObject z;
                IRubyObject x = z = this;
                IRubyObject n = Numeric.f_sub(context, other, one);
                final IRubyObject two = RubyFixnum.two(context.getRuntime());
                while (!Numeric.f_zero_p(context, n)) {
                    for (RubyArray a2 = Numeric.f_divmod(context, n, two).convertToArray(); Numeric.f_zero_p(context, a2.eltInternal(1)); a2 = Numeric.f_divmod(context, n, two).convertToArray()) {
                        final RubyComplex xComplex = (RubyComplex)x;
                        x = newComplex(context, this.getMetaClass(), Numeric.f_sub(context, Numeric.f_mul(context, xComplex.real, xComplex.real), Numeric.f_mul(context, xComplex.image, xComplex.image)), Numeric.f_mul(context, Numeric.f_mul(context, two, xComplex.real), xComplex.image));
                        n = a2.eltInternal(0);
                    }
                    z = Numeric.f_mul(context, z, x);
                    n = Numeric.f_sub(context, n, one);
                }
                return z;
            }
            return Numeric.f_expt(context, Numeric.f_div(context, Numeric.f_to_r(context, one), this), Numeric.f_negate(context, other));
        }
        else {
            if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
                final RubyArray a = Numeric.f_polar(context, this).convertToArray();
                final IRubyObject r = a.eltInternal(0);
                final IRubyObject theta = a.eltInternal(1);
                return f_complex_polar(context, this.getMetaClass(), Numeric.f_expt(context, r, other), Numeric.f_mul(context, theta, other));
            }
            return this.coerceBin(context, "**", other);
        }
    }
    
    @JRubyMethod(name = { "==" })
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyComplex) {
            final RubyComplex otherComplex = (RubyComplex)other;
            final boolean test = Numeric.f_equal(context, this.real, otherComplex.real).isTrue() && Numeric.f_equal(context, this.image, otherComplex.image).isTrue();
            return context.getRuntime().newBoolean(test);
        }
        if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
            final boolean test2 = Numeric.f_equal(context, this.real, other).isTrue() && Numeric.f_zero_p(context, this.image);
            return context.getRuntime().newBoolean(test2);
        }
        return Numeric.f_equal(context, other, this);
    }
    
    @JRubyMethod(name = { "coerce" })
    public IRubyObject coerce(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyNumeric && Numeric.f_real_p(context, other).isTrue()) {
            return context.getRuntime().newArray(newComplexBang(context, this.getMetaClass(), other), this);
        }
        throw context.getRuntime().newTypeError(other.getMetaClass().getName() + " can't be coerced into " + this.getMetaClass().getName());
    }
    
    @JRubyMethod(name = { "abs", "magnitude" })
    public IRubyObject abs(final ThreadContext context) {
        return RubyMath.hypot(this, this.real, this.image);
    }
    
    @JRubyMethod(name = { "abs2" })
    public IRubyObject abs2(final ThreadContext context) {
        return Numeric.f_add(context, Numeric.f_mul(context, this.real, this.real), Numeric.f_mul(context, this.image, this.image));
    }
    
    @JRubyMethod(name = { "arg", "angle", "phase" })
    public IRubyObject arg(final ThreadContext context) {
        return RubyMath.atan2(this, this.image, this.real);
    }
    
    @JRubyMethod(name = { "rectangular", "rect" })
    public IRubyObject rect(final ThreadContext context) {
        return context.getRuntime().newArray(this.real, this.image);
    }
    
    @JRubyMethod(name = { "polar" })
    public IRubyObject polar(final ThreadContext context) {
        return context.getRuntime().newArray(Numeric.f_abs(context, this), Numeric.f_arg(context, this));
    }
    
    @JRubyMethod(name = { "conjugate", "conj", "~" })
    public IRubyObject conjugate(final ThreadContext context) {
        return newComplex(context, this.getMetaClass(), this.real, Numeric.f_negate(context, this.image));
    }
    
    @JRubyMethod(name = { "real?" })
    public IRubyObject real_p(final ThreadContext context) {
        return context.getRuntime().getFalse();
    }
    
    public IRubyObject complex_p(final ThreadContext context) {
        return context.getRuntime().getTrue();
    }
    
    public IRubyObject exact_p(final ThreadContext context) {
        return (Numeric.f_exact_p(context, this.real).isTrue() && Numeric.f_exact_p(context, this.image).isTrue()) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
    }
    
    public IRubyObject inexact_p(final ThreadContext context) {
        return this.exact_p(context).isTrue() ? context.getRuntime().getFalse() : context.getRuntime().getTrue();
    }
    
    @JRubyMethod(name = { "denominator" })
    public IRubyObject demoninator(final ThreadContext context) {
        return Numeric.f_lcm(context, Numeric.f_denominator(context, this.real), Numeric.f_denominator(context, this.image));
    }
    
    @JRubyMethod(name = { "numerator" })
    public IRubyObject numerator(final ThreadContext context) {
        final IRubyObject cd = this.callMethod(context, "denominator");
        return newComplex(context, this.getMetaClass(), Numeric.f_mul(context, Numeric.f_numerator(context, this.real), Numeric.f_div(context, cd, Numeric.f_denominator(context, this.real))), Numeric.f_mul(context, Numeric.f_numerator(context, this.image), Numeric.f_div(context, cd, Numeric.f_denominator(context, this.image))));
    }
    
    @JRubyMethod(name = { "hash" })
    public IRubyObject hash(final ThreadContext context) {
        return Numeric.f_xor(context, RuntimeHelpers.invokedynamic(context, this.real, 3), RuntimeHelpers.invokedynamic(context, this.image, 3));
    }
    
    @JRubyMethod(name = { "eql?" })
    public IRubyObject eql_p(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyComplex) {
            final RubyComplex otherComplex = (RubyComplex)other;
            if (this.real.getMetaClass() == otherComplex.real.getMetaClass() && this.image.getMetaClass() == otherComplex.image.getMetaClass() && Numeric.f_equal(context, this, otherComplex).isTrue()) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    private static boolean signbit(final ThreadContext context, final IRubyObject x) {
        if (x instanceof RubyFloat) {
            final double value = ((RubyFloat)x).getDoubleValue();
            return !Double.isNaN(value) && Double.doubleToLongBits(value) < 0L;
        }
        return Numeric.f_negative_p(context, x);
    }
    
    private static boolean tpositive_p(final ThreadContext context, final IRubyObject x) {
        return !signbit(context, x);
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        final boolean impos = tpositive_p(context, this.image);
        final RubyString str = Numeric.f_to_s(context, this.real).convertToString();
        str.cat((byte)(impos ? 43 : 45));
        str.cat(Numeric.f_to_s(context, Numeric.f_abs(context, this.image)).convertToString().getByteList());
        if (!lastCharDigit(str)) {
            str.cat((byte)42);
        }
        str.cat((byte)105);
        return str;
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        final boolean impos = tpositive_p(context, this.image);
        final RubyString str = context.getRuntime().newString();
        str.cat((byte)40);
        str.cat(Numeric.f_inspect(context, this.real).convertToString().getByteList());
        str.cat((byte)(impos ? 43 : 45));
        str.cat(Numeric.f_inspect(context, Numeric.f_abs(context, this.image)).convertToString().getByteList());
        if (!lastCharDigit(str)) {
            str.cat((byte)42);
        }
        str.cat((byte)105);
        str.cat((byte)41);
        return str;
    }
    
    private static boolean lastCharDigit(final RubyString str) {
        final ByteList bytes = str.getByteList();
        return ASCIIEncoding.INSTANCE.isDigit(bytes.getUnsafeBytes()[bytes.getBegin() + bytes.getRealSize() - 1]);
    }
    
    @JRubyMethod(name = { "marshal_dump" })
    public IRubyObject marshal_dump(final ThreadContext context) {
        final RubyArray dump = context.getRuntime().newArray(this.real, this.image);
        if (this.hasVariables()) {
            dump.syncVariables(this);
        }
        return dump;
    }
    
    @JRubyMethod(name = { "marshal_load" })
    public IRubyObject marshal_load(final ThreadContext context, final IRubyObject arg) {
        final RubyArray load = arg.convertToArray();
        this.real = ((load.size() > 0) ? load.eltInternal(0) : context.getRuntime().getNil());
        this.image = ((load.size() > 1) ? load.eltInternal(1) : context.getRuntime().getNil());
        if (load.hasVariables()) {
            this.syncVariables((IRubyObject)load);
        }
        return this;
    }
    
    @JRubyMethod(name = { "to_i" })
    public IRubyObject to_i(final ThreadContext context) {
        if (Numeric.k_inexact_p(this.image) || !Numeric.f_zero_p(context, this.image)) {
            throw context.getRuntime().newRangeError("can't convert " + Numeric.f_to_s(context, this).convertToString() + " into Integer");
        }
        return Numeric.f_to_i(context, this.real);
    }
    
    @JRubyMethod(name = { "to_f" })
    public IRubyObject to_f(final ThreadContext context) {
        if (Numeric.k_inexact_p(this.image) || !Numeric.f_zero_p(context, this.image)) {
            throw context.getRuntime().newRangeError("can't convert " + Numeric.f_to_s(context, this).convertToString() + " into Float");
        }
        return Numeric.f_to_f(context, this.real);
    }
    
    @JRubyMethod(name = { "to_r" })
    public IRubyObject to_r(final ThreadContext context) {
        if (Numeric.k_inexact_p(this.image) || !Numeric.f_zero_p(context, this.image)) {
            throw context.getRuntime().newRangeError("can't convert " + Numeric.f_to_s(context, this).convertToString() + " into Rational");
        }
        return Numeric.f_to_r(context, this.real);
    }
    
    @JRubyMethod(name = { "rationalize" }, optional = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject rationalize(final ThreadContext context, final IRubyObject[] args) {
        return this.to_r(context);
    }
    
    static RubyArray str_to_c_internal(final ThreadContext context, final IRubyObject recv) {
        final RubyString s = recv.convertToString();
        final ByteList bytes = s.getByteList();
        final Ruby runtime = context.getRuntime();
        if (bytes.getRealSize() == 0) {
            return runtime.newArray(runtime.getNil(), recv);
        }
        IRubyObject re;
        IRubyObject sr;
        IRubyObject si = sr = (re = runtime.getNil());
        boolean po = false;
        IRubyObject m = RubyRegexp.newDummyRegexp(runtime, Numeric.ComplexPatterns.comp_pat0).callMethod(context, "match", s);
        if (!m.isNil()) {
            sr = m.callMethod(context, "[]", RubyFixnum.one(runtime));
            si = m.callMethod(context, "[]", RubyFixnum.two(runtime));
            re = m.callMethod(context, "post_match");
            po = true;
        }
        if (m.isNil()) {
            m = RubyRegexp.newDummyRegexp(runtime, Numeric.ComplexPatterns.comp_pat1).callMethod(context, "match", s);
            if (!m.isNil()) {
                sr = runtime.getNil();
                si = m.callMethod(context, "[]", RubyFixnum.one(runtime));
                if (si.isNil()) {
                    si = runtime.newString();
                }
                IRubyObject t = m.callMethod(context, "[]", RubyFixnum.two(runtime));
                if (t.isNil()) {
                    t = runtime.newString(new ByteList(new byte[] { 49 }));
                }
                si.convertToString().cat(t.convertToString().getByteList());
                re = m.callMethod(context, "post_match");
                po = false;
            }
        }
        if (m.isNil()) {
            m = RubyRegexp.newDummyRegexp(runtime, Numeric.ComplexPatterns.comp_pat2).callMethod(context, "match", s);
            if (m.isNil()) {
                return runtime.newArray(runtime.getNil(), recv);
            }
            sr = m.callMethod(context, "[]", RubyFixnum.one(runtime));
            if (m.callMethod(context, "[]", RubyFixnum.two(runtime)).isNil()) {
                si = runtime.getNil();
            }
            else {
                si = m.callMethod(context, "[]", RubyFixnum.three(runtime));
                IRubyObject t = m.callMethod(context, "[]", RubyFixnum.four(runtime));
                if (t.isNil()) {
                    t = runtime.newString(new ByteList(new byte[] { 49 }));
                }
                si.convertToString().cat(t.convertToString().getByteList());
            }
            re = m.callMethod(context, "post_match");
            po = false;
        }
        IRubyObject i;
        IRubyObject r = i = RubyFixnum.zero(runtime);
        if (!sr.isNil()) {
            if (sr.callMethod(context, "include?", runtime.newString(new ByteList(new byte[] { 47 }))).isTrue()) {
                r = Numeric.f_to_r(context, sr);
            }
            else if (Numeric.f_gt_p(context, sr.callMethod(context, "count", runtime.newString(".eE")), RubyFixnum.zero(runtime)).isTrue()) {
                r = Numeric.f_to_f(context, sr);
            }
            else {
                r = Numeric.f_to_i(context, sr);
            }
        }
        if (!si.isNil()) {
            if (si.callMethod(context, "include?", runtime.newString(new ByteList(new byte[] { 47 }))).isTrue()) {
                i = Numeric.f_to_r(context, si);
            }
            else if (Numeric.f_gt_p(context, si.callMethod(context, "count", runtime.newString(".eE")), RubyFixnum.zero(runtime)).isTrue()) {
                i = Numeric.f_to_f(context, si);
            }
            else {
                i = Numeric.f_to_i(context, si);
            }
        }
        return runtime.newArray(po ? newComplexPolar(context, r, i) : newComplexCanonicalize(context, r, i), re);
    }
    
    private static IRubyObject str_to_c_strict(final ThreadContext context, final IRubyObject recv) {
        final RubyArray a = str_to_c_internal(context, recv);
        if (a.eltInternal(0).isNil() || a.eltInternal(1).convertToString().getByteList().length() > 0) {
            final IRubyObject s = recv.callMethod(context, "inspect");
            throw context.getRuntime().newArgumentError("invalid value for convert(): " + s.convertToString());
        }
        return a.eltInternal(0);
    }
    
    static {
        RubyComplex.COMPLEX_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyFixnum zero = RubyFixnum.zero(runtime);
                return new RubyComplex(runtime, klass, zero, zero, null);
            }
        };
        RubyComplex.canonicalization = false;
    }
}
