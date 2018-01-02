// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.awt.Color;
import java.util.Random;

public class D
{
    public static final int P = 0;
    public static final int N = 1;
    public static final int U = 2;
    public static final int X = 3;
    public static final int T = 4;
    public static final int I = 5;
    public static final int O = 6;
    public static final int A = 7;
    public static final int G = 8;
    public static final int W = 9;
    public static final int F = 10;
    public static final int H = 11;
    public static final int R = 12;
    public static final int C = 13;
    public static final int M = 14;
    public static final int D = 15;
    public static final int E = 16;
    public static final int S = 17;
    public static final int B = 18;
    public static final int V = 19;
    public static final int L = 20;
    private static Random Q;
    private static final float[] K;
    private static final float[] J;
    
    public static int B(final int n) {
        if (n < 0) {
            return 0;
        }
        if (n > 255) {
            return 255;
        }
        return n;
    }
    
    public static int A(final int n, final int n2, final float n3) {
        return B((int)(n + n3 * (n2 - n)));
    }
    
    public static int A(final int n) {
        return ((n >> 16 & 0xFF) + (n >> 8 & 0xFF) + (n & 0xFF)) / 3;
    }
    
    public static boolean A(final int n, final int n2, final int n3) {
        final int n4 = n >> 16 & 0xFF;
        final int n5 = n >> 8 & 0xFF;
        final int n6 = n & 0xFF;
        final int n7 = n2 >> 16 & 0xFF;
        final int n8 = n2 >> 8 & 0xFF;
        final int n9 = n2 & 0xFF;
        return Math.abs(n4 - n7) <= n3 && Math.abs(n5 - n8) <= n3 && Math.abs(n6 - n9) <= n3;
    }
    
    public static int B(final int n, final int n2, final int n3) {
        return A(n, n2, n3, 255);
    }
    
    public static int A(final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n2 & ~n5) | A(n & n5, n2, n3, n4);
    }
    
    public static int A(int hsBtoRGB, final int n, final int n2, final int n3) {
        if (n2 == 0) {
            return hsBtoRGB;
        }
        int b = hsBtoRGB >> 24 & 0xFF;
        int n4 = hsBtoRGB >> 16 & 0xFF;
        int n5 = hsBtoRGB >> 8 & 0xFF;
        int n6 = hsBtoRGB & 0xFF;
        final int n7 = n >> 24 & 0xFF;
        final int n8 = n >> 16 & 0xFF;
        final int n9 = n >> 8 & 0xFF;
        final int n10 = n & 0xFF;
        switch (n2) {
            case 2: {
                n4 = Math.min(n4, n8);
                n5 = Math.min(n5, n9);
                n6 = Math.min(n6, n10);
                break;
            }
            case 3: {
                n4 = Math.max(n4, n8);
                n5 = Math.max(n5, n9);
                n6 = Math.max(n6, n10);
                break;
            }
            case 4: {
                n4 = B(n4 + n8);
                n5 = B(n5 + n9);
                n6 = B(n6 + n10);
                break;
            }
            case 5: {
                n4 = B(n8 - n4);
                n5 = B(n9 - n5);
                n6 = B(n10 - n6);
                break;
            }
            case 6: {
                n4 = B(Math.abs(n4 - n8));
                n5 = B(Math.abs(n5 - n9));
                n6 = B(Math.abs(n6 - n10));
                break;
            }
            case 7: {
                n4 = B(n4 * n8 / 255);
                n5 = B(n5 * n9 / 255);
                n6 = B(n6 * n10 / 255);
                break;
            }
            case 17: {
                if ((jmaster.util.D.A.D.Q.nextInt() & 0xFF) <= b) {
                    n4 = n8;
                    n5 = n9;
                    n6 = n10;
                    break;
                }
                break;
            }
            case 13: {
                n4 = (n4 + n8) / 2;
                n5 = (n5 + n9) / 2;
                n6 = (n6 + n10) / 2;
                break;
            }
            case 8:
            case 9:
            case 10:
            case 11: {
                Color.RGBtoHSB(n4, n5, n6, jmaster.util.D.A.D.K);
                Color.RGBtoHSB(n8, n9, n10, jmaster.util.D.A.D.J);
                switch (n2) {
                    case 8: {
                        jmaster.util.D.A.D.J[0] = jmaster.util.D.A.D.K[0];
                        break;
                    }
                    case 9: {
                        jmaster.util.D.A.D.J[1] = jmaster.util.D.A.D.K[1];
                        break;
                    }
                    case 10: {
                        jmaster.util.D.A.D.J[2] = jmaster.util.D.A.D.K[2];
                        break;
                    }
                    case 11: {
                        jmaster.util.D.A.D.J[0] = jmaster.util.D.A.D.K[0];
                        jmaster.util.D.A.D.J[1] = jmaster.util.D.A.D.K[1];
                        break;
                    }
                }
                hsBtoRGB = Color.HSBtoRGB(jmaster.util.D.A.D.J[0], jmaster.util.D.A.D.J[1], jmaster.util.D.A.D.J[2]);
                n4 = (hsBtoRGB >> 16 & 0xFF);
                n5 = (hsBtoRGB >> 8 & 0xFF);
                n6 = (hsBtoRGB & 0xFF);
                break;
            }
            case 12: {
                n4 = 255 - (255 - n4) * (255 - n8) / 255;
                n5 = 255 - (255 - n5) * (255 - n9) / 255;
                n6 = 255 - (255 - n6) * (255 - n10) / 255;
                break;
            }
            case 14: {
                n4 = ((255 - (255 - n4) * (255 - n8) / 255) * n4 + n4 * n8 / 255 * (255 - n4)) / 255;
                n5 = ((255 - (255 - n5) * (255 - n9) / 255) * n5 + n5 * n9 / 255 * (255 - n5)) / 255;
                n6 = ((255 - (255 - n6) * (255 - n10) / 255) * n6 + n6 * n10 / 255 * (255 - n6)) / 255;
                break;
            }
            case 15: {
                n5 = (n4 = (n6 = 255));
                break;
            }
            case 18: {
                return B(n7 * b / 255) << 24 | B(n8 * b / 255) << 16 | B(n9 * b / 255) << 8 | B(n10 * b / 255);
            }
            case 19: {
                return b * n7 / 255 << 24 | n8 << 16 | n9 << 8 | n10;
            }
            case 20: {
                final int n11 = 255 - b;
                return b << 24 | n11 << 16 | n11 << 8 | n11;
            }
        }
        if (n3 != 255 || b != 255) {
            final int n12 = b * n3 / 255;
            final int n13 = (255 - n12) * n7 / 255;
            n4 = B((n4 * n12 + n8 * n13) / 255);
            n5 = B((n5 * n12 + n9 * n13) / 255);
            n6 = B((n6 * n12 + n10 * n13) / 255);
            b = B(n12 + n13);
        }
        return b << 24 | n4 << 16 | n5 << 8 | n6;
    }
    
    static {
        jmaster.util.D.A.D.Q = new Random();
        K = new float[3];
        J = new float[3];
    }
}
