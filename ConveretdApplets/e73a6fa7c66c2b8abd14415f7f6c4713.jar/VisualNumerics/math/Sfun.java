// 
// Decompiled by Procyon v0.5.30
// 

package VisualNumerics.math;

public class Sfun
{
    public static final double EPSILON_SMALL = 1.11022302462515E-16;
    public static final double EPSILON_LARGE = 2.2204460492503E-16;
    public static final double Euler = 0.5772156649015329;
    static final double aln2 = 0.6931471805599453;
    private static final double log10_loge = 0.4342944819032518;
    private static final double cot_pi2rec = 0.011619772367581343;
    private static final double cot_xmax = 4.503599627370523E15;
    private static final double cot_xsml;
    private static final double cot_sqeps;
    private static final double[] cot_coef;
    private static final double sinh_ymax;
    private static final double sinh_sqeps;
    private static final double[] sinh_coef;
    private static final double cosh_ymax;
    private static final double tanh_xmax;
    private static final double tanh_sqeps;
    private static final double[] tanh_coef;
    private static final double asinh_sqeps;
    private static final double asinh_xmax;
    private static double[] asinh_coef;
    private static final double acosh_xmax;
    private static final double atanh_sqeps;
    private static final double[] atanh_coef;
    private static final double gamma_sq2pil = 0.9189385332046728;
    private static final double gamma_dxrel;
    private static final double gamma_xsml = 5.56302416559456E-309;
    private static final double gamma_xmin = -170.56;
    private static final double gamma_xmax = 171.614;
    private static final double gamma_sqpi2l = 0.22579135264472744;
    private static final double[] gamma_coef;
    private static final double logGammaCorrection_xbig;
    private static final double logGammaCorrection_xmax = 1.39118E11;
    private static final double[] logGammaCorrection_coef;
    private static final double[] erfc_coef;
    private static final double[] erfc2_coef;
    private static final double[] erfcc_coef;
    private static final double erf_xbig;
    private static final double erf_sqeps;
    private static final double erfc_xsml;
    private static double erfc_xmax;
    
    static {
        cot_xsml = Math.sqrt(3.33066907387545E-16);
        cot_sqeps = Math.sqrt(2.2204460492503E-16);
        cot_coef = new double[] { 0.2402591609829563, -0.016533031601500228, -4.299839193172402E-5, -1.5928322332754105E-7, -6.191093135129349E-10, -2.430197415072646E-12, -9.56093675880008E-15, -3.763537981945806E-17, -1.4816657464674657E-19 };
        sinh_ymax = 1.0 / Math.sqrt(1.11022302462515E-16);
        sinh_sqeps = Math.sqrt(6.6613381477509E-16);
        sinh_coef = new double[] { 0.1730421940471796, 0.08759422192276048, 0.00107947777456713, 6.37484926075E-6, 2.202366404E-8, 4.98794E-11, 7.973E-14, 9.0E-17 };
        cosh_ymax = 1.0 / Math.sqrt(1.11022302462515E-16);
        tanh_xmax = -0.5 * Math.log(1.11022302462515E-16);
        tanh_sqeps = Math.sqrt(3.33066907387545E-16);
        tanh_coef = new double[] { -0.2582875664363471, -0.11836106330053497, 0.009869442648006398, -8.35798662344582E-4, 7.0904321198943E-5, -6.01642431812E-6, 5.105241908E-7, -4.3320729077E-8, 3.675999055E-9, -3.11928496E-10, 2.6468828E-11, -2.246023E-12, 1.90587E-13, -1.6172E-14, 1.372E-15, -1.16E-16, 9.0E-18 };
        asinh_sqeps = Math.sqrt(1.11022302462515E-16);
        asinh_xmax = 1.0 / Sfun.asinh_sqeps;
        Sfun.asinh_coef = new double[] { -0.12820039911738187, -0.05881176118995177, 0.004727465432212481, -4.938363162653618E-4, 5.850620705855741E-5, -7.466998328931368E-6, 1.00116935835582E-6, -1.3903543858708333E-7, 1.9823169483172795E-8, -2.8847468417848845E-9, 4.2672965467159937E-10, -6.397608465436636E-11, 9.699168608906471E-12, -1.4844276972043772E-12, 2.290373793902745E-13, -3.5588395132732646E-14, 5.563969408005679E-15, -8.746250959962468E-16, 1.381524884452669E-16, -2.1916688282900364E-17, 3.490465852482756E-18 };
        acosh_xmax = 1.0 / Math.sqrt(1.11022302462515E-16);
        atanh_sqeps = Math.sqrt(3.33066907387545E-16);
        atanh_coef = new double[] { 0.0943951023931955, 0.04919843705578616, 0.002102593522455433, 1.0735544497761166E-4, 5.978267249293031E-6, 3.505062030889135E-7, 2.1263743437653402E-8, 1.3216945357155272E-9, 8.36587550117807E-11, 5.370503749311002E-12, 3.4866594701571077E-13, 2.284549509603433E-14, 1.508407105944793E-15, 1.0024188168041091E-16, 6.69867473816507E-18 };
        gamma_dxrel = Math.sqrt(2.2204460492503E-16);
        gamma_coef = new double[] { 0.00857119559098933, 0.004415381324841007, 0.05685043681599363, -0.00421983539641856, 0.0013268081812124603, -1.8930245297988805E-4, 3.606925327441245E-5, -6.056761904460864E-6, 1.0558295463022833E-6, -1.811967365542384E-7, 3.117724964715322E-8, -5.354219639019687E-9, 9.193275519859589E-10, -1.5779412802883398E-10, 2.7079806229349544E-11, -4.64681865382573E-12, 7.97335019200742E-13, -1.368078209830916E-13, 2.3473194865638007E-14, -4.027432614949067E-15, 6.910051747372101E-16, -1.185584500221993E-16, 2.034148542496374E-17, -3.490054341717406E-18, 5.987993856485306E-19 };
        logGammaCorrection_xbig = 1.0 / Math.sqrt(1.11022302462515E-16);
        logGammaCorrection_coef = new double[] { 0.16663894804518634, -1.384948176067564E-5, 9.81082564692473E-9, -1.809129475572494E-11, 6.221098041892606E-14, -3.399615005417722E-16, 2.683181998482699E-18 };
        erfc_coef = new double[] { -0.049046121234691806, -0.14226120510371365, 0.010035582187599796, -5.768764699767485E-4, 2.741993125219606E-5, -1.1043175507344507E-6, 3.8488755420345036E-8, -1.1808582533875466E-9, 3.2334215826050907E-11, -7.991015947004549E-13, 1.7990725113961456E-14, -3.718635487818693E-16, 7.103599003714253E-18 };
        erfc2_coef = new double[] { -0.0696013466023095, -0.04110133936262089, 0.003914495866689627, -4.906395650548979E-4, 7.157479001377036E-5, -1.1530716341312328E-5, 1.9946705902019974E-6, -3.642666471599223E-7, 6.944372610005012E-8, -1.371220902104366E-8, 2.7883896610071373E-9, -5.814164724331161E-10, 1.2389204917527532E-10, -2.6906391453067435E-11, 5.942614350847911E-12, -1.3323867357581197E-12, 3.0280468061771323E-13, -6.966648814941033E-14, 1.620854541053923E-14, -3.809934465250492E-15, 9.040487815978831E-16, -2.1640061950896072E-16, 5.222102233995855E-17, -1.2697296023645554E-17, 3.1091455042761977E-18 };
        erfcc_coef = new double[] { 0.07151793102029248, -0.026532434337606717, 0.0017111539779208558, -1.6375166345851787E-4, 1.9871293500552038E-5, -2.843712412766555E-6, 4.6061613089631305E-7, -8.227753025879209E-8, 1.5921418727709012E-8, -3.295071362252843E-9, 7.223439760400556E-10, -1.6648558133987297E-10, 4.010392588237665E-11, -1.004816214425731E-11, 2.608275913300334E-12, -6.991110560404025E-13, 1.9294923332617072E-13, -5.470131188754331E-14, 1.5896633097626975E-14, -4.726893980197555E-15, 1.4358733767849847E-15, -4.449510561817358E-16, 1.4048108847682335E-16, -4.5138183877642106E-17, 1.474521541045133E-17, -4.8926214069457765E-18, 1.6476121414106467E-18, -5.626817176329408E-19, 1.9474433822320786E-19 };
        erf_xbig = Math.sqrt(-Math.log(1.9678190753608168E-16));
        erf_sqeps = Math.sqrt(2.2204460492503E-16);
        erfc_xsml = -Math.sqrt(-Math.log(1.9678190753608168E-16));
        Sfun.erfc_xmax = Math.sqrt(-Math.log(1.772453850905516 * Double.MIN_VALUE));
        Sfun.erfc_xmax += -0.5 * Math.log(Sfun.erfc_xmax) / Sfun.erfc_xmax - 0.01;
    }
    
    public static double acosh(final double n) {
        if (n < 1.0) {
            return Double.NaN;
        }
        if (n < Sfun.acosh_xmax) {
            return Math.log(n + Math.sqrt(n * n - 1.0));
        }
        return 0.6931471805599453 + Math.log(n);
    }
    
    public static double asinh(final double n) {
        final double abs = Math.abs(n);
        double log;
        if (abs <= 1.0) {
            if (abs <= Sfun.asinh_sqeps) {
                log = n;
            }
            else {
                log = n * (1.0 + csevl(2.0 * n * n - 1.0, Sfun.asinh_coef));
            }
        }
        else if (abs < Sfun.asinh_xmax) {
            log = Math.log(abs + Math.sqrt(abs * abs + 1.0));
        }
        else {
            log = 0.6931471805599453 + Math.log(abs);
        }
        return log;
    }
    
    public static double atanh(final double n) {
        final double abs = Math.abs(n);
        double n2;
        if (abs >= 1.0) {
            n2 = Double.NaN;
        }
        else if (abs > Sfun.atanh_sqeps && abs <= 0.5) {
            n2 = n * (1.0 + csevl(8.0 * n * n - 1.0, Sfun.atanh_coef));
        }
        else if (abs > 0.5) {
            n2 = 0.5 * Math.log((1.0 + n) / (1.0 - n));
        }
        else {
            n2 = n;
        }
        return n2;
    }
    
    public static double cosh(final double n) {
        final double exp = Math.exp(Math.abs(n));
        if (Double.isInfinite(exp)) {
            return exp;
        }
        return (exp >= Sfun.cosh_ymax) ? (0.5 * exp) : (0.5 * (exp + 1.0 / exp));
    }
    
    public static double cot(final double n) {
        final double abs = Math.abs(n);
        if (abs > 4.503599627370523E15) {
            return Double.NaN;
        }
        final double n2 = (int)abs;
        final double n3 = abs - n2;
        final double n4 = 0.625 * n2;
        final double n5 = (int)n4;
        final double n6 = n4 - n5 + 0.625 * n3 + abs * 0.011619772367581343;
        final double n7 = (int)n6;
        final double n8 = n5 + n7;
        double n9 = n6 - n7;
        final int n10 = (int)(n8 % 2.0);
        if (n10 == 1) {
            n9 = 1.0 - n9;
        }
        double sign;
        if (n9 <= 0.25) {
            if (n9 == 0.0) {
                return Double.POSITIVE_INFINITY;
            }
            if (n9 <= Sfun.cot_xsml) {
                sign = 1.0 / n9;
            }
            else {
                sign = (0.5 + csevl(32.0 * n9 * n9 - 1.0, Sfun.cot_coef)) / n9;
            }
        }
        else if (n9 <= 0.5) {
            final double n11 = (0.5 + csevl(8.0 * n9 * n9 - 1.0, Sfun.cot_coef)) / (0.5 * n9);
            sign = (n11 * n11 - 1.0) * 0.5 / n11;
        }
        else {
            final double n12 = (0.5 + csevl(2.0 * n9 * n9 - 1.0, Sfun.cot_coef)) / (0.25 * n9);
            final double n13 = (n12 * n12 - 1.0) * 0.5 / n12;
            sign = (n13 * n13 - 1.0) * 0.5 / n13;
        }
        if (n != 0.0) {
            sign = sign(sign, n);
        }
        if (n10 == 1) {
            sign = -sign;
        }
        return sign;
    }
    
    static double csevl(final double n, final double[] array) {
        final int length = array.length;
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        final double n5 = 2.0 * n;
        for (int i = length - 1; i >= 0; --i) {
            n4 = n2;
            n2 = n3;
            n3 = n5 * n2 - n4 + array[i];
        }
        return 0.5 * (n3 - n4);
    }
    
    public static double erf(final double n) {
        final double abs = Math.abs(n);
        double n2;
        if (abs <= 1.0) {
            if (abs <= Sfun.erf_sqeps) {
                n2 = 2.0 * n / 1.772453850905516;
            }
            else {
                n2 = n * (1.0 + csevl(2.0 * n * n - 1.0, Sfun.erfc_coef));
            }
        }
        else if (abs <= Sfun.erf_xbig) {
            n2 = sign(1.0 - erfc(abs), n);
        }
        else {
            n2 = sign(1.0, n);
        }
        return n2;
    }
    
    public static double erfc(final double n) {
        double n2;
        if (n <= Sfun.erfc_xsml) {
            n2 = 2.0;
        }
        else if (n <= Sfun.erfc_xmax) {
            final double abs = Math.abs(n);
            if (abs <= 1.0) {
                if (abs < Sfun.erf_sqeps) {
                    n2 = 1.0 - 2.0 * n / 1.772453850905516;
                }
                else {
                    n2 = 1.0 - n * (1.0 + csevl(2.0 * n * n - 1.0, Sfun.erfc_coef));
                }
            }
            else {
                final double n3 = abs * abs;
                if (n3 <= 4.0) {
                    n2 = Math.exp(-n3) / Math.abs(n) * (0.5 + csevl((8.0 / n3 - 5.0) / 3.0, Sfun.erfc2_coef));
                }
                else {
                    n2 = Math.exp(-n3) / Math.abs(n) * (0.5 + csevl(8.0 / n3 - 1.0, Sfun.erfcc_coef));
                }
                if (n < 0.0) {
                    n2 = 2.0 - n2;
                }
            }
        }
        else {
            n2 = 0.0;
        }
        return n2;
    }
    
    public static double gamma(final double n) {
        final double abs = Math.abs(n);
        if (abs <= 10.0) {
            int n2 = (int)n;
            if (n < 0.0) {
                --n2;
            }
            final double n3 = n - n2;
            --n2;
            double n4 = 0.9375 + csevl(2.0 * n3 - 1.0, Sfun.gamma_coef);
            if (n2 == 0) {
                return n4;
            }
            if (n2 >= 0) {
                double n5 = 1.0;
                for (int i = 1; i <= n2; ++i) {
                    n4 *= n3 + n5;
                    ++n5;
                }
                return n4;
            }
            final int n6 = -n2;
            if (n == 0.0) {
                return Double.NaN;
            }
            if (n3 < 5.56302416559456E-309) {
                return Double.POSITIVE_INFINITY;
            }
            final double n7 = n6 - 2;
            if (n < 0.0 && n + n7 == 0.0) {
                return Double.NaN;
            }
            double n8 = 0.0;
            for (int j = 1; j <= n6; ++j) {
                n4 /= n + n8;
                ++n8;
            }
            return n4;
        }
        else {
            if (n > 171.614) {
                return Double.POSITIVE_INFINITY;
            }
            if (n < -170.56) {
                return 0.0;
            }
            final double exp = Math.exp((abs - 0.5) * Math.log(abs) - abs + 0.9189385332046728 + logGammaCorrection(abs));
            if (n > 0.0) {
                return exp;
            }
            final double sin = Math.sin(3.141592653589793 * abs);
            if (sin == 0.0) {
                return Double.NaN;
            }
            return -3.141592653589793 / (abs * sin * exp);
        }
    }
    
    public static double log10(final double n) {
        return 0.4342944819032518 * Math.log(n);
    }
    
    public static double logGamma(final double n) {
        final double abs = Math.abs(n);
        double log;
        if (abs <= 10.0) {
            log = Math.log(Math.abs(gamma(n)));
        }
        else if (n > 0.0) {
            log = 0.9189385332046728 + (n - 0.5) * Math.log(n) - n + logGammaCorrection(abs);
        }
        else {
            final double abs2 = Math.abs(Math.sin(3.141592653589793 * abs));
            if (abs2 == 0.0) {
                return Double.NaN;
            }
            log = 0.22579135264472744 + (n - 0.5) * Math.log(abs) - n - Math.log(abs2) - logGammaCorrection(abs);
        }
        return log;
    }
    
    static double logGammaCorrection(final double n) {
        if (n < 10.0) {
            return Double.NaN;
        }
        if (n >= 1.39118E11) {
            return 0.0;
        }
        if (n >= Sfun.logGammaCorrection_xbig) {
            return 1.0 / (12.0 * n);
        }
        return csevl(2.0 * Math.pow(10.0 / n, 2.0) - 1.0, Sfun.logGammaCorrection_coef) / n;
    }
    
    public static int nearestInteger(final double n) {
        return (int)((n < 0.0) ? (n - 0.5) : (n + 0.5));
    }
    
    public static double sign(final double n, final double n2) {
        final double n3 = (n < 0.0) ? (-n) : n;
        return (n2 < 0.0) ? (-n3) : n3;
    }
    
    public static double sinh(final double n) {
        final double abs = Math.abs(n);
        double n2;
        if (abs <= 1.0) {
            if (abs <= Sfun.sinh_sqeps) {
                n2 = n;
            }
            else {
                n2 = n * (1.0 + csevl(2.0 * n * n - 1.0, Sfun.sinh_coef));
            }
        }
        else {
            final double exp = Math.exp(abs);
            if (Double.isInfinite(exp)) {
                return (n < 0.0) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            }
            if (exp >= Sfun.sinh_ymax) {
                n2 = sign(0.5 * exp, n);
            }
            else {
                n2 = sign(0.5 * (exp - 1.0 / exp), n);
            }
        }
        return n2;
    }
    
    public static double tanh(final double n) {
        final double abs = Math.abs(n);
        double n2;
        if (abs <= 1.0) {
            if (abs <= Sfun.tanh_sqeps) {
                n2 = n;
            }
            else {
                n2 = n * (1.0 + csevl(2.0 * n * n - 1.0, Sfun.tanh_coef));
            }
        }
        else if (abs <= Sfun.tanh_xmax) {
            final double exp = Math.exp(abs);
            n2 = sign((exp - 1.0 / exp) / (exp + 1.0 / exp), n);
        }
        else {
            n2 = sign(1.0, n);
        }
        return n2;
    }
}
