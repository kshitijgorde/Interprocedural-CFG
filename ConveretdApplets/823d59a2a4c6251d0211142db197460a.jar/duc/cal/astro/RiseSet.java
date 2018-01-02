// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

public class RiseSet
{
    private static final double[] addCoeff;
    private static final double[] addConst;
    private static final double[] addends;
    private static final double[] addFactor;
    private static final double[] coeff17th;
    private static final double[] coeff18th;
    private static final double[] coeff19th;
    private static final double[] coeffa;
    private static final double[] coeffAnomaly;
    private static final double[] coeffApprox;
    private static final double[] coeffb;
    private static final double[] coeffCapE;
    private static final double[] coeffCapOmega;
    private static final double[] coeffEccentricity;
    private static final int[] coefficients;
    private static final double[] coeffLongitude;
    private static final double[] coeffLunarAnomaly;
    private static final double[] coeffMoonArgument;
    private static final double[] coeffObliquity;
    private static final double[] coeffSolarAnomaly;
    private static final byte[] EFactor;
    private static final double[] extra;
    private static final byte[] lunarCoeff;
    private static final byte[] moonCoeff;
    private static final double[] multipliers;
    private static final double[] sineCoeff;
    private static final byte[] solarCoeff;
    
    static {
        addCoeff = new double[] { 0.016321, 26.641886, 36.412478, 18.206239, 53.303771, 2.453732, 7.30686, 27.261239, 0.121824, 1.844379, 24.198154, 25.513099, 3.592518 };
        addConst = new double[] { 251.88, 251.83, 349.42, 84.66, 141.74, 207.14, 154.84, 34.52, 207.19, 291.34, 161.72, 239.56, 331.55 };
        addends = new double[] { 270.54861, 340.19128, 63.91854, 331.2622, 317.843, 86.631, 240.052, 310.26, 247.23, 260.87, 297.82, 343.14, 166.79, 81.53, 3.5, 132.75, 182.95, 162.03, 29.8, 266.4, 249.2, 157.6, 257.8, 185.1, 69.9, 8.0, 197.1, 250.4, 65.3, 162.7, 341.5, 291.6, 98.5, 146.7, 110.0, 5.2, 342.6, 230.9, 256.1, 45.3, 242.9, 115.2, 151.8, 285.3, 53.3, 126.6, 205.7, 85.9, 146.1 };
        addFactor = new double[] { 1.65E-4, 1.64E-4, 1.26E-4, 1.1E-4, 6.2E-5, 6.0E-5, 5.6E-5, 4.7E-5, 4.2E-5, 4.0E-5, 3.7E-5, 3.5E-5, 2.3E-5 };
        coeff17th = new double[] { 196.58333, -4.0675, 0.0219167 };
        coeff18th = new double[] { -9.0E-6, 0.003844, 0.083563, 0.865736, 4.867575, 15.845535, 31.332267, 38.291999, 28.316289, 11.636204, 2.043794 };
        coeff19th = new double[] { -2.0E-5, 2.97E-4, 0.025184, -0.181133, 0.55304, -0.861938, 0.677066, -0.212591 };
        coeffa = new double[] { 124.9, -1934.134, 0.002063 };
        coeffAnomaly = new double[] { 357.5291, 35999.0503, -1.559E-4, -4.8E-7 };
        coeffApprox = new double[] { 730125.59765, 36524.90882283305, 1.337E-4, -1.5E-7, 7.3E-10 };
        coeffb = new double[] { 201.11, 72001.5377, 5.7E-4 };
        coeffCapE = new double[] { 1.0, -0.002516, -7.4E-6 };
        coeffCapOmega = new double[] { 124.7746, -1934.1313612299998, 0.0020691, 2.15E-6 };
        coeffEccentricity = new double[] { 0.016708617, -4.2037E-5, -1.236E-7 };
        coefficients = new int[] { 403406, 195207, 119433, 112392, 3891, 2819, 1721, 660, 350, 334, 314, 268, 242, 234, 158, 132, 129, 114, 99, 93, 86, 78, 72, 68, 64, 46, 38, 37, 32, 29, 28, 27, 27, 25, 24, 21, 21, 20, 18, 17, 14, 13, 13, 13, 12, 10, 10, 10, 10 };
        coeffLongitude = new double[] { 280.46645, 36000.76983, 3.032E-4 };
        coeffLunarAnomaly = new double[] { 201.5643, 477197.67640106793, 0.0107438, 1.239E-5, -5.8E-8 };
        coeffMoonArgument = new double[] { 160.7108, 483200.81131396897, -0.0016341, -2.27E-6, 1.1E-8 };
        coeffObliquity = new double[] { 0.0, angle(0.0, 0.0, -46.815), angle(0.0, 0.0, -5.9E-4), angle(0.0, 0.0, 0.001813) };
        coeffSolarAnomaly = new double[] { 2.5534, 35998.960422026496, -2.18E-5, -1.1E-7 };
        EFactor = new byte[] { 0, 1, 0, 0, 1, 1, 2, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        extra = new double[] { 299.77, 132.8475848, -0.009173 };
        lunarCoeff = new byte[] { 1, 0, 2, 0, 1, 1, 0, 1, 1, 2, 3, 0, 0, 2, 1, 2, 0, 1, 2, 1, 1, 1, 3, 4 };
        moonCoeff = new byte[] { 0, 0, 0, 2, 0, 0, 0, -2, 2, 0, 0, 2, -2, 0, 0, -2, 0, -2, 2, 2, 2, -2, 0, 0 };
        multipliers = new double[] { 0.9287892, 35999.1376958, 35999.4089666, 35998.7287385, 71998.20261, 71998.4403, 36000.35726, 71997.4812, 32964.4678, -19.441, 445267.1117, 45036.884, 3.1008, 22518.4434, -19.9739, 65928.9345, 9038.0293, 3034.7684, 33718.148, 3034.448, -2280.773, 29929.992, 31556.493, 149.588, 9037.75, 107997.405, -4444.176, 151.771, 67555.316, 31556.08, -4561.54, 107996.706, 1221.655, 62894.167, 31437.369, 14578.298, -31931.757, 34777.243, 1221.999, 62894.511, -4442.039, 107997.909, 119.066, 16859.071, -4.578, 26895.292, -39.127, 12297.536, 90073.778 };
        sineCoeff = new double[] { -0.4072, 0.17241, 0.01608, 0.01039, 0.00739, -0.00514, 0.00208, -0.00111, -5.7E-4, 5.6E-4, -4.2E-4, 4.2E-4, 3.8E-4, -2.4E-4, -7.0E-5, 4.0E-5, 4.0E-5, 3.0E-5, 3.0E-5, -3.0E-5, 3.0E-5, -2.0E-5, -2.0E-5, 2.0E-5 };
        solarCoeff = new byte[] { 0, 1, 0, 0, -1, 1, 2, 0, 0, 1, 0, 1, 1, -1, 2, 0, 3, 1, 0, 1, -1, -1, 1, 0 };
    }
    
    private static double aberration(final double d) {
        final double d2 = julianCenturies(d);
        return 9.74E-5 * mathCosDegrees(177.63 + 35999.01848 * d2) - 0.005575;
    }
    
    private static double angle(final double d, final double d1, final double d2) {
        return d + (d1 + d2 / 60.0) / 60.0;
    }
    
    private static double dawn(final long l, final Location location, final double d) {
        double d2;
        try {
            d2 = momentFromDepression(l + 0.25, location, d);
        }
        catch (Throwable _ex) {
            d2 = l;
        }
        final double d3 = momentFromDepression(d2, location, d);
        return standardFromLocal(d3, location);
    }
    
    private static double degrees(final double theta) {
        return mod(theta, 360.0);
    }
    
    private static double degreesToRadians(final double theta) {
        return degrees(theta) * 3.141592653589793 / 180.0;
    }
    
    private static double dusk(final long l, final Location location, final double d) {
        double d2;
        try {
            d2 = momentFromDepression(l + 0.75, location, d);
        }
        catch (Throwable _ex) {
            d2 = l + 0.99;
        }
        final double d3 = momentFromDepression(d2, location, d);
        return standardFromLocal(d3, location);
    }
    
    private static double dynamicalFromUniversal(final double d) {
        return d + ephemerisCorrection(d);
    }
    
    private static double ephemerisCorrection(final double moment) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: dload_0         /* moment */
        //     1: ldc2_w          365.25
        //     4: ddiv           
        //     5: invokestatic    java/lang/Math.round:(D)J
        //     8: l2i            
        //     9: istore_2        /* year */
        //    10: iconst_1       
        //    11: bipush          7
        //    13: iload_2         /* year */
        //    14: invokestatic    duc/cal/CalUtil.jdFromDate:(III)I
        //    17: iconst_1       
        //    18: iconst_1       
        //    19: sipush          1900
        //    22: invokestatic    duc/cal/CalUtil.jdFromDate:(III)I
        //    25: isub           
        //    26: i2d            
        //    27: ldc2_w          36525.0
        //    30: ddiv           
        //    31: dstore_3        /* theta */
        //    32: sipush          1988
        //    35: iload_2         /* year */
        //    36: if_icmpgt       61
        //    39: iload_2         /* year */
        //    40: sipush          2019
        //    43: if_icmpgt       61
        //    46: iload_2         /* year */
        //    47: sipush          1933
        //    50: isub           
        //    51: i2d            
        //    52: ldc2_w          86400.0
        //    55: ddiv           
        //    56: dstore          result
        //    58: goto            189
        //    61: sipush          1900
        //    64: iload_2         /* year */
        //    65: if_icmpgt       87
        //    68: iload_2         /* year */
        //    69: sipush          1987
        //    72: if_icmpgt       87
        //    75: dload_3         /* theta */
        //    76: getstatic       duc/cal/astro/RiseSet.coeff19th:[D
        //    79: invokestatic    duc/cal/astro/RiseSet.poly:(D[D)D
        //    82: dstore          result
        //    84: goto            189
        //    87: sipush          1800
        //    90: iload_2         /* year */
        //    91: if_icmpgt       113
        //    94: iload_2         /* year */
        //    95: sipush          1899
        //    98: if_icmpgt       113
        //   101: dload_3         /* theta */
        //   102: getstatic       duc/cal/astro/RiseSet.coeff18th:[D
        //   105: invokestatic    duc/cal/astro/RiseSet.poly:(D[D)D
        //   108: dstore          result
        //   110: goto            189
        //   113: sipush          1620
        //   116: iload_2         /* year */
        //   117: if_icmpgt       148
        //   120: iload_2         /* year */
        //   121: sipush          1799
        //   124: if_icmpgt       148
        //   127: iload_2         /* year */
        //   128: sipush          1600
        //   131: isub           
        //   132: i2d            
        //   133: getstatic       duc/cal/astro/RiseSet.coeff17th:[D
        //   136: invokestatic    duc/cal/astro/RiseSet.poly:(D[D)D
        //   139: ldc2_w          86400.0
        //   142: ddiv           
        //   143: dstore          result
        //   145: goto            189
        //   148: ldc2_w          0.5
        //   151: iconst_1       
        //   152: iconst_1       
        //   153: iload_2         /* year */
        //   154: invokestatic    duc/cal/CalUtil.jdFromDate:(III)I
        //   157: i2d            
        //   158: dadd           
        //   159: iconst_1       
        //   160: iconst_1       
        //   161: sipush          1810
        //   164: invokestatic    duc/cal/CalUtil.jdFromDate:(III)I
        //   167: i2d            
        //   168: dsub           
        //   169: dstore          x
        //   171: dload           x
        //   173: dload           x
        //   175: dmul           
        //   176: ldc2_w          4.104848E7
        //   179: ddiv           
        //   180: ldc2_w          15.0
        //   183: dsub           
        //   184: ldc2_w          86400.0
        //   187: ddiv           
        //   188: dreturn        
        //   189: dload           5
        //   191: dreturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------
        //  0      192     0     moment  D
        //  10     182     2     year    I
        //  32     160     3     theta   D
        //  58     3       5     result  D
        //  84     3       5     result  D
        //  110    3       5     result  D
        //  145    3       5     result  D
        //  171    18      7     x       D
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static double equationOfTime(final double d) {
        final double d2 = julianCenturies(d);
        final double d3 = poly(d2, RiseSet.coeffLongitude);
        final double d4 = poly(d2, RiseSet.coeffAnomaly);
        final double d5 = poly(d2, RiseSet.coeffEccentricity);
        final double d6 = obliquity(d);
        final double x = mathTanDegrees(d6 / 2.0);
        final double d7 = x * x;
        final double d8 = 0.15915494309189535 * (d7 * mathSinDegrees(2.0 * d3) + -2.0 * d5 * mathSinDegrees(d4) + 4.0 * d5 * d7 * mathSinDegrees(d4) * mathCosDegrees(2.0 * d3) + -0.5 * d7 * d7 * mathSinDegrees(4.0 * d3) + -1.25 * d5 * d5 * mathSinDegrees(2.0 * d4));
        return signum(d8) * Math.min(Math.abs(d8), 0.5);
    }
    
    private static double julianCenturies(final double d) {
        return (dynamicalFromUniversal(d) - 730120.5) / 36525.0;
    }
    
    private static double localFromApparent(final double d) {
        return d - equationOfTime(d);
    }
    
    private static double mathAcosDegrees(final double d) {
        return radiansToDegrees(Math.acos(d));
    }
    
    private static double mathAsinDegrees(final double d) {
        return radiansToDegrees(Math.asin(d));
    }
    
    private static double mathAtanDegrees(final double d, final int i) {
        final double d2 = radiansToDegrees(Math.atan(d));
        return mod((i != 1 && i != 4) ? (d2 + 180.0) : d2, 360.0);
    }
    
    private static double mathCosDegrees(final double d) {
        return Math.cos(degreesToRadians(d));
    }
    
    private static double mathSinDegrees(final double d) {
        return Math.sin(degreesToRadians(d));
    }
    
    private static double mathTanDegrees(final double d) {
        return Math.tan(degreesToRadians(d));
    }
    
    private static double mod(final double x, final double y) {
        return x - y * Math.floor(x / y);
    }
    
    private static double momentFromDepression(final double d, final Location location, final double d1) {
        final double d2 = location.latitude;
        final double d3 = universalFromLocal(d, location);
        final double sl1 = solarLongitude(d3);
        final double d4 = mathAsinDegrees(mathSinDegrees(obliquity(d3)) * mathSinDegrees(sl1));
        final boolean flag = mod(d, 1.0) < 0.5;
        final double d5 = mathTanDegrees(d2) * mathTanDegrees(d4) + mathSinDegrees(d1) / (mathCosDegrees(d4) * mathCosDegrees(d2));
        final double d6 = mod(0.5 + mathAsinDegrees(d5) / 360.0, 1.0) - 0.5;
        if (Math.abs(d5) > 1.0) {
            throw new RuntimeException("Bogus time!");
        }
        return localFromApparent(Math.floor(d) + (flag ? (0.25 - d6) : (0.75 + d6)));
    }
    
    public static double nthNewMoon(final long l) {
        final double d = l - 24724L;
        final double d2 = d / 1236.85;
        final double d3 = poly(d2, RiseSet.coeffApprox);
        final double d4 = poly(d2, RiseSet.coeffCapE);
        final double d5 = poly(d2, RiseSet.coeffSolarAnomaly);
        final double d6 = poly(d2, RiseSet.coeffLunarAnomaly);
        final double d7 = poly(d2, RiseSet.coeffMoonArgument);
        final double d8 = poly(d2, RiseSet.coeffCapOmega);
        double d9 = -1.7E-4 * mathSinDegrees(d8);
        for (int i = 0; i < RiseSet.sineCoeff.length; ++i) {
            d9 += RiseSet.sineCoeff[i] * Math.pow(d4, RiseSet.EFactor[i]) * mathSinDegrees(RiseSet.solarCoeff[i] * d5 + RiseSet.lunarCoeff[i] * d6 + RiseSet.moonCoeff[i] * d7);
        }
        double d10 = 0.0;
        for (int j = 0; j < RiseSet.addConst.length; ++j) {
            d10 += RiseSet.addFactor[j] * mathSinDegrees(RiseSet.addConst[j] + RiseSet.addCoeff[j] * d);
        }
        final double d11 = 3.25E-4 * mathSinDegrees(poly(d2, RiseSet.extra));
        return universalFromDynamical(d3 + d9 + d11 + d10);
    }
    
    private static double nutation(final double d) {
        final double d2 = julianCenturies(d);
        final double d3 = poly(d2, RiseSet.coeffa);
        final double d4 = poly(d2, RiseSet.coeffb);
        return -0.004778 * mathSinDegrees(d3) + -3.667E-4 * mathSinDegrees(d4);
    }
    
    private static double obliquity(final double d) {
        final double d2 = julianCenturies(d);
        return angle(23.0, 26.0, 21.448) + poly(d2, RiseSet.coeffObliquity);
    }
    
    private static double poly(final double d, final double[] a) {
        double result = a[0];
        for (int i = 1; i < a.length; ++i) {
            result += a[i] * Math.pow(d, i);
        }
        return result;
    }
    
    private static double radiansToDegrees(final double theta) {
        return degrees(theta / 3.141592653589793 * 180.0);
    }
    
    private static int signum(final double x) {
        if (x < 0.0) {
            return -1;
        }
        if (x > 0.0) {
            return 1;
        }
        return 0;
    }
    
    public static double solarLongitude(final double d) {
        final double c = julianCenturies(d);
        double sigma = 0.0;
        for (int i = 0; i < RiseSet.coefficients.length; ++i) {
            sigma += RiseSet.coefficients[i] * mathSinDegrees(RiseSet.multipliers[i] * c + RiseSet.addends[i]);
        }
        final double longitude = 282.7771834 + 36000.76953744 * c + 5.729577951308232E-6 * sigma;
        return mod(longitude + aberration(d) + nutation(d), 360.0);
    }
    
    private static double standardFromLocal(final double d, final Location location) {
        return standardFromUniversal(universalFromLocal(d, location), location);
    }
    
    private static double standardFromUniversal(final double d, final Location location) {
        return d + location.zone / 24.0;
    }
    
    public static double sunrise(final long l, final Location location) {
        final double d = Math.max(0.0, location.elevation);
        final double d2 = 6372000.0;
        final double d3 = mathAcosDegrees(d2 / (d2 + d));
        final double d4 = angle(0.0, 50.0, 0.0) + d3;
        return dawn(l, location, d4);
    }
    
    public static double sunset(final long l, final Location location) {
        final double d = Math.max(0.0, location.elevation);
        final double d2 = 6372000.0;
        final double d3 = mathAcosDegrees(d2 / (d2 + d));
        final double d4 = angle(0.0, 50.0, 0.0) + d3;
        return dusk(l, location, d4);
    }
    
    private static double universalFromDynamical(final double d) {
        return d - ephemerisCorrection(d);
    }
    
    private static double universalFromLocal(final double d, final Location location) {
        return d - location.longitude / 360.0;
    }
}
