// 
// Decompiled by Procyon v0.5.30
// 

public class Bessel
{
    private static final double ACC = 40.0;
    private static final double BIGNO = 1.0E10;
    private static final double BIGNI = 1.0000000000000002E-10;
    
    public static void main(final String[] array) {
        for (double n = 0.0; n <= 2.0; n += 0.05) {
            System.out.println(" " + n + " " + bessi(1, n) + " " + bessi(2, n));
        }
    }
    
    public static double bessj(final int n, final double n2) {
        if (n == 0) {
            return bessj0(n2);
        }
        if (n == 1) {
            return bessj1(n2);
        }
        final double abs = Math.abs(n2);
        if (abs == 0.0) {
            return 0.0;
        }
        double n5;
        if (abs > n) {
            final double n3 = 2.0 / abs;
            double bessj0 = bessj0(abs);
            double bessj2 = bessj1(abs);
            for (int i = 1; i < n; ++i) {
                final double n4 = i * n3 * bessj2 - bessj0;
                bessj0 = bessj2;
                bessj2 = n4;
            }
            n5 = bessj2;
        }
        else {
            final double n6 = 2.0 / abs;
            final int n7 = 2 * ((n + (int)Math.sqrt(40.0 * n)) / 2);
            boolean b = false;
            double n10;
            double n9;
            double n8 = n9 = (n10 = 0.0);
            double n11 = 1.0;
            for (int j = n7; j > 0; --j) {
                final double n12 = j * n6 * n11 - n9;
                n9 = n11;
                n11 = n12;
                if (Math.abs(n11) > 1.0E10) {
                    n11 *= 1.0000000000000002E-10;
                    n9 *= 1.0000000000000002E-10;
                    n8 *= 1.0000000000000002E-10;
                    n10 *= 1.0000000000000002E-10;
                }
                if (b) {
                    n10 += n11;
                }
                b = !b;
                if (j == n) {
                    n8 = n9;
                }
            }
            n5 = n8 / (2.0 * n10 - n11);
        }
        if (n2 < 0.0 && n % 2 == 0) {
            return -n5;
        }
        return n5;
    }
    
    public static double bessi(final int n, final double n2) {
        if (n == 0) {
            return bessi0(n2);
        }
        if (n == 1) {
            return bessi1(n2);
        }
        if (n2 == 0.0) {
            return 0.0;
        }
        final double n3 = 2.0 / Math.abs(n2);
        double n5;
        double n4 = n5 = 0.0;
        double n6 = 1.0;
        for (int i = 2 * (n + (int)Math.sqrt(40.0 * n)); i > 0; --i) {
            final double n7 = n5 + i * n3 * n6;
            n5 = n6;
            n6 = n7;
            if (Math.abs(n6) > 1.0E10) {
                n4 *= 1.0000000000000002E-10;
                n6 *= 1.0000000000000002E-10;
                n5 *= 1.0000000000000002E-10;
            }
            if (i == n) {
                n4 = n5;
            }
        }
        final double n8 = n4 * (bessi0(n2) / n6);
        if (n2 < 0.0 && n % 2 == 0) {
            return -n8;
        }
        return n8;
    }
    
    private static double fabs(final double n) {
        return Math.abs(n);
    }
    
    private static double bessi0(final double n) {
        final double abs;
        double n4;
        if ((abs = Math.abs(n)) < 3.75) {
            final double n2 = n / 3.75;
            final double n3 = n2 * n2;
            n4 = 1.0 + n3 * (3.5156229000000003 + n3 * (3.0899424000000004 + n3 * (1.2067492 + n3 * (0.2659732000000001 + n3 * (0.0360768 + n3 * 0.004581300000000001)))));
        }
        else {
            final double n5 = 3.75 / abs;
            n4 = Math.exp(abs) / Math.sqrt(abs) * (0.39894228 + n5 * (0.01328592 + n5 * (0.00225319 + n5 * (-0.00157565 + n5 * (0.00916281 + n5 * (-0.02057706 + n5 * (0.02635537 + n5 * (-0.01647633 + n5 * 0.00392377))))))));
        }
        return n4;
    }
    
    private static double bessi1(final double n) {
        final double abs;
        double n4;
        if ((abs = Math.abs(n)) < 3.75) {
            final double n2 = n / 3.75;
            final double n3 = n2 * n2;
            n4 = abs * (0.5 + n3 * (0.87890594 + n3 * (0.51498869 + n3 * (0.15084934 + n3 * (0.02658733 + n3 * (0.00301532 + n3 * 3.2411E-4))))));
        }
        else {
            final double n5 = 3.75 / abs;
            n4 = (0.39894228 + n5 * (-0.039880240000000004 + n5 * (-0.00362018 + n5 * (0.00163801 + n5 * (-0.01031555 + n5 * (0.02282967 + n5 * (-0.028953120000000002 + n5 * (0.01787654 - n5 * 0.00420059)))))))) * (Math.exp(abs) / Math.sqrt(abs));
        }
        if (n < 0.0) {
            return -n4;
        }
        return n4;
    }
    
    private static double bessj0(final double n) {
        final double abs;
        double n3;
        if ((abs = Math.abs(n)) < 8.0) {
            final double n2 = n * n;
            n3 = (5.7568490574E10 + n2 * (-1.3362590354E10 + n2 * (6.516196407E8 + n2 * (-1.121442418E7 + n2 * (77392.33017000002 + n2 * -184.9052456))))) / (5.7568490411E10 + n2 * (1.029532985E9 + n2 * (9494680.718 + n2 * (59272.648530000006 + n2 * (267.85327120000005 + n2)))));
        }
        else {
            final double n4 = 8.0 / abs;
            final double n5 = n4 * n4;
            final double n6 = abs - 0.7853981640000001;
            n3 = Math.sqrt(0.636619772 / abs) * (Math.cos(n6) * (1.0 + n5 * (-0.001098628627 + n5 * (2.7345104070000003E-5 + n5 * (-2.0733706390000007E-6 + n5 * 2.0938872109999999E-7)))) - n4 * Math.sin(n6) * (-0.015624999950000003 + n5 * (1.430488765E-4 + n5 * (-6.911147651000001E-6 + n5 * (7.621095160999999E-7 - n5 * 9.34935152E-8)))));
        }
        return n3;
    }
    
    private static double bessj1(final double n) {
        final double abs;
        double n3;
        if ((abs = Math.abs(n)) < 8.0) {
            final double n2 = n * n;
            n3 = n * (7.2362614232E10 + n2 * (-7.895059235E9 + n2 * (2.4239685310000002E8 + n2 * (-2972611.4390000002 + n2 * (15704.482600000001 + n2 * -30.16036606))))) / (1.44725228442E11 + n2 * (2.300535178E9 + n2 * (1.8583304740000002E7 + n2 * (99447.43394000002 + n2 * (376.99913970000006 + n2)))));
        }
        else {
            final double n4 = 8.0 / abs;
            final double n5 = n4 * n4;
            final double n6 = abs - 2.356194491;
            n3 = Math.sqrt(0.636619772 / abs) * (Math.cos(n6) * (1.0 + n5 * (0.00183105 + n5 * (-3.516396496E-5 + n5 * (2.4575201740000003E-6 + n5 * -2.4033701900000003E-7)))) - n4 * Math.sin(n6) * (0.04687499995 + n5 * (-2.002690873E-4 + n5 * (8.449199096000001E-6 + n5 * (-8.822898700000001E-7 + n5 * 1.05787412E-7)))));
            if (n < 0.0) {
                n3 = -n3;
            }
        }
        return n3;
    }
}
