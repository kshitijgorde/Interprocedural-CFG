// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jcodings.Encoding;
import org.jcodings.specific.ASCIIEncoding;
import org.joni.Regex;
import org.jruby.RubyInteger;
import org.jruby.RubyFloat;
import org.jruby.Ruby;
import org.jruby.RubyBignum;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class Numeric
{
    public static final boolean CANON = true;
    private static long SQRT_LONG_MAX;
    
    public static IRubyObject f_add(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (y instanceof RubyFixnum && ((RubyFixnum)y).getLongValue() == 0L) {
            return x;
        }
        if (x instanceof RubyFixnum && ((RubyFixnum)x).getLongValue() == 0L) {
            return y;
        }
        return x.callMethod(context, "+", y);
    }
    
    public static IRubyObject f_cmp(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (!(x instanceof RubyFixnum) || !(y instanceof RubyFixnum)) {
            return RuntimeHelpers.invokedynamic(context, x, 4, y);
        }
        final long c = ((RubyFixnum)x).getLongValue() - ((RubyFixnum)y).getLongValue();
        if (c > 0L) {
            return RubyFixnum.one(context.getRuntime());
        }
        if (c < 0L) {
            return RubyFixnum.minus_one(context.getRuntime());
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    public static IRubyObject f_div(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (y instanceof RubyFixnum && ((RubyFixnum)y).getLongValue() == 1L) {
            return x;
        }
        return x.callMethod(context, "/", y);
    }
    
    public static IRubyObject f_gt_p(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (x instanceof RubyFixnum && y instanceof RubyFixnum) {
            return (((RubyFixnum)x).getLongValue() > ((RubyFixnum)y).getLongValue()) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
        }
        return x.callMethod(context, ">", y);
    }
    
    public static IRubyObject f_lt_p(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (x instanceof RubyFixnum && y instanceof RubyFixnum) {
            return (((RubyFixnum)x).getLongValue() < ((RubyFixnum)y).getLongValue()) ? context.getRuntime().getTrue() : context.getRuntime().getFalse();
        }
        return x.callMethod(context, "<", y);
    }
    
    public static IRubyObject f_mod(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "%", y);
    }
    
    public static IRubyObject f_mul(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        final Ruby runtime = context.getRuntime();
        if (y instanceof RubyFixnum) {
            final long iy = ((RubyFixnum)y).getLongValue();
            if (iy == 0L) {
                if (x instanceof RubyFixnum || x instanceof RubyBignum) {
                    return RubyFixnum.zero(runtime);
                }
            }
            else if (iy == 1L) {
                return x;
            }
        }
        else if (x instanceof RubyFixnum) {
            final long ix = ((RubyFixnum)x).getLongValue();
            if (ix == 0L) {
                if (y instanceof RubyFixnum || y instanceof RubyBignum) {
                    return RubyFixnum.zero(runtime);
                }
            }
            else if (ix == 1L) {
                return y;
            }
        }
        return x.callMethod(context, "*", y);
    }
    
    public static IRubyObject f_sub(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (y instanceof RubyFixnum && ((RubyFixnum)y).getLongValue() == 0L) {
            return x;
        }
        return x.callMethod(context, "-", y);
    }
    
    public static IRubyObject f_xor(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "^", y);
    }
    
    public static IRubyObject f_abs(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "abs");
    }
    
    public static IRubyObject f_abs2(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "abs2");
    }
    
    public static IRubyObject f_arg(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "arg");
    }
    
    public static IRubyObject f_conjugate(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "conjugate");
    }
    
    public static IRubyObject f_denominator(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "denominator");
    }
    
    public static IRubyObject f_exact_p(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "exact?");
    }
    
    public static IRubyObject f_numerator(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "numerator");
    }
    
    public static IRubyObject f_polar(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "polar");
    }
    
    public static IRubyObject f_real_p(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "real?");
    }
    
    public static IRubyObject f_integer_p(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "integer?");
    }
    
    public static IRubyObject f_divmod(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "divmod", y);
    }
    
    public static IRubyObject f_floor(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "floor");
    }
    
    public static IRubyObject f_inspect(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "inspect");
    }
    
    public static IRubyObject f_negate(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "-@");
    }
    
    public static IRubyObject f_to_f(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "to_f");
    }
    
    public static IRubyObject f_to_i(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "to_i");
    }
    
    public static IRubyObject f_to_r(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "to_r");
    }
    
    public static IRubyObject f_to_s(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "to_s");
    }
    
    public static IRubyObject f_truncate(final ThreadContext context, final IRubyObject x) {
        return x.callMethod(context, "truncate");
    }
    
    public static IRubyObject f_equal(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (x instanceof RubyFixnum && y instanceof RubyFixnum) {
            return context.getRuntime().newBoolean(((RubyFixnum)x).getLongValue() == ((RubyFixnum)y).getLongValue());
        }
        return x.callMethod(context, "==", y);
    }
    
    public static IRubyObject f_expt(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "**", y);
    }
    
    public static IRubyObject f_idiv(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "div", y);
    }
    
    public static IRubyObject f_quo(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "quo", y);
    }
    
    public static IRubyObject f_rshift(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, ">>", y);
    }
    
    public static IRubyObject f_lshift(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        return x.callMethod(context, "<<", y);
    }
    
    public static boolean f_negative_p(final ThreadContext context, final IRubyObject x) {
        if (x instanceof RubyFixnum) {
            return ((RubyFixnum)x).getLongValue() < 0L;
        }
        return x.callMethod(context, "<", RubyFixnum.zero(context.getRuntime())).isTrue();
    }
    
    public static boolean f_zero_p(final ThreadContext context, final IRubyObject x) {
        if (x instanceof RubyFixnum) {
            return ((RubyFixnum)x).getLongValue() == 0L;
        }
        return x.callMethod(context, "==", RubyFixnum.zero(context.getRuntime())).isTrue();
    }
    
    public static boolean f_one_p(final ThreadContext context, final IRubyObject x) {
        if (x instanceof RubyFixnum) {
            return ((RubyFixnum)x).getLongValue() == 1L;
        }
        return x.callMethod(context, "==", RubyFixnum.one(context.getRuntime())).isTrue();
    }
    
    public static long i_gcd(long x, long y) {
        if (x < 0L) {
            x = -x;
        }
        if (y < 0L) {
            y = -y;
        }
        if (x == 0L) {
            return y;
        }
        if (y == 0L) {
            return x;
        }
        while (x > 0L) {
            final long t = x;
            x = y % x;
            y = t;
        }
        return y;
    }
    
    public static IRubyObject f_gcd(final ThreadContext context, IRubyObject x, IRubyObject y) {
        if (x instanceof RubyFixnum && y instanceof RubyFixnum) {
            return RubyFixnum.newFixnum(context.getRuntime(), i_gcd(((RubyFixnum)x).getLongValue(), ((RubyFixnum)y).getLongValue()));
        }
        if (f_negative_p(context, x)) {
            x = f_negate(context, x);
        }
        if (f_negative_p(context, y)) {
            y = f_negate(context, y);
        }
        if (f_zero_p(context, x)) {
            return y;
        }
        if (f_zero_p(context, y)) {
            return x;
        }
        while (true) {
            if (x instanceof RubyFixnum) {
                if (((RubyFixnum)x).getLongValue() == 0L) {
                    return y;
                }
                if (y instanceof RubyFixnum) {
                    return RubyFixnum.newFixnum(context.getRuntime(), i_gcd(((RubyFixnum)x).getLongValue(), ((RubyFixnum)y).getLongValue()));
                }
            }
            final IRubyObject z = x;
            x = f_mod(context, y, x);
            y = z;
        }
    }
    
    public static IRubyObject f_lcm(final ThreadContext context, final IRubyObject x, final IRubyObject y) {
        if (f_zero_p(context, x) || f_zero_p(context, y)) {
            return RubyFixnum.zero(context.getRuntime());
        }
        return f_abs(context, f_mul(context, f_div(context, x, f_gcd(context, x, y)), y));
    }
    
    public static long i_ilog2(final ThreadContext context, IRubyObject x) {
        final long q = (x.callMethod(context, "size").convertToInteger().getLongValue() - 8L) * 8L + 1L;
        if (q > 0L) {
            x = f_rshift(context, x, RubyFixnum.newFixnum(context.getRuntime(), q));
        }
        long fx;
        long r;
        for (fx = x.convertToInteger().getLongValue(), r = -1L; fx != 0L; fx >>= 1, ++r) {}
        return q + r;
    }
    
    public static double ldexp(final double f, final long e) {
        return f * Math.pow(2.0, e);
    }
    
    public static double frexp(double mantissa, final long[] e) {
        short sign = 1;
        long exponent = 0L;
        if (Double.isInfinite(mantissa) || Double.isNaN(mantissa)) {
            return mantissa;
        }
        if (mantissa != 0.0) {
            if (mantissa < 0.0) {
                mantissa = -mantissa;
                sign = -1;
            }
            while (mantissa < 0.5) {
                mantissa *= 2.0;
                --exponent;
            }
            while (mantissa >= 1.0) {
                mantissa *= 0.5;
                ++exponent;
            }
        }
        e[0] = exponent;
        return sign * mantissa;
    }
    
    static boolean fitSqrtLong(final long n) {
        return n < Numeric.SQRT_LONG_MAX && n >= -Numeric.SQRT_LONG_MAX;
    }
    
    public static IRubyObject int_pow(final ThreadContext context, long x, long y) {
        boolean neg = x < 0L;
        long z = 1L;
        if (neg) {
            x = -x;
        }
        if ((y & 0x1L) != 0x0L) {
            z = x;
        }
        else {
            neg = false;
        }
        y &= 0xFFFFFFFFFFFFFFFEL;
        final Ruby runtime = context.getRuntime();
        while (true) {
            if (y % 2L == 0L) {
                if (!fitSqrtLong(x)) {
                    IRubyObject v = RubyBignum.newBignum(runtime, RubyBignum.fix2big(RubyFixnum.newFixnum(runtime, x))).op_pow(context, RubyFixnum.newFixnum(runtime, y));
                    if (z != 1L) {
                        v = RubyBignum.newBignum(runtime, RubyBignum.fix2big(RubyFixnum.newFixnum(runtime, neg ? (-z) : z))).op_mul19(context, v);
                    }
                    return v;
                }
                x *= x;
                y >>= 1;
            }
            else {
                final long xz = x * x;
                if (xz / x != z) {
                    IRubyObject v2 = RubyBignum.newBignum(runtime, RubyBignum.fix2big(RubyFixnum.newFixnum(runtime, x))).op_pow(context, RubyFixnum.newFixnum(runtime, y));
                    if (z != 1L) {
                        v2 = RubyBignum.newBignum(runtime, RubyBignum.fix2big(RubyFixnum.newFixnum(runtime, neg ? (-z) : z))).op_mul19(context, v2);
                    }
                    return v2;
                }
                z = xz;
                if (--y == 0L) {
                    if (neg) {
                        z = -z;
                    }
                    return RubyFixnum.newFixnum(runtime, z);
                }
                continue;
            }
        }
    }
    
    public static boolean k_exact_p(final IRubyObject x) {
        return !(x instanceof RubyFloat);
    }
    
    public static boolean k_inexact_p(final IRubyObject x) {
        return x instanceof RubyFloat;
    }
    
    public static IRubyObject[] nurat_rationalize_internal(final ThreadContext context, final IRubyObject[] ary) {
        IRubyObject a = ary[0];
        IRubyObject b = ary[1];
        final RubyFixnum zero = RubyFixnum.zero(context.getRuntime());
        final RubyFixnum one = RubyFixnum.one(context.getRuntime());
        IRubyObject p0;
        IRubyObject q1 = p0 = zero;
        IRubyObject p2;
        IRubyObject q2 = p2 = one;
        IRubyObject c;
        while (true) {
            c = a.callMethod(context, "ceil");
            if (f_lt_p(context, c, b).isTrue()) {
                break;
            }
            final IRubyObject k = f_sub(context, c, one);
            final IRubyObject p3 = f_add(context, f_mul(context, k, p2), p0);
            final IRubyObject q3 = f_add(context, f_mul(context, k, q1), q2);
            final IRubyObject t = f_quo(context, one, f_sub(context, b, k));
            b = f_quo(context, one, f_sub(context, a, k));
            a = t;
            p0 = p2;
            q2 = q1;
            p2 = p3;
            q1 = q3;
        }
        final IRubyObject p4 = f_add(context, f_mul(context, c, p2), p0);
        final IRubyObject q4 = f_add(context, f_mul(context, c, q1), q2);
        final IRubyObject[] v = { p4, q4 };
        return v;
    }
    
    public static void checkInteger(final ThreadContext context, final IRubyObject obj) {
        if (!(obj instanceof RubyInteger)) {
            throw context.getRuntime().newTypeError("not an integer");
        }
    }
    
    static {
        Numeric.SQRT_LONG_MAX = 2147483648L;
    }
    
    public static final class ComplexPatterns
    {
        public static final Regex comp_pat0;
        public static final Regex comp_pat1;
        public static final Regex comp_pat2;
        public static final Regex underscores_pat;
        
        static {
            final String WS = "\\s*";
            final String DIGITS = "(?:\\d(?:_\\d|\\d)*)";
            final String NUMERATOR = "(?:" + DIGITS + "?\\.)?" + DIGITS + "(?:[eE][-+]?" + DIGITS + ")?";
            final String DENOMINATOR = DIGITS;
            final String NUMBER = "[-+]?" + NUMERATOR + "(?:\\/" + DENOMINATOR + ")?";
            final String NUMBERNOS = NUMERATOR + "(?:\\/" + DENOMINATOR + ")?";
            final String PATTERN0 = "\\A" + WS + "(" + NUMBER + ")@(" + NUMBER + ")" + WS;
            final String PATTERN2 = "\\A" + WS + "([-+])?(" + NUMBER + ")?[iIjJ]" + WS;
            final String PATTERN3 = "\\A" + WS + "(" + NUMBER + ")(([-+])(" + NUMBERNOS + ")?[iIjJ])?" + WS;
            comp_pat0 = new Regex(PATTERN0.getBytes(), 0, PATTERN0.length(), 0, ASCIIEncoding.INSTANCE);
            comp_pat1 = new Regex(PATTERN2.getBytes(), 0, PATTERN2.length(), 0, ASCIIEncoding.INSTANCE);
            comp_pat2 = new Regex(PATTERN3.getBytes(), 0, PATTERN3.length(), 0, ASCIIEncoding.INSTANCE);
            underscores_pat = new Regex("_+".getBytes(), 0, 2, 0, ASCIIEncoding.INSTANCE);
        }
    }
    
    public static final class RationalPatterns
    {
        public static final Regex rat_pat;
        public static final Regex an_e_pat;
        public static final Regex a_dot_pat;
        
        static {
            final String WS = "\\s*";
            final String DIGITS = "(?:\\d(?:_\\d|\\d)*)";
            final String NUMERATOR = "(?:" + DIGITS + "?\\.)?" + DIGITS + "(?:[eE][-+]?" + DIGITS + ")?";
            final String DENOMINATOR = DIGITS;
            final String PATTERN = "\\A" + WS + "([-+])?(" + NUMERATOR + ")(?:\\/(" + DENOMINATOR + "))?" + WS;
            rat_pat = new Regex(PATTERN.getBytes(), 0, PATTERN.length(), 0, ASCIIEncoding.INSTANCE);
            an_e_pat = new Regex("[Ee]".getBytes(), 0, 4, 0, ASCIIEncoding.INSTANCE);
            a_dot_pat = new Regex("\\.".getBytes(), 0, 2, 0, ASCIIEncoding.INSTANCE);
        }
    }
}
