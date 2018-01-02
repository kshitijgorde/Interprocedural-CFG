// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Math" })
public class RubyMath
{
    private static final double[] ASINH_COEF;
    private static final double[] ATANH_COEF;
    private static final double[] ERFC_COEF;
    private static final double[] ERFC2_COEF;
    private static final double[] ERFCC_COEF;
    private static final double[] FACTORIAL;
    private static final double[] NEMES_GAMMA_COEFF;
    
    public static RubyModule createMathModule(final Ruby runtime) {
        final RubyModule result = runtime.defineModule("Math");
        runtime.setMath(result);
        result.defineConstant("E", RubyFloat.newFloat(runtime, 2.718281828459045));
        result.defineConstant("PI", RubyFloat.newFloat(runtime, 3.141592653589793));
        result.defineAnnotatedMethods(RubyMath.class);
        return result;
    }
    
    private static void domainCheck(final IRubyObject recv, final double value, final String msg) {
        if (Double.isNaN(value)) {
            throw recv.getRuntime().newErrnoEDOMError(msg);
        }
    }
    
    private static void domainCheck19(final IRubyObject recv, final double value, final String msg) {
        if (Double.isNaN(value)) {
            throw recv.getRuntime().newMathDomainError(msg);
        }
    }
    
    private static double chebylevSerie(final double x, final double[] coef) {
        double b1 = 0.0;
        double b2 = 0.0;
        double b3 = 0.0;
        final double twox = 2.0 * x;
        for (int i = coef.length - 1; i >= 0; --i) {
            b3 = b1;
            b1 = b2;
            b2 = twox * b1 - b3 + coef[i];
        }
        return 0.5 * (b2 - b3);
    }
    
    private static double sign(final double x, final double y) {
        final double abs = (x < 0.0) ? (-x) : x;
        return (y < 0.0) ? (-abs) : abs;
    }
    
    private static RubyFloat needFloat(final IRubyObject x) {
        if (x instanceof RubyFloat) {
            return (RubyFloat)x;
        }
        if (!x.getRuntime().getNumeric().isInstance(x)) {
            TypeConverter.handleUncoercibleObject(true, x, x.getRuntime().getFloat());
        }
        return (RubyFloat)TypeConverter.convertToType19(x, x.getRuntime().getFloat(), "to_f", true);
    }
    
    @JRubyMethod(name = { "atan2" }, required = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat atan2(final IRubyObject recv, final IRubyObject x, final IRubyObject y) {
        final double valuea = RubyKernel.new_float(recv, x).getDoubleValue();
        final double valueb = RubyKernel.new_float(recv, y).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.atan2(valuea, valueb));
    }
    
    @JRubyMethod(name = { "atan2" }, required = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat atan219(final IRubyObject recv, final IRubyObject x, final IRubyObject y) {
        final double valuea = needFloat(x).getDoubleValue();
        final double valueb = needFloat(y).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.atan2(valuea, valueb));
    }
    
    @JRubyMethod(name = { "cos" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat cos(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.cos(value));
    }
    
    @JRubyMethod(name = { "cos" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat cos19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.cos(value));
    }
    
    @JRubyMethod(name = { "sin" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat sin(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.sin(value));
    }
    
    @JRubyMethod(name = { "sin" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat sin19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.sin(value));
    }
    
    @JRubyMethod(name = { "tan" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat tan(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.tan(value));
    }
    
    @JRubyMethod(name = { "tan" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat tan19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.tan(value));
    }
    
    @JRubyMethod(name = { "asin" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat asin(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        final double result = Math.asin(value);
        domainCheck(recv, result, "asin");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "asin" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat asin19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        final double result = Math.asin(value);
        domainCheck(recv, result, "asin");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "acos" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat acos(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        final double result = Math.acos(value);
        domainCheck(recv, result, "acos");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "acos" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat acos19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        final double result = Math.acos(value);
        domainCheck(recv, result, "acos");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "atan" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat atan(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.atan(value));
    }
    
    @JRubyMethod(name = { "atan" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat atan19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.atan(value));
    }
    
    @JRubyMethod(name = { "cosh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat cosh(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), (Math.exp(value) + Math.exp(-value)) / 2.0);
    }
    
    @JRubyMethod(name = { "cosh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat cosh19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), (Math.exp(value) + Math.exp(-value)) / 2.0);
    }
    
    @JRubyMethod(name = { "sinh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat sinh(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), (Math.exp(value) - Math.exp(-value)) / 2.0);
    }
    
    @JRubyMethod(name = { "sinh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat sinh19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), (Math.exp(value) - Math.exp(-value)) / 2.0);
    }
    
    @JRubyMethod(name = { "tanh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat tanh(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.tanh(value));
    }
    
    @JRubyMethod(name = { "tanh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat tanh19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.tanh(value));
    }
    
    @JRubyMethod(name = { "acosh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat acosh(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        double result;
        if (Double.isNaN(value) || value < 1.0) {
            result = Double.NaN;
        }
        else if (value < 9.490626562E7) {
            result = Math.log(value + Math.sqrt(value * value - 1.0));
        }
        else {
            result = 0.6931471805599453 + Math.log(value);
        }
        domainCheck(recv, result, "acosh");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "acosh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat acosh19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        double result;
        if (Double.isNaN(value) || value < 1.0) {
            result = Double.NaN;
        }
        else if (value < 9.490626562E7) {
            result = Math.log(value + Math.sqrt(value * value - 1.0));
        }
        else {
            result = 0.6931471805599453 + Math.log(value);
        }
        domainCheck(recv, result, "acosh");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "asinh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat asinh(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (Double.isNaN(value)) {
            result = Double.NaN;
        }
        else if (y <= 1.05367E-8) {
            result = value;
        }
        else if (y <= 1.0) {
            result = value * (1.0 + chebylevSerie(2.0 * value * value - 1.0, RubyMath.ASINH_COEF));
        }
        else if (y < 9.490626562E7) {
            result = Math.log(value + Math.sqrt(value * value + 1.0));
        }
        else {
            result = 0.6931471805599453 + Math.log(y);
            if (value < 0.0) {
                result *= -1.0;
            }
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "asinh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat asinh19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (Double.isNaN(value)) {
            result = Double.NaN;
        }
        else if (y <= 1.05367E-8) {
            result = value;
        }
        else if (y <= 1.0) {
            result = value * (1.0 + chebylevSerie(2.0 * value * value - 1.0, RubyMath.ASINH_COEF));
        }
        else if (y < 9.490626562E7) {
            result = Math.log(value + Math.sqrt(value * value + 1.0));
        }
        else {
            result = 0.6931471805599453 + Math.log(y);
            if (value < 0.0) {
                result *= -1.0;
            }
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "atanh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat atanh(final IRubyObject recv, final IRubyObject x) {
        final double result = atanh_common(recv, x);
        domainCheck(recv, result, "atanh");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "atanh" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat atanh_19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        final double y = Math.abs(value);
        if (y == 1.0) {
            throw recv.getRuntime().newErrnoEDOMError("atanh");
        }
        final double result = atanh_common(recv, x);
        domainCheck19(recv, result, "atanh");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    private static double atanh_common(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (Double.isNaN(value)) {
            result = Double.NaN;
        }
        else if (y < 1.82501E-8) {
            result = value;
        }
        else if (y <= 0.5) {
            result = value * (1.0 + chebylevSerie(8.0 * value * value - 1.0, RubyMath.ATANH_COEF));
        }
        else if (y < 1.0) {
            result = 0.5 * Math.log((1.0 + value) / (1.0 - value));
        }
        else if (y == 1.0) {
            result = value * Double.POSITIVE_INFINITY;
        }
        else {
            result = Double.NaN;
        }
        return result;
    }
    
    @JRubyMethod(name = { "exp" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat exp(final IRubyObject recv, final IRubyObject exponent) {
        final double value = RubyKernel.new_float(recv, exponent).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.exp(value));
    }
    
    @JRubyMethod(name = { "exp" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat exp19(final IRubyObject recv, final IRubyObject exponent) {
        final double value = needFloat(exponent).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), Math.exp(value));
    }
    
    private static RubyFloat log_common(final IRubyObject recv, final double value, final double base, final String msg) {
        final double result = Math.log(value) / Math.log(base);
        domainCheck(recv, result, msg);
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    private static RubyFloat log_common19(final IRubyObject recv, final double value, final double base, final String msg) {
        if (value < 0.0) {
            throw recv.getRuntime().newMathDomainError(msg);
        }
        final double result = Math.log(value) / Math.log(base);
        domainCheck(recv, result, msg);
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "log" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat log(final IRubyObject recv, final IRubyObject x) {
        return log_common(recv, RubyKernel.new_float(recv, x).getDoubleValue(), 2.718281828459045, "log");
    }
    
    @JRubyMethod(name = { "log" }, required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat log_19(final IRubyObject recv, final IRubyObject[] args) {
        final double value = needFloat(args[0]).getDoubleValue();
        double base = 2.718281828459045;
        if (args.length == 2) {
            base = needFloat(args[1]).getDoubleValue();
        }
        return log_common19(recv, value, base, "log");
    }
    
    @JRubyMethod(name = { "log10" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat log10(final IRubyObject recv, final IRubyObject x) {
        return log_common(recv, RubyKernel.new_float(recv, x).getDoubleValue(), 10.0, "log10");
    }
    
    @JRubyMethod(name = { "log10" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat log10_19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return log_common19(recv, value, 10.0, "log10");
    }
    
    @JRubyMethod(name = { "log2" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat log2(final IRubyObject recv, final IRubyObject x) {
        return log_common(recv, RubyKernel.new_float(recv, x).getDoubleValue(), 2.0, "log2");
    }
    
    @JRubyMethod(name = { "log2" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat log2_19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        return log_common19(recv, value, 2.0, "log2");
    }
    
    @JRubyMethod(name = { "sqrt" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat sqrt(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        double result;
        if (value < 0.0) {
            result = Double.NaN;
        }
        else {
            result = Math.sqrt(value);
        }
        domainCheck(recv, result, "sqrt");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "sqrt" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat sqrt19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        double result;
        if (value < 0.0) {
            result = Double.NaN;
        }
        else {
            result = Math.sqrt(value);
        }
        domainCheck19(recv, result, "sqrt");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "cbrt" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat cbrt(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        double result;
        if (value < 0.0) {
            result = -Math.pow(-value, 0.3333333333333333);
        }
        else {
            result = Math.pow(value, 0.3333333333333333);
        }
        domainCheck(recv, result, "cbrt");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "hypot" }, required = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat hypot(final IRubyObject recv, final IRubyObject x, final IRubyObject y) {
        final double valuea = RubyKernel.new_float(recv, x).getDoubleValue();
        final double valueb = RubyKernel.new_float(recv, y).getDoubleValue();
        double result;
        if (Math.abs(valuea) > Math.abs(valueb)) {
            result = valueb / valuea;
            result = Math.abs(valuea) * Math.sqrt(1.0 + result * result);
        }
        else if (valueb != 0.0) {
            result = valuea / valueb;
            result = Math.abs(valueb) * Math.sqrt(1.0 + result * result);
        }
        else {
            result = 0.0;
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "hypot" }, required = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat hypot19(final IRubyObject recv, final IRubyObject x, final IRubyObject y) {
        final double valuea = needFloat(x).getDoubleValue();
        final double valueb = needFloat(y).getDoubleValue();
        double result;
        if (Math.abs(valuea) > Math.abs(valueb)) {
            result = valueb / valuea;
            result = Math.abs(valuea) * Math.sqrt(1.0 + result * result);
        }
        else if (valueb != 0.0) {
            result = valuea / valueb;
            result = Math.abs(valueb) * Math.sqrt(1.0 + result * result);
        }
        else {
            result = 0.0;
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "frexp" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyArray frexp(final IRubyObject recv, final IRubyObject other) {
        double mantissa = RubyKernel.new_float(recv, other).getDoubleValue();
        short sign = 1;
        long exponent = 0L;
        if (!Double.isInfinite(mantissa) && mantissa != 0.0) {
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
        return RubyArray.newArray(recv.getRuntime(), RubyFloat.newFloat(recv.getRuntime(), sign * mantissa), RubyNumeric.int2fix(recv.getRuntime(), exponent));
    }
    
    @JRubyMethod(name = { "frexp" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyArray frexp19(final IRubyObject recv, final IRubyObject other) {
        double mantissa = needFloat(other).getDoubleValue();
        short sign = 1;
        long exponent = 0L;
        if (!Double.isInfinite(mantissa) && mantissa != 0.0) {
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
        return RubyArray.newArray(recv.getRuntime(), RubyFloat.newFloat(recv.getRuntime(), sign * mantissa), RubyNumeric.int2fix(recv.getRuntime(), exponent));
    }
    
    @JRubyMethod(name = { "ldexp" }, required = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat ldexp(final IRubyObject recv, final IRubyObject mantissa, final IRubyObject exponent) {
        final double mantissaValue = RubyKernel.new_float(recv, mantissa).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), mantissaValue * Math.pow(2.0, RubyNumeric.num2int(exponent)));
    }
    
    @JRubyMethod(name = { "ldexp" }, required = 2, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat ldexp19(final IRubyObject recv, final IRubyObject mantissa, final IRubyObject exponent) {
        final double mantissaValue = needFloat(mantissa).getDoubleValue();
        return RubyFloat.newFloat(recv.getRuntime(), mantissaValue * Math.pow(2.0, RubyNumeric.num2int(exponent)));
    }
    
    @JRubyMethod(name = { "erf" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat erf(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (y <= 1.49012E-8) {
            result = 2.0 * value / 1.772453850905516;
        }
        else if (y <= 1.0) {
            result = value * (1.0 + chebylevSerie(2.0 * value * value - 1.0, RubyMath.ERFC_COEF));
        }
        else if (y < 6.013687357) {
            result = sign(1.0 - erfc(recv, RubyFloat.newFloat(recv.getRuntime(), y)).getDoubleValue(), value);
        }
        else {
            result = sign(1.0, value);
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "erf" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat erf19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (y <= 1.49012E-8) {
            result = 2.0 * value / 1.772453850905516;
        }
        else if (y <= 1.0) {
            result = value * (1.0 + chebylevSerie(2.0 * value * value - 1.0, RubyMath.ERFC_COEF));
        }
        else if (y < 6.013687357) {
            result = sign(1.0 - erfc(recv, RubyFloat.newFloat(recv.getRuntime(), y)).getDoubleValue(), value);
        }
        else {
            result = sign(1.0, value);
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "erfc" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public static RubyFloat erfc(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (value <= -6.013687357) {
            result = 2.0;
        }
        else if (y < 1.49012E-8) {
            result = 1.0 - 2.0 * value / 1.772453850905516;
        }
        else {
            final double ysq = y * y;
            if (y < 1.0) {
                result = 1.0 - value * (1.0 + chebylevSerie(2.0 * ysq - 1.0, RubyMath.ERFC_COEF));
            }
            else if (y <= 4.0) {
                result = Math.exp(-ysq) / y * (0.5 + chebylevSerie((8.0 / ysq - 5.0) / 3.0, RubyMath.ERFC2_COEF));
                if (value < 0.0) {
                    result = 2.0 - result;
                }
                if (value < 0.0) {
                    result = 2.0 - result;
                }
                if (value < 0.0) {
                    result = 2.0 - result;
                }
            }
            else {
                result = Math.exp(-ysq) / y * (0.5 + chebylevSerie(8.0 / ysq - 1.0, RubyMath.ERFCC_COEF));
                if (value < 0.0) {
                    result = 2.0 - result;
                }
            }
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "erfc" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat erfc19(final IRubyObject recv, final IRubyObject x) {
        final double value = needFloat(x).getDoubleValue();
        final double y = Math.abs(value);
        double result;
        if (value <= -6.013687357) {
            result = 2.0;
        }
        else if (y < 1.49012E-8) {
            result = 1.0 - 2.0 * value / 1.772453850905516;
        }
        else {
            final double ysq = y * y;
            if (y < 1.0) {
                result = 1.0 - value * (1.0 + chebylevSerie(2.0 * ysq - 1.0, RubyMath.ERFC_COEF));
            }
            else if (y <= 4.0) {
                result = Math.exp(-ysq) / y * (0.5 + chebylevSerie((8.0 / ysq - 5.0) / 3.0, RubyMath.ERFC2_COEF));
                if (value < 0.0) {
                    result = 2.0 - result;
                }
                if (value < 0.0) {
                    result = 2.0 - result;
                }
                if (value < 0.0) {
                    result = 2.0 - result;
                }
            }
            else {
                result = Math.exp(-ysq) / y * (0.5 + chebylevSerie(8.0 / ysq - 1.0, RubyMath.ERFCC_COEF));
                if (value < 0.0) {
                    result = 2.0 - result;
                }
            }
        }
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "gamma" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyFloat gamma(final IRubyObject recv, final IRubyObject x) {
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        double result = nemes_gamma(value);
        if (Double.isInfinite(result)) {
            if (value < 0.0) {
                result = Double.NaN;
            }
            else {
                result = Double.POSITIVE_INFINITY;
            }
        }
        if (Double.isNaN(value)) {
            return RubyFloat.newFloat(recv.getRuntime(), Double.NaN);
        }
        domainCheck19(recv, result, "gamma");
        return RubyFloat.newFloat(recv.getRuntime(), result);
    }
    
    @JRubyMethod(name = { "lgamma" }, required = 1, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static RubyArray lgamma(final IRubyObject recv, final IRubyObject x) {
        final Ruby runtime = recv.getRuntime();
        final double value = RubyKernel.new_float(recv, x).getDoubleValue();
        if (value < 0.0 && Double.isInfinite(value)) {
            throw recv.getRuntime().newMathDomainError("lgamma");
        }
        final NemesLogGamma l = new NemesLogGamma(value);
        final IRubyObject[] ary = { RubyFloat.newFloat(runtime, l.value), RubyNumeric.int2fix(runtime, (int)l.sign) };
        return RubyArray.newArray(recv.getRuntime(), ary);
    }
    
    private static double nemes_gamma(final double x) {
        final double int_part = (int)x;
        if (x - int_part == 0.0 && 0.0 < int_part && int_part <= RubyMath.FACTORIAL.length) {
            return RubyMath.FACTORIAL[(int)int_part - 1];
        }
        final NemesLogGamma l = new NemesLogGamma(x);
        return l.sign * Math.exp(l.value);
    }
    
    static {
        ASINH_COEF = new double[] { -0.12820039911738187, -0.05881176118995177, 0.004727465432212481, -4.938363162653618E-4, 5.850620705855741E-5, -7.466998328931368E-6, 1.00116935835582E-6, -1.3903543858708333E-7, 1.9823169483172795E-8, -2.8847468417848845E-9, 4.2672965467159937E-10, -6.397608465436636E-11, 9.699168608906471E-12, -1.4844276972043772E-12, 2.290373793902745E-13, -3.5588395132732646E-14, 5.563969408005679E-15, -8.746250959962468E-16, 1.381524884452669E-16, -2.1916688282900364E-17, 3.490465852482756E-18 };
        ATANH_COEF = new double[] { 0.0943951023931955, 0.04919843705578616, 0.002102593522455433, 1.0735544497761166E-4, 5.978267249293031E-6, 3.505062030889135E-7, 2.1263743437653402E-8, 1.3216945357155272E-9, 8.36587550117807E-11, 5.370503749311002E-12, 3.4866594701571077E-13, 2.284549509603433E-14, 1.508407105944793E-15, 1.0024188168041091E-16, 6.69867473816507E-18, 4.497954546494931E-19 };
        ERFC_COEF = new double[] { -0.049046121234691806, -0.14226120510371365, 0.010035582187599796, -5.768764699767485E-4, 2.741993125219606E-5, -1.1043175507344507E-6, 3.8488755420345036E-8, -1.1808582533875466E-9, 3.2334215826050907E-11, -7.991015947004549E-13, 1.7990725113961456E-14, -3.718635487818693E-16, 7.103599003714253E-18, -1.2612455119155226E-19 };
        ERFC2_COEF = new double[] { -0.0696013466023095, -0.04110133936262089, 0.003914495866689627, -4.906395650548979E-4, 7.157479001377036E-5, -1.1530716341312328E-5, 1.9946705902019974E-6, -3.642666471599223E-7, 6.944372610005012E-8, -1.371220902104366E-8, 2.7883896610071373E-9, -5.814164724331161E-10, 1.2389204917527532E-10, -2.6906391453067435E-11, 5.942614350847911E-12, -1.3323867357581197E-12, 3.0280468061771323E-13, -6.966648814941033E-14, 1.620854541053923E-14, -3.809934465250492E-15, 9.040487815978831E-16, -2.1640061950896072E-16, 5.222102233995855E-17, -1.2697296023645554E-17, 3.1091455042761977E-18, -7.663762920320386E-19, 1.9008192513627452E-19 };
        ERFCC_COEF = new double[] { 0.07151793102029248, -0.026532434337606717, 0.0017111539779208558, -1.6375166345851787E-4, 1.9871293500552038E-5, -2.843712412766555E-6, 4.6061613089631305E-7, -8.227753025879209E-8, 1.5921418727709012E-8, -3.295071362252843E-9, 7.223439760400556E-10, -1.6648558133987297E-10, 4.010392588237665E-11, -1.004816214425731E-11, 2.608275913300334E-12, -6.991110560404025E-13, 1.9294923332617072E-13, -5.470131188754331E-14, 1.5896633097626975E-14, -4.726893980197555E-15, 1.4358733767849847E-15, -4.449510561817358E-16, 1.4048108847682335E-16, -4.5138183877642106E-17, 1.474521541045133E-17, -4.8926214069457765E-18, 1.6476121414106467E-18, -5.626817176329408E-19, 1.9474433822320786E-19 };
        FACTORIAL = new double[] { 1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0, 3628800.0, 3.99168E7, 4.790016E8, 6.2270208E9, 8.71782912E10, 1.307674368E12, 2.0922789888E13, 3.55687428096E14, 6.402373705728E15, 1.21645100408832E17, 2.43290200817664E18, 5.109094217170944E19, 1.1240007277776077E21 };
        NEMES_GAMMA_COEFF = new double[] { 1.0, 0.0, 0.08333333333333333, 0.0, 6.944444444444445E-4, 0.0, 6.586199294532628E-4, 0.0, -5.328781782774838E-4, 0.0, 7.927858870060838E-4, 0.0, -0.0018475818932203302, 0.0, 0.006250678247849418, 0.0, -0.02901710246301151, 0.0, 0.1771845724249131, 0.0, -1.3774768170399354 };
    }
    
    private static class NemesLogGamma
    {
        double value;
        double sign;
        
        private NemesLogGamma(final double x) {
            this.sign = 1.0;
            if (Double.isInfinite(x)) {
                this.value = Double.POSITIVE_INFINITY;
                return;
            }
            if (Double.isNaN(x)) {
                this.value = Double.NaN;
                return;
            }
            final double int_part = (int)x;
            this.sign = ((int_part % 2.0 == 0.0 && x - int_part != 0.0 && x < 0.0) ? -1.0 : 1.0);
            if (x - int_part == 0.0 && 0.0 < int_part && int_part <= RubyMath.FACTORIAL.length) {
                this.value = Math.log(RubyMath.FACTORIAL[(int)int_part - 1]);
            }
            else if (x < 10.0) {
                double rising_factorial = 1.0;
                for (int i = 0; i < (int)Math.abs(x) - int_part + 10.0; ++i) {
                    rising_factorial *= x + i;
                }
                final NemesLogGamma l = new NemesLogGamma(x + (int)Math.abs(x) - int_part + 10.0);
                this.value = l.value - Math.log(Math.abs(rising_factorial));
            }
            else {
                double temp = 0.0;
                for (int i = 0; i < RubyMath.NEMES_GAMMA_COEFF.length; ++i) {
                    temp += RubyMath.NEMES_GAMMA_COEFF[i] * 1.0 / Math.pow(x, i);
                }
                this.value = x * (Math.log(x) - 1.0 + Math.log(temp)) + (Math.log(2.0) + Math.log(3.141592653589793) - Math.log(x)) / 2.0;
            }
        }
    }
}
