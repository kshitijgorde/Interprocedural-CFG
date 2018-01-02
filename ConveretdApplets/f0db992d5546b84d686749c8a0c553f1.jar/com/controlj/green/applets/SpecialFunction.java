// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

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
    
    public static double log10(final double x) throws ArithmeticException {
        if (x <= 0.0) {
            throw new ArithmeticException("range exception");
        }
        return Math.log(x) / 2.302585092994046;
    }
    
    public static double cosh(final double x) throws ArithmeticException {
        double a = x;
        if (a < 0.0) {
            a = Math.abs(x);
        }
        a = Math.exp(a);
        return 0.5 * (a + 1.0 / a);
    }
    
    public static double sinh(final double x) throws ArithmeticException {
        if (x == 0.0) {
            return x;
        }
        double a = x;
        if (a < 0.0) {
            a = Math.abs(x);
        }
        a = Math.exp(a);
        if (x < 0.0) {
            return -0.5 * (a - 1.0 / a);
        }
        return 0.5 * (a - 1.0 / a);
    }
    
    public static double tanh(final double x) throws ArithmeticException {
        if (x == 0.0) {
            return x;
        }
        double a = x;
        if (a < 0.0) {
            a = Math.abs(x);
        }
        a = Math.exp(2.0 * a);
        if (x < 0.0) {
            return -(1.0 - 2.0 / (a + 1.0));
        }
        return 1.0 - 2.0 / (a + 1.0);
    }
    
    public static double acosh(final double x) throws ArithmeticException {
        if (x < 1.0) {
            throw new ArithmeticException("range exception");
        }
        return Math.log(x + Math.sqrt(x * x - 1.0));
    }
    
    public static double asinh(final double xx) throws ArithmeticException {
        if (xx == 0.0) {
            return xx;
        }
        int sign;
        double x;
        if (xx < 0.0) {
            sign = -1;
            x = -xx;
        }
        else {
            sign = 1;
            x = xx;
        }
        return sign * Math.log(x + Math.sqrt(x * x + 1.0));
    }
    
    public static double atanh(final double x) throws ArithmeticException {
        if (x > 1.0 || x < -1.0) {
            throw new ArithmeticException("range exception");
        }
        return 0.5 * Math.log((1.0 + x) / (1.0 - x));
    }
    
    public static double j0(final double x) throws ArithmeticException {
        final double ax;
        if ((ax = Math.abs(x)) < 8.0) {
            final double y = x * x;
            final double ans1 = 5.7568490574E10 + y * (-1.3362590354E10 + y * (6.516196407E8 + y * (-1.121442418E7 + y * (77392.33017 + y * -184.9052456))));
            final double ans2 = 5.7568490411E10 + y * (1.029532985E9 + y * (9494680.718 + y * (59272.64853 + y * (267.8532712 + y * 1.0))));
            return ans1 / ans2;
        }
        final double z = 8.0 / ax;
        final double y2 = z * z;
        final double xx = ax - 0.785398164;
        final double ans3 = 1.0 + y2 * (-0.001098628627 + y2 * (2.734510407E-5 + y2 * (-2.073370639E-6 + y2 * 2.093887211E-7)));
        final double ans4 = -0.01562499995 + y2 * (1.430488765E-4 + y2 * (-6.911147651E-6 + y2 * (7.621095161E-7 - y2 * 9.34935152E-8)));
        return Math.sqrt(0.636619772 / ax) * (Math.cos(xx) * ans3 - z * Math.sin(xx) * ans4);
    }
    
    public static double j1(final double x) throws ArithmeticException {
        final double ax;
        if ((ax = Math.abs(x)) < 8.0) {
            final double y = x * x;
            final double ans1 = x * (7.2362614232E10 + y * (-7.895059235E9 + y * (2.423968531E8 + y * (-2972611.439 + y * (15704.4826 + y * -30.16036606)))));
            final double ans2 = 1.44725228442E11 + y * (2.300535178E9 + y * (1.858330474E7 + y * (99447.43394 + y * (376.9991397 + y * 1.0))));
            return ans1 / ans2;
        }
        final double z = 8.0 / ax;
        final double xx = ax - 2.356194491;
        final double y = z * z;
        final double ans1 = 1.0 + y * (0.00183105 + y * (-3.516396496E-5 + y * (2.457520174E-6 + y * -2.40337019E-7)));
        final double ans2 = 0.04687499995 + y * (-2.002690873E-4 + y * (8.449199096E-6 + y * (-8.8228987E-7 + y * 1.05787412E-7)));
        double ans3 = Math.sqrt(0.636619772 / ax) * (Math.cos(xx) * ans1 - z * Math.sin(xx) * ans2);
        if (x < 0.0) {
            ans3 = -ans3;
        }
        return ans3;
    }
    
    public static double jn(final int n, final double x) throws ArithmeticException {
        final double ACC = 40.0;
        final double BIGNO = 1.0E10;
        final double BIGNI = 1.0E-10;
        if (n == 0) {
            return j0(x);
        }
        if (n == 1) {
            return j1(x);
        }
        final double ax = Math.abs(x);
        if (ax == 0.0) {
            return 0.0;
        }
        double ans;
        if (ax > n) {
            final double tox = 2.0 / ax;
            double bjm = j0(ax);
            double bj = j1(ax);
            for (int j = 1; j < n; ++j) {
                final double bjp = j * tox * bj - bjm;
                bjm = bj;
                bj = bjp;
            }
            ans = bj;
        }
        else {
            final double tox = 2.0 / ax;
            final int m = 2 * ((n + (int)Math.sqrt(ACC * n)) / 2);
            boolean jsum = false;
            double bjp;
            double sum;
            ans = (bjp = (sum = 0.0));
            double bj = 1.0;
            for (int j = m; j > 0; --j) {
                final double bjm = j * tox * bj - bjp;
                bjp = bj;
                bj = bjm;
                if (Math.abs(bj) > BIGNO) {
                    bj *= BIGNI;
                    bjp *= BIGNI;
                    ans *= BIGNI;
                    sum *= BIGNI;
                }
                if (jsum) {
                    sum += bj;
                }
                jsum = !jsum;
                if (j == n) {
                    ans = bjp;
                }
            }
            sum = 2.0 * sum - bj;
            ans /= sum;
        }
        return (x < 0.0 && n % 2 == 1) ? (-ans) : ans;
    }
    
    public static double y0(final double x) throws ArithmeticException {
        if (x < 8.0) {
            final double y = x * x;
            final double ans1 = -2.957821389E9 + y * (7.062834065E9 + y * (-5.123598036E8 + y * (1.087988129E7 + y * (-86327.92757 + y * 228.4622733))));
            final double ans2 = 4.0076544269E10 + y * (7.452499648E8 + y * (7189466.438 + y * (47447.2647 + y * (226.1030244 + y * 1.0))));
            return ans1 / ans2 + 0.636619772 * j0(x) * Math.log(x);
        }
        final double z = 8.0 / x;
        final double y2 = z * z;
        final double xx = x - 0.785398164;
        final double ans3 = 1.0 + y2 * (-0.001098628627 + y2 * (2.734510407E-5 + y2 * (-2.073370639E-6 + y2 * 2.093887211E-7)));
        final double ans4 = -0.01562499995 + y2 * (1.430488765E-4 + y2 * (-6.911147651E-6 + y2 * (7.621095161E-7 + y2 * -9.34945152E-8)));
        return Math.sqrt(0.636619772 / x) * (Math.sin(xx) * ans3 + z * Math.cos(xx) * ans4);
    }
    
    public static double y1(final double x) throws ArithmeticException {
        if (x < 8.0) {
            final double y = x * x;
            final double ans1 = x * (-4.900604943E12 + y * (1.27527439E12 + y * (-5.153438139E10 + y * (7.349264551E8 + y * (-4237922.726 + y * 8511.937935)))));
            final double ans2 = 2.49958057E13 + y * (4.244419664E11 + y * (3.733650367E9 + y * (2.245904002E7 + y * (102042.605 + y * (354.9632885 + y)))));
            return ans1 / ans2 + 0.636619772 * (j1(x) * Math.log(x) - 1.0 / x);
        }
        final double z = 8.0 / x;
        final double y2 = z * z;
        final double xx = x - 2.356194491;
        final double ans3 = 1.0 + y2 * (0.00183105 + y2 * (-3.516396496E-5 + y2 * (2.457520174E-6 + y2 * -2.40337019E-7)));
        final double ans4 = 0.04687499995 + y2 * (-2.002690873E-4 + y2 * (8.449199096E-6 + y2 * (-8.8228987E-7 + y2 * 1.05787412E-7)));
        return Math.sqrt(0.636619772 / x) * (Math.sin(xx) * ans3 + z * Math.cos(xx) * ans4);
    }
    
    public static double yn(final int n, final double x) throws ArithmeticException {
        if (n == 0) {
            return y0(x);
        }
        if (n == 1) {
            return y1(x);
        }
        final double tox = 2.0 / x;
        double by = y1(x);
        double bym = y0(x);
        for (int j = 1; j < n; ++j) {
            final double byp = j * tox * by - bym;
            bym = by;
            by = byp;
        }
        return by;
    }
    
    public static double fac(final double x) throws ArithmeticException {
        final double d = Math.abs(x);
        if (Math.floor(d) == d) {
            return fac((int)x);
        }
        return gamma(x + 1.0);
    }
    
    public static int fac(final int j) throws ArithmeticException {
        int i = j;
        int d = 1;
        if (j < 0) {
            i = Math.abs(j);
        }
        while (i > 1) {
            d *= i--;
        }
        if (j < 0) {
            return -d;
        }
        return d;
    }
    
    public static double gamma(double x) throws ArithmeticException {
        final double[] P = { 1.6011952247675185E-4, 0.0011913514700658638, 0.010421379756176158, 0.04763678004571372, 0.20744822764843598, 0.4942148268014971, 1.0 };
        final double[] Q = { -2.3158187332412014E-5, 5.396055804933034E-4, -0.004456419138517973, 0.011813978522206043, 0.035823639860549865, -0.23459179571824335, 0.0714304917030273, 1.0 };
        final double MAXGAM = 171.6243769563027;
        final double LOGPI = 1.1447298858494002;
        double q = Math.abs(x);
        if (q > 33.0) {
            if (x >= 0.0) {
                return stirf(x);
            }
            double p = Math.floor(q);
            if (p == q) {
                throw new ArithmeticException("gamma: overflow");
            }
            final int i = (int)p;
            double z = q - p;
            if (z > 0.5) {
                ++p;
                z = q - p;
            }
            z = q * Math.sin(3.141592653589793 * z);
            if (z == 0.0) {
                throw new ArithmeticException("gamma: overflow");
            }
            z = Math.abs(z);
            z = 3.141592653589793 / (z * stirf(q));
            return -z;
        }
        else {
            double z;
            for (z = 1.0; x >= 3.0; --x, z *= x) {}
            while (x < 0.0) {
                if (x == 0.0) {
                    throw new ArithmeticException("gamma: singular");
                }
                if (x > -1.0E-9) {
                    return z / ((1.0 + 0.5772156649015329 * x) * x);
                }
                z /= x;
                ++x;
            }
            while (x < 2.0) {
                if (x == 0.0) {
                    throw new ArithmeticException("gamma: singular");
                }
                if (x < 1.0E-9) {
                    return z / ((1.0 + 0.5772156649015329 * x) * x);
                }
                z /= x;
                ++x;
            }
            if (x == 2.0 || x == 3.0) {
                return z;
            }
            x -= 2.0;
            final double p = polevl(x, P, 6);
            q = polevl(x, Q, 7);
            return z * p / q;
        }
    }
    
    private static double stirf(final double x) throws ArithmeticException {
        final double[] STIR = { 7.873113957930937E-4, -2.2954996161337813E-4, -0.0026813261780578124, 0.0034722222160545866, 0.08333333333334822 };
        final double MAXSTIR = 143.01608;
        double w = 1.0 / x;
        double y = Math.exp(x);
        w = 1.0 + w * polevl(w, STIR, 4);
        if (x > MAXSTIR) {
            final double v = Math.pow(x, 0.5 * x - 0.25);
            y = v * (v / y);
        }
        else {
            y = Math.pow(x, x - 0.5) / y;
        }
        y = 2.5066282746310007 * y * w;
        return y;
    }
    
    public static double igamc(final double a, final double x) throws ArithmeticException {
        final double big = 4.503599627370496E15;
        final double biginv = 2.220446049250313E-16;
        if (x <= 0.0 || a <= 0.0) {
            return 1.0;
        }
        if (x < 1.0 || x < a) {
            return 1.0 - igam(a, x);
        }
        double ax = a * Math.log(x) - x - lgamma(a);
        if (ax < -709.782712893384) {
            return 0.0;
        }
        ax = Math.exp(ax);
        double y = 1.0 - a;
        double z = x + y + 1.0;
        double c = 0.0;
        double pkm2 = 1.0;
        double qkm2 = x;
        double pkm3 = x + 1.0;
        double qkm3 = z * x;
        double ans = pkm3 / qkm3;
        double t;
        do {
            ++c;
            ++y;
            z += 2.0;
            final double yc = y * c;
            final double pk = pkm3 * z - pkm2 * yc;
            final double qk = qkm3 * z - qkm2 * yc;
            if (qk != 0.0) {
                final double r = pk / qk;
                t = Math.abs((ans - r) / r);
                ans = r;
            }
            else {
                t = 1.0;
            }
            pkm2 = pkm3;
            pkm3 = pk;
            qkm2 = qkm3;
            qkm3 = qk;
            if (Math.abs(pk) > big) {
                pkm2 *= biginv;
                pkm3 *= biginv;
                qkm2 *= biginv;
                qkm3 *= biginv;
            }
        } while (t > 1.1102230246251565E-16);
        return ans * ax;
    }
    
    public static double igam(final double a, final double x) throws ArithmeticException {
        if (x <= 0.0 || a <= 0.0) {
            return 0.0;
        }
        if (x > 1.0 && x > a) {
            return 1.0 - igamc(a, x);
        }
        double ax = a * Math.log(x) - x - lgamma(a);
        if (ax < -709.782712893384) {
            return 0.0;
        }
        ax = Math.exp(ax);
        double r = a;
        double c = 1.0;
        double ans = 1.0;
        do {
            ++r;
            c *= x / r;
            ans += c;
        } while (c / ans > 1.1102230246251565E-16);
        return ans * ax / a;
    }
    
    public static double chisq(final double df, final double x) throws ArithmeticException {
        if (x < 0.0 || df < 1.0) {
            return 0.0;
        }
        return igam(df / 2.0, x / 2.0);
    }
    
    public static double chisqc(final double df, final double x) throws ArithmeticException {
        if (x < 0.0 || df < 1.0) {
            return 0.0;
        }
        return igamc(df / 2.0, x / 2.0);
    }
    
    public static double poisson(final int k, final double x) throws ArithmeticException {
        if (k < 0 || x < 0.0) {
            return 0.0;
        }
        return igamc(k + 1, x);
    }
    
    public static double poissonc(final int k, final double x) throws ArithmeticException {
        if (k < 0 || x < 0.0) {
            return 0.0;
        }
        return igam(k + 1, x);
    }
    
    public static double normal(final double a) throws ArithmeticException {
        final double x = a * 0.7071067811865476;
        final double z = Math.abs(x);
        double y;
        if (z < 0.7071067811865476) {
            y = 0.5 + 0.5 * erf(x);
        }
        else {
            y = 0.5 * erfc(z);
            if (x > 0.0) {
                y = 1.0 - y;
            }
        }
        return y;
    }
    
    public static double erfc(final double a) throws ArithmeticException {
        final double[] P = { 2.461969814735305E-10, 0.5641895648310689, 7.463210564422699, 48.63719709856814, 196.5208329560771, 526.4451949954773, 934.5285271719576, 1027.5518868951572, 557.5353353693994 };
        final double[] Q = { 13.228195115474499, 86.70721408859897, 354.9377788878199, 975.7085017432055, 1823.9091668790973, 2246.3376081871097, 1656.6630919416134, 557.5353408177277 };
        final double[] R = { 0.5641895835477551, 1.275366707599781, 5.019050422511805, 6.160210979930536, 7.4097426995044895, 2.9788666537210022 };
        final double[] S = { 2.2605286322011726, 9.396035249380015, 12.048953980809666, 17.08144507475659, 9.608968090632859, 3.369076451000815 };
        double x;
        if (a < 0.0) {
            x = -a;
        }
        else {
            x = a;
        }
        if (x < 1.0) {
            return 1.0 - erf(a);
        }
        double z = -a * a;
        if (z < -709.782712893384) {
            if (a < 0.0) {
                return 2.0;
            }
            return 0.0;
        }
        else {
            z = Math.exp(z);
            double p;
            double q;
            if (x < 8.0) {
                p = polevl(x, P, 8);
                q = p1evl(x, Q, 8);
            }
            else {
                p = polevl(x, R, 5);
                q = p1evl(x, S, 6);
            }
            double y = z * p / q;
            if (a < 0.0) {
                y = 2.0 - y;
            }
            if (y != 0.0) {
                return y;
            }
            if (a < 0.0) {
                return 2.0;
            }
            return 0.0;
        }
    }
    
    public static double erf(final double x) throws ArithmeticException {
        final double[] T = { 9.604973739870516, 90.02601972038427, 2232.005345946843, 7003.325141128051, 55592.30130103949 };
        final double[] U = { 33.56171416475031, 521.3579497801527, 4594.323829709801, 22629.000061389095, 49267.39426086359 };
        if (Math.abs(x) > 1.0) {
            return 1.0 - erfc(x);
        }
        final double z = x * x;
        final double y = x * polevl(z, T, 4) / p1evl(z, U, 5);
        return y;
    }
    
    private static double polevl(final double x, final double[] coef, final int N) throws ArithmeticException {
        double ans = coef[0];
        for (int i = 1; i <= N; ++i) {
            ans = ans * x + coef[i];
        }
        return ans;
    }
    
    private static double p1evl(final double x, final double[] coef, final int N) throws ArithmeticException {
        double ans = x + coef[0];
        for (int i = 1; i < N; ++i) {
            ans = ans * x + coef[i];
        }
        return ans;
    }
    
    private static double lgamma(double x) throws ArithmeticException {
        final double[] A = { 8.116141674705085E-4, -5.950619042843014E-4, 7.936503404577169E-4, -0.002777777777300997, 0.08333333333333319 };
        final double[] B = { -1378.2515256912086, -38801.631513463784, -331612.9927388712, -1162370.974927623, -1721737.0082083966, -853555.6642457654 };
        final double[] C = { -351.81570143652345, -17064.210665188115, -220528.59055385445, -1139334.4436798252, -2532523.0717758294, -2018891.4143353277 };
        if (x < -34.0) {
            final double q = -x;
            final double w = lgamma(q);
            double p = Math.floor(q);
            if (p == q) {
                throw new ArithmeticException("lgam: Overflow");
            }
            double z = q - p;
            if (z > 0.5) {
                ++p;
                z = p - q;
            }
            z = q * Math.sin(3.141592653589793 * z);
            if (z == 0.0) {
                throw new ArithmeticException("lgamma: Overflow");
            }
            z = 1.1447298858494002 - Math.log(z) - w;
            return z;
        }
        else if (x < 13.0) {
            double z;
            for (z = 1.0; x >= 3.0; --x, z *= x) {}
            while (x < 2.0) {
                if (x == 0.0) {
                    throw new ArithmeticException("lgamma: Overflow");
                }
                z /= x;
                ++x;
            }
            if (z < 0.0) {
                z = -z;
            }
            if (x == 2.0) {
                return Math.log(z);
            }
            x -= 2.0;
            final double p = x * polevl(x, B, 5) / p1evl(x, C, 6);
            return Math.log(z) + p;
        }
        else {
            if (x > 2.556348E305) {
                throw new ArithmeticException("lgamma: Overflow");
            }
            double q = (x - 0.5) * Math.log(x) - x + 0.9189385332046728;
            if (x > 1.0E8) {
                return q;
            }
            final double p = 1.0 / (x * x);
            if (x >= 1000.0) {
                q += ((7.936507936507937E-4 * p - 0.002777777777777778) * p + 0.08333333333333333) / x;
            }
            else {
                q += polevl(p, A, 4) / x;
            }
            return q;
        }
    }
    
    public static double ibeta(final double aa, final double bb, final double xx) throws ArithmeticException {
        if (aa <= 0.0 || bb <= 0.0) {
            throw new ArithmeticException("ibeta: Domain error!");
        }
        if (xx <= 0.0 || xx >= 1.0) {
            if (xx == 0.0) {
                return 0.0;
            }
            if (xx == 1.0) {
                return 1.0;
            }
            throw new ArithmeticException("ibeta: Domain error!");
        }
        else {
            boolean flag = false;
            if (bb * xx <= 1.0 && xx <= 0.95) {
                final double t = pseries(aa, bb, xx);
                return t;
            }
            double w = 1.0 - xx;
            double a;
            double b;
            double xc;
            double x;
            if (xx > aa / (aa + bb)) {
                flag = true;
                a = bb;
                b = aa;
                xc = xx;
                x = w;
            }
            else {
                a = aa;
                b = bb;
                xc = w;
                x = xx;
            }
            if (flag && b * x <= 1.0 && x <= 0.95) {
                double t = pseries(a, b, x);
                if (t <= 1.1102230246251565E-16) {
                    t = 0.9999999999999999;
                }
                else {
                    t = 1.0 - t;
                }
                return t;
            }
            double y = x * (a + b - 2.0) - (a - 1.0);
            if (y < 0.0) {
                w = incbcf(a, b, x);
            }
            else {
                w = incbd(a, b, x) / xc;
            }
            y = a * Math.log(x);
            double t = b * Math.log(xc);
            if (a + b < 171.6243769563027 && Math.abs(y) < 709.782712893384 && Math.abs(t) < 709.782712893384) {
                t = Math.pow(xc, b);
                t *= Math.pow(x, a);
                t /= a;
                t *= w;
                t *= gamma(a + b) / (gamma(a) * gamma(b));
                if (flag) {
                    if (t <= 1.1102230246251565E-16) {
                        t = 0.9999999999999999;
                    }
                    else {
                        t = 1.0 - t;
                    }
                }
                return t;
            }
            y += t + lgamma(a + b) - lgamma(a) - lgamma(b);
            y += Math.log(w / a);
            if (y < -745.1332191019412) {
                t = 0.0;
            }
            else {
                t = Math.exp(y);
            }
            if (flag) {
                if (t <= 1.1102230246251565E-16) {
                    t = 0.9999999999999999;
                }
                else {
                    t = 1.0 - t;
                }
            }
            return t;
        }
    }
    
    private static double incbcf(final double a, final double b, final double x) throws ArithmeticException {
        final double big = 4.503599627370496E15;
        final double biginv = 2.220446049250313E-16;
        double k1 = a;
        double k2 = a + b;
        double k3 = a;
        double k4 = a + 1.0;
        double k5 = 1.0;
        double k6 = b - 1.0;
        double k7 = k4;
        double k8 = a + 2.0;
        double pkm2 = 0.0;
        double qkm2 = 1.0;
        double pkm3 = 1.0;
        double qkm3 = 1.0;
        double ans = 1.0;
        double r = 1.0;
        int n = 0;
        final double thresh = 3.3306690738754696E-16;
        do {
            double xk = -(x * k1 * k2) / (k3 * k4);
            double pk = pkm3 + pkm2 * xk;
            double qk = qkm3 + qkm2 * xk;
            pkm2 = pkm3;
            pkm3 = pk;
            qkm2 = qkm3;
            qkm3 = qk;
            xk = x * k5 * k6 / (k7 * k8);
            pk = pkm3 + pkm2 * xk;
            qk = qkm3 + qkm2 * xk;
            pkm2 = pkm3;
            pkm3 = pk;
            qkm2 = qkm3;
            qkm3 = qk;
            if (qk != 0.0) {
                r = pk / qk;
            }
            double t;
            if (r != 0.0) {
                t = Math.abs((ans - r) / r);
                ans = r;
            }
            else {
                t = 1.0;
            }
            if (t < thresh) {
                return ans;
            }
            ++k1;
            ++k2;
            k3 += 2.0;
            k4 += 2.0;
            ++k5;
            --k6;
            k7 += 2.0;
            k8 += 2.0;
            if (Math.abs(qk) + Math.abs(pk) > big) {
                pkm2 *= biginv;
                pkm3 *= biginv;
                qkm2 *= biginv;
                qkm3 *= biginv;
            }
            if (Math.abs(qk) >= biginv && Math.abs(pk) >= biginv) {
                continue;
            }
            pkm2 *= big;
            pkm3 *= big;
            qkm2 *= big;
            qkm3 *= big;
        } while (++n < 300);
        return ans;
    }
    
    private static double incbd(final double a, final double b, final double x) throws ArithmeticException {
        final double big = 4.503599627370496E15;
        final double biginv = 2.220446049250313E-16;
        double k1 = a;
        double k2 = b - 1.0;
        double k3 = a;
        double k4 = a + 1.0;
        double k5 = 1.0;
        double k6 = a + b;
        double k7 = a + 1.0;
        double k8 = a + 2.0;
        double pkm2 = 0.0;
        double qkm2 = 1.0;
        double pkm3 = 1.0;
        double qkm3 = 1.0;
        final double z = x / (1.0 - x);
        double ans = 1.0;
        double r = 1.0;
        int n = 0;
        final double thresh = 3.3306690738754696E-16;
        do {
            double xk = -(z * k1 * k2) / (k3 * k4);
            double pk = pkm3 + pkm2 * xk;
            double qk = qkm3 + qkm2 * xk;
            pkm2 = pkm3;
            pkm3 = pk;
            qkm2 = qkm3;
            qkm3 = qk;
            xk = z * k5 * k6 / (k7 * k8);
            pk = pkm3 + pkm2 * xk;
            qk = qkm3 + qkm2 * xk;
            pkm2 = pkm3;
            pkm3 = pk;
            qkm2 = qkm3;
            qkm3 = qk;
            if (qk != 0.0) {
                r = pk / qk;
            }
            double t;
            if (r != 0.0) {
                t = Math.abs((ans - r) / r);
                ans = r;
            }
            else {
                t = 1.0;
            }
            if (t < thresh) {
                return ans;
            }
            ++k1;
            --k2;
            k3 += 2.0;
            k4 += 2.0;
            ++k5;
            ++k6;
            k7 += 2.0;
            k8 += 2.0;
            if (Math.abs(qk) + Math.abs(pk) > big) {
                pkm2 *= biginv;
                pkm3 *= biginv;
                qkm2 *= biginv;
                qkm3 *= biginv;
            }
            if (Math.abs(qk) >= biginv && Math.abs(pk) >= biginv) {
                continue;
            }
            pkm2 *= big;
            pkm3 *= big;
            qkm2 *= big;
            qkm3 *= big;
        } while (++n < 300);
        return ans;
    }
    
    private static double pseries(final double a, final double b, final double x) throws ArithmeticException {
        final double ai = 1.0 / a;
        double u = (1.0 - b) * x;
        final double t1;
        double v = t1 = u / (a + 1.0);
        double t2 = u;
        double n = 2.0;
        double s = 0.0;
        for (double z = 1.1102230246251565E-16 * ai; Math.abs(v) > z; v = t2 / (a + n), s += v, ++n) {
            u = (n - b) * x / n;
            t2 *= u;
        }
        s += t1;
        s += ai;
        u = a * Math.log(x);
        if (a + b < 171.6243769563027 && Math.abs(u) < 709.782712893384) {
            t2 = gamma(a + b) / (gamma(a) * gamma(b));
            s = s * t2 * Math.pow(x, a);
        }
        else {
            t2 = lgamma(a + b) - lgamma(a) - lgamma(b) + u + Math.log(s);
            if (t2 < -745.1332191019412) {
                s = 0.0;
            }
            else {
                s = Math.exp(t2);
            }
        }
        return s;
    }
}
