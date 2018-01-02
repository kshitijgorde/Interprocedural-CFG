// 
// Decompiled by Procyon v0.5.30
// 

class Complex
{
    public double re;
    public double im;
    
    public Complex() {
        this.re = 0.0;
        this.im = 0.0;
    }
    
    public Complex(final Complex complex) {
        this.re = complex.re;
        this.im = complex.im;
    }
    
    public Complex(double re, double im) {
        if (re == -0.0) {
            re = 0.0;
        }
        if (im == -0.0) {
            im = 0.0;
        }
        this.re = re;
        this.im = im;
    }
    
    public static Complex Number(final double n, final double n2) {
        return new Complex(n, n2);
    }
    
    public static Complex Number(final double n) {
        return new Complex(n, 0.0);
    }
    
    public static Complex Number() {
        return new Complex(0.0, 0.0);
    }
    
    public static Complex Number(final Complex complex) {
        return new Complex(complex.re, complex.im);
    }
    
    public static boolean IsNaN(final Complex complex) {
        return Double.isNaN(complex.re) || Double.isNaN(complex.im);
    }
    
    public static boolean IsInfinite(final Complex complex) {
        return Double.isInfinite(complex.re) || Double.isInfinite(complex.im);
    }
    
    public static boolean IsDefined(final Complex complex) {
        return !IsNaN(complex) && !IsInfinite(complex);
    }
    
    public static Complex Add(final Complex complex, final Complex complex2) {
        return new Complex(complex.re + complex2.re, complex.im + complex2.im);
    }
    
    public static Complex Sub(final Complex complex, final Complex complex2) {
        return new Complex(complex.re - complex2.re, complex.im - complex2.im);
    }
    
    public static Complex Neg(final Complex complex) {
        return new Complex(-complex.re, -complex.im);
    }
    
    public static Complex Mul(final Complex complex, final Complex complex2) {
        double n;
        double n2;
        if (complex.im == 0.0 && complex2.im == 0.0) {
            n = complex.re * complex2.re;
            n2 = 0.0;
        }
        else {
            n = complex.re * complex2.re - complex.im * complex2.im;
            n2 = complex.re * complex2.im + complex.im * complex2.re;
        }
        return new Complex(n, n2);
    }
    
    public static Complex Mul(final Complex complex, final double n) {
        return new Complex(complex.re * n, complex.im * n);
    }
    
    public static Complex Muli(final Complex complex, final double n) {
        return new Complex(-complex.im * n, complex.re * n);
    }
    
    public static Complex Div(final Complex complex, final Complex complex2) {
        double n;
        double n2;
        if (complex.im == 0.0 && complex2.im == 0.0) {
            n = complex.re / complex2.re;
            n2 = 0.0;
        }
        else {
            final double dot = Dot(complex2, complex2);
            n = (complex.re * complex2.re + complex.im * complex2.im) / dot;
            n2 = (complex.im * complex2.re - complex.re * complex2.im) / dot;
        }
        return new Complex(n, n2);
    }
    
    public static double Abs(final Complex complex) {
        return Math.sqrt(Dot(complex, complex));
    }
    
    public static double Arg(final Complex complex) {
        return Math.atan2(complex.im, complex.re);
    }
    
    public static Complex Conj(final Complex complex) {
        return new Complex(complex.re, -complex.im);
    }
    
    public static Complex Int(final Complex complex) {
        double n;
        if (complex.re >= 0.0) {
            n = Math.floor(complex.re);
        }
        else {
            n = Math.ceil(complex.re);
        }
        double n2;
        if (complex.im >= 0.0) {
            n2 = Math.floor(complex.im);
        }
        else {
            n2 = Math.ceil(complex.im);
        }
        return new Complex(n, n2);
    }
    
    public static Complex Ceil(final Complex complex) {
        return new Complex(Math.ceil(complex.re), Math.ceil(complex.im));
    }
    
    public static Complex Floor(final Complex complex) {
        return new Complex(Math.floor(complex.re), Math.floor(complex.im));
    }
    
    public static Complex Rem(final Complex complex, final Complex complex2) {
        return new Complex(Math.IEEEremainder(complex.re, complex2.re), 0.0);
    }
    
    public static Complex Mod(final Complex complex, final Complex complex2) {
        final double n = complex.re / complex2.re;
        return new Complex(complex2.re * (n - Math.floor(n)), 0.0);
    }
    
    public static Complex Sqrt(final Complex complex) {
        final double sqrt = Math.sqrt((Abs(complex) + complex.re) * 0.5);
        double sqrt2;
        if (sqrt == 0.0) {
            sqrt2 = Math.sqrt(-complex.re);
        }
        else {
            sqrt2 = 0.5 * complex.im / sqrt;
        }
        return new Complex(sqrt, sqrt2);
    }
    
    public static Complex Pow(final Complex complex, final Complex complex2) {
        double n;
        double n2;
        if (complex.re == 0.0 && complex.im == 0.0 && complex2.im == 0.0 && complex2.re > 0.0) {
            n = 0.0;
            n2 = 0.0;
        }
        else if (complex.im == 0.0 && complex.re > 0.0) {
            final double log = Math.log(complex.re);
            final double n3 = complex2.re * log;
            final double n4 = complex2.im * log;
            final double exp = Math.exp(n3);
            n = exp * Math.cos(n4);
            n2 = exp * Math.sin(n4);
        }
        else if (complex2.im == 0.0) {
            final double abs = Abs(complex);
            final double arg = Arg(complex);
            final double exp2 = Math.exp(complex2.re * Math.log(abs));
            n = exp2 * Math.cos(complex2.re * arg);
            n2 = exp2 * Math.sin(complex2.re * arg);
        }
        else {
            final double abs2 = Abs(complex);
            final double arg2 = Arg(complex);
            final double log2 = Math.log(abs2);
            final double exp3 = Math.exp(complex2.re * log2 - complex2.im * arg2);
            final double n5 = complex2.im * log2 + complex2.re * arg2;
            n = exp3 * Math.cos(n5);
            n2 = exp3 * Math.sin(n5);
        }
        return new Complex(n, n2);
    }
    
    public static Complex Exp(final Complex complex) {
        final double exp = Math.exp(complex.re);
        return new Complex(exp * Math.cos(complex.im), exp * Math.sin(complex.im));
    }
    
    public static Complex Log(final Complex complex) {
        return new Complex(Math.log(Dot(complex, complex)) * 0.5, Arg(complex));
    }
    
    public static Complex Sin(final Complex complex) {
        final double exp = Math.exp(complex.im);
        final double n = exp / 2.0;
        final double n2 = 0.5 / exp;
        return new Complex(Math.sin(complex.re) * (n + n2), Math.cos(complex.re) * (n - n2));
    }
    
    public static Complex Cos(final Complex complex) {
        final double exp = Math.exp(complex.im);
        final double n = exp / 2.0;
        final double n2 = 0.5 / exp;
        return new Complex(Math.cos(complex.re) * (n + n2), Math.sin(complex.re) * -(n - n2));
    }
    
    public static Complex Tan(final Complex complex) {
        return Div(Sin(complex), Cos(complex));
    }
    
    public static Complex Cot(final Complex complex) {
        return Div(Cos(complex), Sin(complex));
    }
    
    public static Complex Sec(final Complex complex) {
        return Div(Number(1.0, 0.0), Cos(complex));
    }
    
    public static Complex Csc(final Complex complex) {
        return Div(Number(1.0, 0.0), Sin(complex));
    }
    
    public static Complex Sinh(final Complex complex) {
        final double exp = Math.exp(complex.re);
        final double n = exp / 2.0;
        final double n2 = 0.5 / exp;
        return new Complex(Math.cos(complex.im) * (n - n2), Math.sin(complex.im) * (n + n2));
    }
    
    public static Complex Cosh(final Complex complex) {
        final double exp = Math.exp(complex.re);
        final double n = exp / 2.0;
        final double n2 = 0.5 / exp;
        return new Complex(Math.cos(complex.im) * (n + n2), Math.sin(complex.im) * (n - n2));
    }
    
    public static Complex Tanh(final Complex complex) {
        return Div(Sinh(complex), Cosh(complex));
    }
    
    public static Complex Coth(final Complex complex) {
        return Div(Cosh(complex), Sinh(complex));
    }
    
    public static Complex Sech(final Complex complex) {
        return Div(Number(1.0), Cosh(complex));
    }
    
    public static Complex Csch(final Complex complex) {
        return Div(Number(1.0), Sinh(complex));
    }
    
    public static Complex Asin(final Complex complex) {
        return Muli(Log(Add(Muli(complex, 1.0), Sqrt(Sub(Number(1.0), Mul(complex, complex))))), -1.0);
    }
    
    public static Complex Acos(final Complex complex) {
        return Muli(Log(Add(complex, Muli(Sqrt(Sub(Number(1.0), Mul(complex, complex))), 1.0))), -1.0);
    }
    
    public static Complex Atan(final Complex complex) {
        final Complex number = Number(0.0, 1.0);
        return Muli(Log(Div(Add(number, complex), Sub(number, complex))), 0.5);
    }
    
    public static Complex Asec(final Complex complex) {
        return Acos(Div(Number(1.0), complex));
    }
    
    public static Complex Acsc(final Complex complex) {
        return Asin(Div(Number(1.0), complex));
    }
    
    public static Complex Acot(final Complex complex) {
        final Complex number = Number(0.0, 1.0);
        return Muli(Log(Div(Add(number, complex), Sub(complex, number))), -0.5);
    }
    
    public static Complex Asinh(final Complex complex) {
        return Log(Add(complex, Sqrt(Add(Mul(complex, complex), Number(1.0)))));
    }
    
    public static Complex Acosh(final Complex complex) {
        return Log(Add(complex, Sqrt(Sub(Mul(complex, complex), Number(1.0)))));
    }
    
    public static Complex Atanh(final Complex complex) {
        final Complex number = Number(1.0);
        return Mul(Log(Div(Add(number, complex), Sub(number, complex))), 0.5);
    }
    
    public static Complex Asech(final Complex complex) {
        return Acosh(Div(Number(1.0), complex));
    }
    
    public static Complex Acsch(final Complex complex) {
        return Asinh(Div(Number(1.0), complex));
    }
    
    public static Complex Acoth(final Complex complex) {
        final Complex number = Number(1.0);
        return Mul(Log(Div(Add(number, complex), Sub(complex, number))), 0.5);
    }
    
    public static double Dot(final Complex complex, final Complex complex2) {
        double n = complex.re * complex2.re + complex.im * complex2.im;
        if (n == -0.0) {
            n = 0.0;
        }
        return n;
    }
    
    public static double Cross(final Complex complex, final Complex complex2) {
        double n = complex.re * complex2.im - complex.im * complex2.re;
        if (n == -0.0) {
            n = 0.0;
        }
        return n;
    }
    
    public static Complex Shadow(final Complex complex, final Complex complex2) {
        return Mul(complex2, Dot(complex, complex2) / Dot(complex2, complex2));
    }
}
