// 
// Decompiled by Procyon v0.5.30
// 

package sTools.graph;

public final class SpecialFunction
{
    private static final double MACHEP = 1.1102230246251565E-16;
    private static final double MAXLOG = 709.782712893384;
    private static final double MINLOG = -745.1332191019412;
    private static final double MAXGAM = 171.6243769563027;
    private static final double SQTPI = 2.5066282746310007;
    private static final double SQRTH = 0.7071067811865476;
    private static final double LOGPI = 1.1447298858494002;
    public static final double BOLTZMAN = 1.3807E-16;
    public static final double ECHARGE = 4.8032E-10;
    public static final double EMASS = 9.1095E-28;
    public static final double PMASS = 1.6726E-24;
    public static final double GRAV = 6.672E-8;
    public static final double PLANCK = 6.6262E-27;
    public static final double LIGHTSPEED = 2.9979E10;
    public static final double STEFANBOLTZ = 5.6703E-5;
    public static final double AVOGADRO = 6.022E23;
    public static final double GASCONSTANT = 8.3144E7;
    public static final double GRAVACC = 980.67;
    public static final double SOLARMASS = 1.99E33;
    public static final double SOLARRADIUS = 6.96E10;
    public static final double SOLARLUM = 3.9E33;
    public static final double SOLARFLUX = 6.41E10;
    public static final double AU = 1.5E13;
    
    public static double log10(final double n) throws ArithmeticException {
        if (n <= 0.0) {
            throw new ArithmeticException("range exception");
        }
        return Math.log(n) / 2.302585092994046;
    }
    
    public static double cosh(final double n) throws ArithmeticException {
        double abs = n;
        if (abs < 0.0) {
            abs = Math.abs(n);
        }
        final double exp = Math.exp(abs);
        return 0.5 * (exp + 1 / exp);
    }
    
    public static double sinh(final double n) throws ArithmeticException {
        if (n == 0.0) {
            return n;
        }
        double abs = n;
        if (abs < 0.0) {
            abs = Math.abs(n);
        }
        final double exp = Math.exp(abs);
        if (n < 0.0) {
            return -0.5 * (exp - 1 / exp);
        }
        return 0.5 * (exp - 1 / exp);
    }
    
    public static double tanh(final double n) throws ArithmeticException {
        if (n == 0.0) {
            return n;
        }
        double abs = n;
        if (abs < 0.0) {
            abs = Math.abs(n);
        }
        final double exp = Math.exp(2.0 * abs);
        if (n < 0.0) {
            return -(1.0 - 2.0 / (exp + 1.0));
        }
        return 1.0 - 2.0 / (exp + 1.0);
    }
    
    public static double acosh(final double n) throws ArithmeticException {
        if (n < 1.0) {
            throw new ArithmeticException("range exception");
        }
        return Math.log(n + Math.sqrt(n * n - 1));
    }
    
    public static double asinh(final double n) throws ArithmeticException {
        if (n == 0.0) {
            return n;
        }
        int n2;
        double n3;
        if (n < 0.0) {
            n2 = -1;
            n3 = -n;
        }
        else {
            n2 = 1;
            n3 = n;
        }
        return n2 * Math.log(n3 + Math.sqrt(n3 * n3 + 1));
    }
    
    public static double atanh(final double n) throws ArithmeticException {
        if (n > 1.0 || n < -1.0) {
            throw new ArithmeticException("range exception");
        }
        return 0.5 * Math.log((1.0 + n) / (1.0 - n));
    }
    
    public static double j0(final double n) throws ArithmeticException {
        final double abs;
        if ((abs = Math.abs(n)) < 8.0) {
            final double n2 = n * n;
            return (5.7568490574E10 + n2 * (-1.3362590354E10 + n2 * (6.516196407E8 + n2 * (-1.121442418E7 + n2 * (77392.33017 + n2 * -184.9052456))))) / (5.7568490411E10 + n2 * (1.029532985E9 + n2 * (9494680.718 + n2 * (59272.64853 + n2 * (267.8532712 + n2 * 1.0)))));
        }
        final double n3 = 8.0 / abs;
        final double n4 = n3 * n3;
        final double n5 = abs - 0.785398164;
        return Math.sqrt(0.636619772 / abs) * (Math.cos(n5) * (1.0 + n4 * (-0.001098628627 + n4 * (2.734510407E-5 + n4 * (-2.073370639E-6 + n4 * 2.093887211E-7)))) - n3 * Math.sin(n5) * (-0.01562499995 + n4 * (1.430488765E-4 + n4 * (-6.911147651E-6 + n4 * (7.621095161E-7 - n4 * 9.34935152E-8)))));
    }
    
    public static double j1(final double n) throws ArithmeticException {
        final double abs;
        if ((abs = Math.abs(n)) < 8.0) {
            final double n2 = n * n;
            return n * (7.2362614232E10 + n2 * (-7.895059235E9 + n2 * (2.423968531E8 + n2 * (-2972611.439 + n2 * (15704.4826 + n2 * -30.16036606))))) / (1.44725228442E11 + n2 * (2.300535178E9 + n2 * (1.858330474E7 + n2 * (99447.43394 + n2 * (376.9991397 + n2 * 1.0)))));
        }
        final double n3 = 8.0 / abs;
        final double n4 = abs - 2.356194491;
        final double n5 = n3 * n3;
        double n6 = Math.sqrt(0.636619772 / abs) * (Math.cos(n4) * (1.0 + n5 * (0.00183105 + n5 * (-3.516396496E-5 + n5 * (2.457520174E-6 + n5 * -2.40337019E-7)))) - n3 * Math.sin(n4) * (0.04687499995 + n5 * (-2.002690873E-4 + n5 * (8.449199096E-6 + n5 * (-8.8228987E-7 + n5 * 1.05787412E-7)))));
        if (n < 0.0) {
            n6 = -n6;
        }
        return n6;
    }
    
    public static double jn(final int n, final double n2) throws ArithmeticException {
        final double n3 = 40.0;
        final double n4 = 1.0E10;
        final double n5 = 1.0E-10;
        if (n == 0) {
            return j0(n2);
        }
        if (n == 1) {
            return j1(n2);
        }
        final double abs = Math.abs(n2);
        if (abs == 0.0) {
            return 0.0;
        }
        double n8;
        if (abs > n) {
            final double n6 = 2.0 / abs;
            double j0 = j0(abs);
            double j2 = j1(abs);
            for (int i = 1; i < n; ++i) {
                final double n7 = i * n6 * j2 - j0;
                j0 = j2;
                j2 = n7;
            }
            n8 = j2;
        }
        else {
            final double n9 = 2.0 / abs;
            final int n10 = 2 * ((n + (int)Math.sqrt(n3 * n)) / 2);
            boolean b = false;
            double n13;
            double n12;
            double n11 = n12 = (n13 = 0.0);
            double n14 = 1.0;
            for (int k = n10; k > 0; --k) {
                final double n15 = k * n9 * n14 - n12;
                n12 = n14;
                n14 = n15;
                if (Math.abs(n14) > n4) {
                    n14 *= n5;
                    n12 *= n5;
                    n11 *= n5;
                    n13 *= n5;
                }
                if (b) {
                    n13 += n14;
                }
                b = !b;
                if (k == n) {
                    n11 = n12;
                }
            }
            n8 = n11 / (2.0 * n13 - n14);
        }
        return (n2 < 0.0 && n % 2 == 1) ? (-n8) : n8;
    }
    
    public static double y0(final double n) throws ArithmeticException {
        if (n < 8.0) {
            final double n2 = n * n;
            return (-2.957821389E9 + n2 * (7.062834065E9 + n2 * (-5.123598036E8 + n2 * (1.087988129E7 + n2 * (-86327.92757 + n2 * 228.4622733))))) / (4.0076544269E10 + n2 * (7.452499648E8 + n2 * (7189466.438 + n2 * (47447.2647 + n2 * (226.1030244 + n2 * 1.0))))) + 0.636619772 * j0(n) * Math.log(n);
        }
        final double n3 = 8.0 / n;
        final double n4 = n3 * n3;
        final double n5 = n - 0.785398164;
        return Math.sqrt(0.636619772 / n) * (Math.sin(n5) * (1.0 + n4 * (-0.001098628627 + n4 * (2.734510407E-5 + n4 * (-2.073370639E-6 + n4 * 2.093887211E-7)))) + n3 * Math.cos(n5) * (-0.01562499995 + n4 * (1.430488765E-4 + n4 * (-6.911147651E-6 + n4 * (7.621095161E-7 + n4 * -9.34945152E-8)))));
    }
    
    public static double y1(final double n) throws ArithmeticException {
        if (n < 8.0) {
            final double n2 = n * n;
            return n * (-4.900604943E12 + n2 * (1.27527439E12 + n2 * (-5.153438139E10 + n2 * (7.349264551E8 + n2 * (-4237922.726 + n2 * 8511.937935))))) / (2.49958057E13 + n2 * (4.244419664E11 + n2 * (3.733650367E9 + n2 * (2.245904002E7 + n2 * (102042.605 + n2 * (354.9632885 + n2)))))) + 0.636619772 * (j1(n) * Math.log(n) - 1.0 / n);
        }
        final double n3 = 8.0 / n;
        final double n4 = n3 * n3;
        final double n5 = n - 2.356194491;
        return Math.sqrt(0.636619772 / n) * (Math.sin(n5) * (1.0 + n4 * (0.00183105 + n4 * (-3.516396496E-5 + n4 * (2.457520174E-6 + n4 * -2.40337019E-7)))) + n3 * Math.cos(n5) * (0.04687499995 + n4 * (-2.002690873E-4 + n4 * (8.449199096E-6 + n4 * (-8.8228987E-7 + n4 * 1.05787412E-7)))));
    }
    
    public static double yn(final int n, final double n2) throws ArithmeticException {
        if (n == 0) {
            return y0(n2);
        }
        if (n == 1) {
            return y1(n2);
        }
        final double n3 = 2.0 / n2;
        double y1 = y1(n2);
        double y2 = y0(n2);
        for (int i = 1; i < n; ++i) {
            final double n4 = i * n3 * y1 - y2;
            y2 = y1;
            y1 = n4;
        }
        return y1;
    }
    
    public static double fac(final double n) throws ArithmeticException {
        final double abs = Math.abs(n);
        if (Math.floor(abs) == abs) {
            return fac((int)n);
        }
        return gamma(n + 1.0);
    }
    
    public static int fac(final int n) throws ArithmeticException {
        int i = n;
        int n2 = 1;
        if (n < 0) {
            i = Math.abs(n);
        }
        while (i > 1) {
            n2 *= i--;
        }
        if (n < 0) {
            return -n2;
        }
        return n2;
    }
    
    public static double gamma(double n) throws ArithmeticException {
        final double[] array = { 1.6011952247675185E-4, 0.0011913514700658638, 0.010421379756176156, 0.04763678004571372, 0.20744822764843598, 0.4942148268014971, 1.0 };
        final double[] array2 = { -2.3158187332412014E-5, 5.396055804933034E-4, -0.004456419138517973, 0.011813978522206043, 0.035823639860549865, -0.23459179571824335, 0.0714304917030273, 1.0 };
        final double abs = Math.abs(n);
        if (abs > 33.0) {
            if (n >= 0.0) {
                return stirf(n);
            }
            final double floor = Math.floor(abs);
            if (floor == abs) {
                throw new ArithmeticException("gamma: overflow");
            }
            final int n2 = (int)floor;
            double n3 = abs - floor;
            if (n3 > 0.5) {
                n3 = abs - (floor + 1.0);
            }
            final double n4 = abs * Math.sin(3.141592653589793 * n3);
            if (n4 == 0.0) {
                throw new ArithmeticException("gamma: overflow");
            }
            return -(3.141592653589793 / (Math.abs(n4) * stirf(abs)));
        }
        else {
            double n5;
            for (n5 = 1.0; n >= 3.0; --n, n5 *= n) {}
            while (n < 0.0) {
                if (n == 0.0) {
                    throw new ArithmeticException("gamma: singular");
                }
                if (n > -1.0E-9) {
                    return n5 / ((1.0 + 0.5772156649015329 * n) * n);
                }
                n5 /= n;
                ++n;
            }
            while (n < 2.0) {
                if (n == 0.0) {
                    throw new ArithmeticException("gamma: singular");
                }
                if (n < 1.0E-9) {
                    return n5 / ((1.0 + 0.5772156649015329 * n) * n);
                }
                n5 /= n;
                ++n;
            }
            if (n == 2.0 || n == 3.0) {
                return n5;
            }
            n -= 2.0;
            return n5 * polevl(n, array, 6) / polevl(n, array2, 7);
        }
    }
    
    private static double stirf(final double n) throws ArithmeticException {
        final double[] array = { 7.873113957930937E-4, -2.2954996161337813E-4, -0.0026813261780578124, 0.0034722222160545866, 0.08333333333334822 };
        final double n2 = 143.01608;
        final double n3 = 1.0 / n;
        final double exp = Math.exp(n);
        final double n4 = 1.0 + n3 * polevl(n3, array, 4);
        double n5;
        if (n > n2) {
            final double pow = Math.pow(n, 0.5 * n - 0.25);
            n5 = pow * (pow / exp);
        }
        else {
            n5 = Math.pow(n, n - 0.5) / exp;
        }
        return 2.5066282746310007 * n5 * n4;
    }
    
    public static double igamc(final double n, final double n2) throws ArithmeticException {
        final double n3 = 4.503599627370496E15;
        final double n4 = 2.220446049250313E-16;
        if (n2 <= 0 || n <= 0) {
            return 1.0;
        }
        if (n2 < 1.0 || n2 < n) {
            return 1.0 - igam(n, n2);
        }
        final double n5 = n * Math.log(n2) - n2 - lgamma(n);
        if (n5 < -709.782712893384) {
            return 0.0;
        }
        final double exp = Math.exp(n5);
        double n6 = 1.0 - n;
        double n7 = n2 + n6 + 1.0;
        double n8 = 0.0;
        double n9 = 1.0;
        double n10 = n2;
        double n11 = n2 + 1.0;
        double n12 = n7 * n2;
        double n13 = n11 / n12;
        double abs;
        do {
            ++n8;
            ++n6;
            n7 += 2.0;
            final double n14 = n6 * n8;
            final double n15 = n11 * n7 - n9 * n14;
            final double n16 = n12 * n7 - n10 * n14;
            if (n16 != 0) {
                final double n17 = n15 / n16;
                abs = Math.abs((n13 - n17) / n17);
                n13 = n17;
            }
            else {
                abs = 1.0;
            }
            n9 = n11;
            n11 = n15;
            n10 = n12;
            n12 = n16;
            if (Math.abs(n15) > n3) {
                n9 *= n4;
                n11 *= n4;
                n10 *= n4;
                n12 *= n4;
            }
        } while (abs > 1.1102230246251565E-16);
        return n13 * exp;
    }
    
    public static double igam(final double n, final double n2) throws ArithmeticException {
        if (n2 <= 0 || n <= 0) {
            return 0.0;
        }
        if (n2 > 1.0 && n2 > n) {
            return 1.0 - igamc(n, n2);
        }
        final double n3 = n * Math.log(n2) - n2 - lgamma(n);
        if (n3 < -709.782712893384) {
            return 0.0;
        }
        final double exp = Math.exp(n3);
        double n4 = n;
        double n5 = 1.0;
        double n6 = 1.0;
        do {
            ++n4;
            n5 *= n2 / n4;
            n6 += n5;
        } while (n5 / n6 > 1.1102230246251565E-16);
        return n6 * exp / n;
    }
    
    public static double chisq(final double n, final double n2) throws ArithmeticException {
        if (n2 < 0.0 || n < 1.0) {
            return 0.0;
        }
        return igam(n / 2.0, n2 / 2.0);
    }
    
    public static double chisqc(final double n, final double n2) throws ArithmeticException {
        if (n2 < 0.0 || n < 1.0) {
            return 0.0;
        }
        return igamc(n / 2.0, n2 / 2.0);
    }
    
    public static double poisson(final int n, final double n2) throws ArithmeticException {
        if (n < 0 || n2 < 0) {
            return 0.0;
        }
        return igamc(n + 1, n2);
    }
    
    public static double poissonc(final int n, final double n2) throws ArithmeticException {
        if (n < 0 || n2 < 0) {
            return 0.0;
        }
        return igam(n + 1, n2);
    }
    
    public static double normal(final double n) throws ArithmeticException {
        final double n2 = n * 0.7071067811865476;
        final double abs = Math.abs(n2);
        double n3;
        if (abs < 0.7071067811865476) {
            n3 = 0.5 + 0.5 * erf(n2);
        }
        else {
            n3 = 0.5 * erfc(abs);
            if (n2 > 0) {
                n3 = 1.0 - n3;
            }
        }
        return n3;
    }
    
    public static double erfc(final double n) throws ArithmeticException {
        final double[] array = { 2.461969814735305E-10, 0.5641895648310689, 7.463210564422699, 48.63719709856814, 196.5208329560771, 526.4451949954773, 934.5285271719576, 1027.5518868951572, 557.5353353693994 };
        final double[] array2 = { 13.228195115474499, 86.70721408859897, 354.9377788878199, 975.7085017432055, 1823.9091668790973, 2246.3376081871097, 1656.6630919416134, 557.5353408177277 };
        final double[] array3 = { 0.5641895835477551, 1.275366707599781, 5.019050422511805, 6.160210979930536, 7.4097426995044895, 2.9788666537210022 };
        final double[] array4 = { 2.2605286322011726, 9.396035249380015, 12.048953980809666, 17.08144507475659, 9.608968090632859, 3.369076451000815 };
        double n2;
        if (n < 0.0) {
            n2 = -n;
        }
        else {
            n2 = n;
        }
        if (n2 < 1.0) {
            return 1.0 - erf(n);
        }
        final double n3 = -n * n;
        if (n3 < -709.782712893384) {
            if (n < 0) {
                return 2.0;
            }
            return 0.0;
        }
        else {
            final double exp = Math.exp(n3);
            double n4;
            double n5;
            if (n2 < 8.0) {
                n4 = polevl(n2, array, 8);
                n5 = p1evl(n2, array2, 8);
            }
            else {
                n4 = polevl(n2, array3, 5);
                n5 = p1evl(n2, array4, 6);
            }
            double n6 = exp * n4 / n5;
            if (n < 0) {
                n6 = 2.0 - n6;
            }
            if (n6 != 0.0) {
                return n6;
            }
            if (n < 0) {
                return 2.0;
            }
            return 0.0;
        }
    }
    
    public static double erf(final double n) throws ArithmeticException {
        final double[] array = { 9.604973739870516, 90.02601972038427, 2232.005345946843, 7003.325141128051, 55592.30130103949 };
        final double[] array2 = { 33.56171416475031, 521.3579497801527, 4594.323829709801, 22629.000061389095, 49267.39426086359 };
        if (Math.abs(n) > 1.0) {
            return 1.0 - erfc(n);
        }
        final double n2 = n * n;
        return n * polevl(n2, array, 4) / p1evl(n2, array2, 5);
    }
    
    private static double polevl(final double n, final double[] array, final int n2) throws ArithmeticException {
        double n3 = array[0];
        for (int i = 1; i <= n2; ++i) {
            n3 = n3 * n + array[i];
        }
        return n3;
    }
    
    private static double p1evl(final double n, final double[] array, final int n2) throws ArithmeticException {
        double n3 = n + array[0];
        for (int i = 1; i < n2; ++i) {
            n3 = n3 * n + array[i];
        }
        return n3;
    }
    
    private static double lgamma(double n) throws ArithmeticException {
        final double[] array = { 8.116141674705085E-4, -5.950619042843014E-4, 7.936503404577169E-4, -0.002777777777300997, 0.08333333333333319 };
        final double[] array2 = { -1378.2515256912086, -38801.631513463784, -331612.9927388712, -1162370.974927623, -1721737.0082083966, -853555.6642457654 };
        final double[] array3 = { -351.81570143652345, -17064.210665188115, -220528.59055385445, -1139334.4436798252, -2532523.0717758294, -2018891.4143353277 };
        if (n < -34.0) {
            final double n2 = -n;
            final double lgamma = lgamma(n2);
            final double floor = Math.floor(n2);
            if (floor == n2) {
                throw new ArithmeticException("lgam: Overflow");
            }
            double n3 = n2 - floor;
            if (n3 > 0.5) {
                n3 = floor + 1.0 - n2;
            }
            final double n4 = n2 * Math.sin(3.141592653589793 * n3);
            if (n4 == 0.0) {
                throw new ArithmeticException("lgamma: Overflow");
            }
            return 1.1447298858494002 - Math.log(n4) - lgamma;
        }
        else if (n < 13.0) {
            double n5;
            for (n5 = 1.0; n >= 3.0; --n, n5 *= n) {}
            while (n < 2.0) {
                if (n == 0.0) {
                    throw new ArithmeticException("lgamma: Overflow");
                }
                n5 /= n;
                ++n;
            }
            if (n5 < 0.0) {
                n5 = -n5;
            }
            if (n == 2.0) {
                return Math.log(n5);
            }
            n -= 2.0;
            return Math.log(n5) + n * polevl(n, array2, 5) / p1evl(n, array3, 6);
        }
        else {
            if (n > 2.556348E305) {
                throw new ArithmeticException("lgamma: Overflow");
            }
            final double n6 = (n - 0.5) * Math.log(n) - n + 0.9189385332046728;
            if (n > 1.0E8) {
                return n6;
            }
            final double n7 = 1.0 / (n * n);
            double n8;
            if (n >= 1000.0) {
                n8 = n6 + ((7.936507936507937E-4 * n7 - 0.002777777777777778) * n7 + 0.08333333333333333) / n;
            }
            else {
                n8 = n6 + polevl(n7, array, 4) / n;
            }
            return n8;
        }
    }
    
    public static double ibeta(final double n, final double n2, final double n3) throws ArithmeticException {
        if (n <= 0.0 || n2 <= 0.0) {
            throw new ArithmeticException("ibeta: Domain error!");
        }
        if (n3 <= 0.0 || n3 >= 1.0) {
            if (n3 == 0.0) {
                return 0.0;
            }
            if (n3 == 1.0) {
                return 1.0;
            }
            throw new ArithmeticException("ibeta: Domain error!");
        }
        else {
            boolean b = false;
            if (n2 * n3 <= 1.0 && n3 <= 0.95) {
                return pseries(n, n2, n3);
            }
            final double n4 = 1.0 - n3;
            double n5;
            double n6;
            double n7;
            double n8;
            if (n3 > n / (n + n2)) {
                b = true;
                n5 = n2;
                n6 = n;
                n7 = n3;
                n8 = n4;
            }
            else {
                n5 = n;
                n6 = n2;
                n7 = n4;
                n8 = n3;
            }
            if (b && n6 * n8 <= 1.0 && n8 <= 0.95) {
                final double pseries = pseries(n5, n6, n8);
                double n9;
                if (pseries <= 1.1102230246251565E-16) {
                    n9 = 0.9999999999999999;
                }
                else {
                    n9 = 1.0 - pseries;
                }
                return n9;
            }
            double incbcf;
            if (n8 * (n5 + n6 - 2.0) - (n5 - 1.0) < 0.0) {
                incbcf = incbcf(n5, n6, n8);
            }
            else {
                incbcf = incbd(n5, n6, n8) / n7;
            }
            final double n10 = n5 * Math.log(n8);
            final double n11 = n6 * Math.log(n7);
            if (n5 + n6 < 171.6243769563027 && Math.abs(n10) < 709.782712893384 && Math.abs(n11) < 709.782712893384) {
                double n12 = Math.pow(n7, n6) * Math.pow(n8, n5) / n5 * incbcf * (gamma(n5 + n6) / (gamma(n5) * gamma(n6)));
                if (b) {
                    if (n12 <= 1.1102230246251565E-16) {
                        n12 = 0.9999999999999999;
                    }
                    else {
                        n12 = 1.0 - n12;
                    }
                }
                return n12;
            }
            final double n13 = n10 + (n11 + lgamma(n5 + n6) - lgamma(n5) - lgamma(n6)) + Math.log(incbcf / n5);
            double exp;
            if (n13 < -745.1332191019412) {
                exp = 0.0;
            }
            else {
                exp = Math.exp(n13);
            }
            if (b) {
                if (exp <= 1.1102230246251565E-16) {
                    exp = 0.9999999999999999;
                }
                else {
                    exp = 1.0 - exp;
                }
            }
            return exp;
        }
    }
    
    private static double incbcf(final double n, final double n2, final double n3) throws ArithmeticException {
        final double n4 = 4.503599627370496E15;
        final double n5 = 2.220446049250313E-16;
        double n6 = n;
        double n7 = n + n2;
        double n8 = n;
        double n9 = n + 1.0;
        double n10 = 1.0;
        double n11 = n2 - 1.0;
        double n12 = n9;
        double n13 = n + 2.0;
        double n14 = 0.0;
        double n15 = 1.0;
        double n16 = 1.0;
        double n17 = 1.0;
        double n18 = 1.0;
        double n19 = 1.0;
        int n20 = 0;
        final double n21 = 3.3306690738754696E-16;
        do {
            final double n22 = -(n3 * n6 * n7) / (n8 * n9);
            final double n23 = n16 + n14 * n22;
            final double n24 = n17 + n15 * n22;
            final double n25 = n16;
            final double n26 = n23;
            final double n27 = n17;
            final double n28 = n24;
            final double n29 = n3 * n10 * n11 / (n12 * n13);
            final double n30 = n26 + n25 * n29;
            final double n31 = n28 + n27 * n29;
            n14 = n26;
            n16 = n30;
            n15 = n28;
            n17 = n31;
            if (n31 != 0) {
                n19 = n30 / n31;
            }
            double abs;
            if (n19 != 0) {
                abs = Math.abs((n18 - n19) / n19);
                n18 = n19;
            }
            else {
                abs = 1.0;
            }
            if (abs < n21) {
                return n18;
            }
            ++n6;
            ++n7;
            n8 += 2.0;
            n9 += 2.0;
            ++n10;
            --n11;
            n12 += 2.0;
            n13 += 2.0;
            if (Math.abs(n31) + Math.abs(n30) > n4) {
                n14 *= n5;
                n16 *= n5;
                n15 *= n5;
                n17 *= n5;
            }
            if (Math.abs(n31) >= n5 && Math.abs(n30) >= n5) {
                continue;
            }
            n14 *= n4;
            n16 *= n4;
            n15 *= n4;
            n17 *= n4;
        } while (++n20 < 300);
        return n18;
    }
    
    private static double incbd(final double n, final double n2, final double n3) throws ArithmeticException {
        final double n4 = 4.503599627370496E15;
        final double n5 = 2.220446049250313E-16;
        double n6 = n;
        double n7 = n2 - 1.0;
        double n8 = n;
        double n9 = n + 1.0;
        double n10 = 1.0;
        double n11 = n + n2;
        double n12 = n + 1.0;
        double n13 = n + 2.0;
        double n14 = 0.0;
        double n15 = 1.0;
        double n16 = 1.0;
        double n17 = 1.0;
        final double n18 = n3 / (1.0 - n3);
        double n19 = 1.0;
        double n20 = 1.0;
        int n21 = 0;
        final double n22 = 3.3306690738754696E-16;
        do {
            final double n23 = -(n18 * n6 * n7) / (n8 * n9);
            final double n24 = n16 + n14 * n23;
            final double n25 = n17 + n15 * n23;
            final double n26 = n16;
            final double n27 = n24;
            final double n28 = n17;
            final double n29 = n25;
            final double n30 = n18 * n10 * n11 / (n12 * n13);
            final double n31 = n27 + n26 * n30;
            final double n32 = n29 + n28 * n30;
            n14 = n27;
            n16 = n31;
            n15 = n29;
            n17 = n32;
            if (n32 != 0) {
                n20 = n31 / n32;
            }
            double abs;
            if (n20 != 0) {
                abs = Math.abs((n19 - n20) / n20);
                n19 = n20;
            }
            else {
                abs = 1.0;
            }
            if (abs < n22) {
                return n19;
            }
            ++n6;
            --n7;
            n8 += 2.0;
            n9 += 2.0;
            ++n10;
            ++n11;
            n12 += 2.0;
            n13 += 2.0;
            if (Math.abs(n32) + Math.abs(n31) > n4) {
                n14 *= n5;
                n16 *= n5;
                n15 *= n5;
                n17 *= n5;
            }
            if (Math.abs(n32) >= n5 && Math.abs(n31) >= n5) {
                continue;
            }
            n14 *= n4;
            n16 *= n4;
            n15 *= n4;
            n17 *= n4;
        } while (++n21 < 300);
        return n19;
    }
    
    private static double pseries(final double n, final double n2, final double n3) throws ArithmeticException {
        final double n4 = 1.0 / n;
        final double n5 = (1.0 - n2) * n3;
        double n7;
        double n6;
        double n8;
        double n9;
        double n10;
        for (n6 = (n7 = n5 / (n + 1.0)), n8 = n5, n9 = 2.0, n10 = 0.0; Math.abs(n6) > 1.1102230246251565E-16 * n4; n6 = n8 / (n + n9), n10 += n6, ++n9) {
            n8 *= (n9 - n2) * n3 / n9;
        }
        final double n11 = n10 + n7 + n4;
        final double n12 = n * Math.log(n3);
        double exp;
        if (n + n2 < 171.6243769563027 && Math.abs(n12) < 709.782712893384) {
            exp = n11 * (gamma(n + n2) / (gamma(n) * gamma(n2))) * Math.pow(n3, n);
        }
        else {
            final double n13 = lgamma(n + n2) - lgamma(n) - lgamma(n2) + n12 + Math.log(n11);
            if (n13 < -745.1332191019412) {
                exp = 0.0;
            }
            else {
                exp = Math.exp(n13);
            }
        }
        return exp;
    }
}
